package edu.hw3;

import java.util.ArrayList;

public class Task2 {
    private Task2() {
    }

    public static String[] clusterize(String input) {
        if (input == null) {
            return null;
        }

        var result = new ArrayList<String>();
        var currentCluster = new StringBuilder();
        long currentNumberInCluster = 0;

        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if ((symbol != '(') && (symbol != ')')) {
                return null;
            }

            if (symbol == '(') {
                currentNumberInCluster++;
            } else {
                currentNumberInCluster--;
            }

            currentCluster.append(symbol);

            if (currentNumberInCluster < 0) {
                return null;
            } else if (currentNumberInCluster == 0) {
                result.add(currentCluster.toString());
                currentCluster.setLength(0);
            }
        }
        return result.toArray(new String[0]);
    }
}
