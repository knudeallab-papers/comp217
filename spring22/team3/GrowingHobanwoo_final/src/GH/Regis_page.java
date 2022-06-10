package GH;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static GH.Character_status.score;

public class Regis_page extends JFrame implements ActionListener, MouseListener {
	
	JList scrollList, myList;
	ImageIcon icon;
    int iii=0;
	static Lecture JAVA = new Lecture("자바프로그래밍", "서영균", 3);
	static Lecture Entrepreneurship = new Lecture("기업가정신과 벤처창업", "차홍렬", 3);
	static Lecture FutureTech = new Lecture("미래산업과 직업선택", "류택형", 3);
	static Lecture Algorithm = new Lecture("알고리즘실습", "배준현", 3);
	static Lecture Probability = new Lecture("확률과통계", "백호기", 3);
	static Lecture CompArch = new Lecture("컴퓨터구조", "최창훈", 3);
	static Lecture SWCareer= new Lecture("SW진로설계", "안현숙", 3);
	static Lecture Opensource = new Lecture("오픈소스프로그래밍", "정설영", 3);
	static Lecture AI = new Lecture("인공지능", "박상효", 3);
	static Lecture StartUp = new Lecture("스타트업설계", "김진욱", 3);
	static Lecture Compiler = new Lecture("컴파일러", "이은주", 3);
	
	Lecture[] lectureList = { JAVA, Entrepreneurship, FutureTech,
			Algorithm, Probability, CompArch, SWCareer, Opensource,
			AI, StartUp, Compiler };
	
	Lecture selectedLecture;
	static Lecture[] selectedList = new Lecture[6];
	int count = 0; 
	ListModel listModel;
	JLabel totalCredit;
	
		static int[] studyCnt = { 0, 0, 0, 0, 0, 0 };
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Regis_page gui = new Regis_page();
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
	
	
	public Regis_page() {
		super("호반우를 키워라_수강신청");
		setSize(740, 430);
		setLocationRelativeTo(null);
    	setResizable(false);
		
		icon = new ImageIcon("동아리방.jfif");
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, 720, 404, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        panel.setLayout(null);
       
        scrollList = new JList<Lecture>(lectureList);
        scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollList.addMouseListener(this);
        scrollList.setBounds(130, 30, 200, 300);
        scrollList.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(scrollList);
        
        myList = new JList<Lecture>(selectedList);
        myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myList.addMouseListener(this);
        myList.setBounds(350, 30, 200, 200);
        myList.setSelectedIndex(0);
        panel.add(myList);
        
        totalCredit = new JLabel("Credit: "+count*3);
      
        totalCredit.setBounds(350, 250, 150, 50);
        panel.add(totalCredit);
        
        JButton submit_btn = new JButton("수강신청");
        submit_btn.addActionListener(this);
        submit_btn.setFocusPainted(false);
        submit_btn.setBounds(400, 300, 90, 30);
        panel.add(submit_btn);
		
        add(panel);
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		
		if (actionCmd.equals("수강신청")) {
			listModel = myList.getModel();
			
			if(iii == 6) {
			JOptionPane.showMessageDialog(null, "수강신청 완료");
			setVisible(false);
			score = 18;
			}else {
				JOptionPane.showMessageDialog(null, "18학점을 채워주세요!");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		DefaultListModel defaultModel = new DefaultListModel();
		int index;
		// TODO Auto-generated method stub
		if (arg0.getClickCount() == 2) {
			if (arg0.getComponent().equals(scrollList)) {
				selectedLecture = (Lecture) scrollList.getSelectedValue();

				System.out.println(selectedLecture);
				
				listModel = myList.getModel();
				for (int i = 0; i < listModel.getSize(); i++)
					defaultModel.addElement(listModel.getElementAt(i));
				
				if (defaultModel.contains(selectedLecture))
					JOptionPane.showMessageDialog(null, "과목 중복 수강 불가");
				else {
					if (iii == 6) // count -> iii
						JOptionPane.showMessageDialog(null, "18학점 이상 수강 불가");
					else {
						selectedList[iii++] = selectedLecture;
						defaultModel.addElement(selectedLecture);
					}
					
				}
				
				
				myList.setModel(defaultModel);
			}
			totalCredit.setText("Credit: "+iii*3);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
