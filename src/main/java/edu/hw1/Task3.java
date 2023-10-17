package edu.hw1;

public class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        if ((arr1 == null) || (arr2 == null) || (arr1.length == 0) || (arr2.length == 0)) {
            return false;
        }

        int min1 = arr1[0];
        int max1 = arr1[0];
        int min2 = arr2[0];
        int max2 = arr2[0];

        for (int i = 1; i < arr1.length; i++) {
            if (arr1[i] < min1) {
                min1 = arr1[i];
            }
            if (arr1[i] > max1) {
                max1 = arr1[i];
            }
        }
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[i] < min2) {
                min2 = arr2[i];
            }
            if (arr2[i] > max2) {
                max2 = arr2[i];
            }
        }

        return min2 < min1 && max2 > max1;
    }
}
