package org.example.composite;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.visitor.ExpressionVisitor;

@RequiredArgsConstructor(staticName = "of")
public class Valeur implements Expression {
    @Getter @Setter
    @NonNull
    private double value;

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitValeur(this);
    }
}
