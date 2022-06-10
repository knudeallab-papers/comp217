package teamproject;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
public class Quiz extends JFrame implements gameChanger{
	 
	public static final int WIDTH = 600;
	public static final int HEIGHT =400;
	public static final int X_START = 20;
	public static final int Y_START = 200;
	private JLabel quizLabel = new JLabel();
	private JPanel quizPanel = new JPanel();
	private Font fontObj = new Font("Serif", Font.PLAIN|Font.BOLD, 30);
	private int randomChoice = 0;
	private String Quiz = "";
	private String theText = "뉴질랜드의 수도는?, 웰링턴, 오클랜드, 더니든" ; 
	String OImage = "O.png";
	String OPath = "./src/teamproject/" + OImage;
	ImageIcon OIcon = new ImageIcon(OPath);
	String XImage = "X.png";
	String XPath = "./src/teamproject/"+XImage;
	ImageIcon XIcon = new ImageIcon(XPath);	
	String filename = "./src/teamproject/" + "quiz.txt";
	
	private int gameChanger = 0;
	
	String [] quiz = new String[3];
	String [] choice1 = new String[3];
	String [] choice2 = new String[3];
	String [] choice3 = new String[3];
	String [] answer = new String[3];
	
	
	public static void main(String[] args) {
		
		Quiz qz = new Quiz();
		qz.setVisible(true);
		
	}	
	
	Quiz(){
		try {			
			Scanner inputStream = null;
			int i = 0;			
			double average = 0;
			inputStream = new Scanner(new FileInputStream(filename));			
			
			
			for(i = 0; i< 3; i++) {
				StringTokenizer sample = new StringTokenizer(inputStream.nextLine(),",");
				quiz[i] = sample.nextToken();
				choice1[i] = sample.nextToken();
				choice2[i] = sample.nextToken();
				choice3[i] = sample.nextToken();
				answer[i] = sample.nextToken();				
			}			
			
			Random random = new Random();			
			randomChoice = random.nextInt(3);			
			
			Quiz = quiz[randomChoice];  
			theText	= " 1."+ choice1[randomChoice] + " 2."+ choice2[randomChoice]+ " 3."+ choice3[randomChoice];			
							
			
			inputStream.close();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.exit(0);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		setSize(WIDTH, HEIGHT);
		setTitle("Quiz");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		//add(quizPanel, BorderLayout.CENTER);
		
		JPanel ButtonPanel = new JPanel();
		JButton firstButton = new JButton("1");
		firstButton.addActionListener(this);
		ButtonPanel.add(firstButton);
		
		JButton secondButton = new JButton("2");
		secondButton.addActionListener(this);
		ButtonPanel.add(secondButton);
		
		JButton thirdButton = new JButton("3");
		thirdButton.addActionListener(this);
		ButtonPanel.add(thirdButton);
		
		add(ButtonPanel, BorderLayout.SOUTH);
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		fontObj = new Font("SanSerif", Font.PLAIN, 24); 		
		g.setFont(fontObj);
		g.setColor(Color.BLACK);		
		g.drawString(Quiz, X_START,Y_START);
		g.drawString(theText, X_START + 20,Y_START + 40);
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
		
		if(actionCommand.equals("1")) {
			quizPanel.add(quizLabel);		
			add(quizPanel, BorderLayout.CENTER);
			if(choice1[randomChoice].compareTo(answer[randomChoice])== 0) {
				quizLabel.setIcon(OIcon);
				gameChanger=1;
				System.out.println("kk1");
			}else {
				quizLabel.setIcon(XIcon);
				gameChanger=-1;
				System.out.println("kk-1");
			}
			Packer packerThread = new Packer();
	         packerThread.start();
	         
		}
		
		else if(actionCommand.equals("2")) {
			quizPanel.add(quizLabel);		
			add(quizPanel, BorderLayout.CENTER);
			if(choice2[randomChoice].compareTo(answer[randomChoice])== 0) {
				quizLabel.setIcon(OIcon);
				gameChanger=1;
				System.out.println("kk1");
			}else {
				quizLabel.setIcon(XIcon);
				gameChanger=-1;
				System.out.println("kk-1");
			}			
			Packer packerThread = new Packer();
	         packerThread.start();
	      
		}
		
		else if(actionCommand.equals("3")) {
			if(gameChanger!=0) {
				
			System.out.println("asd");	
			}else {
			quizPanel.add(quizLabel);		
			
			add(quizPanel, BorderLayout.CENTER);
			if(choice3[randomChoice].compareTo(answer[randomChoice]) == 0) {
				quizLabel.setIcon(OIcon);
				gameChanger=1;
				System.out.println("kk1");
			}else {
				quizLabel.setIcon(XIcon);
				gameChanger=-1;
				System.out.println("kk-1");
			}
			Packer packerThread = new Packer();
	         packerThread.start();
			}
		}
		
		else {
			System.out.println("not correct answer");
		}
	}
	
	public void setGameChanger(int i) {
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