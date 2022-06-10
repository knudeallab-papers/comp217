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

public class StaffButton implements ActionListener{
	
	int which=1;
	int count=0;
	public JLabel[][] staffLabel = new JLabel[9][6];
	public String[][] staffData = new String[9][6];
	static JButton[][] staffWindow = new JButton[10][7];
	JButton edit = new JButton("편집");
	JButton plus = new JButton("추가");
	JButton delete = new JButton("삭제");
	JFrame frame = new JFrame();
	
	JPanel ButtonPanel = new JPanel();
	JPanel FunctionPanel = new JPanel();
	
	public StaffButton(JFrame Frame) {
		
		for ( int i = 0 ; i < 9 ; i++ ) {
			for ( int j = 0 ; j < 6 ; j++ ) {
				staffData[i][j] = "";
			}
		}
		
		readStaff();
		
		frame=Frame;
		
		
		staffWindow[0][0] = new JButton("번호");
		staffWindow[0][0].setBorder(new LineBorder(Color.GRAY,2,true));
		staffWindow[0][0].setBounds(0,89,40,30);
		staffWindow[0][0].setBackground(Color.GRAY);
		
		staffWindow[0][1] = new JButton("이름");
		staffWindow[0][1].setBorder(new LineBorder(Color.GRAY,2,true));
		staffWindow[0][1].setBounds(39,89,101,30);
		staffWindow[0][1].setBackground(Color.GRAY);
		
		staffWindow[0][2] = new JButton("급여");
		staffWindow[0][2].setBorder(new LineBorder(Color.GRAY,2,true));
		staffWindow[0][2].setBounds(139,89,101,30);
		staffWindow[0][2].setBackground(Color.GRAY);
		
		staffWindow[0][3] = new JButton("직급");
		staffWindow[0][3].setBorder(new LineBorder(Color.GRAY,2,true));
		staffWindow[0][3].setBounds(239,89,101,30);
		staffWindow[0][3].setBackground(Color.GRAY);
		
		staffWindow[0][4] = new JButton("입사");
		staffWindow[0][4].setBorder(new LineBorder(Color.GRAY,2,true));
		staffWindow[0][4].setBounds(339,89,131,30);
		staffWindow[0][4].setBackground(Color.GRAY);
		
		staffWindow[0][5] = new JButton("연락처");
		staffWindow[0][5].setBorder(new LineBorder(Color.GRAY,2,true));
		staffWindow[0][5].setBounds(469,89,131,30);
		staffWindow[0][5].setBackground(Color.GRAY);
		
		for (int i = 0 ; i <= 5 ; i++ ) {
			staffWindow[0][i].setVisible(false);
			frame.add(staffWindow[0][i]);
			staffWindow[0][i].setVisible(true);
		}
		
		ButtonPanel.setLayout(null);
		ButtonPanel.setBorder(new LineBorder(Color.GRAY,2,true));
		ButtonPanel.setBackground(Color.WHITE);
		ButtonPanel.setBounds(0,89,600,240);
		
		for ( int i = 1 ; i < 8 ; i++ ) {
			for ( int j = 0 ; j < 6 ; j++ ) {
				staffWindow[i][j] = new JButton(staffData[i-1][j]);
				staffWindow[i][j].setBorder(new LineBorder(Color.GRAY,2));
				staffWindow[i][j].setOpaque(true);
				staffWindow[i][j].setBackground(Color.WHITE);
			}
			staffWindow[i][0].setBounds(0,30*i,40,30);
			staffWindow[i][1].setBounds(39,30*i,101,30);
			staffWindow[i][2].setBounds(139,30*i,101,30);
			staffWindow[i][3].setBounds(239,30*i,101,30);
			staffWindow[i][4].setBounds(339,30*i,131,30);
			staffWindow[i][5].setBounds(469,30*i,131,30);	
		}
		
		for ( int i = 1 ; i < 8 ; i++ ) {
			for ( int j = 0 ; j < 6 ; j++ ) {
				staffWindow[i][j].addActionListener(this);
				ButtonPanel.add(staffWindow[i][j]);
			}
		}
		
		
		
		ButtonPanel.setVisible(false);
		frame.add(ButtonPanel);
		ButtonPanel.setVisible(true);
		
		FunctionPanel.setBounds(0,319,600,59);
		FunctionPanel.setBackground(Color.WHITE);
		FunctionPanel.setBorder(new LineBorder(Color.GRAY,2,true));
		FunctionPanel.setLayout(null);
		
		edit.addActionListener(this);
		edit.setBounds(390, 14, 60, 40);
		plus.addActionListener(this);
		plus.setBounds(460, 14, 60, 40);
		delete.addActionListener(this);
		delete.setBounds(530, 14, 60, 40);
		
		FunctionPanel.add(edit);
		FunctionPanel.add(plus);
		FunctionPanel.add(delete);
		
		FunctionPanel.setVisible(false);
		frame.add(FunctionPanel);
		FunctionPanel.setVisible(true);
		
		frame.setVisible(true);
	
	}

	public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();
		Object obj = e.getSource();
		
		if ( btnStr.equals("아니오")) {
			plusFrame.dispose();
			nameField.setText("");
			salaryField.setText("");
			stateField.setText("");
			phoneNumberField.setText("");
		}else if ( btnStr.equals("추가")) { 
			plusButton();
		}else if ( btnStr.equals("수정")) {
			editData(which);
		}else if ( btnStr.equals("삭제")) {
			if(which <= count)
				deleteButton();
		}else if ( btnStr.equals("예")) {
			getData();
		}else if ( btnStr.equals("확인")) {
			deleteData(which);
		}else if ( btnStr.equals("취소")) {
			deleteFrame.dispose();
		}else if ( btnStr.equals("편집")) {
			if(which <= count)
				editButton();
		}else if ( btnStr.equals("나가기")) {
			nameField.setText("");
			salaryField.setText("");
			stateField.setText("");
			phoneNumberField.setText("");
			editFrame.dispose();
		}else {
			resetColor();
			for(int i=1; i<8; i++) {
				if(obj == staffWindow[i][0]||obj ==staffWindow[i][1]||obj ==staffWindow[i][2]||obj ==staffWindow[i][3]||obj ==staffWindow[i][4]||obj ==staffWindow[i][5]) {
					which = i;
					for(int j=0; j<6; j++) {
						staffWindow[i][j].setBackground(Color.LIGHT_GRAY);
					}
				}
			}
		}
		
	}
	
	JTextField nameField = new JTextField(20);
	JTextField salaryField = new JTextField(20);
	JTextField stateField = new JTextField(20);
	JTextField phoneNumberField = new JTextField(20);
	
	JFrame plusFrame;
	JFrame deleteFrame;
	JFrame editFrame;
	
	void editButton() {
		editFrame = new JFrame("편집");
		editFrame.setSize(375, 250);
		
		JButton yes = new JButton("수정");
		JButton no = new JButton("나가기");
		
		yes.addActionListener(this);
		no.addActionListener(this);
		
		editFrame.setLayout(new GridLayout(5,1));
		
		JPanel name = new JPanel();
		name.setLayout(new GridLayout(1,2));
		JPanel salary = new JPanel();
		salary.setLayout(new GridLayout(1,2));
		JPanel state = new JPanel();
		state.setLayout(new GridLayout(1,2));
		JPanel phoneNumber = new JPanel();
		phoneNumber.setLayout(new GridLayout(1,2));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		
		
		JLabel nameLabel = new JLabel("이름 : ",SwingConstants.CENTER);
		name.add(nameLabel);
		name.add(nameField);
		nameField.setText(staffData[which-1][1]);
		
		JLabel salaryLabel = new JLabel("급여 : ",SwingConstants.CENTER);
		salary.add(salaryLabel);
		salary.add(salaryField);
		salaryField.setText(staffData[which-1][2]);
		
		JLabel stateLabel = new JLabel("직급 : ",SwingConstants.CENTER);
		state.add(stateLabel);
		state.add(stateField);
		stateField.setText(staffData[which-1][3]);
		
		JLabel phoneNumberLabel = new JLabel("연락처 : ",SwingConstants.CENTER);
		phoneNumber.add(phoneNumberLabel);
		phoneNumber.add(phoneNumberField);
		phoneNumberField.setText(staffData[which-1][5]);
		
		buttonPanel.setLayout(new FlowLayout( ));
		
		buttonPanel.add(yes);
		buttonPanel.add(no);
		
		editFrame.add(name);
		editFrame.add(salary);
		editFrame.add(state);
		editFrame.add(phoneNumber);
		editFrame.add(buttonPanel);
		
		editFrame.setVisible(true);
	}
	
	void deleteButton() {
		deleteFrame = new JFrame("삭제");
		deleteFrame.setSize(375, 250);
		
		JButton yes = new JButton("확인");
		JButton no = new JButton("취소");
		
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
	void plusButton () {
		
		plusFrame = new JFrame("추가");
		
		JButton yes = new JButton("예");
		JButton no = new JButton("아니오");
		
		JPanel name = new JPanel();
		name.setLayout(new GridLayout(1,2));
		JPanel salary = new JPanel();
		salary.setLayout(new GridLayout(1,2));
		JPanel state = new JPanel();
		state.setLayout(new GridLayout(1,2));
		JPanel phoneNumber = new JPanel();
		phoneNumber.setLayout(new GridLayout(1,2));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		
		JLabel nameLabel = new JLabel("이름 : ",SwingConstants.CENTER);
		name.add(nameLabel);
		name.add(nameField);
		
		JLabel salaryLabel = new JLabel("급여 : ",SwingConstants.CENTER);
		salary.add(salaryLabel);
		salary.add(salaryField);
		
		JLabel stateLabel = new JLabel("직급 : ",SwingConstants.CENTER);
		state.add(stateLabel);
		state.add(stateField);
		
		JLabel phoneNumberLabel = new JLabel("연락처 : ",SwingConstants.CENTER);
		phoneNumber.add(phoneNumberLabel);
		phoneNumber.add(phoneNumberField);
		
		plusFrame.setSize(375, 250);
		plusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		plusFrame.setLayout(new GridLayout(5,1));
		
		buttonPanel.setLayout(new FlowLayout( ));
		
		
		yes.addActionListener(this);
        buttonPanel.add(yes);
        
        no.addActionListener(this);
        buttonPanel.add(no);
         
       
		plusFrame.add(name);
		plusFrame.add(salary);
		plusFrame.add(state);
		plusFrame.add(phoneNumber);
		plusFrame.add(buttonPanel);
		
		plusFrame.setVisible(true);
	}
	
	public String inputName;
	public String inputSalary;
	public String inputState;
	public String inputPhoneNumber;
	
	void getData () {
		inputName = nameField.getText();
		inputSalary = salaryField.getText();
		inputState = stateField.getText();
		inputPhoneNumber = phoneNumberField.getText();
		
		staffData[count][0] = (count+1)+"";
		staffData[count][1] = inputName;
		staffData[count][2] = inputSalary;
		staffData[count][3] = inputState;
		staffData[count][4] = Base.year + "."+Base.month+"."+Base.day;
		staffData[count][5] = inputPhoneNumber;
		
		for(int i=0; i<6; i++)
		{
			staffWindow[count+1][i].setText(staffData[count][i]);
			ButtonPanel.add(staffWindow[count+1][i]);
		}
		
		count++;
		
		nameField.setText("");
		salaryField.setText("");
		stateField.setText("");
		phoneNumberField.setText("");
		
		frame.setVisible(true);
		plusFrame.dispose();
	}
	
    void deleteData(int which) {
    		
    		if(which <= count) {
    		frame.setVisible(false);
    		count--;
    		int tempCount = which;
    		for(int i = which-1; i<count; i++)
    		{
    			for(int j = 0; j<6; j++)
    			{
    				if(j==0)
    					staffData[i][j] = (tempCount++)+"";
    				else
    					staffData[i][j] = staffData[i+1][j];
    			}
    		}
    		
    		for(int i = count; i<8; i++)
    		{
    			for(int j = 0; j<6; j++)
    			{
    				staffData[i][j] = "";
    			}
    		}
    		
    		for(int j=1; j<8; j++) {
    			for(int i=0; i<6; i++)
        		{
    				staffWindow[j][i].removeAll();
        		}
    		}
    		
    		for(int j=0; j<7; j++) {
    			for(int i=0; i<6; i++)
        		{
        			staffWindow[j+1][i].setText(staffData[j][i]);
        			ButtonPanel.add(staffWindow[j+1][i]);
        		}
    		}
    		
    		}
    		frame.setVisible(true);
    		deleteFrame.dispose();
    		
    }
    
    void editData(int which) {
    		if(which <= count) {
    		inputName = nameField.getText();
		inputSalary = salaryField.getText();
		inputState = stateField.getText();
		inputPhoneNumber = phoneNumberField.getText();
		
		staffData[which-1][0] = which+"";
		staffData[which-1][1] = inputName;
		staffData[which-1][2] = inputSalary;
		staffData[which-1][3] = inputState;
		staffData[which-1][5] = inputPhoneNumber;
		
		for(int i=0; i<6; i++)
			staffWindow[which][i].removeAll();
		
		for(int i=0; i<6; i++)
		{
			staffWindow[which][i].setText(staffData[which-1][i]);
			ButtonPanel.add(staffWindow[which][i]);
		}
    		}
		nameField.setText("");
		salaryField.setText("");
		stateField.setText("");
		phoneNumberField.setText("");
    		
		frame.setVisible(true);
		editFrame.dispose();
    }
  
    public void resetColor() {
    		for(int i=1; i<8; i++) {
    			for(int j=0; j<6; j++)
    			{
    				staffWindow[i][j].setBackground(Color.WHITE);
    			}
    		}
    }
    
    public void setVisible(boolean e) {
		if ( e == true ) {
			for ( int i = 0 ; i < 6 ; i++ ) {
				staffWindow[0][i].setVisible(true);
			}
			ButtonPanel.setVisible(true);
			FunctionPanel.setVisible(true);
		}else {
			for ( int i = 0 ; i < 6 ; i++ ) {
				staffWindow[0][i].setVisible(false);
			}
			ButtonPanel.setVisible(false);
			FunctionPanel.setVisible(false);
		}
	}
    
    public String[][] getStaffData(){
		return staffData;
    }

    public int getNumberOfStaff() {
    		return count;
    }
    
    public void reset() {
    		for ( int i = 0 ; i < 8 ; i++ ) {
    			for ( int j = 0 ; j < 6 ; j++ ) {
    				staffWindow[i][j].setBackground(Color.WHITE);
    			}
    		}
    		which = 1;
    }
    
    public void readStaff() {
    		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("StaffFile.txt"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		count = sc.nextInt();
		
		for ( int i = 0 ; i < count ; i++ ) {
			for ( int j = 0 ; j < 6 ; j++ ) {
				staffData[i][j] = sc.next();
			}
		}
		
		sc.close();
    }
    
    public void writeStaff() {
    		
    		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream("StaffFile.txt"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		pw.println(count);
		for ( int i = 0 ; i < count ; i++ ) {
			for ( int j = 0 ; j < 6 ; j++ ) {
				pw.print(staffData[i][j]);
				pw.print(" ");
			}
			pw.println();
		}
		pw.close();
    }
}