package Jeu.Plateau.Case;

import Jeu.Joueur;

public class Ville extends Case {
    public Ville() {super();}
    public Ville(Joueur proprietaire) {
        super(proprietaire);
        etat=Etat.VILLE;
    }


    @Override
    public String toString() {
        return " * ";
    }
}
