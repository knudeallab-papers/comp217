package Restraunt;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Restraunt extends JFrame implements ActionListener, Serializable{

	public static final int WIDTH = 610;
	public static final int HEIGHT = 600;
	public static final int NUMBER_OF_TABLE = 6;
	
	private static int todaySales = 0;
	private static int totalSales = 0;
	
	public static int year = 2018;
	public static int month = 6;
	public static int day = 30;
	
	static JLabel SalesLabel;
	static JLabel DateLabel;
	
	public static int get_year() { return year; }
	public static int get_month() { return month;}
	public static int get_day() { return day; }
	public static int get_Sales(int money, int number) { return totalSales - (money*number); }
	
	static Restraunt mainWindow;
	public static void main(String[] args) {
		mainWindow = new Restraunt();

		mainWindow.setVisible(true);
	}
	
	public Restraunt() {
		super("Restraunt");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(null);
		
		JPanel navPanel = new JPanel();
		navPanel.setBackground(Color.WHITE);
		navPanel.setLayout(null);
			
		/* 테이블 + 창고 + 회원 + 메뉴 + 직원 버튼 */
		JTabbedPane TablePane = setTablePane();
		navPanel.add(TablePane);
		TablePane.setBounds(0, HEIGHT/10 + 20, WIDTH, HEIGHT/10*9 - 30);
		
		ReadData();
		
		/* 날짜 + 마감 */
		JPanel DatePanel = setDatePanel();
		navPanel.add(DatePanel);
		DatePanel.setBounds(0, 0, WIDTH/2, HEIGHT/10);
		
		/* 오늘 매출 */
		JPanel SalesPanel = setSalesPanel();
		navPanel.add(SalesPanel);
		SalesPanel.setBounds(WIDTH/2, 0, WIDTH/2 + 100, HEIGHT/10);

		/* 종료 버튼  */
		JPanel exitPanel = setExitPanel();
		navPanel.add(exitPanel);
		exitPanel.setBounds(WIDTH/2, HEIGHT/10 + 5, WIDTH/2, HEIGHT/10 - 20);
		
		add(navPanel);
		navPanel.setBounds(0,0,WIDTH, HEIGHT);
	}

	public JPanel setDatePanel() {
		JPanel DatePanel = new JPanel();
		
		DateLabel = new JLabel("2018년 6월 30일");
		DateLabel.setText(year + "년 " + month + "월 " + day + "일 ");
		JButton finishButton = new JButton("마감");
		finishButton.setActionCommand("finish Button");
		finishButton.addActionListener(this);
	
		DatePanel.setLayout(null);
		DatePanel.add(DateLabel);
		DatePanel.add(finishButton);
		
		DateLabel.setBounds(15, 0, 130, 50);
		finishButton.setBounds(135, 10, 50, 30);
		
		return DatePanel;
	}
	
	public JPanel setSalesPanel() {
		JPanel SalesPanel = new JPanel();
		SalesPanel.setLayout(null);
		
		SalesLabel = new JLabel();
		SalesLabel.setText("오늘의 매출: " + todaySales +"원  전체 잔고: " + totalSales + "원");
		
		SalesPanel.add(SalesLabel);
		SalesLabel.setBounds(0, 0, 250, 50);
		
		return SalesPanel;
	}
	
	public JPanel setExitPanel() {
		JPanel exitPanel = new JPanel();
		exitPanel.setLayout(null);
		exitPanel.setBackground(Color.WHITE);
		
		JButton exitButton = new JButton("종료");
		exitButton.setActionCommand("exit Button");
		exitButton.addActionListener(this);
		exitPanel.add(exitButton);
		exitButton.setBounds(200, 0, 70, 25);
		
		return exitPanel;
	}
	
	Tab_Menu TabMenu;
	Tab_Table TabTable;
	Tab_Member TabMember;
	Tab_Employee TabEmployee;
	Container container;
	JTabbedPane tablePane;
	ImportAccount importAccount;
	ExportAccount exportAccount;
	
	public JTabbedPane setTablePane() {
		tablePane = new JTabbedPane();
		
		TabMenu = new Tab_Menu();
		TabTable = new Tab_Table();
		TabMember = new Tab_Member();
		TabEmployee = new Tab_Employee();
		container = new Container();
		importAccount = new ImportAccount();
		exportAccount = new ExportAccount();
		
		tablePane.add("테이블", TabTable);
		tablePane.add("창고", container);
		tablePane.add("회원", TabMember);
		tablePane.add("메뉴", TabMenu );
		tablePane.add("직원", TabEmployee);
		tablePane.add("수입목록", importAccount);
		tablePane.add("지출목록", exportAccount);

		ImportAccount.writeAccount(year + "년 " + month + "월 " + day + "일 " + "매출: " + todaySales +"원  전체 잔고: " + totalSales + "원");
		ExportAccount.writeAccount(year + "년 " + month + "월 " + day + "일 " + "매출: " + todaySales +"원  전체 잔고: " + totalSales + "원");
		
		return tablePane;
	}
	
	
	public JPanel setOrderPanel() {
		JPanel orderPanel = new JPanel();
		orderPanel.setLayout(null);
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(new GridLayout(1,2));
		
		JLabel Menu = new JLabel("	메뉴");
		JLabel Price = new JLabel("	가격");
		
		upPanel.add(Menu);
		upPanel.add(Price);
		orderPanel.add(upPanel);
		upPanel.setBounds(0,0,200,25);
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(10,2));
		
		orderPanel.setBackground(Color.GRAY);
		
		return orderPanel;
	}
	
	public void updateSales() {
		totalSales += todaySales;
		todaySales = 0;
	}
	
	public void updateDate() {
		day++;
		if( day >= 31) {
			totalSales -= Tab_Employee.getEmployeeFee();
			TabMember.resetMileage();
			ExportAccount.writeAccount("직원 지출 " + Tab_Employee.getEmployeeFee() +"\n", Tab_Employee.getEmployeeFee());
			month++;
			day = 1;
		}
		if( month > 12) {
			year++;
			month = 1;
		}
		
		ImportAccount.writeAccount(" 오늘의 수익 총합은 : " + ImportAccount.todayImport + "\n");
		ExportAccount.writeAccount(" 오늘의 지출 총액은 : " + ExportAccount.todayExport + "\n");

		
		ImportAccount.resetTodayImport();
		ExportAccount.resetTodayExport();
		
		ImportAccount.writeAccount(year + "년 " + month + "월 " + day + "일 " + "매출: " + todaySales +"원  전체 잔고: " + totalSales + "원");
		ExportAccount.writeAccount(year + "년 " + month + "월 " + day + "일 " + "매출: " + todaySales +"원  전체 잔고: " + totalSales + "원");
	}
	
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		
		System.out.println(actionCmd);
		
		if( actionCmd.equals("exit Button") ){
			if( TabTable.emptyTable() ) {
				DataBackup();
				System.exit(0);
			}
			else {
				errorHandler("아직 남아있는 테이블이 있습니다. ");
			}
			
		}
		else if( actionCmd.equals("finish Button") ){
			if( Tab_Table.emptyTable() ) {
				updateSales();
				updateDate();
				SalesLabel.setText("오늘의 매출: " + todaySales +"원  전체 잔고: " + totalSales + "원");
				DateLabel.setText(year + "년 " + month + "월 " + day + "일 ");
			}
			else {
				errorHandler("아직 주문된 테이블이 남아있습니다.");
			}
		}
	}
	
	public static void addtodaySale(int price) {
		todaySales += price;
		SalesLabel.setText("오늘의 매출: " + todaySales +"원  전체 잔고: " + totalSales + "원");
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
	
	class errListener implements ActionListener , Serializable{
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("OK")) {
				errFrame.setVisible(false);
			}
		}
	}
	
	public static void ContainerAddMaterial(int price) {
		System.out.println(price);
		totalSales -= price;
		SalesLabel.setText("오늘의 매출: " + todaySales +"원  전체 잔고: " + totalSales + "원");
	}
	
	public static void ContainerMinusMaterial(int price) {
		System.out.println(price);
		totalSales += price;
		SalesLabel.setText("오늘의 매출: " + todaySales +"원  전체 잔고: " + totalSales + "원");
	}
	
	public void DataBackup() { //종료 할ㄷ 
		try {
			PrintWriter outstream = new PrintWriter(new FileOutputStream("myRestraunt.txt"));
			
			outstream.println(todaySales);
			outstream.println(totalSales);
			outstream.println(year);
			outstream.println(month);
			outstream.println(day);
			
			outstream.println(Tab_Employee.EmployeeNumber);
			for(int i=0; i<Tab_Employee.EmployeeNumber; i++) {
				for(int j=0; j<4; j++) {
					String tmp = Tab_Employee.EmployeeLabel[i][j].getText().trim();
					outstream.println(tmp);
				}
				outstream.println(Tab_Employee.EmployeeLabel[i][5].getText().trim());
				
				String tmp = Tab_Employee.EmployeeJoinYearLabel[i].getText().trim();
				outstream.println(tmp);
				String tmp2 = Tab_Employee.EmployeeJoinDateLabel[i].getText().trim();
				outstream.println(tmp2);
			}
			
			outstream.println(Tab_Member.MemberNumber);
			for(int i=0; i<TabMember.MemberNumber; i++) {
				for(int j=0; j<5; j++) {
					String tmp = TabMember.MemberLabel[i][j].getText().trim();
					outstream.println(tmp);
				}
			}
			
			outstream.println(Tab_Menu.MenuNumber);
			for(int i=0; i<Tab_Menu.MenuNumber; i++) {
				outstream.println(Tab_Menu.MenuName[i]);
				outstream.println(Tab_Menu.MenuPrice[i]);
				outstream.println(Tab_Menu.MenuPrice2[i]);
				for(int j=0; j<6; j++) {
					outstream.println(Tab_Menu.MenuMaterial[i][j]);
				}
			}
			
			outstream.println(Container.count);
			for(int i=0; i<Container.count; i++) {
				outstream.println(Container.ItemName.get(i));
				outstream.println(Container.ItemFrom.get(i));
				outstream.println(Container.ItemCall.get(i));
				outstream.println(Container.ItemPrice.get(i));
				outstream.println(Container.ItemNowVol.get(i));
				outstream.println(Container.ItemBuyVol.get(i));
			}
			
			outstream.close();
			
			outstream = new PrintWriter(new FileOutputStream("myRestraunt_import.txt"));
			outstream.println(ImportAccount.todayImport);
			outstream.println(ImportAccount.AccountBook.getText());
			
			outstream.close();
			
			outstream = new PrintWriter(new FileOutputStream("myRestraunt_export.txt"));
			outstream.println(ExportAccount.todayExport);
			outstream.println(ExportAccount.AccountBook.getText());
			
			outstream.close();
		}
		catch( FileNotFoundException e) {
			System.err.println("File Not Found - Restraunt");
		}
	}
	
	public void ReadData() {
		try {
			Scanner inputstream = new Scanner(new FileInputStream("myRestraunt.txt"));
			
			totalSales = inputstream.nextInt();
			todaySales = inputstream.nextInt();
			year =inputstream.nextInt();
			month =inputstream.nextInt();
			day = inputstream.nextInt();
			
			TabEmployee.EmployeeNumber = inputstream.nextInt();
			String tmp = inputstream.nextLine();
			System.out.println(TabEmployee.EmployeeNumber);
			
			for(int i=0; i<TabEmployee.EmployeeNumber; i++) {
				for(int j=0; j<4; j++) {
					TabEmployee.EmployeeLabel[i][j].setText("         " + inputstream.nextLine());
				}
				TabEmployee.EmployeeLabel[i][5].setText("         " + inputstream.nextLine());
				
				TabEmployee.EmployeeJoinYearLabel[i].setText("         " + inputstream.nextLine());
				TabEmployee.EmployeeJoinDateLabel[i].setText("         " + inputstream.nextLine());
			}
			
			TabMember.MemberNumber = inputstream.nextInt();
			inputstream.nextLine();
			System.out.println(TabMember.MemberNumber);
			for(int i=0; i<TabMember.MemberNumber; i++) {
				for(int j=0; j<5; j++) {
					TabMember.MemberLabel[i][j].setText("         " + inputstream.nextLine());
				}
			}
			
			TabMenu.MenuNumber = inputstream.nextInt();
			System.out.println(TabMenu.MenuNumber);
			tmp = inputstream.nextLine();
			
			for(int i=0; i<TabMenu.MenuNumber; i++) {
				TabMenu.MenuName[i] = inputstream.nextLine();
				TabMenu.MenuPrice[i] = inputstream.nextInt();
				TabMenu.MenuPrice2[i] = inputstream.nextInt();

				System.out.println("OKKK");
				
				TabMenu.MenuButton[i].setText(TabMenu.MenuName[i]);
				
				TabMenu.MenuLabel[i][0].setText(TabMenu.MenuName[i]);
				TabMenu.MenuLabel[i][1].setText(TabMenu.MenuPrice[i]+"");
				TabMenu.MenuLabel[i][2].setText(TabMenu.MenuPrice2[i]+"");
				
				inputstream.nextLine();
				for(int j=0; j<6; j++) {
					TabMenu.MenuMaterial[i][j] = inputstream.nextLine();
					
					System.out.println(TabMenu.MenuMaterial[i][j]);
					TabMenu.MenuLabel[i][3].setText(TabMenu.MenuLabel[i][3].getText() + "\n" + TabMenu.MenuMaterial[i][j]);
				}
			}
			
			container.count = inputstream.nextInt();
			System.out.println(container.count);
			tmp = inputstream.nextLine();
			
			for(int i=0; i<Container.count; i++) {
				container.ItemName.add(i,inputstream.nextLine());
				container.ItemFrom.add(i,inputstream.nextLine());
				container.ItemCall.add(i,inputstream.nextLine());
				container.ItemPrice.add(i,inputstream.nextLine());
				container.ItemNowVol.add(i,inputstream.nextLine());
				container.ItemBuyVol.add(i,inputstream.nextLine());
				
				container.Saving[i][0] = container.ItemName.get(i);
				container.Saving[i][1] = container.ItemNowVol.get(i);
				container.Saving[i][2] = container.ItemPrice.get(i);
				
				container.model.addRow(container.Saving[i]);
			}
			
			inputstream.close();
			
			inputstream = new Scanner(new FileInputStream("myRestraunt_import.txt"));
			ImportAccount.todayImport = inputstream.nextInt();
			
			ImportAccount.AccountBook.setText("");
			String line = null;
			while( inputstream.hasNextLine()) {
				line = inputstream.nextLine();
				ImportAccount.AccountBook.setText(ImportAccount.AccountBook.getText() + line);
				ImportAccount.AccountBook.setText(ImportAccount.AccountBook.getText() + "\n");
			}
			
			inputstream.close();
			inputstream = new Scanner(new FileInputStream("myRestraunt_export.txt"));
			ExportAccount.todayExport = inputstream.nextInt();
			
			ExportAccount.AccountBook.setText("");
			while( inputstream.hasNextLine()) {
				line = inputstream.nextLine();
				ExportAccount.AccountBook.setText(ExportAccount.AccountBook.getText() + line);
				ExportAccount.AccountBook.setText(ExportAccount.AccountBook.getText() + "\n");
			}
			
			inputstream.close();
		}
		catch( FileNotFoundException e) {
			System.err.println("File Not Found - Restraunt");
		}
	}
}
