package edu.hw3.task6;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMarket {
    private static final Comparator<Stock> STOCK_COMPARATOR =
        Collections.reverseOrder(Comparator.comparingDouble(Stock::getPrice));

    private final PriorityQueue<Stock> stocks = new PriorityQueue<>(STOCK_COMPARATOR);

    public void add(Stock stock) {
        stocks.add(stock);
    }

    public void remove(Stock stock) {
        if (stock == null) {
            throw new NullPointerException();
        }
        if (!stocks.remove(stock)) {
            throw new RuntimeException("There is no such element");
        }
    }

    public Stock mostValuableStock()  {
        return stocks.peek();
    }
}

