package Team_project;


public class Payment {

	private double insert;
	private double change;
	private int price;
	
	public Payment()
	{
		insert = 0;
		change = 0;
		price = 0;
	}
	
	public Payment(double insert, int price, double change)
	{
		if(insert >= 0)
		{
			this.insert = insert;
			this.price = price;
			this.change = change;
		}
	}
	
	public void set_insert(double insert)
	{
		if(insert >= 0)
		{
			this.insert = insert;
		}
	}
	
	public void set_price(int price)
	{
		if(price >= 0)
		{
			this.price = price;
		}
	}
	
	public double get_insert()
	{
		return insert;
	}
	public int get_price()
	{
		return price;
	}
	public double get_change()
	{
		cal_change();
		return change;
	}
	
	private void cal_change()
	{
	
		if(insert == 0 || price == 0)
		{
			//System.out.println("Wrong values");
		}
		else
		{
			
			change = insert - price;
				
			insert = change;
			
		}
		
	}
}
