package en.oiseauxpascontents.factories;

import java.util.HashMap;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.CharacterType;
import en.oiseauxpascontents.characters.Pig;
import en.oiseauxpascontents.interfaces.IAttacker;
import en.oiseauxpascontents.interfaces.IDefender;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Usine pour construire les personnages défenseurs.
 */
public class DefendersFactory extends CharactersFactory {

	private HashMap<String, IDefender> prototypes = new HashMap<String, IDefender>();
	
	public void loadCharacters() {
		
		prototypes.put(CharacterType.PIG, new Pig());
	}
	
	@Override
	public IAttacker getAttacker(String attacker) {
		return null;
	}

	public IDefender getDefender(String defender) {

		if (defender.equalsIgnoreCase(CharacterType.BIRD)) {
			
			return prototypes.get(CharacterType.BIRD).clone();
		}
		
		return null;
	}
}
