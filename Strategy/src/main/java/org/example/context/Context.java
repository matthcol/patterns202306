package org.example.context;

import org.example.strategy.BubbleSortStrategy;
import org.example.strategy.QuickSortStrategy;
import org.example.strategy.SortStrategy;

import java.util.*;

public class Context<T extends Comparable<? super T>> {
    private List<T> list;
    private Map<String, SortStrategy<T>> strategyDict;

    public Context(Collection<? extends T> list) {
        this.list = new ArrayList<>(list);
        strategyDict = new TreeMap<>();
        strategyDict.put("quickSort", new QuickSortStrategy<>());
        strategyDict.put("bubbleSort", new BubbleSortStrategy<>());
    }

    public Context(T... data){
        this(Arrays.asList(data));
    }

    public void operation() {
        System.out.println("before sorting:" + list);
        String nameStrategy= (list.size() >=10) ? "quickSort": "bubbleSort";
        System.out.println("strategy chosen:" + nameStrategy);
        SortStrategy sortStrategy = strategyDict.get(nameStrategy);
        sortStrategy.sort(list);
        System.out.println("after sorting:" + list);
        System.out.println();
    }
}
