
import java.io.Serializable;

public class item implements Serializable {
	private String itemName;
	private int price, cost;
	private boolean soldOut;
	
	public item(String iName, int p, int c, boolean sO)
	{
		setItemName(iName);
		setPrice(p);
		setCost(c);
		setSoldOut(sO);
	}
	
	public void setItemName( String iName )
	{
		itemName = iName;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public void setPrice( int p )
	{
		price = p;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public void setCost( int c )
	{
		cost = c;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public void setSoldOut( boolean sO )
	{
		soldOut = sO;
	}
	
	public boolean getSoldOut()
	{
		return soldOut;
	}
}
