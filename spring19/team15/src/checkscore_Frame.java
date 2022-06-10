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

public class checkscore_Frame extends JFrame implements ActionListener {

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
	JFrame beforeFrame;	// 이전 프레임을 다루기 위한 변수
	
	public checkscore_Frame(JFrame f)
	{
		setTitle("점수 화면");
	    setSize(f_width, f_height);

	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    beforeFrame = f;
	    
	    Dimension screen = tk.getScreenSize();
	    int f_xpos = (int)(screen.getWidth() / 2 - f_width );
	    int f_ypos = (int)(screen.getHeight() / 2 - f_height );
	    
	    newRecordIdx = 0;
	    num = 0;
		
		scoreList = new Score[10];
		for(int i=0;i<10;i++)
			scoreList[i] = new Score();
		
		getData();
	    
    	
		
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(10,1));
		
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
		
		ImageIcon startIcon = new ImageIcon(".\\img\\start.png");
		Image startImage = startIcon.getImage(); // ImageIcon을 Image로 변환.
		Image changeStartImage = startImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		startIcon = new ImageIcon(changeStartImage);
		JButton startButton = new JButton("Start");
		startButton.setIcon(startIcon);
		startButton.addActionListener(this);
		buttonPanel.add(startButton);

		ImageIcon exitIcon = new ImageIcon(".\\img\\exit.png");
		Image exitImage = exitIcon.getImage(); // ImageIcon을 Image로 변환.
		Image changeExitImage = exitImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		exitIcon = new ImageIcon(changeExitImage);
		JButton exitButton = new JButton("Exit");
		exitButton.setIcon(exitIcon);
		exitButton.addActionListener(this);
		buttonPanel.add(exitButton);

		add(buttonPanel, BorderLayout.SOUTH);

	    
		
	    setLocation(f_xpos, f_ypos);
	    setResizable(false);
	    setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();
		if(btnStr.equals("Start")) {
			beforeFrame.dispose();
			dispose();

			game_Frame fms = new game_Frame();
		}
		if(btnStr.equals("Exit")) {
			dispose();
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
	
}
