import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class denied extends JFrame implements ActionListener{
	
	public denied()
	{
		super("error");
		setSize(300,200);
		setLocation(600,380);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JLabel label1 = new JLabel("ID 또는 비밀번호가 잘못 되었습니다.");
		panel1.add(label1, BorderLayout.CENTER);
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		panel1.add(ok, BorderLayout.SOUTH);
		add(panel1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		if( s.equals("OK"))
			dispose();
		
	}
	
}