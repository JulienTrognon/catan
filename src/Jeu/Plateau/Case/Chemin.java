package Jeu.Plateau.Case;

import Jeu.Joueur;

/** 
 * Représente une arête adjacente à deux {@link Tuile}s,
 * sur les lignes ayant des intersections.
 */
public class Chemin extends Case {
    
    public Chemin() {super();}
    public Chemin(Joueur proprietaire) {
        super(proprietaire);
    }


    @Override
    public String toString() {
        return "___";
    }

}
