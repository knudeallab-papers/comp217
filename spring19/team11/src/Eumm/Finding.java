package Eumm;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Finding extends JFrame{

	 Scanner AskPasswordinfo = null;
	 private Choice c = new Choice();
	 public Finding(HashMap <String, Member> infomap) {
		
		setLayout(new GridLayout(1,2));
		
		Font f = new Font("���õ����� Regular", Font.PLAIN, 20);
	    Font f2 = new Font("���õ����� Regular", Font.PLAIN, 30);
	    Font f3 = new Font("���õ����� Regular", Font.PLAIN, 34);
		
		
		
		JPanel findid = new JPanel(new GridLayout(5,1));
		JPanel id1 = new JPanel(new FlowLayout());
		JPanel id2 = new JPanel(new FlowLayout());
		
		ImageIcon peopleIcon = new ImageIcon("Paint_Eumm.png");
	    JLabel peoplelabel = new JLabel();
	    peoplelabel.setHorizontalAlignment(JLabel.CENTER);
	    peoplelabel.setIcon(peopleIcon);

		
		JLabel name1 = new JLabel("    �̸�            ");
		JTextField name1text = new JTextField(10);
		name1.setFont(f);
		JLabel num1 = new JLabel("��ȭ��ȣ('-'������)");
		JTextField num1text = new JTextField(15);
		num1.setFont(f);

		JLabel eummlabel = new JLabel("������ �մ� �װ�, '����' ");
	    eummlabel.setHorizontalAlignment(JLabel.CENTER);
	    eummlabel.setFont(f3);

		JButton findidbutton = new JButton("IDã��");
		findidbutton.setFont(f2);
	    findidbutton.setBackground(Color.YELLOW);
	    name1.setHorizontalAlignment(JLabel.CENTER);
	    name1text.setHorizontalAlignment(JLabel.CENTER);
		JButton backbutton = new JButton("�ڷ�");
		
		id1.add(backbutton);
		id1.add(name1);
		id1.add(name1text);
		
		id2.add(num1);
		id2.add(num1text);
		
		findid.add(id1);
		findid.add(id2);
		findid.add(peoplelabel);  // �����ֱܳ�
	    findid.add(eummlabel);    // ���� �Ұ��� �ֱ�

		//findid.add(backbutton);
		findid.add(findidbutton);
		
		JPanel findpw = new JPanel(new GridLayout(5,1));
		JPanel pw1 = new JPanel(new FlowLayout());
		JPanel pw2 = new JPanel(new FlowLayout());
		JPanel pw3 = new JPanel(new FlowLayout());
		JPanel pw4 = new JPanel(new FlowLayout());
		
		JLabel id = new JLabel("      ID     ");
		JTextField idtext = new JTextField(10);
		id.setFont(f);
		
		JLabel name2 = new JLabel("     �̸�           ");
		JTextField name2text = new JTextField(10);
		
		name2.setFont(f);
		JLabel num2 = new JLabel("��ȭ��ȣ('-'������)");
		JTextField num2text = new JTextField(15);
		num2.setFont(f);
		
		JLabel answer = new JLabel();     // �ְ��� ���� ���� ��
	    JTextField answerText = new JTextField(20);

		JButton findpwbutton = new JButton("PWã��");
		findpwbutton.setFont(f2);
	    findpwbutton.setBackground(Color.YELLOW);
		
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
		//c.setFont(f);
		pw1.add(id);
		pw1.add(idtext);
		
		
		pw2.add(name2);
		pw2.add(name2text);
		
		pw3.add(num2);
		pw3.add(num2text);
		
		pw4.add(c);
	    pw4.add(answer);
	    pw4.add(answerText);

	
		findpw.add(pw1);
		findpw.add(pw2);
		findpw.add(pw3);
		findpw.add(pw4);
		findpw.add(findpwbutton);
		
		add(findid);
		add(findpw);
		
		//�̿����� ������ �޾ƿ�//
		
		findidbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String key= "";
				for(String k : infomap.keySet()) {
					if(name1text.getText().equals(infomap.get(k).getName()))
					{
						key = k;
					}
				}
				
				if(key.equals(""))
				{
					JOptionPane.showMessageDialog(null, "��ġ�ϴ� ������ �����ϴ�.");
					name1text.setText("");
					num1text.setText("");
				}
			
				else
				{
					if(name1text.getText().equals(infomap.get(key).getName()))
				
					{
						if(num1text.getText().equals(infomap.get(key).getTelnum()))
						{
							new ShowID(infomap,key);
							//dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "��ġ�ϴ� ������ �����ϴ�.");
							name1text.setText("");
							num1text.setText("");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "��ġ�ϴ� ������ �����ϴ�.");
						name1text.setText("");
						num1text.setText("");
					}
				
				}
			}
		});
		
		findpwbutton.addActionListener(new ActionListener() {
	          
	           public void actionPerformed(ActionEvent e)
	           {
	              if(infomap.containsKey(idtext.getText()) )  // ���̵� �����ϰ� �ִ��� Ȯ���ϱ�
	              {
	                
	                 if(infomap.get(idtext.getText()).getName().compareTo(name2text.getText()) == 0)  // �̸� �����ϰ� �ִ��� Ȯ���ϱ�
	                 {
	                    
	                    if(infomap.get(idtext.getText()).getTelnum().compareTo(num2text.getText()) == 0) // ���� �����ϰ� �ִ���
	                    {
	                       if(c.getSelectedItem().compareTo(infomap.get(idtext.getText()).getQuestion())== 0)
	                       {
	                         
	                             if(infomap.get(idtext.getText()).getAnswer().compareTo(answerText.getText()) == 0)
	                             {
	                            	new ShowPW(infomap,infomap.get(idtext.getText()).getPW());
	     							//dispose();
	                             }
	                             else
	                             {
	                                JOptionPane.showMessageDialog(null, "�ش�Ǵ� ȸ�������� �������� �ʽ��ϴ�.");
	                                idtext.setText("");
	                                name2text.setText("");
	                                num2text.setText("");
	                                answerText.setText("");
	                             }
	                       }
	                          
	                       
	                       else
	                       {
	                          JOptionPane.showMessageDialog(null, "�ش�Ǵ� ȸ�������� �������� �ʽ��ϴ�.");
	                          idtext.setText("");
                              name2text.setText("");
                              num2text.setText("");
                              answerText.setText("");
	                       }
	                    }
	                    else     //���� X
	                    {
	                       JOptionPane.showMessageDialog(null, "�ش�Ǵ� ȸ�������� �������� �ʽ��ϴ�.");
	                       idtext.setText("");
                           name2text.setText("");
                           num2text.setText("");
                           answerText.setText("");
	                    }
	                  
	                 }
	                 else   // �̸�X
	                 {
	                    JOptionPane.showMessageDialog(null, "�ش�Ǵ� ȸ�������� �������� �ʽ��ϴ�.");
	                    idtext.setText("");
                        name2text.setText("");
                        num2text.setText("");
                        answerText.setText("");
	                 }
	                 
	              }
	              else    // ���̵� �������� ���� ��
	              {
	                 JOptionPane.showMessageDialog(null, "�ش�Ǵ� ȸ�������� �������� �ʽ��ϴ�.");
	                 idtext.setText("");
                     name2text.setText("");
                     num2text.setText("");
                     answerText.setText("");
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
		setLocationRelativeTo(null); 		//�����츦 ��ǻ�� �߰��� ����
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
