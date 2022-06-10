package cafe;

import java.io.Serializable;

public class InvenDataSet implements Serializable 
{
	private InvenData[] inven = new InvenData[45];
	
	
	public InvenDataSet()
	{
	
	}
	public InvenData getData(int n)
	{
		return inven[n];
	}
	public int findData(String n)
	{
		int idx;
		for(idx = 0; idx < 45; idx++)
		{
			if(inven[idx].name.equals(n))
			{
				break;
			}
		}
		return idx;
	}
	public void reset()
	{
		for(int c1 = 0; c1 < 45; c1++)
		{
			inven[c1] = new InvenData();
		}
	}
	
}
