package en.oiseauxpascontents.game;

import java.util.ArrayList;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.CharacterConstants;
import en.oiseauxpascontents.characters.Pig;
import en.oiseauxpascontents.factories.CharactersFactory;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.1
 *
 * Classe contenant les paramètres d'un niveau.
 */
public class Level {

	private int nbBird;					// Nombre d'oiseaux
    private int nbPig;					// Nombre de cochons
    private int nbBlackHoles;			// Nombre de trous noirs.
    
    private double gravity;     		// gravité
    
	public Level(int nbBird, int nbPig, int nbBlackHoles, double gravity) {
		
		this.nbBird = nbBird;
		this.nbPig = nbPig;
		this.nbBlackHoles = nbBlackHoles;
		this.gravity = gravity;
		
		CharactersFactory.getInstance().loadPrototypes();
		
		System.out.println("Niveau initialisé avec " + nbBird + " essai(s), " + nbPig + " cochon(s) et " + nbBlackHoles + " trou(s) noir (gravité : " + this.gravity + " ).");
	}

	/**
	 * Construit une liste des personnages "oiseau".
	 * 
	 * @return La liste de personnages "oiseau".
	 * 
	 * @throws CloneNotSupportedException
	 */
	public ArrayList<Bird> getBirds() throws CloneNotSupportedException {
		
		ArrayList<Bird> birds = new ArrayList<Bird>();
		
		for(int i = 0; i < nbBird; i++) {
			
			birds.add((Bird)CharactersFactory.getInstance().getCharacter(CharacterConstants.BIRD));
		}
		
		return birds;
	}

	/**
	 * Construit une liste des personnages "cochon".
	 * 
	 * @return La liste de personnages "cochon".
	 * 
	 * @throws CloneNotSupportedException
	 */
	public ArrayList<Pig> getPigs() throws CloneNotSupportedException {
		
		ArrayList<Pig> pigs = new ArrayList<Pig>();
		
		for(int i = 0; i < nbPig; i++){
			
			pigs.add((Pig)CharactersFactory.getInstance().getCharacter(CharacterConstants.PIG));
		}
		
		return pigs;
	}

	public double getGravity() {
		
		return gravity;
	}

	public void setGravity(double gravity) {
		
		this.gravity = gravity;
	}
	
	public int getNbBlackHoles() {
		
		return nbBlackHoles;
	}

	public void setNbBlackHoles(int nbBlackHoles) {
		
		this.nbBlackHoles = nbBlackHoles;
	}
}
