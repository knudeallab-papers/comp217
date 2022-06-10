package Restraunt;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Tab_Menu extends JPanel implements ActionListener ,Serializable{

	JLabel[][] MenuLabel = new JLabel[10][4];   /* 각 버튼의 정보를 저장하는 라벨 */
	static String[] MenuName = new String[10];
	static int[] MenuPrice = new int[10];
	static int[] MenuPrice2 = new int[10];
	static String[][] MenuMaterial = new String[10][6];
	
	JLabel[][] MenuViewLabel = new JLabel[4][2]; /* 현재 화면에 보여주고 있는 라벨 */
	JTextArea MenuViewTextArea = new JTextArea(15, 70);
	
	JTextField[] materialTextFields;
	
	private final static int ButtonNumber = 10; /* 버튼의 개수 */
	static JButton[] MenuButton = new JButton[ButtonNumber];
	
	public Tab_Menu() {
		setBackground(Color.WHITE);
		setLayout(null);
	
		/* 메뉴 목록 부분 */
		JPanel MenuPanel = new JPanel();
		MenuPanel.setLayout(new GridLayout(ButtonNumber+1,1));
		MenuButtonListener menuButtonListener = new MenuButtonListener();
		
		JLabel menuLabel = new JLabel("                              메뉴 목록");
		MenuPanel.add(menuLabel);
		
		for( int i = 0; i<ButtonNumber; i++) {
			MenuButton[i] = new JButton();
			MenuButton[i].setActionCommand("Button"+i);
			MenuButton[i].addActionListener(menuButtonListener);
			MenuPanel.add(MenuButton[i]);
		}
		
		/* 메뉴 설명 부분 */
		JPanel MenuExplainPanel = new JPanel();
		MenuExplainPanel.setLayout(null);
		
		MenuViewLabel[0][0] = new JLabel("  이름 : ");
		MenuViewLabel[1][0] = new JLabel("  가격 : ");
		MenuViewLabel[2][0] = new JLabel("  생산단가 : ");
		MenuViewLabel[3][0] = new JLabel("  사용된 재료 : ");
		
		MenuExplainPanel.add(MenuViewLabel[0][0]);
		MenuExplainPanel.add(MenuViewLabel[1][0]);
		MenuExplainPanel.add(MenuViewLabel[2][0]);
		MenuExplainPanel.add(MenuViewLabel[3][0]);
		
		MenuViewLabel[0][0].setBounds(0,40,100,30);
		MenuViewLabel[1][0].setBounds(0,80,100,30);
		MenuViewLabel[2][0].setBounds(0,120,100,30);
		MenuViewLabel[3][0].setBounds(0,160,100,30);
		
		MenuViewLabel[0][1] = new JLabel();
		MenuViewLabel[1][1] = new JLabel();
		MenuViewLabel[2][1] = new JLabel();
//		MenuViewLabel[3][1] = new JLabel();
		
		MenuExplainPanel.add(MenuViewLabel[0][1]);
		MenuExplainPanel.add(MenuViewLabel[1][1]);
		MenuExplainPanel.add(MenuViewLabel[2][1]);
		MenuExplainPanel.add(MenuViewTextArea);
//		MenuExplainPanel.add(MenuViewLabel[3][1]);
		
		MenuViewLabel[0][1].setBounds(120,40,100,30);
		MenuViewLabel[1][1].setBounds(120,80,100,30);
		MenuViewLabel[2][1].setBounds(120,120,100,30);
		MenuViewTextArea.setBounds(120,160,100,100);
		
		MenuViewTextArea.setEditable(false);
		MenuViewTextArea.setBackground(Color.LIGHT_GRAY);
//		MenuViewLabel[3][1].setBounds(120,160,100,100);
		
		setMenuLabel(); /* MenuLabel 메모리 할당 */
		
		/* 버튼 부분 */
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new GridLayout(1,3));
		
		JButton addButton = new JButton("추가");
		JButton deleteButton = new JButton("삭제");
		JButton editButton = new JButton("편집");
		
		addButton.setActionCommand("Menu add");
		deleteButton.setActionCommand("Menu delete");
		editButton.setActionCommand("Menu edit");
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		editButton.addActionListener(this);
		
		ButtonPanel.add(addButton);
		ButtonPanel.add(deleteButton);
		ButtonPanel.add(editButton);
		
		add(MenuPanel);
		MenuPanel.setBounds(0, 0, 300, 470);
		add(MenuExplainPanel);
		MenuExplainPanel.setBounds(305,5,270,400);
		add(ButtonPanel);
		ButtonPanel.setBounds(305,410,270,50);
	}
	
	public void setMenuLabel() {
		for(int i=0; i<10; i++) {
			for(int j=0; j<4; j++) {
				MenuLabel[i][j] = new JLabel();
			}
		}
	}
	
	static int MenuNumber = 0;
	JFrame addQuestion = new JFrame("메뉴 추가하기");
	JTextField[] addQuestionTextFields;
	/* 추가 버튼을 눌렸을 때 보여주는 Frame */
	public void addMenu() { 
		addQuestion.setSize(400,400);
		addQuestion.setLayout(null);
		addListener AddListener = new addListener();
		
		/* 라벨 부분 */
		JLabel[] addQuestionLabel = new JLabel[4];
		addQuestionLabel[0] = new JLabel("이름 : ");
		addQuestionLabel[1] = new JLabel("가격 : ");
		addQuestionLabel[2] = new JLabel("생산단가 : ");
		addQuestionLabel[3] = new JLabel("사용된 재료 : ");
		
		addQuestion.add(addQuestionLabel[0]);
		addQuestion.add(addQuestionLabel[1]);
		addQuestion.add(addQuestionLabel[2]);
		addQuestion.add(addQuestionLabel[3]);

		addQuestionLabel[0].setBounds(50, 20, 100, 40);
		addQuestionLabel[1].setBounds(50, 70, 100, 40);
		addQuestionLabel[2].setBounds(50, 120, 100, 40);
		addQuestionLabel[3].setBounds(50, 170, 100, 40);
		
		/* TextFields 부분 */
		addQuestionTextFields = new JTextField[4];
		addQuestionTextFields[0] = new JTextField();
		addQuestionTextFields[1] = new JTextField();
//		addQuestionTextFields[2] = new JTextField();
//		addQuestionTextFields[3] = new JTextField();
		
		materialTextFields = new JTextField[6];
		for(int i=0; i<6; i+=2) {
			materialTextFields[i] = new JTextField();
			materialTextFields[i+1] = new JTextField();
			addQuestion.add(materialTextFields[i]);
			addQuestion.add(materialTextFields[i+1]);
			materialTextFields[i].setBounds(150, 180 + i * 20, 100, 30);
			materialTextFields[i+1].setBounds(150 + 120, 180 + i * 20, 100, 30);
		}
		
		addQuestion.add(addQuestionTextFields[0]);
		addQuestion.add(addQuestionTextFields[1]);
//		addQuestion.add(addQuestionTextFields[2]);
//		addQuestion.add(addQuestionTextFields[3]);
		
		addQuestionTextFields[0].setBounds(150, 20, 200, 40);
		addQuestionTextFields[1].setBounds(150, 70, 200, 40);
//		addQuestionTextFields[2].setBounds(150, 120, 200, 40);
//		addQuestionTextFields[3].setBounds(150, 170, 200, 40);
		
		/* Button 부분 */
		JButton SuccessButton = new JButton("추가하기");
		JButton CancelButton = new JButton("취소하기");
		
		SuccessButton.setActionCommand("add Success");
		CancelButton.setActionCommand("add Cancel");
		
		SuccessButton.addActionListener(AddListener);
		CancelButton.addActionListener(AddListener);
		
		addQuestion.add(SuccessButton);
		addQuestion.add(CancelButton);
		SuccessButton.setBounds(150, 300, 100 , 40);
		CancelButton.setBounds(270, 300, 100 , 40);
		
		JButton junk = new JButton();
		addQuestion.add(junk);
		
		addQuestion.setVisible(true);
	}
	
	JFrame deleteQuestion = new JFrame("메뉴정보 삭제하기");
	JTextField[] deleteQuestionTextFields;
	JLabel deleteConfirmLabel = new JLabel();
	/* 삭제 버튼을 눌렸을 때 보여주는 Frame */
	public void deleteMenu() {
		deleteQuestion.setSize(300,150);
		deleteQuestion.setLayout(null);
		deleteListener DeleteListener = new deleteListener();
		
		deleteConfirmLabel.setText( MenuLabel[chooseButton][0].getText() + " 의 메뉴 정보를 삭제하시겠습니까?");
		deleteQuestion.add(deleteConfirmLabel);
		deleteConfirmLabel.setBounds(40, 10, 220, 30);
		
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
	
	JFrame editQuestion = new JFrame("메뉴 정보 편집하기");
	JTextField[] editQuestionTextFields;
	JLabel editConfirmLabel = new JLabel();
	JTextField[] editmaterialTextFields;
	/* 편집 버튼을 눌렸을 때 보여주는 Frame */
	public void editMenu() {
		editQuestion.setSize(600,350);
		editQuestion.setLayout(null);
		editListener EditListener = new editListener();

		editConfirmLabel.setText((chooseButton+1) + " 의 메뉴 정보를 편집하는 중입니다");
		editQuestion.add(editConfirmLabel);
		editConfirmLabel.setBounds(60, 10, 200, 30);
		
		/* 라벨 부분 */
		JLabel[] editQuestionLabel = new JLabel[4];
		editQuestionLabel[0] = new JLabel("이름 : ");
		editQuestionLabel[1] = new JLabel("가격 : ");
		editQuestionLabel[2] = new JLabel("생산단가 : ");
		editQuestionLabel[3] = new JLabel("사용된 재료 : ");
		
		editQuestion.add(editQuestionLabel[0]);
		editQuestion.add(editQuestionLabel[1]);
		editQuestion.add(editQuestionLabel[2]);
		editQuestion.add(editQuestionLabel[3]);

		editQuestionLabel[0].setBounds(50, 30, 100, 30);
		editQuestionLabel[1].setBounds(50, 80, 100, 30);
		editQuestionLabel[2].setBounds(50, 130, 100, 30);
		editQuestionLabel[3].setBounds(50, 180, 100, 30);
		
		/* TextFields 부분 */
		editQuestionTextFields = new JTextField[4];
		editQuestionTextFields[0] = new JTextField(MenuLabel[chooseButton][0].getText().trim());
		editQuestionTextFields[1] = new JTextField(MenuLabel[chooseButton][1].getText().trim());
//		editQuestionTextFields[2] = new JTextField(MenuLabel[chooseButton][2].getText().trim());
//		editQuestionTextFields[3] = new JTextField(MenuLabel[chooseButton][3].getText().trim());
		editmaterialTextFields = new JTextField[6];
		for(int i=0; i<6; i+=2) {
			editmaterialTextFields[i] = new JTextField(MenuMaterial[chooseButton][i]);
			editmaterialTextFields[i+1] = new JTextField(MenuMaterial[chooseButton][i+1]);
			
//			editmaterialTextFields[i].setText(MenuMaterial[chooseButton][i]);
//			editmaterialTextFields[i].setText();
			
			editQuestion.add(editmaterialTextFields[i]);
			editQuestion.add(editmaterialTextFields[i+1]);
			editmaterialTextFields[i].setBounds(150, 180 + i * 20, 100, 30);
			editmaterialTextFields[i+1].setBounds(150 + 120, 180 + i * 20, 100, 30);
		}
	
		editQuestion.add(editQuestionTextFields[0]);
		editQuestion.add(editQuestionTextFields[1]);
//		editQuestion.add(editQuestionTextFields[2]);
//		editQuestion.add(editQuestionTextFields[3]);
		
		editQuestionTextFields[0].setBounds(150, 30, 400, 50);
		editQuestionTextFields[1].setBounds(150, 80, 400, 50);
//		editQuestionTextFields[2].setBounds(150, 130, 400, 50);
//		editQuestionTextFields[3].setBounds(150, 180, 400, 50);
		
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
		
		if( actionCmd.equals("Menu edit") ){
			System.out.println(chooseButton + "   " + MenuNumber);
			if( chooseButton >= MenuNumber ) {
				errorHandler("Doesn't exist this Menu for edit");
			}
			
			else if( editCount == 0) { /* editCount = 0 일 때 셋팅 */
				editCount = 1;
				editMenu(); /* initMenu Frame */
			}
			else {
				editQuestionTextFields[0].setText(MenuLabel[chooseButton][0].getText().trim());
				editQuestionTextFields[1].setText(MenuLabel[chooseButton][1].getText().trim());
				
				for(int i=0; i<6; i++) {
					editmaterialTextFields[i].setText(MenuMaterial[chooseButton][i]);
				}
//				editQuestionTextFields[2].setText(MenuLabel[chooseButton][2].getText().trim());
//				editQuestionTextFields[3].setText(MenuLabel[chooseButton][3].getText().trim());
				editQuestion.setVisible(true);
			}
		}
		else if( actionCmd.equals("Menu add") ){
			System.out.println(addCount);
			
			if( ButtonNumber <= MenuNumber ) {
				errorHandler("Already Exist Full Menu");
			}
			else if( addCount == 0 ) {
				addCount = 1;
				addMenu();
			}
			else {
//				int n = Container.count;
//				
//				for(int i=0; i<n; i++) {
//					materialLabel[i].setText(Container.ItemName.get(i) + "  (" + Container.ItemPrice.get(i) + ")");
//				}
//				
				addQuestion.setVisible(true);
			}
		}
		else if( actionCmd.equals("Menu delete") ){
			System.out.println(chooseButton + "   " + MenuNumber);
			if( chooseButton >= MenuNumber ) {
				errorHandler("Doesn't exist this Menu for delete");
			}
			else 
				deleteMenu();
		}
	}
	
	int chooseButton = 999;
	class MenuButtonListener implements ActionListener{
		
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
			else if( actionCmd.equals("Button5")) {
				chooseButton = 5;
			}
			else if( actionCmd.equals("Button6")) {
				chooseButton = 6;
			}
			else if( actionCmd.equals("Button7")) {
				chooseButton = 7;
			}
			else if( actionCmd.equals("Button8")) {
				chooseButton = 8;
			}
			
			showButtonInformation(chooseButton);
		}
	}
	
	public void showButtonInformation(int chooseButton) {
		MenuViewLabel[0][1].setText(MenuLabel[chooseButton][0].getText());
		MenuViewLabel[1][1].setText(MenuLabel[chooseButton][1].getText());
		MenuViewLabel[2][1].setText(MenuLabel[chooseButton][2].getText());
		MenuViewTextArea.setText(MenuLabel[chooseButton][3].getText());
//		MenuViewLabel[3][1].setText(MenuLabel[chooseButton][3].getText());
	}
	
	class addListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("add Success")) {
				
				try {
					MenuPrice[MenuNumber] = Integer.parseInt(addQuestionTextFields[1].getText());
//					MenuPrice2[MenuNumber] = Integer.parseInt(addQuestionTextFields[2].getText());
				}
				catch(NumberFormatException a) {
					errorHandler("Put Integer in Price");
					return;
				}
				
				
				if( Container.count == 0 ) {
					errorHandler("창고에 재료를 먼저 추가해주세요 ^^;;");
				}
				else if( Container.findMaterial(materialTextFields[0].getText()) == false ) {
					errorHandler("메뉴에 없는 재료가 있습니다. 창고에서 먼저 메뉴를 추가해주세요^^");
				}
				else if( Container.findMaterial(materialTextFields[1].getText()) == false) {
					errorHandler("메뉴에 없는 재료가 있습니다. 창고에서 먼저 메뉴를 추가해주세요^^");
				}
				else if( Container.findMaterial(materialTextFields[2].getText()) == false) {
					errorHandler("메뉴에 없는 재료가 있습니다. 창고에서 먼저 메뉴를 추가해주세요^^");
				}
				else if( Container.findMaterial(materialTextFields[3].getText()) == false) {
					errorHandler("메뉴에 없는 재료가 있습니다. 창고에서 먼저 메뉴를 추가해주세요^^");
				}
				else if( Container.findMaterial(materialTextFields[4].getText()) == false) {
					errorHandler("메뉴에 없는 재료가 있습니다. 창고에서 먼저 메뉴를 추가해주세요^^");
				}
				else if( Container.findMaterial(materialTextFields[5].getText()) ) {
					MenuButton[MenuNumber].setText(addQuestionTextFields[0].getText());
					MenuName[MenuNumber] = addQuestionTextFields[0].getText();
					
					MenuMaterial[MenuNumber][0] = materialTextFields[0].getText();
					MenuMaterial[MenuNumber][1] = materialTextFields[1].getText();
					MenuMaterial[MenuNumber][2] = materialTextFields[2].getText();
					MenuMaterial[MenuNumber][3] = materialTextFields[3].getText();
					MenuMaterial[MenuNumber][4] = materialTextFields[4].getText();
					MenuMaterial[MenuNumber][5] = materialTextFields[5].getText();
				
					setAdd(MenuNumber); /* 메뉴 설명에 정보 업로드 */
					showButtonInformation(MenuNumber);
					MenuNumber++;
					addQuestionTextFields[0].setText("");
					addQuestionTextFields[1].setText("");
//					addQuestionTextFields[2].setText("");
//					addQuestionTextFields[3].setText("");
					
				}
				materialTextFields[0].setText("");
				materialTextFields[1].setText("");
				materialTextFields[2].setText("");
				materialTextFields[3].setText("");
				materialTextFields[4].setText("");
				materialTextFields[5].setText("");
				
				addQuestion.dispose();
			}
			else if( actionCmd.equals("add Cancel")) {
				System.out.println("Cancel OK");
				addQuestionTextFields[0].setText("");
				addQuestionTextFields[1].setText("");
//				addQuestionTextFields[2].setText("");
//				addQuestionTextFields[3].setText("");
				addQuestion.dispose();
			}
		}
	}
	
	public void setAdd(int index) {
		MenuLabel[index][0].setText(addQuestionTextFields[0].getText());
		MenuLabel[index][1].setText(addQuestionTextFields[1].getText());
		MenuPrice[index] = Integer.parseInt(MenuLabel[index][1].getText());
		MenuPrice2[index] = Container.calculateMaterial(MenuMaterial[index]);
		MenuLabel[index][2].setText(MenuPrice2[index] +"");
//		MenuLabel[index][2].setText(addQuestionTextFields[2].getText());
		MenuLabel[index][3].setText(MenuMaterial[index][0] + "\n" + MenuMaterial[index][1] + "\n" + 
				MenuMaterial[index][2] + "\n" + MenuMaterial[index][3] + "\n" + 
				MenuMaterial[index][4] + "\n" + MenuMaterial[index][5] + "\n" );
	}
	
	class deleteListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("delete Success")) {
				for(int i= chooseButton; i<MenuNumber-1; i++) {
					copyLabel(i); /* 선택된 버튼부터 시작해서 i 버튼에 i+1 버튼들의 정보로 덮어나간다 */ 
				}
				eraseLabel(MenuNumber-1); /* 마지막 버튼의 정보는 지운다 */
				MenuNumber--;
				eraseViewLabel(); /* ViewLabel 의 정보를 지운다 */
				deleteQuestion.dispose();
			}
			else if( actionCmd.equals("delete Cancel")) {
				deleteQuestion.dispose();
			}
			
		}
	}
	
	public void eraseViewLabel() {
		 MenuViewLabel[0][1].setText("");
		 MenuViewLabel[1][1].setText("");
		 MenuViewLabel[2][1].setText("");
		 MenuViewTextArea.setText("");
//		 MenuViewLabel[3][1].setText("");
	}
	public void copyLabel(int index) {
		MenuLabel[index][0].setText(MenuLabel[index+1][0].getText());
		MenuLabel[index][1].setText(MenuLabel[index+1][1].getText());
		MenuLabel[index][2].setText(MenuLabel[index+1][2].getText());
		MenuLabel[index][3].setText(MenuLabel[index+1][3].getText());
		
		MenuButton[index].setText(MenuButton[index+1].getText());
	}
	
	public void eraseLabel(int index) {
		MenuLabel[index][0].setText(" ");
		MenuLabel[index][1].setText("");
		MenuLabel[index][2].setText("");
		MenuLabel[index][3].setText("");
		
		MenuButton[index].setText("");
	}
	
	class editListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("edit Success")) {
				editButton(chooseButton);
				showButtonInformation(chooseButton);
				editQuestion.dispose();
			}
			else if( actionCmd.equals("edit Cancel")) {
				editQuestion.dispose();
			}
		}
	}
	
	public void editButton(int index) {
		MenuLabel[index][0].setText( editQuestionTextFields[0].getText());
		MenuLabel[index][1].setText( editQuestionTextFields[1].getText());
//		MenuLabel[index][2].setText( editQuestionTextFields[2].getText());
		
		MenuMaterial[index][0] = editmaterialTextFields[0].getText();
		MenuMaterial[index][1] = editmaterialTextFields[1].getText();
		MenuMaterial[index][2] = editmaterialTextFields[2].getText();
		MenuMaterial[index][3] = editmaterialTextFields[3].getText();
		MenuMaterial[index][4] = editmaterialTextFields[4].getText();
		MenuMaterial[index][5] = editmaterialTextFields[5].getText();
		
		MenuLabel[index][3].setText(MenuMaterial[index][0] + "\n" + MenuMaterial[index][1] + "\n" + 
				MenuMaterial[index][2] + "\n" + MenuMaterial[index][3] + "\n" + 
				MenuMaterial[index][4] + "\n" + MenuMaterial[index][5] + "\n" );
		
		MenuButton[index].setText( editQuestionTextFields[0].getText() );
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
			
			errLabel.setBounds(100, 30, 250, 50);
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
	
	class materialListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String action = e.getActionCommand();
			
			for(int i=0; i<Container.count; i++) {
				if( action.equals("materialButton"+i) ) {
					
				}
			}
		}
	}
	public static int getMenuNumber() {
		return MenuNumber;
	}
	
	public static String getMenuText(int chooseButton) {
		return MenuButton[chooseButton].getText();
	}
	
	public static int getPrice1(int chooseButton) {
		return MenuPrice[chooseButton];
	}
	
	public static int getPrice2(int chooseButton) {
		return MenuPrice2[chooseButton];
	}
	
	public static String[] getMaterial(int chooseButton) {
		return MenuMaterial[chooseButton];
	}
	
	public static void MenuBackup() {
		try {
			PrintWriter outstream = new PrintWriter(new FileOutputStream("myRestraunt.txt"),true);
			
			for(int i=0; i<MenuNumber; i++) {
				outstream.println(MenuName[i]);
				outstream.println(MenuPrice[i]);
				outstream.println(MenuPrice2[i]);
				for(int j=0; j<6; j++) {
					outstream.println(MenuMaterial[i][j]);
				}
			}
			outstream.close();
		}
		catch( FileNotFoundException e) {
			System.err.println("File Not Found - Menu");
		}
	}
}
