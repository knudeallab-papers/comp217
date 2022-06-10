package five_nights_at_freddy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class FNAF_Screen extends JFrame implements ActionListener, Serializable, WindowListener, ListSelectionListener{
	public static final int WIDTH = 720;
	public static final int HEIGHT = 400;

	LocalDateTime now = LocalDateTime.now();
	public Date today = new Date(now.getMonthValue(), now.getDayOfMonth(),now.getYear());
	
	JLabel dateA = new JLabel();
	
	//money 파트 
	public int todayMoney = 0;
	public int acumulateMoney = 0;
	JLabel moneyLabel;
	
	//테이블 파트
	FoodMenu[] tableMenus = new FoodMenu[9];
	public int currentTable = 1;
	JPanel[] tablePanels = new JPanel[9];
	JTextArea[] tableTextArea = new JTextArea[9];
	Object[][] tableListItem = new Object[11][2];
	String tableColumnNames[] = {"메뉴","가격"};
	JTable tableList = new JTable(tableListItem, tableColumnNames);
	
	
	//멤버 파트
	Member[] members = new Member[11];
	public int memberCount = 0;
	public int currentMember = 0;
	Object[][] memberListItem = new Object[11][5];
	String memberColumnNames[] = {"번호","등급", "이름", "마일리지", "연락처"};
	JTable memberList = new JTable(memberListItem, memberColumnNames);

	
	//메뉴 파트
	JButton[] menu_making = new JButton[5];
	JPanel menuleft1_1 = new JPanel();
	static int menu_number = 1;
	public int now_edit_menu = 1;
	JTextArea input_menu1 = null;
	JTextArea input_menu2 = null;
	JTextArea input_menu3 = null;
	JTextArea input_menu4 = null;
	public String name;
	public int cost;
	public String info;
	public int cost_production;
	FoodMenu in = new FoodMenu();
	
	//저장고 파트
	Ingredients[] ingredients = new Ingredients[11];
	Object[][] storageListItem = new Object[11][4];
	int ingredientCount = 4;
	int currentIngredient = 1;
	String storageColumnNames[] = {"이름","재고", "주문", "가격"};
	JTable storageList = new JTable(storageListItem, storageColumnNames);
	JTextArea itemNameText = new JTextArea();
	JTextArea itemPriceText = new JTextArea();
	JTextArea itemSellerText = new JTextArea();
	JTextArea itemCallText = new JTextArea();
	JTextArea itemAmountText = new JTextArea();
	JTextArea itemOrderedText = new JTextArea();
	JLabel itemName = new JLabel();JLabel itemPrice = new JLabel();
	JLabel itemSeller = new JLabel();JLabel itemCall = new JLabel();
	JLabel itemAmount = new JLabel();JLabel itemOrdered = new JLabel();
	
	//직원
	Employee[] employees = new Employee[11];
	public int employeeCount = 0;
	public int currentEmployee = 0;
	Object[][] employeeListItem = new Object[11][6];
	String employeeColumnNames[] = {"번호","이름", "급여", "직급", "입사일", "연락처"};
	JTable employeeList = new JTable(employeeListItem, employeeColumnNames);
	
	public FNAF_Screen() {
		super("Freddy's Pizza Store");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		setLayout(new BorderLayout());
		
		JPanel list = new JPanel();
		list.setBackground(Color.black);
		list.setLayout(new FlowLayout());
		JButton dayOver = new JButton("마감");
		dayOver.addActionListener(this);
		
		//날짜 계산
		dateA.setText(today.toString());
		
		moneyLabel = new JLabel("오늘 매출 : " + todayMoney + "원 " + "전체 잔고 : " + acumulateMoney + "원");
		moneyLabel.setForeground(Color.WHITE);
		dateA.setForeground(Color.white);
		list.add(dateA);
		list.add(dayOver);
		list.add(moneyLabel);
		
		list.setBounds(0,0,800,100);
		
		add(list, BorderLayout.NORTH);
		
		
		//tab구현 
		JTabbedPane tabs = new JTabbedPane();
		
		//1번째 table
		JPanel tableTab = new JPanel();
		tableTab.setLayout(new FlowLayout());	
		tableTab.setBackground(Color.DARK_GRAY);
		
		JPanel tables = new JPanel();
		
		
		JButton[] tableBtn = new JButton[9];
		
		tables.setLayout(new GridLayout(2,4));
		for(int i = 1; i <= 8 ; i++) {
			tablePanels[i] = new JPanel();
			tablePanels[i].setBorder(BorderFactory.createLineBorder(Color.black));
			tablePanels[i].setLayout(new BorderLayout());
			tablePanels[i].setBackground(Color.WHITE);
			
			tableTextArea[i] = new JTextArea(7,10);
			tableTextArea[i].setEditable(false);
			tableMenus[i] = new FoodMenu();
			tableTextArea[i].setText("\n\n\n" +"        테이블 " + i +"\n        "
					+ "" + tableMenus[i].getPrice());
			tablePanels[i].add(tableTextArea[i], BorderLayout.CENTER);
			
			tableBtn[i] = new JButton();
			tableBtn[i].setText("확인");
			tableBtn[i].setActionCommand("table " + i);
			tableBtn[i].addActionListener(this);
			tablePanels[i].add(tableBtn[i], BorderLayout.SOUTH);
			
			tables.add(tablePanels[i]);
		}
		
		for(int i = 0; i <= 8; i++)
			tableMenus[i] = new FoodMenu();
		
		tableTab.add(tables);
		//메뉴와 가격표
				JPanel menuprice = new JPanel();
				
				
				tableListItem[0][0] = (Object) "메뉴"; tableListItem[0][1] = (Object) "가격";
				menuprice.setLayout(new BorderLayout());
				
				tableList.setFont(new Font("Sans Serif",Font.PLAIN,12));
				JScrollPane menuScollPane = new JScrollPane(tableList);
				tableList = new JTable(tableListItem, tableColumnNames);
				menuprice.add(tableList, BorderLayout.NORTH);
				
				JPanel tableMenuButtons = new JPanel();
				JButton addMenu = new JButton("추가");
				addMenu.setActionCommand("menu add");
				addMenu.addActionListener(this);
				addMenu.setFont(new Font("Sans Serif", Font.BOLD, 24));
				JButton payMenu = new JButton("결제");
				payMenu.setActionCommand("menu pay");
				payMenu.addActionListener(this);
				payMenu.setFont(new Font("Sans Serif", Font.BOLD, 24));
				tableMenuButtons.setLayout(new FlowLayout());
				tableMenuButtons.add(addMenu);
				tableMenuButtons.add(payMenu);
				menuprice.add(tableMenuButtons, BorderLayout.CENTER);
				
		tableTab.add(menuprice);
		tabs.addTab("테이블",tableTab);
		
		
		
		JPanel storage = new JPanel();
		
		
		storageListItem[0][0] = (Object) "이름"; storageListItem[0][1] = (Object) "재고";
		storageListItem[0][2] = (Object) "주문"; storageListItem[0][3] = (Object) "가격";
		
		storage.setLayout(new GridLayout(1,2));
		
		//sotrage의 List N Buttons
		JPanel storListNbtn = new JPanel();
		storListNbtn.setLayout(new BorderLayout());
		
		storageList.getSelectionModel().addListSelectionListener(this);
		storageList.setFont(new Font("Sans Serif",Font.PLAIN,12));
		JScrollPane storageScollPane = new JScrollPane(storageList);
		
		ingredients[0] = new Ingredients("Animatronics", 5, 1000000000, "Scott Cawthon", "PhoneGuy");
		ingredients[1] = new Ingredients("TomatoSause", 10, 1500, "Vampire's drink", "I-hate-blade");
		ingredients[2] = new Ingredients("Flour", 10, 2000, "Silent Hill", "flour-by-mist");
		ingredients[3] = new Ingredients("Pepperoni", 10, 3000, "Hannibal Rector", "Meat-by-what?");
		
		for(int i = 0; i < ingredientCount; i++ ) {
			storageListItem[i+1][0] = ingredients[i].name;
			storageListItem[i+1][1] = ingredients[i].leaving;
			storageListItem[i+1][2] = ingredients[i].calling;
			storageListItem[i+1][3] = ingredients[i].price;
		}
		storListNbtn.add(storageList, BorderLayout.NORTH);
		
		JPanel storageBtns1 = new JPanel();
		storageBtns1.setLayout(new FlowLayout());
		
		JButton storageAdd = new JButton("추가");
		storageAdd.setActionCommand("storage add");
		storageAdd.addActionListener(this);
		storageBtns1.add(storageAdd);
		
		JButton storageDelete = new JButton("삭제");
		storageDelete.setActionCommand("storage delete");
		storageDelete.addActionListener(this);
		storageBtns1.add(storageDelete);
		
		storListNbtn.add(storageBtns1, BorderLayout.CENTER);
		
		JPanel storInfoNbtn = new JPanel();
		storInfoNbtn.setLayout(new BorderLayout());
		JPanel itemInform = new JPanel();
		itemInform.setLayout(new GridLayout(6,2));
		
		itemName.setText("이름 : ");
		itemPrice.setText("가격 : ");
		itemSeller.setText("판매처 : ");
		itemCall.setText("연락처 : ");
		itemAmount.setText("수량 : ");
		itemOrdered.setText("주문량 : ");
		
		itemNameText.setEditable(false);
		itemPriceText.setEditable(false);
		itemSellerText.setEditable(false);
		itemCallText.setEditable(false);
		itemAmountText.setEditable(false);
		itemOrderedText.setEditable(false);
		
		itemInform.add(itemName);
		itemInform.add(itemNameText);
		itemInform.add(itemPrice);
		itemInform.add(itemPriceText);
		itemInform.add(itemSeller);
		itemInform.add(itemSellerText);
		itemInform.add(itemCall);
		itemInform.add(itemCallText);
		itemInform.add(itemAmount);
		itemInform.add(itemAmountText);
		itemInform.add(itemOrdered);
		itemInform.add(itemOrderedText);
		
		storInfoNbtn.add(itemInform, BorderLayout.NORTH);
		
		JPanel storageBtns2 = new JPanel();
		
		JButton storOrder = new JButton("주문 / 주문 취소");
		storOrder.setActionCommand("order ingre");
		storOrder.addActionListener(this);
		storageBtns2.add(storOrder);
		
		storInfoNbtn.add(storageBtns2, BorderLayout.CENTER);
		storage.add(storListNbtn);
		storage.add(storInfoNbtn);
		
		tabs.addTab("창고",storage);
		
		
		JPanel members = new JPanel();
		
		memberListItem[0][0] = (Object) "번호"; memberListItem[0][1] = (Object) "등급";
		memberListItem[0][2] = (Object) "이름"; memberListItem[0][3] = (Object) "마일리지";
		memberListItem[0][4] = (Object) "연락처";
		
		members.setLayout(new BorderLayout());
		
		memberList = new JTable(memberListItem, memberColumnNames);
		
		memberList.getSelectionModel().addListSelectionListener(this);
		memberList.setFont(new Font("Sans Serif",Font.PLAIN,12));
		JScrollPane memberScollPane = new JScrollPane(memberList);
		
		members.add(memberList, BorderLayout.NORTH);
		
		JPanel memberBtns = new JPanel();
		memberBtns.setLayout(new FlowLayout());
		JButton memberEdit = new JButton("편집");
		memberEdit.setActionCommand("member edit");
		memberEdit.addActionListener(this);
		memberBtns.add(memberEdit);
		
		JButton memberAdd = new JButton("추가");
		memberAdd.setActionCommand("member add");
		memberAdd.addActionListener(this);
		memberBtns.add(memberAdd);
		
		JButton memberExit = new JButton("삭제");
		memberExit.setActionCommand("member exit");
		memberExit.addActionListener(this);
		memberBtns.add(memberExit);
		
		members.add(memberBtns, BorderLayout.CENTER);
		
		tabs.addTab("회원",members);
		
		String name;
		int cost;
		int cost_production;
		String info;
		
		
		JPanel menus = new JPanel();
		JPanel menuLabel = new JPanel();
		
		JPanel fourth = new JPanel();
		fourth.setLayout(new GridLayout(1,2));
		
		JPanel menuleft1 = new JPanel();
		menuleft1.setLayout(new BorderLayout());
		
		JButton menuplus = new JButton("추가");
		menuplus.addActionListener(this);
		
		menuleft1_1.setLayout(new GridLayout(5,1));
		menuleft1.add(menuleft1_1,"Center");
		
		menuleft1.add(menuplus,"South");
		fourth.add(menuleft1);
		
		JPanel menuleft2 = new JPanel();
		JPanel menuleft2_1 = new JPanel();
		JPanel menuleft2_2 = new JPanel();
		menuleft2.setLayout(new GridLayout(1,2));
		menuleft2_1.setLayout(new GridLayout(5,1));
		
		JTextArea menu1 = new JTextArea("이름 : ",3,10);
		menu1.setEditable(false);
		menuleft2_1.add(menu1);
		JTextArea menu2 = new JTextArea("가격 : ",3,10);
		menu2.setEditable(false);
		menuleft2_1.add(menu2);
		JTextArea menu3 = new JTextArea("생산단가 : ",3,10);
		menu3.setEditable(false);
		menuleft2_1.add(menu3);
		JTextArea menu4 = new JTextArea("사용된 재료 : ",3,10);
		menu4.setEditable(false);
		menuleft2_1.add(menu4);
		
		JButton menuedit = new JButton("편집");
		menuedit.addActionListener(this);
		menuleft2_1.add(menuedit);
		
		menuleft2.add(menuleft2_1);

		menuleft2_2.setLayout(new GridLayout(5,1));
		
		input_menu1 = new JTextArea();
		menuleft2_2.add(input_menu1);
		
		input_menu2 = new JTextArea();
		menuleft2_2.add(input_menu2);
		
		input_menu3 = new JTextArea();
		menuleft2_2.add(input_menu3);
		
		input_menu4 = new JTextArea();
		menuleft2_2.add(input_menu4);
	
		JButton menudelete = new JButton("삭제");
		menudelete.addActionListener(this);
		menuleft2_2.add(menudelete);
		
		menuleft2.add(menuleft2_2);
		
		fourth.add(menuleft2);
		
		
		menuLabel.add(fourth);
		menus.add(menuLabel);
		tabs.addTab("메뉴",menus);

		
		
		JPanel employees = new JPanel();
		employees.setLayout(new BorderLayout());
		employeeListItem[0][0] = (Object)"ID";
		employeeListItem[0][1] = (Object)"이름";
		employeeListItem[0][2] = (Object)"급여";
		employeeListItem[0][3] = (Object)"직급";
		employeeListItem[0][4] = (Object)"입사일";
		employeeListItem[0][5] = (Object)"연락처";
		
		employeeList = new JTable(employeeListItem,employeeColumnNames);
		employeeList.getSelectionModel().addListSelectionListener(this);
		
		JScrollPane employeeScollPane = new JScrollPane(employeeList);
		
		JButton employeeedit = new JButton("편집");
		employeeedit.setActionCommand("직원편집");
		employeeedit.addActionListener(this);
		
		JButton employeeplus = new JButton("추가");
		employeeplus.setActionCommand("직원추가");
		employeeplus.addActionListener(this);
		
		JButton employeedelete = new JButton("삭제");
		employeedelete.setActionCommand("직원삭제");
		employeedelete.addActionListener(this);
		
		JPanel employeetriplebutton = new JPanel();
		employeetriplebutton.setLayout(new GridLayout(1,3));
		
		employeetriplebutton.add(employeeedit);
		employeetriplebutton.add(employeeplus);
		employeetriplebutton.add(employeedelete);
		
		
		employees.add(employeeList,BorderLayout.CENTER);
		employees.add(employeetriplebutton,BorderLayout.SOUTH);
		
		
		tabs.addTab("직원",employees);

		add(tabs, BorderLayout.CENTER);
		
		
	}
	
	public String billing() {
		String returnStr = "";
		double finalPrice = 0.0;
		int tableIndex = currentTable;
		System.out.println(tableIndex);
		for(int i = 0; tableMenus[tableIndex].menuListName[i] != null; i++) {
			returnStr = returnStr + "Menu " + (i+1) + " : " + tableMenus[tableIndex].menuListName[i] + " " 
		+ tableMenus[tableIndex].menuListPrice[i] + "원" + System.lineSeparator();
			finalPrice += tableMenus[tableIndex].menuListPrice[i];
		}
		
		returnStr = returnStr + "---------------------" + System.lineSeparator() + "Total : " + finalPrice + "원" + System.lineSeparator();
		return returnStr;
	}
	
	public double calPrice(int tableIndex) {
		double finalPrice = 0.0;
		for(int i = 0; tableMenus[tableIndex].menuListName[i] != null; i++) {
			finalPrice += tableMenus[tableIndex].menuListPrice[i];
		}
		
		return finalPrice;
	}
	
	private class AddingMenu extends JFrame implements ActionListener, ItemListener{
		
		private JLabel orderLabel = new JLabel("                                       피자의 베이스를 정해주세요.");
		private JCheckBox[] checkPizzaBasics = new JCheckBox[3];
		
	    
	    private JLabel plusLabel = new JLabel("                                          토핑을 선택해주세요.");
	    private JCheckBox[] checkPizzaPluss = new JCheckBox[5];
	    
	    
	    private JLabel sideLabel = new JLabel("                                         사이드 메뉴를 선택해주세요.");
	    private JCheckBox[] checkSideMenus = new JCheckBox[5];
	    
	    
	    private JLabel drinkLabel = new JLabel("                                           음료를 선택해주세요.");
	    private JCheckBox[] checkDrinks = new JCheckBox[5];
	    
	    
	    private JLabel animaLabel = new JLabel("                                  서빙해주길 원하는 마스코트가 있으신가요?");
	    private JCheckBox[] checkAnimas = new JCheckBox[3];
	    
	    
		public AddingMenu() {
			super();
			setSize(500, 400);
			getContentPane().setBackground(Color.DARK_GRAY);
			setLayout(new GridLayout(6,1));
			
			JPanel panel1 = new JPanel();
			panel1.setLayout(new GridLayout(2,1));
			panel1.add(orderLabel);
			
			JPanel checkPanel1 = new JPanel();
			checkPanel1.setLayout(new GridLayout(1,3));
			checkPanel1.setBackground(Color.white);
			
			checkPizzaBasics[0] = new JCheckBox("치즈");
		    checkPizzaBasics[1] = new JCheckBox("콤비네이션");
		    checkPizzaBasics[2] = new JCheckBox("시카고");
		    
		    for(int i = 0 ; i < 3; i++) {
				checkPizzaBasics[i].addItemListener(this);
				checkPanel1.add(checkPizzaBasics[i]);
		    }
			panel1.add(checkPanel1);
			
			add(panel1);

			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(2,1));
			panel2.add(plusLabel);
			
			JPanel checkPanel2 = new JPanel();
			checkPanel2.setLayout(new GridLayout(1,5));
			checkPanel2.setBackground(Color.white);
			
			checkPizzaPluss[0] = new JCheckBox("불고기");
		    checkPizzaPluss[1] = new JCheckBox("감자");
		    checkPizzaPluss[2] = new JCheckBox("새우");
		    checkPizzaPluss[3] = new JCheckBox("고구마");
		    checkPizzaPluss[4] = new JCheckBox("스테이크");
		    
		    for(int i = 0; i < 5; i++) {
				checkPizzaPluss[i].addItemListener(this);
				checkPanel2.add(checkPizzaPluss[i]);
		    }
			panel2.add(checkPanel2);
			
			add(panel2);
			
			JPanel panel3 = new JPanel();
			panel3.setLayout(new GridLayout(2,1));
			panel3.add(sideLabel);
			
			JPanel checkPanel3 = new JPanel();
			checkPanel3.setLayout(new GridLayout(1,5));
			
			checkSideMenus[0] = new JCheckBox("버팔로 윙");
		    checkSideMenus[1] = new JCheckBox("스파게티");
		    checkSideMenus[2] = new JCheckBox("감자튀김");
		    checkSideMenus[3] = new JCheckBox("치킨 볼");
		    checkSideMenus[4] = new JCheckBox("새우튀김");
		    
			checkPanel3.setBackground(Color.white);
			
			for(int i = 0; i < 5; i++) {
				checkSideMenus[i].addItemListener(this);
				checkPanel3.add(checkSideMenus[i]);
			}
			panel3.add(checkPanel3);
			
			add(panel3);
			
			JPanel panel4 = new JPanel();
			panel4.setLayout(new GridLayout(2,1));
			panel4.add(drinkLabel);
			
			JPanel checkPanel4 = new JPanel();
			checkPanel4.setLayout(new GridLayout(1,5));
			checkPanel4.setBackground(Color.white);
			
			checkDrinks[0] = new JCheckBox("코카콜라");
		    checkDrinks[1] = new JCheckBox("환타");
		    checkDrinks[2] = new JCheckBox("스프라이트");
		    checkDrinks[3] = new JCheckBox("오렌지주스");
		    checkDrinks[4] = new JCheckBox("마운틴 듀");
		     
		    for(int i = 0 ; i < 5; i++) {
		    		checkDrinks[i].addItemListener(this);
		    		checkPanel4.add(checkDrinks[i]);
		    }
			panel4.add(checkPanel4);
			
			add(panel4);
			
			JPanel panel5 = new JPanel();
			panel5.setLayout(new GridLayout(2,1));
			panel5.add(animaLabel);
			
			JPanel checkPanel5 = new JPanel();
			checkPanel5.setLayout(new GridLayout(1,3));
			checkPanel5.setBackground(Color.white);
			
			checkAnimas[0] = new JCheckBox("프레디");
		    checkAnimas[1] = new JCheckBox("보니");
		    checkAnimas[2] = new JCheckBox("치카");
		    
		    for(int i = 0; i < 3; i++) {
				checkAnimas[i].addItemListener(this);
				checkPanel5.add(checkAnimas[i]);
		    }
			panel5.add(checkPanel5);
			
			add(panel5);
			
			JPanel checkBtns = new JPanel();
			checkBtns.setLayout(new FlowLayout());
			JButton checkOk = new JButton("확인");
			checkOk.addActionListener(this);
			JButton cancel = new JButton("취소");
			cancel.addActionListener(this);
			checkBtns.add(checkOk);
			checkBtns.add(cancel);
			
			add(checkBtns);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			int finalPrice = 0;
			
			int index = tableMenus[currentTable].foodIndex;
			if(cmd.equals("확인")) {
				for(int i = 0 ; i < 3; i++) {
					if(checkPizzaBasics[i].isSelected()) {
						tableMenus[currentTable] = new FoodMenu();
						tableMenus[currentTable].menuListName[index] = checkPizzaBasics[i].getText();
						tableMenus[currentTable].menuListPrice[index] = 5000 * i;
						System.out.printf("%s %d",tableMenus[currentTable].menuListName[index],tableMenus[currentTable].menuListPrice[index]);
						index++;
						finalPrice += 5000*i;
						break;
					}
				}
				for(int i = 0 ; i < 5; i++) {
					if(checkPizzaPluss[i].isSelected()) {
						tableMenus[currentTable].menuListName[index] = checkPizzaPluss[i].getText();
						tableMenus[currentTable].menuListPrice[index] = 1000 * (5 - i);
						System.out.printf("%s %d",tableMenus[currentTable].menuListName[index],tableMenus[currentTable].menuListPrice[index]);
						index++;
						finalPrice += 1000*(5-i);
						break;
					}
				}
				for(int i = 0 ; i < 5; i++) {
					if(checkSideMenus[i].isSelected()) {
						tableMenus[currentTable].menuListName[index] = checkSideMenus[i].getText();
						tableMenus[currentTable].menuListPrice[index] = 5000;
						System.out.printf("%s %d",tableMenus[currentTable].menuListName[index],tableMenus[currentTable].menuListPrice[index]);
						index++;
						finalPrice += 5000;
						break;
					}
				}
				for(int i = 0 ; i < 5; i++) {
					if(checkDrinks[i].isSelected()) {
						tableMenus[currentTable].menuListName[index] = checkDrinks[i].getText();
						tableMenus[currentTable].menuListPrice[index] = 1500;
						System.out.printf("%s %d",tableMenus[currentTable].menuListName[index],tableMenus[currentTable].menuListPrice[index]);
						index++;
						
						finalPrice += 1500;
						break;
					}
				}
				for(int i = 0 ; i < 3; i++) {
					if(checkAnimas[i].isSelected()) {
						tableMenus[currentTable].menuListName[index] = checkAnimas[i].getText();
						tableMenus[currentTable].menuListPrice[index] = 1000;
						System.out.printf("%s %d",tableMenus[currentTable].menuListName[index],tableMenus[currentTable].menuListPrice[index]);
						index++;
						finalPrice += 1000;
						break;
					}
				}
				
				tableMenus[currentTable].foodIndex = index;
				
				for(int i = 0; i < index; i++) {
					tableListItem[i+1][0] = tableMenus[currentTable].menuListName[i];
					tableListItem[i+1][1] = tableMenus[currentTable].menuListPrice[i];
				}
				tableTextArea[currentTable].setText("\n\n\n" +"        테이블 " + currentTable +"\n        "
						+ "" + tableMenus[currentTable].getPrice());
				dispose();
			}
			else if(cmd.equals("취소")) {
				dispose();
			}
			
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
		}
		
	}
	
	// table 결제버튼 클릭 시 나오는 창
	private class Payment extends JFrame implements ActionListener, Serializable{
		
		JTextField nameField = new JTextField();
		JTextField idField = new JTextField();
		JTextField phoneField = new JTextField();
		public int tableNum = 0;
		public Payment(int num) {
			super();
			tableNum = num;
			currentTable = num;
			setSize(300,500);
			getContentPane().setBackground(Color.white);
			setLayout(new BorderLayout());
			
			JTextArea bill = new JTextArea(11,11);
			bill.setText(billing());
			add(bill, BorderLayout.NORTH);
			
			JPanel inputPanel = new JPanel();
			inputPanel.setLayout(new GridLayout(3,1));
			
			JPanel namePanel = new JPanel();
			namePanel.setLayout(new BorderLayout());
			JLabel nameText = new JLabel("이름(YN)");
			namePanel.add(nameText,BorderLayout.WEST);
			namePanel.add(nameField, BorderLayout.CENTER);
			inputPanel.add(namePanel);
			
			JPanel idPanel = new JPanel();
			idPanel.setLayout(new BorderLayout());
			JLabel idText = new JLabel("ID(숫자)");
			idPanel.add(idText,BorderLayout.WEST);
			idPanel.add(idField, BorderLayout.CENTER);
			inputPanel.add(idPanel);
			
			JPanel phonePanel = new JPanel();
			phonePanel.setLayout(new BorderLayout());
			JLabel phoneText = new JLabel("전화번호");
			phonePanel.add(phoneText,BorderLayout.WEST);
			phonePanel.add(phoneField, BorderLayout.CENTER);
			inputPanel.add(phonePanel);
			
			add(inputPanel, BorderLayout.CENTER);
			
			JButton inputBtn = new JButton("입력");
			inputBtn.setActionCommand("inputPay");
			inputBtn.addActionListener(this);
			add(inputBtn, BorderLayout.SOUTH);
			
		}
		
		public int findMember(String newName, int newId_number, String newPhone) {
			int memberIndex = -1;
			for(int i = 0; i < memberCount && members[i] != null; i++) {
				if(members[i].name.equals(newName) && members[i].id_number == newId_number 
						&& members[i].phoneNumber.equals(newPhone)) {
					memberIndex = i;
					break;
				}	
			}
			
			return memberIndex;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			
			if(cmd.equals("inputPay")) {
				int memberNum;
				if(nameField.getText().equals("")) todayMoney += calPrice(tableNum);
				else {
					memberNum = findMember(nameField.getText(),Integer.parseInt(idField.getText()),phoneField.getText());
					
					if(memberNum == -1) {
						members[memberCount] = new Member(nameField.getText()
								,Integer.parseInt(idField.getText()), phoneField.getText());
						members[memberCount].newPayment(calPrice(tableNum));
						if(memberCount < 10) {
							memberListItem[memberCount + 1][0] = members[memberCount].id_number;
							memberListItem[memberCount + 1][1] = members[memberCount].grade;
							memberListItem[memberCount + 1][2] = members[memberCount].name;
							memberListItem[memberCount + 1][3] = members[memberCount].mileage;
							memberListItem[memberCount + 1][4] = members[memberCount].phoneNumber;
						}
						todayMoney += calPrice(tableNum);
						moneyLabel.setText("오늘 매출 : " + todayMoney + "원 " + "전체 잔고 : " + acumulateMoney + "원");
						System.out.println(members[memberCount].mileage);
					}
					else {
						if(members[memberNum].grade.equals("gold")) 
							todayMoney += (calPrice(tableNum) * 0.95);
						else if(members[memberNum].grade.equals("platinum")) 
							todayMoney += (calPrice(tableNum) * 0.9);
						else
							todayMoney += (calPrice(tableNum) * 0.98);
						members[memberNum].newPayment(calPrice(tableNum));
						if(memberCount < 10) {
							memberListItem[memberNum + 1][0] = members[memberNum].id_number;
							memberListItem[memberNum + 1][1] = members[memberNum].grade;
							memberListItem[memberNum + 1][2] = members[memberNum].name;
							memberListItem[memberNum + 1][3] = members[memberNum].mileage;
							memberListItem[memberNum + 1][4] = members[memberNum].phoneNumber;
						}
						System.out.println(members[memberNum].mileage);
					}
					memberCount++;
				}
				
				tableMenus[currentTable] = new FoodMenu();
				for(int i = 0; i < 10; i++) {
					if(tableMenus[currentTable].menuListName[i] == null || tableMenus[currentTable].menuListName[i].equals("")) {
						tableListItem[i+1][0] = "";
						tableListItem[i+1][1] = "";
					}
					else{
					tableListItem[i+1][0] = tableMenus[currentTable].menuListName[i];
					tableListItem[i+1][1] = tableMenus[currentTable].menuListPrice[i];
					}
				}
				tableTextArea[currentTable].setText("\n\n\n" +"        테이블 " + currentTable +"\n        "
						+ "" + tableMenus[currentTable].getPrice());
				dispose();
			}
		}
	}	
	private class FlushWindow extends JFrame{
		public FlushWindow() {
			super();
			dispose();
		}
	}
	//종료시 나오는 창
	private class ConfirmWindow extends JFrame implements ActionListener, Serializable{
		public ConfirmWindow() {
			super();
			setSize(500,200);
			getContentPane().setBackground(Color.LIGHT_GRAY);
			setLayout(new BorderLayout());
			
			JLabel confirmLabel = new JLabel("    야간 알바생을 끔찍한 악몽으로 내몰고 도망시겠습니까?");
			confirmLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
			add(confirmLabel, BorderLayout.CENTER);
			
			JPanel btnPanel = new JPanel();
			btnPanel.setBackground(Color.DARK_GRAY);
			btnPanel.setLayout(new FlowLayout());
			
			JButton saveExitBtn = new JButton("내일 돌아오겠소");
			saveExitBtn.setActionCommand("saveExit");
			saveExitBtn.addActionListener(this);
			btnPanel.add(saveExitBtn);
			
			JButton exitBtn = new JButton("가게를 버리겠소");
			exitBtn.setActionCommand("exit");
			exitBtn.addActionListener(this);
			btnPanel.add(exitBtn);
			
			JButton cancelBtn = new JButton("아직 떠나지 않겠소");
			cancelBtn.setActionCommand("cancel");
			cancelBtn.addActionListener(this);
			btnPanel.add(cancelBtn);
			
			add(btnPanel, BorderLayout.SOUTH);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			
			if(cmd.equals("saveExit")) {
				try {
					ObjectOutputStream saving = new ObjectOutputStream(new FileOutputStream("save"));
					saving.writeObject(FNAF_Screen.this);
				}catch(IOException a) {
					a.printStackTrace();
				}
				System.exit(0);
			}else if(cmd.equals("exit")) {
				try {
					ObjectOutputStream saving = new ObjectOutputStream(new FileOutputStream("save"));
					saving.writeObject((FNAF_Screen) null);
				}catch(IOException a) {
					a.printStackTrace();
				}
				System.exit(0);
			}else {
				dispose();
			}
		}
	}
	
	private class OrderWindow extends JFrame implements ActionListener, Serializable{
		
		JTextField ammount = new JTextField();
		
		public OrderWindow() {
			super();
			setSize(250,100);
			getContentPane().setBackground(Color.LIGHT_GRAY);
			setLayout(new BorderLayout());
			JLabel orderTextLabel = new JLabel("   주문 혹은 주문 취소하실 양을 적어주세요");
			add(orderTextLabel, BorderLayout.NORTH);
			add(ammount,BorderLayout.CENTER);
			
			JPanel orderWindowBtnPanel = new JPanel();
			orderWindowBtnPanel.setLayout(new FlowLayout());
			JButton ordering = new JButton("주문");
			ordering.addActionListener(this);
			JButton cancelOrdering = new JButton("주문 취소");
			cancelOrdering.addActionListener(this);
			
			orderWindowBtnPanel.add(ordering);
			orderWindowBtnPanel.add(cancelOrdering);
			
			add(orderWindowBtnPanel, BorderLayout.SOUTH);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
		
			if(cmd.equals("주문")){
				ingredients[currentIngredient].calling += Integer.parseInt(ammount.getText());
				acumulateMoney -= ingredients[currentIngredient].price * Integer.parseInt(ammount.getText());
				moneyLabel.setText("오늘 매출 : " + todayMoney + "원 " + "전체 잔고 : " + acumulateMoney + "원");
				
				dispose();
			}
			else if(cmd.equals("주문 취소")) {
				ingredients[currentIngredient].calling -= Integer.parseInt(ammount.getText());
				acumulateMoney += ingredients[currentIngredient].price * Integer.parseInt(ammount.getText());
				if(ingredients[currentIngredient].calling < 0) ingredients[currentIngredient].calling = 0;
				moneyLabel.setText("오늘 매출 : " + todayMoney + "원 " + "전체 잔고 : " + acumulateMoney + "원");
				
				dispose();
			}
			itemOrderedText.setText(String.valueOf(ingredients[storageList.getSelectedRow() - 1].calling));
			
				storageListItem[currentIngredient+1][0] = ingredients[currentIngredient].name;
				storageListItem[currentIngredient+1][1] = ingredients[currentIngredient].leaving;
				storageListItem[currentIngredient+1][2] = ingredients[currentIngredient].calling;
				storageListItem[currentIngredient+1][3] = ingredients[currentIngredient].price;
		}
	}
	
	private class PlusOrderWindow extends JFrame implements ActionListener, Serializable{
		//Ingredients(String newName, int newLeaving, int newPrice, String newSeller, String newPhoneNumber)
		JLabel nameOrderLabel = new JLabel();
		JLabel priceOrderLabel = new JLabel();
		JLabel sellerOrderLabel = new JLabel();
		JLabel callOrderLabel = new JLabel();
		JLabel orderedOrderLabel = new JLabel();
	
		JTextField nameField = new JTextField();
		JTextField priceField = new JTextField();
		JTextField sellerField = new JTextField();
		JTextField callField = new JTextField();
		JTextField orderedField = new JTextField();
		
		
		public PlusOrderWindow() {
			super();
			setSize(250,400);
			getContentPane().setBackground(Color.LIGHT_GRAY);
			
			JPanel inputOrderPanel = new JPanel();
			inputOrderPanel.setLayout(new GridLayout(6,2));
			nameOrderLabel.setText("이름 : ");
			priceOrderLabel.setText("가격 : ");
			sellerOrderLabel.setText("판매처 : ");
			callOrderLabel.setText("연락처 : ");
			orderedOrderLabel.setText("주문하실 양 : ");
			
			inputOrderPanel.add(nameOrderLabel);
			inputOrderPanel.add(nameField);
			inputOrderPanel.add(priceOrderLabel);
			inputOrderPanel.add(priceField);
			inputOrderPanel.add(sellerOrderLabel);
			inputOrderPanel.add(sellerField);
			inputOrderPanel.add(callOrderLabel);
			inputOrderPanel.add(callField);
			inputOrderPanel.add(orderedOrderLabel);
			inputOrderPanel.add(orderedField);
			
			add(inputOrderPanel, BorderLayout.NORTH);
			
			JPanel inputOrderBtnPanel = new JPanel();
			inputOrderBtnPanel.setLayout(new FlowLayout());
			
			JButton inputPlusOrder = new JButton("추가");
			JButton cancelPlusOrder = new JButton("취소");
			inputPlusOrder.addActionListener(this);
			cancelPlusOrder.addActionListener(this);
			inputOrderBtnPanel.add(inputPlusOrder);
			inputOrderBtnPanel.add(cancelPlusOrder);
			
			add(inputOrderBtnPanel, BorderLayout.CENTER);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			int i;
			
			if(cmd.equals("추가")) {
				//Ingredients(String newName, int newLeaving, int newPrice, String newSeller, String newPhoneNumber)
				Ingredients another = new Ingredients(nameField.getText(), 0,
						Integer.parseInt(priceField.getText()), sellerField.getText(), callField.getText());
				another.plusCalling(Integer.parseInt(orderedField.getText()));
				for(i = 0; i < ingredientCount; i++) {
					if(ingredients[i] == null) {
						ingredients[i] = another;
						acumulateMoney -= ingredients[i].price * Integer.parseInt(orderedField.getText());
						moneyLabel.setText("오늘 매출 : " + todayMoney + "원 " + "전체 잔고 : " + acumulateMoney + "원");
						break;
					}
				}
				if(i == ingredientCount) {
					ingredients[i] = another;
					acumulateMoney -= ingredients[i].price * Integer.parseInt(orderedField.getText());
					moneyLabel.setText("오늘 매출 : " + todayMoney + "원 " + "전체 잔고 : " + acumulateMoney + "원");
					ingredientCount++;
				}
				storageListItem[i+1][0] = ingredients[i].name;
				storageListItem[i+1][1] = ingredients[i].leaving;
				storageListItem[i+1][2] = ingredients[i].calling;
				storageListItem[i+1][3] = ingredients[i].price;
				
				dispose();
			}
			else if(cmd.equals("취소"))
				dispose();
		}
	}
	
	private class EditMemberWindow extends JFrame implements ActionListener, Serializable{
		//public Member(String newName, int newId_number, String newPhoneNumber)
		JLabel nameMemberLabel = new JLabel();
		JLabel idMemberLabel = new JLabel();
		JLabel phoneMemberLabel = new JLabel();
	
		JTextField nameField = new JTextField();
		JTextField idField = new JTextField();
		JTextField phoneField = new JTextField();
		
		
		public EditMemberWindow() {
			super();
			setSize(250,400);
			getContentPane().setBackground(Color.LIGHT_GRAY);
			
			JPanel inputOrderPanel = new JPanel();
			inputOrderPanel.setLayout(new GridLayout(4,2));
			nameMemberLabel.setText("이름 : ");
			idMemberLabel.setText("ID(숫자) : ");
			phoneMemberLabel.setText("연락처 : ");
			
			inputOrderPanel.add(nameMemberLabel);
			inputOrderPanel.add(nameField);
			inputOrderPanel.add(idMemberLabel);
			inputOrderPanel.add(idField);
			inputOrderPanel.add(phoneMemberLabel);
			inputOrderPanel.add(phoneField);
			
			add(inputOrderPanel, BorderLayout.NORTH);
			
			JPanel inputOrderBtnPanel = new JPanel();
			inputOrderBtnPanel.setLayout(new FlowLayout());
			
			JButton inputPlusOrder = new JButton("변경");
			JButton cancelPlusOrder = new JButton("취소");
			inputPlusOrder.addActionListener(this);
			cancelPlusOrder.addActionListener(this);
			inputOrderBtnPanel.add(inputPlusOrder);
			inputOrderBtnPanel.add(cancelPlusOrder);
			
			add(inputOrderBtnPanel, BorderLayout.CENTER);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			int i;
			if(cmd.equals("변경")) {
				//Ingredients(String newName, int newLeaving, int newPrice, String newSeller, String newPhoneNumber)
				members[currentMember].setMember(nameField.getText(),
						Integer.parseInt(idField.getText()), phoneField.getText());
				memberListItem[currentMember+1][0] = members[currentMember].id_number;
				memberListItem[currentMember+1][2] = members[currentMember].name;
				memberListItem[currentMember+1][4] = members[currentMember].phoneNumber;
				
				dispose();
			}
			else if(cmd.equals("취소"))
				dispose();
		}
	}
	
	private class AddEmployeeWindow extends JFrame implements ActionListener, Serializable{
		//Employee(int newId, String newName, int newSalary, String newPosition, Date newDate, String newPhoneNumber)
		
		JLabel idEmployeeLabel = new JLabel();
		JLabel nameEmployeeLabel = new JLabel();
		JLabel salaryEmployeeLabel = new JLabel();
		JLabel positionEmployeeLabel = new JLabel();
		JLabel phoneEmployeeLabel = new JLabel();
	
		JTextField idField = new JTextField();
		JTextField nameField = new JTextField();
		JTextField salaryField = new JTextField();
		JTextField positionField = new JTextField();
		JTextField phoneField = new JTextField();
		
		public AddEmployeeWindow() {
			super();
			setSize(250,400);
			getContentPane().setBackground(Color.LIGHT_GRAY);
			
			JPanel inputOrderPanel = new JPanel();
			inputOrderPanel.setLayout(new GridLayout(5,2));
			idEmployeeLabel.setText("ID(숫자) : ");
			nameEmployeeLabel.setText("이름 : ");
			salaryEmployeeLabel.setText("연봉 : ");
			positionEmployeeLabel.setText("직급 : ");
			phoneEmployeeLabel.setText("연락처 : ");
			
			inputOrderPanel.add(idEmployeeLabel);
			inputOrderPanel.add(idField);
			inputOrderPanel.add(nameEmployeeLabel);
			inputOrderPanel.add(nameField);
			inputOrderPanel.add(salaryEmployeeLabel);
			inputOrderPanel.add(salaryField);
			inputOrderPanel.add(positionEmployeeLabel);
			inputOrderPanel.add(positionField);
			inputOrderPanel.add(phoneEmployeeLabel);
			inputOrderPanel.add(phoneField);
			
			add(inputOrderPanel, BorderLayout.NORTH);
			
			JPanel inputOrderBtnPanel = new JPanel();
			inputOrderBtnPanel.setLayout(new FlowLayout());
			
			JButton inputPlusOrder = new JButton("추가");
			JButton cancelPlusOrder = new JButton("취소");
			inputPlusOrder.addActionListener(this);
			cancelPlusOrder.addActionListener(this);
			inputOrderBtnPanel.add(inputPlusOrder);
			inputOrderBtnPanel.add(cancelPlusOrder);
			
			add(inputOrderBtnPanel, BorderLayout.CENTER);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			int i;
			
			if(cmd.equals("추가")) {
				
				//Employee(int newId, String newName, int newSalary, String newPosition, Date newDate, String newPhoneNumber)
				Employee another = new Employee(Integer.parseInt(idField.getText()),nameField.getText()
						,Integer.parseInt(salaryField.getText()), positionField.getText(),today, phoneField.getText());
				for(i = 0; i < memberCount; i++) {
					if(employees[i] == null) {
						employees[i] = another;
						break;
					}
				}
				if(i == memberCount) {
					employees[i] = another;
					memberCount++;
				}
				employeeListItem[i+1][0] = employees[i].id;
				employeeListItem[i+1][1] = employees[i].name;
				employeeListItem[i+1][2] = employees[i].salary;
				employeeListItem[i+1][3] = employees[i].position;
				employeeListItem[i+1][4] = employees[i].hired;
				employeeListItem[i+1][5] = employees[i].phoneNumber;
				
				dispose();
			}
			else if(cmd.equals("취소"))
				dispose();
		}
	}
	
	private class EditEmployeeWindow extends JFrame implements ActionListener, Serializable{
		JLabel nameEmployeeLabel = new JLabel();
		JLabel salaryEmployeeLabel = new JLabel();
		JLabel positionEmployeeLabel = new JLabel();
		JLabel phoneEmployeeLabel = new JLabel();
	
		JTextField nameField = new JTextField();
		JTextField salaryField = new JTextField();
		JTextField positionField = new JTextField();
		JTextField phoneField = new JTextField();
		
		public EditEmployeeWindow() {
			super();
			setSize(250,340);
			getContentPane().setBackground(Color.LIGHT_GRAY);
			
			JPanel inputOrderPanel = new JPanel();
			inputOrderPanel.setLayout(new GridLayout(4,2));
			nameEmployeeLabel.setText("이름 : ");
			salaryEmployeeLabel.setText("연봉 : ");
			positionEmployeeLabel.setText("직급 : ");
			phoneEmployeeLabel.setText("연락처 : ");
			
			inputOrderPanel.add(nameEmployeeLabel);
			inputOrderPanel.add(nameField);
			inputOrderPanel.add(salaryEmployeeLabel);
			inputOrderPanel.add(salaryField);
			inputOrderPanel.add(positionEmployeeLabel);
			inputOrderPanel.add(positionField);
			inputOrderPanel.add(phoneEmployeeLabel);
			inputOrderPanel.add(phoneField);
			
			add(inputOrderPanel, BorderLayout.NORTH);
			
			JPanel inputOrderBtnPanel = new JPanel();
			inputOrderBtnPanel.setLayout(new FlowLayout());
			
			JButton inputPlusOrder = new JButton("변경");
			JButton cancelPlusOrder = new JButton("취소");
			inputPlusOrder.addActionListener(this);
			cancelPlusOrder.addActionListener(this);
			inputOrderBtnPanel.add(inputPlusOrder);
			inputOrderBtnPanel.add(cancelPlusOrder);
			
			add(inputOrderBtnPanel, BorderLayout.CENTER);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			int i;
			
			if(cmd.equals("변경")) {
				//setEmployee(int newId, String newName, int newSalary, String newPosition, String newPhoneNumber) {
				System.out.printf("%d\n",currentEmployee);
				employees[currentEmployee].setEmployee(nameField.getText(), Integer.parseInt(salaryField.getText()), 
						positionField.getText(), phoneField.getText());
				employeeListItem[currentEmployee+1][1] = employees[currentEmployee].name;
				employeeListItem[currentEmployee+1][2] = employees[currentEmployee].salary;
				employeeListItem[currentEmployee+1][3] = employees[currentEmployee].position;
				employeeListItem[currentEmployee+1][5] = employees[currentEmployee].phoneNumber;
				
				dispose();
			}
			else if(cmd.equals("취소"))
				dispose();
		}
	}
	
	private class AddMemberWindow extends JFrame implements ActionListener, Serializable{
		//public Member(String newName, int newId_number, String newPhoneNumber)
		JLabel nameMemberLabel = new JLabel();
		JLabel idMemberLabel = new JLabel();
		JLabel phoneMemberLabel = new JLabel();
	
		JTextField nameField = new JTextField();
		JTextField idField = new JTextField();
		JTextField phoneField = new JTextField();
		
		
		public AddMemberWindow() {
			super();
			setSize(250,400);
			getContentPane().setBackground(Color.LIGHT_GRAY);
			
			JPanel inputOrderPanel = new JPanel();
			inputOrderPanel.setLayout(new GridLayout(4,2));
			nameMemberLabel.setText("이름 : ");
			idMemberLabel.setText("ID(숫자) : ");
			phoneMemberLabel.setText("연락처 : ");
			
			inputOrderPanel.add(nameMemberLabel);
			inputOrderPanel.add(nameField);
			inputOrderPanel.add(idMemberLabel);
			inputOrderPanel.add(idField);
			inputOrderPanel.add(phoneMemberLabel);
			inputOrderPanel.add(phoneField);
			
			add(inputOrderPanel, BorderLayout.NORTH);
			
			JPanel inputOrderBtnPanel = new JPanel();
			inputOrderBtnPanel.setLayout(new FlowLayout());
			
			JButton inputPlusOrder = new JButton("추가");
			JButton cancelPlusOrder = new JButton("취소");
			inputPlusOrder.addActionListener(this);
			cancelPlusOrder.addActionListener(this);
			inputOrderBtnPanel.add(inputPlusOrder);
			inputOrderBtnPanel.add(cancelPlusOrder);
			
			add(inputOrderBtnPanel, BorderLayout.CENTER);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			int i;
			
			if(cmd.equals("추가")) {
				
				//Ingredients(String newName, int newLeaving, int newPrice, String newSeller, String newPhoneNumber)
				Member another = new Member(nameField.getText(), 
						Integer.parseInt(idField.getText()), phoneField.getText());
				another.newPayment(0);
				for(i = 0; i < memberCount; i++) {
					if(members[i] == null) {
						members[i] = another;
						break;
					}
				}
				if(i == memberCount) {
					members[i] = another;
					memberCount++;
				}
				memberListItem[i+1][0] = members[i].id_number;
				memberListItem[i+1][1] = members[i].grade;
				memberListItem[i+1][2] = members[i].name;
				memberListItem[i+1][3] = members[i].mileage;
				memberListItem[i+1][4] = members[i].phoneNumber;
				
				dispose();
			}
			else if(cmd.equals("취소"))
				dispose();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("마감")) {
			acumulateMoney += todayMoney;
			todayMoney = 0;
			today.nextDay();
			dateA.setText(today.toString());
			
			for(int i = 0; i < ingredientCount && ingredients[i] != null; i++) {
				ingredients[i].leaving += ingredients[i].calling;
				ingredients[i].calling = 0;
				storageListItem[i+1][0] = ingredients[i].name;
				storageListItem[i+1][1] = ingredients[i].leaving;
				storageListItem[i+1][2] = ingredients[i].calling;
				storageListItem[i+1][3] = ingredients[i].price;
			}
			
			for(int i = 0; i < employeeCount && employees[i] != null; i++)
				acumulateMoney -= employees[i].salary;
			
			moneyLabel.setText("오늘 매출 : " + todayMoney + "원 " + "전체 잔고 : " + acumulateMoney + "원");
			
		}
		else if (cmd.equals("table 1")) {
			if(tableMenus[currentTable] != null && !tableMenus[currentTable].menuListName[0].equals("")){
				tablePanels[currentTable].setBackground(Color.cyan);
				tableTextArea[currentTable].setBackground(Color.cyan);
			}
			else {
				tablePanels[currentTable].setBackground(Color.white);
				tableTextArea[currentTable].setBackground(Color.white);
			}
			currentTable = 1;
			if(tableMenus[1] == null)
				tableMenus[1] = new FoodMenu();
			tablePanels[currentTable].setBackground(Color.blue);
			tableTextArea[currentTable].setBackground(Color.blue);
			for(int i = 0; i < 10; i++) {
				if(tableMenus[currentTable].menuListName[i] == null || tableMenus[currentTable].menuListName[i].equals("")) {
					tableListItem[i+1][0] = "";
					tableListItem[i+1][1] = "";
				}
				else{
				tableListItem[i+1][0] = tableMenus[currentTable].menuListName[i];
				tableListItem[i+1][1] = tableMenus[currentTable].menuListPrice[i];
				}
			}
			
			((AbstractTableModel) tableList.getModel()).fireTableDataChanged();
		}
		else if (cmd.equals("table 2")) {
			if(tableMenus[currentTable] != null && !tableMenus[currentTable].menuListName[0].equals("")){
				tablePanels[currentTable].setBackground(Color.cyan);
				tableTextArea[currentTable].setBackground(Color.cyan);
			}
			else {
				tablePanels[currentTable].setBackground(Color.white);
				tableTextArea[currentTable].setBackground(Color.white);
			}
			currentTable = 2;
			if(tableMenus[2] == null)
				tableMenus[2] = new FoodMenu();
			tablePanels[currentTable].setBackground(Color.blue);
			tableTextArea[currentTable].setBackground(Color.blue);
			for(int i = 0; i < 10; i++) {
				if(tableMenus[currentTable].menuListName[i] == null || tableMenus[currentTable].menuListName[i].equals("")) {
					tableListItem[i+1][0] = "";
					tableListItem[i+1][1] = "";
				}
				else{
				tableListItem[i+1][0] = tableMenus[currentTable].menuListName[i];
				tableListItem[i+1][1] = tableMenus[currentTable].menuListPrice[i];
				}
			}
			
			((AbstractTableModel) tableList.getModel()).fireTableDataChanged();
		}
		else if (cmd.equals("table 3")) {
			if(tableMenus[currentTable] != null && !tableMenus[currentTable].menuListName[0].equals("")){
				tablePanels[currentTable].setBackground(Color.cyan);
				tableTextArea[currentTable].setBackground(Color.cyan);
			}
			else {
				tablePanels[currentTable].setBackground(Color.white);
				tableTextArea[currentTable].setBackground(Color.white);
			}
			currentTable = 3;
			if(tableMenus[3] == null)
				tableMenus[3] = new FoodMenu();
			tablePanels[currentTable].setBackground(Color.blue);
			tableTextArea[currentTable].setBackground(Color.blue);
			for(int i = 0; i < 10; i++) {
				if(tableMenus[currentTable].menuListName[i] == null || tableMenus[currentTable].menuListName[i].equals("")) {
					tableListItem[i+1][0] = "";
					tableListItem[i+1][1] = "";
				}
				else{
				tableListItem[i+1][0] = tableMenus[currentTable].menuListName[i];
				tableListItem[i+1][1] = tableMenus[currentTable].menuListPrice[i];
				}
			}
			
			((AbstractTableModel) tableList.getModel()).fireTableDataChanged();
		}
		else if (cmd.equals("table 4")) {
			if(tableMenus[currentTable] != null && !tableMenus[currentTable].menuListName[0].equals("")){
				tablePanels[currentTable].setBackground(Color.cyan);
				tableTextArea[currentTable].setBackground(Color.cyan);
			}
			else {
				tablePanels[currentTable].setBackground(Color.white);
				tableTextArea[currentTable].setBackground(Color.white);
			}
			currentTable = 4;
			if(tableMenus[4] == null)
				tableMenus[4] = new FoodMenu();
			tablePanels[currentTable].setBackground(Color.blue);
			tableTextArea[currentTable].setBackground(Color.blue);
			for(int i = 0; i < 10; i++) {
				if(tableMenus[currentTable].menuListName[i] == null || tableMenus[currentTable].menuListName[i].equals("")) {
					tableListItem[i+1][0] = "";
					tableListItem[i+1][1] = "";
				}
				else{
				tableListItem[i+1][0] = tableMenus[currentTable].menuListName[i];
				tableListItem[i+1][1] = tableMenus[currentTable].menuListPrice[i];
				}
			}
			
			((AbstractTableModel) tableList.getModel()).fireTableDataChanged();
		}
		else if (cmd.equals("table 5")) {
			if(tableMenus[currentTable] != null && !tableMenus[currentTable].menuListName[0].equals("")){
				tablePanels[currentTable].setBackground(Color.cyan);
				tableTextArea[currentTable].setBackground(Color.cyan);
			}
			else {
				tablePanels[currentTable].setBackground(Color.white);
				tableTextArea[currentTable].setBackground(Color.white);
			}
			currentTable = 5;
			if(tableMenus[5] == null)
				tableMenus[5] = new FoodMenu();
			tablePanels[currentTable].setBackground(Color.blue);
			tableTextArea[currentTable].setBackground(Color.blue);
			for(int i = 0; i < 10; i++) {
				if(tableMenus[currentTable].menuListName[i] == null || tableMenus[currentTable].menuListName[i].equals("")) {
					tableListItem[i+1][0] = "";
					tableListItem[i+1][1] = "";
				}
				else{
				tableListItem[i+1][0] = tableMenus[currentTable].menuListName[i];
				tableListItem[i+1][1] = tableMenus[currentTable].menuListPrice[i];
				}
			}
			
			((AbstractTableModel) tableList.getModel()).fireTableDataChanged();
		}
		else if (cmd.equals("table 6")) {
			if(tableMenus[currentTable] != null && !tableMenus[currentTable].menuListName[0].equals("")){
				tablePanels[currentTable].setBackground(Color.cyan);
				tableTextArea[currentTable].setBackground(Color.cyan);
			}
			else {
				tablePanels[currentTable].setBackground(Color.white);
				tableTextArea[currentTable].setBackground(Color.white);
			}
			currentTable = 6;
			if(tableMenus[6] == null)
				tableMenus[6] = new FoodMenu();
			tablePanels[currentTable].setBackground(Color.blue);
			tableTextArea[currentTable].setBackground(Color.blue);
			for(int i = 0; i < 10; i++) {
				if(tableMenus[currentTable].menuListName[i] == null || tableMenus[currentTable].menuListName[i].equals("")) {
					tableListItem[i+1][0] = "";
					tableListItem[i+1][1] = "";
				}
				else{
				tableListItem[i+1][0] = tableMenus[currentTable].menuListName[i];
				tableListItem[i+1][1] = tableMenus[currentTable].menuListPrice[i];
				}
			}
			
			((AbstractTableModel) tableList.getModel()).fireTableDataChanged();
		}
		else if (cmd.equals("table 7")) {
			if(tableMenus[currentTable] != null && !tableMenus[currentTable].menuListName[0].equals("")){
				tablePanels[currentTable].setBackground(Color.cyan);
				tableTextArea[currentTable].setBackground(Color.cyan);
			}
			else {
				tablePanels[currentTable].setBackground(Color.white);
				tableTextArea[currentTable].setBackground(Color.white);
			}
			currentTable = 7;
			if(tableMenus[7] == null)
				tableMenus[7] = new FoodMenu();
			tablePanels[currentTable].setBackground(Color.blue);
			tableTextArea[currentTable].setBackground(Color.blue);
			for(int i = 0; i < 10; i++) {
				if(tableMenus[currentTable].menuListName[i] == null || tableMenus[currentTable].menuListName[i].equals("")) {
					tableListItem[i+1][0] = "";
					tableListItem[i+1][1] = "";
				}
				else{
				tableListItem[i+1][0] = tableMenus[currentTable].menuListName[i];
				tableListItem[i+1][1] = tableMenus[currentTable].menuListPrice[i];
				}
			}
			
			((AbstractTableModel) tableList.getModel()).fireTableDataChanged();
		}
		else if (cmd.equals("table 8")) {
			if(tableMenus[currentTable] != null && !tableMenus[currentTable].menuListName[0].equals("")){
				tablePanels[currentTable].setBackground(Color.cyan);
				tableTextArea[currentTable].setBackground(Color.cyan);
			}
			else {
				tablePanels[currentTable].setBackground(Color.white);
				tableTextArea[currentTable].setBackground(Color.white);
			}
			currentTable = 8;
			if(tableMenus[8] == null)
				tableMenus[8] = new FoodMenu();
			tablePanels[currentTable].setBackground(Color.blue);
			tableTextArea[currentTable].setBackground(Color.blue);
			for(int i = 0; i < 10; i++) {
				if(tableMenus[currentTable].menuListName[i] == null || tableMenus[currentTable].menuListName[i].equals("")) {
					tableListItem[i+1][0] = "";
					tableListItem[i+1][1] = "";
				}
				else{
				tableListItem[i+1][0] = tableMenus[currentTable].menuListName[i];
				tableListItem[i+1][1] = tableMenus[currentTable].menuListPrice[i];
				}
			}
			
			((AbstractTableModel) tableList.getModel()).fireTableDataChanged();
		}
		else if(cmd.equals("menu add")) {
			AddingMenu adding = new AddingMenu();
			adding.setVisible(true);
		}
		else if(cmd.equals("menu pay")) {
			Payment paying = new Payment(currentTable);
			paying.setVisible(true);
		}
		else if(cmd.equals("member edit")) {
			EditMemberWindow editMemberWindow = new EditMemberWindow();
			editMemberWindow.setVisible(true);
		}
		else if(cmd.equals("member add")) {
			AddMemberWindow addMemberWindow = new AddMemberWindow();
			addMemberWindow.setVisible(true);
		}
		else if(cmd.equals("member exit")) {
			System.out.println(currentMember);
			members[currentMember] = null;
			
			for(int i = 0; i < 5; i++)
				memberListItem[currentMember + 1][i] = "";
			((AbstractTableModel) memberList.getModel()).fireTableDataChanged();
		}
		else if(cmd.equals("추가") && menu_number < 5)
		{
			int k = menu_number;
			System.out.println(k);
			menu_making[k] = new JButton("음식 "+k);
			menu_making[k].addActionListener(this);
			menuleft1_1.add(menu_making[k]);
			menu_number++;
			input_menu1.setText("");
			input_menu2.setText("");
			input_menu3.setText("");
			input_menu4.setText("");
		}
		else if(cmd.equals("음식 1")) {
			now_edit_menu = 1;
			if(in.has(now_edit_menu-1)) {
				input_menu1.setText(in.returnName(now_edit_menu-1));
				input_menu2.setText(String.valueOf(in.returnPrice(now_edit_menu-1)));
				input_menu3.setText(String.valueOf(in.returnCostProduction(now_edit_menu-1)));
				input_menu4.setText(in.returnIndex(now_edit_menu-1));
			}
		}
		else if(cmd.equals("음식 2")) {
			now_edit_menu = 2;
			if(in.has(now_edit_menu-1)) {
				input_menu1.setText(in.returnName(now_edit_menu-1));
				input_menu2.setText(String.valueOf(in.returnPrice(now_edit_menu-1)));
				input_menu3.setText(String.valueOf(in.returnCostProduction(now_edit_menu-1)));
				input_menu4.setText(in.returnIndex(now_edit_menu-1));
			}
		}
		else if(cmd.equals("음식 3")) {
			now_edit_menu = 3;
			if(in.has(now_edit_menu-1)) {
				input_menu1.setText(in.returnName(now_edit_menu-1));
				input_menu2.setText(String.valueOf(in.returnPrice(now_edit_menu-1)));
				input_menu3.setText(String.valueOf(in.returnCostProduction(now_edit_menu-1)));
				input_menu4.setText(in.returnIndex(now_edit_menu-1));
			}
		}
		else if(cmd.equals("음식 4")) {
			now_edit_menu = 4;
			if(in.has(now_edit_menu-1)) {
				input_menu1.setText(in.returnName(now_edit_menu-1));
				input_menu2.setText(String.valueOf(in.returnPrice(now_edit_menu-1)));
				input_menu3.setText(String.valueOf(in.returnCostProduction(now_edit_menu-1)));
				input_menu4.setText(in.returnIndex(now_edit_menu-1));
			}
		}
		else if(cmd.equals("음식 5")) {
			now_edit_menu = 5;
			if(in.has(now_edit_menu-1)) {
				input_menu1.setText(in.returnName(now_edit_menu-1));
				input_menu2.setText(String.valueOf(in.returnPrice(now_edit_menu-1)));
				input_menu3.setText(String.valueOf(in.returnCostProduction(now_edit_menu-1)));
				input_menu4.setText(in.returnIndex(now_edit_menu-1));
			}
		}
		else if(cmd.equals("편집")) {
			if(now_edit_menu == 1) {
				name = input_menu1.getText();
				info = input_menu4.getText();
				cost = Integer.parseInt(input_menu2.getText());
				cost_production = Integer.parseInt(input_menu3.getText());
				in.setMenu(now_edit_menu-1,name,cost,cost_production,info);
			}
			else if(now_edit_menu == 2) {
				name = input_menu1.getText();
				info = input_menu4.getText();
				cost = Integer.parseInt(input_menu2.getText());
				cost_production = Integer.parseInt(input_menu3.getText());
				in.setMenu(now_edit_menu-1,name,cost,cost_production,info);
			}
			else if(now_edit_menu == 3) {
				name = input_menu1.getText();
				info = input_menu4.getText();
				cost = Integer.parseInt(input_menu2.getText());
				cost_production = Integer.parseInt(input_menu3.getText());
				in.setMenu(now_edit_menu-1,name,cost,cost_production,info);
			}
			else if(now_edit_menu == 4) {
				name = input_menu1.getText();
				info = input_menu4.getText();
				cost = Integer.parseInt(input_menu2.getText());
				cost_production = Integer.parseInt(input_menu3.getText());
				in.setMenu(now_edit_menu-1,name,cost,cost_production,info);
			}
		}
		else if(cmd.equals("삭제")) {
			if(in.has(now_edit_menu-1)) {
				input_menu1.setText(in.returnName(now_edit_menu-1));
				input_menu2.setText(String.valueOf(in.returnPrice(now_edit_menu-1)));
				input_menu3.setText(String.valueOf(in.returnCostProduction(now_edit_menu-1)));
				input_menu4.setText(in.returnIndex(now_edit_menu-1));
				in.setMenu(now_edit_menu-1,"",0,0,"");
			}
		}
		else if(cmd.equals("storage add")) {
			PlusOrderWindow plusorders = new PlusOrderWindow();
			plusorders.setVisible(true);
		}
		else if(cmd.equals("storage delete")) {
			ingredients[currentIngredient] = null;
			
			for(int i = 0; i < 4; i++)
				storageListItem[currentIngredient + 1][i] = "";
			
			itemNameText.setText("");
			itemPriceText.setText("");
			itemSellerText.setText("");
			itemCallText.setText("");
			itemAmountText.setText("");
			itemOrderedText.setText("");
		}
		else if(cmd.equals("order ingre")) {
			OrderWindow orders = new OrderWindow();
			orders.setVisible(true);
		}
		else if(cmd.equals("직원편집")) {
			EditEmployeeWindow editEmployeeWindow = new EditEmployeeWindow();
			editEmployeeWindow.setVisible(true);
		}
		else if(cmd.equals("직원추가")) {
			AddEmployeeWindow addEmployeeWindow = new AddEmployeeWindow();
			addEmployeeWindow.setVisible(true);
		}
		else if(cmd.equals("직원삭제")) {
			employees[currentEmployee] = null;
			
			for(int i = 0; i < 6; i++)
				employeeListItem[currentEmployee + 1][i] = "";
		}
		((AbstractTableModel) employeeList.getModel()).fireTableDataChanged();
	}

	//List 이벤트 발생시!
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(storageList.getSelectedRow() > 0)
			if(ingredients[storageList.getSelectedRow() - 1] != null) {
				itemNameText.setText(ingredients[storageList.getSelectedRow() - 1].name);
				itemPriceText.setText(String.valueOf(ingredients[storageList.getSelectedRow() - 1].price));
				itemSellerText.setText(ingredients[storageList.getSelectedRow() - 1].name);
				itemCallText.setText(ingredients[storageList.getSelectedRow() - 1].phoneNumber);
				itemAmountText.setText(String.valueOf(ingredients[storageList.getSelectedRow() - 1].leaving));
				itemOrderedText.setText(String.valueOf(ingredients[storageList.getSelectedRow() - 1].calling));
			}
		
		currentIngredient = storageList.getSelectedRow() - 1;
		if(memberList.getSelectedRow() > 0)
			currentMember = memberList.getSelectedRow() - 1;
		if(employeeList.getSelectedRow() > 0)
			currentEmployee = employeeList.getSelectedRow() - 1;
		System.out.printf("%d\n", currentEmployee);
	}
	
	@Override
	public void windowOpened(WindowEvent e) {// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		ConfirmWindow checkers = new ConfirmWindow();
		checkers.setVisible(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent e) {// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent e) {// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(WindowEvent e) {// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {// TODO Auto-generated method stub
	}
}
