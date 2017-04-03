package gui.threads;

import gui.ComputerPaddlePanel;

public class ComputerThread extends Thread {

	private ComputerPaddlePanel computerPaddle;

	public ComputerThread(ComputerPaddlePanel computerPaddle) {
		super();
		this.computerPaddle = computerPaddle;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread launch");
		computerPaddle.go();
	}

}
