package cafe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import cafe.CoffeeBoard.sellAct;

public class DessertBoard extends Board 
{
	private int num;
	
	public DessertBoard(String s)
	{
		super();
		num = 0;
		getMenuFile();
		setBtnText();
		setBtnActionListener(s);
	}
	public void getMenuFile()
	{
		data = new MenuDataSet();
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("MenuData.dat"));
			
			data = (MenuDataSet)inputStream.readObject();
			
			
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
	public void setBtnActionListener(String s)
	{
		if(s.equals("메뉴"))
		{
			for(int c1 = 0; c1 < BtnNum; c1++)
			{
				menuBtn[c1].addActionListener(new menuAct());
			}
		}
		else if(s.equals("판매"))
		{
			for(int c1 = 0; c1 < BtnNum; c1++)
			{
				menuBtn[c1].addActionListener(new sellAct());
			}
		}
	}
	public void setBtnText()
	{
		for(int c1 = 0; c1 < BtnNum; c1++)
		{
			String name;
			String price;
//			if(data.getCoffeeData(c1) != null)
//			{
//				name = data.getCoffeeData(c1).getName();
//				price = Double.toString(data.getCoffeeData(c1).getPrice());
//
//				menuBtn[c1].setText("<html>" + name + "<br />" + price + "</html>");
//			}
			
			if(data.getDessertData(c1).getExist())
			{
				name = data.getDessertData(c1).getName();
				price = Double.toString(data.getDessertData(c1).getPrice());

				if(name.length() < 9)
				{
					menuBtn[c1].setText("<html>" + name + "<br />" + price + "</html>");
				}
				else
				{
					System.out.print("1");
					menuBtn[c1].setText("<html>" + name.substring(0, 8) + "<br />" + name.substring(8) + "<br />" + price + "</html>");
				}
			}
		}
	}
	class menuAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			menuBtn[num].setBackground(unClicked);
			String actionCommand = e.getActionCommand();
			num = Integer.parseInt(actionCommand);
			
			menuBtn[num].setBackground(Clicked);
			Actions.addToDetailPnl("Dessert", num);
		}
	}
	class sellAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			
			String actionCommand = e.getActionCommand();
			int num = Integer.parseInt(actionCommand);
			String kind = data.getCoffeeData(num).getName();
			double price = data.getCoffeeData(num).getPrice();
			
			
			Actions.addtoSellTable("Dessert", num);
					
			
		}
	}
}
