import java.io.Serializable;
import java.util.Calendar;

public class MyCalendar implements Serializable{
	public int YEAR;
	public int MONTH;
	public int DATE;
	
	public MyCalendar(int year, int month, int day)
	{
		this.YEAR = year;
		this.MONTH = month;
		this.DATE = day;
	}
	
	public void setYear(int input)
	{
		this.YEAR = input;
	}
	
	public int getYear()
	{
		return this.YEAR;
	}
	
	public void setMonth(int input)
	{
		this.MONTH = input;
	}
	
	public int getMonth()
	{
		return this.MONTH;
	}
	
	public void setDay(int input)
	{
		this.DATE = input;
	}
	
	public int getDay()
	{
		return this.DATE;
	}
	
	public int toInt()
	{
		return(getYear()*1000+getMonth()*100+getDay());
	}
}
