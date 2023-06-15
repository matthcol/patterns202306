package org.example.visitor;

import org.example.composite.Expression;
import org.example.composite.ExpressionProvider;
import org.example.composite.Operator;
import org.example.composite.Variable;
import org.example.visitor.impl.DisplayVisitor;
import org.example.visitor.impl.EvaluationVisitor;
import org.example.visitor.impl.ToStringVisitor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class ExpressionVisitorTest {

    @Test
    void visitDisplay() {
        // expected result
        // *
        //    +
        //       12.0
        //       x
        //    -
        //       3.0
        //       y
        Expression expression = ExpressionProvider.expression3levels();
        ExpressionVisitor visitor = new DisplayVisitor();
        expression.accept(visitor);
    }

    @Test
    void visitToString() {
        Expression expression = ExpressionProvider.expression3levels();
        ExpressionVisitorResult<String> visitor = new ToStringVisitor();
        expression.accept(visitor);
        String text = visitor.getResult();
        System.out.println("Expression toString: " + text);
        assertEquals("12.0 x + 3.0 y - *", text);
    }

    @Test
    void visitEvaluationComplex() {
        // visit parameters
        var args = Map.of("x", 5.0, "y", 10.0);
        Expression expression = ExpressionProvider.expression3levels();
        ExpressionVisitorResult<Double> visitor = new EvaluationVisitor(args);
        expression.accept(visitor);
        double result = visitor.getResult();
        System.out.println("Result: " + result);
        assertEquals(-119.0, result);
    }

    @Test
    void visitEvaluationUnaryMinus() {
        // visit parameters
        var args = Map.of("x", 5.0);
        Expression expression = Operator.of("-")
                .addOperand(Variable.of("x"));
        ExpressionVisitorResult<Double> visitor = new EvaluationVisitor(args);
        expression.accept(visitor);
        double result = visitor.getResult();
        System.out.println("Result: " + result);
        assertEquals(-5, result);
    }

    @Test
    void visitEvaluationComplexKoVariableNotFound() {
        // visit parameters (missing value for y)
        var args = Map.of("x", 5.0);
        Expression expression = ExpressionProvider.expression3levels();
        ExpressionVisitorResult<Double> visitor = new EvaluationVisitor(args);
        ArithmeticException exception = assertThrows(ArithmeticException.class,
                () -> expression.accept(visitor));
        assertEquals("No value for variable: y", exception.getMessage());
    }
}
