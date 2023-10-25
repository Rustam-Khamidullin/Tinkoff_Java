package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Task5 {
    private static final String CONTACT_RE = "^[A-Za-z\\-]+( [A-Za-z\\-]+)?$";
    private static final Comparator<String> COMPARE_CONTACTS = (o1, o2) -> {
        String[] s1 = o1.split(" ");
        String[] s2 = o2.split(" ");

        return s1[s1.length - 1].compareTo(s2[s2.length - 1]);
    };

    private Task5() {
    }

    public static List<String> parseContacts(List<String> contacts, String order) {
        if (contacts == null) {
            return null;
        }

        for (var contact : contacts) {
            if (!contact.matches(CONTACT_RE)) {
                throw new RuntimeException("Incorrect contact");
            }
        }

        ArrayList<String> result = new ArrayList<>(contacts);

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
