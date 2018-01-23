package en.oiseauxpascontents.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import en.oiseauxpascontents.game.Game;

public class LevelFrame extends JPanel implements IPanel, ActionListener {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabPane = new JTabbedPane();
	
	private JComponent panel1;
	
	private JPanel constantsParameters;
	
	private JLabel selectTriesNumber;
	private JLabel selectPigsNumber;
	private JLabel selectHolesNumber;
	private JLabel selectGravityValue;
	private JLabel sliderTryValue;
	private JLabel sliderPigValue;
	private JLabel sliderHoleValue;
	private JLabel sliderGravityValue;
	
	private JSlider trySlider;
	private JSlider pigSlider;
	private JSlider holeSlider;
	private JSlider gravitySlider;
	
	private JButton submit;
	
	public LevelFrame() {
		
		build();
	}
	
	public void build() {
		
		this.submit = new JButton("Commencer la partie");
		this.submit.addActionListener(this);
		
		this.panel1 = buildConstantsParameters();
		
		this.tabPane.addTab("Paramètres", this.panel1);
		
		this.setLayout(new BorderLayout());
		this.add(this.tabPane, BorderLayout.CENTER);
		this.add(this.submit, BorderLayout.SOUTH);
		this.setBackground(Color.CYAN);
	}

	public JPanel buildConstantsParameters() {
		
		this.constantsParameters = new JPanel();
		this.constantsParameters.setLayout(new GridLayout(8, 1));
		this.constantsParameters.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JPanel sliderTryZone = new JPanel();
		JPanel sliderPigZone = new JPanel();
		JPanel sliderHoleZone = new JPanel();
		JPanel sliderGravityZone = new JPanel();
		
		this.trySlider = new JSlider(1, 10, 1);
		this.pigSlider = new JSlider(1, 5, 1);
		this.holeSlider = new JSlider(0, 3, 0);
		this.gravitySlider = new JSlider(1, 10, 1);
		
		this.sliderTryValue = new JLabel("" + trySlider.getValue() + " / " + trySlider.getMaximum());
		this.sliderPigValue = new JLabel("" + pigSlider.getValue() + " / " + pigSlider.getMaximum());
		this.sliderHoleValue = new JLabel("" + holeSlider.getValue() + " / " + holeSlider.getMaximum());
		this.sliderGravityValue = new JLabel("" + (double) gravitySlider.getValue() / 10 + " / 1");
		
		this.sliderTryValue.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		this.sliderPigValue.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		this.sliderHoleValue.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		this.sliderGravityValue.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		this.selectTriesNumber = new JLabel("Sélectionnez le nombre d'essais :");
		this.selectPigsNumber = new JLabel("Sélectionnez le nombre de cochons :");
		this.selectHolesNumber = new JLabel("Sélectionnez le nombre de trous noirs :");
		this.selectGravityValue = new JLabel("Sélectionnez la valeur de la gravité :");
		
		this.selectTriesNumber.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		this.selectPigsNumber.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		this.selectHolesNumber.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		this.selectGravityValue.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		
		sliderTryZone.setLayout(new GridLayout(1, 2));
		sliderPigZone.setLayout(new GridLayout(1, 2));
		sliderHoleZone.setLayout(new GridLayout(1, 2));
		sliderGravityZone.setLayout(new GridLayout(1, 2));
		
		this.trySlider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				
				JSlider source = (JSlider) arg0.getSource();
				sliderTryValue.setText("" + source.getValue() + " / " + source.getMaximum());
			}
		});
		
		this.pigSlider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				
				JSlider source = (JSlider) arg0.getSource();
				sliderPigValue.setText("" + source.getValue() + " / " + source.getMaximum());
			}
		});
		
		this.holeSlider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				
				JSlider source = (JSlider) arg0.getSource();
				sliderHoleValue.setText("" + source.getValue() + " / " + source.getMaximum());
			}
		});
		
		this.gravitySlider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				
				JSlider source = (JSlider) arg0.getSource();
				sliderGravityValue.setText("" + (double) source.getValue() / 10 + " / 1");
			}
		});
		
		sliderTryZone.add(this.trySlider);
		sliderTryZone.add(this.sliderTryValue);
		sliderPigZone.add(this.pigSlider);
		sliderPigZone.add(this.sliderPigValue);
		sliderHoleZone.add(this.holeSlider);
		sliderHoleZone.add(this.sliderHoleValue);
		sliderGravityZone.add(this.gravitySlider);
		sliderGravityZone.add(this.sliderGravityValue);
		
		this.constantsParameters.add(selectTriesNumber);
		this.constantsParameters.add(sliderTryZone);
		this.constantsParameters.add(selectPigsNumber);
		this.constantsParameters.add(sliderPigZone);
		this.constantsParameters.add(selectHolesNumber);
		this.constantsParameters.add(sliderHoleZone);
		this.constantsParameters.add(selectGravityValue);
		this.constantsParameters.add(sliderGravityZone);
		
		return this.constantsParameters;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == this.submit) {
			
			try {
				
				MainFrame mf = MainFrame.getInstance();
				
				mf.buildFrame("Détruisez les cochons !", new Game(this.trySlider.getValue(), this.pigSlider.getValue(), this.holeSlider.getValue(), (double) this.gravitySlider.getValue() / 10), new Dimension(800, 600));
			
			} catch (CloneNotSupportedException | IOException e) {
				
				e.printStackTrace();
			}
		}
	}
}
