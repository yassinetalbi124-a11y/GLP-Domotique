package engine.rod;

import java.util.ArrayList;

public class Humor {
    private int niveau;
    private String etatHumeur;

    public Humor() {
        niveau = 100;
        etatHumeur = "Excellent";
    }

    public double calculer(ArrayList<State> states) {
        int somme = 0;

        for (State state : states) {
            somme = somme + state.getPourcentage();
        }

        if (states.size() > 0) {
            niveau = somme / states.size();
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