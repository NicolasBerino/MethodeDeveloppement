package en.oiseauxpascontents.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import en.oiseauxpascontents.game.Game;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Panel de gestion du menu principal.
 */
public class MainMenu extends JPanel implements IPanel, ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel panelLabel;
	private JLabel panelCopyright;
	
	private JButton playButton;
	private JButton quitButton;
	
	private Font title1 = new Font("Trebuchet MS", Font.BOLD, 20);
	private Font title2 = new Font("Arial", Font.BOLD, 15);
	private Font buttonFont = new Font("Futura", Font.BOLD, 15);
	
	private Image background = Toolkit.getDefaultToolkit().getImage("images/background-menu.png");
	
	public MainMenu() {
		
		build();
	}

	public void build() {

		this.setLayout(new GridLayout(4, 1));
		
		this.panelLabel = new JLabel("Oiseaux Pas Contents", SwingConstants.CENTER);
		this.panelCopyright = new JLabel("BERINO Nicolas - SEMLER Romain", SwingConstants.CENTER);
		
		this.panelLabel.setFont(title1);
		this.panelCopyright.setFont(title2);
		
		this.playButton = new JButton("JOUER");
		this.quitButton = new JButton("QUITTER");
		
		this.playButton.setFont(buttonFont);
		this.quitButton.setFont(buttonFont);
		
		/*this.playButton.setForeground(Color.BLUE);
		this.quitButton.setForeground(Color.BLUE);*/
		
		this.playButton.setOpaque(false);
		this.quitButton.setOpaque(false);
		
		this.playButton.setContentAreaFilled(false);
		this.quitButton.setContentAreaFilled(false);
		
		this.playButton.setBorderPainted(false);
		this.quitButton.setBorderPainted(false);
		
		this.playButton.addActionListener(this);
		this.quitButton.addActionListener(this);
		
		this.add(panelLabel);
		this.add(panelCopyright);
		this.add(playButton);
		this.add(quitButton);
	}
	
	public void actionPerformed(ActionEvent arg0) {
    	
    	if(arg0.getSource() == playButton)
			try {
				MainFrame.getInstance().buildFrame("Détruisez les cochons !", new Game(), new Dimension(800, 600));
			} catch (CloneNotSupportedException | IOException e) {
				e.printStackTrace();
			}
    	if(arg0.getSource() == quitButton)
    		System.exit(0);
    }
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
	}
}
