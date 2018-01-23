package en.oiseauxpascontents.characters;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.1
 *
 * Classe identifiant un personnage "oiseau" (BIRD). 
 */
public class Bird extends GameCharacter {
	
	public Bird() {
	}
	
	public Bird(double posX, double posY) throws IOException {
		
		super(posX, posY);
		this.setImage(ImageIO.read(new File(CharacterConstants.BIRD_IMAGE)));
	}

	@Override
	public void getCollision(GameCharacter gameCharacter) {
		
		if(gameCharacter instanceof Pig){
			
			try {
				
				this.setImage(ImageIO.read(new File(CharacterConstants.BEAT_IMAGE)));
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			System.out.println("Collision entre oiseau et cochon.");
		
		} else {
			
			try {
				
				this.setImage(ImageIO.read(new File(CharacterConstants.BIRD_CRASHED)));
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			System.out.println("Collision entre oiseau et mur ou sol.");
		}
	}
	
}
