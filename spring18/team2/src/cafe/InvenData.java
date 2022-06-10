package cafe;

import java.io.Serializable;

public class InvenData implements Serializable 
{
	public String name;
	public double price;
	public int stock;
	public int order;
	public String loc;
	public String contact;
	boolean exist;
	
	public InvenData()
	{
		name = "";
		price = 0.0;
		stock = 0;
		order = 0;
		loc = "";
		contact = "";
		exist = false;
	}
}
