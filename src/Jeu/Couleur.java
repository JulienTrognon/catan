package Jeu;

public enum Couleur {
	//Color end string, color reset
    RESET("\033[0m"),

    // Regular Colors. Normal color, no bold, background color etc.
    BLACK("\033[0;30m"),    // BLACK
    RED("\033[0;31m"),      // RED
    GREEN("\033[0;32m"),    // GREEN
    YELLOW("\033[0;33m"),   // YELLOW
    BLUE("\033[0;34m");    // BLUE

	private final String code;
	
	Couleur(String code){
		this.code=code;
	}
	
	public String getCode() {
		return code;
	}
}
