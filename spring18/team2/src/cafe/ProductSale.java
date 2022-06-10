package cafe;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductSale extends JPanel
{
	private static final int WIDTH = 530;
	private static final int HEIGHT = 610;
	
	private String header[] = {"메뉴명", "단가", "수량", "금액"};
//	private String contents[][];
	
	public DefaultTableModel model;
	public JTable table;
	
	private JScrollPane scrollPane;
	private Font tableFont = new Font("SanSerif", Font.PLAIN, 13);
	private curSaleData data;
	
	public ProductSale()
	{
		super();
		setSize(WIDTH, HEIGHT);
		
		getSaleFile();
		
		setTableUI();
		
		
		
//		setBackground(darkGray);
		
	}
	private void getSaleFile()
	{
		data = new curSaleData();
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("CurSaleData.dat"));
			
			data = (curSaleData)inputStream.readObject();
			
			
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
	}
	public void setTableUI()
	{
		model = new DefaultTableModel(data.Contents, header);
		table = new JTable(model);
		table.setFont(tableFont);
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(WIDTH, HEIGHT);
		add(scrollPane);
		scrollPane.setLocation(0, 0);
		
		String[] t = new String[4];
		
		t[0] = "합계";
		t[1] = "";
		t[2] = Integer.toString(data.count);
		t[3] = Double.toString(data.cost);
		
		model.addRow(t);
	}
}
