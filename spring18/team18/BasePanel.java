package storeManagementProgram2;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class BasePanel extends JFrame{
	/**
	 * 바탕 패널을 구현한 클래스입니다
	 * 결제 시스템 위의 시간 및 매출 상태를 기록하는 상태바 가 함께 구현되어있습니다.
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 1000;
	public static int HEIGHT = 700;
	
	private JPanel basePanel;
	private JPanel foundationPanel;
	private Calendar cal = Calendar.getInstance();
	private String mTime;
	private int balance = 0;
	private JLabel printBalance;
	private int year= 0, month= 0, date= 0;

	TablePanel t = new TablePanel();
	Warehouse w = new Warehouse();
	Menu m = new Menu();
	Staff s;
	Membership mem = new Membership();
	
	
	
	public BasePanel() {
		super("Res Management System");
		Container con = this.getContentPane();
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());
		basePanel.setBorder(new LineBorder(Color.BLACK,1));
		
		foundationPanel = new JPanel();
		foundationPanel.setLayout(null);
		foundationPanel.setPreferredSize(new Dimension(1000, 150));
		foundationPanel.setBorder(new LineBorder(Color.BLACK,1));
		foundationPanel.setBackground(Color.WHITE);
		basePanel.add(foundationPanel, BorderLayout.NORTH);
		
		try {
			File accountFile = new File("account.txt");
			Scanner fr = new Scanner(new FileReader(accountFile));
			String yearS = fr.next();
			String monthS = fr.next();
			String dateS = fr.next();
			mTime = yearS + " " + monthS + " " + dateS;
			year = Integer.valueOf(yearS.substring(0, 4));
			if(monthS.length()>2) {
				month = Integer.valueOf(monthS.substring(0, 2));
			}else {
				month = Integer.valueOf(monthS.substring(0, 1));
			}
			
			if(dateS.length()>2) {
				date = Integer.valueOf(dateS.substring(0, 2));
			}else {
				date = Integer.valueOf(dateS.substring(0, 1));
			}
			cal.set(year, month-1, date);
			
			fr.next();
			balance = fr.nextInt();
			fr.close();
		}catch(FileNotFoundException e) {
			System.out.println("Cannot found account File");
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH)+1;
			date = cal.get(Calendar.DATE);
		}
		mTime = DateCheck(cal);
		JLabel printDate = new JLabel(mTime);		//기본 상태 바에서 출력할 정보(현재날짜, 매출, 잔고);
		printDate.setFont(new Font("San Serif", Font.PLAIN, 25));
		foundationPanel.add(printDate);					
		printDate.setBounds(25, 15, 300, 25);
		
		s = new Staff(mTime);
		
		JButton closeBtn = new JButton(" 마감 ");			//마감버튼 구현하기;
		closeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!t.checkPayment()){
					System.out.println("아직 결제가 이루어지지 않은 테이블이 있습니다.");
					warningCheckPaymentSubFrame warning = new warningCheckPaymentSubFrame();
				}
				else {
					balance += t.totalMoney;
					try {
						File account = new File("account.txt");
						PrintWriter inputTodaySales = new PrintWriter(new BufferedWriter(new FileWriter(account, false)));
						inputTodaySales.println(mTime + "\t" + t.totalMoney + "\t" + balance);
						inputTodaySales.close();
					}catch(IOException ex) {
						System.out.println("Fail to input account data on file");
					}
				
					
					int checkMonth = cal.get(Calendar.MONTH)+1;
					cal.set(Calendar.DATE, date+1);
					if(checkMonth!=cal.get(Calendar.MONTH)+1) {
						date = 1;	
					}else {
						date += 1;
					}
					mTime = DateCheck(cal);
					printDate.setText(mTime);
					
					processDelivery();
					processSalary(date);
					
					printBalance.setText("  잔액  :  " + balance + " 원");
					t.totalMoney = 0;
					t.printSales.setText(" 총 매출  :  " + t.totalMoney + " 원");
					
					Calendar temp = Calendar.getInstance();
					temp.add(Calendar.DATE, 1);
					if(temp.get(Calendar.DATE) == 1) {
						mem.resetGrade(1);
						mem.showTable();
					}
					
				}
			}
			
		});
		foundationPanel.add(closeBtn);
		closeBtn.setBounds(850, 90, 60, 40);
		
		t.printSales.setFont(new Font("San Serif", Font.PLAIN, 25));
		foundationPanel.add(t.printSales);
		t.printSales.setBounds(400, 15, 300, 25);
		
		printBalance = new JLabel(" 잔액  :  " + balance + " 원");
		printBalance.setFont(new Font("San Serif", Font.PLAIN, 25));
		foundationPanel.add(printBalance);
		printBalance.setBounds(700, 15, 300, 25);
		
		JButton endBtn = new JButton(" 종료 ");
		endBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		foundationPanel.add(endBtn);
		endBtn.setBounds(920, 90, 60, 40);
		
		
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);		//탭 버튼 선언;
		tab.addTab(" 테이블 ", t.wholeData);
		tab.addTab(" 창고 ", w.dataPanel);
		tab.addTab(" 회원 ",  mem.dataPanel);
		tab.addTab(" 메뉴 ", m.dataPanel);
		tab.addTab(" 직원 ",  s.dataPanel);
		tab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				mem.refreshAll();
				w.refreshAll();
			}
	    });
		basePanel.add(tab,"Center");
		
		con.add(basePanel);
		
		setVisible(true);
	}
	
	public String DateCheck(Calendar cal) {
		String now = cal.get(Calendar.YEAR)+"년 "+
				(cal.get(Calendar.MONTH)+1)+"월 "+
				cal.get(Calendar.DATE)+"일 ";
		return now;
	}
	
	public void processDelivery() {
		
		try {
			File delivery = new File("stock.txt");
			BufferedReader br = new BufferedReader(new FileReader(delivery));
			
			DefaultTableModel stockModel;			//창고 재고정보 테이블;
			JTable stockList;
			String stockListHeader[] = {"Type", "Stock", "Price", "Expiration Data", "Order", "where", "contact"};		//테이블 열 제목;
			String stockArray[][] = new String[0][7];		//테이블 내부 정보 저장 배열;
			stockModel = new DefaultTableModel(stockArray, stockListHeader);		//재고 리스트 저장 테이블 선언;
			stockList = new JTable(stockModel);
			importStockData(stockList);
			
			SetNewStockExpSubFrame expSet;
			int getRow = stockList.getRowCount();
			for(int i=0;i<getRow; i++) {
				if(Integer.valueOf((String)stockList.getValueAt(i,  4)) != 0) {
					stockList.setValueAt("0",  i,  4);
					for(int j=0; j<7;j++) {
						if(j==3) {
							expSet = new SetNewStockExpSubFrame(i, stockList, stockModel, (String) stockList.getValueAt(i,  0));
							continue;
						}
					}
				}
			}
			br.close();
		}catch(IOException ex) {
			System.out.println("Fail to read delivery data to process delivery");
		}
	}
	
	public void processSalary(int nowDate) {
		try {
			File salary = new File("staff.txt");
			BufferedReader br = new BufferedReader(new FileReader(salary));
			
			DefaultTableModel staffModel;			//정보 테이블;
			JTable staffList;
			String staffListHeader[] = {"Number", "Name", "Salary", "Position", "Phone","Day"};		//테이블 열 제목;
			String staffArray[][] = new String[0][6];		//테이블 내부 정보 저장 배열;
			staffModel = new DefaultTableModel(staffArray, staffListHeader);		//리스트 저장 테이블 선언;
			staffList = new JTable(staffModel);
			importFileData(staffList, salary);
		
			int getRow = staffList.getRowCount();
			int sumSal = 0;
			for(int i=0;i<getRow; i++) {
				int tempDate;
				String temp = (String) staffList.getValueAt(i,  5);
				try {
					tempDate = Integer.valueOf(temp.substring(temp.length()-4, temp.length()-2));
				}catch(NumberFormatException e) {
					tempDate = Integer.valueOf(temp.substring(temp.length()-4, temp.length()-3));
				}
				if(tempDate == nowDate){
					sumSal += Integer.valueOf((String) staffList.getValueAt(i,  2));
				}
			}
			balance -=sumSal;
			System.out.println("직원 급여가 " + sumSal + "원 지급되었습니다.");
			br.close();
		}catch(IOException ex) {
			System.out.println("Fail to read staff data to process salary");
		}
	}
	
	public class SetNewStockExpSubFrame extends JFrame implements ActionListener{

		/**
		 * 주문한 새로운 재고 도착 후 유통기한 설정 창;
		 */
		private static final long serialVersionUID = 1L;
		
		public SetNewStockExpSubFrame(int selected, JTable stockList, DefaultTableModel stockModel, String type) {
			super("Set New Stock Expiration Date");
			Container con = this.getContentPane();
			setSize(400, 100);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			
			JLabel stockName = new JLabel("    새로운 <" + type + "> 재고 유통기한 설정    ");
			con.add(stockName, BorderLayout.NORTH);
			JTextField inputDate = new JTextField();
			con.add(inputDate, BorderLayout.CENTER);
		
			JButton confirmBtn = new JButton("Confirm");
			confirmBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String[] inputStr = new String[7];
					for(int j=0; j<7;j++) {
						if(j==3) {
							inputStr[j] = inputDate.getText();
							continue;
						}
						inputStr[j] = (String)stockList.getValueAt(selected,  j);
					}
					stockModel.addRow(inputStr);
					exportStockData(stockList);
					w.importStockData();
					
					balance -= Integer.valueOf(inputStr[1]) * Integer.valueOf(inputStr[2]);
					if(balance<0) {
						System.out.println("잔고가 부족합니다.");
					}
					
					dispose();
					
				}
			});
			
			con.add(confirmBtn, BorderLayout.SOUTH);
		
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
		
	}
	
	public class warningCheckPaymentSubFrame extends JFrame implements ActionListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public warningCheckPaymentSubFrame(){
			super("warning");
			setSize(350,150);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			
			JLabel warn = new JLabel("    아직 결제하지 않은 테이블이 있습니다.    ");
			warn.setFont(new Font("San Serif", Font.PLAIN, 20));
			add(warn, BorderLayout.CENTER);
			
			JButton check = new JButton("  확인  ");
			check.addActionListener(this);
			add(check, BorderLayout.SOUTH);
			
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
		
	}
	
	public void exportStockData(JTable stockList) {
		/*
		 * 재고 리스트를 파일로 출력하는 메서드;
		 */
		try {
			File file = new File("stock.txt");
			if(!file.exists()) {
				file.createNewFile();
				
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i<stockList.getRowCount(); i++) {
				for(int j = 0; j<stockList.getColumnCount(); j++) {
					bw.write(stockList.getValueAt(i,  j).toString() + "\t");
				}
				bw.newLine();
			}
			
			System.out.println("Success export data");
			
			bw.close();
			fw.close();
		}catch (IOException ex) {
			System.out.println("Fail to export data");
		}
	}
	
	public void importStockData(JTable stockList) {
		/*
		 * 파일로부터 재고리스트를 불러와 테이블에 로드하는 메서드;
		 */
		File file = new File("stock.txt");
		if(!file.exists()) {
			return;
		}
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			DefaultTableModel tempModel = (DefaultTableModel) stockList.getModel();
			Object[] lines = br.lines().toArray();
			
			
			for(int i=0; i<lines.length; i++) {
				String[] row = lines[i].toString().split("\t");
				tempModel.addRow(row);
			}
			
			br.close();
		}catch(Exception ex){
			System.out.println("Fail to load stock data");
		}
	}
	
	public void importFileData(JTable list, File file) {
		/*
		 * 파일로부터 테이블 리스트를 불러와 테이블에 로드하는 메서드;
		 */
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			DefaultTableModel model = (DefaultTableModel) list.getModel();
			Object[] lines = br.lines().toArray();
			
			
			for(int i=0; i<lines.length; i++) {
				String[] row = lines[i].toString().split("\t");
				model.addRow(row);
			}
			
			br.close();
		}catch(Exception ex){
			System.out.println("Fail to load file data");
		}
	}
}
