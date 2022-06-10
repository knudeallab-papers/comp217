package cafe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

import cafe.DetailPanel.func;

public class InvenDetailPanel extends JPanel
{
	private static final int WIDTH = 450;
	private static final int HEIGHT = 610;
	
	private InvenDetail Detail[] = new InvenDetail[45];
	
	private InvenDetail cur;
	private int curNum;
	
	private JButton EditBtn;
	private JButton SubmitBtn;
	private JButton ResetBtn;
	
	private InvenDataSet data;
	
	public InvenDetailPanel()
	{
		super();
		setSize(WIDTH, HEIGHT);
		
//		setDummy();
		
		getMenuFile();
		
		setBtn();

		setDetailDefault();
		
	}
	public void getMenuFile()
	{
		data = new InvenDataSet();
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("InvenData.dat"));
			
			data = (InvenDataSet)inputStream.readObject();
			
			
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
	public void goNextDay()
	{
		for(int c1 = 0; c1 < 45; c1++)
		{
			Detail[c1].nextDayAct();
			
			InvenData t = data.getData(c1);

//			t.name = Detail[c1].name.getText();
//			t.price = Double.parseDouble(Detail[c1].price.getText());
			t.stock = Integer.parseInt(Detail[c1].stock.getText());
			t.order = Integer.parseInt(Detail[c1].order.getText());
//			t.loc = Detail[c1].loc.getText();
//			t.contact = Detail[c1].contact.getText();
//			
//			if(cur.name.getText().equals("") || cur.price.getText().equals("0") || cur.loc.getText().equals("") || cur.contact.getText().equals(""))
//			{
//				t.exist = false;
//			}
//			else
//			{
//				t.exist = true;
//			}

		}

		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("InvenData.dat"));
			outputStream.writeObject(data);
			outputStream.close();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	private void setDummy()
	{
		cur = new InvenDetail();
		add(cur);
		cur.setLocation(15, 15);
	}
	private void setDetailDefault()
	{
		setDummy();
		for(int c1 = 0; c1 < 45; c1++)
		{
			InvenData t = data.getData(c1);
			
			Detail[c1] = new InvenDetail();
			Detail[c1].name.setText(t.name);
			Detail[c1].price.setText(Double.toString(t.price));
			Detail[c1].stock.setText(Integer.toString(t.stock));
			Detail[c1].order.setText(Integer.toString(t.order));
			Detail[c1].loc.setText(t.loc);
			Detail[c1].contact.setText(t.contact);
		}
	}
	private void setBtn()
	{
		EditBtn = new JButton("편집");
		EditBtn.setSize(90, 45);
		add(EditBtn);
		EditBtn.setLocation(150, 560);
		EditBtn.addActionListener(new func());
		
		SubmitBtn = new JButton("저장");
		SubmitBtn.setSize(90, 45);
		add(SubmitBtn);
		SubmitBtn.setLocation(250, 560);
		SubmitBtn.addActionListener(new func());
		
		ResetBtn = new JButton("초기화");
		ResetBtn.setSize(90, 45);
		add(ResetBtn);
		ResetBtn.setLocation(350, 560);
		ResetBtn.addActionListener(new func());
	}
	
	public void setDetail(int n)
	{
		remove(cur);
		repaint();
		cur = Detail[n];
		add(cur);
		cur.setLocation(15, 15);
		curNum = n;
//		curKind = "Coffee";
		cur.setTextEditableFalse();
	}
	private void updateMenuData()
	{
		MenuDataSet data1 = new MenuDataSet();
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("MenuData.dat"));
			
			data1 = (MenuDataSet)inputStream.readObject();
			
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
		
		for(int c1 = 0; c1 < 15; c1++)
		{
			data1.getCoffeeData(c1).CalPrimeCost();
			data1.getNonCoffeeData(c1).CalPrimeCost();
			data1.getDessertData(c1).CalPrimeCost();
		}
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MenuData.dat"));
			outputStream.writeObject(data1);
			outputStream.close();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
	}
	class func implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String actionCommand = e.getActionCommand();
			
			if(actionCommand.equals("편집"))
			{
				cur.setTextEditable();
			}
			else if(actionCommand.equals("저장"))
			{
				InvenData t = data.getData(curNum);

				t.name = cur.name.getText();
				t.price = Double.parseDouble(cur.price.getText());
				t.stock = Integer.parseInt(cur.stock.getText());
				t.order = Integer.parseInt(cur.order.getText());
				t.loc = cur.loc.getText();
				t.contact = cur.contact.getText();
				
				if(cur.name.getText().equals("") || cur.price.getText().equals("0") || cur.loc.getText().equals("") || cur.contact.getText().equals(""))
				{
					t.exist = false;
				}
				else
				{
					t.exist = true;
				}
				cur.setTextEditableFalse();
				
				try
				{
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("InvenData.dat"));
					outputStream.writeObject(data);
					outputStream.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
				
				updateMenuData();
				
				Actions.addToMainPnl("재고");

			}
			else if(actionCommand.equals("초기화"))
			{
				cur.setTextReset();
			}
			
		
		}
	}
}
