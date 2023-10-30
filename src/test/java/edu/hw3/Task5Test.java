package edu.hw3;

import edu.hw3.task5.Contact;
import edu.hw3.task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {
    @Test
    @DisplayName("Sort contacts in ascending order")
    public void testParseContactsAscending() {
        List<String> input = List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes");
        List<Contact> expected = new ArrayList<>();
        expected.add(new Contact("Thomas Aquinas"));
        expected.add(new Contact("Rene Descartes"));
        expected.add(new Contact("David Hume"));
        expected.add(new Contact("John Locke"));
        assertIterableEquals(expected, Task5.parseContacts(input, "ASC"));
    }

    @Test
    @DisplayName("Sort contacts in descending order")
    public void testParseContactsDescending() {
        List<String> input = List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss");
        List<Contact> expected = new ArrayList<>();
        expected.add(new Contact("Carl Gauss"));
        expected.add(new Contact("Leonhard Euler"));
        expected.add(new Contact("Paul Erdos"));
        assertIterableEquals(expected, Task5.parseContacts(input, "DESC"));
    }

    @Test
    @DisplayName("Sort contacts without surname")
    public void testParseContactsWithoutSurname() {
        List<String> input = List.of("Paul F", "A", "Carl Gauss");
        List<Contact> expected = new ArrayList<>();
        expected.add(new Contact("A"));
        expected.add(new Contact("Paul F"));
        expected.add(new Contact("Carl Gauss"));
        assertIterableEquals(expected, Task5.parseContacts(input, "ASC"));
    }

    @Test
    @DisplayName("Sort empty array")
    public void testParseContactsEmptyArray() {
        List<String> input = List.of();
        List<String> expected = List.of();
        assertIterableEquals(expected, Task5.parseContacts(input, "ASC"));
    }

    @Test
    @DisplayName("Sort null array")
    public void testParseContactsNullArray() {
        List<String> input = null;
        List<String> expected = null;
        assertIterableEquals(expected, Task5.parseContacts(input, "ASC"));
    }

    @Test
    @DisplayName("Sort contacts with a number in the name")
    public void testParseContactsNumber() {
        List<String> input = List.of("Paul Erdos", "Leonhard Euler", "Carl Gaus11s");
        assertThrows(RuntimeException.class, () -> Task5.parseContacts(input, "ASC"));
    }

    @Test
    @DisplayName("Sort contacts with a lot of words")
    public void testParseContactsLotWords() {
        List<String> input = List.of("Paul Erdos Andrew", "Leonhard Euler", "Carl Gaus11s");
        assertThrows(RuntimeException.class, () -> Task5.parseContacts(input, "ASC"));
    }

    @Test
    @DisplayName("Sort contacts with a few words")
    public void testParseContactsFewWords() {
        List<String> input = List.of("", "Leonhard Euler", "Carl Gaus11s");
        assertThrows(RuntimeException.class, () -> Task5.parseContacts(input, "ASC"));
    }
}
