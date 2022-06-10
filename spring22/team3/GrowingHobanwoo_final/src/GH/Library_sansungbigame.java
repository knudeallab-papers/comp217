package GH;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import static GH.Character_status.stress;
import static GH.Character_status.hp;
import static GH.Character_status.happy;

import static GH.Main_HOBAN.happy_;

public class Library_sansungbigame extends JFrame implements ActionListener, KeyListener {
	
	ImageIcon icon;
	JPanel startPanel;
	GamePanel gPanel;
	GameThread gThread;
	int i=30; 
	Lecture temp;
	
	static ArrayList<String> words = new ArrayList<String>(); 
	
	static int speed = 1000; 
	
	JTextField answer, inputN; 
	String answerS;
	int mistake = 0;
	Random getRan = new Random();
	
	static int score = 0; 
	
	String time;
	JLabel timeL = new JLabel("Time : 30");
	
	JLabel scoreL = new JLabel("Score : 0");
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Library_sansungbigame gui = new Library_sansungbigame(Regis_page.JAVA); //test
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
	
	class FallingWord {
		int x, y;
		int dy;
		boolean isDead = false;
		JLabel fallingWordL;
		int index;
		
		public FallingWord(String a, int index) {
			fallingWordL = new JLabel(a);
			fallingWordL.setForeground(Color.white);
			fallingWordL.setOpaque(false);
			fallingWordL.setSize(100, 20);
			this.index = index;
			
			x = getRan.nextInt(600); 
			y = 0;
			dy = getRan.nextInt(15)+1;
			
			fallingWordL.setLocation(x, y);
			System.out.println(fallingWordL.getText());
			gPanel.add(fallingWordL);
		}
		
		void move() {
			y+=dy;
			fallingWordL.setLocation(x, y);
			if (y > 340) {
				isDead = true;
				mistake++;
				System.out.println("mistake: "+mistake);
				
				}
			if (mistake == 5 || i < 0) {
					gThread.interrupt();
			}
		}
		
		void check() {
			if (isDead)
				fallingWordL.setVisible(false);
		}
		
		public boolean equals(Object o) {
			FallingWord w = (FallingWord) o;
			if (this.fallingWordL.getText().equals(w.fallingWordL.getText()))
				return true;
			else
				return false;
		}
	}
	
	class GamePanel extends JPanel {
		
		int x, y, w, h;
		int dx = 0, dy = 0;
		ArrayList<FallingWord> fallingWords = new ArrayList<FallingWord>();
		
		public GamePanel() {
			this.setLayout(null);
		}
		
		void makeFallingWord() {
			int n = getRan.nextInt(15);
			if (n==0) {
				int index = getRan.nextInt(Lecture.size);
				fallingWords.add(new FallingWord(words.get(index), index)); //수정
			}
		}
		
		void move() {
			for (int i=fallingWords.size() - 1; i >= 0; i--) {
				FallingWord w = fallingWords.get(i);
				w.move();
				w.check();
				if (w.isDead)
					fallingWords.remove(i);
			}
		}
		
		void checkInput() {
			for (int i= 0; i < fallingWords.size(); i++) {
				String str = fallingWords.get(i).fallingWordL.getText();
				if (str.equals(answerS)) {
					fallingWords.get(i).isDead = true;
					fallingWords.get(i).check();
					score++;
				}
			}
		}
		
		void viewChange() {
			timeL.setForeground(Color.white);
			timeL.setText(time);
			scoreL.setForeground(Color.white);
			scoreL.setText("Score : "+score);
		}
 		
	}
	
	class GameThread extends Thread {
		int index;
		
		public void run() {
			while (true) {
				gPanel.makeFallingWord();
				gPanel.move();
				if (this.isInterrupted()) {
					System.out.println("게임 스레드 종료");
					exit();

					break;
				}
				gPanel.checkInput();
				gPanel.viewChange();
				
				try {
					sleep(100);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	public Library_sansungbigame() {	}
	
	public Library_sansungbigame(Lecture a) {
		super("호반우를 키워라_산성비");
		setSize(740, 430);
		setLocationRelativeTo(null);
    	setResizable(false);
		
		
		temp = a;
		
		for (int i=0; i<6; i++) {
			
			System.out.println(temp.getName());
			if ( temp.getName().equals(Regis_page.selectedList[i].getName()) ) {
				
				Regis_page.studyCnt[i]++;
				System.out.println("출석 횟수:"+Regis_page.studyCnt[i]);
				break;
			}
		}
	
		icon = new ImageIcon("칠판.png");
        JPanel panel = new JPanel(){
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, 720, 404, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        panel.setLayout(null);
        
        JPanel answerPanel = new JPanel();
        answerPanel.setBounds(310, 340, 120, 30);
        answerPanel.setBackground(Color.WHITE);
        answer = new JTextField(10); 
        answer.setBounds(320, 350, 100, 30);
        answer.addKeyListener(this);
        answerPanel.add(answer);
        panel.add(answerPanel);
        
        words = a.wordMake(); 
		
        startPanel = new JPanel();
        startPanel.setBounds(250, 150, 240, 70);
        startPanel.setLayout(new FlowLayout());
        startPanel.setBackground(Color.WHITE);
        panel.add(startPanel);
        
        JLabel info = new JLabel("<html><body style='text-align:center'>"
        		+ "수업 필기 속 단어를 복습해 봅시다!<br/>"
        		+ "5단어를 놓치면 중지됩니다.</body></html>");
        startPanel.add(info);
        
        JButton start_btn = new JButton("시작");
        start_btn.addActionListener(this);
        startPanel.add(start_btn);
       
        gPanel = new GamePanel(){
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, 720, 404, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        gPanel.setBounds(0, 0, 730, 340);
        gPanel.setLocation(0, 0);
        
        
        panel.add(gPanel);
        gThread = new GameThread();
        
        timeL.setBounds(10, 10, 80, 30);
        timeL.setOpaque(false);
        gPanel.add(timeL);
        
        scoreL.setBounds(10, 50, 80, 30);
        scoreL.setOpaque(false);
        gPanel.add(scoreL);
        
        add(panel);
        if(hp <= 0) {
			Ending_Guaro gre = new Ending_Guaro();
			gre.setVisible(true);
			setVisible(false);
		}else if(stress >= 100) {
			Ending_Gapyear gye = new Ending_Gapyear();
			gye.setVisible(true);
			setVisible(false);
		}else
			setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		
		if (actionCmd.equals("시작")) {
			startPanel.setVisible(false);
			
	        if ( words == null ) {
	        	JOptionPane.showMessageDialog(null, "수업을 듣고 필기하세요!");
	        	
	        	setVisible(false);
	        } else {
	        	gThread.start();
	    		Timer timer = new Timer();
	    		
	        	timer.scheduleAtFixedRate(new TimerTask() {
	    			public void run() {

	    				time = "Time left: " + i;
	    				i--;
	    				
	    				if (i < 0) {
	    					timer.cancel();
	    					time = "Time Over";
	    					System.out.println("타임 스레드 종료");
	    				
	    				}
	    			}
	    		}, 0, 1000);
	        
		    }
		}else {
			//error
		}
	}
	
	void exit() {
		new Library_page();
		
		JFrame annFrame = new JFrame("Gameover");
		annFrame.setLocationRelativeTo(null);
    	annFrame.setResizable(false);
		annFrame.setSize(250, 70);
		JPanel tempPanel = new JPanel();
		
		annFrame.add(tempPanel);
		JLabel announce = new JLabel("Score "+score+" up!");
		
		hp -= 10;
		stress += 10;
		happy -= 10;
		
		happy_ -= 10;
		
		tempPanel.add(announce, BorderLayout.CENTER);
		annFrame.setVisible(true);
		
		temp.lib_scoreUP(score);

		score=0;
		
		
		for (int i=0; i<6; i++) { 
			if (Regis_page.studyCnt[i]>=2) {
				
				if (i==5) {
					System.out.println("학술엔딩 호출");
					new Ending_Graduate();
				}
			}
			else break;
		}
		this.setVisible(false);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			answerS = answer.getText();
			answer.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}