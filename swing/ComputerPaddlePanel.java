package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import gui.threads.ComputerThread;

public class ComputerPaddlePanel extends PaddlePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Random rng = new Random();
	private static final int tolerance = 10;
	private BallPanel ball;
	static int vy = 30;

	private ComputerThread computer;

	public ComputerPaddlePanel(BallPanel ball) {
		super();
		this.setBackground(Color.RED);
		this.ball = ball;
		computer = new ComputerThread(this);
		computer.start();
	}

	public void go() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			this.augmentPaddleY();
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(0, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
	}

	public void augmentPaddleY() {
		if (rng.nextBoolean() && rng.nextBoolean() && rng.nextBoolean()) {
//			System.out.println(vy);
			if (!(paddleY < getHeight() - PADDLE_HEIGHT - tolerance)) {
				vy *= -1;
//				System.out.println("down");
			}
			if (paddleY < 0 - tolerance) {
				vy *= -1;
//				System.out.println("up");
			}
			this.paddleY += vy;
		} else {
			this.paddleY = this.ball.getBallY();
		}
		
	}

}
