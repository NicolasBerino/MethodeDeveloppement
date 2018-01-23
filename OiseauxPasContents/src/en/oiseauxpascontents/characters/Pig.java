package en.oiseauxpascontents.characters;

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
	
	private boolean beaten = false;
	
	public Pig() {}
	
	public Pig(double posX, double posY) throws IOException{
		
		super(posX, posY);
		this.setImage(ImageIO.read(new File(CharacterConstants.PIG_IMAGE)));
		this.setPositionX(Math.random()* 500 + 200);
		this.setPositionY(480);
	}

	@Override
	public void getCollision(GameCharacter gameCharacter) {
		
		beaten = true;
		
		if(gameCharacter instanceof Bird) {
			
			try {
				
				this.setImage(ImageIO.read(new File(CharacterConstants.PIG_BEATEN)));
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			System.out.println("Collision entre cochon et oiseau.");
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
			
			this.setImage(ImageIO.read(new File(CharacterConstants.PIG_IMAGE)));
		}
	}
	
	/**
	 * Place le cochon dans le second état (état pendant le lancé).
	 * 
	 * @throws IOException Si l'image associée à cet état est inexistante.
	 */
	public void secondState() throws IOException{
		
		if(!beaten) {
			
			this.setImage(ImageIO.read(new File(CharacterConstants.PIG_AFFRAID)));
		}
	}
	
}
