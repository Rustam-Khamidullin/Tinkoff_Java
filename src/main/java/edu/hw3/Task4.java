package edu.hw3;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import static java.util.Map.entry;

@SuppressWarnings("MagicNumber")
public class Task4 {

    private static final int UPPER_BOUND = 4000;
    private static final int LOWER_BOUND = 0;
    private static final SortedMap<Integer, String> CONVERT_TO_ROMAN_MAP = new TreeMap<>(Collections.reverseOrder());

    static {
        CONVERT_TO_ROMAN_MAP.putAll(Map.ofEntries(
            entry(1000, "M"),
            entry(900, "CM"),
            entry(500, "D"),
            entry(400, "CD"),
            entry(100, "C"),
            entry(90, "XC"),
            entry(50, "L"),
            entry(40, "XL"),
            entry(10, "X"),
            entry(9, "IX"),
            entry(5, "V"),
            entry(4, "IV"),
            entry(1, "I")
        ));
    }

    private Task4() {
    }

    public static String convertToRoman(int num) {
        if (num <= LOWER_BOUND || num >= UPPER_BOUND) {
            return null;
        }

        int x = num;

        var result = new StringBuilder();

        for (var dijit : CONVERT_TO_ROMAN_MAP.entrySet()) {
            while (x >= dijit.getKey()) {
                x -= dijit.getKey();
                result.append(dijit.getValue());
            }
        }

        return result.toString();
    }
}
