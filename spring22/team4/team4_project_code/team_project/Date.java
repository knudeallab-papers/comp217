package team_project;

import java.io.Serializable;
import java.util.Calendar;

public class Date implements Serializable{
	
	private int year;
	private int month;
	private int day;

	public static int[] lastDate = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public Date() {
		Calendar cal = Calendar.getInstance();

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DATE);
	}
	
	public Date(Date d) {
		day = d.getDay();
		year = d.getYear();
		month = d.getMonth();	
	}
	
	public Date(int y, int m, int d)
	{
		year = y;
		month = m;
		day = d;
	}
	
	public void setYear(int y) { year = y; }
	public void setMonth(int m) { month = m; }
	public void setDay(int d) { day = d; }
	public int getYear() {  return year;}
	public int getMonth() {  return month;}
	public int getDay() {  return day; }
	public String toString() { return month + "월 " + day + "일"; }

	public static int convertDate(Date d) { // 년(year-2000) 월 일을 모두 일로 변환
		int convertMonth = 0;
		for (int i = 0; i < d.getMonth() - 1; i++)
			convertMonth += lastDate[i];
		return (d.getYear() - 2000) * 365 + convertMonth + d.getDay();
	}
	
	public static int getTodayConvertDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		
		return convertDate(new Date(year, month ,day));
	}
	
	public static Date getTodayDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		
		return new Date(year, month, day);
	}

}
