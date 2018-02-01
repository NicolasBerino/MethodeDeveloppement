package en.oiseauxpascontents.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.JPanel;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.BlackHole;
import en.oiseauxpascontents.characters.Gravity;
import en.oiseauxpascontents.characters.Pig;
import en.oiseauxpascontents.main.Main;

import java.awt.event.*;
import java.io.IOException;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.3
 *
 * Panel de gestion du jeu.
 */
public class Game extends JPanel implements Runnable, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	private int mouseX, mouseY;                 // Position de la souris lors de la sélection
	private int tryNumber, targetsNumber;		// Nombre d'essais et nombre de cibles
	private int initialTryNumber;				// Nombre initial d'essais.
	private int initialTargetNumber;			// Nombre initial de cibles.
	private int blackHolesNumber;				// Nombre de trous noirs
	private double gravity;						// Gravité
	private int score;                          // Nombre de fois où le joueur a gagné
    
    private boolean gameOver;                   // Vrai lorsque le joueur a touché un bord ou le cochon
    private boolean selecting;                  // Vrai lorsque le joueur sélectionne l'angle et la vitesse
    private boolean tryOver;					// Vrai lorsque un essai est terminé
    
    private String message;                     // Message à afficher en haut de l'écran
    
    private Image buffer;                       // Image pour le rendu hors écran
    
    private ArrayList<Bird> birds;				// Liste des instances d'oiseaux (clones)
    private ArrayList<Pig> pigs;				// Liste des instances de cochons (clones)
    private ArrayList<Gravity> gravities;			// Liste des différentes gravités
    
    private Level level;						// Niveau à paramétrer
    
    private Bird currentBird;					
   
    private Image background = Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.BACKGROUND_IMAGE));
    
    private CollisionManager collisionManager = CollisionManager.getInstance();

    public Game(int nbBirds, int nbPigs, int nbBlackHoles, double gravity) throws CloneNotSupportedException, IOException {
    	
        this.score = 0;
        this.initialTryNumber = nbBirds;
    	this.initialTargetNumber = nbPigs;
    	this.blackHolesNumber = nbBlackHoles;
    	this.gravity = gravity;
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
				e1.printStackTrace();
			}
    		
        } else if(selecting) {
            
        	this.currentBird.setVelocityX((currentBird.getPositionX() - mouseX) / 20.0);
            this.currentBird.setVelocityY((currentBird.getPositionY() - mouseY) / 20.0);
            this.message = "A l'attaque !";
            this.selecting = false;
            
        } else if(tryOver){
        	
        	try {
        		
				initTry();
				
			} catch (IOException e1) {

				e1.printStackTrace();
			}
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
    
    /**
     * Initialisation du niveau du jeu.
     * 
     * @throws CloneNotSupportedException
     * @throws IOException
     */
    void initLevel() throws CloneNotSupportedException, IOException {
    	
    	this.tryNumber = this.initialTryNumber;
    	this.targetsNumber = this.initialTargetNumber;
    	this.score = 0;
    	
    	this.level = new Level(this.tryNumber, this.targetsNumber, this.blackHolesNumber, this.gravity);
    	
    	this.birds = this.level.getBirds();
    	this.pigs = this.level.getPigs();
    	this.gravities = this.level.getListGravity();
    	
    	Iterator<Pig> ITpig = pigs.iterator();
    	
    	double newPosition;
    	while(ITpig.hasNext()) {
    		
    		ITpig.next().setImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.PIG_IMAGE)));
    	}
    	
    	ITpig = pigs.iterator();
    	newPosition = Math.random() * 500 + 200;
    	
    	while(ITpig.hasNext()) {
    		
    		Pig p = ITpig.next();
    		
    		while(!freePositionPig(newPosition)) {
    			
    			newPosition = Math.random() * 500 + 200;
    		}
    		
    		p.setPositionX(newPosition);// position aléatoire pour le cochon
    		p.setPositionY(GameConstants.COORD_Y_LEVEL);
    	
    	}
    	if(gravities!=null) {
	    	Iterator<Gravity> ITgrav = gravities.iterator();
	    	ITgrav.next();
	    	while(ITgrav.hasNext()){
	    		BlackHole blHo = (BlackHole) ITgrav.next();
	    		blHo.setImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.BLACK_HOLE_IMAGE)));
	    		while(!freePositionBlackHole(newPosition)) {
	    			
	    			newPosition = Math.random() * 500 + 200;
	    		}
	    		
	    		blHo.setPositionX(newPosition);// position aléatoire pour le trou noir
	    		blHo.setPositionY(Math.random()* +200);
	    	}
    	}
    	
    	
    	initTry();
    }
    
    /**
     * Vérifie si une position d'un cochon est libre.
     * 
     * @param position
     * 		La position courante.
     * 
     * @return true si la position est libre.
     */
    boolean freePositionPig(double position) {
    	
    	Iterator<Pig> ITpig2 = pigs.iterator();
    	
    	while(ITpig2.hasNext()) {
    		
    		if(Math.abs(position - ITpig2.next().getPositionX()) < 40) 
    			return false;
    	}
    	
    	return true;
    }
    
    /**
     * Vérifie si une position d'un trou noir est libre.
     * 
     * @param position
     * 		La position courante.
     * 
     * @return true si la position est libre.
     */
    boolean freePositionBlackHole(double position) {
    	
    	Iterator<Gravity> ITBH = gravities.iterator();
    	ITBH.next();
    	while(ITBH.hasNext()) {
    		
    		if(Math.abs(position - ITBH.next().getPositionX()) < 40) 
    			return false;
    	}
    	
    	return true;
    }
    
    /**
     * Initialisation d'un essai (d'un lancé).
     * 
     * @throws IOException
     */
    void initTry() throws IOException {
    	
    	this.tryNumber--;
    	this.currentBird = this.birds.get(this.initialTryNumber - this.tryNumber - 1);
    	
    	this.currentBird.setImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.BIRD_IMAGE)));

    	Iterator<Pig> ITpig = pigs.iterator();
    	
    	while(ITpig.hasNext()) {
    		
    		Pig p = ITpig.next();
    		p.firstState();
    		if(p.isBeaten()) ITpig.remove();
    	}
    	
    	this.collisionManager.clearObservableCharacter();
    	

    	ITpig = pigs.iterator();
    	
    	while(ITpig.hasNext()) {
    		
    		this.collisionManager.addObservableCharacter((Pig) ITpig.next()); 
    	}
    	
    	Iterator<Gravity> itBH = gravities.iterator();
    	itBH.next();
    	
    	while(itBH.hasNext()) {
    		
    		this.collisionManager.addObservableCharacter((BlackHole) itBH.next()); 
    	}
    	
    	this.collisionManager.addObservableCharacter((Bird) this.currentBird);
    	
    	this.tryOver = false;
    	this.gameOver = false;
    	this.selecting = true;
    	
    	this.currentBird.setPositionX(95);
    	this.currentBird.setPositionY(430);
    	this.currentBird.setVelocityX(0);
    	this.currentBird.setVelocityY(0);
    	
    	this.message = "Choisissez l'angle et la vitesse.";
    }
    
    /**
     * Finalise un essai ou la partie.
     */
    void stop() {
    	
    	this.tryOver = true;
    	this.currentBird.setVelocityX(0);
    	this.currentBird.setVelocityY(0);
    	
    	if(this.tryNumber == 0 || this.targetsNumber == 0) 
    		this.gameOver = true;
    }
    
    /**
     * Calcule la position de l'oiseau en vol, 
     * effectue l'affichage et teste les conditions de victoire.
     */
	public void run() {
		
		int resultat;
		
        while(true) {
        	
            // un pas de simulation toutes les 10ms
            try { 
            	Thread.currentThread();
            	Thread.sleep(10); 
            } catch(InterruptedException e) { }

            if(!this.tryOver && !this.selecting) {
            	
            	Iterator<Pig> ITpig = pigs.iterator();
            	
            	while(ITpig.hasNext()) {
            		
            		try {
            			
    					ITpig.next().secondState();
    					
    				} catch (IOException e) {

    					e.printStackTrace();
    				} 
            	}
            	
                // moteur physique
            	this.currentBird.setPositionX(this.currentBird.getPositionX() + this.currentBird.getVelocityX());
            	this.currentBird.setPositionY(this.currentBird.getPositionY() + this.currentBird.getVelocityY());
            	this.level.applyGravity(this.currentBird);
            	
            	this.currentBird.setImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource(GameConstants.BIRD_FLYING_IMAGE)));
            	
            	resultat = this.collisionManager.checkCollision();
				
                // conditions de victoire
                if(resultat == 0) {
                	
                    stop();
                    this.score++;
                    
                    targetsNumber--;
                    
                    if(this.targetsNumber == 0) {
                    	
                    	this.message = "Gagné ! Cliquez pour recommencer.";
                    	gameOver = true;
                    	
                    } else {
                    	
                    	this.message = "Touché ! Plus que " + this.targetsNumber +" cible(s).";
                    	tryOver = true;
                    }
                    
                } else if(resultat == 1) {
                	
                    if(this.tryNumber == 0 && this.targetsNumber > 0) {
                    	
	                	stop();
	                    this.message = "Perdu... Cliquez pour recommencer.";
	                    gameOver = true;
	                    
                    } else {
                    	
	                	stop();
	                    this.message = "Manqué !";
	                    tryOver = true;
	                }
                }

                repaint();
            }
            
        }
    }

    /**
     * Met à jour le dessin pour éviter les scintillements.
     */
    public void update(Graphics g) {

        paint(g);
    }
    
    /**
     * Dessine le contenu de l'écran dans un buffer 
     * puis copie le buffer à l'écran.
     */
    public void paint(Graphics g2) {
    	
        if(buffer == null) buffer = createImage(800, 600);
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        // FOND
        g.drawImage(background, 0, 0, null);

        if(selecting) 
        	g.drawLine((int) currentBird.getPositionX(), (int) currentBird.getPositionY() + 20, mouseX, mouseY); // montre l'angle et la vitesse

        g.drawImage(currentBird.getImage(), (int) currentBird.getPositionX() - 20, (int) currentBird.getPositionY() - 20, this);

        // COCHON
    	Iterator<Pig> ITpig = pigs.iterator();
    	
    	while(ITpig.hasNext()) {
    		
    		Pig p = ITpig.next();
    		g.drawImage(p.getImage(), (int) p.getPositionX() , (int) p.getPositionY() - 20, this);
		}
        
    	
    	 // TROU NOIR
    	Iterator<Gravity> ITBH = gravities.iterator();
    	ITBH.next();
    	
    	while(ITBH.hasNext()) {
    		
    		BlackHole bH = (BlackHole) ITBH.next();
    		g.drawImage(bH.getImage(), (int) bH.getPositionX()-100, (int) bH.getPositionY()-100, this);
		}

        // MESSAGES
    	g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        drawCenteredString(g, message, 800, 30);
        g.drawString("Score : " + score, 20, 20);
        g.drawString("Essai(s) : " + (this.tryNumber + 1), 680, 20);
        
        // AFFICHAGE ECRAN SANS SCINTILLEMENTS
        g2.drawImage(buffer, 0, 0, null);
    }
    
    /**
     * Crée un texte centré dans une zone.
     * 
     * @param g
     * 		Le contenu graphique du jeu.
     * @param text
     * 		Le texte à afficher.
     * @param width
     * 		La largeur de la zone.
     * @param height
     * 		La hauteur de la zone.
     */
    public void drawCenteredString(Graphics g, String text, int width, int height) {
    	
        FontMetrics fm = g.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = (fm.getAscent() + (height - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(text, x, y);
    }
}
