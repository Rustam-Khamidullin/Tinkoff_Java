package edu.hw8;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import static edu.hw8.Task3.HackerMan.getMD5Hash;
import static edu.hw8.Task3.HackerMan.hack;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackerManTest {
    @Test
    public void testHack() {
        var passwords = new HashMap<String, String>();

        passwords.put("a.v.petrov", getMD5Hash("q3ds"));
        passwords.put("v.v.belov", getMD5Hash("Zf25"));
        passwords.put("a.s.ivanov", getMD5Hash("awe3"));
        passwords.put("k.p.maslov", getMD5Hash("10000"));
        passwords.put("k.p.maslova1", getMD5Hash("10001"));
        passwords.put("k.p.maslova2", getMD5Hash("10002"));
        passwords.put("k.p.maslova3", getMD5Hash("10003"));
        passwords.put("k.p.maslova4", getMD5Hash("10004"));
        passwords.put("k.p.maslova5", getMD5Hash("10005"));
        passwords.put("k.p.maslova6", getMD5Hash("10006"));
        passwords.put("k.p.maslova7", getMD5Hash("ZZZZZ"));

        var crackedPassword = hack(passwords);

        assertEquals(11, crackedPassword.size());
        assertEquals(getMD5Hash("q3ds"), getMD5Hash(crackedPassword.get("a.v.petrov")));
        assertEquals(getMD5Hash("Zf25"), getMD5Hash(crackedPassword.get("v.v.belov")));
        assertEquals(getMD5Hash("awe3"), getMD5Hash(crackedPassword.get("a.s.ivanov")));
        assertEquals(getMD5Hash("10000"), getMD5Hash(crackedPassword.get("k.p.maslov")));
        assertEquals(getMD5Hash("10001"), getMD5Hash(crackedPassword.get("k.p.maslova1")));
        assertEquals(getMD5Hash("10002"), getMD5Hash(crackedPassword.get("k.p.maslova2")));
        assertEquals(getMD5Hash("10003"), getMD5Hash(crackedPassword.get("k.p.maslova3")));
        assertEquals(getMD5Hash("10004"), getMD5Hash(crackedPassword.get("k.p.maslova4")));
        assertEquals(getMD5Hash("10005"), getMD5Hash(crackedPassword.get("k.p.maslova5")));
        assertEquals(getMD5Hash("10006"), getMD5Hash(crackedPassword.get("k.p.maslova6")));
        assertEquals(getMD5Hash("ZZZZZ"), getMD5Hash(crackedPassword.get("k.p.maslova7")));
    }
}
