package cafe;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;


public class MainWindow extends JFrame 
{
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	
	public Color briGray = new Color(215, 215, 215);
	public static Color midGray = new Color(180, 180, 180);
	
	JPanel pnl1;
	JPanel temp;
	
	
	public MainWindow(Color theColor)
	{
		super("MAIN");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(theColor);
		setResizable(false);
		
		setVisible(true);
	}
	public MainWindow()
	{
		this(Color.LIGHT_GRAY);
	}
	public void setSideBar(SideBar side)
	{
		add(side);
		side.setLocation(930, 0);
	}
	public void setTopBar(TopBar top) 
	{
		add(top);
		top.setLocation(0, 0);
	}
	public void setMainPanel(MainPanel main)
	{
		add(main);
		main.setLocation(0, 150);
	}
}
