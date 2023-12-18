package edu.hw10;

import edu.hw10.task1.MyClass;
import edu.hw10.task1.MyRecord;
import edu.hw10.task1.RandomObjectGenerator;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    public void testRandomObjectGenerator() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        try {
            MyRecord record = rog.nextObject(MyRecord.class);
            MyClass class1 = rog.nextObject(MyClass.class);
            MyClass class2 = rog.nextObject(MyClass.class, "create");

            System.out.println(record);
            System.out.println();


            System.out.println(class1.getFirst());
            System.out.println(class1.getSecond());
            System.out.println(class1.getThird());
            System.out.println(class1.getString());
            System.out.println();

            System.out.println(class2.getFirst());
            System.out.println(class2.getSecond());
            System.out.println(class2.getThird());
            System.out.println(class2.getString());
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
