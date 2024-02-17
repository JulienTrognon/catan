package Jeu.Plateau.Case;

import Jeu.Joueur;

/** 
 * Une case est un espace du plateau contenant diverses données.
 */
public abstract class Case {
    protected int x;
    protected int y;
    protected Color couleur;
    protected Joueur proprietaire=null;
    protected Etat etat;

    public Joueur getProprietaire() {return proprietaire;}
    public void setProprietaire(Joueur proprietaire) {this.proprietaire = proprietaire;}

    public int getx(){return x;}
    public int gety(){return y;}
    public void setx(int x){this.x=x;}
    public void sety(int y){this.y=y;
    }
    public Etat getEtat() { return etat; }

    public boolean isLibre() { return etat == Etat.VIDE; }



    /** pour créer une {@link Case} vide, sans {@code proprietaire} */
    public Case() {
    	etat=Etat.VIDE;
    }
    /** pour créer une {@link Case} ayant déjà un {@code proprietaire} */
    public Case(Joueur proprietaire) {
        this.proprietaire = proprietaire;
        etat = Etat.REMPLI;
    }


    public boolean isEmpty() {return this == null;}


    @Override
    public String toString() {
        return "   ";
    }

}
