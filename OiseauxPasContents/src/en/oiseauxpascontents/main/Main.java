package en.oiseauxpascontents.main;

import java.awt.Dimension;
import java.io.IOException;

import en.oiseauxpascontents.gui.MainFrame;
import en.oiseauxpascontents.gui.MainMenu;

/**
 * @author Nicolas Berino - Romain Semler
 * @version 1.0
 *
 * Classe de lancement de l'application. 
 */
public class Main {

    public static void main(String args[]) throws CloneNotSupportedException, IOException {
    	
    	String title = "Oiseaux pas contents | Menu Principal";
    	
    	MainFrame.getInstance().buildFrame(title, new MainMenu(), new Dimension(400, 200));
    }
}
