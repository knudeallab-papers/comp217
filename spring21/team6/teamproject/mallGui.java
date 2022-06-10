package teamproject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;

public class mallGui extends JFrame implements ActionListener{
	private int player1Nammal = 0;
	private int player2Nammal = 0;	
	private boolean turn = true;
	private String resText = "?";
	private yut yourYut = new yut();
//	private Pplayer player1 = new Pplayer();
//	private Pplayer player2 = new Pplayer();
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 720;
	public static final int POINT_SIZE = 30;	
	private JLabel resLabel = new JLabel(resText);
	private JLabel yutLabel1 = new JLabel();			
	private JLabel yutLabel2 = new JLabel();			
	private JLabel yutLabel3 = new JLabel();			
	private JLabel yutLabel4 = new JLabel();		
	
	//�̹��� ���
	String playerCheckImage;
	String playerCheckPath;
	ImageIcon CheckIcon;
	String player1mallImage;
	String player1mallPath;
	ImageIcon player1mallIcon;
	String player2mallImage;
	String player2mallPath;
	ImageIcon player2mallIcon;
	String WhiteImage;
	String WhitePath;
	ImageIcon WhiteIcon;
	String frontYutImage;
	String frontYutPath;
	ImageIcon frontYutIcon;
	String rearYutImage;
	String rearYutPath;
	ImageIcon rearYutIcon;
	String backDoImage;
	String backDoPath;
	ImageIcon backDoIcon;
	JPanel PlayerPanel;
	JButton clickButton;
	
	static String Src = "src";
	static String Package = "teamproject";
	//�� ��© �̹���
	String yutImage = "";
	String yutPath = "";
	ImageIcon yutIcon = new ImageIcon(yutPath);
	//��� ǥ�õǴ� �۾� �����ؼ� ���� ��, �̹����� �������� ���������𻪵� �� 6���� �̹����� �����;� �ȴ�
	String resImage = "";
	String resPath = "";
	ImageIcon resIcon = new ImageIcon(resPath);
	
	
	
	
/*	public int visualizeMall(Pplayer player) {		 
		return player.getNamunmal();		
	}*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mallGui Bgame = new mallGui();
		Bgame.setVisible(true);
	}
	mallGui(){
		setSize(WIDTH, HEIGHT);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.GREEN);
		setLayout(null);
		
		String currentProjPath = "";
		try {
			currentProjPath = new File(".").getCanonicalPath();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		playerCheckImage = "greenCircle.png";
		playerCheckPath = currentProjPath + "/" + Src + "/" + Package + "/" + playerCheckImage;
		CheckIcon = new ImageIcon(playerCheckPath);
		player1mallImage = "orangeCircle.png";
		player1mallPath = currentProjPath + "/" + Src + "/" + Package + "/" + player1mallImage;
		player1mallIcon = new ImageIcon(player1mallPath);
		player2mallImage = "purpleCircle.png";
		player2mallPath = currentProjPath + "/" + Src + "/" + Package + "/" + player2mallImage;
		player2mallIcon = new ImageIcon(player2mallPath);
		WhiteImage = "white.png";
		WhitePath = currentProjPath + "/" + Src + "/" + Package + "/" + WhiteImage;
		WhiteIcon = new ImageIcon(WhitePath);
		frontYutImage = "yut1.png";
		frontYutPath = currentProjPath + "/" + Src + "/" + Package + "/" + frontYutImage;
		frontYutIcon = new ImageIcon(frontYutPath);
		rearYutImage = "yut2.png";
		rearYutPath = currentProjPath + "/" + Src + "/" + Package + "/" + rearYutImage;
		rearYutIcon = new ImageIcon(rearYutPath);
		backDoImage = "yut3.png";
		backDoPath = currentProjPath + "/" + Src + "/" + Package + "/" + backDoImage;
		backDoIcon = new ImageIcon(backDoPath);
		
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setBounds(0,0,720,720);
		add(gamePanel);
		
		int i;
		//�ʺ� 280, ���� 720 , ���߿��� �⺻ 720���� ���ؼ� �����ϸ� �ǰڴ�
		//�÷��̾�1,2 �������� ���� ���������� ǥ���ϴ� �г�
		PlayerPanel = new JPanel();
		PlayerPanel.setLayout(null);
		PlayerPanel.setBounds(720, 0, 280, 720);				
		
		//ù��° �÷��̾��� ǥ�� ����				
		
		JLabel IconCheck1 = new JLabel();
		IconCheck1.setBounds(0, 0, 40, 90);
		if(turn == true) {
			IconCheck1.setIcon(CheckIcon);
		}
		else {
			IconCheck1.setIcon(WhiteIcon);
		}
		
		//�̰� �̹����� ������ �ƴϸ� ��Ʈ Ȱ���ؼ� �� �� �ѹ� ���غ���		
		JLabel CheckLabel = new JLabel("Player 1");
		CheckLabel.setBounds(40, 0, 240, 90);		
		
		PlayerPanel.add(IconCheck1);
		PlayerPanel.add(CheckLabel);
		
		
		
		//ù��° ���� ����		
		JLabel []mallLabel = new JLabel[4];
		
		int interval = 0;		
		for(i = 0; i< 4; i++) {
			mallLabel[i] = new JLabel();
			mallLabel[i].setBounds(0+interval, 90, 70, 90);
			PlayerPanel.add(mallLabel[i]);
			interval = interval + 70;
		}	
		
		//�̰� ��ư �ٲ�� �ٲ�� �ؾ� �� �״�� ActionListener�ʿ� �������� �ᵵ �� �� 
//		for(i = 0; i< 4; i++) {
//			if(i <= visualizeMall(player1)){
//				mallLabel[i].setIcon(player1mallIcon);
//			}else{
//				mallLabel[i].setIcon(WhiteIcon);
//			}			
//		}		
				
		
		//�ι�° �÷��̾� �̸� ǥ�� ����		
		
		JLabel IconCheck2 = new JLabel();
		IconCheck2.setBounds(0, 180, 40, 90);
		
		//���� �ڵ�� �ߺ��Ǵ� ���߿� turn�� ��ĥ�� ������ ���س�����
		if(turn == false) {
			IconCheck2.setIcon(CheckIcon);
		}
		else {
			IconCheck2.setIcon(WhiteIcon);
		}
		
		JLabel CheckLabel2 = new JLabel("Player 2");
		CheckLabel2.setBounds(40, 180, 240, 90);
		
		PlayerPanel.add(IconCheck2);
		PlayerPanel.add(CheckLabel2);		
		
		//�ι�° �÷��̾� �� ǥ�� ����				
		JLabel []mallLabel2 = new JLabel[4];
		
		int secondInterval = 0;
		
		for(i = 0; i< 4; i++) {
			mallLabel2[i] = new JLabel();
			mallLabel2[i].setBounds(0+secondInterval, 270, 70, 90);
			PlayerPanel.add(mallLabel2[i]);
			secondInterval = secondInterval + 70;
		}	
		
		//�̰� ��ư �ٲ�� �ٲ�� �ؾ� �� �״�� ActionListener�ʿ� �������� �ᵵ �� �� 
	//	for(i = 0; i< 4; i++) {
//			if(i <= visualizeMall(player2)){
//				mallLabel2[i].setIcon(player2mallIcon);
//			}else{
//				mallLabel2[i].setIcon(WhiteIcon);
//			}			
//		}		
				
		
		yutLabel1.setBounds(0, 360, 70, 270);
		yutLabel2.setBounds(70, 360, 70, 270);
		yutLabel3.setBounds(140, 360, 70, 270);
		yutLabel4.setBounds(210, 360, 70, 270);
		
		yutLabel1.setIcon(frontYutIcon);
		yutLabel2.setIcon(frontYutIcon);
		yutLabel3.setIcon(frontYutIcon);
		yutLabel4.setIcon(frontYutIcon);
				
		PlayerPanel.add(yutLabel1);				
		PlayerPanel.add(yutLabel2);				
		PlayerPanel.add(yutLabel3);				
		PlayerPanel.add(yutLabel4);		
				
		
		
		
		resLabel.setBounds(0, 630, 40, 60);
		PlayerPanel.add(resLabel);
		
		
		clickButton = new JButton("click!");
		clickButton.setBounds(40, 630, 240, 60);
		clickButton.addActionListener(this);
		PlayerPanel.add(clickButton);
		
		add(PlayerPanel);
		
	}
	public void actionPerformed(ActionEvent e){
		try{
			assumingCorrectNumberFormats(e);
		}catch(NumberFormatException e2){
			//ioField.setText("Error: Reenter Number.");
			//JOptionPane.showMessageDialog(this);
		}
	}
	
	public void assumingCorrectNumberFormats(ActionEvent e){
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("click!")) {
			int res = 0;
			//���� ��â ����� �������� ��© �����ش�.
			//�� �� ���� ����
			
			yourYut.yutDun();			
			for(int i = 0; i< 3; i++) {
				res += yourYut.getYut(i);
			}
			if(res == 3 && yourYut.getYut(3) == 0) {
				resText = "��";
				resLabel.setText(resText);
				if(yourYut.getYut(0) == 0) {			
					yutLabel1.setIcon(frontYutIcon);
				}
				else {
					yutLabel1.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(1) == 0) {			
					yutLabel2.setIcon(frontYutIcon);
				}
				else {
					yutLabel2.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(2) == 0) {			
					yutLabel3.setIcon(frontYutIcon);
				}
				else {
					yutLabel3.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(3) == 0) {			
					yutLabel4.setIcon(frontYutIcon);					
				}
				else {
					yutLabel4.setIcon(backDoIcon);				
				}
			}
			else if(res == 3 && yourYut.getYut(3) == 1) {
				resText = "��";
				resLabel.setText(resText);
				if(yourYut.getYut(0) == 0) {			
					yutLabel1.setIcon(frontYutIcon);
				}
				else {
					yutLabel1.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(1) == 0) {			
					yutLabel2.setIcon(frontYutIcon);
				}
				else {
					yutLabel2.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(2) == 0) {			
					yutLabel3.setIcon(frontYutIcon);
				}
				else {
					yutLabel3.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(3) == 0) {			
					yutLabel4.setIcon(frontYutIcon);
				}
				else {					
					yutLabel4.setIcon(backDoIcon);
				}
			}
			else if(res == 2 && yourYut.getYut(3) == 1) {
				resText = "��";
				resLabel.setText(resText);
				if(yourYut.getYut(0) == 0) {			
					yutLabel1.setIcon(frontYutIcon);
				}
				else {
					yutLabel1.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(1) == 0) {			
					yutLabel2.setIcon(frontYutIcon);
				}
				else {
					yutLabel2.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(2) == 0) {			
					yutLabel3.setIcon(frontYutIcon);
				}
				else {
					yutLabel3.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(3) == 0) {			
					yutLabel4.setIcon(frontYutIcon);
				}
				else {
					yutLabel4.setIcon(backDoIcon);				
				}
			}
			else if(res == 1 && yourYut.getYut(3) == 1) {
				resText = "��";
				resLabel.setText(resText);
				if(yourYut.getYut(0) == 0) {			
					yutLabel1.setIcon(frontYutIcon);
				}
				else {
					yutLabel1.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(1) == 0) {			
					yutLabel2.setIcon(frontYutIcon);
				}
				else {
					yutLabel2.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(2) == 0) {			
					yutLabel3.setIcon(frontYutIcon);
				}
				else {
					yutLabel3.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(3) == 0) {			
					yutLabel4.setIcon(frontYutIcon);
				}
				else {
					yutLabel4.setIcon(backDoIcon);				
				}
			}
			else if(res == 0 && yourYut.getYut(3) == 1) {
				resText = "����";
				resLabel.setText(resText);
				if(yourYut.getYut(0) == 0) {			
					yutLabel1.setIcon(frontYutIcon);
				}
				else {
					yutLabel1.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(1) == 0) {			
					yutLabel2.setIcon(frontYutIcon);
				}
				else {
					yutLabel2.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(2) == 0) {			
					yutLabel3.setIcon(frontYutIcon);
				}
				else {
					yutLabel3.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(3) == 0) {			
					yutLabel4.setIcon(frontYutIcon);
				}
				else {
					yutLabel4.setIcon(backDoIcon);				
				}
			}			
			else if(res == 2 && yourYut.getYut(3) == 0) {
				resText = "��";
				resLabel.setText(resText);
				if(yourYut.getYut(0) == 0) {			
					yutLabel1.setIcon(frontYutIcon);
				}
				else {
					yutLabel1.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(1) == 0) {			
					yutLabel2.setIcon(frontYutIcon);
				}
				else {
					yutLabel2.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(2) == 0) {			
					yutLabel3.setIcon(frontYutIcon);
				}
				else {
					yutLabel3.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(3) == 0) {			
					yutLabel4.setIcon(frontYutIcon);
				}
				else {
					yutLabel4.setIcon(backDoIcon);				
				}
			}
			else if(res == 1 && yourYut.getYut(3) == 0) {
				resText = "��";
				resLabel.setText(resText);
				if(yourYut.getYut(0) == 0) {			
					yutLabel1.setIcon(frontYutIcon);
				}
				else {
					yutLabel1.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(1) == 0) {			
					yutLabel2.setIcon(frontYutIcon);
				}
				else {
					yutLabel2.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(2) == 0) {			
					yutLabel3.setIcon(frontYutIcon);
				}
				else {
					yutLabel3.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(3) == 0) {			
					yutLabel4.setIcon(frontYutIcon);
				}
				else {
					yutLabel4.setIcon(backDoIcon);				
				}
			}
			else if(res == 0 && yourYut.getYut(3) == 0) {
				resText = "��";
				resLabel.setText(resText);
				if(yourYut.getYut(0) == 0) {			
					yutLabel1.setIcon(frontYutIcon);
				}
				else {
					yutLabel1.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(1) == 0) {			
					yutLabel2.setIcon(frontYutIcon);
				}
				else {
					yutLabel2.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(2) == 0) {			
					yutLabel3.setIcon(frontYutIcon);
				}
				else {
					yutLabel3.setIcon(rearYutIcon);				
				}
				if(yourYut.getYut(3) == 0) {			
					yutLabel4.setIcon(frontYutIcon);
				}
				else {
					yutLabel4.setIcon(backDoIcon);				
				}
			}
			else {
				System.out.print("not correct yutDun");
			}
			
		}
	}
	
/*	private class ConfirmWindow extends JFrame implements ActionListener{
	public ConfirmWindow() {
		setSize(SMALL_WIDTH, SMALL_HEIGHT);
		setTitle("");
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		String	DunningImage = "";
		String DunningPath = "";
		ImageIcon DunningIcon = new ImageIcon(DunningPath);
				
		JLabel yutLabel = new JLabel();
		yutLabel.setIcon(DunningIcon);
		add(yutLabel, BorderLayout.CENTER);
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		if(actionCmd.equals("Ȯ��")) {
			System.exit(0);
		}
		else
			System.out.println("Unexpected Error"
					+ " in Confirm Window");
	}
}*/ // end of ConfirmWindow
}