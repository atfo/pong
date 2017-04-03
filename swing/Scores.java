package gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scores extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 378998325515923389L;
	
	
	private JLabel scores;
	
	public Scores() {
		scores = new JLabel("0 - 0");
		this.setSize(new Dimension(getWidth(), 10));
		this.add(scores);
	}
	
	void setSores(int player, int computer){
		scores.setText(player + " - " + computer);
	}
	
}
