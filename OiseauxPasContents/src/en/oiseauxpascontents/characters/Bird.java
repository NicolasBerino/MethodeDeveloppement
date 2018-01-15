package en.oiseauxpascontents.characters;

import en.oiseauxpascontents.interfaces.IAttacker;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe identifiant un personnage attaquant (BIRD). 
 */
public class Bird extends Character implements IAttacker {
	
	public Bird() {}
	
	public Bird(double posX, double posY) {
		
		super(posX, posY);
		this.setColor(""); //TODO: remplacer par l'image.
	}
}
