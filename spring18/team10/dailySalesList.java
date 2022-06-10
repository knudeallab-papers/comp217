
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class dailySalesList implements Serializable {
	private ArrayList<order> orderList;
	private Calendar date;
	
	public dailySalesList()
	{
		orderList = new ArrayList<order>();
		date = Calendar.getInstance();
	}
	
	public void addOrderList( order order )
	{
		orderList.add( order );
	}
	
	public void removeOrderList( order order )
	{
		orderList.remove( order );
	}
	public void removeOrderList( int index )
	{
		orderList.remove( index );
	}
	
	public ArrayList<order> getOrderList()
	{
		return orderList;
	}
	
	public Calendar getDate()
	{
		return date;
	}
}
