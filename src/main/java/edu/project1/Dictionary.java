package edu.project1;

import java.util.Random;

public class Dictionary {
    private static final String[] words = {
        "Cherry",
        "Dog",
        "Elephant",
        "Flower",
        "Guitar",
        "Helicopter",
        "Jaguar"};

    public static String getRandomWord() {
        var random = new Random();
        return words[random.nextInt(words.length)];
    }
}
