package org.example.composite;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Composite class in the Composite design pattern.
 * Represents an operator expression with 1 to n operands of any kind of expression
 */
@RequiredArgsConstructor(staticName = "of")
public class Operator implements Expression {
    @Getter @Setter
    @NonNull
    private String name;

    /***
     * an operator is valid after construction/initialization
     * if it has at least one operand
     */
    @Size(min = 1)
    private List<Expression> operands = new ArrayList<>();

    @Override
    public Expression addOperand(Expression operand) {
        operands.add(operand);
        return this;
    }

    /**
     * string representation using 'polonaise inversée'
     * @return string representation
     */
    @Override
    public String toString() {
        String childrenToString = this.operands.stream()
                .map(Expression::toString)
                .collect(Collectors.joining(" "));
        return childrenToString + " " + this.name;
    }

    @Override
    public Iterator<Expression> iterator() {
        return OperatorIterator.of(this);
    }

    @RequiredArgsConstructor(staticName = "of")
    class OperatorIterator implements Iterator<Expression> {

        private Deque<Expression> expressionToIterate = new LinkedList<>();

        @NonNull
        private Operator rootExpression;

        @Override
        public boolean hasNext() {
            return !expressionToIterate.isEmpty();
        }

        @Override
        public Expression next() {
            if (expressionToIterate.isEmpty()) {
                throw new NoSuchElementException("nothing to iterate anymore");
            }
            var nextExpression = expressionToIterate.pop();
            // TODO: push children in the stack
            return nextExpression;
        }
    }
}
