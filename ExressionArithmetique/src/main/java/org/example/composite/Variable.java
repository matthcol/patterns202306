package org.example.composite;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.visitor.ExpressionVisitor;

@RequiredArgsConstructor(staticName = "of")
public class Variable implements Expression {
    @Getter @Setter
    @NonNull
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitVariable(this);
    }
}
