package pongGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class Pong extends JFrame {
	
	private static final long serialVersionUID = 1L;
	//screen size variables.
	public static final int WINDOW_WIDTH = 500;
	public static final int WINDOW_HEIGHT = 400;
	
	public static boolean Replay = true;
	public boolean playCheck = false;
	
	Dimension screenSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
	
	Image dbImage;
	Graphics dbGraphics;
	
	//ball object
	static Ball b = new Ball(WINDOW_WIDTH/2-1, WINDOW_HEIGHT/2);
	
public class Login extends JFrame {
		
	private static final long serialVersionUID = 1L;
	
	Dimension screenSize = new Dimension(Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT);
	
	public Login() 
	{
		setTitle("What the Pong?");
		setSize(screenSize);
		setBackground(Color.DARK_GRAY);
		this.setResizable(false);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.DARK_GRAY);
		
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new playAction());
		buttonPanel.add(playButton);
		add(buttonPanel, BorderLayout.SOUTH);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font("SansSerif", Font.PLAIN, 24));
		g.setColor(Color.WHITE);
		g.drawString("What the pong?", 168, 200);
	}
	
	private class playAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e1) {
			playCheck = true;
			dispose();
			Thread ball = new Thread(Pong.b);
			ball.start();
			Thread p1 = new Thread(Pong.b.p1);
			Thread p2 = new Thread(Pong.b.p2);
			p1.start();
			p2.start();
		}
	}	
}
	//constructor for window
	public Pong() {
		this.setTitle("What the Pong?");
		this.setSize(screenSize);
		this.setResizable(false);
		this.setVisible(true);
		this.setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(new AL());
	}
	
	public static void main(String[] args) {
		Pong pg = new Pong();
		Login login = pg.new Login();
		login.setVisible(true);
	}
	

	@Override
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbGraphics = dbImage.getGraphics();
		draw(dbGraphics);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void draw(Graphics g) {
		
		b.draw(g);
		b.p1.draw(g);
		b.p2.draw(g);
		
		g.setFont(new Font("SansSerif", Font.PLAIN, 24));
		g.setColor(Color.WHITE);
		g.drawString(""+b.p1score, WINDOW_WIDTH/2-50, WINDOW_HEIGHT/2);
		
		for(int i=0;i<WINDOW_HEIGHT;i++)
		{
			g.drawString("|\n",WINDOW_WIDTH/2,i);
		}
		g.drawString(""+b.p2score, WINDOW_WIDTH/2+50, WINDOW_HEIGHT/2);
		
		g.setFont(new Font("SansSerif", Font.PLAIN, 15));
		g.setColor(Color.WHITE);
		g.drawString("Ball Speed : "+b.speed, 20, WINDOW_HEIGHT - 60);
		g.drawString("Player Mode : " + b.paddlemodeChange, 20, WINDOW_HEIGHT - 45);
		g.drawString("Player 1 Speed : "+b.p1.speed, 20, WINDOW_HEIGHT - 30);
		g.drawString("Player 2 Speed : "+b.p2.speed, 20, WINDOW_HEIGHT - 15);
		repaint();
		
	}
	
	public class AL extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			b.p1.keyPressed(e);
			b.p2.keyPressed(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			b.p1.keyReleased(e);
			b.p2.keyReleased(e);
		}	
	}
}