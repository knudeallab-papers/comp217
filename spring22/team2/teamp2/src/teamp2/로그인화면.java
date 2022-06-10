package teamp2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.UIManager;


public class 로그인화면 extends JFrame {

	
	private JTextField textField;
	private JPasswordField passwordField;
	String ID_value; 
	String PW_value;
	JPanel contentPane;
    JLabel 어플제목 = new JLabel();
    
	
	public 로그인화면() 
	{
		
		
		getContentPane().setBackground(new Color(255, 153, 102));
		setBackground(Color.WHITE);
				
				
		try {
            
			setDefaultCloseOperation(EXIT_ON_CLOSE);
            contentPane = (JPanel) getContentPane();
            setSize(new Dimension(800, 600));
            setTitle("TODAY FIT");
    		getContentPane().setLayout(null);
    		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    		JPanel 로그인버튼클릭시패널 = new JPanel();
    		로그인버튼클릭시패널.setBackground(new Color(255, 153, 102));
    		로그인버튼클릭시패널.setBounds(113, 449, 557, 87);
    		JLabel msgLabel = new JLabel("");
    		msgLabel.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
    		msgLabel.setBounds(43, 33, 400, 20);
			로그인버튼클릭시패널.add(msgLabel);
    		
    		
    		getContentPane().add(로그인버튼클릭시패널);
    		
    		
    		로그인버튼클릭시패널.setVisible(false);
    		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    		JLabel lblNewLabel_2 = new JLabel("TODAY FIT");
    		lblNewLabel_2.setForeground(UIManager.getColor("text"));
    		lblNewLabel_2.setBackground(Color.ORANGE);
    		lblNewLabel_2.setFont(new Font("휴먼편지체", Font.BOLD, 99));
    		lblNewLabel_2.setBounds(149, 41, 485, 185);
    		
    		둥근버튼 btnNewButton = new 둥근버튼("입장 하기");
			btnNewButton.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
			btnNewButton.setBounds(250,33, 97, 23);
			로그인버튼클릭시패널.add(btnNewButton);
    		
    		getContentPane().add(lblNewLabel_2);
    		
    		JPanel panel = new JPanel();
    		panel.setBackground(new Color(255, 153, 102));
    		panel.setBounds(253, 29, 277, 87);
    		getContentPane().add(panel);
            // add the header label

            
    		어플제목.setBounds(0, 0, 784, 561);
    		
    		contentPane.add(어플제목);
    		
    		textField = new JTextField();
    		textField.setBounds(313, 452, 192, 32);
    		contentPane.add(textField);
    		textField.setColumns(10);
    		
    		passwordField = new JPasswordField();
    		passwordField.setBounds(313, 494, 192, 32);
    		contentPane.add(passwordField);
    		
    		JLabel lblNewLabel = new JLabel("ID");
    		lblNewLabel.setForeground(Color.WHITE);
    		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    		lblNewLabel.setFont(new Font("휴먼편지체", Font.BOLD, 25));
    		lblNewLabel.setBounds(256, 452, 45, 32);
    		contentPane.add(lblNewLabel);
    		
    		JLabel lblNewLabel_1 = new JLabel("PW");
    		lblNewLabel_1.setForeground(Color.WHITE);
    		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
    		lblNewLabel_1.setFont(new Font("휴먼편지체", Font.BOLD, 25));
    		lblNewLabel_1.setBounds(256, 494, 45, 32);
    		contentPane.add(lblNewLabel_1);
    		
    		둥근버튼 로그인버튼 = new 둥근버튼("로그인");
    		로그인버튼.setFont(new Font("휴먼편지체", Font.BOLD, 17));
    		
    		로그인버튼.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
    				로그인버튼클릭시패널.setVisible(true);
    				ID_value=textField.getText();; 
    				PW_value=passwordField.getText();;
    				int  check=-1;
    				FileReader readFile;		
    				BufferedReader br;	
    				String getLine;		
    				try {
    					readFile = new FileReader(".\\src\\teamp2\\program\\login.txt");// 로그인 정보 저장된 파일경로			  
    					br = new BufferedReader(readFile);
    					String resultvalue = "";
    				
    					while((getLine = br.readLine()) != null)
    					{		// 한줄씩 읽기 (마지막줄 체크)     					
    						
    						
    						if(getLine.startsWith(ID_value))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
    						{					
    							resultvalue = getLine;
    							String[] newStr = resultvalue.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
    							
    							if(newStr[1].compareTo(PW_value)==0)
    							{
    								msgLabel.setText("환영합니다 "+newStr[0]+" 님");
    								msgLabel.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
    								msgLabel.setBounds(43, 33, 400, 20);
    								
    								btnNewButton.setText("입장하기");
    								
    								btnNewButton.addActionListener(new ActionListener() {
    									public void actionPerformed(ActionEvent e) {
    										어플홈화면 win= new 어플홈화면(ID_value);
    									}
    								});
    								
    								
    								check=1;
    							}
    							else
    							{
    								msgLabel.setText("해당 아이디가 존재하지만 비밀번호가 틀렸습니다\n 다시입력 해주세요");
    								msgLabel.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
    								msgLabel.setBounds(43, 33, 400, 20);
    								btnNewButton.setText("다시 입력하기");
    								
    								btnNewButton.addActionListener(new ActionListener() {
    									public void actionPerformed(ActionEvent e) {
    										로그인버튼클릭시패널.setVisible(false);
    									}
    								});
    						  								
    								
    								check=1;
    							}
    							
    						}
    						
    							
    						
    					}
    					
    					if(check<0)
    					{
    						msgLabel.setText("해당 아이디가 존재하지 않습니다 다시입력하시거나 회원가입 해주세요");
    						msgLabel.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
    						msgLabel.setBounds(43, 73, 400, 20);
    						
    						btnNewButton.setText("다시 입력하기");
							
							btnNewButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									로그인버튼클릭시패널.setVisible(false);
								}
							});
														
    						
    					}
    				
    				} 
    				catch (FileNotFoundException e1) 
    				{
    					e1.printStackTrace();
    				} catch (IOException e1) 
    				{
    					e1.printStackTrace();
    				}
    				
    			}
    		});
    		로그인버튼.setBounds(517, 450, 109, 32);
    		contentPane.add(로그인버튼);
    		
    		둥근버튼 회원가입버튼 = new 둥근버튼("회원가입");
    		회원가입버튼.setFont(new Font("휴먼편지체", Font.BOLD, 17));
    		회원가입버튼.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
    				ID_value=textField.getText();; 
    				PW_value=passwordField.getText();;
    				회원가입버튼클릭시 reg= new 회원가입버튼클릭시();
    			}
    		});
    		회원가입버튼.setBounds(517, 492, 109, 32);
    		contentPane.add(회원가입버튼);
            
            // add the image label
            ImageIcon ii = new ImageIcon(this.getClass().getResource("main_login.gif"));
            어플제목.setIcon(ii);
            contentPane.add(어플제목, java.awt.BorderLayout.CENTER);
            // show it
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
		
	}
	
}
