package org.example.composite.visitor;

import org.example.composite.Operator;
import org.example.composite.Valeur;
import org.example.composite.Variable;

public interface ExpressionVisitor {
    void visitOperator(Operator operator);
    void visitVariable(Variable variable);
    void visitValeur(Valeur valeur);
}
