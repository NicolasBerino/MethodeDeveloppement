package en.oiseauxpascontents.characters;

import en.oiseauxpascontents.interfaces.IDefender;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe identifiant un personnage défenseur (PIG). 
 */
public class Pig extends Character implements IDefender {
	
	public Pig() {}
	
	public Pig(double posX, double posY){
		
		super(posX, posY);
		this.setColor(""); //TODO: remplacer par l'image.
		this.setPositionX(Math.random()* 500 + 200);
		this.setPositionY(480);
	}
}
