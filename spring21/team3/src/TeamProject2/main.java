package TeamProject2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class main extends JFrame {
	public first firstPanel = null;
	public gamemain gamemainPanel = null;
	public rankmain rankmainPanel = null;
	public ranking rankingPanel3;
	public ranking rankingPanel4;
	public ranking rankingPanel5;
	public ranking rankingPanel6;
	
	public void change(String panelName) {
		if(panelName.equals("first")) {
			getContentPane().removeAll();
			getContentPane().add(firstPanel);
			revalidate();
			repaint();
		}else if(panelName.equals("rankmain")){
			getContentPane().removeAll();
			getContentPane().add(rankmainPanel);
			revalidate();
			repaint();
		}else if(panelName.equals("gamemain")) {
			getContentPane().removeAll();
			getContentPane().add(gamemainPanel);
			revalidate();
			repaint();
		}else if(panelName.equals("ranking3")) {
			getContentPane().removeAll();
			getContentPane().add(rankingPanel3);
			revalidate();
			repaint();
		}else if(panelName.equals("ranking4")) {
			getContentPane().removeAll();
			getContentPane().add(rankingPanel4);
			revalidate();
			repaint();
		}else if(panelName.equals("ranking5")) {
			getContentPane().removeAll();
			getContentPane().add(rankingPanel5);
			revalidate();
			repaint();
		}else if(panelName.equals("ranking6")) {
			getContentPane().removeAll();
			getContentPane().add(rankingPanel6);
			revalidate();
			repaint();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		main x = new main();
		x.firstPanel = new first(x);
		x.gamemainPanel = new gamemain(x);
		x.rankmainPanel = new rankmain(x);
		try {
			x.rankingPanel3 = new ranking(x,3);
			x.rankingPanel4 = new ranking(x,4);
			x.rankingPanel5 = new ranking(x,5);
			x.rankingPanel6 = new ranking(x,6);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		x.add(x.firstPanel);
		x.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		x.setSize(600,600);
		x.setLocationRelativeTo(null);
		x.setVisible(true);
	}
}

class first extends JPanel{
	private main x;
	
	public first(main x) {
		this.x = x;
		setLayout(null);
		setBackground(new Color(250,248,239));

		JLabel gamename = new JLabel();
		gamename.setBounds(100,50,400,200);
		gamename.setText("2048");
		gamename.setFont(new Font("SansSerif",Font.BOLD,80));
		gamename.setHorizontalAlignment(JLabel.CENTER);
		add(gamename);
		
		newBtn startbtn = new newBtn("게임시작");
		startbtn.setFont(new Font("나눔고딕",Font.BOLD,20));
		newBtn rankbtn = new newBtn("랭킹");
		rankbtn.setFont(new Font("나눔고딕",Font.BOLD,20));
		newBtn exitbtn = new newBtn("게임종료");
		exitbtn.setFont(new Font("나눔고딕",Font.BOLD,20));
		
		
		startbtn.setBounds(200,250,200,30);
		rankbtn.setBounds(200,330,200,30);
		exitbtn.setBounds(200,410,200,30);
		
		startbtn.addActionListener(new btnListener());
		rankbtn.addActionListener(new btnListener());
		exitbtn.addActionListener(new btnListener());
		
		add(startbtn);
		add(rankbtn);
		add(exitbtn);
	}
	
	 @Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D graphics = (Graphics2D) g;
	    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
	    Color x = new Color(250,224,212);
	    g.setColor(x);
        g.fillRoundRect(180, 230, 240, 230, 15, 15);
	 
	}
	
	public class btnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String actionCmd = e.getActionCommand();
			if(actionCmd.equals("게임시작")) {
				x.change("gamemain");
			}else if(actionCmd.equals("랭킹")) {
				try {
					x.rankingPanel3 = new ranking(x,3);
					x.rankingPanel4 = new ranking(x,4);
					x.rankingPanel5 = new ranking(x,5);
					x.rankingPanel6 = new ranking(x,6);
				} catch (IOException e1) {
					e1.printStackTrace();
				}//랭킹 누를때마다 갱신
				
				x.change("rankmain");				
			}else if(actionCmd.equals("게임종료")) {
				System.exit(0);
			}else {}
		}
	}
}

class gamemain extends JPanel{
	private JButton resume;
	private newBtn three, four, five ,six;
	private main x;
	
	public gamemain(main x) {
		this.x=x;
		setLayout(null);
		setBackground(new Color(250,248,239));
		
		
		three = new newBtn("3x3");
		three.setBounds(100,100,150,150);
		three.setFont(new Font("SansSerif",Font.BOLD,80));
		add(three);
		
		four = new newBtn("4x4");
		four.setBounds(350,100,150,150);
		four.setFont(new Font("SansSerif",Font.BOLD,80));
		add(four);
		
		five = new newBtn("5x5");
		five.setBounds(100,300,150,150);
		five.setFont(new Font("SansSerif",Font.BOLD,80));
		add(five);
		
		six = new newBtn("6x6");
		six.setBounds(350,300,150,150);
		six.setFont(new Font("SansSerif",Font.BOLD,80));
		add(six);
		
		resume = new newBtn("이전");
		resume.setSize(70,20);
		resume.setLocation(100, 500);
		add(resume);

		three.addActionListener(new MyActionListener());
		four.addActionListener(new MyActionListener());
		five.addActionListener(new MyActionListener());
		six.addActionListener(new MyActionListener());
		resume.addActionListener(new MyActionListener());
	}
	
	 @Override
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D graphics = (Graphics2D) g;
		    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    
		    Color x = new Color(250,224,212);
		    g.setColor(x);
	        g.fillRoundRect(95, 95, 160, 160, 15, 15);
	        g.fillRoundRect(345, 95, 160, 160, 15, 15);
	        g.fillRoundRect(95, 295, 160, 160, 15, 15);
	        g.fillRoundRect(345, 295, 160, 160, 15, 15);
		 
		}
	
	public class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String actionCmd = e.getActionCommand();
			if(actionCmd.equals("3x3")) {
				Ingame gamedisplay = new Ingame(3);
				gamedisplay.play(3);
			}else if(actionCmd.equals("4x4")) {
				Ingame gamedisplay = new Ingame(4);
				gamedisplay.play(4);
			}else if(actionCmd.equals("5x5")){
				Ingame gamedisplay = new Ingame(5);
				gamedisplay.play(5);
			}else if(actionCmd.equals("6x6")) {
				Ingame gamedisplay = new Ingame(6);
				gamedisplay.play(6);
			}else {
				x.change("first");
			}
		}
		
	}
}



class ranking extends JPanel{
	private newBtn resume;
	private main x;
	private int filenumber;
	
	public ranking(main x,int mode) throws IOException {
		this.x=x;
		filenumber=mode;
		setLayout(null);
		setBackground(new Color(250,248,239));
		Font f1 = new Font("SansSerif",Font.BOLD,20);
		Font f2 = new Font("SansSerif",Font.BOLD,18);
		Font f3 = new Font("SansSerif",Font.PLAIN,18);
		
		RankingDemo();
		int t1=70;
		
		JLabel ranktitle = new JLabel("랭  킹");
		if(filenumber==3) {
			ranktitle = new JLabel("3x3 랭 킹");
		}else if(filenumber==4){
			ranktitle = new JLabel("4x4 랭 킹");
		}else if(filenumber==5){
			ranktitle = new JLabel("5x5 랭 킹");
		}else if(filenumber==6){
			ranktitle = new JLabel("6x6 랭 킹");
		}
		ranktitle.setFont(new Font("나눔고딕",Font.BOLD,40));
		ranktitle.setSize(200, 80);
		ranktitle.setLocation(200, 0);
		add(ranktitle);
		
		JPanel[] rankpanel = new JPanel[11];
		for(int i =0;i<11;i++) {
			rankpanel[i]=new JPanel();
			rankpanel[i].setBackground(new Color(255,255,204,200));
			rankpanel[i].setSize(500, 36);
			rankpanel[i].setLocation(40, t1);
			rankpanel[i].setLayout(new GridLayout(1,4));
			add(rankpanel[i]);
			t1+=36;
		}
		JLabel rankword = new JLabel("Rank");
		rankword.setFont(f1);
		JLabel nameword = new JLabel("Name");
		nameword.setFont(f1);
		JLabel scoreword = new JLabel("Score");
		scoreword.setFont(f1);
		JLabel dateword = new JLabel("Date");
		dateword.setFont(f1); //폰트변경을 위한 부분
		
		rankpanel[0].add(rankword);
		rankpanel[0].add(nameword);
		rankpanel[0].add(scoreword);
		rankpanel[0].add(dateword);
		
		
		for(int i =1;i<=count+1;i++) {
			JLabel rankfont;
			if(i==1) {
				rankfont = new JLabel(Integer.toString(rank.get(i-1).getRank())+"st");
			}else if(i==2){
				rankfont = new JLabel(Integer.toString(rank.get(i-1).getRank())+"nd");
			}else if(i==3) {
				rankfont = new JLabel(Integer.toString(rank.get(i-1).getRank())+"rd");			
			}else {
				rankfont = new JLabel(Integer.toString(rank.get(i-1).getRank())+"th");
			}
			JLabel namefont = new JLabel(rank.get(i-1).getName());
			JLabel scorefont = new JLabel(Integer.toString(rank.get(i-1).getScore()));
			JLabel datefont = new JLabel(rank.get(i-1).getDate());
			
			rankfont.setFont(f2);
			namefont.setFont(f2);
			scorefont.setFont(f3);
			datefont.setFont(f3);
			
			rankpanel[i].add(rankfont);
			rankpanel[i].add(namefont);
			rankpanel[i].add(scorefont);
			rankpanel[i].add(datefont);
			
		}
		
		resume = new newBtn("이전");
		resume.setSize(70,20);
		resume.setLocation(100,500);
		add(resume);
	
		resume.addActionListener(new MyActionListener());
	}
	
	public class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			x.change("rankmain"); //랭킹화면으로간다.
		}
	}
	
	//파일 읽어오기
	public final static int MAX=11;//10명까지표시하겠다. 추가될것 까지 11개
	public String fileName3 = "Ranking3.txt";
	public String fileName4 = "Ranking4.txt";
	public String fileName5 = "Ranking5.txt";
	public String fileName6 = "Ranking6.txt";//파일명
	public ArrayList<RankSystem> rank = new ArrayList<RankSystem>(MAX);//순위하나당 저장할곳
	public String[] sentence = new String[MAX];
	public int  count = 0;
	public String text="1 aaa 0000 00000000";
		
	public void RankingDemo() throws IOException {
		File f= new File("");
		if(filenumber==3) {
			f = new File(fileName3);
		}else if(filenumber==4){
			f = new File(fileName4);
		}else if(filenumber==5){
			f = new File(fileName5);
		}else if(filenumber==6){
			f = new File(fileName6);
		}
			
		
		if(f.isFile()) {//Ranking.txt파일이 존재하면
			
			play(f);
		
		}else {//Ranking.txt파일이 존재하지않으면
			try {
			
				FileWriter fw = new FileWriter(f,false);//파일생성
				fw.write(text);
				fw.flush();//적용
				fw.close();
				
				play(f);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void play(File file) throws IOException {
		FileReader fr = new FileReader(file);
		
		int c;
		while((c=fr.read())!=-1)
		{
			sentence[count]+=(char)c;
			
			if(c=='\n') {
				count++;
			}
		}
		for(int i=0;i<=count;i++) {
			sentence[i]=sentence[i].substring(4);
			String[] memo=new String[4];
			memo=sentence[i].split(" ");
			rank.add(new RankSystem(memo[0],memo[1],memo[2],memo[3].substring(0,8)));
		}
	
		fr.close();
	}
}

class rankmain extends JPanel{
	private JButton resume;
	private newBtn three, four, five ,six;
	private main x;
	
	public rankmain(main x) {
		this.x=x;
		setLayout(null);
		setBackground(new Color(250,248,239));
		
		
		JLabel ranktitle = new JLabel("랭  킹");
		ranktitle.setFont(new Font("나눔고딕",Font.BOLD,50));
		ranktitle.setSize(200, 100);
		ranktitle.setLocation(230, 0);
		add(ranktitle);
		
		three = new newBtn("3x3");
		three.setBounds(100,100,150,150);
		three.setFont(new Font("SansSerif",Font.BOLD,80));
		add(three);
		
		four = new newBtn("4x4");
		four.setBounds(350,100,150,150);
		four.setFont(new Font("SansSerif",Font.BOLD,80));
		add(four);
		
		five = new newBtn("5x5");
		five.setBounds(100,300,150,150);
		five.setFont(new Font("SansSerif",Font.BOLD,80));
		add(five);
		
		six = new newBtn("6x6");
		six.setBounds(350,300,150,150);
		six.setFont(new Font("SansSerif",Font.BOLD,80));
		add(six);
		
		resume = new newBtn("이전");
		resume.setSize(70,20);
		resume.setLocation(100, 500);
		add(resume);

		three.addActionListener(new MyActionListener());
		four.addActionListener(new MyActionListener());
		five.addActionListener(new MyActionListener());
		six.addActionListener(new MyActionListener());
		resume.addActionListener(new MyActionListener());
	}
	
	 @Override
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D graphics = (Graphics2D) g;
		    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    
		    Color x = new Color(250,224,212);
		    g.setColor(x);
	        g.fillRoundRect(95, 95, 160, 160, 15, 15);
	        g.fillRoundRect(345, 95, 160, 160, 15, 15);
	        g.fillRoundRect(95, 295, 160, 160, 15, 15);
	        g.fillRoundRect(345, 295, 160, 160, 15, 15);
		 
		}
	
	public class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String actionCmd = e.getActionCommand();
			if(actionCmd.equals("3x3")) {
				x.change("ranking3");
			}else if(actionCmd.equals("4x4")) {
				x.change("ranking4");
			}else if(actionCmd.equals("5x5")){
				x.change("ranking5");
			}else if(actionCmd.equals("6x6")) {
				x.change("ranking6");
			}else {
				x.change("first");
			}
		}
		
	}
}

class Ingame extends JPanel {
	public static int regame=0;

	final Color[] colorTable = {
	        new Color(0xfeddc3), new Color(0xfdc395), new Color(0xfcb173),
	        new Color(0xfb9a4b), new Color(0xfb8423), new Color(0xf06f05),
	        new Color(0xc85c04), new Color(0xa04a03), new Color(0x783702),
	        new Color(0x502502), new Color(0x281201), new Color(0x140900)};
	private static int blocksize=4;
	
	static JLabel scorelabel = new JLabel();
	static JLabel backlabel =new JLabel();
	private static int[][] game; // 게임판
	private static int[][] before; // 이동하기 전 저장
	private static int[][] temp; // temp배열
	private static int point; // 블럭 생성 변수
	private static int move = 0; // 움직였는지 확인 변수
	private static int score = 0;
	private static int scoretemp = 0;
	private static int movecount=0;
	public static int savescore = 0;
	public static int beforescore=0;
	public static int beforemove=0;
	public JFrame frame;
	public static String fileName3 = "Game3.txt";
	public static String fileName4 = "Game4.txt";
	public static String fileName5 = "Game5.txt";
	public static String fileName6 = "Game6.txt";//파일명
	public static File f = new File("");
	public static boolean clear= false;

	public static void randomblock() { // 빈블럭에 랜덤으로 2or4블럭 생성
		int row, col;

		Random random = new Random();
		point = random.nextInt(blocksize*blocksize);
		row = point / blocksize;
		col = point % blocksize;

		while (game[row][col] != 0) {

			point = random.nextInt(blocksize*blocksize);
			row = point / blocksize;
			col = point % blocksize;

		}

		if (random.nextBoolean())
			game[row][col] = 2;
		else
			game[row][col] = 4;

	}

	public static void gamecopytobefore() { // before 배열에 game배열 저장
		for (int i = 0; i < blocksize; i++)
			for (int j = 0; j < blocksize; j++)
				before[i][j] = game[i][j];
	}

	public static void gamecopytotemp() { // game 배열에 temp배열 저장
		for (int i = 0; i < blocksize; i++)
			for (int j = 0; j < blocksize; j++)
				temp[i][j] = game[i][j];
	}

	public static void beforecopytogame() { // game 배열에 before배열 저장
		for (int i = 0; i < blocksize; i++)
			for (int j = 0; j < blocksize; j++)
				game[i][j] = before[i][j];
		score=beforescore;
	}

	public static void beforecopytotemp() { // game 배열에 temp배열 저장
		for (int i = 0; i < blocksize; i++)
			for (int j = 0; j < blocksize; j++)
				temp[i][j] = before[i][j];
		
	}

	public static void tempcopytobefore() { // game 배열에 temp배열 저장
		for (int i = 0; i < blocksize; i++)
			for (int j = 0; j < blocksize; j++)
				before[i][j] = temp[i][j];
	}

	public static void tempcopytogame() { // game 배열에 temp배열 저장
		for (int i = 0; i < blocksize; i++)
			for (int j = 0; j < blocksize; j++)
				game[i][j] = temp[i][j];
	}

	public static void leftshift() { // 블럭을 왼쪽으로 정렬
		int p;

		for (int i = 0; i < blocksize; i++)
			for (int j = 0; j < blocksize; j++) {
				p = j;

				if (game[i][j] != 0) {
					while (p > 0 && game[i][p - 1] == 0) {
						game[i][p - 1] = game[i][p];
						game[i][p] = 0;
						move = 1;
						p--;
					}
					if (p > 0 && game[i][p - 1] == game[i][p]) {
						game[i][p - 1] += game[i][p];
						score += game[i][p] * 2;
						game[i][p] = 0;
						move = 1;
						p--;
					}
				}
			}

	}

	public static void rightshift() { // 블럭을 오른쪽으로 정렬
		int p;
		for (int i = blocksize-1; i >= 0; i--)
			for (int j = blocksize-1; j >= 0; j--) {
				p = j;

				if (game[i][j] != 0) {
					while (p < blocksize-1 && game[i][p + 1] == 0) {
						game[i][p + 1] = game[i][p];
						game[i][p] = 0;
						move = 1;
						p++;
					}
					if (p < blocksize-1 && game[i][p + 1] == game[i][p]) {
						game[i][p + 1] += game[i][p];
						score += game[i][p] * 2;
						game[i][p] = 0;
						move = 1;
						p++;
					}
				}
			}
	}
	
	public static void upshift() { // 블럭을 위쪽으로 정렬
		int p;
		for (int i = 0; i < blocksize; i++)
			for (int j = 0; j < blocksize; j++) {
				p = j;

				if (game[j][i] != 0) {
					while (p > 0 && game[p - 1][i] == 0) {
						game[p - 1][i] = game[p][i];
						game[p][i] = 0;
						move = 1;
						p--;
					}
					if (p > 0 && game[p - 1][i] == game[p][i]) {
						game[p - 1][i] += game[p][i];
						score += game[p][i] * 2;
						game[p][i] = 0;
						move = 1;
						p--;
					}
				}
			}
	}

	public static void downshift() { // 블럭을 아래쪽으로 정렬
		int p;

		for (int i = blocksize-1; i >= 0; i--)
			for (int j = blocksize-1; j >= 0; j--) {
				p = j;

				if (game[j][i] != 0) {
					while (p < blocksize-1 && game[p + 1][i] == 0) {
						game[p + 1][i] = game[p][i];
						game[p][i] = 0;
						move = 1;
						p++;
					}
					if (p < blocksize-1 && game[p + 1][i] == game[p][i]) {
						game[p + 1][i] += game[p][i];
						score += game[p][i] * 2;
						game[p][i] = 0;
						move = 1;
						p++;
					}
				}
			}
	}

	public static void reset() {
		score = 0;
		scoretemp=0;
		movecount=0;
		clear=false;
		for (int i = 0; i < blocksize; i++) {
			for (int j = 0; j < blocksize; j++) {
				game[i][j] = 0;

			}
		}
	}

	public static boolean checkGameover() {
		rightshift();
		tempcopytogame();
		for (int i = 0; i < blocksize; i++) {
			for (int j = 0; j < blocksize; j++) {
				if(blocksize==3) {
					if (game[i][j] == 1024) {
						clear=true;
						return true;
					}
				}else if(blocksize==4) {
					if (game[i][j] == 2048) {
						clear=true;
						return true;
					}
				}else {
					if (game[i][j] == 4096) {
					clear=true;
					return true;
					}	
				}
			}
		}
		if (move == 0) {
			leftshift();
			tempcopytogame();
			if (move == 0) {
				downshift();
				tempcopytogame();
				if (move == 0) {
					upshift();
					tempcopytogame();
					if (move == 0) {
						return true;
					}
				}
			}
		}

		return false;
	}
	
public void play(int mode) {
		
		frame = new JFrame("2048");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel board = new Ingame(mode);
		
		scorelabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		scorelabel.setText("score: " + Integer.toString(score)+"  move: "+movecount);
		scorelabel.setBounds(35, 0, 600, 50);
		
		JLabel resetlabel = new JLabel();
		resetlabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		resetlabel.setText("Press 'R' to reset");
		resetlabel.setBounds(400,20,250,50);
		
		backlabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		backlabel.setText("X");
		backlabel.setBounds(400, 0, 250, 50);
						
		board.setLayout(null);
		board.setBackground(new Color(250,248,239));
		
		frame.add(scorelabel);
		frame.add(resetlabel);
		frame.add(backlabel);
		frame.add(board);
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		regame = 0;
	}
	
	public Ingame(int mode) {
		blocksize = mode;
		game = new int[blocksize][blocksize]; // 게임판
		before = new int[blocksize][blocksize]; // 이동하기 전 저장
		temp = new int[blocksize][blocksize];
		score = 0;
		movecount = 0;
		beforescore=0;
		if(blocksize==3) {
			f = new File(fileName3);
		}else if(blocksize==4){
			f = new File(fileName4);
		}else if(blocksize==5){
			f = new File(fileName5);
		}else if(blocksize==6){
			f = new File(fileName6);
		}
		
		setLayout(null);
		setFocusable(true);
		numBtn[][] button = new numBtn[blocksize+1][blocksize+1];
		for (int i = 0; i < blocksize; i++) {
			for (int j = 0; j < blocksize; j++) {
				button[i][j] = new numBtn();
				add(button[i][j]);
			}
		}

		
		if(mode==3) {	
			for (int i=0; i<mode;i++) {
				for(int j=0;j<mode;j++) {
					button[i][j].setBounds(100+j*140, 70+i*140, 120, 120);
				}
			}
		}
		
		if(mode==4) {
			for (int i=0; i<mode;i++) {
				for(int j=0;j<mode;j++) {
					button[i][j].setBounds(100+j*104, 70+i*104, 88, 88);
				}
			}
		}
		
		if(mode==5) {
			for (int i=0; i<mode;i++) {
				for(int j=0;j<mode;j++) {
					button[i][j].setBounds(100+j*83, 70+i*83, 68, 68);
				}
			}
		}
		
		if(mode==6) {
			for (int i=0; i<mode;i++) {
				for(int j=0;j<mode;j++) {
					button[i][j].setBounds(100+j*68, 70+i*68, 60, 60);
				}
			}
		}
		
		randomblock();
		randomblock();
		
		if(f.isFile()) {
			FileReader fr;
			try {
				fr = new FileReader(f);
				String sentence = null;
				String[] memo = new String[blocksize*blocksize+2];
				int c;
				int counts=0;
				try {
					while((c=fr.read())!=-1)
					{
						sentence+=(char)c;
					}
					sentence=sentence.substring(4);
					memo=sentence.split(" ");
					fr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i < blocksize; i++) {
					for (int j = 0; j < blocksize; j++) {
						game[i][j]=Integer.parseInt(memo[counts]);
						counts++;
					}
				}
				score=Integer.parseInt(memo[counts]);
				movecount=Integer.parseInt(memo[counts+1]);
				beforescore=score;
				beforemove=movecount;
				
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}

		gamecopytotemp();
		gamecopytobefore();
		
		for (int i = 0; i < blocksize; i++) {
			for (int j = 0; j < blocksize; j++) {
				if (game[i][j] != 0) {
					button[i][j].setText(Integer.toString(game[i][j]));
				}
				else
					button[i][j].setText("");
			}
		}
		
		for (int i = 0; i < blocksize; i++) {
			for (int j = 0; j < blocksize; j++) {
				String text = button[i][j].getText();
				switch(text) {
				case "2":
					button[i][j].setBackground(colorTable[0]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
					button[i][j].setForeground(Color.BLACK);
					break;
				case "4":
					button[i][j].setBackground(colorTable[1]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
					button[i][j].setForeground(Color.BLACK);
					break;
				case "8":
					button[i][j].setBackground(colorTable[2]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
					button[i][j].setForeground(Color.BLACK);
					break;
				case "16":
					button[i][j].setBackground(colorTable[3]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
					button[i][j].setForeground(Color.BLACK);
					break;
				case "32":
					button[i][j].setBackground(colorTable[4]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
					button[i][j].setForeground(Color.BLACK);
					break;
				case "64":
					button[i][j].setBackground(colorTable[5]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
					button[i][j].setForeground(Color.BLACK);
					break;
				case "128":
					button[i][j].setBackground(colorTable[6]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
					button[i][j].setForeground(Color.WHITE);
					break;
				case "256":
					button[i][j].setBackground(colorTable[7]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
					button[i][j].setForeground(Color.WHITE);
					break;
				case "512":
					button[i][j].setBackground(colorTable[8]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
					button[i][j].setForeground(Color.WHITE);
					break;
				case "1024":
					button[i][j].setBackground(colorTable[9]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
					button[i][j].setForeground(Color.WHITE);
					break;
				case "2048":
					button[i][j].setBackground(colorTable[10]);
					button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
					button[i][j].setForeground(Color.WHITE);
					break;
				default:
					button[i][j].setBackground(Color.WHITE);
					break;
				}
			}
		}
		
		addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					beforemove =movecount;
					beforescore=score;
					leftshift();
					movecount++;
					
					backlabel.setText("Press backspace to undo");
					
					if (move == 1) {
						randomblock();
						tempcopytobefore();
						gamecopytotemp();
					}else
						movecount--;
					scoretemp = score;
					
					try {						
						FileWriter fw = new FileWriter(f,false);//파일생성
						
						for (int i = 0; i < blocksize; i++) {
							for (int j = 0; j < blocksize; j++) {
								fw.write(Integer.toString(game[i][j])+" ");
							}
						}
						fw.write(Integer.toString(score)+" "+movecount);
						fw.flush();//적용
						fw.close();
						}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					if (checkGameover()) {
						System.out.println("Game Over");
						savescore=score;
						f.delete();
						GameOverWindow win = new GameOverWindow(clear);
						win.setLocationRelativeTo(null);
						win.setVisible(true);
						reset();
						randomblock();
						randomblock();
						gamecopytotemp();
						gamecopytobefore();
					}
					score = scoretemp;
					
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							scorelabel.setText("score: " + Integer.toString(score)+"  move: "+movecount);
							if (game[i][j] != 0) {
								button[i][j].setText(Integer.toString(game[i][j]));
							}
							else
								button[i][j].setText("");
						}
					}

					move = 0;
					for (int i = 0; i < blocksize; i++) {

						for (int j = 0; j < blocksize; j++) {
							System.out.print(game[i][j] + " ");
						}
						System.out.println();

					}
					
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							String text = button[i][j].getText();
							switch(text) {
							case "2":
								button[i][j].setBackground(colorTable[0]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "4":
								button[i][j].setBackground(colorTable[1]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "8":
								button[i][j].setBackground(colorTable[2]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "16":
								button[i][j].setBackground(colorTable[3]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "32":
								button[i][j].setBackground(colorTable[4]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "64":
								button[i][j].setBackground(colorTable[5]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "128":
								button[i][j].setBackground(colorTable[6]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "256":
								button[i][j].setBackground(colorTable[7]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "512":
								button[i][j].setBackground(colorTable[8]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "1024":
								button[i][j].setBackground(colorTable[9]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "2048":
								button[i][j].setBackground(colorTable[10]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							default:
								button[i][j].setBackground(Color.WHITE);
								break;
							}
						}
					}

				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					beforemove =movecount;
					beforescore=score;
					rightshift();
					movecount++;
					
					backlabel.setText("Press backspace to undo");
					
					if (move == 1) {
						randomblock();
						tempcopytobefore();
						gamecopytotemp();
					}else
						movecount--;
					scoretemp = score;

					try {						
						FileWriter fw = new FileWriter(f,false);//파일생성
						
						for (int i = 0; i < blocksize; i++) {
							for (int j = 0; j < blocksize; j++) {
								fw.write(Integer.toString(game[i][j])+" ");
							}
						}
						fw.write(Integer.toString(score)+" "+movecount);
						fw.flush();//적용
						fw.close();
						}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					if (checkGameover()) {
						System.out.println("Game Over");
						savescore=score;
						f.delete();
						GameOverWindow win = new GameOverWindow(clear);
						win.setLocationRelativeTo(null);
						win.setVisible(true);
						reset();
						randomblock();
						randomblock();
						gamecopytotemp();
						gamecopytobefore();
					}
					score = scoretemp;
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							scorelabel.setText("score: " + Integer.toString(score)+"  move: "+movecount);
							if (game[i][j] != 0) {
								button[i][j].setText(Integer.toString(game[i][j]));
								
							}
							else
								button[i][j].setText("");
						}
					}

					move = 0;
					for (int i = 0; i < blocksize; i++) {

						for (int j = 0; j < blocksize; j++) {
							System.out.print(game[i][j] + " ");
						}
						System.out.println();

					}
					
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							String text = button[i][j].getText();
							switch(text) {
							case "2":
								button[i][j].setBackground(colorTable[0]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "4":
								button[i][j].setBackground(colorTable[1]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "8":
								button[i][j].setBackground(colorTable[2]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "16":
								button[i][j].setBackground(colorTable[3]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "32":
								button[i][j].setBackground(colorTable[4]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "64":
								button[i][j].setBackground(colorTable[5]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "128":
								button[i][j].setBackground(colorTable[6]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "256":
								button[i][j].setBackground(colorTable[7]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "512":
								button[i][j].setBackground(colorTable[8]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "1024":
								button[i][j].setBackground(colorTable[9]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "2048":
								button[i][j].setBackground(colorTable[10]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							default:
								button[i][j].setBackground(Color.WHITE);
								break;
							}
						}
					}


				}
				if(e.getKeyCode()==KeyEvent.VK_UP) {
					beforemove =movecount;
					beforescore=score;
					upshift();
					movecount++;
					
					backlabel.setText("Press backspace to undo");
					
					if (move == 1) {
						randomblock();
						tempcopytobefore();
						gamecopytotemp();
					}else
						movecount--;
					scoretemp = score;

					try {						
						FileWriter fw = new FileWriter(f,false);//파일생성
						
						for (int i = 0; i < blocksize; i++) {
							for (int j = 0; j < blocksize; j++) {
								fw.write(Integer.toString(game[i][j])+" ");
							}
						}
						fw.write(Integer.toString(score)+" "+movecount);
						fw.flush();//적용
						fw.close();
						}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					if (checkGameover()) {
						System.out.println("Game Over");
						savescore=score;
						f.delete();
						GameOverWindow win = new GameOverWindow(clear);
						win.setLocationRelativeTo(null);
						win.setVisible(true);
						reset();
						randomblock();
						randomblock();
						gamecopytotemp();
						gamecopytobefore();
					}
					score = scoretemp;
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							scorelabel.setText("score: " + Integer.toString(score)+"  move: "+movecount);
							if (game[i][j] != 0) {
								button[i][j].setText(Integer.toString(game[i][j]));
								
							}
							else
								button[i][j].setText("");
						}
					}

					move = 0;
					for (int i = 0; i < blocksize; i++) {

						for (int j = 0; j < blocksize; j++) {
							System.out.print(game[i][j] + " ");
						}
						System.out.println();

					}
					
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							String text = button[i][j].getText();
							switch(text) {
							case "2":
								button[i][j].setBackground(colorTable[0]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "4":
								button[i][j].setBackground(colorTable[1]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "8":
								button[i][j].setBackground(colorTable[2]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "16":
								button[i][j].setBackground(colorTable[3]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "32":
								button[i][j].setBackground(colorTable[4]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "64":
								button[i][j].setBackground(colorTable[5]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "128":
								button[i][j].setBackground(colorTable[6]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "256":
								button[i][j].setBackground(colorTable[7]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "512":
								button[i][j].setBackground(colorTable[8]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "1024":
								button[i][j].setBackground(colorTable[9]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "2048":
								button[i][j].setBackground(colorTable[10]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							default:
								button[i][j].setBackground(Color.WHITE);
								break;
							}
						}
					}


				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					beforemove =movecount;
					beforescore=score;
					downshift();
					movecount++;
					
					backlabel.setText("Press backspace to undo");
					
					if (move == 1) {
						randomblock();
						tempcopytobefore();
						gamecopytotemp();
					}else
						movecount--;
					scoretemp = score;
					if (checkGameover()) {
						System.out.println("Game Over");
						savescore=score;
						f.delete();
						GameOverWindow win = new GameOverWindow(clear);
						win.setLocationRelativeTo(null);
						win.setVisible(true);
						reset();
						randomblock();
						randomblock();
						gamecopytotemp();
						gamecopytobefore();
					}
					score = scoretemp;

					try {						
						FileWriter fw = new FileWriter(f,false);//파일생성
						
						for (int i = 0; i < blocksize; i++) {
							for (int j = 0; j < blocksize; j++) {
								fw.write(Integer.toString(game[i][j])+" ");
							}
						}
						fw.write(Integer.toString(score)+" "+movecount);
						fw.flush();//적용
						fw.close();
						}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							scorelabel.setText("score: " + Integer.toString(score)+"  move: "+movecount);
							if (game[i][j] != 0) {
								button[i][j].setText(Integer.toString(game[i][j]));
								
							}
							else
								button[i][j].setText("");
						}
					}

					move = 0;
					for (int i = 0; i < blocksize; i++) {

						for (int j = 0; j < blocksize; j++) {
							System.out.print(game[i][j] + " ");
						}
						System.out.println();

					}
					
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							String text = button[i][j].getText();
							switch(text) {
							case "2":
								button[i][j].setBackground(colorTable[0]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "4":
								button[i][j].setBackground(colorTable[1]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "8":
								button[i][j].setBackground(colorTable[2]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "16":
								button[i][j].setBackground(colorTable[3]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "32":
								button[i][j].setBackground(colorTable[4]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "64":
								button[i][j].setBackground(colorTable[5]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "128":
								button[i][j].setBackground(colorTable[6]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "256":
								button[i][j].setBackground(colorTable[7]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "512":
								button[i][j].setBackground(colorTable[8]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "1024":
								button[i][j].setBackground(colorTable[9]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "2048":
								button[i][j].setBackground(colorTable[10]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							default:
								button[i][j].setBackground(Color.WHITE);
								break;
							}
						}
					}


				}
				if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
					beforecopytogame();
					beforecopytotemp();
					
					backlabel.setText("X");
					movecount=beforemove;
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							scorelabel.setText("score: " + Integer.toString(beforescore)+"  move: "+movecount);
							if (game[i][j] != 0) {
								button[i][j].setText(Integer.toString(game[i][j]));
							}
							else
								button[i][j].setText("");
						}
					}

					move = 0;
					for (int i = 0; i < blocksize; i++) {

						for (int j = 0; j < blocksize; j++) {
							System.out.print(game[i][j] + " ");
						}
						System.out.println();

					}
					
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							String text = button[i][j].getText();
							switch(text) {
							case "2":
								button[i][j].setBackground(colorTable[0]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "4":
								button[i][j].setBackground(colorTable[1]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "8":
								button[i][j].setBackground(colorTable[2]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "16":
								button[i][j].setBackground(colorTable[3]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "32":
								button[i][j].setBackground(colorTable[4]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "64":
								button[i][j].setBackground(colorTable[5]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "128":
								button[i][j].setBackground(colorTable[6]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "256":
								button[i][j].setBackground(colorTable[7]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "512":
								button[i][j].setBackground(colorTable[8]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "1024":
								button[i][j].setBackground(colorTable[9]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "2048":
								button[i][j].setBackground(colorTable[10]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							default:
								button[i][j].setBackground(Color.WHITE);
								break;
							}
						}
					}					
				}
				if(e.getKeyCode()==KeyEvent.VK_R) {
					beforemove = movecount;
					beforescore = score;
					tempcopytobefore();
					gamecopytotemp();
					f.delete();
					reset();
					randomblock();
					randomblock();
					score=0;
					movecount=0;
					
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							scorelabel.setText("score: " + Integer.toString(score)+"  move: "+movecount);
							if (game[i][j] != 0) {
								button[i][j].setText(Integer.toString(game[i][j]));
							}
							else
								button[i][j].setText("");
						}
					}

					move = 0;
					for (int i = 0; i < blocksize; i++) {

						for (int j = 0; j < blocksize; j++) {
							System.out.print(game[i][j] + " ");
						}
						System.out.println();

					}
					
					for (int i = 0; i < blocksize; i++) {
						for (int j = 0; j < blocksize; j++) {
							String text = button[i][j].getText();
							switch(text) {
							case "2":
								button[i][j].setBackground(colorTable[0]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "4":
								button[i][j].setBackground(colorTable[1]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "8":
								button[i][j].setBackground(colorTable[2]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "16":
								button[i][j].setBackground(colorTable[3]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "32":
								button[i][j].setBackground(colorTable[4]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "64":
								button[i][j].setBackground(colorTable[5]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 48));
								button[i][j].setForeground(Color.BLACK);
								break;
							case "128":
								button[i][j].setBackground(colorTable[6]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "256":
								button[i][j].setBackground(colorTable[7]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "512":
								button[i][j].setBackground(colorTable[8]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 36));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "1024":
								button[i][j].setBackground(colorTable[9]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							case "2048":
								button[i][j].setBackground(colorTable[10]);
								button[i][j].setFont(new Font("SansSerif", Font.BOLD, 28));
								button[i][j].setForeground(Color.WHITE);
								break;
							default:
								button[i][j].setBackground(Color.WHITE);
								break;
							}
						}
					}					
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}			
		});		
	}
	
	
	
	private class GameOverWindow extends JFrame implements ActionListener{
		public String name;
		public JTextField namespace;

		public GameOverWindow(boolean clear) {
		    Color x = new Color(250,248,239);
			setSize(300,200);
			if(clear) setTitle("Clear!");
			else setTitle("GameOver");
			getContentPane().setBackground(x);
			setLayout(new BorderLayout());
			
			JPanel LabelPanel = new JPanel();
			LabelPanel.setBackground(x);
			if(clear) LabelPanel.setLayout(new GridLayout(4,1));
			else LabelPanel.setLayout(new GridLayout(3,1));
			
			JLabel Label1 = new JLabel("Score: "+Integer.toString(savescore));
			JLabel Label2 = new JLabel("Move: "+Integer.toString(movecount));
			JLabel LabelClear = new JLabel("Congratulations! Clear!");
			namespace = new JTextField("What's your name?",20);
			Label1.setFont(new Font("SansSerif", Font.BOLD, 18));
			Label2.setFont(new Font("SansSerif", Font.BOLD, 18));
			LabelClear.setFont(new Font("SansSerif", Font.BOLD, 20));
			if(clear) LabelPanel.add(LabelClear);
			LabelPanel.add(Label1);
			LabelPanel.add(Label2);
			LabelPanel.add(namespace);
			
			add(LabelPanel,BorderLayout.CENTER);
			
			newBtn saveBtn = new newBtn("Save");
			saveBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
			saveBtn.addActionListener(this);
			add(saveBtn,BorderLayout.SOUTH);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			if(actionCommand.equals("Save")) {
				name=namespace.getText();
				Save save = new Save();
				try {
					save.Save(name,savescore);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		}
		
	}
	
	public static class Save {
		public final static int MAX=11;//10명까지표시하겠다. 추가될것 까지 11개
		public static String fileName3 = "Ranking3.txt";
		public static String fileName4 = "Ranking4.txt";
		public static String fileName5 = "Ranking5.txt";
		public static String fileName6 = "Ranking6.txt";//파일명
		public static ArrayList<RankSystem> rank= new ArrayList<RankSystem>(MAX);//순위하나당 저장할곳
		public static String[] sentence;
		public static int count = 0;
		public static String text="1 aaa 0000 00000000";
				
		public void Save(String name, int savescore) throws IOException {
			File f= new File("");
			sentence = new String[MAX];
			rank.clear();
			if(blocksize==3) {
				f = new File(fileName3);
			}else if(blocksize==4){
				f = new File(fileName4);
			}else if(blocksize==5){
				f = new File(fileName5);
			}else if(blocksize==6){
				f = new File(fileName6);
			}
				
			
			if(f.isFile()) {//Ranking.txt파일이 존재하면
				count = 0;
				save(f,name,savescore);//Text파일에 있는 정보 불러오기
			
			}else {//Ranking.txt파일이 존재하지않으면
				try {
				
					FileWriter fw = new FileWriter(f,false);//파일생성
					fw.write(text);
					fw.flush();//적용
					fw.close();
					
					count = 0;
					save(f,name,savescore);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public void save(File file, String name, int savescore) throws IOException {
			FileReader fr = new FileReader(file);
			
			int c;
			while((c=fr.read())!=-1)
			{
				sentence[count]+=(char)c;
				
				if(c=='\n') {
					count++;
				}
			}
			for(int i=0;i<=count;i++) {
				sentence[i]=sentence[i].substring(4);
				String[] memo=new String[4];
				memo=sentence[i].split(" ");			
				rank.add(new RankSystem(memo[0],memo[1],memo[2],memo[3]));
			}
			regame++;
			
			rank.set(count, new RankSystem(Integer.toString(rank.get(count).getRank()),rank.get(count).getName(),Integer.toString(rank.get(count).getScore()),rank.get(count).getDate()+"\n"));
			//마지막랭크에 \n이 붙어있으면 에러가뜨고 \n없으면 구분이 안되기때문에 마지막항목만 date뒤에\n추가
			
			count++;
			rank.add(new RankSystem(Integer.toString(count+1),name,Integer.toString(savescore),(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+"\n")));
			rankreset();
	
			fr.close();
			
			rank.set(count, new RankSystem(Integer.toString(rank.get(count).getRank()),rank.get(count).getName(),Integer.toString(rank.get(count).getScore()),rank.get(count).getDate().substring(0,8)));
			
			FileWriter fw2 = new FileWriter(file,false);//파일생성
			for(int i=0;i<=count;i++) {
				fw2.write(rank.get(i).toString());
			}
			fw2.flush();//적용
			fw2.close();
		}
		public static void rankreset() {//랭크 정렬
			for(int i =0;i<count;i++)
			{
				int max=i;
				for(int j=i;j<=count;j++)
				{
					if(rank.get(j).getScore()>rank.get(max).getScore())
						max=j;
				}
				RankSystem temp=new RankSystem(rank.get(max));
				rank.set(max, rank.get(i));
				rank.set(i, temp);
			}
			for(int i = 0;i<=count;i++)//랭킹 숫자 변경
				rank.set(i,new RankSystem(Integer.toString(i+1),rank.get(i).getName(),Integer.toString(rank.get(i).getScore()),rank.get(i).getDate()));
			if(count==(MAX-1)) {//11등 제거
				rank.remove(count);
				count--;
			}
		}
	}
}