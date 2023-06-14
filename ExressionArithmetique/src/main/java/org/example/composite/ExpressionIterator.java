package org.example.composite;

import lombok.NonNull;
import org.apache.commons.collections4.iterators.ReverseListIterator;

import java.util.*;

class ExpressionIterator implements Iterator<Expression> {

    private Deque<Expression> remainingExpressionQueue;

    private ExpressionIterator(@NonNull Expression rootExpression) {
        // initialize queue with the root expression
        this.remainingExpressionQueue = new LinkedList<>();
        this.remainingExpressionQueue.addFirst(rootExpression);
    }

    public static ExpressionIterator of(@NonNull Expression rootExpression) {
        return new ExpressionIterator(rootExpression);
    }

    @Override
    public boolean hasNext() {
        return !remainingExpressionQueue.isEmpty();
    }

    @Override
    public Expression next() {
        if (remainingExpressionQueue.isEmpty()) {
            throw new NoSuchElementException("nothing to iterate anymore");
        }
        var nextExpression = remainingExpressionQueue.removeFirst();
        switch (nextExpression) {
            case Operator operator:
                addExpressionsInQueue(operator.getOperands());
                break;
            default: // we are on a leaf, next step will go backwards int the tree using the queue
        }
        return nextExpression;
    }

    /**
     * add elements at the begining of the queue in reverse order
     * @param list
     */
    private void addExpressionsInQueue(List<? extends Expression> list) {
        var it = new ReverseListIterator<>(list);
        while (it.hasNext()){
            this.remainingExpressionQueue.addFirst(it.next());
        }
    }
}
