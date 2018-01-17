package en.oiseauxpascontents.factories;

import java.util.HashMap;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.CharacterType;
import en.oiseauxpascontents.characters.GameCharacter;
import en.oiseauxpascontents.characters.Pig;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Usine abstraite pour construire les personnages 
 * selon leur type grâce à d'autres usines.
 */
public class CharactersFactory {
	
	private static CharactersFactory instance =	new CharactersFactory();
	private CharactersFactory(){}
	public static CharactersFactory getInstance(){
		return instance;
	}
	
	HashMap<String, GameCharacter> prototypes = new HashMap<String, GameCharacter>(); 
	
	public void loadPrototypes(){
		prototypes.put(CharacterType.BIRD, new Bird());
		prototypes.put(CharacterType.PIG, new Pig());
	}
	
	
	
	public GameCharacter getCharacter(String character) throws CloneNotSupportedException{
		if(character == null) return null;
		return prototypes.get(character).clone();
	}
}
