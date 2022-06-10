package storeManagementProgram2;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.io.*;
import java.util.Scanner;

public class Staff extends JFrame{
	/**
	 * 창고 탭을 구현한 클래스입니다
	 * JTable을 이용하여 정보를 처리합니다
	 * 세 개의 subFrame을 가집니다 (주문 처리, 주문취소 처리, JTabel add 처리)	
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	public static final int MYWIDTH = 900;
	public static final int EDIT_HEIGHT = 200;
	public static final int ADD_HEIGHT = 200;

	protected JPanel dataPanel;		//아래의 정보패널;
	private JPanel listPanel;		//창고 재고의 리스트패널;
	private JPanel detailPanel;		//재고에 대한 세부정보패널;
	
	private static int ARRAY_COUNT;
	private int selectedRow = -1;
	private JLabel getDataOfSelectedRow[] = new JLabel[6];
	private JTable stockList;
	private String header[] = {"Number", "Name", "Salary", "Position", "Phone","Day"};		//테이블 열 제목;
	private String[][] mData = new String[50][6];		//테이블 내부 정보 저장 배열;
	private JScrollPane scrollPane;
	private JPanel stockListBtnPane;
	private JTextField jtfFilter = new JTextField();
	//private JButton jbtFilter = new JButton("Filter");
	private TableRowSorter<TableModel> rowSorter;
	
	private String tempEnterDate = null;
	

	public   static String Save[][]=new String[20][6];
	
	//Menu m = new Menu();
	
		



	public Staff(String enterDate){
		
		super("Staff Demo");
		
		tempEnterDate = enterDate;
		
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
										
		stockList = new JTable(mData, header) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
	            return false;
			}
		};  //재고 리스트 저장 테이블 선언;
		rowSorter = new TableRowSorter<>(stockList.getModel());
		stockList.setRowSorter(rowSorter);
		stockList.setRowHeight(25);
		scrollPane = new JScrollPane(stockList);
		listPanel.add(scrollPane, BorderLayout.CENTER);
		
		getStaffFile();
		showTable();
		
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
				else if(e.getClickCount() == 2) {
					if(stockList.getValueAt(selectedRow, 0) != null)//1번 클릭시 반응 하도록 설정;
						for(int i = 0; i < 5; i++) 
							getDataOfSelectedRow[i].setText((String) stockList.getValueAt(selectedRow, i + 1));
				}
			}
		});					//재고 리스트 테이블에서 마우스클릭 리스너 구현;
		
		stockListBtnPane = new JPanel();					//재고 리스트 버튼패널(add, delete);
		stockListBtnPane.setPreferredSize(new Dimension(500, 100));
		stockListBtnPane.setLayout(null);
		stockListBtnPane.setBorder(new LineBorder(Color.BLACK,1));
		stockListBtnPane.setBackground(Color.GRAY);
		
		JTextArea search = new JTextArea("Search Type",1, 12);		//재고 리스트 검색 글상자 구현;
		search.setFont(new Font("San Serif", Font.PLAIN, 15));
		stockListBtnPane.add(search);
		search.setBounds(10, 55, 100, 30);
		stockListBtnPane.add(jtfFilter);
		jtfFilter.setBounds(110, 50, 200, 40);
		
		
		JButton addStockBtn = new JButton("Add");		//stock list button - add button 
		addStockBtn.addActionListener(new ActionListener() {
	        
			AddWindow saf = null;
			@Override
			public void actionPerformed(ActionEvent arg0) {
			   
				if(saf == null) {
					saf = new AddWindow();
					saf.setVisible(true);
				}else {
					saf.dispose();
					saf = new AddWindow();
					saf.setVisible(true);
				}
				
			}
			
		});
		JButton reviseStockBtn = new JButton("revise");		//stock list button - revise button 
		reviseStockBtn.addActionListener(new ActionListener() {
			EditWindow edi = null;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(stockList.getSelectedRow() == -1) {
					return;
				}else {
					if(edi == null) {
						edi = new EditWindow();
						edi.setVisible(true);
						
					}else {
						edi.dispose();
						edi = new EditWindow();
						edi.setVisible(true);
					}
				}
				
			}
			
			
		});
		stockListBtnPane.add(addStockBtn);
		addStockBtn.setBounds(320, 50, 40, 40);
		
		
		JButton delStockBtn = new JButton("Del");		//stock list button - delete button
		delStockBtn.addActionListener(new ActionListener() {
		DeleteWindow deletewin = null;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(deletewin == null) {
					deletewin = new DeleteWindow();
					deletewin.setVisible(true);
				}else {
					deletewin.dispose();
					deletewin = new DeleteWindow();
					deletewin.setVisible(true);
				}
				
			}
			
		});
	
		stockListBtnPane.add(delStockBtn);
		delStockBtn.setBounds(370, 50 ,40 ,40);
		
		stockListBtnPane.add(reviseStockBtn);
		reviseStockBtn.setBounds(420, 50 ,60 ,40);
		listPanel.add(stockListBtnPane, BorderLayout.SOUTH);
		
		
		JPanel detailDataPane = new JPanel();			//stock detail panel - data print
		detailDataPane.setLayout(new GridLayout(5, 2));
		detailDataPane.setBorder(new LineBorder(Color.BLACK,1));
		
		JLabel detailDataLabels[] = new JLabel[5];
		detailDataLabels[0] = new JLabel("    Name   : ");
		detailDataLabels[1] = new JLabel("    Salary    : ");
		detailDataLabels[2] = new JLabel("    Position    : ");
		detailDataLabels[3] = new JLabel("    Phone    : ");
		detailDataLabels[4] = new JLabel("    Day    : ");
		
		
		for(int k=0 ;k<5; k++) {
			getDataOfSelectedRow[k] = new JLabel("");
		}
		
		for(int k=0; k<5; k++) {
			detailDataPane.add(detailDataLabels[k]);
			detailDataPane.add(getDataOfSelectedRow[k]);
		}
		
		
		
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
	public class EndingListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			
		}
		
	}
	
    
	private class EditWindow extends JFrame implements ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel call, info, saveBtnPanel;
		private JTextField empNum;
		private JTextField[] itemText = new JTextField[5];
		public EditWindow() {
			setSize(MYWIDTH, EDIT_HEIGHT);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setLayout(new BorderLayout());
			JLabel ask = new JLabel("편집하십시오");
			add(ask, BorderLayout.NORTH);
			
			call = new JPanel();
			call.setLayout(new BorderLayout());
			
			empNum = new JTextField(3);
			call.add(empNum, BorderLayout.CENTER);
			
			JLabel numbAsk = new JLabel("편집하시겠습니까?");
			call.add(numbAsk, BorderLayout.NORTH);
			
			JButton callBtn = new JButton("조회");
			callBtn.addActionListener(this);
			call.add(callBtn, BorderLayout.SOUTH);
			
			add(call, BorderLayout.WEST);
			
			info = new JPanel();
			info.setLayout(new GridLayout(2, 5));
			JLabel[] item = new JLabel[5];

			for(int i = 0; i < 5; i++) {
				item[i] = new JLabel(header[i]);
				info.add(item[i]);
			}
			
			for(int i = 0; i < 5; i++) {
				itemText[i] = new JTextField(15);
				info.add(itemText[i]);
			}
			
			add(info, BorderLayout.CENTER);
				
			saveBtnPanel =  new JPanel();
			saveBtnPanel.setLayout(new FlowLayout());
			
			JButton saveBtn = new JButton("저장");
			saveBtn.addActionListener(this);
			saveBtnPanel.add(saveBtn);
			
			JButton exitBtn = new JButton("나가기");
			exitBtn.addActionListener(this);
			saveBtnPanel.add(exitBtn);
			
			add(saveBtnPanel, BorderLayout.SOUTH);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String act = e.getActionCommand();
			
			if(act.equals("조회")) {
				showInfo();
			}
			else if(act.equals("저장")) {
				saveInfo();
				showTable();
			}
			else if(act.equals("나가기")) {
				dispose();
			}
			else
				System.out.println("Unexpected Error in Edit Window");
		}
		
		private void saveInfo() {
			String numb = itemText[0].getText();

			if(IsNumber(numb) && (Integer.parseInt(numb) > 0)) {
				int a = Integer.parseInt(numb) - 1;
				if(numb.equals(empNum.getText())) {
					for(int i = 0; i < 5; i++) {
						mData[a][i] = itemText[i].getText();
					}
				}
				try {
					FileIo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		private void showInfo() {
			String numb = empNum.getText();
			int a = Integer.parseInt(numb)-1;
			if(IsNumber(numb) && (Integer.parseInt(numb) > 0) && numb.equals(mData[a][0])) {
				a = Integer.parseInt(numb)-1;
				for(int i = 0; i < 5; i++) {
					itemText[i].setText(mData[a][i]);
				}
			}
			else {
				for(int i = 0; i < 5; i++) {
					itemText[i].setText("");
				}
			}
		}
	}
	
	private class DeleteWindow extends JFrame implements ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel call, info, deleteBtnPanel;
		private JTextField empNum;
		private JTextField[] itemText = new JTextField[5];
		public DeleteWindow() {
			setSize(MYWIDTH, ADD_HEIGHT);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setLayout(new BorderLayout());
			JLabel ask = new JLabel("삭제하십시오");
			add(ask, BorderLayout.NORTH);
			
			call = new JPanel();
			call.setLayout(new BorderLayout());
			
			empNum = new JTextField(3);
			call.add(empNum, BorderLayout.CENTER);
			
			JLabel numbAsk = new JLabel("삭제하시겠습니까?");
			call.add(numbAsk, BorderLayout.NORTH);
			
			JButton callBtn = new JButton("조회");
			callBtn.addActionListener(this);
			call.add(callBtn, BorderLayout.SOUTH);
			
			add(call, BorderLayout.WEST);
			
			info = new JPanel();
			info.setLayout(new GridLayout(2, 5));
			JLabel[] item = new JLabel[5];

			for(int i = 0; i < 5; i++) {
				item[i] = new JLabel(header[i]);
				info.add(item[i]);
			}
			
			for(int i = 0; i < 5; i++) {
				itemText[i] = new JTextField(15);
				itemText[i].setEditable(false);
				info.add(itemText[i]);
			}
			
			add(info, BorderLayout.CENTER);
				
			deleteBtnPanel =  new JPanel();
			deleteBtnPanel.setLayout(new FlowLayout());
			
			JButton saveBtn = new JButton("삭제");
			saveBtn.setActionCommand("삭제버튼");
			saveBtn.addActionListener(this);
			deleteBtnPanel.add(saveBtn);
			
			JButton exitBtn = new JButton("나가기");
			exitBtn.addActionListener(this);
			deleteBtnPanel.add(exitBtn);
			
			add(deleteBtnPanel, BorderLayout.SOUTH);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			

			if(cmd.equals("조회")) {
				showInfo();
			}
			else if(cmd.equals("삭제버튼")) {
				deleteInfo();
				showTable();
			}
			else if(cmd.equals("나가기")) {
				dispose();
			}
			else
				System.out.println("Unexpected Error in Delete Window");
			
		}
		
		private void deleteInfo() {
			String numb = empNum.getText();
			int a = Integer.parseInt(numb)-1;
			if(IsNumber(numb) && (Integer.parseInt(numb) > 0)) {
				if(numb.equals(mData[a][0])) {
					for(int i = a; i < ARRAY_COUNT-1; i++) {
						for(int j = 0; j < 6; j++) {
							mData[i][j] = new String(mData[i+1][j]);
						}
						mData[i][0] = Integer.toString(i+1);
					}
					for(int i = 0; i < 6; i++)
						mData[ARRAY_COUNT-1][i] = null;  
					ARRAY_COUNT--;
					try {
						FileIo();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		private void showInfo() {
			String numb = empNum.getText();
			int a = Integer.parseInt(numb)-1;
			if(IsNumber(numb) && (Integer.parseInt(numb) > 0) && numb.equals(mData[a][0])) {
				a = Integer.parseInt(numb)-1;
				for(int i = 0; i < 5; i++) {
					itemText[i].setText(mData[a][i]);
				}
			}
			else {
				for(int i = 0; i < 5; i++) {
					itemText[i].setText("");
				}
			}
		}
	}
	
	private class AddWindow extends JFrame implements ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel info, addBtnPanel;
		private JTextField[] itemText = new JTextField[4];
		public AddWindow() {
			setSize(MYWIDTH, ADD_HEIGHT);
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setLayout(new BorderLayout());
			JLabel ask = new JLabel("추가하십시오");
			add(ask, BorderLayout.NORTH);
			
			info = new JPanel();
			info.setLayout(new GridLayout(2, 4));
			JLabel[] item = new JLabel[4];

			for(int i = 0; i < 4; i++) {
				item[i] = new JLabel(header[i+1]);
				info.add(item[i]);
			}
			
			for(int i = 0; i < 4; i++) {
				itemText[i] = new JTextField(15);
				info.add(itemText[i]);
			}
			
			add(info, BorderLayout.CENTER);
				
			addBtnPanel =  new JPanel();
			addBtnPanel.setLayout(new FlowLayout());
			
			JButton saveBtn = new JButton("추가");
			saveBtn.setActionCommand("추가버튼");
			saveBtn.addActionListener(this);
			addBtnPanel.add(saveBtn);
			
			JButton exitBtn = new JButton("나가기");
			exitBtn.addActionListener(this);
			addBtnPanel.add(exitBtn);
			
			add(addBtnPanel, BorderLayout.SOUTH);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			
			if(cmd.equals("추가버튼")) {
				addInfo();
				showTable();
			}
			else if(cmd.equals("나가기")) {
				dispose();
			}
			else
				System.out.println("Unexpected Error in Add Window");
			
		}
		
		private void addInfo() {
			int a = ARRAY_COUNT;
			mData[a][0] = Integer.toString(ARRAY_COUNT+1);
			for(int i = 0; i < 5; i++) {
				if(i==4) {
					String temp = null;
					temp = tempEnterDate;
					mData[a][i+1] = temp;
					break;
				}
				mData[a][i+1] = itemText[i].getText();
			}
			ARRAY_COUNT++;
			try {
				FileIo();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void showTable() {
		try {
			stockList.repaint();
		}
		catch(Exception e) {
			System.err.println("테이블 구성 에러");
		}
	}
	private boolean IsNumber(String input){
		try{
			Integer.parseInt(input);	
		}
		catch(NumberFormatException e){
			System.out.println(e);
			return false;			
		}		
		return true;
	}
	
	protected void getStaffFile() {
		Scanner inputStream = null;
		ARRAY_COUNT = 0;
		File file = new File("staff.txt");
		if(!file.exists()) {
			System.out.println("Cannot found staff file");
			return;
		}
		
		try {
			inputStream = new Scanner(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		while(inputStream.hasNextLine()) {
			mData[ARRAY_COUNT][0] = Integer.toString(inputStream.nextInt());
			mData[ARRAY_COUNT][1] = inputStream.next();
			mData[ARRAY_COUNT][2] = inputStream.next();
			mData[ARRAY_COUNT][3] = inputStream.next();
			mData[ARRAY_COUNT][4] = inputStream.next();
			mData[ARRAY_COUNT][5] = inputStream.nextLine();
			ARRAY_COUNT++;
			//inputStream.nextLine();
		}
		
		inputStream.close();
	}
	
	public  void FileIo () throws IOException {

		FileWriter reader = new FileWriter("staff.txt"); // 텍스트 파일이 없으면 새로 생성함!

				
       for(int j=0;j<ARRAY_COUNT;j++)
       {
		reader.write(mData[j][0]+"\t"+mData[j][1]+"\t"+mData[j][2]+"\t"+mData[j][3]+ "\t" + mData[j][4] +  "\t" + mData[j][5] + "\n"); // 파일에 "입출력!"을 저장함.
        }
       reader.close(); // 파일을 닫음
	}
}


