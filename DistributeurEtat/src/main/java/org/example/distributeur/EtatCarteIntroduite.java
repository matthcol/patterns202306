package org.example.distributeur;

import org.example.client.CarteBancaire;

public class EtatCarteIntroduite extends AbstractEtatDistributeur {
    @Override
    public void handleTaperCode(Distributeur distributeur, CarteBancaire carteBancaire, int code) {
        distributeur.incrementerEssai();
        if (code == carteBancaire.getCode()) {
            distributeur.setEtatDistributeur(IEtatDistributeur.CLIENT_AUTHENTIFIE);
        } else if (distributeur.getEssai() == 3) {
            distributeur.avalerCarte();;
            distributeur.setEtatDistributeur(IEtatDistributeur.EN_SERVICE);
        } else {
            distributeur.notifyEchecCode();
            // stay in this state
        }
    }
}
