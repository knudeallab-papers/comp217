package storeManagementProgram2;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Menu extends JFrame{
	/**
	 *메뉴 탭을 구현한 클래스입니다
	 * JTable을 이용하여 정보를 처리합니다
	 * 세 개의 subFrame을 가집니다 (주문 처리, 주문취소 처리, JTabel add 처리)	
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	protected JPanel dataPanel;		//아래의 정보패널;
	private JPanel listPanel;		//창고 재고의 리스트패널;
	private JPanel detailPanel;		//재고에 대한 세부정보패널;
	
	private DefaultTableModel model;			//메뉴 정보 테이블;
	private int selectedRow = -1;
	private JLabel getDataOfSelectedRow[] = new JLabel[8];
	private JTable menuList;
	private String header[] = {"Name", "Price", "stock1", "stock2", "stock3", "stock4", "stock5"};		//테이블 열 제목;
	private String list[][] = new String[0][7];		//테이블 내부 정보 저장 배열;
	private JScrollPane scrollPane;
	private JPanel menuListBtnPane;
	private JTextField jtfFilter = new JTextField();
	private TableRowSorter<TableModel> rowSorter;
	private int[] stockPrice = new int[5];
	
	
    
    
	public Menu(){
		
		super("Menu Demo");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(1,2));
		
		listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		//listPanel.setPreferredSize(new Dimension(600, 550));
		
		detailPanel = new JPanel();
		detailPanel.setLayout(new BorderLayout());
		//detailPanel.setPreferredSize(new Dimension(400, 550));
		
	
		dataPanel.setBorder(new LineBorder(Color.BLACK,1));
		listPanel.setBorder(new LineBorder(Color.BLACK,1));
		detailPanel.setBorder(new LineBorder(Color.BLACK,1));
		
	
		dataPanel.setBackground(Color.WHITE);
		listPanel.setBackground(Color.WHITE);
		detailPanel.setBackground(Color.WHITE);
		
		
		model = new DefaultTableModel(list, header) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};		//메뉴 리스트 저장 테이블 선언;
		menuList = new JTable(model);
		rowSorter = new TableRowSorter<>(menuList.getModel());
		menuList.setRowSorter(rowSorter);
		menuList.setRowHeight(25);
		scrollPane = new JScrollPane(menuList);
		listPanel.add(scrollPane, BorderLayout.CENTER);
		
		importMenuData();
		
		menuList.getColumnModel().getColumn(2).setMinWidth(0);		//표에서 숨길 열 지정후 너비 0으로 설정;
		menuList.getColumnModel().getColumn(2).setMaxWidth(0);
		menuList.getColumnModel().getColumn(2).setWidth(0);
		
		menuList.getColumnModel().getColumn(3).setMinWidth(0);	
		menuList.getColumnModel().getColumn(3).setMaxWidth(0);
		menuList.getColumnModel().getColumn(3).setWidth(0);
		
		menuList.getColumnModel().getColumn(4).setMinWidth(0);		
		menuList.getColumnModel().getColumn(4).setMaxWidth(0);
		menuList.getColumnModel().getColumn(4).setWidth(0);
	
		menuList.getColumnModel().getColumn(5).setMinWidth(0);
		menuList.getColumnModel().getColumn(5).setMaxWidth(0);
		menuList.getColumnModel().getColumn(5).setWidth(0);
		
		menuList.getColumnModel().getColumn(6).setMinWidth(0);
		menuList.getColumnModel().getColumn(6).setMaxWidth(0);
		menuList.getColumnModel().getColumn(6).setWidth(0);

		
		jtfFilter.getDocument().addDocumentListener(new DocumentListener(){
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();
				
				if(text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				}else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supproted yet.");
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();
				
				if(text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				}else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
				
			}
		});					//재고 리스트 검색 기능;
		
		menuList.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectedRow = menuList.getSelectedRow();
				for(int k=0; k<8; k++) {
					getDataOfSelectedRow[k].setText(" ");
				}
				if(selectedRow<0) return;
				else if(e.getClickCount() == 2) {	//2번 클릭시 반응 하도록 설정;
					for(int k=0; k<7; k++) {
						if(k<=1)
							getDataOfSelectedRow[k].setText((String) menuList.getValueAt(selectedRow, k));
						else if(menuList.getValueAt(selectedRow,  k).equals("-")) continue;
						else
							getDataOfSelectedRow[k].setText((String) menuList.getValueAt(selectedRow, k));
					}
					int temp = Integer.valueOf((String) menuList.getValueAt(selectedRow,  1));
					getDataOfSelectedRow[7].setText(String.valueOf(temp-3000));
				}
			}
		});					//재고 리스트 테이블에서 마우스클릭 리스너 구현;
		
		
		menuListBtnPane = new JPanel();					//재고 리스트 버튼패널(add, delete);
		menuListBtnPane.setPreferredSize(new Dimension(500, 100));
		menuListBtnPane.setLayout(null);
		menuListBtnPane.setBorder(new LineBorder(Color.BLACK,1));
		menuListBtnPane.setBackground(Color.GRAY);
		
		JLabel search = new JLabel("Search Type");		//재고 리스트 검색 글상자 구현;
		search.setFont(new Font("San Serif", Font.PLAIN, 15));
		menuListBtnPane.add(search);
		search.setBounds(10, 55, 100, 25);
		menuListBtnPane.add(jtfFilter);
		jtfFilter.setBounds(110, 50, 200, 40);
		
		
		JButton addStockBtn = new JButton("Add");		//stock list button - add button 
		addStockBtn.addActionListener(new ActionListener() {
	        
			SubAddFrame saf = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(saf == null) {
					saf = new SubAddFrame();
				}else {
					saf.dispose();
					saf = new SubAddFrame();
				}
				
			}
			
		});
		
		JButton reviseStockBtn = new JButton("revise");		//stock list button - revise button 
		reviseStockBtn.addActionListener(new ActionListener() {
			SubAddFrame saf = null;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(menuList.getSelectedRow() == -1) {
					return;
				}else {
					model.removeRow(menuList.getSelectedRow());
					if(saf == null) {
						saf = new SubAddFrame();
					}else {
						saf.dispose();
						saf = new SubAddFrame();
					}
				}
				
			}
			
			
		});
		menuListBtnPane.add(addStockBtn);
		addStockBtn.setBounds(320, 50, 40, 40);
		
		
		JButton delStockBtn = new JButton("Del");		//stock list button - delete button
		delStockBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(menuList.getSelectedRow() == -1) {
					return;
				}else {
					model.removeRow(menuList.getSelectedRow());
					exportMenuData();
				}
				
			}
			
		});
	
		menuListBtnPane.add(delStockBtn);
		delStockBtn.setBounds(370, 50 ,40 ,40);
		
		menuListBtnPane.add(reviseStockBtn);
		reviseStockBtn.setBounds(420, 50 ,60 ,40);
		listPanel.add(menuListBtnPane, BorderLayout.SOUTH);
		
		
		
		JPanel detailDataPane = new JPanel();			//stock detail panel - data print
		detailDataPane.setLayout(null);
		
		JLabel detailDataLabels[] = new JLabel[4];
		detailDataLabels[0] = new JLabel(" Type  : ");
		detailDataLabels[0].setFont(new Font("San Serif", Font.PLAIN, 15));
		detailDataLabels[1] = new JLabel(" Price  : ");
		detailDataLabels[1].setFont(new Font("San Serif", Font.PLAIN, 15));
		detailDataLabels[2] = new JLabel(" Ingredient Price  : ");
		detailDataLabels[2].setFont(new Font("San Serif", Font.PLAIN, 15));
		detailDataLabels[3] = new JLabel(" Ingredient  : ");
		detailDataLabels[3].setFont(new Font("San Serif", Font.PLAIN, 15));
		
		
		
		for(int k=0 ;k<8; k++) {
			getDataOfSelectedRow[k] = new JLabel(" ");
			getDataOfSelectedRow[k].setFont(new Font("San Serif", Font.PLAIN, 15));
			detailDataPane.add(getDataOfSelectedRow[k]);
		}
		
		for(int k=0;k<4;k++) {
			detailDataPane.add(detailDataLabels[k]);
		}
		detailDataLabels[0].setBounds(10, 20, 100, 20);
		getDataOfSelectedRow[0].setBounds(100, 20, 100, 20);
		detailDataLabels[1].setBounds(10, 50, 100, 20);
		getDataOfSelectedRow[1].setBounds(100, 50, 100, 20);
		detailDataLabels[2].setBounds(10, 80, 200, 20);
		getDataOfSelectedRow[7].setBounds(180, 80, 100, 20);
		detailDataLabels[3].setBounds(10, 110, 100, 20);
		getDataOfSelectedRow[2].setBounds(150, 110, 200, 20);
		getDataOfSelectedRow[3].setBounds(150, 140, 200, 20);
		getDataOfSelectedRow[4].setBounds(150, 170, 200, 20);
		getDataOfSelectedRow[5].setBounds(150, 200, 200, 20);
		getDataOfSelectedRow[6].setBounds(150, 230, 200, 20);
		
		
		
		JPanel detailBtnPane = new JPanel();				//stock detail panel  - order button
		detailBtnPane.setLayout(null);
		detailBtnPane.setPreferredSize(new Dimension(500, 100));
		detailBtnPane.setBorder(new LineBorder(Color.BLACK, 1));
		detailBtnPane.setBackground(Color.GRAY);
	
		
		detailPanel.add(detailDataPane, BorderLayout.CENTER);
		detailPanel.add(detailBtnPane, BorderLayout.SOUTH);
		
		dataPanel.add(listPanel);
		dataPanel.add(detailPanel);
		
	}
	
	public class SubAddFrame extends JFrame implements ActionListener{		//subFrame with add process

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private DefaultTableModel stockModel;			//창고 재고정보 테이블;
		private JTable stockList;
		private String stockListHeader[] = {"Type", "Stock", "Price", "Expiration Data", "Order", "where", "contact"};		//테이블 열 제목;
		private String stockArray[][] = new String[0][7];		//테이블 내부 정보 저장 배열;

		public SubAddFrame() {
			super("Input Data");

			stockModel = new DefaultTableModel(stockArray, stockListHeader);		//재고 리스트 저장 테이블 선언;
			stockList = new JTable(stockModel);
			importStockData(stockList);
			
			Container con = this.getContentPane();
			setSize(800, 150);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new BorderLayout());
			
			JPanel namePane = new JPanel();
			namePane.setLayout(new GridLayout(1, 6));
			
			//Add를 클릭하면 나오는 글자
			JLabel fieldName[] = new JLabel[6];
			fieldName[0] = new JLabel("  Name  ");
			fieldName[1] = new JLabel("  Ingredient1  ");
			fieldName[2] = new JLabel("  Ingredient2  ");
			fieldName[3] = new JLabel("  Ingredient3  ");
			fieldName[4] = new JLabel("  Ingredient4  ");
			fieldName[5] = new JLabel("  Ingredient5  ");
			
			for(int k=0;k<6;k++) {
				namePane.add(fieldName[k]);
			}
			
			//Add를 클릭하고 적는 텍스트
			JPanel inputPane = new JPanel();
			inputPane.setLayout(new GridLayout(1, 6));
			
			JTextField inputFd[] = new JTextField[6];
			
			for(int k=0;k<6;k++) {
				inputFd[k] = new JTextField("-");
				inputPane.add(inputFd[k]);//텍스트를 추가
			}
			
			
			JButton confirmBtn = new JButton("Confirm");
			confirmBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String inputStr[] = new String[7];
					
					for(int k=0; k<7;k++) {
						if(k==0) {
							inputStr[k] = inputFd[k].getText();
							inputFd[k].setText("");
						}
						else if(k==1) continue;
						else {
							inputStr[k] = inputFd[k-1].getText();
							inputFd[k-1].setText("");
						}
						
					}
					inputStr[1] = String.valueOf(calculatePrice(stockList, inputStr));
					//checkStockPrice(stockList, inputStr);

					model.addRow(inputStr);
					exportMenuData();
					dispose();
				}
				
			});
			
			con.add(namePane, BorderLayout.NORTH);
			con.add(inputPane, BorderLayout.CENTER);
			con.add(confirmBtn, BorderLayout.SOUTH);
			
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
	public int calculatePrice(JTable stockList, String[] inputData) {
		int sum = 0;
		for(int i=2;i<7;i++) {
			for(int j=0;j<stockList.getRowCount();j++) {
				if(inputData[i].equals((String) stockList.getValueAt(j, 0))) {
					int temp = Integer.valueOf((String) stockList.getValueAt(j,  1));
					sum += temp;
				}
			}
		}
		return sum+3000;				//순익 계산하는곳	원하는 순익만큼 sum에 더하면 됩니다;
	}
	
	public void checkStockPrice(JTable stockList, String[] inputData) {
		for(int i=0; i<5; i++) {
			for(int j=0 ;j<stockList.getRowCount(); j++) {
				if(inputData[i+2].equals((String) stockList.getValueAt(j, 0))) {
					stockPrice[i] = (int) stockList.getValueAt(j, 1);
				}
			}
		}
	}

	
	public void exportMenuData() {
		/*
		 * 재고 리스트를 파일로 출력하는 메서드;
		 */
		try {
			File file = new File("menu.txt");
			if(!file.exists()) {
				file.createNewFile();
				
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i<menuList.getRowCount(); i++) {
				for(int j = 0; j<menuList.getColumnCount(); j++) {
					if(menuList.getValueAt(i,  j)==null) continue;
					bw.write(menuList.getModel().getValueAt(i,  j).toString() + "\t");
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
	public void importMenuData() {
		/*
		 * 파일로부터 재고리스트를 불러와 테이블에 로드하는 메서드;
		 */
		File file = new File("menu.txt");
		if(!file.exists()) {
			return;
		}
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			DefaultTableModel tempModel = (DefaultTableModel) menuList.getModel();
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

