package fr.umlv.javainside;

import javax.annotation.processing.SupportedAnnotationTypes;

import static java.util.Objects.requireNonNull;

public record Book(@JSONProperty String book_title, int year) {
    public Book {
        requireNonNull(book_title);
    }
}
