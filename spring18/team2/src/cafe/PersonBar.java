package cafe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class PersonBar extends SmallBar 
{
	private JButton EmpBtn;
	private JButton MemBtn;
	
	private Color Clicked = new Color(180, 200, 255);
	private Color unClicked = new Color(215, 215, 215);
	
	public PersonBar() 
	{
		super();
		setExitBtn("Person");
		setMenuBtn();
	}
	private void setMenuBtn()
	{
		EmpBtn = new JButton("직원 관리");
		EmpBtn.setSize(180, 90);
		EmpBtn.setLocation(20, 60);
		EmpBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
		EmpBtn.setActionCommand("직원 관리");
		EmpBtn.setOpaque(true);
		EmpBtn.setBackground(unClicked);
		
		add(EmpBtn);
		EmpBtn.addActionListener(new Create());
		
		MemBtn = new JButton("회원 관리");
		MemBtn.setSize(180, 90);
		MemBtn.setLocation(20, 200);
		MemBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
		MemBtn.setActionCommand("회원 관리");
		MemBtn.setOpaque(true);
		MemBtn.setBackground(unClicked);
		
		add(MemBtn);
		MemBtn.addActionListener(new Create());
	}
	class Create implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			EmpBtn.setBackground(unClicked);
			MemBtn.setBackground(unClicked);
			
			String actionCommand = e.getActionCommand();
			
			if(actionCommand.equals("직원 관리"))
			{
				EmpBtn.setBackground(Clicked);
			}
			else
			{
				MemBtn.setBackground(Clicked);
			}
			
			Actions.addToMainPnl(actionCommand);
		}
	}
}
