package cafe;

import javax.swing.JPanel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;


public class test1 
{

	public static void main(String[] args)
	{
		MainWindow mainWindow = new MainWindow();
		SideBar side = new SideBar();
		TopBar top = new TopBar();
		MainPanel pnl = new MainPanel();
		Data data = new Data();
		MenuDataSet menuData = new MenuDataSet();
		InvenDataSet invenData = new InvenDataSet();
		TableDataSet tableData = new TableDataSet();
		MoneyData money = new MoneyData();
		curSaleData sale = new curSaleData();
		Actions act = new Actions();
		
		act.setMainWindow(mainWindow);
		act.setSideBar(side);
		act.setTopBar(top);
		act.setMainPanel(pnl);
		
		mainWindow.setSideBar(side);
		mainWindow.setTopBar(top);
		mainWindow.setMainPanel(pnl);
		
		side.addPasswordBar();
	
		
//		menuData.reset();
//		try
//		{
//			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MenuData.dat"));
//			outputStream.writeObject(menuData);
//			outputStream.close();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//		
//		invenData.reset();
//		try
//		{
//			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("InvenData.dat"));
//			outputStream.writeObject(invenData);
//			outputStream.close();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
		
//		tableData.reset();
//		try
//		{
//			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("TableData.dat"));
//			outputStream.writeObject(tableData);
//			outputStream.close();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
		
		
//		money.initMoney();
//		money.initdaySale();
//		try
//		{
//			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MoneyData.dat"));
//			outputStream.writeObject(money);
//			outputStream.close();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
		
//		sale.reset();
//		try
//		{
//			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("CurSaleData.dat"));
//			outputStream.writeObject(sale);
//			outputStream.close();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
		
		
		
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("MoneyData.dat"));
			
			money = (MoneyData)inputStream.readObject();
			
			
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
		
		
		act.setMoney(money); ///!!!!!!!
		act.setMoneyDataOnTop();
		
		try
		{
			ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("CurSaleData.dat"));
			
			sale = (curSaleData)inputStream.readObject();
			
			
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
		
		act.setCurSale(sale);
	}

}
