import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;


public class guidemo extends JFrame implements ActionListener{
	public static Members[] MemberAry = new Members[10];
	public static Table[] TableAry = new Table[8];
	public static Menu[] MenuAry = new Menu[10];
	public static Asset[] AssetAry = new Asset[10];
	
	
	public static int EntireMoney = 0; //총 수익 
	public static int TableNow;
	
	public int WIDTH = 1040;
	public int HEIGHT = 860;
	
	public static final String tapBtn1 = "테이블";
	public static final String tapBtn2 = "회원";
	public static final String tapBtn3 = "메뉴";

	
	public static JPanel tapPanel;
	public static JPanel contentPanel;
	
	public static JPanel tablePanel;
	public static JPanel tableInfoPanel;
	public static JPanel tableInfoPanel_1;
	public static JPanel tableInfoPanel_2;
	
	public static JButton[] tableSection = new JButton[8];
	public static JButton tableAdd;
	public static JButton tableEnd;
	public static JButton tableconfirm;
	
	public static JLabel[] tableInfoLabel = new JLabel[11];
	
	public static JTextField textfield1;
	
	public static void main(String[] args) {
		guidemo gui = new guidemo();
		gui.setVisible(true);	
	}

	public guidemo() 
	{
		super("Table Demo");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set Tap GUI
		tapPanel = new JPanel();
		tapPanel.setLayout(new FlowLayout());
		
		JButton tapTable = new JButton(tapBtn1);
		tapTable.addActionListener(this);
		tapTable.setLocation(10, 10);
		tapTable.setPreferredSize(new Dimension(120, 45));
		tapPanel.add(tapTable);
		
		JButton tapMember = new JButton(tapBtn2);
		tapMember.addActionListener(this);
		tapMember.setPreferredSize(new Dimension(120, 45));
		tapPanel.add(tapMember);
		
		JButton tapMenu = new JButton(tapBtn3);
		tapMenu.addActionListener(this);
		tapMenu.setPreferredSize(new Dimension(120, 45));
		tapPanel.add(tapMenu);
		
		tapPanel.setSize(WIDTH, HEIGHT/5);
		getContentPane().add(tapPanel, BorderLayout.NORTH);
		
		//Set Content Section
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.white);
		
		tapPanel.setSize(WIDTH, HEIGHT*4/5);
		
		JButton tapAsset = new JButton("창고");
		tapAsset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tapPanel.add(tapAsset);
		
		JLabel label = new JLabel("오늘 날짜: ");
		tapPanel.add(label);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton = new JButton("New button");
		contentPanel.add(btnNewButton);
		
		List list = new List();
		contentPanel.add(list);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		System.out.println("recieved ActionEvent " + action);
		
		if(action.equals(tapBtn1))
		{
			TableUI();
			repaint();
		}
		else if(action.equals("테이블1"))
		{
			tableInfo(0);
		}
		else if(action.equals("테이블2"))
		{
			tableInfo(1);
		}
		else if(action.equals("테이블3"))
		{
			tableInfo(2);
		}
		else if(action.equals("테이블4"))
		{
			tableInfo(3);
		}
		else if(action.equals("테이블5"))
		{
			tableInfo(4);
		}
		else if(action.equals("테이블6"))
		{
			tableInfo(5);
		}
		else if(action.equals("테이블7"))
		{
			tableInfo(6);
		}
		else if(action.equals("테이블8"))
		{
			tableInfo(7);
		}
		
	}
	
	public void TableUI() {
		//set table section
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(2, 4));
		tablePanel.setBackground(Color.lightGray);
		System.out.println("tablePanel works well!");
				
		for(int i = 0; i<8;i++)
		{
			if(TableAry[i] != null)
			{
				drawTable(TableAry[i], i);
			}
			else
			{
				drawTable(i);
			}
		}
		tablePanel.setSize(WIDTH*2/3, HEIGHT*4/5);
		contentPanel.add(tablePanel, BorderLayout.CENTER);
		
		
		tableInfoPanel = new JPanel();
		tableInfoPanel.setLayout(new BorderLayout());
		tableInfoPanel.setBackground(Color.orange);
		
		tableInfoPanel.setSize(WIDTH/3, HEIGHT*4/5);
		contentPanel.add(tableInfoPanel, BorderLayout.EAST);
	
		JLabel ldemo = new JLabel();
		ldemo.setText("                                                테이블 정보                                          ");
		tableInfoPanel.add(ldemo, BorderLayout.NORTH);
		
		setVisible(true);
	}
	
	public void drawTable(Table item, int i)
	{
		String str = "table" + (i+1);
		tableSection[i] = new JButton(str);
		tableSection[i].addActionListener(this);
		tableSection[i].setPreferredSize(new Dimension(120, 120));
		tableSection[i].setText("테이블"+(i+1));
		tableSection[i].setBackground(Color.cyan);
		tableSection[i].setText("총액: " + item.getPrice());
		tableSection[i].setOpaque(true);
		tableSection[i].setContentAreaFilled(true);
		tableSection[i].setBorderPainted(true);

		
		tablePanel.add(tableSection[i]);
	}
	public void drawTable(int i)
	{
		tableSection[i] = new JButton();
		tableSection[i].addActionListener(this);
		tableSection[i].setPreferredSize(new Dimension(120, 120));
		tableSection[i].setText("테이블"+(i+1));
		tableSection[i].setBackground(Color.white);
		tableSection[i].setOpaque(true);
		tableSection[i].setContentAreaFilled(true);
		tableSection[i].setBorderPainted(true);


		tablePanel.add(tableSection[i]);
	}

	public void tableInfo(int tablenum)
	{
		System.out.println("Do tableInfo. argument is " + tablenum);
		TableNow = tablenum;
		
		for(int i = 0; i<8;i++)
		{
			if(i == tablenum)
			{
				tableSection[tablenum].setBackground(Color.BLUE);

			}
			else if(TableAry[i] == null)
			{
				tableSection[i].setBackground(Color.white);
			}
			else
			{
				tableSection[i].setBackground(Color.CYAN);
			}
		}

		tableInfoPanel_1 = new JPanel();
		tableInfoPanel_1.setLayout(new GridLayout(10, 2));
		tableInfoPanel_1.setBackground(Color.white);
		
		
		tableInfoLabel[0] = new JLabel();
		tableInfoLabel[0].setText("메뉴           가격");
		tableInfoPanel_1.add(tableInfoLabel[0]);
		
		if(TableAry[tablenum]!=null)
		{
			String order = TableAry[tablenum].getOrder();
			StringTokenizer wordfactory = new StringTokenizer(order, ",");
			String str;
			
			for(int i = 2; i<TableAry[tablenum].ordered+2;i++)
			{
				tableInfoLabel[i] = new JLabel();
				str = wordfactory.nextToken();
				tableInfoLabel[i].setText( str+ "          " + Menu.searchMenu(str).getPrice());
				tableInfoPanel_1.add(tableInfoLabel[i]);
			}
		}
		
		
		tableInfoPanel.add(tableInfoPanel_1, BorderLayout.CENTER);
		
		
		tableInfoPanel_2 = new JPanel();
		tableInfoPanel_2.setLayout(new FlowLayout());
		tableInfoPanel_2.setBackground(Color.yellow);
		
		tableAdd = new JButton();
		tableAdd.setText("추가");
		tableAdd.setPreferredSize(new Dimension(120, 45));
		tableAdd.addActionListener(this);
		tableInfoPanel_2.add(tableAdd);
		
		tableEnd = new JButton();
		tableEnd.setText("결제");
		tableEnd.setPreferredSize(new Dimension(120, 45));
		try {
			tableEnd.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					tableInfoPanel_2 = new JPanel();
					tableInfoPanel_2.setLayout(new FlowLayout());
					tableInfoPanel_2.setBackground(Color.gray);
					
					textfield1 = new JTextField(10);
					tableInfoPanel_2.add(textfield1);
					
					JLabel showprice = new JLabel();
					showprice.setText("총액 : " + TableAry[TableNow].getPrice());
					tableInfoPanel_2.add(showprice);
									
					JButton textconfirm = new JButton();
					textconfirm.setText("회원 할인 적용");
					textconfirm.setPreferredSize(new Dimension(120, 45));
					textconfirm.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							int membnum = 0;
							for(int i = 0; i<Members.membernum;i++)
							{
								if(MemberAry[i].getName().equals(textfield1.getText()))
								{
									membnum = i;
								}
							}
							TableAry[TableNow].ApplyMile(textfield1.getText());
							showprice.setText("총액 : " + TableAry[TableNow].getPrice() + "현재 마일리지: " + MemberAry[membnum].getMile());
							setVisible(true);
						}
					});
									
					tableconfirm = new JButton();
					tableconfirm.setText("확인");
					tableconfirm.setPreferredSize(new Dimension(120, 45));
					tableconfirm.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							EntireMoney += TableAry[TableNow].getPrice();
							TableAry[TableNow].TableEnd();
							
							tableInfo(TableNow);
						}
					});
				}
			});
		}catch(NullPointerException e)
		{
			System.err.println("You confirm order in empty table!!");
		}
		
		tableInfoPanel_2.add(tableEnd);

		
		tableInfoPanel.add(tableInfoPanel_2, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}
	
}
