import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class notexist extends JFrame implements ActionListener{

	public notexist()
	{
		super("error");
		setSize(300,200);
		setLocation(600,380);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JLabel label1 = new JLabel("사용 가능한 아이디입니다.");
		panel1.add(label1, BorderLayout.CENTER);
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		panel1.add(ok, BorderLayout.SOUTH);
		add(panel1);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		if( s.equals("OK"))
			dispose();
		
	}
}