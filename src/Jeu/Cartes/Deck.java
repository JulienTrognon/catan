package Jeu.Cartes;

import java.util.ArrayList;
import java.util.Collections;
// import java.util.Stack;

public class Deck {
    
    private ArrayList<Carte> deck;


    public ArrayList<Carte> getDeck() { return deck; }


    public Deck() {			//Cr�ation du deck du jeu contenant toutes les cartes, puis on les m�lange avec shuffle
        deck = new ArrayList<Carte>();

        for(int i = 0 ; i < 14 ; i++)
            deck.add(new Chevalier());
        for(int i = 0 ; i < 2 ; i++)
            deck.add(new ConstructionRoutes());
        for(int i = 0 ; i < 5 ; i++)
            deck.add(new PointVictoire());
        Collections.shuffle(deck);
    }
    
    public Carte last() {	//Permet de r�cup�rer la derniere carte
    	if(deck.size()>0) {return deck.remove(deck.size()-1);}
    	else {
    		System.out.println("Il n'y a plus de cartes a recuperer.");
    		return null;
    	}
    }


    

}
