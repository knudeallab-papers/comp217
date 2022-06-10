import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class room extends JFrame implements ActionListener{
	
	public room()
	{
		super("room");
		setSize(400,400);
		setLocation(500,280);
		setLayout(null);
		setVisible(true);
		
		JButton r1 = new JButton("309");
		JButton r2 = new JButton("342");
		JButton r3 = new JButton("345");
		JButton r4 = new JButton("355");
		
		r1.setBounds(50,50,100,100);
		r2.setBounds(200,50,100, 100);
		r3.setBounds(50,200,100,100);
		r4.setBounds(200,200,100,100);
		
		r1.addActionListener(this);
		r2.addActionListener(this);
		r3.addActionListener(this);
		r4.addActionListener(this);
		
		add(r1);
		add(r2);
		add(r3);
		add(r4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		if(s.equals("309"))
		{
			login.roomnum=309;
			lectureTimetable p = new lectureTimetable();
		}
		if(s.equals("342"))
		{
			login.roomnum=342;
			lectureTimetable p = new lectureTimetable();
		}
		if(s.equals("345"))
		{
			login.roomnum=345;
			lectureTimetable p = new lectureTimetable();
		}
		if(s.equals("355"))
		{
			login.roomnum=355;
			lectureTimetable p = new lectureTimetable();
		}
		
		
	}

}