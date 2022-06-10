package Restraunt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tab_Member extends JPanel implements ActionListener, Serializable{

	JPanel MemberPanel = new JPanel();
	JButton[] MemberButton = new JButton[5];
	static JLabel[][] MemberLabel = new JLabel[5][5];
	
	public Tab_Member() {
		setBackground(Color.WHITE);
		setLayout(null);
	
		JPanel navPanel = new JPanel();
		navPanel.setLayout(new GridLayout(1,5));
		
		/* 위에 메뉴 */
		JLabel[] navLabel = new JLabel[5];
		navLabel[0] = new JLabel("         번호");
		navLabel[1] = new JLabel("         등급");
		navLabel[2] = new JLabel("         이름");
		navLabel[3] = new JLabel("         마일리지");
		navLabel[4] = new JLabel("         연락처");
		
		navPanel.add(navLabel[0]);
		navPanel.add(navLabel[1]);
		navPanel.add(navLabel[2]);
		navPanel.add(navLabel[3]);
		navPanel.add(navLabel[4]);
		
		/* 밑에 버튼 */
		JPanel ButtonPanel = new JPanel();
		
		JButton[] Button = new JButton[3];
		Button[0] = new JButton("편집");
		Button[1] = new JButton("추가");
		Button[2] = new JButton("삭제");
		
		Button[0].setActionCommand("Member edit");
		Button[1].setActionCommand("Member add");
		Button[2].setActionCommand("Member delete");
		
		Button[0].addActionListener(this);
		Button[1].addActionListener(this);
		Button[2].addActionListener(this);
		
		ButtonPanel.add(Button[0]);
		ButtonPanel.add(Button[1]);
		ButtonPanel.add(Button[2]);

		/* 중간에 회원 정보 */
		
		/* 회원정보 버튼 */
		MemberButtonListener ButtonListener = new MemberButtonListener();
		MemberButton[0] = new JButton();
		MemberButton[1] = new JButton();
		MemberButton[2] = new JButton();
		MemberButton[3] = new JButton();
		MemberButton[4] = new JButton();
		
		MemberButton[0].setActionCommand("Button0");
		MemberButton[1].setActionCommand("Button1");
		MemberButton[2].setActionCommand("Button2");
		MemberButton[3].setActionCommand("Button3");
		MemberButton[4].setActionCommand("Button4");
		
		MemberButton[0].addActionListener(ButtonListener);
		MemberButton[1].addActionListener(ButtonListener);
		MemberButton[2].addActionListener(ButtonListener);
		MemberButton[3].addActionListener(ButtonListener);
		MemberButton[4].addActionListener(ButtonListener);
		
		/* 회원정보 라벨 */
		MemberPanel.setLayout(new GridLayout(5, 1));
		MemberLabel[0] = new JLabel[5];
		MemberLabel[1] = new JLabel[5];
		MemberLabel[2] = new JLabel[5];
		MemberLabel[3] = new JLabel[5];
		MemberLabel[4] = new JLabel[5];
		
		initMemberLabel(0,MemberLabel[0]);
		initMemberLabel(1,MemberLabel[1]);
		initMemberLabel(2,MemberLabel[2]);
		initMemberLabel(3,MemberLabel[3]);
		initMemberLabel(4,MemberLabel[4]);
	
		MemberPanel.add(MemberButton[0]);
		MemberPanel.add(MemberButton[1]);
		MemberPanel.add(MemberButton[2]);
		MemberPanel.add(MemberButton[3]);
		MemberPanel.add(MemberButton[4]);
		
		add(MemberPanel);
		add(navPanel,BorderLayout.NORTH);
		add(ButtonPanel, BorderLayout.NORTH );
		
		MemberPanel.setBounds(0,65, 600, 350 );
		navPanel.setBounds(0,0,600,60);
		ButtonPanel.setBounds(0,420,600,70);
	}
	
	public void initMemberLabel(int index, JLabel[] MemberLabel) {
		MemberLabel[0] = new JLabel();
		MemberLabel[1] = new JLabel();
		MemberLabel[2] = new JLabel();
		MemberLabel[3] = new JLabel();
		MemberLabel[4] = new JLabel();
		
		MemberButton[index].setLayout(new GridLayout(1,5));
		MemberButton[index].add(MemberLabel[0]);
		MemberButton[index].add(MemberLabel[1]);
		MemberButton[index].add(MemberLabel[2]);
		MemberButton[index].add(MemberLabel[3]);
		MemberButton[index].add(MemberLabel[4]);
	}
	
	static int MemberNumber = 0;
	JFrame addQuestion = new JFrame("회원정보 등록하기");
	JTextField[] addQuestionTextFields;
	
	public void addMember() {
		addQuestion.setSize(600,350);
		addQuestion.setLayout(null);
		addListener AddListener = new addListener();
		
		/* 라벨 부분 */
		JLabel[] addQuestionLabel = new JLabel[2];
//		addQuestionLabel[0] = new JLabel("번호는 : ");
		addQuestionLabel[0] = new JLabel("이름은 : ");
		addQuestionLabel[1] = new JLabel("연락처는 : ");
		
		addQuestion.add(addQuestionLabel[0]);
		addQuestion.add(addQuestionLabel[1]);
//		addQuestion.add(addQuestionLabel[2]);

		addQuestionLabel[0].setBounds(50, 30, 100, 50);
		addQuestionLabel[1].setBounds(50, 100, 100, 50);
//		addQuestionLabel[2].setBounds(50, 170, 100, 50);
		
		/* TextFields 부분 */
		addQuestionTextFields = new JTextField[2];
		addQuestionTextFields[0] = new JTextField();
		addQuestionTextFields[1] = new JTextField();
//		addQuestionTextFields[2] = new JTextField();
		
		addQuestion.add(addQuestionTextFields[0]);
		addQuestion.add(addQuestionTextFields[1]);
//		addQuestion.add(addQuestionTextFields[2]);
		
		addQuestionTextFields[0].setBounds(150, 30, 400, 50);
		addQuestionTextFields[1].setBounds(150, 100, 400, 50);
//		addQuestionTextFields[2].setBounds(150, 170, 400, 50);
		
		/* Button 부분 */
		JButton SuccessButton = new JButton("추가하기");
		JButton CancelButton = new JButton("취소하기");
		
		SuccessButton.setActionCommand("add Success");
		CancelButton.setActionCommand("add Cancel");
		
		SuccessButton.addActionListener(AddListener);
		CancelButton.addActionListener(AddListener);
		
		addQuestion.add(SuccessButton);
		addQuestion.add(CancelButton);
		SuccessButton.setBounds(350, 250, 100 , 50);
		CancelButton.setBounds(470, 250, 100 , 50);
		
		JButton junk = new JButton();
		addQuestion.add(junk);
		
		addQuestion.setVisible(true);
	}
	
	JFrame deleteQuestion = new JFrame("회원정보 삭제하기");
	JTextField[] deleteQuestionTextFields;
	JLabel deleteConfirmLabel = new JLabel();
	
	public void deleteMember() {
		deleteQuestion.setSize(300,150);
		deleteQuestion.setLayout(null);
		deleteListener DeleteListener = new deleteListener();
		
		
		deleteConfirmLabel.setText((chooseButton+1) + " 의 회원 정보를 삭제하시겠습니까?");
		deleteQuestion.add(deleteConfirmLabel);
		deleteConfirmLabel.setBounds(60, 10, 200, 30);
		
		JButton SuccessButton = new JButton("예");
		JButton CancelButton = new JButton("아니요");
		SuccessButton.setActionCommand("delete Success");
		CancelButton.setActionCommand("delete Cancel");
		SuccessButton.addActionListener(DeleteListener);
		CancelButton.addActionListener(DeleteListener);
		
		deleteQuestion.add(SuccessButton);
		deleteQuestion.add(CancelButton);
		
		SuccessButton.setBounds(40, 40, 80, 50);
		CancelButton.setBounds(180, 40, 80, 50);
		deleteQuestion.setVisible(true);
	}
	
	JFrame editQuestion = new JFrame("회원정보 편집하기");
	JTextField[] editQuestionTextFields;
	JLabel editConfirmLabel = new JLabel();
	
	
	public void editMember() {
		editQuestion.setSize(600,350);
		editQuestion.setLayout(null);
		editListener EditListener = new editListener();

		editConfirmLabel.setText((chooseButton+1) + " 의 회원 정보를 편집하는 중입니다");
		editQuestion.add(editConfirmLabel);
		editConfirmLabel.setBounds(60, 10, 200, 30);
		
		/* 라벨 부분 */
		JLabel[] editQuestionLabel = new JLabel[2];
//		editQuestionLabel[0] = new JLabel("번호는 : ");
		editQuestionLabel[0] = new JLabel("이름은 : ");
		editQuestionLabel[1] = new JLabel("연락처는 : ");
		
		editQuestion.add(editQuestionLabel[0]);
		editQuestion.add(editQuestionLabel[1]);
//		editQuestion.add(editQuestionLabel[2]);

		editQuestionLabel[0].setBounds(50, 30, 100, 50);
		editQuestionLabel[1].setBounds(50, 100, 100, 50);
//		editQuestionLabel[2].setBounds(50, 170, 100, 50);
		
		/* TextFields 부분 */
		editQuestionTextFields = new JTextField[2];
		editQuestionTextFields[0] = new JTextField(MemberLabel[chooseButton][2].getText().trim());
		editQuestionTextFields[1] = new JTextField(MemberLabel[chooseButton][4].getText().trim());
//		editQuestionTextFields[2] = new JTextField(MemberLabel[chooseButton][4].getText().trim());
		
		editQuestion.add(editQuestionTextFields[0]);
		editQuestion.add(editQuestionTextFields[1]);
//		editQuestion.add(editQuestionTextFields[2]);
		
		editQuestionTextFields[0].setBounds(150, 30, 400, 50);
		editQuestionTextFields[1].setBounds(150, 100, 400, 50);
//		editQuestionTextFields[2].setBounds(150, 170, 400, 50);
		
		/* Button 부분 */
		JButton SuccessButton = new JButton("완료");
		JButton CancelButton = new JButton("취소");
		
		SuccessButton.setActionCommand("edit Success");
		CancelButton.setActionCommand("edit Cancel");
		
		SuccessButton.addActionListener(EditListener);
		CancelButton.addActionListener(EditListener);
		
		editQuestion.add(SuccessButton);
		editQuestion.add(CancelButton);
		SuccessButton.setBounds(350, 250, 100 , 50);
		CancelButton.setBounds(470, 250, 100 , 50);
		
		JButton junk = new JButton();
		editQuestion.add(junk);
		
		editQuestion.setVisible(true);
	}
	
	int addCount = 0, editCount = 0;
	
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		
		System.out.println(actionCmd);
		
		if( actionCmd.equals("Member edit") ){
			System.out.println(chooseButton + "   " + MemberNumber);
			if( chooseButton >= MemberNumber ) {
				errorHandler("Doesn't exist this member");
			}
			
			else if( editCount == 0) {
				editCount = 1;
				editMember();
			}
			else {
//				editQuestionTextFields[0].setText(MemberLabel[chooseButton][0].getText().trim());
				editQuestionTextFields[0].setText(MemberLabel[chooseButton][2].getText().trim());
				editQuestionTextFields[1].setText(MemberLabel[chooseButton][4].getText().trim());
				editQuestion.setVisible(true);
			}
		}
		else if( actionCmd.equals("Member add") ){
			System.out.println(addCount);
			
			if( addCount == 0 ) {
				addCount = 1;
				addMember();
			}
			else {
				addQuestion.setVisible(true);
			}
		}
		else if( actionCmd.equals("Member delete") ){
			System.out.println(chooseButton + "   " + MemberNumber);
			if( chooseButton >= MemberNumber ) {
				errorHandler("Doesn't exist this member");
			}
			else 
				deleteMember();
		}
	}
	
	int chooseButton = 999;
	class MemberButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("Button0")) {
				chooseButton = 0;
			}
			else if( actionCmd.equals("Button1")) {
				chooseButton = 1;
			}
			else if( actionCmd.equals("Button2")) {
				chooseButton = 2;
			}
			else if( actionCmd.equals("Button3")) {
				chooseButton = 3;
			}
			else if( actionCmd.equals("Button4")) {
				chooseButton = 4;
			}
		}
	}
	
	class addListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("add Success")) {
				setAdd();
				MemberNumber++;
				addQuestionTextFields[0].setText("");
				addQuestionTextFields[1].setText("");
//				addQuestionTextFields[2].setText("");
				addQuestion.dispose();
			}
			else if( actionCmd.equals("add Cancel")) {
				System.out.println("Cancel OK");
				addQuestionTextFields[0].setText("");
				addQuestionTextFields[1].setText("");
//				addQuestionTextFields[2].setText("");
				addQuestion.dispose();
			}
		}
	}
	
	public void setAdd() {
		MemberLabel[MemberNumber][0].setText("        " + (MemberNumber+1) );
		MemberLabel[MemberNumber][1].setText("        " +"브론즈");
		MemberLabel[MemberNumber][2].setText("        " +addQuestionTextFields[0].getText());
		MemberLabel[MemberNumber][3].setText("        " +"0");
		MemberLabel[MemberNumber][4].setText("   " +addQuestionTextFields[1].getText());
	}
	
	class deleteListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("delete Success")) {
				for(int i= chooseButton; i<MemberNumber-1; i++) {
					copyButton(i);
				}
				eraseButton(MemberNumber-1);
				MemberNumber--;
				
				deleteQuestion.dispose();
			}
			else if( actionCmd.equals("delete Cancel")) {
				deleteQuestion.dispose();
			}
			
		}
	}
	
	public void copyButton(int index) {
//		MemberLabel[index][0].setText(MemberLabel[index+1][0].getText());
		MemberLabel[index][1].setText(MemberLabel[index+1][1].getText());
		MemberLabel[index][2].setText(MemberLabel[index+1][2].getText());
		MemberLabel[index][3].setText(MemberLabel[index+1][3].getText());
		MemberLabel[index][4].setText(MemberLabel[index+1][4].getText());
	}
	
	public void eraseButton(int index) {
		MemberLabel[index][0].setText(" ");
		MemberLabel[index][1].setText("");
		MemberLabel[index][2].setText("");
		MemberLabel[index][3].setText("");
		MemberLabel[index][4].setText("");
	
	}
	
	public void editButton(int index) {
//		MemberLabel[index][0].setText("        " + editQuestionTextFields[0].getText());
//		MemberLabel[index][1].setText("");
		MemberLabel[index][2].setText("        " + editQuestionTextFields[0].getText());
//		MemberLabel[index][3].setText("");
		MemberLabel[index][4].setText("   " + editQuestionTextFields[1].getText());
	}
	
	class editListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("edit Success")) {
				editButton(chooseButton);
				editQuestion.dispose();
			}
			else if( actionCmd.equals("edit Cancel")) {
				editQuestion.dispose();
			}
		}
	}
	
	JFrame errFrame;
	int errCount = 0;
	JLabel errLabel;
	JButton errButton;
	
	public void errorHandler(String str) {
		
		if( errCount == 0 ) {
			System.out.println("Error OK");
			errFrame = new JFrame("Error!!");
			errFrame.setSize(400, 200);
			errFrame.setLayout(null);
			errFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			errLabel = new JLabel();
			errLabel.setText("Err: " + str );
			errButton = new JButton("확인");
			errButton.setActionCommand("OK");
			errButton.addActionListener(new errListener());
			
			errFrame.add(errLabel);
			errFrame.add(errButton);
			
			errLabel.setBounds(100, 30, 200, 50);
			errButton.setBounds(100, 100, 200, 50);
			errCount = 1;
			errFrame.setVisible(true);
		}
		else {
			errLabel.setText("Err: " + str );
			errFrame.setVisible(true);
		}
	}
	
	class errListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("OK")) {
				errFrame.setVisible(false);
			}
		}
	}
	
	public static String findMember(String name) {
		int i;
		System.out.println(name);
		if( name.equals("" )) {
			return null;
		}
		
		for(i=0; i<5; i++) {
			System.out.println(MemberLabel[i][2].getText());
			if( name.equals(MemberLabel[i][2].getText().trim())) {
				break;
			}
		}
		
		if( i == 5 ) {
			return null;
		}
		else {
			return MemberLabel[i][1].getText().trim();
		}
	}
	
	public static int findMember(String name, int k) {
		int i;
		System.out.println(name);
		
		for(i=0; i<5; i++) {
			System.out.println(MemberLabel[i][2].getText());
			if( name.equals(MemberLabel[i][2].getText().trim())) {
				break;
			}
		}
		
		if( i == 5 ) {
			return -1;
		}
		else {
			return i;
		}
	}
	
	public static void EarnMileage(String name,double point) {
		int i = findMember(name, 12);
		double mileage = Integer.parseInt(MemberLabel[i][3].getText().trim()) + point;
		MemberLabel[i][3].setText( (int)mileage + "" );
		
		if( mileage >= 500.0 && mileage < 1000.0 ) {
			MemberLabel[i][1].setText( "골드" );
		}
		else if( mileage >= 1000.0 ) {
			MemberLabel[i][1].setText( "플레티넘" );
		}
	}
	
	public void resetMileage() {
		for( int i=0; i < MemberNumber; i++) {
			MemberLabel[i][3].setText("0");
		}
	}
}