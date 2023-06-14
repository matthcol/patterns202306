package org.example.strategy;

import java.util.Collections;
import java.util.List;

public class QuickSortStrategy<T extends Comparable<? super T>> implements SortStrategy<T>{
    @Override
    public void sort(List<T> list) {
        // delegate to Java library
        Collections.sort(list);
    }
}
