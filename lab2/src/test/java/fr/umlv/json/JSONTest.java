package fr.umlv.json;

import fr.umlv.javainside.Alien;
import fr.umlv.javainside.IncompleteJSONParser;
import fr.umlv.javainside.JSONPrinter;
import fr.umlv.javainside.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JSONTest {

    @Test
    public void testPerson() {
        var person = new Person("John", "Doe");
        assertEquals(IncompleteJSONParser.parse("{\"firstName\":\"John\",\"lastName\":\"Doe\"}"), IncompleteJSONParser.parse(JSONPrinter.toJSON(person)));
    }

    @Test
    public void testAlien() {
        var alien = new Alien(100, "Saturn");
        assertEquals(IncompleteJSONParser.parse("{\"age\":100,\"planet\":\"Saturn\"}"), IncompleteJSONParser.parse(JSONPrinter.toJSON(alien)));

    }

}
