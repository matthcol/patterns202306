package org.example.distributeur;

import jakarta.validation.constraints.Min;
import lombok.NonNull;
import org.example.client.CarteBancaire;

public class AbstractEtatDistributeur implements IEtatDistributeur {
    @Override
    public void handleAlimenter(Distributeur distributeur, @Min(10) int somme){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    @Override
    public void handleRetirerSomme(Distributeur distributeur, @Min(10) int somme){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    @Override
    public void handleIntroduireCarte(Distributeur distributeur, @NonNull CarteBancaire carteBancaire){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    @Override
    public void handleTaperCode(Distributeur distributeur, CarteBancaire carteBancaire, int code){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
