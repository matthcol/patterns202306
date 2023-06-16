package org.example.distributeur;

public class EtatHorsService extends AbstractEtatDistributeur {
    @Override
    public void handleAlimenter(Distributeur distributeur, int somme) {
        distributeur.incrementerFond(somme);
        distributeur.setEtatDistributeur(IEtatDistributeur.EN_SERVICE);
    }
}
