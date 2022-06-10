package teamp2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;



public class 회원가입버튼클릭시 {

	private JFrame frame;
	private static String id2;
	private static String pw2;
	private static String gender;
	private static String shape;
	private static String email2;
	private JTextField 아이디입력창;
	private JTextField 패스워드입력창;
	private JTextField 이메일입력창;
	
	
	public 회원가입버튼클릭시(){
		
			
		frame = new JFrame();
		frame.setBounds(100, 100, 377, 474);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("TODAY FIT");
		frame.getContentPane().setLayout(null);
		
		ImageIcon icon=new ImageIcon("./src/teamp2/program/background.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(377, 474, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(changeIcon.getImage(), 0, 0, null);
                
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
			}			
		};
		panel.setBounds(0, 0, 361, 435);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		String[] genderlist= {"남자", "여자"};
		String[] shapelist= {"마름", "평범","통통"};
		
				
		
		JComboBox comboBox = new JComboBox(genderlist);
		comboBox.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		comboBox.setBounds(135, 182, 116, 23);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(shapelist);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\uD3C9\uBC94", "\uB9C8\uB984", "\uD1B5\uD1B5"}));
		comboBox_1.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		comboBox_1.setBounds(135, 231, 116, 23);
		panel.add(comboBox_1);
		
		
		
		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("휴먼편지체", Font.BOLD, 25));
		lblNewLabel.setBounds(40, 23, 130, 46);
		panel.add(lblNewLabel);
		
		JLabel lblId = new JLabel("\uD68C\uC6D0 ID");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		lblId.setBounds(59, 79, 64, 30);
		panel.add(lblId);
		
		JLabel lblPw = new JLabel("\uD68C\uC6D0 PW");
		lblPw.setForeground(Color.WHITE);
		lblPw.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		lblPw.setBounds(59, 125, 64, 30);
		panel.add(lblPw);
		
		아이디입력창 = new JTextField();
		아이디입력창.setBounds(135, 84, 183, 21);
		panel.add(아이디입력창);
		아이디입력창.setColumns(10);
		
		패스워드입력창 = new JTextField();
		패스워드입력창.setBounds(135, 130, 183, 21);
		panel.add(패스워드입력창);
		패스워드입력창.setColumns(10);
		
		JLabel lblPw_1 = new JLabel("\uC131\uBCC4");
		lblPw_1.setForeground(Color.WHITE);
		lblPw_1.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		lblPw_1.setBounds(59, 178, 64, 30);
		panel.add(lblPw_1);
		
		JLabel lblPw_1_1 = new JLabel("\uCCB4\uD615");
		lblPw_1_1.setForeground(Color.WHITE);
		lblPw_1_1.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		lblPw_1_1.setBounds(59, 227, 64, 30);
		panel.add(lblPw_1_1);
		
		JLabel lblEmail = new JLabel("E-mail \uC8FC\uC18C");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		lblEmail.setBounds(59, 279, 82, 30);
		panel.add(lblEmail);
		
		이메일입력창 = new JTextField();
		이메일입력창.setColumns(10);
		이메일입력창.setBounds(135, 284, 183, 21);
		panel.add(이메일입력창);
		
		JButton 결정 = new JButton("Sign Up");
		결정.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		결정.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				id2=아이디입력창.getText();
				pw2=패스워드입력창.getText();
				email2=이메일입력창.getText();
		
				
				Path 새로운파일생성 = Paths.get("./src/teamp2/program/"+id2);
				
				try {            // 디렉토리 생성            
					Files.createDirectory(새로운파일생성);             
					
					
					Path 옷 = Paths.get("./src/teamp2/program/"+id2+"/"+"cloth");
					Files.createDirectory(옷);
					
					if((comboBox.getSelectedItem().toString().equals("남자")))
						{
						Image Image = ImageIO.read(new File("./src/teamp2/program/profile1.png"));
						Image resizeImg = Image.getScaledInstance( 200, 200, Image.SCALE_SMOOTH);
						
						BufferedImage newImage = new BufferedImage( 200, 200, BufferedImage.TYPE_INT_RGB );
						Graphics g = newImage.getGraphics();
						g.drawImage(resizeImg, 0, 0, null);
						g.dispose();
						
						
						ImageIO.write(newImage, "png", new File("./src/teamp2/program/"+id2+"/profile.png"));
						}
												
						
						
					else if((comboBox.getSelectedItem().toString().equals("여자")))
					{
						Image Image = ImageIO.read(new File("./src/teamp2/program/profile2.png"));
						Image resizeImg = Image.getScaledInstance( 200, 200, Image.SCALE_SMOOTH);
						
						BufferedImage newImage = new BufferedImage( 200, 200, BufferedImage.TYPE_INT_RGB );
						Graphics g = newImage.getGraphics();
						g.drawImage(resizeImg, 0, 0, null);
						g.dispose();
						
						
						ImageIO.write(newImage, "png", new File("./src/teamp2/program/"+id2+"/profile.png"));
						}
					
					//옷에 대한 정보 하위 파일 생성 모자/상의/하의/바지
					
					Path 모자= Paths.get("./src/teamp2/program/"+id2+"/"+"cloth"+"/"+"1");			
					Files.createDirectory(모자); 
					Path 상의= Paths.get("./src/teamp2/program/"+id2+"/"+"cloth"+"/"+"2");
					Files.createDirectory(상의); 
					Path 하의= Paths.get("./src/teamp2/program/"+id2+"/"+"cloth"+"/"+"3");
					Files.createDirectory(하의); 
					Path 신발= Paths.get("./src/teamp2/program/"+id2+"/"+"cloth"+"/"+"4");
					Files.createDirectory(신발); 
					
					Path filePath1 = Paths.get("./src/teamp2/program/"+id2+"/"+"cloth"+"/"+"1"+"/"+"1.txt");
					Files.createFile(filePath1);
					Path filePath2 = Paths.get("./src/teamp2/program/"+id2+"/"+"cloth"+"/"+"2"+"/"+"2.txt");
					Files.createFile(filePath2);
					Path filePath3 = Paths.get("./src/teamp2/program/"+id2+"/"+"cloth"+"/"+"3"+"/"+"3.txt");
					Files.createFile(filePath3);
					Path filePath4 = Paths.get("./src/teamp2/program/"+id2+"/"+"cloth"+"/"+"4"+"/"+"4.txt");
					Files.createFile(filePath4);
					
					
					Path 월별입은옷정보 = Paths.get("./src/teamp2/program/"+id2+"/"+"date");
					Files.createDirectory(월별입은옷정보);
					//현재 날짜에 대한 하위 파일 생성 일단은 이번달
					
					LocalDate now = LocalDate.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM");
					String formatedNow = now.format(formatter);
					
					Path 월별입은옷정보txt = Paths.get("./src/teamp2/program/"+id2+"/"+"date"+"/"+formatedNow+".txt");
					Files.createFile(월별입은옷정보txt);
					
					
					메시지표시창 mas=new 메시지표시창(id2 + "님의 디렉토리가 생성되었습니다.");				
					//회원의 남성 여성 여부를 결정하는 프로필 작성///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					
					
					PrintWriter outputStream=null;
					//회원가입 정보를 데이터베이스에 저장
					   회원가입버튼클릭시 profile=new 회원가입버튼클릭시();
					
					
					
					} 
				catch (FileAlreadyExistsException e1) 
				{            
					
					JLabel lblNewLabel1 = new JLabel("동일한 ID 가 이미 존재합니다 다른 ID로 회원가입 진행해주세요");
					lblNewLabel1.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
					lblNewLabel1.setBounds(32, 74, 370, 112);
					frame.getContentPane().add(lblNewLabel1);
				} 
				catch (NoSuchFileException e1) 
				{            
					
					JLabel lblNewLabel1 = new JLabel("회원가입에서 오류 발생");
					lblNewLabel1.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
					lblNewLabel1.setBounds(32, 74, 370, 112);
					frame.getContentPane().add(lblNewLabel1);
				}
				catch (IOException e1) 
				{            
					e1.printStackTrace();        
					
				}
				어플홈화면 win= new 어플홈화면(아이디입력창.getText());
			}
		});
		결정.setBounds(118, 352, 125, 46);
		panel.add(결정);
		
		
		
		
		try(FileWriter fw = new FileWriter("./src/teamp2/program/login.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				
				gender=comboBox.getSelectedItem().toString();
				shape=comboBox_1.getSelectedItem().toString();
				
			    out.println(id2+" "+pw2+" "+gender+" "+shape+" "+email2);
			    
			    
			    
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		
		

		frame.setVisible(true);
		
		
		
	}

	
}