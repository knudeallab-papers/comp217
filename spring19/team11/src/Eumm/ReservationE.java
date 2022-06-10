package Eumm;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReservationE extends JFrame{
	private String line = null;
	public ReservationE(String id,HashMap<String, Member> infomap) {
		
		ArrayList<String> file = new ArrayList<String>();
		JPanel mentpanel = new JPanel();
		JLabel mentlabel = new JLabel("<<�����û ���>>");
		mentpanel.add(mentlabel);
		
		Choice people = new Choice();
		String fname = infomap.get(id).getTelnum()+".txt";
		Scanner f = null;
		try {
			f = new Scanner(new FileInputStream(fname));
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		
		
		String category = f.next();
		String addinfo = f.nextLine();
		//file.add(category+" "+addinfo+'\n');
		people.add("���� ��û ����");
		while(f.hasNext())
		{
			line = f.nextLine();
			people.add(line);
		}
		f.close();
		JPanel buttonpanel = new JPanel();
		JButton okbutton = new JButton("����");
		JButton nobutton = new JButton("����");
		JButton backbutton = new JButton("�ڷ�");
		buttonpanel.add(okbutton);
		buttonpanel.add(nobutton);
		buttonpanel.add(backbutton);
		
		add(mentpanel,BorderLayout.NORTH);
		add(people,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
		
		okbutton.addActionListener(new ActionListener() 
	      {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 if(people.getSelectedItem().equals("���� ��û ����"))
	        	 {
	        		 JOptionPane.showMessageDialog(null,"�����û�� �������ּ���.");
	        	 }
	        	 else
	        	 {
	        		 StringTokenizer t = new StringTokenizer(people.getSelectedItem()," ");
		        	 String cid = t.nextToken();
		        	 String date =t.nextToken();
		        	 date= t.nextToken();
		        	 String time =t.nextToken();
		        	 JOptionPane.showMessageDialog(null, cid+" ���� �����û�� �����Ͽ����ϴ�.");
		        	 
		        	 BufferedWriter b = null;
	                 try {
	                	 String fname = infomap.get(cid).getTelnum()+".txt";
	                    b = new BufferedWriter(new FileWriter(fname, true));
	                 } catch (IOException e1) {
	      
	                    e1.printStackTrace();
	                 }  
	                  PrintWriter ppw = new PrintWriter(b, true);
	                
	                  ppw.write(category+addinfo+" "+infomap.get(id).getName() + " "+ date+" "+time+" y\n");
	                  ppw.flush();
	                  ppw.close();
	                  
	                  Scanner f = null;
		          		try {
		          			f = new Scanner(new FileInputStream(fname));
		          		}catch(FileNotFoundException ee)
		          		{
		          			ee.printStackTrace();
		          			System.exit(0);
		          		}
		          		while(f.hasNext())
		          		{
		          			String line2 = f.nextLine();
		          			if(!line2.equals(people.getSelectedItem()))
		          			{
		          				file.add(line2);
		          			}
		          		}
		          		PrintWriter w = null;
		          		try {
		          			w = new PrintWriter(new FileOutputStream(fname));
		          		}catch(FileNotFoundException eee)
		          		{
		          			System.exit(0);
		          		}
		          		for(int i=0;i<file.size();i++)
		          		{
		          			w.write(file.get(i)+'\n');
		          		}
		          		
		          		
		          		f.close();
		          		w.close();
			        	dispose();
		        	 }
		        	 
		         
	        	 
	         }
	      });
		
		nobutton.addActionListener(new ActionListener() 
	      {
	         public void actionPerformed(ActionEvent e)
	         {
	        	 if(people.getSelectedItem().equals("���� ��û ����"))
	        	 {
	        		 JOptionPane.showMessageDialog(null,"�����û�� �������ּ���.");
	        	 }
	        	 else
	        	 {
	        		 StringTokenizer t = new StringTokenizer(people.getSelectedItem()," ");
		        	 String cid = t.nextToken();
		        	 String date =t.nextToken();
		        	 date= t.nextToken();
		        	 String time = t.nextToken();
		        	 JOptionPane.showMessageDialog(null, cid+" ���� �����û�� �����Ͽ����ϴ�.");
		        	 
		        	 BufferedWriter b = null;
	                 try {
	                	 String fname = infomap.get(cid).getTelnum()+".txt";
	                    b = new BufferedWriter(new FileWriter(fname, true));
	                 } catch (IOException e1) {
	      
	                    e1.printStackTrace();
	                 }  
	                  PrintWriter ppw = new PrintWriter(b, true);
	                
	                  ppw.write(category+addinfo+" "+infomap.get(id).getName() + " "+ date+" "+time+" n\n");
	                  
	                  ppw.flush();
	                  ppw.close();
		        	
	                 
	                  Scanner f = null;
		          		try {
		          			f = new Scanner(new FileInputStream(fname));
		          		}catch(FileNotFoundException ee)
		          		{
		          			ee.printStackTrace();
		          			System.exit(0);
		          		}
		          		while(f.hasNext())
		          		{
		          			String line2 = f.nextLine();
		          			if(!line2.equals(people.getSelectedItem()))
		          			{
		          				file.add(line2);
		          			}
		          		}
		          		PrintWriter w = null;
		          		try {
		          			w = new PrintWriter(new FileOutputStream(fname));
		          		}catch(FileNotFoundException eee)
		          		{
		          			System.exit(0);
		          		}
		          		for(int i=0;i<file.size();i++)
		          		{
		          			w.write(file.get(i)+'\n');
		          		}
		          		
	          		
	          		f.close();
	          		w.close();
		        	dispose();
	        	 }
	        	 
	         }
	      });
		
		 backbutton.addActionListener(new ActionListener() 
	      {
		     public void actionPerformed(ActionEvent e)
		     {
		 		 dispose();
		     }
		  });
		
		setVisible(true);
		setSize(400,650);
		setLocationRelativeTo(null); 		//�����츦 ��ǻ�� �߰��� ����
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
