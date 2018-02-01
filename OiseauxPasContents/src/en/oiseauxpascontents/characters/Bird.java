package en.oiseauxpascontents.characters;

import java.awt.Toolkit;
import java.io.IOException;

import en.oiseauxpascontents.game.GameConstants;
import en.oiseauxpascontents.main.Main;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.1
 *
 * Classe identifiant un personnage "oiseau" (BIRD). 
 */
public class Bird extends GameCharacter {
	
	public Bird() {}
	
	public Bird(double posX, double posY) throws IOException {
		
		super(posX, posY);
		this.setImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.BIRD_IMAGE)));
	}

	@Override
	public void getCollision(GameCharacter gameCharacter) {
		
		if(gameCharacter instanceof Pig){
			
			this.setImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.BEAT_IMAGE)));
		
		} else {
			
			this.setImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.BIRD_CRASHED_IMAGE)));
		}
	}
	
}
