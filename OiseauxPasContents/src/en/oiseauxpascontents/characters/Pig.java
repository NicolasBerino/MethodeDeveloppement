package en.oiseauxpascontents.characters;

import java.awt.Toolkit;
import java.io.IOException;

import en.oiseauxpascontents.game.GameConstants;
import en.oiseauxpascontents.main.Main;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe identifiant un personnage "cochon" (PIG). 
 */
public class Pig extends GameCharacter {
	
	private boolean beaten = false;
	
	public Pig() {}
	
	public Pig(double posX, double posY) throws IOException{
		
		super(posX, posY);
	}

	@Override
	public void getCollision(GameCharacter gameCharacter) {
		
		beaten = true;
		
		if(gameCharacter instanceof Bird) {
			
			this.setImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.PIG_BEATEN_IMAGE)));
		}
	}
	
	/**
	 * Vérifie si le cochon est touché.
	 * 
	 * @return true si le cochon est touché.
	 */
	public boolean isBeaten() {
		
		return beaten;
	}

	/**
	 * Place le cochon dans le premier état (état normal avant le lancé).
	 * 
	 * @throws IOException Si l'image associée à cet état est inexistante.
	 */
	public void firstState() throws IOException{
		
		if (!beaten) {
			
			this.setImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.PIG_IMAGE)));
		}
	}
	
	/**
	 * Place le cochon dans le second état (état pendant le lancé).
	 * 
	 * @throws IOException Si l'image associée à cet état est inexistante.
	 */
	public void secondState() throws IOException{
		
		if(!beaten) {
			
			this.setImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.PIG_AFFRAID_IMAGE)));
		}
	}
	
}
