
// GUI import
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Calendar;




import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// project import


public class MainPage extends JFrame
{
	private JPanel mainPagePane, itemPagePane, orderPagePane, salesListPagePane;
	
	private JTextField categoryNameTextField;
	private JTextField itemNameTextField;
	private JTextField itemPriceTextField;
	private JTextField itemCostTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton itemSoldOutRdbtnO, itemSoldOutRdbtnX;
	
	private JList categoryListJList, itemListJList;
	private JComboBox categoryOrderComboBox, itemCategoryComboBox, itemOrderComboBox;
	int selectedCategoryIndex, selectedItemIndex;
	
	private itemList itemList;
	private totalSalesList totalSalesList;
	private order order;
	private ObjectOutputStream output, output2;
	private ObjectInputStream input, input2;

	// 주문계산부분
	private JList orderList;
	private timeRunable timeChange;
	private JLabel totalMoneyLabel;
	public JTable itemListTable;
	private DefaultTableModel DefaultTableModelOrderList;
	private DefaultTableCellRenderer celAlignCenter;
	private DefaultTableCellRenderer celAlignRight;
	private Table t = new Table();

	
	// 계산결제
	private JFrame cashFrame;
	private int paymentSum;
	private JLabel paymentSumLabel, changeSumLabel;
	private int sum;
	
	// 오늘 주문목록
	private JFrame todayFrame;
	private JTable todaySalesListTable;
	private DefaultTableModel tables;
	
	// 통계부분
	private JTable salesListTable;
	private final ButtonGroup salesListButtonGroup = new ButtonGroup();
	private DefaultTableModel DefaultTableModelSalesList;
	
	private int totalMoney = 0;
	private int totalCost = 0;
	private int cashMoney = 0;
	
	private JComboBox monthComboBox, dayComboBox;
	private JRadioButton monthRadioBtn, dayRadioBtn;
	
	private JLabel totalMoneyLabel2, totalCostMoneyLabel, totalProfitMoneyLabel, cashMoneyLabel;
	
	Calendar today;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	
	public MainPage()
	{
		// GUI부분
				setResizable(false);
				
				// 메인페이지 생성
				
				
				JPanel down = new JPanel();
				
				mainPagePane = new JPanel();
				
				JLabel totalMoney = new JLabel();
								
				mainPagePane.add(totalMoney);
				
				mainPagePane.add(down);
				down.setLayout( new GridLayout(1,4) );
				this.setVisible(true);
				
//				  JTabbedPane tPane = new JTabbedPane();
//				  
//				  tPane.addTab("테이블", itemManagementGUI());
//				  tPane.addTab("메인메뉴",mainPagePane);
//
//				  tPane.setBounds(0, 80, 1000, 700);
				  
//				this.add(tPane);				
				  member m = new member();
				  Employee2 e2 = new Employee2();

				add(mainPagePane);
				JButton orderBtn = new JButton("영업");
				orderBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						orderManagementGUI();
					}
				});
				down.add(orderBtn);
				
				JButton memberBtn = new JButton("손님");
				memberBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
//						JPanel memberPane = new JPanel();
//						memberPane.add(m.getContentPane());
//						memberPane.setVisible(true);
						m();
						
					}
				});
				down.add(memberBtn);
				
				JButton employeeBtn = new JButton("직원");
				employeeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
//						JPanel employeePane = new JPanel();
//						employeePane.add(e2.getContentPane());
//						employeePane.setVisible(true);
						e();
					}
				});
				down.add(employeeBtn);
				
				JButton itemMBtn = new JButton("물품관리");
				itemMBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						itemManagementGUI();
					}
				});
				down.add(itemMBtn);
				
				JButton salesListMBtn = new JButton("매출현황");
				salesListMBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						salesListGUI();
					}
				});
				down.add(salesListMBtn);
				
				JButton exitBtn = new JButton("저장 & 종료");
				exitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						fileSave();
						System.exit(0);
					}
				});
				down.add(exitBtn);
				
				
				mainPageGUI();
				
				// 내용 부분
				itemList = new itemList();
				totalSalesList = new totalSalesList();
				fileLoad();
				
				celAlignCenter = new DefaultTableCellRenderer();
				celAlignRight = new DefaultTableCellRenderer();
				celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
				celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
	}
	
	public JTable getItemListTable()
	{
		return itemListTable;
	}
	
	public void m()
	{
		member m = new member();
	}

	public void e()
	{
		Employee2 e2 = new Employee2();
	}
	
	public void fileSave()
	{
		try
		{
			output = new ObjectOutputStream( new FileOutputStream("obj.sav") );
			output.writeObject(itemList);
			output2 = new ObjectOutputStream( new FileOutputStream("obj2.sav") );
			output2.writeObject(totalSalesList);
		}
		catch( IOException exception )
		{
			JOptionPane.showMessageDialog(this, "파일읽기 혹은 쓰기 에러발생", "error", JOptionPane.WARNING_MESSAGE);
		}
		
		try
		{
			if( output != null )
				output.close();
			if( output2 != null )
				output2.close();
		}
		catch( IOException exception )
		{
			JOptionPane.showMessageDialog(this, "파일닫기 에러발생", "error", JOptionPane.WARNING_MESSAGE);
			System.exit(1);
		}
	}
	
	public void fileLoad()
	{
		if( (new File("obj.sav")).isFile() )
		{
			try
			{
				input = new ObjectInputStream( new FileInputStream("obj.sav") );
				itemList = (itemList)input.readObject();
			}
			catch( ClassNotFoundException exception )
			{
				JOptionPane.showMessageDialog(this, "클래스 생성 불가", "error", JOptionPane.WARNING_MESSAGE);
			}
			catch( IOException exception )
			{
				JOptionPane.showMessageDialog(this, "파일읽기 혹은 쓰기 에러발생", "error", JOptionPane.WARNING_MESSAGE);
			}
			
			try
			{
				if( input != null )
					input.close();
			}
			catch( IOException exception )
			{
				JOptionPane.showMessageDialog(this, "파일닫기 에러발생", "error", JOptionPane.WARNING_MESSAGE);
				System.exit(1);
			}
		}
		if( (new File("obj2.sav")).isFile() )
		{
			try
			{
				input2 = new ObjectInputStream( new FileInputStream("obj2.sav") );
				totalSalesList = (totalSalesList)input2.readObject();
			}
			catch( ClassNotFoundException exception )
			{
				JOptionPane.showMessageDialog(this, "클래스 생성 불가", "error", JOptionPane.WARNING_MESSAGE);
			}
			catch( IOException exception )
			{
				JOptionPane.showMessageDialog(this, "파일읽기 혹은 쓰기 에러발생", "error", JOptionPane.WARNING_MESSAGE);
			}
			
			try
			{
				if( input2 != null )
					input2.close();
			}
			catch( IOException exception )
			{
				JOptionPane.showMessageDialog(this, "파일닫기 에러발생", "error", JOptionPane.WARNING_MESSAGE);
				System.exit(1);
			}
		}
	}
	
	public void mainPageGUI()
	{
		setBounds(100, 100, 1000, 300);
		setTitle("메인메뉴");
		mainPagePane.setVisible(true);
	}
	
	// 품목관리 부분
	public JPanel itemManagementGUI()
	{
		mainPagePane.setVisible(false);
		setBounds(100, 100, 1000, 800);
		setTitle("품목관리");
		
		itemPagePane = new JPanel();
		itemPagePane.setLayout(null);
		add(itemPagePane);
		

		// 왼쪽
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(12, 10, 456, 461);
		leftPanel.setLayout(null);
		itemPagePane.add(leftPanel);
		
		JLabel mainLabel = new JLabel("품목관리");
		mainLabel.setBounds(12, 2, 142, 35);
		leftPanel.add(mainLabel);
		
		JLabel lblNewLabel = new JLabel("분류 목록");
		lblNewLabel.setBounds(10, 47, 88, 22);
		leftPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("품목 목록");
		label.setBounds(232, 47, 88, 22);
		leftPanel.add(label);
		
		categoryListJList = new JList();
		categoryListJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		categoryListJList.setListData( itemList.getCategories().toArray() );
		categoryListJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged( ListSelectionEvent event ) {
				selectCategory();
			}
		});
		categoryListJList.setVisibleRowCount(100);
		
		JScrollPane categoryScroll = new JScrollPane(categoryListJList);
		categoryScroll.setBounds(10, 71, 210, 380);
		leftPanel.add(categoryScroll);
		
		itemListJList = new JList();
		itemListJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemListJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged( ListSelectionEvent event ) {
				selectItem();
			}
		});
		itemListJList.setVisibleRowCount(100);
		
		JScrollPane itemScroll = new JScrollPane(itemListJList);
		itemScroll.setBounds(232, 71, 214, 380);
		leftPanel.add(itemScroll);
		
		
		// 오른쪽
		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(480, 10, 302, 461);
		rightPanel.setLayout(null);
		itemPagePane.add(rightPanel);
		
		JButton btnNewButton = new JButton("메인메뉴");
		btnNewButton.setBounds(198, 0, 104, 31);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				remove( itemPagePane );
				mainPageGUI();
			}
		});
		rightPanel.add(btnNewButton);
		
		JPanel ManagePanel = new JPanel();
		ManagePanel.setBorder(new LineBorder( new Color(51, 153, 255) ));
		ManagePanel.setBackground(Color.WHITE);
		ManagePanel.setBounds(2, 47, 300, 404);
		ManagePanel.setLayout(null);
		rightPanel.add(ManagePanel);
		
		// 카테고리 추가부분
		JPanel CategoryMPanel = new JPanel();
		CategoryMPanel.setBackground(Color.WHITE);
		CategoryMPanel.setBounds(12, 10, 278, 126);
		ManagePanel.add(CategoryMPanel);
		CategoryMPanel.setLayout(null);
		
		JLabel CategoryMMainLabel = new JLabel("분류 추가 / 수정 / 삭제");
		CategoryMMainLabel.setBounds(0, 1, 276, 27);
		CategoryMPanel.add(CategoryMMainLabel);
		
		JLabel categoryNameLabel = new JLabel("분류명");
		categoryNameLabel.setBounds(10, 38, 48, 20);
		CategoryMPanel.add(categoryNameLabel);
		
		categoryNameTextField = new JTextField();
		categoryNameTextField.setBounds(83, 38, 116, 20);
		categoryNameTextField.addKeyListener(new KeyAdapter() { // 엔터치면 분류추가하도록
			public void keyTyped(KeyEvent event) {
				if(event.getKeyChar() == KeyEvent.VK_ENTER){
					addCategory();
				}
			} 
		});
		CategoryMPanel.add(categoryNameTextField);
		categoryNameTextField.setColumns(10);
		
		JLabel categoryOrderLabel = new JLabel("분류순서");
		categoryOrderLabel.setBounds(10, 68, 68, 20);
		CategoryMPanel.add(categoryOrderLabel);
		
		categoryOrderComboBox = new JComboBox();
		categoryOrderComboBox.setBackground(Color.WHITE);
		categoryOrderComboBox.setBounds(83, 68, 68, 20);
		for( int i = 0; i <= itemList.getCategories().size(); i++ )
		{
			categoryOrderComboBox.addItem(i+1);
		}
		CategoryMPanel.add(categoryOrderComboBox);
		
		JButton categoryAddBtn = new JButton("추가");
		categoryAddBtn.setBounds(10, 98, 60, 23);
		categoryAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addCategory();
			}
		});;
		CategoryMPanel.add(categoryAddBtn);
		
		JButton categoryModifyBtn = new JButton("수정");
		categoryModifyBtn.setBounds(78, 98, 60, 23);
		categoryModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				modifyCategory();
			}
		});
		CategoryMPanel.add(categoryModifyBtn);
		
		JButton categoryDelBtn = new JButton("삭제");
		categoryDelBtn.setBounds(146, 98, 60, 23);
		categoryDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				delCategory();
			}
		});
		CategoryMPanel.add(categoryDelBtn);
		
		JButton categoryResetBtn = new JButton("리셋");
		categoryResetBtn.setBounds(214, 98, 60, 23);
		categoryResetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				categoryNameTextField.setText("");
				categoryOrderComboBox.setSelectedIndex(categoryOrderComboBox.getItemCount()-1);
				selectedCategoryIndex = -1;
			}
		});
		CategoryMPanel.add(categoryResetBtn);
		
		
		// 품목추가 부분
		JPanel itemMPanel = new JPanel();
		itemMPanel.setBackground(Color.WHITE);
		itemMPanel.setLayout(null);
		itemMPanel.setBounds(12, 174, 278, 227);
		ManagePanel.add(itemMPanel);
		
		JLabel itemMMainLabel = new JLabel("품목 추가 / 수정 / 삭제");
		itemMMainLabel.setBounds(0, 0, 278, 27);
		itemMPanel.add(itemMMainLabel);
		
		JLabel itemNameLabel = new JLabel("품목명");
		itemNameLabel.setBounds(10, 37, 48, 20);
		itemMPanel.add(itemNameLabel);
		
		itemNameTextField = new JTextField();
		itemNameTextField.setColumns(10);
		itemNameTextField.setBounds(83, 37, 116, 20);
		itemNameTextField.addKeyListener(new KeyAdapter() { // 엔터치면 품목추가하도록
			public void keyTyped(KeyEvent event) {
				if(event.getKeyChar() == KeyEvent.VK_ENTER){
					addItem();
				}
			} 
		});
		itemMPanel.add(itemNameTextField);
		
		JLabel itemPriceLabel = new JLabel("판매가격");
		itemPriceLabel.setBounds(10, 62, 68, 20);
		itemMPanel.add(itemPriceLabel);
		
		itemPriceTextField = new JTextField();
		itemPriceTextField.setColumns(10);
		itemPriceTextField.setBounds(83, 62, 116, 20);
		itemPriceTextField.addKeyListener(new KeyAdapter() { // 엔터치면 품목추가하도록
			public void keyTyped(KeyEvent event) {
				if(event.getKeyChar() == KeyEvent.VK_ENTER){
					addItem();
				}
				if( event.getKeyChar() < '0' || event.getKeyChar() > '9' )
					event.consume();
			}
		});
		itemMPanel.add(itemPriceTextField);
		
		JLabel itemCostLabel = new JLabel("원가");
		itemCostLabel.setBounds(10, 88, 48, 20);
		itemMPanel.add(itemCostLabel);
		
		itemCostTextField = new JTextField();
		itemCostTextField.setColumns(10);
		itemCostTextField.setBounds(83, 88, 116, 20);
		itemCostTextField.addKeyListener(new KeyAdapter() { // 엔터치면 품목추가하도록
			public void keyTyped(KeyEvent event) {
				if(event.getKeyChar() == KeyEvent.VK_ENTER){
					addItem();
				}
				if( event.getKeyChar() < '0' || event.getKeyChar() > '9' )
					event.consume();
			} 
		});
		itemMPanel.add(itemCostTextField);
		
		JLabel itemSoldOutLabel = new JLabel("품절유무");
		itemSoldOutLabel.setBounds(10, 118, 68, 20);
		itemMPanel.add(itemSoldOutLabel);
		
		itemSoldOutRdbtnO = new JRadioButton("O");
		itemSoldOutRdbtnO.setBackground(Color.WHITE);
		buttonGroup.add(itemSoldOutRdbtnO);
		itemSoldOutRdbtnO.setBounds(83, 119, 48, 23);
		itemMPanel.add(itemSoldOutRdbtnO);
		
		itemSoldOutRdbtnX = new JRadioButton("X");
		itemSoldOutRdbtnX.setBackground(Color.WHITE);
		buttonGroup.add(itemSoldOutRdbtnX);
		itemSoldOutRdbtnX.setBounds(151, 119, 48, 23);
		itemSoldOutRdbtnX.setSelected(true);
		itemMPanel.add(itemSoldOutRdbtnX);
		
		JLabel itemCategoryLabel = new JLabel("분류");
		itemCategoryLabel.setBounds(10, 148, 57, 20);
		itemMPanel.add(itemCategoryLabel);
		
		itemCategoryComboBox = new JComboBox();
		itemCategoryComboBox.setBackground(Color.WHITE);
		itemCategoryComboBox.setBounds(83, 148, 115, 21);
		itemCategoryComboBox.addItemListener(new ItemListener(){
			public void itemStateChanged( ItemEvent event )
			{
				if( event.getStateChange() == ItemEvent.SELECTED )
				{
					itemOrderComboBox.removeAllItems();
					for( int i = 0; i <= itemList.getItemM( itemCategoryComboBox.getSelectedIndex() ).getSize(); i++ )
						itemOrderComboBox.addItem( i+1 );
					itemOrderComboBox.setSelectedIndex(itemOrderComboBox.getItemCount()-1);
				}
			}
		});
		itemMPanel.add(itemCategoryComboBox);
		
		JLabel itemOrderLabel = new JLabel("품목순서");
		itemOrderLabel.setBounds(10, 175, 68, 20);
		itemMPanel.add(itemOrderLabel);
		
		itemOrderComboBox = new JComboBox();
		itemOrderComboBox.setBackground(Color.WHITE);
		itemOrderComboBox.setBounds(83, 175, 68, 20);
		itemMPanel.add(itemOrderComboBox);
		
		// 품목추가 부분 버튼
		JButton itemAddBtn = new JButton("추가");
		itemAddBtn.setBounds(10, 204, 60, 23);
		itemAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addItem();
			}
		});
		itemMPanel.add(itemAddBtn);
		
		JButton itemModifyBtn = new JButton("수정");
		itemModifyBtn.setBounds(78, 204, 60, 23);
		itemModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				modifyItem();
			}
		});
		itemMPanel.add(itemModifyBtn);
		
		JButton itemDelBtn = new JButton("삭제");
		itemDelBtn.setBounds(146, 204, 60, 23);
		itemDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				delItem();
			}
		});
		itemMPanel.add(itemDelBtn);
		
		JButton itemResetBtn = new JButton("리셋");
		itemResetBtn.setBounds(214, 204, 60, 23);
		itemResetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				itemNameTextField.setText("");
				itemPriceTextField.setText("");
				itemCostTextField.setText("");
				itemSoldOutRdbtnX.setSelected(true);
				itemOrderComboBox.setSelectedIndex(itemOrderComboBox.getItemCount()-1);
				
				selectedItemIndex = -1;
			}
		});
		itemMPanel.add(itemResetBtn);
		return itemMPanel;
		
		
	}
	
	public void selectCategory()
	{
		if( categoryListJList.getSelectedValue() != null )
		{
			String selectedString = categoryListJList.getSelectedValue().toString();
			
			// 분류추가 부분
			selectedCategoryIndex = itemList.getCategoryOrder( selectedString );						
			categoryNameTextField.setText( selectedString );
			categoryOrderComboBox.setSelectedIndex( selectedCategoryIndex );
			
			// 품목목록
			itemListJList.setListData( itemList.getItemM( selectedCategoryIndex ).getItemsNames() );
			// 품목추가 부분
			itemCategoryComboBox.removeAllItems();
			
			for( String tempString : itemList.getCategories() )
				itemCategoryComboBox.addItem( tempString );
			
			itemCategoryComboBox.setSelectedIndex( selectedCategoryIndex );
			
			itemOrderComboBox.removeAllItems();
			
			for( int i = 0; i <= itemList.getItemM(selectedCategoryIndex).getSize(); i++ )
				itemOrderComboBox.addItem( i+1 );
			
			itemOrderComboBox.setSelectedIndex(itemOrderComboBox.getItemCount()-1);
			
			itemNameTextField.setText( "" );
			itemPriceTextField.setText( "" );
			itemCostTextField.setText( "" );
			itemSoldOutRdbtnX.setSelected(true);
		}
	}
	
	public void selectItem()
	{
		if( itemListJList.getSelectedValue() != null )
		{
			String selectedString = itemListJList.getSelectedValue().toString();
			
			selectedItemIndex = itemList.getItemM(selectedCategoryIndex).getItemsOrder(selectedString);
			item tempItem = itemList.getItemM(selectedCategoryIndex).getItem(selectedItemIndex);
			itemNameTextField.setText( tempItem.getItemName() );
			itemPriceTextField.setText( String.valueOf(tempItem.getPrice()) );
			itemCostTextField.setText( String.valueOf(tempItem.getCost()) );
			itemCategoryComboBox.setSelectedIndex( selectedCategoryIndex );
			if( tempItem.getSoldOut() )
				itemSoldOutRdbtnO.setSelected(true);
			else
				itemSoldOutRdbtnX.setSelected(true);
			itemOrderComboBox.setSelectedIndex(selectedItemIndex);
		}
	}

	public void addCategory()
	{
		if( categoryNameTextField.getText().trim().isEmpty() )
			JOptionPane.showMessageDialog(itemPagePane, "분류명을 입력하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemList.getCategories().contains(categoryNameTextField.getText().trim()) )
			JOptionPane.showMessageDialog(itemPagePane, "분류명은 중복될 수 없습니다.", "error", JOptionPane.WARNING_MESSAGE);
		else
		{
			itemList.addCategory(categoryNameTextField.getText().trim());
			categoryNameTextField.setText("");
			
			if( categoryOrderComboBox.getSelectedIndex() != itemList.getCategories().size()-1 )
				itemList.setCategoryOrder(itemList.getCategories().size()-1, categoryOrderComboBox.getSelectedIndex());
			
			categoryListJList.setListData( itemList.getCategories().toArray() );
			
			
			// 품목추가의 분류선택
			itemCategoryComboBox.removeAllItems();
			for( String tempString : itemList.getCategories() )
				itemCategoryComboBox.addItem( tempString );
			itemCategoryComboBox.setSelectedIndex( categoryOrderComboBox.getSelectedIndex() );
			itemOrderComboBox.removeAllItems();
			for( int i = 0; i <= itemList.getItemM(itemList.getCategories().size()-1).getSize(); i++ )
				itemOrderComboBox.addItem( i+1 );
			itemOrderComboBox.setSelectedIndex(itemOrderComboBox.getItemCount()-1);
		
			// 분류추가의 분류순서 초기화
			categoryOrderComboBox.addItem(itemList.getCategories().size()+1);
			categoryOrderComboBox.setSelectedIndex(categoryOrderComboBox.getItemCount()-1);
			selectedCategoryIndex = -1;
		}
	}
	
	public void modifyCategory()
	{
		if( categoryNameTextField.getText().trim().isEmpty() )
			JOptionPane.showMessageDialog(itemPagePane, "분류명을 입력하세요", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemList.getCategories().contains(categoryNameTextField.getText().trim()) &&  itemList.getCategories().indexOf(categoryNameTextField.getText().trim())!= selectedCategoryIndex )
			JOptionPane.showMessageDialog(itemPagePane, "분류명은 중복될 수 없습니다.", "error", JOptionPane.WARNING_MESSAGE);
		else if( categoryOrderComboBox.getSelectedIndex() >= itemList.getCategories().size() )
			JOptionPane.showMessageDialog(itemPagePane, "올바른 분류순서를 선택하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( selectedCategoryIndex < 0 )
			JOptionPane.showMessageDialog(itemPagePane, "수정할 분류를 다시 선택하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else
		{
			itemList.modifyCategory(selectedCategoryIndex, categoryNameTextField.getText().trim());
			itemList.setCategoryOrder(selectedCategoryIndex, categoryOrderComboBox.getSelectedIndex());
			categoryListJList.setListData( itemList.getCategories().toArray() );
			
			// 품목추가의 분류선택
			itemCategoryComboBox.removeAllItems();
			for( String tempString : itemList.getCategories() )
				itemCategoryComboBox.addItem(tempString);
			itemCategoryComboBox.setSelectedIndex(selectedCategoryIndex);
		}
	}
	
	public void delCategory()
	{
		if( selectedCategoryIndex < 0 )
			JOptionPane.showMessageDialog(itemPagePane, "삭제할 분류를 다시 선택하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else
		{
			categoryNameTextField.setText("");
			itemList.delCategory( selectedCategoryIndex );
			categoryListJList.setListData( itemList.getCategories().toArray() );
			categoryOrderComboBox.removeItemAt(itemList.getCategories().size()+1);
			categoryOrderComboBox.setSelectedIndex(categoryOrderComboBox.getItemCount()-1);
			
			// 품목추가의 분류선택
			itemCategoryComboBox.removeItemAt( selectedCategoryIndex );
			selectedCategoryIndex = -1;
		}
	}
	
	public void addItem()
	{
		if( itemCategoryComboBox.getItemCount() == 0 )
			JOptionPane.showMessageDialog(itemPagePane, "분류추가를 먼저 해주세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemNameTextField.getText().trim().isEmpty() )
			JOptionPane.showMessageDialog(itemPagePane, "품목명을 입력하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemPriceTextField.getText().trim().isEmpty() )
			JOptionPane.showMessageDialog(itemPagePane, "가격을 입력하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemCostTextField.getText().trim().isEmpty() )
			JOptionPane.showMessageDialog(itemPagePane, "원가을 입력하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemList.getItemM(itemCategoryComboBox.getSelectedIndex()).getItem( itemNameTextField.getText().trim() ) != null )
			JOptionPane.showMessageDialog(itemPagePane, "품목명은 중복될 수 없습니다.", "error", JOptionPane.WARNING_MESSAGE);
		else
		{
			itemList.addItem(itemCategoryComboBox.getSelectedIndex(), itemNameTextField.getText().trim(), 
					Integer.parseInt(itemPriceTextField.getText()), Integer.parseInt(itemCostTextField.getText()), itemSoldOutRdbtnO.isSelected());
			if( itemOrderComboBox.getSelectedIndex() != itemList.getItemM(itemCategoryComboBox.getSelectedIndex()).getSize()-1 )
					itemList.getItemM(itemCategoryComboBox.getSelectedIndex()).setItemOrder(itemList.getItemM(itemCategoryComboBox.getSelectedIndex()).getSize()-1, itemOrderComboBox.getSelectedIndex());
			
			
			// 품목목록 갱신
			selectedCategoryIndex = itemCategoryComboBox.getSelectedIndex();
			categoryListJList.setSelectedIndex(selectedCategoryIndex);
			itemListJList.setListData( itemList.getItemM( selectedCategoryIndex ).getItemsNames() );
			
			itemNameTextField.setText("");
			itemPriceTextField.setText("");
			itemCostTextField.setText("");
			itemOrderComboBox.addItem( itemList.getItemM( selectedCategoryIndex).getSize()+1 );
			itemOrderComboBox.setSelectedIndex(itemOrderComboBox.getItemCount()-1);
		}
	}
	
	public void modifyItem()
	{
		if( selectedItemIndex < 0 )
			JOptionPane.showMessageDialog(itemPagePane, "수정할 품목을 다시 선택하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemCategoryComboBox.getItemCount() == 0 )
			JOptionPane.showMessageDialog(itemPagePane, "분류추가를 먼저 해주세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemNameTextField.getText().trim().isEmpty() )
			JOptionPane.showMessageDialog(itemPagePane, "품목명을 입력하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemPriceTextField.getText().trim().isEmpty() )
			JOptionPane.showMessageDialog(itemPagePane, "가격을 입력하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemCostTextField.getText().trim().isEmpty() )
			JOptionPane.showMessageDialog(itemPagePane, "원가을 입력하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemOrderComboBox.getSelectedIndex() >= itemList.getItemM(itemCategoryComboBox.getSelectedIndex()).getSize() )
			JOptionPane.showMessageDialog(itemPagePane, "올바른 품목순서을 선택하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else if( itemList.getItemM(itemCategoryComboBox.getSelectedIndex()).getItem( itemNameTextField.getText().trim() ) != null 
				&& !itemList.getItemM(itemCategoryComboBox.getSelectedIndex()).getItem( itemNameTextField.getText().trim() ).getItemName().equals( itemList.getItemM(itemCategoryComboBox.getSelectedIndex()).getItem(selectedItemIndex).getItemName() ) )
			JOptionPane.showMessageDialog(itemPagePane, "품목명은 중복될 수 없습니다.", "error", JOptionPane.WARNING_MESSAGE);
		else
		{
			itemList.modifyItem(itemCategoryComboBox.getSelectedIndex(), selectedItemIndex, itemNameTextField.getText().trim(), 
					Integer.parseInt(itemPriceTextField.getText()), Integer.parseInt(itemCostTextField.getText()), itemSoldOutRdbtnO.isSelected());
			itemList.getItemM(itemCategoryComboBox.getSelectedIndex()).setItemOrder(selectedItemIndex, itemOrderComboBox.getSelectedIndex());
			
			
			itemListJList.setListData( itemList.getItemM( selectedCategoryIndex ).getItemsNames() );
		}
	}
	
	public void delItem()
	{
		if( selectedItemIndex < 0 )
			JOptionPane.showMessageDialog(itemPagePane, "삭제할 품목를 다시 선택하세요.", "error", JOptionPane.WARNING_MESSAGE);
		else
		{
			itemList.delItem(selectedCategoryIndex, selectedItemIndex);
			// 품목목록 갱신
			itemListJList.setListData( itemList.getItemM(selectedCategoryIndex).getItemsNames() );
			
			// 품목추가부분 리셋
			itemNameTextField.setText("");
			itemPriceTextField.setText("");
			itemCostTextField.setText("");
			itemSoldOutRdbtnX.setSelected(true);
			itemOrderComboBox.removeItemAt(itemList.getItemM(selectedCategoryIndex).getSize()+1);
			itemOrderComboBox.setSelectedIndex(itemOrderComboBox.getItemCount()-1);
			
			selectedItemIndex = -1;
		}
	}
	

	// 주문 계산 부분
	public void orderManagementGUI()
	{
		order = new order();
		
		mainPagePane.setVisible(false);
		setBounds(100, 100, 1500, 650);
		setTitle("주문 / 계산");
		

		
		orderPagePane = new JPanel();
		orderPagePane.setLayout(null);
		add(orderPagePane);
				
		// 윗 패널
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 934, 60);
		orderPagePane.add(topPanel);
		topPanel.setLayout(null);
		
		JLabel mainLabel = new JLabel("주문 / 계산");
		mainLabel.setBounds(12, 10, 154, 41);
		topPanel.add(mainLabel);
		
		
		JLabel presentTimeLabel = new JLabel("현재 시간 : ");
		presentTimeLabel.setBounds(350, 10, 94, 41);
		topPanel.add(presentTimeLabel);
		
		// 시간 바꾸기
		JLabel timeLabel = new JLabel();
		timeChange = new timeRunable(timeLabel);
		ExecutorService runner = Executors.newFixedThreadPool(1);
		runner.execute(timeChange);
		timeLabel.setForeground(new Color(0, 204, 102));
		timeLabel.setBounds(446, 10, 250, 41);
		topPanel.add(timeLabel);
		
		JButton nextDay = new JButton();
		topPanel.add(nextDay);
		nextDay.setText("마감");
		nextDay.setBounds(818, 47, 104, 31);
		nextDay.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						timeChange.nextDay();
						System.out.println("time change");
					}
			
				});
		
		
		
		JButton mainMenuBtn = new JButton("메인메뉴");
		mainMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove( orderPagePane );
				mainPageGUI();
			}
		});
		
		mainMenuBtn.setBounds(818, 16, 104, 31);
		topPanel.add(mainMenuBtn);
		
		// 왼쪽 패널
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(0, 63, 348, 548);
		orderPagePane.add(leftPanel);
		leftPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 0, 324, 326);
		leftPanel.add(scrollPane_1);
		
		DefaultTableModelOrderList = new DefaultTableModel(new Object[0][4], new String[]{"품목명", "단가", "수량", "가격"}){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		itemListTable = new JTable();
		scrollPane_1.setViewportView(itemListTable);
		itemListTable.setModel(DefaultTableModelOrderList);
		

		itemListTable.setRowHeight(25);
		itemListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel totalLabel = new JLabel("합계  :  ");
		totalLabel.setBounds(177, 326, 65, 27);
		leftPanel.add(totalLabel);
		
		totalMoneyLabel = new JLabel("0원");
		totalMoneyLabel.setForeground(new Color(204, 51, 51));
		totalMoneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalMoneyLabel.setBounds(230, 326, 106, 27);
		leftPanel.add(totalMoneyLabel);
		
		JPanel orderListBtnsPanel = new JPanel();
		orderListBtnsPanel.setBounds(12, 363, 324, 175);
		leftPanel.add(orderListBtnsPanel);
		orderListBtnsPanel.setLayout(new GridLayout(4, 2, 3, 3));
		
		// 왼쪽패널 
		JButton plusBtn = new JButton("+");
		plusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int row = itemListTable.getSelectedRow();
				
				if( row < 0 )
					JOptionPane.showMessageDialog(itemPagePane, "품목을 먼저 선택해주세요.", "error", JOptionPane.WARNING_MESSAGE);
				else
				{
					if( order.getOrderItemNum(row) == -1 )
					{
						order.delOrderItem(row);
						DefaultTableModelOrderList.removeRow(row);
					}
					else
					{
						order.setOrderItemNum(row, order.getOrderItemNum(row)+1);
						DefaultTableModelOrderList.setValueAt(order.getOrderItemNum(row), row, 2);
						DefaultTableModelOrderList.setValueAt(String.format("%,d", order.getOrderItemList().get(row).getOrderItem().getPrice()*order.getOrderItemNum(row)), row, 3);
					}
					totalSum();
				}
			}
		});
		
		orderListBtnsPanel.add(plusBtn);
		
		JButton minusBtn = new JButton("-");
		minusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int row = itemListTable.getSelectedRow();
				
				if( row < 0 )
					JOptionPane.showMessageDialog(itemPagePane, "품목을 먼저 선택해주세요.", "error", JOptionPane.WARNING_MESSAGE);
				else
				{
					if( order.getOrderItemNum(row) == 1 )
					{
						order.delOrderItem(row);
						DefaultTableModelOrderList.removeRow(row);
					}
					else
					{
						order.setOrderItemNum(row, order.getOrderItemNum(row)-1);
						DefaultTableModelOrderList.setValueAt(order.getOrderItemNum(row), row, 2);
						DefaultTableModelOrderList.setValueAt(String.format("%,d", order.getOrderItemList().get(row).getOrderItem().getPrice()*order.getOrderItemNum(row)), row, 3);
					}
					totalSum();
				}
			}
		});
		orderListBtnsPanel.add(minusBtn);
		
		JButton selectedDelBtn = new JButton("선택삭제");
		selectedDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int row = itemListTable.getSelectedRow();
				if( row < 0 )
					JOptionPane.showMessageDialog(itemPagePane, "품목을 먼저 선택해주세요.", "error", JOptionPane.WARNING_MESSAGE);
				else
				{
					order.delOrderItem(row);
					DefaultTableModelOrderList.removeRow(row);
					totalSum();
				}
			}
		});
		orderListBtnsPanel.add(selectedDelBtn);
		
		JButton allDelBtn = new JButton("전체삭제");
		allDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				orderReset();
			}
		});
		orderListBtnsPanel.add(allDelBtn);
		
		JButton cashBtn = new JButton("계산");
		cashBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					cashGUI();
			}
		});
		orderListBtnsPanel.add(cashBtn);
		
		JButton todaySalesList = new JButton("금일판매목록");
		todaySalesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				todaySalesListGUI(false);
			}
		});
		orderListBtnsPanel.add(todaySalesList);
		
		//오른 패널
		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(350, 63, 584, 548);
		orderPagePane.add(rightPanel);
		rightPanel.setLayout(null);
		
		JTabbedPane tPane = new JTabbedPane(JTabbedPane.TOP);
		tPane.setBounds(0, 0, 574, 538);
		rightPanel.add(tPane);
		


		tPane.addTab("테이블",t.getContentPane());
						
		for( int i = 0; i < itemList.getCategories().size(); i++ )
		{
			for( int j = 0; j <= (itemList.getItemM(i).getSize()-1)/25; j++ )
			{
				int k;
				JPanel categoryPanel = new JPanel();
				tPane.addTab(itemList.getCategories().get(i), categoryPanel);
				categoryPanel.setLayout(new GridLayout(0, 5, 10, 10));
				
				for( k = 0; k < itemList.getItemM(i).getSize()-j*25 && k < 25 ; k++ )
				{
					item tempItem = itemList.getItemM(i).getItem(k+j*25);
					JButton itemBtn = new JButton( String.format(tempItem.getItemName(), 
							tempItem.getItemName(), tempItem.getPrice()) );
					
					itemBtn.setActionCommand( String.format("%d %d", i, k+j*25) );
					itemBtn.setMargin( new Insets(0,0,0,0) );
					itemBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							String[] tempStringArray = event.getActionCommand().split(" ");
							
							int cIndex = Integer.parseInt(tempStringArray[0]);
							int iIndex = Integer.parseInt(tempStringArray[1]);
							
						    					    
							item tempItem = itemList.getItemM( cIndex ).getItem(iIndex);
							int indexNum = order.getOrderItemIndex(tempItem);
							
							if( indexNum == -1 )
							{
								order.setOrderItem(tempItem);
								DefaultTableModelOrderList.addRow( new Object[]{tempItem.getItemName(), String.format("%,d", tempItem.getPrice()), 1, String.format("%,d", tempItem.getPrice())} );
							}
							else
							{
								order.setOrderItemNum(indexNum, order.getOrderItemNum(indexNum)+1);
								DefaultTableModelOrderList.setValueAt(order.getOrderItemNum(indexNum), indexNum, 2);
								DefaultTableModelOrderList.setValueAt(String.format("%,d", tempItem.getPrice()*order.getOrderItemNum(indexNum)), indexNum, 3);
							}
							
							totalSum();	
						}
					});
					
					categoryPanel.add(itemBtn);
				}
				for( int m = k; m < 25; m++ )
				{
					JButton emptyBtn = new JButton();
					categoryPanel.add(emptyBtn);
				}
			}
		}
		
	}
	
	public int totalSum()
	{
		int sum = 0;
		for( orderItems tempOrderItems : order.getOrderItemList() )
			sum += tempOrderItems.getOrderItem().getPrice() * tempOrderItems.getNum();
		
		totalMoneyLabel.setText( String.format("%,d원", sum) );
		
		return sum;
	}
	
//	public void getTable()
//	{
//		int i = 0;
//		
//		for(i=0; i<8; i++)
//		{
//			if(itemList.items.isEmpty())
//			{
//				t.buttons[i].setForeground(Color.YELLOW);
//				
//			}
//			
//		}
//		
//		
//	}
//	
	public void orderReset()
	{
		order = new order();
		DefaultTableModelOrderList.setRowCount(0);
		totalSum();
	}
	
	public void cashGUI()
	{
		paymentSum = 0;
		
		cashFrame = new JFrame("계산");
		cashFrame.setVisible(true);
		cashFrame.setBounds(100, 100, 300, 400);
		cashFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		cashFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				cashFrame.setVisible(false);
			}
		});
		cashFrame.getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(51, 102, 204)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 275, 321);
		cashFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel paymentLabel = new JLabel("결제금액");
		paymentLabel.setBounds(0, 0, 251, 47);
		panel.add(paymentLabel);
		
		paymentSumLabel = new JLabel("0원");
		paymentSumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		paymentSumLabel.setBounds(0, 39, 275, 47);
		panel.add(paymentSumLabel);
		
		JLabel totalLabel = new JLabel("청구금액");
		totalLabel.setBounds(0, 105, 251, 47);
		panel.add(totalLabel);
		
		sum = 0;
		for( orderItems tempOrderItems : order.getOrderItemList() )
			sum += tempOrderItems.getOrderItem().getPrice() * tempOrderItems.getNum();
		
		JButton okay = new JButton("확인");
				
		okay.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JOptionPane.showMessageDialog(cashFrame, "계산 완료!", "계산 완료", JOptionPane.INFORMATION_MESSAGE);
						order.setDate();
						order.setCash(true);
						totalSalesList.addSalesListOrder(order);
						orderReset();
						cashFrame.setVisible(false);
					}
			
				});
		panel.add(okay);
		okay.setBounds(200,100,100,100);
	}
	

	public void todaySalesListGUI(boolean returnBtn)
	{
		todayFrame = new JFrame();
		todayFrame.setBounds(100, 100, 650, 400);
		todayFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		todayFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				todayFrame.setVisible(false);
			}
		});
		todayFrame.getContentPane().setLayout(null);
		todayFrame.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 510, 341);
		todayFrame.add(scrollPane);
		
		JButton nextDay = new JButton();
		todayFrame.add(nextDay);
		nextDay.setText("마감");
		nextDay.setBounds(522, 10, 100, 341);
		nextDay.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
			    			today.add(Calendar.DATE,1);
					}
			
				});
		
		
		dailySalesList todaySalesList = totalSalesList.getSalesList();
		
		char tempDayOfWeek = 0;
		today = Calendar.getInstance();
		
		switch( today.get(Calendar.DAY_OF_WEEK) )
		{
			case 1:
				tempDayOfWeek = '일';
				break;
			case 2:
				tempDayOfWeek = '월';
				break;
			case 3:
				tempDayOfWeek = '화';
				break;
			case 4:
				tempDayOfWeek = '수';
				break;
			case 5:
				tempDayOfWeek = '목';
				break;
			case 6:
				tempDayOfWeek = '금';
				break;
			case 7:
				tempDayOfWeek = '토';
				break;
		}
		if( returnBtn )
			todayFrame.setTitle( "판품선택창" );
		else
			todayFrame.setTitle( String.format("%d년 %d월 %d일(%c) 금일판매목록", today.get(Calendar.YEAR), today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH), tempDayOfWeek) );
		
		Object[][] listString;
		if( todaySalesList == null)
		{
			listString = new Object[0][3];
		}
		else
		{
			listString = new Object[todaySalesList.getOrderList().size()][3];
			for( int i = 0; i < todaySalesList.getOrderList().size(); i++ )
			{
				order tempOrder = todaySalesList.getOrderList().get(todaySalesList.getOrderList().size() - i - 1);
				
				listString[i][0] = String.format("%02d:%02d:%02d", tempOrder.getDate().get(Calendar.HOUR_OF_DAY), tempOrder.getDate().get(Calendar.MINUTE), tempOrder.getDate().get(Calendar.SECOND) );
				
				int sum = 0;
				StringBuilder tempStringBuilder = new StringBuilder();
				for( orderItems tempOrderItems : tempOrder.getOrderItemList() )
				{
					tempStringBuilder.append(tempOrderItems.getOrderItem().getItemName() + "*" + tempOrderItems.getNum() +", ");
					sum += tempOrderItems.getOrderItem().getPrice() * tempOrderItems.getNum();
				}
				if( tempOrder.getCash() )
					listString[i][1] = String.format("%,d원(계산)", sum);
				
				listString[i][2] = tempStringBuilder.toString();
			}
		}
		
		todaySalesListTable = new JTable();
		tables = new DefaultTableModel( listString,	new String[] {"거래일시", "계산금액", "거래내역"} ) {
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
	}
	
	
	// 매출 현황 부분 
	public void salesListGUI()
	{
		mainPagePane.setVisible(false);
		setTitle("매출현황");
		setBounds(100, 100, 650, 650);
		
		salesListPagePane = new JPanel();
		salesListPagePane.setLayout(null);
		add(salesListPagePane);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 634, 611);
		salesListPagePane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(10, 10, 612, 41);
		mainPanel.add(topPanel);
		topPanel.setLayout(null);
		
		JLabel label = new JLabel("매출현황");
		label.setBounds(0, -1, 131, 41);
		topPanel.add(label);

		JButton total = new JButton();
		total.setBounds(139, 12, 53, 23);
		total.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						totalSalesList();
					}
			
				});
		

		JButton button = new JButton("메인메뉴");
		button.setBounds(508, 6, 104, 31);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove( salesListPagePane );
				mainPageGUI();
			}
		});
		topPanel.add(button);
		
		
		// 매출현황표
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 55, 434, 546);
		mainPanel.add(scrollPane);
		
		
		
		
		DefaultTableModelSalesList = new DefaultTableModel(new Object[0][4], new String[] {"품목명", "분류", "단가", "수량", "가격"})
		{
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		salesListTable = new JTable();
		salesListTable.setModel(DefaultTableModelSalesList);
		scrollPane.setViewportView(salesListTable);
		
		for( dailySalesList tempdSL : totalSalesList.getTotalSalesList())
		{
			Calendar tempDate = tempdSL.getDate();
			
		}
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setBounds(448, 55, 174, 546);
		mainPanel.add(rightPanel);
		rightPanel.setLayout(null);
		
		JLabel totalLabel = new JLabel("총액");
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel.setBounds(66, 10, 96, 38);
		rightPanel.add(totalLabel);
		
		totalMoneyLabel2 = new JLabel();
		totalMoneyLabel2.setForeground(Color.BLUE);
		totalMoneyLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		totalMoneyLabel2.setBounds(23, 44, 139, 38);
		rightPanel.add(totalMoneyLabel2);
		
		JLabel totalCostLabel = new JLabel("원가");
		totalCostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalCostLabel.setBounds(69, 87, 93, 38);
		rightPanel.add(totalCostLabel);
		
		totalCostMoneyLabel = new JLabel();
		totalCostMoneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalCostMoneyLabel.setForeground(Color.BLACK);
		totalCostMoneyLabel.setBounds(23, 122, 139, 38);
		rightPanel.add(totalCostMoneyLabel);
		
		JLabel totalProfitLabel = new JLabel("매출");
		totalProfitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalProfitLabel.setBounds(69, 170, 93, 38);
		rightPanel.add(totalProfitLabel);
		
		totalProfitMoneyLabel = new JLabel();
		totalProfitMoneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalProfitMoneyLabel.setForeground(Color.RED);
		totalProfitMoneyLabel.setBounds(23, 205, 139, 38);
		rightPanel.add(totalProfitMoneyLabel);
		
		JLabel cashLabel = new JLabel("계산");
		cashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cashLabel.setBounds(69, 277, 93, 38);
		rightPanel.add(cashLabel);

		
		
		
		totalSalesList();
	}
	
	public void setMoney()
	{
		totalMoneyLabel2.setText( String.format("%,d원", totalMoney) );
		totalCostMoneyLabel.setText( String.format("%,d원", totalCost) );
		totalProfitMoneyLabel.setText( String.format("%,d원", totalMoney-totalCost) );
		cashMoneyLabel.setText( String.format("%,d원", cashMoney) );
	}
	
	public void resetMoney()
	{
		totalMoney = 0;
		totalCost = 0;
		cashMoney = 0;

	}
	
	public void totalSalesList()
	{
		resetMoney();
		
		DefaultTableModelSalesList.setRowCount(0);
		for( int i = 0; i < itemList.getCategories().size(); i++ )
		{
			itemManage tempItemManage = itemList.getItemM(i);
			for( int j = 0; j < tempItemManage.getSize(); j++ )
			{
				int num = 0;
				item tempItem = tempItemManage.getItem(j);
				
				for( dailySalesList tempdSL : totalSalesList.getTotalSalesList())
				{
					for( order tempOrder : tempdSL.getOrderList() )
					{
						boolean cash = tempOrder.getCash();
												
						for( orderItems tempOI : tempOrder.getOrderItemList() )
						{
							if( tempItem.getItemName().equals( tempOI.getOrderItem().getItemName() ) )
							{
								num += tempOI.getNum();
								if( cash )
									cashMoney += tempOI.getOrderItem().getPrice() * tempOI.getNum();
							}
						}
						
					}
				}
				
				if( num > 0 )
					DefaultTableModelSalesList.addRow( new Object[]{tempItem.getItemName(), itemList.getCategories().get(i), String.format("%,d", tempItem.getPrice())
							, num, String.format("%,d", tempItem.getPrice()*num)} );
				
				totalMoney += tempItem.getPrice()*num;
				totalCost += tempItem.getCost()*num;
			}
		}
		
		for( dailySalesList tempdSL : totalSalesList.getTotalSalesList())
		{
			for( order tempOrder : tempdSL.getOrderList() )
			{
				if( !tempOrder.getCash() )
				{
					System.out.println("testtest");
				}
			}
		}
		
		setMoney();
	}
	
	public void monthSalesList()
	{
		resetMoney();
		
		DefaultTableModelSalesList.setRowCount(0);
		for( int i = 0; i < itemList.getCategories().size(); i++ )
		{
			itemManage tempItemManage = itemList.getItemM(i);
			for( int j = 0; j < tempItemManage.getSize(); j++ )
			{
				int num = 0;
				item tempItem = tempItemManage.getItem(j);
				
				for( dailySalesList tempdSL : totalSalesList.getTotalSalesList())
				{
					Calendar tempDate = tempdSL.getDate();
					if( String.format("%d' %d", tempDate.get(Calendar.YEAR), tempDate.get(Calendar.MONTH)+1).equals( monthComboBox.getSelectedItem() ) )
					{
						for( order tempOrder : tempdSL.getOrderList() )
						{
							boolean cash = tempOrder.getCash();
							
							for( orderItems tempOI : tempOrder.getOrderItemList() )
							{
								if( tempItem.getItemName().equals( tempOI.getOrderItem().getItemName() ) )
								{
									num += tempOI.getNum();
									if( cash )
										cashMoney += tempOI.getOrderItem().getPrice() * tempOI.getNum();
									
								}
							}
							
						}
					}
				}
				
				if( num > 0 )
					DefaultTableModelSalesList.addRow( new Object[]{tempItem.getItemName(), itemList.getCategories().get(i), String.format("%,d", tempItem.getPrice())
							, num, String.format("%,d", tempItem.getPrice()*num)} );
				
				totalMoney += tempItem.getPrice()*num;
				totalCost += tempItem.getCost()*num;
			}
		}
		
		for( dailySalesList tempdSL : totalSalesList.getTotalSalesList())
		{
			Calendar tempDate = tempdSL.getDate();
			if( String.format("%d' %d", tempDate.get(Calendar.YEAR), tempDate.get(Calendar.MONTH)+1).equals( monthComboBox.getSelectedItem() ) ) 
			{
				for( order tempOrder : tempdSL.getOrderList() )
				{
					System.out.println("testtest");
				}
			}
		}
		
		setMoney();
	}
	
	public void daySalesList()
	{
		resetMoney();
		
		DefaultTableModelSalesList.setRowCount(0);
		for( int i = 0; i < itemList.getCategories().size(); i++ )
		{
			itemManage tempItemManage = itemList.getItemM(i);
			for( int j = 0; j < tempItemManage.getSize(); j++ )
			{
				int num = 0;
				item tempItem = tempItemManage.getItem(j);
				
				for( dailySalesList tempdSL : totalSalesList.getTotalSalesList())
				{
					Calendar tempDate = tempdSL.getDate();
					if( String.format("%d' %d.%d", tempDate.get(Calendar.YEAR), tempDate.get(Calendar.MONTH)+1, tempDate.get(Calendar.DATE)).equals( dayComboBox.getSelectedItem() ) )
					{
						for( order tempOrder : tempdSL.getOrderList() )
						{
							boolean cash = tempOrder.getCash();
							
							for( orderItems tempOI : tempOrder.getOrderItemList() )
							{
								if( tempItem.getItemName().equals( tempOI.getOrderItem().getItemName() ) )
								{
									num += tempOI.getNum();
									if( cash )
										cashMoney += tempOI.getOrderItem().getPrice() * tempOI.getNum();
							}
							
						}
					}
				}
				
				if( num > 0 )
					DefaultTableModelSalesList.addRow( new Object[]{tempItem.getItemName(), itemList.getCategories().get(i), String.format("%,d", tempItem.getPrice())
							, num, String.format("%,d", tempItem.getPrice()*num)} );
				
				totalMoney += tempItem.getPrice()*num;
				totalCost += tempItem.getCost()*num;
			}
		}
		
		for( dailySalesList tempdSL : totalSalesList.getTotalSalesList())
		{
			Calendar tempDate = tempdSL.getDate();
			if( String.format("%d' %d.%d", tempDate.get(Calendar.YEAR), tempDate.get(Calendar.MONTH)+1, tempDate.get(Calendar.DATE)).equals( dayComboBox.getSelectedItem() ) )
			{
				for( order tempOrder : tempdSL.getOrderList() )
				{
					if( !tempOrder.getCash() )
					{
						System.out.println("testtest");
					}
				}
			}
		}
		
		setMoney();
	}
}
}
