package org.example.distributeur;

import jakarta.validation.constraints.Min;
import lombok.*;
import org.example.client.CarteBancaire;

@ToString(of = {"fondDisponible", "etatDistributeur"})
public class Distributeur {

    @Getter // for demo only
    @Setter(AccessLevel.PACKAGE)
    @NonNull
    private IEtatDistributeur etatDistributeur;

    @Getter
    private int fondDisponible;

    @Getter // for demo only
    @Setter(AccessLevel.PACKAGE)
    private CarteBancaire carteBancaire;

    @Getter
    private int essai;

    public Distributeur() {
        // initial state
        this.etatDistributeur = IEtatDistributeur.HORS_SERVICE;
        // initial values
        this.fondDisponible = 0;
        this.carteBancaire = null;
    }

    public void alimenter(@Min(10) int somme){
        etatDistributeur.handleAlimenter(this, somme);
    }

    public void retirerSomme(@Min(10) int somme){
        etatDistributeur.handleRetirerSomme(this, somme);
    }

    public void introduireCarte(@NonNull CarteBancaire carteBancaire){
        etatDistributeur.handleIntroduireCarte(this, carteBancaire);
    }

    public void taperCode(int code){
        etatDistributeur.handleTaperCode(this, carteBancaire, code);

    }

    // method for state only (package private)

    void incrementerFond(@Min(10) int somme){
        this.fondDisponible += somme;
    }

    void decrementerFond(@Min(10) int somme){
        this.fondDisponible -= somme;
    }

    void incrementerEssai(){
        this.essai++;
    }

    // interaction with client
    void avalerCarte(){
        System.out.println("Carte avalée: " + carteBancaire.getNumero());
        this.carteBancaire = null;
    }

    void rendreCarte(){
        System.out.println("Carte rendue: " + carteBancaire.getNumero());
        this.carteBancaire = null;
    }

    void delivrerSomme(@Min(10) int somme){
        System.out.println("Somme délivrée: " + somme);
    }

    void notifyFondInsuffisant() {
        System.out.println("Fond insuffisant pour délivrer cette somme");
    }

    void notifyEchecCode() {
        System.out.println("Code incorrect");
    }

}
