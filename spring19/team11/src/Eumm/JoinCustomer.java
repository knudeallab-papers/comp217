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
     
      Font f = new Font("경기천년바탕 Regular",Font.PLAIN,36);
      Font f2 = new Font("경기천년바탕 Regular",Font.PLAIN,25);  // 각각의 항목들

      JLabel title = new JLabel("<<회원가입>>");
      JButton backbutton = new JButton("뒤로");
      
      title.setFont(f);
      title.setHorizontalAlignment(JLabel.CENTER);
      titlepanel.add(title,BorderLayout.CENTER);
      titlepanel.add(backbutton,BorderLayout.WEST);
      setLayout(new GridLayout(9,1));
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
      JPanel telpanel = new JPanel();
      JLabel telnumlabel = new JLabel("전화번호 : ");
      telnumlabel.setFont(f2);
     
      
       // 신규정보 입력
      JTextField id = new JTextField(10);
      JPasswordField pw = new JPasswordField(20);
      JPasswordField onemorepw = new JPasswordField(20);
      JTextField name = new JTextField(10);
      JTextField telnum = new JTextField(10);
      
      
      JButton check = new JButton("중복 확인");
      
      JButton finish = new JButton("입력완료");
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
      
 
       // 입력완료 버튼 누르면  회원가입축하 알림창 뜨도록 하기
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
                   
                    
                    JOptionPane.showMessageDialog(null, "회원가입을 축하합니다!");
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
                    JOptionPane.showMessageDialog(null, "비밀번호를 재확인 해주세요.");
                   
                 }
        	 }
        	 else
        	 {
        		 JOptionPane.showMessageDialog(null, "ID 중복확인을 해주세요.");
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
   	setLocationRelativeTo(null); 		//윈도우를 컴퓨터 중간에 띄우기
   	setResizable(false);
   	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
}









