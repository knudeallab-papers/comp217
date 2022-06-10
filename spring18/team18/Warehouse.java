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
import javax.swing.BoxLayout;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Warehouse extends JFrame{
	/**
	 * 창고 탭을 구현한 클래스입니다
	 * JTable을 이용하여 정보를 처리합니다
	 * 세 개의 subFrame을 가집니다 (주문 처리, 주문취소 처리, JTabel add 처리)	
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	

	protected JPanel dataPanel;		//아래의 정보패널;
	private JPanel listPanel;		//창고 재고의 리스트패널;
	private JPanel detailPanel;		//재고에 대한 세부정보패널;
	
	private DefaultTableModel model;			//창고 재고정보 테이블;
	private int selectedRow = -1;
	private JLabel getDataOfSelectedRow[] = new JLabel[7];
	private JTable stockList;
	private String header[] = {"Type", "Price", "Stock", "Expiration Data", "Order", "where", "contact"};		//테이블 열 제목;
	private String list[][] = new String[0][7];		//테이블 내부 정보 저장 배열;
	private JScrollPane scrollPane;
	private JPanel stockListBtnPane;
	private JTextField jtfFilter = new JTextField();
	//private JButton jbtFilter = new JButton("Filter");
	private TableRowSorter<TableModel> rowSorter;

	
	public Warehouse(){
		
		super("warehouse Demo");
		//Container container = this.getContentPane();
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
		};		//재고 리스트 저장 테이블 선언;
		stockList = new JTable(model);
		rowSorter = new TableRowSorter<>(stockList.getModel());
		stockList.setRowSorter(rowSorter);
		stockList.setRowHeight(25);
		scrollPane = new JScrollPane(stockList);
		listPanel.add(scrollPane, BorderLayout.CENTER);
		
		stockList.getColumnModel().getColumn(5).setMinWidth(0);		//표에서 숨길 열 지정후 너비 0으로 설정;
		stockList.getColumnModel().getColumn(5).setMaxWidth(0);
		stockList.getColumnModel().getColumn(5).setWidth(0);
		
		stockList.getColumnModel().getColumn(6).setMinWidth(0);
		stockList.getColumnModel().getColumn(6).setMaxWidth(0);
		stockList.getColumnModel().getColumn(6).setWidth(0);

		
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
		
		stockList.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectedRow = stockList.getSelectedRow();
				if(selectedRow<0) return;
				else if(e.getClickCount() == 2) {	//2번 클릭시 반응 하도록 설정;
					for(int k=0; k<7; k++) {
						getDataOfSelectedRow[k].setText((String) stockList.getValueAt(selectedRow, k));
					}
				}
			}
		});					//재고 리스트 테이블에서 마우스클릭 리스너 구현;
		
		importStockData();		//파일에서 저장되어있는 정보를 불러오는 메소드 호출;
		
		
		
		stockListBtnPane = new JPanel();					//재고 리스트 버튼패널(add, delete);
		stockListBtnPane.setPreferredSize(new Dimension(500, 100));
		stockListBtnPane.setLayout(null);
		stockListBtnPane.setBorder(new LineBorder(Color.BLACK,1));
		stockListBtnPane.setBackground(Color.GRAY);
		
		JLabel search = new JLabel("Search Type");		//재고 리스트 검색 글상자 구현;
		search.setFont(new Font("San Serif", Font.PLAIN, 15));
		stockListBtnPane.add(search);
		search.setBounds(10, 55, 100, 30);
		stockListBtnPane.add(jtfFilter);
		jtfFilter.setBounds(110, 50, 200, 40);
		
		
		
		JButton addStockBtn = new JButton("Add");		//stock list button - add button 
		addStockBtn.addActionListener(new ActionListener() {
			SubAddFrame saf = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(saf == null) {
					try {
						saf = new SubAddFrame();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					saf.dispose();
					try {
						saf = new SubAddFrame();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		stockListBtnPane.add(addStockBtn);
		addStockBtn.setBounds(340, 50, 60, 40);
		
		
		JButton delStockBtn = new JButton("Del");		//stock list button - delete button
		delStockBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(stockList.getSelectedRow() == -1) {
					return;
				}else {
					model.removeRow(stockList.getSelectedRow());
					exportStockData();
				}
				
			}
			
		});
	
		stockListBtnPane.add(delStockBtn);
		delStockBtn.setBounds(410, 50 ,60 ,40);
		
		listPanel.add(stockListBtnPane, BorderLayout.SOUTH);
		
		
		
		JPanel detailDataPane = new JPanel();			//stock detail panel - data print
		detailDataPane.setLayout(new GridLayout(8, 2));
		detailDataPane.setBorder(new LineBorder(Color.BLACK,1));
		
		JLabel detailDataLabels[] = new JLabel[7];
		detailDataLabels[0] = new JLabel("    Type    :   ");
		detailDataLabels[1] = new JLabel("    Price    :   ");
		detailDataLabels[2] = new JLabel("    Quantity    :   ");
		detailDataLabels[3] = new JLabel("    Expiration date    :   ");
		detailDataLabels[4] = new JLabel("    Order quantity    :   ");
		detailDataLabels[5] = new JLabel("    Where to buy    :   ");
		detailDataLabels[6] = new JLabel("    Contact number    :   ");
		
		for(int k=0 ;k<7; k++) {
			getDataOfSelectedRow[k] = new JLabel("");
		}
		
		for(int k=0; k<7; k++) {
			detailDataPane.add(detailDataLabels[k]);
			detailDataPane.add(getDataOfSelectedRow[k]);
		}
		
		
		
		JPanel detailBtnPane = new JPanel();				//stock detail panel  - order button
		detailBtnPane.setLayout(null);
		detailBtnPane.setPreferredSize(new Dimension(500, 100));
		detailBtnPane.setBorder(new LineBorder(Color.BLACK, 1));
		detailBtnPane.setBackground(Color.GRAY);
		
		JButton orderBtn = new JButton("Order");
		orderBtn.addActionListener(new ActionListener() {
			SubOrderFrame sof = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sof == null){
					sof = new SubOrderFrame(selectedRow);
				}else {
			
					sof.dispose();
					sof = new SubOrderFrame(selectedRow);
				}
				
			}
			
		});
		
		JButton orderCancelBtn = new JButton("Order Cancel");
		orderCancelBtn.addActionListener(new ActionListener() {
			SubOrderCancelFrame socf = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(socf == null) {
					socf = new SubOrderCancelFrame(selectedRow);
				}else {
					socf.dispose();
					socf = new SubOrderCancelFrame(selectedRow);
				}
				
			}
			
		});
		
		detailBtnPane.add(orderBtn);
		orderBtn.setBounds(280, 50, 60, 40);
		detailBtnPane.add(orderCancelBtn);
		orderCancelBtn.setBounds(350, 50 ,120 ,40);
		
		detailPanel.add(detailDataPane, BorderLayout.CENTER);
		detailPanel.add(detailBtnPane, BorderLayout.SOUTH);
		
		dataPanel.add(listPanel);
		dataPanel.add(detailPanel);
		
	}
	public class EndingListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			
		}
		
	}
	
	public class SubAddFrame extends JFrame implements ActionListener{		//subFrame with add process

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SubAddFrame() throws IOException {
			super("Input Data");
			Container con = this.getContentPane();
			setSize(800, 150);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new BorderLayout());
			
			JPanel namePane = new JPanel();
			namePane.setLayout(new GridLayout(1, 6));
			
			JLabel fieldName[] = new JLabel[6];
			fieldName[0] = new JLabel("  Type  ");
			fieldName[1] = new JLabel("  Price  ");
			fieldName[2] = new JLabel("  Quantity  ");
			fieldName[3] = new JLabel("  Exp Date  ");
			fieldName[4] = new JLabel("  Where to buy  ");
			fieldName[5] = new JLabel("  Contact Num  ");
			
			for(int k=0;k<6;k++) {
				namePane.add(fieldName[k]);
			}
			
			
			JPanel inputPane = new JPanel();
			inputPane.setLayout(new GridLayout(1, 6));
			
			JTextField inputFd[] = new JTextField[6];
			for(int k=0;k<6;k++) {
				inputFd[k] = new JTextField("");
				inputPane.add(inputFd[k]);
			}
			
			
			JButton confirmBtn = new JButton("Confirm");
			confirmBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String inputStr[] = new String[7];
					
					for(int k=0; k<7;k++) {
						if(k==4) {
							inputStr[k] = "0";
							continue;
						}else if(k>4) {
							inputStr[k] = inputFd[k-1].getText();
							inputFd[k-1].setText("");
							continue;
						}
						inputStr[k] = inputFd[k].getText();
						inputFd[k].setText("");
						
					}
					model.addRow(inputStr);
					exportStockData();
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
	
	public class SubOrderFrame extends JFrame implements ActionListener{			//subFrame with order process

		/**
		 * 주문 수량을 입력받는 서브프레임;
		 */
		private static final long serialVersionUID = 1L;
		
		public SubOrderFrame(int selected) {
			super("Order Input");
			Container con = this.getContentPane();
			setSize(300, 100);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new BorderLayout());
			
			JPanel inputPane = new JPanel();
			inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.X_AXIS));
			
			JLabel orderQue = new JLabel("    How Many?    ");
			JTextField inputNum = new JTextField();
			
			inputPane.add(orderQue);
			inputPane.add(inputNum);
			
			
			JButton confirmBtn = new JButton("Confirm");
			confirmBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int input = 0;
					input = Integer.valueOf(inputNum.getText());
					stockList.setValueAt(input, selected, 4);
					getDataOfSelectedRow[4].setText(String.valueOf(input));
					inputNum.setText("");
					
					exportStockData();
					
					dispose();
				}
				
			});
			
			con.add(inputPane, BorderLayout.CENTER);
			con.add(confirmBtn, BorderLayout.SOUTH);
			
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	
	public class SubOrderCancelFrame extends JFrame implements ActionListener{

		/**
		 * 주문취소량을 입력받는 서브프레임;
		 */
		private static final long serialVersionUID = 1L;
		
		public SubOrderCancelFrame(int selected) {
			
			super("Order Cancel");
			Container con = this.getContentPane();
			setSize(300, 100);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new BorderLayout());
			
			JPanel inputPane = new JPanel();
			inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.X_AXIS));
			
			JLabel orderQue = new JLabel("  How Many Cancel?  ");
			JTextField inputNum = new JTextField();
			
			inputPane.add(orderQue);
			inputPane.add(inputNum);
			
			JButton confirmBtn = new JButton("Confirm");
			confirmBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int input = 0;
					input =Integer.valueOf(inputNum.getText());			//기존의 주문 수량에서 입력된 수량 만큼 뻄;
					int temp = (int) stockList.getModel().getValueAt(selected, 4);
					input = Integer.valueOf(temp) - input;
					
					stockList.setValueAt(input, selected, 4);
					getDataOfSelectedRow[4].setText(String.valueOf(input));
					inputNum.setText("");
					
					exportStockData();
					dispose();
				}
				
			});
			
			con.add(inputPane, BorderLayout.CENTER);
			con.add(confirmBtn, BorderLayout.SOUTH);
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	
	public void exportStockData() {
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
					bw.write(stockList.getModel().getValueAt(i,  j).toString() + "\t");
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
	
	public void importStockData() {
		/*
		 * 파일로부터 재고리스트를 불러와 테이블에 로드하는 메서드;
		 */
		File file = new File("stock.txt");
		if(!file.exists()) {
			System.out.println("Cannot found stock file");
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
	
	public void resetTable() {
		DefaultTableModel dm = (DefaultTableModel) stockList.getModel();
		int rowCount = dm.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
	
	public void refreshAll() {
		resetTable();
		importStockData();
	}
}
