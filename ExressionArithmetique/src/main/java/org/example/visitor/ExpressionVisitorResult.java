package org.example.visitor;

public interface ExpressionVisitorResult<T> extends ExpressionVisitor {
    T getResult();
}
