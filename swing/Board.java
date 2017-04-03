package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Board extends JPanel implements MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int paddleTolerance = 5;
	protected BallPanel ball = new BallPanel(this);
	protected PlayerPaddlePanel player = new PlayerPaddlePanel();
	protected ComputerPaddlePanel computer = new ComputerPaddlePanel(ball);
	private static int playerScore = 0;
	private static int computerScore = 0;
	private static Scores scores;

	public Board() {
		super(new BorderLayout());
		scores = new Scores();
		this.setSize(new Dimension(1000, 1000));
		this.addMouseMotionListener(this);
		this.add(player, BorderLayout.WEST);
		this.add(computer, BorderLayout.EAST);
		this.add(scores, BorderLayout.NORTH);
		this.add(ball, BorderLayout.CENTER);
	}

	public void go() {
		player.go();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		player.mouseMoved(arg0);

	}

	public PlayerPaddlePanel getPlayer() {
		return player;
	}

	public ComputerPaddlePanel getComputer() {
		return computer;
	}

	public static void computerScored() {
		System.out.println("computer");
		computerScore++;
		scores.setSores(playerScore, computerScore);
	}

	public static void playerScored() {
		playerScore++;
		scores.setSores(playerScore, computerScore);
	}

}
