package cafe;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.*;

import cafe.EmployeeBoard.EditActionListener;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MemberBoard extends JPanel
{
	private static final int WIDTH = 910;
	private static final int HEIGHT = 610;
	
	JTextField text1 = new JTextField(1);
	JTextField text2 = new JTextField(5);
	JTextField text3 = new JTextField(5);
	JTextField text4 = new JTextField(5);
	JTextField text5 = new JTextField(12);
	
	private MemberData md;
	
	public MemberBoard()
	{
		super();
		
		setSize(WIDTH, HEIGHT);
		setBackground(Color.WHITE);
		
		
		setTable();
		//setEditPnl();
	}
	
	public void setTable()
	{
		md = new MemberData();
		
		String header[] = {"번호", "등급", "이름", "마일리지", "연락처"};
		
		DefaultTableModel model = new DefaultTableModel(header, 0);
		setLayout(null);
		JTable table = new JTable(model);
		
		table.setFillsViewportHeight(true);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setSize(910, 500);
		add(scroll);
		scroll.setLocation(0, 0);

		//패널 
		JPanel panel = new JPanel();
		
		//파일 만들기 
//		try
//		{
//			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MemberData.dat"));
//			
//			outputStream.writeObject(md);
//			
//			outputStream.close();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
		
		//파일에 저장되어있는 자료넣기 
		try
		{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MemberData.dat"));
									
			md = (MemberData)inputStream.readObject();
							
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
								
		DefaultTableModel Tmodel = (DefaultTableModel) table.getModel();
				
		String[][] ele = new String[100][5];
		for(int i = 0; i < md.getTableElement().length; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				ele[i][j] = md.getTableElement()[i][j];
			}
		}
				
		
		JButton button1 = new JButton("add");
		JButton button2 = new JButton("del");
		JButton button3 = new JButton("edit");
		JButton button4 = new JButton("clear");
		
		button1.setSize(60, 30);
		button2.setSize(60, 30);
		button3.setSize(60, 30);
		button4.setSize(160, 30);

		JLabel num = new JLabel("번호");
		num.setSize(30, 30);
		panel.add(num);
		num.setLocation(30, 45);
		text1.setSize(40, 30);
		panel.add(text1);
		text1.setLocation(55, 45);
		
		JLabel rank = new JLabel("등급");
		rank.setSize(30, 30);
		panel.add(rank);
		rank.setLocation(105, 45);
		text2.setSize(60, 30);
		panel.add(text2);
		text2.setLocation(130, 45);
		
		JLabel name = new JLabel("이름");
		name.setSize(30, 30);
		panel.add(name);
		name.setLocation(200, 45);
		text3.setSize(60, 30);
		panel.add(text3);
		text3.setLocation(225, 45);
		
		JLabel mileage = new JLabel("마일리지");
		mileage.setSize(50, 30);
		panel.add(mileage);
		mileage.setLocation(295, 45);
		text4.setSize(60, 30);
		panel.add(text4);
		text4.setLocation(340, 45);
		
		JLabel phone = new JLabel("연락처");
		phone.setSize(40, 30);
		panel.add(phone);
		phone.setLocation(415, 45);
		text5.setSize(110, 30);
		panel.add(text5);
		text5.setLocation(455, 45);
		  
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		
		button1.setLocation(700, 45);
		button2.setLocation(750, 45);
		button3.setLocation(800, 45);
		button4.setLocation(700, 70);
		  
		panel.setSize(930, 110);
		add(panel);
		panel.setLocation(0, 500);
		setVisible(true);
		
		//파일에 있는거 화면에 표시 
		for(int i = 0; i < 100; i++)
		{
			if(ele[i][0] != null)
			{
				Tmodel.addRow(ele[i]);
			}
		}
		  
		button1.addActionListener(new AddActionListener(table, text1, text2, text3, text4, text5));
		button2.addActionListener(new RemoveActionListener(table));
		button3.addActionListener(new EditActionListener(table, text1, text2, text3, text4, text5));
		button4.addActionListener(new ClearListener());
	}
	
	
	public class AddActionListener implements ActionListener
	{
		JTable table;
		JTextField text1,text2,text3,text4,text5;
		
		AddActionListener(JTable table, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5) 
		{
			this.table = table;
			this.text1 = text1;
			this.text2 = text2;
			this.text3 = text3;
			this.text4 = text4;
			this.text5 = text5;
		}
		public void actionPerformed(ActionEvent e)
		{
			  String arr[] = new String[5];
			  arr[0] = text1.getText();
			  arr[1] = text2.getText();
			  arr[2] = text3.getText();
			  arr[3] = text4.getText();
			  arr[4] = text5.getText();
			  
			  DefaultTableModel model = (DefaultTableModel) table.getModel();
			  model.addRow(arr);
			  
			  try
				{
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MemberData.dat"));
					String arry[][] = new String[model.getRowCount()][model.getColumnCount()];
					
					for(int i = 0; i < model.getRowCount(); i++)
					{
						for(int j = 0; j < model.getColumnCount(); j++)
						{
							//outputStream.writeObject(table.getValueAt(i, j));
							arry[i][j] = (String)model.getValueAt(i, j);
						}
					}
					md.setTableElement(arry);
					outputStream.writeObject(md); //
					outputStream.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
		}

	 }
	
	public class RemoveActionListener implements ActionListener
	{
		JTable table;
		
		RemoveActionListener(JTable table)
		{
			this.table = table;
		}
		 
		public void actionPerformed(ActionEvent e)
		{
			int row = table.getSelectedRow();
			
			if ( row == -1)
			{
				return;
			}

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.removeRow(row);
			
			try
			{
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MemberData.dat"));
				String arr[][] = new String[100][6];
				
				
				for(int i = 0; i < table.getRowCount(); i++)
				{
					for(int j = 0; j < table.getColumnCount(); j++)
					{
						//outputStream.writeObject(table.getValueAt(i, j));
						arr[i][j] = (String)table.getValueAt(i, j);
					}
				}

				md.setTableElement(arr);
				outputStream.writeObject(md); //
				outputStream.close();
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
		 }
		
	}

	public class EditActionListener implements ActionListener
	{
		JTable table;
		JTextField text1,text2,text3,text4,text5;
		
		EditActionListener(JTable table, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5) 
		{
			this.table = table;
			this.text1 = text1;
			this.text2 = text2;
			this.text3 = text3;
			this.text4 = text4;
			this.text5 = text5;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			int row = table.getSelectedRow();
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			
			model.setValueAt(text1.getText(), row, 0);
			model.setValueAt(text2.getText(), row, 1);
			model.setValueAt(text3.getText(), row, 2);
			model.setValueAt(text4.getText(), row, 3);
			model.setValueAt(text5.getText(), row, 4);
			
			try
			{
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MemberData.dat"));
				String arr[][] = new String[model.getRowCount()][model.getColumnCount()];
				
				for(int i = 0; i < model.getRowCount(); i++)
				{
					for(int j = 0; j < model.getColumnCount(); j++)
					{
						//outputStream.writeObject(table.getValueAt(i, j));
						arr[i][j] = (String)model.getValueAt(i, j);
					}
				}
				md.setTableElement(arr);
				outputStream.writeObject(md); //
				outputStream.close();
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}
		
	public class ClearListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			text1.setText("");
			text2.setText("");
			text3.setText("");
			text4.setText("");
			text5.setText("");
		}
	}
}
