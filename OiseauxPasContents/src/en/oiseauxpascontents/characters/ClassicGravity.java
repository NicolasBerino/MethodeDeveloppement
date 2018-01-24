package en.oiseauxpascontents.characters;

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
