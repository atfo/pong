package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class PlayerPaddlePanel extends PaddlePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PlayerPaddlePanel() {
		super();
		this.setBackground(Color.RED);
	}

	public void mouseMoved(MouseEvent event) {
		int y = event.getY();
		if(y < this.getHeight()-PADDLE_HEIGHT)
			this.paddleY = y;
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(X_OFFSET, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
	}

}
