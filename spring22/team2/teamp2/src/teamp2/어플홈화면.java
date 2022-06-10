package teamp2;

import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class 어플홈화면 {

	private JFrame frame;
	private ImageIcon bg1 = null;
	private ImageIcon bg2 = null;
	private ImageIcon bg3 = null;
	private ImageIcon bg4 = null;
	private ImageIcon profile = null;
	private ImageIcon weather_icon = null;
	String decide_style;
	String[] newStr;
	String decide_모자;
	String decide_상의;
	String decide_하의;
	String decide_신발;
	
	double max_temp = 0;
	double min_temp = 0;
	double avg_temp = 0;
	String mainWeather;
	String wear_weather;
	/**
	 * Launch the application.
	 */
	
	public 어플홈화면(String ID_value) {
		
		openWeather new_weather=new openWeather();
		

		
		max_temp = new_weather.max_temp;
		min_temp = new_weather.min_temp;
		avg_temp = new_weather.avg_temp;
		mainWeather=new_weather.showMain_weather();
		wear_weather=new_weather.showWeather(avg_temp);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1400, 890);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("메인 화면");
		
		ImageIcon icon=new ImageIcon("./src/teamp2/program/background.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(1400, 890, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		//메인패널///////////////////////////////////////////////////////////////////////////////
		JPanel panel = new JPanel();
		panel.setBackground(new Color(239, 228, 176));
		panel.setBounds(0, 0, 1400, 890);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel decide_done_panel = new JPanel();
		decide_done_panel.setBounds(12, 717, 448, 122);
		panel.add(decide_done_panel);
		decide_done_panel.setLayout(null);
		decide_done_panel.setVisible(false);
		
		JLabel decide_done_label = new JLabel(" ");
		decide_done_label.setHorizontalAlignment(SwingConstants.CENTER);
		decide_done_label.setFont(new Font("휴먼편지체", Font.PLAIN, 30));
		decide_done_label.setBounds(12, 10, 424, 45);
		decide_done_panel.add(decide_done_label);
		
		JLabel decide_done_label_1 = new JLabel("");
		decide_done_label_1.setHorizontalAlignment(SwingConstants.CENTER);
		decide_done_label_1.setFont(new Font("휴먼편지체", Font.PLAIN, 30));
		decide_done_label_1.setBounds(12, 67, 424, 45);
		decide_done_panel.add(decide_done_label_1);
		
		
		JPanel 옷선택패널 = new JPanel();
		옷선택패널.setBackground(Color.WHITE);
		옷선택패널.setBounds(12, 165, 448, 542);
		panel.add(옷선택패널);
		옷선택패널.setLayout(null);
		
		JPanel 데일리포멀패널 = new JPanel();
		데일리포멀패널.setBackground(Color.WHITE);
		데일리포멀패널.setBounds(12, 10, 424, 522);
		옷선택패널.add(데일리포멀패널);
		데일리포멀패널.setLayout(null);
		
		둥근버튼 데일리버튼 = new 둥근버튼("데일리");
		데일리버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		데일리버튼.setBounds(12, 441, 191, 71);
		데일리포멀패널.add(데일리버튼);
		
		둥근버튼 포멀버튼 = new 둥근버튼("포멀");
		포멀버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		포멀버튼.setBounds(221, 441, 191, 71);
		데일리포멀패널.add(포멀버튼);
		
		JPanel 캐주얼로맨틱패널 = new JPanel();
		캐주얼로맨틱패널.setBackground(Color.WHITE);
		캐주얼로맨틱패널.setBounds(12, 10, 424, 522);
		옷선택패널.add(캐주얼로맨틱패널);
		캐주얼로맨틱패널.setLayout(null);
		
		둥근버튼 캐주얼버튼 = new 둥근버튼("캐주얼");
		캐주얼버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		캐주얼버튼.setBounds(12, 441, 191, 71);
		캐주얼로맨틱패널.add(캐주얼버튼);
		
		둥근버튼 로맨틱버튼 = new 둥근버튼("로맨틱");
		로맨틱버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		로맨틱버튼.setBounds(221, 441, 191, 71);
		캐주얼로맨틱패널.add(로맨틱버튼);
		
		JPanel 출근하객패널 = new JPanel();
		출근하객패널.setBackground(Color.WHITE);
		출근하객패널.setBounds(12, 10, 424, 522);
		옷선택패널.add(출근하객패널);
		출근하객패널.setLayout(null);
		
		둥근버튼 출근버튼 = new 둥근버튼("출근");
		출근버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		출근버튼.setBounds(12, 441, 191, 71);
		출근하객패널.add(출근버튼);
		
		둥근버튼 하객버튼 = new 둥근버튼("하객");
		하객버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		하객버튼.setBounds(221, 441, 191, 71);
		출근하객패널.add(하객버튼);
		
		옷선택패널.setVisible(false);
		데일리포멀패널.setVisible(false);
		캐주얼로맨틱패널.setVisible(false);
		출근하객패널.setVisible(false);
		
		JPanel 옷표시패널 = new JPanel();
		옷표시패널.setBackground(Color.white);		
		옷표시패널.setBounds(12, 165, 448, 542);		
		panel.add(옷표시패널);
		옷표시패널.setLayout(null);
		옷표시패널.setVisible(true);
		
		
		
		//옷표시 패널쪽에 보일 옷 구성요소 패널들	///////////////////////////////////////////////////////////////////////////////
		
		JPanel 신발표시패널 = new JPanel() //신발 이미지 띄울 패널
				{
			    public void paintComponent(Graphics g) 
			    {
			     g.drawImage(bg4.getImage(), 30, 210, 150, 150, null);
			    }
			   };
			신발표시패널.setBounds(12, 210, 424, 382);
			옷표시패널.add(신발표시패널);
			신발표시패널.setLayout(null);
			신발표시패널.setVisible(false);
					
				JPanel 모자표시패널 = new JPanel()
				{
				    public void paintComponent(Graphics g) 
				    {
				     g.drawImage(bg1.getImage(), 30, 0, 150, 150, null);
				    }
				   }; //모자 이미지 띄울 패널
				
				모자표시패널.setBounds(12, -20, 424, 402);
				옷표시패널.add(모자표시패널);
				모자표시패널.setLayout(null);
				모자표시패널.setVisible(false);
				
				JPanel 상의표시패널 = new JPanel() //상의 이미지 띄울 패널
				   {
				    public void paintComponent(Graphics g) 
				    {
				     g.drawImage(bg2.getImage(), 80, 0, 260, 250, null);
				    }
				   };
				상의표시패널.setBounds(12, 50, 424, 382);
				옷표시패널.add(상의표시패널);
				상의표시패널.setLayout(null);
				상의표시패널.setVisible(false);
				
				JPanel 하의표시패널 = new JPanel() //하의 이미지 띄울 패널
					{
				    public void paintComponent(Graphics g) 
				    {
				     g.drawImage(bg3.getImage(), 115, 0, 200, 300, null);
				    }
				   };
				하의표시패널.setBounds(12, 230, 424, 382);
				옷표시패널.add(하의표시패널);
				하의표시패널.setLayout(null);
				하의표시패널.setVisible(false);
				
				
				
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
		
		데일리버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				데일리포멀패널.setVisible(false);
				캐주얼로맨틱패널.setVisible(true);
			}
		});
		포멀버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				데일리포멀패널.setVisible(false);
				출근하객패널.setVisible(true);
			}
		});
		캐주얼버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				캐주얼로맨틱패널.setVisible(false);
				옷선택패널.setVisible(false);
				옷표시패널.setVisible(true);
				decide_style="캐주얼";
				
				System.out.println(decide_style);
				
				Random random = new Random();
		        random.setSeed(System.currentTimeMillis());
		        
		        //신발결정/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		        
		        FileReader readFile1;		
				BufferedReader br1;	
				String getLine1;	
				int i1=0;
				String[] 조건부합모자모음= new String[20];
				try {
					readFile1 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/1/1.txt");// 로그인 정보 저장된 파일경로			  
					br1 = new BufferedReader(readFile1);
					String resultvalue1 = "";
				
					while((getLine1 = br1.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine1.contains(decide_style) && getLine1.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue1 = getLine1;
							String[] newStr1 = resultvalue1.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합모자모음[i1++]=newStr1[0];
						}
						
					}
					int ran1=random.nextInt(i1);
					
					decide_모자=조건부합모자모음[ran1];
					String 모자="./src/teamp2/program/"+ID_value+"/cloth/1/"+decide_모자+".png";
					bg1 = new ImageIcon(모자);
					모자표시패널.setVisible(true);
				
				
				} 
				
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
		        
		        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
		       	//상의 결정/////////////////////////////////////////////////////////////////////////////////////////////////////
		        
		        FileReader readFile2;		
				BufferedReader br2;	
				String getLine2;	
				int i2=0;
				String[] 조건부합상의모음= new String[20];
				try {
					readFile2 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/2/2.txt");// 로그인 정보 저장된 파일경로			  
					br2 = new BufferedReader(readFile2);
					String resultvalue2 = "";
				
					while((getLine2 = br2.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine2.contains(decide_style) && getLine2.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue2 = getLine2;
							String[] newStr2 = resultvalue2.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합상의모음[i2++]=newStr2[0];
							
							
						}
						
						
					}
					int ran2=random.nextInt(i2);
					CostumeCalendar c = new CostumeCalendar(LocalDate.now(), ID_value);
					while(조건부합상의모음[ran2].equals(c.similar(ID_value).get(0).top) || 조건부합상의모음[ran2].equals(c.similar(ID_value).get(1).top) || 조건부합상의모음[ran2].equals(c.similar(ID_value).get(2).top))
						ran2=random.nextInt(i2);
					for(int j2=0;j2<i2;j2++)
					{
						System.out.println(조건부합상의모음[j2]);
						
					}
					System.out.println(ran2);
					System.out.println(i2);
					
					decide_상의=조건부합상의모음[ran2];
					String 상의="./src/teamp2/program/"+ID_value+"/cloth/2/"+decide_상의+".png";
					bg2 = new ImageIcon(상의);
					상의표시패널.setVisible(true);
					
				
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
		        
		        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//하의 결정/////////////////////////////////////////////////////////////////////////////////////////////////////
		        
		        FileReader readFile3;		
				BufferedReader br3;	
				String getLine3;	
				int i3=0;
				String[] 조건부합하의모음= new String[20];
				try {
					readFile3 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/3/3.txt");// 로그인 정보 저장된 파일경로			  
					br3 = new BufferedReader(readFile3);
					String resultvalue3 = "";
				
					while((getLine3 = br3.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine3.contains(decide_style) && getLine3.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue3 = getLine3;
							String[] newStr3 = resultvalue3.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합하의모음[i3++]=newStr3[0];
							
							
						}
						
						
					}
					int ran3=random.nextInt(i3);
					CostumeCalendar c = new CostumeCalendar(LocalDate.now(), ID_value);
					while(조건부합하의모음[ran3].equals(c.similar(ID_value).get(0).bottoms) || 조건부합하의모음[ran3].equals(c.similar(ID_value).get(1).bottoms) || 조건부합하의모음[ran3].equals(c.similar(ID_value).get(2).bottoms))
						ran3=random.nextInt(i3);
					for(int j3=0;j3<i3;j3++)
					{
						System.out.println(조건부합하의모음[j3]);
						
					}
					System.out.println(ran3);
					System.out.println(i3);
					
					decide_하의=조건부합하의모음[ran3];
					String 하의="./src/teamp2/program/"+ID_value+"/cloth/3/"+decide_하의+".png";
					bg3 = new ImageIcon(하의);
					하의표시패널.setVisible(true);
				
				}
					catch (FileNotFoundException e1) 
					{
						e1.printStackTrace();
					} catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				//신발결정/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		        
		        FileReader readFile4;		
				BufferedReader br4;	
				String getLine4;	
				int i4=0;
				String[] 조건부합신발모음= new String[20];
				try {
					readFile4 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/4/4.txt");// 로그인 정보 저장된 파일경로			  
					br4 = new BufferedReader(readFile4);
					String resultvalue4 = "";
				
					while((getLine4 = br4.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine4.contains(decide_style) && getLine4.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue4 = getLine4;
							String[] newStr4 = resultvalue4.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합신발모음[i4++]=newStr4[0];
						}
						
					}
					int ran4=random.nextInt(i4);
					
					decide_신발=조건부합신발모음[ran4];
					String 신발="./src/teamp2/program/"+ID_value+"/cloth/4/"+decide_신발+".png";
					bg4 = new ImageIcon(신발);
					신발표시패널.setVisible(true);
				
				
				} 
				
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
		        
		        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
				

			}
		});
		로맨틱버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				캐주얼로맨틱패널.setVisible(false);
				옷선택패널.setVisible(false);
				옷표시패널.setVisible(true);
				decide_style="로맨틱";
				
				System.out.println(decide_style);
				
				Random random = new Random();
		        random.setSeed(System.currentTimeMillis());
		        
		        //모자결정/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		        FileReader readFile1;		
				BufferedReader br1;	
				String getLine1;	
				int i1=0;
				String[] 조건부합모자모음= new String[20];
				try {
					readFile1 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/1/1.txt");// 로그인 정보 저장된 파일경로			  
					br1 = new BufferedReader(readFile1);
					String resultvalue1 = "";
				
					while((getLine1 = br1.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine1.contains(decide_style) && getLine1.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue1 = getLine1;
							String[] newStr1 = resultvalue1.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합모자모음[i1++]=newStr1[0];
						}
						
					}
					int ran1=random.nextInt(i1);
					
					decide_모자=조건부합모자모음[ran1];
					String 모자="./src/teamp2/program/"+ID_value+"/cloth/1/"+decide_모자+".png";
					bg1 = new ImageIcon(모자);
					모자표시패널.setVisible(true);
				
				
				} 
				
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
		        
		       	//상의 결정/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		        
		        FileReader readFile2;		
				BufferedReader br2;	
				String getLine2;	
				int i2=0;
				String[] 조건부합상의모음= new String[20];
				try {
					readFile2 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/2/2.txt");// 로그인 정보 저장된 파일경로			  
					br2 = new BufferedReader(readFile2);
					String resultvalue2 = "";
				
					while((getLine2 = br2.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine2.contains(decide_style) && getLine2.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue2 = getLine2;
							String[] newStr2 = resultvalue2.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합상의모음[i2++]=newStr2[0];
							
							
						}
						
						
					}
					int ran2=random.nextInt(i2);
					CostumeCalendar c = new CostumeCalendar(LocalDate.now(), ID_value);
					while(조건부합상의모음[ran2].equals(c.similar(ID_value).get(0).top) || 조건부합상의모음[ran2].equals(c.similar(ID_value).get(1).top) || 조건부합상의모음[ran2].equals(c.similar(ID_value).get(2).top))
						ran2=random.nextInt(i2);
					for(int j2=0;j2<i2;j2++)
					{
						System.out.println(조건부합상의모음[j2]);
						
					}
					System.out.println(ran2);
					System.out.println(i2);
					
					decide_상의=조건부합상의모음[ran2];
					String 상의="./src/teamp2/program/"+ID_value+"/cloth/2/"+decide_상의+".png";
					bg2 = new ImageIcon(상의);
					상의표시패널.setVisible(true);
					
				
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				//하의 결정/////////////////////////////////////////////////////////////////////////////////////////////////////
		        
		        FileReader readFile3;		
				BufferedReader br3;	
				String getLine3;	
				int i3=0;
				String[] 조건부합하의모음= new String[20];
				try {
					readFile3 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/3/3.txt");// 로그인 정보 저장된 파일경로			  
					br3 = new BufferedReader(readFile3);
					String resultvalue3 = "";
				
					while((getLine3 = br3.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine3.contains(decide_style) && getLine3.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue3 = getLine3;
							String[] newStr3 = resultvalue3.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합하의모음[i3++]=newStr3[0];
							
							
						}
						
						
					}
					int ran3=random.nextInt(i3);
					CostumeCalendar c = new CostumeCalendar(LocalDate.now(), ID_value);
					while(조건부합하의모음[ran3].equals(c.similar(ID_value).get(0).bottoms) || 조건부합하의모음[ran3].equals(c.similar(ID_value).get(1).bottoms) || 조건부합하의모음[ran3].equals(c.similar(ID_value).get(2).bottoms))
						ran3=random.nextInt(i3);
					for(int j3=0;j3<i3;j3++)
					{
						System.out.println(조건부합하의모음[j3]);
						
					}
					System.out.println(ran3);
					System.out.println(i3);
					
					decide_하의=조건부합하의모음[ran3];
					String 하의="./src/teamp2/program/"+ID_value+"/cloth/3/"+decide_하의+".png";
					bg3 = new ImageIcon(하의);
					하의표시패널.setVisible(true);
				
				}
					catch (FileNotFoundException e1) 
					{
						e1.printStackTrace();
					} catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				//신발결정/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		        
		        FileReader readFile4;		
				BufferedReader br4;	
				String getLine4;	
				int i4=0;
				String[] 조건부합신발모음= new String[20];
				try {
					readFile4 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/4/4.txt");// 로그인 정보 저장된 파일경로			  
					br4 = new BufferedReader(readFile4);
					String resultvalue4 = "";
				
					while((getLine4 = br4.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine4.contains(decide_style) && getLine4.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue4 = getLine4;
							String[] newStr4 = resultvalue4.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합신발모음[i4++]=newStr4[0];
						}
						
					}
					int ran4=random.nextInt(i4);
					
					decide_신발=조건부합신발모음[ran4];
					String 신발="./src/teamp2/program/"+ID_value+"/cloth/4/"+decide_신발+".png";
					bg4 = new ImageIcon(신발);
					신발표시패널.setVisible(true);
				
				
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
		출근버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				출근하객패널.setVisible(false);
				옷선택패널.setVisible(false);
				옷표시패널.setVisible(true);
				decide_style="출근";
				
				System.out.println(decide_style);
				
				Random random = new Random();
		        random.setSeed(System.currentTimeMillis());
		        
		        //누가회사출근하느데 모자쓰고감? 모자부분 삭제/
		        
		       	//상의 결정/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		        
		        FileReader readFile2;		
				BufferedReader br2;	
				String getLine2;	
				int i2=0;
				String[] 조건부합상의모음= new String[20];
				try {
					readFile2 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/2/2.txt");// 로그인 정보 저장된 파일경로			  
					br2 = new BufferedReader(readFile2);
					String resultvalue2 = "";
				
					while((getLine2 = br2.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine2.contains(decide_style) && getLine2.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue2 = getLine2;
							String[] newStr2 = resultvalue2.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합상의모음[i2++]=newStr2[0];
							
							
						}
						
						
					}
					int ran2=random.nextInt(i2);
					CostumeCalendar c = new CostumeCalendar(LocalDate.now(), ID_value);
					while(조건부합상의모음[ran2].equals(c.similar(ID_value).get(0).top) || 조건부합상의모음[ran2].equals(c.similar(ID_value).get(1).top) || 조건부합상의모음[ran2].equals(c.similar(ID_value).get(2).top))
						ran2=random.nextInt(i2);
					for(int j2=0;j2<i2;j2++)
					{
						System.out.println(조건부합상의모음[j2]);
						
					}
					System.out.println(ran2);
					System.out.println(i2);
					
					decide_상의=조건부합상의모음[ran2];
					String 상의="./src/teamp2/program/"+ID_value+"/cloth/2/"+decide_상의+".png";
					bg2 = new ImageIcon(상의);
					상의표시패널.setVisible(true);
					
				
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				//하의 결정/////////////////////////////////////////////////////////////////////////////////////////////////////
		        
		        FileReader readFile3;		
				BufferedReader br3;	
				String getLine3;	
				int i3=0;
				String[] 조건부합하의모음= new String[20];
				try {
					readFile3 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/3/3.txt");// 로그인 정보 저장된 파일경로			  
					br3 = new BufferedReader(readFile3);
					String resultvalue3 = "";
				
					while((getLine3 = br3.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine3.contains(decide_style) && getLine3.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue3 = getLine3;
							String[] newStr3 = resultvalue3.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합하의모음[i3++]=newStr3[0];
							
							
						}
						
						
					}
					int ran3=random.nextInt(i3);
					CostumeCalendar c = new CostumeCalendar(LocalDate.now(), ID_value);
					while(조건부합하의모음[ran3].equals(c.similar(ID_value).get(0).bottoms) || 조건부합하의모음[ran3].equals(c.similar(ID_value).get(1).bottoms) || 조건부합하의모음[ran3].equals(c.similar(ID_value).get(2).bottoms))
						ran3=random.nextInt(i3);
					for(int j3=0;j3<i3;j3++)
					{
						System.out.println(조건부합하의모음[j3]);
						
					}
					System.out.println(ran3);
					System.out.println(i3);
					
					decide_하의=조건부합하의모음[ran3];
					String 하의="./src/teamp2/program/"+ID_value+"/cloth/3/"+decide_하의+".png";
					bg3 = new ImageIcon(하의);
					하의표시패널.setVisible(true);
				
				}
					catch (FileNotFoundException e1) 
					{
						e1.printStackTrace();
					} catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				//신발결정/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		        
		        FileReader readFile4;		
				BufferedReader br4;	
				String getLine4;	
				int i4=0;
				String[] 조건부합신발모음= new String[20];
				try {
					readFile4 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/4/4.txt");// 로그인 정보 저장된 파일경로			  
					br4 = new BufferedReader(readFile4);
					String resultvalue4 = "";
				
					while((getLine4 = br4.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine4.contains(decide_style) && getLine4.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue4 = getLine4;
							String[] newStr4 = resultvalue4.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합신발모음[i4++]=newStr4[0];
						}
						
					}
					int ran4=random.nextInt(i4);
					
					decide_신발=조건부합신발모음[ran4];
					String 신발="./src/teamp2/program/"+ID_value+"/cloth/4/"+decide_신발+".png";
					bg4 = new ImageIcon(신발);
					신발표시패널.setVisible(true);
				
				
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
		하객버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				출근하객패널.setVisible(false);
				옷선택패널.setVisible(false);
				옷표시패널.setVisible(true);
				decide_style="하객";
				
				System.out.println(decide_style);
				
				Random random = new Random();
		        random.setSeed(System.currentTimeMillis());
		        
		        //누가결혼식에 모자쓰고감?// 모자 부분 삭제
		        
		       	//상의 결정/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		        
		        FileReader readFile2;		
				BufferedReader br2;	
				String getLine2;	
				int i2=0;
				String[] 조건부합상의모음= new String[20];
				try {
					readFile2 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/2/2.txt");// 로그인 정보 저장된 파일경로			  
					br2 = new BufferedReader(readFile2);
					String resultvalue2 = "";
				
					while((getLine2 = br2.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine2.contains(decide_style) && getLine2.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue2 = getLine2;
							String[] newStr2 = resultvalue2.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합상의모음[i2++]=newStr2[0];
							
							
						}
						
						
					}
					int ran2=random.nextInt(i2);
					CostumeCalendar c = new CostumeCalendar(LocalDate.now(), ID_value);
					while(조건부합상의모음[ran2].equals(c.similar(ID_value).get(0).top) || 조건부합상의모음[ran2].equals(c.similar(ID_value).get(1).top) || 조건부합상의모음[ran2].equals(c.similar(ID_value).get(2).top))
						ran2=random.nextInt(i2);
					for(int j2=0;j2<i2;j2++)
					{
						System.out.println(조건부합상의모음[j2]);
						
					}
					System.out.println(ran2);
					System.out.println(i2);
					
					decide_상의=조건부합상의모음[ran2];
					String 상의="./src/teamp2/program/"+ID_value+"/cloth/2/"+decide_상의+".png";
					bg2 = new ImageIcon(상의);
					상의표시패널.setVisible(true);
					
				
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				//하의 결정/////////////////////////////////////////////////////////////////////////////////////////////////////
		        
		        FileReader readFile3;		
				BufferedReader br3;	
				String getLine3;	
				int i3=0;
				String[] 조건부합하의모음= new String[20];
				try {
					readFile3 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/3/3.txt");// 로그인 정보 저장된 파일경로			  
					br3 = new BufferedReader(readFile3);
					String resultvalue3 = "";
				
					while((getLine3 = br3.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine3.contains(decide_style) && getLine3.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue3 = getLine3;
							String[] newStr3 = resultvalue3.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합하의모음[i3++]=newStr3[0];
							
							
						}
						
						
					}
					int ran3=random.nextInt(i3);
					CostumeCalendar c = new CostumeCalendar(LocalDate.now(), ID_value);
					while(조건부합하의모음[ran3].equals(c.similar(ID_value).get(0).bottoms) || 조건부합하의모음[ran3].equals(c.similar(ID_value).get(1).bottoms) || 조건부합하의모음[ran3].equals(c.similar(ID_value).get(2).bottoms))
						ran3=random.nextInt(i3);
					for(int j3=0;j3<i3;j3++)
					{
						System.out.println(조건부합하의모음[j3]);
						
					}
					System.out.println(ran3);
					System.out.println(i3);
					
					decide_하의=조건부합하의모음[ran3];
					String 하의="./src/teamp2/program/"+ID_value+"/cloth/3/"+decide_하의+".png";
					bg3 = new ImageIcon(하의);
					하의표시패널.setVisible(true);
				
				}
					catch (FileNotFoundException e1) 
					{
						e1.printStackTrace();
					} catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				//신발결정/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		        
		        FileReader readFile4;		
				BufferedReader br4;	
				String getLine4;	
				int i4=0;
				String[] 조건부합신발모음= new String[20];
				try {
					readFile4 = new FileReader("./src/teamp2/program/"+ID_value+"/cloth/4/4.txt");// 로그인 정보 저장된 파일경로			  
					br4 = new BufferedReader(readFile4);
					String resultvalue4 = "";
				
					while((getLine4 = br4.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						
						
						if(getLine4.contains(decide_style) && getLine4.contains(wear_weather))// txt파일 값중 ID_value 로 시작하는 줄 찾아서 저장.	
						{					
							resultvalue4 = getLine4;
							String[] newStr4 = resultvalue4.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 아이디값 뒤쪽은 비밀번호값
							조건부합신발모음[i4++]=newStr4[0];
						}
						
					}
					int ran4=random.nextInt(i4);
					
					decide_신발=조건부합신발모음[ran4];
					String 신발="./src/teamp2/program/"+ID_value+"/cloth/4/"+decide_신발+".png";
					bg4 = new ImageIcon(신발);
					신발표시패널.setVisible(true);
				
				
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
		
		
		
		JPanel 사용자정보패널 = new JPanel();
		사용자정보패널.setBounds(12, 20, 717, 135);
		사용자정보패널.setBackground(Color.white);
		panel.add(사용자정보패널);
		사용자정보패널.setLayout(null);	
		
		ImageIcon icon2=new ImageIcon("./src/teamp2/program/"+ID_value+"/profile.png");
		Image img2 = icon2.getImage();
		Image changeImg2 = img2.getScaledInstance(115, 115, Image.SCALE_SMOOTH);
		ImageIcon changeIcon2 = new ImageIcon(changeImg2);
		
		JPanel 프로필표시패널 = new JPanel(){
			public void paintComponent(Graphics g) {
		
			g.drawImage(changeIcon2.getImage(), 0, 0, null);
            
            setOpaque(false); //그림을 표시하게 설정,투명하게 조절
            super.paintComponent(g);
			}
		};
		프로필표시패널.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});;
		프로필표시패널.setBackground(Color.CYAN);
		프로필표시패널.setBounds(12, 10, 115, 115);
		사용자정보패널.add(프로필표시패널);
		프로필표시패널.setLayout(null);
		프로필표시패널.setVisible(true);
				   
		
		
		JLabel 사용자아이디표시라벨 = new JLabel("   "+ID_value+"님 환영합니다");
		사용자아이디표시라벨.setFont(new Font("휴먼편지체", Font.PLAIN, 50));
		사용자아이디표시라벨.setBounds(153, 10, 552, 115);
		사용자정보패널.add(사용자아이디표시라벨);
		
			
		
		
		둥근버튼 옷생성버튼 = new 둥근버튼("오늘 입을 옷 생성하기");
		옷생성버튼.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				옷표시패널.setVisible(false);
				모자표시패널.setVisible(false);
				상의표시패널.setVisible(false);
				하의표시패널.setVisible(false);
				신발표시패널.setVisible(false);
				
				
				옷선택패널.setVisible(true);
				데일리포멀패널.setVisible(true);
				///////////////////////////////////////////////////////////////////////////////////////////
				//여기까지 정상
				
				
				  
			}
				
			
			
		});
		
		옷생성버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		옷생성버튼.setBounds(12, 717, 448, 122);
		panel.add(옷생성버튼);
		
		JPanel 우측패널 = new JPanel();
		우측패널.setBackground(Color.white);
		우측패널.setBounds(741, 20, 630, 819);
		panel.add(우측패널);
		우측패널.setLayout(null);
		
		둥근버튼 새로운옷등록버튼 = new 둥근버튼("새로운 옷 등록");
		새로운옷등록버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  EventQueue.invokeLater(new Runnable() {
					   public void run() {
					    try {
					     새로운옷등록클릭시 frame = new 새로운옷등록클릭시(ID_value,"./src/teamp2/program/",1);
					     frame.setVisible(true);
					    } catch (Exception e) {
					     e.printStackTrace();
					    }
					   }
					  });
			}
		});
		새로운옷등록버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		새로운옷등록버튼.setBounds(12, 159, 606, 122);
		우측패널.add(새로운옷등록버튼);
		
		둥근버튼 캘린터다이어리버튼 = new 둥근버튼("캘린더/다이어리");
		캘린터다이어리버튼.addActionListener(new ActionListener()// 캘린더 버튼 누를시 새로운 캘린더 생성
		{
			public void actionPerformed(ActionEvent e) {
				LocalDate now = LocalDate.now();
				CostumeCalendar gui = new CostumeCalendar(now,ID_value);
				gui.setVisible(true);
			}
		});
		캘린터다이어리버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		캘린터다이어리버튼.setBounds(12, 423, 606, 122);
		우측패널.add(캘린터다이어리버튼);
		
		둥근버튼 설정버튼 = new 둥근버튼("\uAC1C\uC778\uC124\uC815");
		설정버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				개인설정 setting=new 개인설정(ID_value);
				
			}
		});
		설정버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		설정버튼.setBounds(12, 555, 606, 122);
		우측패널.add(설정버튼);
		
		둥근버튼 프로그램종료버튼 = new 둥근버튼("\uD504\uB85C\uADF8\uB7A8 \uC885\uB8CC");
		프로그램종료버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				///프로그램 종료
			}
		});
		프로그램종료버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		프로그램종료버튼.setBounds(12, 687, 606, 122);
		우측패널.add(프로그램종료버튼);
		
		둥근버튼 cloth_buy_button = new 둥근버튼("캘린더/다이어리");
		cloth_buy_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Desktop.getDesktop().browse(new URI("https://www.musinsa.com/app/"));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
		
			}
		});
		cloth_buy_button.setText("새로운 옷 사러가기");
		cloth_buy_button.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		cloth_buy_button.setBounds(12, 291, 606, 122);
		우측패널.add(cloth_buy_button);
		
		JPanel 날씨패널 = new JPanel();
		날씨패널.setBackground(Color.white);
		날씨패널.setBounds(472, 165, 257, 542);
		날씨패널.setLayout(null);
		
		LocalDate today_date=LocalDate.now();
		
		JLabel date_label = new JLabel("날짜 "+today_date.toString());
		date_label.setBounds(0, 43, 257, 48);
		date_label.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		date_label.setHorizontalAlignment(SwingConstants.CENTER);
		날씨패널.add(date_label);
		
		
		
		
		
		JLabel max_temp_label = new JLabel("최고 기온 : "+(int) Math.round(max_temp)+"°C");
		max_temp_label.setBounds(0, 133, 257, 48);
		max_temp_label.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		max_temp_label.setHorizontalAlignment(SwingConstants.CENTER);
		날씨패널.add(max_temp_label);
		
		JLabel min_temp_label = new JLabel("최저 기온 : "+(int) Math.round(min_temp)+"°C");
		min_temp_label.setBounds(0, 173, 257, 48);
		min_temp_label.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		min_temp_label.setHorizontalAlignment(SwingConstants.CENTER);
		날씨패널.add(min_temp_label);
		
		JLabel avg_temp_label = new JLabel("평균 기온 : "+(int) Math.round(avg_temp)+"°C");
		avg_temp_label.setBounds(0, 215, 257, 48);
		avg_temp_label.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		avg_temp_label.setHorizontalAlignment(SwingConstants.CENTER);
		날씨패널.add(avg_temp_label);
		
		
		String  weather_address="./src/teamp2/program/weather/Sun.png";
		String  warning="맑고 화창해요";
		if(mainWeather.equals("Rain"))
		{
			warning="비가 와요. 우산 챙기세요!";
			weather_address="./src/teamp2/program/weather/Rain.png";
		}
		else if(mainWeather.equals("Clouds"))
		{
			warning="흐린 날씨에요";
			weather_address="./src/teamp2/program/weather/Clouds.png";
		}
		else if(mainWeather.equals("Snow"))
		{
			warning="눈이 와요 여벌 코트를 챙겨요";
			weather_address="./src/teamp2/program/weather/Snow.png";
		}
		
		ImageIcon weathericon=new ImageIcon(weather_address);
		Image weatherimg = weathericon.getImage();
		Image weatherchangeImg = weatherimg.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		weather_icon = new ImageIcon(weatherchangeImg);
		
		
		JLabel weather_warning_inform = new JLabel(warning);
		weather_warning_inform.setBounds(0, 483, 257, 58);
		weather_warning_inform.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		weather_warning_inform.setHorizontalAlignment(SwingConstants.CENTER);
		날씨패널.add(weather_warning_inform);
		panel.add(날씨패널);
		날씨패널.setBackground(Color.white);
		날씨패널.setBounds(472, 165, 257, 542);
		panel.add(날씨패널);
		
		JPanel weather_icon_panel = new JPanel() {
			
			public void paintComponent(Graphics g) 
		    {
		     g.drawImage(weather_icon.getImage(), 0, 0, 200, 200, null);
		    }
			
		};
		weather_icon_panel.setBackground(Color.WHITE);
		weather_icon_panel.setBounds(26, 273, 200, 200);
		날씨패널.add(weather_icon_panel);
		weather_icon_panel.setLayout(null);
		
		둥근버튼 결정버튼 = new 둥근버튼("결정하기");
		
		결정버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				decide_done_panel.setVisible(true);
				
				FileReader readFile;		
				BufferedReader br;	
				String getLine;
				String resultvalue = "";
				String correction="";
				

				
				int year = today_date.getYear();
				int month = today_date.getMonthValue();
				int day=today_date.getDayOfMonth();
				
				
				
				decide_done_label.setText(today_date+"옷 생성완료!");
				decide_done_label_1.setText(decide_모자+" "+decide_상의+" "+decide_하의+" "+decide_신발);
				
				try {
					readFile = new FileReader("./src/teamp2/program/"+ID_value+"/date/"+year+"_"+month+".txt");// 그 날짜에 입은 저장된 파일경로			  
					br = new BufferedReader(readFile);
					resultvalue = "";
				
					while((getLine = br.readLine()) != null)
					{		// 한줄씩 읽기 (마지막줄 체크)     					
						String[] temp_str=getLine.split("\\s+");
						String temp=temp_str[0];
						
						
						if(Integer.toString(day).equals(temp))// txt파일 값중 day값이랑 일치하는 줄 찾아서 저장.	
						{	
							System.out.println(day+" "+temp);
							resultvalue = getLine; //원본문자열
							newStr = resultvalue.split("\\s+");//저장된 값을 공백으로 구분 앞쪽은 날짜 뒤쪽은 그날 입었던 옷의 정보
							newStr[1]=decide_모자;
							newStr[2]=decide_상의;
							newStr[3]=decide_하의;
							newStr[4]=decide_신발;
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
					BufferedReader br1 = new BufferedReader(new FileReader(new File("./src/teamp2/program/"+ID_value+"/date/"+year+"_"+month+".txt")));
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/teamp2/program/"+ID_value+"/date/"+year+"_"+month+"(1).txt")));
					
					while((result = br1.readLine()) != null) {
						//
						String[] temp_result=result.split("\\s+");
						
						if(newStr[0].equals(temp_result[0])) {
							result = result.replace(resultvalue, correction);
							bw.write(result+"\n");
							bw.flush();							
						}
						else
						{
							bw.write(result+"\n");
							bw.flush();	
						}
						
						
					}
					bw.close();
					br1.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				File file3 = new File("./src/teamp2/program/"+ID_value+"/date/"+year+"_"+month+".txt");
		        
		    	if( file3.exists() )
		    	{
		    		file3.delete();	
		    	}
		        
		    	String result1="";
		    	
		    	
				try {
					BufferedReader br1 = new BufferedReader(new FileReader(new File("./src/teamp2/program/"+ID_value+"/date/"+year+"_"+month+"(1).txt")));
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/teamp2/program/"+ID_value+"/date/"+year+"_"+month+".txt")));

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
			
				File file2 = new File("./src/teamp2/program/"+ID_value+"/date/"+year+"_"+month+"(1).txt");
		        
		    	if( file2.exists() )
		    	{
		    		file2.delete();	
		    	}
				
				
				
				
			}
		});
		
		결정버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		결정버튼.setBounds(472, 717, 257, 122);
		panel.add(결정버튼);
		frame.setVisible(true);
		
		
		}
	}
	
	
	

