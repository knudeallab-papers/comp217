package Team_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Vending_machine extends JFrame implements ActionListener{

	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;
	
	public static final int NUMBER_OF_DIGIT = 30;
	private JTextField iofield;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vending_machine machineGui = new Vending_machine();
		
		machineGui.setVisible(true);
	}
	
	JPanel products = new JPanel();
	JPanel textpanel = new JPanel();
	
	public Vending_machine()
	{
		 setTitle("<VENDING MACHINE>");
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setSize(WIDTH, HEIGHT);
	      
	      setLayout(new BorderLayout());//프레임 설정      
	      
	      products.setLayout(new GridLayout(4, 1));
	      products.setBackground(Color.WHITE);
	      
	      JLabel selection = new JLabel("Select a category",JLabel.CENTER);
	      products.add(selection);
	      
	      JButton beverage = new JButton("Beverage");
	      beverage.addActionListener(this);
	      products.add(beverage);
	      
	      JButton snack = new JButton("Snack");
	      snack.addActionListener(this);
	      products.add(snack);
	      
	      JButton ramen = new JButton("Ramen");
	      ramen.addActionListener(this);
	      products.add(ramen);
	      
		add(products, BorderLayout.CENTER);
//텍스트 패널
		
		JMenu modemenu = new JMenu("Add_Stock");
		
		JMenuItem bev = new JMenuItem("Beverage");
		bev.addActionListener(this);
		bev.setActionCommand("bev");
		modemenu.add(bev);
		
		JMenuItem sna = new JMenuItem("Snack");
		sna.addActionListener(this);
		sna.setActionCommand("sna");
		modemenu.add(sna);
		
		JMenuItem ram = new JMenuItem("Ramen");
		ram.addActionListener(this);
		ram.setActionCommand("ram");
		modemenu.add(ram);
		
		JMenu recom = new JMenu("Recommanded");
		JMenuItem recom_i = new JMenuItem("Recommanded Items");
		recom_i.addActionListener(this);
		recom.add(recom_i);
		
		JMenuBar bar = new JMenuBar();
		bar.add(modemenu);
		bar.add(recom);
		setJMenuBar(bar);
	}

	String drinks[];
	String ramens[];
	String snacks[];
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		
		if(actionCmd.equals("Beverage"))
		{
			products.removeAll();
			beverage bev = new beverage();
			bev.display();
			bev.setVisible(true);
		}
		else if(actionCmd.equals("Snack"))
		{
			products.removeAll();
			snack snacks = new snack();
			snacks.display();
			snacks.setVisible(true);
		}
		else if(actionCmd.equals("Ramen"))
		{ 
			products.removeAll();
			ramen ramens = new ramen();
			ramens.display();
			ramens.setVisible(true);
			
		}
		
		else if(actionCmd.equals("bev"))
		{
			beverage beverages = new beverage();
			beverages.add_stock();
		}
		
		else if(actionCmd.equals("sna"))
		{
			snack snacks=new snack();
			snacks.add_stock();
		}

		else if(actionCmd.equals("ram"))
		{
			ramen ramens=new ramen();
			ramens.add_stock();
		}
		else if(actionCmd.equals("Recommanded Items"))
		{
			Recommanded re = new Recommanded();
			re.setVisible(true);
		}
		else
		{
			iofield.setText("Unexpected error.");
		}
	}

}
