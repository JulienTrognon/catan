package Jeu.Cartes;

import Jeu.Joueur;

public class Carte {
	protected Joueur proprietaire;
	
    public Carte() {
    	proprietaire=null;
    }
    
    public Joueur getproprietaire() {
    	return proprietaire;
    }
    
    public void setproprietaire(Joueur j) {
    	proprietaire=null;
    }
    
    public void destruction() {
    	proprietaire=null;
    }
}
