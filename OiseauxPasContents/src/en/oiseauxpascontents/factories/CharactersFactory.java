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
 * Usine <<singleton>> pour cloner les personnages selon leur type.
 */
public class CharactersFactory {
	
	private static CharactersFactory instance =	null;
	HashMap<String, GameCharacter> prototypes = new HashMap<String, GameCharacter>();
	
	private CharactersFactory() {}
	
	public static CharactersFactory getInstance(){
		
		if (instance == null) {
			
			instance = new CharactersFactory();
		}
		
		return instance;
	} 
	
	public void loadPrototypes() {
		
		prototypes.put(CharacterType.BIRD, new Bird());
		prototypes.put(CharacterType.PIG, new Pig());
	}
	
	public GameCharacter getCharacter(String character) throws CloneNotSupportedException {
		
		if(character == null) 
			return null;
		
		return prototypes.get(character).clone();
	}
}
