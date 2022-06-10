package Checkers;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import Main.MainMenu;
import Main.User;

public class IntroScreen extends JFrame implements WindowListener {
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image background = new ImageIcon(IntroScreen.class.getResource("/images/introBackground.png")).getImage();
	
	private ImageIcon startButtonBasicIcon = new ImageIcon(IntroScreen.class.getResource("/images/startButtonBasic.png"));
	private ImageIcon startButtonEnteredIcon = new ImageIcon(IntroScreen.class.getResource("/images/startButtonEntered.png"));
	
	private JButton startButton = new JButton(startButtonBasicIcon);
	
	public IntroScreen(User p1, User p2) {
		addWindowListener(this);
		setTitle("Checkers");
		setSize(600, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(null);
		
		startButton.setVisible(true);
		startButton.setBounds(230, 640, 150, 90);
		startButton.setBorderPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredIcon);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicIcon);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e) {
				// 시작버튼 누르면..
				new Checkers(p1, p2);
				dispose();
			}
		});
		add(startButton);
		
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		screenImage = createImage(600, 800);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		new MainMenu();
		dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
