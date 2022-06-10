package Team_project;

import java.io.Serializable;

public class Order implements Serializable{
	public String name;
	public int num=0;
	
	public Order()
	{
		this.name="none";
		this.num=0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getNum()
	{
		return this.num;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void setNum(int num)
	{
		this.num=num;
	}
}
