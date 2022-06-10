package GH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import static GH.Character_status.hp;
import static GH.Character_status.stress;

public class Library_page extends JFrame implements ActionListener, MouseListener, btMain {
	
	ImageIcon icon;
	JList scrollList;
	String selectedStudy;
	JButton main_btn;
	
	Lecture[] list = { Regis_page.JAVA };
	Lecture selectedLecture;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Library_page gui = new Library_page();
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
	
	public Library_page() {
		super("호반우를 키워라_도서관");
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
        scrollList.setBounds(270, 50, 200, 200);
        panel.add(scrollList);
        
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(255,0,0,0));
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBounds(200, 250, 320, 100);
        panel.add(btnPanel);
        JButton rewrite_btn = new JButton("재정리");
        rewrite_btn.addActionListener(this);
        btnPanel.add(rewrite_btn);
        JButton sansungbi_btn = new JButton("산성비");
        sansungbi_btn.addActionListener(this);
        btnPanel.add(sansungbi_btn);
        
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
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("재정리")) {
			if (selectedLecture != null) {
				try {
					new Library_rewrite_page(selectedLecture);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
			else JOptionPane.showMessageDialog(null, "강의가 선택되지 않았습니다.");
		}
		else if(actionCommand.equals("산성비")) {
			if (selectedLecture != null) {
				new Library_sansungbigame(selectedLecture);
				setVisible(false);
			}
			else JOptionPane.showMessageDialog(null, "강의가 선택되지 않았습니다.");
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
		if ( arg0.getClickCount() != 0) { 
			selectedLecture = (Lecture) scrollList.getSelectedValue();
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

	@Override
	public void mainbt() {
		main_btn = new JButton("Back");
    	main_btn.setBounds(600, 320, 80, 30);
    	main_btn.setFocusPainted(false);
    	main_btn.addActionListener(this);
		
	}
	
	
}
