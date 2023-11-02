package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Test BackwardIterator for Integer")
    public void testBackwardIteratorInt() {
        var iterator = new BackwardIterator<>(List.of(1,2,3));

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isFalse();
        assertThat(iterator.next()).isEqualTo(null);
    }

    @Test
    @DisplayName("Test BackwardIterator for String")
    public void testBackwardIteratorString() {
        var iterator = new BackwardIterator<>(List.of("a", "b", "c"));

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo("c");
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo("b");
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo("a");
        assertThat(iterator.hasNext()).isFalse();
        assertThat(iterator.next()).isEqualTo(null);
    }

    @Test
    @DisplayName("Test BackwardIterator for single element")
    public void testBackwardIteratorSingleElem() {
        var iterator = new BackwardIterator<>(List.of(1.5));

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1.5);
        assertThat(iterator.hasNext()).isFalse();
        assertThat(iterator.next()).isEqualTo(null);
    }

    @Test
    @DisplayName("Test BackwardIterator for empty list")
    public void testBackwardIteratorEmptyList() {
        var iterator = new BackwardIterator<>(List.of());

        assertThat(iterator.hasNext()).isFalse();
        assertThat(iterator.next()).isEqualTo(null);
    }
}
