package en.oiseauxpascontents.characters;


public class BlackHole extends Gravity{

	@Override
	public void actOn(GameCharacter token) {
		double distance = distance(token.getPositionX(), token.getPositionY(),this.getPositionX(), this.getPositionY());
		if(distance < 150){
			token.setVelocityX(token.getVelocityX()+(this.getPositionX()/200));
			token.setVelocityY(token.getVelocityY()+(this.getPositionY()/200));
		}
		
	}

	@Override
	public void getCollision(GameCharacter gameCharacter) {
		System.out.println("test");
		gameCharacter.setVelocityX(0);
		gameCharacter.setVelocityY(0);
		gameCharacter.setImage(null);
	}
	
    public static double distance(double x1, double y1, double x2, double y2) {
    	
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

}
