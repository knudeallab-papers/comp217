
import java.io.Serializable;

public class orderItems implements Serializable {
	private item orderItem;
	private int num;
	
	public orderItems( item oI )
	{
		setOrderItem( oI );
		setNum(1);
	}
	
	public void setOrderItem( item oI )
	{
		orderItem = oI;
	}
	
	public item getOrderItem()
	{
		return orderItem;
	}
	
	public void setNum( int n )
	{
		num = n;
	}
	
	public int getNum()
	{
		return num;
	}
}
