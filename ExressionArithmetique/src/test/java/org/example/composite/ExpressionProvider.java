package org.example.composite;

public class ExpressionProvider {

    public static Expression expression3levels() {
        Expression expression1 = Operator.of("+")
                .addOperand(Valeur.of(12.0))
                .addOperand(Variable.of("x"));
        Expression expression2 = Operator.of("-")
                .addOperand(Valeur.of(3.0))
                .addOperand(Variable.of("y"));
        Expression expression3 = Operator.of("*")
                .addOperand(expression1)
                .addOperand(expression2);
        return expression3;
    }
}
