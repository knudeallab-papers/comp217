package Eumm;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ShowPW extends JFrame{

	public ShowPW()
	{
		
	}
	public ShowPW(HashMap <String, Member> infomap,String pw) {
		// TODO Auto-generated constructor stub
		
		setLayout(new GridLayout(2,1));
		JPanel show = new JPanel();
		JTextField showpw = new JTextField();
		showpw.setEditable(false);
		showpw.setText("PW : "+pw);
		show.add(showpw);
		
		JButton join = new JButton("Ȯ��");
		
		
		
		join.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				//new LogIn();
			}
		});
		
		
		
		add(show);
		add(join);
		
		setVisible(true);
		setSize(200,100);
		setLocationRelativeTo(null); 		//�����츦 ��ǻ�� �߰��� ����
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
