// import java.awt.Color;
import java.util.ArrayList;

import Jeu.Couleur;
import Jeu.Joueur;

public class Lanceur {

    private static Joueur courant;
    private static ArrayList<Joueur> joueurs=new ArrayList<Joueur>();
    private static Jeu jeu;
    private static int nbAdversaires;
    // private static int nbJoueurs;
    private static boolean GUI;
    private static boolean typeAdversaire;

    public static void main(String [] args){
        joueurs.add(new Joueur( Couleur.BLUE));
        courant=joueurs.get(0);
        courant.demanderNom();                                                //Demande le nom du joueur
        nbAdversaires=courant.nbJoueurs();                                    //Demande le nombre d'adversaire (2 ou 3)
        typeAdversaire=courant.typeAdversaire();                              //Demande le type d'adversaire (IA ou joueurs)
        if(!typeAdversaire){                                                  //Si ce sont des IA, on les définis.
            joueurs.add(new Joueur("IA1", Couleur.RED));
            joueurs.add(new Joueur("IA2", Couleur.GREEN));
            if(nbAdversaires==3){joueurs.add(new Joueur("IA3", Couleur.YELLOW));}
        }else{                                                                //Sinon, on demander le nom de chaque joueur
            joueurs.add(new Joueur( Couleur.RED));
            joueurs.get(1).demanderNom();
            joueurs.add(new Joueur( Couleur.GREEN));
            joueurs.get(2).demanderNom();
            if(nbAdversaires==3){
                joueurs.add(new Joueur( Couleur.YELLOW));
                joueurs.get(3).demanderNom();
            }
        }
        // nbJoueurs=joueurs.size();
        jeu=new Jeu(joueurs);
        GUI=courant.choix();                                             //Demande le type d'interface (terminal ou GUI)
        if(GUI){                                                         //Lance le jeu en fonction de l'interface choisie
            jeu.jouerGUI();
        }else{
            jeu.jouerConsole();
        }joueurs.get(0).finish();
    }
}
