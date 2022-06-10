package Eumm;
/*
 * 분야별로 전문가 목록을 보여주는 윈도우
 * 앞의 Category클래스에서 분야를 인자로받아
 * 분야에 맞는 파일을 불러와 map에 저장후 보여주기
 * ->자세히보기 버튼 : 전문가에 대한 자세한 정보를 보여주는 윈도우를 부름 -> ShowAddInfo
 * ->뒤로 : 다시 Category를 불러 분야를 선택 할 수 있는 윈도우를 부름
 */
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
public class ExpertList extends JFrame{
	
	HashMap <String, Person> helinfomap = new HashMap<String, Person>();
	HashMap <String, Person> hiinfomap = new HashMap<String, Person>();
	HashMap <String, Person> psyinfomap = new HashMap<String, Person>();
	HashMap <String, Person> langinfomap = new HashMap<String, Person>();
	HashMap <String, Person> insinfomap = new HashMap<String, Person>();
	HashMap <String, Person> cookinfomap = new HashMap<String, Person>();
	
	public ExpertList()
	{
		
	}
	private Choice c;
	
	public ExpertList(String category,String id,HashMap <String, Member> infomap) {
		setLayout(new GridLayout(2,1));
		setBackground(Color.LIGHT_GRAY);
		Font f = new Font("경기천년바탕 Regular",Font.PLAIN,24);
		
		JPanel intropanel = new JPanel(new GridLayout(2,1));
		JTextArea intro = new JTextArea();
		
		intro.setFont(f);
		intro.setBackground(Color.yellow);
		intropanel.add(intro,BorderLayout.CENTER);
		intro.setEditable(false);
		
		JPanel buttonpanel = new JPanel();
		JButton backbutton = new JButton("뒤로");
		JButton addinfo = new JButton("자세히보기");
		buttonpanel.add(backbutton);
		buttonpanel.add(addinfo);
		
		switch(category)
		{
		case "hel":
			intro.setText("운동분야의 전문가들을 소개합니다.\n "
					+ "자세히보기를 클릭하시면 전문가의 상세정보를 알 수 있습니다.");
			Scanner helpersoninfo = null;
			try
			{
				helpersoninfo = new Scanner(new FileInputStream("health.txt"));
			}catch(FileNotFoundException e) {
				System.out.println("File not found. ");
				System.exit(0);
			}
			
			
			String name;
			while(helpersoninfo.hasNext())
			{	
				name = helpersoninfo.next();
				helinfomap.put(name,new Person(name,helpersoninfo.nextInt(),helpersoninfo.next(),helpersoninfo.nextDouble(),
						helpersoninfo.next(),helpersoninfo.next(),helpersoninfo.nextDouble(),helpersoninfo.next(),helpersoninfo.next()));
			}
			
			c = new Choice();
			c.add("전문가 목록");
			for(String s : helinfomap.keySet())
			{
				
				c.add(helinfomap.get(s).getName()+", "+helinfomap.get(s).getAge()+"세, "+helinfomap.get(s).getSex()+", "+helinfomap.get(s).getType());
			}

			
			break;
			
		case "hi":
			intro.setText("고등과외분야의 전문가들을 소개합니다.\n"
					+ "자세히보기를 클릭하시면 전문가의 상세정보를 알 수 있습니다.");
			Scanner hipersoninfo = null;
			try
			{
				hipersoninfo = new Scanner(new FileInputStream("hi.txt"));
			}catch(FileNotFoundException e) {
				System.out.println("File not found. ");
				System.exit(0);
			}
			
			
		
			while(hipersoninfo.hasNext())
			{	
				name = hipersoninfo.next();
				hiinfomap.put(name,new Person(name,hipersoninfo.nextInt(),hipersoninfo.next(),hipersoninfo.nextDouble(),
						hipersoninfo.next(),hipersoninfo.next(),hipersoninfo.nextDouble(),hipersoninfo.next(),hipersoninfo.next()));
			}
			c = new Choice();
			c.add("전문가 목록");
			for(String s : hiinfomap.keySet())
			{
				c.add(hiinfomap.get(s).getName()+", "+hiinfomap.get(s).getAge()+"세, "+hiinfomap.get(s).getSex()+", "+hiinfomap.get(s).getType());
			}
			break;
			
		case "psy":
			intro.setText("심리분야의 전문가들을 소개합니다."
					+ "\n자세히보기를 클릭하시면 전문가의 상세정보를 알 수 있습니다.");
			Scanner psypersoninfo = null;
			try
			{
				psypersoninfo = new Scanner(new FileInputStream("psy.txt"));
			}catch(FileNotFoundException e) {
				System.out.println("File not found. ");
				System.exit(0);
			}
			
		
		
			while(psypersoninfo.hasNext())
			{	
				name = psypersoninfo.next();
				psyinfomap.put(name,new Person(name,psypersoninfo.nextInt(),psypersoninfo.next(),psypersoninfo.nextDouble(),
						psypersoninfo.next(),psypersoninfo.next(),psypersoninfo.nextDouble(),psypersoninfo.next(),psypersoninfo.next()));
			}
			c = new Choice();
			c.add("전문가 목록");
			for(String s : psyinfomap.keySet())
			{
				
				c.add(psyinfomap.get(s).getName()+", "+psyinfomap.get(s).getAge()+"세, "+psyinfomap.get(s).getSex()+", "+psyinfomap.get(s).getType());
			}
			
			break;
		case "lang":
			intro.setText("언어분야의 전문가들을 소개합니다."
					+ "\n자세히보기를 클릭하시면 전문가의 상세정보를 알 수 있습니다.");
			Scanner langpersoninfo = null;
			try
			{
				langpersoninfo = new Scanner(new FileInputStream("lang.txt"));
			}catch(FileNotFoundException e) {
				System.out.println("File not found. ");
				System.exit(0);
			}
			
			
		
			while(langpersoninfo.hasNext())
			{	
				name = langpersoninfo.next();
				langinfomap.put(name,new Person(name,langpersoninfo.nextInt(),langpersoninfo.next(),langpersoninfo.nextDouble(),
						langpersoninfo.next(),langpersoninfo.next(),langpersoninfo.nextDouble(),langpersoninfo.next(),langpersoninfo.next()));
			}
			c = new Choice();
			c.add("전문가 목록");
			for(String s : langinfomap.keySet())
			{
				
				c.add(langinfomap.get(s).getName()+", "+langinfomap.get(s).getAge()+"세, "+langinfomap.get(s).getSex()+", "+langinfomap.get(s).getType());
			}
			break;
		case "ins":
			intro.setText("악기분야의 전문가들을 소개합니다."
					+ "\n자세히보기를 클릭하시면 전문가의 상세정보를 알 수 있습니다.");
			Scanner inspersoninfo = null;
			try
			{
				inspersoninfo = new Scanner(new FileInputStream("ins.txt"));
			}catch(FileNotFoundException e) {
				System.out.println("File not found. ");
				System.exit(0);
			}
			
		
			while(inspersoninfo.hasNext())
			{	
				name = inspersoninfo.next();
				insinfomap.put(name,new Person(name,inspersoninfo.nextInt(),inspersoninfo.next(),inspersoninfo.nextDouble(),
					inspersoninfo.next(),inspersoninfo.next(),inspersoninfo.nextDouble(),inspersoninfo.next(),inspersoninfo.next()));
			}
			c = new Choice();
			c.add("전문가 목록");
			for(String s : insinfomap.keySet())
			{
				c.add(insinfomap.get(s).getName()+", "+insinfomap.get(s).getAge()+"세, "+insinfomap.get(s).getSex()+", "+insinfomap.get(s).getType());
			}
			break;
		case "cook":
			intro.setText("요리분야의 전문가들을 소개합니다."
					+ "\n자세히보기를 클릭하시면 전문가의 상세정보를 알 수 있습니다.");
			Scanner cookpersoninfo = null;
			try
			{
				cookpersoninfo = new Scanner(new FileInputStream("cook.txt"));
			}catch(FileNotFoundException e) {
				System.out.println("File not found. ");
				System.exit(0);
			}
			
			
		
			while(cookpersoninfo.hasNext())
			{	
				name = cookpersoninfo.next();
				cookinfomap.put(name,new Person(name,cookpersoninfo.nextInt(),cookpersoninfo.next(),cookpersoninfo.nextDouble(),
						cookpersoninfo.next(),cookpersoninfo.next(),cookpersoninfo.nextDouble(),cookpersoninfo.next(),cookpersoninfo.next()));
			}
			
			c = new Choice();
			c.add("전문가 목록");
			for(String s : cookinfomap.keySet())
			{
				c.add(cookinfomap.get(s).getName()+", "+cookinfomap.get(s).getAge()+"세, "+cookinfomap.get(s).getSex()+", "+cookinfomap.get(s).getType());
			}
			break;
		}
		
		intropanel.add(c);
		
		add(intropanel);
		add(buttonpanel);
		
		

		backbutton.addActionListener(new ActionListener() 
	      {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 dispose();
	        	 new Category(id,infomap);
	         }
	      });
		
		
		addinfo.addActionListener(new ActionListener() 
	      {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 switch(category)
	        	 {
	        	 case "hel":
	        		 new ShowAddInfo(id,infomap,helinfomap,c.getSelectedItem(),category);
	        		 break;
	        	 case "hi":
	        		 new ShowAddInfo(id,infomap,hiinfomap,c.getSelectedItem(),category);
	        		 break;
	        	 case "psy":
	        		 new ShowAddInfo(id,infomap,psyinfomap,c.getSelectedItem(),category);
	        		 break;
	        	 case "lang":
	        		 new ShowAddInfo(id,infomap,langinfomap,c.getSelectedItem(),category);
	        		 break;
	        	 case "ins":
	        		 new ShowAddInfo(id,infomap,insinfomap,c.getSelectedItem(),category);
	        		 break;
	        	 case "cook":
	        		 new ShowAddInfo(id,infomap,cookinfomap,c.getSelectedItem(),category);
	        		 break;
	        	 }
	         }
	      });
		
		
		setVisible(true);
		setSize(700,650);
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
