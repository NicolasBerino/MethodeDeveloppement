package en.oiseauxpascontents.game;

import java.util.ArrayList;
import java.util.Iterator;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.BlackHole;
import en.oiseauxpascontents.characters.ClassicGravity;
import en.oiseauxpascontents.characters.GameCharacter;
import en.oiseauxpascontents.characters.Gravity;
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
    
    private ArrayList<Gravity> gravities;     		// gravité
    
	public Level(int nbBird, int nbPig, int nbBlackHoles, double gravity) throws CloneNotSupportedException {
		
		this.nbBird = nbBird;
		this.nbPig = nbPig;
		this.nbBlackHoles = nbBlackHoles;
		this.gravities= new ArrayList<Gravity>();
		this.gravities.add(new ClassicGravity(gravity));
		
		CharactersFactory.getInstance().loadPrototypes();
		this.gravities.addAll(getBlackHoles());
		
		
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
			
			birds.add((Bird)CharactersFactory.getInstance().getCharacter(GameConstants.BIRD));
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
			
			pigs.add((Pig)CharactersFactory.getInstance().getCharacter(GameConstants.PIG));
		}
		
		return pigs;
	}

	public ArrayList<BlackHole> getBlackHoles() throws CloneNotSupportedException{
		ArrayList<BlackHole> blackHoles = new ArrayList<BlackHole>();
		for(int i = 0;i< nbBlackHoles;i++){
			blackHoles.add((BlackHole)CharactersFactory.getInstance().getCharacter(GameConstants.BLACK_HOLE));
		}
		return blackHoles;
	}
	
	
	public ArrayList<Gravity> getListGravity() {
		
		return gravities;
	}

	public int getNbBlackHoles() {
		
		return nbBlackHoles;
	}

	public void setNbBlackHoles(int nbBlackHoles) {
		
		this.nbBlackHoles = nbBlackHoles;
	}
	
	public void applyGravity(GameCharacter token){
		Iterator<Gravity> itGrav = gravities.iterator();
		while(itGrav.hasNext()){
			itGrav.next().actOn(token);
		}
	}
	
}
