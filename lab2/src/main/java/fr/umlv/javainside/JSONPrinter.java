package fr.umlv.javainside;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JSONPrinter {

    private static Object invokeAccessor(Method accessor, Record record) {
        try {
            return accessor.invoke(record);
        } catch (IllegalAccessException e) {
            throw (IllegalAccessError) new IllegalAccessError().initCause(e);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException re)
                throw re;
            if (cause instanceof  Error error)
                throw error;
            else
                throw new UndeclaredThrowableException(cause);
        }
    }

    public static String toJSON(Record record) {
        var t = record.getClass();
        var tab = t.getRecordComponents();

        return Arrays.stream(tab)
                .map(recordComponent -> {
                    var recordName = (recordComponent.isAnnotationPresent(JSONProperty.class)) ? recordComponent.getAnnotation(JSONProperty.class).value() : recordComponent.getName();
                    var recordVal = invokeAccessor(recordComponent.getAccessor(), record);
                    var mark = (recordVal instanceof String) ? "\"" : "";

                    return "\""+recordName+"\""+":"
                            +mark+invokeAccessor(recordComponent.getAccessor(), record).toString()+mark;
                })
                .collect(Collectors.joining(",", "{", "}"));
    }
}
