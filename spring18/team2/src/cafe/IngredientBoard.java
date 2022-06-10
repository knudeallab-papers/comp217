package cafe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import cafe.CoffeeBoard.menuAct;

public class IngredientBoard extends Board
{
	private int num;
	private String kind;
	
	public IngredientBoard(String s)
	{
		super();
		num = 0;
		kind = s;
		getMenuFile();
		setBtnText();
		setBtnActionListener();
	}
	public void getMenuFile() // 바꾸기 
	{
		data2 = new InvenDataSet();
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("InvenData.dat"));
			
			data2 = (InvenDataSet)inputStream.readObject();
			
			
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
	public void setBtnActionListener()
	{
		
		for(int c1 = 0; c1 < BtnNum; c1++)
		{
			menuBtn[c1].addActionListener(new menuAct());
		}
		
	}
	public void setBtnText()
	{
		int sys = 0;
		if(kind.equals("Ingredient"))
		{
			sys = 0;
		}
		else if(kind.equals("Refrigerator"))
		{
			sys = BtnNum;
		}
		else if(kind.equals("Disposable"))
		{
			sys = BtnNum * 2;
		}
		for(int c1 = 0; c1 < BtnNum; c1++)
		{
			String name;
			String price;
			

			if(data2.getData(c1 + sys).exist)
			{
				name = data2.getData(c1 + sys).name;
				price = Integer.toString(data2.getData(c1 + sys).stock);
				if(name.length() < 9)
				{
					menuBtn[c1].setText("<html>" + name + "<br />" + price + "</html>");
				}
				else
				{
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
			Actions.addToDetailPnl(kind, num);
		}
	}
}
