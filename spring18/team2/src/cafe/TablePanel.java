package cafe;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TablePanel extends JPanel 
{
	private static final int WIDTH = 450;
	private static final int HEIGHT = 230;
	private static final int TableNum = 6;
	
	private JButton table[] = new JButton[TableNum];
	
	private Color Clicked = new Color(180, 200, 255);
	private Color unClicked = new Color(240, 240, 240);
	private Color full = new Color(255, 0, 0);
	
	private int num;
	
	public TablePanel()
	{
		super();
		setSize(WIDTH, HEIGHT);
		num = 0;
		
		setBtn();
	}
	public void setBtn()
	{
		for(int c1 = 0, locX = 10, locY = -100; c1 < TableNum ; c1++)
		{
			if(c1 % 3 == 0)
			{
				locY += 110;
				locX = 10;
			}
			else
			{
				locX += 145;
			}
			
			table[c1] = new JButton("테이블" + (c1 + 1)); // 보류 
			table[c1].setSize(140, 100);
			add(table[c1]);
			table[c1].setLocation(locX, locY);
			table[c1].setActionCommand(Integer.toString(c1));
			table[c1].setOpaque(true);
			table[c1].addActionListener(new tableAction());
//			table[c1].setBackground(unClicked); //
			setBtnColor(c1);
//			menuBtn[c1].setFont(BtnFont);
		}
	}
	public void setBtnColor(int n)
	{
		if(Actions.getisEmpty(n) == true)
		{
			table[n].setBackground(full);
		}
		else
		{
			table[n].setBackground(unClicked);
		}
	}
	class tableAction implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
//			setBtnColor(num);
			for(int c1 = 0; c1 < TableNum; c1++)
			{
				setBtnColor(c1);
			}
			String actionCommand = e.getActionCommand();
			num = Integer.parseInt(actionCommand);
			
			table[num].setBackground(Clicked);
			
			Actions.addToSellDetailPnl(Integer.parseInt(actionCommand));		
		}
		
	}
	
}
