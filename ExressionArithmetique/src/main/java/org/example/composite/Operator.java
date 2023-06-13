package org.example.composite;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void addOperand(Expression operand) {
        operands.add(operand);
    }

    @Override
    public String toString() {
        String childrenToString = this.operands.stream()
                .map(Expression::toString)
                .collect(Collectors.joining(" "));
        return childrenToString + " " + name;
    }
}
