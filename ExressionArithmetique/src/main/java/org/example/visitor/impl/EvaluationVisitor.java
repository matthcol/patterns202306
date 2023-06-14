package org.example.visitor.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.composite.Operator;
import org.example.composite.Valeur;
import org.example.composite.Variable;
import org.example.visitor.ExpressionVisitor;
import org.example.visitor.ExpressionVisitorResult;

import java.util.Map;

@RequiredArgsConstructor
public class EvaluationVisitor implements ExpressionVisitorResult<Double> {

    @NonNull
    private Map<String, Double> variableValues;

    @Override
    public void visitOperator(Operator operator) {

    }

    @Override
    public void visitVariable(Variable variable) {

    }

    @Override
    public void visitValeur(Valeur valeur) {

    }

    /**
     * NB: signature can be optimized by defining interface ExpressionVisitorDoubleResult
     * with a method double getResult()
     * @return
     */
    @Override
    public Double getResult() {
        return Double.NaN;
    }
}
