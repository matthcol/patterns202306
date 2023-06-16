package org.example.distributeur;

import lombok.NonNull;
import org.example.client.CarteBancaire;

public class EtatEnService extends AbstractEtatDistributeur {
    @Override
    public void handleIntroduireCarte(Distributeur distributeur, @NonNull CarteBancaire carteBancaire) {
        distributeur.setCarteBancaire(carteBancaire);
        distributeur.setEtatDistributeur(IEtatDistributeur.CARTE_INTRODUITE);
    }
}
