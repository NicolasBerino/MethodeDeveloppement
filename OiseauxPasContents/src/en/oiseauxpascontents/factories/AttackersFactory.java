package en.oiseauxpascontents.factories;

import java.util.HashMap;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.CharacterType;
import en.oiseauxpascontents.interfaces.IAttacker;
import en.oiseauxpascontents.interfaces.IDefender;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Usine pour construire les personnages attaquants.
 */
public class AttackersFactory extends CharactersFactory {

	private HashMap<String, IAttacker> prototypes = new HashMap<String, IAttacker>();
	
	public void loadCharacters() {
		
		prototypes.put(CharacterType.BIRD, new Bird());
	}
	
	public IAttacker getAttacker(String attacker) {
		
		if (attacker.equalsIgnoreCase(CharacterType.BIRD)) {
			
			return prototypes.get(CharacterType.BIRD).clone();
		}
		
		return null;
	}

	@Override
	public IDefender getDefender(String defender) {
		return null;
	}
}
