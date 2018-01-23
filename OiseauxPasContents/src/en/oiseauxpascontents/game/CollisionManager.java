package en.oiseauxpascontents.game;

import java.util.ArrayList;
import java.util.Iterator;

import en.oiseauxpascontents.characters.GameCharacter;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.1
 *
 * Classe <<singleton>> de gestion des collisions entre les objets.
 */
public class CollisionManager {
	
	private static CollisionManager instance = null;
	private ArrayList<GameCharacter> observableCharacters = new ArrayList<GameCharacter>();

	private CollisionManager() {}
	
	public static CollisionManager getInstance() {
		
		if (instance == null) {
			
			instance = new CollisionManager();
		}
		
		return instance;
	}
	
	/**
	 * Ajoute un personnage à observer.
	 * 
	 * @param gameCharacter
	 * 		Le personnage à observer.
	 */
	public void addObservableCharacter(GameCharacter gameCharacter) {
		
		observableCharacters.add(gameCharacter);
	}
	
	/**
	 * Vide la liste des personnages à observer.
	 */
	public void clearObservableCharacter() {
		
		observableCharacters.clear();
	}
	
	/**
	 * Vérifie si une collision s'est produite.
	 * 
	 * @return 0 si une collision s'est produite entre deux personnages,
	 * 		   1 si une collision s'est produite avec un mur ou le sol,
	 * 		   2 si aucune collision ne s'est produite.
	 */
	public int checkCollision() {
		
		Iterator<GameCharacter> itOC1 = observableCharacters.iterator();
		
		while(itOC1.hasNext()) {
			
			GameCharacter gc1 = itOC1.next();
			
			Iterator<GameCharacter> itOC2 = observableCharacters.iterator();
			
			while(itOC2.hasNext()) {
				
				GameCharacter gc2 = itOC2.next();
				
				if (gc1 == gc2) 
					break;
				
				if(distance(gc1.getPositionX(), gc1.getPositionY(), gc2.getPositionX(), gc2.getPositionY()) < 35) {
					
					gc1.getCollision(gc2);
					gc2.getCollision(gc1);
					return 0;
				}
			}
			
			if(gc1.getPositionX() < 20 || gc1.getPositionX() > 780 ||gc1.getPositionY() < 0 || gc1.getPositionY() > 480) {
				
				gc1.getCollision(null);//collision avec le sol ou un mur
				return 1;
			}
		}
		
		return 2;
	}
	
    public static double distance(double x1, double y1, double x2, double y2) {
    	
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
