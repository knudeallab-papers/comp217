package bk;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import bk.BurgerKing;

public class MainWindow extends JPanel implements ActionListener, MouseListener, Serializable {

	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;
	public static final int MAX_ING = 100;
	int trace = 0;
	
	private boolean click = false;
	
	private JButton table1;
	String menucost[][] = new String[10][4];
	int count=0;
	int countmember = 1;
	String inputStr[] = new String[5];
	String member[][] = new String[20][5];
	Choice choice;
	private String menuname[] = new String[10]; //메뉴이름배열 
	private int menupricearr[] = new int[10];
	int tabel1arr[] = new int[10];
	//
	String header[] = {"메뉴 ","가격 "};
	String cells[][] = {};
	
	DefaultTableModel menumodel = new DefaultTableModel(cells, header);
	DefaultTableModel menumodel2 = new DefaultTableModel(cells, header);
	JTable tabl = new JTable(menumodel);
	JScrollPane js = new JScrollPane(tabl);

	//
	String tablemenuadd1[][] = new String[10][2];
	String tablemenuadd2[][] = new String[10][2];
	String tablemenuadd3[][] = new String[10][2];
	String tablemenuadd4[][] = new String[10][2];
	String tablemenuadd5[][] = new String[10][2];
	String tablemenuadd6[][] = new String[10][2];
	String tablemenuadd7[][] = new String[10][2];
	String tablemenuadd8[][] = new String[10][2];
	int tablenumber = 0;
	int tablecount1 =0 ;
	int tablecount2=0;
	int tablecount3=0;
	int tablecount4=0;
	int tablecount5=0;
	int tablecount6=0;
	int tablecount7=0;
	int tablecount8=0;
	int totalcost = 0; //테이블 총액 
	int tatalcost1 = 0;
	int totalcost2=0;
	int totalcost3=0;
	int totalcost4=0;
	int totalcost5=0;
	int totalcost6=0;
	int totalcost7=0;
	int totalcost8=0;
	 private int payment = 0;

	 
	 //chango order information
	 JLabel ingNameVal = new JLabel("");
		JLabel ingPriceVal = new JLabel("");
		JLabel ingSellsVal = new JLabel("");
		JLabel ingContactVal = new JLabel("");
		public static JLabel ingQuantityVal = new JLabel("");
		public static JLabel ingOrderAmountVal = new JLabel("");
		
		static String[] columnType = {"이름", "재고", "주문", "가격"};
		static Object[][] data = {
				{"빵", "2", "0" , "1000", "이마트" ,"053)123-456"},
				{"베이컨", "3", "0", "1000", "이마트" ,"053)123-456"},
				{"치즈", "5", "0", "500", "이마트" ,"053)123-456"},
				{"양상추", "5", "0", "100", "이마트" ,"053)123-456"},
				{"토마토", "3", "0", "500", "이마트" ,"053)123-456"},
				{"패티", "2", "0", "1000", "이마트" ,"053)123-456"},
				{"양파", "2", "0", "500" , "이마트" ,"053)123-456"},
				{"새우", "3", "0", "1000","이마트" ,"053)123-456"},
				{"치킨너겟", "10", "0", "1000","이마트" ,"053)123-456"}
		};
		static Object[][] dataInfo = new Object[MAX_ING][6];
	public static DefaultTableModel modelTbl = new DefaultTableModel(data, columnType);
	public static JTable t = new JTable(modelTbl);
	
	
	String input3[] = new String[2];

	 public void printTable(int tablecount, String[][] tablemenuadd, int totalcost)
	 {
	  for(int j=0; j<tablecount; j++)
	  {
	   if(tablemenuadd[j][0]==null && tablemenuadd[j][1]==null)
	    break;
	   
	    input3[0] = tablemenuadd[j][0];
	    input3[1] = tablemenuadd[j][1];
	    totalcost = totalcost + Integer.parseInt(tablemenuadd[j][1]);
	    menumodel.addRow(input3);
	  }
	 }
	 
	 public void tablelistset(int tablecount, String tablemenuadd[][])
	 {
	  for(int i=0; i<tablecount; i++)
	  {
	   tablemenuadd[i][0] = null;
	   tablemenuadd[i][1] = null;
	  }
	 }
	 
	 public int pay(int selectednum, int totalcost, int payment, int tablecount, String[][] tablemenuadd1,JButton table)
	 {
	  
	  if(selectednum == 0)
	   payment = totalcost-(totalcost/100*2);
	  else if(selectednum == 1)
	   payment = totalcost-(totalcost/100*5);
	  else if(selectednum == 2)
	   payment = totalcost-(totalcost/100*10);
	  else if(selectednum == 3)
	   payment = totalcost;
	  
	  tablelistset(tablecount,tablemenuadd1);
	  totalcost = 0;
	  table.setText("총액 : "+String.valueOf(totalcost));
	  
	  return payment;
	 }
	 
	public MainWindow() {
		super();
		super.setBackground(Color.ORANGE);
		//setSize(WIDTH, HEIGHT);

		JPanel main = new JPanel();
		main.setBackground(Color.ORANGE);
		//main.setLayout(new BorderLayout());
		//main.setSize(600,300);
		
		main.setBounds(0, 300, 600, 300);
		add(main);
		
		//table
		JPanel table = new JPanel();
		table.setPreferredSize(new Dimension(700,300));
		table.setBounds(0, 300, 600, 300);//x, y, width, height 위치와 크기 
		JPanel menu1 = new JPanel();
		menu1.setPreferredSize(new Dimension(180,300));
		 //테이블추가삭제가능하게하는메소드
		js.setPreferredSize(new Dimension(180,240));
		menu1.add(js);
		table.add(menu1,BorderLayout.EAST);
		JPanel tbl = new JPanel();
	      tbl.setPreferredSize(new Dimension(500,300));
	
	      table.add(tbl, BorderLayout.WEST);
	      table.add(menu1);
	      int cost = 0;
	      
	      table1 = new JButton("총액 : "+totalcost);
	      JButton table2 = new JButton("총액 : "+totalcost2);
	      JButton table3 = new JButton("총액 : "+totalcost3);
	      JButton table4 = new JButton("총액 : "+totalcost4);
	      JButton table5 = new JButton("총액 : "+totalcost5);
	      JButton table6 = new JButton("총액 : "+totalcost6);
	      JButton table7 = new JButton("총액 : "+totalcost7);
	      JButton table8 = new JButton("총액 : "+totalcost8);
	      table1.setPreferredSize(new Dimension(100,140));
	      table1.setBackground(Color.ORANGE);
	         table2.setBackground(Color.ORANGE);
	         table3.setBackground(Color.ORANGE);
	         table4.setBackground(Color.ORANGE);
	         table5.setBackground(Color.ORANGE);
	         table6.setBackground(Color.ORANGE);
	         table7.setBackground(Color.ORANGE);
	         table8.setBackground(Color.ORANGE);
	      tbl.add(table1);
	      table1.addActionListener(new ActionListener()
	    		  {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						menumodel.setNumRows(0);
						tablenumber = 1;
						totalcost=0;
						for(int j=0; j<tablecount1; j++)
						{
								input3[0] = tablemenuadd1[j][0];
								input3[1] = tablemenuadd1[j][1];
								totalcost = totalcost + Integer.parseInt(tablemenuadd1[j][1]);
								menumodel.addRow(input3);
						}
						table1.setText("총액 : "+String.valueOf(totalcost));
						System.out.println(tablenumber);
						
						table1.setBackground(Color.RED);
		                  table2.setBackground(Color.ORANGE);
		                  table3.setBackground(Color.ORANGE);
		                  table4.setBackground(Color.ORANGE);
		                  table5.setBackground(Color.ORANGE);
		                  table6.setBackground(Color.ORANGE);
		                  table7.setBackground(Color.ORANGE);
		                  table8.setBackground(Color.ORANGE);
					}
	    	  
	    		  });
	    
	      table2.setPreferredSize(new Dimension(100,140));
	      tbl.add(table2);
	      table2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tablenumber = 2;
				totalcost2=0;
				menumodel.setNumRows(0);
				for(int j=0; j<tablecount2; j++)
				{
						input3[0] = tablemenuadd2[j][0];
						input3[1] = tablemenuadd2[j][1];
						totalcost2 = totalcost2 + Integer.parseInt(tablemenuadd2[j][1]);
						menumodel.addRow(input3);
				}
				table2.setText("총액 : "+String.valueOf(totalcost2));
				//System.out.println(tablenumber);
				
				table1.setBackground(Color.ORANGE);
	            table2.setBackground(Color.RED);
	            table3.setBackground(Color.ORANGE);
	            table4.setBackground(Color.ORANGE);
	            table5.setBackground(Color.ORANGE);
	            table6.setBackground(Color.ORANGE);
	            table7.setBackground(Color.ORANGE);
	            table8.setBackground(Color.ORANGE);
			}
	      });
	      
	      table3.setPreferredSize(new Dimension(100,140));
	      tbl.add(table3);
	      table3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					tablenumber = 3;
					totalcost3=0;
					menumodel.setNumRows(0);
					for(int j=0; j<tablecount3; j++)
					{
							input3[0] = tablemenuadd3[j][0];
							input3[1] = tablemenuadd3[j][1];
							totalcost3 = totalcost3 + Integer.parseInt(tablemenuadd3[j][1]);
							menumodel.addRow(input3);
					}
					table3.setText("총액 : "+String.valueOf(totalcost3));
					//System.out.println(tablenumber);
					
					table1.setBackground(Color.ORANGE);
		               table2.setBackground(Color.ORANGE);
		               table3.setBackground(Color.RED);
		               table4.setBackground(Color.ORANGE);
		               table5.setBackground(Color.ORANGE);
		               table6.setBackground(Color.ORANGE);
		               table7.setBackground(Color.ORANGE);
		               table8.setBackground(Color.ORANGE);
				}
		      });
	 

	      table4.setPreferredSize(new Dimension(100,140));             
	      tbl.add(table4);
	      table4.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					tablenumber = 4;
					totalcost4=0;
					menumodel.setNumRows(0);
					for(int j=0; j<tablecount4; j++)
					{
							input3[0] = tablemenuadd4[j][0];
							input3[1] = tablemenuadd4[j][1];
							totalcost4 = totalcost4 + Integer.parseInt(tablemenuadd4[j][1]);
							menumodel.addRow(input3);
					}
					table4.setText("총액 : "+String.valueOf(totalcost4));
					//System.out.println(tablenumber);
					System.out.println(tablenumber);
					//System.out.println(tablenumber);
		               table1.setBackground(Color.ORANGE);
		               table2.setBackground(Color.ORANGE);
		               table3.setBackground(Color.ORANGE);
		               table4.setBackground(Color.RED);
		               table5.setBackground(Color.ORANGE);
		               table6.setBackground(Color.ORANGE);
		               table7.setBackground(Color.ORANGE);
		               table8.setBackground(Color.ORANGE);
				}
		      });
	      

	      table5.setPreferredSize(new Dimension(100,140));
	      tbl.add(table5);
	      table5.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					tablenumber = 5;
					totalcost5=0;
					menumodel.setNumRows(0);
					for(int j=0; j<tablecount5; j++)
					{
							input3[0] = tablemenuadd5[j][0];
							input3[1] = tablemenuadd5[j][1];
							totalcost5 = totalcost5 + Integer.parseInt(tablemenuadd5[j][1]);
							menumodel.addRow(input3);
					}
					table5.setText("총액 : "+String.valueOf(totalcost5));
					//System.out.println(tablenumber);
					System.out.println(tablenumber);
		               table1.setBackground(Color.ORANGE);
		               table2.setBackground(Color.ORANGE);
		               table3.setBackground(Color.ORANGE);
		               table4.setBackground(Color.ORANGE);
		               table5.setBackground(Color.RED);
		               table6.setBackground(Color.ORANGE);
		               table7.setBackground(Color.ORANGE);
		               table8.setBackground(Color.ORANGE);
				}
		      });

//	      JButton table6 = new JButton("테이블6\n\n 총액 : "+totalcost6);
	      table6.setPreferredSize(new Dimension(100,140));
	      tbl.add(table6);
	      table6.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					tablenumber = 6;
					totalcost6=0;
					menumodel.setNumRows(0);
					for(int j=0; j<tablecount6; j++)
					{
							input3[0] = tablemenuadd6[j][0];
							input3[1] = tablemenuadd6[j][1];
							totalcost6 = totalcost6 + Integer.parseInt(tablemenuadd6[j][1]);
							menumodel.addRow(input3);
					}
					table6.setText("총액 : "+String.valueOf(totalcost6));
					//System.out.println(tablenumber);
					System.out.println(tablenumber);
		               table1.setBackground(Color.ORANGE);
		               table2.setBackground(Color.ORANGE);
		               table3.setBackground(Color.ORANGE);
		               table4.setBackground(Color.ORANGE);
		               table5.setBackground(Color.ORANGE);
		               table6.setBackground(Color.RED);
		               table7.setBackground(Color.ORANGE);
		               table8.setBackground(Color.ORANGE);
				}
		      });
	  
//	      JButton table7 = new JButton("테이블7\n\n 총액 : "+totalcost7);
	      table7.setPreferredSize(new Dimension(100,140));
	      tbl.add(table7);
	      table7.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					tablenumber = 7;
					totalcost7=0;
					menumodel.setNumRows(0);
					for(int j=0; j<tablecount7; j++)
					{
							input3[0] = tablemenuadd7[j][0];
							input3[1] = tablemenuadd7[j][1];
							totalcost7 = totalcost7 + Integer.parseInt(tablemenuadd7[j][1]);
							menumodel.addRow(input3);
					}
					table7.setText("총액 : "+String.valueOf(totalcost7));
					//System.out.println(tablenumber);
					System.out.println(tablenumber);
		               table1.setBackground(Color.ORANGE);
		               table2.setBackground(Color.ORANGE);
		               table3.setBackground(Color.ORANGE);
		               table4.setBackground(Color.ORANGE);
		               table5.setBackground(Color.ORANGE);
		               table6.setBackground(Color.ORANGE);
		               table7.setBackground(Color.RED);
		               table8.setBackground(Color.ORANGE);
				}
		      });
	    
//	      JButton table8 = new JButton("테이블8\n\n 총액 : "+totalcost8);
	      table8.setPreferredSize(new Dimension(100,140));
	      tbl.add(table8);
	      table8.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					tablenumber = 8;
					totalcost8=0;
					menumodel.setNumRows(0);
					for(int j=0; j<tablecount8; j++)
					{
							input3[0] = tablemenuadd8[j][0];
							input3[1] = tablemenuadd8[j][1];
							totalcost8 = totalcost8 + Integer.parseInt(tablemenuadd8[j][1]);
							menumodel.addRow(input3);
					}
					table8.setText("총액 : "+String.valueOf(totalcost8));
					//System.out.println(tablenumber);
					System.out.println(tablenumber);
		               table1.setBackground(Color.ORANGE);
		               table2.setBackground(Color.ORANGE);
		               table3.setBackground(Color.ORANGE);
		               table4.setBackground(Color.ORANGE);
		               table5.setBackground(Color.ORANGE);
		               table6.setBackground(Color.ORANGE);
		               table7.setBackground(Color.ORANGE);
		               table8.setBackground(Color.RED);
		      }});
	      
	      table1.addActionListener(this);
	      ///////////////추가버튼누르면 
	      JButton add = new JButton("추가");
	      

	      add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s = (String)JOptionPane.showInputDialog(null, "새로운 주문을 추가해주세요!","주문추가 ", 
						JOptionPane.QUESTION_MESSAGE, null, menuname, menuname[0]);
				
				if(s == null)
				{
					System.out.println("취소 ");
					return;
				}
				else
				{
					//tablecount1가 1번에 등어이는 행수 
					//tablenubmer은 테이블 숫자 
					if(tablenumber == 1)
					{
						
						tablemenuadd1[tablecount1][0] = s; //오류 
						input3[0] = tablemenuadd1[tablecount1][0];
						//System.out.println(menuname[count-1]);
						
							for(int i=0;i<count;i++) // 담긴 메뉴이름 
							{
								if(s.equals(menuname[count-1])) { //메뉴이름과 ㅇ같은항의가격찾아서넣기 
									//System.out.println(menupricearr[count-1]);
									tablemenuadd1[tablecount1][1] = String.valueOf(menupricearr[count-1]);
									input3[1] = tablemenuadd1[tablecount1][1];
									//System.out.println(tablemenuadd1[tablecount1][1]);
								}
							}
						menumodel.addRow(input3);
						tablecount1++;
						s = null;
						
					}
					else if(tablenumber == 2)
					{
						tablemenuadd2[tablecount2][0] = s; //오류 
						input3[0] = tablemenuadd2[tablecount2][0];
						//System.out.println(menuname[count-1]);
						
							for(int i=0;i<count;i++) // 담긴 메뉴이름 
							{
								if(s.equals(menuname[count-1])) { //메뉴이름과 ㅇ같은항의가격찾아서넣기 
									//System.out.println(menupricearr[count-1]);
									tablemenuadd2[tablecount2][1] = String.valueOf(menupricearr[count-1]);
									input3[1] = tablemenuadd2[tablecount2][1];
									//System.out.println(tablemenuadd1[tablecount1][1]);
								}
							}
						menumodel.addRow(input3);
						tablecount2++;
						s = null;
					}
					else if(tablenumber == 3)
					{
						
						tablemenuadd3[tablecount3][0] = s; //오류 
						input3[0] = tablemenuadd3[tablecount3][0];
						//System.out.println(menuname[count-1]);
						
							for(int i=0;i<count;i++) // 담긴 메뉴이름 
							{
								if(s.equals(menuname[count-1])) { //메뉴이름과 ㅇ같은항의가격찾아서넣기 
									//System.out.println(menupricearr[count-1]);
									tablemenuadd3[tablecount2][1] = String.valueOf(menupricearr[count-1]);
									input3[1] = tablemenuadd3[tablecount2][1];
									//System.out.println(tablemenuadd1[tablecount1][1]);
								}
							}
						menumodel.addRow(input3);
						tablecount3++;
						s = null;
					}
					else if(tablenumber == 4)
					{
						
						tablemenuadd4[tablecount2][0] = s; //오류 
						input3[0] = tablemenuadd4[tablecount4][0];
						//System.out.println(menuname[count-1]);
						
							for(int i=0;i<count;i++) // 담긴 메뉴이름 
							{
								if(s.equals(menuname[count-1])) { //메뉴이름과 ㅇ같은항의가격찾아서넣기 
									//System.out.println(menupricearr[count-1]);
									tablemenuadd4[tablecount4][1] = String.valueOf(menupricearr[count-1]);
									input3[1] = tablemenuadd4[tablecount4][1];
									//System.out.println(tablemenuadd1[tablecount1][1]);
								}
							}
						menumodel.addRow(input3);
						tablecount4++;
						s = null;
					}
					else if(tablenumber == 5)
					{
						//System.out.println(s);
						//System.out.println(tablecount5);
						tablemenuadd5[tablecount5][0] = s; //오류 
						input3[0] = tablemenuadd5[tablecount5][0];
						//System.out.println(menuname[count-1]);
						
							for(int i=0;i<count;i++) // 담긴 메뉴이름 
							{
								if(s.equals(menuname[count-1])) { //메뉴이름과 ㅇ같은항의가격찾아서넣기 
									//System.out.println(menupricearr[count-1]);
									tablemenuadd5[tablecount5][1] = String.valueOf(menupricearr[count-1]);
									input3[1] = tablemenuadd5[tablecount2][1];
									//System.out.println(tablemenuadd1[tablecount1][1]);
								}
							}
						menumodel.addRow(input3);
						tablecount5++;
						s = null;
					}
					
					else if(tablenumber == 6)
					{
						//System.out.println(s);
						//System.out.println(tablecount2);
						tablemenuadd6[tablecount6][0] = s; //오류 
						input3[0] = tablemenuadd6[tablecount6][0];
						//System.out.println(menuname[count-1]);
						
							for(int i=0;i<count;i++) // 담긴 메뉴이름 
							{
								if(s.equals(menuname[count-1])) { //메뉴이름과 ㅇ같은항의가격찾아서넣기 
									//System.out.println(menupricearr[count-1]);
									tablemenuadd6[tablecount6][1] = String.valueOf(menupricearr[count-1]);
									input3[1] = tablemenuadd6[tablecount6][1];
									//System.out.println(tablemenuadd1[tablecount1][1]);
								}
							}
						menumodel.addRow(input3);
						tablecount6++;
						s = null;
					}
					else if(tablenumber == 7)
					{
						//System.out.println(s);
						//System.out.println(tablecount2);
						tablemenuadd7[tablecount7][0] = s; //오류 
						input3[0] = tablemenuadd7[tablecount7][0];
						//System.out.println(menuname[count-1]);
						
							for(int i=0;i<count;i++) // 담긴 메뉴이름 
							{
								if(s.equals(menuname[count-1])) { //메뉴이름과 ㅇ같은항의가격찾아서넣기 
									//System.out.println(menupricearr[count-1]);
									tablemenuadd7[tablecount7][1] = String.valueOf(menupricearr[count-1]);
									input3[1] = tablemenuadd7[tablecount7][1];
									//System.out.println(tablemenuadd1[tablecount1][1]);
								}
							}
						menumodel.addRow(input3);
						tablecount7++;
						s = null;
					}
					
					else if(tablenumber == 8)
					{
						//System.out.println(s);
						//System.out.println(tablecount2);
						tablemenuadd8[tablecount8][0] = s; //오류 
						input3[0] = tablemenuadd8[tablecount8][0];
						//System.out.println(menuname[count-1]);
						
							for(int i=0;i<count;i++) // 담긴 메뉴이름 
							{
								if(s.equals(menuname[count-1])) { //메뉴이름과 ㅇ같은항의가격찾아서넣기 
									//System.out.println(menupricearr[count-1]);
									tablemenuadd8[tablecount8][1] = String.valueOf(menupricearr[count-1]);
									input3[1] = tablemenuadd8[tablecount8][1];
									//System.out.println(tablemenuadd1[tablecount1][1]);
								}
							}
						menumodel.addRow(input3);
						tablecount8++;
						s = null;
					}
				}
			}
	      });

	      JPanel user = new JPanel();
			user.setPreferredSize(new Dimension(600,300));
			user.setBounds(0, 300, 600, 300);
			JPanel upanel = new JPanel();
			upanel.setPreferredSize(new Dimension(0,70));
			String ucolName[] = {"번호","등급","이름", "마일리지", "연락처"};
			//
			DefaultTableModel model = new DefaultTableModel(ucolName, 0);
			//
			JTable utab = new JTable(model);
			
	      /////////////결제버튼누르면
	      JButton pay = new JButton("결제");
	      pay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				{
			Object[] options = {"SILVER", "GOLD", "PLATINUM","회원가입"};
  				int selectedNum = JOptionPane.showOptionDialog(null, "멤버십가입되어있으신가요? ", "멤버십확인 ", 
  				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
  				
  				String username = JOptionPane.showInputDialog("이름을 입력해주세요");
					System.out.println(username);
  					int usercount = 0;
  					
				for(int i=0; i< utab.getRowCount();i++)
				{
					Object username1 = utab.getValueAt(i, 2);
					if(username1.equals(username))
					{
						usercount = i;
					}
				}
				
				int row = usercount;
				
  					if(tablenumber==1)
  					{
  						int mil = totalcost/100*2;
  					
  						
  						utab.setValueAt(String.valueOf(mil),row,3);
  						
  						if(selectedNum == 0) {
  							payment = totalcost-(totalcost/100*2);
  
  						}
  						else if(selectedNum == 1)
  							payment = totalcost-(totalcost/100*5);
  						else if(selectedNum == 2)
  							payment = totalcost-(totalcost/100*10);
  						totalcost = 0;
  						table1.setText("총액 : "+String.valueOf(totalcost));
  					}
  					else if(tablenumber ==2)
  					{
  						int mil = totalcost2/100*2;
  					
  						
  						utab.setValueAt(String.valueOf(mil),row,3);
  						
  						if(selectedNum == 0)
  							payment = totalcost2-(totalcost2/100*2);
  						else if(selectedNum == 1)
  							payment = totalcost2-(totalcost2/100*5);
  						else if(selectedNum == 2)
  							payment = totalcost2-(totalcost2/100*10);
  						totalcost2 = 0;
  						table2.setText("총액 : "+String.valueOf(totalcost2));
  					}
  					else if(tablenumber ==3)
  					{
  						int mil = totalcost3/100*2;
  						
  						
  						utab.setValueAt(String.valueOf(mil),row,3);
  						
  						if(selectedNum == 0)
  							payment = totalcost3-(totalcost3/100*2);
  						else if(selectedNum == 1)
  							payment = totalcost3-(totalcost3/100*5);
  						else if(selectedNum == 2)
  							payment = totalcost3-(totalcost3/100*10);
  						totalcost3 = 0;
  						table3.setText("총액 : "+String.valueOf(totalcost3));
  					}
  					else if(tablenumber ==4)
  					{
  						int mil = totalcost4/100*4;
  					
  						
  						utab.setValueAt(String.valueOf(mil),row,3);
  						
  						if(selectedNum == 0)
  							payment = totalcost4-(totalcost4/100*2);
  						else if(selectedNum == 1)
  							payment = totalcost4-(totalcost4/100*5);
  						else if(selectedNum == 2)
  							payment = totalcost4-(totalcost4/100*10);
  						totalcost4 = 0;
  						table4.setText("총액 : "+String.valueOf(totalcost4));
  					}
  					else if(tablenumber ==5)
  					{
  						int mil = totalcost5/100*2;
  					
  						
  						utab.setValueAt(String.valueOf(mil),row,3);
  						
  						if(selectedNum == 0)
  							payment = totalcost5-(totalcost5/100*2);
  						else if(selectedNum == 1)
  							payment = totalcost5-(totalcost5/100*5);
  						else if(selectedNum == 2)
  							payment = totalcost5-(totalcost5/100*10);
  						totalcost5 = 0;
  						table5.setText("총액 : "+String.valueOf(totalcost5));
  					}
  					else if(tablenumber ==6)
  					{
  						
  						int mil = totalcost6/100*2;
  						utab.setValueAt(String.valueOf(mil),row,3);
  						
  						if(selectedNum == 0)
  							payment = totalcost6-(totalcost6/100*2);
  						else if(selectedNum == 1)
  							payment = totalcost6-(totalcost6/100*5);
  						else if(selectedNum == 2)
  							payment = totalcost6-(totalcost6/100*10);
  						totalcost6 = 0;
  						table6.setText("총액 : "+String.valueOf(totalcost6));
  					}
  					else if(tablenumber ==7)
  					{
  						int mil = totalcost7/100*2;
  						utab.setValueAt(String.valueOf(mil),row,3);
  						
  						if(selectedNum == 0)
  							payment = totalcost7-(totalcost7/100*2);
  						else if(selectedNum == 1)
  							payment = totalcost7-(totalcost7/100*5);
  						else if(selectedNum == 2)
  							payment = totalcost7-(totalcost7/100*10);
  						totalcost7 = 0;
  						table7.setText("총액 : "+String.valueOf(totalcost7));
  					}
  					else if(tablenumber ==8)
  					{
  						int mil = totalcost8/100*2;
  						utab.setValueAt(String.valueOf(mil),row,3);
  						
  						if(selectedNum == 0)
  							payment = totalcost8-(totalcost8/100*2);
  						else if(selectedNum == 1)
  							payment = totalcost8-(totalcost8/100*5);
  						else if(selectedNum == 2)
  							payment = totalcost8-(totalcost8/100*10);
  						totalcost8 = 0;
  						table8.setText("총액 : "+String.valueOf(totalcost8));
  					}
  					
  					System.out.println(payment);
		}

				//
				int amountMenu = tabl.getRowCount();
				 
				 BurgerKing.totalsales = BurgerKing.totalsales + payment;
				 BurgerKing.todaysales = BurgerKing.todaysales + payment;
				 (BurgerKing.label2).setText("오늘 매출 : "+BurgerKing.todaysales+"원 전체 잔고 : "+BurgerKing.totalsales+"원");
				 
				 for(int i = 0; i < amountMenu; i++) {
					 menumodel.setValueAt("", i, 0);
					 menumodel.setValueAt("", i, 1);
				 }
				 
				 
				 
				 
			}
	    	  
	      });
	      menu1.add(add,BorderLayout.SOUTH);
	      menu1.add(pay,BorderLayout.SOUTH);

		  table.add(tbl,BorderLayout.WEST);
		  
		//chango
		JPanel chango = new JPanel();
		chango.setPreferredSize(new Dimension(700,300));
		chango.setBounds(0, 300, 600, 300);
		chango.setLayout(new BorderLayout());
		
		//chango table
		JPanel changoTablePanel = new JPanel();
		changoTablePanel.setPreferredSize(new Dimension(400,300));
		String[] columnType = {"이름", "재고", "주문", "가격"};
		Object[][] data = {
				{"빵", "2", "0" , "1000", "이마트" ,"053)123-456"},
				{"베이컨", "3", "0", "1000", "이마트" ,"053)123-456"},
				{"치즈", "5", "0", "500", "이마트" ,"053)123-456"},
				{"양상추", "5", "0", "100", "이마트" ,"053)123-456"},
				{"토마토", "3", "0", "500", "이마트" ,"053)123-456"},
				{"패티", "2", "0", "1000", "이마트" ,"053)123-456"},
				{"양파", "2", "0", "500" , "이마트" ,"053)123-456"},
				{"새우", "3", "0", "1000","이마트" ,"053)123-456"},
				{"치킨너겟", "10", "0", "1000","이마트" ,"053)123-456"}
		};
		Object[][] dataInfo = new Object[MAX_ING][6];
		DefaultTableModel modelTbl = new DefaultTableModel(data, columnType);
		JTable t = new JTable(modelTbl);
		JScrollPane sc = new JScrollPane(t);
		sc.setPreferredSize(new Dimension(400,250));
		t.setPreferredSize(new Dimension(400,250));
		changoTablePanel.add(sc, BorderLayout.CENTER);
		for(int i = 0; i < data.length ; i++)
			for(int j = 0; j < dataInfo[i].length; j++)
				dataInfo[i][j] = data[i][j];
		
		//trace = getTrace()+data.length;
		trace = data.length;
		//추가 && 삭제 버튼 
		JPanel BtnPanel = new JPanel();//button panel
		BtnPanel.setPreferredSize(new Dimension(500,50));
		BtnPanel.setLayout(new FlowLayout());
		
		JButton addBtn = new JButton("추가");
		JButton deleteBtn = new JButton("삭제");
		addBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		JTextField nameInput = new JTextField("이름");
		JTextField stock = new JTextField("재고");
		JTextField order = new JTextField("주문");
		JTextField price = new JTextField("가격");
		JTextField sells = new JTextField("판매처");
		JTextField contact = new JTextField("연락처");
		BtnPanel.add(nameInput);
		BtnPanel.add(stock);
		BtnPanel.add(order);
		BtnPanel.add(price);
		BtnPanel.add(sells);
		BtnPanel.add(contact);
		
		BtnPanel.add(addBtn);
		BtnPanel.add(deleteBtn);
		changoTablePanel.add(BtnPanel, BorderLayout.SOUTH);
		chango.add(changoTablePanel, BorderLayout.WEST);
		
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cmd = e.getActionCommand();
				
				if(cmd.equals("추가")) {
					String arr[] = new String[4];
					
					arr[0] = nameInput.getText();
					arr[1] = stock.getText();
					arr[2] = order.getText();
					arr[3] = price.getText();
					modelTbl.addRow(arr);
					
					dataInfo[trace][0] = nameInput.getText();
					dataInfo[trace][1] = stock.getText();
					dataInfo[trace][2] = order.getText();
					dataInfo[trace][3] = price.getText();
							
					nameInput.setText("이름");
					stock.setText("재고");
					order.setText("주문");
					price.setText("가격");
					
					trace++;
					
				}
			}
			
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t.getSelectedRow() == -1) {
					return;
				}else {
					int sel = t.getSelectedRow();
					for(int i = sel+1; i<= trace; i++) {
						for(int j = 0; j < 6; j++) {
							dataInfo[i-1][j] = dataInfo[i][j];
						}
					}
					modelTbl.removeRow(t.getSelectedRow());
					trace--;
				}
			}
		});
	
		
		//chango order part
		JPanel orderPart = new JPanel();//order information part
		orderPart.setPreferredSize(new Dimension(300,600));
		orderPart.setLayout(new BorderLayout());
		
		JPanel ingInfoPanel = new JPanel();//letters, info part
		ingInfoPanel.setPreferredSize(new Dimension(300, 400));
		ingInfoPanel.setLayout(new GridLayout(3,4));
		
		JLabel ingNameVal = new JLabel("");
		JLabel ingPriceVal = new JLabel("");
		JLabel ingSellsVal = new JLabel("");
		JLabel ingContactVal = new JLabel("");
		JLabel ingQuantityVal = new JLabel("");
		JLabel ingOrderAmountVal = new JLabel("");
		ingInfoPanel.add(ingNameVal);
		ingInfoPanel.add(ingPriceVal);
		ingInfoPanel.add(ingSellsVal);
		ingInfoPanel.add(ingContactVal);
		ingInfoPanel.add(ingQuantityVal);
		ingInfoPanel.add(ingOrderAmountVal);

		t.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = t.getSelectedRow();
				ingNameVal.setText("	 이름 : " + dataInfo[row][0]);
				ingPriceVal.setText("  가격 : " + dataInfo[row][3]);
				ingSellsVal.setText("  판매처 : "+dataInfo[row][4]);
				ingContactVal.setText("  연락처 : "+dataInfo[row][5]);
				ingQuantityVal.setText("  수량 : "+dataInfo[row][1]);
				ingOrderAmountVal.setText("  주문량 : "+dataInfo[row][2]);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		orderPart.add(ingInfoPanel, BorderLayout.CENTER);
		
		//수량 입력 부분 
		JTextField amount = new JTextField("주문량");
		
		JPanel BtnPanel2 = new JPanel();//buttons
		BtnPanel2.setLayout(new FlowLayout());
		JButton orderBtn = new JButton("주문");
		JButton cancleBtn = new JButton("주문 취소");
		orderBtn.addActionListener(this);
		cancleBtn.addActionListener(this);
		BtnPanel2.add(amount);
		BtnPanel2.add(orderBtn);
		BtnPanel2.add(cancleBtn);
		
		orderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = amount.getText();
				int am = Integer.parseInt(text);
				System.out.println(am);
				int selRow = t.getSelectedRow();
				int selRowVal = Integer.valueOf(modelTbl.getValueAt(selRow, 2).toString());
				int sum = (selRowVal + am);
				System.out.println(sum);
				modelTbl.setValueAt(sum, selRow, 2);
				//dataInfo[selRow][2] = Integer.parseInt((String)dataInfo[selRow][2])+ am;
				//modelTbl.setValueAt(dataInfo[selRow][2], selRow, 2);
				ingOrderAmountVal.setText("  주문량 : "+modelTbl.getValueAt(selRow, 2));
				amount.setText("주문량");
				
			}
			
		});
		cancleBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = amount.getText();
				int am = Integer.parseInt(text);
				int selRow = t.getSelectedRow();
				int selRowVal = Integer.valueOf(modelTbl.getValueAt(selRow, 2).toString());
				int sub = (selRowVal - am);
				System.out.println("sub" + sub);
				dataInfo[selRow][2] = sub;
				modelTbl.setValueAt(sub, selRow, 2);
				ingOrderAmountVal.setText("  주문량 : "+dataInfo[selRow][2]);
				amount.setText("주문량");
			}
			
		});
		
		orderPart.add(BtnPanel2, BorderLayout.SOUTH);
		
		chango.add(orderPart, BorderLayout.EAST);
		
		
		//user
		//JPanel user = new JPanel();
		//user.setPreferredSize(new Dimension(600,300));
		//user.setBounds(0, 300, 600, 300);
		//JPanel upanel = new JPanel();
		//upanel.setPreferredSize(new Dimension(0,70));

		/*
		String ucolName[] = {"번호","등급","이름", "마일리지", "연락처"};
		//
		DefaultTableModel model = new DefaultTableModel(ucolName, 0);
		//
		JTable utab = new JTable(model);*/
		
		DefaultTableCellRenderer utableCell = new DefaultTableCellRenderer();
		utableCell.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel uCellModel = utab.getColumnModel();
		
		for(int i=0; i<uCellModel.getColumnCount(); i++) {
			uCellModel.getColumn(i).setCellRenderer(utableCell);
			
		}
		//
		user.setLayout(new BorderLayout());

		user.add(new JScrollPane(utab), BorderLayout.CENTER);
		
		JTextField utext1 = new JTextField(5);
		JTextField utext2 = new JTextField(10);
		JTextField utext3 = new JTextField(10);
		JTextField utext4 = new JTextField(10);
		JTextField utext5 = new JTextField(10);

		
		JButton ubutton1 = new JButton("추가");
		JButton ubutton2 = new JButton("삭제");
		JButton ubutton3 = new JButton("편집");
		
		upanel.add(new JLabel("번호"));
		upanel.add(utext1);
		upanel.add(new JLabel("등급"));
		upanel.add(utext2);
		upanel.add(new JLabel("이름"));
		upanel.add(utext3);
		upanel.add(new JLabel("마일리지"));
		upanel.add(utext4);
		upanel.add(new JLabel("연락처"));
		upanel.add(utext5);
		
		upanel.add(ubutton1);
		upanel.add(ubutton2);
		upanel.add(ubutton3);
		
		ubutton1.addActionListener(new UserAddActionClass(utab,utext1,utext2,utext3,utext4,utext5));
		
		ubutton2.addActionListener(new RemoveActionClass(utab));
		ubutton3.addActionListener(new UserEditActionClass(utab,utext1,utext2,utext3,utext4,utext5));
		user.add(upanel, BorderLayout.SOUTH);

		/*String cells2[][] = {};
		DefaultTableModel model = new DefaultTableModel(cells2, header2); //테이블추가삭제가능하게하는메소드 
		JTable tabl2 = new JTable(model); 
		JScrollPane js2 = new JScrollPane(tabl2); //스크롤페인에 테이블추
		js2.setPreferredSize(new Dimension(700,215)); //표사이즈 
		user2.setLayout(new BoxLayout(user2,BoxLayout.X_AXIS));
		
		//JTextField number = new JTextField(10);
		JTextField level = new JTextField(10);
		JTextField name = new JTextField(10);
		JTextField mileage = new JTextField(5);
		JTextField phone = new JTextField(13);
		
		//user2.add(number);
		user2.add(level);
		user2.add(name);
		user2.add(mileage);
		user2.add(phone);
	
		JButton edit = new JButton("편집");
		JButton add2 = new JButton("추가");
		JButton delete = new JButton("삭제");
		
		
		add2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//테이블에 넣으면 숫자갱신이 안됨! 어떡한
				
				inputStr[0] = String.valueOf(countmember); //인트를 스트링으로 
				member[countmember][0] = String.valueOf(countmember);
				inputStr[1] = level.getText();
				member[countmember][1] = level.getText();
				inputStr[2] = name.getText();
				member[countmember][2] = name.getText();
				inputStr[3] = mileage.getText();
				member[countmember][3]=mileage.getText();
				inputStr[4] = phone.getText();
				member[countmember][4] = phone.getText();
				model.addRow(inputStr);
				
				countmember++;
				
				//number.setText("");
				level.setText("");
				name.setText("");
				mileage.setText("");
				phone.setText("");
			}
		});
		
		int srow = tabl2.getSelectedRow();
		
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabl2.getSelectedRow()==-1)
				{
					return;
				}
				else {
					// 한칸씩 땡기는거 구현 해야됨 
					member[countmember][0] = String.valueOf(countmember-1);
					inputStr[0] = String.valueOf(countmember-1);
					
					for(int i=tabl2.getSelectedRow()+1;i<=countmember;i++)
					{
						for(int j=0;j<5;j++)
						{
							member[i-1][j] = member[i][j];
						}
					}
					model.removeRow(tabl2.getSelectedRow());
					countmember = countmember-1;
					
				}
			}
		});
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					//tabl2.setValueAt(number, srow, 0);
			}
		});
		
		user.add(js2,BorderLayout.NORTH);
		user.add(user2,BorderLayout.CENTER);
		user.add(edit,BorderLayout.SOUTH);
		user.add(add2,BorderLayout.SOUTH);
		user.add(delete,BorderLayout.SOUTH);*/
	

		
		//menu
		JPanel menu = new JPanel();
		menu.setPreferredSize(new Dimension(600,300));
		menu.setBounds(0, 300, 600, 300);
		// 제일 큰 패널 
		//menu.setBackground(Color.WHITE);
		// 메뉴출력 패널 
		JPanel totalmenu = new JPanel();
		totalmenu.setPreferredSize(new Dimension(200,290));
		//totalmenu.setBackground(Color.RED);
		String menuheader[]= {"메뉴목록 "};
		String menucells[][]= {};
		DefaultTableModel totalmenumodel = new DefaultTableModel(menucells, menuheader); //테이블추가삭제가능하게하는메소드 
		
		JTable menutable = new JTable(totalmenumodel);
		JScrollPane menuscroll = new JScrollPane(menutable); //스크롤페인에 테이블추
		menuscroll.setPreferredSize(new Dimension(200,250));
		//JTextField menutitle = new JTextField(10);
		totalmenu.setLayout(new BorderLayout());
		JTextField menunumber = new JTextField(5);
		JTextField menuprice = new JTextField(5);
		JTextField menuprice2 = new JTextField(5);
		JTextField menuingredient = new JTextField(5);
		
		JButton menuadd = new JButton("추가");
		
		menuadd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String inputmenu[] = new String[1];
				
				dialog dia = new dialog();
				dia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel diapanel = new JPanel();
				diapanel.setLayout(new FlowLayout());
				
				JLabel dianame = new JLabel("이름 ");
				JLabel diaprice = new JLabel("가격 ");
				JLabel diaprice2 = new JLabel("생산단가 ");
				JLabel diaingredient = new JLabel("사용된 재료 ");
				JButton diabutton = new JButton("확인 ");
				diabutton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						inputmenu[0] = menunumber.getText();
						menuname[count] = inputmenu[0];
						menucost[count][0] = inputmenu[0];
						menucost[count][1] = menuprice.getText();
						menupricearr[count] = Integer.parseInt(menuprice.getText());
						menucost[count][2] = menuprice2.getText();
						menucost[count][3] = menuingredient.getText();
						System.out.println(menucost[count]);
						totalmenumodel.addRow(inputmenu);
						count++;
						
						menuprice.setText("");
						menunumber.setText("");
						menuprice2.setText("");
						menuingredient.setText("");
						
						dia.dispose();
					}
					
				});
				dia.setBounds(300,300,600,60);
				diapanel.add(dianame);
				diapanel.add(menunumber); //t
				diapanel.add(diaprice);
				diapanel.add(menuprice); //t
				diapanel.add(diaprice2);
				diapanel.add(menuprice2);//t
				diapanel.add(diaingredient);
				diapanel.add(menuingredient);//t
				diapanel.add(diabutton);
				dia.add(diapanel);
				dia.setVisible(true);
				
			}
		});
	
		
		totalmenu.add(menuscroll,BorderLayout.NORTH);
		
		//totalmenu.add(input,BorderLayout.CENTER);
		totalmenu.add(menuadd,BorderLayout.SOUTH);
		
		JPanel menudetails = new JPanel();
		menudetails.setPreferredSize(new Dimension(400,295));
		JTextArea menutext = new JTextArea(16,30);

		menudetails.setLayout(new BorderLayout());
		
		JPanel menubutton = new JPanel();
		JButton editmenu = new JButton("편집");
		JButton deletemenu = new JButton("삭제");
		deletemenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(menutable.getSelectedRow()==-1)
					{
						return;
					}
					else {
						for(int i=menutable.getSelectedRow()+1;i<=count;i++)
						{
							for(int j=0;j<4;j++)
							{
								menucost[i-1][j] = menucost[i][j];
							}
						}
						//System.out.println(menucost[menutable.getSelectedRow()][0]);
						totalmenumodel.removeRow(menutable.getSelectedRow());
						menutext.setText("");
		
				}
			}
		});
		
		menubutton.add(editmenu);
		menubutton.add(deletemenu);
		
		menutable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
				int row = menutable.getSelectedRow();
				
				menutext.setText("이름 : "+menucost[row][0]+"\n가격 : "+menucost[row][1]+"\n생산단가 : "+menucost[row][2]+"\n사용된 재료 : "+menucost[row][3]);
			}
				
		});
		
		menudetails.add(menubutton,BorderLayout.SOUTH);
		menudetails.add(menutext,BorderLayout.NORTH);
		//menudetails.setBackground(Color.YELLOW);
		menu.add(totalmenu,BorderLayout.WEST);
		menu.add(menudetails,BorderLayout.EAST);

		
		//worker
		JPanel worker = new JPanel();
		worker.setPreferredSize(new Dimension(600,300));
		worker.setBounds(0, 300, 600, 300);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0,70));

		
		String colName[] = {"번호","이름","급여", "직급","입사일", "연락처"};
		//
		DefaultTableModel model3 = new DefaultTableModel(colName, 0);
		//
		JTable tab = new JTable(model3);
		
		DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer();
		tableCell.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel CellModel = tab.getColumnModel();
		
		for(int i=0; i<CellModel.getColumnCount(); i++) {
			CellModel.getColumn(i).setCellRenderer(tableCell);
			
		}
		//
		worker.setLayout(new BorderLayout());

		worker.add(new JScrollPane(tab), BorderLayout.CENTER);
		
		JTextField text1 = new JTextField(5);
		JTextField text2 = new JTextField(10);
		JTextField text3 = new JTextField(10);
		JTextField text4 = new JTextField(10);
		JTextField text5 = new JTextField(10);
		//JTextField text6 = new JTextField(10);

		
		JButton button1 = new JButton("추가");
		JButton button2 = new JButton("삭제");
		JButton button3 = new JButton("편집");
		
		
		
		
		panel.add(new JLabel("번호"));
		panel.add(text1);
		panel.add(new JLabel("이름"));
		panel.add(text2);
		panel.add(new JLabel("급여"));
		panel.add(text3);
		panel.add(new JLabel("직급"));
		panel.add(text4);
	//	panel.add(new JLabel("입사일"));
		panel.add(new JLabel("연락처"));
		panel.add(text5);
		
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		
		button1.addActionListener(new AddActionClass(tab,text1,text2,text3,text4,text5));
		
		button2.addActionListener(new RemoveActionClass(tab));
		button3.addActionListener(new EditActionClass(tab,text1,text2,text3,text4,text5));
		worker.add(panel, BorderLayout.SOUTH);
		
		JTabbedPane tPane = new JTabbedPane();
		tPane.addTab("테이블", table);
		tPane.addTab("창고",  chango);
		tPane.addTab("회원", user);
		tPane.addTab("메뉴", menu);
		tPane.addTab("직원", worker);
		
		table.add(menu1,BorderLayout.EAST);
		main.add(tPane);
		
		
		//JButton endButton = new JButton("Click to end programs.");
		//endButton.addActionListener(new EndingListener());
		//add(endButton);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ClickedObj = e.getSource();
		if(ClickedObj == table1) {
		//table1.setOpaque(true);
		table1.setBackground(Color.RED);
		
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object ClickedObj = e.getSource();
		((JButton) ClickedObj).setBackground(Color.RED);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}



