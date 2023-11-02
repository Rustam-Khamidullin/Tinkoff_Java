package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {
    @Test
    @DisplayName("Test MostValuableStock")
    public void testMostValuableStock() {
        StockMarket stockMarket = new StockMarket();

        stockMarket.add(new Stock(1));
        stockMarket.add(new Stock(2));
        stockMarket.add(new Stock(3));
        stockMarket.add(new Stock(4));

        Stock mostValuable = stockMarket.mostValuableStock();
        assertThat(mostValuable.getPrice()).isEqualTo(4);
    }

    @Test
    @DisplayName("Test Add and Remove")
    public void testAddAndRemove() {
        StockMarket stockMarket = new StockMarket();

        stockMarket.add(new Stock(1));
        stockMarket.add(new Stock(2));
        stockMarket.add(new Stock(3));

        var mostValuable = new Stock(4);
        stockMarket.add(mostValuable);

        stockMarket.remove(mostValuable);

        Stock newMostValuable = stockMarket.mostValuableStock();
        assertThat(newMostValuable.getPrice()).isEqualTo(3);
    }

    @Test
    @DisplayName("Test Add Null")
    public void testAddNull() {
        StockMarket stockMarket = new StockMarket();

        assertThrows(NullPointerException.class, () -> stockMarket.add(null));
    }

    @Test
    @DisplayName("Test Remove Null")
    public void testRemoveNull() {
        StockMarket stockMarket = new StockMarket();

        assertThrows(NullPointerException.class, () -> stockMarket.remove(null));
    }

    @Test
    @DisplayName("Test Remove wrong element")
    public void testRemoveWrongElem() {
        StockMarket stockMarket = new StockMarket();

        stockMarket.add(new Stock(1));

        assertThrows(RuntimeException.class, () -> stockMarket.remove(new Stock(1)));
    }
}
