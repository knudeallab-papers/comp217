package cafe;


import javax.swing.JPanel;
import javax.swing.JPasswordField;

import cafe.PasswordBar.inputNum;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class SettingBar extends SmallBar {

	/**
	 * Create the panel.
	 */
	private JButton btn;
	
	private Color Clicked = new Color(180, 200, 255);
	private Color unClicked = new Color(215, 215, 215);
	
	public SettingBar() 
	{
		super();
		setExitBtn("Setting");
		setLayout(null);
		
		setPasswordChangeBtn();
	}

	private void setPasswordChangeBtn()
	{
		btn = new JButton("비밀번호 관리");
		btn.setSize(180, 90);
		btn.setLocation(20, 60);
		btn.setFont(new Font("SanSerif", Font.BOLD, 20));
		btn.setOpaque(true);
		btn.setActionCommand("비밀번호 관리");
		btn.setBackground(unClicked);
		
		add(btn);
		btn.addActionListener(new Change());
	}
	class Change implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			btn.setBackground(unClicked);
			
			
			String actionCommand = e.getActionCommand();
			
			if(actionCommand.equals("비밀번호 관리"))
			{
				btn.setBackground(Clicked);
			}
			
			Actions.addToMainPnl(actionCommand);
		}
	}
}
