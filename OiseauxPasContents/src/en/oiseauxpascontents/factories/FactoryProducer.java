package en.oiseauxpascontents.factories;

import en.oiseauxpascontents.characters.CharacterType;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe en charge de lancer l'usine principale.
 */
public class FactoryProducer {

	public static CharactersFactory getFactory(String factory) {
		
		if (factory.equalsIgnoreCase(CharacterType.ATTACKERS)) {
			
			return new AttackersFactory();
		
		} else if (factory.equalsIgnoreCase(CharacterType.DEFENDERS)) {
			
			return new DefendersFactory();
		}
		
		return null;
	}
}
