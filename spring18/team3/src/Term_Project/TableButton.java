package Term_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class TableButton implements ActionListener{
	
	JFrame Frame = new JFrame();
	JPanel tablePanel = new JPanel();
	JPanel[][] orderPanel = new JPanel[9][20];
	JPanel menuPanel = new JPanel(); // order sheet 에서 메뉴 적혀있는 칸
	JPanel pricePanel = new JPanel(); // order sheet 에서 가격 적혀있는 칸
	JPanel buttonPanel = new JPanel(); // order sheet 에서 맨 밑에 2개버튼 있는 
	JButton[] Table = new JButton[9];
	JButton addBtn = new JButton("추가");
	JButton payBtn = new JButton("결제");
	String[][] menuStr = new String[9][10];
	String[][] priceOfMenu = new String[9][10];
	int[] numberOfOrderedMenu = new int[9];
	int SelectedTable = -1;
	int[] priceOfTable = new int[9];
	JLabel[] priceLabel = new JLabel[9];
	
	//결제
	String[] member = new String[2];
	JTextField[] memberTF = new JTextField[2];
	JLabel[] memberLabel = new JLabel[2];
	JPanel[] memberPanel = new JPanel[3];
	JButton[] memberBtn = new JButton[2];
	
	JFrame newFrame = new JFrame();
	String[][] Menu = new String[8][2];
	String[] SelectedMenu = new String[2];
	int numberOfMenu = 0;
	int SelectedIndex = -1;
	JButton[][] MenuBoard = new JButton[8][2];
	JButton yes = new JButton("주문");
	JButton no = new JButton("취소");
	
	JFrame newFrame2 = new JFrame();
	JLabel label2 = new JLabel("재료가 충분한 지 확인하십시오.");
	JButton Yes = new JButton("확인");
	
	boolean[] flag = new boolean[9];
	
	public TableButton(JFrame frame) {
		Frame = frame;
		
		for ( int i = 0 ; i < 8 ; i++ ) {
			for ( int j = 0 ; j < 2 ; j++ ) {
				Menu[i][j] = "";
			}
		}
		
		for ( int i = 0 ; i < 9 ; i++ ) {
			flag[i] = false;
		}
		
		tablePanel.setBounds(0,89,431,289);
		tablePanel.setBackground(Color.WHITE);
		tablePanel.setBorder(new LineBorder(Color.GRAY, 2));
		tablePanel.setLayout(null);
		
		for ( int i = 1 ; i <= 8 ; i++ ) {
			Table[i] = new JButton("테이블 " + i);
			Table[i].setVerticalAlignment(SwingConstants.NORTH);
			Table[i].setBorder(new LineBorder(Color.GRAY, 2, true));
			Table[i].setLocation(10+105*((i-1)%4),30+140*((i-1)/4));
			Table[i].setSize(90,100);
			Table[i].setBackground(Color.WHITE);
			Table[i].setOpaque(true);
			Table[i].addActionListener(this);
			Table[i].setLayout(null);
			tablePanel.add(Table[i]);
		}
		frame.add(tablePanel);
		tablePanel.setVisible(false);
		tablePanel.setVisible(true);
		
		menuPanel.setBackground(Color.LIGHT_GRAY);
		menuPanel.add(new JLabel("메뉴"));
		menuPanel.setBorder(new LineBorder(Color.GRAY,2,true));
		menuPanel.setBounds(430,89,86,30);
		frame.add(menuPanel);
		
		pricePanel = new JPanel();
		pricePanel.setBackground(Color.LIGHT_GRAY);
		pricePanel.add(new JLabel("가격"));
		pricePanel.setBorder(new LineBorder(Color.GRAY,2,true));
		pricePanel.setBounds(514,89,86,30);
		frame.add(pricePanel);
		
		menuPanel.setVisible(false);
		menuPanel.setVisible(true);
		pricePanel.setVisible(false);
		pricePanel.setVisible(true);
		
		for ( int i = 0 ; i < 9 ; i++ ) {
			for ( int j = 0 ; j < 20 ; j++ ) {
				orderPanel[i][j] = new JPanel(new BorderLayout());
				orderPanel[i][j].setBorder(new LineBorder(Color.GRAY,2,true));
				orderPanel[i][j].setBackground(Color.WHITE);
				if ( j % 2 == 0 ) {
					orderPanel[i][j].setBounds(430,118+20*(j/2),86,20);
				}else {
					orderPanel[i][j].setBounds(514,118+20*((j-1)/2),86,20);
				}
			}
		}
		
		for ( int j = 0 ; j < 20 ; j++ ) {
			frame.add(orderPanel[0][j]);
		}
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBorder(new LineBorder(Color.GRAY, 2, true));
		buttonPanel.setBounds(430,317,170,61);
		buttonPanel.setLayout(null);
		
		addBtn.setBounds(10, 20, 70, 25);
		addBtn.addActionListener(this);
		buttonPanel.add(addBtn);
		
		payBtn.setBounds(90, 20, 70, 25);
		payBtn.addActionListener(this);
		buttonPanel.add(payBtn);
		
		frame.add(buttonPanel);
		
		for ( int j = 0 ; j < 20 ; j++ ) {
			orderPanel[0][j].setVisible(false);
			orderPanel[0][j].setVisible(true);
		}
		buttonPanel.setVisible(false);
		buttonPanel.setVisible(true);
		
		resetTableColor();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		resetTableColor();
		
		if ( obj == Table[1]) {
			Table[1].setBackground(Color.GRAY);
			showSheet(1);
			SelectedTable = 1;
		}else if ( obj == Table[2]) {
			Table[2].setBackground(Color.GRAY);
			showSheet(2);
			SelectedTable = 2;
		}else if ( obj == Table[3]) {
			Table[3].setBackground(Color.GRAY);
			showSheet(3);
			SelectedTable = 3;
		}else if ( obj == Table[4]) {
			Table[4].setBackground(Color.GRAY);
			showSheet(4);
			SelectedTable = 4;
		}else if ( obj == Table[5]) {
			Table[5].setBackground(Color.GRAY);
			showSheet(5);
			SelectedTable = 5;
		}else if ( obj == Table[6]) {
			Table[6].setBackground(Color.GRAY);
			showSheet(6);
			SelectedTable = 6;
		}else if ( obj == Table[7]) {
			Table[7].setBackground(Color.GRAY);
			showSheet(7);
			SelectedTable = 7;
		}else if ( obj == Table[8]) {
			Table[8].setBackground(Color.GRAY);
			showSheet(8);
			SelectedTable = 8;
		}else if ( obj == addBtn) {
			if ( SelectedTable != -1 ) {
				updateSheet();
			}
		}else if ( obj == payBtn) {
			if ( SelectedTable != -1 && numberOfOrderedMenu[SelectedTable] != 0 ) {
				newFrame = new JFrame("회원 결제");
				newFrame.setSize(250, 150);
				newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				newFrame.getContentPane().setBackground(Color.WHITE);
				newFrame.setLayout(new GridLayout(3,1));
				newFrame.setLocation(200,150);
				
				memberPanel[0] = new JPanel(new GridLayout(1,2));
				memberPanel[1] = new JPanel(new GridLayout(1,2));
				memberPanel[2] = new JPanel(new FlowLayout());
				
				memberLabel[0] = new JLabel("이름", SwingConstants.CENTER);
				memberLabel[1] = new JLabel("연락처", SwingConstants.CENTER);
				
				memberTF[0] = new JTextField(20);
				memberTF[1] = new JTextField(20);
				memberTF[0].setText("");
				memberTF[1].setText("");
				
				memberBtn[0] = new JButton("회원 결제");
				memberBtn[1] = new JButton("일반 결제");
				
				memberBtn[0].addActionListener(this);
				memberBtn[1].addActionListener(this);
				
				memberPanel[0].add(memberLabel[0]);
				memberPanel[0].add(memberTF[0]);
				memberPanel[1].add(memberLabel[1]);
				memberPanel[1].add(memberTF[1]);
				memberPanel[2].add(memberBtn[0]);
				memberPanel[2].add(memberBtn[1]);
				
				newFrame.add(memberPanel[0]);
				newFrame.add(memberPanel[1]);
				newFrame.add(memberPanel[2]);
				
				memberPanel[0].setVisible(false);
				memberPanel[1].setVisible(false);
				memberPanel[2].setVisible(false);
				
				memberPanel[0].setVisible(true);
				memberPanel[1].setVisible(true);
				memberPanel[2].setVisible(true);
				
				newFrame.setVisible(true);
			}
		}else if ( obj == yes) {
			if ( SelectedTable != -1 ) {
				resetTableColor();
				flag[SelectedTable] = false;
				SelectedIndex = -1;
				SelectedTable = -1;
				newFrame.dispose();
			}
		}else if ( obj == no ) {
			if ( SelectedTable != -1 ) {
				if ( flag[SelectedTable] == true ) {
					resetTable();
				}else {
					resetTableColor();
					SelectedTable = -1;
					SelectedIndex = -1;
					newFrame.dispose();
				}
			}
		}else if ( obj == memberBtn[0] ) {
			if ( !memberTF[0].getText().equals("") && !memberTF[1].getText().equals("")) {
				Base.s.afterOrder(menuStr[SelectedTable], numberOfOrderedMenu[SelectedTable]);
				int i;
				int mileage = priceOfTable[SelectedTable];
				member[0] = memberTF[0].getText();
				member[1] = memberTF[1].getText();
				for ( i = 0 ; i < MemberButton.NumberOfMembers ; i++ ) {
					if ( member[0].equals(MemberButton.MemberInfo[i][2]) && member[1].equals(MemberButton.MemberInfo[i][4])) {
						if ( MemberButton.MemberInfo[i][1].equals("일반")) {
							mileage = mileage*2/100;
						}else if ( MemberButton.MemberInfo[i][1].equals("골드")) {
							mileage = mileage*5/100;
						}else if ( MemberButton.MemberInfo[i][1].equals("플래티넘")) {
							mileage = mileage*10/100;
						}
						priceOfTable[SelectedTable] -= mileage;
						mileage += Integer.parseInt(MemberButton.MemberInfo[i][3]);
						
						Base.m.updateMileage(i, String.valueOf(mileage));
						break;
					}
				}
				if ( i == MemberButton.NumberOfMembers ) {
					mileage = mileage*2/100;
					priceOfTable[SelectedTable] -= mileage;
					Base.m.update(member[0], member[1], String.valueOf(mileage));
				}
				Table[SelectedTable].setBackground(Color.WHITE);
				for ( int j = 0 ; j < numberOfOrderedMenu[SelectedTable]*2 ; j++ ) {
					orderPanel[SelectedTable][j].setVisible(false);
					orderPanel[SelectedTable][j] = new JPanel(new BorderLayout());
					orderPanel[SelectedTable][j].setBorder(new LineBorder(Color.GRAY,2,true));
					orderPanel[SelectedTable][j].setBackground(Color.WHITE);
					if ( j % 2 == 0 ) {
						orderPanel[SelectedTable][j].setBounds(430,118+20*(j/2),86,20);
					}else {
						orderPanel[SelectedTable][j].setBounds(514,118+20*((j-1)/2),86,20);
					}
					Frame.add(orderPanel[SelectedTable][j]);
					orderPanel[SelectedTable][j].setVisible(true);
				}
				
				numberOfOrderedMenu[SelectedTable] = 0;
				Table[SelectedTable].removeAll();
				Base.sales += priceOfTable[SelectedTable];
				Base.updateSales();
				priceOfTable[SelectedTable] = 0;
				SelectedTable = -1;
				newFrame.dispose();
			}
			
		}else if ( obj == memberBtn[1] ){
			Base.s.afterOrder(menuStr[SelectedTable], numberOfOrderedMenu[SelectedTable]);
			Table[SelectedTable].setBackground(Color.WHITE);
			
			for ( int j = 0 ; j < numberOfOrderedMenu[SelectedTable]*2 ; j++ ) {
				orderPanel[SelectedTable][j].setVisible(false);
				orderPanel[SelectedTable][j] = new JPanel(new BorderLayout());
				orderPanel[SelectedTable][j].setBorder(new LineBorder(Color.GRAY,2,true));
				orderPanel[SelectedTable][j].setBackground(Color.WHITE);
				if ( j % 2 == 0 ) {
					orderPanel[SelectedTable][j].setBounds(430,118+20*(j/2),86,20);
				}else {
					orderPanel[SelectedTable][j].setBounds(514,118+20*((j-1)/2),86,20);
				}
				Frame.add(orderPanel[SelectedTable][j]);
				orderPanel[SelectedTable][j].setVisible(true);
			}
			
			numberOfOrderedMenu[SelectedTable] = 0;
			Table[SelectedTable].removeAll();
			Base.sales += priceOfTable[SelectedTable];
			Base.updateSales();
			priceOfTable[SelectedTable] = 0;
			SelectedTable = -1;
			newFrame.dispose();
		}else if ( obj == Yes ) {
			newFrame2.dispose();
		}else {
			for ( int i = 0 ; i < 4 ; i++ ) {
				MenuBoard[i][0].setBackground(Color.WHITE);
				MenuBoard[i][1].setBackground(Color.WHITE);
			}
			for ( int i = 0 ; i < 4 ; i++ ) {
				for ( int j = 0 ; j < 2 ; j++ ) {
					if ( obj == MenuBoard[i][j] ) {
						SelectedIndex = i+j;
						SelectedMenu[0] = Menu[i*2+j][0];
						SelectedMenu[1] = Menu[i*2+j][1];
						MenuBoard[i][j].setBackground(Color.LIGHT_GRAY);
						
						if ( SelectedIndex != -1 && SelectedIndex < numberOfMenu ) {
							menuStr[SelectedTable][numberOfOrderedMenu[SelectedTable]] = SelectedMenu[0];
							priceOfMenu[SelectedTable][numberOfOrderedMenu[SelectedTable]] = SelectedMenu[1];
							numberOfOrderedMenu[SelectedTable]++;
							
							flag[SelectedTable] = true;
							
							if ( Base.s.checkOrder(menuStr, numberOfOrderedMenu) == true) {
								numberOfOrderedMenu[SelectedTable]--;
								orderPanel[SelectedTable][numberOfOrderedMenu[SelectedTable]*2].add(new JLabel(SelectedMenu[0], SwingConstants.CENTER));
								orderPanel[SelectedTable][numberOfOrderedMenu[SelectedTable]*2+1].add(new JLabel(SelectedMenu[1],SwingConstants.CENTER));
								Frame.add(orderPanel[SelectedTable][numberOfOrderedMenu[SelectedTable]*2]);
								Frame.add(orderPanel[SelectedTable][numberOfOrderedMenu[SelectedTable]*2+1]);
								
								priceOfTable[SelectedTable] += Integer.parseInt(priceOfMenu[SelectedTable][numberOfOrderedMenu[SelectedTable]]);
								Table[SelectedTable].removeAll();
								priceLabel[SelectedTable] = new JLabel("총액 " + priceOfTable[SelectedTable]);
								priceLabel[SelectedTable].setBounds(10,20,100,20);
								Table[SelectedTable].add(priceLabel[SelectedTable]);
								
								orderPanel[SelectedTable][numberOfOrderedMenu[SelectedTable]*2].setVisible(false);
								orderPanel[SelectedTable][numberOfOrderedMenu[SelectedTable]*2+1].setVisible(false);
								orderPanel[SelectedTable][numberOfOrderedMenu[SelectedTable]*2].setVisible(true);
								orderPanel[SelectedTable][numberOfOrderedMenu[SelectedTable]*2+1].setVisible(true);
								
								numberOfOrderedMenu[SelectedTable]++;
								
							}else {
								numberOfOrderedMenu[SelectedTable]--;
								menuStr[SelectedTable][numberOfOrderedMenu[SelectedTable]] = "";
								priceOfMenu[SelectedTable][numberOfOrderedMenu[SelectedTable]] = "";
								newFrame2.setSize(300,200);
								newFrame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
								newFrame2.getContentPane().setBackground(Color.WHITE);
								newFrame2.setLayout(null);
								newFrame2.setLocation(300,200);
								
								Yes.addActionListener(this);
								Yes.setBounds(120, 130, 60, 30);
								
								label2.setBounds(70, 60, 200, 30);
								
								newFrame2.add(label2);
								newFrame2.add(Yes);
								
								newFrame2.setVisible(true);
							}
						}
						
						break;
					}
				}
			}
		}
	}
	
	void updateSheet() {
		setMenu();
		newFrame = new JFrame("메뉴판");
		newFrame.setSize(500, 400);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newFrame.getContentPane().setBackground(Color.WHITE);
		newFrame.setLayout(null);
		newFrame.setLocation(200,150);
		
		
		for ( int i = 0 ; i < 4 ; i++ ) {
			if ( Menu[i*2][0] == "" ) {
				MenuBoard[i][0] = new JButton();
			}else {
				MenuBoard[i][0] = new JButton(Menu[i*2][0] + " " + Menu[i*2][1] + "원");
			}
			MenuBoard[i][0].setBorder(new LineBorder(Color.GRAY,2));
			MenuBoard[i][0].setBounds(100,30+50*i,151,51);
			MenuBoard[i][0].addActionListener(this);
			MenuBoard[i][0].setOpaque(true);
			MenuBoard[i][0].setBackground(Color.WHITE);
			newFrame.add(MenuBoard[i][0]);
			if ( Menu[i*2+1][1] == "" ) {
				MenuBoard[i][1] = new JButton();
			}else {
				MenuBoard[i][1] = new JButton(Menu[i*2+1][0] + " " + Menu[i*2+1][1] + "원");
			}
			MenuBoard[i][1].setBorder(new LineBorder(Color.GRAY,2));
			MenuBoard[i][1].setBounds(249,30+50*i,151,51);
			MenuBoard[i][1].addActionListener(this);
			MenuBoard[i][1].setOpaque(true);
			MenuBoard[i][1].setBackground(Color.WHITE);
			newFrame.add(MenuBoard[i][1]);
		}
		yes.addActionListener(this);
		yes.setBounds(160, 300, 80, 50);
		no.addActionListener(this);
		no.setBounds(260, 300, 80, 50);
		
		newFrame.add(yes);
		newFrame.add(no);
		
		newFrame.setVisible(true);
		
	}
	
	void resetTableColor() {
		for ( int i = 1 ; i <= 8 ; i++ ) {
			if ( numberOfOrderedMenu[i] == 0 )
				Table[i].setBackground(Color.WHITE);
			else
				Table[i].setBackground(Color.LIGHT_GRAY);
		}
	}
	
	void showSheet(int index) {
		for ( int i = 0 ; i <= 8 ; i++ ) {
			for ( int j = 0 ; j < 20 ; j++ ) {
				orderPanel[i][j].setVisible(false);
			}
		}
		
		for ( int j = 0 ; j < 20 ; j++ ) {
			Frame.add(orderPanel[index][j]);
			orderPanel[index][j].setVisible(true);
		}
	}
	
	public void setVisible(boolean e) {
		if ( e == true ) {
			tablePanel.setVisible(true);
			for ( int j = 0 ; j < 20 ; j++ ) {
				orderPanel[0][j].setVisible(true);
			}
			menuPanel.setVisible(true);
			pricePanel.setVisible(true);
			buttonPanel.setVisible(true);
		}else {
			tablePanel.setVisible(false);
			for ( int i = 0 ; i < 9 ; i++ ) {
				for ( int j = 0 ; j < 20 ; j++ ) {
					orderPanel[i][j].setVisible(false);
				}
			}
			menuPanel.setVisible(false);
			pricePanel.setVisible(false);
			buttonPanel.setVisible(false);
		}
	}
	
	public void setMenu() {
		numberOfMenu = Base.M.getNumberOfMenu();
		String[] temp1 = Base.M.getNameOfMenu();
		String[] temp2 = Base.M.getPriceOfMenu();
		for ( int i = 0 ; i < numberOfMenu ; i++ ) {
			Menu[i][0] = temp1[i];
			Menu[i][1] = temp2[i];
		}
		
	}
	
	public int[] getNumberOfOrderedMenu() {
		return numberOfOrderedMenu;
	}
	
	public void resetTable() {
		if ( SelectedTable != -1 ) {
			for ( int i = 0 ; i < numberOfOrderedMenu[SelectedTable] ; i++ ) {
				menuStr[SelectedTable][i] = "";
				priceOfMenu[SelectedTable][i] = "";
				priceOfTable[SelectedTable] = 0;
				
				orderPanel[SelectedTable][i*2].removeAll();
				orderPanel[SelectedTable][i*2+1].removeAll();
				Table[SelectedTable].removeAll();
				
			}
			numberOfOrderedMenu[SelectedTable] = 0;
			showSheet(SelectedTable);
			flag[SelectedTable] = false;
			SelectedTable = -1;
			SelectedIndex = -1;
			newFrame.dispose();
		}
	}
	
	public void reset() {
		resetTableColor();
		SelectedTable = -1;
		SelectedIndex = -1;
		showSheet(0);
	}
}