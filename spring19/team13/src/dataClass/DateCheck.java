package dataClass;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

 

public class DateCheck
{
  public static long diffOfDate(String begin, String end) throws Exception
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

 

    Date beginDate = formatter.parse(begin);
    Date endDate = formatter.parse(end);

 

    long diff = endDate.getTime() - beginDate.getTime();
    long diffDays = diff / (24 * 60 * 60 * 1000);

 

    return diffDays;
  }
  public static String doDateAdd(String begin)throws Exception{
	    Calendar cal = new GregorianCalendar();
	    SimpleDateFormat fm = new SimpleDateFormat(
	            "yyyyMMdd");
	    Date beginDate = fm.parse(begin);
	    cal.setTime(beginDate);
	    cal.add(Calendar.DAY_OF_YEAR, 7); // 하루를 더한다.


	    String strDate = fm.format(cal.getTime());
	    return strDate;
	}
  public static String getCurDate()
  {
	  SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
	  		
	  Date time = new Date();
	  		
	  String timeStr = format1.format(time);
	  return timeStr;
  }

}
