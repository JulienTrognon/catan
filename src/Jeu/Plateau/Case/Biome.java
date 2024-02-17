package Jeu.Plateau.Case;

import Jeu.Ressource;

/** Tous les biomes existants sur le plateau */
public enum Biome {

    /** produit du {@code BOIS} */
    FORET(Ressource.BOIS),

    /** produit de l'{@code ARGILE} */
    COLLINE(Ressource.ARGILE),

    /** produit de la {@code LAINE} */
    PRE(Ressource.LAINE),

    /** produit du {@code BLE} */
    CHAMPS(Ressource.BLE),

    /** produit du {@code MINERAI} */
    MONTAGNE(Ressource.MINERAI),

    DESERT(null),

    PORT(null);

    public Ressource r;

    Biome(Ressource r){
        this.r=r;
    }

}
