package cafe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import cafe.SellBar.Create;

public class BusinessBar extends SmallBar 
{
	private JButton sale;
	
	private Color Clicked = new Color(180, 200, 255);
	private Color unClicked = new Color(215, 215, 215);
	
	public BusinessBar()
	{
		super();
		setExitBtn("Business");
		setSaleBtn();
//		setDeliverBtn();
	}
	private void setSaleBtn()
	{
		sale = new JButton("상품별 매출");
		sale.setSize(180, 90);
		sale.setLocation(20, 60);
		sale.setFont(new Font("SanSerif", Font.BOLD, 20));
		sale.setOpaque(true);
		sale.setBackground(unClicked);
		
		add(sale);
		sale.addActionListener(new Create());
	}
	class Create implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			sale.setBackground(unClicked);
			
			String actionCommand = e.getActionCommand();
			if(actionCommand.equals("상품별 매출"))
			{
				sale.setBackground(Clicked);
			}
			
			Actions.addToMainPnl(actionCommand);
		}
	}
}
