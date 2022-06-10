package teamproject;
/***1. 판 num 바뀌는 부분에서 백도 수정 필요
 * 2. 윷, 모 나온 후 상대 말 잡았을 때 한번 더 안던지는 거 수정 필요(모로 잡았을 때도 던지게)
 * 3. 윷, 모로 상대 말 잡았을 때 차례가 상대로 넘어가는 거 수정 필요(해결)
 * 4. player1 윷, 모 나왔을 때 한번 더 안던지는 거 수정 필요(해결)
 * */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;

public class Pan extends JFrame implements ActionListener{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 720;
	public static final int TEXT_FIELD_SIZE = 10;
	public static final String srcPath = "src";
	public static final String packageName = "teamproject";
	
	JFrame comp = new JFrame();
	JPanel btnPanel = new JPanel();
	JPanel pan;
	
	ImageIcon btn1;
	ImageIcon btn2;
	ImageIcon btnch;
	ImageIcon trbtn;
	ImageIcon exist;
	ImageIcon bgCh;
	ImageIcon bgEx;
	ImageIcon exist2;
	ImageIcon bgEx2;
	
	Malll[] P1 = new Malll[2];
	Malll[] P2 = new Malll[2];
	
	boolean player1 = true;
	boolean player2 = false;
	
	boolean chance1 = true;
	boolean chance2 = true;
	
	public int p1cnt = 0;
	public int p2cnt = 0;
	public int num1;
	public int num2;
	public ArrayList<Integer> p1rd=new ArrayList<Integer>();
	public ArrayList<Integer> p2rd=new ArrayList<Integer>();
	
	public int mall;
	
	//public int loc;
	
	public int[] bfmv1 = new int[2];
	public int[] bfmv2 = new int[2];
	
	public int[] afmv1 = new int[2];
	public int[] afmv2 = new int[2];
	
	public int loc;
	
	JButton[] btn = new JButton[71];
	boolean[] ch1 = new boolean[71];
	boolean[] ch2 = new boolean[71];
	JLabel[] image = new JLabel[71];
	boolean throwY = false;
	
	yut y = new yut();
	
	private JTextField message;
	
	public static void main(String[] args) {
		Pan panGui = new Pan();
		panGui.comp.setVisible(true);
	}
	
	public Pan() {
		comp.setSize(WIDTH, HEIGHT);
		comp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		comp.setBackground(Color.WHITE);
		comp.setLayout(null);
		
		pan = new JPanel(); 
		pan.setLayout(null);
		pan.setBounds(0,0,700,720);
		
		comp.validate();
		pan.validate();

		
		btnPanel.setLayout(new BorderLayout());
		btnPanel.setBounds(700,0,300,720);
		
		String currentProjPath = "";
		try {
			currentProjPath = new File(".").getCanonicalPath();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		String button1 = "BUTTON1.png";
		String button2 = "BUTTON2.png";
		String btonCh = "BUTTON_change.png";
		String button = "button.png";
		String exbutton = "exist.png";
		String bigCh = "bigchange.png";
		String bigEx = "bigexist.png";
		String exbutton2 = "exist2.png";
		String bigEx2 = "bigexist2.png";
		
		btn1 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+button1);
		btn2 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+button2);
		btnch = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+btonCh);
		trbtn = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+button);
		exist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+exbutton);
		bgCh = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+bigCh);
		bgEx = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+bigEx);
		exist2 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+exbutton2);
		bgEx2 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+bigEx2);
		
		JButton click = new JButton("click");
		click.addActionListener(this);
		btnPanel.add(click);
		
		for(int i=0;i<2;i++) {
			P1[i] = new Malll();
			P2[i] = new Malll();
		}
		
		for(int i=0;i<71;i++) {
			image[i]=new JLabel(btn1);
			btn[i]=new JButton(trbtn);
		}
		
		image[0] = new JLabel(btn1);
		image[0].setBounds(535, 545, 100, 120);
			
		btn[0] = new JButton(trbtn);
		btn[0].setBounds(535, 545, 100, 120);
		btn[0].setBorderPainted(false);
		btn[0].setFocusPainted(false);
		btn[0].setContentAreaFilled(false);
		btn[0].addActionListener(this);
		
		image[1] = new JLabel(btn2);
		image[1].setBounds(535, 545-125, 100, 120);
			
		btn[1] = new JButton(trbtn);
		btn[1].setBounds(535, 545-125, 100, 120);
		btn[1].setBorderPainted(false);
		btn[1].setFocusPainted(false);
		btn[1].setContentAreaFilled(false);
		btn[1].addActionListener(this);

		image[2] = new JLabel(btn2);
		image[2].setBounds(535, 545-225, 100, 120);
		
		btn[2] = new JButton(trbtn);
		btn[2].setBounds(535, 545-225, 100, 120);
		btn[2].setBorderPainted(false);
		btn[2].setFocusPainted(false);
		btn[2].setContentAreaFilled(false);
		btn[2].addActionListener(this);
		//btn1_3.setEnabled(false);
		
		image[3] = new JLabel(btn2);
		image[3].setBounds(535, 545-325, 100, 120);
		
		btn[3] = new JButton(trbtn);
		btn[3].setBounds(535, 545-325, 100, 120);
		btn[3].setBorderPainted(false);
		btn[3].setFocusPainted(false);
		btn[3].setContentAreaFilled(false);
		btn[3].addActionListener(this);
		//btn1_4.setEnabled(false);
		
		image[4] = new JLabel(btn2);
		image[4].setBounds(535, 545-425, 100, 120);
		
		btn[4] = new JButton(trbtn);
		btn[4].setBounds(535, 545-425, 100, 120);
		btn[4].setBorderPainted(false);
		btn[4].setFocusPainted(false);
		btn[4].setContentAreaFilled(false);
		btn[4].addActionListener(this);
		//btn1_5.setEnabled(false);
		
		image[60] = new JLabel(btn1);
		image[60].setBounds(535, 10, 100, 120);
		
		btn[60] = new JButton(trbtn);
		btn[60].setBounds(535, 10, 100, 120);
		btn[60].setBorderPainted(false);
		btn[60].setFocusPainted(false);
		btn[60].setContentAreaFilled(false);
		btn[60].addActionListener(this);
		//btn2_1.setEnabled(false);
		
		image[6] = new JLabel(btn2);
		image[6].setBounds(535-120, 10, 100, 120);
		
		btn[6] = new JButton(trbtn);
		btn[6].setBounds(535-120, 10, 100, 120);
		btn[6].setBorderPainted(false);
		btn[6].setFocusPainted(false);
		btn[6].setContentAreaFilled(false);
		btn[6].addActionListener(this);
		//btn2_2.setEnabled(false);
		
		image[7] = new JLabel(btn2);
		image[7].setBounds(545-220, 10, 100, 120);
		
		btn[7] = new JButton(trbtn);
		btn[7].setBounds(545-220, 10, 100, 120);
		btn[7].setBorderPainted(false);
		btn[7].setFocusPainted(false);
		btn[7].setContentAreaFilled(false);
		btn[7].addActionListener(this);
		//btn2_3.setEnabled(false);
		
		image[8] = new JLabel(btn2);
		image[8].setBounds(545-320, 10, 100, 120);
		
		btn[8] = new JButton(trbtn);
		btn[8].setBounds(545-320, 10, 100, 120);
		btn[8].setBorderPainted(false);
		btn[8].setFocusPainted(false);
		btn[8].setContentAreaFilled(false);
		btn[8].addActionListener(this);
		//btn2_4.setEnabled(false);
		
		image[9] = new JLabel(btn2);
		image[9].setBounds(545-420, 10, 100, 120);
		
		btn[9] = new JButton(trbtn);
		btn[9].setBounds(545-420, 10, 100, 120);
		btn[9].setBorderPainted(false);
		btn[9].setFocusPainted(false);
		btn[9].setContentAreaFilled(false);
		btn[9].addActionListener(this);
		//btn2_5.setEnabled(false);
		
		image[40] = new JLabel(btn1);
		image[40].setBounds(10 , 10, 100, 120);
		
		btn[40] = new JButton(trbtn);
		btn[40].setBounds(10 , 10, 100, 120);
		btn[40].setBorderPainted(false);
		btn[40].setFocusPainted(false);
		btn[40].setContentAreaFilled(false);
		btn[40].addActionListener(this);
		//btn[10].setEnabled(false);
		
		image[11] = new JLabel(btn2);
		image[11].setBounds(10, 10+110, 100, 120);
		
		btn[11] = new JButton(trbtn);
		btn[11].setBounds(10, 10+110, 100, 120);
		btn[11].setBorderPainted(false);
		btn[11].setFocusPainted(false);
		btn[11].setContentAreaFilled(false);
		btn[11].addActionListener(this);
		//btn[11].setEnabled(false);
		
		image[12] = new JLabel(btn2);
		image[12].setBounds(10, 10+210, 100, 120);
		
		btn[12] = new JButton(trbtn);
		btn[12].setBounds(10, 10+210, 100, 120);
		btn[12].setBorderPainted(false);
		btn[12].setFocusPainted(false);
		btn[12].setContentAreaFilled(false);
		btn[12].addActionListener(this);
		//btn[12].setEnabled(false);
		
		image[13] = new JLabel(btn2);
		image[13].setBounds(10, 10+310, 100, 120);
		
		btn[13] = new JButton(trbtn);
		btn[13].setBounds(10, 10+310, 100, 120);
		btn[13].setBorderPainted(false);
		btn[13].setFocusPainted(false);
		btn[13].setContentAreaFilled(false);
		btn[13].addActionListener(this);
		//btn[13].setEnabled(false);
		
		image[14] = new JLabel(btn2);
		image[14].setBounds(10, 10+410, 100, 120);
		
		btn[14] = new JButton(trbtn);
		btn[14].setBounds(10, 10+410, 100, 120);
		btn[14].setBorderPainted(false);
		btn[14].setFocusPainted(false);
		btn[14].setContentAreaFilled(false);
		btn[14].addActionListener(this);
		//btn[14].setEnabled(false);
		
		image[15] = new JLabel(btn1);
		image[15].setBounds(10 , 545, 100, 120);
		
		btn[15] = new JButton(trbtn);
		btn[15].setBounds(10 , 545, 100, 120);
		btn[15].setBorderPainted(false);
		btn[15].setFocusPainted(false);
		btn[15].setContentAreaFilled(false);
		btn[15].addActionListener(this);
		//btn[15].setEnabled(false);
		
		image[16] = new JLabel(btn2);
		image[16].setBounds(10+110, 545, 100, 120);
		
		btn[16] = new JButton(trbtn);
		btn[16].setBounds(10+110, 545, 100, 120);
		btn[16].setBorderPainted(false);
		btn[16].setFocusPainted(false);
		btn[16].setContentAreaFilled(false);
		btn[16].addActionListener(this);
		//btn[16].setEnabled(false);
		
		image[17] = new JLabel(btn2);
		image[17].setBounds(10+210, 545, 100, 120);
		
		btn[17] = new JButton(trbtn);
		btn[17].setBounds(10+210, 545, 100, 120);
		btn[17].setBorderPainted(false);
		btn[17].setFocusPainted(false);
		btn[17].setContentAreaFilled(false);
		btn[17].addActionListener(this);
		//btn[17].setEnabled(false);
		
		image[18] = new JLabel(btn2);
		image[18].setBounds(10+310, 545, 100, 120);
		
		btn[18] = new JButton(trbtn);
		btn[18].setBounds(10+310, 545, 100, 120);
		btn[18].setBorderPainted(false);
		btn[18].setFocusPainted(false);
		btn[18].setContentAreaFilled(false);
		btn[18].addActionListener(this);
		//btn[18].setEnabled(false);
		
		image[19] = new JLabel(btn2);
		image[19].setBounds(10+410, 545, 100, 120);
		
		btn[19] = new JButton(trbtn);
		btn[19].setBounds(10+410, 545, 100, 120);
		btn[19].setBorderPainted(false);
		btn[19].setFocusPainted(false);
		btn[19].setContentAreaFilled(false);
		btn[19].addActionListener(this);
		//btn[19].setEnabled(false);
		
		image[61] = new JLabel(btn2);
		image[61].setBounds(535-100, 10+100, 100, 120);
		
		btn[61] = new JButton(trbtn);
		btn[61].setBounds(535-100, 10+100, 100, 120);
		btn[61].setBorderPainted(false);
		btn[61].setFocusPainted(false);
		btn[61].setContentAreaFilled(false);
		btn[61].addActionListener(this);
		//btn[20].setEnabled(false);
		
		image[62] = new JLabel(btn2);
		image[62].setBounds(535-170, 10+180, 100, 120);
		
		btn[62] = new JButton(trbtn);
		btn[62].setBounds(535-170, 10+180, 100, 120);
		btn[62].setBorderPainted(false);
		btn[62].setFocusPainted(false);
		btn[62].setContentAreaFilled(false);
		btn[62].addActionListener(this);
		//btn[21].setEnabled(false);
		
		image[41] = new JLabel(btn2);
		image[41].setBounds(10+100 , 10+100, 100, 120);
		
		btn[41] = new JButton(trbtn);
		btn[41].setBounds(10+100 , 10+100, 100, 120);
		btn[41].setBorderPainted(false);
		btn[41].setFocusPainted(false);
		btn[41].setContentAreaFilled(false);
		btn[41].addActionListener(this);
		//btn[22].setEnabled(false);
		
		image[42] = new JLabel(btn2);
		image[42].setBounds(10+170 , 10+180, 100, 120);
		
		btn[42] = new JButton(trbtn);
		btn[42].setBounds(10+170 , 10+180, 100, 120);
		btn[42].setBorderPainted(false);
		btn[42].setFocusPainted(false);
		btn[42].setContentAreaFilled(false);
		btn[42].addActionListener(this);
		//btn[23].setEnabled(false);
		
		image[65] = new JLabel(btn2);
		image[65].setBounds(10+100 , 545-100, 100, 120);
		
		btn[65] = new JButton(trbtn);
		btn[65].setBounds(10+100 , 545-100, 100, 120);
		btn[65].setBorderPainted(false);
		btn[65].setFocusPainted(false);
		btn[65].setContentAreaFilled(false);
		btn[65].addActionListener(this);
		//btn[24].setEnabled(false);
		
		image[64] = new JLabel(btn2);
		image[64].setBounds(10+170 , 545-180, 100, 120);
		
		btn[64] = new JButton(trbtn);
		btn[64].setBounds(10+170 , 545-180, 100, 120);
		btn[64].setBorderPainted(false);
		btn[64].setFocusPainted(false);
		btn[64].setContentAreaFilled(false);
		btn[64].addActionListener(this);
		//btn[25].setEnabled(false);
		
		image[45] = new JLabel(btn2);
		image[45].setBounds(535-100, 545-100, 100, 120);
		
		btn[45] = new JButton(trbtn);
		btn[45].setBounds(535-100, 545-100, 100, 120);
		btn[45].setBorderPainted(false);
		btn[45].setFocusPainted(false);
		btn[45].setContentAreaFilled(false);
		btn[45].addActionListener(this);
		//btn[26].setEnabled(false);

		image[44] = new JLabel(btn2);
		image[44].setBounds(535-170, 545-180, 100, 120);
		
		btn[44] = new JButton(trbtn);
		btn[44].setBounds(535-170, 545-180, 100, 120);
		btn[44].setBorderPainted(false);
		btn[44].setFocusPainted(false);
		btn[44].setContentAreaFilled(false);
		btn[44].addActionListener(this);
		//btn[27].setEnabled(false);
		
		image[43] = new JLabel(btn1);
		image[43].setBounds(272, 272, 100, 120);
		
		btn[43] = new JButton(trbtn);
		btn[43].setBounds(272, 272, 100, 120);
		btn[43].setBorderPainted(false);
		btn[43].setFocusPainted(false);
		btn[43].setContentAreaFilled(false);
		btn[43].addActionListener(this);
		//cen.setEnabled(false);
		
		int i;
		
		
		for(i=0; i<71; i++) {
			pan.add(btn[i]);
			ch1[i] = false;
			ch2[i] = false;
		}
		
		for(i=0; i<71; i++) {
			pan.add(image[i]);
		}
		
		for(i=0; i<2; i++) {
			P1[i] = new Malll();
			P2[i] = new Malll();
		}
		
		comp.add(pan);
		comp.add(btnPanel);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Scanner keyboard = new Scanner(System.in);
		String actionCommand = e.getActionCommand();
		
		String currentProjPath = "";
		
		try {
			currentProjPath = new File(".").getCanonicalPath();
		}catch (IOException E) {
			E.printStackTrace();
		}
		
		if(actionCommand.equals("click") && throwY==false) {//윷 던져서 위치 받아오기, throwY : throw 된 상태인지 확인(true이면 click으로 윷 던지기 불가능, 이동 후 false로 변경)
			
			if (player1 == true) { //플레이어 1 턴이면
			//	p1rd = y.yutDun(); //p1rd : 윷 던져서 숫자 받아옴
		//		System.out.println("윷 : " + p1rd);
			//	if (p1rd == 4 || p1rd == 5) chance1 = true;
				int testnum=0;
				/*
				 * if(testnum==4||testnum==5) { chance1=true; }
				 */
				
				if(p1rd.size()>=1) {
					if(chance1) {
						testnum=y.yutDun();
						p1rd.add(testnum);
						System.out.println("p1윷: "+p1rd);
						if(testnum<4)
							chance1=false;
					}
				}
				else {
					testnum=y.yutDun();
					p1rd.add(testnum);
					System.out.println("p1윷 : " + p1rd);
				}
				
				if(testnum<=3) {
					throwY = true;
					chance1=false;
				System.out.println("P1. Which mall : ");
				mall = keyboard.nextInt(); // 말 선택
				
				if(mall == 1) {
					System.out.println("p1이동하고 싶은 칸 수 위치"+p1rd);
					num1=keyboard.nextInt();
					num1=num1-1;
					bfmv1[0] = P1[0].getLocation();
					afmv1[0] = P1[0].preview(p1rd.get(num1));
				}
				else if(mall == 2) {
					System.out.println("p1이동하고 싶은 칸 수 위치"+p1rd);
					num1=keyboard.nextInt();
					num1=num1-1;
					bfmv1[1] = P1[1].getLocation();
					afmv1[1] = P1[1].preview(p1rd.get(num1));
				}
				
				if(mall == 1 && afmv1[0]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch1[afmv1[0]] = true;
					ch1[bfmv1[0]] = false;
					
					
					if(afmv1[0]==0 || afmv1[0]==60 || afmv1[0]==40|| afmv1[0]==15|| afmv1[0]==43) {
						image[afmv1[0]].setIcon(bgCh);
					}
					else image[afmv1[0]].setIcon(btnch);
					//위 코드는 이동 가능한 칸의 색 변화를 시행함
				}
				
				if(mall == 2 && afmv1[1]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch1[afmv1[1]] = true;
					ch1[bfmv1[1]] = false;
					
					
					if(afmv1[1]==0 || afmv1[1]==60 || afmv1[1]==40|| afmv1[1]==15|| afmv1[1]==43) {
						image[afmv1[1]].setIcon(bgCh);
					}
					else image[afmv1[1]].setIcon(btnch);
					//위 코드는 이동 가능한 칸의 색 변화를 시행함
				}
				
				else if((mall == 1 && afmv1[0]<0) || (mall == 2 && afmv1[1]<0)){
					throwY = false;
					JOptionPane.showMessageDialog(null, "You cannot move", "", JOptionPane.INFORMATION_MESSAGE);
					p1rd.remove(num1);
					player1 = false;
					player2 = true;
					
				}
			}
			}
			
			else if (player2 == true) { //플레이어 2 턴이면
				int testnum2=0;				
				/*
				 * if(testnum2==4 || testnum2==5) { chance2 = true; }
				 */
				
				if(p2rd.size()>=1) {
					if(chance2) {
						testnum2=y.yutDun();
						p2rd.add(testnum2);
						System.out.println("p2윷 : " + p2rd);
						if(testnum2<4)
							chance2=false;
					}
				}
				else {
					testnum2=y.yutDun();
					p2rd.add(testnum2);
					System.out.println("p2윷 : " + p2rd);
				}
				
				if(testnum2<=3) {
				//		p2rd = y.yutDun(); //p1rd : 윷 던져서 숫자 받아옴
				throwY = true;
				chance2=false;
		//		System.out.println("윷 : " + p2rd);
		//		if (p2rd == 4 || p2rd == 5) chance2 = true;
				
				System.out.println("P2. Which mall : ");
				mall = keyboard.nextInt(); // 말 선택
				
				if(mall == 1) {
					System.out.println("p2이동하고 싶은 칸 수 위치"+p2rd);
					num2=keyboard.nextInt();
					num2=num2-1;
					
					bfmv2[0] = P2[0].getLocation();
					afmv2[0] = P2[0].preview(p2rd.get(num2));
				}
				else if(mall == 2) {
					System.out.println("p2이동하고 싶은 칸 수 위치"+p2rd);
					num2=keyboard.nextInt();
					num2=num2-1;
					bfmv2[1] = P2[1].getLocation();
					afmv2[1] = P2[1].preview(p2rd.get(num2));
				}
				
				if(mall == 1 && afmv2[0]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch2[afmv2[0]] = true;
					ch2[bfmv2[0]] = false;
					
					
					if(afmv2[0]==0 || afmv2[0]==60 || afmv2[0]==40|| afmv2[0]==15|| afmv2[0]==43) {
						image[afmv2[0]].setIcon(bgCh);
					}
					else image[afmv2[0]].setIcon(btnch);
					//위 코드는 이동 가능한 칸의 색 변화를 시행함
				}
				
				if(mall == 2 && afmv2[1]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch2[afmv2[1]] = true;
					ch2[bfmv2[1]] = false;
					
					
					if(afmv2[1]==0 || afmv2[1]==60 || afmv2[1]==40|| afmv2[1]==15|| afmv2[1]==43) {
						image[afmv2[1]].setIcon(bgCh);
					}
					else image[afmv2[1]].setIcon(btnch);
					//위 코드는 이동 가능한 칸의 색 변화를 시행함
				}
				
				else if((mall == 1 && afmv2[0]<0) || (mall == 2 && afmv2[1]<0)){
					throwY = false;
					JOptionPane.showMessageDialog(null, "You cannot move", "", JOptionPane.INFORMATION_MESSAGE);
					p2rd.remove(num2);
					player1 = true;
					player2 = false;
					
				}
			}
		
			}
			}
		
		
		for(int i = 0; i<71; i++) { //이동
			if(player1 == true) {//플레이어 1의 차례
				if(e.getSource() == btn[i] && ch1[i] == true && throwY == true) { //버튼(칸)이 눌렸음 && 칸에 말 존재 && 윷 던져진 상태
					throwY = false; //다음 턴에서 윷을 던질 수 있도록 함
					
					if(mall==1 && bfmv1[0]==0) {
						if(afmv1[0]==0 || afmv1[0]==60 || afmv1[0]==40|| afmv1[0]==15|| afmv1[0]==43) {
							image[afmv1[0]].setIcon(bgEx);
						}
						else image[afmv1[0]].setIcon(exist);
						//이동 후, 1번 말이 위치한 곳의 아이콘을 바꾸기 위함
						
						P1[0].setLocation(p1rd.get(num1)); //1번 말 이동한 위치 저장
						System.out.println("location : " + P1[0].getLocation());
					}
					
					else if(mall==2 && bfmv1[0]==0) {
						if(afmv1[1]==0 || afmv1[1]==60 || afmv1[1]==40|| afmv1[1]==15|| afmv1[1]==43) {
							image[afmv1[1]].setIcon(bgEx);
						}
						else image[afmv1[1]].setIcon(exist);
						
						P1[1].setLocation(p1rd.get(num1));
						System.out.println("location : " + P1[1].getLocation());
					}
					else for(int j = 0; j<2; j++) { // 업기
						loc = P1[j].getLocation() + p1rd.get(num1);
						
						if(loc==5) loc = 60;
						else if(loc==10) loc = 40;
						else if(loc==63) loc = 43;
						else if(loc>=66) loc -= 51;
						
						System.out.println("i ; " + i + "  P1[j].getLocation() + p1rd ; " + loc);
						if(i == loc) {
							if(loc==0 || loc==60 || loc==40|| loc==15|| loc==43) {
								image[loc].setIcon(bgEx);
							}
							else image[loc].setIcon(exist);
							//이동 후, 1번 말이 위치한 곳의 아이콘을 바꾸기 위함
							
							P1[j].setLocation(p1rd.get(num1)); //1번 말 이동한 위치 저장
							System.out.println("location : " + P1[j].getLocation());
						}
					}
					
					if(ch2[i]==true) {
						for(int j=0; j<2; j++) {
							if(i == P2[j].getLocation()) {
								P2[j].goStart();
							}
						}
					//	p1rd.remove(num1);
						JOptionPane.showMessageDialog(null, "You catch the Player2's mal", "Catch", JOptionPane.INFORMATION_MESSAGE);
						ch2[i]=false;
						chance1 =true;
					}
					
					for(int j = 0; j<71; j++) {
						if(ch1[j] == false && ch2[j] == false) { //말이 없는 칸을 찾아서 버튼 변경
							if(j==0 || j==60 || j== 40 || j==15 || j==43 ) {
								image[j].setIcon(btn1);
							}
							else image[j].setIcon(btn2);
						}
						if(ch1[j] == true && ch2[j] == false) {
							if(j==0 || j==60 || j== 40 || j==15 || j==43 ) {
								image[j].setIcon(bgEx);
							}
							else image[j].setIcon(exist);
						}
						else if(ch1[j] == false && ch2[j] == true) {
							if(j==0 || j==60 || j== 40 || j==15 || j==43 ) {
								image[j].setIcon(bgEx2);
							}
							else image[j].setIcon(exist2);
						}
					}
					p1rd.remove(num1);
					System.out.println(p1rd.size());
					if(0!=p1rd.size()) 
						chance1=true;
				//	else
				//		chance1=false;
					if(chance1) {
						player1 = true;
						player2 = false;
						chance1 = false;
					}
					else {
						player1 = false;
						player2 = true;
						chance1=true;
					}
				}
			}
				
			else if(player2 == true) {//플레이어 1의 차례
				if(e.getSource() == btn[i] && ch2[i] == true && throwY == true) { //버튼(칸)이 눌렸음 && 칸에 말 존재 && 윷 던져진 상태
					throwY = false; //다음 턴에서 윷을 던질 수 있도록 함
					
					if(mall==1 && bfmv2[0]==0) {
						if(afmv2[0]==0 || afmv2[0]==60 || afmv2[0]==40|| afmv2[0]==15|| afmv2[0]==43) {
							image[afmv2[0]].setIcon(bgEx2);
						}
						else image[afmv2[0]].setIcon(exist2);
						//이동 후, 1번 말이 위치한 곳의 아이콘을 바꾸기 위함
						
						P2[0].setLocation(p2rd.get(num2)); //1번 말 이동한 위치 저장
						System.out.println("location : " + P2[0].getLocation());
					}
					else if(mall==2 && bfmv2[1]==0) {
						if(afmv2[1]==0 || afmv2[1]==60 || afmv2[1]==40|| afmv2[1]==15|| afmv2[1]==43) {
							image[afmv2[1]].setIcon(bgEx2);
						}
						else image[afmv2[1]].setIcon(exist2);
						
						P2[1].setLocation(p2rd.get(num2));
						System.out.println("location : " + P2[1].getLocation());
					}
					else for(int j = 0; j<2; j++) { // 업기
						loc = P2[j].getLocation() + p2rd.get(num2);
						
						if(loc==5) loc = 60;
						else if(loc==10) loc = 40;
						else if(loc==63) loc = 43;
						else if(loc>=66) loc -= 51;
						
						System.out.println("i ; " + i + "  P2[j].getLocation() + p2rd ; " + loc);
						if(i == loc) {
							if(loc==0 || loc==60 || loc==40|| loc==15|| loc==43) {
								image[loc].setIcon(bgEx);
							}
							else image[loc].setIcon(exist);
							//이동 후, 1번 말이 위치한 곳의 아이콘을 바꾸기 위함
							
							P2[j].setLocation(p2rd.get(num2)); //1번 말 이동한 위치 저장
							System.out.println("location : " + P2[j].getLocation());
						}
					}
					
					if(ch1[i]==true) {
						for(int j=0; j<2; j++) {
							if(i == P1[j].getLocation()) {
								P1[j].goStart();
							}
						}
						p2rd.remove(num2);
						JOptionPane.showMessageDialog(null, "You catch the Player1's mal", "Catch", JOptionPane.INFORMATION_MESSAGE);
						ch1[i]=false;
						chance2 =true;
					}
					
					for(int j = 0; j<71; j++) {
						if(ch1[j] == false && ch2[j] == false) { //말이 없는 칸을 찾아서 버튼 변경
							if(j==0 || j==60 || j== 40 || j==15 || j==43 ) {
								image[j].setIcon(btn1);
							}
							else image[j].setIcon(btn2);
						}
						if(ch1[j] == true && ch2[j] == false) {
							if(j==0 || j==60 || j== 40 || j==15 || j==43 ) {
								image[j].setIcon(bgEx);
							}
							else image[j].setIcon(exist);
						}
						else if(ch1[j] == false && ch2[j] == true) {
							if(j==0 || j==60 || j== 40 || j==15 || j==43 ) {
								image[j].setIcon(bgEx2);
							}
							else image[j].setIcon(exist2);
						}
					}
					p2rd.remove(num2);
					System.out.println(p2rd.size());
					if(0!=p2rd.size()) 
						chance2=true;
				//	else
				//		chance2=false;
					if(chance2) {
						player2 = true;
						player1 = false;
						chance2 = false;
					}
					else {
						player2 = false;
						player1 = true;
						chance2=true;
					}
				}
			}
		}
	}	
}

