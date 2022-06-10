package cafe;

import java.io.Serializable;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class EmployeeData implements Serializable{
	
	private String element[][] = new String[100][6];
	//private List<String> element = new ArrayList<String>();
	
	public EmployeeData() 
	{
		int i, j;
		for(i = 0; i < 100; i++)
		{
			for(j = 0; j < 6; j++)
			{
				element[i][j] = null;
			}
		}
	}
	
	public void setTableElement(String[][] e)
	{
		int i, j;
		for(i = 0; i < e.length; i++)
		{
			for(j = 0; j < 6; j++)
			{
				element[i][j] = e[i][j];
			}
		}
	}
	public String[][] getTableElement()
	{
		return element;
	}
}
