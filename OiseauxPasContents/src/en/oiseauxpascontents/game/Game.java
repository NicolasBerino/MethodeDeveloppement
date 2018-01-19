package en.oiseauxpascontents.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.Pig;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Panel de gestion du jeu.
 */
public class Game extends JPanel implements Runnable, MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	private int mouseX, mouseY;                 // Position de la souris lors de la sélection
	private int tryNumber, targetsNumber;		// Nombre d'essais et nombre de cibles
    private int score;                          // Nombre de fois où le joueur a gagné
    
    private boolean gameOver;                   // Vrai lorsque le joueur a touché un bord ou le cochon
    private boolean selecting;                  // Vrai lorsque le joueur sélectionne l'angle et la vitesse
    private boolean tryOver;					// Vrai lorsque un essai est terminé
    
    private String message;                     // Message à afficher en haut de l'écran
    
    private Image buffer;                       // Image pour le rendu hors écran
    
    private ArrayList<Bird> birds;				// Liste des instances d'oiseaux (clones)
    private ArrayList<Pig> pigs;				// Liste des instances de cochons (clones)
    
    private Level level;						// Niveau à paramétrer
    
    private Bird currentBird;					
    private Pig currentPig;
    
    private CollisionManager collisionManager = CollisionManager.getInstance();

    public Game() throws CloneNotSupportedException, IOException {
    	
        this.score = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
        initLevel();
        new Thread(this).start();
    }
 
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    
    public void mouseReleased(MouseEvent e) {
        
    	if(gameOver) {
            
    		try {
				initLevel();
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
        } else if(selecting) {
            
        	this.currentBird.setVelocityX((currentBird.getPositionX() - mouseX) / 20.0);
            this.currentBird.setVelocityY((currentBird.getPositionY() - mouseY) / 20.0);
            this.message = "L'oiseau prend sont envol";
            this.selecting = false;
            
        } else if(tryOver){
        	
        	initTry();
        }
    	
        repaint();
    }
    
    public void mouseDragged(MouseEvent e) { 
    	
    	mouseMoved(e); 
    }
    
    public void mouseMoved(MouseEvent e) { 
        
    	this.mouseX = e.getX();
        this.mouseY = e.getY();
        repaint();
    }
    
    // début de partie
    void initLevel() throws CloneNotSupportedException, IOException {
    	
    	this.tryNumber = 2;
    	this.targetsNumber = 2;
    	
    	this.level = new Level(0.1, this.tryNumber, this.targetsNumber, "");
    	
    	this.birds = this.level.getBirds();
    	this.pigs = this.level.getPigs();
    	
    	this.currentBird = this.birds.get(0);
    	this.currentPig = this.pigs.get(0);
    	this.currentBird.setImage(ImageIO.read(new File("images/bird.png")));
    	this.currentPig.setImage(ImageIO.read(new File("images/pig.png")));
    	
    	this.collisionManager.clearObservableCharacter();
    	this.collisionManager.addObservableCharacter((Bird) this.currentBird);
    	this.collisionManager.addObservableCharacter((Pig) this.currentPig);
    	
    	initTry();
    }
    
    void initTry() {
    	
    	this.tryOver = false;
    	this.gameOver = false;
    	this.selecting = true;
    	
    	this.currentBird.setPositionX(100);
    	this.currentBird.setPositionY(400);
    	this.currentBird.setVelocityX(0);
    	this.currentBird.setVelocityY(0);
    	this.currentPig.setPositionX(Math.random() * 500 + 200);// position aléatoire pour le cochon
    	this.currentPig.setPositionY(480);
    	
    	this.message = "Choisissez l'angle et la vitesse.";
    }
    
    // fin de partie
    void stop() {
    	
    	this.tryOver = true;
    	this.currentBird.setVelocityX(0);
    	this.currentBird.setVelocityY(0);
    	
    	if(this.tryNumber == 0 || this.targetsNumber == 0) 
    		this.gameOver = true;
    }
    
    // boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
	public void run() {
		
		int resultat;
		
        while(true) {
        	
            // un pas de simulation toutes les 10ms
            try { 
            	Thread.currentThread();
            	Thread.sleep(10); 
            } catch(InterruptedException e) { }

            if(!this.tryOver && !this.selecting) {

                // moteur physique
            	this.currentBird.setPositionX(this.currentBird.getPositionX()+this.currentBird.getVelocityX());
            	this.currentBird.setPositionY(this.currentBird.getPositionY()+this.currentBird.getVelocityY());
            	this.currentBird.setVelocityY(this.currentBird.getVelocityY()+this.level.getGravity());
            	
            	resultat = this.collisionManager.checkCollision();
            	
                // conditions de victoire
                if(resultat == 0) {
                	
                    stop();
                    this.score++;
                    this.tryNumber--;
                    
                    if(this.targetsNumber == 0){
                    	
                    	this.message = "Gagné : cliquez pour recommencer.";
                    	
                    } else{
                    	
                    	this.message = "Plus que " + this.targetsNumber +" cibles.";
                    }
                    
                } else if(resultat == 1) {
                	
                	this.tryNumber--;
                	
                    if(this.tryNumber == 0 && this.targetsNumber > 0) {
                    	
	                	stop();
	                    this.message = "Perdu : cliquez pour recommencer.";
	                    
                    } else {
                    	
	                	stop();
	                    this.message = "Plus que " + this.targetsNumber +" essais.";
	                }
                }

                repaint();
            }
            
        }
    }

    // évite les scintillements
    public void update(Graphics g) {
    	
        paint(g);
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
        if(selecting) g.drawLine((int) currentBird.getPositionX(), (int) currentBird.getPositionY()+20, mouseX, mouseY); // montre l'angle et la vitesse

        g.drawImage(currentBird.getImage(), (int) currentBird.getPositionX()-20, (int) currentBird.getPositionY()-20, this);

        // cochon
        g.drawImage(currentPig.getImage(), (int) currentPig.getPositionX() - 20, (int) currentPig.getPositionY() - 20, this);
        // g.fillOval(, (int) currentPig.getPositionY() - 20, 40, 40);

        // messages
        g.setColor(Color.BLACK);
        g.drawString(message, 300, 100);
        g.drawString("score: " + score, 20, 20);

        // affichage à l'écran sans scintillement
        g2.drawImage(buffer, 0, 0, null);
    }

    // met le jeu dans une fenêtre
    /*public static void main(String args[]) throws CloneNotSupportedException, IOException {
        Frame frame = new Frame("Oiseau pas content");
        final Game obj = new Game();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        frame.add(obj);
        frame.pack();
        frame.setVisible(true);
    }*/
}
