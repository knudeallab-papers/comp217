package Eumm;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReservationC extends JFrame{

	public ReservationC(String id,HashMap <String, Member> infomap,String pnum) {
		
		
		JPanel mentpanel = new JPanel();
		JLabel mentlabel = new JLabel("����â");
		mentpanel.add(mentlabel);
		JPanel infopanel = new JPanel(new GridLayout(2,1));
		JPanel datepanel = new JPanel();
		JLabel date = new JLabel("���Ͻô� ��¥�� �Է����ּ���(��/��).");
		JTextField inputdate = new JTextField(10);
		datepanel.add(date);
		datepanel.add(inputdate);
		JPanel timepanel = new JPanel();
		JLabel timelabel = new JLabel("���Ͻô� �ð��� �������ּ���.");
		Choice timechoice = new Choice();
		JButton backbutton = new JButton("�ݱ�");
		for(int i=9;i<=22;i++)
		{
			timechoice.add(i+":00");
		}
		timepanel.add(timelabel);
		timepanel.add(timechoice);
		
		infopanel.add(datepanel);
		infopanel.add(timepanel);
		
		JPanel confirmpanel = new JPanel();
		JButton confirm = new JButton("�����û");
		confirmpanel.add(confirm);
		confirmpanel.add(backbutton);
		add(mentpanel,BorderLayout.NORTH);
		add(infopanel,BorderLayout.CENTER);
		add(confirmpanel,BorderLayout.SOUTH);
		
		
		confirm.addActionListener(new ActionListener() 
	      {
		     public void actionPerformed(ActionEvent e)
		     {
		 		 BufferedWriter b = null;
                 try {
                	 String fname = pnum+".txt";
                    b = new BufferedWriter(new FileWriter(fname, true));
                 } catch (IOException e1) {
      
                    e1.printStackTrace();
                 }  
                  PrintWriter ppw = new PrintWriter(b, true);
                
                  ppw.write(id+" : "+inputdate.getText()+" "+timechoice.getSelectedItem()+" "
  		 				+infomap.get(id).getTelnum()+'\n');
                  ppw.flush();
                  ppw.close();
                  
		 		JOptionPane.showMessageDialog(null, "���������� �����û�� �Ͽ����ϴ�.");
		 		dispose();
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
	


