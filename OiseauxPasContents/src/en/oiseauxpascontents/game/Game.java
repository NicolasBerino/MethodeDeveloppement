package en.oiseauxpascontents.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.util.*;

import en.oiseauxpascontents.characters.Bird;
import en.oiseauxpascontents.characters.GameCharacter;
import en.oiseauxpascontents.characters.Pig;

import java.awt.event.*;

public class Game extends Panel implements Runnable, MouseListener, MouseMotionListener{

    int mouseX, mouseY;                         // position de la souris lors de la sélection
    private String message;                             // message à afficher en haut de l'écran
    private int score;                                  // nombre de fois où le joueur a gagné
    private boolean gameOver;                           // vrai lorsque le joueur a touché un bord ou le cochon
    private boolean selecting;                          // vrai lorsque le joueur sélectionne l'angle et la vitesse
    Image buffer;                               // image pour le rendu hors écran
    
    boolean tryOver;
    
    Level l;
    ArrayList<GameCharacter> Birds;
    ArrayList<GameCharacter> Pigs;
    GameCharacter CurrentBird;
    GameCharacter CurrentPig;
    
    int nombreEssai;
    int nombreCible;
    
    // constructeur
    Game() throws CloneNotSupportedException {
        score = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
        initLevel();
        new Thread(this).start();
    }
    
    // calcule la distance entre deux points
    static double distance(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
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
    void initLevel() throws CloneNotSupportedException {
    	nombreEssai = 2;
    	nombreCible = 2;
    	l = new Level(0.1,nombreEssai,nombreCible,"");
    	Birds = l.getBirds();
    	Pigs = l.getPigs();
    	CurrentBird = Birds.get(0);
    	CurrentPig = Pigs.get(0);
    	
    	initTry();
    }
    
    void initTry(){
    	nombreEssai --;
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
        while(true) {
            // un pas de simulation toutes les 10ms
            try { Thread.currentThread();
			Thread.sleep(10); } catch(InterruptedException e) { }

            if(!tryOver && !selecting) {

                // moteur physiquedistance
            	CurrentBird.setPositionX(CurrentBird.getPositionX()+CurrentBird.getVelocityX());
            	CurrentBird.setPositionY(CurrentBird.getPositionY()+CurrentBird.getVelocityY());
            	CurrentBird.setVelocityY(CurrentBird.getVelocityY()+l.getGravity());
            	
                // conditions de victoire
                if(distance(CurrentBird.getPositionX(), CurrentBird.getPositionY(), CurrentPig.getPositionX(), CurrentPig.getPositionY()) < 35) {
                    stop();
                    score++;
                    nombreCible --;
                    System.out.println(nombreCible);
                    if(nombreCible ==0){
                    	message = "Gagné : cliquez pour recommencer.";
                    }
                } else if(CurrentBird.getPositionX() < 20 || CurrentBird.getPositionX() > 780 ||CurrentBird.getPositionY() < 0 || CurrentBird.getPositionY() > 480) {
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
        if(selecting) g.drawLine((int) CurrentBird.getPositionX(), (int) CurrentBird.getPositionY(), mouseX, mouseY); // montre l'angle et la vitesse
        g.fillOval((int) CurrentBird.getPositionX() - 20, (int) CurrentBird.getPositionY() - 20, 40, 40);

        // cochon
        g.setColor(Color.GREEN);
        g.fillOval((int) CurrentPig.getPositionX() - 20, (int) CurrentPig.getPositionY() - 20, 40, 40);

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
    public static void main(String args[]) throws CloneNotSupportedException {
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
