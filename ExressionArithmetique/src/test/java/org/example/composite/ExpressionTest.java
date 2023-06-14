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
        var child3 = Operator.of("+")
                .addOperand(child1)
                .addOperand(child2);
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
        Expression expression3 = ExpressionProvider.expression3levels();
        // operation broadcast
        String texte = expression3.toString();
        // checkup (notation polonaise inversée)
        assertEquals("12.0 x + 3.0 y - *", texte);
    }

}