package edu.project1;

import java.util.Random;

public class Dictionary {
    private static final String[] WORDS = {
        "Cherry",
        "Dog",
        "Elephant",
        "Flower",
        "Guitar",
        "Helicopter",
        "Jaguar"};

    private Dictionary() {
    }

    public static String getRandomWord() {
        var random = new Random();
        return WORDS[random.nextInt(WORDS.length)];
    }
}
