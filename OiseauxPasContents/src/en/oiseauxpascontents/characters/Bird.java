package en.oiseauxpascontents.characters;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe identifiant un personnage attaquant (BIRD). 
 */
public class Bird extends GameCharacter {
	
	public Bird() {}
	
	public Bird(double posX, double posY) throws IOException {
		
		super(posX, posY);
		Image im = ImageIO.read(new File("images/bird.png"));
	}

	@Override
	public void getCollision(GameCharacter gC) {
		if(gC instanceof Pig){
			System.out.println("coliision");
		}
		
	}
	
}
