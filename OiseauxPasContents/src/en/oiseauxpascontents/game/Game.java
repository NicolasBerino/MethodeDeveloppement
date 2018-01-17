package en.oiseauxpascontents.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.util.*;

import javax.imageio.ImageIO;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.GameCharacter;
import en.oiseauxpascontents.characters.Pig;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Game extends Panel implements Runnable, MouseListener, MouseMotionListener{

    int mouseX, mouseY;                         // position de la souris lors de la sélection
    private String message;                             // message à afficher en haut de l'écran
    private int score;                                  // nombre de fois où le joueur a gagné
    private boolean gameOver;                           // vrai lorsque le joueur a touché un bord ou le cochon
    private boolean selecting;                          // vrai lorsque le joueur sélectionne l'angle et la vitesse
    Image buffer;                               // image pour le rendu hors écran
    
    CollisionManager cM = CollisionManager.getInstance();
    boolean tryOver;
    
    Level l;
    ArrayList<Bird> Birds;
    ArrayList<Pig> Pigs;
    Bird CurrentBird;
    Pig CurrentPig;
    
    int nombreEssai;
    int nombreCible;
    
    // constructeur
    Game() throws CloneNotSupportedException, IOException {
        score = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
        initLevel();
        new Thread(this).start();
    }
    

    
 // gestion des événements souris
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
            CurrentBird.setVelocityX((CurrentBird.getPositionX() - mouseX) / 20.0);
            CurrentBird.setVelocityY((CurrentBird.getPositionY() - mouseY) / 20.0);
            message = "L'oiseau prend sont envol";
            selecting = false;
        }
        else if(tryOver){
        	initTry();
        }
        repaint();
    }
    public void mouseDragged(MouseEvent e) { mouseMoved(e); }
    public void mouseMoved(MouseEvent e) { 
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }
    
    // début de partie
    void initLevel() throws CloneNotSupportedException, IOException {
    	nombreEssai = 2;
    	nombreCible = 2;
    	l = new Level(0.1,nombreEssai,nombreCible,"");
    	Birds = l.getBirds();
    	Pigs = l.getPigs();
    	CurrentBird = Birds.get(0);
    	CurrentPig = Pigs.get(0);
    	CurrentBird.setImage(ImageIO.read(new File("images/bird.png")));
    	CurrentPig.setImage(ImageIO.read(new File("images/pig.png")));
    	
    	cM.ClearObservableChar();
    	cM.AddObservableChar((Bird)CurrentBird);
    	cM.AddObservableChar((Pig)CurrentPig);
    	
    	initTry();
    }
    
    void initTry(){
    	tryOver = false;
        gameOver = false;
        selecting = true;
        CurrentBird.setPositionX(100);
        CurrentBird.setPositionY(400);
        CurrentBird.setVelocityX(0);
        CurrentBird.setVelocityY(0);
        CurrentPig.setPositionX(Math.random() * 500 + 200);// position aléatoire pour le cochon
        CurrentPig.setPositionY(480);
        message = "Choisissez l'angle et la vitesse.";
    }
    
    // fin de partie
    void stop() {
    	tryOver = true;
    	CurrentBird.setVelocityX(0);
    	CurrentBird.setVelocityY(0);
    	if(nombreEssai==0 || nombreCible ==0) gameOver = true;
    }
    // boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
	public void run() {
		int resultat;
        while(true) {
            // un pas de simulation toutes les 10ms
            try { Thread.currentThread();
			Thread.sleep(10); } catch(InterruptedException e) { }

            if(!tryOver && !selecting) {

                // moteur physiquedistance
            	CurrentBird.setPositionX(CurrentBird.getPositionX()+CurrentBird.getVelocityX());
            	CurrentBird.setPositionY(CurrentBird.getPositionY()+CurrentBird.getVelocityY());
            	CurrentBird.setVelocityY(CurrentBird.getVelocityY()+l.getGravity());
            	
            	resultat =  cM.checkCollision();
                // conditions de victoire
                if(resultat == 0) {
                    stop();
                    score++;
                    nombreCible --;
                    System.out.println(nombreCible);
                    if(nombreCible ==0){
                    	message = "Gagné : cliquez pour recommencer.";
                    }
                    else{
                    	message = "Plus que " + nombreCible +" cibles.";
                    }
                } else if(resultat ==1) {
                	nombreEssai--;
                    if(nombreEssai == 0 && nombreCible >0){
	                	stop();
	                    message = "Perdu : cliquez pour recommencer.";
	                    
                    }
	                else{
	                	stop();
	                    message = "Plus que " + nombreEssai +" essais.";
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
        if(selecting) g.drawLine((int) CurrentBird.getPositionX(), (int) CurrentBird.getPositionY()+20, mouseX, mouseY); // montre l'angle et la vitesse

        g.drawImage(CurrentBird.getImage(), (int) CurrentBird.getPositionX()-20, (int) CurrentBird.getPositionY()-20, this);

        // cochon
        g.drawImage(CurrentPig.getImage(), (int) CurrentPig.getPositionX() - 20, (int) CurrentPig.getPositionY() - 20, this);
       // g.fillOval(, (int) CurrentPig.getPositionY() - 20, 40, 40);

        // messages
        g.setColor(Color.BLACK);
        g.drawString(message, 300, 100);
        g.drawString("score: " + score, 20, 20);

        // affichage à l'écran sans scintillement
        g2.drawImage(buffer, 0, 0, null);
    }

    // taille de la fenêtre
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    // met le jeu dans une fenêtre
    public static void main(String args[]) throws CloneNotSupportedException, IOException {
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
    }
}
