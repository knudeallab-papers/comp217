package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Checkers.IntroScreen;
import Monorail.Monorail;
import Omok.Omok;

public class GameSelect extends JFrame implements ActionListener {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 700;
	private User p1, p2;
	
	private ImageIcon screen = new ImageIcon(GameSelect.class.getResource("/images/gameSelectScreen.png"));
	
	public GameSelect(User pl1, User pl2) {
		p1 = pl1;
		p2 = pl2;
		setTitle("Game Select");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		JButton Monorail = new JButton(new ImageIcon(GameSelect.class.getResource("/images/monorail.png")));
		JButton Checkers = new JButton(new ImageIcon(GameSelect.class.getResource("/images/checkers.png")));
		JButton Omok = new JButton(new ImageIcon(GameSelect.class.getResource("/images/omok.png")));

		Monorail.setBounds(316, 360, 245, 60);
		Checkers.setBounds(316, 440, 245, 60);
		Omok.setBounds(316, 520, 245, 60);
		
		Monorail.setActionCommand("Monorail");
		Checkers.setActionCommand("Checkers");
		Omok.setActionCommand("Omok");
		
		Monorail.addActionListener(this);
		Checkers.addActionListener(this);
		Omok.addActionListener(this);
		
		add(Monorail);
		add(Checkers);
		add(Omok);
		
		JLabel screenLabel = new JLabel(screen);
		screenLabel.setBounds(0, 0, WIDTH, HEIGHT);
		add(screenLabel);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("Monorail"))
		{
			Monorail mono = new Monorail(p1, p2);
			mono.setVisible(true);
			dispose();
		}
		else if (actionCmd.equals("Checkers"))
		{
			dispose();
			new IntroScreen(p1, p2);
		}
		else if (actionCmd.equals("Omok"))
		{
			dispose();
			Omok omok = new Omok(p1, p2);
			omok.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		new GameSelect(new User(), new User());
	}

}
