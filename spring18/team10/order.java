

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class order implements Serializable {
	private ArrayList<orderItems> orderItemList;
	private boolean cash, back;
	private Calendar date;
	
	public order()
	{
		orderItemList = new ArrayList<orderItems>();
		setBack( false );
	}
	
	public void setOrderItem( item item )
	{
		orderItemList.add( new orderItems(item) );
	}
	
	public void setOrderItemNum( item item, int num )
	{
		int indexNum = getOrderItemIndex( item );
		if( indexNum != -1 )
			setOrderItemNum( indexNum, num );
	}
	public void setOrderItemNum( int indexNum, int num )
	{
		orderItemList.get( indexNum ).setNum( num );
	}
	
	public ArrayList<orderItems> getOrderItemList()
	{
		return orderItemList;
	}
	
	public int getOrderItemIndex( item item )
	{
		for( int i = 0; i < orderItemList.size(); i++ )
		{
			if( orderItemList.get(i).getOrderItem() == item )
				return i;
		}
		return -1;
	}
	
	public int getOrderItemNum( item item )
	{
		for( int i = 0; i < orderItemList.size(); i++ )
		{
			if( orderItemList.get(i).getOrderItem() == item )
				return orderItemList.get(i).getNum();
		}
		return -1;
	}
	public int getOrderItemNum( int indexNum )
	{
		return orderItemList.get(indexNum).getNum();
	}
	
	public void delOrderItem( item item )
	{
		int indexNum = getOrderItemNum(item);
		if( indexNum >= 0 )
			orderItemList.remove(indexNum);
	}
	public void delOrderItem( int indexNum )
	{
		if( indexNum >= 0 )
			orderItemList.remove(indexNum);
	}
	
	public void setCash( boolean c )
	{
		cash = c;
	}
	
	public boolean getCash()
	{
		return cash;
	}
	
	public void setBack( boolean b )
	{
		back = b;
	}
	
	public boolean getBack()
	{
		return back;
	}
	
	public void setDate()
	{
		date = Calendar.getInstance();
	}
	
	public Calendar getDate()
	{
		return date;
	}
}
