package GH;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import static GH.Character_status.stress;
import static GH.Character_status.hp;
import static GH.Character_status.happy;

import static GH.Main_HOBAN.happy_;

public class Classroom_lectpage extends JFrame implements ActionListener {
	
	ImageIcon icon;
	ImageIcon contentImg;
	
	JPanel attendPanel;
	int num;
	JTextField attendTF;
	Lecture temp;
	
	JPanel viewPanel;
	JTextArea memopad;
	
	public Classroom_lectpage() {
	}
	
	public Classroom_lectpage(Lecture a) {
		super("호반우를 키워라_강의");
		setSize(740, 430);
		setLocationRelativeTo(null);
    	setResizable(false);
		
		temp = a;
		
		icon = new ImageIcon("동아리방.jfif");
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, 720, 404, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        panel.setLayout(null);
        
        JButton classroom_btn = new JButton("<");
    	classroom_btn.setBounds(20, 20, 50, 30);
    	classroom_btn.addActionListener(this);
    	panel.add(classroom_btn);
        
        attendPanel = new JPanel();
        attendPanel.setBounds(320, 100, 100, 100);
        attendPanel.setLayout(new FlowLayout());
        attendPanel.setBackground(Color.WHITE);
        panel.add(attendPanel);
        
        num = (int) (Math.random()*10000);
        JLabel attendNum = new JLabel("출석번호 :"+num);
        attendPanel.add(attendNum);
        
        attendTF = new JTextField();
        attendTF.setColumns(4);
        attendPanel.add(attendTF);
        
        JButton submit_btn = new JButton("출석");
        submit_btn.addActionListener(this);
        attendPanel.add(submit_btn);
        
    	viewPanel = new JPanel();
    	viewPanel.setBounds(110, 50, 500, 250);
    	viewPanel.setBackground(Color.RED);
    	viewPanel.setLayout(new GridLayout(1, 2));
    	panel.add(viewPanel);
    	viewPanel.setVisible(false);
    	
    	
    	contentImg = new ImageIcon(a.getName()+".png");
    	
    	JPanel contentsPanel = new JPanel() {
    		public void paintComponent(Graphics g) {
                g.drawImage(contentImg.getImage(), 0, 0, 250, 250, null);
                setOpaque(false);
                super.paintComponent(g);
    		}
    	};
    	viewPanel.add(contentsPanel);
    	
    	JPanel memoPanel = new JPanel();
    	memoPanel.setLayout(new BorderLayout());
    	viewPanel.add(memoPanel);
    	
    	JLabel note = new JLabel("복습을 위해 필기하세요!");
    	note.setHorizontalTextPosition(JLabel.CENTER);
    	memoPanel.add(note, BorderLayout.NORTH);
    	
    	memopad = new JTextArea();
    	memopad.setEditable(true);
    	
    	JScrollPane scrollpad = new JScrollPane(memopad);
    	
    	memoPanel.add(scrollpad, BorderLayout.CENTER);
    	
    	JButton save_btn = new JButton("저장");
    	save_btn.addActionListener(this);
    	memoPanel.add(save_btn, BorderLayout.SOUTH);
    	
        add(panel);
        
        if(hp <= 0) {
			Ending_Guaro gre = new Ending_Guaro();
			gre.setVisible(true);
			setVisible(false);
		}else if(stress >= 100) {
			Ending_Gapyear gye = new Ending_Gapyear();
			gye.setVisible(true);
			setVisible(false);
		}else
			setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
			assumingAction(e);
		}catch(NumberFormatException e2) {
			//
		}

	}

	private void assumingAction(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("출석")) {
			if ( num == Integer.parseInt(attendTF.getText()) ) {
				JOptionPane.showMessageDialog(null, "출석되었습니다.");
				
				hp -= 10;
				stress += 10;
				happy -= 10;
				
				happy_ -= 10;
				
				temp.attend();
				
				attendPanel.setVisible(false);
				viewPanel.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "번호가 틀렸습니다.\n다시 입력해주세요.");
				attendTF.setText("");
			}
		}
		else if(actionCommand.equals("저장")) {
			PrintWriter outputStream = null;
			try {
				String fileName = temp.getName()+".txt";
				outputStream = new PrintWriter(new FileOutputStream(fileName, false));
			}catch(FileNotFoundException e3) {
				e3.printStackTrace();
			}
			
			outputStream.print(memopad.getText());
			outputStream.close();
			
			JOptionPane.showMessageDialog(null, "저장되었습니다.");
			
			setVisible(false);
		}
		else if(actionCommand.equals("<")) {
			setVisible(false);
		}
		else {
			//오류
		}
		
	}
}