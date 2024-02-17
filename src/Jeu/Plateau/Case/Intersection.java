package Jeu.Plateau.Case;

import Jeu.Joueur;
import Jeu.Plateau.Plateau;

/** 
 * Représente un sommet adjacent à quatre {@link Tuile}s.
 */
public class Intersection extends Case {

    public Intersection() {super();}
    public Intersection(Joueur proprietaire) {
        super(proprietaire);
    }



    public boolean checkRoute(Plateau p, Joueur j, int x, int y) {
        Case[] adjacentes = new Case[]{
            p.getCase(x,y-1), p.getCase(x-1,y), p.getCase(x,y+1), p.getCase(x+1,y)
        };

        for(Case c : adjacentes)
            if(c.proprietaire == j) return true;

        return false;
    }

    @Override
    public String toString() {
        return " . ";
    }
}
