package Term_Project;

import java.awt.BorderLayout;
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

public class MenuButton implements ActionListener{
	JFrame frame = new JFrame();
	int count = 0;
	int check = 1;
	
	String[] name = new String[10];
	String[] price = new String[10];
	String[] productionUnitPrice = new String[10];
	String[][] material = new String[10][5];
	int[][] materialNumber = new int[10][5]; //재료 가격이 아니라 재료 개수
	
	JButton[] menuButton = new JButton[8];
	
	JFrame plusFrame;
	JFrame plusFrameForMaterial;
	JFrame deleteFrame;
	JFrame editFrame;
	JFrame editFrameForMaterial;
	
	JPanel menuPanel = new JPanel();
	JPanel informationPanel = new JPanel();
	
	JTextField nameField = new JTextField(20);
	JTextField priceField = new JTextField(20);
	JTextField productionUnitPriceField = new JTextField(20);
	
	JLabel[] dataArea = new JLabel[6];
	
	JTextField  [][] materialField = new JTextField[2][5];
	
	JButton edit = new JButton("편집");
	JButton plus = new JButton("추가");
	JButton delete = new JButton("삭제");
	
	JPanel buttonPanel = new JPanel();
	
	public MenuButton(JFrame Frame) {
		
		for ( int i = 0 ; i < 10 ; i++ ) {
			name[i] = "";
			price[i] = "";
			productionUnitPrice[i] = "";
			for ( int j = 0 ; j < 5 ; j++ ) {
				material[i][j] = "";
				materialNumber[i][j] = 0;
			}
		}
		
		readMenu();
		this.frame = Frame;
		
		
		buttonPanel.setLayout(null);
		
		edit.setBounds(115,5,50,40);
		edit.addActionListener(this);
		buttonPanel.add(edit);
		
		plus.setBounds(175,5,50,40);
		plus.addActionListener(this);
		buttonPanel.add(plus);
		
		delete.setBounds(235,5,50,40);
		delete.addActionListener(this);
		buttonPanel.add(delete);
		
		informationPanel.setLayout(null);
		
		buttonPanel.setBorder(new LineBorder(Color.GRAY,2));
		buttonPanel.setBounds(0,240,400,50);
		buttonPanel.setBackground(Color.WHITE);
		informationPanel.add(buttonPanel);
		
		menuPanel.setLayout(new GridLayout(8,1));
		
		for(int i=0; i<8; i++) {
			menuButton[i] = new JButton(name[i]);
			menuButton[i].addActionListener(this);
			menuPanel.add(menuButton[i]);
		}
		
		menuPanel.setBorder(new LineBorder(Color.GRAY,2));
		menuPanel.setBounds(0,89,200,290);
		menuPanel.setBackground(Color.WHITE);
		frame.add(menuPanel);
		
		informationPanel.setBorder(new LineBorder(Color.GRAY,2));
		informationPanel.setBounds(200,89,400,290);
		informationPanel.setBackground(Color.WHITE);
		
		dataArea[0] = new JLabel();
		dataArea[0].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[0].setBounds(50,30,300,30);
		dataArea[1] = new JLabel();
		dataArea[1].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[1].setBounds(50,70,300,30);
		dataArea[2] = new JLabel();
		dataArea[2].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[2].setBounds(50,110,300,30);
		dataArea[3] = new JLabel();
		dataArea[3].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[3].setBounds(50,150,300,30);
		dataArea[4] = new JLabel();
		dataArea[4].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[4].setBounds(50,170,300,30);
		dataArea[5] = new JLabel();
		dataArea[5].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[5].setBounds(50,190,300,30);
		
		informationPanel.add(dataArea[0]);
		informationPanel.add(dataArea[1]);
		informationPanel.add(dataArea[2]);
		informationPanel.add(dataArea[3]);
		informationPanel.add(dataArea[4]);
		informationPanel.add(dataArea[5]);
		
		informationPanel.setVisible(false);
		frame.add(informationPanel);
		informationPanel.setVisible(true);
		
		frame.setVisible(true);
	}
	
	void showData(int check) {
		String temp = "";
		
		dataArea[0].setText("이름 : "+name[check]);
		dataArea[1].setText("가격 : "+price[check] + "원");
		dataArea[2].setText("생산단가 : "+productionUnitPrice[check] + "원");
		dataArea[3].setText("사용된 재료 : ");
		for ( int i = 0 ; i < 2 ; i++ ) {
			if ( !material[check][i].equals("")) {
				temp += material[check][i] + "(" + materialNumber[check][i] + "개) ";
			}
		}
		dataArea[4].setText(temp);
		temp = "";
		for ( int i = 2 ; i < 5 ; i++ ) {
			if ( !material[check][i].equals("")) {
				temp += material[check][i] + "(" + materialNumber[check][i] + "개) ";
			}
		}
		dataArea[5].setText(temp);
		
		frame.setVisible(true);
	}
	
	void plusButton() {
		plusFrame = new JFrame("추가");
		
		JButton yes = new JButton("예");
		JButton no = new JButton("아니오");
		
		JPanel namePanel = new JPanel();
		JPanel pricePanel = new JPanel();
		JPanel productionUnitPricePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		JLabel nameMessage = new JLabel("                  음식 : ");
		JLabel priceMessage = new JLabel("                  가격 : ");
		JLabel productionUnitPriceMessage = new JLabel("                생산단가 : ");
		
		namePanel.setLayout(new GridLayout(1,2));
		pricePanel.setLayout(new GridLayout(1,2));
		productionUnitPricePanel.setLayout(new GridLayout(1,2));
		
		namePanel.add(nameMessage);
		namePanel.add(nameField);
		pricePanel.add(priceMessage);
		pricePanel.add(priceField);
		productionUnitPricePanel.add(productionUnitPriceMessage);
		productionUnitPricePanel.add(productionUnitPriceField);
		
		
		plusFrame.setSize(375, 250);
		plusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		plusFrame.setLayout(new GridLayout(4,1));
		
		buttonPanel.setLayout(new FlowLayout( ));
		
		
		yes.addActionListener(this);
        buttonPanel.add(yes);
        
        no.addActionListener(this);
        buttonPanel.add(no);
         
       
		plusFrame.add(namePanel);
		plusFrame.add(pricePanel);
		plusFrame.add(productionUnitPricePanel);
		plusFrame.add(buttonPanel);
		
		plusFrame.setVisible(true);
	}
	
	void plusMaterialButton() {
		plusFrameForMaterial = new JFrame("재료 추가");
		
		JButton yes = new JButton("재료추가");
		
		JPanel materialPanel = new JPanel();
		
		JLabel empty = new JLabel("");
		JLabel [] materialLabel = new JLabel[5];
		JLabel [] standard = new JLabel[2];
		
		materialPanel.setBounds(0,0,375,190);
		materialPanel.setLayout(new GridLayout(3,6));
		
		materialPanel.add(empty);
		
		for(int i = 0; i<5; i++) {
			materialLabel[i] = new JLabel("재료"+(i+1), SwingConstants.CENTER);
			materialPanel.add(materialLabel[i]);
		}
		
		standard[0]= new JLabel("재료", SwingConstants.CENTER);
		standard[1]= new JLabel("개수", SwingConstants.CENTER);
		
		for(int j=0; j<2; j++) { 
			materialPanel.add(standard[j]);
			for(int i = 0; i<5; i++) {
				materialField[j][i] = new JTextField(10);
				materialPanel.add(materialField[j][i]);
			}
		}
		
		yes.addActionListener(this);
		yes.setBounds(157,187,60,40);
		
		plusFrameForMaterial.setSize(375, 250);
		plusFrameForMaterial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		plusFrameForMaterial.setLayout(null);
		
		plusFrameForMaterial.add(materialPanel);
		plusFrameForMaterial.add(yes);
		
		plusFrameForMaterial.setVisible(true);
		
	}
	
	
	void plusData () {
		
		name[count] = new String();
		name[count] = nameField.getText();
		price[count] = new String();
		price[count] = priceField.getText();
		productionUnitPrice[count] = new String();
		productionUnitPrice[count] = productionUnitPriceField.getText();

		nameField.setText("");
		priceField.setText("");
		productionUnitPriceField.setText("");
	
		frame.setVisible(true);
		plusFrame.dispose();
    }
	
	void plusMaterialData() {
		for(int i=0; i<5; i++) {
			material[count][i] = new String();
			material[count][i] = materialField[0][i].getText();
			if(material[count][i].equals("")) continue;
			materialNumber[count][i]=Integer.parseInt(materialField[1][i].getText());
		}
		
		for (int i=0; i<2; i++)
			for(int j=0; j<5; j++)
				materialField[i][j].setText("");
	
		if(!(name[count].equals("")||price[count].equals("")||productionUnitPrice[count].equals("")))
		{
		menuPanel.removeAll();
		
		count++;
		
		for(int i=0; i<8; i++) {
			menuButton[i] = new JButton(name[i]);
			menuButton[i].addActionListener(this);
			menuPanel.add(menuButton[i]);
		}
		
		frame.add(menuPanel);
		}
		frame.setVisible(true);
		plusFrameForMaterial.dispose();
		
		
	}
	void editButton() {
		editFrame = new JFrame("편집");
		editFrame.setSize(375, 250);
		
		JButton yes = new JButton("승인");
		JButton no = new JButton("나가기");
		
		JPanel namePanel = new JPanel();
		JPanel pricePanel = new JPanel();
		JPanel productionUnitPricePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		JLabel nameMessage = new JLabel("                  음식 : ");
		JLabel priceMessage = new JLabel("                  가격 : ");
		JLabel productionUnitPriceMessage = new JLabel("                생산단가 : ");
		
		namePanel.setLayout(new GridLayout(1,2));
		pricePanel.setLayout(new GridLayout(1,2));
		productionUnitPricePanel.setLayout(new GridLayout(1,2));
		
		namePanel.add(nameMessage);
		namePanel.add(nameField);
		pricePanel.add(priceMessage);
		pricePanel.add(priceField);
		productionUnitPricePanel.add(productionUnitPriceMessage);
		productionUnitPricePanel.add(productionUnitPriceField);
		
		
		editFrame.setSize(375, 250);
		editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editFrame.setLayout(new GridLayout(4,1));
		
		buttonPanel.setLayout(new FlowLayout( ));
		
		
		yes.addActionListener(this);
        buttonPanel.add(yes);
        
        no.addActionListener(this);
        buttonPanel.add(no);
         
        nameField.setText(name[check]);
		priceField.setText(price[check]);
		productionUnitPriceField.setText(productionUnitPrice[check]);
		
		editFrame.add(namePanel);
		editFrame.add(pricePanel);
		editFrame.add(productionUnitPricePanel);
		editFrame.add(buttonPanel);
		
		editFrame.setVisible(true);
	}
	
	void editMaterialButton(int check) {
		editFrameForMaterial = new JFrame("재료 수정");
		
		JButton yes = new JButton("결정");
		
		JPanel materialPanel = new JPanel();
		
		JLabel empty = new JLabel();
		JLabel [] materialLabel = new JLabel[5];
		JLabel [] standard = new JLabel[2];
		
		materialPanel.setBounds(0,0,375,190);
		materialPanel.setLayout(new GridLayout(3,6));
		
		materialPanel.add(empty);
		
		for(int i = 0; i<5; i++) {
			materialLabel[i] = new JLabel("재료"+(i+1));
			materialPanel.add(materialLabel[i]);
		}
		
		standard[0]= new JLabel("재료");
		standard[1]= new JLabel("개수");
		
		for(int j=0; j<2; j++) {
			materialPanel.add(standard[j]);
			for(int i = 0; i<5; i++) {
				materialField[j][i] = new JTextField(10);
				materialPanel.add(materialField[j][i]);
			
				if(j==0) {
					materialField[j][i].setText(material[check][i]);
				}
				else {
					if(materialNumber[check][i]==0) {
						materialField[j][i].setText("");
					}
					else {
						materialField[j][i].setText(materialNumber[check][i]+"");
					}
				}

			}
		}
		
		
		yes.addActionListener(this);
		yes.setBounds(157,187,60,40);
		
		editFrameForMaterial.setSize(375, 250);
		editFrameForMaterial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editFrameForMaterial.setLayout(null);
		
		editFrameForMaterial.add(materialPanel);
		editFrameForMaterial.add(yes);
		
	    editFrameForMaterial.setVisible(true);
	    
	}
	void editData(int check) {
		
		name[check] = nameField.getText();
		price[check] = priceField.getText();
		productionUnitPrice[check] = productionUnitPriceField.getText();
	
		nameField.setText("");
		priceField.setText("");
		productionUnitPriceField.setText("");
	
		editFrame.dispose();
	}
	
	void editMaterialData(int check) {
		for(int i=0; i<5; i++) {
			material[check][i] = new String();
			material[check][i] = materialField[0][i].getText();
			if ( !materialField[1][i].getText().equals("")) {
				materialNumber[check][i]=Integer.parseInt(materialField[1][i].getText());
			}else {
				materialNumber[check][i]=0;
			}
		}
		
		for (int i=0; i<2; i++) {
			for(int j=0; j<5; j++) {
				materialField[i][j].setText("");
			}
		}
	
		menuPanel.removeAll();
		
		for(int i=0; i<8; i++) {
			menuButton[i] = new JButton(name[i]);
			menuButton[i].addActionListener(this);
			menuPanel.add(menuButton[i]);
		}
		
		frame.add(menuPanel);
		
	
		for (int i = 0; i<6; i++)
			dataArea[i].setText("");
		
		frame.setVisible(true);
		editFrameForMaterial.dispose();
	}
	void deleteButton() {
		deleteFrame = new JFrame("삭제");
		deleteFrame.setSize(375, 250);
		
		JButton yes = new JButton("확인");
		JButton no = new JButton("거절");
		
		yes.addActionListener(this);
		no.addActionListener(this);
		
		deleteFrame.setLayout(new GridLayout(2,1));
		
		JButton questionPanel = new JButton("정말 삭제하시겠습니까?");
		questionPanel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.add(yes);
		buttonPanel.add(no);
		
		deleteFrame.add(questionPanel,BorderLayout.CENTER);
		deleteFrame.add(buttonPanel);
		
		deleteFrame.setVisible(true);
		 
	}
	
	void deleteData(int check) {
		frame.setVisible(false);
		count--;
		for(int i = check; i<count; i++)
		{
			name[i] = name[i+1];
			price[i] = price[i+1];
			productionUnitPrice[i] = productionUnitPrice[i+1];
			for(int j = 0 ; j<4; j++) {
				material[i][j] = material[i][j];
				materialNumber[i][j] = materialNumber[i][j];
			}
		}
		
		for(int i = count; i<8; i++) {
			name[i] = "";
			price[i] = "";
			productionUnitPrice[i] = "";
			for(int j = 0 ; j<5; j++) {
				material[i][j] = "";
				materialNumber[i][j] = 0;
			}
		}
		
		menuPanel.removeAll();
		
		for(int i=0; i<8; i++) {
			menuButton[i] = new JButton(name[i]);
			menuButton[i].addActionListener(this);
			menuPanel.add(menuButton[i]);
		}
		
		frame.add(menuPanel);
		
		for (int i = 0; i<6; i++)
			dataArea[i].setText("");
		
		frame.setVisible(true);
		deleteFrame.dispose();
	}
	public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();
		Object obj = e.getSource();
		if ( btnStr.equals("승인")) {
			editData(check);
			editMaterialButton(check);
		}else if ( btnStr.equals("결정")) {
			editMaterialData(check);
		}else if ( btnStr.equals("예")) {
			plusData();
			if(!(name[count].equals("")||price[count].equals("")||productionUnitPrice[count].equals("")))
				plusMaterialButton();
		}else if ( btnStr.equals("재료추가")) {
			plusMaterialData();
		}else if ( btnStr.equals("확인")) {
			deleteData(check);
		}else if ( btnStr.equals("나가기")) {
			editFrame.dispose();
			nameField.setText("");
			priceField.setText("");
			productionUnitPriceField.setText("");
		}else if ( btnStr.equals("아니오")) {
			plusFrame.dispose();
			nameField.setText("");
			priceField.setText("");
			productionUnitPriceField.setText("");
		}else if ( btnStr.equals("거절")) {
			deleteFrame.dispose();
		}else if ( btnStr.equals("편집")) {
			if(check < count&&check!=-1) 
				editButton();
		}else if ( btnStr.equals("추가")) {
			plusButton();
		}else if ( btnStr.equals("삭제")) {
			if(check < count&&check!=-1)
				deleteButton();
		}else {
			for(int i=0; i<8; i++) {
				if(obj == menuButton[i]) {
					check = i;
					menuButton[i].setBackground(Color.LIGHT_GRAY);
					if (!btnStr.equals(""))
						showData(check);
					else {
						for (int j = 0; j<6; j++)
							dataArea[j].setText("");
						frame.setVisible(true);
					}
						
				}
			}
		}
	}
	
	void setVisible(boolean e) {
		if ( e == true ) {
			menuPanel.setVisible(true);
			informationPanel.setVisible(true);
		}else {
			menuPanel.setVisible(false);
			informationPanel.setVisible(false);
		}
	}
	
	public String[] getNameOfMenu() {
		return name;
	}
	
	public String[] getPriceOfMenu() {
		return price;
	}
	
	public String[][] getStuff(){
		return material;
	}
	
	public int[][] getStuffNumber(){
		return materialNumber;
	}
	
	public int getNumberOfMenu() {
		return count;
	}
	
	public void reset() {
		for ( int i = 0 ; i < 8 ; i++ ) {
			menuButton[i].setBackground(Color.WHITE);
			check = -1;
		}
		informationPanel.removeAll();
		informationPanel.add(buttonPanel);
		dataArea[0] = new JLabel();
		dataArea[0].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[0].setBounds(50,30,300,30);
		dataArea[1] = new JLabel();
		dataArea[1].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[1].setBounds(50,70,300,30);
		dataArea[2] = new JLabel();
		dataArea[2].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[2].setBounds(50,110,300,30);
		dataArea[3] = new JLabel();
		dataArea[3].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[3].setBounds(50,150,300,30);
		dataArea[4] = new JLabel();
		dataArea[4].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[4].setBounds(50,170,300,30);
		dataArea[5] = new JLabel();
		dataArea[5].setBorder(new LineBorder(Color.WHITE,2));
		dataArea[5].setBounds(50,190,300,30);
		
		informationPanel.add(dataArea[0]);
		informationPanel.add(dataArea[1]);
		informationPanel.add(dataArea[2]);
		informationPanel.add(dataArea[3]);
		informationPanel.add(dataArea[4]);
		informationPanel.add(dataArea[5]);
	}
	
	private void readMenu() {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("MenuFile.txt"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		count = sc.nextInt();
		
		for ( int i = 0 ; i < count ; i++ ) {
			int count2 = 0;
			name[i] = sc.next();
			price[i] = sc.next();
			productionUnitPrice[i] = sc.next();
			for ( int j = 0 ; !sc.hasNextInt() ; j++ ) {
				material[i][j] = sc.next();
				count2++;
			}
			for ( int j = 0 ; j < count2 ; j++ ) {
				materialNumber[i][j] = sc.nextInt();
			}
		}
		sc.close();
	}
	
	public void writeMenu() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream("MenuFile.txt"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		pw.println(count);
		for ( int i = 0 ; i < count ; i++ ) {
			pw.print(name[i]);
			pw.print(" ");
			pw.print(price[i]);
			pw.print(" ");
			pw.print(productionUnitPrice[i]);
			pw.print("\n");
			for ( int j = 0 ; j < 5 ; j++ ) {
				if ( !material[i][j].equals("")) {
					pw.print(material[i][j]);
					pw.print(" ");
				}
			}
			pw.println();
			for ( int j = 0 ; j < 5 ; j++ ) {
				if ( materialNumber[i][j] != 0) {
					pw.print(materialNumber[i][j]);
					pw.print(" ");
				}
			}
			pw.println();
		}
		pw.close();
	}
}
