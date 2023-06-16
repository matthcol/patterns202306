package org.example.distributeur;

import jakarta.validation.constraints.Min;
import lombok.NonNull;
import org.example.client.CarteBancaire;

public interface EtatDistributeur {
    static EtatDistributeur HORS_SERVICE = new EtatHorsService();
    static EtatDistributeur EN_SERVICE = new EtatEnService();
    static EtatDistributeur CARTE_INTRODUITE = new EtatCarteIntroduite();
    static EtatDistributeur CLIENT_AUTHENTIFIE = new EtatClientAuthentifie();

    default void handleAlimenter(Distributeur distributeur, @Min(10) int somme){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    default void handleRetirerSomme(Distributeur distributeur, @Min(10) int somme){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    default void handleIntroduireCarte(Distributeur distributeur, @NonNull CarteBancaire carteBancaire){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    default void handleTaperCode(Distributeur distributeur, CarteBancaire carteBancaire, int code){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    @Override
    default String toString() {
        return this.getClass().getSimpleName();
    }

}
