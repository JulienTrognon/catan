package Jeu.Plateau.Case;

import Jeu.Joueur;
public class Colonie extends Case {
    public Colonie() {super();}
    public Colonie(Joueur proprietaire) {
        super(proprietaire);
        
    }


    @Override
    public String toString() {
        return " + ";
    }
}
