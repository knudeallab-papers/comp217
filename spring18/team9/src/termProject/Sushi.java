package termProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Sushi extends JFrame implements ActionListener, Serializable{
	public static final int WIDTH = 1500;
	public static final int HEIGHT = 1000;
	public static final int SMALL_WIDTH = 500;
	public static final int SMALL_HEIGHT = 250;
	
	static Sushi gui;
	static TablePanel tablePanel;
	static StoragePanel storagePanel;
	static MenuPanel menuPanel;
	static GuestPanel guestPanel;
	static MemberPanel memberPanel;
	
	Font font = new Font("�������", Font.BOLD, 20);
	
	Calendar calendar;
	public static int year;
	public static int month;
	public static int day;
	public static String date;
	
	public static int sales;
	public static int balance;
	JLabel dateLabel;
	public static String moneyStr;
	static JLabel moneyLabel;
	
	private JPanel bottomPanel;
	
	private JToggleButton tableMenu;
	private JToggleButton storageMenu;
	private JToggleButton memberMenu;
	private JToggleButton menuMenu;
	private JToggleButton staffMenu;
	
	private ButtonGroup bg = new ButtonGroup();
	
	private CardLayout cardLayout = new CardLayout();
	
	public Sushi() {
		super("�����̸�");
		setSize(WIDTH, HEIGHT); //�ʺ�����
		setLayout(new BorderLayout(0, 10));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(1500, 80));
		topPanel.setLayout(new BorderLayout());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout());
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1; //0~11��(0:1��)
		day = calendar.get(Calendar.DAY_OF_MONTH);
		date = year + "�� " + month + "�� " + day + "��";
		dateLabel = new JLabel(date);
		leftPanel.add(dateLabel);
		
		JButton deadlineBtn = new JButton("����");
		deadlineBtn.setBackground(Color.WHITE);
		deadlineBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DeadlineWindow deadlineWindow = new DeadlineWindow();
				deadlineWindow.setVisible(true);
			}
		});
		leftPanel.add(deadlineBtn);
		topPanel.add(leftPanel, BorderLayout.WEST);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new FlowLayout());
		sales = 0;
		balance = 0;
		moneyStr = "���� ���� : " + sales + "�� ��ü �ܰ� : " + balance + "��";
		moneyLabel = new JLabel(moneyStr);
		rightPanel.add(moneyLabel);
		JButton exitBtn = new JButton("����");
		exitBtn.setBackground(Color.WHITE);
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "���� ���� �Ͻðڽ��ϱ�?", 
						"���� â", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(result == 0) {
					ObjectOutputStream outputStream = null;
					try {
						outputStream = new ObjectOutputStream(new FileOutputStream("sushi.dat"));
						outputStream.writeObject(gui);
						outputStream.writeObject(new TablePanel());
						outputStream.writeObject(new StoragePanel());
						outputStream.writeObject(new GuestPanel());
						outputStream.writeObject(new MenuPanel());
						outputStream.writeObject(new MemberPanel());
						
						//outputStream.writeObject(StoragePanel.struct);
						//outputStream.writeObject(GuestPanel.struct);
						//outputStream.writeObject(MenuPanel.struct);
						//outputStream.writeObject(MemberPanel.struct);
						
						outputStream.close();
					}catch(IOException e2) {
						e2.printStackTrace();
						System.err.println("Problem with file output.");
					}
					System.exit(0);
					
				}else
					return;
				
			}
		});
		rightPanel.add(exitBtn);
		topPanel.add(rightPanel, BorderLayout.EAST);
		
		JPanel barPanel = new JPanel();
		barPanel.setLayout(new GridLayout(1, 5));
		tableMenu = new JToggleButton("���̺�");
		tableMenu.setFont(font);
		tableMenu.setBackground(Color.WHITE);
		tableMenu.addActionListener(this);
		bg.add(tableMenu);
		barPanel.add(tableMenu);
		storageMenu = new JToggleButton("â��");
		storageMenu.setFont(font);
		storageMenu.setBackground(Color.WHITE);
		storageMenu.addActionListener(this);
		bg.add(storageMenu);
		barPanel.add(storageMenu);
		memberMenu = new JToggleButton("ȸ��");
		memberMenu.setFont(font);
		memberMenu.setBackground(Color.WHITE);
		memberMenu.addActionListener(this);
		bg.add(memberMenu);
		barPanel.add(memberMenu);
		menuMenu = new JToggleButton("�޴�");
		menuMenu.setFont(font);
		menuMenu.setBackground(Color.WHITE);
		menuMenu.addActionListener(this);
		bg.add(menuMenu);
		barPanel.add(menuMenu);
		staffMenu = new JToggleButton("����");
		staffMenu.setFont(font);
		staffMenu.setBackground(Color.WHITE);
		staffMenu.addActionListener(this);
		bg.add(staffMenu);
		barPanel.add(staffMenu);
		topPanel.add(barPanel, BorderLayout.SOUTH);
		
		add(topPanel, BorderLayout.NORTH);
		
		tablePanel = new TablePanel();
		storagePanel = new StoragePanel();
		menuPanel = new MenuPanel();
		guestPanel = new GuestPanel();
		memberPanel = new MemberPanel();
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(cardLayout);
		bottomPanel.add(tablePanel, "1");
		bottomPanel.add(storagePanel, "2");
		bottomPanel.add(guestPanel, "3");
		bottomPanel.add(menuPanel, "4");
		bottomPanel.add(memberPanel, "5");
		cardLayout.show(bottomPanel, "1");
		add(bottomPanel, BorderLayout.CENTER);
	}
	
	private class DeadlineWindow extends JFrame implements ActionListener, Serializable{
		
		public DeadlineWindow() {
			super("���� â");
			setSize(SMALL_WIDTH, SMALL_HEIGHT);
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new BorderLayout());
			JLabel confirmLabel = new JLabel("������ �����Ͻðڽ��ϱ�?");
			topPanel.add(confirmLabel, BorderLayout.CENTER);
			add(topPanel, BorderLayout.CENTER);
			
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new FlowLayout());
			JButton confirmBtn = new JButton("Ȯ��");
			confirmBtn.setBackground(Color.WHITE);
			confirmBtn.addActionListener(this);
			bottomPanel.add(confirmBtn);
			JButton cancelBtn = new JButton("���");
			cancelBtn.setBackground(Color.WHITE);
			cancelBtn.addActionListener(this);
			bottomPanel.add(cancelBtn);
			add(bottomPanel, BorderLayout.SOUTH);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String actionCmd = e.getActionCommand();
			
			Color[] color = new Color[8];
			color[0] = TablePanel.table1B.getBackground();
			color[1] = TablePanel.table2B.getBackground();
			color[2] = TablePanel.table3B.getBackground();
			color[3] = TablePanel.table4B.getBackground();
			color[4] = TablePanel.table5B.getBackground();
			color[5] = TablePanel.table6B.getBackground();
			color[6] = TablePanel.table7B.getBackground();
			color[7] = TablePanel.table8B.getBackground();
			
			if(actionCmd.equals("Ȯ��")) {
				//���ȵ� ���̺�->�����޽���
				for(int i=0; i<8; i++)
					if(color[i].equals(Color.BLUE)) {
						JOptionPane.showMessageDialog(this, "�մ��� �ֽ��ϴ�", "��� â", JOptionPane.ERROR_MESSAGE);
						dispose();
						return;
					}
				
				balance += sales;
	            sales = 0;
	            
	            //��� ���� ����
	            StoragePanel.storageOrder();
	            //���� �޿� ����
	            MemberPanel.paySalary(day);
	            
	            calendar.add(Calendar.DAY_OF_MONTH, 1);
	            year = calendar.get(Calendar.YEAR);
	            month = calendar.get(Calendar.MONTH) + 1; //0~11��(0:1��)
	            day = calendar.get(Calendar.DAY_OF_MONTH);
	            date = year + "�� " + month + "�� " + day + "��";
	            dateLabel.setText(date);
	            if(day == 1)
	            	GuestPanel.initializeMileage();
	            
	            moneyStr = "���� ���� : " + sales + "�� ��ü �ܰ� : " + balance + "��";
	            moneyLabel.setText(moneyStr);
	            dispose();

			}else if(actionCmd.equals("���")) {
				dispose();
			}else
				System.out.println("Unexpected error in DeadlineWindow");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		
		if(actionCmd.equals("���̺�"))
			cardLayout.show(bottomPanel, "1");
		else if(actionCmd.equals("â��"))
			cardLayout.show(bottomPanel, "2");
		else if(actionCmd.equals("ȸ��"))
			cardLayout.show(bottomPanel, "3");
		else if(actionCmd.equals("�޴�"))
			cardLayout.show(bottomPanel, "4");
		else if(actionCmd.equals("����"))
			cardLayout.show(bottomPanel, "5");
		else
			System.err.println("Unexpected error in Menu Bar");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "sushi.dat";
		
		File fileObj = new File(fileName);
		if(fileObj.exists()) {
			try {
				ObjectInputStream inputStream = 
						new ObjectInputStream(new FileInputStream("sushi.dat"));
				Sushi sushi = (Sushi)inputStream.readObject();
				TablePanel tablepanel = (TablePanel)inputStream.readObject();
				StoragePanel storagepanel = (StoragePanel)inputStream.readObject();
				GuestPanel guestpanel = (GuestPanel)inputStream.readObject();
				MenuPanel menupanel = (MenuPanel)inputStream.readObject();
				MemberPanel memberpanel= (MemberPanel)inputStream.readObject();
				//storagePanel.struct = (IngreStruct[])inputStream.readObject();
				//guestPanel.struct = (GuestStruct[])inputStream.readObject();
				//menuPanel.struct = (MenuStruct[])inputStream.readObject();
				//memberPanel.struct = (MemberStruct[])inputStream.readObject();
				
				sushi.setVisible(true);
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				System.err.println("Problem one with file input.");
				e.printStackTrace();
			}catch(IOException e) {
				System.err.println("Problem two with file input.");
				e.printStackTrace();
			}
			
		}else {
			gui = new Sushi();
			gui.setVisible(true);
			
		}
	}
}
