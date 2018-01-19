package en.oiseauxpascontents.characters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe identifiant un personnage "cochon" (PIG). 
 */
public class Pig extends GameCharacter {
	
	public Pig() {}
	
	public Pig(double posX, double posY) throws IOException{
		
		super(posX, posY);
		Image im = ImageIO.read(new File("images/pig.png"));
		this.setImage(im);
		this.setPositionX(Math.random()* 500 + 200);
		this.setPositionY(480);
	}

	@Override
	public void getCollision(GameCharacter gC) {
		
		if(gC instanceof Bird){
			
			System.out.println("Collision entre cochon et oiseau.");
		}
		
	}
	
}
