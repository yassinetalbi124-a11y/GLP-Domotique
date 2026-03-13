package engine.rod;

import java.util.ArrayList;

public class Humor {
    private int niveau;
    private String etatHumeur;

    public Humor() {
        niveau = 100;
        etatHumeur = "Excellent";
    }

    public double calculer(ArrayList<State> etats) {
        int somme = 0;

        for (State etat : etats) {
            somme = somme + etat.getPourcentage();
        }

        if (etats.size() > 0) {
            niveau = somme / etats.size();
        }
        else {
            niveau = 0;
        }

        mettreAJourEtatHumeur();
        return niveau;
    }

    private void mettreAJourEtatHumeur() {
        if (niveau >= 80) {
            etatHumeur = "Excellent";
        }
        else if (niveau >= 60) {
            etatHumeur = "Bien";
        }
        else if (niveau >= 40) {
            etatHumeur = "Moyen";
        }
        else if (niveau >= 20) {
            etatHumeur = "Mauvais";
        }
        else {
            etatHumeur = "Critique";
        }
    }

    public int getNiveau() {
        return niveau;
    }

    public String getEtatHumeur() {
        return etatHumeur;
    }
}