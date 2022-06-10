package teamproject;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Window;

import java.io.File;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import java.util.Random;

public class Lots extends JFrame implements gameChanger{
	private static final int success = 1;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 600;
	private JLabel tryLabel = new JLabel();
	private int gameChanger;
	
	String paperImage = "";
	String paperPath = "./src/teamproject/draw.png";
	ImageIcon paperIcon = new ImageIcon(paperPath);
	String successImage = "";
	String successPath = "./src/teamproject/success.png";
	ImageIcon successIcon = new ImageIcon(successPath);
	String failureImage = "";
	String failurePath = "./src/teamproject/failure.png";
	ImageIcon failureIcon = new ImageIcon(failurePath);
	
	
	public static boolean tryLots() {
		Random random = new Random();
		
		if(random.nextInt(5) == success) {
			return true;
		}
		else {
			return false;
		}
		
	}	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Lots LGame = new Lots();
		LGame.setVisible(true);
	}

	Lots(){
		Random random = new Random();
		gameChanger=random.nextInt(2);
		if(gameChanger==0) {
			gameChanger=-1;
			}
		setTitle("Lots");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		
		JPanel tryPanel  = new JPanel();
		tryLabel.setIcon(paperIcon);
		tryPanel.add(tryLabel, tryPanel);
		add(tryPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		JButton drawButton = new JButton("Draw");
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
		
		if(actionCommand.equals("Draw")){
			if(gameChanger==1) {
				setGameChanger(1);
				tryLabel.setIcon(successIcon);
			}
			else {
				setGameChanger(-1);				
				tryLabel.setIcon(failureIcon);
			}
			Packer packerThread = new Packer();
		    packerThread.start();
		}
	}
	
	public void setGameChanger(int i) {
		gameChanger = i;
	} 
				
	
	public int getGameChanger() {
		
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

 
