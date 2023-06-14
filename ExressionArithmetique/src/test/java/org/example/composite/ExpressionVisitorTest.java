package org.example.composite;

import org.example.composite.visitor.ExpressionVisitor;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ExpressionVisitorTest {

    @Test
    void visitDisplay() {
        Expression expression = ExpressionProvider.expression3levels();
        ExpressionVisitor visitor = null; // TODO:  new DisplayVisitor();
        expression.accept(visitor);
    }

    @Test
    void visitToString() {
        Expression expression = ExpressionProvider.expression3levels();
        ExpressionVisitor visitor = null; // TODO:  new ToStringVisitor();
        expression.accept(visitor);
//        String text = visitor.getResult();
//        System.out.println("Expression toString:" + text);
    }

    @Test
    void visitEvaluation() {
        // visit parameters
        var args = Map.of("x", 5, "y", 10);
        Expression expression = ExpressionProvider.expression3levels();
        ExpressionVisitor visitor = null; // TODO:  new EvaluationVisitor(args);
        expression.accept(visitor);
//        double result = visitor.getResult();
//        System.out.println("Result:" + result);
    }
}
