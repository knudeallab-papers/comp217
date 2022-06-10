package teamp2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class 메시지표시창 {

	private JFrame frame;

	
	public 메시지표시창(String massage) 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("TODAY FIT");
		JLabel lblNewLabel = new JLabel(massage);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 10, 410, 241);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
	}

}