package org.example.distributeur;

public class EtatClientAuthentifie extends AbstractEtatDistributeur {

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
                distributeur.setEtatDistributeur(IEtatDistributeur.HORS_SERVICE);
            } else {
                distributeur.setEtatDistributeur(IEtatDistributeur.EN_SERVICE);
            }
        }
    }
}
