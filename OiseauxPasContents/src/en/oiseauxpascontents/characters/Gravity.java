package en.oiseauxpascontents.characters;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe abstraite identifiant l'élément gravité du jeu. 
 */
public abstract class Gravity extends GameCharacter{
		
	double gravity;
	
	public abstract void actOn(GameCharacter token);

	public double getGravity() {
		
		return gravity;
	}

	public void setGravity(double gravity) {
		
		this.gravity = gravity;
	}

	
	
}
