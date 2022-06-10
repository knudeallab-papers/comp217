package cafe;

import java.util.Calendar;
import java.io.Serializable;

public class DateData implements Serializable
{
	private Calendar cal = Calendar.getInstance();
	
	public DateData() 
	{

		
	}
	public void setNextDate() //하루 더하기 
	{
		this.cal.add(Calendar.DATE, 1);
	}
	public void setDate(Calendar c)
	{
		cal = c;
	}
	public String getDate()
	{
		return (cal.get(Calendar.YEAR) + "년 " + (cal.get(Calendar.MONTH) + 1) + "월 " + cal.get(Calendar.DAY_OF_MONTH) + "일");
	}
	public int getDay()
	{
		return cal.get(Calendar.DAY_OF_MONTH);
	}
}

