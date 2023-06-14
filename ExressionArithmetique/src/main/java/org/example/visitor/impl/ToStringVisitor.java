package org.example.visitor.impl;

import org.example.composite.Operator;
import org.example.composite.Valeur;
import org.example.composite.Variable;
import org.example.visitor.ExpressionVisitor;
import org.example.visitor.ExpressionVisitorResult;

/**
 * Text representation of arithmetic expression.
 * Memory usage is optimized using a StringBuilder
 */
public class ToStringVisitor implements ExpressionVisitorResult<String> {

    @Override
    public void visitOperator(Operator operator) {

    }

    @Override
    public void visitVariable(Variable variable) {

    }

    @Override
    public void visitValeur(Valeur valeur) {

    }

    @Override
    public String getResult(){
        return null;
    }
}
