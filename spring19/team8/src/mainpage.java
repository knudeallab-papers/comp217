import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JCheckBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;

public class mainpage extends JFrame {
	static excelRead er=null;
	String myId="";
	public static void main(String[] args) {
		er=new excelRead();
		er.themeRead();
		ex1 ex=new ex1();
	}
	
	// 메인 창
	public mainpage() {}
	public mainpage(String id, String password) {
		myId=id;
		setSize(1200,1000);
		setTitle("knu 시간표 마법사 메인화면");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		addWindowListener(new CheckonExit());
		
		JMenu searchMenu=new JMenu("검색");
		JMenuItem search=new JMenuItem("검색하기");
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchCourse s = new searchCourse();
				s.setVisible(true);
			}
		});
		searchMenu.add(search);
		
		JMenu tableMenu=new JMenu("시간표");
		JMenu myTable=new JMenu("내 시간표");
		
		JMenuItem lookingmyTable=new JMenuItem("내 시간표 보기");
		lookingmyTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				mytable m=new mytable();
				m.setVisible(true);
			}
			
		});
		myTable.add(lookingmyTable);
		
		JMenuItem makingmyTable=new JMenuItem("시간표 추가하기");
		makingmyTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchCourse s=new searchCourse();
				s.setVisible(true);
			}
			
		});
		myTable.add(makingmyTable);
		
		JMenuItem deletemyTable=new JMenuItem("시간표 삭제하기");
		deletemyTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				deleteCourse d=new deleteCourse();
				d.setVisible(true);
			}
			
		});
		myTable.add(deletemyTable);
		tableMenu.add(myTable);
		
		JMenu friendTable=new JMenu("친구 시간표");
		
		JMenuItem addfriend=new JMenuItem("친구 추가하기");
		addfriend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addFriend a=new addFriend();
				a.setVisible(true);
			}
			
		});
		friendTable.add(addfriend);
		
		JMenuItem lookingfriendTable=new JMenuItem("친구 시간표 보기");
		lookingfriendTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				friendtable f=new friendtable();
				f.setVisible(true);
			}
			
		});
		friendTable.add(lookingfriendTable);
		
		tableMenu.add(friendTable);
	
		JMenu themeMenu=new JMenu("테마 리뷰");
		JMenuItem addTheme=new JMenuItem("테마 추가");
		addTheme.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addTheme a=new addTheme();
				a.setVisible(true);
			}
			
		});
		themeMenu.add(addTheme);
		JMenuItem deleteTheme=new JMenuItem("테마 삭제");
		deleteTheme.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				deleteTheme d=new deleteTheme();
				d.setVisible(true);
			}
			
		});
		themeMenu.add(deleteTheme);
		JMenuItem modifyTheme=new JMenuItem("테마 수정");
		modifyTheme.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				modifyTheme m=new modifyTheme();
				m.setVisible(true);
			}
			
		});
		themeMenu.add(modifyTheme);
		
		JMenuBar bar=new JMenuBar();
		bar.add(searchMenu);
		bar.add(tableMenu);
		bar.add(themeMenu);
		setJMenuBar(bar);
		
		JPanel logoPanel=new JPanel();
		logoPanel.setBackground(Color.WHITE);
		logoPanel.setLayout(new BorderLayout());
		ImageIcon logoIcon=new ImageIcon("hat_logo.jpg");
		Font f=new Font("돋움", Font.BOLD, 30);
		ImageIcon title=new ImageIcon("title.png");
		JLabel logoLabel=new JLabel(title);
		logoLabel.setFont(f);
		logoLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel iconLabel=new JLabel(logoIcon);
		
		logoPanel.add(logoLabel, BorderLayout.CENTER);
		logoPanel.add(iconLabel, BorderLayout.NORTH);
		iconLabel.setBorder(new EmptyBorder(200,0,0,0));
		logoLabel.setBorder(new EmptyBorder(0,0,200,0));
		
		add(logoPanel, BorderLayout.CENTER);
		
		ImageIcon i=new ImageIcon("user.jpg");
		Image image=i.getImage();
		JPanel rightPanel=new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(image, 0, 0, null);
			}
		};
		rightPanel.setLayout(new BorderLayout());
		
		ImageIcon userIcon=new ImageIcon("user_image.png");
		JLabel userLabel=new JLabel(userIcon);
		rightPanel.add(userLabel, BorderLayout.NORTH);

		
		JPanel userPanel=new JPanel();
		userPanel.setLayout(new GridLayout(3,1));
		// 사용자 정보 찾기
		String s;
		String[] array;
		String name="", major="",sid="";
		BufferedReader bos;
		try {
			bos = new BufferedReader(new FileReader("회원명단.txt"));
			try {
				while((s=bos.readLine())!=null){
					array=s.split("/");
					if(id.equals(array[1])&&password.equals(array[2]))
					{
						name=array[0];
						major=array[3];
						sid=array[4];
						break;
					}	
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel nameLabel=new JLabel("이름 : "+name);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		userPanel.add(nameLabel);
		
		JLabel majorLabel=new JLabel("전공: "+major);
		majorLabel.setHorizontalAlignment(JLabel.CENTER);
		userPanel.add(majorLabel);
		
		JLabel idLabel=new JLabel("학번: "+sid);
		idLabel.setHorizontalAlignment(JLabel.CENTER);
		userPanel.add(idLabel);
		
		rightPanel.add(userPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
	}
	
	// 닫기 창
	private class CheckonExit extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			mainCheck checker=new mainCheck();
			checker.setVisible(true);
		}
	}
	private class mainCheck extends JFrame implements ActionListener{
		public mainCheck() {
			setSize(400,200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel messagePanel=new JPanel();
			messagePanel.setLayout(new BorderLayout());
			
			JLabel message=new JLabel("시간표 마법사를 나가시겠습니까?");
			message.setHorizontalAlignment(JLabel.CENTER);
			message.setHorizontalAlignment(JLabel.CENTER);
			messagePanel.add(message, BorderLayout.CENTER);
			add(messagePanel, BorderLayout.CENTER);
			
			JPanel buttonPanel=new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			
			JButton yesButton=new JButton("네");
			yesButton.addActionListener(this);
			buttonPanel.add(yesButton);
			
			JButton noButton=new JButton("아니오");
			noButton.addActionListener(this);
			buttonPanel.add(noButton);
			
			add(buttonPanel, BorderLayout.SOUTH);	
		}
		
		public void actionPerformed(ActionEvent e) {
			String btn=e.getActionCommand();
			if (btn.equals("네")) {
				System.exit(0);
			}
			else if(btn.equals("아니오")) {
				dispose();
			}
			else {
				System.out.println("Exit error");
			}
		}
	}
	
	
	// 검색
	private JTextField IOField;
	private JCheckBox check1;
	private JCheckBox check2;
	private JCheckBox check3;
	private JCheckBox check4;
	public class searchCourse extends JFrame implements ActionListener{
		
		public searchCourse() {
			setSize(400,200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel messagePanel=new JPanel();
			messagePanel.setLayout(new BorderLayout());
			
			JLabel message=new JLabel("검색");
			message.setBorder(new EmptyBorder(20,0,0,0));
			message.setHorizontalAlignment(JLabel.CENTER);
			IOField=new JTextField("검색어를 입력하세요!");
			IOField.setHorizontalAlignment(JLabel.CENTER);
			JPanel checkBoxPanel=new JPanel();
			checkBoxPanel.setLayout(new FlowLayout());
			check1=new JCheckBox("강의명");
			checkBoxPanel.add(check1);
			check2=new JCheckBox("전공");
			checkBoxPanel.add(check2);
			check3=new JCheckBox("교과목 코드");
			checkBoxPanel.add(check3);
			check4=new JCheckBox("테마");
			checkBoxPanel.add(check4);
			checkBoxPanel.setBorder(new EmptyBorder(0,0,10,0));
			messagePanel.add(IOField, BorderLayout.SOUTH);
			messagePanel.add(message, BorderLayout.NORTH);
			messagePanel.add(checkBoxPanel, BorderLayout.CENTER);
			add(messagePanel, BorderLayout.NORTH);
			
			JPanel buttonPanel=new JPanel();
			buttonPanel.setLayout(new BorderLayout());
			
			JButton yesButton=new JButton("OK");
			yesButton.addActionListener(this);
			buttonPanel.add(yesButton, BorderLayout.EAST);
			
			add(buttonPanel, BorderLayout.SOUTH);
		}
		public void actionPerformed(ActionEvent e) {
			String btn=e.getActionCommand();
			String searchText="";
			int type=0;
			if (btn.equals("OK")) {
				searchText=IOField.getText();
				if (check1.isSelected())
					type=1;
				else if (check2.isSelected())
					type=2;
				else if (check3.isSelected())
					type=3;
				else if (check4.isSelected())
					type=4;
				else
					System.out.println("Checkbox Error");
				dispose();
				
			}
			else {
				System.out.println("Button error");
			}
			dispose();
			SearchResult sr=new SearchResult(searchText, type);
			sr.setVisible(true);
		}
	}
	public class SearchResult extends JFrame implements ActionListener{
		public SearchResult() {}
		public SearchResult(String text, int type){
			setSize(1000,600);
			setTitle(text+" 검색 결과");
			setLayout(new BorderLayout());
			getContentPane().setBackground(Color.WHITE);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel topPanel=new JPanel();
			topPanel.setBackground(Color.WHITE);
			topPanel.setLayout(new BorderLayout());
			ImageIcon title=new ImageIcon("search_title.png");
			JLabel logoPanel=new JLabel(title);
			Font f=new Font("돋움",Font.BOLD, 15);
			JLabel describePanel=new JLabel("["+text+"] 검색결과입니다.");
			describePanel.setFont(f);
			describePanel.setHorizontalAlignment(JLabel.CENTER);
			topPanel.add(describePanel, BorderLayout.CENTER);
			topPanel.add(logoPanel, BorderLayout.NORTH);
			add(topPanel, BorderLayout.NORTH);
			
			JButton returnButton=new JButton("되돌아가기");
			returnButton.addActionListener(this);
			add(returnButton, BorderLayout.SOUTH);
			
			int count=0; 
			int indexArray[]=new int [er.lesson.size()];
			
			switch(type) {
			//강의명 배열 5
			case 1:
				for (int i=0; i<er.lesson.size(); i++) {
					if (text.equals(er.lesson.get(i).getName())){
						indexArray[count++]=i;
					}
				}
				break;
			//전공 배열  12
			case 2:
				for (int i=0; i<er.lesson.size(); i++) {
					if (text.equals(er.lesson.get(i).getMajor())){
						indexArray[count++]=i;
					}
				}
				break;
			//교과목 코드
			case 3:
				for (int i=0; i<er.lesson.size(); i++) {
					if (text.equals(er.lesson.get(i).getCode())){
						indexArray[count++]=i;
					}
				}
				break;
				
			//테마
			case 4:
				for (int i=0; i<er.lesson.size(); i++) {
					if (er.lesson.get(i).getTheme().contains(text)){
						indexArray[count++]=i;
					}
				}
			}
			JPanel resultPanel=new JPanel();
			resultPanel.setBackground(Color.WHITE);
			resultPanel.setLayout(new GridLayout(count, 1));
			if (count==0){
				JLabel resultLabel=new JLabel("검색 결과가 없습니다.");
				resultLabel.setHorizontalAlignment(JLabel.CENTER);
				resultPanel.add(resultLabel);
			}
			
		
			for (int i=0; i<count; i++) {
				int k=indexArray[i];
				JPanel eachPanel=new JPanel();
				eachPanel.setBackground(Color.WHITE);
				eachPanel.setLayout(new BorderLayout());
				JLabel resultLabel=new JLabel(er.lesson.get(indexArray[i]).getCode()+" "+er.lesson.get(indexArray[i]).getGradePoint()+"학점 "+er.lesson.get(indexArray[i]).getName()+" "+er.lesson.get(indexArray[i]).getMajor());
				JButton addButton=new JButton("추가하기");
				addButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						//1단계 아이디 : myId
						ArrayList<member> test = new ArrayList<member>();
						try {
					         ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("memberFile"));
					         try {
					        	 test = (ArrayList<member>)inputStream.readObject();
					         }catch(EOFException t) {
					        	 System.out.println("No more numbers in the file.");
					         }
					      }catch(FileNotFoundException t){
					         System.out.println("Cannot find file arrayfile.");
					         System.exit(0);
					      }catch(ClassNotFoundException t) {
					         System.err.println("Problems with the file input4");
					         System.exit(0);
					      }catch(IOException t) {
					         System.out.println("Problems with the file input12");
					         System.exit(0);
					      }
						//2단계 동일한 id의 데이터 불러오기
						int index=0;
						member m = new member();
						
						for(int j=0; j<test.size(); j++) {
							if(test.get(j).getId().equals(myId)) {
								index = j;
								m = test.get(j);
								System.out.println(test.get(j).getId());
								break;
							}
						}
						int check = k;
						
						
						if (test.get(index).index>=2) {
							check=-1;
						}
						
						m.setIndex(check);
						test.remove(index);
						test.add(m);
				        try {
				        	ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("memberFile"));
				        	outputStream.writeObject(test);
				        	outputStream.close();
				        }catch(IOException t){
					        System.err.println("Error writing to file");
					        System.exit(0);
					    }
						addCourse a=new addCourse(check);
						a.setVisible(true);
						dispose();
					}
					
				});
				eachPanel.add(resultLabel, BorderLayout.CENTER);
				eachPanel.add(addButton, BorderLayout.EAST);
				resultLabel.setHorizontalAlignment(JLabel.CENTER);
				resultPanel.add(eachPanel);
			}
			add(resultPanel, BorderLayout.CENTER);

		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("되돌아가기")){
				dispose();
			}
			else {
				System.out.println("Button error");
			}
		}
		
	}
	public class addCourse extends JFrame implements ActionListener{
		public addCourse() {}
		
		public addCourse(int a) {
			setSize(400,200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel messagePanel=new JPanel();
			messagePanel.setLayout(new BorderLayout());
			
			JLabel message;
			if (a!=-1) 
				message=new JLabel("추가 되었습니다.");
			else
				message=new JLabel("6학점 이상 담을 수 없습니다.");
			message.setHorizontalAlignment(JLabel.CENTER);
			messagePanel.add(message, BorderLayout.CENTER);
			add(messagePanel, BorderLayout.CENTER);
			
			JPanel buttonPanel=new JPanel();
			buttonPanel.setLayout(new BorderLayout());
			JButton yesButton=new JButton("OK");
			yesButton.addActionListener(this);
			buttonPanel.add(yesButton, BorderLayout.CENTER);

			add(buttonPanel, BorderLayout.SOUTH);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("OK")){
				dispose();
			}
			else {
				System.out.println("Button error");
			}
		}
		
	}
	
	// 테마 리뷰 -> 테마 추가
	JTextField subjectField, themeField;
	public class addTheme extends JFrame implements ActionListener{

		public addTheme() {
			setSize(400,200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JLabel messageLabel=new JLabel("테마 추가");
			messageLabel.setHorizontalAlignment(JLabel.CENTER);
			add(messageLabel, BorderLayout.NORTH);
			
			JPanel searchPanel=new JPanel();
			searchPanel.setLayout(new GridLayout(2,1));
			
			JPanel subjectPanel=new JPanel();
			subjectPanel.setLayout(new BorderLayout());
			JLabel subjectLabel=new JLabel("과목");
			subjectLabel.setHorizontalAlignment(JLabel.CENTER);
			searchPanel.add(subjectLabel, BorderLayout.WEST);
			subjectField=new JTextField("과목명을 입력하세요");
			searchPanel.add(subjectField, BorderLayout.EAST);
			add(searchPanel);
			
			JPanel themePanel=new JPanel();
			themePanel.setLayout(new BorderLayout());
			JLabel themeLabel=new JLabel("테마");
			themeLabel.setHorizontalAlignment(JLabel.CENTER);
			searchPanel.add(themeLabel, BorderLayout.WEST);
			themeField=new JTextField("추가할 테마를 입력하세요");
			searchPanel.add(themeField, BorderLayout.CENTER);
			add(searchPanel, BorderLayout.CENTER);
			
			JPanel buttonPanel=new JPanel();
			themePanel.setLayout(new BorderLayout());
			JButton submitButton=new JButton("OK");
			submitButton.addActionListener(this);
			themePanel.add(submitButton, BorderLayout.CENTER);
			add(themePanel, BorderLayout.SOUTH);
		}
		
		String subject, theme;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("OK")) {
				subject=subjectField.getText();
				theme=themeField.getText();
				
				dispose();
				
				addResult a=new addResult(subject, theme);
				a.setVisible(true);
			}
			else {
				System.out.println("Button error");
			}
		}
		
	}
	public class addResult extends JFrame implements ActionListener{
		public addResult() {}
		public addResult(String subject, String theme) {
			setSize(1000,600);
			setTitle(subject+" 테마 추가 결과");
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JLabel logoPanel=new JLabel("시간표 마법사");
			logoPanel.setHorizontalAlignment(JLabel.CENTER);
			add(logoPanel, BorderLayout.NORTH);
			
			JButton returnButton=new JButton("되돌아가기");
			returnButton.addActionListener(this);
			add(returnButton, BorderLayout.SOUTH);
			
			JPanel userPanel=new JPanel();
			userPanel.setBackground(Color.LIGHT_GRAY);
			add(userPanel, BorderLayout.EAST);
			
			JPanel resultPanel=new JPanel();
			int count=0;

			resultPanel.setLayout(new GridLayout(count, 1));
			
			for (int i=0; i<er.lesson.size(); i++) {
				if (subject.equals(er.lesson.get(i).getName())){
					if (count==0) {
						if (!er.lesson.get(i).getTheme().contains(theme)) {
							er.lesson.get(i).addString(theme);
							er.themeUpload();
							er.themeRead();
							JLabel themeLabel=new JLabel(er.lesson.get(i).getGradePoint()+"학점 "+er.lesson.get(i).getName()+" "+er.lesson.get(i).getMajor()+" "+er.lesson.get(i).getTheme());
							themeLabel.setHorizontalAlignment(JLabel.CENTER);
							resultPanel.add(themeLabel);
							count++;
						}
					}
					else {
						if (!er.lesson.get(i).getTheme().contains(theme)) {
							er.lesson.get(i).addString(theme);
							er.themeUpload();
						}
					}
				}
			}
			if (count==0) {
				JLabel themeLabel=new JLabel(subject+" 의 검색결과가 없습니다.");
				themeLabel.setHorizontalAlignment(JLabel.CENTER);
				resultPanel.add(themeLabel);
				
			}
			
			add(resultPanel, BorderLayout.CENTER);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("되돌아가기")) {
				dispose();
			}
			else {
				System.out.println("Button error");
			}
			
		}
		
	}
	
	// 테마 리뷰 -> 테마 삭제
	JTextField subjectField2, themeField2;
	public class deleteTheme extends JFrame implements ActionListener{

		public deleteTheme() {
			setSize(400,200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JLabel messageLabel=new JLabel("테마 삭제");
			messageLabel.setHorizontalAlignment(JLabel.CENTER);
			add(messageLabel, BorderLayout.NORTH);
			
			JPanel searchPanel=new JPanel();
			searchPanel.setLayout(new GridLayout(2,1));
			
			JPanel subjectPanel=new JPanel();
			subjectPanel.setLayout(new BorderLayout());
			JLabel subjectLabel=new JLabel("과목");
			subjectLabel.setHorizontalAlignment(JLabel.CENTER);
			searchPanel.add(subjectLabel, BorderLayout.WEST);
			subjectField2=new JTextField("과목명을 입력하세요");
			searchPanel.add(subjectField2, BorderLayout.EAST);
			add(searchPanel);
			
			JPanel themePanel=new JPanel();
			themePanel.setLayout(new BorderLayout());
			JLabel themeLabel=new JLabel("테마");
			themeLabel.setHorizontalAlignment(JLabel.CENTER);
			searchPanel.add(themeLabel, BorderLayout.WEST);
			themeField2=new JTextField("삭제할 테마를 입력하세요");
			searchPanel.add(themeField2, BorderLayout.CENTER);
			add(searchPanel, BorderLayout.CENTER);
			
			JPanel buttonPanel=new JPanel();
			themePanel.setLayout(new BorderLayout());
			JButton submitButton=new JButton("OK");
			submitButton.addActionListener(this);
			themePanel.add(submitButton, BorderLayout.CENTER);
			add(themePanel, BorderLayout.SOUTH);
		}
		
		String subject, theme;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("OK")) {
				subject=subjectField2.getText();
				theme=themeField2.getText();
				
				dispose();
				
				deleteResult d=new deleteResult(subject, theme);
				d.setVisible(true);
			}
			else {
				System.out.println("Button error");
			}
		}
		
	}
	public class deleteResult extends JFrame implements ActionListener{
		public deleteResult() {}
		public deleteResult(String subject, String theme) {
			setSize(1000,600);
			setTitle(subject+" 테마 삭제 결과");
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JLabel logoPanel=new JLabel("시간표 마법사");
			logoPanel.setHorizontalAlignment(JLabel.CENTER);
			add(logoPanel, BorderLayout.NORTH);
			
			JButton returnButton=new JButton("되돌아가기");
			returnButton.addActionListener(this);
			add(returnButton, BorderLayout.SOUTH);
			
			JPanel userPanel=new JPanel();
			userPanel.setBackground(Color.LIGHT_GRAY);
			add(userPanel, BorderLayout.EAST);
			
			JPanel resultPanel=new JPanel();
			int count=0;

			resultPanel.setLayout(new GridLayout(count, 1));
			
			for (int i=0; i<er.lesson.size(); i++) {
				if (subject.equals(er.lesson.get(i).getName())){
					if (count==0) {
						if (er.lesson.get(i).getTheme().contains(theme)) {
							er.lesson.get(i).getTheme().remove(theme);
							er.themeUpload();
							er.themeRead();
							JLabel themeLabel=new JLabel(er.lesson.get(i).getGradePoint()+"학점 "+er.lesson.get(i).getName()+" "+er.lesson.get(i).getMajor()+" "+er.lesson.get(i).getTheme());
							themeLabel.setHorizontalAlignment(JLabel.CENTER);
							resultPanel.add(themeLabel);
							er.themeUpload();
							count++;
						}
					}
					else {
						if (!er.lesson.get(i).getTheme().contains(theme)) {
							er.lesson.get(i).getTheme().remove(theme);
							er.themeUpload();
						}
					}
				}
			}
			if (count==0) {
				JLabel themeLabel=new JLabel(subject+" 의 검색결과가 없습니다.");
				themeLabel.setHorizontalAlignment(JLabel.CENTER);
				resultPanel.add(themeLabel);
			}
			
			add(resultPanel, BorderLayout.CENTER);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("되돌아가기")) {
				dispose();
			}
			else {
				System.out.println("Button error");
			}
			
		}
		
	}
	
	// 테마 리뷰 -> 테마 수정
	JTextField subjectField3, themeField3, themeField4;
	public class modifyTheme extends JFrame implements ActionListener{

		public modifyTheme() {
			setSize(400,200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JLabel messageLabel=new JLabel("테마 수정");
			messageLabel.setHorizontalAlignment(JLabel.CENTER);
			add(messageLabel, BorderLayout.NORTH);
			
			JPanel searchPanel=new JPanel();
			searchPanel.setLayout(new GridLayout(3,1));
			
			JPanel subjectPanel=new JPanel();
			subjectPanel.setLayout(new BorderLayout());
			JLabel subjectLabel=new JLabel("과목");
			subjectLabel.setHorizontalAlignment(JLabel.CENTER);
			searchPanel.add(subjectLabel, BorderLayout.WEST);
			subjectField3=new JTextField("과목명을 입력하세요");
			searchPanel.add(subjectField3, BorderLayout.EAST);
		
			JPanel themePanel=new JPanel();
			themePanel.setLayout(new BorderLayout());
			JLabel themeLabel=new JLabel("테마 수정 전");
			themeLabel.setHorizontalAlignment(JLabel.CENTER);
			searchPanel.add(themeLabel, BorderLayout.WEST);
			themeField3=new JTextField("수정할 테마를 입력하세요");
			searchPanel.add(themeField3, BorderLayout.CENTER);
			add(searchPanel, BorderLayout.CENTER);
			
			JPanel themePanel2=new JPanel();
			themePanel2.setLayout(new BorderLayout());
			JLabel themeLabel2=new JLabel("테마 수정 후");
			themeLabel2.setHorizontalAlignment(JLabel.CENTER);
			searchPanel.add(themeLabel2, BorderLayout.WEST);
			themeField4=new JTextField("새로운 테마를 입력하세요");
			searchPanel.add(themeField4, BorderLayout.CENTER);
			add(searchPanel, BorderLayout.CENTER);
			
			JPanel buttonPanel=new JPanel();
			themePanel.setLayout(new BorderLayout());
			JButton submitButton=new JButton("OK");
			submitButton.addActionListener(this);
			themePanel.add(submitButton, BorderLayout.CENTER);
			add(themePanel, BorderLayout.SOUTH);
		}
		
		String subject, theme, theme2;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("OK")) {
				subject=subjectField3.getText();
				theme=themeField3.getText();
				theme2=themeField4.getText();
				
				dispose();
				
				modifyResult m=new modifyResult(subject, theme, theme2);
				m.setVisible(true);
			}
			else {
				System.out.println("Button error");
			}
		}
		
	}
	public class modifyResult extends JFrame implements ActionListener{
		public modifyResult() {}
		public modifyResult(String subject, String theme, String theme2) {
			setSize(1000,600);
			setTitle(subject+" 테마 수정 결과");
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JLabel logoPanel=new JLabel("시간표 마법사");
			logoPanel.setHorizontalAlignment(JLabel.CENTER);
			add(logoPanel, BorderLayout.NORTH);
			
			JButton returnButton=new JButton("되돌아가기");
			returnButton.addActionListener(this);
			add(returnButton, BorderLayout.SOUTH);
			
			JPanel userPanel=new JPanel();
			userPanel.setBackground(Color.LIGHT_GRAY);
			add(userPanel, BorderLayout.EAST);
			
			JPanel resultPanel=new JPanel();
			int count=0;

			resultPanel.setLayout(new GridLayout(count, 1));
			
			for (int i=0; i<er.lesson.size(); i++) {
				if (subject.equals(er.lesson.get(i).getName())){
					if (count==0) {
						if (er.lesson.get(i).getTheme().contains(theme)) {
							er.lesson.get(i).getTheme().remove(theme);
							er.lesson.get(i).addString(theme2);
							er.themeUpload();
							er.themeRead();
							JLabel themeLabel=new JLabel(er.lesson.get(i).getGradePoint()+"학점 "+er.lesson.get(i).getName()+" "+er.lesson.get(i).getMajor()+" "+er.lesson.get(i).getTheme());
							themeLabel.setHorizontalAlignment(JLabel.CENTER);
							resultPanel.add(themeLabel);
							er.themeUpload();
							count++;
						}
					}
					else {
						if (!er.lesson.get(i).getTheme().contains(theme)) {
							er.lesson.get(i).getTheme().remove(theme);
							er.lesson.get(i).addString(theme2);
							er.themeUpload();
						}
					}
				}
			}
			
			if (count==0) {
				JLabel themeLabel=new JLabel(subject+" 의 검색결과가 없습니다.");
				themeLabel.setHorizontalAlignment(JLabel.CENTER);
				resultPanel.add(themeLabel);
			}
			
			add(resultPanel, BorderLayout.CENTER);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("되돌아가기")) {
				dispose();
			}
			else {
				System.out.println("Button error");
			}	
		}	
	}
	
	// 내 시간표 보기
	public class mytable extends JFrame implements ActionListener{

		public mytable() {
			setSize(1200,1000);
			JPanel topPanel=new JPanel();
			topPanel.setBackground(Color.WHITE);
			topPanel.setLayout(new BorderLayout());
			ImageIcon title=new ImageIcon("search_title.png");
			JLabel logoPanel=new JLabel(title);
			Font f=new Font("돋움",Font.BOLD, 20);
			JLabel describePanel=new JLabel("2019-S 시간표입니다.");
			describePanel.setFont(f);
			describePanel.setHorizontalAlignment(JLabel.CENTER);
			topPanel.add(describePanel, BorderLayout.CENTER);
			topPanel.add(logoPanel, BorderLayout.NORTH);
			add(topPanel, BorderLayout.NORTH);
			//1단계 아이디 : myId
			
			ArrayList<member> test2 = new ArrayList<member>();
			try {
		         ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("memberFile"));
		         try {
		        	test2 = (ArrayList<member>)inputStream.readObject();
		         }catch(EOFException e) {
		        	 System.out.println("No more numbers in the file.");
		         }
		      }catch(FileNotFoundException e){
		         System.out.println("Cannot find file arrayfile.");
		         System.exit(0);
		      }catch(ClassNotFoundException e) {
		         System.err.println("Problems with the file input");
		         System.exit(0);
		      }catch(IOException e) {
		         System.out.println("Problems with the file input13");
		         System.exit(0);
		      }
			//2단계 동일한 id의 데이터 불러오기
			int index=0;
			
			member ms = new member();
			
			for(int i=0; i<test2.size(); i++) {
				if(test2.get(i).getId().equals(myId)) {
					//member에 추가
					index = i;
					break;
				}
			}
			
			System.out.println(index);
			System.out.println(test2.size());
			
			int class1 = test2.get(index).getIndex()[0]; //첫번째로 들어가있는 시간표의 index
			int class2 =  test2.get(index).getIndex()[1]; //첫번째로 들어가있는 시간표의 index
			System.out.println(class1 + "-" + class2);
//			String text = class1 + "-" + class2;
//			String text2 = "나와라 :" + test.size();
			
			JPanel tablePanel=new JPanel();
			tablePanel.setLayout(new GridLayout(2,1));
			JLabel tableLabel1;
			if (class1==0) {
				tableLabel1=new JLabel("없음");
			}
			else 
				tableLabel1=new JLabel(er.lesson.get(class1).getName());
			tableLabel1.setHorizontalAlignment(JLabel.CENTER);
			
			JLabel tableLabel2;
			if (class2==0) {
				tableLabel2=new JLabel("없음");
			}
			else
				tableLabel2=new JLabel(er.lesson.get(class2).getName());
			tableLabel2.setHorizontalAlignment(JLabel.CENTER);
			tablePanel.add(tableLabel1);
			tablePanel.add(tableLabel2);
			add(tablePanel, BorderLayout.CENTER);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("되돌아가기")) {
				dispose();
			}
			else {
				System.out.println("Button error");
			}	
		}
		
	}
	
	// 친구 추가
	public class addFriend extends JFrame implements ActionListener{
		public addFriend() {
			setSize(400,200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel messagePanel=new JPanel();
			messagePanel.setLayout(new BorderLayout());
			
			JLabel message=new JLabel("친구 추가");
			message.setBorder(new EmptyBorder(20,0,20,0));
			message.setHorizontalAlignment(JLabel.CENTER);
			IOField=new JTextField("추가할 친구의 ID를 입력하세요!");
			IOField.setHorizontalAlignment(JLabel.CENTER);
			
			messagePanel.add(IOField, BorderLayout.SOUTH);
			messagePanel.add(message, BorderLayout.NORTH);
			
			add(messagePanel, BorderLayout.NORTH);
			
			JPanel buttonPanel=new JPanel();
			buttonPanel.setLayout(new BorderLayout());
			
			JButton yesButton=new JButton("추가");
			yesButton.addActionListener(this);
			buttonPanel.add(yesButton, BorderLayout.EAST);
			
			add(buttonPanel, BorderLayout.SOUTH);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("추가")) {
				addfriend_ok a=new addfriend_ok(IOField.getText());
				a.setVisible(true);
				dispose();
			}
			else {
				System.out.println("Button error");
			}
			
		}
		
	}
	public class addfriend_ok extends JFrame implements ActionListener{
		public addfriend_ok(String id){
			setSize(400,200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel messagePanel=new JPanel();
			messagePanel.setLayout(new BorderLayout());
			
			JLabel message=new JLabel(id+"님이 추가 되었습니다.");
			message.setHorizontalAlignment(JLabel.CENTER);
			messagePanel.add(message, BorderLayout.CENTER);
			add(messagePanel, BorderLayout.CENTER);
			
			JPanel buttonPanel=new JPanel();
			buttonPanel.setLayout(new BorderLayout());
			JButton yesButton=new JButton("OK");
			yesButton.addActionListener(this);
			buttonPanel.add(yesButton, BorderLayout.CENTER);

			add(buttonPanel, BorderLayout.SOUTH);
			
			//1단계 아이디 : myId
			
			ArrayList<member> test = new ArrayList<member>();
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("memberFile"));
		        try {
		        	test = (ArrayList<member>)inputStream.readObject();
		        }catch(EOFException e) {
		        	 System.out.println("No more numbers in the file.2");
		        }
			}catch(FileNotFoundException e){
				System.out.println("Cannot find file arrayfile.");
				System.exit(0);
			}catch(ClassNotFoundException e) {
				System.err.println("Problems with the file input");
				System.exit(0);
			}catch(IOException e) {
				System.out.println(test.size());
				System.out.println("Problems with the file input14");
				System.exit(0);
			}
			//2단계 동일한 id의 데이터 불러오기
			int index=0;
			memberSpecitify ms = new memberSpecitify();
			for(int i=0; i<test.size(); i++) {
				if(test.get(i).getId()==myId) {
					//memberSpecitify에 추가
					index = i;
					ms = (memberSpecitify) test.get(i);
					break;
				}
			}
			//3단계 친구 추가
			ms.addFriend(id);
			
			try {
		         ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("memberSpecitifyFile"));
		         outputStream.writeObject(ms);
		         outputStream.close();
		      }catch(IOException e){
		         System.err.println("Error writing to file");
		         System.exit(0);
		      }
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("OK")){
				dispose();
			}
			else {
				System.out.println("Button error");
			}
		}
	}
	public class deleteCourse extends JFrame implements ActionListener{
		public deleteCourse() {
			setSize(400,200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel messagePanel=new JPanel();
			messagePanel.setLayout(new BorderLayout());
			
			JLabel message=new JLabel("시간표 삭제");
			message.setBorder(new EmptyBorder(20,0,20,0));
			message.setHorizontalAlignment(JLabel.CENTER);
			IOField=new JTextField("삭제할 과목의 이름을 입력하세요!");
			IOField.setHorizontalAlignment(JLabel.CENTER);
			
			messagePanel.add(IOField, BorderLayout.SOUTH);
			messagePanel.add(message, BorderLayout.NORTH);
			
			add(messagePanel, BorderLayout.NORTH);
			
			JPanel buttonPanel=new JPanel();
			buttonPanel.setLayout(new BorderLayout());
			
			JButton yesButton=new JButton("삭제");
			yesButton.addActionListener(this);
			buttonPanel.add(yesButton, BorderLayout.EAST);
			
			add(buttonPanel, BorderLayout.SOUTH);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("삭제")) {
				delete_ok a=new delete_ok(IOField.getText());
				a.setVisible(true);
				dispose();
			}
			else {
				System.out.println("Button error");
			}
			
		}
		
	}
	public class delete_ok extends JFrame implements ActionListener{
		public delete_ok(String name){
			setSize(400,200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel messagePanel=new JPanel();
			messagePanel.setLayout(new BorderLayout());
			
			JLabel message=new JLabel(name+"이 삭제되었습니다.");
			message.setHorizontalAlignment(JLabel.CENTER);
			messagePanel.add(message, BorderLayout.CENTER);
			add(messagePanel, BorderLayout.CENTER);
			
			JPanel buttonPanel=new JPanel();
			buttonPanel.setLayout(new BorderLayout());
			JButton yesButton=new JButton("OK");
			yesButton.addActionListener(this);
			buttonPanel.add(yesButton, BorderLayout.CENTER);

			add(buttonPanel, BorderLayout.SOUTH);
			
			ArrayList<member> test = new ArrayList<member>();
			try {
		         ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("memberFile"));
		         try {
		        	 test = (ArrayList<member>)inputStream.readObject();
		         }catch(EOFException t) {
		        	 System.out.println("No more numbers in the file.");
		         }
		      }catch(FileNotFoundException t){
		         System.out.println("Cannot find file arrayfile.");
		         System.exit(0);
		      }catch(ClassNotFoundException t) {
		         System.err.println("Problems with the file input4");
		         System.exit(0);
		      }catch(IOException t) {
		         System.out.println("Problems with the file input12");
		         System.exit(0);
		      }
			
			int index=0;
			member m = new member();
			
			for(int j=0; j<test.size(); j++) {
				if(test.get(j).getId().equals(myId)) {
					index = j;
					m = test.get(j);
					System.out.println(test.get(j).getId());
					break;
				}
			}
			
			test.remove(index);
			if(name.equals(er.lesson.get(m.getIndex()[0]).getName())) {
				m.getIndex()[0] = m.getIndex()[1];
				m.getIndex()[1]=0;
				m.index--;
			}
			if(name.equals(er.lesson.get(m.getIndex()[1]).getName())) {
				m.getIndex()[1] = 0;
				m.index--;
			}
			test.add(m);
	        try {
	        	ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("memberFile"));
	        	outputStream.writeObject(test);
	        	outputStream.close();
	        }catch(IOException t){
		        System.err.println("Error writing to file");
		        System.exit(0);
		    }
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("OK")){
				dispose();
			}
			else {
				System.out.println("Button error");
			}
			
		}
	}
	
	public class friendtable extends JFrame implements ActionListener{

		public friendtable() {
			setSize(1200,1000);
			JPanel topPanel=new JPanel();
			topPanel.setBackground(Color.WHITE);
			topPanel.setLayout(new BorderLayout());
			ImageIcon title=new ImageIcon("search_title.png");
			JLabel logoPanel=new JLabel(title);
			Font f=new Font("돋움",Font.BOLD, 20);
			JLabel describePanel=new JLabel("2019-S 친구 시간표입니다.");
			describePanel.setFont(f);
			describePanel.setHorizontalAlignment(JLabel.CENTER);
			topPanel.add(describePanel, BorderLayout.CENTER);
			topPanel.add(logoPanel, BorderLayout.NORTH);
			add(topPanel, BorderLayout.NORTH);
			//1단계 아이디 : myId
			
			ArrayList<member> test2 = new ArrayList<member>();
			memberSpecitify ms = new memberSpecitify();
			try {
		         ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("memberFile"));
		         try {
		        	test2 = (ArrayList<member>)inputStream.readObject();
		        	inputStream.close();
		         }catch(EOFException e) {
		        	 System.out.println("No more numbers in the file.");
		         }
		      }catch(FileNotFoundException e){
		         System.out.println("Cannot find file arrayfile1.");
		         System.exit(0);
		      }catch(ClassNotFoundException e) {
		         System.err.println("Problems with the file input");
		         System.exit(0);
		      }catch(IOException e) {
		         System.out.println("Problems with the file input13");
		         System.exit(0);
		      }
			try {
				 ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("memberSpecitifyFile"));
	        	ms= (memberSpecitify)inputStream.readObject();
	        	inputStream.close();
	         }catch(FileNotFoundException e){
		         System.out.println("Cannot find file arrayfile2.");
		         System.exit(0);
		      }catch(ClassNotFoundException e) {
		         System.err.println("Problems with the file input");
		         System.exit(0);
		      }catch(IOException e) {
		         System.out.println("Problems with the file input13");
		         System.exit(0);
		      }
			//2단계 동일한 id의 데이터 불러오기
			int index=0;
			
			
			for(int i=0; i<test2.size(); i++) {
				if(test2.get(i).getId().equals(ms.getFriend().get(0))) {
					//member에 추가
					index = i;
					break;
				}
			}
			
			System.out.println(index);
			System.out.println(test2.size());
			
			int class1 = test2.get(index).getIndex()[0]; //첫번째로 들어가있는 시간표의 index
			int class2 =  test2.get(index).getIndex()[1]; //첫번째로 들어가있는 시간표의 index
			System.out.println(class1 + "-" + class2);
//			String text = class1 + "-" + class2;
//			String text2 = "나와라 :" + test.size();
			JPanel tablePanel=new JPanel();
			tablePanel.setLayout(new GridLayout(2,1));
			JLabel tableLabel1;
			if (class1==0) {
				tableLabel1=new JLabel("없음");
			}
			else 
				tableLabel1=new JLabel(er.lesson.get(class1).getName());
			tableLabel1.setHorizontalAlignment(JLabel.CENTER);
			
			JLabel tableLabel2;
			if (class2==0) {
				tableLabel2=new JLabel("없음");
			}
			else
				tableLabel2=new JLabel(er.lesson.get(class2).getName());
			tableLabel2.setHorizontalAlignment(JLabel.CENTER);
			tablePanel.add(tableLabel1);
			tablePanel.add(tableLabel2);
			add(tablePanel, BorderLayout.CENTER);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String btn=e.getActionCommand();
			if (btn.equals("되돌아가기")) {
				dispose();
			}
			else {
				System.out.println("Button error");
			}	
		}
		
	}
	
}
