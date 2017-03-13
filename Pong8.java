import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Pong8 extends GraphicsProgram {
       /**
             * 
              */
       private static final long serialVersionUID = 1L;
       /** Width and height of application window in pixels */
       public static final int APPLICATION_WIDTH = 2000;
       public static final int APPLICATION_HEIGHT = 1000;

       private static final int HEIGHT = APPLICATION_HEIGHT;

       /** Dimensions of the paddle */
       private static final int PADDLE_WIDTH = 20;
       private static final int PADDLE_HEIGHT = 90;

       /** Offset of the paddle from the side */
       private static final int PADDLE_X_OFFSET = 30;

       /** Radius of the ball in pixels */
       private static final int BALL_RADIUS = 20;

       /** Ball velocity */
       private double vx, vy;
       /** Random number generator for vx */

       private RandomGenerator rgen = RandomGenerator.getInstance();
       
       /** Animation delay or pause time between ball moves */
       private static int DELAY = 10;
       
       private int score1;
       private int score2;

       public void run() {
             while (true) {
            	 initScore();
                 chooseLevel();
                 addMouseListeners();
                 waitForClick();
                 setUpGame();
                 playGame();
                 initScore();
             }
       }

       private void chooseLevel() {
    	   	System.out.print("Level: ");
             @SuppressWarnings("resource")
             Scanner in = new Scanner(System.in);
             int level = Integer.parseInt(in.nextLine());
             if(level <= 10 && level >= 0) DELAY = 10-level;
             
       }

       private void setUpGame() {
             drawPaddle1();
             drawPaddle2();
             drawBall();
             drawMiddle();
             displayScore1();
             displayScore2();
       }

       private GLabel displayScore1;
       private GLabel displayScore2;

       private void displayScore2() {
             
             displayScore2 = new GLabel("Score 2 = " + score2, getWidth() - 70, 30);
             add(displayScore2);
       }

       private void displayScore1() {
             
             displayScore1 = new GLabel("Score 1 = " + score1, 50, 30);
             add(displayScore1);
       }

       private void drawMiddle() {
             
             GLine middle = new GLine(getWidth() / 2, 0, getWidth() / 2, HEIGHT);
             add(middle);
       }

       private GRect paddle1;
       private GRect paddle2;

       private void drawPaddle1() {
             // paddle set-up
             double x = PADDLE_X_OFFSET;
             double y = getHeight() / 2;
             paddle1 = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
             paddle1.setFilled(true);
             add(paddle1);
             addMouseListeners();
       }

       // making the mouse track the paddle
       public void mouseMoved(MouseEvent e) {
             if ((e.getY() < getHeight() - PADDLE_HEIGHT / 2) && (e.getY() > PADDLE_HEIGHT / 2)) {
                    paddle1.setLocation(PADDLE_X_OFFSET + PADDLE_WIDTH, e.getY() - PADDLE_HEIGHT / 2);
             }
       }

       private void drawPaddle2() {
             // paddle set-up
             // starting the paddle in the middle of the screen
             double x = getWidth() - PADDLE_WIDTH / 2 - PADDLE_X_OFFSET;
             // the paddle height stays consistent throughout the game
             // need to make sure to subtract the PADDLE_HEIGHT,
             // since the rectangle gets drawn from the top left corner
             double y = getHeight() / 2;
             paddle2 = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
             paddle2.setFilled(true);
             add(paddle2);
       }

       private void movePaddle() {
             movePaddleUpAndDown();
       }

       private void movePaddleUpAndDown() {
             boolean moving = rgen.nextBoolean(0.55);
             if (moving == false) {
                    if ((paddle2.getY() < getHeight() - PADDLE_HEIGHT)) {
                           paddle2.setLocation(getWidth() - PADDLE_X_OFFSET - PADDLE_WIDTH, paddle2.getY() + 10);
                           moveBall();
                           pause(DELAY);
                    }
                    if ((paddle2.getY() > 0)) {
                           paddle2.setLocation(getWidth() - PADDLE_X_OFFSET - PADDLE_WIDTH, paddle2.getY() - 10);
                           moveBall();
                           pause(DELAY);
                    }
             } else {
                    paddle2.setLocation(paddle2.getX(), ball.getY());
             }
       }

       private void playGame() {
             waitForClick();
             getBallVelocity();
             while (score1 != 8 || score2 != 8) {
                    movePaddle();
             }
       }

       private void drawBall() {
             double x = getWidth() / 2 - BALL_RADIUS / 2;
             double y = getHeight() / 2 - BALL_RADIUS;
             ball = new GOval(x, y, BALL_RADIUS, BALL_RADIUS);
             ball.setFilled(true);
             add(ball);
       }

       private void getBallVelocity() {
             vy = 10.0;
             vx = rgen.nextDouble(9.5, 10.0);
             if (rgen.nextBoolean(0.5)) {
                    vx = -vx;
             }
       }

       private GOval ball;

       public void moveBall() {
             if (ball.getX() < 0) {
                    remove(displayScore2);
                    score2++;
                    displayScore2();
                    ball.setLocation(getWidth() / 2, getHeight() / 2);
                    if (score2 == 8) {
                           removeAll();
                           GLabel player2won = new GLabel("You lost...", getWidth() / 2 - 50, getHeight() / 2 - 50);
                           player2won.setFont("TimesNewRoman-50");
                           player2won.setColor(Color.RED);
                           add(player2won);
                           waitForClick();
                           removeAll();
                           initScore();
                           run();
                    }
             }
             if (ball.getX() > getWidth()) {
                    remove(displayScore1);
                    score1++;
                    displayScore1();
                    ball.setLocation(getWidth() / 2, getHeight() / 2);
                    if (score1 == 8) {
                           removeAll();
                           GLabel player1won = new GLabel("Player 1 won!!!", getWidth() / 2 - 50, getHeight() / 2 - 50);
                           player1won.setFont("TimesNewRoman-50");
                           player1won.setColor(Color.RED);
                           add(player1won);
                           waitForClick();
                           removeAll();
                           initScore();
                           run();
                           
                    }
             }
             ball.move(vx, vy);
             if ((ball.getY() - vy <= 0 && vy < 0) || (ball.getY() >= getHeight())) {
                    vy = -vy;
             }
             // check for other objects
             GObject collider = getCollidingObject();
             if (collider == paddle1 || collider == paddle2) {
                    /*
                    * We need to make sure that the ball only bounces off the top part
                    * of the paddle and also that it doesn't "stick" to it if different
                    * sides of the ball hit the paddle quickly and get the ball "stuck"
                    * on the paddle. I ran "println ("vx: " + vx + ", vy: " + vy + ",
                    * ballX: " + ball.getX() + ", ballY: " +ball.getY());" and found
                    * that the ball.getY() changes by 4 every time, instead of 1, so it
                    * never reaches exactly the the height at which the ball hits the
                    * paddle (paddle height + ball height), therefore, I estimate the
                    * point to be greater or equal to the height at which the ball hits
                    * the paddle, but less than the height where the ball hits the
                   * paddle minus 4.
                    */
                    if ((ball.getX() <= PADDLE_X_OFFSET + PADDLE_WIDTH + BALL_RADIUS * 2
                                  && ball.getX() > PADDLE_X_OFFSET + PADDLE_WIDTH + BALL_RADIUS * 2 - 45)
                                 || (ball.getX() >= getWidth() - PADDLE_X_OFFSET - PADDLE_WIDTH - BALL_RADIUS * 2
                                               && ball.getX() < getWidth() - PADDLE_X_OFFSET - PADDLE_WIDTH - BALL_RADIUS * 2 + 40)) {
                           vx = -vx;

                    }
             }
       }

       private GObject getCollidingObject() {
             if ((getElementAt(ball.getX(), ball.getY())) != null) {
                    return getElementAt(ball.getX(), ball.getY());
             } else if (getElementAt((ball.getX() + BALL_RADIUS * 2), ball.getY()) != null) {
                    return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());
             } else if (getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS * 2)) != null) {
                    return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);
             } else if (getElementAt((ball.getX() + BALL_RADIUS * 2), (ball.getY() + BALL_RADIUS * 2)) != null) {
                    return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2);
             }
             // need to return null if there are no objects present
             else {
                    return null;
             }
       }

       private void initScore(){
             score1 = 0;
             score2 = 0;
       }
}

class Ball extends GraphicsProgram{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void run(){
		new Pong8();
	}
	
}

