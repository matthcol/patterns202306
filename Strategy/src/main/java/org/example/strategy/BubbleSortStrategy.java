package org.example.strategy;

import java.util.List;

public class BubbleSortStrategy<T extends Comparable<? super T>> implements SortStrategy<T> {
    @Override
    public void sort(List<T> list) {
        for (int i = list.size() -1; i >= 1; i--) {
            boolean sorted = true;
            for (int j = 0; j <= i - 1; j++){
                if (list.get(j+1).compareTo(list.get(j)) < 0){
                    T tmp = list.get(j+1);
                    list.set(j+1, list.get(j));
                    list.set(j, tmp);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }
}
