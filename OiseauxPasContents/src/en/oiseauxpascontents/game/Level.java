package en.oiseauxpascontents.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Level {

	int numberOfBird;
	double gravity;                             // gravité
	
    // début de partie
    void init() {
        gameOver = false;
        selecting = true;
        birdX = 100;
        birdY = 400;
        velocityX = 0;
        velocityY = 0;
        pigX = Math.random() * 500 + 200; // position aléatoire pour le cochon
        pigY = 480;
        message = "Choisissez l'angle et la vitesse.";
    }
	
 // dessine le contenu de l'écran dans un buffer puis copie le buffer à l'écran
    public void paint(Graphics g2) {
        if(buffer == null) buffer = createImage(800, 600);
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        // fond
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // décor
        g.setColor(Color.BLACK);
        g.drawLine(0, 500, 800, 500);
        g.drawLine(100, 500, 100, 400);

        // oiseau
        g.setColor(Color.RED);
        if(selecting) g.drawLine((int) birdX, (int) birdY, mouseX, mouseY); // montre l'angle et la vitesse
        g.fillOval((int) birdX - 20, (int) birdY - 20, 40, 40);

        // cochon
        g.setColor(Color.GREEN);
        g.fillOval((int) pigX - 20, (int) pigY - 20, 40, 40);

        // messages
        g.setColor(Color.BLACK);
        g.drawString(message, 300, 100);
        g.drawString("score: " + score, 20, 20);

        // affichage à l'écran sans scintillement
        g2.drawImage(buffer, 0, 0, null);
    }
    
}
