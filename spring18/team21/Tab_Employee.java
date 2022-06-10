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

public class Tab_Employee extends JPanel implements ActionListener, Serializable{

	JPanel EmployeePanel = new JPanel();
	JButton[] EmployeeButton = new JButton[5];
	static JLabel[][] EmployeeLabel = new JLabel[5][6];
	static JLabel[] EmployeeJoinYearLabel = new JLabel[5];
	static JLabel[] EmployeeJoinDateLabel = new JLabel[5];
	
	public Tab_Employee() {
		setBackground(Color.WHITE);
		setLayout(null);
	
		JPanel navPanel = new JPanel();
		navPanel.setLayout(new GridLayout(1,6));
		
		/* 위에 메뉴 */
		JLabel[] navLabel = new JLabel[6];
		navLabel[0] = new JLabel("         번호");
		navLabel[1] = new JLabel("         이름");
		navLabel[2] = new JLabel("         급여");
		navLabel[3] = new JLabel("         직급");
		navLabel[4] = new JLabel("         입사일");
		navLabel[5] = new JLabel("         연락처");
		
		navPanel.add(navLabel[0]);
		navPanel.add(navLabel[1]);
		navPanel.add(navLabel[2]);
		navPanel.add(navLabel[3]);
		navPanel.add(navLabel[4]);
		navPanel.add(navLabel[5]);
		
		/* 밑에 버튼 */
		JPanel ButtonPanel = new JPanel();
		
		JButton[] Button = new JButton[3];
		Button[0] = new JButton("편집");
		Button[1] = new JButton("추가");
		Button[2] = new JButton("삭제");
		
		Button[0].setActionCommand("Employee edit");
		Button[1].setActionCommand("Employee add");
		Button[2].setActionCommand("Employee delete");
		
		Button[0].addActionListener(this);
		Button[1].addActionListener(this);
		Button[2].addActionListener(this);
		
		ButtonPanel.add(Button[0]);
		ButtonPanel.add(Button[1]);
		ButtonPanel.add(Button[2]);

		/* 중간에 회원 정보 */
		
		/* 회원정보 버튼 */
		EmployeeButtonListener ButtonListener = new EmployeeButtonListener();
		EmployeeButton[0] = new JButton();
		EmployeeButton[1] = new JButton();
		EmployeeButton[2] = new JButton();
		EmployeeButton[3] = new JButton();
		EmployeeButton[4] = new JButton();
		
		EmployeeButton[0].setActionCommand("Button0");
		EmployeeButton[1].setActionCommand("Button1");
		EmployeeButton[2].setActionCommand("Button2");
		EmployeeButton[3].setActionCommand("Button3");
		EmployeeButton[4].setActionCommand("Button4");
		
		EmployeeButton[0].addActionListener(ButtonListener);
		EmployeeButton[1].addActionListener(ButtonListener);
		EmployeeButton[2].addActionListener(ButtonListener);
		EmployeeButton[3].addActionListener(ButtonListener);
		EmployeeButton[4].addActionListener(ButtonListener);
		
		/* 회원정보 라벨 */
		EmployeePanel.setLayout(new GridLayout(5,	1));
		EmployeeLabel[0] = new JLabel[6];
		EmployeeLabel[1] = new JLabel[6];
		EmployeeLabel[2] = new JLabel[6];
		EmployeeLabel[3] = new JLabel[6];
		EmployeeLabel[4] = new JLabel[6];
		
		initEmployeeLabel(0,EmployeeLabel[0]);
		initEmployeeLabel(1,EmployeeLabel[1]);
		initEmployeeLabel(2,EmployeeLabel[2]);
		initEmployeeLabel(3,EmployeeLabel[3]);
		initEmployeeLabel(4,EmployeeLabel[4]);
		
		EmployeePanel.add(EmployeeButton[0]);
		EmployeePanel.add(EmployeeButton[1]);
		EmployeePanel.add(EmployeeButton[2]);
		EmployeePanel.add(EmployeeButton[3]);
		EmployeePanel.add(EmployeeButton[4]);
		add(EmployeePanel);
		add(navPanel,BorderLayout.NORTH);
		add(ButtonPanel, BorderLayout.NORTH );
		
		EmployeePanel.setBounds(0,65, 600, 350 );
		navPanel.setBounds(0,0,600,60);
		ButtonPanel.setBounds(0,420,600,70);
	}
	
	public void initEmployeeLabel(int index, JLabel[] EmployeeLabel) {
		EmployeeLabel[0] = new JLabel();
		EmployeeLabel[1] = new JLabel();
		EmployeeLabel[2] = new JLabel();
		EmployeeLabel[3] = new JLabel();
		EmployeeLabel[4] = new JLabel();
		EmployeeLabel[5] = new JLabel();
		
		EmployeeJoinYearLabel[index] = new JLabel();
		EmployeeJoinDateLabel[index] = new JLabel();
		EmployeeLabel[4].setLayout(new GridLayout(2,1));
		EmployeeLabel[4].add(EmployeeJoinYearLabel[index]);
		EmployeeLabel[4].add(EmployeeJoinDateLabel[index]);
		
		EmployeeButton[index].setLayout(new GridLayout(1,5));
		EmployeeButton[index].add(EmployeeLabel[0]);
		EmployeeButton[index].add(EmployeeLabel[1]);
		EmployeeButton[index].add(EmployeeLabel[2]);
		EmployeeButton[index].add(EmployeeLabel[3]);
		EmployeeButton[index].add(EmployeeLabel[4]);
		EmployeeButton[index].add(EmployeeLabel[5]);
	}
	
	static int EmployeeNumber = 0;
	JFrame addQuestion = new JFrame("직원정보 등록하기");
	JTextField[] addQuestionTextFields;
	
	public void addEmployee() {
		addQuestion.setSize(600,500);
		addQuestion.setLayout(null);
		addListener AddListener = new addListener();
		
		/* 라벨 부분 */
		JLabel[] addQuestionLabel = new JLabel[5];
		addQuestionLabel[0] = new JLabel("이름은 : ");
		addQuestionLabel[1] = new JLabel("급여는 : ");
		addQuestionLabel[2] = new JLabel("직급은 : ");
		addQuestionLabel[3] = new JLabel("연락처 : ");
//		addQuestionLabel[4] = new JLabel("연락처는 : ");
		
		addQuestion.add(addQuestionLabel[0]);
		addQuestion.add(addQuestionLabel[1]);
		addQuestion.add(addQuestionLabel[2]);
		addQuestion.add(addQuestionLabel[3]);
//		addQuestion.add(addQuestionLabel[4]);

		addQuestionLabel[0].setBounds(50, 30, 100, 50);
		addQuestionLabel[1].setBounds(50, 100, 100, 50);
		addQuestionLabel[2].setBounds(50, 170, 100, 50);
		addQuestionLabel[3].setBounds(50, 240, 100, 50);
//		addQuestionLabel[4].setBounds(50, 310, 100, 50);
		
		/* TextFields 부분 */
		addQuestionTextFields = new JTextField[5];
		addQuestionTextFields[0] = new JTextField();
		addQuestionTextFields[1] = new JTextField();
		addQuestionTextFields[2] = new JTextField();
		addQuestionTextFields[3] = new JTextField();
//		addQuestionTextFields[4] = new JTextField();
		
		addQuestion.add(addQuestionTextFields[0]);
		addQuestion.add(addQuestionTextFields[1]);
		addQuestion.add(addQuestionTextFields[2]);
		addQuestion.add(addQuestionTextFields[3]);
//		addQuestion.add(addQuestionTextFields[4]);
		
		addQuestionTextFields[0].setBounds(150, 30, 400, 50);
		addQuestionTextFields[1].setBounds(150, 100, 400, 50);
		addQuestionTextFields[2].setBounds(150, 170, 400, 50);
		addQuestionTextFields[3].setBounds(150, 240, 400, 50);
//		addQuestionTextFields[4].setBounds(150, 310, 400, 50);
		
		/* Button 부분 */
		JButton SuccessButton = new JButton("추가하기");
		JButton CancelButton = new JButton("취소하기");
		
		SuccessButton.setActionCommand("add Success");
		CancelButton.setActionCommand("add Cancel");
		
		SuccessButton.addActionListener(AddListener);
		CancelButton.addActionListener(AddListener);
		
		addQuestion.add(SuccessButton);
		addQuestion.add(CancelButton);
		SuccessButton.setBounds(350, 400, 100 , 50);
		CancelButton.setBounds(470, 400, 100 , 50);
		
		JButton junk = new JButton();
		addQuestion.add(junk);
		
		addQuestion.setVisible(true);
	}
	
	JFrame deleteQuestion = new JFrame("직원정보 삭제하기");
	JTextField[] deleteQuestionTextFields;
	JLabel deleteConfirmLabel = new JLabel();
	
	public void deleteEmployee() {
		deleteQuestion.setSize(300,150);
		deleteQuestion.setLayout(null);
		deleteListener DeleteListener = new deleteListener();
		
		
		deleteConfirmLabel.setText((chooseButton+1) + " 의 직원 정보를 삭제하시겠습니까?");
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
	
	JFrame editQuestion = new JFrame("직원정보 편집하기");
	JTextField[] editQuestionTextFields;
	JLabel editConfirmLabel = new JLabel();
	
	
	public void editEmployee() {
		editQuestion.setSize(600,500);
		editQuestion.setLayout(null);
		editListener EditListener = new editListener();

		editConfirmLabel.setText((chooseButton+1) + " 의 직원 정보를 편집하는 중입니다");
		editQuestion.add(editConfirmLabel);
		editConfirmLabel.setBounds(60, 5, 200, 30);
		
		/* 라벨 부분 */
		JLabel[] editQuestionLabel = new JLabel[5];
		editQuestionLabel[0] = new JLabel("이름은 : ");
		editQuestionLabel[1] = new JLabel("급여는 : ");
		editQuestionLabel[2] = new JLabel("직급은 : ");
		editQuestionLabel[3] = new JLabel("연락처는 : ");
//		editQuestionLabel[4] = new JLabel("연락처는 : ");
		
		editQuestion.add(editQuestionLabel[0]);
		editQuestion.add(editQuestionLabel[1]);
		editQuestion.add(editQuestionLabel[2]);
		editQuestion.add(editQuestionLabel[3]);
//		editQuestion.add(editQuestionLabel[4]);

		editQuestionLabel[0].setBounds(50, 30, 100, 50);
		editQuestionLabel[1].setBounds(50, 100, 100, 50);
		editQuestionLabel[2].setBounds(50, 170, 100, 50);
		editQuestionLabel[3].setBounds(50, 240, 100, 50);
//		editQuestionLabel[4].setBounds(50, 310, 100, 50);
		
		/* TextFields 부분 */
		editQuestionTextFields = new JTextField[5];
		editQuestionTextFields[0] = new JTextField(EmployeeLabel[chooseButton][1].getText().trim());
		editQuestionTextFields[1] = new JTextField(EmployeeLabel[chooseButton][2].getText().trim());
		editQuestionTextFields[2] = new JTextField(EmployeeLabel[chooseButton][3].getText().trim());
		editQuestionTextFields[3] = new JTextField(EmployeeLabel[chooseButton][5].getText().trim());
//		editQuestionTextFields[4] = new JTextField(EmployeeLabel[chooseButton][5].getText().trim());
		
		editQuestion.add(editQuestionTextFields[0]);
		editQuestion.add(editQuestionTextFields[1]);
		editQuestion.add(editQuestionTextFields[2]);
		editQuestion.add(editQuestionTextFields[3]);
//		editQuestion.add(editQuestionTextFields[4]);
		
		editQuestionTextFields[0].setBounds(150, 30, 400, 50);
		editQuestionTextFields[1].setBounds(150, 100, 400, 50);
		editQuestionTextFields[2].setBounds(150, 170, 400, 50);
		editQuestionTextFields[3].setBounds(150, 240, 400, 50);
//		editQuestionTextFields[4].setBounds(150, 310, 400, 50);
		
		/* Button 부분 */
		JButton SuccessButton = new JButton("완료");
		JButton CancelButton = new JButton("취소");
		
		SuccessButton.setActionCommand("edit Success");
		CancelButton.setActionCommand("edit Cancel");
		
		SuccessButton.addActionListener(EditListener);
		CancelButton.addActionListener(EditListener);
		
		editQuestion.add(SuccessButton);
		editQuestion.add(CancelButton);
		SuccessButton.setBounds(350, 400, 100 , 50);
		CancelButton.setBounds(470, 400, 100 , 50);
		
		JButton junk = new JButton();
		editQuestion.add(junk);
		
		editQuestion.setVisible(true);
	}
	
	int addCount = 0, editCount = 0;
	
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		
		System.out.println(actionCmd);
		
		if( actionCmd.equals("Employee edit") ){
			System.out.println(chooseButton + "   " + EmployeeNumber);
			if( chooseButton >= EmployeeNumber ) {
				errorHandler("Doesn't exist this Employee");
			}
			
			else if( editCount == 0) {
				editCount = 1;
				editEmployee();
			}
			else {
				editQuestionTextFields[0].setText(EmployeeLabel[chooseButton][1].getText().trim());
				editQuestionTextFields[1].setText(EmployeeLabel[chooseButton][2].getText().trim());
				editQuestionTextFields[2].setText(EmployeeLabel[chooseButton][3].getText().trim());
				editQuestionTextFields[3].setText(EmployeeLabel[chooseButton][5].getText().trim());
//				editQuestionTextFields[4].setText(EmployeeLabel[chooseButton][5].getText().trim());
				editQuestion.setVisible(true);
			}
		}
		else if( actionCmd.equals("Employee add") ){
			System.out.println(addCount);
			
			if( addCount == 0 ) {
				addCount = 1;
				addEmployee();
			}
			else {
				addQuestion.setVisible(true);
			}
		}
		else if( actionCmd.equals("Employee delete") ){
			System.out.println(chooseButton + "   " + EmployeeNumber);
			if( chooseButton >= EmployeeNumber ) {
				errorHandler("Doesn't exist this Employee");
			}
			else 
				deleteEmployee();
		}
	}
	
	int chooseButton = 999;
	class EmployeeButtonListener implements ActionListener{
		
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
				EmployeeNumber++;
				addQuestionTextFields[0].setText("");
				addQuestionTextFields[1].setText("");
				addQuestionTextFields[2].setText("");
				addQuestionTextFields[3].setText("");
//				addQuestionTextFields[4].setText("");
				addQuestion.dispose();
			}
			else if( actionCmd.equals("add Cancel")) {
				System.out.println("Cancel OK");
				addQuestionTextFields[0].setText("");
				addQuestionTextFields[1].setText("");
				addQuestionTextFields[2].setText("");
				addQuestionTextFields[3].setText("");
//				addQuestionTextFields[4].setText("");
				addQuestion.dispose();
			}
		}
	}
	
	public void setAdd() {
		EmployeeLabel[EmployeeNumber][0].setText("        " + (EmployeeNumber+1));
		EmployeeLabel[EmployeeNumber][1].setText("        " +addQuestionTextFields[0].getText());
		EmployeeLabel[EmployeeNumber][2].setText("        " +addQuestionTextFields[1].getText());
		EmployeeLabel[EmployeeNumber][3].setText("        " +addQuestionTextFields[2].getText());
//		EmployeeLabel[EmployeeNumber][4].setText("     " +addQuestionTextFields[3].getText());
		EmployeeJoinYearLabel[EmployeeNumber].setText("    " +Restraunt.get_year() + "년");
		EmployeeJoinDateLabel[EmployeeNumber].setText("    " +Restraunt.get_month() + "월" + Restraunt.get_day() + "일 " );
//		EmployeeLabel[EmployeeNumber][4].setText("     " + Restraunt.get_year() + "년 \n" + Restraunt.get_month() + "월 " + Restraunt.get_day() + "일 ");
		EmployeeLabel[EmployeeNumber][5].setText("     " +addQuestionTextFields[3].getText());
	}
	
	class deleteListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("delete Success")) {
				for(int i= chooseButton; i<EmployeeNumber-1; i++) {
					copyButton(i);
				}
				eraseButton(EmployeeNumber-1);
				EmployeeNumber--;
				
				deleteQuestion.dispose();
			}
			else if( actionCmd.equals("delete Cancel")) {
				deleteQuestion.dispose();
			}
			
		}
	}
	
	public void copyButton(int index) {
//		EmployeeLabel[index][0].setText(EmployeeLabel[index+1][0].getText());
		EmployeeLabel[index][1].setText(EmployeeLabel[index+1][1].getText());
		EmployeeLabel[index][2].setText(EmployeeLabel[index+1][2].getText());
		EmployeeLabel[index][3].setText(EmployeeLabel[index+1][3].getText());
//		EmployeeLabel[index][4].setText(EmployeeLabel[index+1][4].getText());
		EmployeeJoinYearLabel[index].setText(EmployeeJoinYearLabel[index+1].getText());
		EmployeeJoinDateLabel[index].setText(EmployeeJoinDateLabel[index+1].getText());
		EmployeeLabel[index][5].setText(EmployeeLabel[index+1][5].getText());
	}
	
	public void eraseButton(int index) {
		EmployeeLabel[index][0].setText("");
		EmployeeLabel[index][1].setText("");
		EmployeeLabel[index][2].setText("");
		EmployeeLabel[index][3].setText("");
//		EmployeeLabel[index][4].setText("");
		EmployeeJoinYearLabel[index].setText("");
		EmployeeJoinDateLabel[index].setText("");
		EmployeeLabel[index][5].setText("");
	}
	
	public void editButton(int index) {
//		EmployeeLabel[index][1].setText("        " + editQuestionTextFields[1].getText());
//		EmployeeLabel[index][1].setText("");
		EmployeeLabel[index][2].setText("        " + editQuestionTextFields[1].getText());
		EmployeeLabel[index][3].setText("     " + editQuestionTextFields[2].getText());
		EmployeeLabel[index][5].setText("     " + editQuestionTextFields[3].getText());
//		EmployeeLabel[index][5].setText("     " + editQuestionTextFields[4].getText());
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

	public static int getEmployeeFee() {
		int fee = 0;
		
		for(int i=0; i<EmployeeNumber; i++) {
			fee += Integer.parseInt(EmployeeLabel[i][2].getText().trim());
		}
		System.out.println(fee);
		return fee;
	}

	public static void EmployeeBackup() {
		try {
			PrintWriter outstream = new PrintWriter(new FileOutputStream("myRestraunt.txt"),true);
			
			for(int i=0; i<EmployeeNumber; i++) {
				for(int j=0; j<4; j++) {
					String tmp = EmployeeLabel[i][j].getText();
					outstream.println(tmp);
				}
				String tmp = EmployeeJoinYearLabel[i].getText();
				outstream.println(tmp);
				String tmp2 = EmployeeJoinDateLabel[i].getText();
				outstream.println(tmp2);
			}
			
			for(int i=0; i<Tab_Member.MemberNumber; i++) {
				for(int j=0; j<5; j++) {
					String tmp = Tab_Member.MemberLabel[i][j].getText();
					outstream.println(tmp);
				}
			}
			
			outstream.close();
		}
		catch( FileNotFoundException e) {
			System.err.println("File Not Found - Employee");
		}
	}
}
