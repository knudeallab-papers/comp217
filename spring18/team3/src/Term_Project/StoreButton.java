package Term_Project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class StoreButton implements ActionListener{
	
	JFrame Frame = new JFrame();
	JFrame newFrame;
	JPanel namePanel = new JPanel();
	JPanel leftPanel = new JPanel();
	JPanel orderPanel = new JPanel();
	JPanel pricePanel = new JPanel();
	JPanel stuffPanel = new JPanel();
	JPanel infoPanel = new JPanel();
	JButton[][] stuffButton = new JButton[10][4];
	JButton orderButton = new JButton("주문");
	JButton notButton = new JButton("주문 취소");
	JPanel buttonPanel = new JPanel();
	JButton addBtn = new JButton("추가");
	JButton deleteBtn = new JButton("삭제");
	JLabel[] infoLabel = new JLabel[6];
	
	int numberOfStuff = 0;
	int SelectedIndex = -1;
	String[] nameOfStuff = new String[10];
	String[] leftOfStuff = new String[10];
	String[] orderOfStuff = new String[10];
	String[] priceOfStuff = new String[10];
	String[] supplyOfStuff = new String[10];
	String[] phoneOfStuff = new String[10];
	
	// addStuff
	JPanel newNamePanel = new JPanel(new GridLayout(1,2));
	JPanel newLeftPanel = new JPanel(new GridLayout(1,2));
	JPanel newOrderPanel = new JPanel(new GridLayout(1,2));
	JPanel newPricePanel = new JPanel(new GridLayout(1,2));
	JPanel newSupplyPanel = new JPanel(new GridLayout(1,2));
	JPanel newPhonePanel = new JPanel(new GridLayout(1,2));
	JPanel newButtonPanel = new JPanel(new FlowLayout());
	
	JLabel nameLabel = new JLabel("이름", SwingConstants.CENTER);
	JLabel leftLabel = new JLabel("재고", SwingConstants.CENTER);
	JLabel orderLabel = new JLabel("주문", SwingConstants.CENTER);
	JLabel priceLabel = new JLabel("가격", SwingConstants.CENTER);
	JLabel supplyLabel = new JLabel("판매처", SwingConstants.CENTER);
	JLabel phoneLabel = new JLabel("연락처", SwingConstants.CENTER);
	
	JTextField nameTF = new JTextField(20);
	JTextField leftTF = new JTextField(20);
	JTextField orderTF = new JTextField(20);
	JTextField priceTF = new JTextField(20);
	JTextField supplyTF = new JTextField(20);
	JTextField phoneTF = new JTextField(20);
	
	JButton addButton = new JButton("확인");
	JButton cancelButton = new JButton("취소");
	
	//deleteStuff
	JButton Yes = new JButton("예");
	JButton No = new JButton("아니오");
	
	//주문
	JFrame newFrame2;
	JButton Yes2 = new JButton("확인");
	JButton No2 = new JButton("취소");
	JLabel orderLabel1 = new JLabel("주문량",SwingConstants.CENTER);
	JLabel orderLabel2 = new JLabel("1개당 가격",SwingConstants.CENTER);
	JPanel orderPanel1;
	JPanel orderPanel2;
	JPanel orderPanel3;
	JTextField orderText1 = new JTextField(30);
	JTextField orderText2 = new JTextField(30);
	
	//주문취소
	JButton Yes3 = new JButton("확인");
	JButton No3 = new JButton("취소");
	JLabel orderCancelLabel1 = new JLabel("현재 주문량",SwingConstants.CENTER);
	JLabel orderCancelLabel1_2;
	JLabel orderCancelLabel2 = new JLabel("주문 취소량",SwingConstants.CENTER);
	JPanel orderCancelPanel1;
	JPanel orderCancelPanel2;
	JPanel orderCancelPanel3;
	JTextField orderCancelText = new JTextField(30);
	
	boolean[] IsOrder = new boolean[8];

	public StoreButton(JFrame frame) {
		
		for ( int i = 0 ; i < 8 ; i++ ) {
			IsOrder[i] = false;
		}
		
		for ( int i = 0 ; i < 8 ; i++ ) {
			nameOfStuff[i] = "";
			leftOfStuff[i] = "";
			orderOfStuff[i] = "0";
			priceOfStuff[i] = "0";
			supplyOfStuff[i] = "";
			phoneOfStuff[i] = "";
		}
		
		readStore();
		
		Frame = frame;
		
		infoPanel.setBorder(new LineBorder(Color.GRAY,2,true));
		infoPanel.setBounds(299,89,301,289);
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setLayout(null);
		
		orderButton.setBounds(100,220,50,35);
		orderButton.addActionListener(this);
		
		notButton.setBounds(160,220,100,35);
		notButton.addActionListener(this);
		
		infoPanel.add(orderButton);
		infoPanel.add(notButton);
		
		frame.add(infoPanel);
		infoPanel.setVisible(false);
		infoPanel.setVisible(true);
		
		namePanel.add(new JLabel("이름"));
		namePanel.setBounds(0,89,100,30);
		namePanel.setBorder(new LineBorder(Color.GRAY, 2));
		namePanel.setBackground(Color.WHITE);
		
		leftPanel.add(new JLabel("재고"));
		leftPanel.setBounds(99,89,50,30);
		leftPanel.setBorder(new LineBorder(Color.GRAY, 2));
		leftPanel.setBackground(Color.WHITE);
		
		orderPanel.add(new JLabel("주문"));
		orderPanel.setBounds(148,89,50,30);
		orderPanel.setBorder(new LineBorder(Color.GRAY, 2));
		orderPanel.setBackground(Color.WHITE);
		
		pricePanel.add(new JLabel("가격"));
		pricePanel.setBounds(197,89,103,30);
		pricePanel.setBorder(new LineBorder(Color.GRAY, 2));
		pricePanel.setBackground(Color.WHITE);
		
		frame.add(namePanel);
		frame.add(leftPanel);
		frame.add(orderPanel);
		frame.add(pricePanel);
		
		namePanel.setVisible(false);
		leftPanel.setVisible(false);
		orderPanel.setVisible(false);
		pricePanel.setVisible(false);
		
		namePanel.setVisible(true);
		leftPanel.setVisible(true);
		orderPanel.setVisible(true);
		pricePanel.setVisible(true);
		
		stuffPanel.setBorder(new LineBorder(Color.GRAY, 2,true));
		stuffPanel.setBounds(0,118,300,200);
		stuffPanel.setBackground(Color.WHITE);
		stuffPanel.setLayout(null);
		
		frame.add(stuffPanel);
		stuffPanel.setVisible(false);
		stuffPanel.setVisible(true);
		
		for ( int i = 0 ; i < 8 ; i++ ) {
			for ( int j = 0 ; j < 4 ; j++ ) {
				if ( j == 0 ) {
					stuffButton[i][j] = new JButton(nameOfStuff[i]);
				}else if ( j == 1 ) {
					stuffButton[i][j] = new JButton(leftOfStuff[i]);
				}else if ( j == 2 ) {
					if ( i < numberOfStuff ) {
						stuffButton[i][j] = new JButton(orderOfStuff[i]);
					}else {
						stuffButton[i][j] = new JButton();
					}
				}else if ( j == 3 ) {
					if ( i < numberOfStuff ) {
						stuffButton[i][j] = new JButton(priceOfStuff[i]);
					}else {
						stuffButton[i][j] = new JButton();
					}
				}
				stuffButton[i][j].setBorder(new LineBorder(Color.GRAY, 2));
				stuffButton[i][j].setBackground(Color.WHITE);
				stuffButton[i][j].setOpaque(true);
				stuffButton[i][j].addActionListener(this);
			}
			stuffButton[i][0].setBounds(0,25*i,100,25);
			stuffButton[i][1].setBounds(99,25*i,50,25);
			stuffButton[i][2].setBounds(148,25*i,50,25);
			stuffButton[i][3].setBounds(197,25*i,103,25);
		}
		
		for ( int i = 0 ; i < 8 ; i++ ) {
			for ( int j = 0 ; j < 4 ; j++ ) {
				stuffPanel.add(stuffButton[i][j]);
			}
		}
		
		buttonPanel.setBorder(new LineBorder(Color.GRAY, 2, true));
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBounds(0,315,300,63);
		buttonPanel.setLayout(null);
		
		addBtn.setBounds(190, 15, 50, 35);
		addBtn.addActionListener(this);
		deleteBtn.setBounds(240, 15, 50, 35);
		deleteBtn.addActionListener(this);
		
		buttonPanel.add(addBtn);
		buttonPanel.add(deleteBtn);
		
		frame.add(buttonPanel);
		
		buttonPanel.setVisible(false);
		buttonPanel.setVisible(true);
		
		frame.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if ( obj == addBtn ) {
			addStuff();
			resetColor();
			resetInfo();
		}else if ( obj == deleteBtn ) {
			if ( SelectedIndex != -1 && SelectedIndex < numberOfStuff ) {
				deleteStuff();
			}
		}else if ( obj == addButton ) {
			updateStuff();
			nameTF.setText("");
			leftTF.setText("");
			supplyTF.setText("");
			phoneTF.setText("");
			SelectedIndex = -1;
		}else if ( obj == cancelButton ) {
			newFrame.dispose();
			nameTF.setText("");
			leftTF.setText("");
			supplyTF.setText("");
			phoneTF.setText("");
			SelectedIndex = -1;
		}else if ( obj == Yes) {
			if ( SelectedIndex != -1 ) {
				numberOfStuff--;
				if ( numberOfStuff >= 0 ) {
					for ( int i = SelectedIndex ; i < numberOfStuff ; i++ ) {
						nameOfStuff[i] = nameOfStuff[i+1];
						leftOfStuff[i] = leftOfStuff[i+1];
						orderOfStuff[i] = orderOfStuff[i+1];
						priceOfStuff[i] = priceOfStuff[i+1];
						supplyOfStuff[i] = supplyOfStuff[i+1];
						phoneOfStuff[i] = phoneOfStuff[i+1];
						for ( int j = 0 ; j < 4 ; j++ ) {
							stuffButton[i][j].setText(stuffButton[i+1][j].getText());
						}
					}
					nameOfStuff[numberOfStuff] = "";
					leftOfStuff[numberOfStuff] = "";
					orderOfStuff[numberOfStuff] = "0";
					priceOfStuff[numberOfStuff] = "0";
					supplyOfStuff[numberOfStuff] = "";
					phoneOfStuff[numberOfStuff] = "";
					for ( int j = 0 ; j < 4 ; j++ ) {
						stuffButton[numberOfStuff][j].setText("");
					}
				}
			}
			resetColor();
			SelectedIndex = -1;
			resetInfo();
			newFrame.dispose();
		}else if ( obj == No ) {
			newFrame.dispose();
		}else if ( obj == orderButton ) {
			if ( SelectedIndex != -1 && SelectedIndex < numberOfStuff ) {
				newFrame2 = new JFrame("주문");
				newFrame2.setLayout(new GridLayout(3,1));
				newFrame2.setSize(250, 150);
				newFrame2.setLocation(450,250);
				newFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				newFrame2.getContentPane().setBackground(Color.WHITE);
				
				orderPanel1 = new JPanel(new GridLayout(1,2));
				orderPanel2 = new JPanel(new GridLayout(1,2));
				orderPanel3 = new JPanel(new FlowLayout());
				
				Yes2.addActionListener(this);
				No2.addActionListener(this);
				orderPanel3.add(Yes2);
				orderPanel3.add(No2);
				
				orderPanel1.add(orderLabel1);
				orderPanel1.add(orderText1);
				
				orderPanel2.add(orderLabel2);
				orderPanel2.add(orderText2);
				
				newFrame2.add(orderPanel1);
				newFrame2.add(orderPanel2);
				newFrame2.add(orderPanel3);
				
				newFrame2.setVisible(true);
			}
		}else if ( obj == Yes2 ) {
			if ( SelectedIndex != -1 && SelectedIndex < numberOfStuff ) {
				int amount = Integer.parseInt(orderText1.getText());
				if ( amount > 0 ) {
					IsOrder[SelectedIndex] = true;
					int pricePerOne = Integer.parseInt(orderText2.getText());
					orderOfStuff[SelectedIndex] = String.valueOf(amount);
					priceOfStuff[SelectedIndex] = String.valueOf(amount*pricePerOne);
					stuffButton[SelectedIndex][2].setText(orderOfStuff[SelectedIndex]);
					stuffButton[SelectedIndex][3].setText(priceOfStuff[SelectedIndex]);
					resetInfo();
					orderText1.setText("");
					orderText2.setText("");
					newFrame2.dispose();
					resetColor();
					SelectedIndex = -1;
				}
			}
		}else if ( obj == No2 ) {
			SelectedIndex = -1;
			resetColor();
			resetInfo();
			orderText1.setText("");
			orderText2.setText("");
			newFrame2.dispose();
		}else if ( obj == notButton ) {
			if ( SelectedIndex != -1 && SelectedIndex < numberOfStuff ) {
				newFrame2 = new JFrame("주문취소");
				newFrame2.setLayout(new GridLayout(3,1));
				newFrame2.setSize(250, 150);
				newFrame2.setLocation(450,250);
				newFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				newFrame2.getContentPane().setBackground(Color.WHITE);
				
				orderCancelPanel1 = new JPanel(new GridLayout(1,2));
				orderCancelPanel2 = new JPanel(new GridLayout(1,2));
				orderCancelPanel3 = new JPanel(new FlowLayout());
				
				Yes3.addActionListener(this);
				No3.addActionListener(this);
				orderCancelPanel3.add(Yes3);
				orderCancelPanel3.add(No3);
				
				orderCancelLabel1_2 = new JLabel(orderOfStuff[SelectedIndex]);
				orderCancelPanel1.add(orderCancelLabel1);
				orderCancelPanel1.add(orderCancelLabel1_2);
				
				orderCancelPanel2.add(orderCancelLabel2);
				orderCancelPanel2.add(orderCancelText);
				
				newFrame2.add(orderCancelPanel1);
				newFrame2.add(orderCancelPanel2);
				newFrame2.add(orderCancelPanel3);
				
				newFrame2.setVisible(true);
			}
		}else if ( obj == Yes3 ) {
			
			if ( SelectedIndex != -1 && SelectedIndex < numberOfStuff ) {
				int amount = Integer.parseInt(orderOfStuff[SelectedIndex]) - Integer.parseInt(orderCancelText.getText());
				if ( amount >= 0 ) {
					int pricePerOne = Integer.parseInt(priceOfStuff[SelectedIndex]) / Integer.parseInt(orderOfStuff[SelectedIndex]);
					if ( amount == 0 ) {
						IsOrder[SelectedIndex] = false;
					}
					orderOfStuff[SelectedIndex] = String.valueOf(amount);
					priceOfStuff[SelectedIndex] = String.valueOf(amount*pricePerOne);
					stuffButton[SelectedIndex][2].setText(orderOfStuff[SelectedIndex]);
					stuffButton[SelectedIndex][3].setText(priceOfStuff[SelectedIndex]);
					resetInfo();
					orderCancelText.setText("");
					newFrame2.dispose();
					resetColor();
					SelectedIndex = -1;
				}
			}
		}else if ( obj == No3 ) {
			SelectedIndex = -1;
			resetColor();
			resetInfo();
			orderCancelText.setText("");
			newFrame2.dispose();
		}else {
			resetColor();
			for ( int i = 0 ; i < 8 ; i++ ) {
				if ( obj == stuffButton[i][0] || obj == stuffButton[i][1] || obj == stuffButton[i][2] || obj == stuffButton[i][3] ) {
					SelectedIndex = i;
					for ( int j = 0 ; j < 4 ; j++ ) {
						stuffButton[i][j].setBackground(Color.LIGHT_GRAY);
					}
					break;
				}
			}
			if ( SelectedIndex < numberOfStuff ) {
				resetInfo();
				showInfo();
			}else {
				resetInfo();
			}
		}
		
	}
	
	public void resetColor() {
		for ( int i = 0 ; i < 8 ; i++ ) {
			for ( int j = 0 ; j < 4 ; j++ ) {
				stuffButton[i][j].setBackground(Color.WHITE);
			}
		}
	}
	
	public void addStuff() {
		newFrame = new JFrame("재료 추가하기");
		newFrame.setSize(350, 250);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newFrame.getContentPane().setBackground(Color.WHITE);
		newFrame.setLayout(new GridLayout(5,1));
		newFrame.setLocation(200,150);
		
		addButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		newNamePanel.add(nameLabel);
		newNamePanel.add(nameTF);
		newLeftPanel.add(leftLabel);
		newLeftPanel.add(leftTF);
		newSupplyPanel.add(supplyLabel);
		newSupplyPanel.add(supplyTF);
		newPhonePanel.add(phoneLabel);
		newPhonePanel.add(phoneTF);
		newButtonPanel.add(addButton);
		newButtonPanel.add(cancelButton);
		
		newFrame.add(newNamePanel);
		newFrame.add(newLeftPanel);
		newFrame.add(newSupplyPanel);
		newFrame.add(newPhonePanel);
		newFrame.add(newButtonPanel);
		
		newNamePanel.setVisible(true);
		newLeftPanel.setVisible(true);
		newSupplyPanel.setVisible(true);
		newPhonePanel.setVisible(true);
		newButtonPanel.setVisible(true);
		
		newFrame.setVisible(true);
	}
	
	public void updateStuff() {
		nameOfStuff[numberOfStuff] = nameTF.getText();
		leftOfStuff[numberOfStuff] = leftTF.getText();
		supplyOfStuff[numberOfStuff] = supplyTF.getText();
		phoneOfStuff[numberOfStuff] = phoneTF.getText();
		
		
		if ( !nameOfStuff[numberOfStuff].equals("") && !leftOfStuff[numberOfStuff].equals("") && !supplyOfStuff[numberOfStuff].equals("") && !phoneOfStuff[numberOfStuff].equals("") ) {
			stuffButton[numberOfStuff][0].setText(nameOfStuff[numberOfStuff]);
			stuffButton[numberOfStuff][1].setText(leftOfStuff[numberOfStuff]);
			stuffButton[numberOfStuff][2].setText("0");
			stuffButton[numberOfStuff][3].setText("0");
			numberOfStuff++;
		}
		newFrame.dispose();
	}
	
	public void deleteStuff() {
		newFrame = new JFrame("재료 삭제");
		newFrame.setSize(300, 200);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newFrame.getContentPane().setBackground(Color.WHITE);
		newFrame.setLayout(null);
		newFrame.setLocation(200,150);
		
		JLabel bLable = new JLabel("정말로 삭제하시겠습니까?");
		bLable.setBounds(80,50,200,30);
		
		Yes.setBounds(60, 150, 80, 30);
		Yes.addActionListener(this);
		No.setBounds(160, 150, 80, 30);
		No.addActionListener(this);
		
		newFrame.add(bLable);
		newFrame.add(Yes);
		newFrame.add(No);
		
		newFrame.setVisible(true);
	}
	
	public void showInfo() {
		infoLabel[0] = new JLabel("이름 : " + nameOfStuff[SelectedIndex]);
		infoLabel[1] = new JLabel("가격 : " + priceOfStuff[SelectedIndex]);
		infoLabel[2] = new JLabel("판매처 : " + supplyOfStuff[SelectedIndex]);
		infoLabel[3] = new JLabel("연락처 : " + phoneOfStuff[SelectedIndex]);
		infoLabel[4] = new JLabel("수량 : " + leftOfStuff[SelectedIndex]);
		infoLabel[5] = new JLabel("주문량 : " + orderOfStuff[SelectedIndex]);
		
		infoLabel[0].setBounds(50,30,200,20);
		infoLabel[1].setBounds(50,60,200,20);
		infoLabel[2].setBounds(50,90,200,20);
		infoLabel[3].setBounds(50,120,200,20);
		infoLabel[4].setBounds(50,150,200,20);
		infoLabel[5].setBounds(50,180,200,20);
		
		infoPanel.add(infoLabel[0]);
		infoPanel.add(infoLabel[1]);
		infoPanel.add(infoLabel[2]);
		infoPanel.add(infoLabel[3]);
		infoPanel.add(infoLabel[4]);
		infoPanel.add(infoLabel[5]);
		
		infoPanel.setVisible(false);
		infoPanel.setVisible(true);
	}
	
	public void resetInfo() {
		infoPanel.removeAll();
		infoPanel.add(orderButton);
		infoPanel.add(notButton);
		
		infoPanel.setVisible(false);
		infoPanel.setVisible(true);
	}
	
	public void setVisible(boolean e) {
		if ( e == true ) {
			namePanel.setVisible(true);
			leftPanel.setVisible(true);
			orderPanel.setVisible(true);
			pricePanel.setVisible(true);
			stuffPanel.setVisible(true);
			infoPanel.setVisible(true);
			buttonPanel.setVisible(true);
		}else {
			namePanel.setVisible(false);
			leftPanel.setVisible(false);
			orderPanel.setVisible(false);
			pricePanel.setVisible(false);
			stuffPanel.setVisible(false);
			infoPanel.setVisible(false);
			buttonPanel.setVisible(false);
		}
	}
	
	public int update() {
		int totalPrice = 0;
		for ( int i = 0 ; i < 8 ; i++ ) {
			if ( IsOrder[i] == true ) {
				int left = Integer.parseInt(leftOfStuff[i]) + Integer.parseInt(orderOfStuff[i]);
				totalPrice += Integer.parseInt(priceOfStuff[i]);
				leftOfStuff[i] = String.valueOf(left);
				orderOfStuff[i] = "0";
				priceOfStuff[i] = "0";
				stuffButton[i][1].setText(leftOfStuff[i]);
				stuffButton[i][2].setText(orderOfStuff[i]);
				stuffButton[i][3].setText(priceOfStuff[i]);
			}
		}
		for ( int i = 0 ; i < 8 ; i++ ) {
			IsOrder[i] = false;
		}
		
		return totalPrice;
	}
	
	public boolean checkOrder(String[][] menuName, int[] number) {
		int i,j,p,q,r;
		String[] nameOfMenu = Base.M.getNameOfMenu();
		String[][] material = Base.M.getStuff();
		int[][] numberOfMaterial = Base.M.getStuffNumber();
		int numberOfMenu = Base.M.getNumberOfMenu();
		int[] left = new int[8];
		
		for ( i = 0 ; i < numberOfStuff ; i++ ) {
			left[i] = Integer.parseInt(leftOfStuff[i]);
		}
		
		for ( r = 1 ; r <= 8 ; r++ ) {
			for ( i = 0 ; i < number[r] ; i++ ) {
				for ( j = 0 ; j < numberOfMenu ; j++ ) {
					if ( menuName[r][i].equals(nameOfMenu[j])) {
						for ( p = 0 ; p < 5 ; p++ ) {
							for ( q = 0 ; q < numberOfStuff ; q++ ) {
								if ( material[j][p].equals("")) {
									break;
								}
								if ( material[j][p].equals(nameOfStuff[q])) {
									left[q] -= numberOfMaterial[j][p];
									if ( left[q] < 0 ) {
										return false;
									}
									break;
								}
							}
							if ( q == numberOfStuff ){
								return false;
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
	public void afterOrder(String[] menuName, int number) {
		String[] nameOfMenu = Base.M.getNameOfMenu();
		String[][] material = Base.M.getStuff();
		int[][] numberOfMaterial = Base.M.getStuffNumber();
		int numberOfMenu = Base.M.getNumberOfMenu();
		
		for ( int i = 0 ; i < number ; i++ ) {
			for ( int j = 0 ; j < numberOfMenu ; j++ ) {
				if ( menuName[i].equals(nameOfMenu[j])) {
					for ( int p = 0 ; p < 5 ; p++ ) {
						for ( int q = 0 ; q < numberOfStuff ; q++ ) {
							if ( material[j][p].equals(nameOfStuff[q])) {
								int left = Integer.parseInt(leftOfStuff[q]) - numberOfMaterial[j][p];
								leftOfStuff[q] = String.valueOf(left);
								stuffButton[q][1].setText(leftOfStuff[q]);
							}
						}
					}
				}
			}
		}
	}
	
	public void reset() {
		for ( int i = 0 ; i < 8 ; i++ ) {
			for ( int j = 0 ; j < 4 ; j++ ) {
				stuffButton[i][j].setBackground(Color.WHITE);
			}
		}
		SelectedIndex = -1;
	}
	
	public void readStore() {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("StoreFile.txt"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		numberOfStuff = sc.nextInt();
		
		for ( int i = 0 ; i < numberOfStuff ; i++ ) {
			nameOfStuff[i] = sc.next();
			leftOfStuff[i] = sc.next();
			orderOfStuff[i] = sc.next();
			priceOfStuff[i] = sc.next();
			supplyOfStuff[i] = sc.next();
			phoneOfStuff[i] = sc.next();
		}
		
		sc.close();
	}
	
	public void writeStore() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream("StoreFile.txt"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		pw.println(numberOfStuff);
		
		for ( int i = 0 ; i < numberOfStuff ; i++ ) {
			pw.print(nameOfStuff[i]);
			pw.print(" ");
			pw.print(leftOfStuff[i]);
			pw.print(" ");
			pw.print(orderOfStuff[i]);
			pw.print(" ");
			pw.print(priceOfStuff[i]);
			pw.print(" ");
			pw.print(supplyOfStuff[i]);
			pw.print(" ");
			pw.print(phoneOfStuff[i]);
			pw.print("\n");
		}
		pw.close();
	}
	
}
