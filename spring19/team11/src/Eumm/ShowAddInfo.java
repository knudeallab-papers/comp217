package Eumm;
/*
 * ExpertList에서 선택한 전문가에 대한 자세한 정보를 보여줌
 * ->예약하기 : 마음에 들면 예약을 진행 할 수 있음
 * ->뒤로 : 마음에 들지 않으면 다른 전문가를 선택 할 수 있음
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowAddInfo extends JFrame {

	private String addinfoname;
	private String cat;
	public ShowAddInfo()
	{
		
	}
	public ShowAddInfo(String id,HashMap <String, Member> infomap,HashMap <String, Person> info,String selected,String category) {
		setLayout(new GridLayout(4,1));
		
		switch(category)
   	 	{
   	 		case "hel":
   	 			cat = "운동";
   	 			break;
   	 		case "hi":
   	 			cat = "고등과외";
   	 			break;
   	 		case "psy":
   	 			cat = "심리";
   	 			break;
   	 		case "lang":
   	 			cat = "언어";
   	 			break;
   	 		case "ins":
   	 			cat = "악기";
   	 			break;
   	 		case "cook":
   	 			cat = "요리";
   	 			break;
   	 	}
		
		Font f = new Font("경기천년바탕 Regular",Font.PLAIN,24);
		Font b = new Font("경기천년바탕 Regular",Font.PLAIN,14);
		StringTokenizer t = new StringTokenizer(selected,",");
		String name = t.nextToken();
		
		JPanel namepanel = new JPanel();
		namepanel.setBackground(Color.yellow);
		JLabel namelabel = new JLabel(cat+"분야 "+info.get(name).getType()+" 전문가 "+name);
		namelabel.setFont(f);
		namepanel.add(namelabel);
		
		JPanel buttonpanel = new JPanel();
		JButton backbutton = new JButton("뒤로");
		JButton reservation = new JButton("예약하기");
		buttonpanel.add(backbutton);
		buttonpanel.add(reservation);
		
		switch(category)
   	 	{
   	 		case "hel":
   	 			addinfoname = "장소";
   	 			break;
   	 		case "hi":
   	 			addinfoname = "학교 및 전공";
   	 			break;
   	 		case "psy":
   	 			addinfoname = "상담시간(분)";
   	 			break;
   	 		case "lang":
   	 			addinfoname = "자격증 및 점수";
   	 			break;
   	 		case "ins":
   	 			addinfoname = "경력(년)";
   	 			break;
   	 		case "cook":
   	 			addinfoname = "장소";
   	 			break;
   	 	}
		
		JPanel info1 = new JPanel(new GridLayout(2,2));					//이름 나이 성별 별점
		JLabel namelabel2 = new JLabel("이름 : "+name);
		namelabel2.setFont(b);
		JLabel agelabel = new JLabel("나이 : "+ info.get(name).getAge());
		agelabel.setFont(b);
		JLabel sexlabel = new JLabel("성별 : "+ info.get(name).getSex());
		sexlabel.setFont(b);
		JLabel starlabel = new JLabel("별점 : "+ info.get(name).getLike()+"/5");
		starlabel.setFont(b);
		
		info1.add(namelabel2);
		info1.add(agelabel);
		info1.add(sexlabel);
		info1.add(starlabel);
		
		JPanel info2 = new JPanel(new GridLayout(2,2));					//전화번호 이메일 가격  addinfo
		JLabel phonenumlabel = new JLabel("전화번호 : "+ info.get(name).getTelnum());
		phonenumlabel.setFont(b);
		JLabel emaillabel = new JLabel("이메일 : "+ info.get(name).getEmail());
		emaillabel.setFont(b);
		JLabel pricelabel = new JLabel("가격(회당): "+ info.get(name).getPrice()+"(만원)");
		pricelabel.setFont(b);
		JLabel addinfolabel = new JLabel(addinfoname+" : "+ info.get(name).getAddinfo());
		addinfolabel.setFont(b);
		
		info2.add(phonenumlabel);
		info2.add(emaillabel);
		info2.add(pricelabel);
		info2.add(addinfolabel);
		
		add(namepanel);
		add(info1);				
		add(info2);				
		add(buttonpanel);
		
		
		backbutton.addActionListener(new ActionListener() 
	      {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 dispose();
	         }
	      });
		
		reservation.addActionListener(new ActionListener() 
	      {
		     public void actionPerformed(ActionEvent e)
		     {
		    	 new ReservationC(id, infomap,info.get(name).getTelnum());
		     }
		  });
		
		
		setVisible(true);
		setSize(400,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
