import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.IOException;

public class editor extends JFrame implements ActionListener{
	ImageIcon editor1;
	ImageIcon editor2;
	
	JLabel editor1text;
	JLabel editor2text;
	JLabel editor1IMG;
	JLabel editor2IMG;
	
	JButton btn1;
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	class inner implements WindowListener{

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			dispose();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public editor() {
		super("만든사람");
		
		editor1 = new ImageIcon("e1.jpg");
		editor2 = new ImageIcon("e2.jpg");
		
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel south = new JPanel();
		south.setLayout(new BorderLayout());
		
		JButton backtoMenu=new JButton("메뉴로 돌아가기");
		backtoMenu.addActionListener(this);
		south.add(backtoMenu);
		
		JPanel center=new JPanel();
		center.setBackground(Color.WHITE);
		center.setLayout(new GridLayout(2,1,100,100));
		
		editor1text = new JLabel("이름 : 강인수   학번 : 2018111641");
		editor1text.setFont(new Font("Serif",Font.BOLD,12));
		editor1text.setIcon(editor1);
		center.add(editor1text);
		
		editor2text = new JLabel("이름 : 성유성   학번 : 2018110047");
		editor2text.setFont(new Font("Serif",Font.BOLD,12));
		editor2text.setIcon(editor2);
		center.add(editor2text);
		
		add(south,BorderLayout.SOUTH);
		add(center,BorderLayout.CENTER);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String btn=e.getActionCommand();
		if(btn.equals("메뉴로 돌아가기")) {
			Menu menu=new Menu();
			menu.setLocationRelativeTo(null);
			menu.setVisible(true);
			dispose();
		}
	}
	

}
