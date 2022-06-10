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
	
	private JButton[][] Board = new JButton[8][8]; // ������ ��ư
	private int[][] boardState = new int[8][8]; // ������ ���� �����ϴ� �迭
	/*
	 * 0 = �� ����
	 * 1 = ��� Men
	 * 2 = ���� Men
	 * 3 = ��� King
	 * 4 = ���� King
	 */
	
	private int whiteNumber; // ����� ��
	private int blackNumber; // �������� ��
	private JLabel whiteNumberLabel = new JLabel(""); // ��� �� ���� ǥ���ϴ� label
	private JLabel blackNumberLabel = new JLabel(""); // ������ �� ���� ǥ���ϴ� label
	
	private int whiteWin = 0; // ��� �̱� Ƚ��
	private int blackWin = 0; // ������ �̱� Ƚ��
	private JLabel whiteWinLabel = new JLabel(""); // ����� �̱� �� ǥ���ϴ� label
	private JLabel blackWinLabel = new JLabel(""); // �������� �̱� �� ǥ���ϴ� label
	
	private String Turn; // ��(black / white)
	private ArrayList<Piece> blackPiece = new ArrayList<Piece>(); // ������ �� ���� �迭
	private ArrayList<Piece> whitePiece = new ArrayList<Piece>(); // ��� �� ���� �迭
	
	private ImageIcon BoardBasicIcon = new ImageIcon(
			Checkers.class.getResource("/images/BoardBasic.png")); // ������ �� ĭ �⺻ ������
	private ImageIcon BoardClickAvailableIcon = new ImageIcon(
			Checkers.class.getResource("/images/BoardClickAvailable.png"));	// ������ �� ĭ Ŭ�� ������ ���� ������
	
	private Color backgroundColor = new Color(91, 70, 56);
	private Color fontColor = new Color(255, 231, 216);
	
 	public Checkers(User pl1, User pl2) {
		makeGUI(); // ȭ�� ����
		startGame(); // ���� ����
		p1 = pl1;
		p2 = pl2;
	}
	
	/* ȭ�� ���� makeGUI */
	void makeGUI() {
		addWindowListener(this);
		setTitle("Checkers");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false); // ����ڰ� ���Ƿ� ȭ�� ũ�� ���� �Ұ���
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null); // ���� �� ��ǻ�� �߾ӿ� �ߵ���
		getContentPane().setBackground(backgroundColor); // ����ȭ�� ���� ����
		setLayout(null);
		
		/* ������ */
		JPanel boardPanel = new JPanel();
		boardPanel.setBounds(50, 30,480, 480);
		boardPanel.setLayout(new GridLayout(8, 8));
		
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				Board[i][j] = new JButton();
				Board[i][j].setSize(60, 60);
				Board[i][j].setBorderPainted(false);
				
				if ((i % 2) == (j % 2)) // ������ ���� �κ�
					Board[i][j].setBackground(new Color(230, 196, 175));
				else // ������ ��ο� �κ�(�� ĭ������ ������ �̵� ������)
					Board[i][j].setIcon(BoardBasicIcon);
				
				Board[i][j].addActionListener(new ButtonListener(i, j));
				boardPanel.add(Board[i][j]);
			}
		}
		add(boardPanel);
		
		/* �� ���� ǥ���� */		
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
		
		/* �̱� Ƚ�� ǥ���� */
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
	/* �� ���� ���� �Լ� */
	void startGame() {
		/* �� �ʱ�ȭ */
		whitePiece.clear();
		blackPiece.clear();
		
		/* �� ����� */
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
		
		/* �� ��ġ */
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
		
		/* �ʱ� ���� */
		menClick = true; // true�̸� �� Ŭ���ؾ� ��
		boardClick = false; // true�̸� ������ �� ĭ Ŭ���ؾ� ��
		next = false;
		
		// ������� ����
		Turn = "white";
		wt = new whiteTurn();
	}
	
	/* ������ ����(�� ��ġ �ٲ� ������ ����) */
	void resetBoard() {
		/* ��� �� �����ǿ� setIcon ���ֱ� */
		for (int i = 0; i < whitePiece.size(); i++) {
			Board[whitePiece.get(i).getX()][whitePiece.get(i).getY()].setIcon(whitePiece.get(i).basicIcon);
		}
		for (int i = 0; i < blackPiece.size(); i++) {
			Board[blackPiece.get(i).getX()][blackPiece.get(i).getY()].setIcon(blackPiece.get(i).basicIcon);
		}
		
		/* ��ĭ BoardBasicIcon ���� ���� ���ֱ� */
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if ( (i % 2 != j % 2) &&(boardState[i][j] == 0)) {
					Board[i][j].setIcon(BoardBasicIcon);
				}
			}
		}
		
		/* �� ���� ǥ���� ���� */
		whiteNumber = whitePiece.size();
		blackNumber = blackPiece.size();
		whiteNumberLabel.setText("white : "+ whiteNumber);
		blackNumberLabel.setText("black : "+ blackNumber);
	}

	// ��� �� ������ �� �� ���� ������ �� �ִ� ��ġ �����ϴ� class
	class whiteTurn {
		private int numberOfPriority = 0; // �켱�Ǵ� �� ����
		private int[][] priorityBoard = new int[8][8]; // �켱�Ǵ� ��(���� ���� Ŭ���� �� �ִ� ��)�� �� �����س��� �迭
		
		public int getNumberOfPriority() {
			return numberOfPriority;
		}
		public int getPriorityBoard(int x, int y) {
			return priorityBoard[x][y];
		}
 		public whiteTurn() {
			/* �� ���� ������ �� �ִ� ��ġ setting */
			for (int i=0; i<whitePiece.size(); i++) {
				whitePiece.get(i).Paths.clear(); // i��°�� ���� ������ �� �ִ� �� �ʱ�ȭ
				whitePiece.get(i).setMoveable(); 
				System.out.println("white["+i+"]");
				whitePiece.get(i).printMoveable();
			
				if (whitePiece.get(i).prioritize()) {
					numberOfPriority += 1;
				}
			}
			
			clickAvailablePieceNumber = 0;
			/* ������ �������� �ϴ� ��(��� �� ���� �� �ִ� ��) ������ */
			if (numberOfPriority == 0) {
				/* ��� ������ �� �ִ� �� Ŭ�� �����ϰ� �ϱ� */
				for (int i=0; i<whitePiece.size(); i++) // �� ������ŭ
				{
					if ((whitePiece.get(i).Paths).size() != 0) { // ������ �� �ִ� �� ������ Ŭ�� �����ϵ���
						clickAvailablePieceNumber += 1;
						priorityBoard[whitePiece.get(i).getX()][whitePiece.get(i).getY()] = 1;
						Board[whitePiece.get(i).getX()][whitePiece.get(i).getY()].setIcon(whitePiece.get(i).ClickAvailableIcon);
					}
				}
			}
			/* ������ �������� �ϴ� �� ������ */
			else {
				/*  ������ �������� �ϴ� ���� Ŭ�� �����ϰ� �ϱ� */
				for (int i=0; i<whitePiece.size(); i++) {
					if (whitePiece.get(i).prioritize()) {
						clickAvailablePieceNumber += 1;
						priorityBoard[whitePiece.get(i).getX()][whitePiece.get(i).getY()] = 2;
						// ���� 2�� ������ �߰��� ���� ���� �ִٴ� ���� ǥ��
						Board[whitePiece.get(i).getX()][whitePiece.get(i).getY()].setIcon(whitePiece.get(i).ClickAvailableIcon);;
					}
				}
			}
		}
	}
	// ������ �� ������ �� �� ���� ������ �� �ִ� ��ġ �����ϴ� class
	class blackTurn {
		private int numberOfPriority = 0; // �켱�Ǵ� �� ����
		private int[][] priorityBoard = new int[8][8]; // �켱�Ǵ� ��(���� ���� Ŭ���� �� �ִ� ��)�� �� �����س��� �迭
		
		public int getNumberOfPriority() {
			return numberOfPriority;
		}
		public int getPriorityBoard(int x, int y) {
			return priorityBoard[x][y];
		}
		public blackTurn() {
			/* �� ���� ������ �� �ִ� ��ġ setting */
			for (int i=0; i<blackPiece.size(); i++) {
				blackPiece.get(i).setMoveable(); 
				System.out.println("black["+i+"]");
				blackPiece.get(i).printMoveable();
			
				if (blackPiece.get(i).prioritize()) {		
					numberOfPriority += 1;
				}
			}
			
			clickAvailablePieceNumber = 0;
			/* ������ �������� �ϴ� ��(��� �� ���� �� �ִ� ��) ������ */
			if (numberOfPriority == 0) {
				/* ��� ������ �� �ִ� �� Ŭ�� �����ϰ� �ϱ� */
				for (int i=0; i<blackPiece.size(); i++) // �� ������ŭ
				{
					if ((blackPiece.get(i).Paths).size() != 0) { // ������ �� �ִ� �� ������ Ŭ�� �����ϵ���
						clickAvailablePieceNumber += 1;
						priorityBoard[blackPiece.get(i).getX()][blackPiece.get(i).getY()] = 1;
						Board[blackPiece.get(i).getX()][blackPiece.get(i).getY()].setIcon(blackPiece.get(i).ClickAvailableIcon);
					}
				}
			}
			/* ������ �������� �ϴ� �� ������ */
			else {
				/*  ������ �������� �ϴ� ���� Ŭ�� �����ϰ� �ϱ� */
				for (int i=0; i<blackPiece.size(); i++) {
					if (blackPiece.get(i).prioritize()) {
						clickAvailablePieceNumber += 1;
						priorityBoard[blackPiece.get(i).getX()][blackPiece.get(i).getY()] = 2;
						// ���� 2�� ������ �߰��� ���� ���� �ִٴ� ���� ǥ��
						Board[blackPiece.get(i).getX()][blackPiece.get(i).getY()].setIcon(blackPiece.get(i).ClickAvailableIcon);
					}
				}
			}
		}
	}
	
	private whiteTurn wt;
	private blackTurn bt;
	
	boolean menClick = true; // true�̸� �� Ŭ���ؾ� ��
	boolean boardClick = false; // true�̸� ������ �� ĭ Ŭ���ؾ� ��
	boolean next = false;
	
	int clickedPieceIndex; // Ŭ���� ���� �ε���
	ArrayList<path> availablePaths = new ArrayList<path>(); // Ŭ���� ���� �̵��� �� �ִ� ��ġ �����ϴ� �迭
	int clickAvailablePieceNumber; // Ŭ�� ������ ���� ��
	
	// ������ �� ��ư Ŭ�� �� action �����ϴ� class 
	class ButtonListener implements ActionListener {
		private int x; // ������ x��ǥ
		private int y; // ������ y��ǥ
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
			if ((x%2)!=(y%2)) // ������ �� �ִ� ������ ��쿡��!
			{
				if (menClick) // �� Ŭ���ؾ� �ϴ� ���
				{
					availablePaths.clear();
					if (Turn.equals("white")) // ��� ���̸�
					{	
						// ������ �� �ִ� ��� Men �Ǵ� ��� King Ŭ���ؾ� ��
						if (wt.getPriorityBoard(x, y) > 0) {
							menClick = false;
							boardClick = true;
							System.out.printf("white men(%d, %d) clicked\n", x, y);
							
							// Ŭ���� ���� �̵������� ��ġ ����
							for (int i=0; i<whitePiece.size(); i++) {
								if (whitePiece.get(i).getX() == x && whitePiece.get(i).getY() == y) {
									Board[x][y].setIcon(whitePiece.get(i).ClickedIcon);
									clickedPieceIndex = i;
									for (int j=0; j<whitePiece.get(i).Paths.size(); j++) {
										if (wt.getPriorityBoard(x, y) == 2) // �߰��� ���� �� �ִ� �� ������
										{
											next = true;
											if (whitePiece.get(i).Paths.get(j).getDistance() != 0) {
												availablePaths.add(new path(whitePiece.get(i).Paths.get(j)));
												// Ŭ�� ������ ĭ���� ǥ��
												Board[whitePiece.get(i).Paths.get(j).getX()][whitePiece.get(i).Paths.get(j).getY()].setIcon(BoardClickAvailableIcon);
											}
										}
										else // �߰��� ���� �� �ִ� �� ������
										{	
											availablePaths.add(new path(whitePiece.get(i).Paths.get(j)));
											// Ŭ�� ������ ĭ���� ǥ��
											Board[whitePiece.get(i).Paths.get(j).getX()][whitePiece.get(i).Paths.get(j).getY()].setIcon(BoardClickAvailableIcon);
										}
									}
									break;
								}
							}
						}
					}
					else // ��� ���̸� 
					{	
						// ��� Men �Ǵ� ��� King Ŭ���ؾ� ��
						if (bt.getPriorityBoard(x, y) > 0) {
							menClick = false;
							boardClick = true;
							System.out.printf("black men(%d, %d) clicked\n", x, y);
							
							// Ŭ���� ���� �̵������� ��ġ ����
							for (int i=0; i<blackPiece.size(); i++) {
								if (blackPiece.get(i).getX() == x && blackPiece.get(i).getY() == y) {
									Board[x][y].setIcon(blackPiece.get(i).ClickedIcon);
									clickedPieceIndex = i;
									for (int j=0; j < blackPiece.get(i).Paths.size(); j++) {
										if (bt.getPriorityBoard(x, y) == 2) // �߰��� ���� �� �ִ� �� ������
										{
											next = true; 
											if (blackPiece.get(i).Paths.get(j).getDistance() != 0) {
												availablePaths.add(new path(blackPiece.get(i).Paths.get(j)));
												// Ŭ�� ������ ĭ���� ǥ��
												Board[blackPiece.get(i).Paths.get(j).getX()][blackPiece.get(i).Paths.get(j).getY()].setIcon(BoardClickAvailableIcon);
											}
										}
										else // �߰��� ���� �� �ִ� �� ������ 
										{
											availablePaths.add(new path(blackPiece.get(i).Paths.get(j)));
											// Ŭ�� ������ ĭ���� ǥ��
											Board[blackPiece.get(i).Paths.get(j).getX()][blackPiece.get(i).Paths.get(j).getY()].setIcon(BoardClickAvailableIcon);
										}
									}
									break;
								}
							}
						}
					}
				}
			
				else // ������ ĭ Ŭ���ؾ� �ϴ� ���
				{
					for (int i=0; i<availablePaths.size(); i++) {
						if (x == availablePaths.get(i).getX() && y == availablePaths.get(i).getY()) {
							// Ŭ���� �� �ִ� �� Ŭ����
							if (Turn.equals("white")) // ��� ��
							{
								/* ���̿� ���� �� ���� ��� */
								if (availablePaths.get(i).getDistance() == 0) {
									// 1) ���� �� ��ġ�� boardState 0���� �ٲٱ�
									// 2) �� ��ġ ���� (Piece.move(x, y))
									// 3) �ű� �ڸ� boardState�� kindOfPiece�� �ٲٱ�
									boardState[whitePiece.get(clickedPieceIndex).getX()][whitePiece.get(clickedPieceIndex).getY()]
											= 0;
									whitePiece.get(clickedPieceIndex).move(x, y);
									
									// 4) ���� �ű� ���� �ڽ� ��ġ�κ��� ������ ��(x == 0)�� �����ϸ� King���� �±�(�̹� king�� ��� ����)
									if (x == 0 && whitePiece.get(clickedPieceIndex).getKindOfPiece() == 1) {
										whitePiece.remove(clickedPieceIndex);
										whitePiece.add(clickedPieceIndex, new King(x, y, "white"));
									}
									boardState[x][y] = whitePiece.get(clickedPieceIndex).getKindOfPiece();
								}
								/* ���̿� ���� �� �ִ� ��� */
								else {
									// 1) ���� �� ��ġ�� boardState 0���� �ٲٱ�
									// 2) �� ��ġ ���� (Piece.move(x, y))
									// 3) �ű� �ڸ� boardState�� kindOfPiece�� �ٲٱ�
									// 4) ���� �� ã�Ƽ� ���ְ�, �� �ڸ� boardState 0���� �ٲٱ�
									boardState[whitePiece.get(clickedPieceIndex).getX()][whitePiece.get(clickedPieceIndex).getY()]
											= 0;
									int xOfRemove, yOfRemove; // ���� ���� x, y��ǥ
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
									
									// 5) ���� �ű� ���� �ڽ� ��ġ�κ��� ������ ��(x == 0)�� �����ϸ� King���� �±�(�̹� king�� ��� ����)
									if (x == 0 && whitePiece.get(clickedPieceIndex).getKindOfPiece() == 1) {
										whitePiece.remove(clickedPieceIndex);
										whitePiece.add(clickedPieceIndex, new King(x, y, "white"));
									}
									boardState[x][y] = whitePiece.get(clickedPieceIndex).getKindOfPiece();
								}
							}
							else // ��� ��
							{
								/* ���̿� ���� �� ���� ��� */
								if (availablePaths.get(i).getDistance() == 0) {
									// 1) ���� �� ��ġ�� boardState 0���� �ٲٱ�
									// 2) �� ��ġ ���� (Piece.move(x, y))
									// 3) �ű� �ڸ� boardState�� kindOfPiece�� �ٲٱ�
									boardState[blackPiece.get(clickedPieceIndex).getX()][blackPiece.get(clickedPieceIndex).getY()]
											= 0;
									blackPiece.get(clickedPieceIndex).move(x, y);
									
									// 4) ���� �ű� ���� �ڽ� ��ġ�κ��� ������ ��(x == 7)�� �����ϸ� King���� �±�(�̹� king�� ��� ����)
									if (x == 7 && blackPiece.get(clickedPieceIndex).getKindOfPiece() == 2) {
										blackPiece.remove(clickedPieceIndex);
										blackPiece.add(clickedPieceIndex, new King(x, y, "black"));
									}
									boardState[x][y] = blackPiece.get(clickedPieceIndex).getKindOfPiece();
								}
								/* ���̿� ���� �� �ִ� ��� */
								else {
									// 1) ���� �� ��ġ�� boardState 0���� �ٲٱ�
									// 2) �� ��ġ ���� (Piece.move(x, y))
									// 3) �ű� �ڸ� boardState�� kindOfPiece�� �ٲٱ�
									// 4) ���� �� ã�Ƽ� ���ְ�, �� �ڸ� boardState 0���� �ٲٱ�
									boardState[blackPiece.get(clickedPieceIndex).getX()][blackPiece.get(clickedPieceIndex).getY()]
											= 0;
									int xOfRemove, yOfRemove; // ���� ���� x, y��ǥ
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
									
									// 5) ���� �ű� ���� �ڽ� ��ġ�κ��� ������ ��(x == 7)�� �����ϸ� King���� �±�(�̹� king�� ��� ����)
									if (x == 7 && blackPiece.get(clickedPieceIndex).getKindOfPiece() == 2) {
										blackPiece.remove(clickedPieceIndex);
										blackPiece.add(clickedPieceIndex, new King(x, y, "black"));
									}
									boardState[x][y] = blackPiece.get(clickedPieceIndex).getKindOfPiece();
								}
							}
							
							
							resetBoard();
							
							/*
							 * ��� ���ָ� �̵��� �� �ִ� ���� ������ �� ���� �̵��ϰ� �ϰ�
							 * �׷��� ���� ��쿣 �� ��ü!!
							 */
							if (Turn.equals("white")) {
								if (next && isAvailableNext(whitePiece.get(clickedPieceIndex))) // �� �� ���ְ� �� ���� �� ������
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
								 * 	���� ������ 0�� �Ǵ�
								 *  ������ �� �ִ� ���� ������ 0���̸� �й�
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
								if (next && isAvailableNext(blackPiece.get(clickedPieceIndex))) // �� �� ���ְ� �� ���� �� ������
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
								 * 	���� ������ 0�� �Ǵ�
								 *  ������ �� �ִ� ���� ������ 0���̸� �й�
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
	
	// Piece ����ϴ� class�鿡�� ���� ������ �� �ִ� ��ġ, ���� �� �ִ� ��� �� ���� �����ϴ� class
	class path {
		int x, y; // ������
		int distance; // ���� ���� �ִ� ��ġ���� ����(�밢������)
					  // 0 �̸� �ٷ� ���� ĭ�̰�
					  // 1�Ǵ� -1�̸� ���� y��ǥ���� �� �������� ��ĭ �ǳ� �� ĭ
		
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
	
	// ���� ǥ���ϴ� �߻� class (Men/King �� ���)
	abstract class Piece {
		int x, y; // ���� ������ �� ��ġ
		String color; // ���� �� (black or white)
		String name; // ���� ���� (men or king)
		int kindOfPiece; // boardState�� �����ϱ� ���� ���� ���� ��Ÿ���� ����
		ImageIcon basicIcon, ClickAvailableIcon; // ����, Ŭ�����ɽ� ������
		ImageIcon ClickedIcon; // Ŭ���Ǿ��� �� ������
		
		boolean[][] moveable = new boolean[8][8]; // ���� ������ �� �ִ� ��ġ�̸� true
		
		// ���� ������ �� �ִ� ��ġ�� ���� �� �ִ� ��� �� ���� �����ϴ� path �迭
		ArrayList<path> Paths = new ArrayList<path>(); 
		
		// �� ���� �켱�� ��(�̵��ϴ� ��ġ�� ���� �� �ִ� ��� �� �ִ� ��)�̸� true ��ȯ�ϴ� �Լ�
		public boolean prioritize() {
			for (int i=0; i<Paths.size(); i++) {
				if (Paths.get(i).distance != 0) // ���̿� ���� ������ �켱��
					return true;
			}
			return false;
		}
		
		// Ư�� ��ġ�� �� �̵�
		void move(int x, int y) { 
			this.x = x;
			this.y = y;
		}
		
		// moveable �迭�� Paths ���� �Լ�
		abstract void setMoveable();
		
		// moveable �迭�� Paths �ܼ�â�� ���
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
	
	// �밢�� �� �������� ������ ������ Men piece
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
				this.kindOfPiece = 1; // whiteMen�̸�
			else // blackMen�̸�
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
				if (boardState[X][Y] == 0) { // ����ִ� �����̸�
					this.moveable[X][Y] = true;
					if (rival > 0) { // �߰��� �ִ� ����� ���� ������...
						Paths.add(new path(X, Y, direction));
					}
					else {
						Paths.add(new path(X, Y, 0));
					}
				}
				else if ((boardState[X][Y] == kindOfPiece) ||
						(boardState[X][Y]-2 == kindOfPiece)) {
					// ���� ������ ���� ����������
					this.moveable[X][Y] = false;
				}
				else { // ��� ���� ����������
					rival += 1;
					if ((rival % 2) == 0) // ��� �� �ι� �������� ���°Ŷ��
					{
						this.moveable[X][Y] = false;
					}
					else {
						// �밢�� �� �������θ� �˻� ����..
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
				int x1, x2; // ���� 2�� ĭ�� x��ǥ
				int y1, y2; // ���� 2�� ĭ�� y��ǥ
				
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
	// �밢�� �� ���� ��� �̵� ������ King piece
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
				this.kindOfPiece = 3; // whiteKing�̸�
			else // blackKing�̸�
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
				if (boardState[X][Y] == 0) { // ����ִ� �����̸�
					this.moveable[X][Y] = true;
					if (rival > 0) { // �߰��� �ִ� ����� ���� ������...
						Paths.add(new path(X, Y, directionY));
					}
					else {
						Paths.add(new path(X, Y, 0));
					}
				}
				else if ((boardState[X][Y] == kindOfPiece) ||
						(boardState[X][Y]+2 == kindOfPiece)) {
					// ���� ������ ���� ����������
					this.moveable[X][Y] = false;
				}
				else { // ��� ���� ����������
					rival += 1;
					if ((rival % 2) == 0) // ��� �� �ι� �������� ���°Ŷ��
					{
						this.moveable[X][Y] = false;
					}
					else {
						// �밢�� �� �������θ� �˻� ����..
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

			int x1, x2, x3, x4; // ���� 4�� ĭ�� x��ǥ
			int y1, y2, y3, y4;// ���� 4�� ĭ�� y��ǥ
				
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
