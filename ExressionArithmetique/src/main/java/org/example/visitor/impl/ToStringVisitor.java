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

    private StringBuilder stringBuilder;
    public ToStringVisitor() {
        stringBuilder = new StringBuilder();
    }

    @Override
    public void visitOperator(Operator operator) {
        operator.getOperands().forEach(operand -> {
            operand.accept(this);
            stringBuilder.append(' ');
        });
        stringBuilder.append(operator.getName());
    }

    @Override
    public void visitVariable(Variable variable) {
        stringBuilder.append(variable.getName());
    }

    @Override
    public void visitValeur(Valeur valeur) {
        stringBuilder.append(valeur.getValue());
    }

    @Override
    public String getResult(){
        return stringBuilder.toString();
    }
}
