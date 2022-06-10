package Eumm;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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

public class JoinExpert extends JFrame{
    Scanner personinfo = null;
    private boolean key = false;
    private String conid;
    public JoinExpert()
    {
      
    }
    public JoinExpert(String cat)
    {
     Font f = new Font("���õ����� Regular",Font.PLAIN,36);
     Font f2 = new Font("���õ����� Regular",Font.PLAIN,25);  // ������ �׸��
     JPanel titlepanel = new JPanel(new BorderLayout());
     JLabel title = new JLabel("ȸ������ ("+cat+" ������)");
     JButton backbutton = new JButton("�ڷ�");
     title.setFont(f);
     titlepanel.add(backbutton,BorderLayout.WEST);
     title.setHorizontalAlignment(JLabel.CENTER);
     titlepanel.add(title,BorderLayout.CENTER); 
     setLayout(new GridLayout(15,1));
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
      JPanel agepanel = new JPanel();
      JLabel agelabel = new JLabel("���� : ");
      agelabel.setFont(f2);
      
      JPanel genderpanel = new JPanel();
      JLabel genderlabel = new JLabel("���� : ");
      genderlabel.setFont(f2);
      
      CheckboxGroup g = new CheckboxGroup();
      Checkbox w= new Checkbox("��",false,g);
      Checkbox m = new Checkbox("��",false,g);
      w.setFont(f2);
      m.setFont(f2);
      JPanel telpanel = new JPanel();
      JLabel telnumlabel = new JLabel("��ȭ��ȣ : ");
      telnumlabel.setFont(f2);
      
      JPanel emailpanel = new JPanel();
      JLabel emaillabel = new JLabel("�̸��� : ");
      emaillabel.setFont(f2);
      JPanel pricepanel = new JPanel();
      JLabel pricelabel = new JLabel("����(ȸ��, ����): ");
      pricelabel.setFont(f2);
      
      s1 = null;
      s2 = null;
     
 
      switch(cat)
      {
         
         case "�":
         {
            s1 = "� ����";
            s2 = "���";
            break;
         }
         case "������":
         {
            s1 = "����";
            s2 = "�б� �� ����(�б�_����(����x))";
            break;
         }
         case "�ɸ�":
         {
            s1 = "���о�";
            s2 = "���ð�(��)";
            break;
         }
         case "���":
         {
            s1 = "��� ����";
            s2 = "�ڰ��� �� ����";
            break;
         }
         case "�丮":
         {
            s1 = "�о�(�ѽ�,�Ͻ�,�߽�,��� ��)";
            s2 = "���";
            break;
         }
         case "�Ǳ�":
         {
            s1 = "�Ǳ� ����";
            s2 = "���(��)";
            break;
         }
         
      }
      
      JPanel typeInfpanel = new JPanel();
      JLabel typeInflabel = new JLabel(s1+" : ");
      typeInflabel.setFont(f2);
      JPanel addinfopanel = new JPanel();
      JLabel addinfolabel = new JLabel(s2+" : ");
      addinfolabel.setFont(f2);
      
       // �ű����� �Է�
      JTextField id = new JTextField(20);
      JPasswordField pw = new JPasswordField(20);
      JPasswordField onemorepw = new JPasswordField(20);

      JTextField name = new JTextField(20);
      JTextField age = new JTextField(20);
      
      JTextField telnum = new JTextField(20);
      JTextField email = new JTextField(20);
      JTextField price = new JTextField(20);
      JTextField typeInf = new JTextField(20);
      JTextField addinfo = new JTextField(20);
      
      JButton check = new JButton("�ߺ� Ȯ��");
      JButton finish = new JButton("�Է¿Ϸ�");
      finish.setFont(f);
      finish.setBackground(Color.PINK);

      idpanel.add(idlabel);
      idpanel.add(id);
      idpanel.add(check);  // �ߺ�Ȯ��
      
      pwpanel.add(pwlabel);
      pwpanel.add(pw);
      
      onemorepwpanel.add(onemorepwlabel);
      onemorepwpanel.add(onemorepw);
      
      namepanel.add(namelabel);
      namepanel.add(name);
      
      agepanel.add(agelabel);
      agepanel.add(age);
      
      genderpanel.add(genderlabel);
      //genderpanel.add(gender);
      genderpanel.add(m);
      genderpanel.add(w);
      
      telpanel.add(telnumlabel);
      telpanel.add(telnum);
      
      emailpanel.add(emaillabel);
      emailpanel.add(email);
      
      pricepanel.add(pricelabel);
      pricepanel.add(price);
      
      typeInfpanel.add(typeInflabel);
      typeInfpanel.add(typeInf);
     
      addinfopanel.add(addinfolabel);
      addinfopanel.add(addinfo);
      
      add(titlepanel);
      add(idpanel);
      add(pwpanel);
      add(onemorepwpanel);
      add(pwq);
      add(answerpanel);
      add(namepanel);
      add(agepanel);
      add(genderpanel);
      add(pricepanel);
      add(telpanel);
      add(emailpanel);
      add(typeInfpanel);
      add(addinfopanel);
      add(finish);
      
      HashMap <String, Member> infomap = new HashMap<String, Member>();  
      ObjectInputStream st=null;
      try {
			st = new ObjectInputStream(new FileInputStream("personinfo.dat"));
			try {
				while(true)
				{
					Member inputmember = new Member();
					inputmember=(Member)st.readObject();
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
                 key = false;
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
      
      // �ߺ� Ȯ���� �� ���ο� ȸ���� map�� �����ϱ�
      Member nnnew = new Member();
      nnnew.setPW(pw.getText());
      nnnew.setName(name.getText());
      nnnew.setTelnum(telnum.getText());
      infomap.put(conid, new Member(conid, nnnew.getPW(), nnnew.getName(), nnnew.getTelnum(),nnnew.getQuestion(),nnnew.getQuestion(), "e"));
       // üũ��ư ������ �� üũ�� �� ��������
       // �Է¿Ϸ� ��ư ������  ȸ���������� �˸�â �ߵ��� �ϱ�
      
      finish.addActionListener(new ActionListener() 
      {
         String genderItem = null;
         
         public void actionPerformed(ActionEvent e)
         {
            if(g.getCurrent().equals(w))
             {
                genderItem = "��";
             }
             else
             {
                genderItem= "��";
             }
            
            
            if(!key)
            {
               JOptionPane.showMessageDialog(null, "ID �ߺ�Ȯ���� ���ּ���.");
            }
              
            else
            {                   // ��й�ȣ ��Ȯ��
               if(pw.getText().equals(onemorepw.getText()))
                {
                     String realFN = null;
                     switch(cat)
                     {
                           case "������":
                           {
                              realFN = "hi.txt";
                              break;
                           }
                           case "�ɸ�":
                           {
                              realFN = "psy.txt";
                              break;
                           }
                           case "�Ǳ�":
                           {
                              realFN = "ins.txt";
                              break;
                           }
                           case "���":
                           {
                              realFN = "lang.txt";
                              break;
                           }
                           case "�丮":
                           {
                              realFN = "cook.txt";
                              break;
                           }
                           case "�":
                           {
                              realFN = "health.txt";
                              break;
                           }
                     }
                     
                    ObjectOutputStream out = null;
                     try {
                         out = new ObjectOutputStream(new FileOutputStream("personinfo.dat"));
                         Member m = new Member(conid , pw.getText() , name.getText(), telnum.getText(),"e",c.getSelectedItem(),answerText.getText());
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
                             
                    
                     BufferedWriter bw = null;
                     
                     try
                     {
                        bw = new BufferedWriter(new FileWriter(realFN, true));
                     }
                     catch (IOException e1)
                     {
                        e1.printStackTrace();
                     }  
                     
                     PrintWriter pw = new PrintWriter(bw, true);
                      
                     pw.write(name.getText() +" "+ age.getText()+" "+ genderItem +" "+0.0+" "+ telnum.getText() +" "+ email.getText() +" "+ price.getText() +" "+ typeInf.getText()+" "+addinfo.getText()+"\n");          
                    
                     pw.flush();
                     pw.close();
                     PrintWriter outputstream = null;
                     
                     try {
                    	 String filename = telnum.getText()+".txt";
                    	 outputstream = 
                    			 new PrintWriter(new FileOutputStream(filename));
                     }catch(FileNotFoundException eee)
                     {
                    	 System.exit(0);
                     }
                     outputstream.println(cat+" "+typeInf.getText());
                     
                     JOptionPane.showMessageDialog(null, "ȸ�������� �����մϴ�!");
                     dispose();
                     outputstream.close();
                     
                     new LogIn();
                   }
            
                else
                {
                   JOptionPane.showMessageDialog(null, "��й�ȣ�� ��Ȯ�� ���ּ���.");
                   id.setText("");
                   pw.setText("");
                   onemorepw.setText("");
                   name.setText("");
                   age.setText("");
                   telnum.setText("");
                   price.setText("");
                   email.setText("");
                   typeInf.setText("");
                   addinfo.setText("");
                   key = false;
                }
            }
         }
      });
       
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
     setLocationRelativeTo(null);       //�����츦 ��ǻ�� �߰��� ����
     setResizable(false);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
    }
   
}