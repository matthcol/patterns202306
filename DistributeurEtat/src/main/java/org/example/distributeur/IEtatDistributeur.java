package org.example.distributeur;

import jakarta.validation.constraints.Min;
import lombok.NonNull;
import org.example.client.CarteBancaire;

public interface IEtatDistributeur {
    static IEtatDistributeur HORS_SERVICE = new EtatHorsService();
    static IEtatDistributeur EN_SERVICE = new EtatEnService();
    static IEtatDistributeur CARTE_INTRODUITE = new EtatCarteIntroduite();
    static IEtatDistributeur CLIENT_AUTHENTIFIE = new EtatClientAuthentifie();

    void handleAlimenter(Distributeur distributeur, @Min(10) int somme);

    void handleRetirerSomme(Distributeur distributeur, @Min(10) int somme);

    void handleIntroduireCarte(Distributeur distributeur, @NonNull CarteBancaire carteBancaire);

    void handleTaperCode(Distributeur distributeur, CarteBancaire carteBancaire, int code);


}
