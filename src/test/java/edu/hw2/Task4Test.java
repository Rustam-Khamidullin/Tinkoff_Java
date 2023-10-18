package edu.hw2;

import edu.hw2.task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("CollingInfo from tests")
    void testCollingInfo() {
        assertThat(Task4.getCollingInfo()).isEqualTo(new Task4.CollingInfo("com.intellij.rt.junit.JUnitStarter", "main"));
    }
}
