package cafe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import cafe.SettingBar.Change;

public class ProductBar extends SmallBar
{
	private JButton menuBtn;
	private JButton invenBtn;
	
	private Color Clicked = new Color(180, 200, 255);
	private Color unClicked = new Color(215, 215, 215);
	
	public ProductBar() 
	{
		super();
		setExitBtn("Product");
		setMenuBtn();
		setInventoryBtn();
	}
	private void setMenuBtn()
	{
		menuBtn = new JButton("메뉴");
		menuBtn.setSize(180, 90);
		menuBtn.setLocation(20, 60);
		menuBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
		menuBtn.setActionCommand("메뉴");
		menuBtn.setOpaque(true);
		menuBtn.setBackground(unClicked);
		
		add(menuBtn);
		menuBtn.addActionListener(new Create());
	}
	private void setInventoryBtn()
	{
		invenBtn = new JButton("재고");
		invenBtn.setSize(180, 90);
		invenBtn.setLocation(20, 200);
		invenBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
		invenBtn.setActionCommand("재고");
		invenBtn.setOpaque(true);
		invenBtn.setBackground(unClicked);
		
		add(invenBtn);
		invenBtn.addActionListener(new Create());
	}
	class Create implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			menuBtn.setBackground(unClicked);
			invenBtn.setBackground(unClicked);
			
			String actionCommand = e.getActionCommand();
			if(actionCommand.equals("메뉴"))
			{
				menuBtn.setBackground(Clicked);
			}
			else
			{
				invenBtn.setBackground(Clicked);
			}
			
			Actions.addToMainPnl(actionCommand);
		}
	}
	
}
