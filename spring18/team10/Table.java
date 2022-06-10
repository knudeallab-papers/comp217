import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


//전체 크기 가로1000 세로800 
public class Table extends JFrame implements ActionListener {

	JPanel tables = new JPanel();
	
	JPanel tableInfo = new JPanel();

	JButton[] buttons = new JButton[8];
	
//	private itemList iList = new itemList();
//	private totalSalesList totalSalesList = new totalSalesList();
//	private order order = new order();
	
	JButton menuBtns[] = new JButton[25];
	String[] temp = new String[25];
	
	public Table()
	{
		int i =0;
				
		this.setLayout(null);

		tables.setLayout(new GridLayout(2, 4, 30, 30));
		
		for (i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton("Table" + i+1);
			buttons[i].setText("Table"+(i+1));
			buttons[i].addActionListener(this);
			/*
			 *button[0].setBackground(Color.BLUE); 
			 * */
			

			tables.add(buttons[i]);
		}
		
//		rightSide();
		
		Insets insets = this.getInsets();

		this.add(tables);
		tables.setSize(500,500);
		tables.setBounds(insets.left, insets.top, 500,500);
		
		this.add(tableInfo);
		tableInfo.setSize(300,700);
		tableInfo.setBounds(insets.left+700, insets.top ,300,700);
		
		
		pack();
		
	}

	
//	public void rightSide()
//	{
//		Insets insets = this.getInsets();
//
//		//width: 300 height:700
//		JPanel up = new JPanel();
//		JPanel down = new JPanel();
//		
//		up.setLayout(new BorderLayout());
//						
//		JLabel menuText = new JLabel();
//		menuText.setText("메뉴");
//		menuText.setFont(new Font("SansSerif", Font.BOLD, 30));
//		menuText.setBounds(insets.left+700, insets.top, 150,100);
//		
//		JLabel priceText = new JLabel();
//		priceText.setText("가격");
//		priceText.setFont(new Font("SansSerif", Font.BOLD, 30));
//		priceText.setBounds(insets.left+850, insets.top, 150,100);
//
//		
//		this.add(menuText);
//		this.add(priceText);
//		
//		JPanel up_down = new JPanel();
//		
//		up_down.setLayout(new GridLayout(10,2));
//		
//	}
	
	JFrame menuPanel = new JFrame();
	
	//25 is max size

	
//	public void makeMenuPanel(itemList input)
//	{
//		int i;
//		
//		menuPanel.setLayout(new GridLayout(5,5));
//
//		for (i = 0; i <iList.items.size() + 10; i++)
//		{
//			menuBtns[i] = new JButton();
//			menuBtns[i].setText(temp[i]);
//			menuBtns[i].addActionListener(this);
//
//			menuPanel.add(menuBtns[i]);
//		}
//		
//		pack();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String actionCommand = e.getActionCommand();

		System.out.println(actionCommand);
		
		String firstActionCommand = null;
		
		order[] temp = new order[8];

		
		if(actionCommand.equals("Table" + 1))
		{
			firstActionCommand = "Table1";
			
			buttons[0].setForeground(Color.BLACK);
			buttons[1].setForeground(Color.BLACK);
			buttons[2].setForeground(Color.BLACK);
			buttons[3].setForeground(Color.BLACK);
			buttons[4].setForeground(Color.BLACK);
			buttons[5].setForeground(Color.BLACK);
			buttons[6].setForeground(Color.BLACK);
			buttons[7].setForeground(Color.BLACK);
			buttons[0].setForeground(Color.BLUE);
			
			temp[1] = new order();
						
//			int row = itemListTable.getSelectedRow();
//
//			buttons[1].setText(temp[1].getOrderItemList().get(row).getOrderItem().getPrice()*temp.getOrderItemNum(row)) + "");
			
		}
		else if(actionCommand.equals("Table" + 2))
		{
			firstActionCommand = "Table2";
			
			buttons[0].setForeground(Color.BLACK);
			buttons[1].setForeground(Color.BLACK);
			buttons[2].setForeground(Color.BLACK);
			buttons[3].setForeground(Color.BLACK);
			buttons[4].setForeground(Color.BLACK);
			buttons[5].setForeground(Color.BLACK);
			buttons[6].setForeground(Color.BLACK);
			buttons[7].setForeground(Color.BLACK);
			buttons[1].setForeground(Color.BLUE);
		}
		else if(actionCommand.equals("Table" + 3))
		{
			firstActionCommand = "Table3";
			
			buttons[0].setForeground(Color.BLACK);
			buttons[1].setForeground(Color.BLACK);
			buttons[2].setForeground(Color.BLACK);
			buttons[3].setForeground(Color.BLACK);
			buttons[4].setForeground(Color.BLACK);
			buttons[5].setForeground(Color.BLACK);
			buttons[6].setForeground(Color.BLACK);
			buttons[7].setForeground(Color.BLACK);
			buttons[2].setForeground(Color.BLUE);
		}
		else if(actionCommand.equals("Table" + 4))
		{
			firstActionCommand = "Table4";
			
			buttons[0].setForeground(Color.BLACK);
			buttons[1].setForeground(Color.BLACK);
			buttons[2].setForeground(Color.BLACK);
			buttons[3].setForeground(Color.BLACK);
			buttons[4].setForeground(Color.BLACK);
			buttons[5].setForeground(Color.BLACK);
			buttons[6].setForeground(Color.BLACK);
			buttons[7].setForeground(Color.BLACK);
			buttons[3].setForeground(Color.BLUE);
		}
		else if(actionCommand.equals("Table" + 5))
		{
			firstActionCommand = "Table5";
			
			buttons[0].setForeground(Color.BLACK);
			buttons[1].setForeground(Color.BLACK);
			buttons[2].setForeground(Color.BLACK);
			buttons[3].setForeground(Color.BLACK);
			buttons[4].setForeground(Color.BLACK);
			buttons[5].setForeground(Color.BLACK);
			buttons[6].setForeground(Color.BLACK);
			buttons[7].setForeground(Color.BLACK);
			buttons[4].setForeground(Color.BLUE);
		}
		else if(actionCommand.equals("Table" + 6))
		{
			firstActionCommand = "Table6";

			buttons[0].setForeground(Color.BLACK);
			buttons[1].setForeground(Color.BLACK);
			buttons[2].setForeground(Color.BLACK);
			buttons[3].setForeground(Color.BLACK);
			buttons[4].setForeground(Color.BLACK);
			buttons[5].setForeground(Color.BLACK);
			buttons[6].setForeground(Color.BLACK);
			buttons[7].setForeground(Color.BLACK);
			buttons[5].setForeground(Color.BLUE);
		}
		else if(actionCommand.equals("Table" + 7))
		{
			firstActionCommand = "Table7";
			
			buttons[0].setForeground(Color.BLACK);
			buttons[1].setForeground(Color.BLACK);
			buttons[2].setForeground(Color.BLACK);
			buttons[3].setForeground(Color.BLACK);
			buttons[4].setForeground(Color.BLACK);
			buttons[5].setForeground(Color.BLACK);
			buttons[6].setForeground(Color.BLACK);
			buttons[7].setForeground(Color.BLACK);
			buttons[6].setForeground(Color.BLUE);
		}
		else if(actionCommand.equals("Table" + 8))
		{
			firstActionCommand = "Table8";
			
			buttons[0].setForeground(Color.BLACK);
			buttons[1].setForeground(Color.BLACK);
			buttons[2].setForeground(Color.BLACK);
			buttons[3].setForeground(Color.BLACK);
			buttons[4].setForeground(Color.BLACK);
			buttons[5].setForeground(Color.BLACK);
			buttons[6].setForeground(Color.BLACK);
			buttons[7].setForeground(Color.BLACK);
			buttons[7].setForeground(Color.BLUE);
		}

	}


	
}
