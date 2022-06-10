package team_project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorMsg extends JFrame implements ActionListener{
	
	
	public ErrorMsg(String err)
	{
		super("Error Msg");
		setSize(300,120);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel MainPanel = new JPanel();
		MainPanel.setLayout(new FlowLayout());
		JLabel msg = new JLabel(err);

		MainPanel.add(msg);
		add(MainPanel);
		
		JPanel BtnPanel = new JPanel();
		BtnPanel.setLayout(new FlowLayout());
		JButton btn = new JButton("Check");
		btn.addActionListener(this);
		BtnPanel.add(btn);
		
		add(BtnPanel, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
	
		 String actionCmd = e.getActionCommand();
		 
		 if (actionCmd.equals("Check"))
		 {
			 this.dispose();
		 }
	}
}
