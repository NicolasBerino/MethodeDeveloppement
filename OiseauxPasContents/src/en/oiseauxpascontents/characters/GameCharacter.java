package en.oiseauxpascontents.characters;

import java.awt.Image;
import java.io.IOException;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe identifiant un personnage de l'application. 
 */
public abstract class GameCharacter implements Cloneable {

	private double posX, posY;
	private double velocityX, velocityY;
	private Image image;
	
	//TODO : ajouter type image
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public GameCharacter() {}
	
	public GameCharacter(double posX, double posY) {
		super();
		this.posX = posX;
		this.posY = posY;
		velocityX = 0;
		velocityY = 0;
	}
	
	public GameCharacter clone() throws CloneNotSupportedException{
        return (GameCharacter) super.clone();
    }

	public double getPositionX(){
		return this.posX;
	}
	
	public void setPositionX(double X) {
		this.posX = X;
	}
	
	public double getPositionY(){
		return this.posY;
	}
	
	public void setPositionY(double Y) {
		this.posY = Y;
	}
	
	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	public abstract void getCollision(GameCharacter gC);
}
