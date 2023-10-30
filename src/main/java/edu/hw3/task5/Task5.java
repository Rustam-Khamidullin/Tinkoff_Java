package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Task5 {
    private static final String CONTACT_RE = "^[A-Za-z\\-]+( [A-Za-z\\-]+)?$";
    private static final Comparator<Contact> COMPARE_CONTACTS =
        Comparator.comparing(o -> (o.getLastName() + o.getFirstName()));

    private Task5() {
    }

    public static List<Contact> parseContacts(List<String> contacts, String order) {
        if (contacts == null) {
            return null;
        }

        ArrayList<Contact> result = new ArrayList<>();

        for (var contact : contacts) {
            if (!contact.matches(CONTACT_RE)) {
                throw new RuntimeException("Incorrect contact");
            } else {
                result.add(new Contact(contact));
            }
        }

        if (order.equals("ASC")) {
            result.sort(COMPARE_CONTACTS);
        } else if (order.equals("DESC")) {
            result.sort(Collections.reverseOrder(COMPARE_CONTACTS));
        } else {
            return null;
        }

        return result;
    }
}
