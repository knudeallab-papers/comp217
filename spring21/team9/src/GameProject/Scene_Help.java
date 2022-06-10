package GameProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scene_Help extends JFrame{
	public static final int SMALL_WIDTH = 600;
	public static final int SMALL_HEIGHT = 300;
	final int EXPLAIN_POINT = 20;
	final int CREATOR_POINT_SIZE = 24;
	final int PEOPLE_POINT_SIZE = 16;
	Font explain = new Font("Serif", Font.PLAIN, EXPLAIN_POINT);
	Font creator = new Font("Serif", Font.BOLD, CREATOR_POINT_SIZE);
	Font people = new Font("Serif", Font.PLAIN, PEOPLE_POINT_SIZE);
	//Text
	String creatortext = "Creator";
	String peopletext = "2018113300 송정헌 2018113469 곽희섭 2018114686 박준하\n";
	String explaintext1 = "이 게임은 검문소의 직원이 되어서 ";
	String explaintext2 = "검문소 일을 직접 해보는 게임입니다.";
	String explaintext3 = "플레이어는 10Days 동안 게임을 플레이하며";
	String explaintext4 = "올바른 사람을 통과시키면 점수를 얻고,";
	String explaintext5 = "범죄자나 위조여권 사용자를 통과시키면 점수를 잃습니다.";
	
	public Scene_Help() {
		setTitle("Help");
		setSize(SMALL_WIDTH, SMALL_HEIGHT);
		getContentPane().setBackground(Color.BLACK);
		JPanel helpPanel = new JPanel();
		helpPanel.setBackground(Color.BLACK);
		setVisible(true);
	}	

	public void paint(Graphics g) {
	super.paint(g);
	g.setColor(Color.RED);
	g.setFont(creator);
	g.drawString("도움말", 30, 60);
	
	g.setFont(explain);
	g.drawString(explaintext1, 30, 90);
	g.drawString(explaintext2, 30, 115);
	g.drawString(explaintext3, 30, 140);
	g.drawString(explaintext4, 30, 165);
	g.drawString(explaintext5, 30, 190);
	
	g.setFont(creator);
	g.drawString(creatortext, 30, 250);
	
	g.setFont(people);
	g.drawString(peopletext, 30, 270);
	}
}