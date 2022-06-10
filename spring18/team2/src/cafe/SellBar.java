package cafe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import cafe.ProductBar.Create;

public class SellBar extends SmallBar 
{
	
	private JButton hallBtn;
	private JButton packingBtn;
	private JButton deliverBtn;
	
	private Color Clicked = new Color(180, 200, 255);
	private Color unClicked = new Color(215, 215, 215);
	
	public SellBar()
	{
		super();
		setExitBtn("Sell");
		setHallBtn();
		setPackingBtn();
//		setDeliverBtn();
	}
	private void setHallBtn()
	{
		hallBtn = new JButton("홀");
		hallBtn.setSize(180, 90);
		hallBtn.setLocation(20, 60);
		hallBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
		hallBtn.setActionCommand("홀");
		hallBtn.setOpaque(true);
		hallBtn.setBackground(unClicked);
		
		add(hallBtn);
		hallBtn.addActionListener(new Create());
	}
	private void setPackingBtn()
	{
		packingBtn = new JButton("포장");
		packingBtn.setSize(180, 90);
		packingBtn.setLocation(20, 200);
		packingBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
		packingBtn.setActionCommand("포장");
		packingBtn.setOpaque(true);
		packingBtn.setBackground(unClicked);
		
		add(packingBtn);
		packingBtn.addActionListener(new Create());
	}
	private void setDeliverBtn()
	{
		deliverBtn = new JButton("배달");
		deliverBtn.setSize(180, 90);
		deliverBtn.setLocation(20, 340);
		deliverBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
		deliverBtn.setActionCommand("배달");
		deliverBtn.setOpaque(true);
		deliverBtn.setBackground(unClicked);
		
		add(deliverBtn);
		deliverBtn.addActionListener(new Create());
	}
	class Create implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			hallBtn.setBackground(unClicked);
			packingBtn.setBackground(unClicked);
//			deliverBtn.setBackground(unClicked);
			
			String actionCommand = e.getActionCommand();
			if(actionCommand.equals("홀"))
			{
				hallBtn.setBackground(Clicked);
			}
			else if(actionCommand.equals("포장"))
			{
				packingBtn.setBackground(Clicked);
			}
			else
			{
				deliverBtn.setBackground(Clicked);
			}
			
			Actions.addToMainPnl(actionCommand);
		}
	}
}
