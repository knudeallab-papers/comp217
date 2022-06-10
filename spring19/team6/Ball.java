package pongGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JOptionPane;

public class Ball implements Runnable {

	//global variables
	int x, y, xDirection, yDirection;
	int speed = 2;
	int paddlemodeChange;
	
	int p1score, p2score;
	
	Paddle p1 = new Paddle(Paddle.PADDLE_WIDTH, Pong.WINDOW_HEIGHT/2-Paddle.PADDLE_HEIGHT/2, 1);
	Paddle p2 = new Paddle(Pong.WINDOW_WIDTH-2*Paddle.PADDLE_WIDTH, Pong.WINDOW_HEIGHT/2-Paddle.PADDLE_HEIGHT/2, 2);
	
	Rectangle ball;
	
	boolean exit = false;
	
	Random paddleMode = new Random();
	public Ball(int x, int y){
		p1score = p2score = 0;

		this.x = x;
		this.y = y;
		
		//Set ball moving randomly

		Random r = new Random();
		int ranXDir = r.nextInt(1);
		if (ranXDir == 0)
			ranXDir--;
		setXDirection(ranXDir * speed);
		
		int ranYDir = r.nextInt(1);
		if (ranYDir == 0)
			ranYDir--;
		setYDirection(ranYDir * speed);
		
		paddlemodeChange = 0;
		p1.speed = p2.speed = 2;
		
		//create "ball"
		ball = new Rectangle(this.x, this.y, 15, 15);
	}
	
	public void setXDirection(int xDir){
		xDirection = xDir;
	}
	
	public void setYDirection(int yDir){
		yDirection = yDir;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(ball.x, ball.y, ball.width, ball.height);
	}
	
	public void collision(){
        if(ball.intersects(p1.paddle)) {
            setXDirection(+1 * speed);
        }
        	
        if(ball.intersects(p2.paddle)){
        	setXDirection(-1 * speed);
        }
	}	
	
	public void move() {
		collision();
		ball.x += xDirection;
		ball.y += yDirection;
		
		//bounce the ball when it hits the edge of the screen
		if (ball.x <= 0) {
			paddlemodeChange = paddleMode.nextInt(2);
			
			if (paddlemodeChange == 0) {
				p1.speed = p2.speed = p1.speedChange();
			}
			if (paddlemodeChange == 1) {
				p1.speed = p1.speedChange();
				p2.speed = p2.speedChange();
				while(true) {
					if (p1.speed != p2.speed)
						break;
					else
						p2.speed = p2.speedChange();
				}
			}
			
			p2score++;
			if(p2score >= 15) {
	            JOptionPane.showMessageDialog(null, "Player 2 Win!");
	            p1score = 0;
	            p2score = 0;
	         }
			
			speed = speedChange();
			ball.x = 250;
			ball.y = 200;
			setXDirection(+1 * speed);
			setYDirection(+1 * speed);
		}
		
		if (ball.x >= Pong.WINDOW_WIDTH-15) {
			paddlemodeChange = paddleMode.nextInt(2);
			
			
			if (paddlemodeChange == 0) {
				p1.speed = p2.speed = p1.speedChange();
			}
			if (paddlemodeChange == 1) {
				p1.speed = p1.speedChange();
				p2.speed = p2.speedChange();
				while(true) {
					if (p1.speed != p2.speed)
						break;
					else
						p2.speed = p2.speedChange();
				}
			}
			
			p1score++;
			if(p1score >= 15) {
	            JOptionPane.showMessageDialog(null, "Player 1 Win!");
				p1score = 0;
	            p2score = 0;
	         }
			
			speed = speedChange();
			ball.x = 250;
			ball.y = 200;
			setXDirection(-1 * speed);
			setYDirection(+1 * speed);
			
		}
		
		if (ball.y <= 15) {
			setYDirection(+1 * speed);
		}
		
		if (ball.y >= Pong.WINDOW_HEIGHT-15) {
			setYDirection(-1 * speed);
		}
	}
	
	public int speedChange() {
		int speed = 1;

		Random r1 = new Random();
	
		int option1 = r1.nextInt(3);
		
		if (option1 == 0) {
			speed *= 1;
		}
		else if (option1 == 1) {
			speed *= 2;
		}
		else {
			speed *= 3;
		}
		JOptionPane.showMessageDialog(null, "Mode change");
		return speed;
	}
	
	@Override
	public void run() {
		try {
			while(!exit) {
				move();
				Thread.sleep(8);
			}
		}catch(Exception e) { 
			System.err.println(e.getMessage()); 
		}
	}
}