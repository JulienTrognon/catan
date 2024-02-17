package Jeu.Plateau;

import Jeu.Plateau.Case.*;

import java.util.ArrayList;

import Jeu.Couleur;
import Jeu.Joueur;
import Jeu.Cartes.*;

/** 
 * Représente l'objet sur lequel se trouvent les {@link Case},
 * sur lesquelles on peut poser des {@link Piece}.
 */
public class Plateau {
    
    /** {@code longueur} × {@code hauteur} */
    private Case[][] cases;
    private Deck deck;
    private ArrayList<Tuile> tuiles;


    public Case[][] getCases() { return cases; }
    public Case getCase(int x, int y) { return cases[x][y]; }

    public Deck getDeck() { return deck; }


    /**
     * construit un {@link Plateau} de catan
     * de taille 4×4 tuiles, en plus des ports.
     */
    public Plateau() {
        cases = new Case[13][13];
        tuiles=new ArrayList<Tuile>();
        fill();
    }




    /** 
     * Remplit le {@link Plateau}
     * avec un layout de {@link Case}s
     * qui permet de jouer au catan.
     * 
     * Utilise les fonctions {@code fillTuiles()}, 
     * {@code fillChemins()}, {@code fillIntersections()}
     * et {@code fillPort()} séparément.
     */
    private void fill() {
        fillTuiles();
        fillCheminsAndIntersections();
        fillPort();
    }
    private void fillTuiles() {
        cases[3][3] = new Tuile(Biome.FORET,6,3,3);
        cases[5][3] = new Tuile(Biome.CHAMPS,4,5,3);
        cases[7][3] = new Tuile(Biome.MONTAGNE,3,7,3);
        cases[9][3] = new Tuile(Biome.COLLINE,9,9,3);
        tuiles.add((Tuile) cases[3][3]);
        tuiles.add((Tuile) cases[5][3]);
        tuiles.add((Tuile) cases[7][3]);
        tuiles.add((Tuile) cases[9][3]);
        
        cases[3][5] = new Tuile(Biome.PRE,10,3,5);
        cases[5][5] = new Tuile(Biome.COLLINE,9,5,5);
        cases[7][5] = new Tuile(Biome.DESERT,0,7,5);
        ((Tuile) cases[7][5]).setVoleurOn();
        cases[9][5] = new Tuile(Biome.MONTAGNE,8,9,5);
        tuiles.add((Tuile) cases[3][5]);
        tuiles.add((Tuile) cases[5][5]);
        tuiles.add((Tuile) cases[7][5]);
        tuiles.add((Tuile) cases[9][5]);
        
        cases[3][7] = new Tuile(Biome.CHAMPS,11,3,7);
        cases[5][7] = new Tuile(Biome.FORET,5,5,7);
        cases[7][7] = new Tuile(Biome.CHAMPS,10,7,7);
        cases[9][7] = new Tuile(Biome.PRE,5,9,7);
        tuiles.add((Tuile) cases[3][7]);
        tuiles.add((Tuile) cases[5][7]);
        tuiles.add((Tuile) cases[7][7]);
        tuiles.add((Tuile) cases[9][7]);
        
        cases[3][9] = new Tuile(Biome.PRE,8,3,9);
        cases[5][9] = new Tuile(Biome.MONTAGNE,12,5,9);
        cases[7][9] = new Tuile(Biome.COLLINE,6,7,9);
        cases[9][9] = new Tuile(Biome.FORET,2,9,9);
        tuiles.add((Tuile) cases[3][9]);
        tuiles.add((Tuile) cases[5][9]);
        tuiles.add((Tuile) cases[7][9]);
        tuiles.add((Tuile) cases[9][9]);
    }
    private void fillCheminsAndIntersections() {
        // remplit les lignes sans tuiles de chemins et intersections
        for(int i = 2 ; i <= 10 ; i+=2) {
            for(int j = 2 ; j <= 9; j+=2) { 
                cases[i][j]   = new Intersection();
                cases[i][j+1] = new Chemin();
            }
            cases[i][10] = new Intersection();
        }
        // remplit les lignes avec tuiles de chemins
        for(int i = 3 ; i <= 9 ; i+=2) {
            for(int j = 2 ; j <= 10 ; j+= 2) {
                cases[i][j] = new CheminVertical();
            }
        }
    }
    private void fillPort() {
        cases[1][5]  = new Port(Biome.PRE,2); // Laine
        cases[5][11] = new Port(Biome.FORET,2); // Bois
        cases[7][1]  = new Port(Biome.CHAMPS,2); // Blé
        cases[11][3] = new Port(Biome.MONTAGNE,2); // Minerais
        cases[11][7] = new Port(Biome.COLLINE,2); // Argile

        cases[3][1]  = new Port(3);
        cases[1][9]  = new Port(3);
        cases[9][11] = new Port(3);
    }
    public boolean testverticalex(int x){
        if((x==3)||(x==5)||(x==7)||(x==9))return true;
        else return false;
    }
    public boolean testverticaley(int x){
        if((x==2)||(x==4)||(x==6)||(x==8)||(x==10))return true;
        else return false;
    }
    public boolean estcheminV(int x, int y){
        if(testverticalex(x)&&(testverticaley(y)))return true;
        return false;
    }
    
    public Case [] adjacentes(Case c, int x, int y) {
    	Case[] adjacentes = new Case[]{ // les cases à gauche, en haut, à droite, en bas
                cases[x][y-1],cases[x-1][y],cases[x][y+1],cases[x+1][y]
         };return adjacentes;
    }
    
    public Case [] adjacentesRoutes(Case c, int x, int y) {
    	Case[] adjacentes = new Case[]{	
                cases[x][y-1],cases[x][y+1],cases[x-1][y-1],cases[x-1][y+1],cases[x+1][y-1],cases[x+1][y+1],cases[x][y-2],cases[x][y+2]
         };return adjacentes;
    }
    
    public Case [] adjacentesRoutesVerti(Case c, int x, int y) {
    	Case[] adjacentes = new Case[]{ 
                cases[x-1][y-1],cases[x-1][y],cases[x-1][y+1],cases[x+1][y-1],cases[x+1][y],cases[x+1][y+1],cases[x+2][y],cases[x-2][y]
         };return adjacentes;
    }
    /** 
     * ajoute une route sur le {@link Plateau}.
     * @param j   joueur posant la route.
     * @param x   position horizontale de la route.
     * @param y   position verticale de la route.
     * 
     * @return    {@code false} si les conditions 
     *            pour poser une route 
     *            ne sont pas remplies.
     */
    public boolean addRoute(Joueur j, int x, int y) {
        if(!checkRoute(j,x,y)||!(j.useRoute())) {
        	return false;
        }
        else{
            if(estcheminV(x,y))cases[x][y]=new CheminVertical(j);
            else cases[x][y]=new Chemin(j); 
        }
        return true;
    }
    
    public boolean RouteGratuite(Joueur j, int x, int y) {
    	if(!checkRoute(j,x,y)||!(j.useFirstR()))return false;
        else{
            if(estcheminV(x,y))cases[x][y]=new CheminVertical(j);
            else cases[x][y]=new Chemin(j); 
        }
        return true;
    }
    
    private boolean checkRoute(Joueur j, int x, int y) {
        if(!(cases[x][y] instanceof Chemin)) {
        	System.out.println("Cette case n'est pas une route.");
        	return false;
        }
        if(cases[x][y].getEtat() != Etat.VIDE) {
        	System.out.println("Cette route appartient déjà quelqu'un.");
        	return false;
        }

        if(cases[x][y] instanceof CheminVertical) {
        	Case[] adjacentes =adjacentesRoutesVerti(cases[x][y],x,y);
        	for(Case c : adjacentes) {
	            if(c instanceof Intersection) {
	                return ((Intersection)c).checkRoute(this,j,x,y);
	            }
	        }
        }
        else{
        	Case[] adjacentes =adjacentesRoutes(cases[x][y],x,y);
        	for(Case c : adjacentes) {
        		if(c!=null) {
        			if(c.getProprietaire()==j)return true;
        		}
	        }
        }
        return false;
        
    }


    /** 
     * ajoute un colonie sur le {@link Plateau}.
     * 
     * @param j   joueur posant le colonie}.
     * @param x   position horizontale de le colonie.
     * @param y   position verticale de le colonie.
     * 
     * @return    {@code false} si les conditions 
     *            pour poser un colonie         
     *            ne sont pas remplies.
     */
    public boolean addColonie(Joueur j, int x, int y) {
       if(!checkColonie(j,x,y)||!(j.useColonie())) return false;
        else{
            cases[x][y]=new Colonie(j); 
        }
        return true;
    }
    
    public boolean firstColonie(Joueur j, int x, int y) {
    	if(!checkColonie(j,x,y)||!(j.useFirstC())) {
    		return false;
    	}
        else{
        
            cases[x][y]=new Colonie(j); 
        }
        return true;
    }
    
    private boolean checkColonie(Joueur j, int x, int y) {
        if(!(cases[x][y] instanceof Intersection)) {
        	System.out.println("Cette case n'est pas une intersection.");
        	return false;
        }
        if(cases[x][y].getEtat() != Etat.VIDE) {
        	System.out.println("Cette intersection appartient d�j� � quelqu'un.");
        	return false;
        }
       
        
        /*Case[] adjacentes =adjacentes(cases[x][y],x,y);
        
        for(Case c : adjacentes) {
        	if(cases[x][y].getProprietaire()==j) {
        		Case [] adj=adjacentes(c,c.getx(),c.gety());
        		
        	}
        }*/

        return true;
    }

    /** 
     * ajoute une ville sur le {@link Plateau}.
     * 
     * @param j   joueur posant la ville.
     * @param x   position horizontale de la ville.
     * @param y   position verticale de la ville.
     * 
     * @return    {@code false} si les conditions 
     *            pour poser un ville         
     *            ne sont pas remplies.
     */
    public boolean addVille(Joueur j, int x, int y) {
        if(!checkVille(j,x,y)||!(j.useVille())) return false;
        else{
            cases[x][y]=new Ville(j); 
        }
        return true;
    }

    public boolean checkVille(Joueur j, int x,int y){
        if((cases[x][y] instanceof Colonie)&&(cases[x][y].getProprietaire()==j)) return true;
        else {
        	System.out.println("Vous vous �tes tromp� de case.");
        	return false;
        }
    }

    public ArrayList<Tuile> getTuiles(){
        return tuiles;
    }

    public void affiche(){
    	System.out.print("    ");
        for(int i=0; i<12;i++){
            System.out.print("  "+i);
        }System.out.println();
        for(int i=0;i<12;i++){
            System.out.print((char)(i+65));
            if(i==1) {										//Les if sont l� pour mettre les ports dans le bon alignement
            	System.out.print("       ");
            	for(int j=0;j<12;j++){
                    if(cases[i][j]!=null) {
                    	if(cases[i][j].getProprietaire()!=null) System.out.print(cases[i][j].getProprietaire().getColor().getCode()+cases[i][j].toString()+"  "+Couleur.RESET.getCode());
                    	else System.out.print(cases[i][j].toString()+"  ");
                    }
                    else System.out.print("  ");
                }
            }else if(i==11) {
            	System.out.print("     ");
            	for(int j=0;j<12;j++){
                    if(cases[i][j]!=null) {
                    	if(cases[i][j].getProprietaire()!=null) System.out.print(cases[i][j].getProprietaire().getColor().getCode()+cases[i][j].toString()+"  "+Couleur.RESET.getCode());
                    	else System.out.print(cases[i][j].toString()+"  ");
                    }
                    else System.out.print("  ");
                }
            }
            else if(i==7) {
            	System.out.print("  ");
            	for(int j=0;j<12;j++){
                    if(cases[i][j]!=null) {
                    	if(cases[i][j].getProprietaire()!=null)	System.out.print(cases[i][j].getProprietaire().getColor().getCode()+cases[i][j].toString()+Couleur.RESET.getCode());
                    	else System.out.print(cases[i][j].toString());
                    }
                    else System.out.print("   ");
                }
            }
            else{
            	System.out.print("   ");
            	for(int j=0;j<12;j++){
                    if(cases[i][j]!=null) {
                    	if(cases[i][j].getProprietaire()!=null)	System.out.print(cases[i][j].getProprietaire().getColor().getCode()+cases[i][j].toString()+Couleur.RESET.getCode());
                    	else System.out.print(cases[i][j].toString());
                    }
                    else System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
}
