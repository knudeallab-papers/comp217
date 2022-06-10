package Omok;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainMenu;
import Main.User;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Omok extends JFrame implements MouseListener, WindowListener{

	public static final int WIDTH = 600;
	public static final int HEIGHT = 650;
	public static final int N = 19;
	public static final int TURN_1P = 1;
	public static final int TURN_2P = 2;
	private User p1, p2;
	
	private JLabel turnLabel;
	private JLabel[][] img;
	private int[][] tileMap;
	private int turn;
	private ImageIcon[] icons;
	private Image im;
	private ImageIcon icon;
	
	public Omok(User pl1, User pl2)
	{
		addWindowListener(this);
		//-----Set Default------//
		p1 = pl1;
		p2 = pl2;
		tileMap = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				tileMap[i][j] = 0;
		
		turn = TURN_1P;
		
		icons = new ImageIcon[3];
		
		for (int i = 0; i < 3; i++)
		{
			icon = new ImageIcon(Omok.class.getResource("/omokTile/0_" + i + ".png"));
			im = icon.getImage().getScaledInstance(30,  30,  Image.SCALE_DEFAULT);
			icons[i] = new ImageIcon(im);
		}
		
		setTitle("Omok");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setResizable(false);
		
		//-----Labels Panel-----//
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());
		turnLabel = new JLabel("1P Turn");
		labelPanel.add(turnLabel);
		add(labelPanel);
		
		//-----Game Panel-----//
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(N, N));
		img = new JLabel[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				JLabel tmp = new JLabel(icons[0]);
				int x = i, y = j;
				tmp.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						int judge;
						if (turn == TURN_1P)
						{
							if (tileMap[x][y] == 0)
							{
								tileMap[x][y] = 1;
								tmp.setIcon(icons[1]);
								
								judge = evaluate(turn - 1);
								System.out.println(judge);
								if (judge == TURN_1P)
								{
									User.p1Win(p1, p2);
									reset();
								}
								else
								{
									turn = TURN_2P;
									turnLabel.setText("2P Turn");
								}
							}
						}
						else
						{
							if (tileMap[x][y] == 0)
							{
								tileMap[x][y] = 2;
								tmp.setIcon(icons[2]);
								
								judge = evaluate(turn - 1);
								System.out.println(judge);
								if (judge == TURN_2P)
								{
									User.p2Win(p1, p2);
									reset();
								}
								else
								{
									turn = TURN_1P;
									turnLabel.setText("1P Turn");
								}
							}
						}
					}
				});
				img[i][j] = tmp;
				gamePanel.add(img[i][j]);
			}
		add(gamePanel);
	}
	
	public int evaluate(int turn)
	{
	    int result;
	    int count;
	    result = 0;

	    // 가로줄 체크
	    count = 0;
	    for(int i = 0; i < 19; i++)
	    {
	        for(int j = 0; j < 18; j++)
	        {
	            if((tileMap[i][j] == tileMap[i][j+1]) && (tileMap[i][j] == turn + 1))
	                count ++;
	        }
	        if(count >= 4)
	            result = turn + 1;
	        count = 0;
	    }

	    // 세로줄 체크
	    count = 0;
	    for(int j = 0; j < 19; j++)
	    {
	        for(int i = 0; i < 18; i++)
	        {
	            if((tileMap[i][j] == tileMap[i+1][j]) && (tileMap[i][j] == turn + 1))
	                count ++;
	        }
	        if(count >= 4)
	            result = turn + 1;
	        count = 0;
	    }

	    // 대각선 \ 체크
	    count = 0;
	    for(int i = 18; i >= 0; i--)
	    {
	        for(int j = 0; j < i; j++)
	        {
	            if((tileMap[18-i+j][j] == tileMap[18-i+j+1][j+1]) && (tileMap[18-i+j][j] == turn +1))
	                count ++;
	        }
	        if(count >= 4)
	            result = turn + 1;
	        count = 0;
	    }
	    count = 0;
	    for(int i = 17; i >= 0; i--)
	    {
	        for(int j = 0; j < i; j++)
	        {
	            if((tileMap[j][18-i+j] == tileMap[j+1][18-i+j+1]) && (tileMap[j][18-i+j] == turn +1))
	                count ++;
	        }
	        if(count >= 4)
	            result = turn + 1;
	        count = 0;
	    }

	    // 대각선 / 체크
	    count = 0;
	    for(int i = 18; i >= 0; i--)
	    {
	        for(int j = 0; j < i; j++)
	        {
	            if((tileMap[i-j][j] == tileMap[i-j-1][j+1]) && (tileMap[i-j][j] == turn +1))
	                count ++;
	        }
	        if(count >= 4)
	            result = turn + 1;
	        count = 0;
	    }
	    count = 0;
	    for(int i = 17; i >= 0; i--)
	    {
	        for(int j = 0; j < i; j++)
	        {
	            if((tileMap[18-j][j+18-i] == tileMap[18-j-1][j+18-i+1]) && (tileMap[18-j][j+18-i] == turn +1))
	                count ++;
	        }
	        if(count >= 4)
	            result = turn + 1;
	        count = 0;
	    }
	    
	    return result;
	}

	public void reset()
	{
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				tileMap[i][j] = 0;
				img[i][j].setIcon(icons[0]);
			}
		
		turn = TURN_1P;
		turnLabel.setText("1P Turn");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
