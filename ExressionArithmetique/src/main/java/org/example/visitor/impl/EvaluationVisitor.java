package org.example.visitor.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.composite.Operator;
import org.example.composite.Valeur;
import org.example.composite.Variable;
import org.example.visitor.ExpressionVisitor;
import org.example.visitor.ExpressionVisitorResult;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class EvaluationVisitor implements ExpressionVisitorResult<Double> {

    @NonNull
    private Map<String, Double> variableValues;

    private List<Double> stackEvaluation = new LinkedList<>();

    @Override
    public void visitOperator(Operator operator) {
        String operatorName = operator.getName();
        int nbOperands = operator.getOperands().size();
        // visit operands
        operator.getOperands().forEach(operand -> operand.accept(this));
        // compute operation
        DoubleStream streamOperandValues = popOperands(nbOperands)
                .stream()
                .mapToDouble(d -> d);
        OptionalDouble result = switch (operatorName) {
            case "+" -> streamOperandValues.reduce((a,b) -> a+b);
            case "-" -> (nbOperands == 1) ?
                    // unary minus
                    OptionalDouble.of(- streamOperandValues.findFirst().getAsDouble())
                    // binary+ minus
                    : streamOperandValues.reduce((a,b) -> a-b);
            case "*" -> (nbOperands == 1) ?
                    OptionalDouble.empty()
                    : streamOperandValues.reduce((a,b) -> a*b);
            case "/" -> (nbOperands == 1) ?
                    OptionalDouble.empty()
                    : streamOperandValues.reduce((a,b) -> a/b);
            default -> throw new UnsupportedOperationException(
                    MessageFormat.format(
                            "Operator {0} not implemented for evaluation", operatorName
                    ));
        };
        stackEvaluation.add(result.orElseThrow(() ->
                new ArithmeticException("Not enough operands to evaluate")));
    }

    @Override
    public void visitVariable(Variable variable) {
        String nameVariable = variable.getName();
        Double value = variableValues.get(nameVariable);
        if (Objects.isNull(value)){
            throw new ArithmeticException(MessageFormat.format(
                    "No value for variable: {0}", nameVariable));
        }
        stackEvaluation.add(variableValues.get(nameVariable));
    }

    @Override
    public void visitValeur(Valeur valeur) {
        stackEvaluation.add(valeur.getValue());
    }

    /**
     * NB: signature can be optimized by defining interface ExpressionVisitorDoubleResult
     * with a method double getResult()
     * @return
     */
    @Override
    public Double getResult() {
        int stackSize = stackEvaluation.size();
        if (stackSize != 1) {
            throw new ArithmeticException(MessageFormat.format(
                    "Wrong number of values at the end of evaluation: expected 1 got {0}",
                    stackSize
            ));
        }
        return stackEvaluation.get(0);
    }

    private List<Double> popOperands(int nbOperands) {
        int stackSize = stackEvaluation.size();
        // get  nbOperands last elements on the stack
        List<Double> operands = IntStream.range(0, nbOperands)
                .mapToObj( i -> stackEvaluation.get(stackSize - nbOperands + i))
                .toList();
        // remove nbOperands last elements from the stack
        IntStream.range(0, nbOperands).forEach(i -> stackEvaluation.remove(stackSize - i - 1));
        return operands;
    }
}
