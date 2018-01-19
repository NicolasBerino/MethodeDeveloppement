package en.oiseauxpascontents.characters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe identifiant un personnage "cochon" (PIG). 
 */
public class Pig extends GameCharacter {
	
	boolean beaten = false;
	
	public Pig() {
	}
	
	public Pig(double posX, double posY) throws IOException{
		
		super(posX, posY);
		Image im = ImageIO.read(new File("images/pig.png"));
		this.setImage(im);
		this.setPositionX(Math.random()* 500 + 200);
		this.setPositionY(480);
	}

	@Override
	public void getCollision(GameCharacter gC) {
		beaten = true;
		if(gC instanceof Bird){
			try {
				this.setImage(ImageIO.read(new File("images/pigBeaten.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Collision entre cochon et oiseau.");
		}
	}
	
	public boolean isBeaten() {
		return beaten;
	}

	public void setBeaten(boolean beaten) {
		this.beaten = beaten;
	}

	public void firstState() throws IOException{
		if (!beaten){
			this.setImage(ImageIO.read(new File("images/pig.png")));
		}
	}
	
	public void birdThrowed() throws IOException{
		if(!beaten){
			this.setImage(ImageIO.read(new File("images/pig2.png")));
		}
	}
	
}
