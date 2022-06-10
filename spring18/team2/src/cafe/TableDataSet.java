package cafe;

import java.io.Serializable;

public class TableDataSet implements Serializable
{
	private static final int TableNum = 7;
	private TableData[] table = new TableData[TableNum];
	
	
	public TableDataSet()
	{
	
	}
	public TableData getData(int n)
	{
		return table[n];
	}
	public void reset()
	{
		for(int c1 = 0; c1 < TableNum; c1++)
		{
			table[c1] = new TableData();
		}
	}
}
