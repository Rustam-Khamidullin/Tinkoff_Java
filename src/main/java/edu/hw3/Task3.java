package edu.hw3;

import java.util.HashMap;
import java.util.List;

public class Task3 {
    private Task3() {
    }

    public static <K> HashMap<K, Integer> freqDict(List<K> list) {
        if (list == null) {
            return null;
        }
        var result = new HashMap<K, Integer>();

        for (var elem : list) {
            if (result.containsKey(elem)) {
                result.put(elem, result.get(elem) + 1);
            } else {
                result.put(elem, 1);
            }
        }

        return result;
    }
}
