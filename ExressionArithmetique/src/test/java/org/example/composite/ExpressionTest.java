package org.example.composite;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {

    static Stream<Expression> leaves() {
        return Stream.of(
                Valeur.of(5),
                Variable.of("x")
        );
    }

    @ParameterizedTest
    @MethodSource("leaves")
    void testLeafHasNoChild(Expression leaf) {
        var child = Valeur.of(5);
        assertThrows(UnsupportedOperationException.class, () ->
            leaf.addOperand(child)
        );
    }

    static Stream<Expression> children() {
        var child1 = Valeur.of(5);
        var child2 = Variable.of("x");
        var child3 = Operator.of("+");
        child3.addOperand(child1);
        child3.addOperand(child2);
        return Stream.of(child1,child2,child3);
    }

    @ParameterizedTest
    @MethodSource("children")
    void testOperatorHasChildren(Expression child) {
        var operator = Operator.of("+");
        assertDoesNotThrow(() ->
            operator.addOperand(child)
        );
    }

    @Test
    void testToString(){
        // composite expression
        Expression expression1 = Operator.of("+");
        expression1.addOperand(Value.of(12));
        expression1.addOperand(Variable.of("x"));
        Expression expression2 = Operator.of("-");
        expression2.addOperand(Value.of(3));
        expression2.addOperand(Variable.of("y"));
        Expression expression3 = Operator.of("*");
        expression3.addOperand(expression1);
        expression3.addOperand(expression2);
        // operation broadcast
        String texte = expression3.toString();
        // checkup (notation polonaise inversée)
        assertEquals("12 x + 3 y - *", texte);
    }

}