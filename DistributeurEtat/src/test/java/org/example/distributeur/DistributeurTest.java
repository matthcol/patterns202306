package org.example.distributeur;

import org.example.client.CarteBancaire;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistributeurTest {

    @Test
    void scenarioOk() {
        Distributeur distributeur = new Distributeur();
        distributeur.alimenter(10000);
        CarteBancaire carteBancaire = CarteBancaire.of("1234-4321-5678-8657", 1234);
        distributeur.introduireCarte(carteBancaire);
        distributeur.taperCode(4321); // echec
        distributeur.taperCode(5678); // echec
        distributeur.taperCode(1234); // ok
        distributeur.retirerSomme(20000); // echec
        distributeur.retirerSomme(300); // OK
        assertSame(IEtatDistributeur.EN_SERVICE, distributeur.getEtatDistributeur());
    }

    @Test
    void scenarioOkVideDistributeur() {
        Distributeur distributeur = new Distributeur();
        distributeur.alimenter(300);
        CarteBancaire carteBancaire = CarteBancaire.of("1234-4321-5678-8657", 1234);
        distributeur.introduireCarte(carteBancaire);
        distributeur.taperCode(4321); // echec
        distributeur.taperCode(5678); // echec
        distributeur.taperCode(1234); // ok
        distributeur.retirerSomme(20000); // echec
        distributeur.retirerSomme(300); // OK
        assertSame(IEtatDistributeur.HORS_SERVICE, distributeur.getEtatDistributeur());
    }

    @Test
    void scenarioOkAvaleCarte() {
        Distributeur distributeur = new Distributeur();
        distributeur.alimenter(300);
        CarteBancaire carteBancaire = CarteBancaire.of("1234-4321-5678-8657", 1234);
        distributeur.introduireCarte(carteBancaire);
        distributeur.taperCode(4321); // echec
        distributeur.taperCode(5678); // echec
        distributeur.taperCode(8764); // 3e echec
        assertSame(IEtatDistributeur.EN_SERVICE, distributeur.getEtatDistributeur());
    }

    @Test
    void scenarioKoHorsServiceInsererCarte() {
        Distributeur distributeur = new Distributeur();
        CarteBancaire carteBancaire = CarteBancaire.of("1234-4321-5678-8657", 1234);
        assertThrows(UnsupportedOperationException.class, () ->
            distributeur.introduireCarte(carteBancaire)
        );
    }

    @Test
    void scenarioKoHorsServiceTaperCode() {
        Distributeur distributeur = new Distributeur();
        CarteBancaire carteBancaire = CarteBancaire.of("1234-4321-5678-8657", 1234);
        assertThrows(UnsupportedOperationException.class, () ->
                distributeur.taperCode(1234)
        );
    }

    @Test
    void scenarioKoHorsServiceRetirerSomme() {
        Distributeur distributeur = new Distributeur();
        assertThrows(UnsupportedOperationException.class, () ->
                distributeur.retirerSomme(10000)
        );
    }

}