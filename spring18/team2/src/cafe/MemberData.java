package cafe;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class MemberData implements Serializable{

	private String element[][] = new String[100][5];
	
	public MemberData() 
	{
		int i, j;
		for(i = 0; i < 100; i++)
		{
			for(j = 0; j < 5; j++)
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
			for(j = 0; j < 5; j++)
			{
				element[i][j] = e[i][j];
			}
		}
	}
	public String[][] getTableElement()
	{
		return element;
	}
	
	public int getMemberIndex(String name, int k)
	{
		//String arr[] = new String[5];
		int c = 0, t = -1;
		
		for(int i = 0; i < element.length; i++)
		{
			if(name.equals(element[i][2])) 
			{
				if(c == k)
				{
					t = i;
					break;
				}
				c++;
			}
		}
		return t;
	}
	
	public int[] getMemberIndexArr(String name)
	{
		int i, c = 0;
		int indexArr[] = new int[100];
		int temp;
		for(i = 0; i < 100; i++)
		{
			indexArr[i] = -1;
		}
		
		for(i = 0; i < element.length; i++)
		{

			temp = getMemberIndex(name ,c);
			
			if(temp != -1)
			{
				indexArr[c++] = temp;
			}
		}
		
		return indexArr;
	}
	
	public String[] getMember(int index)
	{
		return element[index];
	}
	public int getMemberIndex(String name)
	{
		int temp = -1;
		
		for(int i = 0; i < 100; i++)
		{
			if(name.equals(element[i][2]))
			{
				temp = i;
			}
		}
		return temp;
	}
}
