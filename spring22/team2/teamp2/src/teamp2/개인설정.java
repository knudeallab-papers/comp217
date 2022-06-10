package teamp2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class 개인설정 {

	private JFrame frame;
	private JTextField 비밀번호수정;
	private JTextField 이메일수정;

	
	public 개인설정(String ID_value) {
		frame = new JFrame();
		frame.setBounds(100, 100, 454, 457);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel 메인패널 = new JPanel();
		메인패널.setBounds(0, 0, 438, 568);
		메인패널.setBackground(new Color(239, 228, 176));
		frame.getContentPane().add(메인패널);
		메인패널.setLayout(null);
		
		ImageIcon icon=new ImageIcon(".\\src\\teamp2\\program\\"+ID_value+"\\profile.png");
		Image img = icon.getImage();
		
		Image changeImg = img.getScaledInstance(98, 98, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel 계정표시라벨 = new JLabel("\uACC4\uC815");
		계정표시라벨.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		계정표시라벨.setBounds(12, 138, 98, 34);
		메인패널.add(계정표시라벨);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 182, 414, 227);
		메인패널.add(panel);
		panel.setLayout(null);
		
		//수정 하는거 //////////////////////////////////////////////////////////////////////////////////////////////////////////////
		둥근버튼 프로필사진수정버튼 = new 둥근버튼("\uD504\uB85C\uD544 \uC0AC\uC9C4 \uC218\uC815");
		프로필사진수정버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 EventQueue.invokeLater(new Runnable() {
					   public void run() {
					    try {
					     새로운옷등록클릭시 frame = new 새로운옷등록클릭시(ID_value,"./src/teamp2/program/dd/",0);
					     frame.setVisible(true);
					    } catch (Exception e) {
					     e.printStackTrace();
					    }
					   }
					  });
				 
				 
			}
		});
		String[]  content={"평범","마름","통통"};
		JComboBox 체형콤보박스 = new JComboBox(content);
		체형콤보박스.setBounds(12, 63, 131, 43);
		panel.add(체형콤보박스);
		프로필사진수정버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 20));
		프로필사진수정버튼.setBackground(new Color(220, 220, 220));
		프로필사진수정버튼.setBounds(12, 10, 393, 43);
		panel.add(프로필사진수정버튼);
		
		둥근버튼 체형수정버튼 = new 둥근버튼("\uCCB4\uD615\uC218\uC815\uD558\uAE30");
		체형수정버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String 변경값=체형콤보박스.getSelectedItem().toString();
				FileReader readFile;		
				BufferedReader br;	
				String getLine;
				String resultvalue = "";
				String correction="";
				
				try {
					readFile = new FileReader("./src/teamp2/program/login.txt");// 로그인 정보 저장된 파일경로			  
					br = new BufferedReader(readFile);
					resultvalue = "";
				
					while((getLine = br.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						if(getLine.contains(ID_value))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue = getLine; //원본문자열
							String[] newStr = resultvalue.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							newStr[3]=변경값;
							correction=newStr[0]+" "+newStr[1]+" "+newStr[2]+" "+newStr[3]+" "+newStr[4];
							
						}
						
						
					}
								
				
				
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
				String result="";
				try {
					BufferedReader br1 = new BufferedReader(new FileReader(new File("./src/teamp2/program/login.txt")));
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/teamp2/program/login2.txt")));

					while((result = br1.readLine()) != null) {
						result = result.replace(resultvalue, correction);
						bw.write(result+"\n");
						bw.flush();
					}
					bw.close();
					br1.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				File file3 = new File("./src/teamp2/program/login.txt");
		        
		    	if( file3.exists() )
		    	{
		    		file3.delete();	
		    	}
		        
		    	String result1="";
		    	
		    	
				try {
					BufferedReader br1 = new BufferedReader(new FileReader(new File("./src/teamp2/program/login2.txt")));
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/teamp2/program/login.txt")));

					while((result1 = br1.readLine()) != null) 
					{
						
						bw.write(result1+"\n");
						bw.flush();
					}
					bw.close();
					br1.close();
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			
				File file2 = new File("./src/teamp2/program/login2.txt");
		        
		    	if( file2.exists() )
		    	{
		    		file2.delete();	
		    	}

				
				
			}
		});
		체형수정버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 20));
		체형수정버튼.setBackground(new Color(220, 220, 220));
		체형수정버튼.setBounds(155, 63, 250, 43);
		panel.add(체형수정버튼);
		
		둥근버튼 비밀번호수정버튼 = new 둥근버튼("\uBE44\uBC00\uBC88\uD638\uC218\uC815");
		비밀번호수정버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String 변경값=비밀번호수정.getText();
				FileReader readFile;		
				BufferedReader br;	
				String getLine;
				String resultvalue = "";
				String correction="";
				
				try {
					readFile = new FileReader("./src/teamp2/program/login.txt");// 로그인 정보 저장된 파일경로			  
					br = new BufferedReader(readFile);
					resultvalue = "";
				
					while((getLine = br.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						if(getLine.contains(ID_value))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue = getLine; //원본문자열
							String[] newStr = resultvalue.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							newStr[1]=변경값;
							correction=newStr[0]+" "+newStr[1]+" "+newStr[2]+" "+newStr[3]+" "+newStr[4];
							
						}
						
						
					}
								
				
				
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
				String result="";
				try {
					BufferedReader br1 = new BufferedReader(new FileReader(new File("./src/teamp2/program/login.txt")));
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/teamp2/program/login2.txt")));

					while((result = br1.readLine()) != null) {
						result = result.replace(resultvalue, correction);
						bw.write(result+"\n");
						bw.flush();
					}
					bw.close();
					br1.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				File file3 = new File("./src/teamp2/program/login.txt");
		        
		    	if( file3.exists() )
		    	{
		    		file3.delete();	
		    	}
		        
		    	String result1="";
		    	
		    	
				try {
					BufferedReader br1 = new BufferedReader(new FileReader(new File("./src/teamp2/program/login2.txt")));
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/teamp2/program/login.txt")));

					while((result1 = br1.readLine()) != null) 
					{
						
						bw.write(result1+"\n");
						bw.flush();
					}
					bw.close();
					br1.close();
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			
				File file2 = new File("./src/teamp2/program/login2.txt");
		        
		    	if( file2.exists() )
		    	{
		    		file2.delete();	
		    	}
				
				
			}
		});
		비밀번호수정버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 20));
		비밀번호수정버튼.setBackground(new Color(220, 220, 220));
		비밀번호수정버튼.setBounds(155, 116, 250, 43);
		panel.add(비밀번호수정버튼);
		
		둥근버튼 이메일수정버튼 = new 둥근버튼("\uC774\uBA54\uC77C\uC218\uC815");
		이메일수정버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String 변경값=이메일수정.getText();
				FileReader readFile;		
				BufferedReader br;	
				String getLine;
				String resultvalue = "";
				String correction="";
				
				try {
					readFile = new FileReader("./src/teamp2/program/login.txt");// 로그인 정보 저장된 파일경로			  
					br = new BufferedReader(readFile);
					resultvalue = "";
				
					while((getLine = br.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						if(getLine.contains(ID_value))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue = getLine; //원본문자열
							String[] newStr = resultvalue.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							newStr[4]=변경값;
							correction=newStr[0]+" "+newStr[1]+" "+newStr[2]+" "+newStr[3]+" "+newStr[4];
							
						}
						
						
					}
								
				
				
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
				String result="";
				try {
					BufferedReader br1 = new BufferedReader(new FileReader(new File("./src/teamp2/program/login.txt")));
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/teamp2/program/login2.txt")));

					while((result = br1.readLine()) != null) {
						result = result.replace(resultvalue, correction);
						bw.write(result+"\n");
						bw.flush();
					}
					bw.close();
					br1.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				File file3 = new File("./src/teamp2/program/login.txt");
		        
		    	if( file3.exists() )
		    	{
		    		file3.delete();	
		    	}
		        
		    	String result1="";
		    	
		    	
				try {
					BufferedReader br1 = new BufferedReader(new FileReader(new File("./src/teamp2/program/login2.txt")));
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/teamp2/program/login.txt")));

					while((result1 = br1.readLine()) != null) 
					{
						
						bw.write(result1+"\n");
						bw.flush();
					}
					bw.close();
					br1.close();
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			
				File file2 = new File("./src/teamp2/program/login2.txt");
		        
		    	if( file2.exists() )
		    	{
		    		file2.delete();	
		    	}
				
				
			}
		});
		이메일수정버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 20));
		이메일수정버튼.setBackground(new Color(220, 220, 220));
		이메일수정버튼.setBounds(155, 169, 250, 43);
		panel.add(이메일수정버튼);
		
		비밀번호수정 = new JTextField();
		비밀번호수정.setBounds(12, 116, 131, 43);
		panel.add(비밀번호수정);
		비밀번호수정.setColumns(10);
		
		이메일수정 = new JTextField();
		이메일수정.setColumns(10);
		이메일수정.setBounds(12, 169, 131, 43);
		panel.add(이메일수정);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 10, 414, 118);
		메인패널.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ID_value);
		lblNewLabel.setBounds(132, 28, 192, 62);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("휴먼편지체", Font.PLAIN, 20));
		
		JPanel 프로필패널 = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(changeIcon.getImage(), 0, 0, null);
                
                setOpaque(false);
                super.paintComponent(g);
			}			
		};
		프로필패널.setBounds(12, 10, 98, 98);
		panel_1.add(프로필패널);
		프로필패널.setBackground(Color.CYAN);
		
		프로필패널.setLayout(null);
		프로필패널.setVisible(true);
		
		frame.setVisible(true);
	}
}