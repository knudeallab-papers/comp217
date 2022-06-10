package termProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class TablePanel extends JPanel implements ActionListener, Serializable {
	public static final int SMALL_WIDTH = 700;
	public static final int SMALL_HEIGHT = 400;
	
	Font font = new Font("맑은고딕", Font.BOLD, 15);
	
	private String tableNum;
	
	static JToggleButton table1B;
	static JToggleButton table2B;
	static JToggleButton table3B;
	static JToggleButton table4B;
	static JToggleButton table5B;
	static JToggleButton table6B;
	static JToggleButton table7B;
	static JToggleButton table8B;

	JTextArea table1A;
	JTextArea table2A;
	JTextArea table3A;
	JTextArea table4A;
	JTextArea table5A;
	JTextArea table6A;
	JTextArea table7A;
	JTextArea table8A;
	
	JTextField table1F;
	JTextField table2F;
	JTextField table3F;
	JTextField table4F;
	JTextField table5F;
	JTextField table6F;
	JTextField table7F;
	JTextField table8F;
	
	static String[] menuStr = {"메뉴선택"};
	static JComboBox menuBox = new JComboBox(menuStr);
	static String[] guestStr = {"선택안함"};
	static JComboBox guestBox = new JComboBox(guestStr);
	
	public TablePanel() {
		setLayout(new BorderLayout());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(2, 4, 20, 20));
		
		ButtonGroup tableGroup = new ButtonGroup();
		
		JPanel table1P = new JPanel();
		table1P.setLayout(new BorderLayout(10, 10));
		table1B = new JToggleButton("#1");
		table1B.setBackground(Color.WHITE);
		table1B.addActionListener(this);
		table1P.add(table1B, BorderLayout.NORTH);
		table1A = new JTextArea(15, 10);
		table1A.setEditable(false);
		table1P.add(table1A, BorderLayout.CENTER);
		table1F = new JTextField();
		table1F.setEditable(false);
		table1F.setBackground(Color.WHITE);
		table1P.add(table1F, BorderLayout.SOUTH);
		leftPanel.add(table1P);
		
		JPanel table2P = new JPanel();
		table2P.setLayout(new BorderLayout(10, 10));
		table2B = new JToggleButton("#2");
		table2B.setBackground(Color.WHITE);
		table2B.addActionListener(this);
		table2P.add(table2B, BorderLayout.NORTH);
		table2A = new JTextArea(15, 10);
		table2A.setEditable(false);
		table2P.add(table2A, BorderLayout.CENTER);
		table2F = new JTextField();
		table2F.setEditable(false);
		table2F.setBackground(Color.WHITE);
		table2P.add(table2F, BorderLayout.SOUTH);
		leftPanel.add(table2P);
		
		JPanel table3P = new JPanel();
		table3P.setLayout(new BorderLayout(10, 10));
		table3B = new JToggleButton("#3");
		table3B.setBackground(Color.WHITE);
		table3B.addActionListener(this);
		table3P.add(table3B, BorderLayout.NORTH);
		table3A = new JTextArea(15, 10);
		table3A.setEditable(false);
		table3P.add(table3A, BorderLayout.CENTER);
		table3F = new JTextField();
		table3F.setEditable(false);
		table3F.setBackground(Color.WHITE);
		table3P.add(table3F, BorderLayout.SOUTH);
		leftPanel.add(table3P);
		
		JPanel table4P = new JPanel();
		table4P.setLayout(new BorderLayout(10, 10));
		table4B = new JToggleButton("#4");
		table4B.setBackground(Color.WHITE);
		table4B.addActionListener(this);
		table4P.add(table4B, BorderLayout.NORTH);
		table4A = new JTextArea(15 ,10);
		table4A.setEditable(false);
		table4P.add(table4A, BorderLayout.CENTER);
		table4F = new JTextField();
		table4F.setEditable(false);
		table4F.setBackground(Color.WHITE);
		table4P.add(table4F, BorderLayout.SOUTH);
		leftPanel.add(table4P);
		
		JPanel table5P = new JPanel();
		table5P.setLayout(new BorderLayout(10, 10));
		table5B = new JToggleButton("#5");
		table5B.setBackground(Color.WHITE);
		table5B.addActionListener(this);
		table5P.add(table5B, BorderLayout.NORTH);
		table5A = new JTextArea(15, 10);
		table5A.setEditable(false);
		table5P.add(table5A, BorderLayout.CENTER);
		table5F = new JTextField();
		table5F.setEditable(false);
		table5F.setBackground(Color.WHITE);
		table5P.add(table5F, BorderLayout.SOUTH);
		leftPanel.add(table5P);
		
		JPanel table6P = new JPanel();
		table6P.setLayout(new BorderLayout(10, 10));
		table6B = new JToggleButton("#6");
		table6B.setBackground(Color.WHITE);
		table6B.addActionListener(this);
		table6P.add(table6B, BorderLayout.NORTH);
		table6A = new JTextArea(15, 10);
		table6A.setEditable(false);
		table6P.add(table6A, BorderLayout.CENTER);
		table6F = new JTextField();
		table6F.setEditable(false);
		table6F.setBackground(Color.WHITE);
		table6P.add(table6F, BorderLayout.SOUTH);
		leftPanel.add(table6P);
		
		JPanel table7P = new JPanel();
		table7P.setLayout(new BorderLayout(10, 10));
		table7B = new JToggleButton("#7");
		table7B.setBackground(Color.WHITE);
		table7B.addActionListener(this);
		table7P.add(table7B, BorderLayout.NORTH);
		table7A = new JTextArea(15, 10);
		table7A.setEditable(false);
		table7P.add(table7A, BorderLayout.CENTER);
		table7F = new JTextField();
		table7F.setEditable(false);
		table7F.setBackground(Color.WHITE);
		table7P.add(table7F, BorderLayout.SOUTH);
		leftPanel.add(table7P);
		
		JPanel table8P = new JPanel();
		table8P.setLayout(new BorderLayout(10, 10));
		table8B = new JToggleButton("#8");
		table8B.setBackground(Color.WHITE);
		table8B.addActionListener(this);
		table8P.add(table8B, BorderLayout.NORTH);
		table8A = new JTextArea(15, 10);
		table8A.setEditable(false);
		table8P.add(table8A, BorderLayout.CENTER);
		table8F = new JTextField();
		table8F.setEditable(false);
		table8F.setBackground(Color.WHITE);
		table8P.add(table8F, BorderLayout.SOUTH);
		leftPanel.add(table8P);
		
		tableGroup.add(table1B);
		tableGroup.add(table2B);
		tableGroup.add(table3B);
		tableGroup.add(table4B);
		tableGroup.add(table5B);
		tableGroup.add(table6B);
		tableGroup.add(table7B);
		tableGroup.add(table8B);
		add(leftPanel, BorderLayout.CENTER);
		
		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(new FlowLayout());
		JButton addBtn = new JButton("추가");
		addBtn.setFont(font);
		addBtn.setBackground(Color.WHITE);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				AddOrderWindow addOrderWindow = new AddOrderWindow();
				addOrderWindow.setVisible(true);
			}
		});
		choicePanel.add(addBtn);
		JButton payBtn = new JButton("결제");
		payBtn.setFont(font);
		payBtn.setBackground(Color.WHITE);
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PayWindow payWindow = new PayWindow();
				payWindow.setVisible(true);
			}
		});
		choicePanel.add(payBtn);
		
		add(choicePanel, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		tableNum = e.getActionCommand();
		
		JToggleButton selectedBtn = (JToggleButton)e.getSource();
		Color btnColor = selectedBtn.getBackground();
		if(btnColor.equals(Color.BLUE))
			return;
		
		if(tableNum.indexOf("#") != -1) {
			OrderWindow orderWindow = new OrderWindow();
			orderWindow.setVisible(true);
		}
	}
	
	private class OrderWindow extends JFrame implements ActionListener, Serializable{
		JTable subTable;
		
		public OrderWindow() {
			super("주문 창");
			setSize(SMALL_WIDTH, SMALL_HEIGHT);
			setLayout(new GridLayout(1, 2));
			
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			String[] subHeader = {"메뉴", "수량"};
			String[][] subContents = null;
			DefaultTableModel subModel = new DefaultTableModel(subContents, subHeader);
			subTable = new JTable(subModel);
			subTable.setRowHeight(30);
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcm = subTable.getColumnModel();
			for(int i=0; i<tcm.getColumnCount(); i++)
				tcm.getColumn(i).setCellRenderer(dtcr);
			JScrollPane subScr = new JScrollPane(subTable);
			rightPanel.add(subScr, BorderLayout.CENTER);
			
			JPanel leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			JPanel topPanel = new JPanel();
			topPanel.setLayout(null);
			JLabel menuLabel = new JLabel("메뉴");
			topPanel.add(menuLabel);
			menuBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JComboBox box = (JComboBox)e.getSource();
					Object item = box.getSelectedItem();
					
					if(item.equals("메뉴선택"))
						return;
					
					if(subTable.getRowCount() != 0) 
						for(int i=0; i<subTable.getRowCount(); i++)
							if(item.equals(subTable.getValueAt(i, 0))) {
								Integer numObj = (Integer)subTable.getValueAt(i, 1);
								int num = numObj;
								num++;
								subTable.setValueAt(new Integer(num), i, 1);
								return;
							}
					
					Object[] stateStr = new Object[2];
					stateStr[0] = item;
					stateStr[1] = new Integer(1);
					subModel.addRow(stateStr);
				}
			});
			topPanel.add(menuBox);
			
			menuLabel.setBounds(100, 100, 50, 20);
			menuBox.setBounds(150, 100, 100, 20);
			leftPanel.add(topPanel, BorderLayout.CENTER);
			
			JPanel downPanel = new JPanel();
			downPanel.setLayout(new FlowLayout());
			JButton confirmBtn = new JButton("확인");
			confirmBtn.setFont(font);
			confirmBtn.setBackground(Color.WHITE);
			confirmBtn.addActionListener(this);
			downPanel.add(confirmBtn);
			JButton deleteBtn = new JButton("삭제");
			deleteBtn.setFont(font);
			deleteBtn.setBackground(Color.WHITE);
			deleteBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(subTable.getSelectedRow() == -1)
						return;
					else
						subModel.removeRow(subTable.getSelectedRow());
				}
			});
			downPanel.add(deleteBtn);
			leftPanel.add(downPanel, BorderLayout.SOUTH);
			
			add(leftPanel);
			add(rightPanel);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int totalPrice = 0;
			String outputStr = "";
			
			for(int i=0; i<subTable.getRowCount(); i++) {
				String menuStr = (String)subTable.getValueAt(i, 0);
				Integer menuInt = (Integer)subTable.getValueAt(i, 1);
				int menuNum = menuInt;
				
				int menuPrice = 0;
				for(int j=0; j<100; j++)
					if(menuStr.equals(MenuPanel.struct[j].name)) {
						menuPrice = MenuPanel.struct[j].price;
						break;
					}
				outputStr += (menuStr + " x" + menuNum + "\n");
				totalPrice += menuNum * menuPrice;
			}
			
			String priceStr = String.valueOf(totalPrice);
			
			switch(tableNum) {
			case "#1":
				table1B.setBackground(Color.BLUE);
				table1A.setText(outputStr);
				table1F.setText(priceStr);
				break;
			case "#2":
				table2B.setBackground(Color.BLUE);
				table2A.setText(outputStr);
				table2F.setText(priceStr);
				break;
			case "#3":
				table3B.setBackground(Color.BLUE);
				table3A.setText(outputStr);
				table3F.setText(priceStr);
				break;
			case "#4":
				table4B.setBackground(Color.BLUE);
				table4A.setText(outputStr);
				table4F.setText(priceStr);
				break;
			case "#5":
				table5B.setBackground(Color.BLUE);
				table5A.setText(outputStr);
				table5F.setText(priceStr);
				break;
			case "#6":
				table6B.setBackground(Color.BLUE);
				table6A.setText(outputStr);
				table6F.setText(priceStr);
				break;
			case "#7":
				table7B.setBackground(Color.BLUE);
				table7A.setText(outputStr);
				table7F.setText(priceStr);
				break;
			case "#8":
				table8B.setBackground(Color.BLUE);
				table8A.setText(outputStr);
				table8F.setText(priceStr);
				break;
			}
			
			dispose();
		}
	}
	
	private class AddOrderWindow extends OrderWindow implements Serializable{
		
		public AddOrderWindow() {
			super();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int totalPrice = 0;
			String outputStr = "";
			
			for(int i=0; i<subTable.getRowCount(); i++) {
				String menuStr = (String)subTable.getValueAt(i, 0);
				Integer menuInt = (Integer)subTable.getValueAt(i, 1);
				int menuNum = menuInt;
				
				int menuPrice = 0;
				for(int j=0; j<100; j++)
					if(menuStr.equals(MenuPanel.struct[j].name)) {
						menuPrice = MenuPanel.struct[j].price;
						break;
					}
				outputStr += (menuStr + " x" + menuNum + "\n");
				totalPrice += menuNum * menuPrice;
			}
			
			//String priceStr = String.valueOf(totalPrice);
			int originPri;
			
			switch(tableNum) {
			case "#1":
				table1A.append(outputStr);
				originPri = Integer.parseInt(table1F.getText());
				originPri += totalPrice;
				table1F.setText(String.valueOf(originPri));
				break;
			case "#2":
				table2A.append(outputStr);
				originPri = Integer.parseInt(table2F.getText());
				originPri += totalPrice;
				table2F.setText(String.valueOf(originPri));
				break;
			case "#3":
				table3A.append(outputStr);
				originPri = Integer.parseInt(table3F.getText());
				originPri += totalPrice;
				table3F.setText(String.valueOf(originPri));
				break;
			case "#4":
				table4A.append(outputStr);
				originPri = Integer.parseInt(table4F.getText());
				originPri += totalPrice;
				table4F.setText(String.valueOf(originPri));
				break;
			case "#5":
				table5A.append(outputStr);
				originPri = Integer.parseInt(table5F.getText());
				originPri += totalPrice;
				table5F.setText(String.valueOf(originPri));
				break;
			case "#6":
				table6A.append(outputStr);
				originPri = Integer.parseInt(table6F.getText());
				originPri += totalPrice;
				table6F.setText(String.valueOf(originPri));
				break;
			case "#7":
				table7A.append(outputStr);
				originPri = Integer.parseInt(table7F.getText());
				originPri += totalPrice;
				table7F.setText(String.valueOf(originPri));
				break;
			case "#8":
				table8A.append(outputStr);
				originPri = Integer.parseInt(table8F.getText());
				originPri += totalPrice;
				table8F.setText(String.valueOf(originPri));
				break;
			}
			
			dispose();
		}
	}
	
	private class PayWindow extends JFrame implements ActionListener, Serializable{
		private double total;
		private String gradeStr = "";
		private int guestIndex;
		private double tempTotal;
		
		public PayWindow() {
			super("결제 창");
			setSize(SMALL_WIDTH, SMALL_HEIGHT);
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(null);
			JLabel totalLabel = new JLabel("총 금 액");
			topPanel.add(totalLabel);
			JTextField totalField = new JTextField(10);
			topPanel.add(totalField);
			switch(tableNum) {
			case "#1":
				totalField.setText(table1F.getText());
				total = Double.parseDouble(table1F.getText());
				break;
			case "#2":
				totalField.setText(table2F.getText());
				total = Double.parseDouble(table2F.getText());
				break;
			case "#3":
				totalField.setText(table3F.getText());
				total = Double.parseDouble(table3F.getText());
				break;
			case "#4":
				totalField.setText(table4F.getText());
				total = Double.parseDouble(table4F.getText());
				break;
			case "#5":
				totalField.setText(table5F.getText());
				total = Double.parseDouble(table5F.getText());
				break;
			case "#6":
				totalField.setText(table6F.getText());
				total = Double.parseDouble(table6F.getText());
				break;
			case "#7":
				totalField.setText(table7F.getText());
				total = Double.parseDouble(table7F.getText());
				break;
			case "#8":
				totalField.setText(table8F.getText());
				total = Double.parseDouble(table8F.getText());
				break;
			}
			JLabel discountLabel = new JLabel("할인금액");
			topPanel.add(discountLabel);
			JTextField discountField = new JTextField("0");
			topPanel.add(discountField);
			JLabel payLabel = new JLabel("받을금액");
			topPanel.add(payLabel);
			JTextField payField = new JTextField(String.valueOf(total));
			topPanel.add(payField);
			
			totalLabel.setBounds(250, 70, 100, 20);
			discountLabel.setBounds(250, 120, 100, 20);
			payLabel.setBounds(250, 170, 100, 20);
			totalField.setBounds(350, 70, 100, 20);
			discountField.setBounds(350, 120, 100, 20);
			payField.setBounds(350, 170, 100, 20);
			add(topPanel, BorderLayout.CENTER);
			
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new FlowLayout());
			JLabel guestLabel = new JLabel("회원");
			bottomPanel.add(guestLabel);
			guestBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JComboBox box = (JComboBox)e.getSource();
					Object item = box.getSelectedItem();
					
					if(item.equals("선택안함"))
						return;
						
					for(int i=0; i<100; i++)
						if(GuestPanel.struct[i] != null && item.equals(GuestPanel.struct[i].name)) {
							gradeStr = GuestPanel.struct[i].grade;
							guestIndex = i;
							break;
						}
					
					double discount;
					if(gradeStr.equals("일반"))
						discount = total * 0.02;
					else if(gradeStr.equals("골드"))
						discount = total * 0.05;
					else
						discount = total * 0.10;
					
					discountField.setText(String.valueOf(discount));
					
					tempTotal = total;
					total = total - discount;
					payField.setText(String.valueOf(total));
				}
			});
			bottomPanel.add(guestBox);
			JButton confirmBtn = new JButton("확인");
			confirmBtn.setFont(font);
			confirmBtn.setBackground(Color.WHITE);
			confirmBtn.addActionListener(this);
			bottomPanel.add(confirmBtn);
			JButton cancelBtn = new JButton("취소");
			cancelBtn.setFont(font);
			cancelBtn.setBackground(Color.WHITE);
			cancelBtn.addActionListener(this);
			bottomPanel.add(cancelBtn);
			add(bottomPanel, BorderLayout.SOUTH);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String actionCmd = e.getActionCommand();
			if(actionCmd.equals("취소")) {
				dispose();
				return;
			}
			
			Sushi.sales += total;
			Sushi.moneyStr = "오늘 매출 : " + Sushi.sales + "원 전체 잔고 : " + Sushi.balance + "원";
			Sushi.moneyLabel.setText(Sushi.moneyStr);
			
			if(guestBox.getSelectedIndex() != 0) {
				double mileage = (tempTotal * 0.02);
				
				int originMileage = GuestPanel.struct[guestIndex].mileage;
				originMileage += mileage;
				if(originMileage > 1000) {
					GuestPanel.struct[guestIndex].grade = "플래티넘";
				}else if(originMileage <= 1000 && originMileage > 500) {
					GuestPanel.struct[guestIndex].grade = "골드";
				}else
					GuestPanel.struct[guestIndex].grade = "일반";
				
				GuestPanel.struct[guestIndex].mileage = originMileage;
				
				String guestName;
				int row = 0;
				for(int i=0; i<GuestPanel.guestTable.getRowCount(); i++) {
					guestName = (String)GuestPanel.guestTable.getValueAt(i, 2);
					if(guestName.equals(GuestPanel.struct[guestIndex].name)) {
						row = i;
						break;
					}	
				}
				
				GuestPanel.guestTable.setValueAt(GuestPanel.struct[guestIndex].grade, row, 1);
				GuestPanel.guestTable.setValueAt(GuestPanel.struct[guestIndex].mileage, row, 3);
			}
			
			switch(tableNum) {
			case "#1":
				table1B.setBackground(Color.WHITE);
				table1A.setText("");
				table1F.setText("");
				break;
			case "#2":
				table2B.setBackground(Color.WHITE);
				table2A.setText("");
				table2F.setText("");
				break;
			case "#3":
				table3B.setBackground(Color.WHITE);
				table3A.setText("");
				table3F.setText("");
				break;
			case "#4":
				table4B.setBackground(Color.WHITE);
				table4A.setText("");
				table4F.setText("");
				break;
			case "#5":
				table5B.setBackground(Color.WHITE);
				table5A.setText("");
				table5F.setText("");
				break;
			case "#6":
				table6B.setBackground(Color.WHITE);
				table6A.setText("");
				table6F.setText("");
				break;
			case "#7":
				table7B.setBackground(Color.WHITE);
				table7A.setText("");
				table7F.setText("");
				break;
			case "#8":
				table8B.setBackground(Color.WHITE);
				table8A.setText("");
				table8F.setText("");
				break;
			}
			
			dispose();
		}
	}

}
