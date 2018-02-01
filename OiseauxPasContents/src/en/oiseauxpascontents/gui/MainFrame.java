package en.oiseauxpascontents.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe <<singleton>> de la fenêtre principale. 
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MainFrame instance =	null;
	
	private MainFrame() {}
	
	public static MainFrame getInstance(){
		
		if (instance == null) {
			
			instance = new MainFrame();
		}
		
		return instance;
	}
	
	/**
	 * Construit la fenêtre graphique.
	 * 
	 * @param title
	 * 		Le titre de la fenêtre à afficher.
	 * @param panel
	 * 		Le panel contenu dans la fenêtre.
	 * @param dimensions
	 * 		Les dimensions de la fenêtre.
	 */
	public void buildFrame(String title, JPanel panel, Dimension dimensions) {

		this.setTitle(title);
		if (dimensions != null)
			this.setSize(dimensions);
        else
        	this.pack();
		this.setResizable(false);
        this.setContentPane(panel);
		this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
}
