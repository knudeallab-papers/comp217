package cafe;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class PasswordBar extends SmallBar 
{
	public static final int NUM_OF_CHAR = 30;
	private JPasswordField password;
	public Color darkGray = new Color(100, 100, 100);
	
	private Data data;
	
	private String PasswordValue;
	
	public PasswordBar()
	{
		super();
		setExitBtn("Exit");
		setLock();
		setNumPad("Password");
		setNumBtnAct();
//		setCupLab();
		
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("Data.dat"));
			
			data = (Data) inputStream.readObject();
			
			
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		PasswordValue = data.getPasswordValue();
	}
	private void setLock()
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
		String LockFileName = "Lock.png";
		String LockFilePath = currentPath + "/" + srcPath + "/" + packageName + "/" + LockFileName;
		ImageIcon LockIcon = new ImageIcon(LockFilePath);
		JMenuItem LockItem = new JMenuItem(LockIcon);
		LockItem.setBackground(briGray);
		LockItem.setSize(55, 55);
		add(LockItem);
		LockItem.setLocation(0, 250);
		
		password = new JPasswordField(NUM_OF_CHAR);
		password.setSize(145, 50);
		password.setBackground(Color.WHITE);
		password.setFont(new Font("Monospaced", Font.PLAIN, 20));
		password.setEchoChar('*');
		
		add(password);
		password.setLocation(60, 250);
	}
	private void setNumBtnAct()
	{
		for(int c1 = 0; c1 < 13; c1++)
		{
			NBtn[c1].addActionListener(new inputNum());
		}
	}
	class inputNum implements ActionListener
	{
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();
			String cur;
			
			if(actionCommand.equals("7"))
			{
				cur = password.getText();
				cur = cur + "7";
				password.setText(cur);
			}
			else if(actionCommand.equals("8"))
			{
				cur = password.getText();
				cur = cur + "8";
				password.setText(cur);
			}
			else if(actionCommand.equals("9"))
			{
				cur = password.getText();
				cur = cur + "9";
				password.setText(cur);
			}
			else if(actionCommand.equals("4"))
			{
				cur = password.getText();
				cur = cur + "4";
				password.setText(cur);
			}
			else if(actionCommand.equals("5"))
			{
				cur = password.getText();
				cur = cur + "5";
				password.setText(cur);
			}
			else if(actionCommand.equals("6"))
			{
				cur = password.getText();
				cur = cur + "6";
				password.setText(cur);
			}
			else if(actionCommand.equals("1"))
			{
				cur = password.getText();
				cur = cur + "1";
				password.setText(cur);
			}
			else if(actionCommand.equals("2"))
			{
				cur = password.getText();
				cur = cur + "2";
				password.setText(cur);
			}
			else if(actionCommand.equals("3"))
			{
				cur = password.getText();
				cur = cur + "3";
				password.setText(cur);
			}
			else if(actionCommand.equals("0"))
			{
				cur = password.getText();
				cur = cur + "0";
				password.setText(cur);
			}
			else if(actionCommand.equals("<"))
			{
				cur = password.getText();
				if(cur.length() > 0)
				{
					cur= cur.substring(0, cur.length() -1);
					password.setText(cur);
				}
			}
			else if(actionCommand.equals("C"))
			{
				cur = "";
				password.setText(cur);
			}
			else if(actionCommand.equals("LOGIN"))
			{
				cur = password.getText();
				if(cur.equals(PasswordValue))
				{
					Actions.removeSideBar("LOGIN");
				}
				else if(cur.equals(""))
				{
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
					cur = "";
					password.setText(cur);
				}
			}
			else
			{
				cur = password.getText();
				cur = cur + "*";
				password.setText(cur);
			}
		}
	}
}
