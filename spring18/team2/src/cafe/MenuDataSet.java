package cafe;

import java.io.Serializable;

public class MenuDataSet implements Serializable
{
//	private boolean exist[] = new boolean[15];
	private MenuData CoffeeData[] = new MenuData[15];
	private MenuData NonCoffeeData[] = new MenuData[15];
	private MenuData DessertData[] = new MenuData[15];
	
	public MenuDataSet()
	{
//		CoffeeData[0] = new MenuData();
//		CoffeeData[0].setName("아이스아메리카노");
//		CoffeeData[0].setPrice(3500);
		
	}
	public MenuData getCoffeeData(int n)
	{
		return CoffeeData[n];
	}
	public MenuData getNonCoffeeData(int n)
	{
		return NonCoffeeData[n];
	}
	public MenuData getDessertData(int n)
	{
		return DessertData[n];
	}
	public MenuData findGetData(String n)
	{
		for(int c1 = 0; c1 < 15; c1++)
		{
			if(CoffeeData[c1].getName().equals(n))
			{
				return CoffeeData[c1];
			}
			else if(NonCoffeeData[c1].getName().equals(n))
			{
				return NonCoffeeData[c1];
			}
			else if(DessertData[c1].getName().equals(n))
			{
				return DessertData[c1];
			}
		}
		
		return new MenuData();
	}
	public void reset()
	{
		for(int c1 = 0; c1 < 15; c1++)
		{
			CoffeeData[c1] = new MenuData();
			NonCoffeeData[c1] = new MenuData();
			DessertData[c1] = new MenuData();
		}
	}
}
