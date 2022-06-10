package Monorail;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.User;
import Main.MainMenu;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Monorail extends JFrame implements ActionListener, WindowListener{

	public static final int WIDTH = 320;
	public static final int HEIGHT = 500;
	public static final int N = 10;
	public static final int MAX_TILES = 18;
	public static final int MAX_TEMP_TILES = 3;
	public static final int TURN_1P = 0;
	public static final int TURN_2P = 1;
	private User p1, p2;
	
	private JLabel turnLabel;
	private JLabel tileLabel;
	private JLabel[][] img;
	private int[][] tileMap;
	private ArrayList<Tile> tiles;
	private ArrayList<Tile> tempTiles;
	private int tempTileCount;
	private int turn;
	private int tileCount;
	private int focusX;
	private int focusY;
	private int focusTile;
	private boolean impCheck;
	private ImageIcon icon0_0;
	private ImageIcon icon1_0;
	private ImageIcon icon1_1;
	private ImageIcon icon1_2;
	private ImageIcon icon2_0;
	private ImageIcon icon2_1;
	private ImageIcon icon2_2;
	private ImageIcon icon3_0;
	private ImageIcon icon3_1;
	private ImageIcon icon3_2;
	private ImageIcon icon4_0;
	private ImageIcon icon4_1;
	private ImageIcon icon4_2;
	private ImageIcon icon5_0;
	private ImageIcon icon5_1;
	private ImageIcon icon5_2;
	private ImageIcon icon6_0;
	private ImageIcon icon6_1;
	private ImageIcon icon6_2;
	private Image im;
	private ImageIcon icon;
	
	public Monorail(User pl1, User pl2)
	{
		addWindowListener(this);
		//-----Set Default------//
		p1 = pl1;
		p2 = pl2;
		tileMap = new int[N][N];
		tiles = new ArrayList<Tile>(MAX_TILES);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				tileMap[i][j] = -1;
		tempTiles = new ArrayList<Tile>(MAX_TILES);
		tempTileCount = 0;
		tileCount = 0;
		turn = TURN_1P;
		impCheck = false;
		icon = new ImageIcon(Monorail.class.getResource("/tile/0.png"));
		im = icon.getImage().getScaledInstance(30,  30,  Image.SCALE_DEFAULT);
		icon0_0 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/1.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon1_0 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/1_1.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon1_1 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/1_2.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon1_2 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/2.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon2_0 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/2_1.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon2_1 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/2_2.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon2_2 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/3.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon3_0 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/3_1.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon3_1 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/3_2.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon3_2 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/4.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon4_0 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/4_1.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon4_1 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/4_2.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon4_2 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/5.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon5_0 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/5_1.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon5_1 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/5_2.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon5_2 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/6.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon6_0 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/6_1.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon6_1 = new ImageIcon(im);
		icon = new ImageIcon(Monorail.class.getResource("/tile/6_2.png"));
		im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon6_2 = new ImageIcon(im);
		
		setTitle("Monorail");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setResizable(false);
		
		//-----Labels Panel-----//
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(2, 1));
		turnLabel = new JLabel("1P Turn");
		labelPanel.add(turnLabel);
		tileLabel = new JLabel("Tileleft: 16");
		labelPanel.add(tileLabel);
		add(labelPanel);
		
		//-----Game Panel-----//
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(N, N));
		img = new JLabel[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				img[i][j] = new JLabel(icon0_0);
				gamePanel.add(img[i][j]);
			}
		add(gamePanel);

		//-----Tile Buttons-----//
		JPanel tilePanel = new JPanel();
		tilePanel.setLayout(new GridLayout(2, 6));
		JButton button1 = new JButton("─");
		JButton button2 = new JButton("│");
		JButton button3 = new JButton("┌");
		JButton button4 = new JButton("┐");
		JButton button5 = new JButton("└");
		JButton button6 = new JButton("┘");
		JButton button7 = new JButton("▲");
		JButton button8 = new JButton("▼");
		JButton button9 = new JButton("◀");
		JButton button10 = new JButton("▶");
		JButton button11 = new JButton("O");
		JButton button12 = new JButton("X");
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);
		button10.addActionListener(this);
		button11.addActionListener(this);
		button12.addActionListener(this);
		tilePanel.add(button1);
		tilePanel.add(button2);
		tilePanel.add(button3);
		tilePanel.add(button4);
		tilePanel.add(button5);
		tilePanel.add(button6);
		tilePanel.add(button7);
		tilePanel.add(button8);
		tilePanel.add(button9);
		tilePanel.add(button10);
		tilePanel.add(button11);
		tilePanel.add(button12);
		add(tilePanel);
		
		//-----Game Buttons-----//
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		JButton button13 = new JButton("Turn End");
		button13.addActionListener(this);
		btnPanel.add(button13);
		JButton button14 = new JButton("Impossible");
		button14.addActionListener(this);
		btnPanel.add(button14);
		add(btnPanel);
		
		//-----Start Setting-----//
		addStraightTile(N / 2, N / 2 - 1, StraightTile.STRAIGHT_HORIZONTAL);
		addStraightTile(N / 2, N / 2, StraightTile.STRAIGHT_HORIZONTAL);
		focusX = N / 2; focusY = N / 2 + 1;
		focusTile = 1;
		img[focusX][focusY].setIcon(icon1_2);
	}
	
	public void addStraightTile(int xpos, int ypos, int straightType)
	{
		tiles.add(new StraightTile(xpos, ypos, straightType));
		tileMap[xpos][ypos] = tileCount++;
		if (straightType == StraightTile.STRAIGHT_HORIZONTAL)
			img[xpos][ypos].setIcon(icon1_0);
		else
			img[xpos][ypos].setIcon(icon2_0);
	}
	
	public void addTempStraightTile(int xpos, int ypos, int straightType)
	{
		tempTiles.add(new StraightTile(xpos, ypos, straightType));
		tileMap[xpos][ypos] = 100 + tempTileCount++;
	}
	
	public void addCurvedTile(int xpos, int ypos, int curvedType)
	{
		tiles.add(new StraightTile(xpos, ypos, curvedType));
		tileMap[xpos][ypos] = tileCount++;
		if (curvedType == CurvedTile.CURVED_1ST_QUADRANT)
			img[xpos][ypos].setIcon(icon5_0);
		else if (curvedType == CurvedTile.CURVED_2ND_QUADRANT)
			img[xpos][ypos].setIcon(icon6_0);
		else if (curvedType == CurvedTile.CURVED_3RD_QUADRANT)
			img[xpos][ypos].setIcon(icon4_0);
		else if (curvedType == CurvedTile.CURVED_4TH_QUADRANT)
			img[xpos][ypos].setIcon(icon3_0);
	}
	
	public void addTempCurvedTile(int xpos, int ypos, int curvedType)
	{
		tempTiles.add(new CurvedTile(xpos, ypos, curvedType));
		tileMap[xpos][ypos] = 100 + tempTileCount++;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String actionCmd = e.getActionCommand();
		// Move Focus Tile
		if (actionCmd.equals("◀") || actionCmd.equals("▶") || actionCmd.equals("▲") || actionCmd.equals("▼"))
		{
			if (tileMap[focusX][focusY] == -1)
				img[focusX][focusY].setIcon(icon0_0);
			else if (tileMap[focusX][focusY] >= 100)
			{
				if (tempTiles.get(tileMap[focusX][focusY] - 100).getTileType() == StraightTile.STRAIGHT_HORIZONTAL)
					img[focusX][focusY].setIcon(icon1_1);
				else if (tempTiles.get(tileMap[focusX][focusY] - 100).getTileType() == StraightTile.STRAIGHT_VERTICAL)
					img[focusX][focusY].setIcon(icon2_1);
				else if (tempTiles.get(tileMap[focusX][focusY] - 100).getTileType() == CurvedTile.CURVED_1ST_QUADRANT)
					img[focusX][focusY].setIcon(icon5_1);
				else if (tempTiles.get(tileMap[focusX][focusY] - 100).getTileType() == CurvedTile.CURVED_2ND_QUADRANT)
					img[focusX][focusY].setIcon(icon6_1);
				else if (tempTiles.get(tileMap[focusX][focusY] - 100).getTileType() == CurvedTile.CURVED_3RD_QUADRANT)
					img[focusX][focusY].setIcon(icon4_1);
				else if (tempTiles.get(tileMap[focusX][focusY] - 100).getTileType() == CurvedTile.CURVED_4TH_QUADRANT)
					img[focusX][focusY].setIcon(icon3_1);
			}
			else
			{
				if (tiles.get(tileMap[focusX][focusY]).getTileType() == StraightTile.STRAIGHT_HORIZONTAL)
					img[focusX][focusY].setIcon(icon1_0);
				else if (tiles.get(tileMap[focusX][focusY]).getTileType() == StraightTile.STRAIGHT_VERTICAL)
					img[focusX][focusY].setIcon(icon2_0);
				else if (tiles.get(tileMap[focusX][focusY]).getTileType() == CurvedTile.CURVED_1ST_QUADRANT)
					img[focusX][focusY].setIcon(icon5_0);
				else if (tiles.get(tileMap[focusX][focusY]).getTileType() == CurvedTile.CURVED_2ND_QUADRANT)
					img[focusX][focusY].setIcon(icon6_0);
				else if (tiles.get(tileMap[focusX][focusY]).getTileType() == CurvedTile.CURVED_3RD_QUADRANT)
					img[focusX][focusY].setIcon(icon4_0);
				else if (tiles.get(tileMap[focusX][focusY]).getTileType() == CurvedTile.CURVED_4TH_QUADRANT)
					img[focusX][focusY].setIcon(icon3_0);
			}
			
			if (actionCmd.equals("◀"))
			{
				if (focusY > 0)
					focusY--;
			}
			else if (actionCmd.equals("▶"))
			{
				if (focusY < N - 1)
					focusY++;
			}
			else if (actionCmd.equals("▲"))
			{
				if (focusX > 0)
					focusX--;
			}
			else if (actionCmd.equals("▼"))
			{
				if (focusX < N - 1)
					focusX++;
			}
			
			if (focusTile == 1)
				img[focusX][focusY].setIcon(icon1_2);
			else if (focusTile == 2)
				img[focusX][focusY].setIcon(icon2_2);
			else if (focusTile == 3)
				img[focusX][focusY].setIcon(icon3_2);
			else if (focusTile == 4)
				img[focusX][focusY].setIcon(icon4_2);
			else if (focusTile == 5)
				img[focusX][focusY].setIcon(icon5_2);
			else if (focusTile == 6)
				img[focusX][focusY].setIcon(icon6_2);
		}
		// Change Focus Tile
		else if (actionCmd.equals("─") || actionCmd.equals("│") ||
				actionCmd.equals("┌") || actionCmd.equals("┐") || actionCmd.equals("└") || actionCmd.equals("┘"))
		{
			if (actionCmd.equals("─"))
				focusTile = 1;
			else if (actionCmd.equals("│"))
				focusTile = 2;
			else if (actionCmd.equals("┌"))
				focusTile = 3;
			else if (actionCmd.equals("┐"))
				focusTile = 4;
			else if (actionCmd.equals("└"))
				focusTile = 5;
			else if (actionCmd.equals("┘"))
				focusTile = 6;
			
			if (focusTile == 1)
				img[focusX][focusY].setIcon(icon1_2);
			else if (focusTile == 2)
				img[focusX][focusY].setIcon(icon2_2);
			else if (focusTile == 3)
				img[focusX][focusY].setIcon(icon3_2);
			else if (focusTile == 4)
				img[focusX][focusY].setIcon(icon4_2);
			else if (focusTile == 5)
				img[focusX][focusY].setIcon(icon5_2);
			else if (focusTile == 6)
				img[focusX][focusY].setIcon(icon6_2);
		}
		// Put Focus Tile
		else if (actionCmd.equals("O"))
		{
			if (tileMap[focusX][focusY] == -1 && tempTileCount < MAX_TILES - tileCount && (impCheck || tempTileCount < MAX_TEMP_TILES))
			{
				if (focusTile == 1)
					addTempStraightTile(focusX, focusY, StraightTile.STRAIGHT_HORIZONTAL);
				else if (focusTile == 2)
					addTempStraightTile(focusX, focusY, StraightTile.STRAIGHT_VERTICAL);
				else if (focusTile == 3)
					addTempCurvedTile(focusX, focusY, CurvedTile.CURVED_4TH_QUADRANT);
				else if (focusTile == 4)
					addTempCurvedTile(focusX, focusY, CurvedTile.CURVED_3RD_QUADRANT);
				else if (focusTile == 5)
					addTempCurvedTile(focusX, focusY, CurvedTile.CURVED_1ST_QUADRANT);
				else if (focusTile == 6)
					addTempCurvedTile(focusX, focusY, CurvedTile.CURVED_2ND_QUADRANT);
			}
		}
		// Pop Focus Tile
		else if (actionCmd.equals("X"))
		{
			if (tileMap[focusX][focusY] >= 100)
			{
				tempTiles.remove(tileMap[focusX][focusY] - 100);
				for (int i = 0; i < N; i++)
					for (int j = 0; j < N; j++)
						if (tileMap[i][j] > tileMap[focusX][focusY])
							tileMap[i][j]--;
				tempTileCount--;
				tileMap[focusX][focusY] = -1;
			}
		}
		// End Turn
		else if (actionCmd.equals("Turn End"))
		{
			if (impCheck || (tempTileCount > 0 && isAddOkay()))
			{
				for (Tile x : tempTiles)
				{
					if (x.getTileType() == StraightTile.STRAIGHT_HORIZONTAL
							|| x.getTileType() == StraightTile.STRAIGHT_VERTICAL)
						addStraightTile(x.getXPos(), x.getYPos(), x.getTileType());
					else if (x.getTileType() == CurvedTile.CURVED_1ST_QUADRANT
							|| x.getTileType() == CurvedTile.CURVED_2ND_QUADRANT
							|| x.getTileType() == CurvedTile.CURVED_3RD_QUADRANT
							|| x.getTileType() == CurvedTile.CURVED_4TH_QUADRANT)
						addCurvedTile(x.getXPos(), x.getYPos(), x.getTileType());
				}
				tileLabel.setText("Tileleft: " + Integer.toString(MAX_TILES - tileCount));
				if (isOnlyCycle())
				{
					if (turn == TURN_1P)
						User.p1Win(p1, p2);
					else
						User.p2Win(p1, p2);
					reset();
				}
				else if (impCheck)
				{
					if (turn == TURN_1P)
						User.p2Win(p1, p2);
					else
						User.p1Win(p1, p2);
					reset();
				}
				else if (MAX_TILES - tileCount == 0)
				{
					if (turn == TURN_1P)
						User.p2Win(p1, p2);
					else
						User.p1Win(p1, p2);
					reset();
				}
				else
				{
					if (turn == TURN_1P)
					{
						turn = TURN_2P;
						turnLabel.setText("2P Turn");
					}
					else
					{
						turn = TURN_1P;
						turnLabel.setText("1P Turn");
					}
					tempTileCount = 0;
					tempTiles.clear();
				}
			}
			else
				JOptionPane.showMessageDialog(this, "놓을 수 없는 위치의 타일이 있습니다.\n(타일은 최소 1개, 최대 3개"
						+ "일직선이 되어야 하며 기존 타일에 인접해야합니다.)", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (actionCmd.equals("Impossible"))
		{
			if (impCheck == false)
			{
				impCheck = true;
				if (turn == TURN_1P)
				{
					turn = TURN_2P;
					turnLabel.setText("2P Turn");
				}
				else
				{
					turn = TURN_1P;
					turnLabel.setText("1P Turn");
				}
			}
		}
	}
	
	public boolean isOnlyCycle()
	{
		boolean[][] visit = new boolean[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				visit[i][j] = false;
		int mpX = N / 2;
		int mpY = N / 2 - 1;
		int vCount = 0;
		
		while(true)
		{
			if (mpX >= N || mpY >= N || mpX < 0 || mpY < 0)
				break;
			else if (tileMap[mpX][mpY] == -1)
				break;
			
			if (visit[mpX][mpY] == false)
				visit[mpX][mpY] = true;
			vCount++;
			if (tiles.get(tileMap[mpX][mpY]).getTileType() == StraightTile.STRAIGHT_HORIZONTAL)
			{
				if (visit[mpX][mpY + 1] == false)
					mpY++;
				else if (visit[mpX][mpY - 1] == false)
					mpY--;
				else
					break;
			}
			else if (tiles.get(tileMap[mpX][mpY]).getTileType() == StraightTile.STRAIGHT_VERTICAL)
			{
				if (visit[mpX + 1][mpY] == false)
					mpX++;
				else if (visit[mpX - 1][mpY] == false)
					mpX--;
				else
					break;
			}
			else if (tiles.get(tileMap[mpX][mpY]).getTileType() == CurvedTile.CURVED_1ST_QUADRANT)
			{
				if (visit[mpX - 1][mpY] == false)
					mpX--;
				else if (visit[mpX][mpY + 1] == false)
					mpY++;
				else
					break;
			}
			else if (tiles.get(tileMap[mpX][mpY]).getTileType() == CurvedTile.CURVED_2ND_QUADRANT)
			{
				if (visit[mpX - 1][mpY] == false)
					mpX--;
				else if (visit[mpX][mpY - 1] == false)
					mpY--;
				else
					break;
			}
			else if (tiles.get(tileMap[mpX][mpY]).getTileType() == CurvedTile.CURVED_3RD_QUADRANT)
			{
				if (visit[mpX + 1][mpY] == false)
					mpX++;
				else if (visit[mpX][mpY - 1] == false)
					mpY--;
				else
					break;
			}
			else if (tiles.get(tileMap[mpX][mpY]).getTileType() == CurvedTile.CURVED_4TH_QUADRANT)
			{
				if (visit[mpX + 1][mpY] == false)
					mpX++;
				else if (visit[mpX][mpY + 1] == false)
					mpY++;
				else
					break;
			}
		}
		
		if (mpX == N / 2 && mpY == N / 2 - 2)
		{
			if (tileMap[mpX][mpY] == -1)
				return false;
			else if (vCount != tileCount)
				return false;
			else if (tiles.get(tileMap[mpX][mpY]).getTileType() == StraightTile.STRAIGHT_HORIZONTAL
			|| tiles.get(tileMap[mpX][mpY]).getTileType() == CurvedTile.CURVED_1ST_QUADRANT
			|| tiles.get(tileMap[mpX][mpY]).getTileType() == CurvedTile.CURVED_4TH_QUADRANT)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public boolean isAddOkay()
	{
		boolean isOneLine = true, isAdj = false;
		int idx = 0;
		int fixX = 0, fixY = 0;
		for (Tile x : tempTiles)
		{
			if (idx == 0)
				fixX = x.getXPos();
			else
			{
				if (x.getXPos() != fixX)
					isOneLine = false;
				if (!(tileMap[x.getXPos()][x.getYPos() + 1] >= 100
					|| tileMap[x.getXPos()][x.getYPos() - 1] >= 100))
					isOneLine = false;
			}
			idx++;
		}
		idx = 0;
		if (!isOneLine)
		{
			isOneLine = true;
			for (Tile x : tempTiles)
			{
				if (idx == 0)
					fixY = x.getYPos();
				else
				{
					if (x.getYPos() != fixY)
						isOneLine = false;
					if (!(tileMap[x.getXPos() + 1][x.getYPos()] >= 100
						|| tileMap[x.getXPos() - 1][x.getYPos()] >= 100))
						isOneLine = false;
				}
				idx++;
			}
		}
		
		for (Tile x : tempTiles)
		{
			int tX = x.getXPos(), tY = x.getYPos();
			if (tX + 1 < N && tileMap[tX + 1][tY] > -1 && tileMap[tX + 1][tY] < MAX_TILES)
				isAdj = true;
			else if (tX - 1 >= 0 && tileMap[tX - 1][tY] > -1 && tileMap[tX - 1][tY] < MAX_TILES)
				isAdj = true;
			else if (tY + 1 < N && tileMap[tX][tY + 1] > -1 && tileMap[tX][tY + 1] < MAX_TILES)
				isAdj = true;
			else if (tY - 1 >= 0 && tileMap[tX][tY - 1] > -1 &&tileMap[tX][tY - 1] < MAX_TILES)
				isAdj = true;
		}
		return isOneLine && isAdj;
	}
	
	public void reset()
	{
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				tileMap[i][j] = -1;
		tempTileCount = 0;
		tileCount = 0;
		turn = TURN_1P;
		impCheck = false;
		tiles.clear();
		tempTiles.clear();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				img[i][j].setIcon(icon0_0);
		tileLabel.setText("Tileleft: 16");
		turnLabel.setText("1P Turn");
		addStraightTile(N / 2, N / 2 - 1, StraightTile.STRAIGHT_HORIZONTAL);
		addStraightTile(N / 2, N / 2, StraightTile.STRAIGHT_HORIZONTAL);
		focusX = N / 2; focusY = N / 2 + 1;
		focusTile = 1;
		img[focusX][focusY].setIcon(icon1_2);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
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
