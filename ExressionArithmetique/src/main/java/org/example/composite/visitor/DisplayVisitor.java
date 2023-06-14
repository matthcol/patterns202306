package org.example.composite.visitor;

import org.example.composite.Operator;
import org.example.composite.Valeur;
import org.example.composite.Variable;

public class DisplayVisitor implements ExpressionVisitor{
    private int indent;
    private int deltaIndent;

    public DisplayVisitor() {
        indent = 0;
        deltaIndent = 3;
    }
    @Override
    public void visitOperator(Operator operator) {
        printlnIndent(operator.getName());
        indent += deltaIndent;
        for (var operand: operator.getOperands()){
            operand.accept(this);
        }
        indent -= deltaIndent;
    }

    @Override
    public void visitVariable(Variable variable) {
        printlnIndent(variable.getName());
    }

    @Override
    public void visitValeur(Valeur valeur) {
        printlnIndent("" + valeur.getValue());
    }

    private void printlnIndent(String string){
        for (int i=0; i< indent; i++) {
            System.out.print(' ');
        }
        System.out.println(string);
    }
}
