package org.example.composite;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Composite class in the Composite design pattern.
 * Represents an operator expression with 1 to n operands of any kind of expression
 */
@RequiredArgsConstructor(staticName = "of")
public class Operator implements Expression {
    @Getter @Setter
    @NonNull
    private String name;

    /***
     * an operator is valid after construction/initialization
     * if it has at least one operand
     */
    @Size(min = 1)
    private List<Expression> operands = new ArrayList<>();

    @Override
    public Expression addOperand(Expression operand) {
        operands.add(operand);
        return this;
    }

    /**
     * string representation using 'polonaise inversée'
     * @return string representation
     */
    @Override
    public String toString() {
        String childrenToString = this.operands.stream()
                .map(Expression::toString)
                .collect(Collectors.joining(" "));
        return childrenToString + " " + this.name;
    }
}
