package Eumm;
/*
 * ExpertList���� ������ �������� ���� �ڼ��� ������ ������
 * ->�����ϱ� : ������ ��� ������ ���� �� �� ����
 * ->�ڷ� : ������ ���� ������ �ٸ� �������� ���� �� �� ����
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
   	 			cat = "�";
   	 			break;
   	 		case "hi":
   	 			cat = "������";
   	 			break;
   	 		case "psy":
   	 			cat = "�ɸ�";
   	 			break;
   	 		case "lang":
   	 			cat = "���";
   	 			break;
   	 		case "ins":
   	 			cat = "�Ǳ�";
   	 			break;
   	 		case "cook":
   	 			cat = "�丮";
   	 			break;
   	 	}
		
		Font f = new Font("���õ����� Regular",Font.PLAIN,24);
		Font b = new Font("���õ����� Regular",Font.PLAIN,14);
		StringTokenizer t = new StringTokenizer(selected,",");
		String name = t.nextToken();
		
		JPanel namepanel = new JPanel();
		namepanel.setBackground(Color.yellow);
		JLabel namelabel = new JLabel(cat+"�о� "+info.get(name).getType()+" ������ "+name);
		namelabel.setFont(f);
		namepanel.add(namelabel);
		
		JPanel buttonpanel = new JPanel();
		JButton backbutton = new JButton("�ڷ�");
		JButton reservation = new JButton("�����ϱ�");
		buttonpanel.add(backbutton);
		buttonpanel.add(reservation);
		
		switch(category)
   	 	{
   	 		case "hel":
   	 			addinfoname = "���";
   	 			break;
   	 		case "hi":
   	 			addinfoname = "�б� �� ����";
   	 			break;
   	 		case "psy":
   	 			addinfoname = "���ð�(��)";
   	 			break;
   	 		case "lang":
   	 			addinfoname = "�ڰ��� �� ����";
   	 			break;
   	 		case "ins":
   	 			addinfoname = "���(��)";
   	 			break;
   	 		case "cook":
   	 			addinfoname = "���";
   	 			break;
   	 	}
		
		JPanel info1 = new JPanel(new GridLayout(2,2));					//�̸� ���� ���� ����
		JLabel namelabel2 = new JLabel("�̸� : "+name);
		namelabel2.setFont(b);
		JLabel agelabel = new JLabel("���� : "+ info.get(name).getAge());
		agelabel.setFont(b);
		JLabel sexlabel = new JLabel("���� : "+ info.get(name).getSex());
		sexlabel.setFont(b);
		JLabel starlabel = new JLabel("���� : "+ info.get(name).getLike()+"/5");
		starlabel.setFont(b);
		
		info1.add(namelabel2);
		info1.add(agelabel);
		info1.add(sexlabel);
		info1.add(starlabel);
		
		JPanel info2 = new JPanel(new GridLayout(2,2));					//��ȭ��ȣ �̸��� ����  addinfo
		JLabel phonenumlabel = new JLabel("��ȭ��ȣ : "+ info.get(name).getTelnum());
		phonenumlabel.setFont(b);
		JLabel emaillabel = new JLabel("�̸��� : "+ info.get(name).getEmail());
		emaillabel.setFont(b);
		JLabel pricelabel = new JLabel("����(ȸ��): "+ info.get(name).getPrice()+"(����)");
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
		setLocationRelativeTo(null); 		//�����츦 ��ǻ�� �߰��� ����
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
