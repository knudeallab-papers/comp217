/**********
 * Filename : Store.java
 * Author   : Team1 - mwJeong, jyRyu
 * Purpose  : Implement store`s main
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class Store extends JFrame implements ActionListener{
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 1080;
	public static final int MAX_TABLE = 8;
	public static final int MAX_MENU = 10;
	
	Calendar cal = Calendar.getInstance();
	
	private boolean isFirst = false;
	private int year = cal.get(Calendar.YEAR);
	private int month = cal.get(Calendar.MONTH) + 1;
	private int day = cal.get(Calendar.DAY_OF_MONTH);
	private double sales = 0;
	private double balance = 0;
	private int curTable = 0;
	private int tableCount = 0;
	private int menuCount = 0;
	private int orderMenu = 0;
	private int curMenu = 0;
	private int curTab = 0;
	
	private int memberCount = 0;
	private int curMember = 0;
	private int orderMember = 0;
	
	private int curWh = 0;
	private int whNumber = 0;
	
	private int empCount = 0;
	private int curEmp = 0;
	
	JLabel dayInfo;
	JLabel moneyInfo;
	JLabel[] tableMenu = new JLabel[9];
	JLabel[] tablePrice = new JLabel[9];
	
	JButton tableBtn = new JButton("테이블");
	JButton warehouseBtn = new JButton("창고");
	JButton memberBtn = new JButton("회원");
	JButton menuBtn = new JButton("메뉴");
	JButton employeeBtn = new JButton("직원");
	JButton addBtn = new JButton("추가");
	JButton payBtn = new JButton("결제");
	JButton[] menuList = new JButton[MAX_MENU];
	
	JPanel mainPanel = new JPanel();
	JPanel tablePanel = new JPanel();
	JPanel tableInfoPanel = new JPanel();
	JPanel tableBtnPanel = new JPanel();
	JPanel tableBigPanel = new JPanel();
	
	Table[] tableInfo = new Table[MAX_TABLE];
	Menu[] menuInfo = new Menu[MAX_MENU];
	
	// add Menu Components
	JFrame addMenu;
	
	JPanel addNamePanel;
	JPanel addPricePanel;
	JPanel addMPricePanel;
	JPanel addBtnPanel;
	JPanel rightTab = new JPanel();
	JPanel[] addMaterialPanel = new JPanel[7];
	JPanel[] addLeftPanel = new JPanel[7];
	JPanel[] addRightPanel = new JPanel[7];
	
	JLabel addNameLabel;
	JLabel addPriceLabel;
	JLabel addMPriceLabel;
	JLabel[] addLeftLabel = new JLabel[7];
	JLabel[] addRightLabel = new JLabel[7];
	
	JButton addEndBtn = new JButton("완료");
	JButton addCancleBtn = new JButton("취소");
	JButton[] tableList = new JButton[8];
	
	JTextField addNameField;
	JTextField addPriceField;
	JTextField addMPriceField;
	JTextField[] addLeftField = new JTextField[7];
	JTextField[] addRightField = new JTextField[7];
	
	Material[] addMaterial = new Material[7];
	
	// member Component
	JButton[] memberIDList = new JButton[7];
	JButton[] memberListBtn = new JButton[8];
	
	JLabel[] memberGradeList = new JLabel[7];
	JLabel[] memberNameList = new JLabel[7];
	JLabel[] memberMileList = new JLabel[7];
	JLabel[] memberCallList = new JLabel[7];
	
	Member[] memberInfo = new Member[7];
	
	JTextField idField;
	JTextField nameField;
	JTextField callField;
	
	// warehouse Component
	Warehouse[] whInfo = new Warehouse[9];
	
	JButton[] whNameList = new JButton[9];
	
	JPanel RightTab;
	
	JLabel[] whCountList = new JLabel[9];
	JLabel[] whOrderList = new JLabel[9];
	JLabel[] whPriceList = new JLabel[9];
	
	JTextField whNameField;
	JTextField whPriceField;
	JTextField whPlaceField;
	JTextField whCallField;
	JTextField whCountField;
	JTextField whOrderField;
	JTextField addOrderField;
	
	// employee Component
	Employee[] empInfo = new Employee[9];
	
	JButton[] empIdList = new JButton[9];
	
	JLabel[] empNameList = new JLabel[9];
	JLabel[] empSalaryList = new JLabel[9];
	JLabel[] empGradeList = new JLabel[9];
	JLabel[] empDayList = new JLabel[9];
	JLabel[] empCallList = new JLabel[9];
	
	JTextField empIdField;
	JTextField empNameField;
	JTextField empSalaryField;
	JTextField empGradeField;
	JTextField empCallField;
	
	public Store() throws IOException{
		super();
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("고급 레스토랑");
		
		setLayout(new BorderLayout());	// for upper tab
		
		// input file
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("data.dat"));
			year = inputStream.readInt();
			month = inputStream.readInt();
			day = inputStream.readInt();
			
			sales = inputStream.readDouble();
			balance = inputStream.readDouble();
			
			tableCount = inputStream.readInt();
			tableInfo = (Table[])inputStream.readObject();
			
			whNumber = inputStream.readInt();
			whInfo = (Warehouse[])inputStream.readObject();
			
			memberCount = inputStream.readInt();
			memberInfo = (Member[])inputStream.readObject();
			
			menuCount = inputStream.readInt();
			menuInfo = (Menu[])inputStream.readObject();
			
			empCount = inputStream.readInt();
			empInfo = (Employee[])inputStream.readObject();
			
			inputStream.close();
		} catch(FileNotFoundException e) {
			isFirst = true;
			System.out.println("Welcome");
		} catch(ClassNotFoundException e) {
			System.out.println("class error");
		}
		
		// Implement upper tab
		JPanel upperTab = new JPanel();
		upperTab.setLayout(new GridLayout(2, 2));
		
		// upper upside Left Tab
		JPanel daySortTab = new JPanel();
		daySortTab.setLayout(new BorderLayout());
		
		JPanel dayTab = new JPanel();
		dayInfo = new JLabel(year + "년 " + month + "월 " + day + "일 ");
		dayTab.add(dayInfo);
		
		JButton buttonEnd = new JButton("마감");
		buttonEnd.addActionListener(this);
		dayTab.add(buttonEnd);
		
		daySortTab.add(dayTab, BorderLayout.WEST);
		
		upperTab.add(daySortTab);
		
		// upper upside Right Tab
		JPanel moneySortTab = new JPanel();
		moneySortTab.setLayout(new BorderLayout());
		moneyInfo = new JLabel("오늘 매출: " + sales + "원 / 전체 잔고: " + balance + "원");
		moneySortTab.add(moneyInfo, BorderLayout.EAST);
		
		upperTab.add(moneySortTab);
		
		// upper downside Left Tab
		JPanel menuTab = new JPanel();
		menuTab.setLayout(new GridLayout(1, 5));
		
		tableBtn.addActionListener(this);
		tableBtn.setOpaque(true);
		menuTab.add(tableBtn);
		
		warehouseBtn.addActionListener(this);
		warehouseBtn.setOpaque(true);
		menuTab.add(warehouseBtn);
		
		memberBtn.addActionListener(this);
		memberBtn.setOpaque(true);
		menuTab.add(memberBtn);
		
		menuBtn.addActionListener(this);
		menuBtn.setOpaque(true);
		menuTab.add(menuBtn);
		
		employeeBtn.addActionListener(this);
		employeeBtn.setOpaque(true);
		menuTab.add(employeeBtn);
		
		menuTab.setBorder(new LineBorder(Color.BLACK, 3));
		upperTab.add(menuTab);
		
		// upper downside Right Tab
		JPanel endPanel = new JPanel();
		endPanel.setLayout(new BorderLayout());
		
		JButton endBtn = new JButton("종료");
		endPanel.add(endBtn, BorderLayout.EAST);
		endBtn.addActionListener(this);
		
		upperTab.add(endPanel);
		upperTab.setBorder(new LineBorder(Color.BLACK, 3));
		
		
		add(upperTab, BorderLayout.NORTH);
		
		// make main panel
		mainPanel.setBorder(new LineBorder(Color.BLACK, 3));
		
		add(mainPanel, BorderLayout.CENTER);
		
		// init setting
		if (isFirst) {
			initTable();
			initMenu();
			initMember();
			initWarehouse();
			initEmployee();
		}
	}
	
	public void showTableTab() {
		mainPanel.removeAll();
		tablePanel.removeAll();
		tableInfoPanel.removeAll();
		
		/*
		mainPanel = new JPanel();
		tablePanel = new JPanel();
		tableInfoPanel = new JPanel();
		tableBtnPanel = new JPanel();
		tableBigPanel = new JPanel();*/
		
		mainPanel.setLayout(new BorderLayout());
		
		tablePanel.setLayout(new GridLayout(2, 4));
		
		// make table button
		for (int i = 0; i < 8; i++ ) {
			int temp = tableInfo[i].getPrice();
			JLabel won = new JLabel(temp + "원");
			
			tableList[i] = new JButton("테이블" + (i+1));
			tableList[i].setLayout(new BorderLayout());

			tableList[i].setOpaque(true);
			tableList[i].addActionListener(this);
			
			if (temp > 0) {
				tableList[i].add(won, BorderLayout.EAST);
			}
			
			tablePanel.add(tableList[i]);
		}
		
		mainPanel.add(tablePanel, BorderLayout.CENTER);
		
		tableBtnPanel = new JPanel();
		
		// make table info
		tableInfoPanel.setLayout(new GridLayout(10, 2));
		tableInfoPanel.setBorder(new LineBorder(Color.BLACK, 3));
		tableBtnPanel.setLayout(new FlowLayout());
		tableBtnPanel.setBorder(new LineBorder(Color.BLACK, 3));
		tableBigPanel.setLayout(new BorderLayout());
		
		JLabel dishMenu = new JLabel("메뉴");
		tableInfoPanel.add(dishMenu);
		dishMenu.setHorizontalAlignment(SwingConstants.CENTER);
		dishMenu.setBorder(new LineBorder(Color.BLACK, 3));
		
		JLabel dishPrice = new JLabel("가격");
		dishPrice.setBorder(new LineBorder(Color.BLACK, 3));
		dishPrice.setHorizontalAlignment(SwingConstants.CENTER);
		tableInfoPanel.add(dishPrice);
		
		for (int i = 0; i < 9; i++) {
			tableMenu[i] = new JLabel();
			tableMenu[i].setBorder(new LineBorder(Color.BLACK, 3));
			tablePrice[i] = new JLabel();
			tablePrice[i].setBorder(new LineBorder(Color.BLACK, 3));
			
			tableInfoPanel.add(tableMenu[i]);
			tableInfoPanel.add(tablePrice[i]);
		}
			
		addBtn = new JButton("추가");
		addBtn.setActionCommand("테이블 추가");
		addBtn.addActionListener(this);
		tableBtnPanel.add(addBtn);
		
		payBtn = new JButton("결제");
		payBtn.addActionListener(this);
		tableBtnPanel.add(payBtn);
		
		tableBigPanel.add(tableInfoPanel);
		tableBigPanel.add(tableBtnPanel, BorderLayout.SOUTH);
		mainPanel.add(tableBigPanel, BorderLayout.EAST);

		mainPanel.revalidate();
		mainPanel.repaint();
		tableInfoPanel.revalidate();
		tableInfoPanel.repaint();
	}
	
	public void showMenuTab() {
		mainPanel.removeAll();
		
		mainPanel.setLayout(new BorderLayout());
		
		JPanel leftTab = new JPanel();
		JPanel leftList = new JPanel();
		JPanel bottom = new JPanel();
		
		leftTab.setLayout(new BorderLayout());
		leftTab.setBorder(new LineBorder(Color.BLACK, 3));
		bottom.setBorder(new LineBorder(Color.BLACK, 3));
		
		leftList.setLayout(new GridLayout(10, 1));
		for (int i = 0; i < menuCount; i++) {
			menuList[i] = new JButton(menuInfo[i].getName());
			menuList[i].setActionCommand("메뉴" + (i+1));
			menuList[i].setOpaque(true);
			menuList[i].addActionListener(this);
			leftList.add(menuList[i]);
		}
		
		JButton menuAddBtn = new JButton("추가");
		menuAddBtn.setActionCommand("메뉴 추가");
		menuAddBtn.addActionListener(this);
		bottom.add(menuAddBtn, BorderLayout.EAST);
		
		leftTab.add(leftList, BorderLayout.CENTER);
		leftTab.add(bottom, BorderLayout.SOUTH);
		
		mainPanel.add(leftTab, BorderLayout.WEST);
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public void showMemberTab() {
		mainPanel.removeAll();
		
		mainPanel.setLayout(new BorderLayout());
		
		// info tab setting
		JPanel memberInfoPanel = new JPanel();
		memberInfoPanel.setLayout(new GridLayout(8, 5));
		
		JLabel memberIDLabel = new JLabel("번호");
		memberIDLabel.setBorder(new LineBorder(Color.BLACK, 3));
		memberIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memberInfoPanel.add(memberIDLabel);
		
		JLabel memberGradeLabel = new JLabel("등급");
		memberGradeLabel.setBorder(new LineBorder(Color.BLACK, 3));
		memberGradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memberInfoPanel.add(memberGradeLabel);
		
		JLabel memberNameLabel = new JLabel("이름");
		memberNameLabel.setBorder(new LineBorder(Color.BLACK, 3));
		memberNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memberInfoPanel.add(memberNameLabel);
		
		JLabel memberMileLabel = new JLabel("마일리지");
		memberMileLabel.setBorder(new LineBorder(Color.BLACK, 3));
		memberMileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memberInfoPanel.add(memberMileLabel);
		
		JLabel memberCallLabel = new JLabel("연락처");
		memberCallLabel.setBorder(new LineBorder(Color.BLACK, 3));
		memberCallLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memberInfoPanel.add(memberCallLabel);
		
		mainPanel.add(memberInfoPanel, BorderLayout.CENTER);
		
		for (int i = 0; i < 7; i++) {
			memberIDList[i] = new JButton();
			memberIDList[i].setOpaque(true);
			memberIDList[i].setBackground(Color.BLACK);
			memberGradeList[i] = new JLabel();
			memberGradeList[i].setBorder(new LineBorder(Color.BLACK, 3));
			memberNameList[i] = new JLabel();
			memberNameList[i].setBorder(new LineBorder(Color.BLACK, 3));
			memberMileList[i] = new JLabel();
			memberMileList[i].setBorder(new LineBorder(Color.BLACK, 3));
			memberCallList[i] = new JLabel();
			memberCallList[i].setBorder(new LineBorder(Color.BLACK, 3));
			
			if (i < memberCount) {
				memberIDList[i].addActionListener(this);
				memberIDList[i].setText("" + memberInfo[i].getId());
				memberIDList[i].setActionCommand("회원" + (i+1));
				memberIDList[i].setOpaque(true);
				memberIDList[i].setHorizontalAlignment(SwingConstants.CENTER);
				
				memberGradeList[i].setText("" + memberInfo[i].getGrade());
				memberGradeList[i].setOpaque(true);
				memberGradeList[i].setHorizontalAlignment(SwingConstants.CENTER);
				memberGradeList[i].setBackground(Color.LIGHT_GRAY);
				
				memberNameList[i].setText(memberInfo[i].getName());
				memberNameList[i].setOpaque(true);
				memberNameList[i].setHorizontalAlignment(SwingConstants.CENTER);
				memberNameList[i].setBackground(Color.LIGHT_GRAY);
				
				memberMileList[i].setText("" + memberInfo[i].getMile());
				memberMileList[i].setOpaque(true);
				memberMileList[i].setHorizontalAlignment(SwingConstants.CENTER);
				memberMileList[i].setBackground(Color.LIGHT_GRAY);
				
				memberCallList[i].setText(memberInfo[i].getCall());
				memberCallList[i].setOpaque(true);
				memberCallList[i].setHorizontalAlignment(SwingConstants.CENTER);
				memberCallList[i].setBackground(Color.LIGHT_GRAY);
			}
			
			memberInfoPanel.add(memberIDList[i]);
			memberInfoPanel.add(memberGradeList[i]);
			memberInfoPanel.add(memberNameList[i]);
			memberInfoPanel.add(memberMileList[i]);
			memberInfoPanel.add(memberCallList[i]);
		}
		
		// Button settings
		JPanel memberBtnPanel = new JPanel();
		memberBtnPanel.setLayout(new FlowLayout());
		memberBtnPanel.setBorder(new LineBorder(Color.BLACK, 5));
		
		JButton memberEditBtn = new JButton("편집");
		memberEditBtn.setActionCommand("회원 편집");
		memberEditBtn.addActionListener(this);
		memberBtnPanel.add(memberEditBtn);
		
		JButton memberAddBtn = new JButton("추가");
		memberAddBtn.setActionCommand("회원 추가");
		memberAddBtn.addActionListener(this);
		memberBtnPanel.add(memberAddBtn);
		
		JButton memberRemoveBtn = new JButton("삭제");
		memberRemoveBtn.setActionCommand("회원 삭제");
		memberRemoveBtn.addActionListener(this);
		memberBtnPanel.add(memberRemoveBtn);
		
		mainPanel.add(memberBtnPanel, BorderLayout.SOUTH);
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public void showWarehouseTab() {
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(1, 2));
		
		JPanel LeftTab = new JPanel();
		RightTab = new JPanel();
		mainPanel.add(LeftTab);
		mainPanel.add(RightTab);
		
		
		// setting LeftTab
		LeftTab.setLayout(new BorderLayout());
		JPanel LUTab = new JPanel();
		JPanel LDTab = new JPanel();
		LUTab.setBorder(new LineBorder(Color.BLACK, 5));
		LDTab.setBorder(new LineBorder(Color.BLACK, 5));
		LeftTab.add(LUTab, BorderLayout.CENTER);
		LeftTab.add(LDTab, BorderLayout.SOUTH);
		LUTab.setLayout(new GridLayout(10, 4));
		LDTab.setLayout(new FlowLayout());
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBorder(new LineBorder(Color.BLACK, 3));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LUTab.add(nameLabel);
		
		JLabel countLabel = new JLabel("재고");
		countLabel.setBorder(new LineBorder(Color.BLACK, 3));
		countLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LUTab.add(countLabel);
		
		JLabel orderLabel = new JLabel("주문");
		orderLabel.setBorder(new LineBorder(Color.BLACK, 3));
		orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LUTab.add(orderLabel);
		
		JLabel priceLabel = new JLabel("가격");
		priceLabel.setBorder(new LineBorder(Color.BLACK, 3));
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LUTab.add(priceLabel);
		
		for (int i = 0; i < 9; i++) {
			whNameList[i] = new JButton();
			whNameList[i].setOpaque(true);
			whNameList[i].setBackground(Color.BLACK);
			whCountList[i] = new JLabel();
			whCountList[i].setBorder(new LineBorder(Color.BLACK, 3));
			whOrderList[i] = new JLabel();
			whOrderList[i].setBorder(new LineBorder(Color.BLACK, 3));
			whPriceList[i] = new JLabel();
			whPriceList[i].setBorder(new LineBorder(Color.BLACK, 3));
			
			if (i < whNumber) {
				whNameList[i].addActionListener(this);
				whNameList[i].setText(whInfo[i].getName());
				whNameList[i].setActionCommand("창고" + (i+1));
				whNameList[i].setOpaque(true);
				whNameList[i].setHorizontalAlignment(SwingConstants.CENTER);
				
				whCountList[i].setText("" + whInfo[i].getCount());
				whCountList[i].setOpaque(true);
				whCountList[i].setHorizontalAlignment(SwingConstants.CENTER);
				whCountList[i].setBackground(Color.LIGHT_GRAY);
				
				whOrderList[i].setText("" + whInfo[i].getOrder());
				whOrderList[i].setOpaque(true);
				whOrderList[i].setHorizontalAlignment(SwingConstants.CENTER);
				whOrderList[i].setBackground(Color.LIGHT_GRAY);
				
				whPriceList[i].setText("" + whInfo[i].getPrice()*whInfo[i].getOrder() + "원");
				whPriceList[i].setOpaque(true);
				whPriceList[i].setHorizontalAlignment(SwingConstants.CENTER);
				whPriceList[i].setBackground(Color.LIGHT_GRAY);
			}
			
			LUTab.add(whNameList[i]);
			LUTab.add(whCountList[i]);
			LUTab.add(whOrderList[i]);
			LUTab.add(whPriceList[i]);
			
		}
		
		JButton whAddBtn = new JButton("추가");
		whAddBtn.setActionCommand("창고 추가");
		whAddBtn.addActionListener(this);
		JButton whRemoveBtn = new JButton("삭제");
		whRemoveBtn.setActionCommand("창고 삭제");
		whRemoveBtn.addActionListener(this);
		
		LDTab.add(whAddBtn);
		LDTab.add(whRemoveBtn);
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public void showEmployeeTab() {
		mainPanel.removeAll();
		
		mainPanel.setLayout(new BorderLayout());
		
		JPanel upperTab = new JPanel();
		upperTab.setLayout(new GridLayout(10 , 6));
		upperTab.setBorder(new LineBorder(Color.BLACK, 3));
		
		JLabel empIdLabel = new JLabel("번호");
		empIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empIdLabel.setBorder(new LineBorder(Color.BLACK, 3));
		upperTab.add(empIdLabel);
		
		JLabel empNameLabel = new JLabel("이름");
		empNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empNameLabel.setBorder(new LineBorder(Color.BLACK, 3));
		upperTab.add(empNameLabel);
		
		JLabel empSalaryLabel = new JLabel("급여");
		empSalaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empSalaryLabel.setBorder(new LineBorder(Color.BLACK, 3));
		upperTab.add(empSalaryLabel);
		
		JLabel empGradeLabel = new JLabel("직급");
		empGradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empGradeLabel.setBorder(new LineBorder(Color.BLACK, 3));
		upperTab.add(empGradeLabel);
		
		JLabel empDayLabel = new JLabel("입사일");
		empDayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empDayLabel.setBorder(new LineBorder(Color.BLACK, 3));
		upperTab.add(empDayLabel);
		
		JLabel empCallLabel = new JLabel("연락처");
		empCallLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empCallLabel.setBorder(new LineBorder(Color.BLACK, 3));
		upperTab.add(empCallLabel);
		
		for (int i = 0; i < 9; i++) {
			
			empIdList[i] = new JButton();
			empIdList[i].setOpaque(true);
			empIdList[i].setBackground(Color.BLACK);
			empNameList[i] = new JLabel();
			empNameList[i].setBorder(new LineBorder(Color.BLACK, 3));
			empSalaryList[i] = new JLabel();
			empSalaryList[i].setBorder(new LineBorder(Color.BLACK, 3));
			empGradeList[i] = new JLabel();
			empGradeList[i].setBorder(new LineBorder(Color.BLACK, 3));
			empDayList[i] = new JLabel();
			empDayList[i].setBorder(new LineBorder(Color.BLACK, 3));
			empCallList[i] = new JLabel();
			empCallList[i].setBorder(new LineBorder(Color.BLACK, 3));
			
			if (i < empCount ) {
				empIdList[i].addActionListener(this);
				empIdList[i].setText("" + empInfo[i].getId());
				empIdList[i].setActionCommand("직원" + (i+1));
				empIdList[i].setOpaque(true);
				empIdList[i].setHorizontalAlignment(SwingConstants.CENTER);
				
				empNameList[i].setText(empInfo[i].getName());
				empNameList[i].setOpaque(true);
				empNameList[i].setHorizontalAlignment(SwingConstants.CENTER);
				empNameList[i].setBackground(Color.LIGHT_GRAY);
				
				empSalaryList[i].setText("" + empInfo[i].getSalary() + "원");
				empSalaryList[i].setOpaque(true);
				empSalaryList[i].setHorizontalAlignment(SwingConstants.CENTER);
				empSalaryList[i].setBackground(Color.LIGHT_GRAY);
				
				empGradeList[i].setText(empInfo[i].getGrade());
				empGradeList[i].setOpaque(true);
				empGradeList[i].setHorizontalAlignment(SwingConstants.CENTER);
				empGradeList[i].setBackground(Color.LIGHT_GRAY);
				
				empDayList[i].setText(empInfo[i].getDay());
				empDayList[i].setOpaque(true);
				empDayList[i].setHorizontalAlignment(SwingConstants.CENTER);
				empDayList[i].setBackground(Color.LIGHT_GRAY);
				
				empCallList[i].setText(empInfo[i].getCall());
				empCallList[i].setOpaque(true);
				empCallList[i].setHorizontalAlignment(SwingConstants.CENTER);
				empCallList[i].setBackground(Color.LIGHT_GRAY);
			}
			
			upperTab.add(empIdList[i]);
			upperTab.add(empNameList[i]);
			upperTab.add(empSalaryList[i]);
			upperTab.add(empGradeList[i]);
			upperTab.add(empDayList[i]);
			upperTab.add(empCallList[i]);
			
		}
		
		JPanel downTab = new JPanel();
		downTab.setLayout(new FlowLayout());
		downTab.setBorder(new LineBorder(Color.BLACK, 3));
		
		JButton empEditBtn = new JButton("편집");
		empEditBtn.setActionCommand("직원 편집");
		empEditBtn.addActionListener(this);
		downTab.add(empEditBtn);
		
		JButton empAddBtn = new JButton("추가");
		empAddBtn.setActionCommand("직원 추가");
		empAddBtn.addActionListener(this);
		downTab.add(empAddBtn);
		
		JButton empRemoveBtn = new JButton("삭제");
		empRemoveBtn.setActionCommand("직원 삭제");
		empRemoveBtn.addActionListener(this);
		downTab.add(empRemoveBtn);
		
		mainPanel.add(upperTab, BorderLayout.CENTER);
		mainPanel.add(downTab, BorderLayout.SOUTH);
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public void initMenu() {
		for (int i = 0; i < MAX_MENU; i++) {
			menuInfo[i] = new Menu();
			menuList[i] = new JButton();
			menuList[i].setOpaque(true);
		}
	}
	
	public void initTable() {
		for (int i = 0; i < 8; i++)
			tableInfo[i] = new Table();
	}
	
	public void initMember() {
		for (int i = 0; i < 7; i++)
			memberInfo[i] = new Member();
	}
	
	public void initWarehouse() {
		for (int i = 0; i < 9; i++)
			whInfo[i] = new Warehouse();
	}
	
	public void initEmployee() {
		for (int i = 0; i < 9; i++)
			empInfo[i] = new Employee();
	}
	
	public void showTableInfo() {
		int limit = tableInfo[curTable].getIdx();
		
		for (int i = 0; i < 8; i++) {
			if (i == curTable)
				tableList[i].setBackground(Color.BLACK);
			else if (tableInfo[i].getPrice() > 0)
				tableList[i].setBackground(Color.LIGHT_GRAY);
			else
				tableList[i].setBackground(Color.WHITE);
		}
		
		for (int i = 0; i < limit; i++) {
			tableMenu[i].setText(tableInfo[curTable].dish[i].getName());
			tableMenu[i].setOpaque(true);
			tableMenu[i].setBackground(Color.WHITE);
			tablePrice[i].setText("" + tableInfo[curTable].dish[i].getPrice());
			tablePrice[i].setOpaque(true);
			tablePrice[i].setBackground(Color.WHITE);
		}
		for (int i = limit; i < 9; i++) {
			tableMenu[i].setText("");
			tablePrice[i].setText("");
		}
	}
	
	public void procMenuBtn() {
		int limit = menuInfo[curMenu].getIdx();
		mainPanel.remove(rightTab);
		
		menuList[curMenu].setBackground(Color.GRAY);
		for (int i = 0; i < menuCount; i++)
			if (i != curMenu)
				menuList[i].setBackground(Color.WHITE);
		
		// setting upper
		rightTab = new JPanel();
		rightTab.setLayout(new BorderLayout());
		
		// setting Text
		JTextArea mInfo = new JTextArea(10, 15);
		StringBuffer settingText = new StringBuffer();
		settingText.append("이름 : " + menuInfo[curMenu].getName() +
				"\n가격 : " + menuInfo[curMenu].getPrice() +
				"\n생산단가 : " + menuInfo[curMenu].getMPrice() +
				"\n사용된 재료:\n");
		for (int i = 0; i < limit; i++)
			settingText.append(menuInfo[curMenu].material[i].getName() + "(" + menuInfo[curMenu].material[i].getPrice() + "원)\n");
			
		mInfo.setText(settingText.toString());
		mInfo.setFont(new Font("SanSerif", Font.BOLD, 30));
		mInfo.setEditable(false);
		rightTab.add(mInfo, BorderLayout.CENTER);
		
		// setting bottom
		JPanel rightBtnPanel = new JPanel(new FlowLayout());
		rightBtnPanel.setLayout(new FlowLayout());
		
		JButton menuEditBtn = new JButton("편집");
		menuEditBtn.setActionCommand("메뉴 편집");
		menuEditBtn.addActionListener(this);
		rightBtnPanel.add(menuEditBtn);
		
		JButton menuRemoveBtn = new JButton("삭제");
		menuRemoveBtn.setActionCommand("메뉴 삭제");
		menuRemoveBtn.addActionListener(this);
		rightBtnPanel.add(menuRemoveBtn);

		rightTab.add(rightBtnPanel, BorderLayout.SOUTH);
		
		mainPanel.add(rightTab, BorderLayout.CENTER);
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	public void procMemberBtn() {
		
		memberListBtn[orderMember].setBackground(Color.GRAY);
		for (int i = 0; i < 8; i++)
			if (i != orderMember)
				memberListBtn[i].setBackground(Color.WHITE);
	}
	
	public void menuBtnChange() {
		menuList[orderMenu].setBackground(Color.GRAY);
		for (int i = 0; i < 10; i++)
			if (i != orderMenu)
				menuList[i].setBackground(Color.WHITE);
	}
	
	public void memberBtnChange() {
		memberIDList[curMember].setBackground(Color.BLUE);
		for (int i = 0; i < 7; i++)
			if (i != curMember)
				memberIDList[i].setBackground(Color.BLACK);
	}
	
	public void whBtnChange() {
		whNameList[curWh].setBackground(Color.BLUE);
		for (int i = 0; i < 9; i++)
			if (i != curWh)
				whNameList[i].setBackground(Color.BLACK);
		
		mainPanel.remove(RightTab);
		RightTab = new JPanel();
		RightTab.setLayout(new BorderLayout());
		
		JTextArea mInfo = new JTextArea(10, 15);
		StringBuffer settingText = new StringBuffer();
		
		settingText.append("이름 : " + whInfo[curWh].getName() +
				"\n가격 : " + whInfo[curWh].getPrice() +
				"\n판매처 : " + whInfo[curWh].getPlace() +
				"\n연락처 : " + whInfo[curWh].getCall() +
				"\n수량 : " + whInfo[curWh].getCount() +
				"\n주문량 : " + whInfo[curWh].getOrder());
		
			
		mInfo.setText(settingText.toString());
		mInfo.setFont(new Font("SanSerif", Font.BOLD, 30));
		mInfo.setEditable(false);
		RightTab.add(mInfo, BorderLayout.CENTER);
		
		// setting bottom
		JPanel rightBtnPanel = new JPanel(new FlowLayout());
		rightBtnPanel.setLayout(new FlowLayout());
		rightBtnPanel.setBorder(new LineBorder(Color.BLACK, 3));
		
		JButton whOrderBtn = new JButton("주문");
		whOrderBtn.setActionCommand("재료 주문");
		whOrderBtn.addActionListener(this);
		rightBtnPanel.add(whOrderBtn);
		
		JButton whCancleBtn = new JButton("주문 취소");
		whCancleBtn.setActionCommand("재료 주문 취소");
		whCancleBtn.addActionListener(this);
		rightBtnPanel.add(whCancleBtn);

		RightTab.add(rightBtnPanel, BorderLayout.SOUTH);
		RightTab.setBorder(new LineBorder(Color.BLACK));
		
		mainPanel.add(RightTab, BorderLayout.CENTER);
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public void empBtnChange() {
		empIdList[curEmp].setBackground(Color.BLUE);
		for (int i = 0; i < 9; i++)
			if (i != curEmp)
				empIdList[i].setBackground(Color.BLACK);
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("종료")) {
			try {
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("data.dat"));
				outputStream.writeInt(year);
				outputStream.writeInt(month);
				outputStream.writeInt(day);
				
				outputStream.writeDouble(sales);
				outputStream.writeDouble(balance);
				
				outputStream.writeInt(tableCount);
				outputStream.writeObject(tableInfo);
				
				outputStream.writeInt(whNumber);
				outputStream.writeObject(whInfo);
				
				outputStream.writeInt(memberCount);
				outputStream.writeObject(memberInfo);
				
				outputStream.writeInt(menuCount);
				outputStream.writeObject(menuInfo);
				
				outputStream.writeInt(empCount);
				outputStream.writeObject(empInfo);
				
				outputStream.close();
			} catch(IOException error) {
				System.out.println("end");
			}
			
			System.exit(0);
		}
		else if (cmd.equals("테이블")) {
			curTab = 0;
			tableBtn.setBackground(Color.GRAY);
			warehouseBtn.setBackground(Color.WHITE);
			memberBtn.setBackground(Color.WHITE);
			menuBtn.setBackground(Color.WHITE);
			employeeBtn.setBackground(Color.WHITE);
			showTableTab();
		}
		else if (cmd.equals("메뉴")) {
			curTab = 3;
			tableBtn.setBackground(Color.WHITE);
			warehouseBtn.setBackground(Color.WHITE);
			memberBtn.setBackground(Color.WHITE);
			menuBtn.setBackground(Color.GRAY);
			employeeBtn.setBackground(Color.WHITE);
			showMenuTab();
		}
		else if (cmd.equals("창고")) {
			curTab = 1;
			tableBtn.setBackground(Color.WHITE);
			warehouseBtn.setBackground(Color.GRAY);
			memberBtn.setBackground(Color.WHITE);
			menuBtn.setBackground(Color.WHITE);
			employeeBtn.setBackground(Color.WHITE);
			showWarehouseTab();
		}
		else if (cmd.equals("회원")) {
			curTab = 2;
			tableBtn.setBackground(Color.WHITE);
			warehouseBtn.setBackground(Color.WHITE);
			memberBtn.setBackground(Color.GRAY);
			menuBtn.setBackground(Color.WHITE);
			employeeBtn.setBackground(Color.WHITE);
			showMemberTab();
		}
		else if (cmd.equals("직원")) {
			curTab = 4;
			tableBtn.setBackground(Color.WHITE);
			warehouseBtn.setBackground(Color.WHITE);
			memberBtn.setBackground(Color.WHITE);
			menuBtn.setBackground(Color.WHITE);
			employeeBtn.setBackground(Color.GRAY);
			showEmployeeTab();
		}
		else if (cmd.equals("테이블1")) {
			curTable = 0;
			showTableInfo();
		}
		else if (cmd.equals("테이블2")) {
			curTable = 1;
			showTableInfo();
		}
		else if (cmd.equals("테이블3")) {
			curTable = 2;
			showTableInfo();
		}
		else if (cmd.equals("테이블4")) {
			curTable = 3;
			showTableInfo();
		}
		else if (cmd.equals("테이블5")) {
			curTable = 4;
			showTableInfo();
		}
		else if (cmd.equals("테이블6")) {
			curTable = 5;
			showTableInfo();
		}
		else if (cmd.equals("테이블7")) {
			curTable = 6;
			showTableInfo();
		}
		else if (cmd.equals("테이블8")) {
			curTable = 7;
			showTableInfo();
		}
		else if (cmd.equals("테이블 추가")) {
			addMenu = new JFrame("메뉴 추가");
			addMenu.setSize(300, 700);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(11, 1));
			addMenu.setVisible(true);
			
			for (int i = 0; i < 10; i++) {
				
				if (i < menuCount) {
					menuList[i] = new JButton(menuInfo[i].getName() + " " + menuInfo[i].getPrice() + "원");
					menuList[i].setActionCommand("메뉴" + (i+1) + "주문");
					menuList[i].addActionListener(this);
					menuList[i].setOpaque(true);
					addMenu.add(menuList[i]);
				}
				
				else {
					menuList[i] = new JButton();
					addMenu.add(menuList[i]);
				}
			}
			
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new FlowLayout());
			
			JButton addTableEndBtn = new JButton("완료");
			addTableEndBtn.setActionCommand("테이블 추가 완료");
			addTableEndBtn.addActionListener(this);
			btnPanel.add(addTableEndBtn);
			
			JButton addTableCancleBtn = new JButton("취소");
			addTableCancleBtn.setActionCommand("테이블 추가 취소");
			addTableCancleBtn.addActionListener(this);
			btnPanel.add(addTableCancleBtn);
			
			addMenu.add(btnPanel);
		}
		else if (cmd.equals("테이블 추가 완료")) {
			int idx = tableInfo[curTable].getIdx();
			
			if (idx == 0)
				tableCount++;
			
			tableInfo[curTable].dish[idx].setName(new String(menuInfo[orderMenu].getName()));
			tableInfo[curTable].dish[idx].setPrice(menuInfo[orderMenu].getPrice());
			
			tableInfo[curTable].setIdx(idx+1);
			tableInfo[curTable].calPrice();
			
			addMenu.dispose();
		
			showTableTab();
			showTableInfo();
		}
		else if (cmd.equals("테이블 추가 취소")) {
			addMenu.dispose();
		}
		else if (cmd.equals("결제")) {
			addMenu = new JFrame("결제");
			addMenu.setVisible(true);
			addMenu.setSize(300, 700);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(9, 1));
			
			memberListBtn = new JButton[8];
			for (int i = 0; i < 7; i++) {
				memberListBtn[i] = new JButton();
				memberListBtn[i].setOpaque(true);
				
				if (i < memberCount) {
					memberListBtn[i].setText(memberInfo[i].getId() + " " +  memberInfo[i].getName());
					memberListBtn[i].setActionCommand("회원" + (i+1) + "선택");
					memberListBtn[i].addActionListener(this);
				}
				
				addMenu.add(memberListBtn[i]);
			}
			memberListBtn[7] = new JButton("비회원");
			memberListBtn[7].setOpaque(true);
			memberListBtn[7].setActionCommand("비회원선택");
			memberListBtn[7].addActionListener(this);
			addMenu.add(memberListBtn[7]);
			
			JPanel payBtnPanel = new JPanel();
			addMenu.add(payBtnPanel);
			payBtnPanel.setLayout(new FlowLayout());
			
			JButton payEndBtn = new JButton("완료");
			payEndBtn.setActionCommand("결제 완료");
			payEndBtn.addActionListener(this);
			payBtnPanel.add(payEndBtn);
			
			JButton payCancleBtn = new JButton("취소");
			payCancleBtn.setActionCommand("결제 취소");
			payCancleBtn.addActionListener(this);
			payBtnPanel.add(payCancleBtn);
		}
		else if (cmd.equals("결제 완료")){
			if (orderMember != 7) {
				double tempMile;
				
				if (memberInfo[orderMember].getGrade() == Member.Grade.COMMON)
					sales += tableInfo[curTable].getPrice() * 0.98;
				else if (memberInfo[orderMember].getGrade() == Member.Grade.GOLD)
					sales += tableInfo[curTable].getPrice() * 0.95;
				else
					sales += tableInfo[curTable].getPrice() * 0.9;
				
				tempMile = memberInfo[orderMember].getMile();
				tempMile += tableInfo[curTable].getPrice() * 0.02;
				memberInfo[orderMember].setMile(tempMile);
				
				if (memberInfo[orderMember].getMile() > 1000)
					memberInfo[orderMember].setGrade(Member.Grade.PLATINUM);
				else if (memberInfo[orderMember].getMile() > 500)
					memberInfo[orderMember].setGrade(Member.Grade.GOLD);
				
			}
			else {
				sales += tableInfo[curTable].getPrice();
			}
			
			tableCount--;
			tableInfo[curTable] = new Table();
			moneyInfo.setText("오늘 매출: " + sales + "원 / 전체 잔고: " + balance + "원");
			
			addMenu.dispose();
			showTableTab();
		}
		else if (cmd.equals("결제 취소")) {
			addMenu.dispose();
		}
		else if (cmd.equals("창고 추가")) {
			addMenu = new JFrame("창고 추가");
			addMenu.setVisible(true);
			addMenu.setSize(300, 400);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(5, 1));
			
			JPanel namePanel = new JPanel();
			namePanel.setLayout(new BorderLayout());
			JLabel nameLabel = new JLabel("이름");
			namePanel.add(nameLabel, BorderLayout.WEST);
			whNameField = new JTextField();
			namePanel.add(whNameField, BorderLayout.CENTER);
			addMenu.add(namePanel);
			
			JPanel pricePanel = new JPanel();
			pricePanel.setLayout(new BorderLayout());
			JLabel priceLabel = new JLabel("가격");
			pricePanel.add(priceLabel, BorderLayout.WEST);
			whPriceField = new JTextField();
			pricePanel.add(whPriceField, BorderLayout.CENTER);
			addMenu.add(pricePanel);
			
			JPanel placePanel = new JPanel();
			placePanel.setLayout(new BorderLayout());
			JLabel placeLabel = new JLabel("판매처");
			placePanel.add(placeLabel, BorderLayout.WEST);
			whPlaceField = new JTextField();
			placePanel.add(whPlaceField, BorderLayout.CENTER);
			addMenu.add(placePanel);
			
			JPanel callPanel = new JPanel();
			callPanel.setLayout(new BorderLayout());
			JLabel callLabel = new JLabel("연락처");
			callPanel.add(callLabel, BorderLayout.WEST);
			whCallField = new JTextField();
			callPanel.add(whCallField, BorderLayout.CENTER);
			addMenu.add(callPanel);
			
			JPanel whBtnPanel = new JPanel();
			whBtnPanel.setLayout(new FlowLayout());
			addMenu.add(whBtnPanel);
			
			JButton whEndBtn = new JButton("완료");
			whEndBtn.setActionCommand("창고 추가 완료");
			whEndBtn.addActionListener(this);
			whBtnPanel.add(whEndBtn);
			
			JButton whCancleBtn = new JButton("취소");
			whCancleBtn.setActionCommand("창고 추가 취소");
			whCancleBtn.addActionListener(this);
			whBtnPanel.add(whCancleBtn);
		}
		else if (cmd.equals("창고 추가 완료")) {
			whInfo[whNumber].setName(whNameField.getText());
			whInfo[whNumber].setPrice((int)Integer.parseInt(whPriceField.getText()));
			whInfo[whNumber].setPlace(whPlaceField.getText());
			whInfo[whNumber].setCall(whCallField.getText());
			whInfo[whNumber].setCount(0);
			whInfo[whNumber].setOrder(0);
			
			addMenu.dispose();
			
			whNumber++;
			
			showWarehouseTab();
		}
		else if (cmd.equals("창고 추가 취소")) {
			addMenu.dispose();
		}
		else if (cmd.equals("재료 주문")) {
			addMenu = new JFrame("재료 주문");
			addMenu.setVisible(true);
			addMenu.setSize(300, 500);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(6, 1));
			
			JLabel nameLabel = new JLabel("재료 이름 : " + whInfo[curWh].getName());
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addMenu.add(nameLabel);
			
			JLabel priceLabel = new JLabel("재료 가격 : " + whInfo[curWh].getPrice());
			priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addMenu.add(priceLabel);
			
			JLabel countLabel = new JLabel("현재 재고 : " + whInfo[curWh].getCount());
			countLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addMenu.add(countLabel);
			
			JLabel orderLabel = new JLabel("현재 주문량 : " + whInfo[curWh].getOrder());
			orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addMenu.add(orderLabel);
			
			JPanel addOrderPanel = new JPanel();
			addOrderPanel.setLayout(new BorderLayout());
			addMenu.add(addOrderPanel);
			
			JLabel addOrderLabel = new JLabel("추가 주문량");
			addOrderPanel.add(addOrderLabel, BorderLayout.WEST);
			
			addOrderField = new JTextField();
			addOrderPanel.add(addOrderField, BorderLayout.CENTER);
			
			JPanel whBtnPanel = new JPanel();
			whBtnPanel.setLayout(new FlowLayout());
			addMenu.add(whBtnPanel);
			
			JButton whEndBtn = new JButton("완료");
			whEndBtn.setActionCommand("재료 주문 완료");
			whEndBtn.addActionListener(this);
			whBtnPanel.add(whEndBtn);
			
			JButton whCancleBtn = new JButton("취소");
			whCancleBtn.setActionCommand("재료 주문을 취소");
			whCancleBtn.addActionListener(this);
			whBtnPanel.add(whCancleBtn);
		}
		else if (cmd.equals("재료 주문 취소")) {
			addMenu = new JFrame("재료 주문 편집");
			addMenu.setVisible(true);
			addMenu.setSize(300, 500);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(6, 1));
			
			JLabel nameLabel = new JLabel("재료 이름 : " + whInfo[curWh].getName());
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addMenu.add(nameLabel);
			
			JLabel priceLabel = new JLabel("재료 가격 : " + whInfo[curWh].getPrice());
			priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addMenu.add(priceLabel);
			
			JLabel countLabel = new JLabel("현재 재고 : " + whInfo[curWh].getCount());
			countLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addMenu.add(countLabel);
			
			JLabel orderLabel = new JLabel("현재 주문량 : " + whInfo[curWh].getOrder());
			orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addMenu.add(orderLabel);
			
			JPanel addOrderPanel = new JPanel();
			addOrderPanel.setLayout(new BorderLayout());
			addMenu.add(addOrderPanel);
			
			JLabel addOrderLabel = new JLabel("주문 취소량");
			addOrderPanel.add(addOrderLabel, BorderLayout.WEST);
			
			addOrderField = new JTextField();
			addOrderPanel.add(addOrderField, BorderLayout.CENTER);
			
			JPanel whBtnPanel = new JPanel();
			whBtnPanel.setLayout(new FlowLayout());
			addMenu.add(whBtnPanel);
			
			JButton whEndBtn = new JButton("완료");
			whEndBtn.setActionCommand("재료 주문 취소 완료");
			whEndBtn.addActionListener(this);
			whBtnPanel.add(whEndBtn);
			
			JButton whCancleBtn = new JButton("취소");
			whCancleBtn.setActionCommand("재료 주문 취소 취소");
			whCancleBtn.addActionListener(this);
			whBtnPanel.add(whCancleBtn);
			
			showWarehouseTab();
		}
		else if (cmd.equals("재료 주문 취소 완료")) {
			int temp = (int)Integer.parseInt(addOrderField.getText());
			int originOrder = whInfo[curWh].getOrder();
			
			whInfo[curWh].setOrder(originOrder - temp);

			addMenu.dispose();
			showWarehouseTab();
		}
		else if (cmd.equals("재료 주문 취소 취소")) {
			addMenu.dispose();
		}
		else if (cmd.equals("재료 주문 완료")) {
			int temp = (int)Integer.parseInt(addOrderField.getText());
			int originOrder = whInfo[curWh].getOrder();
			
			whInfo[curWh].setOrder(originOrder + temp);

			addMenu.dispose();
			showWarehouseTab();
		}
		else if (cmd.equals("재료 주문을 취소")) {
			addMenu.dispose();
		}
		else if (cmd.equals("회원 추가")) {
			
			addMenu = new JFrame("회원 추가");
			addMenu.setVisible(true);
			addMenu.setSize(300, 400);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(4, 1));
			
			
			// input panel setting
			JPanel idPanel = new JPanel();
			idPanel.setLayout(new BorderLayout());
			addMenu.add(idPanel);
			
			JLabel idLabel = new JLabel("번호");
			idField = new JTextField();
			idPanel.add(idLabel, BorderLayout.WEST);
			idPanel.add(idField, BorderLayout.CENTER);
			
			
			JPanel namePanel = new JPanel();
			namePanel.setLayout(new BorderLayout());
			addMenu.add(namePanel);
			
			JLabel nameLabel = new JLabel("이름");
			nameField = new JTextField();
			namePanel.add(nameLabel, BorderLayout.WEST);
			namePanel.add(nameField, BorderLayout.CENTER);
			
			JPanel callPanel = new JPanel();
			callPanel.setLayout(new BorderLayout());
			addMenu.add(callPanel);
			
			JLabel callLabel = new JLabel("연락처");
			callField = new JTextField();
			callPanel.add(callLabel, BorderLayout.WEST);
			callPanel.add(callField, BorderLayout.CENTER);
			
			JPanel memberBtnPanel = new JPanel();
			memberBtnPanel.setLayout(new FlowLayout());
			addMenu.add(memberBtnPanel);
			
			JButton memberEndBtn = new JButton("완료");
			memberEndBtn.setActionCommand("회원 추가 완료");
			memberEndBtn.addActionListener(this);
			memberBtnPanel.add(memberEndBtn);
			
			JButton memberCancleBtn = new JButton("취소");
			memberCancleBtn.setActionCommand("회원 추가 취소");
			memberCancleBtn.addActionListener(this);
			memberBtnPanel.add(memberCancleBtn);
		}
		else if (cmd.equals("회원 추가 완료")) {
			memberInfo[memberCount].setId((int)Integer.parseInt(idField.getText()));
			memberInfo[memberCount].setName(nameField.getText());
			memberInfo[memberCount].setCall(callField.getText());
			memberInfo[memberCount].setGrade(Member.Grade.COMMON);
			memberInfo[memberCount].setMile(0);
			
			memberCount++;
			addMenu.dispose();
			
			showMemberTab();
		}
		else if (cmd.equals("회원 추가 취소")) {
			addMenu.dispose();
		}
		else if (cmd.equals("회원 편집")) {
			addMenu = new JFrame("회원 편집");
			addMenu.setVisible(true);
			addMenu.setSize(300, 400);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(4, 1));
			
			
			// input panel setting
			JPanel idPanel = new JPanel();
			idPanel.setLayout(new BorderLayout());
			addMenu.add(idPanel);
			
			JLabel idLabel = new JLabel("번호");
			idField = new JTextField("" + memberInfo[curMember].getId());
			idPanel.add(idLabel, BorderLayout.WEST);
			idPanel.add(idField, BorderLayout.CENTER);
			
			
			JPanel namePanel = new JPanel();
			namePanel.setLayout(new BorderLayout());
			addMenu.add(namePanel);
			
			JLabel nameLabel = new JLabel("이름");
			nameField = new JTextField(memberInfo[curMember].getName());
			namePanel.add(nameLabel, BorderLayout.WEST);
			namePanel.add(nameField, BorderLayout.CENTER);
			
			JPanel callPanel = new JPanel();
			callPanel.setLayout(new BorderLayout());
			addMenu.add(callPanel);
			
			JLabel callLabel = new JLabel("연락처");
			callField = new JTextField(memberInfo[curMember].getCall());
			callPanel.add(callLabel, BorderLayout.WEST);
			callPanel.add(callField, BorderLayout.CENTER);
			
			JPanel memberBtnPanel = new JPanel();
			memberBtnPanel.setLayout(new FlowLayout());
			addMenu.add(memberBtnPanel);
			
			JButton memberEndBtn = new JButton("완료");
			memberEndBtn.setActionCommand("회원 편집 완료");
			memberEndBtn.addActionListener(this);
			memberBtnPanel.add(memberEndBtn);
			
			JButton memberCancleBtn = new JButton("취소");
			memberCancleBtn.setActionCommand("회원 편집 취소");
			memberCancleBtn.addActionListener(this);
			memberBtnPanel.add(memberCancleBtn);
		}
		else if (cmd.equals("회원 편집 완료")) {
			memberInfo[curMember].setId((int)Integer.parseInt(idField.getText()));
			memberInfo[curMember].setName(nameField.getText());
			memberInfo[curMember].setCall(callField.getText());
			
			addMenu.dispose();
			
			showMemberTab();
		}
		else if (cmd.equals("회원 편집 취소")) {
			addMenu.dispose();
		}
		else if (cmd.equals("회원 삭제")) {
			for (int i = curMember; i < 6; i++)
				memberInfo[i] = memberInfo[i+1];
			memberCount--;
			
			showMemberTab();
		}
		else if (cmd.equals("메뉴 추가")) {
			int i;
			
			addMenu = new JFrame("메뉴 추가");
			addMenu.setVisible(true);
			addMenu.setSize(300, 700);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(11, 1));
			
			addNamePanel = new JPanel();
			addNamePanel.setLayout(new BorderLayout());
			addNameLabel = new JLabel("이름");
			addNameField = new JTextField();
			addNamePanel.add(addNameLabel, BorderLayout.WEST);
			addNamePanel.add(addNameField, BorderLayout.CENTER);
			addMenu.add(addNamePanel);
			
			addPricePanel = new JPanel();
			addPricePanel.setLayout(new BorderLayout());
			addPriceLabel = new JLabel("가격");
			addPriceField = new JTextField();
			addPricePanel.add(addPriceLabel, BorderLayout.WEST);
			addPricePanel.add(addPriceField, BorderLayout.CENTER);
			addMenu.add(addPricePanel);
			
			addMPricePanel = new JPanel();
			addMPricePanel.setLayout(new BorderLayout());
			addMPriceLabel = new JLabel("생산단가");
			addMPriceField = new JTextField();
			addMPricePanel.add(addMPriceLabel, BorderLayout.WEST);
			addMPricePanel.add(addMPriceField, BorderLayout.CENTER);
			addMenu.add(addMPricePanel);
			
			for (i = 0; i < 7; i++) {
				addMaterialPanel[i] = new JPanel();
				addMaterialPanel[i].setLayout(new GridLayout(1, 2));
				addLeftPanel[i] = new JPanel();
				addLeftPanel[i].setLayout(new BorderLayout());
				addRightPanel[i] = new JPanel();
				addRightPanel[i].setLayout(new BorderLayout());
				
				addLeftLabel[i] = new JLabel("재료" + (i+1));
				addLeftPanel[i].add(addLeftLabel[i], BorderLayout.WEST);
				addRightLabel[i] = new JLabel("가격");
				addRightPanel[i].add(addRightLabel[i], BorderLayout.WEST);
				
				addLeftField[i] = new JTextField();
				addLeftPanel[i].add(addLeftField[i], BorderLayout.CENTER);
				addRightField[i] = new JTextField();
				addRightPanel[i].add(addRightField[i], BorderLayout.CENTER);
				
				addMaterialPanel[i].add(addLeftPanel[i]);
				addMaterialPanel[i].add(addRightPanel[i]);
				addMenu.add(addMaterialPanel[i]);
			}
			
			addBtnPanel = new JPanel();
			addBtnPanel.setLayout(new FlowLayout());
			addEndBtn = new JButton("완료");
			addEndBtn.setActionCommand("추가 완료");
			addEndBtn.addActionListener(this);
			addCancleBtn = new JButton("취소");
			addCancleBtn.setActionCommand("추가 취소");
			addCancleBtn.addActionListener(this);
			addBtnPanel.add(addEndBtn);
			addBtnPanel.add(addCancleBtn);
			addMenu.add(addBtnPanel);
		}
		else if (cmd.equals("추가 완료")) {
			int i;
			
			menuInfo[menuCount].setName(new String(addNameField.getText()));
			menuInfo[menuCount].setPrice((int)Integer.parseInt(new String(addPriceField.getText())));
			menuInfo[menuCount].setMPrice((int)Integer.parseInt(new String(addMPriceField.getText())));
			
			for (i = 0; i < 7; i++) {
				String name = new String(addLeftField[i].getText());
			
				if (name.equals(""))
					break;
				
				int price = (int)Integer.parseInt(new String(addRightField[i].getText()));
				
				menuInfo[menuCount].material[i] = new Material(name, price);
			}
			menuInfo[menuCount].setIdx(i);
			
			addMenu.setVisible(false);
			addMenu.dispose();
			menuCount++;
			showMenuTab();
		}
		
		else if (cmd.equals("추가 취소")) {
			addMenu.dispose();
		}
		else if (cmd.equals("메뉴1")) {
			curMenu = 0;
			procMenuBtn();
		}
		else if (cmd.equals("메뉴2")) {
			curMenu = 1;
			procMenuBtn();
		}
		else if (cmd.equals("메뉴3")) {
			curMenu = 2;
			procMenuBtn();
		}
		else if (cmd.equals("메뉴4")) {
			curMenu = 3;
			procMenuBtn();
		}
		else if (cmd.equals("메뉴5")) {
			curMenu = 4;
			procMenuBtn();
		}
		else if (cmd.equals("메뉴6")) {
			curMenu = 5;
			procMenuBtn();
		}
		else if (cmd.equals("메뉴7")) {
			curMenu = 6;
			procMenuBtn();
		}
		else if (cmd.equals("메뉴8")) {
			curMenu = 7;
			procMenuBtn();
		}
		else if (cmd.equals("메뉴9")) {
			curMenu = 8;
			procMenuBtn();
		}
		else if (cmd.equals("메뉴10")) {
			curMenu = 9;
			procMenuBtn();
		}
		else if (cmd.equals("메뉴 편집")) {
			
			addMenu = new JFrame("메뉴 편집");
			addMenu.setVisible(true);
			addMenu.setSize(300, 700);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(11, 1));
			
			addNamePanel = new JPanel();
			addNamePanel.setLayout(new BorderLayout());
			addNameLabel = new JLabel("이름");
			addNameField = new JTextField(menuInfo[curMenu].getName());
			addNamePanel.add(addNameLabel, BorderLayout.WEST);
			addNamePanel.add(addNameField, BorderLayout.CENTER);
			addMenu.add(addNamePanel);
			
			addPricePanel = new JPanel();
			addPricePanel.setLayout(new BorderLayout());
			addPriceLabel = new JLabel("가격");
			addPriceField = new JTextField("" + menuInfo[curMenu].getPrice());
			addPricePanel.add(addPriceLabel, BorderLayout.WEST);
			addPricePanel.add(addPriceField, BorderLayout.CENTER);
			addMenu.add(addPricePanel);
			
			addMPricePanel = new JPanel();
			addMPricePanel.setLayout(new BorderLayout());
			addMPriceLabel = new JLabel("생산단가");
			addMPriceField = new JTextField("" + menuInfo[curMenu].getMPrice());
			addMPricePanel.add(addMPriceLabel, BorderLayout.WEST);
			addMPricePanel.add(addMPriceField, BorderLayout.CENTER);
			addMenu.add(addMPricePanel);
			
			int limit = menuInfo[curMenu].getIdx();
			for (int i = 0; i < 7; i++) {
				addMaterialPanel[i] = new JPanel();
				addMaterialPanel[i].setLayout(new GridLayout(1, 2));
				addLeftPanel[i] = new JPanel();
				addLeftPanel[i].setLayout(new BorderLayout());
				addRightPanel[i] = new JPanel();
				addRightPanel[i].setLayout(new BorderLayout());
				
				addLeftLabel[i] = new JLabel("재료" + (i+1));
				addLeftPanel[i].add(addLeftLabel[i], BorderLayout.WEST);
				addRightLabel[i] = new JLabel("가격");
				addRightPanel[i].add(addRightLabel[i], BorderLayout.WEST);
				
				if (i >= limit) {
					addLeftField[i] = new JTextField();
					addRightField[i] = new JTextField();
				}
				else {
					addLeftField[i] = new JTextField(menuInfo[curMenu].material[i].getName());
					addRightField[i] = new JTextField("" + menuInfo[curMenu].material[i].getPrice());
				}

				addLeftPanel[i].add(addLeftField[i], BorderLayout.CENTER);
				addRightPanel[i].add(addRightField[i], BorderLayout.CENTER);
				
				addMaterialPanel[i].add(addLeftPanel[i]);
				addMaterialPanel[i].add(addRightPanel[i]);
				addMenu.add(addMaterialPanel[i]);
			}
			
			addBtnPanel = new JPanel();
			addBtnPanel.setLayout(new FlowLayout());
			addEndBtn = new JButton("완료");
			addEndBtn.setActionCommand("편집 완료");
			addEndBtn.addActionListener(this);
			addCancleBtn = new JButton("취소");
			addCancleBtn.setActionCommand("편집 취소");
			addCancleBtn.addActionListener(this);
			addBtnPanel.add(addEndBtn);
			addBtnPanel.add(addCancleBtn);
			addMenu.add(addBtnPanel);
		}
		else if (cmd.equals("편집 완료")) {
			int i;
			
			menuInfo[curMenu].setName(new String(addNameField.getText()));
			menuInfo[curMenu].setPrice((int)Integer.parseInt(new String(addPriceField.getText())));
			menuInfo[curMenu].setMPrice((int)Integer.parseInt(new String(addMPriceField.getText())));
			
			for (i = 0; i < 7; i++) {
				String name = new String(addLeftField[i].getText());
			
				if (name.equals(""))
					break;
				
				int price = (int)Integer.parseInt(new String(addRightField[i].getText()));
				
				menuInfo[curMenu].material[i] = new Material(name, price);
			}
			menuInfo[curMenu].setIdx(i);
			
			addMenu.setVisible(false);
			addMenu.dispose();
			
			showMenuTab();
		}
		else if (cmd.equals("편집 취소")) {
			addMenu.dispose();
		}
		else if (cmd.equals("메뉴 삭제")) {
			for (int i = curMenu; i < MAX_MENU-1; i++)
				menuInfo[i] = menuInfo[i+1];
			menuCount--;
			
			showMenuTab();
		}
		else if (cmd.equals("직원 편집")) {
			addMenu = new JFrame("직원 편집");
			addMenu.setVisible(true);
			addMenu.setSize(300, 600);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(6, 1));
			
			JPanel empIdPanel = new JPanel();
			empIdPanel.setLayout(new BorderLayout());
			JLabel empIdLabel = new JLabel("번호");
			empIdField = new JTextField("" + empInfo[curEmp].getId());
			empIdPanel.add(empIdLabel, BorderLayout.WEST);
			empIdPanel.add(empIdField, BorderLayout.CENTER);
			addMenu.add(empIdPanel); 
			
			JPanel empNamePanel = new JPanel();
			empNamePanel.setLayout(new BorderLayout());
			JLabel empNameLabel = new JLabel("이름");
			empNameField = new JTextField(empInfo[curEmp].getName());
			empNamePanel.add(empNameLabel, BorderLayout.WEST);
			empNamePanel.add(empNameField, BorderLayout.CENTER);
			addMenu.add(empNamePanel);
			
			JPanel empSalaryPanel = new JPanel();
			empSalaryPanel.setLayout(new BorderLayout());
			JLabel empSalaryLabel = new JLabel("급여");
			empSalaryField = new JTextField("" + empInfo[curEmp].getSalary());
			empSalaryPanel.add(empSalaryLabel, BorderLayout.WEST);
			empSalaryPanel.add(empSalaryField, BorderLayout.CENTER);
			addMenu.add(empSalaryPanel);
			
			JPanel empGradePanel = new JPanel();
			empGradePanel.setLayout(new BorderLayout());
			JLabel empGradeLabel = new JLabel("직급");
			empGradeField = new JTextField(empInfo[curEmp].getGrade());
			empGradePanel.add(empGradeLabel, BorderLayout.WEST);
			empGradePanel.add(empGradeField, BorderLayout.CENTER);
			addMenu.add(empGradePanel); 
			
			JPanel empCallPanel = new JPanel();
			empCallPanel.setLayout(new BorderLayout());
			JLabel empCallLabel = new JLabel("연락처");
			empCallField = new JTextField(empInfo[curEmp].getCall());
			empCallPanel.add(empCallLabel, BorderLayout.WEST);
			empCallPanel.add(empCallField, BorderLayout.CENTER);
			addMenu.add(empCallPanel); 
			
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new FlowLayout());
			addMenu.add(btnPanel);
			
			JButton empEndBtn = new JButton("완료");
			empEndBtn.setActionCommand("직원 편집 완료");
			empEndBtn.addActionListener(this);
			btnPanel.add(empEndBtn);
			
			JButton empCancleBtn = new JButton("취소");
			empCancleBtn.setActionCommand("직원 편집 취소");
			empCancleBtn.addActionListener(this);
			btnPanel.add(empCancleBtn);
		}
		else if (cmd.equals("직원 편집 완료")) {
			empInfo[curEmp].setId((int)Integer.parseInt(empIdField.getText()));
			empInfo[curEmp].setName(empNameField.getText());
			empInfo[curEmp].setSalary((int)Integer.parseInt(empSalaryField.getText()));
			empInfo[curEmp].setGrade(empGradeField.getText());
			empInfo[curEmp].setCall(empCallField.getText());
			
			addMenu.dispose();
			
			showEmployeeTab();
		}
		else if (cmd.equals("직원 추가")) {
			addMenu = new JFrame("직원 추가");
			addMenu.setVisible(true);
			addMenu.setSize(300, 600);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addMenu.setLayout(new GridLayout(6, 1));
			
			JPanel empIdPanel = new JPanel();
			empIdPanel.setLayout(new BorderLayout());
			JLabel empIdLabel = new JLabel("번호");
			empIdField = new JTextField();
			empIdPanel.add(empIdLabel, BorderLayout.WEST);
			empIdPanel.add(empIdField, BorderLayout.CENTER);
			addMenu.add(empIdPanel); 
			
			JPanel empNamePanel = new JPanel();
			empNamePanel.setLayout(new BorderLayout());
			JLabel empNameLabel = new JLabel("이름");
			empNameField = new JTextField();
			empNamePanel.add(empNameLabel, BorderLayout.WEST);
			empNamePanel.add(empNameField, BorderLayout.CENTER);
			addMenu.add(empNamePanel);
			
			JPanel empSalaryPanel = new JPanel();
			empSalaryPanel.setLayout(new BorderLayout());
			JLabel empSalaryLabel = new JLabel("급여");
			empSalaryField = new JTextField();
			empSalaryPanel.add(empSalaryLabel, BorderLayout.WEST);
			empSalaryPanel.add(empSalaryField, BorderLayout.CENTER);
			addMenu.add(empSalaryPanel);
			
			JPanel empGradePanel = new JPanel();
			empGradePanel.setLayout(new BorderLayout());
			JLabel empGradeLabel = new JLabel("직급");
			empGradeField = new JTextField();
			empGradePanel.add(empGradeLabel, BorderLayout.WEST);
			empGradePanel.add(empGradeField, BorderLayout.CENTER);
			addMenu.add(empGradePanel); 
			
			JPanel empCallPanel = new JPanel();
			empCallPanel.setLayout(new BorderLayout());
			JLabel empCallLabel = new JLabel("연락처");
			empCallField = new JTextField();
			empCallPanel.add(empCallLabel, BorderLayout.WEST);
			empCallPanel.add(empCallField, BorderLayout.CENTER);
			addMenu.add(empCallPanel); 
			
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new FlowLayout());
			addMenu.add(btnPanel);
			
			JButton empEndBtn = new JButton("완료");
			empEndBtn.setActionCommand("직원 추가 완료");
			empEndBtn.addActionListener(this);
			btnPanel.add(empEndBtn);
			
			JButton empCancleBtn = new JButton("취소");
			empCancleBtn.setActionCommand("직원 추가 취소");
			empCancleBtn.addActionListener(this);
			btnPanel.add(empCancleBtn);
		}
		else if (cmd.equals("직원 추가 완료")) {
			empInfo[empCount].setId((int)Integer.parseInt(empIdField.getText()));
			empInfo[empCount].setName(empNameField.getText());
			empInfo[empCount].setSalary((int)Integer.parseInt(empSalaryField.getText()));
			empInfo[empCount].setGrade(empGradeField.getText());
			empInfo[empCount].setDay(year, month, day);
			empInfo[empCount].setCall(empCallField.getText());
			
			empCount++;
			
			addMenu.dispose();
			
			showEmployeeTab();
		}
		else if (cmd.equals("직원 추가 취소")) {
			addMenu.dispose();
		}
		else if (cmd.equals("직원 삭제")) {
			for (int i = curEmp; i < 8; i++)
				empInfo[i] = empInfo[i+1];
			empCount--;
			
			showEmployeeTab();
		}
		else if (cmd.equals("메뉴1주문")) {
			orderMenu = 0;
			menuBtnChange();	
		}
		else if (cmd.equals("메뉴2주문")) {
			orderMenu = 1;
			menuBtnChange();
		}
		else if (cmd.equals("메뉴3주문")) {
			orderMenu = 2;
			menuBtnChange();
		}
		else if (cmd.equals("메뉴4주문")) {
			orderMenu = 3;
			menuBtnChange();
		}
		else if (cmd.equals("메뉴5주문")) {
			orderMenu = 4;
			menuBtnChange();
		}
		else if (cmd.equals("메뉴6주문")) {
			orderMenu = 5;
			menuBtnChange();
		}
		else if (cmd.equals("메뉴7주문")) {
			orderMenu = 6;
			menuBtnChange();
		}
		else if (cmd.equals("메뉴8주문")) {
			orderMenu = 7;
			menuBtnChange();
		}
		else if (cmd.equals("메뉴9주문")) {
			orderMenu = 8;
			menuBtnChange();
		}
		else if (cmd.equals("메뉴10주문")) {
			orderMenu = 9;
			menuBtnChange();
		}
		else if (cmd.equals("회원1")) {
			curMember = 0;
			memberBtnChange();
		}
		else if (cmd.equals("회원2")) {
			curMember = 1;
			memberBtnChange();
		}
		else if (cmd.equals("회원3")) {
			curMember = 2;
			memberBtnChange();
		}
		else if (cmd.equals("회원4")) {
			curMember = 3;
			memberBtnChange();
		}
		else if (cmd.equals("회원5")) {
			curMember = 4;
			memberBtnChange();
		}
		else if (cmd.equals("회원6")) {
			curMember = 5;
			memberBtnChange();
		}
		else if (cmd.equals("회원7")) {
			curMember = 6;
			memberBtnChange();
		}
		else if (cmd.equals("회원1선택")) {
			orderMember = 0;
			procMemberBtn();
		}
		else if (cmd.equals("회원2선택")) {
			orderMember = 1;
			procMemberBtn();
		}
		else if (cmd.equals("회원3선택")) {
			orderMember = 2;
			procMemberBtn();
		}
		else if (cmd.equals("회원4선택")) {
			orderMember = 3;
			procMemberBtn();
		}
		else if (cmd.equals("회원5선택")) {
			orderMember = 4;
			procMemberBtn();
		}
		else if (cmd.equals("회원6선택")) {
			orderMember = 5;
			procMemberBtn();
		}
		else if (cmd.equals("회원7선택")) {
			orderMember = 6;
			procMemberBtn();
		}
		else if (cmd.equals("비회원선택")) {
			orderMember = 7;
			procMemberBtn();
		}
		else if (cmd.equals("창고1")) {
			curWh = 0;
			whBtnChange();
		}
		else if (cmd.equals("창고2")) {
			curWh = 1;
			whBtnChange();
		}
		else if (cmd.equals("창고3")) {
			curWh = 2;
			whBtnChange();
		}
		else if (cmd.equals("창고4")) {
			curWh = 3;
			whBtnChange();
		}
		else if (cmd.equals("창고5")) {
			curWh = 4;
			whBtnChange();
		}
		else if (cmd.equals("창고6")) {
			curWh = 5;
			whBtnChange();
		}
		else if (cmd.equals("창고7")) {
			curWh = 6;
			whBtnChange();
		}
		else if (cmd.equals("창고8")) {
			curWh = 7;
			whBtnChange();
		}
		else if (cmd.equals("창고9")) {
			curWh = 8;
			whBtnChange();
		}
		else if (cmd.equals("직원1")) {
			curEmp = 0;
			empBtnChange();
		}
		else if (cmd.equals("직원2")) {
			curEmp = 1;
			empBtnChange();
		}
		else if (cmd.equals("직원3")) {
			curEmp = 2;
			empBtnChange();
		}
		else if (cmd.equals("직원4")) {
			curEmp = 3;
			empBtnChange();
		}
		else if (cmd.equals("직원5")) {
			curEmp = 4;
			empBtnChange();
		}
		else if (cmd.equals("직원6")) {
			curEmp = 5;
			empBtnChange();
		}
		else if (cmd.equals("직원7")) {
			curEmp = 6;
			empBtnChange();
		}
		else if (cmd.equals("직원8")) {
			curEmp = 7;
			empBtnChange();
		}
		else if (cmd.equals("직원9")) {
			curEmp = 8;
			empBtnChange();
		}
		else if (cmd.equals("마감") && tableCount != 0) {
			addMenu = new JFrame("결제 오류!");
			addMenu.setVisible(true);
			addMenu.setSize(400, 300);
			addMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
			JButton errorBtn = new JButton("테이블 결제가 완료되지 않았습니다!.");
			errorBtn.addActionListener(this);
			addMenu.add(errorBtn);
			
		}
		else if (cmd.equals("테이블 결제가 완료되지 않았습니다!.")) {
			addMenu.dispose();
		}
		else if (cmd.equals("마감")) {
			cal.add(Calendar.DATE, 1);
			
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
			day = cal.get(Calendar.DAY_OF_MONTH);
			
			dayInfo.setText(year + "년 " + month + "월 " + day + "일 ");
			
			balance += sales;
			sales = 0;
			
			// calculate warehouse order
			for (int i = 0; i < whNumber; i++) {
				balance -= whInfo[i].getPrice() * whInfo[i].getOrder();
				
				int tempCount = whInfo[i].getCount();
				
				whInfo[i].setCount(tempCount + whInfo[i].getOrder());
				whInfo[i].setOrder(0);
			}
			
			// if day == 1
			if (day == 1) {
				
				for (int i = 0; i < memberCount; i++) {
					memberInfo[i].setMile(0);
					memberInfo[i].setGrade(Member.Grade.COMMON);
				}
				
				for (int i = 0; i < empCount; i++)
					balance -= empInfo[i].getSalary();
			}
			
			moneyInfo.setText("오늘 매출: " + sales + "원 / 전체 잔고: " + balance + "원");
			
			if (curTab == 1)
				showWarehouseTab();
			else if (curTab == 2)
				showMemberTab();
			
		}
	}
}