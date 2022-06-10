
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class totalSalesList implements Serializable {
	private ArrayList<dailySalesList> totalSalesList;
	
	public totalSalesList()
	{
		totalSalesList = new ArrayList<dailySalesList>();
	}
	
	public boolean confirmDate( Calendar date1, Calendar date2 )
	{
		if( date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) && date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) && date1.get(Calendar.DATE) == date2.get(Calendar.DATE) )
			return true;
		
		return false;
	}
	public boolean confirmToday( Calendar date )
	{
		return confirmDate(date, Calendar.getInstance());
	}
	
	public void addSalesListOrder( order order )
	{
		dailySalesList todaySalesList = getSalesList( Calendar.getInstance() );
		
		if( todaySalesList == null )
		{
			addSalesList( new dailySalesList() );
			getSalesList().addOrderList(order);
		}
		else
			todaySalesList.addOrderList(order);
	}
	public void addSalesList( dailySalesList dSL )
	{
		totalSalesList.add(dSL);
	}
	
	public dailySalesList getSalesList( Calendar date )
	{
		for(dailySalesList tempDailySalesList : totalSalesList)
		{
			if( confirmDate(tempDailySalesList.getDate(), date) )
				return tempDailySalesList;
		}
		
		return null;
	}
	public dailySalesList getSalesList()
	{
		return getSalesList( Calendar.getInstance() );
	}
	
	public ArrayList<dailySalesList> getTotalSalesList()
	{
		return totalSalesList;
	}
}
