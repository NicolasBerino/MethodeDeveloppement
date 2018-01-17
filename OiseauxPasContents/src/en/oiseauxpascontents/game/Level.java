package en.oiseauxpascontents.game;

import java.util.ArrayList;

import en.oiseauxpascontents.characters.CharacterType;
import en.oiseauxpascontents.characters.GameCharacter;
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
    
	public ArrayList<GameCharacter> getBirds() throws CloneNotSupportedException{
		ArrayList<GameCharacter> birds = new ArrayList<GameCharacter>();
		for(int i=0;i<nbBird;i++){
			birds.add(CharactersFactory.getInstance().getCharacter(CharacterType.BIRD));
		}
		return birds;
	}

	public ArrayList<GameCharacter> getPigs() throws CloneNotSupportedException{
		ArrayList<GameCharacter> pigs = new ArrayList<GameCharacter>();
		for(int i=0;i<nbPig;i++){
			pigs.add(CharactersFactory.getInstance().getCharacter(CharacterType.PIG));
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
