package Jeu.Cartes;

public class PointVictoire extends Carte {
    public PointVictoire() {
    	super();
    }
    
    public void activation() {
    	this.proprietaire.setpoints(proprietaire.getPoints()+1);
    	destruction();
    }
    
}
