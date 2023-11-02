package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> list;
    private int currentPos;

    public BackwardIterator(List<T> list) {
        this.list = list;
        this.currentPos = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentPos >= 0;
    }

    @Override
    public T next() {
        if (currentPos < 0) {
            return null;
        }

        return list.get(currentPos--);
    }
}
