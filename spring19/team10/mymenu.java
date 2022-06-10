import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mymenu extends JFrame implements ActionListener{
	
	public mymenu()
	{
		super("my menu");
		setSize(500,500);
		setLocation(500,280);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		JPanel pan = new JPanel();
		pan.setLayout(null); 
		
		
		JLabel imag = new JLabel("");
		ImageIcon icon = new ImageIcon("gy1.png");
		
		imag.setIcon(icon);
		imag.setBounds(70,50,400,300);
		
		
		JButton pl = new JButton("planner");
		JButton li = new JButton("Library");
		JButton le = new JButton("Lecture");
		le.setActionCommand("lecture!!");
		JButton food = new JButton("½Ä´Ü");
		food.setActionCommand("food");
		pl.addActionListener(this);
		li.addActionListener(this);
		le.addActionListener(this);
		food.addActionListener(this);
		
		pl.setBounds(10,400,100,50);
		pan.add(pl);
		
		li.setBounds(130,400,100,50);
		pan.add(li);
		
		le.setBounds(250,400,100,50);
		pan.add(le);
		
		food.setBounds(370,400,100,50);
		pan.add(food);
		
		pan.add(imag);
		
		add(pan);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		if(s.equals("lecture!!"))
		{
			room r = new room();
			//Plan p = new Plan();
		}
		if(s.equals("food"))
		{
			Food f = new Food();
		}
		if(s.equals("Library"))
		{
			libseat li = new libseat();
		}
		if(s.equals("planner"))
		{
			Planner p = new Planner();
		}
	}

}