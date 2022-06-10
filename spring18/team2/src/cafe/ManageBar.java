package cafe;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cafe.SmallBar.ExitAct;

import javax.swing.JPasswordField;
import javax.swing.JMenuItem;



import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;



public class ManageBar extends SmallBar 
{
	
	
	public ManageBar()
	{
		super();
		
		setManageBtn();
		setExitBtn("Exit");
		
	}
	private void setManageBtn()
	{
		String currentPath = "";
		try
		{
			currentPath = new File(".").getCanonicalPath();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		setBtnIcon(currentPath, "Sell.png", "판매관리", 15, 270);
		setBtnIcon(currentPath, "Product.png", "상품관리", 115, 270);
		setBtnIcon(currentPath, "Calculate.png", "영업관리", 15, 370);
		setBtnIcon(currentPath, "People.png", "회원/직원", 115, 370);
		setBtnIcon(currentPath, "Setting.png", "설정", 15, 470);
		setBtnIcon(currentPath, "Off.png", "시스템종료", 115, 470);
	}
	private void setBtnIcon(String currentPath, String FileName, String Name, int x, int y)
	{
		String FilePath = currentPath + "/" + srcPath + "/" + packageName + "/" + FileName;
		
		ImageIcon Icon = new ImageIcon(FilePath);
		JButton Btn = new JButton(Name, Icon);
		Btn.setSize(90, 90);
		Btn.setVerticalTextPosition(JButton.BOTTOM);
		Btn.setHorizontalTextPosition(JButton.CENTER);
		Btn.setFont(new Font("SanSerif", Font.BOLD, 15));
		Btn.setBackground(new Color(200, 200, 200));
		Btn.setOpaque(true);
//		Btn.setBorderPainted(false);
		Btn.addActionListener(new ManageAct());
		Btn.setActionCommand(Name);
		
		add(Btn);
		Btn.setLocation(x, y);
	}
	class ManageAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String ActionCommand = e.getActionCommand();
			
			Actions.addSideBar(ActionCommand);
		}
		
	}
	
}
