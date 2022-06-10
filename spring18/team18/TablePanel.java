package storeManagementProgram2;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.table.TableColumnModel;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class TablePanel extends JFrame implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int MWIDTH = 1200;
	public static final int MHEIGHT = 800; 
	public JPanel wholeData = new JPanel();
	public JButton[] tables = new JButton[8];
	private JButton addMenu, payMenu;	
	private JLabel[] tLabel = new JLabel[8];
	private JLabel[] tmoney = new JLabel[8];
	private JTable table;
	private JScrollPane jp;
	protected int totalMoney = 0;
	private int[] tableMoney = new int[8];
	private int menuCount;
	private int memberCount;
	private static int tableSelected = -1;
	private static int[] menuCountPerTable = new int[8];
	protected String[][][] tData = new String[8][35][3];
	protected static String[][] copyData = new String[35][3];	
	protected String[][] memb = new String[30][5];
	protected String[] menuName = new String[30];
	protected int[] menuPrice = new int[30];
	private String[] col = {"메뉴", "수량", "가격"}; 
	
	JLabel printSales = new JLabel(" 총 매출  :  " + totalMoney + " 원");
	
	public TablePanel() {
		super("TablePanel");
		setSize(MWIDTH,MHEIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wholeData.setLayout(new BorderLayout());
		
		for(int i = 0; i < 8; i++) {
			tables[i] = new JButton();
			//tables[i].setPreferredSize(new Dimension(100, 100));
			tables[i].setOpaque(true);
			tables[i].setBorderPainted(false);
			tables[i].setBackground(Color.LIGHT_GRAY);
			tables[i].setLayout(new BorderLayout());
			tables[i].setActionCommand("table" + i);
			tables[i].addActionListener(this);
			tLabel[i] = new JLabel("Table " + (i+1));
			tLabel[i].setFont(new Font("SansSerif", Font.BOLD, 15));
			tLabel[i].setHorizontalAlignment(JLabel.CENTER);;
			//tLabel[i].setBounds(46, 40, 70, 15);
			tmoney[i] = new JLabel("총액: 0원");
			tmoney[i].setForeground(Color.BLUE);
			tmoney[i].setHorizontalAlignment(JLabel.CENTER);
			//tmoney[i].setBounds(48, 75, 90, 15);
			tables[i].add(tLabel[i], BorderLayout.CENTER);
			tables[i].add(tmoney[i], BorderLayout.SOUTH);
			tables[i].addMouseListener(this);
		}
		
		JPanel tablePnl = new JPanel();
		//tablePnl.setBounds(0, 0, 700, 800);
		tablePnl.setBorder(new LineBorder(Color.BLACK));
		tablePnl.setLayout(new GridLayout(2, 4));
		for(int i = 0; i < 8; i++) {
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.setBorder(new LineBorder(Color.BLACK));
			//tables[i].setBounds(15, 75, 150, 150);
			temp.add(tables[i], BorderLayout.CENTER);
			tablePnl.add(temp);
		}
		
		//tablePnl.setBackground(Color.BLUE); // Checking table Panel
		wholeData.add(tablePnl, BorderLayout.CENTER);
		table = new JTable(copyData, col);
		table.setGridColor(Color.BLACK);
		table.setRowHeight(30);
		table.setEnabled(false);
		TableColumnModel myCol = table.getColumnModel();
		myCol.getColumn(0).setPreferredWidth(200);
		myCol.getColumn(1).setPreferredWidth(50);
		myCol.getColumn(2).setPreferredWidth(140);
		jp = new JScrollPane(table);
		//jp.setBounds(740, 30, 380, 400);
		//jp.setBackground(Color.RED); //Checking JScrollPane Panel;
		wholeData.add(jp, BorderLayout.EAST);
		jp.addMouseListener(this);
		showTable();
		
		JPanel btnPnl = new JPanel();
		btnPnl.setLayout(new FlowLayout());
		//btnPnl.setLayout(null);
		//btnPnl.setBounds(740, 450, 400, 250);
		
		addMenu = new JButton("주문");
		//addMenu.setPreferredSize(new Dimension(100, 100));
		//addMenu.setBounds(60, 70, 100, 100);
		addMenu.addActionListener(this);
		btnPnl.add(addMenu);

		payMenu = new JButton("결제");
		//payMenu.setPreferredSize(new Dimension(100, 100));
		//payMenu.setBounds(220, 70, 100, 100);
		payMenu.addActionListener(this);
		btnPnl.add(payMenu);
		
		//btnPnl.setBackground(Color.GREEN); //Checking Panel;
		wholeData.add(btnPnl, BorderLayout.SOUTH);
		add(wholeData, BorderLayout.CENTER);
		wholeData.addMouseListener(this);
	}
	
	public void copyDat(int numb) {
		int a = tableSelected;
		for(int i = 0; i < menuCountPerTable[a]; i++) {
			for(int j = 0; j < 3; j++) {
				if(tData[a][i][j] != null)
					copyData[i][j] = tData[a][i][j];
			}
		}
	}
	
	void payDeductStock(int tabl) {                            //현재 선택된 테이블 번호를 받아
		int numbs;											   //테이블에 있는 메뉴들의 이름과 
		String mName; 										   //수량을 저장하고 
															   //deductStock에 넘겨주는 함수입니다. 
		for(int i = 0; i < menuCountPerTable[tabl]; i++) {
			mName = tData[tabl][i][0];
			numbs = Integer.parseInt(tData[tabl][i][1]);
			deductStock(mName, numbs);
		}
	}
	
	int calMemberSale(String grd) {
		double sum = 0;
		sum = tableMoney[tableSelected];
		
		if(grd.equals("일반")) {
			sum = (int)sum*(0.98);
		}else if(grd.equals("골드")) {
			sum = (int)sum*(0.95);
		}else if(grd.equals("플레티넘")) {
			sum = (int)sum*(0.95);
		}
		
		return (int)sum;
	}
	
	int calMileage(int t) {
		double sum = 0;
		int temp = t;
		sum = tableMoney[temp];
		sum = sum*(0.02);
		return (int)sum;
	}
	
	private void membUpgrade(int mnum) {
		int mile = Integer.parseInt(memb[mnum][3]);
		
		if(mile > 500 && mile <= 1000) {
			memb[mnum][1] = "골드";
		}else if(mile > 1000) {
			memb[mnum][1] = "플레티넘";
		}
	}
	private void setTableVal() {
		for(int i = 0; i < 8; i++) {
			tableMoney[i] = sumTableVal(i);
			tmoney[i].setText("총액: " + tableMoney[i] + "원");
		}
	}
	private int sumTableVal(int tablenum) {
		int sum = 0;
		int temp;
		for(int i = 0; i < menuCountPerTable[tablenum]; i++) {
			if(tData[tablenum][i][1] != null && tData[tablenum][i][2] != null) {
				temp = Integer.parseInt(tData[tablenum][i][1]) * Integer.parseInt(tData[tablenum][i][2]);
				sum += temp;
			}
		}
		return sum;
	}
	public void showTable() {
		try {
			if(tableSelected == -1 || menuCountPerTable[tableSelected] == 0) {
				deleteAllRows();
				table.repaint();
			}
			else {
				copyDat(tableSelected);
				table.repaint();
			}
		}
		catch(Exception e) {
			System.err.println("테이블 구성 에러");
		}
	}
	
	public static void deleteAllRows() {
		for(int i = 0; i < copyData.length; i++)
			for(int j = 0; j < 3; j++)
				copyData[i][j] = "";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tBtn = e.getActionCommand();
		if(tBtn.equals("주문") && tableSelected != -1) {
			getMenuFile();
			AddWindow ad = new AddWindow();
			addMenu.setEnabled(false);
			ad.setVisible(true);
		}
		else if(tBtn.equals("결제")) {
			System.out.println("결제버튼 눌림");
			payDeductStock(tableSelected);
			paySmallWindow smPay = new paySmallWindow();
			smPay.setVisible(true);
			showTable();
		}
		else {
			for(int i = 0; i < 8; i++) {
				if(tBtn.equals("table"+i)) {
					tables[i].setBackground(Color.CYAN);
					tableSelected = i;
					showTable();
				}
				else
					tables[i].setBackground(Color.LIGHT_GRAY);
			}
		}
		
	}
	
	public  void FileIo () throws IOException {

		FileWriter reader = new FileWriter("membership.txt"); // 텍스트 파일이 없으면 새로 생성함!

				
       for(int j=0;j<memberCount;j++)
       {
		reader.write(memb[j][0]+"\t"+memb[j][1]+"\t"+memb[j][2]+"\t"+memb[j][3]+ "\t" + memb[j][4] + "\n"); // 파일에 "입출력!"을 저장함.
		
        }
       reader.close(); // 파일을 닫음
	}
	
	private void choiceNormOrMemb() {
		totalMoney += tableMoney[tableSelected];
		tableMoney[tableSelected] = 0;
		menuCountPerTable[tableSelected] = 0;
		tData[tableSelected] = new String[35][3];
		setTableVal();
		System.out.println(totalMoney);
		deleteAllRows();
		printSales.setText(" 총 매출  :  " + totalMoney + " 원");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object temp = e.getSource();
		int i;
		if(e.getButton() == MouseEvent.BUTTON3) {
			for(i = 0; i < 8; i++) {
				if(temp == tables[i])
					break;
			}
			if(i >= 8) {
				for(int j = 0; j < 8; j++)
					tables[j].setBackground(Color.LIGHT_GRAY);
				tableSelected = -1;
				showTable();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object temp = e.getSource();
		for(int i = 0; i < 8; i++) {
			if(temp == tables[i] && tables[i].getBackground() != Color.CYAN) 
				tables[i].setBackground(Color.YELLOW);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object temp = e.getSource();
		for(int i = 0; i < 8; i++) {
			if(temp == tables[i] && tables[i].getBackground() != Color.CYAN)
				tables[i].setBackground(Color.LIGHT_GRAY);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void getMenuFile() {
		Scanner inputStream = null;
		menuCount = 0;
		try {
			inputStream = new Scanner(new FileInputStream("menu.txt")); 
		}catch(FileNotFoundException e) {
			System.out.println("File isn't Found");
			System.out.println("or Couldn't be opened.");
			System.exit(0);
		}
		while(inputStream.hasNextLine()) {
			menuName[menuCount] = inputStream.next();
			menuPrice[menuCount] = inputStream.nextInt();
			menuCount++;
			inputStream.nextLine();
		}
		
		inputStream.close();
	}
	
	private void getMemberFile() {
		Scanner inputStream = null;
		memberCount = 0;
		try {
			inputStream = new Scanner(new FileInputStream("membership.txt")); 
		}catch(FileNotFoundException e) {
			System.out.println("File isn't Found");
			System.out.println("or Couldn't be opened.");
			System.exit(0);
		}
		while(inputStream.hasNextLine()) {
			memb[memberCount][0] = Integer.toString(inputStream.nextInt());
			memb[memberCount][1] = inputStream.next();
			memb[memberCount][2] = inputStream.next();
			memb[memberCount][3] = Integer.toString(inputStream.nextInt());
			memb[memberCount][4] = inputStream.next();
			memberCount++;
			inputStream.nextLine();
		}
		
		inputStream.close();
	}
	
	private class paySmallWindow extends JFrame implements ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public paySmallWindow() {
			setSize(300, 200);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(1, 2));
			JButton normal = new JButton("Normal");
			normal.addActionListener(this);
			add(normal);
			
			JButton member = new JButton("Member");
			member.addActionListener(this);
			add(member);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String temp = e.getActionCommand();
			
			if(temp.equals("Normal")) {
				choiceNormOrMemb();
				dispose();
			}else if(temp.equals("Member")) {
				PayMemberWindow tempPayMem = new PayMemberWindow();
				tempPayMem.setVisible(true);
				this.dispose();
			}
		}
	}
	private class PayMemberWindow extends JFrame implements ActionListener{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JTextField search;
		JTextField[] resultArray = new JTextField[3];
		JPanel searchPnl;
		JPanel result;
		JButton confBtn, memPayBtn;
		JLabel sname, sgrade, addmile;
		int membTemp = -1;
		public PayMemberWindow() {
			setSize(500, 200);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			searchPnl = new JPanel();
			searchPnl.setLayout(new BorderLayout());
			JLabel ask = new JLabel("Enter Phone Number To Search");
			ask.setHorizontalAlignment(JLabel.CENTER);
			searchPnl.add(ask, BorderLayout.NORTH);
		
			search = new JTextField("Enter Phone Number");
			searchPnl.add(search, BorderLayout.CENTER);
		
			add(searchPnl, BorderLayout.NORTH);
		
			result = new JPanel();
			result.setLayout(new GridLayout(2, 3));
			sname = new JLabel("Name");
			sname.setHorizontalAlignment(JLabel.CENTER);
			result.add(sname);
		
			sgrade = new JLabel("Grade");
			sgrade.setHorizontalAlignment(JLabel.CENTER);
			result.add(sgrade);
		
			addmile = new JLabel("+ Mileage");
			addmile.setHorizontalAlignment(JLabel.CENTER);
			result.add(addmile);
		
			for(int i = 0; i < 3; i++) {
				resultArray[i] = new JTextField();
				resultArray[i].setEditable(false);
				result.add(resultArray[i]);
			}
			add(result, BorderLayout.CENTER);
			
			JPanel btnPnl = new JPanel();
			add(btnPnl, BorderLayout.SOUTH);
			btnPnl.setLayout(new FlowLayout());
			
			confBtn = new JButton("Check");
			confBtn.addActionListener(this);
			btnPnl.add(confBtn);
			
			memPayBtn = new JButton("Pay");
			memPayBtn.addActionListener(this);
			memPayBtn.setActionCommand("memPay");
			btnPnl.add(memPayBtn);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String temp = e.getActionCommand();
			if(temp.equals("Check")) {
				membTemp = -1;
				System.out.println("체크버튼 눌림");
				getMemberFile();
				for(int i = 0; i < memberCount; i++) {
					if(memb[i][4].equals(search.getText())) {
						membTemp = i;
						break;
					}
				}
				if(membTemp != -1) {
					String tempMile = Integer.toString(calMileage(tableSelected));
					resultArray[0].setText(memb[membTemp][2]);
					resultArray[1].setText(memb[membTemp][1]);
					resultArray[2].setText(tempMile);
					memb[membTemp][3] = Integer.toString(Integer.parseInt(memb[membTemp][3]) + calMileage(tableSelected));
				}
			}else if(temp.equals("memPay")) {
				if(membTemp != -1) {
					tableMoney[tableSelected] = calMemberSale(memb[membTemp][1]);
					choiceNormOrMemb();
					membUpgrade(membTemp);
					try {
						FileIo();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				}
			}else {
				System.out.println("MemberPay Error!");
				System.exit(0);
			}
		}
	}
	private class AddWindow extends JFrame implements WindowListener, MouseListener, ActionListener{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel info, addBtnPanel;
		private JScrollPane showMenuPane, showSelectedPane;
		private JTable showMenu, showSelected;
		private JTextField selectTotal;
		private int selMenuArrayCount = 0;
		private String[][] selMenues = new String[20][2]; 
		private String[] selCol = {"주문메뉴", "수량"};
		//private JTextField[] itemText = new JTextField[4];
		
		public AddWindow() {
			setSize(700, 400);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(this);
			setLayout(new BorderLayout());
			JLabel ask = new JLabel("추가할 메뉴를 고르시고 수량을 입력하십시오.");
			ask.setHorizontalAlignment(JLabel.CENTER);
			add(ask, BorderLayout.NORTH);
			DefaultTableModel modelPeaks = new DefaultTableModel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int mColIndex) {
		            return false;
				}
			};
			showMenu = new JTable(modelPeaks);
			modelPeaks.addColumn("Menu");
			modelPeaks.addColumn("Price");
			for(int i = 0; i < menuCount; i++) {
				modelPeaks.addRow(new Object[] {menuName[i], menuPrice[i]});
			}
			showMenuPane = new JScrollPane(showMenu);
			info = new JPanel();
			info.setLayout(new GridLayout(1, 2));
			info.add(showMenuPane);
			JPanel selected = new JPanel();
			selected.setLayout(new BorderLayout());
			showSelected = new JTable(selMenues, selCol) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int mColIndex) {
					if(mColIndex == 0)
						return false;
					else
						return true;
				}
			};
			showSelected.addMouseListener(this);
			showSelectedPane = new JScrollPane(showSelected);
			showMenu.addMouseListener(this);
			selected.add(showSelectedPane, BorderLayout.CENTER);
			info.add(selected);
			add(info, BorderLayout.CENTER);
			
			JPanel totAndBtn = new JPanel();
			totAndBtn.setLayout(new BorderLayout());
			//selectTotal = new JTextField("+");
			//selectTotal.setEnabled(false);
			//totAndBtn.add(selectTotal, BorderLayout.CENTER);
			addBtnPanel =  new JPanel();
			addBtnPanel.setLayout(new FlowLayout());
			
			JButton deleteBtn = new JButton("선택 삭제");
			deleteBtn.setActionCommand("서브삭제");
			deleteBtn.addActionListener(this);
			totAndBtn.add(deleteBtn, BorderLayout.CENTER);
			selected.add(totAndBtn, BorderLayout.SOUTH);
			
			JButton saveBtn = new JButton("추가");
			saveBtn.setActionCommand("추가버튼");
			saveBtn.addActionListener(this);
			addBtnPanel.add(saveBtn);
			
			add(addBtnPanel, BorderLayout.SOUTH);
			
		}
		private void showSelTable() {
			try {
				showSelected.repaint();
			}catch(Exception e){
				System.out.println("Something Wrong");
				System.exit(0);
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			
			if(cmd.equals("추가버튼")) {
				addInfo();
				showTable();
				addMenu.setEnabled(true);
				setTableVal();
				dispose();
			}
			else if(cmd.equals("서브삭제")) {
				if(selMenuArrayCount != 0) {
					deleteSelArray(showSelected.getSelectedRow());
					showSelTable();
				}
			}
			else
				System.out.println("Unexpected Error in Add Window");
			
		}
	
		private void addInfo() {
			int a = tableSelected;
			int b = selMenuArrayCount;
			if(selMenuArrayCount != 0){
				tData[a] = new String[35][3];
				for(int i = 0; i < selMenuArrayCount; i++) {
					selMenues[i][1] = (String)showSelected.getValueAt(i, 1);
				}
				for(int i = 0; i < b; i++) {
					for(int j = 0; j < 2; j++) {
						tData[a][i][j] = selMenues[i][j];
					}
					tData[a][i][2] = checkPrice(selMenues[i][0]);
				}
				menuCountPerTable[a] = selMenuArrayCount;
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			Object temp = e.getSource();
			if((JTable)temp == showMenu) {
				if(e.getClickCount() == 2 && selectCheck() && e.getButton() == MouseEvent.BUTTON1) {
					String tempSelectMenu = (String)showMenu.getValueAt(showMenu.getSelectedRow(),0);
					selMenues[selMenuArrayCount++][0] = tempSelectMenu;
					showSelTable();
				}
			}
		}
		
		public void deleteSelArray(int row) {
			int a = row;
			for(int i = a; i < selMenuArrayCount; i++) {
				selMenues[i][0] = selMenues[i+1][0];
				selMenues[i][1] = selMenues[i+1][1];
			}
			selMenues[selMenuArrayCount-1][0] = "";
			selMenues[selMenuArrayCount-1][1] = "";
			selMenuArrayCount--;
		}
		public boolean selectCheck() {
			int a = selMenuArrayCount;
			String b = (String) showMenu.getValueAt(showMenu.getSelectedRow(), 0);
			for(int i = 0; i < a; i++) {
				if(b.equals((String)selMenues[i][0]))
					return false;
			}
			return true;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void windowOpened(WindowEvent e) {}
		@Override
		public void windowClosing(WindowEvent e) {
			addMenu.setEnabled(true);
			dispose();
		}
		@Override
		public void windowClosed(WindowEvent e) {}
		@Override
		public void windowIconified(WindowEvent e) {}
		@Override
		public void windowDeiconified(WindowEvent e) {}
		@Override
		public void windowActivated(WindowEvent e) {}
		@Override
		public void windowDeactivated(WindowEvent e) {}
	}
	private String checkPrice(String nom) {
		for(int i = 0; i < menuCount; i++) {
			if(menuName[i].equals(nom)) {
				return Integer.toString(menuPrice[i]);
			}
		}
		return "0";
	}
	public boolean checkPayment() {
		for(int k=0;k<8;k++) {
			if(this.tableMoney[k]!=0) {
				return false;
			}
		}
		return true;
	}
	
	public void deductStock(String selectMenu, int num) {	
		/*
		 * 주문받은 메뉴에 맞는 재고 수량 줄이는 메소드 입니다
		 * selectMenu 인자로 메뉴 이름 문자열 전달;
		 * num 인자로 해당 메뉴 주문 수량 전달;
		 * 일단 주문하는 서브프레임에서 처리해야 할것 같은데 적당한 위치에 넣어보고 확인 부탁합니다
		 * 주문 완료 되면 해당 메뉴에 들어가는 재료 수량을 창고 테이블에서 줄어드는 것이 확인되어야 합니다
		 */
		File menuFile = new File("menu.txt");
		String[] menuComp = new String[5];
		
		Scanner inputStream = null;
		menuCount = 0;
		try {
			inputStream = new Scanner(new FileInputStream(menuFile)); 
		}catch(FileNotFoundException e) {
			System.out.println("File isn't Found");
			System.out.println("or Couldn't be opened.");
			System.exit(0);
		}
		while(inputStream.hasNextLine()) {
			String name = inputStream.next();
			if(!selectMenu.equals(name)) {
				inputStream.nextLine();
				continue;
			}
			inputStream.next();
			for(int k=0;k<5;k++) {
				String temp = inputStream.next();
				if(temp.equals("-")) continue;
				else {
					menuComp[k] = temp;
				}
			}
			break;
		}
		inputStream.close();
		
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
			
			int getRow = stockList.getRowCount();
			int reNum = 0;
			boolean moreCheck = false;
			for(int i=0;i<5; i++) {
				boolean checkStock = false;
				for(int j=0;j<getRow;j++) {
					String getStockNameFromList = (String) stockList.getValueAt(j,  0);
					if(getStockNameFromList.equals(menuComp[i])) {
						checkStock = true;
						int getStockNum = Integer.valueOf((String) stockList.getValueAt(i,  2));
						if(!moreCheck) {
							if(getStockNum - num <0) {
								stockModel.removeRow(i);
								getRow = stockList.getRowCount();
								i--;
								reNum = num - getStockNum;
								moreCheck = true;
							}else {
								stockList.setValueAt(getStockNum - num, i, 2);
								moreCheck = false;
							}
							break;
						}else {
							stockList.setValueAt(getStockNum - reNum, i, 2);
							reNum = 0;
						}
					}
				}
				if(!checkStock) {
					System.out.println(menuComp[i] + " 의 재료가 부족합니다.");
				}
			}
			
			
			exportStockData(stockList);
			br.close();
		}catch(IOException ex) {
			System.out.println("Fail to read delivery data to process delivery");
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
}
