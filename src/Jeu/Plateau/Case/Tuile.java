package Jeu.Plateau.Case;

import java.util.ArrayList;

import Jeu.Joueur;
import Jeu.Ressource;
import Jeu.Plateau.Plateau;

/** 
 * Représente un terrain qui contient :
 * un {@link Biome} qui produit une {@link Ressource},
 * et une {@code valeur} qui détermine l'activation de la case
 * en fonction du résultat du dé.
 * Une {@link Tuile} peut accueillir le voleur.
 */
public class Tuile extends Case {
    
    private final Biome biome;
    /** 
     * nombre entre 1 et 12, résultat d'un
     * lancer de double dés, déclenche la tuile
     * si le numéro correspond à {@code valeur}.
     */
    private final int valeur;
    /** {@code true} si le voleur est sur la {@link Tuile} */
    private boolean voleurOn;

    public Biome getBiome() {return biome;}
    public int getValeur() {return valeur;}
    public boolean isVoleurOn() {return voleurOn;}

    public void setVoleurOn() {voleurOn = true;}
    public void setVoleurOff() {voleurOn = false;}


    public Tuile(Biome biome, int valeur, int x, int y) {
        this.biome = biome;
        this.valeur = valeur;
        this.x=x;
        this.y=y;
        voleurOn=false;
    }
    
    public String toString() {
    	if(Integer.toString(valeur).length()==1)return biome.name().substring(0,2)+Integer.toString(valeur);
    	else return biome.name().substring(0,1)+Integer.toString(valeur);
    }

    /** produit des ressources si la tuile est activée */
    public void production(ArrayList<Joueur> joueurs, Plateau p) {
        if(!voleurOn){
            Case[] adjacentes = new Case[]{ // les cases à gauche, en haut, à droite, en bas
                p.getCase(x-1,y-1),p.getCase(x-1,y+1),p.getCase(x+1,y-1),p.getCase(x+1,y+1)
            };
            for(Joueur j:joueurs){
                for(Case c: adjacentes){
                    if(c.getProprietaire()==j)j.setRessource(biome.r, j.getRessource(biome.r)+1);
                }
            }
        }
    }
    
    public void vol(ArrayList<Joueur> joueurs,Plateau p) {
    	for(Joueur j: joueurs) {
    		if(j.RessourcesTotal()>7) {
    			int nombre=j.RessourcesTotal();
    			int objectif=nombre/2;
    			if(j.IA()) {
    				
    			}else {
    				System.out.println("Pour donner 2 de Bois par exemple, �crivez B2.");
        			while(nombre>objectif) {
        				System.out.println(j.getNom()+", vous avez actuellement "+nombre+" ressources, vous devez en supprimer pour atteindre "+objectif+" ressources.");
        				String [] a=j.demanderRessources();
        				if(a[1]=="O")j.setRessource(Ressource.BOIS, j.getRessource(Ressource.BOIS)-Integer.parseInt(a[2]));
        				else if(a[1]=="R")j.setRessource(Ressource.ARGILE, j.getRessource(Ressource.ARGILE)-Integer.parseInt(a[2]));
        				else if(a[1]=="A")j.setRessource(Ressource.LAINE, j.getRessource(Ressource.LAINE)-Integer.parseInt(a[2]));
        				else if(a[1]=="L")j.setRessource(Ressource.BLE, j.getRessource(Ressource.BLE)-Integer.parseInt(a[2]));
        				else j.setRessource(Ressource.MINERAI, j.getRessource(Ressource.MINERAI)-Integer.parseInt(a[2]));
        				nombre=j.RessourcesTotal();
        			}
    			}
    			
    		}
    	}System.out.println("Le tour du voleur est termin�");
    }

    


}
