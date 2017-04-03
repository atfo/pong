package gui;

import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class PaddlePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static final int X_OFFSET = 20;
	protected static final int PADDLE_WIDTH = 20;
	protected static final int PADDLE_HEIGHT = 80;
	protected int paddleY = 0;
	
	

	PaddlePanel(){
		System.out.println("tz");
		this.setPreferredSize(new Dimension(X_OFFSET+PADDLE_WIDTH, getHeight()));
	}

	public void go() {
		
	}

	public int getPaddleY() {
		return paddleY;
	}
	
}
