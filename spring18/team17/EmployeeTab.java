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

public class EmployeeTab extends JPanel implements ActionListener{
	public static int EmployeeCount = 0;

	JButton addE = new JButton("추가");
	JButton editE = new JButton("편집");
	JButton deleteE = new JButton("삭제");
	
	JPanel buttonP = new JPanel();
	JPanel lowerRight = new JPanel();
	
	static JTable EmployeeTable;
	static DefaultTableModel tableModel;
	JScrollPane scroll;
	
	String[] header = {"번호","이름","급여","직급","입사일","연락처"};
	public EmployeeTab() {
		setLayout(new BorderLayout());
		lowerRight.setLayout(new BorderLayout());
		
		addE.setActionCommand("add");
		editE.setActionCommand("edit");
		deleteE.setActionCommand("delete");
		
		addE.addActionListener(this);
		editE.addActionListener(this);
		deleteE.addActionListener(this);
		
		buttonP.add(editE);
		buttonP.add(addE);
		buttonP.add(deleteE);
		
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
			String filename = "employee.dat";
			try
			{
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename));
				EmployeeCount = inputStream.readInt();
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
		
		EmployeeTable = new JTable(tableModel);
		
		scroll = new JScrollPane(EmployeeTable);
		EmployeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(scroll,BorderLayout.CENTER);
	}
	
	public static void saveEmployee()
	{
		ObjectOutputStream outputStream = null;
		String filename = "employee.dat";
		
		try{
			outputStream = new ObjectOutputStream(new FileOutputStream(filename));
			outputStream.writeInt(EmployeeCount);
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
	
	public static void payWage()
	{
		int count = EmployeeCount;
		for(int i=0;i<count;i++)
		{
			int tempWage = Integer.parseInt((String)tableModel.getValueAt(i, 2));
			MainFrame.totalMoney -= tempWage;
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand() == "add")
		{
			new AddEmployee();
		}
		else if(arg0.getActionCommand() == "edit")
		{
			if(EmployeeTable.getSelectedRow() == -1)
			{
				JOptionPane.showMessageDialog(this, "직원이 선택되지 않았습니다", "직원 편집", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int now = EmployeeTable.getSelectedRow();
				new EditEmployee(now);
			}
		}
		else if(arg0.getActionCommand() == "delete")
		{
			if(EmployeeTable.getSelectedRow() == -1)
			{
				JOptionPane.showMessageDialog(this, "직원이 선택되지 않았습니다", "직원 삭제", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int now = EmployeeTable.getSelectedRow();
				tableModel.removeRow(now);
				MemberTab.memberCount--;
			}
		}
		
	}
}

class AddEmployee extends JFrame implements ActionListener {

	JButton add = new JButton("등록");
	JButton cancel = new JButton("취소");
	JPanel buttonP = new JPanel();
	JPanel selectP = new JPanel();
	
	JLabel name = new JLabel("이름");
	JLabel contact = new JLabel("연락처");
	JLabel number = new JLabel("번호");
	JLabel wage = new JLabel("급여");
	JLabel position = new JLabel("직급");
	
	JTextField nameF = new JTextField();
	JTextField contactF = new JTextField();
	JTextField numberF = new JTextField();
	JTextField wageF = new JTextField();
	JTextField positionF = new JTextField();
	
	public AddEmployee(){
		super("직원 등록");
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
		
		selectP.setLayout(new GridLayout(5,2));
		name.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		contact.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		number.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		wage.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		position.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

		nameF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		contactF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		numberF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		wage.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		position.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

		
		name.setHorizontalAlignment(JTextField.CENTER);
		contact.setHorizontalAlignment(JTextField.CENTER);
		number.setHorizontalAlignment(JTextField.CENTER);
		wage.setHorizontalAlignment(JTextField.CENTER);
		position.setHorizontalAlignment(JTextField.CENTER);

		nameF.setHorizontalAlignment(JTextField.CENTER);
		contactF.setHorizontalAlignment(JTextField.CENTER);
		numberF.setHorizontalAlignment(JTextField.CENTER);
		wageF.setHorizontalAlignment(JTextField.CENTER);
		positionF.setHorizontalAlignment(JTextField.CENTER);

		selectP.add(number);
		selectP.add(numberF);
		selectP.add(name);
		selectP.add(nameF);
		selectP.add(wage);
		selectP.add(wageF);
		selectP.add(position);
		selectP.add(positionF);
		selectP.add(contact);
		selectP.add(contactF);
		
		
		add(buttonP,BorderLayout.SOUTH);
		add(selectP,BorderLayout.CENTER);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "add")
		{
			String[] input = {numberF.getText(),nameF.getText(),wageF.getText(),positionF.getText(),MainFrame.getDay(),contactF.getText()};
			try {
			EmployeeTab.tableModel.addRow(input);
			EmployeeTab.EmployeeCount++;
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

class EditEmployee extends JFrame implements ActionListener {

	private static int index;
	JButton add = new JButton("등록");
	JButton cancel = new JButton("취소");
	JPanel buttonP = new JPanel();
	JPanel selectP = new JPanel();
	
	JLabel name = new JLabel("이름");
	JLabel contact = new JLabel("연락처");
	JLabel number = new JLabel("번호");
	JLabel wage = new JLabel("급여");
	JLabel position = new JLabel("직급");
	
	JTextField nameF = new JTextField();
	JTextField contactF = new JTextField();
	JTextField numberF = new JTextField();
	JTextField wageF = new JTextField();
	JTextField positionF = new JTextField();
	
	String day;
	
	String[] data = new String[6];
	
	public EditEmployee(int index){
		super("직원 편집");
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
		
		for(int i=0;i<6;i++)
		{
			data[i] = (String) (EmployeeTab.tableModel.getValueAt(index, i));
		}
		selectP.setLayout(new GridLayout(5,2));
		name.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		contact.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		number.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		wage.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		position.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

		nameF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		contactF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		numberF.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		wage.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		position.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

		
		name.setHorizontalAlignment(JTextField.CENTER);
		contact.setHorizontalAlignment(JTextField.CENTER);
		number.setHorizontalAlignment(JTextField.CENTER);
		wage.setHorizontalAlignment(JTextField.CENTER);
		position.setHorizontalAlignment(JTextField.CENTER);

		nameF.setHorizontalAlignment(JTextField.CENTER);
		contactF.setHorizontalAlignment(JTextField.CENTER);
		numberF.setHorizontalAlignment(JTextField.CENTER);
		wageF.setHorizontalAlignment(JTextField.CENTER);
		positionF.setHorizontalAlignment(JTextField.CENTER);

		numberF.setText(data[0]);
		nameF.setText(data[1]);
		wageF.setText(data[2]);
		positionF.setText(data[3]);
		contactF.setText(data[5]);
		
		selectP.add(number);
		selectP.add(numberF);
		selectP.add(name);
		selectP.add(nameF);
		selectP.add(wage);
		selectP.add(wageF);
		selectP.add(position);
		selectP.add(positionF);
		selectP.add(contact);
		selectP.add(contactF);
		
		add(buttonP,BorderLayout.SOUTH);
		add(selectP,BorderLayout.CENTER);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "add")
		{
			String[] input = {numberF.getText(),nameF.getText(),wageF.getText(),positionF.getText(),(String)EmployeeTab.EmployeeTable.getValueAt(index, 4),contactF.getText()};
			EmployeeTab.tableModel.removeRow(index);
			EmployeeTab.tableModel.insertRow(EditEmployee.index, input);
			dispose();
		}
		else if(arg0.getActionCommand() == "cancel")
		{
			dispose();
		}		
	}
	
	
}

