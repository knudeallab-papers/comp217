package GH;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

import static GH.Character_status.stress;
import static GH.Character_status.hp;
import static GH.Character_status.happy;

import static GH.Main_HOBAN.happy_;

public class Gym_hammergame extends JFrame implements ActionListener{
	static int score;
	static String time;
	int totalscore;
	JFrame exit;
	static bgPanel bg;
	ImageIcon btimage;
	JButton bt;
	
	public Gym_hammergame() {
		setBounds(400,100,600,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("버그잡기 게임");
		setResizable(false);
		
		bg = new bgPanel();
		bg.setBackground(Color.white);
		bg.setLayout(null);
		
		btimage = new ImageIcon("bug.png");
		
		bt = new JButton();
		bt.setIcon(btimage);
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);
		bt.setContentAreaFilled(false);
		bt.setBounds(150,100,300,300);
		bt.addActionListener(this);
		
		exit = new JFrame();
		exit.setDefaultCloseOperation(EXIT_ON_CLOSE);
		exit.setLayout(new BorderLayout());
		exit.setResizable(false);
		exit.setBounds(1000,250,210,100);
		JButton cancle = new JButton("닫기");
		cancle.addActionListener(this);
		
		exit.add(cancle,BorderLayout.SOUTH);
		
		Timer timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 10;
			
			public void run() {
				time = "Time left: " + i;
				i--;
				bg.repaint();
				
				if(i<0) {
					
					timer.cancel();
					time = "Time Over";
					JLabel announce = new JLabel("스트레스 : "+score+" 해소!");
					exit.add(announce,BorderLayout.CENTER);
					totalscore = score;
					
					hp += totalscore/4;
					stress -= totalscore/4;
					happy += totalscore/4;
					
					happy_ += totalscore/4;
					
					bg.setOpaque(false);
					exit.setVisible(true);
				}
			}
		}, 0, 1000);
		
		bg.add(bt);
		customcursor();
		add(bg);
		
	}
	
	static void customcursor() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("hammer.png");
		Point point = new Point(0,0);
		Cursor cursor = tk.createCustomCursor(cursorimage, point, "haha");
		
		bg.setCursor(cursor);
	}
	
	private class bgPanel extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		 g.drawString("Score : " + score,10, 30);
		 g.drawString(time, 500, 30);
	}
	}
	
	public static void main(String[] args) {
		Gym_hammergame hg = new Gym_hammergame();
		hg.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		
		if(actionCmd.equals("")) {
			score++;
			btimage = new ImageIcon("bugdie.png");
			bt.setIcon(btimage);
			bg.repaint();
		}
		if(actionCmd.equals("닫기")) {
			dispose();
			exit.dispose();
		}
		
	}

}
