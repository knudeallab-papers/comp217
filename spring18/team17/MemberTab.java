package javaPr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MemberTab extends JPanel implements ActionListener{
	
	static public int memberCount = 0;
	
	JButton addM = new JButton("추가");
	JButton editM = new JButton("편집");
	JButton deleteM = new JButton("삭제");
	
	JPanel buttonP = new JPanel();
	JPanel lowerRight = new JPanel();
	
	static JTable memberTable;
	static DefaultTableModel tableModel;
	JScrollPane scroll;
	
	String[] header = {"번호","등급","이름","마일리지","연락처"};

	public MemberTab() {
		setLayout(new BorderLayout());
		lowerRight.setLayout(new BorderLayout());
		
		addM.setActionCommand("add");
		editM.setActionCommand("edit");
		deleteM.setActionCommand("delete");
		
		addM.addActionListener(this);
		editM.addActionListener(this);
		deleteM.addActionListener(this);
		
		buttonP.add(editM);
		buttonP.add(addM);
		buttonP.add(deleteM);
		
		lowerRight.add(buttonP, BorderLayout.EAST);
		add(lowerRight,BorderLayout.SOUTH);
		
		
		if(MainFrame.isLoaded == false) {
			tableModel = new DefaultTableModel(header,0){
				public boolean isCellEditable(int i, int c)
				{
					return false;
				}
			};
		}
		else
		{
			String filename = "member.dat";
			try
			{
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename));
				memberCount = inputStream.readInt();
				tableModel = (DefaultTableModel) inputStream.readObject();
				inputStream.close();
				if(tableModel == null)
				{
					tableModel = new DefaultTableModel(header,0){
						public boolean isCellEditable(int i, int c)
						{
							return false;
						}
					};
				}
			}catch(FileNotFoundException e){
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		memberTable = new JTable(tableModel);
		
		scroll = new JScrollPane(memberTable);
		memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(scroll,BorderLayout.CENTER);
	}

	public static void saveMember()
	{
		ObjectOutputStream outputStream = null;
		String filename = "member.dat";
		
		try{
			outputStream = new ObjectOutputStream(new FileOutputStream(filename));
			outputStream.writeInt(memberCount);
			outputStream.writeObject(tableModel);
			
			outputStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "add")
		{
			new AddMember();
		}
		else if(arg0.getActionCommand() == "edit")
		{
			if(memberTable.getSelectedRow() == -1)
			{
				JOptionPane.showMessageDialog(this, "회원이 선택되지 않았습니다", "회원 편집", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int now = memberTable.getSelectedRow();
				new EditMember(now);
			}
		}
		else if(arg0.getActionCommand() == "delete")
		{
			if(memberTable.getSelectedRow() == -1)
			{
				JOptionPane.showMessageDialog(this, "회원이 선택되지 않았습니다", "회원 삭제", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int now = memberTable.getSelectedRow();
				tableModel.removeRow(now);
				MemberTab.memberCount--;
			}
		}
	}
	
	static public int getMemberNum()
	{
		return tableModel.getRowCount();
	}
	
	static public void addMileage(int price, int now)
	{
		String temp = (String) tableModel.getValueAt(now, 3);
		int intTemp = Integer.parseInt(temp);
		intTemp += (int)(price*0.02);
		temp = Integer.toString(intTemp);
		String rate = getRate(temp);
		
		String[] input = {(String) tableModel.getValueAt(now, 0),rate,(String) tableModel.getValueAt(now, 2),temp,(String) tableModel.getValueAt(now, 4)};
		
		tableModel.removeRow(now);
		tableModel.insertRow(now, input);
	}
	
	static public void initMileage(int now)
	{		
		String[] input = {(String) tableModel.getValueAt(now, 0),"SILVER",(String) tableModel.getValueAt(now, 2),"0",(String) tableModel.getValueAt(now, 4)};
		
		tableModel.removeRow(now);
		tableModel.insertRow(now, input);
	}
	
	
	static public String getRate(String mileage)
	{
		int mile = Integer.parseInt(mileage);
		if(mile>=1000)
		{
			return "PLATINUM";
		}
		else if(mile>=500)
		{
			return "GOLD";
		}
		else
		{
			return "NORMAL";
		}
	}
}

class AddMember extends JFrame implements ActionListener {

	JButton add = new JButton("등록");
	JButton cancel = new JButton("취소");
	JPanel buttonP = new JPanel();
	JPanel selectP = new JPanel();
	
	JLabel name = new JLabel("이름");
	JLabel contact = new JLabel("연락처");
	JLabel number = new JLabel("번호");
	JTextField nameF = new JTextField();
	JTextField contactF = new JTextField();
	JTextField numberF = new JTextField();
	
	public AddMember(){
		super("회원 등록");
		setLayout(new BorderLayout());
		setBounds(1000,200,400,400);
		setResizable(false);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
		add.setActionCommand("add");
		cancel.setActionCommand("cancel");
		add.addActionListener(this);
		cancel.addActionListener(this);
		buttonP.add(add);
		buttonP.add(cancel);
		
		selectP.setLayout(new GridLayout(3,2));
		name.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		contact.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		number.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		nameF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		contactF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		numberF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		
		name.setHorizontalAlignment(JTextField.CENTER);
		contact.setHorizontalAlignment(JTextField.CENTER);
		number.setHorizontalAlignment(JTextField.CENTER);
		nameF.setHorizontalAlignment(JTextField.CENTER);
		contactF.setHorizontalAlignment(JTextField.CENTER);
		numberF.setHorizontalAlignment(JTextField.CENTER);

		selectP.add(name);
		selectP.add(nameF);
		selectP.add(contact);
		selectP.add(contactF);
		selectP.add(number);
		selectP.add(numberF);
		
		add(buttonP,BorderLayout.SOUTH);
		add(selectP,BorderLayout.CENTER);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "add")
		{
			String[] input = {numberF.getText(),"NORMAL",nameF.getText(),"0",contactF.getText()};
			try {
			MemberTab.tableModel.addRow(input);
			MemberTab.memberCount++;
			dispose();
			}catch(NullPointerException e)
			{
				
			}
		}
		else if(arg0.getActionCommand() == "cancel")
		{
			dispose();
		}		
	}
	
}

class EditMember extends JFrame implements ActionListener {

	private static int index;
	JButton add = new JButton("등록");
	JButton cancel = new JButton("취소");
	JPanel buttonP = new JPanel();
	JPanel selectP = new JPanel();
	
	JLabel name = new JLabel("이름");
	JLabel contact = new JLabel("연락처");
	JLabel number = new JLabel("번호");
	JTextField nameF = new JTextField();
	JTextField contactF = new JTextField();
	JTextField numberF = new JTextField();
	String mileage;
	String rate;
	String[] data = new String[5];
	
	public EditMember(int index){
		super("회원 편집");
		this.index = index;
		setLayout(new BorderLayout());
		setBounds(1000,200,400,400);
		setResizable(false);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
		add.setActionCommand("add");
		cancel.setActionCommand("cancel");
		add.addActionListener(this);
		cancel.addActionListener(this);
		buttonP.add(add);
		buttonP.add(cancel);
		
		for(int i=0;i<5;i++)
		{
			data[i] = (String) (MemberTab.tableModel.getValueAt(index, i));
		}
		selectP.setLayout(new GridLayout(3,2));
		name.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		contact.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		number.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		nameF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		contactF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		numberF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		
		name.setHorizontalAlignment(JTextField.CENTER);
		contact.setHorizontalAlignment(JTextField.CENTER);
		number.setHorizontalAlignment(JTextField.CENTER);
		nameF.setHorizontalAlignment(JTextField.CENTER);
		contactF.setHorizontalAlignment(JTextField.CENTER);
		numberF.setHorizontalAlignment(JTextField.CENTER);

		nameF.setText(data[2]);
		contactF.setText(data[4]);
		numberF.setText(data[0]);
		
		selectP.add(name);
		selectP.add(nameF);
		selectP.add(contact);
		selectP.add(contactF);
		selectP.add(number);
		selectP.add(numberF);
		
		add(buttonP,BorderLayout.SOUTH);
		add(selectP,BorderLayout.CENTER);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "add")
		{
			String[] input = {numberF.getText(),MemberTab.getRate((String)MemberTab.memberTable.getValueAt(index, 3)),nameF.getText(),(String)MemberTab.memberTable.getValueAt(index, 3),contactF.getText()};
			MemberTab.tableModel.removeRow(index);
			MemberTab.tableModel.insertRow(EditMember.index, input);
			dispose();
		}
		else if(arg0.getActionCommand() == "cancel")
		{
			dispose();
		}		
	}
	
	
}

