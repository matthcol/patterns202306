package org.example.composite;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class ExpressionIteratorTest {

    @Test
    void iterateComplexExpression(){
        Expression expression = ExpressionProvider.expression3levels();
        // sequence order expected: * + 12 x - 3 y
        for (var e: expression) {
            var reprExpr = switch (e) {
                case Operator op -> op.getName();
                case Valeur valeur -> "" + valeur.getValue();
                case Variable  variable -> variable.getName();
                default -> throw new IllegalArgumentException("Expression unknown");
            };
            System.out.print(StringUtils.rightPad(reprExpr, 10));
            System.out.print("<-\t");
            System.out.println(e);
        }
    }

    @Test
    void iterateValeur(){
        Expression expression = Valeur.of(4);
        for (Expression e: expression) {
            System.out.println(e);
        }
    }

    @Test
    void iterateVariable(){
        Expression expression = Variable.of("x");
        for (Expression e: expression) {
            System.out.println(e);
        }
    }
}
