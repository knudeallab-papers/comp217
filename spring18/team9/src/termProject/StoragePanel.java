package termProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class StoragePanel extends Panel implements MouseListener, ActionListener, Serializable {
	public static final int SMALL_WIDTH = 700;
	public static final int SMALL_HEIGHT = 400;
	
	Font font = new Font("맑은고딕", Font.BOLD, 15);
	
	DefaultTableModel storageModel;
	static JTable storageTable;
	private String[] stateStr = new String[4];
	public static IngreStruct[] struct = new IngreStruct[100];
	
	JTextField nameF;
	JTextField priceF;
	JTextField storeF;
	JTextField phoneF;
	JTextField quantityF;
	
	JTextField orderField;
	JTextField cancelField;
	
	private int index;
	
	public StoragePanel() {
		setLayout(new BorderLayout());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(600, 1000));
		leftPanel.setLayout(new BorderLayout());
		String[] header = {"이름", "재고", "주문", "가격"};
		String[][] contents = null;
		
		storageModel = new DefaultTableModel(contents, header);
		storageTable = new JTable(storageModel);
		storageTable.setRowHeight(30);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = storageTable.getColumnModel();
		for(int i=0; i<tcm.getColumnCount(); i++)
			tcm.getColumn(i).setCellRenderer(dtcr);
		storageTable.setFont(font);
		JScrollPane storageScr = new JScrollPane(storageTable);
		leftPanel.add(storageScr, BorderLayout.CENTER);
		
		storageTable.addMouseListener(this);
		
		JPanel ingrePanel = new JPanel();
		ingrePanel.setLayout(new FlowLayout());
		JButton ingreBtn = new JButton("추가");
		ingreBtn.setFont(font);
		ingreBtn.setBackground(Color.WHITE);
		ingreBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IngreWindow ingreWindow = new IngreWindow();
				ingreWindow.setVisible(true);
			}
		});
		ingrePanel.add(ingreBtn);
		
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setFont(font);
		deleteBtn.setBackground(Color.WHITE);
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(storageTable.getSelectedRow() == -1)
					return;
				else {
					storageModel.removeRow(storageTable.getSelectedRow());
					struct[index] = null;
				}
			}
		});
		ingrePanel.add(deleteBtn);
		leftPanel.add(ingrePanel, BorderLayout.SOUTH);
		
		add(leftPanel, BorderLayout.WEST);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		JLabel nameL = new JLabel("이름");
		nameL.setFont(font);
		contentPanel.add(nameL);
		nameF = new JTextField(10);
		nameF.setEditable(false);
		nameF.setBackground(Color.WHITE);
		contentPanel.add(nameF);
		JLabel priceL = new JLabel("가격");
		priceL.setFont(font);
		contentPanel.add(priceL);
		priceF = new JTextField(10);
		priceF.setEditable(false);
		priceF.setBackground(Color.WHITE);
		contentPanel.add(priceF);
		JLabel storeL = new JLabel("판매처");
		storeL.setFont(font);
		contentPanel.add(storeL);
		storeF = new JTextField(10);
		storeF.setEditable(false);
		storeF.setBackground(Color.WHITE);
		contentPanel.add(storeF);
		JLabel phoneL = new JLabel("연락처");
		phoneL.setFont(font);
		contentPanel.add(phoneL);
		phoneF = new JTextField(20);
		phoneF.setEditable(false);
		phoneF.setBackground(Color.WHITE);
		contentPanel.add(phoneF);
		JLabel quantityL = new JLabel("수량");
		quantityL.setFont(font);
		contentPanel.add(quantityL);
		quantityF = new JTextField(10);
		quantityF.setEditable(false);
		quantityF.setBackground(Color.WHITE);
		contentPanel.add(quantityF);
		
		nameL.setBounds(270, 170, 100, 20);
		priceL.setBounds(270, 270, 100, 20);
		storeL.setBounds(270, 370, 100, 20);
		phoneL.setBounds(270, 470, 100, 20);
		quantityL.setBounds(270, 570, 100, 20);
		nameF.setBounds(400, 170, 150, 20);
		priceF.setBounds(400, 270, 150, 20);
		storeF.setBounds(400, 370, 150, 20);
		phoneF.setBounds(400, 470, 150, 20);
		quantityF.setBounds(400, 570, 150, 20);
		rightPanel.add(contentPanel, BorderLayout.CENTER);
		
		JPanel orderPanel = new JPanel();
		orderPanel.setLayout(new FlowLayout());
		JLabel orderLabel = new JLabel("주문 수량", JLabel.RIGHT);
		orderLabel.setFont(font);
		orderPanel.add(orderLabel);
		orderField = new JTextField(5);
		orderPanel.add(orderField);
		JLabel cancelLabel = new JLabel("주문 취소 수량", JLabel.RIGHT);
		cancelLabel.setFont(font);
		orderPanel.add(cancelLabel);
		cancelField = new JTextField(5);
		orderPanel.add(cancelField);
		JButton orderBtn = new JButton("주문");
		orderBtn.setFont(font);
		orderBtn.setBackground(Color.WHITE);
		orderBtn.addActionListener(this);
		orderPanel.add(orderBtn);
		JButton cancelBtn = new JButton("주문 취소");
		cancelBtn.setFont(font);
		cancelBtn.setBackground(Color.WHITE);
		cancelBtn.addActionListener(this);
		orderPanel.add(cancelBtn);
		rightPanel.add(orderPanel, BorderLayout.SOUTH);
		
		add(rightPanel, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		
		String inField;
		int input;
		int row = storageTable.getSelectedRow();
		Object obj;
		int tableNum;
		String str;
		int price = Integer.parseInt(struct[index].price);
		
		if(actionCmd.equals("주문")) {
			inField = orderField.getText(); //"3"
			input = Integer.parseInt(inField); //3
			
			obj = storageTable.getValueAt(row, 2);
			tableNum = Integer.parseInt((String)obj);
			tableNum = tableNum + input;
			str = String.valueOf(tableNum);
			storageTable.setValueAt(str, row, 2);

			obj = storageTable.getValueAt(row, 3);
			tableNum = Integer.parseInt((String)obj);
			tableNum = tableNum + input * price;
			str = String.valueOf(tableNum);
			storageTable.setValueAt(str, row, 3);
			
			orderField.setText("");
		}else if(actionCmd.equals("주문 취소")) {
			inField = cancelField.getText(); //"3"
			input = Integer.parseInt(inField); //3
			
			obj = storageTable.getValueAt(row, 2);
			tableNum = Integer.parseInt((String)obj);
			tableNum = tableNum - input;
			str = String.valueOf(tableNum);
			storageTable.setValueAt(str, row, 2);
			
			obj = storageTable.getValueAt(row, 3);
			tableNum = Integer.parseInt((String)obj);
			tableNum = tableNum - input * price;
			str = String.valueOf(tableNum);
			storageTable.setValueAt(str, row, 3);
			
			cancelField.setText("");
		}else
			System.err.println("Unexpected error in StoragePanel");
	}

	
	/////////////// ingreWindow ///////////////
	private class IngreWindow extends JFrame implements ActionListener, Serializable{
		private JTextField nameField;
		private JTextField priceField;
		private JTextField storeField;
		private JTextField phoneField;
		private JTextField quantityField;
		
		public IngreWindow() {
			super("재료 주문 창");
			setSize(SMALL_WIDTH, SMALL_HEIGHT);
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(null);
			JLabel nameLabel = new JLabel("이름");
			nameLabel.setFont(font);
			topPanel.add(nameLabel);
			JLabel priceLabel = new JLabel("가격");
			priceLabel.setFont(font);
			topPanel.add(priceLabel);
			JLabel storeLabel = new JLabel("판매처");
			storeLabel.setFont(font);
			topPanel.add(storeLabel);
			JLabel phoneLabel = new JLabel("연락처");
			phoneLabel.setFont(font);
			topPanel.add(phoneLabel);
			JLabel amountLabel = new JLabel("수량");
			amountLabel.setFont(font);
			topPanel.add(amountLabel);
			
			nameField = new JTextField(10);
			topPanel.add(nameField);
			priceField = new JTextField(10);
			topPanel.add(priceField);
			storeField = new JTextField(10);
			topPanel.add(storeField);
			phoneField = new JTextField(10);
			topPanel.add(phoneField);
			quantityField = new JTextField(10);
			topPanel.add(quantityField);
			
			nameLabel.setBounds(200, 60, 100, 20);
			priceLabel.setBounds(200, 100, 100, 20);
			storeLabel.setBounds(200, 140, 100, 20);
			phoneLabel.setBounds(200, 180, 100, 20);
			amountLabel.setBounds(200, 220, 100, 20);
			
			nameField.setBounds(300, 60, 150, 20);
			priceField.setBounds(300, 100, 150, 20);
			storeField.setBounds(300, 140, 150, 20);
			phoneField.setBounds(300, 180, 150, 20);
			quantityField.setBounds(300, 220, 150, 20);
			add(topPanel, BorderLayout.CENTER);
			
			JPanel choicePanel = new JPanel();
			choicePanel.setLayout(new FlowLayout());
			JButton confirmBtn =  new JButton("확인");
			confirmBtn.setFont(font);
			confirmBtn.setBackground(Color.WHITE);
			confirmBtn.addActionListener(this);
			choicePanel.add(confirmBtn);
			JButton cancelBtn = new JButton("취소");
			cancelBtn.setFont(font);
			cancelBtn.setBackground(Color.WHITE);
			cancelBtn.addActionListener(this);
			choicePanel.add(cancelBtn);
			add(choicePanel, BorderLayout.SOUTH);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String actionCmd = e.getActionCommand();
			
			if(actionCmd.equals("확인")) {
				for(int i=0; i<100; i++)
					if(struct[i] == null) {
						struct[i] = new IngreStruct();
						struct[i].name = nameField.getText();
						struct[i].price = priceField.getText();
						struct[i].store = storeField.getText();
						struct[i].phone = phoneField.getText();
						struct[i].quantity = quantityField.getText();
						break;
					}
				
				stateStr[0] = nameField.getText();
				stateStr[1] = "0";
				stateStr[2] = "0";
				stateStr[3] = "0";
				storageModel.addRow(stateStr);
				
				dispose();
			}else if(actionCmd.equals("취소"))
				dispose();
			else
				System.err.println("Unexpected error in IngreWindow");
		}
	}
	
	public static void storageOrder() {
		if(storageTable.getRowCount() != 0)
			for(int i=0; i<storageTable.getRowCount(); i++) {
				String orderStr = (String)storageTable.getValueAt(i, 2);
				if(orderStr.equals("0"))
					break;
				
				int orderNum = Integer.parseInt(orderStr);
				String stockStr = (String)storageTable.getValueAt(i, 1);
				int stockNum = Integer.parseInt(stockStr);
				stockNum += orderNum;
				storageTable.setValueAt(String.valueOf(stockNum), i, 1);
				storageTable.setValueAt("0", i, 2);
				
				int stockPrice = Integer.parseInt((String)storageTable.getValueAt(i, 3));
				Sushi.balance -= stockPrice;
				storageTable.setValueAt("0", i, 3);
			}
	}
	
	///////////////////////////////////////////
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JTable table = (JTable)e.getSource();
		int row = table.getSelectedRow();
		String key = (String)table.getValueAt(row, 0);
		
		index = 0;
		for(int i=0; i<100; i++)
			if(struct[i] != null && key.equals(struct[i].name)) {
				index = i;
				break;
			}
		
		nameF.setText(struct[index].name);
		priceF.setText(struct[index].price);
		storeF.setText(struct[index].store);
		phoneF.setText(struct[index].phone);
		quantityF.setText(struct[index].quantity);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}

class IngreStruct implements Serializable{
	public String name;
	public String price;
	public String store;
	public String phone;
	public String quantity;
}
