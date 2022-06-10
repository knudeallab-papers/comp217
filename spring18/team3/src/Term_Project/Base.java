package Term_Project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Base implements ActionListener{
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static int sales = 0;
	public static int balance = 0;
	
	public static JFrame frame = new JFrame();
	public static JPanel basePanel = new JPanel();
	public static JLabel dateLabel = new JLabel();
	public static JLabel moneyLabel = new JLabel();
	JButton finishBtn = new JButton("마감");
	JButton tableBtn = new JButton("테이블");
	JButton storeBtn = new JButton("창고");
	JButton memberBtn = new JButton("회원");
	JButton menuBtn = new JButton("메뉴");
	JButton staffBtn = new JButton("직원");
	JButton endBtn = new JButton("종료");
	public static int year, month, day;
	
	public static TableButton t;
	public static StoreButton s;
	public static MemberButton m;
	public static MenuButton M;
	public static StaffButton S;
	
	JFrame newFrame = new JFrame();
	JLabel label = new JLabel("재료가 충분한 지 확인하십시오.");
	JButton Yes = new JButton("확인");

	public Base(){
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("BaseFile.txt"));
		}catch(FileNotFoundException e) {
			System.out.println(1);
		}
		
		sales = sc.nextInt();
		balance = sc.nextInt();
		year = sc.nextInt();
		month = sc.nextInt();
		day = sc.nextInt();
		
		sc.close();
		
		frame.setTitle("Mingo's");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400,200); // 시작할 때 창의 위치 400, 200
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(null);
		
		basePanel.setBounds(0,0,600,90);
		basePanel.setBackground(Color.WHITE);
		basePanel.setBorder(new LineBorder(Color.GRAY, 2, true));
		basePanel.setLayout(null);
		
		dateLabel = new JLabel(year + "년 " + month + "월 " + day + "일");
		dateLabel.setBounds(10,10,100,20); // 위
		basePanel.add(dateLabel);
		
		moneyLabel = new JLabel("오늘 매출 : " + sales + "원  전체 잔고 : " + balance + "원");
		moneyLabel.setBounds(330,10,265,20);
		basePanel.add(moneyLabel);
		
		finishBtn.setBounds(120,10,50,20);
		finishBtn.addActionListener(this);
		basePanel.add(finishBtn);
		
		tableBtn.setBounds(5,60,50,20);
		tableBtn.addActionListener(this);
		basePanel.add(tableBtn);
		
		storeBtn.setBounds(60,60,50,20);
		storeBtn.addActionListener(this);
		basePanel.add(storeBtn);
		
		memberBtn.setBounds(115,60,50,20);
		memberBtn.addActionListener(this);
		basePanel.add(memberBtn);
		
		menuBtn.setBounds(170,60,50,20);
		menuBtn.addActionListener(this);
		basePanel.add(menuBtn);
		
		staffBtn.setBounds(225,60,50,20);
		staffBtn.addActionListener(this);
		basePanel.add(staffBtn);
		
		endBtn.setBounds(540,55,50,20);
		endBtn.addActionListener(this);
		basePanel.add(endBtn);
		
		frame.add(basePanel);
		
		frame.setVisible(true);
		frame.setVisible(false);
		frame.setVisible(true);
		
		t = new TableButton(frame);
		s = new StoreButton(frame);
		S = new StaffButton(frame);
		m = new MemberButton(frame);
		M = new MenuButton(frame);
		
		s.setVisible(false);
		S.setVisible(false);
		m.setVisible(false);
		M.setVisible(false);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();
		
		if ( btnStr.equals("마감")) {
			if ( isTableEmpty() ) {
				oneMoreDay();
				updateBalance();
				updateStaff();
				updateSales();
				t.setVisible(true);
				s.setVisible(false);
				S.setVisible(false);
				m.setVisible(false);
				M.setVisible(false);
			}else {
				System.err.println("아직 계산하지 않은 테이블이 있습니다.");
			}
		}else if ( btnStr.equals("테이블")) {
			t.setVisible(true);
			t.reset();
			s.setVisible(false);
			S.setVisible(false);
			m.setVisible(false);
			M.setVisible(false);
		}else if ( btnStr.equals("창고")) {
			t.setVisible(false);
			s.setVisible(true);
			s.reset();
			S.setVisible(false);
			m.setVisible(false);
			M.setVisible(false);
		}else if ( btnStr.equals("회원")) {
			t.setVisible(false);
			s.setVisible(false);
			S.setVisible(false);
			m.setVisible(true);
			m.reset();
			M.setVisible(false);
		}else if ( btnStr.equals("메뉴")) {
			t.setVisible(false);
			s.setVisible(false);
			S.setVisible(false);
			m.setVisible(false);
			M.setVisible(true);
			M.reset();
		}else if ( btnStr.equals("직원")) {
			t.setVisible(false);
			s.setVisible(false);
			S.setVisible(true);
			S.reset();
			m.setVisible(false);
			M.setVisible(false);
		}else if ( btnStr.equals("종료")) {
			if ( isTableEmpty()) {
				writeBase();
				s.writeStore();
				m.writeMember();
				M.writeMenu();
				S.writeStaff();
				System.exit(0);
			}else {
				System.err.println("아직 계산하지 않은 테이블이 있습니다.");
			}
		}else {
			System.err.println("Unexpected error.");
		}
		
	}
	
	public boolean isTableEmpty() {
		int[] numberOfOrderedMenu = t.getNumberOfOrderedMenu();
		
		for ( int i = 1 ; i <= 8 ; i++ ) {
			if ( numberOfOrderedMenu[i] != 0 )
				return false;
		}
		
		return true;
	}
	
	public void oneMoreDay() {
		if ( month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ) {
			day++;
			if ( day == 32 ) {
				month++;
				day = 1;
			}
		}else if ( month == 2 ) {
			day++;
			if ( (year%4 == 0 && year%100 != 0) || year%400 == 0) {
				if ( day == 30 ) {
					month++;
					day = 1;
				}
			}else {
				if ( day == 29 ) {
					month++;
					day = 1;
				}
			}
		}else {
			day++;
			if ( day == 31 ) {
				month++;
				day = 1;
			}
		}
		if ( month == 13 ) {
			year++;
			month = 1;
		}
		dateLabel.setVisible(false);
		dateLabel = new JLabel(year + "년 " + month + "월 " + day + "일");
		dateLabel.setBounds(10,10,100,20); // 위
		basePanel.add(dateLabel);
		if ( day == 1 ) {
			m.updateMileage();
		}
	}
	
	public static void updateBalance() {
		balance += sales;
		sales = 0;
		balance -= s.update();
	}
	
	public static void updateSales() {
		moneyLabel.setVisible(false);
		basePanel.setVisible(false);
		moneyLabel = new JLabel("오늘 매출 : " + sales + "원  전체 잔고 : " + balance + "원");
		moneyLabel.setBounds(330,10,265,20);
		basePanel.add(moneyLabel);
		frame.add(basePanel);
		basePanel.setVisible(true);
	}
	
	public int getDay() {
		return day;
	}
	
	public void updateStaff() {
		String delimiters = ".";
		String dayOfStaff = "0";
		String[][] staffData = S.getStaffData();
		int numberOfStaff = S.getNumberOfStaff();
		
		for ( int i = 0 ; i < numberOfStaff ; i++ ) {
			StringTokenizer st = new StringTokenizer(staffData[i][4], delimiters);
			if ( st.hasMoreTokens()) {
				dayOfStaff = st.nextToken();
			}if ( st.hasMoreTokens()) {
				dayOfStaff = st.nextToken();
			}if ( st.hasMoreTokens()) {
				dayOfStaff = st.nextToken();
			}
			if ( day == Integer.parseInt(dayOfStaff)) {
				balance -= Integer.parseInt(staffData[i][2]);
			}
		}
	}
	
	private void writeBase() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream("BaseFile.txt"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		pw.println(sales);
		pw.println(balance);
		pw.println(year);
		pw.println(month);
		pw.println(day);
		
		pw.close();
	}

}
