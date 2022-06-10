package Eumm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public abstract class Mypage extends JFrame implements ActionListener{
	private String type;
	public Mypage()
	{
		
	}
	public Mypage(String id,HashMap<String, Member> infomap) {
		
		
		Font f = new Font("경기천년바탕 Regular",Font.PLAIN,24);
		Font b = new Font("경기천년바탕 Regular",Font.PLAIN,34);
		setLayout(new GridLayout(2,1));
		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel mypage = new JPanel();
		mypage.setBackground(Color.LIGHT_GRAY);
		JButton backbutton = new JButton("뒤로");
		
		if(infomap.get(id).getOr().equals("c"))
		{
			type = "사용자 ";
		}
		else
		{
			type = "전문가 ";
		}
		JLabel mypagelabel = new JLabel(type+infomap.get(id).getName()+"님의 페이지");
		mypagelabel.setHorizontalAlignment(JLabel.CENTER);
		
		backbutton.addActionListener(this);
		mypage.add(backbutton);
		mypage.add(mypagelabel,BorderLayout.CENTER);
		mypagelabel.setFont(b);
		
		JPanel infopanel = new JPanel(new GridLayout(1,2));	//개인정보를 나타내주는 패널
		infopanel.setBackground(Color.pink);
		ImageIcon user = new ImageIcon("user.gif");
		JLabel userpic = new JLabel();
		userpic.setIcon(user);
		infopanel.add(userpic);
		
		
		JPanel info2panel = new JPanel(new GridLayout(3,1));
		info2panel.setBackground(Color.pink);
		JLabel namelabel = new JLabel("이름 : "+infomap.get(id).getName());
		
		JLabel idlabel = new JLabel("ID : "+id);
		
		JLabel telnumlabel = new JLabel("전화번호 : "+infomap.get(id).getTelnum());
		
		panel1.add(mypage);
		panel1.add(infopanel);
		
		namelabel.setFont(f);
		idlabel.setFont(f);
		telnumlabel.setFont(f);
		
		info2panel.add(idlabel);
		info2panel.add(namelabel);
		info2panel.add(telnumlabel);
		
		infopanel.add(info2panel,BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel(new GridLayout(1,1));
		JButton reservationbutton = new JButton("나의 예약정보");
		
		reservationbutton.setBackground(Color.LIGHT_GRAY);
		reservationbutton.setFont(b);
		reservationbutton.addActionListener(this);
		
		panel2.add(reservationbutton);
		add(panel1);
		add(panel2);
		
		setVisible(true);
	    setSize(500,750);
	    setLocationRelativeTo(null);       //윈도우를 컴퓨터 중간에 띄우기
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public abstract void addActionPerformed(ActionEvent e);

}
