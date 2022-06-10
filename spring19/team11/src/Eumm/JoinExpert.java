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
     Font f = new Font("경기천년바탕 Regular",Font.PLAIN,36);
     Font f2 = new Font("경기천년바탕 Regular",Font.PLAIN,25);  // 각각의 항목들
     JPanel titlepanel = new JPanel(new BorderLayout());
     JLabel title = new JLabel("회원가입 ("+cat+" 전문가)");
     JButton backbutton = new JButton("뒤로");
     title.setFont(f);
     titlepanel.add(backbutton,BorderLayout.WEST);
     title.setHorizontalAlignment(JLabel.CENTER);
     titlepanel.add(title,BorderLayout.CENTER); 
     setLayout(new GridLayout(15,1));
         // 이 패널에 id, pw, 이름, 전번 넣기
     JPanel idpanel = new JPanel();
     JLabel idlabel = new JLabel("ID : ");
     idlabel.setFont(f2);
     JPanel pwpanel = new JPanel();
     JLabel pwlabel = new JLabel("PW : ");
     pwlabel.setFont(f2);
     JPanel onemorepwpanel = new JPanel();
     JLabel onemorepwlabel = new JLabel("PW 재확인 : ");
     onemorepwlabel.setFont(f2);
     Choice c = new Choice();
     JPanel pwq = new JPanel();
     JPanel answerpanel = new JPanel();
     JLabel answer = new JLabel("답변 : ");     // 주관식 질문 대한 답
     answer.setFont(f2);
	 JTextField answerText = new JTextField(20);
	 answerpanel.add(answer);
	 answerpanel.add(answerText);
	 pwq.add(c);
	c.setFont(f2);
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
	    
      JPanel namepanel = new JPanel();
      JLabel namelabel = new JLabel("이름 : ");
      namelabel.setFont(f2);
      JPanel agepanel = new JPanel();
      JLabel agelabel = new JLabel("나이 : ");
      agelabel.setFont(f2);
      
      JPanel genderpanel = new JPanel();
      JLabel genderlabel = new JLabel("성별 : ");
      genderlabel.setFont(f2);
      
      CheckboxGroup g = new CheckboxGroup();
      Checkbox w= new Checkbox("여",false,g);
      Checkbox m = new Checkbox("남",false,g);
      w.setFont(f2);
      m.setFont(f2);
      JPanel telpanel = new JPanel();
      JLabel telnumlabel = new JLabel("전화번호 : ");
      telnumlabel.setFont(f2);
      
      JPanel emailpanel = new JPanel();
      JLabel emaillabel = new JLabel("이메일 : ");
      emaillabel.setFont(f2);
      JPanel pricepanel = new JPanel();
      JLabel pricelabel = new JLabel("가격(회당, 만원): ");
      pricelabel.setFont(f2);
      
      s1 = null;
      s2 = null;
     
 
      switch(cat)
      {
         
         case "운동":
         {
            s1 = "운동 종류";
            s2 = "장소";
            break;
         }
         case "고등과외":
         {
            s1 = "과목";
            s2 = "학교 및 전공(학교_전공(띄어쓰기x))";
            break;
         }
         case "심리":
         {
            s1 = "상담분야";
            s2 = "상담시간(분)";
            break;
         }
         case "언어":
         {
            s1 = "언어 종류";
            s2 = "자격증 및 점수";
            break;
         }
         case "요리":
         {
            s1 = "분야(한식,일식,중식,양식 등)";
            s2 = "장소";
            break;
         }
         case "악기":
         {
            s1 = "악기 종류";
            s2 = "경력(년)";
            break;
         }
         
      }
      
      JPanel typeInfpanel = new JPanel();
      JLabel typeInflabel = new JLabel(s1+" : ");
      typeInflabel.setFont(f2);
      JPanel addinfopanel = new JPanel();
      JLabel addinfolabel = new JLabel(s2+" : ");
      addinfolabel.setFont(f2);
      
       // 신규정보 입력
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
      
      JButton check = new JButton("중복 확인");
      JButton finish = new JButton("입력완료");
      finish.setFont(f);
      finish.setBackground(Color.PINK);

      idpanel.add(idlabel);
      idpanel.add(id);
      idpanel.add(check);  // 중복확인
      
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
      // 기존에 있던  personinfo 파일 읽어오기
	  // 새로 입력한 회원의 ID가 이미 파일에 있는 것과 같다면  "중복임!!" 알림창 띄우기
      // ID를 비교할 때, 파일에 있는 모든 값들을 반복문으로 확인하기
      check.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent e)
         {
           String iddd=id.getText();
           if(iddd.compareTo("")!=0)
            {
              if((infomap.containsKey(id.getText())))
              {
                 JOptionPane.showMessageDialog(null, "이미 있는 아이디입니다.");
                 id.setText("");
                 key = false;
              }
              else
              {
                 JOptionPane.showMessageDialog(null, "사용가능한 ID입니다.");
                 conid = new String(iddd);
                 key = true;
              }
            }
           else
           {
              JOptionPane.showMessageDialog(null, "ID를 입력해주세요.");
           }
         }
      }
      );
      
      // 중복 확인한 후 새로운 회원을 map에 저장하기
      Member nnnew = new Member();
      nnnew.setPW(pw.getText());
      nnnew.setName(name.getText());
      nnnew.setTelnum(telnum.getText());
      infomap.put(conid, new Member(conid, nnnew.getPW(), nnnew.getName(), nnnew.getTelnum(),nnnew.getQuestion(),nnnew.getQuestion(), "e"));
       // 체크버튼 누르면 그 체크한 값 가져오기
       // 입력완료 버튼 누르면  회원가입축하 알림창 뜨도록 하기
      
      finish.addActionListener(new ActionListener() 
      {
         String genderItem = null;
         
         public void actionPerformed(ActionEvent e)
         {
            if(g.getCurrent().equals(w))
             {
                genderItem = "여";
             }
             else
             {
                genderItem= "남";
             }
            
            
            if(!key)
            {
               JOptionPane.showMessageDialog(null, "ID 중복확인을 해주세요.");
            }
              
            else
            {                   // 비밀번호 재확인
               if(pw.getText().equals(onemorepw.getText()))
                {
                     String realFN = null;
                     switch(cat)
                     {
                           case "고등과외":
                           {
                              realFN = "hi.txt";
                              break;
                           }
                           case "심리":
                           {
                              realFN = "psy.txt";
                              break;
                           }
                           case "악기":
                           {
                              realFN = "ins.txt";
                              break;
                           }
                           case "언어":
                           {
                              realFN = "lang.txt";
                              break;
                           }
                           case "요리":
                           {
                              realFN = "cook.txt";
                              break;
                           }
                           case "운동":
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
                     
                     JOptionPane.showMessageDialog(null, "회원가입을 축하합니다!");
                     dispose();
                     outputstream.close();
                     
                     new LogIn();
                   }
            
                else
                {
                   JOptionPane.showMessageDialog(null, "비밀번호를 재확인 해주세요.");
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
     setLocationRelativeTo(null);       //윈도우를 컴퓨터 중간에 띄우기
     setResizable(false);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
    }
   
}