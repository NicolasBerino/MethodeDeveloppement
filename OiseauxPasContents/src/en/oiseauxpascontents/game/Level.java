package en.oiseauxpascontents.game;

import java.util.ArrayList;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.CharacterType;
import en.oiseauxpascontents.characters.GameCharacter;
import en.oiseauxpascontents.characters.Pig;
import en.oiseauxpascontents.factories.CharactersFactory;

public class Level {

    private double gravity;                             // gravité
    private int nbBird;				
    private int nbPig;
    private String backgroundImage;
    
	public Level(double gravity, int nbBird, int nbPig, String backgroundImage) {
		this.gravity = gravity;
		this.nbBird = nbBird;
		this.nbPig = nbPig;
		this.backgroundImage = backgroundImage;
		CharactersFactory.getInstance().loadPrototypes();
	}
    
	public ArrayList<Bird> getBirds() throws CloneNotSupportedException{
		ArrayList<Bird> birds = new ArrayList<Bird>();
		for(int i=0;i<nbBird;i++){
			birds.add((Bird)CharactersFactory.getInstance().getCharacter(CharacterType.BIRD));
		}
		return birds;
	}

	public ArrayList<Pig> getPigs() throws CloneNotSupportedException{
		ArrayList<Pig> pigs = new ArrayList<Pig>();
		for(int i=0;i<nbPig;i++){
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
