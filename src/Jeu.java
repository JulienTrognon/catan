// import java.awt.Color;
import java.util.ArrayList;
// import java.util.concurrent.*;

import Jeu.Joueur;
import Jeu.Plateau.Plateau;
// import Jeu.Plateau.Case.Tuile;

public class Jeu {
    
    private Plateau p;
    private ArrayList<Joueur> joueurs;

    public Jeu(ArrayList<Joueur> j){
        joueurs=j;
        p=new Plateau();
    }

    public Plateau getPlateau(){return p;}

    public boolean victoire(){
        for(Joueur j: joueurs){
            if(j.getPoints()>=10)return true;
        }return false;
    }

    public Joueur gagnant(){
        for(Joueur j: joueurs){
            if(j.getPoints()>=10)return j;
        }return null;
    }
    
    public void firstRound() {
    	for(Joueur j: joueurs){
    		System.out.print("Commencez par placer une colonie.");
    		int []c=j.demanderCoordonnes();
    		char a=j.demanderAction();
            if(a=='r')p.addRoute(j, c[0], c[1]);
            else p.firstColonie(j, c[0], c[1]);
            System.out.print("Maintenant placer les deux routes.");
            int []co=j.demanderCoordonnes();
    		char ac=j.demanderAction();
            if(ac=='r')p.RouteGratuite(j,co[0], co[1]);
            int []coo=j.demanderCoordonnes();
    		char act=j.demanderAction();
            if(act=='r')p.RouteGratuite(j, coo[0], coo[1]);
            p.affiche();
    	}
    }
    
    public void jouerConsole(){
        p.affiche();
        firstRound();
        /*while(!victoire()){
            for(Joueur j: joueurs){ 
                int randomNum = ThreadLocalRandom.current().nextInt(1,6)+ThreadLocalRandom.current().nextInt(1,6);
                System.out.println(j.getNom()+" lance ses dés et obtient "+randomNum);
                System.out.println("Les ressources sont distribués aux bons joueurs.");
                for(Tuile t:p.getTuiles()){
                    if(t.getValeur()==randomNum)t.production(joueurs ,p);
                }int []c=j.demanderCoordonnes();
                char a=j.demanderAction();
                if(a=='r')p.addRoute(j, c[0], c[1]);
                else if(a=='c')p.addColonie(j, c[0], c[1]);
                else p.addVille(j, c[0],c[1]);
                p.affiche();
            }
        }if(gagnant()==joueurs.get(0)) System.out.println("Bravo, vous avez gagne la partie!");
		else System.out.println("Vous avez perdu!");
		*/
    }

    public void jouerGUI() {
    }
    

}
