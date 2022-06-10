package Eumm;
/*
 * 사용자가 로그인했을 떄, 어떤 분야의 전문가와 매칭할 것인지 분야를 보여주는 윈도우
 * 각각의 분야가 버튼으로 작용하여 분야를 인자로 받는 ExpertList객체를 호출함
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
		Font f = new Font("경기천년바탕 Regular",Font.PLAIN,24);
		JLabel mentlabel = new JLabel(infomap.get(id).getName()+"님, 어떤 분야의 전문가와 매칭하시겠습니까?");
		
		ment.setBackground(Color.LIGHT_GRAY);
		mentlabel.setFont(f);
		ment.add(mentlabel);
		
		
		JPanel select = new JPanel(new GridLayout(2,3));
		JButton helbutton = new JButton("운동");
		JButton hibutton = new JButton("고등과외");
		JButton langbutton = new JButton("언어");
		JButton psybutton = new JButton("심리");
		JButton insbutton = new JButton("악기");
		JButton cookbutton = new JButton("요리");
		
		helbutton.setBackground(Color.ORANGE);
		hibutton.setBackground(Color.ORANGE);
		langbutton.setBackground(Color.ORANGE);
		psybutton.setBackground(Color.ORANGE);
		insbutton.setBackground(Color.ORANGE);
		cookbutton.setBackground(Color.ORANGE);
		f = new Font("경기천년바탕 Regular",Font.PLAIN,48);
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
		
		f = new Font("경기천년바탕 Regular",Font.PLAIN,20);
		JPanel mypage = new JPanel();
		mypage.setBackground(Color.orange);
		JButton mypagebutton = new JButton("마이페이지");
		JButton logout = new JButton("로그아웃");
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
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	
}
