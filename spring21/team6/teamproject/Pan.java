package teamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.Random;

public class Pan extends JFrame implements ActionListener{
	Font font;

	private yut yourYut = new yut();
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 740;
	public static final int POINT_SIZE = 30;
	
	public static final String srcPath = "src";
	public static final String packageName = "teamproject";
	
	private ImageIcon backgroundIcon;
	private JLabel background;
	
	private boolean[] checkr = new boolean[4];
	private boolean[] checky = new boolean[4];
	
	private JLabel resLabel;
	private JLabel yutLabel1 = new JLabel();			
	private JLabel yutLabel2 = new JLabel();			
	private JLabel yutLabel3 = new JLabel();			
	private JLabel yutLabel4 = new JLabel();
	private JLabel quizLabel = new JLabel();
	private JPanel quizPanel = new JPanel();
	
	JFrame quizFrame;
	
	JPanel choose1Panel = new JPanel();
	JPanel choose2Panel = new JPanel();
	JPanel numselectPanel = new JPanel();

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
	
	ImageIcon backdoicon; //윷 한글 이미지
	ImageIcon Doicon;
	ImageIcon geicon;
	ImageIcon gulicon;
	ImageIcon yuticon;
	ImageIcon moicon;
	
	ImageIcon finchange; //날 때 도착지점 변화
	
	JButton back; //이동 범위 선택 패널 버튼
	JButton one;
	JButton two;
	JButton three;
	JButton four;
	JButton five;
	
	JLabel IconCheck1;
	JLabel IconCheck2;
	
	JPanel comp = new JPanel();
	JPanel btnPanel = new JPanel();
	JPanel pan;
	
	/*************판에서 칸 변화*************/
	ImageIcon btn1; 
	ImageIcon btn2;
	ImageIcon btnch;
	ImageIcon trbtn;
	ImageIcon bgCh;
	
	ImageIcon R1exist;
	ImageIcon R2exist;
	ImageIcon R3exist;
	ImageIcon R4exist;
	
	ImageIcon Y1exist;
	ImageIcon Y2exist;
	ImageIcon Y3exist;
	ImageIcon Y4exist;
	
	ImageIcon R1bgexist;
	ImageIcon R2bgexist;
	ImageIcon R3bgexist;
	ImageIcon R4bgexist;
	
	ImageIcon Y1bgexist;
	ImageIcon Y2bgexist;
	ImageIcon Y3bgexist;
	ImageIcon Y4bgexist;
	/************************************/
	
	/*************플레이어 패널*************/
	JLabel []mallLabel2 = new JLabel[4];
	JLabel []mallLabel = new JLabel[4];
	
	ImageIcon redf1;
	ImageIcon redf2;
	ImageIcon redf3;
	ImageIcon redf4;
	
	ImageIcon yelf1;
	ImageIcon yelf2;
	ImageIcon yelf3;
	ImageIcon yelf4;
	
	JButton redmall1Button;
	JButton redmall2Button;
	JButton redmall3Button;
	JButton redmall4Button;
	
	JButton yellowMall1Button;
	JButton yellowMall2Button;
	JButton yellowMall3Button;
	JButton yellowMall4Button;
	
	ImageIcon[] red = new ImageIcon[4];
	ImageIcon[] yellow = new ImageIcon[4];
	
	ImageIcon greenIcon;
	ImageIcon questionicon;
	
	ImageIcon redmall1;
	ImageIcon redmall2;
	ImageIcon redmall3;
	ImageIcon redmall4;
	
	ImageIcon yellowmall1;
	ImageIcon yellowmall2;
	ImageIcon yellowmall3;
	ImageIcon yellowmall4;
	/***********************************/
	
	JButton[] btn = new JButton[71];
	JLabel[] image = new JLabel[71];
	
	private boolean[] rclick = new boolean[4]; //정보 전달용 변수
	private boolean[] yclick = new boolean[4];
	
	private Malll[] P1 = new Malll[4];
	private Malll[] P2 = new Malll[4];
	
	private boolean player1 = true;
	private boolean player2 = false;
	
	private boolean chance1 = false; //상대 말 잡은 경우
	private boolean chance2 = false;
	
	private int p1cnt = 0;
	private int p2cnt = 0;
	
	private int num1;
	private int num2;
	
	private ArrayList<Integer> p1rd=new ArrayList<Integer>();
	private ArrayList<Integer> p2rd=new ArrayList<Integer>();
	
	private int[] checkNum1=new int[4]; 	
	private int[] checkNum2=new int[4];
	private int[] midcheck1=new int[4];
	private int[] midcheck2=new int[4];
	
	String [] quiz = new String[3];//quiz관련
	String [] choice1 = new String[3];
	String [] choice2 = new String[3];
	String [] choice3 = new String[3];
	String [] answer = new String[3];
	private int randomChoice = 0;
	private int gameChanger = 0;
	private Font fontObj = new Font("Serif", Font.PLAIN|Font.BOLD, 30);
	private String Quiz = "";
	private String theText = "뉴질랜드의 수도는?, 웰링턴, 오클랜드, 더니든" ; 
	String filename = "./src/teamproject/" + "quiz.txt";
	public static final int WIDTH2 = 600;
	public static final int HEIGHT2 =400;
	
	
	private int mall;
	
	private int[] bfmv1 = new int[4]; //before move
	private int[] bfmv2 = new int[4];
	
	private int[] afmv1 = new int[4]; //after move
	private int[] afmv2 = new int[4];
	
	private int loc;
	
	boolean[] ch1 = new boolean[71]; //클릭 될 칸 및 말의 존재여부 표시 (정보 저장용 변수)
	boolean[] ch2 = new boolean[71];

	boolean throwY = false; //윷이 던져질 차례인지
	
	public static void main(String[] args) {
		Pan panGui = new Pan();
		panGui.comp.setVisible(true);
	}
	
	public Pan() {
		font = new Font("돋움", font.BOLD, 11);
		Font font2 = new Font("돋움", font.BOLD, 15);
		
		comp.setSize(WIDTH, HEIGHT);
		comp.setBackground(Color.WHITE);
		comp.setLayout(null);
		
		pan = new JPanel(); 
		pan.setLayout(null);
		pan.setBounds(25,0,700,720);
		
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
		String bigCh = "bigchange.png";
		
		String r1exist = "existr1.png";
		String r2exist = "existr2.png";
		String r3exist = "existr3.png";
		String r4exist = "existr4.png";
		
		String y1exist = "existy1.png";
		String y2exist = "existy2.png";
		String y3exist = "existy3.png";
		String y4exist = "existy4.png";
		
		String r1bgexist = "bigexistr1.png";
		String r2bgexist = "bigexistr2.png";
		String r3bgexist = "bigexistr3.png";
		String r4bgexist = "bigexistr4.png";
		
		String y1bgexist = "bigexisty1.png";
		String y2bgexist = "bigexisty2.png";
		String y3bgexist = "bigexisty3.png";
		String y4bgexist = "bigexisty4.png";
		
		btn1 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+button1);
		btn2 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+button2);
		btnch = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+btonCh);
		trbtn = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+button);
		bgCh = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+bigCh);
		
		R1exist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+r1exist);
		R2exist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+r2exist);
		R3exist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+r3exist);
		R4exist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+r4exist);
		
		Y1exist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+y1exist);
		Y2exist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+y2exist);
		Y3exist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+y3exist);
		Y4exist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+y4exist);
		
		R1bgexist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+r1bgexist);
		R2bgexist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+r2bgexist);
		R3bgexist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+r3bgexist);
		R4bgexist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+r4bgexist);
		
		Y1bgexist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+y1bgexist);
		Y2bgexist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+y2bgexist);
		Y3bgexist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+y3bgexist);
		Y4bgexist = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+y4bgexist);
		
		redf1 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "red1f.png");
		redf2 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "red2f.png");
		redf3 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "red3f.png");
		redf4 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "red4f.png");
		
		yelf1 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "yellow1f.png");
		yelf2 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "yellow2f.png");
		yelf3 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "yellow3f.png");
		yelf4 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "yellow4f.png");
		
		finchange = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+"finchange.png");
		
		backdoicon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "빽도.png");
		Doicon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "도.png");
		geicon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "개.png");
		gulicon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "걸.png");
		yuticon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "윷.png");
		moicon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "모.png");
		
		backgroundIcon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "background.png");
		background = new JLabel(backgroundIcon);
		
		background.setBounds(0,-160,1000,1000);
		
		
		
		for(int i=0; i<4; i++) {
			rclick[i] = false;
			yclick[i] = false;
		}
		
		for(int i=0;i<4;i++) {
			P1[i] = new Malll();
			P2[i] = new Malll();
		}
		
		for(int i=0;i<71;i++) {
			image[i]=new JLabel(btn1);
			btn[i]=new JButton(trbtn);
		}
		
		image[46] = new JLabel(btn1);
		image[46].setBounds(535, 545, 100, 120);
			
		btn[46] = new JButton(trbtn);
		btn[46].setBounds(535, 545, 100, 120);
		btn[46].setBorderPainted(false);
		btn[46].setFocusPainted(false);
		btn[46].setContentAreaFilled(false);
		btn[46].addActionListener(this);
		
		image[70] = new JLabel(btn1);
		image[70].setBounds(535-100, 272, 100, 120);
			
		btn[70] = new JButton(trbtn);
		btn[70].setBounds(535-100, 272, 100, 120);
		btn[70].setBorderPainted(false);
		btn[70].setFocusPainted(false);
		btn[70].setContentAreaFilled(false);
		btn[70].addActionListener(this);
		
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
		
		image[3] = new JLabel(btn2);
		image[3].setBounds(535, 545-325, 100, 120);
		
		btn[3] = new JButton(trbtn);
		btn[3].setBounds(535, 545-325, 100, 120);
		btn[3].setBorderPainted(false);
		btn[3].setFocusPainted(false);
		btn[3].setContentAreaFilled(false);
		btn[3].addActionListener(this);
		
		image[4] = new JLabel(btn2);
		image[4].setBounds(535, 545-425, 100, 120);
		
		btn[4] = new JButton(trbtn);
		btn[4].setBounds(535, 545-425, 100, 120);
		btn[4].setBorderPainted(false);
		btn[4].setFocusPainted(false);
		btn[4].setContentAreaFilled(false);
		btn[4].addActionListener(this);
		
		image[60] = new JLabel(btn1);
		image[60].setBounds(535, 10, 100, 120);
		
		btn[60] = new JButton(trbtn);
		btn[60].setBounds(535, 10, 100, 120);
		btn[60].setBorderPainted(false);
		btn[60].setFocusPainted(false);
		btn[60].setContentAreaFilled(false);
		btn[60].addActionListener(this);

		image[6] = new JLabel(btn2);
		image[6].setBounds(535-120, 10, 100, 120);
		
		btn[6] = new JButton(trbtn);
		btn[6].setBounds(535-120, 10, 100, 120);
		btn[6].setBorderPainted(false);
		btn[6].setFocusPainted(false);
		btn[6].setContentAreaFilled(false);
		btn[6].addActionListener(this);
		
		image[7] = new JLabel(btn2);
		image[7].setBounds(545-220, 10, 100, 120);
		
		btn[7] = new JButton(trbtn);
		btn[7].setBounds(545-220, 10, 100, 120);
		btn[7].setBorderPainted(false);
		btn[7].setFocusPainted(false);
		btn[7].setContentAreaFilled(false);
		btn[7].addActionListener(this);
		
		image[8] = new JLabel(btn2);
		image[8].setBounds(545-320, 10, 100, 120);
		
		btn[8] = new JButton(trbtn);
		btn[8].setBounds(545-320, 10, 100, 120);
		btn[8].setBorderPainted(false);
		btn[8].setFocusPainted(false);
		btn[8].setContentAreaFilled(false);
		btn[8].addActionListener(this);
		
		image[9] = new JLabel(btn2);
		image[9].setBounds(545-420, 10, 100, 120);
		
		btn[9] = new JButton(trbtn);
		btn[9].setBounds(545-420, 10, 100, 120);
		btn[9].setBorderPainted(false);
		btn[9].setFocusPainted(false);
		btn[9].setContentAreaFilled(false);
		btn[9].addActionListener(this);
		
		image[40] = new JLabel(btn1);
		image[40].setBounds(10 , 10, 100, 120);
		
		btn[40] = new JButton(trbtn);
		btn[40].setBounds(10 , 10, 100, 120);
		btn[40].setBorderPainted(false);
		btn[40].setFocusPainted(false);
		btn[40].setContentAreaFilled(false);
		btn[40].addActionListener(this);
		
		image[11] = new JLabel(btn2);
		image[11].setBounds(10, 10+110, 100, 120);
		
		btn[11] = new JButton(trbtn);
		btn[11].setBounds(10, 10+110, 100, 120);
		btn[11].setBorderPainted(false);
		btn[11].setFocusPainted(false);
		btn[11].setContentAreaFilled(false);
		btn[11].addActionListener(this);
		
		image[12] = new JLabel(btn2);
		image[12].setBounds(10, 10+210, 100, 120);
		
		btn[12] = new JButton(trbtn);
		btn[12].setBounds(10, 10+210, 100, 120);
		btn[12].setBorderPainted(false);
		btn[12].setFocusPainted(false);
		btn[12].setContentAreaFilled(false);
		btn[12].addActionListener(this);
		
		image[13] = new JLabel(btn2);
		image[13].setBounds(10, 10+310, 100, 120);
		
		btn[13] = new JButton(trbtn);
		btn[13].setBounds(10, 10+310, 100, 120);
		btn[13].setBorderPainted(false);
		btn[13].setFocusPainted(false);
		btn[13].setContentAreaFilled(false);
		btn[13].addActionListener(this);
		
		image[14] = new JLabel(btn2);
		image[14].setBounds(10, 10+410, 100, 120);
		
		btn[14] = new JButton(trbtn);
		btn[14].setBounds(10, 10+410, 100, 120);
		btn[14].setBorderPainted(false);
		btn[14].setFocusPainted(false);
		btn[14].setContentAreaFilled(false);
		btn[14].addActionListener(this);
		
		image[15] = new JLabel(btn1);
		image[15].setBounds(10 , 545, 100, 120);
		
		btn[15] = new JButton(trbtn);
		btn[15].setBounds(10 , 545, 100, 120);
		btn[15].setBorderPainted(false);
		btn[15].setFocusPainted(false);
		btn[15].setContentAreaFilled(false);
		btn[15].addActionListener(this);
		
		image[16] = new JLabel(btn2);
		image[16].setBounds(10+110, 545, 100, 120);
		
		btn[16] = new JButton(trbtn);
		btn[16].setBounds(10+110, 545, 100, 120);
		btn[16].setBorderPainted(false);
		btn[16].setFocusPainted(false);
		btn[16].setContentAreaFilled(false);
		btn[16].addActionListener(this);
		
		image[17] = new JLabel(btn2);
		image[17].setBounds(10+210, 545, 100, 120);
		
		btn[17] = new JButton(trbtn);
		btn[17].setBounds(10+210, 545, 100, 120);
		btn[17].setBorderPainted(false);
		btn[17].setFocusPainted(false);
		btn[17].setContentAreaFilled(false);
		btn[17].addActionListener(this);
		
		image[18] = new JLabel(btn2);
		image[18].setBounds(10+310, 545, 100, 120);
		
		btn[18] = new JButton(trbtn);
		btn[18].setBounds(10+310, 545, 100, 120);
		btn[18].setBorderPainted(false);
		btn[18].setFocusPainted(false);
		btn[18].setContentAreaFilled(false);
		btn[18].addActionListener(this);
		
		image[19] = new JLabel(btn2);
		image[19].setBounds(10+410, 545, 100, 120);
		
		btn[19] = new JButton(trbtn);
		btn[19].setBounds(10+410, 545, 100, 120);
		btn[19].setBorderPainted(false);
		btn[19].setFocusPainted(false);
		btn[19].setContentAreaFilled(false);
		btn[19].addActionListener(this);
		
		image[61] = new JLabel(btn2);
		image[61].setBounds(535-100, 10+100, 100, 120);
		
		btn[61] = new JButton(trbtn);
		btn[61].setBounds(535-100, 10+100, 100, 120);
		btn[61].setBorderPainted(false);
		btn[61].setFocusPainted(false);
		btn[61].setContentAreaFilled(false);
		btn[61].addActionListener(this);
		
		image[62] = new JLabel(btn2);
		image[62].setBounds(535-170, 10+180, 100, 120);
		
		btn[62] = new JButton(trbtn);
		btn[62].setBounds(535-170, 10+180, 100, 120);
		btn[62].setBorderPainted(false);
		btn[62].setFocusPainted(false);
		btn[62].setContentAreaFilled(false);
		btn[62].addActionListener(this);
		
		image[41] = new JLabel(btn2);
		image[41].setBounds(10+100 , 10+100, 100, 120);
		
		btn[41] = new JButton(trbtn);
		btn[41].setBounds(10+100 , 10+100, 100, 120);
		btn[41].setBorderPainted(false);
		btn[41].setFocusPainted(false);
		btn[41].setContentAreaFilled(false);
		btn[41].addActionListener(this);
		
		image[42] = new JLabel(btn2);
		image[42].setBounds(10+170 , 10+180, 100, 120);
		
		btn[42] = new JButton(trbtn);
		btn[42].setBounds(10+170 , 10+180, 100, 120);
		btn[42].setBorderPainted(false);
		btn[42].setFocusPainted(false);
		btn[42].setContentAreaFilled(false);
		btn[42].addActionListener(this);
		
		image[65] = new JLabel(btn2);
		image[65].setBounds(10+100 , 545-100, 100, 120);
		
		btn[65] = new JButton(trbtn);
		btn[65].setBounds(10+100 , 545-100, 100, 120);
		btn[65].setBorderPainted(false);
		btn[65].setFocusPainted(false);
		btn[65].setContentAreaFilled(false);
		btn[65].addActionListener(this);
		
		image[64] = new JLabel(btn2);
		image[64].setBounds(10+170 , 545-180, 100, 120);
		
		btn[64] = new JButton(trbtn);
		btn[64].setBounds(10+170 , 545-180, 100, 120);
		btn[64].setBorderPainted(false);
		btn[64].setFocusPainted(false);
		btn[64].setContentAreaFilled(false);
		btn[64].addActionListener(this);
		
		image[45] = new JLabel(btn2);
		image[45].setBounds(535-100, 545-100, 100, 120);
		
		btn[45] = new JButton(trbtn);
		btn[45].setBounds(535-100, 545-100, 100, 120);
		btn[45].setBorderPainted(false);
		btn[45].setFocusPainted(false);
		btn[45].setContentAreaFilled(false);
		btn[45].addActionListener(this);

		image[44] = new JLabel(btn2);
		image[44].setBounds(535-170, 545-180, 100, 120);
		
		btn[44] = new JButton(trbtn);
		btn[44].setBounds(535-170, 545-180, 100, 120);
		btn[44].setBorderPainted(false);
		btn[44].setFocusPainted(false);
		btn[44].setContentAreaFilled(false);
		btn[44].addActionListener(this);
		
		image[43] = new JLabel(btn1);
		image[43].setBounds(272, 272, 100, 120);
		
		btn[43] = new JButton(trbtn);
		btn[43].setBounds(272, 272, 100, 120);
		btn[43].setBorderPainted(false);
		btn[43].setFocusPainted(false);
		btn[43].setContentAreaFilled(false);
		btn[43].addActionListener(this);
		
		int i;

		for(i=0; i<71; i++) { //변수 설정
			btn[i].setEnabled(false);
			pan.add(btn[i]);
			ch1[i] = false;
			ch2[i] = false;
		}
		
		for(i=0; i<71; i++) {
			pan.add(image[i]);
		}
		/*****************************************************************/
		
		String redmallbutton1 = "red1.png";
		String redmallbutton2 = "red2.png";
		String redmallbutton3 = "red3.png";
		String redmallbutton4 = "red4.png";
		
		redmall1 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+redmallbutton1);
		redmall2 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+redmallbutton2);
		redmall3 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+redmallbutton3);
		redmall4 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+redmallbutton4);
		
		choose1Panel.setLayout(null);
		choose1Panel.setBounds(160, 310, 400, 122);
		choose1Panel.setOpaque(false);
		
		String backpanel1name = "backmall.png";
		ImageIcon backpanel1icon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+backpanel1name);
		JLabel backpanel1 = new JLabel(backpanel1icon);
		backpanel1.setBounds(0,0,400,122);
		
		redmall1Button = new JButton();
		redmall1Button.setBounds(0,30,100,100);
		redmall1Button.setBorderPainted(false);
		redmall1Button.setFocusPainted(false);
		redmall1Button.setContentAreaFilled(false);		
		redmall1Button.addActionListener(this);
		redmall1Button.setIcon(redmall1);
		
		redmall2Button = new JButton();
		redmall2Button.setBounds(100,30,100,100);
		redmall2Button.setBorderPainted(false);
		redmall2Button.setFocusPainted(false);
		redmall2Button.setContentAreaFilled(false);		
		redmall2Button.addActionListener(this);		
		redmall2Button.setIcon(redmall2);
		
		redmall3Button = new JButton();
		redmall3Button.setBounds(200,30,100,100);
		redmall3Button.setBorderPainted(false);
		redmall3Button.setFocusPainted(false);
		redmall3Button.setContentAreaFilled(false);		
		redmall3Button.addActionListener(this);		
		redmall3Button.setIcon(redmall3);
		
		redmall4Button = new JButton();
		redmall4Button.setBounds(300,30,100,100);
		redmall4Button.setBorderPainted(false);
		redmall4Button.setFocusPainted(false);
		redmall4Button.setContentAreaFilled(false);		
		redmall4Button.addActionListener(this);		
		redmall4Button.setIcon(redmall4);	
		
		choose1Panel.add(redmall1Button);		
		choose1Panel.add(redmall2Button);		
		choose1Panel.add(redmall3Button);		
		choose1Panel.add(redmall4Button);
		choose1Panel.add(backpanel1);
		
		choose1Panel.setVisible(false);
		/**********/
		String yellowmallbutton1 = "yellow1.png";
		String yellowmallbutton2 = "yellow2.png";
		String yellowmallbutton3 = "yellow3.png";
		String yellowmallbutton4 = "yellow4.png";
			
		yellowmall1 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+ "/" + yellowmallbutton1);
		yellowmall2 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+ "/" + yellowmallbutton2);
		yellowmall3 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+ "/" + yellowmallbutton3);
		yellowmall4 = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+ "/" + yellowmallbutton4);
				
		choose2Panel.setVisible(false);
		choose2Panel.setLayout(null);
		choose2Panel.setBounds(160, 310, 400, 122);
		choose2Panel.setOpaque(false);
		
		JLabel backpanel1_2 = new JLabel(backpanel1icon);
		backpanel1_2.setBounds(0,0,400,122);
				
		yellowMall1Button = new JButton();
		yellowMall1Button.setBounds(0,30,100,100);
		yellowMall1Button.setBorderPainted(false);
		yellowMall1Button.setFocusPainted(false);
		yellowMall1Button.setContentAreaFilled(false);		
		yellowMall1Button.addActionListener(this);
		yellowMall1Button.setIcon(yellowmall1);
			
		yellowMall2Button = new JButton();
		yellowMall2Button.setBounds(100,30,100,100);
		yellowMall2Button.setBorderPainted(false);
		yellowMall2Button.setFocusPainted(false);
		yellowMall2Button.setContentAreaFilled(false);		
		yellowMall2Button.addActionListener(this);			
		yellowMall2Button.setIcon(yellowmall2);
				
		yellowMall3Button = new JButton();
		yellowMall3Button.setBounds(200,30,100,100);
		yellowMall3Button.setBorderPainted(false);
		yellowMall3Button.setFocusPainted(false);
		yellowMall3Button.setContentAreaFilled(false);		
		yellowMall3Button.addActionListener(this);				
		yellowMall3Button.setIcon(yellowmall3);
				
		yellowMall4Button = new JButton();
		yellowMall4Button.setBounds(300,30,100,100);
		yellowMall4Button.setBorderPainted(false);
		yellowMall4Button.setFocusPainted(false);
		yellowMall4Button.setContentAreaFilled(false);		
		yellowMall4Button.addActionListener(this);				
		yellowMall4Button.setIcon(yellowmall4);
				
		choose2Panel.setBackground(Color.white);
		choose2Panel.add(yellowMall1Button);		
		choose2Panel.add(yellowMall2Button);		
		choose2Panel.add(yellowMall3Button);		
		choose2Panel.add(yellowMall4Button);
		choose2Panel.add(backpanel1_2);
		
		frontYutImage = "yut1.png";
		frontYutPath = currentProjPath + "/" + srcPath + "/" + packageName + "/" + frontYutImage;
		frontYutIcon = new ImageIcon(frontYutPath);
		rearYutImage = "yut2.png";
		rearYutPath = currentProjPath + "/" + srcPath + "/" + packageName + "/" + rearYutImage;
		rearYutIcon = new ImageIcon(rearYutPath);
		backDoImage = "yut3.png";
		backDoPath = currentProjPath + "/" + srcPath + "/" + packageName + "/" + backDoImage;
		backDoIcon = new ImageIcon(backDoPath);
		
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setBounds(0,0,720,720);
		add(gamePanel);
		/**************************************************************************************/
		//너비 280, 높이 720 , 그중에서 기본 720에서 더해서 시작하면 되겠다
		//플레이어1,2 남은말과 현재 누구턴인지 표현하는 패널
		PlayerPanel = new JPanel();
		PlayerPanel.setLayout(null);
		PlayerPanel.setBounds(700, 0, 285, 700);				
		
		String greenIconname = "greenicon.png";
		greenIcon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ greenIconname);
		IconCheck1 = new JLabel(greenIcon);
		IconCheck1.setBounds(0, 0, 40, 85);
		
		String player1image = "player1.png";
		ImageIcon player1icon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+ "/" + player1image);
		JLabel CheckLabel = new JLabel(player1icon);
		CheckLabel.setBounds(40, 0, 240, 85);		
		
		PlayerPanel.add(IconCheck1);
		PlayerPanel.add(CheckLabel);
		
		//첫번째 말쪽 시작		
		
		red[0] = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "red1.png");
		red[1] = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "red2.png");
		red[2] = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "red3.png");
		red[3] = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "red4.png");
		
		int interval = 0;		
		for(i = 0; i< 4; i++) {
			mallLabel[i] = new JLabel(red[i]);
			mallLabel[i].setBounds(0+interval, 85, 70, 85);
			PlayerPanel.add(mallLabel[i]);
			interval = interval + 70;
		}	
		
		//두번째 플레이어 이름 표시 시작		
		IconCheck2 = new JLabel(greenIcon);
		IconCheck2.setVisible(false);
		IconCheck2.setBounds(0, 170, 40, 85);
		
		//앞의 코드와 중복되니 나중에 turn을 합칠때 구현을 잘해놓을것
		
		String player2image = "player2.png";
		ImageIcon player2icon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+ "/" + player2image);
		JLabel CheckLabel2 = new JLabel(player2icon);
		CheckLabel2.setBounds(40, 170, 240, 85);
		
		PlayerPanel.add(IconCheck2);
		PlayerPanel.add(CheckLabel2);		
		
		//두번째 플레이어 말 표시 시작				
		
		yellow[0] = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "yellow1.png");
		yellow[1] = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "yellow2.png");
		yellow[2] = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "yellow3.png");
		yellow[3] = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+ "yellow4.png");
		
		int secondInterval = 0;
		
		for(i = 0; i< 4; i++) {
			mallLabel2[i] = new JLabel(yellow[i]);
			mallLabel2[i].setBounds(0+secondInterval, 255, 70, 85);
			PlayerPanel.add(mallLabel2[i]);
			secondInterval = secondInterval + 70;
		}	
		
		yutLabel1.setBounds(0, 340, 70, 270);
		yutLabel2.setBounds(70, 340, 70, 270);
		yutLabel3.setBounds(140, 340, 70, 270);
		yutLabel4.setBounds(210, 340, 70, 270);
		
		yutLabel1.setIcon(frontYutIcon);
		yutLabel2.setIcon(frontYutIcon);
		yutLabel3.setIcon(frontYutIcon);
		yutLabel4.setIcon(frontYutIcon);
				
		PlayerPanel.add(yutLabel1);				
		PlayerPanel.add(yutLabel2);				
		PlayerPanel.add(yutLabel3);				
		PlayerPanel.add(yutLabel4);		
		
		String questionImage = "question.png";
		String questionPath = currentProjPath + "/" + srcPath + "/" + packageName + "/" + questionImage;
		questionicon = new ImageIcon(questionPath);
				
		resLabel = new JLabel(questionicon);
		resLabel.setBounds(-3, 610, 90, 60);
		PlayerPanel.add(resLabel);
		
		String clickimage = "click.png";
		String clickpath = currentProjPath + "/" + srcPath + "/" + packageName + "/" + clickimage;
		ImageIcon Clickicon = new ImageIcon(clickpath);
		
		clickButton = new JButton(Clickicon);
		clickButton.setBounds(65, 610, 240, 60);
		clickButton.addActionListener(this);
		clickButton.setBorderPainted(false);
		clickButton.setFocusPainted(false);
		clickButton.setContentAreaFilled(false);		
		PlayerPanel.add(clickButton);
		
		add(PlayerPanel);
		
		for(int j=0; j<4; j++) {
			checkr[j] = false;
			checky[j] = false;
		}
		
		/**************************************선택 패널*****************************************/
		
		numselectPanel.setBounds(160, 310, 400, 122);
		numselectPanel.setOpaque(false);
		numselectPanel.setLayout(null);
		
		String backpanel2name = "backpanel.png";
		ImageIcon backpanel2icon = new ImageIcon(currentProjPath+"/"+srcPath+"/"+packageName+"/"+backpanel2name);
		JLabel backpanel2 = new JLabel(backpanel2icon);
		backpanel2.setBounds(0,0,400,122);
		
		back = new JButton("빽도");
		back.addActionListener(this);
		back.setBounds(7, 70, 60, 40);
		back.setFont(font);
		back.setEnabled(false);
		back.setBackground(Color.white);
		one = new JButton("도");
		one.addActionListener(this);
		one.setEnabled(false);
		one.setBounds(72, 70, 60, 40);
		one.setBackground(Color.white);
		one.setFont(font2);
		two = new JButton("개");
		two.addActionListener(this);
		two.setEnabled(false);
		two.setBounds(137, 70, 60, 40);
		two.setBackground(Color.white);
		two.setFont(font2);
		three = new JButton("걸");
		three.addActionListener(this);
		three.setEnabled(false);
		three.setBounds(202, 70, 60, 40);
		three.setBackground(Color.white);
		three.setFont(font2);
		four = new JButton("윷");
		four.addActionListener(this);
		four.setEnabled(false);
		four.setBounds(267, 70, 60, 40);
		four.setBackground(Color.white);
		four.setFont(font2);
		five = new JButton("모");
		five.addActionListener(this);
		five.setEnabled(false);
		five.setBounds(332, 70, 60, 40);
		five.setBackground(Color.white);
		five.setFont(font2);
		
		numselectPanel.add(back);
		numselectPanel.add(one);
		numselectPanel.add(two);
		numselectPanel.add(three);
		numselectPanel.add(four);
		numselectPanel.add(five);
		
		numselectPanel.add(backpanel2);
		numselectPanel.setVisible(false);
		
		/*************************************************************************************/
		
		pan.setOpaque(false);
		PlayerPanel.setOpaque(false);
		
		comp.add(numselectPanel);
		comp.add(choose2Panel);
		comp.add(choose1Panel);
		comp.add(pan);
		comp.add(PlayerPanel);
		comp.add(background);
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
		/***************************************윷 던지기********************************************/
		if(e.getSource() == clickButton && throwY == false) {
			System.out.println("click");
			if(player1 == true) {
				System.out.println("p1");
				int testnum = 0;
				
				testnum = yutdun();
				p1rd.add(testnum);
				System.out.println("p1윷: " + p1rd);
				
				if (testnum == 4 || testnum == 5) {
					throwY = false;
				}
				
				else {
					throwY = true;
					choose1Panel.setVisible(true);
					for(int i=0; i<71; i++) {
						btn[i].setEnabled(false);
					}
				}
			}
			
			if(player2 == true) {
				System.out.println("p2");
				int testnum = 0;
				
				testnum = yutdun();
				p2rd.add(testnum);
				System.out.println("p2윷: " + p2rd);
				
				if (testnum == 4 || testnum == 5) {
					throwY = false;
				}
				
				else {
					throwY = true;
					choose2Panel.setVisible(true);
					for(int i=0; i<71; i++) {
						btn[i].setEnabled(false);
					}
				} 
			}
		}
		/**************************************이동할 말 선택*****************************************/
		
		if((e.getSource() == redmall1Button || e.getSource() == redmall2Button || e.getSource() == redmall3Button || e.getSource() == redmall4Button) && throwY == true) {
			if(e.getSource() == redmall1Button) {
				rclick[0] = true;
				mall = 1;
			}
			else if(e.getSource() == redmall2Button) {
				rclick[1] = true;
				mall = 2;
			}
			else if(e.getSource() == redmall3Button) {
				rclick[2] = true;
				mall = 3;
			}
			else if(e.getSource() == redmall4Button) {
				rclick[3] = true;
				mall = 4;
			}
			
			for(int i=0; i<4; i++) {
				System.out.println(rclick[i]);
			}
			
			choose1Panel.setVisible(false);
			
			back.setEnabled(false);
			one.setEnabled(false);
			two.setEnabled(false);
			three.setEnabled(false);
			four.setEnabled(false);
			five.setEnabled(false);
			
			for(int i=0; i<p1rd.size(); i++) {
				if(p1rd.get(i)==-1) back.setEnabled(true);
				else if(p1rd.get(i) == 1) one.setEnabled(true);
				else if(p1rd.get(i) == 2) two.setEnabled(true);
				else if(p1rd.get(i) == 3) three.setEnabled(true);
				else if(p1rd.get(i) == 4) four.setEnabled(true);
				else if(p1rd.get(i) == 5) five.setEnabled(true);
			}
			numselectPanel.setVisible(true);
			//num1 넘기기
		}
		/************/
		if((e.getSource() == yellowMall1Button || e.getSource() == yellowMall2Button || e.getSource() == yellowMall3Button || e.getSource() == yellowMall4Button) && throwY == true) {
			if(e.getSource() == yellowMall1Button) {
				yclick[0] = true;
				mall = 1;
			}
			else if(e.getSource() == yellowMall2Button) {
				yclick[1] = true;
				mall = 2;
			}
			else if(e.getSource() == yellowMall3Button) {
				yclick[2] = true;
				mall = 3;
			}
			else if(e.getSource() == yellowMall4Button) {
				yclick[3] = true;
				mall = 4;
			}
			
			choose2Panel.setVisible(false);
			//mall = 1;
			back.setEnabled(false);
			one.setEnabled(false);
			two.setEnabled(false);
			three.setEnabled(false);
			four.setEnabled(false);
			five.setEnabled(false);
			
			for(int i=0; i<p2rd.size(); i++) {
				if(p2rd.get(i)==-1) back.setEnabled(true);
				else if(p2rd.get(i) == 1) one.setEnabled(true);
				else if(p2rd.get(i) == 2) two.setEnabled(true);
				else if(p2rd.get(i) == 3) three.setEnabled(true);
				else if(p2rd.get(i) == 4) four.setEnabled(true);
				else if(p2rd.get(i) == 5) five.setEnabled(true);
			}
			numselectPanel.setVisible(true);
			//num1 넘기기
		}
		/*****************************************************************************/
		if(actionCommand.equals("빽도") || actionCommand.equals("도") || actionCommand.equals("개") || actionCommand.equals("걸") || actionCommand.equals("윷") || actionCommand.equals("모")) {
			for(int i=0; i<71; i++) btn[i].setEnabled(true);
			numselectPanel.setVisible(false);
			if(player1 == true) {
				if(actionCommand.equals("빽도")) {
					for(int i = 0; i<p1rd.size(); i++) {
						if(p1rd.get(i) == -1) {
							num1 = i;
							break;
						}
					}
				}
				if(actionCommand.equals("도")) {
					for(int i = 0; i<p1rd.size(); i++) {
						if(p1rd.get(i) == 1) {
							num1 = i;
							break;
						}
					}
				}
				if(actionCommand.equals("개")) {
					for(int i = 0; i<p1rd.size(); i++) {
						if(p1rd.get(i) == 2) {
							num1 = i;
							break;
						}
					}
				}
				if(actionCommand.equals("걸")) {
					for(int i = 0; i<p1rd.size(); i++) {
						if(p1rd.get(i) == 3) {
							num1 = i;
							break;
						}
					}
				}
				if(actionCommand.equals("윷")) {
					for(int i = 0; i<p1rd.size(); i++) {
						if(p1rd.get(i) == 4) {
							num1 = i;
							break;
						}
					}
				}
				if(actionCommand.equals("모")) {
					for(int i = 0; i<p1rd.size(); i++) {
						if(p1rd.get(i) == 5) {
							num1 = i;
							break;
						}
					}
				}
			}
			
			if(player2 == true) {
				if(actionCommand.equals("빽도")) {
					for(int i = 0; i<p2rd.size(); i++) {
						if(p2rd.get(i) == -1) {
							num2 = i;
							break;
						}
					}
				}
				if(actionCommand.equals("도")) {
					for(int i = 0; i<p2rd.size(); i++) {
						if(p2rd.get(i) == 1) {
							num2 = i;
							break;
						}
					}
				}
				if(actionCommand.equals("개")) {
					for(int i = 0; i<p2rd.size(); i++) {
						if(p2rd.get(i) == 2) {
							num2 = i;
							break;
						}
					}
				}
				if(actionCommand.equals("걸")) {
					for(int i = 0; i<p2rd.size(); i++) {
						if(p2rd.get(i) == 3) {
							num2 = i;
							break;
						}
					}
				}
				if(actionCommand.equals("윷")) {
					for(int i = 0; i<p2rd.size(); i++) {
						if(p2rd.get(i) == 4) {
							num2 = i;
							break;
						}
					}
				}
				if(actionCommand.equals("모")) {
					for(int i = 0; i<p2rd.size(); i++) {
						if(p2rd.get(i) == 5) {
							num2 = i;
							break;
						}
					}
				}
			}
				
			if(rclick[0] == true && throwY == true) { //말 r1
				rclick[0] = false;
				bfmv1[0] = P1[0].getLocation();
				afmv1[0] = P1[0].preview(p1rd.get(num1));
				
				if(afmv1[0]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch1[bfmv1[0]] = false;
					
					if((20<afmv1[0] && afmv1[0]<40) || (46<afmv1[0]&&afmv1[0]<60)) {
						image[70].setIcon(finchange);
						ch1[70] = true;
					}
					else if(afmv1[0]==46 || afmv1[0]==60 || afmv1[0]==40|| afmv1[0]==15|| afmv1[0]==43) {
						image[afmv1[0]].setIcon(bgCh);
						ch1[afmv1[0]] = true;
					}
					else {
						image[afmv1[0]].setIcon(btnch);
						ch1[afmv1[0]] = true;
					}
					//위 코드는 이동 가능한 칸의 색 변화를 시행함
				}
				
				else if(afmv1[0]<0) {
					throwY = false;
					JOptionPane.showMessageDialog(null, "You cannot move", "", JOptionPane.INFORMATION_MESSAGE);
					p1rd.remove(num1);
					player1 = false;
					player2 = true;	
					
					IconCheck1.setVisible(false);
					IconCheck2.setVisible(true);
				}		//위 코드는 이동 가능한 칸의 색 변화를 시행함
			}
			
			if(rclick[1] == true && throwY == true) { //말 r1
				rclick[1] = false;
				numselectPanel.setVisible(false);
				bfmv1[1] = P1[1].getLocation();
				afmv1[1] = P1[1].preview(p1rd.get(num1));
				
				if(afmv1[1]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch1[bfmv1[1]] = false;
					
					if((20<afmv1[1] && afmv1[1]<40) || (46<afmv1[1]&&afmv1[1]<60)) {
						image[70].setIcon(finchange);
						ch1[70] = true;
					}
					else if(afmv1[1]==46 || afmv1[1]==60 || afmv1[1]==40|| afmv1[1]==15|| afmv1[1]==43) {
						image[afmv1[1]].setIcon(bgCh);
						ch1[afmv1[1]] = true;
					}
					else {
						image[afmv1[1]].setIcon(btnch);
						ch1[afmv1[1]] = true;
					}
					//위 코드는 이동 가능한 칸의 색 변화를 시행함
				}
				
				else if(afmv1[1]<0) {
					throwY = false;
					JOptionPane.showMessageDialog(null, "You cannot move", "", JOptionPane.INFORMATION_MESSAGE);
					p1rd.remove(num1);
					player1 = false;
					player2 = true;	
					
					IconCheck1.setVisible(false);
					IconCheck2.setVisible(true);
				}		//위 코드는 이동 가능한 칸의 색 변화를 시행함
			}
			
			if(rclick[2] == true && throwY == true) { //말 r1
				rclick[2] = false;
				bfmv1[2] = P1[2].getLocation();
				afmv1[2] = P1[2].preview(p1rd.get(num1));
				
				if(afmv1[2]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch1[bfmv1[2]] = false;
					
					if((20<afmv1[2] && afmv1[2]<40) || (46<afmv1[2]&&afmv1[2]<60)) {
						image[70].setIcon(finchange);
						ch1[70] = true;
					}
					else if(afmv1[2]==46 || afmv1[2]==60 || afmv1[2]==40|| afmv1[2]==15|| afmv1[2]==43) {
						image[afmv1[2]].setIcon(bgCh);
						ch1[afmv1[2]] = true;
					}
					else {
						image[afmv1[2]].setIcon(btnch);
						ch1[afmv1[2]] = true;
					}
					//위 코드는 이동 가능한 칸의 색 변화를 시행함
				}
				
				else if(afmv1[2]<0) {
					throwY = false;
					JOptionPane.showMessageDialog(null, "You cannot move", "", JOptionPane.INFORMATION_MESSAGE);
					p1rd.remove(num1);
					player1 = false;
					player2 = true;	
					
					IconCheck1.setVisible(false);
					IconCheck2.setVisible(true);
				}		//위 코드는 이동 가능한 칸의 색 변화를 시행함
			}
			/***/
			if(rclick[3] == true && throwY == true) { //말 r1
				rclick[3] = false;
				bfmv1[3] = P1[3].getLocation();
				afmv1[3] = P1[3].preview(p1rd.get(num1));
				
				if(afmv1[3]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch1[bfmv1[3]] = false;
					
					if((20<afmv1[3] && afmv1[3]<40) || (46<afmv1[3]&&afmv1[3]<60)) {
						image[70].setIcon(finchange);
						ch1[70] = true;
					}
					else if(afmv1[3]==46 || afmv1[3]==60 || afmv1[3]==40|| afmv1[3]==15|| afmv1[3]==43) {
						image[afmv1[3]].setIcon(bgCh);
						ch1[afmv1[3]] = true;
					}
					else {
						image[afmv1[3]].setIcon(btnch);
						ch1[afmv1[3]] = true;
					}
					//위 코드는 이동 가능한 칸의 색 변화를 시행함
				}
				
				else if(afmv1[3]<0) {
					throwY = false;
					JOptionPane.showMessageDialog(null, "You cannot move", "", JOptionPane.INFORMATION_MESSAGE);
					p1rd.remove(num1);
					player1 = false;
					player2 = true;	
					
					IconCheck1.setVisible(false);
					IconCheck2.setVisible(true);
				}		//위 코드는 이동 가능한 칸의 색 변화를 시행함
			}
			/**********/
			if(yclick[0] == true && throwY == true) { //말 r1
				yclick[0] = false;
				numselectPanel.setVisible(false);
				bfmv2[0] = P2[0].getLocation();
				afmv2[0] = P2[0].preview(p2rd.get(num2));
				
				if(afmv2[0]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch2[bfmv2[0]] = false;
					
					if((20<afmv2[0] && afmv2[0]<40) || (46<afmv2[0]&&afmv2[0]<60)) {
						image[70].setIcon(finchange);
						ch2[70] = true;
					}
					else if(afmv2[0]==46 || afmv2[0]==60 || afmv2[0]==40|| afmv2[0]==15|| afmv2[0]==43) {
						image[afmv2[0]].setIcon(bgCh);
						ch2[afmv2[0]] = true;
					}
					else {
						image[afmv2[0]].setIcon(btnch);
						ch2[afmv2[0]] = true;
					}
					//위 코드는 이동 가능한 칸의 색 변화를 시행함
				}
				
				else if(afmv2[0]<0) {
					throwY = false;
					JOptionPane.showMessageDialog(null, "You cannot move", "", JOptionPane.INFORMATION_MESSAGE);
					p2rd.remove(num2);
					player2 = false;
					player1 = true;	
					
					IconCheck1.setVisible(true);
					IconCheck2.setVisible(false);
				}		//위 코드는 이동 가능한 칸의 색 변화를 시행함
			}
			
			if(yclick[1] == true && throwY == true) { //말 r1
				yclick[1] = false;
				numselectPanel.setVisible(false);
				bfmv2[1] = P2[1].getLocation();
				afmv2[1] = P2[1].preview(p2rd.get(num2));
				
				if(afmv2[1]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch2[bfmv2[1]] = false;
					
					if((20<afmv2[1] && afmv2[1]<40) || (46<afmv2[1]&&afmv2[1]<60)) {
						image[70].setIcon(finchange);
						ch2[70] = true;
					}
					else if(afmv2[1]==46 || afmv2[1]==60 || afmv2[1]==40|| afmv2[1]==15|| afmv2[1]==43) {
						image[afmv2[1]].setIcon(bgCh);
						ch2[afmv2[1]] = true;
					}
					else {
						image[afmv2[1]].setIcon(btnch);
						ch2[afmv2[1]] = true;
					}
				}
				
				else if(afmv2[1]<0) {
					throwY = false;
					JOptionPane.showMessageDialog(null, "You cannot move", "", JOptionPane.INFORMATION_MESSAGE);
					p2rd.remove(num2);
					player2 = false;
					player1 = true;	
					
					IconCheck1.setVisible(true);
					IconCheck2.setVisible(false);
				}		//위 코드는 이동 가능한 칸의 색 변화를 시행함
			}
			/***/
			if(yclick[2] == true && throwY == true) { //말 r1
				yclick[2] = false;
				numselectPanel.setVisible(false);
				bfmv2[2] = P2[2].getLocation();
				afmv2[2] = P2[2].preview(p2rd.get(num2));
				
				if(afmv2[2]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch2[bfmv2[2]] = false;
					
					if((20<afmv2[2] && afmv2[2]<40) || (46<afmv2[2]&&afmv2[2]<60)) {
						image[70].setIcon(finchange);
						ch2[70] = true;
					}
					else if(afmv2[2]==46 || afmv2[2]==60 || afmv2[2]==40|| afmv2[2]==15|| afmv2[2]==43) {
						image[afmv2[2]].setIcon(bgCh);
						ch2[afmv2[2]] = true;
					}
					else {
						image[afmv2[2]].setIcon(btnch);
						ch2[afmv2[2]] = true;
					}
				}
				
				else if(afmv2[2]<0) {
					throwY = false;
					JOptionPane.showMessageDialog(null, "You cannot move", "", JOptionPane.INFORMATION_MESSAGE);
					p2rd.remove(num2);
					player2 = false;
					player1 = true;	
					
					IconCheck1.setVisible(true);
					IconCheck2.setVisible(false);
				}		//위 코드는 이동 가능한 칸의 색 변화를 시행함
			}
			/***/
			if(yclick[3] == true && throwY == true) { //말 r1
				yclick[3] = false;
				numselectPanel.setVisible(false);
				bfmv2[3] = P2[3].getLocation();
				afmv2[3] = P2[3].preview(p2rd.get(num2));
				
				if(afmv2[3]>=0) { //getlocation : 이동 전 말의 위치 받아옴
					ch2[bfmv2[3]] = false;
					
					if((20<afmv2[3] && afmv2[3]<40) || (46<afmv2[3]&&afmv2[3]<60)) {
						image[70].setIcon(finchange);
						ch2[70] = true;
					}
					else if(afmv2[3]==46 || afmv2[3]==60 || afmv2[3]==40|| afmv2[3]==15|| afmv2[3]==43) {
						image[afmv2[3]].setIcon(bgCh);
						ch2[afmv2[3]] = true;
					}
					else {
						image[afmv2[3]].setIcon(btnch);
						ch2[afmv2[3]] = true;
					}
				}
				
				else if(afmv2[3]<0) {
					throwY = false;
					JOptionPane.showMessageDialog(null, "You cannot move", "", JOptionPane.INFORMATION_MESSAGE);
					p2rd.remove(num2);
					player2 = false;
					player1 = true;	
					
					IconCheck1.setVisible(true);
					IconCheck2.setVisible(false);
				}		//위 코드는 이동 가능한 칸의 색 변화를 시행함
			}
		}
	
		/***********************************************************************************/
		
		for(int i = 0; i<71; i++) { //이동
			if(player1 == true) {//플레이어 1의 차례
				if(e.getSource() == btn[i] && ch1[i] == true && throwY == true) { //버튼(칸)이 눌렸음 && 칸에 말 존재 && 윷 던져진 상태
					
					if(mall==1 && bfmv1[0]==0) {
						if(afmv1[0]==46 || afmv1[0]==60 || afmv1[0]==40|| afmv1[0]==15|| afmv1[0]==43) {
							image[afmv1[0]].setIcon(R1bgexist);
						}
						else image[afmv1[0]].setIcon(R1exist);
						
						P1[0].setLocation(p1rd.get(num1)); //1번 말 이동한 위치 저장
						System.out.println("Ch1[46]" + ch1[46]);
					}
					
					else if(mall==2 && bfmv1[1]==0) {
						if(afmv1[1]==46 || afmv1[1]==60 || afmv1[1]==40|| afmv1[1]==15|| afmv1[1]==43) {
							image[afmv1[1]].setIcon(R2bgexist);
						}
						else image[afmv1[1]].setIcon(R2exist);
						
						P1[1].setLocation(p1rd.get(num1));
						System.out.println("Ch1[46]" + ch1[46]);
						//System.out.println("location : " + P1[1].getLocation());
					}
					
					else if(mall==3 && bfmv1[2]==0) {
						if(afmv1[2]==46 || afmv1[2]==60 || afmv1[2]==40|| afmv1[2]==15|| afmv1[2]==43) {
							image[afmv1[2]].setIcon(R3bgexist);
						}
						else image[afmv1[2]].setIcon(R3exist);
						
						P1[2].setLocation(p1rd.get(num1));
						System.out.println("Ch1[46]" + ch1[46]);
						System.out.println("location : " + P1[2].getLocation());
					}
					
					else if(mall==4 && bfmv1[3]==0) {
						if(afmv1[3]==46 || afmv1[3]==60 || afmv1[3]==40|| afmv1[3]==15|| afmv1[3]==43) {
							image[afmv1[3]].setIcon(R4bgexist);
						}
						else image[afmv1[3]].setIcon(R4exist);
						
						P1[3].setLocation(p1rd.get(num1));
						System.out.println("Ch1[46]" + ch1[46]);
						System.out.println("location : " + P1[3].getLocation());
					}
					
					else for(int j = 0; j<4; j++) { // 업기
						loc = P1[j].getLocation() + p1rd.get(num1);
						
						if(loc == 5)	{
							loc=60;
					}
					else if(loc==10) {
						//System.out.println("---");			
							loc=40;
					}else if(loc==14&&checkNum1[j]==1) {
						System.out.println("---");
						loc=65;
				//		location=location+a.yutDun();
						//두번  꺽는 구간 
					}
					
					else if(loc == 63) {
						//System.out.println("---");
						loc-=20;
						midcheck1[j]=1;
					//	location+=a.yutDun();
					}	//중앙에 도착했을 때 꺽기
					else if(loc>65) {

						//System.out.println("---");
						if(loc == 66) {
							//뽑기 실행
							loc = 15;
						}
						else if(loc>66){
							loc -= 51;
						}		
						checkNum1[j]=1;
					}
					else if(loc==59) {
						//System.out.println("---");
						loc = 4;
					}
					else if (loc==42&&midcheck1[j]==1) {
						loc=62;
					}
					else if(loc==59) {
						System.out.println("---");
						loc = 4;//모 자리에서 백도
					}
					else if(loc==20) {
						System.out.println("---");
						loc=46;//마지막은 46으로 통일
						checkNum1[j]=1;
					}else if(loc==45&&checkNum1[j]==1) {
						System.out.println("---");
						loc=19;// 온 방향대로 백도
					}else if(loc == 39) {
						System.out.println("---");
						loc=9;//2번 모 자리에서 백도
					}else if(loc==0) {
						loc=46;
					}
						
						System.out.println("i ; " + i + "  P1[j].getLocation() + p1rd ; " + loc);
						//여기부터 문제
						if(P1[j].getLocation() + p1rd.get(num1) == P1[mall-1].getLocation() + p1rd.get(num1)) {
						if(((P1[j].getLocation() + p1rd.get(num1)>20 && P1[j].getLocation() + p1rd.get(num1)<40) || (P1[j].getLocation() + p1rd.get(num1)>46 && P1[j].getLocation() + p1rd.get(num1)<60))){
							if(j==0 && P1[j].getLocation() + p1rd.get(num1)==loc) {
								redmall1Button.setEnabled(false);
								P1[0].success();
								checkr[0] = true;
								//P1[0].setLocation(p1rd.get(num1));
							}
							if(j==1 && P1[j].getLocation() + p1rd.get(num1)==loc) {
								redmall2Button.setEnabled(false);
								P1[1].success();
								checkr[1] = true;
								//P1[1].setLocation(p1rd.get(num1));
							}
							if(j==2 && P1[j].getLocation() + p1rd.get(num1)==loc) {
								redmall3Button.setEnabled(false);
								P1[2].success();
								checkr[2] = true;
								//P1[2].setLocation(p1rd.get(num1));
							}
							if(j==3 && P1[j].getLocation() + p1rd.get(num1)==loc) {
								redmall4Button.setEnabled(false);
								P1[3].success();
								checkr[3] = true;
								//P1[3].setLocation(p1rd.get(num1));
							}
							}
						}
						if(i == loc) {
							if(loc==46 || loc==60 || loc==40|| loc==15|| loc==43) {
								if(j==0) image[loc].setIcon(R1bgexist);
								else if(j==1) image[loc].setIcon(R2bgexist);
								else if(j==2) image[loc].setIcon(R3bgexist);
								else if(j==3) image[loc].setIcon(R4bgexist);
							}
							else {
								if(j==0) image[loc].setIcon(R1exist);
								else if(j==1) image[loc].setIcon(R2exist);
								else if(j==2) image[loc].setIcon(R3exist);
								else if(j==3) image[loc].setIcon(R4exist);
							}
							
							P1[j].setLocation(p1rd.get(num1)); //1번 말 이동한 위치 저장
							System.out.println("location : " + P1[j].getLocation());
							
						}
					}
					for(int k=0; k<4; k++) {
						if(checkr[k]) {
							P1[k].setLocation(p1rd.get(num1));
						}
					}
					if((ch1[70] == true) && (i==70)) {
						loc = P1[mall-1].getLocation() + p1rd.get(num1);
						if((loc>20 && loc<40) || (loc>46 && loc<60)) {
							ch1[70] = false;
							image[70].setIcon(btn1);
							
						}
					}
					
					if(i==60||i==5) {// 게임 넣는 것
						/*
						 * Quiz quiz = new Quiz(); quiz.setVisible(true);
						 */
						Quizframe(); //fix
						
					
						System.out.println("check");
					}else if(i==10||i==40) {
						rockScissorPaper rsp = new rockScissorPaper();			
						rsp.setVisible(true);		
						if(rsp.getGameChanger() == -1) {
							for(int j=0; j<4; j++) {
								if(i == P1[j].getLocation()) {
									P1[j].goStart();
									checkNum1[j]=0;
									midcheck1[j]=0;
									System.out.println("check"+ rsp.getGameChanger());
								}
							}
							ch1[i]=false;
						}
						
					}else if(i==15||i==66) {
						
							//뽑기 실행
							Lots lot = new Lots();
							lot.setVisible(true);
							if(lot.getGameChanger() == -1) {
								for(int j=0; j<4; j++) {
									if(i == P1[j].getLocation()) {
										P1[j].goStart();
										checkNum1[j]=0;
										midcheck1[j]=0;
										System.out.println("check"+lot.getGameChanger());
									}
								}
								ch1[i]=false;
							}
						
					}
					
					if(ch2[i]==true) {
						for(int j=0; j<4; j++) {
							if(i == P2[j].getLocation()) {
								P2[j].goStart();
								checkNum1[j]=0;
								midcheck1[j]=0;
							}
						}
						JOptionPane.showMessageDialog(null, "You catch the Player2's mal", "Catch", JOptionPane.INFORMATION_MESSAGE);
						ch2[i]=false;
						chance1 =true;
						
					}
					
					for(int j = 0; j<71; j++) {
						if(ch1[j] == false && ch2[j] == false) { //말이 없는 칸을 찾아서 버튼 변경
							if(j==46 || j==60 || j== 40 || j==15 || j==43 || j==70) {
								image[j].setIcon(btn1);
							}
							else image[j].setIcon(btn2);
						}
					}

					System.out.println("Ch1[46]" + ch1[46]);
					p1rd.remove(num1);
					System.out.println(p1rd.size());
					
					for(int k=0; k<4; k++) {
						if(P1[k].getLocation() != 0) mallLabel[k].setVisible(false);
						if(P1[k].getLocation() == 0) mallLabel[k].setVisible(true);
						if(P2[k].getLocation() != 0) mallLabel2[k].setVisible(false);
						if(P2[k].getLocation() == 0) mallLabel2[k].setVisible(true);
					}
					if(P1[0].getNum()!=0) {
						mallLabel[0].setVisible(true);
						mallLabel[0].setIcon(redf1);
					}
					if(P1[1].getNum()!=0) {
						mallLabel[1].setVisible(true);
						mallLabel[1].setIcon(redf2);
					}
					if(P1[2].getNum()!=0) {
						mallLabel[2].setVisible(true);
						mallLabel[2].setIcon(redf3);
					}
					if(P1[3].getNum()!=0) {
						mallLabel[3].setVisible(true);
						mallLabel[3].setIcon(redf4);
					}
					
					if(P2[0].getNum()!=0) {
						mallLabel2[0].setVisible(true);
						mallLabel2[0].setIcon(yelf1);
					}
					if(P2[1].getNum()!=0) {
						mallLabel2[1].setVisible(true);
						mallLabel2[1].setIcon(yelf2);
					}
					if(P2[2].getNum()!=0) {
						mallLabel2[2].setVisible(true);
						mallLabel2[2].setIcon(yelf3);
					}
					if(P2[3].getNum()!=0) {
						mallLabel2[3].setVisible(true);
						mallLabel2[3].setIcon(yelf4);
					}
					
					if(0!=p1rd.size()) {
						choose1Panel.setVisible(true);
						for(int k=0; k<71; k++) {
							btn[k].setEnabled(false);
						}
					}
					else{
						throwY = false;
					
						if(chance1) {
							player1 = true;
							player2 = false;
							chance1 = false;
						}
						else {
							player1 = false;
							player2 = true;
						}
					}
					
					int cnt1 = 0;
					int cnt2 = 0;
					for (int k=0; k<4; k++) {
						if(P1[k].getNum()!=0) {
							cnt1++;
						}
						if(P2[k].getNum()!=0) {
							cnt2++;
						}
					}
					
					if(cnt1==4) JOptionPane.showMessageDialog(null, "Player 1 Win!", "Win", JOptionPane.INFORMATION_MESSAGE);
					if(cnt2==4) JOptionPane.showMessageDialog(null, "Player 2 Win!", "Win", JOptionPane.INFORMATION_MESSAGE);
					if(cnt1==4 || cnt2==4) {
						JOptionPane.showMessageDialog(null, "new game will start", "notice", JOptionPane.INFORMATION_MESSAGE);
						setNewgame();
					}
					
					if(player1 == true) {
						IconCheck1.setVisible(true);
						IconCheck2.setVisible(false);
					}
					else if(player2 == true) {
						IconCheck1.setVisible(false);
						IconCheck2.setVisible(true);
					}
				}
			}
				
			else if(player2 == true) {
				if(e.getSource() == btn[i] && ch2[i] == true && throwY == true) { //버튼(칸)이 눌렸음 && 칸에 말 존재 && 윷 던져진 상태
					
					if(mall==1 && bfmv2[0]==0) {
						if(afmv2[0]==46 || afmv2[0]==60 || afmv2[0]==40|| afmv2[0]==15|| afmv2[0]==43) {
							image[afmv2[0]].setIcon(Y1bgexist);
						}
						else image[afmv2[0]].setIcon(Y1exist);
						
						P2[0].setLocation(p2rd.get(num2)); //1번 말 이동한 위치 저장

						System.out.println("Ch2[46]" + ch2[46]);
						System.out.println("location : " + P2[0].getLocation());
					}
					else if(mall==2 && bfmv2[1]==0) {
						if(afmv2[1]==46 || afmv2[1]==60 || afmv2[1]==40|| afmv2[1]==15|| afmv2[1]==43) {
							image[afmv2[1]].setIcon(Y2bgexist);
						}
						else image[afmv2[1]].setIcon(Y2exist);
						
						P2[1].setLocation(p2rd.get(num2));

						System.out.println("Ch2[46]" + ch2[46]);
						System.out.println("location : " + P2[1].getLocation());
					}
					else if(mall==3 && bfmv2[2]==0) {
						if(afmv2[2]==46 || afmv2[2]==60 || afmv2[2]==40|| afmv2[2]==15|| afmv2[2]==43) {
							image[afmv2[2]].setIcon(Y3bgexist);
						}
						else image[afmv2[2]].setIcon(Y3exist);
						
						P2[2].setLocation(p2rd.get(num2));

						System.out.println("Ch2[46]" + ch2[46]);
						System.out.println("location : " + P2[2].getLocation());
					}
					else if(mall==4 && bfmv2[3]==0) {
						if(afmv2[3]==46 || afmv2[3]==60 || afmv2[3]==40|| afmv2[3]==15|| afmv2[3]==43) {
							image[afmv2[3]].setIcon(Y4bgexist);
						}
						else image[afmv2[3]].setIcon(Y4exist);
						
						P2[3].setLocation(p2rd.get(num2));
						System.out.println("Ch2[46]" + ch2[46]);
						System.out.println("location : " + P2[3].getLocation());
					}
					else for(int j = 0; j<4; j++) { // 업기
						loc = P2[j].getLocation() + p2rd.get(num2);
						
						if(loc == 5)	{
								loc=60;
						}
						else if(loc==10) {
							//System.out.println("---");			
								loc=40;
						}else if(loc==14&&checkNum2[j]==1) {
							System.out.println("---");
							loc=65;
					//		location=location+a.yutDun();
							//두번  꺽는 구간 
						}
						

						else if(loc == 63) {
							//System.out.println("---");
							loc-=20;
							midcheck2[j]=1;
						//	location+=a.yutDun();
						}	//중앙에 도착했을 때 꺽기
						else if(loc>65) {

							//System.out.println("---");
							if(loc == 66) {
								//뽑기 실행
								loc = 15;
							}
							else if(loc>66){
								loc -= 51;
							}		
							checkNum2[j]=1;
						}
						else if(loc==59) {
							//System.out.println("---");
							loc = 4;
						}
						else if (loc==42&&midcheck2[j]==1) {
							loc=62;
						}
						else if(loc==59) {
							System.out.println("---");
							loc = 4;//모 자리에서 백도
						}
						else if(loc==20) {
							System.out.println("---");
							loc=46;//마지막은 46으로 통일
							checkNum2[j]=1;
						}else if(loc==45&&checkNum2[j]==1) {
							System.out.println("---");
							loc=19;// 온 방향대로 백도
						}else if(loc == 39) {
							System.out.println("---");
							loc=9;//2번 모 자리에서 백도
						}
						if(P2[j].getLocation() + p2rd.get(num2) == P2[mall-1].getLocation() + p2rd.get(num2)) {
						if(((P2[j].getLocation() + p2rd.get(num2)>20 && P2[j].getLocation() + p2rd.get(num2)<40) || (P2[j].getLocation() + p2rd.get(num2)>46 && P2[j].getLocation() + p2rd.get(num2)<60))){
							if(j==0 && P2[j].getLocation() + p2rd.get(num2) == loc) {
								yellowMall1Button.setEnabled(false);
								P2[0].success();
								checky[0] = true;
								//P2[0].setLocation(p2rd.get(num2));
							}
							if(j==1 && P2[j].getLocation() + p2rd.get(num2) == loc) {
								yellowMall2Button.setEnabled(false);
								P2[1].success();
								checky[1] = true;
							//	P2[1].setLocation(p2rd.get(num2));
							}
							if(j==2 && P2[j].getLocation() + p2rd.get(num2) == loc) {
								yellowMall3Button.setEnabled(false);
								P2[2].success();
								checky[2] = true;
							//	P2[2].setLocation(p2rd.get(num2));
							}
							if(j==3 && P2[j].getLocation() + p2rd.get(num2) == loc) {
								yellowMall4Button.setEnabled(false);
								P2[3].success();
								checky[3] = true;
							//	P2[3].setLocation(p2rd.get(num2));
							}
						}
						}
						System.out.println("i ; " + i + "  P2[j].getLocation() + p2rd ; " + loc);
						if(i == loc) {
							if(loc==46 || loc==60 || loc==40|| loc==15|| loc==43) {
								if(j==0) {
									image[loc].setIcon(Y1bgexist);
								}
								else if(j==1) image[loc].setIcon(Y2bgexist);
								else if(j==2) image[loc].setIcon(Y3bgexist);
								else if(j==3) image[loc].setIcon(Y4bgexist);
							}
							else{
								if(j==0) image[loc].setIcon(Y1exist);
								else if(j==1) image[loc].setIcon(Y2exist);
								else if(j==2) image[loc].setIcon(Y3exist);
								else if(j==3) image[loc].setIcon(Y4exist);
							}
							P2[j].setLocation(p2rd.get(num2)); //1번 말 이동한 위치 저장
							System.out.println("location : " + P2[j].getLocation());
						}
					
						}
					
					for(int k=0; k<4; k++) {
						if(checky[k]) {
							P2[k].setLocation(p2rd.get(num2));
						}
					}
					if((ch2[70] == true) && (i==70)) {
						loc = P2[mall-1].getLocation() + p2rd.get(num2);
						if((loc>20 && loc<40) || (loc>46 && loc<60)) {
							ch2[70] = false;
							image[70].setIcon(btn1);
						}
					}
			
					if(i==60||i==5) {// 게임 넣는 것
						Quizframe(); //fix
						  
						 }else if(i==10||i==40) {
						rockScissorPaper rsp = new rockScissorPaper();			
						rsp.setVisible(true);		
						if(rsp.getGameChanger() == -1) {
							
							for(int j=0; j<4; j++) {
								if(i == P2[j].getLocation()) {
									P2[j].goStart();
									checkNum2[j]=0;
									midcheck2[j]=0;
								}
							}
							ch2[i]=false;
						}
						
					}else if(i==15||i==66) {
						
							//뽑기 실행
							Lots lot = new Lots();
							lot.setVisible(true);
							if(lot.getGameChanger() == -1) {
								for(int j=0; j<4; j++) {
									if(i == P2[j].getLocation()) {
										P2[j].goStart();
										checkNum2[j]=0;
										midcheck2[j]=0;
									}
								}
								ch2[i]=false;
							}
						
					}
					
					if(ch1[i]==true) {
						for(int j=0; j<4; j++) {
							if(i == P1[j].getLocation()) {
								P1[j].goStart();
							}
						}
						
						JOptionPane.showMessageDialog(null, "You catch the Player1's mal", "Catch", JOptionPane.INFORMATION_MESSAGE);
						ch1[i]=false;
						chance2 =true;
					}
					
					for(int j = 0; j<71; j++) {
						if(ch1[j] == false && ch2[j] == false) { //말이 없는 칸을 찾아서 버튼 변경
							if(j==46 || j==60 || j== 40 || j==15 || j==43 || j==70) {
								image[j].setIcon(btn1);
							}
							else image[j].setIcon(btn2);
						}
					}
					p2rd.remove(num2);
					System.out.println(p2rd.size());
					
					for(int k=0; k<4; k++) {
						if(P1[k].getLocation() != 0) mallLabel[k].setVisible(false);
						if(P1[k].getLocation() == 0) mallLabel[k].setVisible(true);
						if(P2[k].getLocation() != 0) mallLabel2[k].setVisible(false);
						if(P2[k].getLocation() == 0) mallLabel2[k].setVisible(true);
					}
					
					if(P1[0].getNum()!=0) {
						mallLabel[0].setVisible(true);
						mallLabel[0].setIcon(redf1);
					}
					if(P1[1].getNum()!=0) {
						mallLabel[1].setVisible(true);
						mallLabel[1].setIcon(redf2);
					}
					if(P1[2].getNum()!=0) {
						mallLabel[2].setVisible(true);
						mallLabel[2].setIcon(redf3);
					}
					if(P1[3].getNum()!=0) {
						mallLabel[3].setVisible(true);
						mallLabel[3].setIcon(redf4);
					}
					
					if(P2[0].getNum()!=0) {
						mallLabel2[0].setVisible(true);
						mallLabel2[0].setIcon(yelf1);
					}
					if(P2[1].getNum()!=0) {
						mallLabel2[1].setVisible(true);
						mallLabel2[1].setIcon(yelf2);
					}
					if(P2[2].getNum()!=0) {
						mallLabel2[2].setVisible(true);
						mallLabel2[2].setIcon(yelf3);
					}
					if(P2[3].getNum()!=0) {
						mallLabel2[3].setVisible(true);
						mallLabel2[3].setIcon(yelf4);
					}
					
					int cnt1 = 0;
					int cnt2 = 0;
					for (int k=0; k<4; k++) {
						if(P1[k].getNum()!=0) {
							cnt1++;
						}
						if(P2[k].getNum()!=0) {
							cnt2++;
						}
					}
					
					if(cnt1==4) JOptionPane.showMessageDialog(null, "Player 1 Win!", "Win", JOptionPane.INFORMATION_MESSAGE);
					if(cnt2==4) JOptionPane.showMessageDialog(null, "Player 2 Win!", "Win", JOptionPane.INFORMATION_MESSAGE);
					if(cnt1==4 || cnt2==4) {
						JOptionPane.showMessageDialog(null, "new game will start", "notice", JOptionPane.INFORMATION_MESSAGE);
						setNewgame();
					}
					
					if(0!=p2rd.size()) {
						choose2Panel.setVisible(true);
						for(int k=0; k<71; k++) {
							btn[k].setEnabled(false);
						}
					}
					
					/************************/
					
					else{
						throwY = false;
					
						if(chance2) {
							player2 = true;
							player1 = false;
							chance2 = false;
						}
						else {
							player2 = false;
							player1 = true;
						}
					}
					if(player1 == true) {
						IconCheck1.setVisible(true);
						IconCheck2.setVisible(false);
					}
					else if(player2 == true) {
						IconCheck1.setVisible(false);
						IconCheck2.setVisible(true);
					}
				}
			}
		}
		/***************************************수정 fix*****************************************/
		if(actionCommand.equals("1")) {
			quizFrame.add(quizLabel);		
			quizFrame.add(quizPanel, BorderLayout.CENTER);
			if(choice1[randomChoice].compareTo(answer[randomChoice])== 0) {
			//	quizLabel.setIcon(OIcon);
			//	gameChanger=1;
				System.out.println("kk1");
				JOptionPane.showMessageDialog(null, "정답입니다.", "notice", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "틀렸습니다.", "notice", JOptionPane.INFORMATION_MESSAGE);
			//	quizLabel.setIcon(XIcon);
			//	gameChanger=-1;
				if(ch1[60]) {
					for(int j=0; j<4; j++) {
						if(60 == P1[j].getLocation()) {
							P1[j].goStart();
							checkNum1[j]=0;
							midcheck1[j]=0;
							mallLabel[j].setVisible(true);
							//System.out.println("check"+ rsp.getGameChanger());
					
						}
						}
					ch1[60]=false;
				}
				if(ch2[60]) {
					for(int j=0; j<4; j++) {
						if(60 == P2[j].getLocation()) {
						P2[j].goStart();
						checkNum2[j]=0;
						midcheck2[j]=0;
						mallLabel2[j].setVisible(true);
						//System.out.println("check"+ rsp.getGameChanger());
					}
						}
				ch2[60]=false;
				}
				image[60].setIcon(btn1);
				System.out.println("kk-1");
			}
			Packer packerThread = new Packer();
	        packerThread.start();
	        quizFrame.dispose();
		}
		
		else if(actionCommand.equals("2")) {
			quizPanel.add(quizLabel);		
			quizFrame.add(quizPanel, BorderLayout.CENTER);
			if(choice2[randomChoice].compareTo(answer[randomChoice])== 0) {
				JOptionPane.showMessageDialog(null, "정답입니다.", "notice", JOptionPane.INFORMATION_MESSAGE);
			//	quizLabel.setIcon(OIcon);
				gameChanger=1;
				System.out.println("kk1");
			}else {
				JOptionPane.showMessageDialog(null, "틀렸습니다.", "notice", JOptionPane.INFORMATION_MESSAGE);
			//	quizLabel.setIcon(XIcon);
				gameChanger=-1;
				if(ch1[60]) {
					for(int j=0; j<4; j++) {
						if(60 == P1[j].getLocation())
						{P1[j].goStart();
						checkNum1[j]=0;
						midcheck1[j]=0;
						mallLabel[j].setVisible(true);
						//System.out.println("check"+ rsp.getGameChanger());
					
						}
						}
				ch1[60]=false;
				}
				if(ch2[60]) {
					for(int j=0; j<4; j++) {
						if(60 == P2[j].getLocation()) {
							P2[j].goStart();
						
						checkNum2[j]=0;
						midcheck2[j]=0;
						mallLabel2[j].setVisible(true);
						//System.out.println("check"+ rsp.getGameChanger());
					}}
					ch2[60]=false;
				}
				System.out.println("kk-1");
				image[60].setIcon(btn1);
			}			
			Packer packerThread = new Packer();
	         packerThread.start();
	         quizFrame.dispose();
	      
		}
		
		else if(actionCommand.equals("3")) {
			quizPanel.add(quizLabel);		
			
			quizFrame.add(quizPanel, BorderLayout.CENTER);
			if(choice3[randomChoice].compareTo(answer[randomChoice]) == 0) {
				JOptionPane.showMessageDialog(null, "정답입니다.", "notice", JOptionPane.INFORMATION_MESSAGE);
			//	quizLabel.setIcon(OIcon);
				gameChanger=1;
				System.out.println("kk1");
			}else {
				JOptionPane.showMessageDialog(null, "틀렸습니다.", "notice", JOptionPane.INFORMATION_MESSAGE);
			//	quizLabel.setIcon(XIcon);
				gameChanger=-1;
				if(ch1[60]) {
					for(int j=0; j<4; j++) {
						if(60 == P1[j].getLocation()) {
						P1[j].goStart();
						checkNum1[j]=0;
						midcheck1[j]=0;
						mallLabel[j].setVisible(true);
						//System.out.println("check"+ rsp.getGameChanger());
				
						}
						}
					ch1[60]=false;
				}
				if(ch2[60]) {
					for(int j=0; j<4; j++) {
						if(60 == P2[j].getLocation()) {
						P2[j].goStart();
						checkNum2[j]=0;
						midcheck2[j]=0;
						mallLabel2[j].setVisible(true);
						//System.out.println("check"+ rsp.getGameChanger());
					}}
					ch2[60]=false;
				}
				System.out.println("kk-1");
				image[60].setIcon(btn1);
			}
			Packer packerThread = new Packer();
	         packerThread.start();
	         quizFrame.dispose();
		}
		/***************************************************************/
}
					
	public int yutdun() {
		int res = 0;
		int temp;
		
		temp = yourYut.yutDun();			
		for(int i = 0; i< 3; i++) {
			res += yourYut.getYut(i);
		}
		if(res == 3 && yourYut.getYut(3) == 0) {
			temp = 3;
			resLabel.setIcon(gulicon);
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
			temp = 4;
			resLabel.setIcon(yuticon);
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
			temp = 3;
			resLabel.setIcon(gulicon);
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
			temp = 2;
			resLabel.setIcon(geicon);
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
			temp = -1;
			resLabel.setIcon(backdoicon);
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
			temp = 2;
			resLabel.setIcon(geicon);
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
			temp = 1;
			resLabel.setIcon(Doicon);
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
			temp = 5;
			resLabel.setIcon(moicon);
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
		revalidate();
		repaint();
		return temp;
	}
	//새로하기
	public void setNewgame() {
		for(int i=0; i<4; i++) {
			rclick[i] = false;
			yclick[i] = false;
		}
		
		for(int i=0;i<4;i++) {
			P1[i] = new Malll();
			P2[i] = new Malll();
		}
		
		for(int i=0; i<71; i++) { //변수 설정
			btn[i].setEnabled(false);
			pan.add(btn[i]);
			ch1[i] = false;
			ch2[i] = false;
		}
		
		for(int i=0; i<4; i++) {
			mallLabel[i].setVisible(true);
			mallLabel2[i].setVisible(true);
		}
		
		redmall1Button.setEnabled(true);
		redmall2Button.setEnabled(true);
		redmall3Button.setEnabled(true);
		redmall4Button.setEnabled(true);
		
		yellowMall1Button.setEnabled(true);
		yellowMall2Button.setEnabled(true);
		yellowMall3Button.setEnabled(true);
		yellowMall4Button.setEnabled(true);
		
		choose1Panel.setVisible(false);
		choose2Panel.setVisible(false);
		numselectPanel.setVisible(false);
		
		mallLabel[0].setIcon(redmall1);
		mallLabel[1].setIcon(redmall2);
		mallLabel[2].setIcon(redmall3);
		mallLabel[3].setIcon(redmall4);
		
		mallLabel2[0].setIcon(yellowmall1);
		mallLabel2[1].setIcon(yellowmall2);
		mallLabel2[2].setIcon(yellowmall3);
		mallLabel2[3].setIcon(yellowmall4);
			
		resLabel.setIcon(questionicon);
		yutLabel1.setIcon(frontYutIcon);
		yutLabel2.setIcon(frontYutIcon);
		yutLabel3.setIcon(frontYutIcon);
		yutLabel4.setIcon(frontYutIcon);
		
		player1 = true;
		player2 = false;
		
		chance1 = false;
		chance2 = false;
		
		throwY = false;
		
		for(int i=0; i<71; i++) {
			image[i].setIcon(btn2);
			if(i==46 || i==60 || i==40|| i==15|| i==43||i==70) {
				image[i].setIcon(btn1);
			}
			btn[i].setEnabled(false);
		}
		for(int i=p1rd.size() - 1; i>= 0; i--) {
			p1rd.remove(i);
		}
		
		for(int i=p2rd.size() - 1; i>= 0; i--) {
			p2rd.remove(i);
		}
		
		IconCheck1.setVisible(true);
		IconCheck2.setVisible(false);
	}
	
	public void setCongame() {
		for(int i=0; i<71; i++) { //변수 설정
			btn[i].setEnabled(false);
			pan.add(btn[i]);
		}
		
		for(int i=0; i<4; i++) {
			mallLabel[i].setVisible(true);
			mallLabel2[i].setVisible(true);
			
		}
		
		redmall1Button.setEnabled(true);
		redmall2Button.setEnabled(true);
		redmall3Button.setEnabled(true);
		redmall4Button.setEnabled(true);
		
		yellowMall1Button.setEnabled(true);
		yellowMall2Button.setEnabled(true);
		yellowMall3Button.setEnabled(true);
		yellowMall4Button.setEnabled(true);
		
		mallLabel[0].setIcon(redmall1);
		mallLabel[1].setIcon(redmall2);
		mallLabel[2].setIcon(redmall3);
		mallLabel[3].setIcon(redmall4);
		
		mallLabel2[0].setIcon(yellowmall1);
		mallLabel2[1].setIcon(yellowmall2);
		mallLabel2[2].setIcon(yellowmall3);
		mallLabel2[3].setIcon(yellowmall4);
			
		resLabel.setIcon(questionicon);
		yutLabel1.setIcon(frontYutIcon);
		yutLabel2.setIcon(frontYutIcon);
		yutLabel3.setIcon(frontYutIcon);
		yutLabel4.setIcon(frontYutIcon);
		
		choose1Panel.setVisible(false);
		choose2Panel.setVisible(false);
		numselectPanel.setVisible(false);
		
		chance1 = false;
		chance2 = false;
		
		throwY = false;
		
		for(int i=0; i<71; i++) {
			image[i].setIcon(btn2);
			if(i==46 || i==60 || i==40|| i==15|| i==43|| i==70) {
				image[i].setIcon(btn1);
			}
			btn[i].setEnabled(false);
			System.out.print(i+" " + ch1[i] +"\t");
			System.out.print(i+" " + ch2[i] +"\t");
		}
		
		/*******/
		if(P1[0].getLocation()!=0 && P1[0].getNum()==0) {
			mallLabel[0].setVisible(false);
			image[P1[0].getLocation()].setIcon(R1exist);
			if(P1[0].getLocation()==0 || P1[0].getLocation()==60 || P1[0].getLocation() == 40 || P1[0].getLocation() == 15 || P1[0].getLocation() == 43) {
				image[P1[0].getLocation()].setIcon(R1bgexist);
			}
		}
		
		if(P1[1].getLocation()!=0 && P1[1].getNum()==0) {
			mallLabel[1].setVisible(false);
			image[P1[1].getLocation()].setIcon(R2exist);
			if(P1[1].getLocation()==0 || P1[1].getLocation()==60 || P1[1].getLocation() == 40 || P1[1].getLocation() == 15 || P1[1].getLocation() == 43) {
				image[P1[1].getLocation()].setIcon(R2bgexist);
			}
		}
		
		if(P1[2].getLocation()!=0 && P1[2].getNum()==0) {
			mallLabel[2].setVisible(false);
			image[P1[2].getLocation()].setIcon(R3exist);
			if(P1[2].getLocation()==0 || P1[2].getLocation()==60 || P1[2].getLocation() == 40 || P1[2].getLocation() == 15 || P1[2].getLocation() == 43) {
				image[P1[2].getLocation()].setIcon(R3bgexist);
			}
		}
		
		if(P1[3].getLocation()!=0 && P1[3].getNum()==0) {
			mallLabel[3].setVisible(false);
			image[P1[3].getLocation()].setIcon(R4exist);
			if(P1[3].getLocation()==0 || P1[3].getLocation()==60 || P1[3].getLocation() == 40 || P1[3].getLocation() == 15 || P1[3].getLocation() == 43) {
				image[P1[3].getLocation()].setIcon(R4bgexist);
			}
		}
		/*****/
		if(P2[0].getLocation()!=0 && P2[0].getNum()==0) {
			mallLabel2[0].setVisible(false);
			image[P2[0].getLocation()].setIcon(Y1exist);
			if(P2[0].getLocation()==0 || P2[0].getLocation()==60 || P2[0].getLocation() == 40 || P2[0].getLocation() == 15 || P2[0].getLocation() == 43) {
				image[P2[0].getLocation()].setIcon(Y1bgexist);
			}
		}
		
		if(P2[1].getLocation()!=0 && P2[1].getNum()==0) {
			mallLabel2[1].setVisible(false);
			image[P2[1].getLocation()].setIcon(Y2exist);
			if(P2[1].getLocation()==0 || P2[1].getLocation()==60 || P2[1].getLocation() == 40 || P2[1].getLocation() == 15 || P2[1].getLocation() == 43) {
				image[P1[1].getLocation()].setIcon(Y2bgexist);
			}
		}
		
		if(P2[2].getLocation()!=0 && P2[2].getNum()==0) {
			mallLabel2[2].setVisible(false);
			image[P2[2].getLocation()].setIcon(Y3exist);
			if(P2[2].getLocation()==0 || P2[2].getLocation()==60 || P2[2].getLocation() == 40 || P2[2].getLocation() == 15 || P2[2].getLocation() == 43) {
				image[P2[2].getLocation()].setIcon(Y3bgexist);
			}
		}
		
		if(P2[3].getLocation()!=0 && P2[3].getNum()==0) {
			mallLabel2[3].setVisible(false);
			image[P2[3].getLocation()].setIcon(Y4exist);
			if(P2[3].getLocation()==0 || P2[3].getLocation()==60 || P2[3].getLocation() == 40 || P2[3].getLocation() == 15 || P2[3].getLocation() == 43) {
				image[P2[3].getLocation()].setIcon(Y4bgexist);
			}
		}
		/**********/
		
		for(int k=0; k<4; k++) {
			if(P1[k].getLocation() != 0) mallLabel[k].setVisible(false);
			if(P1[k].getLocation() == 0) mallLabel[k].setVisible(true);
			if(P2[k].getLocation() != 0) mallLabel2[k].setVisible(false);
			if(P2[k].getLocation() == 0) mallLabel2[k].setVisible(true);
		}
		
		if(P1[0].getNum()!=0) {
			redmall1Button.setEnabled(false);
			mallLabel[0].setVisible(true);
			mallLabel[0].setIcon(redf1);
		}
		if(P1[1].getNum()!=0) {
			redmall2Button.setEnabled(false);
			mallLabel[1].setVisible(true);
			mallLabel[1].setIcon(redf2);
		}
		if(P1[2].getNum()!=0) {
			redmall3Button.setEnabled(false);
			mallLabel[2].setVisible(true);
			mallLabel[2].setIcon(redf3);
		}
		if(P1[3].getNum()!=0) {
			redmall4Button.setEnabled(false);
			mallLabel[3].setVisible(true);
			mallLabel[3].setIcon(redf4);
		}
		
		if(P2[0].getNum()!=0) {
			yellowMall1Button.setEnabled(false);
			mallLabel2[0].setVisible(true);
			mallLabel2[0].setIcon(yelf1);
		}
		if(P2[1].getNum()!=0) {
			yellowMall2Button.setEnabled(false);
			mallLabel2[1].setVisible(true);
			mallLabel2[1].setIcon(yelf2);
		}
		if(P2[2].getNum()!=0) {
			yellowMall3Button.setEnabled(false);
			mallLabel2[2].setVisible(true);
			mallLabel2[2].setIcon(yelf3);
		}
		if(P2[3].getNum()!=0) {
			yellowMall4Button.setEnabled(false);
			mallLabel2[3].setVisible(true);
			mallLabel2[3].setIcon(yelf4);
		}
		
		
		
		for(int i=p1rd.size() - 1; i>= 0; i--) {
			p1rd.remove(i);
		}
		
		for(int i=p2rd.size() - 1; i>= 0; i--) {
			p2rd.remove(i);
		}
		
		if(player1) {
			IconCheck1.setVisible(true);
			IconCheck2.setVisible(false);
		}
		else if(player2) {
			IconCheck1.setVisible(false);
			IconCheck2.setVisible(true);
		}
	}
	
	public void setP1(Malll[] obj) {
		P1 = obj;
	}
	
	public void setP2(Malll[] obj) {
		P2 = obj;
	}
	
	public void setCh1(boolean[] obj) {
		ch1 = obj;
	}
	
	public void setCh2(boolean[] obj) {
		ch2 = obj;
	}
	
	public void setPlayer1(boolean bo) {
		player1 = bo;
	}
	
	public void setPlayer2(boolean bo) {
		player2 = bo;
	}
	
	public Malll[] getP1(){
		return (Malll[])P1.clone();
	}
	
	public Malll[] getP2() {
		return (Malll[])P2.clone();
	}
	
	public boolean[] getCh1() {
		return (boolean[])ch1.clone();
	}
	
	public boolean[] getCh2() {
		return (boolean[])ch2.clone();
	}
	
	public boolean getPlayer1() {
		return player1;
	}
	
	public boolean getPlayer2() {
		return player2;
	}
	public int quiz() {
		Quiz rsp = new Quiz();			
		rsp.setVisible(true);
		//MatrixTime(10000);
		System.out.println("get "+rsp.getGameChanger());
		return rsp.getGameChanger();
	}
	public void MatrixTime(int delayTime){
		long saveTime = System.currentTimeMillis();
		long currTime = 0;

		while( currTime - saveTime < delayTime){
			currTime = System.currentTimeMillis();
		}
	}
	/******************************************퀴즈 추가(fix)******************************************/
	public void Quizframe(){
		quizFrame = new JFrame();
		
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
		
		
		quizFrame.setSize(WIDTH2, HEIGHT2);
		quizFrame.setTitle("Quiz");		
		//quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		quizFrame.setBackground(Color.WHITE);
		quizFrame.getContentPane().setBackground(Color.WHITE);
		quizFrame.setLayout(new BorderLayout());
		fontObj = new Font("SanSerif", Font.PLAIN, 24); 	
		
		JLabel quizlabel= new JLabel(Quiz + theText);
		quizlabel.setFont(fontObj);

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
		
		quizFrame.add(quizlabel, BorderLayout.CENTER);
		quizFrame.add(ButtonPanel, BorderLayout.SOUTH);
		quizFrame.setVisible(true);
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
	 /***************************************************************************/
	
}