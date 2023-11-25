package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task3Test {
    @Test
    public void personDatabaseTest() {
        var bd = new PersonDatabase();

        var writer = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                bd.add(new Person(i, String.valueOf(i), String.valueOf(i), String.valueOf(i)));
            }
        });
        var reader = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                var byName = bd.findByName(String.valueOf(i));
                var byAddress = bd.findByAddress(String.valueOf(i));
                var byPhone = bd.findByPhone(String.valueOf(i));

                if (byName == null) {
                    assertNull(byAddress);
                    assertNull(byPhone);
                } else {
                    assertNotNull(byAddress);
                    assertNotNull(byPhone);
                    assertEquals(byName.get(0), byAddress.get(0));
                    assertEquals(byName.get(0), byPhone.get(0));
                }
            }
        });

        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException ignored) {
        }
    }

    @Test
    public void personDatabaseReadWriteLockTest() {
        var bd = new PersonDatabaseReadWriteLock();

        var writer = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                bd.add(new Person(i, String.valueOf(i), String.valueOf(i), String.valueOf(i)));
            }
        });
        var reader = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                var byName = bd.findByName(String.valueOf(i));
                var byAddress = bd.findByAddress(String.valueOf(i));
                var byPhone = bd.findByPhone(String.valueOf(i));

                if (byName == null) {
                    assertNull(byAddress);
                    assertNull(byPhone);
                } else {
                    assertNotNull(byAddress);
                    assertNotNull(byPhone);
                    assertEquals(byName.get(0), byAddress.get(0));
                    assertEquals(byName.get(0), byPhone.get(0));
                }
            }
        });

        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException ignored) {
        }
    }
}
