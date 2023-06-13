package org.example.composite;

public interface Expression {
    default void addOperand(Expression operand) {
        throw new UnsupportedOperationException();
    }
}
