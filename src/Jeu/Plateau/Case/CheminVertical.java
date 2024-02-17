package Jeu.Plateau.Case;

import Jeu.Joueur;

/** 
 * Représente une arête adjacente à deux {@link Tuile}s,
 * sur les lignes ayant des tuiles.
 */
public class CheminVertical extends Chemin {
    
    public CheminVertical() {super();}
    public CheminVertical(Joueur proprietaire) {
        super(proprietaire);
    }
    
    public String toString() {
    	return " | ";
    }


}
