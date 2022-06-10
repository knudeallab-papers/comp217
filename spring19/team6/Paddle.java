package pongGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Paddle implements Runnable{
	
	public static final int PADDLE_WIDTH = 10;
	public static final int PADDLE_HEIGHT = 100;
	
	int x, y, yDirection, id;
	int speed = speedChange();
	
	Rectangle paddle;
	
	boolean exit = false;
	
	public Paddle(int x, int y, int id){
		this.x = x;
		this.y = y;
		this.id = id;
		paddle = new Rectangle(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		}
		
	public void keyPressed(KeyEvent e) {
		switch(id) {
			default:
				System.out.println("Please enter a Valid ID in paddle contructor");
				break;
			case 1:
				if(e.getKeyCode() == KeyEvent.VK_W) {
					setYDirection(-1 * speed);
			
				}
				if(e.getKeyCode() == KeyEvent.VK_S) {
					setYDirection(1 * speed);
				}
				break;
			
			case 2:
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					setYDirection(-1 * speed);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					setYDirection(1 * speed);
				}
				break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
			default:
				System.out.println("Please enter a Valid ID in paddle contructor");
				break;
			
			case 1:
				if(e.getKeyCode() == KeyEvent.VK_W) {
					setYDirection(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_S) {
					setYDirection(0);
				}
				break;
			
			case 2:
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					setYDirection(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					setYDirection(0);
				}
				break;
		}
	}
	
	public void setYDirection(int yDir) {
		yDirection = yDir;
	}
	
	public int speedChange() {
		int speed = 1;

		Random r1 = new Random();
	
		int option = r1.nextInt(3);
		
		if (option == 0) {
			speed *= 1;
		}
		else if (option == 1) {
			speed *= 2;
		}
		else {
			speed *= 3;
		}
	
		return speed;
	}
	
	public void move() {
	 	paddle.y += yDirection;
	 	if (paddle.y <= 15)
	 		paddle.y = 15;
	 	if (paddle.y >= Pong.WINDOW_HEIGHT-60)
	 		paddle.y = Pong.WINDOW_HEIGHT-60;
	}
	
	public void draw(Graphics g) {
		switch(id) {
		default:
			System.out.println("Please enter a Valid ID in paddle contructor");
			break;
		case 1:
			g.setColor(Color.CYAN);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
			break;
		case 2:
			g.setColor(Color.PINK);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
			break;
		}
	}
	
	@Override
	public void run() {
		try {
			while(!exit) {
				move();
				Thread.sleep(7);
			}
		} catch(Exception e) { System.err.println(e.getMessage()); }
	}
}