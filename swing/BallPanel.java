package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BallPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int DIAMETER = 15;
	private int ballX = 15;
	private int ballY = 15;
	private int vx = 3;
	private int vy = 3;

	private Board board;

	public BallPanel(Board board) {
		this.board = board;
		Thread t = new Thread(new MoveBall());
		t.start();
	}
	
	public int getBallY(){
		return ballY;
	}

	public void go() {
		if(System.currentTimeMillis() % 2 == 1)
			vx*=-1;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			this.moveBall();
			this.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void moveBall() {
		
		ballX += vx;
		ballY += vy;
		checkGoingOut();

	}

	private void checkGoingOut() {
		if (ballX <= 0-Board.paddleTolerance) {
			System.out.println("Out");
			if (board.getPlayer().getPaddleY() <= ballY+Board.paddleTolerance
					&& board.getPlayer().getPaddleY() + PaddlePanel.PADDLE_HEIGHT >= ballY-Board.paddleTolerance) {
				vx *= -1;
//				System.out.println("Hit");
			} else {
				if(System.currentTimeMillis() % 2 == 1)
					vx*=-1;
//				System.out.println("Fail");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				ballX = this.getHeight() / 2;
				Board.computerScored();
			}
		}
		if (ballX > this.getWidth()+Board.paddleTolerance) {
			System.out.println("Out");
			if (board.getComputer().getPaddleY() <= ballY+Board.paddleTolerance
					&& board.getComputer().getPaddleY() + PaddlePanel.PADDLE_HEIGHT >= ballY-Board.paddleTolerance) {
				vx *= -1;
				System.out.println("Hit");
			} else {
				System.out.println("Fail");
				if(System.currentTimeMillis() % 2 == 1)
					vx*=-1;
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					System.out.println("Interrupted");
//				}
				ballX = this.getHeight() / 2;
				Board.playerScored();
			}
		}
		System.out.println("Hello");
		if (ballY > this.getHeight()){
			System.out.println("Bottom");
			vy *= -1;
		}
		if (ballY <= 0){
			System.out.println("Top");
			vy *= -1;
		}
		System.out.println("Goodbye");
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.fillOval(ballX, ballY, DIAMETER, DIAMETER);
	}

	class MoveBall implements Runnable {

		@Override
		public void run() {
			go();
		}

	}

}
