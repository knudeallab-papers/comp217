package Eumm;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReservationInfo extends JFrame{

	private String category, addinfo, ename, date, time, or,ment;
	public ReservationInfo()
	{
		
	}
	public ReservationInfo(String pnum) 
	{
		JPanel reservedpanel = new JPanel();
		Choice c = new Choice();
		
		String fname = pnum+".txt";
		Scanner f = null;
		try {
			f = new Scanner(new FileInputStream(fname));
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		c.add("���� ���� ����");
		while(f.hasNext())
		{
			String line = f.nextLine();
			StringTokenizer t = new StringTokenizer(line," ");
			category = t.nextToken();
			addinfo = t.nextToken();
			ename = t.nextToken();
			date = t.nextToken();
			time = t.nextToken();
			or = t.nextToken();
			if(or.equals("y"))
			{
				or = "���� ����";
			}
			else
			{
				or = "���� ����";
			}
			ment = category+"�о� "+addinfo+"������ "+ename+", "+date+" "+time+" "+or;
			c.add(ment);
		}
		f.close();
		reservedpanel.add(c);
		JPanel exitpanel = new JPanel();
		JButton exitbutton = new JButton("�ݱ�");
		exitpanel.add(exitbutton);
		
		add(reservedpanel,BorderLayout.CENTER);
		add(exitpanel,BorderLayout.SOUTH);
		
		setVisible(true);
	    setSize(500,100);
	    setLocationRelativeTo(null);       //�����츦 ��ǻ�� �߰��� ����
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    exitbutton.addActionListener(new ActionListener() 
	      {
		     public void actionPerformed(ActionEvent e)
		     {
		 		 dispose();
		     }
		  });
	}
	
	

}
