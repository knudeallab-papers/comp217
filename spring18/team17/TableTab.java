package javaPr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class TableTab extends JPanel implements ActionListener{
	JPanel tablePanel = new JPanel();
	static JPanel listPanel = new JPanel();
	JPanel lowerList = new JPanel();
	JButton listAdd = new JButton("추가");
	JButton listPay = new JButton("결제");
	static DefaultTableModel listTableModel;
	static String tableList[][][] = new String[10][2][20];
	
	static int[] tableListCount = new int[10];
	
	static JTable list;
	static JScrollPane scrollPane;
	static String header[] = {"메뉴","가격"};
	String contents[][] = {{"",""}};
	
	public int nowSelected= 0;
	
	static JToggleButton table[] = new JToggleButton[10];
	static boolean isTableOn[] = new boolean[10];
	ButtonGroup buttonG = new ButtonGroup();
	JLabel tableName[] = new JLabel[10];
	static JLabel tableMoneyLabel[] = new JLabel[10];
	static int tableMoney[] = new int[10];
	
	public TableTab() {
		setLayout(new BorderLayout());
		Arrays.fill(isTableOn, false);
		Arrays.fill(tableMoney, 0);
		Arrays.fill(tableListCount, 0);
		listPanel.setLayout(new BorderLayout());
		listPanel.setPreferredSize(new Dimension(300, HEIGHT));
		tablePanel.setLayout(new GridLayout(2,5));
		tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		lowerList.setLayout(new FlowLayout());
		
		listAdd.setActionCommand("listAdd");
		listPay.setActionCommand("listPay");
		listAdd.addActionListener(this);
		listPay.addActionListener(this);
		lowerList.add(listAdd);
		lowerList.add(listPay);
		listPanel.add(lowerList,BorderLayout.SOUTH);
		
		listPanel.setBackground(Color.WHITE);
		tablePanel.setBackground(Color.GRAY);
		
		listTableModel  = new DefaultTableModel(contents , header) {
			public boolean isCellEditable(int i, int c)
			{
				return false;
			}
		};
		listTableModel.setRowCount(0);
		list = new JTable(listTableModel);
		scrollPane = new JScrollPane(list);
		listPanel.add(scrollPane,BorderLayout.CENTER);
		
		for(int i=0;i<10;i++)
		{
			table[i] = new JToggleButton("테이블 "+(i+1));
			table[i].setText("");
			table[i].setActionCommand(Integer.toString(i+1));
			table[i].addActionListener(this);
			table[i].setBackground(Color.white);
			table[i].setLayout(new GridLayout(2,1));
			tableName[i] = new JLabel();
			tableName[i].setText("테이블 "+(i+1));
			tableName[i].setHorizontalAlignment(JLabel.CENTER);
			tableMoneyLabel[i] = new JLabel();
			tableMoneyLabel[i].setText("");
			tableMoneyLabel[i].setHorizontalAlignment(JLabel.CENTER);

			table[i].add(tableName[i]);
			table[i].add(tableMoneyLabel[i]);
			table[i].setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
			buttonG.add(table[i]);
			tablePanel.add(table[i]);
		}
		add(tablePanel,BorderLayout.CENTER);
		add(listPanel,BorderLayout.EAST);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="listAdd")
		{
			if(buttonG.isSelected(null))
			{
				JOptionPane.showMessageDialog(this, "테이블이 선택되지 않았습니다", "주문추가", JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				new AddOrder(nowSelected);
			}
		}
		else if(e.getActionCommand() == "listPay")
		{
			if(buttonG.isSelected(null))
			{
				JOptionPane.showMessageDialog(this, "테이블이 선택되지 않았습니다", "결제", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(isTableOn[nowSelected] == false)
				{
					JOptionPane.showMessageDialog(this, "테이블에 주문이 없습니다", "결제", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					new PayTable(nowSelected);
				}
			}
		}
		else //table Button
		{
			listTableModel.setRowCount(0);
			int index = Integer.parseInt(e.getActionCommand())-1;
			nowSelected = index;
			if(isTableOn[index])
			{
				for(int i=0;i<tableListCount[nowSelected];i++)
				{
					String[] input = {tableList[nowSelected][0][i],tableList[nowSelected][1][i]};
					listTableModel.addRow(input);
				}
			}
		}
	}
	
	public static void setTableMoney(int tableNum)
	{
		int tempMoney = 0;
		
		for(int i=0; i<tableListCount[tableNum];i++)
		{
			tempMoney += Integer.parseInt(tableList[tableNum][1][i]);
		}
		tableMoney[tableNum]=tempMoney;
		tableMoneyLabel[tableNum].setText("총액 : "+tempMoney);
	}

}

class AddOrder extends JFrame implements ActionListener{

	JButton addB = new JButton("주문추가");
	JButton cancelB = new JButton("취소");
	JPanel buttonP = new JPanel();
	JList foodL;
	JScrollPane scroll;
	int nowSelected;
	public AddOrder(int nowSelected)
	{
		super("주문추가");
		this.nowSelected = nowSelected;
		setResizable(false);
		setLayout(new BorderLayout());
		setBounds(1000,200,400,600);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
		
		addB.addActionListener(this);
		cancelB.addActionListener(this);
		addB.setActionCommand("add");
		cancelB.setActionCommand("cancel");
		buttonP.setLayout(new FlowLayout());
		buttonP.add(addB);
		buttonP.add(cancelB);
		
		foodL = new JList(FoodTab.foodList[0]);
		scroll = new JScrollPane(foodL);
		
		add(scroll,BorderLayout.CENTER);
		add(buttonP,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "add")
		{
			int menuN = foodL.getSelectedIndex();
			int cost = Integer.parseInt(FoodTab.foodList[1][menuN]);
			String input[] = {FoodTab.foodList[0][menuN],FoodTab.foodList[1][menuN]};
			if(TableTab.isTableOn[nowSelected])
			{
				TableTab.tableList[nowSelected][0][TableTab.tableListCount[nowSelected]] = input[0];
				TableTab.tableList[nowSelected][1][TableTab.tableListCount[nowSelected]++] = input[1];
			}
			else
			{
				TableTab.tableList[nowSelected][0][TableTab.tableListCount[nowSelected]] = input[0];
				TableTab.tableList[nowSelected][1][TableTab.tableListCount[nowSelected]++] = input[1];
				TableTab.isTableOn[nowSelected] = true;
				TableTab.table[nowSelected].setBackground(Color.CYAN);
			}

			TableTab.listTableModel.addRow(input);
			TableTab.setTableMoney(nowSelected);
			dispose();
		}
		else if(e.getActionCommand() == "cancel")
		{
			dispose();
		}
	}
	
}

class PayTable extends JFrame implements ActionListener, ItemListener, MouseListener{

	int nowSelected = 0;
	static int price = 0;
	JButton pay = new JButton("결제");
	JButton cancel = new JButton("취소");
	JPanel upperButtonP = new JPanel();
	JPanel buttonP = new JPanel();
	JPanel selectP = new JPanel();
	
	static JLabel totalPrice = new JLabel(" ");
	
	JRadioButton isMember = new JRadioButton("회원");
	JRadioButton isNoneMember = new JRadioButton("비회원");
	ButtonGroup bGroup = new ButtonGroup();
	
	static JTable memberTable;
	DefaultTableModel tableModel;
	JScrollPane scroll;
	
	String[] header = {"이름","번호"};
	@SuppressWarnings("deprecation")
	public PayTable(int nowSelected)
	{
		super("결제");
		this.nowSelected = nowSelected;
		setResizable(false);
		setLayout(new BorderLayout());
		setBounds(1000,200,400,600);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
		selectP.setLayout(new BorderLayout());
		
		pay.setActionCommand("pay");
		pay.addActionListener(this);
		buttonP.add(pay);
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		buttonP.add(cancel);
		add(buttonP, BorderLayout.SOUTH);
		
		bGroup.add(isMember);
		bGroup.add(isNoneMember);
		isNoneMember.setSelected(true);
		
		isMember.addItemListener(this);
		isNoneMember.addItemListener(this);

		tableModel = new DefaultTableModel(header,0){
			public boolean isCellEditable(int i, int c)
			{
				return false;
			}
		};
		memberTable = new JTable(tableModel);
		memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		memberTable.addMouseListener(this);
		scroll = new JScrollPane(memberTable);
		
		upperButtonP.add(isMember);
		upperButtonP.add(isNoneMember);
		
		
		for(int i=0;i<MemberTab.getMemberNum();i++)
		{
			String[] input = {(String)MemberTab.memberTable.getValueAt(i, 2),(String)MemberTab.memberTable.getValueAt(i, 0)};
			tableModel.addRow(input);
		}
		
		totalPrice.setHorizontalAlignment(JTextField.CENTER);
		price = TableTab.tableMoney[nowSelected];
		totalPrice.setText("금액 : "+price+"원");
		selectP.add(upperButtonP,BorderLayout.NORTH);
		selectP.add(scroll, BorderLayout.CENTER);
		selectP.add(totalPrice, BorderLayout.SOUTH);
		
		add(selectP,BorderLayout.CENTER);
		
		memberTable.disable();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "pay")
		{
			int tempCost = 0;
			for(int i=0;i<TableTab.tableListCount[nowSelected];i++)
			{
				tempCost += Integer.parseInt(TableTab.tableList[nowSelected][1][i]);
				TableTab.tableList[nowSelected][0][i] = null;
				TableTab.tableList[nowSelected][1][i] = null;
			}
			if(PayTable.memberTable.isEnabled()) {
				if(PayTable.memberTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(this, "회원이 선택되지 않았습니다\n비회원으로 결제합니다", "결제", JOptionPane.ERROR_MESSAGE);
				}
				else {
					int row = PayTable.memberTable.getSelectedRow();
					int realCost = tempCost;
					String rate = (String) MemberTab.memberTable.getValueAt(row, 1);
					switch(rate)
					{
					case "NORMAL":
						tempCost = (int)(tempCost*0.98);
						break;
					case "GOLD":
						tempCost = (int)(tempCost*0.95);
						break;
					case "PLATINUM":
						tempCost = (int)(tempCost*0.9);
						break;
						default:
					}
					MemberTab.addMileage(realCost, row);
				}
			}
			TableTab.tableListCount[nowSelected] = 0;
			MainFrame.todaysMoney += tempCost;
			MainFrame.setMoney();
			TableTab.listTableModel.setRowCount(0);//need update
			TableTab.table[nowSelected].setBackground(Color.white);
			TableTab.isTableOn[nowSelected] = false;
			TableTab.tableMoneyLabel[nowSelected].setText("");
			dispose();
		}
		else if(arg0.getActionCommand() == "cancel")
		{
			dispose();
		}
	}
	@SuppressWarnings("deprecation")
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(isMember.isSelected())
		{
			memberTable.enable();
		}
		else if(isNoneMember.isSelected())
		{
			memberTable.disable();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(PayTable.memberTable.isEnabled()) {
			int row = PayTable.memberTable.getSelectedRow();
			String rate = (String) MemberTab.memberTable.getValueAt(row, 1);
			switch(rate)
			{
			case "NORMAL":
				PayTable.totalPrice.setText("금액 : "+(int)(PayTable.price*0.98)+"원");
				break;
			case "GOLD":
				PayTable.totalPrice.setText("금액 : "+(int)(PayTable.price*0.95)+"원");
				break;
			case "PLATINUM":
				PayTable.totalPrice.setText("금액 : "+(int)(PayTable.price*0.9)+"원");
				break;
			default:
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}