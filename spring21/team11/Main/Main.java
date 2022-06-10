package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame implements ActionListener{
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 700;
	
	private ImageIcon screen = new ImageIcon(Main.class.getResource("/images/mainScreen.png"));
	
	private JButton startButton;
	private JButton exitButton;
	
	public Main() {
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		startButton = new JButton("Start");
		startButton.setBounds(320, 370, 200, 70);
		startButton.setBackground(new Color(227, 232, 187));
		startButton.setFont(new Font("SanSerif", Font.BOLD, 24));
		startButton.setActionCommand("Start");
		startButton.addActionListener(this);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(320, 470, 200, 70);
		exitButton.setBackground(new Color(227, 232, 187));
		exitButton.setFont(new Font("SanSerif", Font.BOLD, 24));
		exitButton.setActionCommand("Exit");
		exitButton.addActionListener(this);
		
		add(startButton);
		add(exitButton);
		
		JLabel screenLabel = new JLabel(screen);
		screenLabel.setBounds(0, 0, WIDTH, HEIGHT);
		add(screenLabel);
	}
	
	public static void main(String[] args) {
		Main mainFrame = new Main();
		mainFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("Start"))
		{
			MainMenu newFrame = new MainMenu();
			newFrame.setVisible(true);
			dispose();
		}
		else if (actionCmd.equals("Exit"))
		{
			System.exit(0);
		}
	}

}
