package Checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Main.MainMenu;
import Main.User;

public class Checkers extends JFrame implements WindowListener{
	private User p1, p2;
	private static final int SCREEN_WIDTH = 600;
	private static final int SCREEN_HEIGHT = 700;
	
	private JButton[][] Board = new JButton[8][8]; // 보드판 버튼
	private int[][] boardState = new int[8][8]; // 보드판 상태 저장하는 배열
	/*
	 * 0 = 말 없음
	 * 1 = 흰색 Men
	 * 2 = 검정 Men
	 * 3 = 흰색 King
	 * 4 = 검정 King
	 */
	
	private int whiteNumber; // 흰색말 수
	private int blackNumber; // 검은색말 수
	private JLabel whiteNumberLabel = new JLabel(""); // 흰색 말 수를 표시하는 label
	private JLabel blackNumberLabel = new JLabel(""); // 검은색 말 수를 표시하는 label
	
	private int whiteWin = 0; // 흰색 이긴 횟수
	private int blackWin = 0; // 검은색 이긴 횟수
	private JLabel whiteWinLabel = new JLabel(""); // 흰색이 이긴 수 표시하는 label
	private JLabel blackWinLabel = new JLabel(""); // 검은색이 이긴 수 표시하는 label
	
	private String Turn; // 턴(black / white)
	private ArrayList<Piece> blackPiece = new ArrayList<Piece>(); // 검은색 말 저장 배열
	private ArrayList<Piece> whitePiece = new ArrayList<Piece>(); // 흰색 말 저장 배열
	
	private ImageIcon BoardBasicIcon = new ImageIcon(
			Checkers.class.getResource("/images/BoardBasic.png")); // 보드판 한 칸 기본 아이콘
	private ImageIcon BoardClickAvailableIcon = new ImageIcon(
			Checkers.class.getResource("/images/BoardClickAvailable.png"));	// 보드판 한 칸 클릭 가능할 때의 아이콘
	
	private Color backgroundColor = new Color(91, 70, 56);
	private Color fontColor = new Color(255, 231, 216);
	
 	public Checkers(User pl1, User pl2) {
		makeGUI(); // 화면 생성
		startGame(); // 게임 시작
		p1 = pl1;
		p2 = pl2;
	}
	
	/* 화면 생성 makeGUI */
	void makeGUI() {
		addWindowListener(this);
		setTitle("Checkers");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false); // 사용자가 임의로 화면 크기 조정 불가능
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null); // 실행 시 컴퓨터 중앙에 뜨도록
		getContentPane().setBackground(backgroundColor); // 게임화면 배경색 설정
		setLayout(null);
		
		/* 보드판 */
		JPanel boardPanel = new JPanel();
		boardPanel.setBounds(50, 30,480, 480);
		boardPanel.setLayout(new GridLayout(8, 8));
		
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				Board[i][j] = new JButton();
				Board[i][j].setSize(60, 60);
				Board[i][j].setBorderPainted(false);
				
				if ((i % 2) == (j % 2)) // 보드판 밝은 부분
					Board[i][j].setBackground(new Color(230, 196, 175));
				else // 보드판 어두운 부분(이 칸에서만 말들이 이동 가능함)
					Board[i][j].setIcon(BoardBasicIcon);
				
				Board[i][j].addActionListener(new ButtonListener(i, j));
				boardPanel.add(Board[i][j]);
			}
		}
		add(boardPanel);
		
		/* 말 개수 표시판 */		
		JPanel numberPanel = new JPanel();
		numberPanel.setBounds(430, 540, 140, 40);
		numberPanel.setLayout(new GridLayout(2, 1));
		numberPanel.setBackground(backgroundColor);
		
		whiteNumberLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		whiteNumberLabel.setForeground(fontColor);
		blackNumberLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		blackNumberLabel.setForeground(fontColor);
		
		numberPanel.add(whiteNumberLabel);
		numberPanel.add(blackNumberLabel);
		
		add(numberPanel);
		
		/* 이긴 횟수 표시판 */
		JPanel winnerPanel = new JPanel();
		winnerPanel.setBounds(430, 600, 140, 40);
		winnerPanel.setLayout(new GridLayout(2,1));
		winnerPanel.setBackground(backgroundColor);
		
		whiteWinLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		blackWinLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		whiteWinLabel.setForeground(fontColor);
		blackWinLabel.setForeground(fontColor);
		
		winnerPanel.add(whiteWinLabel);
		winnerPanel.add(blackWinLabel);
		
		add(winnerPanel);
		
		setVisible(true);
	}
	/* 새 게임 생성 함수 */
	void startGame() {
		/* 말 초기화 */
		whitePiece.clear();
		blackPiece.clear();
		
		/* 말 만들기 */
		blackPiece.add(new Men(0,1,"black"));
		blackPiece.add(new Men(0,3,"black"));
		blackPiece.add(new Men(0,5,"black"));
		blackPiece.add(new Men(0,7,"black"));
		blackPiece.add(new Men(1,0,"black"));
		blackPiece.add(new Men(1,2,"black"));
		blackPiece.add(new Men(1,4,"black"));
		blackPiece.add(new Men(1,6,"black"));
		blackPiece.add(new Men(2,1,"black"));
		blackPiece.add(new Men(2,3,"black"));
		blackPiece.add(new Men(2,5,"black"));
		blackPiece.add(new Men(2,7,"black"));
				
		whitePiece.add(new Men(7, 0, "white"));
		whitePiece.add(new Men(7, 2, "white"));
		whitePiece.add(new Men(7, 4, "white"));
		whitePiece.add(new Men(7, 6, "white"));
		whitePiece.add(new Men(6, 1, "white"));
		whitePiece.add(new Men(6, 3, "white"));
		whitePiece.add(new Men(6, 5, "white"));
		whitePiece.add(new Men(6, 7, "white"));
		whitePiece.add(new Men(5, 0, "white"));
		whitePiece.add(new Men(5, 2, "white"));
		whitePiece.add(new Men(5, 4, "white"));
		whitePiece.add(new Men(5, 6, "white"));
		
		/* 말 배치 */
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (i%2 == j %2)
					boardState[i][j] = -1;
				else {
					boardState[i][j] = 0;
					Board[i][j].setIcon(BoardBasicIcon);
				}
			}
		}
		
		for (int i=0; i<blackPiece.size(); i++) {
			int x = blackPiece.get(i).getX();
			int y = blackPiece.get(i).getY();
			Board[x][y].setIcon(blackPiece.get(i).basicIcon);
			
			int kind = blackPiece.get(i).getKindOfPiece();
			boardState[x][y] = kind;
		}
		for (int i=0; i<whitePiece.size(); i++) {
			int x = whitePiece.get(i).getX();
			int y = whitePiece.get(i).getY();
			Board[x][y].setIcon(whitePiece.get(i).basicIcon);
			
			int kind = whitePiece.get(i).getKindOfPiece();
			boardState[x][y] = kind;
		}
		
		whiteNumber = whitePiece.size();
		blackNumber = blackPiece.size();
		whiteNumberLabel.setText("white : "+ whiteNumber);
		blackNumberLabel.setText("black : "+ blackNumber);
		whiteWinLabel.setText("whiteWin : " + whiteWin);
		blackWinLabel.setText("blackWin : " + blackWin);
		
		/* 초기 조건 */
		menClick = true; // true이면 말 클릭해야 함
		boardClick = false; // true이면 보드판 한 칸 클릭해야 함
		next = false;
		
		// 백색부터 시작
		Turn = "white";
		wt = new whiteTurn();
	}
	
	/* 보드판 리셋(말 위치 바뀔 때마다 쓰임) */
	void resetBoard() {
		/* 흑백 말 보드판에 setIcon 해주기 */
		for (int i = 0; i < whitePiece.size(); i++) {
			Board[whitePiece.get(i).getX()][whitePiece.get(i).getY()].setIcon(whitePiece.get(i).basicIcon);
		}
		for (int i = 0; i < blackPiece.size(); i++) {
			Board[blackPiece.get(i).getX()][blackPiece.get(i).getY()].setIcon(blackPiece.get(i).basicIcon);
		}
		
		/* 빈칸 BoardBasicIcon 으로 세팅 해주기 */
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if ( (i % 2 != j % 2) &&(boardState[i][j] == 0)) {
					Board[i][j].setIcon(BoardBasicIcon);
				}
			}
		}
		
		/* 말 개수 표시판 세팅 */
		whiteNumber = whitePiece.size();
		blackNumber = blackPiece.size();
		whiteNumberLabel.setText("white : "+ whiteNumber);
		blackNumberLabel.setText("black : "+ blackNumber);
	}

	// 흰색 말 차례일 때 각 말이 움직일 수 있는 위치 세팅하는 class
	class whiteTurn {
		private int numberOfPriority = 0; // 우선되는 말 개수
		private int[][] priorityBoard = new int[8][8]; // 우선되는 말(없을 경우는 클릭할 수 있는 말)인 것 저장해놓은 배열
		
		public int getNumberOfPriority() {
			return numberOfPriority;
		}
		public int getPriorityBoard(int x, int y) {
			return priorityBoard[x][y];
		}
 		public whiteTurn() {
			/* 각 말이 움직일 수 있는 위치 setting */
			for (int i=0; i<whitePiece.size(); i++) {
				whitePiece.get(i).Paths.clear(); // i번째의 말이 움직일 수 있는 길 초기화
				whitePiece.get(i).setMoveable(); 
				System.out.println("white["+i+"]");
				whitePiece.get(i).printMoveable();
			
				if (whitePiece.get(i).prioritize()) {
					numberOfPriority += 1;
				}
			}
			
			clickAvailablePieceNumber = 0;
			/* 무조건 움직여야 하는 말(상대 말 없앨 수 있는 것) 없으면 */
			if (numberOfPriority == 0) {
				/* 모든 움직일 수 있는 말 클릭 가능하게 하기 */
				for (int i=0; i<whitePiece.size(); i++) // 말 개수만큼
				{
					if ((whitePiece.get(i).Paths).size() != 0) { // 움직일 수 있는 말 있으면 클릭 가능하도록
						clickAvailablePieceNumber += 1;
						priorityBoard[whitePiece.get(i).getX()][whitePiece.get(i).getY()] = 1;
						Board[whitePiece.get(i).getX()][whitePiece.get(i).getY()].setIcon(whitePiece.get(i).ClickAvailableIcon);
					}
				}
			}
			/* 무조건 움직여야 하는 말 있으면 */
			else {
				/*  무조건 움직여야 하는 말만 클릭 가능하게 하기 */
				for (int i=0; i<whitePiece.size(); i++) {
					if (whitePiece.get(i).prioritize()) {
						clickAvailablePieceNumber += 1;
						priorityBoard[whitePiece.get(i).getX()][whitePiece.get(i).getY()] = 2;
						// 값을 2로 설정해 중간에 없앨 말이 있다는 것을 표시
						Board[whitePiece.get(i).getX()][whitePiece.get(i).getY()].setIcon(whitePiece.get(i).ClickAvailableIcon);;
					}
				}
			}
		}
	}
	// 검은색 말 차례일 때 각 말이 움직일 수 있는 위치 세팅하는 class
	class blackTurn {
		private int numberOfPriority = 0; // 우선되는 말 개수
		private int[][] priorityBoard = new int[8][8]; // 우선되는 말(없을 경우는 클릭할 수 있는 말)인 것 저장해놓은 배열
		
		public int getNumberOfPriority() {
			return numberOfPriority;
		}
		public int getPriorityBoard(int x, int y) {
			return priorityBoard[x][y];
		}
		public blackTurn() {
			/* 각 말이 움직일 수 있는 위치 setting */
			for (int i=0; i<blackPiece.size(); i++) {
				blackPiece.get(i).setMoveable(); 
				System.out.println("black["+i+"]");
				blackPiece.get(i).printMoveable();
			
				if (blackPiece.get(i).prioritize()) {		
					numberOfPriority += 1;
				}
			}
			
			clickAvailablePieceNumber = 0;
			/* 무조건 움직여야 하는 말(상대 말 없앨 수 있는 것) 없으면 */
			if (numberOfPriority == 0) {
				/* 모든 움직일 수 있는 말 클릭 가능하게 하기 */
				for (int i=0; i<blackPiece.size(); i++) // 말 개수만큼
				{
					if ((blackPiece.get(i).Paths).size() != 0) { // 움직일 수 있는 말 있으면 클릭 가능하도록
						clickAvailablePieceNumber += 1;
						priorityBoard[blackPiece.get(i).getX()][blackPiece.get(i).getY()] = 1;
						Board[blackPiece.get(i).getX()][blackPiece.get(i).getY()].setIcon(blackPiece.get(i).ClickAvailableIcon);
					}
				}
			}
			/* 무조건 움직여야 하는 말 있으면 */
			else {
				/*  무조건 움직여야 하는 말만 클릭 가능하게 하기 */
				for (int i=0; i<blackPiece.size(); i++) {
					if (blackPiece.get(i).prioritize()) {
						clickAvailablePieceNumber += 1;
						priorityBoard[blackPiece.get(i).getX()][blackPiece.get(i).getY()] = 2;
						// 값을 2로 설정해 중간에 없앨 말이 있다는 것을 표시
						Board[blackPiece.get(i).getX()][blackPiece.get(i).getY()].setIcon(blackPiece.get(i).ClickAvailableIcon);
					}
				}
			}
		}
	}
	
	private whiteTurn wt;
	private blackTurn bt;
	
	boolean menClick = true; // true이면 말 클릭해야 함
	boolean boardClick = false; // true이면 보드판 한 칸 클릭해야 함
	boolean next = false;
	
	int clickedPieceIndex; // 클릭된 말의 인덱스
	ArrayList<path> availablePaths = new ArrayList<path>(); // 클릭한 말의 이동할 수 있는 위치 저장하는 배열
	int clickAvailablePieceNumber; // 클릭 가능한 말의 수
	
	// 보드판 위 버튼 클릭 시 action 수행하는 class 
	class ButtonListener implements ActionListener {
		private int x; // 보드판 x좌표
		private int y; // 보드판 y좌표
		public ButtonListener(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		private boolean isAvailableNext(Piece p) {
			p.setMoveable();
			if (p.prioritize())
				return true;
			return false;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if ((x%2)!=(y%2)) // 움직일 수 있는 공간인 경우에만!
			{
				if (menClick) // 말 클릭해야 하는 경우
				{
					availablePaths.clear();
					if (Turn.equals("white")) // 백색 턴이면
					{	
						// 움직일 수 있는 흰색 Men 또는 흰색 King 클릭해야 함
						if (wt.getPriorityBoard(x, y) > 0) {
							menClick = false;
							boardClick = true;
							System.out.printf("white men(%d, %d) clicked\n", x, y);
							
							// 클릭한 말의 이동가능한 위치 저장
							for (int i=0; i<whitePiece.size(); i++) {
								if (whitePiece.get(i).getX() == x && whitePiece.get(i).getY() == y) {
									Board[x][y].setIcon(whitePiece.get(i).ClickedIcon);
									clickedPieceIndex = i;
									for (int j=0; j<whitePiece.get(i).Paths.size(); j++) {
										if (wt.getPriorityBoard(x, y) == 2) // 중간에 없앨 수 있는 말 있으면
										{
											next = true;
											if (whitePiece.get(i).Paths.get(j).getDistance() != 0) {
												availablePaths.add(new path(whitePiece.get(i).Paths.get(j)));
												// 클릭 가능한 칸임을 표시
												Board[whitePiece.get(i).Paths.get(j).getX()][whitePiece.get(i).Paths.get(j).getY()].setIcon(BoardClickAvailableIcon);
											}
										}
										else // 중간에 없앨 수 있는 말 없으면
										{	
											availablePaths.add(new path(whitePiece.get(i).Paths.get(j)));
											// 클릭 가능한 칸임을 표시
											Board[whitePiece.get(i).Paths.get(j).getX()][whitePiece.get(i).Paths.get(j).getY()].setIcon(BoardClickAvailableIcon);
										}
									}
									break;
								}
							}
						}
					}
					else // 흑색 턴이면 
					{	
						// 흑색 Men 또는 흑색 King 클릭해야 함
						if (bt.getPriorityBoard(x, y) > 0) {
							menClick = false;
							boardClick = true;
							System.out.printf("black men(%d, %d) clicked\n", x, y);
							
							// 클릭한 말의 이동가능한 위치 저장
							for (int i=0; i<blackPiece.size(); i++) {
								if (blackPiece.get(i).getX() == x && blackPiece.get(i).getY() == y) {
									Board[x][y].setIcon(blackPiece.get(i).ClickedIcon);
									clickedPieceIndex = i;
									for (int j=0; j < blackPiece.get(i).Paths.size(); j++) {
										if (bt.getPriorityBoard(x, y) == 2) // 중간에 없앨 수 있는 말 있으면
										{
											next = true; 
											if (blackPiece.get(i).Paths.get(j).getDistance() != 0) {
												availablePaths.add(new path(blackPiece.get(i).Paths.get(j)));
												// 클릭 가능한 칸임을 표시
												Board[blackPiece.get(i).Paths.get(j).getX()][blackPiece.get(i).Paths.get(j).getY()].setIcon(BoardClickAvailableIcon);
											}
										}
										else // 중간에 없앨 수 있는 말 없으면 
										{
											availablePaths.add(new path(blackPiece.get(i).Paths.get(j)));
											// 클릭 가능한 칸임을 표시
											Board[blackPiece.get(i).Paths.get(j).getX()][blackPiece.get(i).Paths.get(j).getY()].setIcon(BoardClickAvailableIcon);
										}
									}
									break;
								}
							}
						}
					}
				}
			
				else // 보드판 칸 클릭해야 하는 경우
				{
					for (int i=0; i<availablePaths.size(); i++) {
						if (x == availablePaths.get(i).getX() && y == availablePaths.get(i).getY()) {
							// 클릭할 수 있는 곳 클릭함
							if (Turn.equals("white")) // 백색 턴
							{
								/* 사이에 없앨 말 없는 경우 */
								if (availablePaths.get(i).getDistance() == 0) {
									// 1) 기존 말 위치의 boardState 0으로 바꾸기
									// 2) 말 위치 변경 (Piece.move(x, y))
									// 3) 옮긴 자리 boardState를 kindOfPiece로 바꾸기
									boardState[whitePiece.get(clickedPieceIndex).getX()][whitePiece.get(clickedPieceIndex).getY()]
											= 0;
									whitePiece.get(clickedPieceIndex).move(x, y);
									
									// 4) 만약 옮긴 말이 자신 위치로부터 마지막 행(x == 0)에 도달하면 King으로 승급(이미 king인 경우 제외)
									if (x == 0 && whitePiece.get(clickedPieceIndex).getKindOfPiece() == 1) {
										whitePiece.remove(clickedPieceIndex);
										whitePiece.add(clickedPieceIndex, new King(x, y, "white"));
									}
									boardState[x][y] = whitePiece.get(clickedPieceIndex).getKindOfPiece();
								}
								/* 사이에 없앨 말 있는 경우 */
								else {
									// 1) 기존 말 위치의 boardState 0으로 바꾸기
									// 2) 말 위치 변경 (Piece.move(x, y))
									// 3) 옮긴 자리 boardState를 kindOfPiece로 바꾸기
									// 4) 없앨 말 찾아서 없애고, 그 자리 boardState 0으로 바꾸기
									boardState[whitePiece.get(clickedPieceIndex).getX()][whitePiece.get(clickedPieceIndex).getY()]
											= 0;
									int xOfRemove, yOfRemove; // 없앨 말의 x, y좌표
									xOfRemove = (whitePiece.get(clickedPieceIndex).getX() + x) / 2;
									yOfRemove = (whitePiece.get(clickedPieceIndex).getY() + y) / 2;
									boardState[xOfRemove][yOfRemove] = 0;
									for (int j=0; j<blackPiece.size(); j++) {
										if (blackPiece.get(j).getX() == xOfRemove && blackPiece.get(j).getY() == yOfRemove) {
											blackPiece.remove(j);
											break;
										}
									}
									
									whitePiece.get(clickedPieceIndex).move(x, y);
									
									// 5) 만약 옮긴 말이 자신 위치로부터 마지막 행(x == 0)에 도달하면 King으로 승급(이미 king인 경우 제외)
									if (x == 0 && whitePiece.get(clickedPieceIndex).getKindOfPiece() == 1) {
										whitePiece.remove(clickedPieceIndex);
										whitePiece.add(clickedPieceIndex, new King(x, y, "white"));
									}
									boardState[x][y] = whitePiece.get(clickedPieceIndex).getKindOfPiece();
								}
							}
							else // 흑색 턴
							{
								/* 사이에 없앨 말 없는 경우 */
								if (availablePaths.get(i).getDistance() == 0) {
									// 1) 기존 말 위치의 boardState 0으로 바꾸기
									// 2) 말 위치 변경 (Piece.move(x, y))
									// 3) 옮긴 자리 boardState를 kindOfPiece로 바꾸기
									boardState[blackPiece.get(clickedPieceIndex).getX()][blackPiece.get(clickedPieceIndex).getY()]
											= 0;
									blackPiece.get(clickedPieceIndex).move(x, y);
									
									// 4) 만약 옮긴 말이 자신 위치로부터 마지막 행(x == 7)에 도달하면 King으로 승급(이미 king인 경우 제외)
									if (x == 7 && blackPiece.get(clickedPieceIndex).getKindOfPiece() == 2) {
										blackPiece.remove(clickedPieceIndex);
										blackPiece.add(clickedPieceIndex, new King(x, y, "black"));
									}
									boardState[x][y] = blackPiece.get(clickedPieceIndex).getKindOfPiece();
								}
								/* 사이에 없앨 말 있는 경우 */
								else {
									// 1) 기존 말 위치의 boardState 0으로 바꾸기
									// 2) 말 위치 변경 (Piece.move(x, y))
									// 3) 옮긴 자리 boardState를 kindOfPiece로 바꾸기
									// 4) 없앨 말 찾아서 없애고, 그 자리 boardState 0으로 바꾸기
									boardState[blackPiece.get(clickedPieceIndex).getX()][blackPiece.get(clickedPieceIndex).getY()]
											= 0;
									int xOfRemove, yOfRemove; // 없앨 말의 x, y좌표
									xOfRemove = (blackPiece.get(clickedPieceIndex).getX() + x) / 2;
									yOfRemove = (blackPiece.get(clickedPieceIndex).getY() + y) / 2;
									boardState[xOfRemove][yOfRemove] = 0;
									for (int j=0; j<whitePiece.size(); j++) {
										if (whitePiece.get(j).getX() == xOfRemove && whitePiece.get(j).getY() == yOfRemove) {
											whitePiece.remove(j);
											break;
										}
									}
									
									blackPiece.get(clickedPieceIndex).move(x, y);
									
									// 5) 만약 옮긴 말이 자신 위치로부터 마지막 행(x == 7)에 도달하면 King으로 승급(이미 king인 경우 제외)
									if (x == 7 && blackPiece.get(clickedPieceIndex).getKindOfPiece() == 2) {
										blackPiece.remove(clickedPieceIndex);
										blackPiece.add(clickedPieceIndex, new King(x, y, "black"));
									}
									boardState[x][y] = blackPiece.get(clickedPieceIndex).getKindOfPiece();
								}
							}
							
							
							resetBoard();
							
							/*
							 * 계속 없애며 이동할 수 있는 경우는 무조건 그 말을 이동하게 하고
							 * 그렇지 않은 경우엔 턴 교체!!
							 */
							if (Turn.equals("white")) {
								if (next && isAvailableNext(whitePiece.get(clickedPieceIndex))) // 한 말 없애고 또 없앨 수 있으면
								{
									availablePaths.clear();
									Board[whitePiece.get(clickedPieceIndex).getX()][whitePiece.get(clickedPieceIndex).getY()].setIcon(
											whitePiece.get(clickedPieceIndex).ClickedIcon);
									for (int a = 0; a < whitePiece.get(clickedPieceIndex).Paths.size(); a++) {
										if (whitePiece.get(clickedPieceIndex).Paths.get(a).getDistance() != 0) {
											availablePaths.add(new path(whitePiece.get(clickedPieceIndex).Paths.get(a)));
											Board[whitePiece.get(clickedPieceIndex).Paths.get(a).getX()][whitePiece.get(clickedPieceIndex).Paths.get(a).getY()].setIcon(BoardClickAvailableIcon);
										}
									}
								}
								else {
									next = false;
									Turn = "black";
									bt = new blackTurn();
									
									boardClick = false;
									menClick = true;
								}
								
								/*
								 * 	말의 개수가 0개 또는
								 *  움직일 수 있는 말의 개수가 0개이면 패배
								 */
								System.out.println(Turn +" clickAvailablePiece = " + clickAvailablePieceNumber);
								if (Turn.equals("white")) {
									if (clickAvailablePieceNumber == 0 || whiteNumber == 0) {
										blackWin += 1;
										startGame();
										User.p2Win(p1, p2);
									}
								}
								else {
									if (clickAvailablePieceNumber == 0 || blackNumber == 0) {
										whiteWin += 1;
										startGame();
										User.p1Win(p1, p2);
									}
								}
							}
							else {
								if (next && isAvailableNext(blackPiece.get(clickedPieceIndex))) // 한 말 없애고 또 없앨 수 있으면
								{
									availablePaths.clear();
									Board[blackPiece.get(clickedPieceIndex).getX()][blackPiece.get(clickedPieceIndex).getY()].setIcon(
											blackPiece.get(clickedPieceIndex).ClickedIcon);
									for (int a = 0; a < blackPiece.get(clickedPieceIndex).Paths.size(); a++) {
										if (blackPiece.get(clickedPieceIndex).Paths.get(a).getDistance() != 0) {
											availablePaths.add(new path(blackPiece.get(clickedPieceIndex).Paths.get(a)));
											Board[blackPiece.get(clickedPieceIndex).Paths.get(a).getX()][blackPiece.get(clickedPieceIndex).Paths.get(a).getY()].setIcon(BoardClickAvailableIcon);
										}
									}
								}
								else {
									next = false;
									Turn = "white";
									wt = new whiteTurn();
									
									boardClick = false;
									menClick = true;
								}
								
								/*
								 * 	말의 개수가 0개 또는
								 *  움직일 수 있는 말의 개수가 0개이면 패배
								 */
								System.out.println(Turn +" clickAvailablePiece = " + clickAvailablePieceNumber);
								if (Turn.equals("white")) {
									if (clickAvailablePieceNumber == 0 || whiteNumber == 0) {
										blackWin += 1;
										startGame();
										User.p2Win(p1, p2);
									}
								}
								else {
									if (clickAvailablePieceNumber == 0 || blackNumber == 0) {
										whiteWin += 1;
										startGame();
										User.p1Win(p1, p2);
									}
								}
							}
							
							break;
						}
					}
				}
			}
		}
	}
	
	// Piece 상속하는 class들에서 말이 움직일 수 있는 위치, 없앨 수 있는 상대 말 여부 저장하는 class
	class path {
		int x, y; // 도착지
		int distance; // 기존 말이 있던 위치와의 간격(대각선으로)
					  // 0 이면 바로 다음 칸이고
					  // 1또는 -1이면 기존 y좌표에서 그 방향으로 한칸 건너 뛴 칸
		
		int getX() {
			return x;
		}
		int getY() {
			return y;
		}
		int getDistance() {
			return distance;
		}
		
		public path(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
		
		public path(path otherPath) {
			this.x = otherPath.getX();
			this.y = otherPath.getY();
			this.distance = otherPath.getDistance();
		}
	}
	
	// 말을 표현하는 추상 class (Men/King 이 상속)
	abstract class Piece {
		int x, y; // 말의 보드판 위 위치
		String color; // 말의 색 (black or white)
		String name; // 말의 종류 (men or king)
		int kindOfPiece; // boardState에 저장하기 위한 말의 종류 나타내는 정수
		ImageIcon basicIcon, ClickAvailableIcon; // 평상시, 클릭가능시 아이콘
		ImageIcon ClickedIcon; // 클릭되었을 때 아이콘
		
		boolean[][] moveable = new boolean[8][8]; // 말이 움직일 수 있는 위치이면 true
		
		// 말이 움직일 수 있는 위치와 없앨 수 있는 상대 말 여부 저장하는 path 배열
		ArrayList<path> Paths = new ArrayList<path>(); 
		
		// 그 말이 우선인 말(이동하는 위치에 없앨 수 있는 상대 말 있는 것)이면 true 반환하는 함수
		public boolean prioritize() {
			for (int i=0; i<Paths.size(); i++) {
				if (Paths.get(i).distance != 0) // 사이에 말이 있으면 우선임
					return true;
			}
			return false;
		}
		
		// 특정 위치로 말 이동
		void move(int x, int y) { 
			this.x = x;
			this.y = y;
		}
		
		// moveable 배열과 Paths 세팅 함수
		abstract void setMoveable();
		
		// moveable 배열과 Paths 콘솔창에 출력
		void printMoveable() {
			for (int i=0; i<8; i++) {
				for (int j=0; j<8; j++) {
					if ((this.x == i) && (this.y == j))
						System.out.print("X ");
					else if (moveable[i][j])
						System.out.print("T ");
					else
						System.out.print("0 ");
				}
				System.out.println();
			}
			for (int i=0; i<Paths.size(); i++) {
				System.out.println("path["+i+"] : (" + Paths.get(i).getX() +
						", " + Paths.get(i).getY() + ") - distance = " + Paths.get(i).getDistance());
				
			}
			System.out.println();
		}
		
		public int getX() {
			return this.x;
		}
		public int getY() {
			return this.y;
		}
		public String getColor() {
			return this.color;
		}
		public String getName() {
			return this.name;
		}
		public int getKindOfPiece() {
			return this.kindOfPiece;
		}		
	}
	
	// 대각선 앞 방향으로 전진만 가능한 Men piece
	class Men extends Piece {
		public Men(int x, int y, String color) {
			this.x = x;
			this.y = y;
			this.color = color;
			this.name = "Men";
			
			this.basicIcon = new ImageIcon(Checkers.class.getResource("/images/"+this.color+"Men"+"Basic.png"));
			this.ClickAvailableIcon = new ImageIcon(Checkers.class.getResource("/images/" + this.color + "Men" + "ClickAvailable.png"));
			this.ClickedIcon = new ImageIcon(Checkers.class.getResource("/images/" + this.color + "Men" + "Clicked.png"));
			
			if (this.color.equals("white"))
				this.kindOfPiece = 1; // whiteMen이면
			else // blackMen이면
				this.kindOfPiece = 2;
			
			for (int i=0; i<8; i++) {
				for (int j=0; j<8; j++)
					this.moveable[i][j] = false;
			}
		}
		
		void isMoveable(int X, int Y, int direction, int rival) {
			if ((X < 0) || (Y < 0) || (X > 7) || (Y > 7))
				return;
			else {
				if (boardState[X][Y] == 0) { // 비어있는 공간이면
					this.moveable[X][Y] = true;
					if (rival > 0) { // 중간에 있는 상대편 말이 있으면...
						Paths.add(new path(X, Y, direction));
					}
					else {
						Paths.add(new path(X, Y, 0));
					}
				}
				else if ((boardState[X][Y] == kindOfPiece) ||
						(boardState[X][Y]-2 == kindOfPiece)) {
					// 같은 종류의 말이 놓여있으면
					this.moveable[X][Y] = false;
				}
				else { // 상대 말이 놓여있으면
					rival += 1;
					if ((rival % 2) == 0) // 상대 말 두번 연속으로 나온거라면
					{
						this.moveable[X][Y] = false;
					}
					else {
						// 대각선 한 방향으로만 검사 가능..
						if (this.color.equals("black")) {
							isMoveable(X+1, Y+direction, direction, rival);
						}
						else {
							isMoveable(X-1, Y+direction, direction, rival);
						}
					}
				}
			}
		}
		
		@Override
		void setMoveable() {
			this.Paths.clear();
			for (int i=0; i<8; i++) {
				for (int j=0; j<8; j++)
					this.moveable[i][j] = false;
			}
			/* blackMen */
			if (this.color.equals("black")) {
				int x1, x2; // 비교할 2개 칸의 x좌표
				int y1, y2; // 비교할 2개 칸의 y좌표
				
				x1 = this.x + 1; y1 = this.y - 1; // direction = -1
				x2 = this.x + 1; y2 = this.y + 1; // direction = +1
				
				isMoveable(x1, y1, -1, 0);
				isMoveable(x2, y2, 1, 0);
				
			}
			/* whiteMen */
			else {
				int x1, x2;
				int y1, y2;
				
				x1 = this.x - 1; y1 = this.y - 1; // direction = -1
				x2 = this.x - 1; y2 = this.y + 1; // direction = +1
				
				isMoveable(x1, y1, -1, 0);
				isMoveable(x2, y2, 1, 0);
			}
		}
	}
	// 대각선 네 방향 모두 이동 가능한 King piece
	class King extends Piece {
		public King(int x, int y, String color) {
			this.x = x;
			this.y = y;
			this.color = color;
			this.name = "King";
			
			this.basicIcon = new ImageIcon(Checkers.class.getResource("/images/"+this.color+"King"+"Basic.png"));
			this.ClickAvailableIcon = new ImageIcon(Checkers.class.getResource("/images/"+this.color+"King"+"ClickAvailable.png"));
			this.ClickedIcon = new ImageIcon(Checkers.class.getResource("/images/" + this.color + "King" + "Clicked.png"));

			if (this.color.equals("white"))
				this.kindOfPiece = 3; // whiteKing이면
			else // blackKing이면
				this.kindOfPiece = 4;
			
			for (int i=0; i<8; i++) {
				for (int j=0; j<8; j++)
					this.moveable[i][j] = false;
			}
		}
		
		void isMoveable(int X, int Y, int directionY, int directionX, int rival) {
			if ((X < 0) || (Y < 0) || (X > 7) || (Y > 7))
				return;
			else {
				if (boardState[X][Y] == 0) { // 비어있는 공간이면
					this.moveable[X][Y] = true;
					if (rival > 0) { // 중간에 있는 상대편 말이 있으면...
						Paths.add(new path(X, Y, directionY));
					}
					else {
						Paths.add(new path(X, Y, 0));
					}
				}
				else if ((boardState[X][Y] == kindOfPiece) ||
						(boardState[X][Y]+2 == kindOfPiece)) {
					// 같은 종류의 말이 놓여있으면
					this.moveable[X][Y] = false;
				}
				else { // 상대 말이 놓여있으면
					rival += 1;
					if ((rival % 2) == 0) // 상대 말 두번 연속으로 나온거라면
					{
						this.moveable[X][Y] = false;
					}
					else {
						// 대각선 한 방향으로만 검사 가능..
						isMoveable(X+directionX, Y+directionY, directionY, directionX, rival);
					}
				}
			}
		}
		
		@Override
		void setMoveable() {
			this.Paths.clear();
			for (int i=0; i<8; i++) {
				for (int j=0; j<8; j++)
					this.moveable[i][j] = false;
			}

			int x1, x2, x3, x4; // 비교할 4개 칸의 x좌표
			int y1, y2, y3, y4;// 비교할 4개 칸의 y좌표
				
			x1 = this.x + 1; y1 = this.y - 1; // directionY = -1, directionX = +1
			x2 = this.x + 1; y2 = this.y + 1; // directionY = +1, directionX = +1
			x3 = this.x - 1; y3 = this.y - 1; // directionY = -1, directionX = -1
			x4 = this.x - 1; y4 = this.y + 1; // directionY = +1, directionX = -1
			
			isMoveable(x1, y1, -1, 1, 0);
			isMoveable(x2, y2, 1, 1, 0);
			isMoveable(x3, y3, -1, -1, 0);
			isMoveable(x4, y4, 1, -1, 0);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		new MainMenu();
		dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
