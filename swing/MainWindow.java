package gui;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Board board = new Board();

	public MainWindow() {
		this.setTitle("Pong");
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().add(board);
		this.setVisible(true);
	}


	public static void main(String[] args) {
		new MainWindow();
	}
}
