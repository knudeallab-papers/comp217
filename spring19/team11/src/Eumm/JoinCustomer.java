package Eumm;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinCustomer extends JFrame{
	 Scanner personinfo = null;
	 private boolean key = false;
     private String conid;
   public JoinCustomer()
   {
      setBackground(Color.ORANGE);
      JPanel titlepanel = new JPanel(new BorderLayout());
     
      Font f = new Font("���õ����� Regular",Font.PLAIN,36);
      Font f2 = new Font("���õ����� Regular",Font.PLAIN,25);  // ������ �׸��

      JLabel title = new JLabel("<<ȸ������>>");
      JButton backbutton = new JButton("�ڷ�");
      
      title.setFont(f);
      title.setHorizontalAlignment(JLabel.CENTER);
      titlepanel.add(title,BorderLayout.CENTER);
      titlepanel.add(backbutton,BorderLayout.WEST);
      setLayout(new GridLayout(9,1));
         // �� �гο� id, pw, �̸�, ���� �ֱ�
      JPanel idpanel = new JPanel();
      JLabel idlabel = new JLabel("ID : ");
      idlabel.setFont(f2);
      JPanel pwpanel = new JPanel();
      JLabel pwlabel = new JLabel("PW : ");
      pwlabel.setFont(f2);
      JPanel onemorepwpanel = new JPanel();
      JLabel onemorepwlabel = new JLabel("PW ��Ȯ�� : ");
      onemorepwlabel.setFont(f2);
      Choice c = new Choice();
      JPanel pwq = new JPanel();
      JPanel answerpanel = new JPanel();
      JLabel answer = new JLabel("�亯 : ");     // �ְ��� ���� ���� ��
      answer.setFont(f2);

      JTextField answerText = new JTextField(20);
	  answerpanel.add(answer);
	  answerpanel.add(answerText);
	  pwq.add(c);
	  c.setFont(f2);
		String s = "��й�ȣ Ȯ�������� ������.";
		String s1 = "���Ǻ���1����?";
	    String s2 = "�θ�Բ���ó����������Ҵ�?";
	    String s3 = "���������ϴ�������?";
	    String s4 = "�ƹ����ǰ�����?";
	    String s5 = "���б�3�г⶧���Ӽ����Լ�����?";
	    String s6 = "���Ǿ��������?";
	    String s7 = "���������ϴ»���?";
	    String s8 = "���������ϴ��ι���?";
	         
	    c.add(s);
	    c.add(s1);
	    c.add(s2);
	    c.add(s3);
	    c.add(s4);
	    c.add(s5);
	    c.add(s6);
	    c.add(s7);
	    c.add(s8);
      
      JPanel namepanel = new JPanel();
      JLabel namelabel = new JLabel("�̸� : ");
      namelabel.setFont(f2);
      JPanel telpanel = new JPanel();
      JLabel telnumlabel = new JLabel("��ȭ��ȣ : ");
      telnumlabel.setFont(f2);
     
      
       // �ű����� �Է�
      JTextField id = new JTextField(10);
      JPasswordField pw = new JPasswordField(20);
      JPasswordField onemorepw = new JPasswordField(20);
      JTextField name = new JTextField(10);
      JTextField telnum = new JTextField(10);
      
      
      JButton check = new JButton("�ߺ� Ȯ��");
      
      JButton finish = new JButton("�Է¿Ϸ�");
      finish.setFont(f);
      finish.setBackground(Color.PINK);
      
      idpanel.add(idlabel);
      idpanel.add(id);
      idpanel.add(check);
      
      pwpanel.add(pwlabel);
      pwpanel.add(pw);
      
      onemorepwpanel.add(onemorepwlabel);
      onemorepwpanel.add(onemorepw);
      
      namepanel.add(namelabel);
      namepanel.add(name);
      
      telpanel.add(telnumlabel);
      telpanel.add(telnum);
      
      add(titlepanel);
      add(idpanel);
      add(pwpanel);
      add(onemorepwpanel);
      add(pwq);
      add(answerpanel);
      add(namepanel);
      add(telpanel);
      add(finish);
      
      
      HashMap <String, Member> infomap = new HashMap<String, Member>();
      ObjectInputStream st=null;
      try {
			st = new ObjectInputStream(new FileInputStream("personinfo.dat"));
			try {
				while(true)
				{
					Member inputmember = new Member();
					inputmember = (Member)st.readObject();
					infomap.put(inputmember.getID(), inputmember);
					
				}
			}catch(EOFException ee)
			{
				
			}
			st.close();
		}catch(IOException | ClassNotFoundException e)
		{
			
		}
      	
      
      // ������ �ִ�  personinfo ���� �о����
      // ���� �Է��� ȸ���� ID�� �̹� ���Ͽ� �ִ� �Ͱ� ���ٸ�  "�ߺ���!!" �˸�â ����
      // ID�� ���� ��, ���Ͽ� �ִ� ��� ������ �ݺ������� Ȯ���ϱ�
      
     
      check.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent e)
         {
        	String iddd=id.getText();
        	if(iddd.compareTo("")!=0)
            {
        		if((infomap.containsKey(id.getText())))
        		{
        			JOptionPane.showMessageDialog(null, "�̹� �ִ� ���̵��Դϴ�.");
        			id.setText("");
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(null, "��밡���� ID�Դϴ�.");
        			conid = new String(iddd);
        			key = true;
        		}
            }
        	else
        	{
        		JOptionPane.showMessageDialog(null, "ID�� �Է����ּ���.");
        	}
         }
      }
      );
      
 
       // �Է¿Ϸ� ��ư ������  ȸ���������� �˸�â �ߵ��� �ϱ�
       finish.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent e)
         {
        	 if(key)
        	 {
        		 if(pw.getText().equals(onemorepw.getText()))
                 {
        			
             		 ObjectOutputStream out = null;
                    try {
                       out = new ObjectOutputStream(new FileOutputStream("personinfo.dat"));
                       Member m = new Member(conid , pw.getText() , name.getText(), telnum.getText(),"c",c.getSelectedItem(),answerText.getText());
                       infomap.put(conid, m);
                       
                       Set<String> keys = infomap.keySet();
                       Iterator<String> it = keys.iterator();
                       
                       while(it.hasNext()){
                    	   String key = it.next();
                    	   out.writeObject(infomap.get(key));
                       }
                       out.close();
                       
                       
                    } catch (IOException e1) {
         
                       e1.printStackTrace();
                    }  
                   
                    
                    JOptionPane.showMessageDialog(null, "ȸ�������� �����մϴ�!");
                       dispose();
                       new LogIn();
                 }
                 else
                 {
                	 id.setText("");
                     pw.setText("");
                     onemorepw.setText("");
                     name.setText("");
                     key = false;
                    JOptionPane.showMessageDialog(null, "��й�ȣ�� ��Ȯ�� ���ּ���.");
                   
                 }
        	 }
        	 else
        	 {
        		 JOptionPane.showMessageDialog(null, "ID �ߺ�Ȯ���� ���ּ���.");
        	 }
            
         }
      }
      );
       backbutton.addActionListener(new ActionListener() 
       {
          public void actionPerformed(ActionEvent e)
          {
         	 dispose();
         	 new LogIn();
          }
       });
    setVisible(true);
   	setSize(1000,650);
   	setLocationRelativeTo(null); 		//�����츦 ��ǻ�� �߰��� ����
   	setResizable(false);
   	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
}









