package org.example.distributeur;

import jakarta.validation.constraints.Min;
import lombok.NonNull;
import org.example.client.CarteBancaire;

public enum EtatDistributeur {
    HORS_SERVICE {
        @Override
        public void handleAlimenter(Distributeur distributeur, int somme) {
            distributeur.incrementerFond(somme);
            distributeur.setEtatDistributeur(EN_SERVICE);
        }
    },
    EN_SERVICE {
        @Override
        public void handleIntroduireCarte(Distributeur distributeur, @NonNull CarteBancaire carteBancaire) {
            distributeur.setCarteBancaire(carteBancaire);
            distributeur.setEtatDistributeur(CARTE_INTRODUITE);
        }
    },
    CARTE_INTRODUITE {
        @Override
        public void handleTaperCode(Distributeur distributeur, CarteBancaire carteBancaire, int code) {
            distributeur.incrementerEssai();
            if (code == carteBancaire.getCode()) {
                distributeur.setEtatDistributeur(CLIENT_AUTHENTIFIE);
            } else if (distributeur.getEssai() == 3) {
                distributeur.avalerCarte();;
                distributeur.setEtatDistributeur(EN_SERVICE);
            } else {
                distributeur.notifyEchecCode();
                // stay in this state
            }
        }
    },
    CLIENT_AUTHENTIFIE {
        @Override
        public void handleRetirerSomme(Distributeur distributeur, int somme) {
            if (somme > distributeur.getFondDisponible()) {
                distributeur.notifyFondInsuffisant();
                // stay in this state
            } else {
                distributeur.decrementerFond(somme);
                distributeur.rendreCarte();
                distributeur.delivrerSomme(somme);
                if (distributeur.getFondDisponible() == 0) {
                    distributeur.setEtatDistributeur(HORS_SERVICE);
                } else {
                    distributeur.setEtatDistributeur(EN_SERVICE);
                }
            }
        }
    };

    void handleAlimenter(Distributeur distributeur, @Min(10) int somme){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    void handleRetirerSomme(Distributeur distributeur, @Min(10) int somme){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    void handleIntroduireCarte(Distributeur distributeur, @NonNull CarteBancaire carteBancaire){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }

    void handleTaperCode(Distributeur distributeur, CarteBancaire carteBancaire, int code){
        throw new UnsupportedOperationException("Method not allowed in this state");
    }
    
}
