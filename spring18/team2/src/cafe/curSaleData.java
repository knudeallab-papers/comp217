package cafe;

import java.io.Serializable;

public class curSaleData implements Serializable
{
	public int row;
	public int count;
	public double cost;
	public String Contents[][] = new String[45][4];
	
	public void addToContents(String name, double price, int num, double t_price)
	{
		int sys = 0;
		count += num;
		cost += t_price;
		for(int c1 = 0; c1 < row; c1++)
		{
			if(Contents[c1][0].equals(name))
			{
				Contents[c1][2] = Integer.toString(num + Integer.parseInt(Contents[c1][2]));
				Contents[c1][3] = Double.toString(t_price + Double.parseDouble(Contents[c1][3]));
				sys = 1;
				break;
			}
		}
		if(sys == 0)
		{
			Contents[row][0] = name;
			Contents[row][1] = Double.toString(price);
			Contents[row][2] = Integer.toString(num);
			Contents[row][3] = Double.toString(t_price);
			row += 1;
		}
	}
	public void reset()
	{
		row = 0;
		count = 0;
		cost = 0.0;
		for(int c1 = 0; c1 < 45; c1++) 
		{
			for(int c2 = 0; c2 < 4; c2++)
			{
				Contents[c1][c2] = "";
			}
		}
	}
}
