package Login;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import tool.imgPanel;

public class signup extends JFrame{

	private JTextField idInputFIeld;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JTextField nicknameFIeld;
	private JTextField inputweightField;
	private JTextField heightField;
	private JTextField muscleField;
	private JTextField bodyField;
	private JButton isIDSame;
	private JButton isPWSame;
	private JComboBox<String> genderCombo;
	
	private User userinfo;
	private login log;
	
	
	ArrayList<User> userinfoList_signup = new ArrayList<User>(); //class 배열리스트 설정
	boolean ispushjungbokBtn = false; //ID중복버튼 눌렀는지 체크
	boolean ispushinputcheckBtn = false; //입력확인버튼 눌렀는지 쳌
	
	public signup() {
//		회원가입 하기 전 , 기존 유저들의 정보들을 다 가져오는 작업을 거친다
		File whatsin = new File("user\\");
		String[] filenames = whatsin.list();
		
		if(filenames != null) {
			for (int i = 0; i < filenames.length; i++) {
				User AA = new User();
				try {
					ObjectInputStream inputStream =
							new ObjectInputStream (new FileInputStream("user\\" + filenames[i]));
					AA = (User)inputStream.readObject(); //클래스 정보 가져오기
					userinfoList_signup.add(AA); //add to ArrayList<user>
					inputStream.close();
				}catch(FileNotFoundException e) {
					System.err.println("can't find file");
					System.exit(0);
				}catch(ClassNotFoundException e) {
					System.err.println("prlblem occuered");
					System.exit(0);		
				}catch(IOException e) {
					System.err.println("problem occuered");
					System.exit(0);
				}
			}
		}
		setVisible(true);
		setBounds(100, 100, 900, 700);
		
		//imgPanel 클래스 이용해 이미지 넣기
		imgPanel signupPanel = new imgPanel(new ImageIcon("image\\flowwater.png").getImage());
		signupPanel.setBounds(0, 0, 986, 663);
		setSize(signupPanel.getDim());
		setPreferredSize(signupPanel.getDim());
		getContentPane().add(signupPanel);
		
		JLabel registerlabel = new JLabel("\uD68C\uC6D0 \uAC00\uC785");
		registerlabel.setFont(new Font("휴먼매직체", Font.PLAIN, 30));
		registerlabel.setBounds(31, 10, 231, 56);
		signupPanel.add(registerlabel);
		
		ImageIcon IDicon = new ImageIcon("icon\\id.png");
		JLabel IDIcon = new JLabel(IDicon);
		IDIcon.setBounds(31, 76, 50, 50);
	
		signupPanel.add(IDIcon);
		
		JLabel IDlabel = new JLabel("ID \uC785\uB825");
		IDlabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		IDlabel.setBounds(93, 77, 73, 49);
		signupPanel.add(IDlabel);
		
		idInputFIeld = new JTextField();
		idInputFIeld.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		idInputFIeld.setBounds(179, 82, 191, 35);
		
		signupPanel.add(idInputFIeld);
		idInputFIeld.setColumns(10);
		
		isIDSame = new JButton("\uC911\uBCF5\uD655\uC778");
		isIDSame.setBackground(new Color(135, 206, 235));
		isIDSame.setFont(new Font("휴먼엑스포", Font.BOLD | Font.ITALIC, 16));
		isIDSame.setBounds(382, 78, 98, 43);
		isIDSame.addActionListener(new ActionListener(){
			//회원가입하고자 하는 ID가 기존 회원들의 아이디와 겹치면 안 된다.
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean avail = true;
				ArrayList<String> idnow = new ArrayList<String>();
				if(!(idInputFIeld.getText().equals(""))) { //아무것도 안 적혀있지 않아야함
					for(int i = 0; i < userinfoList_signup.size(); i++) {
						idnow.add(userinfoList_signup.get(i).getID()); //기존 iD 정보를 idnow로 받아온다
					}
					for(int i = 0; i < idnow.size(); i++) {
						if(idnow.get(i).equals(idInputFIeld.getText())) {
							JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.","경고", JOptionPane.ERROR_MESSAGE);
							idInputFIeld.setText("");
							avail = false;
							ispushjungbokBtn = false;
							break;
						}
					}
					if(avail == true ) {
						JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
						ispushjungbokBtn = true;
					}
				}else {
					JOptionPane.showMessageDialog(null, "아이디를 입력하셔야 합니다.", "경고", JOptionPane.ERROR_MESSAGE);
					ispushjungbokBtn = false;
				}
			}
			
		});
		signupPanel.add(isIDSame);
		
		ImageIcon PWicon = new ImageIcon("icon\\PW.png");
		JLabel PWIcon = new JLabel(PWicon);
		PWIcon.setBounds(31, 148, 50, 50);
		signupPanel.add(PWIcon);
		
		JLabel PWlabel = new JLabel("PW \uC785\uB825");
		PWlabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		PWlabel.setBounds(93, 149, 83, 49);
		signupPanel.add(PWlabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 155, 191, 35);
		signupPanel.add(passwordField);
		
		JLabel PWlabel2 = new JLabel("PW \uC7AC\uC785\uB825");
		PWlabel2.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		PWlabel2.setBounds(73, 221, 103, 49);
		signupPanel.add(PWlabel2);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(179, 225, 191, 35);
		signupPanel.add(passwordField2);
		
		isPWSame = new JButton("\uC785\uB825\uD655\uC778");
		isPWSame.setFont(new Font("휴먼엑스포", Font.BOLD | Font.ITALIC, 16));
		isPWSame.setBackground(new Color(135, 206, 235));
		isPWSame.setBounds(382, 221, 98, 43);
		isPWSame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(getPasswordInfo().equals("")) && !(getPasswordInfo2().equals(""))) {
					if(getPasswordInfo().equals(getPasswordInfo2())) {
						JOptionPane.showMessageDialog(null, "동일한 비밀번호입니다.");
						ispushinputcheckBtn = true;
					}
					else {
						JOptionPane.showMessageDialog(null, "두 입력이 서로 다릅니다. 다시 입력하세요");
						ispushinputcheckBtn = false;
						passwordField.setText("");
						passwordField2.setText("");
					}
				}else if(getPasswordInfo().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.", "경고", JOptionPane.ERROR_MESSAGE);
					ispushinputcheckBtn = false;
				}else if(getPasswordInfo2().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 재입력하세요.", "경고", JOptionPane.ERROR_MESSAGE);
					ispushinputcheckBtn = false;
				}
			}
			
		});
		signupPanel.add(isPWSame);
		
		ImageIcon NNicon = new ImageIcon("icon\\nickname.png");
		JLabel NicknameIcon = new JLabel(NNicon);
		NicknameIcon.setBounds(31, 296, 50, 50);
		signupPanel.add(NicknameIcon);
		
		JLabel nicknameLabel = new JLabel("\uB2C9\uB124\uC784");
		nicknameLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		nicknameLabel.setBounds(103, 303, 73, 49);
		signupPanel.add(nicknameLabel);
		
		nicknameFIeld = new JTextField();
		nicknameFIeld.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		nicknameFIeld.setColumns(10);
		nicknameFIeld.setBounds(179, 308, 191, 35);
		signupPanel.add(nicknameFIeld);
		
		ImageIcon genderIcon= new ImageIcon("icon\\gender.png");
		JLabel gendericon = new JLabel(genderIcon);
		gendericon.setBounds(31, 380, 50, 50);
		signupPanel.add(gendericon);
		
		JLabel genderLabel = new JLabel("\uC131\uBCC4");
		genderLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		genderLabel.setBounds(120, 381, 56, 49);
		signupPanel.add(genderLabel);
		
		genderCombo = new JComboBox<String>();
		genderCombo.addItem("남성");
		genderCombo.addItem("여성");
		genderCombo.setFont(new Font("휴먼엑스포", Font.BOLD, 16));
		genderCombo.setBounds(179, 390, 73, 35);
		signupPanel.add(genderCombo);
		
		
		ImageIcon weightIcon= new ImageIcon("icon\\weight.png");
		JLabel weighticon = new JLabel(weightIcon);
		weighticon.setBounds(600, 76, 50, 50);
		signupPanel.add(weighticon);
		
		JLabel weightLabel = new JLabel("\uCCB4\uC911");
		weightLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		weightLabel.setBounds(676, 76, 50, 43);
		signupPanel.add(weightLabel);
		
		inputweightField = new JTextField();
		inputweightField.setHorizontalAlignment(SwingConstants.CENTER);
		inputweightField.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		inputweightField.setColumns(10);
		inputweightField.setBounds(738, 82, 98, 35);
		signupPanel.add(inputweightField);
		
		JLabel lblKg = new JLabel("KG");
		lblKg.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		lblKg.setBounds(848, 76, 50, 43);
		signupPanel.add(lblKg);
		
		ImageIcon heightIcon= new ImageIcon("icon\\height.png");
		JLabel heighticon = new JLabel(heightIcon);
		heighticon.setBounds(600, 148, 50, 50);
		signupPanel.add(heighticon);
		
		JLabel heightLabel = new JLabel("\uC2E0\uC7A5");
		heightLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		heightLabel.setBounds(676, 148, 50, 43);
		signupPanel.add(heightLabel);
		
		heightField = new JTextField();
		heightField.setHorizontalAlignment(SwingConstants.CENTER);
		heightField.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		heightField.setColumns(10);
		heightField.setBounds(738, 154, 98, 35);
		signupPanel.add(heightField);
		
		JLabel CMlabel = new JLabel("cm");
		CMlabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		CMlabel.setBounds(848, 148, 50, 43);
		signupPanel.add(CMlabel);
		
		ImageIcon muscleIcon= new ImageIcon("icon\\muscle.png");
		JLabel muscleicon = new JLabel(muscleIcon);
		muscleicon.setBounds(600, 221, 50, 50);
		signupPanel.add(muscleicon);
		
		JLabel muscleLabel = new JLabel("\uACE8\uADFC\uB7C9");
		muscleLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		muscleLabel.setBounds(666, 224, 62, 43);
		signupPanel.add(muscleLabel);
		
		muscleField = new JTextField();
		muscleField.setHorizontalAlignment(SwingConstants.CENTER);
		muscleField.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		muscleField.setColumns(10);
		muscleField.setBounds(738, 225, 98, 35);
		signupPanel.add(muscleField);
		
		JLabel KG_2 = new JLabel("KG");
		KG_2.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		KG_2.setBounds(848, 221, 50, 43);
		signupPanel.add(KG_2);
		
		JLabel percent_1 = new JLabel("%");
		percent_1.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		percent_1.setBounds(848, 300, 50, 43);
		signupPanel.add(percent_1);
		
		ImageIcon bodyIcon= new ImageIcon("icon\\body.png");
		JLabel bodyrateicon = new JLabel(bodyIcon);
		bodyrateicon.setBounds(600, 296, 50, 50);
		signupPanel.add(bodyrateicon);
		
		JLabel muscleLabel_1 = new JLabel("\uCCB4\uC9C0\uBC29\uB960");
		muscleLabel_1.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		muscleLabel_1.setBounds(662, 303, 76, 43);
		signupPanel.add(muscleLabel_1);
		
		bodyField = new JTextField();
		bodyField.setHorizontalAlignment(SwingConstants.CENTER);
		bodyField.setFont(new Font("휴먼엑스포", Font.ITALIC, 16));
		bodyField.setColumns(10);
		bodyField.setBounds(753, 305, 83, 35);
		signupPanel.add(bodyField);
		
		JButton registerBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		registerBtn.setFont(new Font("휴먼엑스포", Font.BOLD | Font.ITALIC, 16));
		registerBtn.setBackground(new Color(135, 206, 235));
		registerBtn.setBounds(408, 610, 184, 43);
		
		
		registerBtn.addActionListener(new ActionListener(){
			//모두 기입을 했다면, 가입 처리와 동시에 유저 이름으로 파일을 만들고, 유저의 정보를 byte화해서 저장한다.
			@Override
			public void actionPerformed(ActionEvent e) {
				if( !(nicknameFIeld.getText().equals("")) &&!(inputweightField.getText().equals("")) && 
						 !(heightField.getText().equals("")) &&  !(muscleField.getText().equals("")) &&
						 !(bodyField.getText().equals(""))) { //일단 모든 필드가 비어져 있으면 안 되는 것이고
					if( (isDouble(inputweightField.getText()) == true) && (isDouble(heightField.getText()) == true) &&
							(isDouble(muscleField.getText()) == true) && (isDouble(bodyField.getText()) == true)  ) {
						//isDouble 메서드를 통해 0 초과 실수인지 확인하고,
						if(ispushjungbokBtn == true && ispushinputcheckBtn == true) {
							//버튼 2개가 눌린 것이 확인되어야 회원가입되도록 한다
							JOptionPane.showMessageDialog(null, "회원가입 완료되었습니다.");
							log = new login(); 
							
							userinfo = new User(idInputFIeld.getText(), getPasswordInfo2(), genderCombo.getSelectedItem().toString(),
									nicknameFIeld.getText(), 
									Float.valueOf(inputweightField.getText()), Float.valueOf(heightField.getText()), 
									Float.valueOf(muscleField.getText()) , Float.valueOf(bodyField.getText()));
							userinfoList_signup.add(userinfo); //유저 정보 업데이트  = signup의 user class arraylist 에 add

							try { //serialize 진행.
								ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream
										("user\\" + idInputFIeld.getText() +".dat")); //(userID).dat 로 저장
								outputStream.writeObject(userinfo);
								outputStream.close();
							}catch(IOException e1) {
								System.err.println("error occuered when writing to file");
								System.exit(0);
							}
							
							
							log.setUserinfoList(userinfoList_signup); //회원가입한 정보를 userInfoList에 업데이트
							ispushjungbokBtn = false; //중복버튼 초기화.
							ispushinputcheckBtn = false;
									
							dispose(); 
							

						}
						else if(ispushjungbokBtn == false && ispushinputcheckBtn == false) {
							JOptionPane.showMessageDialog(null, "중복확인버튼과 비번입력확인버튼을 눌러야 합니다.","경고",JOptionPane.ERROR_MESSAGE);
						}
						else if(ispushjungbokBtn == true && ispushinputcheckBtn == false) {
							JOptionPane.showMessageDialog(null, "비번입력확인버튼을 눌러야 합니다.","경고",JOptionPane.ERROR_MESSAGE);
						}
						else if(ispushjungbokBtn == false && ispushinputcheckBtn == true) {
							JOptionPane.showMessageDialog(null, "중복확인버튼을 눌러야 합니다.","경고",JOptionPane.ERROR_MESSAGE);
						}	
					}
					else {
						JOptionPane.showMessageDialog(null, "(몸무게, 키, 골격근량, 체지방률)에는 0 초과의 실수만 입력가능합니다.","경고",JOptionPane.ERROR_MESSAGE);
					}
				}else {				
					JOptionPane.showMessageDialog(null, "입력하지 않은 칸이 존재합니다.","경고",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});	
		signupPanel.add(registerBtn);
		getContentPane().setLayout(null);
	}
	//비번 입력창이 2개 있고, 각각을 받아오기 위한 get method 생성
	private String getPasswordInfo() {
		String str = new String(passwordField.getPassword());
		return str;
	}
	
	private String getPasswordInfo2() {
		String str = new String(passwordField2.getPassword());
		return str;
	}
	
	public ArrayList<User> getUserinfoList() {
		return userinfoList_signup;
	}

	public void setUserinfoList(ArrayList<User> userinfoList) {
		this.userinfoList_signup.addAll(userinfoList);
	}
	
	public boolean isDouble(String here) { //0 초과하는 실수인지 확인하기 위한 method
		try {
			if(Double.parseDouble(here) > 0) {
				return true;
			}else {
				return false;
			}
			
		} catch(NumberFormatException e) {
			return false;
		}
	}
}

