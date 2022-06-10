package Restraunt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Tab_Table extends JPanel implements ActionListener, Serializable {
	
	private static final int tWIDTH = 410;
	private static final int iWIDTH = 200- 20;
	
	JPanel addFrame_Menu;
	JButton[] addFrame_Button;
	JTextArea[] menuTextArea = new JTextArea[9];
	private static int[] tablePrice = new int[9];
	JLabel[] PriceLabel ;
	
	private JPanel[] table = new JPanel[9]; 
	JTextArea MenuTable;
	JLabel MenuLabel;
	
	private JFrame addFrame = new JFrame("주문창");
	private JFrame payFrame = new JFrame("결제창");
	private JFrame membership = new JFrame("회원 등급");

	private JTextField inputName = new JTextField("", 10);
	private String memberName =  "";
	private JFrame confirmFrame = new JFrame("결제 확인");
	
	//
	JFrame svc3 = new JFrame("서 비 스");
	JFrame svc5 = new JFrame("서 비 스");
	
	JPanel coffeeService = new JPanel();
	JPanel icecreamService = new JPanel();
	
	String currentProjPath;
	
	ImageIcon coffee;
	ImageIcon icecream;
	
	JLabel CoffeeImage;
	JLabel IcecreamImage;
	
	JLabel over3;
	JLabel over5;
	//
	
	public Tab_Table () {
		setLayout(new BorderLayout());
		
		//TableArea
		JPanel tableArea = new JPanel(); 
		tableArea.setLayout(new FlowLayout());
		tableArea.setPreferredSize(new Dimension(tWIDTH, HEIGHT));
		tableArea.setBorder(new LineBorder(Color.BLACK));
		tableArea.setBackground(new Color(170,200,180,255));

		for(int i=0; i<9; i++) {
			table[i] = new JPanel();
			table[i].setPreferredSize(new Dimension(130,140)); table[i].setBackground(Color.WHITE); table[i].setBorder(new LineBorder(Color.BLACK));
		}

		JButton[] tableBtn = new JButton[9];
		tableButtonListener tableListener = new tableButtonListener();
		
		for(int i=0; i<9; i++) {
			tableBtn[i] = new JButton("테이블 " + (i+1));
			tableBtn[i].setActionCommand((i+1) + "");
			tableBtn[i].addActionListener(tableListener); table[i].add(tableBtn[i]); 
		}

		
		PriceLabel = new JLabel[9];
		for(int i=0; i<9; i++) {
			PriceLabel[i] = new JLabel(); table[i].add(PriceLabel[i]);
		}
		
		for(int i=0; i<9; i++) {
			tableArea.add(table[i]);
		}
		
		/* icon */
		currentProjPath = "";
		
		try {
			currentProjPath = new File(".").getCanonicalPath();
		}catch( IOException e) {
			e.printStackTrace();
		}
		
		coffee = new ImageIcon( currentProjPath + "/src/Restraunt/Coffee.png");
		icecream = new ImageIcon( currentProjPath + "/src/Restraunt/Icecream.png");
		
		CoffeeImage = new JLabel(coffee);
		IcecreamImage = new JLabel(icecream);
		
		over3 = new JLabel("서비스 ( 커피 ) 제공\n(3만원 이상 주문)");
		over5 = new JLabel("서비스 ( 아이스크림 ) 제공\n(5만원 이상 주문)");
		//
		
		//InformationArea
		JPanel infoArea = new JPanel();
		infoArea.setLayout(new BorderLayout());
		infoArea.setPreferredSize(new Dimension(iWIDTH, HEIGHT));
		infoArea.setBorder(new LineBorder(Color.BLACK));
		
		MenuLabel = new JLabel("         << 주문 내역 >>  ");
		
		MenuTable = new JTextArea( 30, 20 );
		MenuTable.setLineWrap(true);
		MenuTable.setEditable(false);
		
		for(int i=0; i<9; i++) {
			menuTextArea[i] = new JTextArea(30,20);
		}
		
		JPanel South = new JPanel(); 
		South.setLayout(new GridLayout(1,2));
		South.setBorder(new LineBorder(Color.BLACK));
		
		JButton add = new JButton("추가"); add.addActionListener(this); South.add(add);
		JButton payment = new JButton("결제"); payment.addActionListener(this); South.add(payment);
		South.setBackground(new Color(170,200,180,255));
		
//		infoArea.add(order);
		infoArea.add(MenuLabel, BorderLayout.NORTH);
		infoArea.add(MenuTable);
		infoArea.add("South", South);
		
		
		// 주문창 
		addFrame.setSize(300, 400);
		addFrame.setLayout(new BorderLayout());
		addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addButtonListener ButtonListener = new addButtonListener();
		
		JPanel addBase = new JPanel(); addBase.setLayout(new BorderLayout());
		addFrame_Menu = new JPanel(); addFrame_Menu.setPreferredSize(new Dimension(300,350));  addFrame_Menu.setBorder(new LineBorder(Color.BLACK)); 
		
		addFrame_Menu.setLayout(new GridLayout(10,1));
		addFrame_Button = new JButton[10];
		for(int i=0; i<10; i++) {
			addFrame_Button[i] = new JButton();
			addFrame_Button[i].setActionCommand("Button" + i);
			addFrame_Button[i].addActionListener(ButtonListener);
			addFrame_Menu.add(addFrame_Button[i]);
		}
		
		
		JPanel addFrame_ButtonPanel = new JPanel(); addFrame_ButtonPanel.setPreferredSize(new Dimension(300,40)); addFrame_ButtonPanel.setBorder(new LineBorder(Color.BLACK)); addFrame_ButtonPanel.setBackground(new Color(170,200,180,255));
		JButton addSuccesButton = new JButton("확인"); addSuccesButton.addActionListener(new addActionListener() ); addFrame_ButtonPanel.add(addSuccesButton);
		JButton addExit = new JButton("주문 취소"); addExit.addActionListener(new addActionListener()); addFrame_ButtonPanel.add(addExit);
		addBase.add("North", addFrame_Menu); addBase.add("South", addFrame_ButtonPanel);
		
		addFrame.add(addBase); addFrame.pack();
		
		// 결제창 
		payFrame.setSize(300, 150);
		payFrame.setLayout(new BorderLayout());
		payFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		paymentListener payListener = new paymentListener();
		
		JPanel payBase = new JPanel();payBase.setLayout(new BorderLayout()); 
		JPanel p1 = new JPanel(); p1.setPreferredSize(new Dimension(200,150)); p1.setBorder(new LineBorder(Color.BLACK));
		JPanel p2 = new JPanel(); p2.setPreferredSize(new Dimension(100,150)); p2.setBorder(new LineBorder(Color.BLACK)); p2.setBackground(new Color(170,200,180,255));
		JButton payMember = new JButton("회원 결제"); payMember.addActionListener(payListener); p2.add(payMember);
		JButton payNormal = new JButton("비회원 결제"); payNormal.addActionListener(payListener); p2.add(payNormal);
		JLabel Blank = new JLabel("\n"); p2.add(Blank);
		JButton payCancel = new JButton("결제 취소"); payCancel.addActionListener(payListener); p2.add(payCancel);
		payBase.add("West", p1); payBase.add("East", p2);
		
		
		// 회원 등급? 
		membership.setLayout(new FlowLayout());
		membership.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel mBase = new JPanel();
		JLabel mName = new JLabel(); mName.setText("회원 이름 : ");
		JButton enter = new JButton("회원 입력"); enter.addActionListener(payListener);
		//	private JTextField inputName = new JTextField("", 10);
		mBase.add(mName); mBase.add(inputName); mBase.add(enter);
		
		membership.add(mBase); membership.pack();

		payFrame.add(payBase);
	
		// 결제 확인
		
		//	private JFrame confirmFrame = new JFrame("결제 확인");
		JPanel confirmBase = new JPanel(); confirmBase.setLayout(new BorderLayout());
		
		JPanel memberInfo = new JPanel();
		JLabel confirmMember = new JLabel(); confirmMember.setText("회원 이름 : " + memberName + "\n회원 등급 : " + "골드");
		memberInfo.add(confirmMember);
		
		JPanel payInfo = new JPanel();
		JLabel confirmPrice = new JLabel(); confirmPrice  .setText("총" + 10000 + "원");
		JButton confirmPay = new JButton("승인");
		payInfo.add("North", confirmPrice); payInfo.add("South", confirmPay);
		
		confirmBase.add("West", memberInfo); confirmBase.add("East", payInfo); 	
		confirmFrame.add(confirmBase); confirmFrame.pack();
		
		add("West", tableArea);
		add("East", infoArea);
	}

	int prevTable = -1;
	int currentTable = -1;
	public void select(String act) {
		
		if( prevTable == -1 ) {
			prevTable = Integer.parseInt(act) -1;
		}
		currentTable = Integer.parseInt(act) -1;
		
		table[prevTable].setBackground(Color.WHITE);
		table[currentTable].setBackground(new Color(140,170,220,200));

		prevTable = Integer.parseInt(act) -1;
	}
	
	class tableButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String act = e.getActionCommand();
			
			if( prevTable == -1 ) {
				prevTable = Integer.parseInt(act) -1;
			}
			currentTable = Integer.parseInt(act) -1;
			
			if( tablePrice[prevTable] != 0 ) {
				table[prevTable].setBackground(Color.GRAY);
			}
			else {
				table[prevTable].setBackground(Color.WHITE);
			}
			table[currentTable].setBackground(new Color(140,170,220,200));
			prevTable = Integer.parseInt(act) -1;
			
			MenuLabel.setText( "  << " + (currentTable+1) +"번 테이블 주문 내역 >>" );
			MenuTable.setText(menuTextArea[currentTable].getText());
		}
	}
	
	class paymentListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("회원 결제")) {
				membership.setVisible(true);
			}
			else if( actionCmd.equals("비회원 결제")) {
				
				ImportAccount.writeAccount("수익(비회원) : 총" + tablePrice[currentTable] + "  \n  " + menuTextArea[currentTable].getText(), tablePrice[currentTable]);
			
				paySuccess(tablePrice[currentTable]);
				Restraunt.addtodaySale(tablePrice[currentTable]);
				resetTable(currentTable);
				payFrame.dispose();
			}
			else if( actionCmd.equals("회원 입력")) {
				String Level;
				Level = Tab_Member.findMember(inputName.getText());
				
				if( Level == null ) {
					errorHandler("없는 회원입니다 ^^");
				}
				else {
					double price = 0;
					double mileage = 0;
					if( Level.equals("브론즈") ) {
						price= ( 1 - 0.02) * tablePrice[currentTable] ;
					}
					else if( Level.equals("골드") ) {
						price= ( 1 - 0.05) * tablePrice[currentTable] ;
					}
					else if( Level.equals("플래티넘") ) {
						price= ( 1 - 0.10) * tablePrice[currentTable] ;
					}
					mileage = tablePrice[currentTable] * (0.02);
					
					Tab_Member.EarnMileage(inputName.getText(), mileage);
					ImportAccount.writeAccount("수익(" + inputName.getText() + "회원님 ) : 총" + price + "  \n   " + menuTextArea[currentTable].getText(), price );
					
					paySuccess(inputName.getText(),Level,tablePrice[currentTable]);
					Restraunt.addtodaySale(tablePrice[currentTable]);
					resetTable(currentTable);
				}
				membership.dispose();
				payFrame.dispose();
			}
			if( actionCmd.equals("결제 취소")) {
				payFrame.dispose();
			}
			
		}
	}

	public void resetTable(int chooseTable) {
		tablePrice[chooseTable] = 0;
		table[chooseTable].setBackground(Color.WHITE);
		menuTextArea[chooseTable].setText("");
		PriceLabel[chooseTable].setText("");
		MenuTable.setText(menuTextArea[currentTable].getText());
	}
	
	class addActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("확인")) {
				if( chooseButton >= Tab_Menu.getMenuNumber() ) {
					return;
				}
				
				String[] tmp = Tab_Menu.getMaterial(chooseButton);
				
				if( Container.checkMaterial(tmp) ) {
					Container.Table_minusMaterial(tmp);
					
					if( tablePrice[currentTable] == 0 ) {
						table[currentTable].setBackground(Color.GRAY);
					}
					tablePrice[currentTable] += Tab_Menu.getPrice1(chooseButton);
					menuTextArea[currentTable].setText( menuTextArea[currentTable].getText() + "       " + Tab_Menu.getMenuText(chooseButton) + "  " +Tab_Menu.getPrice1(chooseButton)+ "원\n");
					MenuTable.setText(menuTextArea[chooseButton].getText());
					PriceLabel[currentTable].setText( tablePrice[currentTable] + " 원");
					
					// 서비스
					if(tablePrice[currentTable] >= 30000 && tablePrice[currentTable] < 50000) {
						setServiceFrame3();
						svc3.setVisible(true);
					}
					else if (tablePrice[currentTable] >= 50000) {
						setServiceFrame5();
						svc5.setVisible(true);
					}
				}
				else {
					errorHandler("재료가 부족합니다. 창고에서 재료를 주문해주세요^^");
				}
				
				addFrame.dispose();
			}else if(actionCmd.equals("주문 취소")){
				addFrame.dispose();
			}
		}
	}
	
	int chooseButton = 0;
	class addButtonListener implements ActionListener{
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
			else if( actionCmd.equals("Button9")) {
				chooseButton = 9;
			}
			else if( actionCmd.equals("Button10")) {
				chooseButton = 10;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String act = e.getActionCommand();
		
		if (act == "추가") {
			for(int i=0; i<10; i++) {
				addFrame_Button[i].setText("");
			}
			System.out.println("추가 버튼 ");
			int n = Tab_Menu.getMenuNumber();
			for(int i=0; i<n; i++) {
				addFrame_Button[i].setText( Tab_Menu.getMenuText(i) + "     " + Tab_Menu.getPrice1(i) + " 원");
			}
			
			addFrame.setVisible(true);
		}
		else if (act == "결제") {
			if( tablePrice[currentTable] == 0 ) {
				errorHandler("주문먼저하세요 고객님 ^^");
			}
			else payFrame.setVisible(true);
			// 테이블 계산 금액을 띄운다.
		}
	}
	
	JFrame paySuccessFrame;
	int payCount = 0;
	JLabel paySuccessLabel;
	JButton paySuccessButton;
	public void paySuccess(String name, String Level, int price) {
		
		if( payCount == 0 ) {
			System.out.println("pay Success OK");
			paySuccessFrame = new JFrame("결제 성공!!");
			paySuccessFrame.setSize(400, 200);
			paySuccessFrame.setLayout(null);
			paySuccessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			paySuccessLabel = new JLabel();
			paySuccessLabel.setText( name + "고객님 (" + Level.trim() + ")" + price + "원 결제가 완료되었습니다.");
			paySuccessButton = new JButton("확인");
			paySuccessButton.setActionCommand("OK");
			paySuccessButton.addActionListener(new payListener());
			
			paySuccessFrame.add(paySuccessLabel);
			paySuccessFrame.add(paySuccessButton);
			
			paySuccessLabel.setBounds(100, 30, 300, 50);
			paySuccessButton.setBounds(100, 100, 200, 50);
			payCount = 1;
			paySuccessFrame.setVisible(true);
		}
		else {
			paySuccessLabel.setText( name + "고객님 (" + Level.trim() + ")" + price + "원 결제가 완료되었습니다.");
			paySuccessFrame.setVisible(true);
		}
	}
	
	int paySuccessCount = 0;
	public void paySuccess(int price) {
		
		if( paySuccessCount == 0 ) {
			System.out.println("pay Success OK");
			paySuccessFrame = new JFrame("결제 성공!!");
			paySuccessFrame.setSize(400, 200);
			paySuccessFrame.setLayout(null);
			paySuccessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			paySuccessLabel = new JLabel();
			paySuccessLabel.setText( price + "원 결제가 완료되었습니다.");
			paySuccessButton = new JButton("확인");
			paySuccessButton.setActionCommand("OK");
			paySuccessButton.addActionListener(new payListener());
			
			paySuccessFrame.add(paySuccessLabel);
			paySuccessFrame.add(paySuccessButton);
			
			paySuccessLabel.setBounds(100, 30, 300, 50);
			paySuccessButton.setBounds(100, 100, 200, 50);
			paySuccessCount = 1;
			
			paySuccessFrame.setVisible(true);
		}
		else {
			paySuccessLabel.setText( price + "원 결제가 완료되었습니다.");
			paySuccessFrame.setVisible(true);
		}
	}

	class payListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("OK")) {
				paySuccessFrame.setVisible(false);
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
	
	public static  boolean emptyTable() { // 테이블이 비어있는지 확인해주는 메소드 
		// 남은 테이블의 개수가 0 이면 true를 리턴
		// 아니라면 false 리턴
		for(int i=0; i<9; i++) {
			if( tablePrice[i] != 0 )
				return false;
		}
		return true;
	}
	
	public void setServiceFrame3() {
		svc3.setSize(300,200);
		svc3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		coffeeService.setBackground(Color.WHITE);
		coffeeService.add(CoffeeImage);
		coffeeService.add(over3);

		svc3.add(coffeeService);
	}
	
	public void setServiceFrame5() {
		svc5.setSize(300,200);
		svc5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		icecreamService.setBackground(Color.WHITE);
		icecreamService.add(IcecreamImage);
		icecreamService.add(over5);

		svc5.add(icecreamService);
	}
}