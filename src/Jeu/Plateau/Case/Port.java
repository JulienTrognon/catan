package Jeu.Plateau.Case;



/**
 * représente un terrain qui donne un effet
 * aux bâtiments qui y sont adjacents.
 */
public class Port extends Case {

    private int taux; // 2 ou 3 seulement
    private Biome biome;

    public int getTaux() {return taux;}
    public Biome getBiome() {return biome;}

    /** construit un port de ratio 2, sans biome */
    public Port() {}
    /** construit un port de ratio 2, avec le biome choisi */
    public Port(Biome biome) {
        this.biome = biome;
    }
    /** construit un port de ratio 2 ou 3, sans biome */
    public Port(int taux) {
        this.taux = taux;
    }
    /** construit un port de ratio 2 ou 3, avec le biome choisi. */
    public Port(Biome biome, int taux) {
        this.taux = taux;
        this.biome=biome;
    }
    

    @Override
    public String toString() {
    	if(biome==null) return " P"+Integer.toString(taux);
    	else return " P"+Integer.toString(taux)+biome.name().substring(0,1);
    }

}
