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
		
		Font f = new Font("경기천년바탕 Regular", Font.PLAIN, 20);
	    Font f2 = new Font("경기천년바탕 Regular", Font.PLAIN, 30);
	    Font f3 = new Font("경기천년바탕 Regular", Font.PLAIN, 34);
		
		
		
		JPanel findid = new JPanel(new GridLayout(5,1));
		JPanel id1 = new JPanel(new FlowLayout());
		JPanel id2 = new JPanel(new FlowLayout());
		
		ImageIcon peopleIcon = new ImageIcon("Paint_Eumm.png");
	    JLabel peoplelabel = new JLabel();
	    peoplelabel.setHorizontalAlignment(JLabel.CENTER);
	    peoplelabel.setIcon(peopleIcon);

		
		JLabel name1 = new JLabel("    이름            ");
		JTextField name1text = new JTextField(10);
		name1.setFont(f);
		JLabel num1 = new JLabel("전화번호('-'미포함)");
		JTextField num1text = new JTextField(15);
		num1.setFont(f);

		JLabel eummlabel = new JLabel("세상을 잇는 그곳, '이음' ");
	    eummlabel.setHorizontalAlignment(JLabel.CENTER);
	    eummlabel.setFont(f3);

		JButton findidbutton = new JButton("ID찾기");
		findidbutton.setFont(f2);
	    findidbutton.setBackground(Color.YELLOW);
	    name1.setHorizontalAlignment(JLabel.CENTER);
	    name1text.setHorizontalAlignment(JLabel.CENTER);
		JButton backbutton = new JButton("뒤로");
		
		id1.add(backbutton);
		id1.add(name1);
		id1.add(name1text);
		
		id2.add(num1);
		id2.add(num1text);
		
		findid.add(id1);
		findid.add(id2);
		findid.add(peoplelabel);  // 아이콘넣기
	    findid.add(eummlabel);    // 이음 소개글 넣기

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
		
		JLabel name2 = new JLabel("     이름           ");
		JTextField name2text = new JTextField(10);
		
		name2.setFont(f);
		JLabel num2 = new JLabel("전화번호('-'미포함)");
		JTextField num2text = new JTextField(15);
		num2.setFont(f);
		
		JLabel answer = new JLabel();     // 주관식 질문 대한 답
	    JTextField answerText = new JTextField(20);

		JButton findpwbutton = new JButton("PW찾기");
		findpwbutton.setFont(f2);
	    findpwbutton.setBackground(Color.YELLOW);
		
		String s = "비밀번호 확인질문을 고르세요.";
		String s1 = "나의보물1위는?";
	    String s2 = "부모님께서처음만나신장소는?";
	    String s3 = "가장좋아하는음식은?";
	    String s4 = "아버지의고향은?";
	    String s5 = "중학교3학년때담임선생님성함은?";
	    String s6 = "나의어릴적별명은?";
	    String s7 = "가장좋아하는색은?";
	    String s8 = "가장존경하는인물은?";
	         
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
		
		//이용자의 정보를 받아옴//
		
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
					JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
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
							JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
							name1text.setText("");
							num1text.setText("");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
						name1text.setText("");
						num1text.setText("");
					}
				
				}
			}
		});
		
		findpwbutton.addActionListener(new ActionListener() {
	          
	           public void actionPerformed(ActionEvent e)
	           {
	              if(infomap.containsKey(idtext.getText()) )  // 아이디 포함하고 있는지 확인하기
	              {
	                
	                 if(infomap.get(idtext.getText()).getName().compareTo(name2text.getText()) == 0)  // 이름 포함하고 있는지 확인하기
	                 {
	                    
	                    if(infomap.get(idtext.getText()).getTelnum().compareTo(num2text.getText()) == 0) // 전번 포함하고 있는지
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
	                                JOptionPane.showMessageDialog(null, "해당되는 회원정보가 존재하지 않습니다.");
	                                idtext.setText("");
	                                name2text.setText("");
	                                num2text.setText("");
	                                answerText.setText("");
	                             }
	                       }
	                          
	                       
	                       else
	                       {
	                          JOptionPane.showMessageDialog(null, "해당되는 회원정보가 존재하지 않습니다.");
	                          idtext.setText("");
                              name2text.setText("");
                              num2text.setText("");
                              answerText.setText("");
	                       }
	                    }
	                    else     //전번 X
	                    {
	                       JOptionPane.showMessageDialog(null, "해당되는 회원정보가 존재하지 않습니다.");
	                       idtext.setText("");
                           name2text.setText("");
                           num2text.setText("");
                           answerText.setText("");
	                    }
	                  
	                 }
	                 else   // 이름X
	                 {
	                    JOptionPane.showMessageDialog(null, "해당되는 회원정보가 존재하지 않습니다.");
	                    idtext.setText("");
                        name2text.setText("");
                        num2text.setText("");
                        answerText.setText("");
	                 }
	                 
	              }
	              else    // 아이디 포함하지 않을 때
	              {
	                 JOptionPane.showMessageDialog(null, "해당되는 회원정보가 존재하지 않습니다.");
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
		setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
