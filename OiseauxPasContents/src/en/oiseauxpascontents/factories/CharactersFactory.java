package en.oiseauxpascontents.factories;

import en.oiseauxpascontents.interfaces.IAttacker;
import en.oiseauxpascontents.interfaces.IDefender;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Usine abstraite pour construire les personnages 
 * selon leur type grâce à d'autres usines.
 */
public abstract class CharactersFactory {
	
	public abstract IAttacker getAttacker(String attacker);
	public abstract IDefender getDefender(String defender);
}
