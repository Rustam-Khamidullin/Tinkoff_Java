package edu.hw8.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;

public class HackerMan {
    private static final int THREADS = 6;
    private static final int PASSWORD_MAX_LEN = 5;
    private static final Map<Character, Integer> CHAR_TO_INT = new HashMap<>();
    private static final List<Character> INT_TO_CHAR = new ArrayList<>();
    private static final int SYM_NUMBER;

    static {
        int current = 0;
        for (char i = '0'; i <= '9'; i++) {
            INT_TO_CHAR.add(i);
            CHAR_TO_INT.put(i, current++);
        }
        for (char i = 'a'; i <= 'z'; i++) {
            INT_TO_CHAR.add(i);
            CHAR_TO_INT.put(i, current++);
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            INT_TO_CHAR.add(i);
            CHAR_TO_INT.put(i, current++);
        }
        SYM_NUMBER = current;
    }

    private HackerMan() {
    }

    public static Map<String, String> hack(Map<String, String> passwords) {
        Set<String> hashes = new HashSet<>(passwords.values());
        List<Future<Map<String, String>>> hackedPasswordsFuture = new ArrayList<>();

        try (ExecutorService executorService = Executors.newFixedThreadPool(THREADS)) {
            for (int i = 0; i < THREADS; i++) {
                int offset = i + 1;
                hackedPasswordsFuture.add(executorService.submit(() -> {
                    Map<String, String> hacked = new HashMap<>();

                    var currentPasswd = nextPassword("", offset);

                    while (currentPasswd.length() <= PASSWORD_MAX_LEN) {
                        var hash = getMD5Hash(currentPasswd);

                        if (hashes.contains(hash)) {
                            hacked.put(hash, currentPasswd);
                        }

                        currentPasswd = nextPassword(currentPasswd, THREADS);
                    }

                    return hacked;
                }));
            }

            executorService.shutdown();

            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException ignored) {
            }
        }

        var flatHackedPasswords = hackedPasswordsFuture.stream()
            .map(mapFuture -> {
                try {
                    return mapFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            })
            .flatMap(map -> map.entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<String, String> hackedUsers = new HashMap<>();

        for (var entry : passwords.entrySet()) {
            hackedUsers.put(entry.getKey(), flatHackedPasswords.getOrDefault(entry.getValue(), null));
        }

        return hackedUsers;
    }

    private static String nextPassword(String password) {
        return nextPassword(password, 1);
    }

    private static String nextPassword(String password, int step) {
        if ((step >= SYM_NUMBER)) {
            throw new AssertionError();
        }

        if (password.isEmpty()) {
            return INT_TO_CHAR.get(step - 1).toString();
        }

        int offset = CHAR_TO_INT.get(password.charAt(password.length() - 1)) + step;

        if (offset >= SYM_NUMBER) {
            return nextPassword(password.substring(0, password.length() - 1)) + INT_TO_CHAR.get(offset % SYM_NUMBER);
        }
        return password.substring(0, password.length() - 1) + INT_TO_CHAR.get(offset);
    }

    public static String getMD5Hash(String st) {
        return DigestUtils.md5Hex(st);
    }
        /*
        5 letters

        THREADS = 1
             ms: 168167

        THREADS = 6
             ms: 56378
         */

}


