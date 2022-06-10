package GH;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import static GH.Character_status.hp;
import static GH.Character_status.stress;
public class Gym_page extends JFrame implements ActionListener, btMain{
	
	JScrollPane scrollPane;
    ImageIcon icon;
    JButton main_btn;
    
	public Gym_page() {
		super("호반우를 키워라_체육관");
		setSize(740, 430);
		setLocationRelativeTo(null);
    	setResizable(false);
    	
        icon = new ImageIcon("체육관.jfif");

        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, 720, 404, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        
    	
    	panel.setLayout(null);
    	
    	JButton gameclub_btn = new JButton("점핑 호반우");
    	gameclub_btn.setFocusPainted(false);
    	gameclub_btn.setBounds(400, 150, 100, 30);
    	gameclub_btn.addActionListener(this);
    	
    	JButton studyclub_btn = new JButton("버그 잡기");
    	studyclub_btn.setBounds(200, 100, 100, 30);
    	studyclub_btn.setFocusPainted(false);
    	studyclub_btn.addActionListener(this);
    	
    	mainbt();
    	
    	panel.add(gameclub_btn);
    	panel.add(main_btn);
    	panel.add(studyclub_btn);
    	
        scrollPane = new JScrollPane(panel);
        setContentPane(scrollPane);
        
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
    public void actionPerformed(ActionEvent e) {
		try{
			assumingAction(e);
			
		}catch(NumberFormatException e2) {
		}
	}
    private void assumingAction(ActionEvent e) {
		// TODO Auto-generated method stub
    	String actionCommand = e.getActionCommand();
    	int a = 1;
    	
		if(actionCommand.equals("점핑 호반우")) {
			 if(hp <= 0) {
					Ending_Guaro gre = new Ending_Guaro();
					gre.setVisible(true);
					setVisible(false);
				}else if(stress >= 100) {
					Ending_Gapyear gye = new Ending_Gapyear();
					gye.setVisible(true);
					setVisible(false);
				}else {
					Gym_jumpgame_frame f = new Gym_jumpgame_frame();
					f.setVisible(true);
					f.start();
		        }
		}
		else if(actionCommand.equals("버그 잡기")) {
			 if(hp <= 0) {
					Ending_Guaro gre = new Ending_Guaro();
					gre.setVisible(true);
					setVisible(false);
				}else if(stress >= 100) {
					Ending_Gapyear gye = new Ending_Gapyear();
					gye.setVisible(true);
					setVisible(false);
				}else {
					Gym_hammergame hg = new Gym_hammergame();
					hg.setVisible(true);
				}
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
		Gym_page frame = new Gym_page();
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
