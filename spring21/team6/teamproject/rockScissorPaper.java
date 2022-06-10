
package teamproject;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Random;

public class rockScissorPaper extends JFrame implements gameChanger{
	private static int scissor = 3;
	private static int rock = 2;
	private static int paper = 1;
	private static int result;
	public static final int WIDTH = 800;
	public static final int HEIGHT =600;
	
	private int gameChanger = 0;
	private String text = "";
	private int numA = 0;
	private int numB = 0;
	private JPanel gamePanel  = new JPanel();
	private JLabel gameLabel = new JLabel();
	private JLabel gameLabel2 = new JLabel();
	private JTextArea resArea  = new JTextArea(text);
	//private JLabel resLabel  = new JLabel(text);
	private JPanel resPanel  = new JPanel();
	
	String playImage = "play.png";
	String playPath = "./src/teamproject/" + playImage;
	ImageIcon playIcon = new ImageIcon(playPath);
	String rockImage = "rock.png";
	String rockPath = "./src/teamproject/"+rockImage;
	ImageIcon rockIcon = new ImageIcon(rockPath);
	String scissorImage = "scissor.png";
	String scissorPath = "./src/teamproject/"+scissorImage;
	ImageIcon scissorIcon = new ImageIcon(scissorPath);
	String paperImage = "paper.png";
	String paperPath = "./src/teamproject/"+ paperImage;
	ImageIcon paperIcon = new ImageIcon(paperPath);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		rockScissorPaper rspGame = new rockScissorPaper();
		rspGame.setVisible(true);
	}
	
	public static int cmpGame(int numA, int numB){
		if(numA == numB){ 
			// 무승부라는 뜻 
			System.out.println("Tie");
			return 0;
		}
		else if(numA+numB == 4) { //scissor, paper
			if(numA == 4)
				return cmpWhoWin(numA, numB, 1);
			else
				return cmpWhoWin(numA, numB, -1);
		}
		else if(numA + numB == 5) { //scissor, rock
			if(numA == 2)
				return cmpWhoWin(numA, numB, 1);
			else
				return cmpWhoWin(numA, numB, -1);
		}
		else if(numA + numB == 3) { //rock, paper
			if(numA == 1)
				return cmpWhoWin(numA, numB, 1);
			else
				return cmpWhoWin(numA, numB, -1);
		}		
		else {
			System.out.println("Not correct Game");
			return -2; // 만약 가위바위보가 이루어 지지 않았을 경우 나오는 것
		}
	}
	//만약 실행속도 느려질시 이 메소드 지우고 새로운 앞의 것에서 이 메소드가 행하는 것을 해도 무방하다 
	public static int cmpWhoWin(int personA, int personB, int win) {
		if(win == 1) { // if personA win return this
			System.out.println("Player 1 Win");
			return personA;
		}
		else if(win == -1){ // if personB win return this
			System.out.println("Player 2 Win");
			return personB;
		}
		else {
			System.out.println("Not correct Game");
			return -2;
		}
		 
	}
	
	public int tryRockScissorPaper() {
		Random random = new Random();
		numA = random.nextInt(2)+1;
		numB = random.nextInt(2)+1;
		result = cmpGame(numA, numB);
		
		return result;
	} 
	rockScissorPaper (){
		Random random = new Random();
		gameChanger=random.nextInt(3);
		if(gameChanger==0) {
			gameChanger=-1;
		}
		setTitle("rockScissorPaper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		
		resPanel.add(resArea);
		add(resPanel, BorderLayout.NORTH);
		
		gameLabel.setIcon(playIcon);
		gamePanel.add(gameLabel);
		add(gamePanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		JButton drawButton = new JButton("Start game!!");
		drawButton.addActionListener(this);
		buttonPanel.add(drawButton);
		add(buttonPanel,BorderLayout.SOUTH);
		
	}
	public void actionPerformed(ActionEvent e){
		try{
			assumingCorrectNumberFormats(e);
					
		}catch(NumberFormatException e2){
			//JOptionPane.showMessageDialog(this, "There exists duplicate number.", "Input error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void assumingCorrectNumberFormats(ActionEvent e){
		String actionCommand = e.getActionCommand();
		int res = 0;
		
		if(actionCommand.equals("Start game!!")){
			res = tryRockScissorPaper();
			System.out.println(res +" "+numA +" "+numB +" ");
			
			/*
			 * if(numA == scissor) { gameLabel.setIcon(scissorIcon); } else if(numA == rock)
			 * { gameLabel.setIcon(rockIcon); } else if(numA == paper) {
			 * gameLabel.setIcon(paperIcon); } else{ System.out.println("IconError"); }
			 * 
			 * gamePanel.add(gameLabel2); if(numB == scissor) {
			 * gameLabel2.setIcon(scissorIcon); } else if(numB == rock) {
			 * gameLabel2.setIcon(rockIcon); } else if(numB == paper) {
			 * gameLabel2.setIcon(paperIcon); } else{ System.out.println("IconError"); }
			 */
			if(gameChanger == 1) {
				text = "Tie";
				resArea.setText(text);
				if(numA == scissor) {
					gameLabel.setIcon(scissorIcon);
					gamePanel.add(gameLabel2);
					gameLabel2.setIcon(scissorIcon);
				}
				else if(numA == rock) {
					gameLabel.setIcon(rockIcon);
					gamePanel.add(gameLabel2);
					gameLabel2.setIcon(rockIcon);
				}
				else if(numA == paper) {
					gameLabel.setIcon(paperIcon);
					gamePanel.add(gameLabel2);
					gameLabel2.setIcon(paperIcon);
				}
				else{
					System.out.println("IconError");
				}
				Packer packerThread = new Packer();
		         packerThread.start();
			}
			else if(gameChanger==2){
				text = "You Win";
				setGameChanger(1);
				resArea.setText(text);
				if(numA == scissor) {
					gameLabel.setIcon(scissorIcon);
					gamePanel.add(gameLabel2);
					gameLabel2.setIcon(paperIcon);
				}
				else if(numA == rock) {
					gameLabel.setIcon(rockIcon);
					gamePanel.add(gameLabel2);
					gameLabel2.setIcon(scissorIcon);
				}
				else if(numA == paper) {
					gameLabel.setIcon(paperIcon);
					gamePanel.add(gameLabel2);
					gameLabel2.setIcon(rockIcon);
				}
				else{
					System.out.println("IconError");
				}
							
				
				Packer packerThread = new Packer();
		         packerThread.start();
			}
			else {
				text = "You Lose";
				setGameChanger(-1);
				resArea.setText(text);
				if(numA == scissor) {
					gameLabel.setIcon(scissorIcon);
					gamePanel.add(gameLabel2);
					gameLabel2.setIcon(rockIcon);
					
				}
				else if(numA == rock) {
					gameLabel.setIcon(rockIcon);
					gamePanel.add(gameLabel2);
					gameLabel2.setIcon(paperIcon);
					
				}
				else if(numA == paper) {
					gameLabel.setIcon(paperIcon);
					gamePanel.add(gameLabel2);
					gameLabel2.setIcon(scissorIcon);
				}
				else{
					System.out.println("IconError");
				}
				Packer packerThread = new Packer();
		         packerThread.start();
			}
		}
	}	
	
	public void setGameChanger(int i){
		gameChanger = i;
	}		
	public int getGameChanger(){
		return gameChanger;
	}
	private class Packer extends Thread{
	    public void run() 
	    {         
	       try {
	          Thread.sleep(1000);
	          dispose();
	       }catch(InterruptedException e) {
	          System.out.println("Unexpected interrupt");
	          System.exit(0);
	       }
	    }
	       
	 }
}
