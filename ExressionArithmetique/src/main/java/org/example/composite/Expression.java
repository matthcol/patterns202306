package org.example.composite;

import java.util.Iterator;

/**
 * Component interface in the Composite design pattern.
 * Generalize every type of components: composite (operator)
 * and leaves (value, variable)
 */
public interface Expression extends Iterable<Expression> {
    /**
     * add a child expression (operand) to the current expression
     * @param operand child to add
     * @return a reference to the current expression
     * @Throws UnsupportedOperationException if current expression is a leaf
     */
    default Expression addOperand(Expression operand) {
        throw new UnsupportedOperationException();
    }

    // you can add other methods to manage children:
    // addOperands, removeOperand(s), replaceOperand
    // insertOperand, clearOperands, ...

    @Override
    default Iterator<Expression> iterator() {
        return ExpressionIterator.of(this);
    }
}
