package en.oiseauxpascontents.factories;

import java.util.HashMap;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.CharacterConstants;
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
	
	/**
	 * Charge une instance de chaque personnages présents,
	 * en vue de les cloner plus tard. 
	 */
	public void loadPrototypes() {
		
		prototypes.put(CharacterConstants.BIRD, new Bird());
		prototypes.put(CharacterConstants.PIG, new Pig());
	}
	
	/**
	 * Clone un personnage (récupère une instance clonée).
	 * 
	 * @param character
	 * 		Le personnage à cloner.
	 * 
	 * @return Le personnage issu du clonage, "null" si le personnage n'existe pas.
	 * 
	 * @throws CloneNotSupportedException
	 */
	public GameCharacter getCharacter(String character) throws CloneNotSupportedException {
		
		if(character == null) 
			return null;
		
		return prototypes.get(character).clone();
	}
}
