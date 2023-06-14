package org.example.composite;

import org.junit.jupiter.api.Test;

public class ExpressionIteratorTest {

    @Test
    void iterateExpression(){
        Expression expression = ExpressionProvider.expression3levels();
        for (var e: expression) {
            switch (e) {
                case Operator op -> System.out.println(op.getName());
                case Valeur valeur -> System.out.println(valeur.getValue());
                case Variable  variable -> System.out.println(variable.getName());
                default -> throw new IllegalArgumentException("Expression unknown");
            }
        }
        // sequence order: * + 12 x - 3 y
    }
}
