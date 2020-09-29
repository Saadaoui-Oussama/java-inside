package fr.umlv.json;

import fr.umlv.javainside.*;
import org.junit.jupiter.api.Test;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

public class JSONTest {

    public record Person(String firstName, String lastName) {
        public Person {
            requireNonNull(firstName);
            requireNonNull(lastName);
        }
    }

    public record Alien(int age, String planet) {
        public Alien {
            if (age < 0) {
                throw new IllegalArgumentException("Negative age");
            }
            requireNonNull(planet);
        }
    }

    public record Book(@JSONProperty String book_title, int year) {
        public Book {
            requireNonNull(book_title);
        }
    }

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

    @Test
    public void testBook() {
        var book = new Book("Liberia", 1989);
        assertEquals(IncompleteJSONParser.parse("{\"book-title\":\"Liberia\",\"year\":1989}"), IncompleteJSONParser.parse(JSONPrinter.toJSON(book)));

    }

}
