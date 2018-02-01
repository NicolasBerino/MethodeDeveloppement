package en.oiseauxpascontents.characters;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe identifiant une gravité classique (celle du sol). 
 */
public class ClassicGravity extends Gravity{

	public ClassicGravity(double gravity){
		this.gravity=gravity;
	}
	
	@Override
	public void actOn(GameCharacter token) {
		token.setVelocityY(token.getVelocityY()+this.gravity);
	}

	@Override
	public void getCollision(GameCharacter gameCharacter) {}

}
