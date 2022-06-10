package cafe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JOptionPane;

public class PasswordChange extends JPanel
{

	public Color briGray = new Color(215, 215, 215);
	public static final int WIDTH = 300;
	public static final int HEIGHT = 375;
	
	private JPasswordField curPassword;
	private JPasswordField newPassword;
	private JPasswordField PasswordConfirm;
	
	private Data data;
	
	private String PasswordValue;
	
	private Font LabelFont = new Font("Monospaced", Font.PLAIN, 15);
	private Font textFont = new Font("Monospaced", Font.PLAIN, 20);
	
	public Color midGray = new Color(180, 180, 180);
	
	public PasswordChange()
	{
		
		setSize(WIDTH, HEIGHT);
		setBackground(briGray);
		
		BevelBorder border =new BevelBorder(BevelBorder.RAISED);
		setBorder(border);
		
		setPasswordChangePnl();
		
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
	public void setPasswordChangePnl()
	{
		JLabel textLabel1 = new JLabel();
		JLabel textLabel2 = new JLabel();
		JLabel textLabel3 = new JLabel();
		
		textLabel1.setText("현재 비밀번호");
		textLabel1.setFont(LabelFont);
		textLabel2.setText(" 새 비밀번호");
		textLabel2.setFont(LabelFont);
		textLabel3.setText("비밀번호 확인");
		textLabel3.setFont(LabelFont);
//		
		curPassword = new JPasswordField(30);
		curPassword.setSize(150, 50);
		curPassword.setFont(textFont);		
		textLabel1.add(curPassword);
		curPassword.setLocation(100, 0);	
		curPassword.setEchoChar('*');
		textLabel1.setSize(300, 50);
		add(textLabel1);
		textLabel1.setLocation(25, 25);
		
		newPassword = new JPasswordField(30);
		newPassword.setSize(150, 50);
		newPassword.setFont(textFont);		
		textLabel2.add(newPassword);
		newPassword.setLocation(100, 0);	
		newPassword.setEchoChar('*');
		textLabel2.setSize(300, 50);
		add(textLabel2);
		textLabel2.setLocation(25, 100);
		
		PasswordConfirm = new JPasswordField(30);
		PasswordConfirm.setSize(150, 50);
		PasswordConfirm.setFont(textFont);		
		textLabel3.add(PasswordConfirm);
		PasswordConfirm.setLocation(100, 0);	
		PasswordConfirm.setEchoChar('*');
		textLabel3.setSize(300, 50);
		add(textLabel3);
		textLabel3.setLocation(25, 175);
		
		JButton ConfirmBtn = new JButton("확 인");
		
		ConfirmBtn.setSize(150, 75);
		ConfirmBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
		ConfirmBtn.setBackground(midGray);
		ConfirmBtn.setOpaque(true);
		ConfirmBtn.setBorderPainted(true);
		ConfirmBtn.setLocation(75, 275);
		ConfirmBtn.addActionListener(new Change());
		
		add(ConfirmBtn);
	}
	class Change implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			String cur = curPassword.getText();
			String new1 = newPassword.getText();
			String new2 = PasswordConfirm.getText();
			
			if(PasswordValue.equals(cur))
			{
				if(new1.equals(new2))
				{
					Actions.removeAtMainPnl("PasswordChange");
					data.setPasswordValue(new1);
					try
					{
						ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Data.dat"));
						outputStream.writeObject(data);
						outputStream.close();
					}
					catch(IOException e1)
					{
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
			}
		}
	}
}
