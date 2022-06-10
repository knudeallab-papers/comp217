package cafe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Board extends JPanel
{
	private static final int WIDTH = 450;
	private static final int HEIGHT = 460;
	private Font BtnFont = new Font("SanSerif", Font.PLAIN, 15);
	protected static final int BtnNum = 15;
	protected JButton menuBtn[] = new JButton[BtnNum];
	
	protected Color unClicked = new Color(240, 240, 240);
	protected Color Clicked = new Color(180, 200, 255);
	
	protected MenuDataSet data;
	protected InvenDataSet data2;
	
	public Board()
	{
		super();
		setSize(WIDTH, HEIGHT);
		setBackground(Color.WHITE);
		
		data = new MenuDataSet();
		
		setBtn();
			
	}
	public void setBtn()
	{
		for(int c1 = 0, locX = 10, locY = -80; c1 < BtnNum; c1++)
		{
			if(c1 % 3 == 0)
			{
				locY += 90;
				locX = 10;
			}
			else
			{
				locX += 145;
			}
			
			menuBtn[c1] = new JButton();
			menuBtn[c1].setSize(140, 80);
			add(menuBtn[c1]);
			menuBtn[c1].setLocation(locX, locY);
			menuBtn[c1].setActionCommand(Integer.toString(c1));
			menuBtn[c1].setOpaque(true);
//			menuBtn[c1].setBackground(Color.WHITE);
//			menuBtn[c1].setFont(BtnFont);
		}
	}
}