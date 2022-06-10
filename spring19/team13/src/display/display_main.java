package display;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class display_main extends JFrame implements ActionListener{
	
	
	public static void main(String[] args)
	{
		display_main hi = new display_main();
		hi.setVisible(true);
	}
	public display_main()
	{
		
		getContentPane().setLayout(null);
		
		JPanel BtnPanel = new JPanel();
		BtnPanel.setBounds(14, 137, 600, 250);
		getContentPane().add(BtnPanel);
		BtnPanel.setLayout(new GridLayout(1, 3, 0, 0));
		
		JButton lentBtn = new JButton("대여 메뉴");
		lentBtn.setActionCommand("lent");
		BtnPanel.add(lentBtn);
		JButton memberBtn = new JButton("회원 관리");
		memberBtn.setActionCommand("member");
		BtnPanel.add(memberBtn);
		JButton bookBtn = new JButton("책 관리");
		bookBtn.setActionCommand("book");
		BtnPanel.add(bookBtn);
		
		
		lentBtn.addActionListener(this);
		memberBtn.addActionListener(this);
		bookBtn.addActionListener(this);
		
		
		JLabel TitleLabel = new JLabel("도서 관리 프로그램");
		TitleLabel.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 30));
		TitleLabel.setBounds(180, 60, 300, 50);
		getContentPane().add(TitleLabel);
		setBounds(500, 200, 650, 500);
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("lent"))
		{
			display_Rent lentD = new display_Rent();
			lentD.setVisible(true);
		}
		else if(e.getActionCommand().equals("member"))
		{
			display_member memberD = new display_member();
			memberD.setVisible(true);
		}
		else if(e.getActionCommand().equals("book"))
		{
			display_book bookD = new display_book();
			bookD.setVisible(true);
		}
		dispose();
		
	}	
}
