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

public class MemberButton implements ActionListener{

	JFrame Frame = new JFrame();
	
	JPanel[] Column = new JPanel[5];
	JPanel ButtonPanel = new JPanel();
	JPanel FunctionPanel = new JPanel();
	public static JButton[][] MemberBtn = new JButton[7][5];
	JButton EditBtn = new JButton("편집");
	JButton AddBtn = new JButton("추가");
	JButton DeleteBtn = new JButton("삭제");
	
	public static int NumberOfMembers = 0;
	public static int SelectedIndex = 0;
	public static String[][] MemberInfo = new String[7][5];
	
	JFrame newFrame;
	JTextField[] aTextField = new JTextField[5];
	JLabel[] aLabel = new JLabel[5];
	JPanel[] aPanel = new JPanel[6];
	
	// 추가에서 사용
	public static JButton yes = new JButton("확인");
	public static JButton no = new JButton("취소");
	
	// 삭제에서 사용
	JButton Yes = new JButton("예");
	JButton No = new JButton("아니오");
	
	// 편집에서 사용
	JButton y = new JButton("수정");
	JButton n = new JButton("취소");
	
	public MemberButton(JFrame frame) {
		
		for ( int i = 0 ; i < 7 ; i++ ) {
			MemberInfo[i][0] = "";
			MemberInfo[i][1] = "";
			MemberInfo[i][2] = "";
			MemberInfo[i][3] = "";
			MemberInfo[i][4] = "";
		}
		
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("MemberFile.txt"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		NumberOfMembers = sc.nextInt();
		
		for ( int i = 0 ; i < NumberOfMembers ; i++ ) {
			MemberInfo[i][0] = sc.next();
			MemberInfo[i][1] = sc.next();
			MemberInfo[i][2] = sc.next();
			MemberInfo[i][3] = sc.next();
			MemberInfo[i][4] = sc.next();
		}
		
		sc.close();
		
		Frame = frame;
		
		Column[0] = new JPanel();
		Column[0].add(new JLabel("번호"));
		Column[0].setBounds(0,89,100,30);
		Column[0].setBackground(Color.WHITE);
		Column[0].setBorder(new LineBorder(Color.GRAY,2));
		
		Column[1] = new JPanel();
		Column[1].add(new JLabel("등급"));
		Column[1].setBounds(99,89,100,30);
		Column[1].setBackground(Color.WHITE);
		Column[1].setBorder(new LineBorder(Color.GRAY,2));
		
		Column[2] = new JPanel();
		Column[2].add(new JLabel("이름"));
		Column[2].setBounds(198,89,100,30);
		Column[2].setBackground(Color.WHITE);
		Column[2].setBorder(new LineBorder(Color.GRAY,2));
		
		Column[3] = new JPanel();
		Column[3].add(new JLabel("마일리지"));
		Column[3].setBounds(297,89,80,30);
		Column[3].setBackground(Color.WHITE);
		Column[3].setBorder(new LineBorder(Color.GRAY,2));
		
		Column[4] = new JPanel();
		Column[4].add(new JLabel("연락처"));
		Column[4].setBounds(376,89,224,30);
		Column[4].setBackground(Color.WHITE);
		Column[4].setBorder(new LineBorder(Color.GRAY,2));
		
		
		for (int i = 0 ; i < 5 ; i++ ) {
			Column[i].setVisible(false);
			frame.add(Column[i]);
			Column[i].setVisible(true);
		}
		
		ButtonPanel.setLayout(null);
		ButtonPanel.setBorder(new LineBorder(Color.GRAY,2));
		ButtonPanel.setBackground(Color.WHITE);
		ButtonPanel.setBounds(0,90,600,230);
		
		for ( int i = 0 ; i < 7 ; i++ ) {
			for ( int j = 0 ; j < 5 ; j++ ) {
				MemberBtn[i][j] = new JButton(MemberInfo[i][j]);
				MemberBtn[i][j].setBorder(new LineBorder(Color.GRAY,2));
				MemberBtn[i][j].setOpaque(true);
				MemberBtn[i][j].setBackground(Color.WHITE);
			}
			MemberBtn[i][0].setBounds(0,28+(29*i),100,30);
			MemberBtn[i][1].setBounds(99,28+(29*i),100,30);
			MemberBtn[i][2].setBounds(198,28+(29*i),100,30);
			MemberBtn[i][3].setBounds(297,28+(29*i),80,30);
			MemberBtn[i][4].setBounds(376,28+(29*i),224,30);
		}
		
		for ( int i = 0 ; i < 7 ; i++ ) {
			for ( int j = 0 ; j < 5 ; j++ ) {
				MemberBtn[i][j].addActionListener(this);
				ButtonPanel.add(MemberBtn[i][j]);
			}
		}
		
		ButtonPanel.setVisible(false);
		frame.add(ButtonPanel);
		ButtonPanel.setVisible(true);
		
		FunctionPanel.setBounds(0,320,600,58);
		FunctionPanel.setBackground(Color.WHITE);
		FunctionPanel.setBorder(new LineBorder(Color.GRAY,2,true));
		FunctionPanel.setLayout(null);
		
		EditBtn.addActionListener(this);
		EditBtn.setBounds(390, 9, 60, 40);
		AddBtn.addActionListener(this);
		AddBtn.setBounds(460, 9, 60, 40);
		DeleteBtn.addActionListener(this);
		DeleteBtn.setBounds(530, 9, 60, 40);
		
		FunctionPanel.add(EditBtn);
		FunctionPanel.add(AddBtn);
		FunctionPanel.add(DeleteBtn);
		
		FunctionPanel.setVisible(false);
		frame.add(FunctionPanel);
		FunctionPanel.setVisible(true);
		
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String btnStr = e.getActionCommand();
		Object obj = e.getSource();
		
		if ( btnStr.equals("편집")) {
			if ( SelectedIndex >= 0 && SelectedIndex < NumberOfMembers ) {
				EditMember();
				resetColor();
			}
		}else if ( btnStr.equals("추가")) {
			resetColor();
			AddMember();
		}else if ( btnStr.equals("삭제")) {
			if ( SelectedIndex >= 0 && SelectedIndex < NumberOfMembers ) {
				DeleteMember();
				resetColor();
			}
		}else if ( btnStr.equals("확인")) {
			
			if ( !aTextField[0].getText().equals("") && !aTextField[1].getText().equals("") && !aTextField[2].getText().equals("") && !aTextField[3].getText().equals("") && !aTextField[4].getText().equals("") ) {
				for ( int i = 0 ; i < 5 ; i++ ) {
					if ( i != 1 ) {
						MemberInfo[NumberOfMembers][i] = aTextField[i].getText();
						MemberBtn[NumberOfMembers][i].setText(MemberInfo[NumberOfMembers][i]);
					}else {
						if ( Integer.parseInt(aTextField[3].getText()) < 500 ) {
							MemberInfo[NumberOfMembers][i] = "일반";
							MemberBtn[NumberOfMembers][i].setText("일반");
						}else if ( Integer.parseInt(aTextField[3].getText()) < 1000 ) {
							MemberInfo[NumberOfMembers][i] = "골드";
							MemberBtn[NumberOfMembers][i].setText("골드");
						}else {
							MemberInfo[NumberOfMembers][i] = "플래티넘";
							MemberBtn[NumberOfMembers][i].setText("플래티넘");
						}
					}
				}
				
				for ( int i = 0 ; i < 5 ; i++ ) {
					aTextField[i].setText("");
				}
				NumberOfMembers++;
			}
			SelectedIndex = -1;
			newFrame.dispose();
		}else if ( btnStr.equals("취소")) {
			SelectedIndex = -1;
			newFrame.dispose();
		}else if ( btnStr.equals("예")){
			if ( SelectedIndex != -1 ) {
				NumberOfMembers--;
				if ( NumberOfMembers >= 0 ) {
					for ( int i = SelectedIndex ; i < NumberOfMembers ; i++ ) {
						for ( int j = 1 ; j < 5 ; j++ ) {
							MemberInfo[i][j] = MemberInfo[i+1][j];
							MemberBtn[i][j].setText(MemberBtn[i+1][j].getText());
						}
					}
					for ( int j = 0 ; j < 5 ; j++ ) {
						MemberInfo[NumberOfMembers][j] = "";
						MemberBtn[NumberOfMembers][j].setText("");
					}
					
				}
			}
			newFrame.dispose();
			SelectedIndex = -1;
		}else if ( btnStr.equals("아니오")) {
			SelectedIndex = -1;
			newFrame.dispose();
		}else if ( btnStr.equals("수정")) {
			if ( SelectedIndex != -1 ) {
				if ( !aTextField[0].getText().equals("") && !aTextField[1].getText().equals("") && !aTextField[2].getText().equals("") && !aTextField[3].getText().equals("") && !aTextField[4].getText().equals("") ) {
					for ( int i = 0 ; i < 5 ; i++ ) {
						MemberInfo[SelectedIndex][i] = aTextField[i].getText();
						MemberBtn[SelectedIndex][i].setText(MemberInfo[SelectedIndex][i]);
					}
					if ( Integer.parseInt(MemberInfo[SelectedIndex][3]) < 500 ) {
						MemberInfo[SelectedIndex][1] = "일반";
						MemberBtn[SelectedIndex][1].setText(MemberInfo[SelectedIndex][1]);
					}else if (Integer.parseInt(MemberInfo[SelectedIndex][3]) < 1000 ) {
						MemberInfo[SelectedIndex][1] = "골드";
						MemberBtn[SelectedIndex][1].setText(MemberInfo[SelectedIndex][1]);
					}else {
						MemberInfo[SelectedIndex][1] = "플래티넘";
						MemberBtn[SelectedIndex][1].setText(MemberInfo[SelectedIndex][1]);
					}
					for ( int i = 0 ; i < 5 ; i++ ) {
						aTextField[i].setText("");
					}
				}
			}
			newFrame.dispose();
			SelectedIndex = -1;
		}else {
			resetColor();
			for ( int i = 0 ; i < 7 ; i++ ) {
				if ( obj == MemberBtn[i][0] || obj == MemberBtn[i][1] || obj == MemberBtn[i][2] || obj == MemberBtn[i][3] || obj == MemberBtn[i][4] ) {
					SelectedIndex = i;
					for ( int j = 0 ; j < 5 ; j++ ) {
						MemberBtn[i][j].setBackground(Color.LIGHT_GRAY);
					}
					break;
				}
			}
			Frame.add(ButtonPanel);
			ButtonPanel.setVisible(false);
			ButtonPanel.setVisible(true);
		}
		
	}
	
	public void resetColor() {
		for ( int i = 0 ; i < 7 ; i++ ) {
			for ( int j = 0 ; j < 5 ; j++ ) {
				MemberBtn[i][j].setBackground(Color.WHITE);
			}
		}
	}
	
	
	
	public void AddMember() {
		newFrame = new JFrame("회원 추가");
		newFrame.setSize(300, 200);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newFrame.getContentPane().setBackground(Color.WHITE);
		newFrame.setLayout(new GridLayout(6,1));
		newFrame.setLocation(200,150);
		
		aLabel[0] = new JLabel("번호", SwingConstants.CENTER);
		aLabel[1] = new JLabel("등급", SwingConstants.CENTER);
		aLabel[2] = new JLabel("이름", SwingConstants.CENTER);
		aLabel[3] = new JLabel("마일리지", SwingConstants.CENTER);
		aLabel[4] = new JLabel("연락처", SwingConstants.CENTER);
		
		aTextField[0] = new JTextField(30);
		aTextField[1] = new JTextField(30);
		aTextField[2] = new JTextField(30);
		aTextField[3] = new JTextField(30);
		aTextField[4] = new JTextField(30);
		
		aTextField[0].setText("" + (NumberOfMembers+1));
		aTextField[1].setText("일반");
		aTextField[3].setText("0");
		
		yes.addActionListener(this);
		no.addActionListener(this);
		for ( int i = 0 ; i < 5 ; i++ ) {
			aPanel[i] = new JPanel();
			aPanel[i].setLayout(new GridLayout(1,2));
			aPanel[i].add(aLabel[i]);
			aPanel[i].add(aTextField[i]);
		}
		aPanel[5] = new JPanel();
		aPanel[5].setLayout(new FlowLayout());
		aPanel[5].add(yes);
		aPanel[5].add(no);
		
		for ( int i = 0 ; i < 6 ; i++ ) {
			newFrame.add(aPanel[i]);
			aPanel[i].setVisible(true);
		}
		
		newFrame.setVisible(true);
	}
	
	public void AddMember(String name, String phone, String mileage) {
		newFrame = new JFrame("회원 추가");
		newFrame.setSize(300, 200);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newFrame.getContentPane().setBackground(Color.WHITE);
		newFrame.setLayout(new GridLayout(6,1));
		newFrame.setLocation(200,150);
		
		aLabel[0] = new JLabel("번호", SwingConstants.CENTER);
		aLabel[1] = new JLabel("등급", SwingConstants.CENTER);
		aLabel[2] = new JLabel("이름", SwingConstants.CENTER);
		aLabel[3] = new JLabel("마일리지", SwingConstants.CENTER);
		aLabel[4] = new JLabel("연락처", SwingConstants.CENTER);
		
		aTextField[0] = new JTextField(30);
		aTextField[1] = new JTextField(30);
		aTextField[2] = new JTextField(30);
		aTextField[3] = new JTextField(30);
		aTextField[4] = new JTextField(30);
		
		aTextField[0].setText("" + (NumberOfMembers+1));
		if ( Integer.parseInt(mileage) < 500 ) {
			aTextField[1].setText("일반");
		}else if ( Integer.parseInt(mileage) < 1000 ) {
			aTextField[1].setText("골드");
		}else {
			aTextField[1].setText("플래티넘");
		}
		aTextField[2].setText(name);
		aTextField[3].setText(mileage);
		aTextField[4].setText(phone);
		
		yes.addActionListener(this);
		for ( int i = 0 ; i < 5 ; i++ ) {
			aPanel[i] = new JPanel();
			aPanel[i].setLayout(new GridLayout(1,2));
			aPanel[i].add(aLabel[i]);
			aPanel[i].add(aTextField[i]);
		}
		aPanel[5] = new JPanel();
		aPanel[5].setLayout(new FlowLayout());
		aPanel[5].add(yes);
		
		for ( int i = 0 ; i < 6 ; i++ ) {
			newFrame.add(aPanel[i]);
			aPanel[i].setVisible(true);
		}
		
		newFrame.setVisible(true);
	}
	
	public void DeleteMember() {
		newFrame = new JFrame("회원 삭제");
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
	
	public void EditMember(){
		newFrame = new JFrame("회원 수정");
		newFrame.setSize(300, 200);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newFrame.getContentPane().setBackground(Color.WHITE);
		newFrame.setLayout(new GridLayout(6,1));
		newFrame.setLocation(200,150);
		
		aLabel[0] = new JLabel("번호", SwingConstants.CENTER);
		aLabel[1] = new JLabel("등급", SwingConstants.CENTER);
		aLabel[2] = new JLabel("이름", SwingConstants.CENTER);
		aLabel[3] = new JLabel("마일리지", SwingConstants.CENTER);
		aLabel[4] = new JLabel("연락처", SwingConstants.CENTER);
		
		aTextField[0] = new JTextField(30);
		aTextField[1] = new JTextField(30);
		aTextField[2] = new JTextField(30);
		aTextField[3] = new JTextField(30);
		aTextField[4] = new JTextField(30);
		
		aTextField[0].setText(MemberInfo[SelectedIndex][0]);
		aTextField[1].setText(MemberInfo[SelectedIndex][1]);
		aTextField[3].setText(MemberInfo[SelectedIndex][3]);
		
		y.addActionListener(this);
		n.addActionListener(this);
		for ( int i = 0 ; i < 5 ; i++ ) {
			aPanel[i] = new JPanel();
			aPanel[i].setLayout(new GridLayout(1,2));
			aPanel[i].add(aLabel[i]);
			aPanel[i].add(aTextField[i]);
		}
		aPanel[5] = new JPanel();
		aPanel[5].setLayout(new FlowLayout());
		aPanel[5].add(y);
		aPanel[5].add(n);
		
		for ( int i = 0 ; i < 6 ; i++ ) {
			newFrame.add(aPanel[i]);
			aPanel[i].setVisible(true);
		}
		
		newFrame.setVisible(true);
	}
	
	public void setVisible(boolean e) {
		if ( e == true ) {
			for ( int i = 0 ; i < 5 ; i++ ) {
				Column[i].setVisible(true);
			}
			ButtonPanel.setVisible(true);
			FunctionPanel.setVisible(true);
		}else {
			for ( int i = 0 ; i < 5 ; i++ ) {
				Column[i].setVisible(false);
			}
			ButtonPanel.setVisible(false);
			FunctionPanel.setVisible(false);
		}
	}
	
	public void update(String name, String phone, String mileage) {
		resetColor();
		AddMember(name, phone, mileage);
	}
	
	public void updateMileage(int i, String mileage) {
		if ( Integer.parseInt(mileage) < 500 ) {
			MemberInfo[i][1] = "일반";
			MemberBtn[i][1].setText(MemberInfo[i][1]);
		}else if (Integer.parseInt(mileage) < 1000 ) {
			MemberInfo[i][1] = "골드";
			MemberBtn[i][1].setText(MemberInfo[i][1]);
		}else {
			MemberInfo[i][1] = "플래티넘";
			MemberBtn[i][1].setText(MemberInfo[i][1]);
		}
		MemberInfo[i][3] = mileage;
		MemberBtn[i][3].setText(mileage);
	}
	
	public void updateMileage() {
		System.out.println(NumberOfMembers);
		for ( int i = 0 ; i < NumberOfMembers ; i++ ) {
			MemberInfo[i][1] = "일반";
			MemberBtn[i][1].setText("일반");
			MemberInfo[i][3] = "0";
			MemberBtn[i][3].setText("0");
		}
	}
	
	public void reset() {
		for ( int i = 0 ; i < 7 ; i++ ) {
			for ( int j = 0 ; j < 5 ; j++ ) {
				MemberBtn[i][j].setBackground(Color.WHITE);
			}
		}
		SelectedIndex = -1;
	}
	
	public void writeMember() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream("MemberFile.txt"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		pw.println(NumberOfMembers);
		for ( int i = 0 ; i < NumberOfMembers ; i++ ) {
			pw.print(MemberInfo[i][0]);
			pw.print(" ");
			pw.print(MemberInfo[i][1]);
			pw.print(" ");
			pw.print(MemberInfo[i][2]);
			pw.print(" ");
			pw.print(MemberInfo[i][3]);
			pw.print(" ");
			pw.print(MemberInfo[i][4]);
			pw.println();
		}
		pw.close();
	}
}
