package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MainMenu extends JFrame implements ActionListener{
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 700;
	
	private User p1 = new User();
	private User p2 = new User();
	
	private JLabel p1Label;
	private JLabel p2Label;
	private JTextField p1Text;
	private JTextField p2Text;
	
	private ImageIcon screen = new ImageIcon(MainMenu.class.getResource("/images/menuScreen.png"));
	
	public MainMenu()
	{
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		this.getContentPane().setBackground(new Color(240, 217, 89));
		
		JLabel menuLabel = new JLabel();
		menuLabel.setBounds(50,50, 500, 110);
		menuLabel.setIcon(new ImageIcon(MainMenu.class.getResource("/images/menuLabel.png")));
		add(menuLabel);
		
		/* 유저 추가, 삭제 버튼 */
		JButton addBtn = new JButton("Add User");
		addBtn.setFont(new Font("SanSerif", Font.BOLD, 24));
		addBtn.setBackground(new Color(233, 230, 213));
		addBtn.setActionCommand("Add");
		addBtn.addActionListener(this);
		addBtn.setBounds(40, 200, 250, 60);
		
		JButton delBtn = new JButton("Delete User");
		delBtn.setFont(new Font("SanSerif", Font.BOLD, 24));
		delBtn.setBackground(new Color(233, 230, 213));
		delBtn.setActionCommand("Del");
		delBtn.addActionListener(this);
		delBtn.setBounds(300, 200, 250, 60);
		
		add(addBtn);
		add(delBtn);
		
		/* 유저 정보 나타내는 Label */
		p1Label = new JLabel();
		p1Label.setBounds(130, 280, 230, 35);
		p1Label.setBackground(new Color(240, 217, 89));
		p1Label.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
		p1Label.setForeground(Color.WHITE);
		if (p1.getName().equals(""))
			p1Label.setText("1P User: No Selected"); // 유저가 선택되지(로그인하지) 않았음
		
		p2Label = new JLabel();
		p2Label.setBounds(130, 410, 230, 35);
		p2Label.setBackground(new Color(240, 217, 89));
		p2Label.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 20));
		p2Label.setForeground(Color.WHITE);
		if (p2.getName().equals(""))
			p2Label.setText("2P User: No Selected"); // 유저가 선택되지(로그인하지) 않았음
		
		add(p2Label);
		add(p1Label);
		
		/* 유저 선택(로그인) */
		p1Text = new JTextField("Input User Name");
		p1Text.setBounds(130, 323, 345, 37);
		p1Text.setFont(new Font("SanSerif", Font.PLAIN, 18));
		
		p2Text = new JTextField("Input User Name");
		p2Text.setBounds(130, 445, 345, 37);
		p2Text.setFont(new Font("SanSerif", Font.PLAIN, 18));
		
		add(p1Text);
		add(p2Text);
		
		JButton p1Btn = new JButton("Select User");
		p1Btn.setBounds(130, 363, 345, 37);
		p1Btn.setFont(new Font("SanSerif", Font.PLAIN, 18));
		p1Btn.setBackground(new Color(233, 230, 213));
		p1Btn.setActionCommand("Btn1");
		p1Btn.addActionListener(this);
		
		JButton p2Btn = new JButton("Select User");
		p2Btn.setBounds(130, 485, 345, 37);
		p2Btn.setFont(new Font("SanSerif", Font.PLAIN, 18));
		p2Btn.setBackground(new Color(233, 230, 213));
		p2Btn.setActionCommand("Btn2");
		p2Btn.addActionListener(this);
		
		add(p1Btn);
		add(p2Btn);
		
		/* 게임 선택 버튼 */
		JButton gsBtn = new JButton("Game Select");
		gsBtn.setBounds(107, 550, 180, 85);
		gsBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
		gsBtn.setBackground(new Color(233, 230, 213));
		gsBtn.setActionCommand("Select");
		gsBtn.addActionListener(this);
		
		add(gsBtn);
		
		/* 게임 설명서 버튼 */
		JButton gmBtn = new JButton("Game Manual");
		gmBtn.setBounds(317, 550, 180, 85);
		gmBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
		gmBtn.setBackground(new Color(233, 230, 213));
		gmBtn.setActionCommand("Manual");
		gmBtn.addActionListener(this);
		
		add(gmBtn);
		
		JLabel screenLabel = new JLabel(screen);
		screenLabel.setBounds(0, 0, WIDTH, HEIGHT);
		add(screenLabel);

		File f = new File("datafile.dat");
		if (!f.exists())
		{
			try {
				ObjectOutputStream outputStream = null;
				outputStream = new ObjectOutputStream(new FileOutputStream("datafile.dat"));
				outputStream.writeObject(p1);
				outputStream.writeObject(p2);
				outputStream.close();
			}catch(IOException g) {
				System.err.println("Problem with file output.");
			}
		}
		
		try {
			ObjectInputStream inputStream = null;
			inputStream = new ObjectInputStream(new FileInputStream("datafile.dat"));
			p1 = (User)inputStream.readObject();
			p2 = (User)inputStream.readObject();
			if (!p1.getName().equals(""))
			{
				f = new File(p1.getName() + ".txt");
				if (f.exists())
				{
					Scanner inputStream2 = null;
					try
					{
						inputStream2 = new Scanner(new FileInputStream(p1.getName() + ".txt"));
					}
					catch(FileNotFoundException g)
					{
						g.printStackTrace();
						System.exit(0);
					}
					String data = inputStream2.nextLine();
					p1.setName(data.split(",")[0]);
					p1.setWin(Integer.parseInt(data.split(",")[1]));
					p1.setLose(Integer.parseInt(data.split(",")[2]));
					p1Label.setText("1P User: " + p1.getName() + ",W:" + p1.getWin() +
																	",L:" + p1.getLose());
					inputStream2.close();
				}
				else
				{
					p1.setName("");
					p1.setWin(0);
					p1.setLose(0);
					p1Label.setText("1P User: No Selected");
				}
			}
			if (!p2.getName().equals(""))
			{
				f = new File(p2.getName() + ".txt");
				if (f.exists())
				{
					Scanner inputStream2 = null;
					try
					{
						inputStream2 = new Scanner(new FileInputStream(p2.getName() + ".txt"));
					}
					catch(FileNotFoundException g)
					{
						g.printStackTrace();
						System.exit(0);
					}
					String data = inputStream2.nextLine();
					p2.setName(data.split(",")[0]);
					p2.setWin(Integer.parseInt(data.split(",")[1]));
					p2.setLose(Integer.parseInt(data.split(",")[2]));
					p2Label.setText("2P User: " + p2.getName() + ",W:" + p2.getWin() +
																	",L:" + p2.getLose());
					inputStream2.close();
				}
				else
				{
					p2.setName("");
					p2.setWin(0);
					p2.setLose(0);
					p2Label.setText("2P User: No Selected");
				}
			}
			inputStream.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}catch(ClassNotFoundException e) {
			f = new File("datafile.dat");
			f.delete();
			System.err.println("Problem with file input.");
			System.exit(0);
		}catch(IOException e) {
			f = new File("datafile.dat");
			f.delete();
			System.err.println("Problem with file input.");
			System.exit(0);
		}
		
		if (!p1.getName().equals(""))
		{
			p1Text.setText(p1.getName());
		}
		if (!p2.getName().equals(""))
		{
			p2Text.setText(p2.getName());
		}
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("Add"))
		{
			AddUser addFrame = new AddUser();
			addFrame.setVisible(true);
		}
		else if (actionCmd.equals("Del"))
		{
			DelUser delFrame = new DelUser();
			delFrame.setVisible(true);
		}
		else if (actionCmd.equals("Btn1"))
		{
			File f = new File(p1Text.getText() + ".txt");
			if (!f.exists())
				JOptionPane.showMessageDialog(this, "User Not Registered", "Message", JOptionPane.INFORMATION_MESSAGE);
			else
			{
				if (p2.getName().equals(p1Text.getText()))
					JOptionPane.showMessageDialog(this, "User Already Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
				else
				{
					Scanner inputStream = null;
					try
					{
						inputStream = new Scanner(f);
					}
					catch(FileNotFoundException g)
					{
						g.printStackTrace();
						System.exit(0);
					}
					String data = inputStream.nextLine();
					p1.setName(data.split(",")[0]);
					p1.setWin(Integer.parseInt(data.split(",")[1]));
					p1.setLose(Integer.parseInt(data.split(",")[2]));
					JOptionPane.showMessageDialog(this, "Welcome " + p1Text.getText(), "Message", JOptionPane.INFORMATION_MESSAGE);
					p1Label.setText("1P User: " + p1Text.getText() + ",W:" + p1.getWin() +
																	",L:" + p1.getLose());
				}
			}
		}
		else if (actionCmd.equals("Btn2"))
		{
			File f = new File(p2Text.getText() + ".txt");
			if (!f.exists())
				JOptionPane.showMessageDialog(this, "User Not Registered", "Message", JOptionPane.INFORMATION_MESSAGE);
			else
			{
				if (p1.getName().equals(p2Text.getText()))
					JOptionPane.showMessageDialog(this, "User Already Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
				else
				{
					Scanner inputStream = null;
					try
					{
						inputStream = new Scanner(f);
					}
					catch(FileNotFoundException g)
					{
						g.printStackTrace();
						System.exit(0);
					}
					String data = inputStream.nextLine();
					p2.setName(data.split(",")[0]);
					p2.setWin(Integer.parseInt(data.split(",")[1]));
					p2.setLose(Integer.parseInt(data.split(",")[2]));
					JOptionPane.showMessageDialog(this, "Welcome " + p2Text.getText(), "Message", JOptionPane.INFORMATION_MESSAGE);
					p2Label.setText("2P User: " + p2Text.getText() + ",W:" + p2.getWin() +
																	",L:" + p2.getLose());
					inputStream.close();
				}
			}
		}
		else if (actionCmd.equals("Select"))
		{
			File f1 = new File(p1.getName() + ".txt");
			File f2 = new File(p2.getName() + ".txt");
			if (p1.getName().equals("") || p2.getName().equals(""))
				JOptionPane.showMessageDialog(this, "Users Not Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
			else if (!f1.exists() || !f2.exists())
			{
				System.err.println("Fatal Error");
				System.exit(0);
			}
			else
			{
				try {
					ObjectOutputStream outputStream = null;
					outputStream = new ObjectOutputStream(new FileOutputStream("datafile.dat"));
					outputStream.writeObject(p1);
					outputStream.writeObject(p2);
					outputStream.close();
				}catch(IOException g) {
					System.err.println("Problem with file output.");
				}
				new GameSelect(p1, p2);

				dispose();
			}
		}
		else if (actionCmd.equals("Manual")) {
			new GameManual();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainMenu();
	}
}
