package en.oiseauxpascontents.game;

import java.util.ArrayList;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.CharacterType;
import en.oiseauxpascontents.characters.Pig;
import en.oiseauxpascontents.factories.CharactersFactory;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe contenant les paramètres d'un niveau.
 */
public class Level {

	private int nbBird;					// Nombre d'oiseaux
    private int nbPig;					// Nombre de cochons
    
    private double gravity;     		// gravité
    
    private String backgroundImage;		// Image de fond
    
	public Level(double gravity, int nbBird, int nbPig, String backgroundImage) {
		
		this.nbBird = nbBird;
		this.nbPig = nbPig;
		this.gravity = gravity;
		this.backgroundImage = backgroundImage;
		
		CharactersFactory.getInstance().loadPrototypes();
	}
    
	public String getBackgroundImage() {
		
		return backgroundImage;
	}

	public ArrayList<Bird> getBirds() throws CloneNotSupportedException {
		
		ArrayList<Bird> birds = new ArrayList<Bird>();
		
		for(int i = 0; i < nbBird; i++) {
			
			birds.add((Bird)CharactersFactory.getInstance().getCharacter(CharacterType.BIRD));
		}
		
		return birds;
	}

	public ArrayList<Pig> getPigs() throws CloneNotSupportedException {
		
		ArrayList<Pig> pigs = new ArrayList<Pig>();
		
		for(int i = 0; i < nbPig; i++){
			
			pigs.add((Pig)CharactersFactory.getInstance().getCharacter(CharacterType.PIG));
		}
		
		return pigs;
	}

	public double getGravity() {
		
		return gravity;
	}

	public void setGravity(double gravity) {
		
		this.gravity = gravity;
	}
}
