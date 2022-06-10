package Eumm;
/*
 * ����ڰ� �α������� ��, � �о��� �������� ��Ī�� ������ �о߸� �����ִ� ������
 * ������ �о߰� ��ư���� �ۿ��Ͽ� �о߸� ���ڷ� �޴� ExpertList��ü�� ȣ����
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Category extends JFrame{
	
	public Category() {
		
	}
	public Category(String id,HashMap <String, Member> infomap) {
		JPanel ment =new JPanel();
		Font f = new Font("���õ����� Regular",Font.PLAIN,24);
		JLabel mentlabel = new JLabel(infomap.get(id).getName()+"��, � �о��� �������� ��Ī�Ͻðڽ��ϱ�?");
		
		ment.setBackground(Color.LIGHT_GRAY);
		mentlabel.setFont(f);
		ment.add(mentlabel);
		
		
		JPanel select = new JPanel(new GridLayout(2,3));
		JButton helbutton = new JButton("�");
		JButton hibutton = new JButton("������");
		JButton langbutton = new JButton("���");
		JButton psybutton = new JButton("�ɸ�");
		JButton insbutton = new JButton("�Ǳ�");
		JButton cookbutton = new JButton("�丮");
		
		helbutton.setBackground(Color.ORANGE);
		hibutton.setBackground(Color.ORANGE);
		langbutton.setBackground(Color.ORANGE);
		psybutton.setBackground(Color.ORANGE);
		insbutton.setBackground(Color.ORANGE);
		cookbutton.setBackground(Color.ORANGE);
		f = new Font("���õ����� Regular",Font.PLAIN,48);
		helbutton.setFont(f);
		hibutton.setFont(f);
		langbutton.setFont(f);
		psybutton.setFont(f);
		insbutton.setFont(f);
		cookbutton.setFont(f);
		
		select.add(helbutton);
		select.add(hibutton);
		select.add(langbutton);
		select.add(psybutton);
		select.add(insbutton);
		select.add(cookbutton);
		
		f = new Font("���õ����� Regular",Font.PLAIN,20);
		JPanel mypage = new JPanel();
		mypage.setBackground(Color.orange);
		JButton mypagebutton = new JButton("����������");
		JButton logout = new JButton("�α׾ƿ�");
		mypagebutton.setFont(f);
		logout.setFont(f);
		mypagebutton.setBackground(Color.LIGHT_GRAY);
		logout.setBackground(Color.LIGHT_GRAY);
		mypage.add(mypagebutton);
		mypage.add(logout);
		
		add(ment,BorderLayout.NORTH);
		add(select,BorderLayout.CENTER);
		add(mypage,BorderLayout.SOUTH);
		
		helbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				new ExpertList("hel",id,infomap);
				dispose();
			}
		});
		
		hibutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ExpertList("hi",id,infomap);
				dispose();
			}
		});
		
		langbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ExpertList("lang",id,infomap);
				dispose();
			}
		});
		
		psybutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ExpertList("psy",id,infomap);
				dispose();
			}
		});
		insbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ExpertList("ins",id,infomap);
				dispose();
			}
		});
		cookbutton.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				new ExpertList("cook",id,infomap);
				dispose();
			}
		});
		
		mypagebutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new MypageCustomer(id,infomap);
				dispose();
			}
		});
		logout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new LogIn();
				dispose();
			}
		});
		
		
		setVisible(true);
		setSize(1000,650);
		setLocationRelativeTo(null); 		//�����츦 ��ǻ�� �߰��� ����
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	
}
