package org.example.strategy;

import java.util.List;

public interface SortStrategy<T extends Comparable<? super T>> {
    void sort(List<T> list);
}
