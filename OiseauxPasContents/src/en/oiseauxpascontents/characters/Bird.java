package en.oiseauxpascontents.characters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe identifiant un personnage "oiseau" (BIRD). 
 */
public class Bird extends GameCharacter {
	
	public Bird() {}
	
	public Bird(double posX, double posY) throws IOException {
		
		super(posX, posY);
		Image image = ImageIO.read(new File("images/bird.png"));
		this.setImage(image);
	}

	@Override
	public void getCollision(GameCharacter gC) {
		
		if(gC instanceof Pig){
			
			System.out.println("Collision entre oiseau et cochon.");
		}
		
	}
	
}
