package edu.hw3;

import edu.hw3.task7.NullComparator;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    public void TestCode() {

        TreeMap<String, String> tree = new TreeMap<>(new NullComparator<>());
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }

}
