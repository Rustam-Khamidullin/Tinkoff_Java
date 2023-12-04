package edu.hw5;

public class Task5 {
    private static final String LICENCE_PLATE_RE = "[А-Я]\\d{3}[А-Я]{2}\\d{3}";

    private Task5() {
    }

    public static boolean isCorrectLicencePlate(String licencePlate) {
        if (licencePlate == null) {
            throw new NullPointerException();
        }
        return licencePlate.matches(LICENCE_PLATE_RE);
    }
}
