package Eumm;
/*
 * �оߺ��� ������ ����� �����ִ� ������
 * ���� CategoryŬ�������� �о߸� ���ڷι޾�
 * �о߿� �´� ������ �ҷ��� map�� ������ �����ֱ�
 * ->�ڼ������� ��ư : �������� ���� �ڼ��� ������ �����ִ� �����츦 �θ� -> ShowAddInfo
 * ->�ڷ� : �ٽ� Category�� �ҷ� �о߸� ���� �� �� �ִ� �����츦 �θ�
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
		Font f = new Font("���õ����� Regular",Font.PLAIN,24);
		
		JPanel intropanel = new JPanel(new GridLayout(2,1));
		JTextArea intro = new JTextArea();
		
		intro.setFont(f);
		intro.setBackground(Color.yellow);
		intropanel.add(intro,BorderLayout.CENTER);
		intro.setEditable(false);
		
		JPanel buttonpanel = new JPanel();
		JButton backbutton = new JButton("�ڷ�");
		JButton addinfo = new JButton("�ڼ�������");
		buttonpanel.add(backbutton);
		buttonpanel.add(addinfo);
		
		switch(category)
		{
		case "hel":
			intro.setText("��о��� ���������� �Ұ��մϴ�.\n "
					+ "�ڼ������⸦ Ŭ���Ͻø� �������� �������� �� �� �ֽ��ϴ�.");
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
			c.add("������ ���");
			for(String s : helinfomap.keySet())
			{
				
				c.add(helinfomap.get(s).getName()+", "+helinfomap.get(s).getAge()+"��, "+helinfomap.get(s).getSex()+", "+helinfomap.get(s).getType());
			}

			
			break;
			
		case "hi":
			intro.setText("�����ܺо��� ���������� �Ұ��մϴ�.\n"
					+ "�ڼ������⸦ Ŭ���Ͻø� �������� �������� �� �� �ֽ��ϴ�.");
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
			c.add("������ ���");
			for(String s : hiinfomap.keySet())
			{
				c.add(hiinfomap.get(s).getName()+", "+hiinfomap.get(s).getAge()+"��, "+hiinfomap.get(s).getSex()+", "+hiinfomap.get(s).getType());
			}
			break;
			
		case "psy":
			intro.setText("�ɸ��о��� ���������� �Ұ��մϴ�."
					+ "\n�ڼ������⸦ Ŭ���Ͻø� �������� �������� �� �� �ֽ��ϴ�.");
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
			c.add("������ ���");
			for(String s : psyinfomap.keySet())
			{
				
				c.add(psyinfomap.get(s).getName()+", "+psyinfomap.get(s).getAge()+"��, "+psyinfomap.get(s).getSex()+", "+psyinfomap.get(s).getType());
			}
			
			break;
		case "lang":
			intro.setText("���о��� ���������� �Ұ��մϴ�."
					+ "\n�ڼ������⸦ Ŭ���Ͻø� �������� �������� �� �� �ֽ��ϴ�.");
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
			c.add("������ ���");
			for(String s : langinfomap.keySet())
			{
				
				c.add(langinfomap.get(s).getName()+", "+langinfomap.get(s).getAge()+"��, "+langinfomap.get(s).getSex()+", "+langinfomap.get(s).getType());
			}
			break;
		case "ins":
			intro.setText("�Ǳ�о��� ���������� �Ұ��մϴ�."
					+ "\n�ڼ������⸦ Ŭ���Ͻø� �������� �������� �� �� �ֽ��ϴ�.");
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
			c.add("������ ���");
			for(String s : insinfomap.keySet())
			{
				c.add(insinfomap.get(s).getName()+", "+insinfomap.get(s).getAge()+"��, "+insinfomap.get(s).getSex()+", "+insinfomap.get(s).getType());
			}
			break;
		case "cook":
			intro.setText("�丮�о��� ���������� �Ұ��մϴ�."
					+ "\n�ڼ������⸦ Ŭ���Ͻø� �������� �������� �� �� �ֽ��ϴ�.");
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
			c.add("������ ���");
			for(String s : cookinfomap.keySet())
			{
				c.add(cookinfomap.get(s).getName()+", "+cookinfomap.get(s).getAge()+"��, "+cookinfomap.get(s).getSex()+", "+cookinfomap.get(s).getType());
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
		setLocationRelativeTo(null); 		//�����츦 ��ǻ�� �߰��� ����
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
