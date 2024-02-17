package Jeu.Cartes;

import Jeu.Ressource;

public class ConstructionRoutes extends Carte {
    public ConstructionRoutes() {
    	super();
    }
    
    public void activation() {
    	if(proprietaire.getNbRoutes()>0) {
    		proprietaire.setRessource(Ressource.ARGILE, proprietaire.getRessource(Ressource.ARGILE)+1);
    		proprietaire.setRessource(Ressource.BOIS, proprietaire.getRessource(Ressource.BOIS)+1);
    	}if(proprietaire.getNbRoutes()>1) {
    		proprietaire.setRessource(Ressource.ARGILE, proprietaire.getRessource(Ressource.ARGILE)+1);
    		proprietaire.setRessource(Ressource.BOIS, proprietaire.getRessource(Ressource.BOIS)+1);
    	}destruction();
    }
}
