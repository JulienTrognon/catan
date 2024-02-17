package Jeu;

import Jeu.Plateau.Case.Chemin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** représente un joueur */
public class Joueur {

    private static int nbJoueurs;
    private static int bestPoints;
    /** numéro du joueur. Définit aussi l'ordre de passage du joueur dans chaque tour */
    private int id;

    // Infos personnelles
    private String nom;
    private int points;
    private final Couleur c;
    private final boolean IA;	//False=Joueur r�el, True=IA

    //Infos partie
    private ArrayList<Chemin>chemins;
    private boolean meilleurRoute;


    // Inventaire

    // Ressources du Joueur à sa disposition.
    // nbRoutes <= 15 ; nbColonies <= 5 ; nbVilles <= 4
    private int nbRoutes   = 15;
    private int nbColonies = 5;
    private int nbVilles   = 4;
    /** inventaire de {@link Ressource}s pour le {@link Joueur} */
    private HashMap<Ressource, Integer> nbRessources = new HashMap<>();
    /** inventaire de cartes pour le {@link Joueur} */
    private HashMap<String, Integer> nbCartes = new HashMap<>();

    private Scanner scanReponse;

    public static int getNbJoueurs() { return nbJoueurs; }
    public static int getBestPoints() { return bestPoints; }

    public String getNom() { return nom; }
    public Couleur getColor(){ return c;}   
    public boolean IA() {return IA;}

    public int getPoints() { return points; }
    public void setpoints(int n){ points=n;} 

    public int getOrdre() { return id; }

    public int getNbRoutes() { return nbRoutes; }
    public int getNbColonies() { return nbColonies; }
    public int getNbVilles() { return nbVilles; }

    public Map<Ressource, Integer> getNbRessources() { return nbRessources; }
    public int getRessource(Ressource r) {
        try{return nbRessources.get(r).intValue(); }
        catch (Exception e){System.out.println("Le nom de votre ressource n'est pas bonne.");}
        return 0;
    }

    public void setRessource(Ressource s, int n) {
		nbRessources.put(s, Integer.valueOf(n));
	}
    public Map<String, Integer> getNbCartes() { return nbCartes; }

    public Joueur(Couleur c){
        this.c=c;
        nbJoueurs++;
        id = nbJoueurs;
        IA=false;

        chemins=new ArrayList<Chemin>();
        scanReponse=new Scanner(System.in);

        nbRessources.put(Ressource.BOIS, 0);
        nbRessources.put(Ressource.ARGILE, 0);
        nbRessources.put(Ressource.LAINE, 0);
        nbRessources.put(Ressource.BLE, 0);
        nbRessources.put(Ressource.MINERAI, 0);

        nbCartes.put("",0);
        nbCartes.put("",0);
        nbCartes.put("",0);
        nbCartes.put("",0);
        nbCartes.put("",0);
    }

    public Joueur(String nom, Couleur c) {
        IA=true;
        this.c=c;
        nbJoueurs++;
        id = nbJoueurs;

        this.nom = nom;
        chemins=new ArrayList<Chemin>();
        scanReponse=new Scanner(System.in);

        nbRessources.put(Ressource.BOIS, 0);
        nbRessources.put(Ressource.ARGILE, 0);
        nbRessources.put(Ressource.LAINE, 0);
        nbRessources.put(Ressource.BLE, 0);
        nbRessources.put(Ressource.MINERAI, 0);

        nbCartes.put("",0);
        nbCartes.put("",0);
        nbCartes.put("",0);
        nbCartes.put("",0);
        nbCartes.put("",0);
    }


    /** 
     * intervertit la position du joueur {code j}
     * et celle du joueur courant.
     */
    public void switchOrdre(Joueur j) {
        int tmp = j.getOrdre();
        j.id = this.id;
        this.id = tmp; 
    }

    /** 
     * consomme une route 
     * et les {@link Ressource}s 
     * nécessaires pour la fabriquer.
     * 
     * @return    {@code false} si les conditions 
     *            pour poser une route 
     *            ne sont pas remplies.
     */
    
    public boolean useFirstR() {
    	if(nbRoutes <= 0) return false;
    	else{
    		nbRoutes--;
    		return true;
    	}
    }
    
    public boolean useRoute() {
        if(nbRoutes <= 0) return false;
        else if((getRessource(Ressource.ARGILE)<1)||(getRessource(Ressource.BOIS)<1)){
        	System.out.println("Vous n'avez pas les ressources necessaires.");
        	return false;
        }
        else {
            setRessource(Ressource.ARGILE, getRessource(Ressource.ARGILE)-1);
            setRessource(Ressource.BOIS, getRessource(Ressource.BOIS)-1);
            nbRoutes--;
        }
        return true;
    }

    /** 
     * consomme une colonie
     * et les {@link Ressource}s 
     * nécessaires pour la fabriquer. 
     * 
     * @return    {@code false} si les conditions 
     *            pour poser une colonie         
     *            ne sont pas remplies.
     */
    
    public boolean useFirstC() {
    	nbColonies--;
    	return true;
    }
    public boolean useColonie() {
        if(nbColonies <= 0) return false;
        else if((getRessource(Ressource.ARGILE)<1)||(getRessource(Ressource.BOIS)<1)||(getRessource(Ressource.BLE)<1)||(getRessource(Ressource.LAINE)<1)){
        	System.out.println("Vous n'avez pas les ressources necessaires.");
        	return false;
        }else {
            setRessource(Ressource.ARGILE, getRessource(Ressource.ARGILE)-1);
            setRessource(Ressource.LAINE, getRessource(Ressource.LAINE)-1);
            setRessource(Ressource.BLE, getRessource(Ressource.BLE)-1);
            setRessource(Ressource.BOIS, getRessource(Ressource.BOIS)-1);
            nbColonies--;
        }
        return true;
    }
    /** 
     * consomme une ville
     * et les {@link Ressource}s 
     * nécessaires pour la fabriquer. 
     * 
     * @return    {@code false} si les conditions 
     *            pour poser une ville         
     *            ne sont pas remplies.
     */
    public boolean useVille() {
        if(nbVilles <= 0) return false;
        else if((getRessource(Ressource.MINERAI)<3)||(getRessource(Ressource.BLE)<2)){
        	System.out.println("Vous n'avez pas les ressources necessaires.");
        	return false;
        }else {
            setRessource(Ressource.MINERAI, getRessource(Ressource.MINERAI)-3);
            setRessource(Ressource.BLE, getRessource(Ressource.BLE)-2);
            nbVilles--;
        }
        return true;
    }   

    
    public void addRoute(Chemin r){ chemins.add(r);}          //Ajoute une route au joueur
    public ArrayList<Chemin> getRoutes(){return chemins;}     //Renvoie toutes les routes du joueur dans une liste

    public boolean getmeilleurRoute(){ return meilleurRoute;}
    public void setmeilleurRoute(boolean b){
        if(meilleurRoute==true && b==false)
            points--;
        else if(meilleurRoute==false && b==true)
            points++;
        meilleurRoute=b;
    }


    public int nbJoueurs(){
        System.out.println("Comment d'adversaires voulez-vous(2 ou 3)?");
        int a=scanReponse.nextInt();
        return a;
    }

    public String demanderNom() {
		System.out.println("Quel est votre nom?");
		String reponse=scanReponse.nextLine();
		this.nom=reponse;
		return reponse;
	}
    
    public boolean typeAdversaire(){
        scanReponse.nextLine();
        System.out.println("Voulez-vous jouer contre des joueurs ou des IA (j ou IA)?");
        String s=scanReponse.nextLine();
        if(s.equals("j"))return true;
        else return false;
    }
    public boolean choix(){
        System.out.println("Voulez-vous jouer sur le terminal ou avec une interface graphique(terminal ou gui)?");
        String s=scanReponse.nextLine();
        if(s.equals("gui"))return true;
        else return false;
    }

    public int [] demanderCoordonnes() {
		scanReponse.nextLine();
		System.out.println("Ou voulez-vous jouer?");
		String reponse=scanReponse.nextLine();
		int a=Character.getNumericValue(reponse.charAt(0))-10;
		int b=Character.getNumericValue(reponse.charAt(1));
		int [] r= {a,b} ;
		return r;
	}
    public char demanderAction(){
        System.out.println("Voulez-vous placer une route(r), une colonie(c) ou une ville(v)?");
        String reponse=scanReponse.nextLine();
        if(reponse.equals("r"))return 'r';
        else if(reponse.equals("c"))return 'c';
        else return 'v';
    }
    
    public String [] demanderRessources() {
    	scanReponse.nextLine();
		System.out.println("Quelles ressources souhaiter vous donner?");
		String reponse=scanReponse.nextLine();
		String a;String b;String c;
		if(reponse.length()==3){
			a=Character.toString(reponse.charAt(0));
			b=Character.toString(reponse.charAt(1));
			c=Character.toString(reponse.charAt(2));
			String r[]= {a,b,c} ;
			return r;
		}else {
			System.out.println("Veuillez �crire correctement.");
			return  null;
		}
    }
    
    @SuppressWarnings("unlikely-arg-type")
	public int RessourcesTotal() {
    	int compteur=0;
    	for(int i=0;i<nbRessources.size();i++) {
    		compteur+=nbRessources.get(i).intValue();
    	}
    	return compteur;
    }


    public void finish() {
		scanReponse.close();
	}

}
