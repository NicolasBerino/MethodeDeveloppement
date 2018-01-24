package en.oiseauxpascontents.characters;

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
