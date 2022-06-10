package cafe;

import java.io.Serializable;

public class TableData implements Serializable
{
	public String contents[][] = new String[45][4];
	public String member[] = new String[5];
	public int rowNum;
	public int totalNum;
	public double totalCost;
	public double discountRate;
	public double finalCost;
	
	public TableData()
	{
		for(int c1 = 0; c1 < 45; c1++)
		{
			for(int c2 = 0; c2 < 4; c2++)
			{
				contents[c1][c2] = "";
			}
		}
		for(int c1 = 0; c1 < 5; c1++)
		{
			member[c1] = "";
		}
		rowNum = 0;
		totalNum = 0;
		totalCost = 0;
		discountRate = 0;
		finalCost = 0;
	}
	
}
