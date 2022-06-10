import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class score_Frame extends JFrame implements ActionListener {

    int f_width = 400 ;
    int f_height =300;
    Toolkit tk = Toolkit.getDefaultToolkit();
    
    Score[] scoreList;	// 이름, 점수, 생존 시간등을 저장할 리스트
	int num;			// 받아온 자료의 수
	int newRecordIdx;	// 새롭게 갱신된 자료의 인덱스
	int playerScore;	// 플레이어의 점수
	int playerTime;		// 플레이어의 생존 시간
    
	JTextField ioField;		// 이름 입력부분
	JButton saveButton;		// 데이터 저장 버튼
	JLabel[] scoreLabel;	// 등 수별 데이터 출력부분
	game_Frame beforeGame;	// 이전 프레임을 다루기 위한 변수
	
	public score_Frame(game_Frame f, int score, int time)
	{
		setTitle("점수 화면");
	    setSize(f_width, f_height);

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Dimension screen = tk.getScreenSize();
	    int f_xpos = (int)(screen.getWidth() / 2 - f.f_width / 2);
	    int f_ypos = (int)(screen.getHeight() / 2 - f.f_height / 2);
	    
	    beforeGame = f;
	    playerScore = score;
	    playerTime = time;
	    newRecordIdx = 0;
	    num = 0;
		
		scoreList = new Score[11];
		for(int i=0;i<11;i++)
			scoreList[i] = new Score();
		
		getData();
	    
	    
	    JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new FlowLayout());
    	
    	// 10위 안 스코어가 아닐 경우
	    if(num == 10 && scoreList[9].score >= playerScore)
	    {
	    	JLabel namelabel = new JLabel("순위권에 들지 못했습니다.");
	    	inputPanel.add(namelabel);
	    }
	    else	//순위권 안에 들어 있을 경우
	    {
		    
		    JLabel namelabel = new JLabel("저장할 이름 : ");
		    inputPanel.add(namelabel);
		    
		    ioField = new JTextField("", 20);
			ioField.setBackground(Color.WHITE);
			inputPanel.add(ioField);
			
			saveButton = new JButton("Save");
			saveButton.addActionListener(this);
			
			inputPanel.add(saveButton);
	    }
	    
		add(inputPanel, BorderLayout.NORTH);
		
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(12,1));
		
		JLabel myScore = new JLabel("내 점수 : "+playerScore);
		scorePanel.add(myScore);
		JLabel myTime = new JLabel("생존 시간 : "+playerTime);
		scorePanel.add(myTime);
		
		scoreLabel = new JLabel[10];
		for(int i=0; i<10;i++)
		{
			scoreLabel[i] = new JLabel();
			if(i<num)
				scoreLabel[i].setText(scoreList[i].toString());
			scorePanel.add(scoreLabel[i]);
		}
		
		add(scorePanel, BorderLayout.CENTER);
		
		
	    JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		ImageIcon restartIcon = new ImageIcon(".\\img\\restart.png");
		Image restartImage = restartIcon.getImage(); // ImageIcon을 Image로 변환.
		Image changereplayImage = restartImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		restartIcon = new ImageIcon(changereplayImage);
		JButton restartButton = new JButton("Restart");
		restartButton.setIcon(restartIcon);
		restartButton.addActionListener(this);
		
		ImageIcon menuIcon = new ImageIcon(".\\img\\menu.png");
		Image MenuImage = menuIcon.getImage(); // ImageIcon을 Image로 변환.
		Image changeMenuImage = MenuImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		menuIcon = new ImageIcon(changeMenuImage);
		JButton menuButton = new JButton("Menu");
		menuButton.setIcon(menuIcon);
		menuButton.addActionListener(this);

		ImageIcon exitIcon = new ImageIcon(".\\img\\exit.png");
		Image exitImage = exitIcon.getImage(); // ImageIcon을 Image로 변환.
		Image changeExitImage = exitImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		exitIcon = new ImageIcon(changeExitImage);
		JButton exitButton = new JButton("Exit");
		exitButton.setIcon(exitIcon);
		exitButton.addActionListener(this);
		
		buttonPanel.add(restartButton);
		buttonPanel.add(menuButton);
		buttonPanel.add(exitButton);

		add(buttonPanel, BorderLayout.SOUTH);

	    
		
	    setLocation(f_xpos, f_ypos);
	    setResizable(false);
	    setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();
		if(btnStr.equals("Menu")) {
			Start s = new Start();
			beforeGame.dispose();
			dispose();
		}
		if(btnStr.equals("Restart")) {
			game_Frame fms = new game_Frame();
			beforeGame.dispose();
			dispose();
		}
		if(btnStr.equals("Exit")) {
			System.exit(0);
		}
		if(btnStr.equals("Save")) {

			Score player = new Score();
			player.name = ioField.getText();
			player.score = playerScore;
			player.time = playerTime;
			
			scoreList[num] = player;

			//디버깅
			/*System.out.println(num);
			for(int i = 0; i <= num; i++) {
				System.out.print((i+1)+" ");
				System.out.println(scoreList[i]);
			}*/
			
			
			sort();
			
			saveButton.setEnabled(false);
			ioField.setText("Saved!!");
			ioField.setEnabled(false);
			
			for(int i=0; i<10 && i <= num ;i++)
			{
				scoreLabel[i].setText(scoreList[i].toString());
				if(i == newRecordIdx)
					scoreLabel[i].setText(scoreLabel[i].getText() + "   ( new )");
			}
			
			//디버깅
			/*System.out.println(num);
			for(int i = 0; i <= num ; i++) {
				System.out.print((i+1)+" ");
				System.out.println(scoreList[i]);
			}*/
			
			saveData();

			
		}
	}
	
	// 역 직렬화 부분
	// 파일에 저장되어있는 정보를 불러온다
	public void getData() 
	{
		try	//파일이 있을 경우 (게임을 1판 이상 실행 했었을 경우)
		{
			ObjectInputStream intputStream = new ObjectInputStream(new FileInputStream("score.dat"));
			try
			{
				while(num < 10)
				{
					scoreList[num] = (Score) intputStream.readObject();
					num++;
				}
			}
			catch(FileNotFoundException e)
			{
				//System.out.println("1");
				//e.printStackTrace();

			}
			catch(ClassNotFoundException e	)
			{
				//System.out.println("2");
				//e.printStackTrace();
			}
			catch(IOException e)
			{
				//System.out.println("3");
				//e.printStackTrace();
			}

			intputStream.close();
		}
		catch(IOException e)	// 파일이 없을 경우 (처음 게임을 실행했을 경우)
		{
			System.out.println("Problem with file input.");
			try {
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("score.dat"));
				outputStream.close();
			}catch(IOException e2)
			{
				System.err.println("Error writing to file.");
			}
			
		}
	}
	
	//직렬화 부분
	public void saveData()
	{
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("score.dat"));
			
			for(int i = 0; i <= num && i < 10; i++)
			{
				outputStream.writeObject(scoreList[i]);
			}
			outputStream.close();
		}
		catch(IOException e)
		{
			System.err.println("Error writing to file.");
		}
	}
	
	// 배열 끝에 있는 새로운 데이터를
	// Score의 내림 차 순, 같을 경우 카운트가 높은 것이 우선으로
	// 들어갈 자리를 찾고 옮겨준다
	// ( 삽입 정렬 부분 이용 )
	
	// %% 값이 이상할 경우 %%
	// 플레이의 삽입을 위한 메서드이므로 이미 .dat 파일의 인풋이 잘못되었을 경우 정렬이 되지않음
	// 이 경우 .dat 파일을 지운 후 새로 만들어 사용.
	public void sort()
	{
		int j;
		Score key = scoreList[num];
		
	    for(j=num-1; j>=0 && scoreList[j].score <= key.score; j--){
	    	if(scoreList[j].score == key.score && scoreList[j].time > key.time)
	    		break;
	    	scoreList[j+1] = scoreList[j]; // 레코드의 오른쪽으로 이동
	    }

	    scoreList[j+1] = key;
	    newRecordIdx = j+1;
	}
}
