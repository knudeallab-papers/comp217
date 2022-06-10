package GH;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Club_page extends JFrame implements ActionListener, btMain{
	
	JScrollPane scrollPane;
    ImageIcon icon;
    JButton main_btn;
    
	public Club_page() {
		super("호반우를 키워라_동아리방");
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
    	
    	JButton gameclub_btn = new JButton("회화 동아리");
    	gameclub_btn.setBounds(400, 150, 100, 30);
    	gameclub_btn.setFocusPainted(false);
    	gameclub_btn.addActionListener(this);
    	
    	JButton studyclub_btn = new JButton("학술 동아리");
    	studyclub_btn.setBounds(200, 100, 100, 30);
    	studyclub_btn.setFocusPainted(false);
    	studyclub_btn.addActionListener(this);
    	
    	mainbt();
    	
    	panel.add(gameclub_btn);
    	panel.add(main_btn);
    	panel.add(studyclub_btn);
    	
        scrollPane = new JScrollPane(panel);
        setContentPane(scrollPane);
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e) {
		try{
			assumingAction(e);
			
		}catch(NumberFormatException e2) {
			//inputL.setText("Error");
		}
	}
    private void assumingAction(ActionEvent e) {
		// TODO Auto-generated method stub
    	String actionCommand = e.getActionCommand();
    	int a = 1;
    	
		if(actionCommand.equals("회화 동아리")) {
			new Club_drawing_mydrawing();
		}
		else if(actionCommand.equals("학술 동아리")) {
			Club_codinggame code = new Club_codinggame();
		}
		else if(actionCommand.equals("Back")) {
			setVisible(false);
		}
		else {
			//오류
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Club_page frame = new Club_page();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
	}
	@Override
	public void mainbt() {
		main_btn = new JButton("Back");
    	main_btn.setBounds(600, 320, 80, 30);
    	main_btn.setFocusPainted(false);
    	main_btn.addActionListener(this);
		
	}

}
