package Eumm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JFrame{

	HashMap <String, Member> infomap = new HashMap<String, Member>();  
	public LogIn() {
		//getContentPane().setBackground(Color.orange);
		JPanel name = new JPanel();
		ImageIcon nameicon = new ImageIcon("logicon.gif");
		JLabel namelabel = new JLabel();
		namelabel.setIcon(nameicon);
		name.add(namelabel);
		
		Font f = new Font("���õ����� Regular",Font.PLAIN,20);
		JPanel logpanel = new JPanel(new GridLayout(3,1));
		JPanel idpanel = new JPanel();
		JPanel pwpanel = new JPanel();
		JLabel idlabel = new JLabel("ID : ");
		JLabel pwlabel = new JLabel("PW : ");
		JTextField id = new JTextField(10);
		JPasswordField pw = new JPasswordField(10);
		idlabel.setFont(f);
		pwlabel.setFont(f);
		id.setFont(f);
		pw.setFont(f);
		
		JPanel joinpanel = new JPanel(new GridLayout(1,3));
		JButton find = new JButton("ID/PW ã��");
		JButton join = new JButton("ȸ������");
		JButton loginbutton = new JButton("�α���");
		find.setFont(f);
		join.setFont(f);
		loginbutton.setFont(f);
		find.setBackground(Color.LIGHT_GRAY);
		join.setBackground(Color.LIGHT_GRAY);
		loginbutton.setBackground(Color.LIGHT_GRAY);
		
		idpanel.add(idlabel);
		idpanel.add(id);
		pwpanel.add(pwlabel);
		pwpanel.add(pw);
		
		joinpanel.add(find);
		joinpanel.add(join);
		joinpanel.add(loginbutton);
		
		logpanel.add(idpanel);
		logpanel.add(pwpanel);
		logpanel.add(joinpanel);
		
		
		
		//------------------------------���� �ҷ�����------------------------
		
	      ObjectInputStream st=null;
	      try {
				st = new ObjectInputStream(new FileInputStream("personinfo.dat"));
				try {
					while(true)
					{
						Member inputmember = new Member();
						inputmember=(Member)st.readObject();
						infomap.put(inputmember.getID(), inputmember);
						
					}
				}catch(EOFException ee)
				{
					
				}
				st.close();
			}catch(IOException | ClassNotFoundException e)
			{
				
			}
		//���Ͽ��� ȸ������ ���ͼ� map�� ����
		
		
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(infomap.containsKey(id.getText()))		//ã����
				{
					if(infomap.get(id.getText()).getPW().compareTo(pw.getText())==0)
					{
					
						dispose();
						if(infomap.get(id.getText()).getOr().equals("c"))
						{
							new Category(id.getText(),infomap);
						}
						else
						{
							new MypageExpert(id.getText(),infomap);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� Ʋ���ϴ�.");
						id.setText("");
						pw.setText("");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� Ʋ���ϴ�.");
					id.setText("");
					pw.setText("");
				}
			}
		});
		
		find.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				new Finding(infomap);
			}
		});
		
		join.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ExpertOrCustomer();
				dispose();
			}
		});
		
		
		add(name,BorderLayout.CENTER);
		add(logpanel,BorderLayout.SOUTH);
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//�����츦 ��ǻ�� �߰��� ����
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	

}
