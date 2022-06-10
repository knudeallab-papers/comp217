package GH;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

import static GH.Character_status.hp;
import static GH.Character_status.stress;
public class Classroom_page extends JFrame implements ActionListener, MouseListener, btMain {
	
	ImageIcon icon;
	JList scrollList;
	JButton main_btn;
	Lecture[] list = { Regis_page.JAVA };
	Lecture selectedLecture;
	
	public Classroom_page() {
		super("호반우를 키워라_강의실");
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
        
        scrollList = new JList<Lecture>(Regis_page.selectedList);
        scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollList.addMouseListener(this);
        scrollList.setBounds(270, 100, 200, 200);
        panel.add(scrollList);
        
        mainbt();
        
    	panel.add(main_btn);
		
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
		
		}
	}
	
	private void assumingAction(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("")) {
			
		}
		else if(actionCommand.equals("Back")) {
			
			setVisible(false);
		}
		else {
			//오류
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		if (arg0.getClickCount() == 2) {
			
			selectedLecture = (Lecture) scrollList.getSelectedValue();
			
			new Classroom_lectpage(selectedLecture);
			
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Classroom_page clp = new Classroom_page();
		clp.setVisible(true);
	}


	@Override
	public void mainbt() {
		main_btn = new JButton("Back");
    	main_btn.setBounds(600, 320, 80, 30);
    	main_btn.setFocusPainted(false);
    	main_btn.addActionListener(this);
		
	}

}
