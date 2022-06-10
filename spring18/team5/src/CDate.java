// Fig. 8.7: Date.java 
// Date class declaration.

public class CDate 
{
   private int month; // 1-12
   private int day; // 1-31 based on month
   private int year; // any year

   private final int[] daysPerMonth = // days in each month
      { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   
   // constructor: call checkMonth to confirm proper value for month; 
   // call checkDay to confirm proper value for day
   public CDate( int theYear, int theMonth, int theDay )
   {
      month = checkMonth( theMonth ); // validate month
      year = checkYear(theYear); // could validate year
      day = checkDay( theDay ); // validate day

      System.out.printf( 
         "Date object constructor for date %s\n", this );
   } // end Date constructor

   public CDate(CDate cp) {
	   month = checkMonth(cp.getMonth());
	   day = checkDay(cp.getDay());
	   year = checkYear(cp.getYear());
   }
   public int getMonth() {
	return month;
}

public void setMonth(int month) {
	this.month = month;
}

public int getDay() {
	return day;
}

public void setDay(int day) {
	this.day = day;
}

public int getYear() {
	return year;
}

public void setYear(int year) {
	this.year = year;
}

// utility method to confirm proper month value
   private int checkYear( int testYear )
   {
      if ( testYear > 0 ) // validate month
         return testYear;
      else // month is invalid  
         throw new IllegalArgumentException( "Year must be > 0" );
   } // end method checkMonth
   private int checkMonth( int testMonth )
   {
      if ( testMonth > 0 && testMonth <= 12 ) // validate month
         return testMonth;
      else // month is invalid 
         throw new IllegalArgumentException( "month must be 1-12" );
   } // end method checkMonth

   // utility method to confirm proper day value based on month and year
   private int checkDay( int testDay )
   {
      // check if day in range for month
      if ( testDay > 0 && testDay <= daysPerMonth[ month ] )
         return testDay;
   
      // check for leap year
      if ( month == 2 && testDay == 29 && ( year % 400 == 0 || 
           ( year % 4 == 0 && year % 100 != 0 ) ) )
         return testDay;
   
      throw new IllegalArgumentException( 
         "day out-of-range for the specified month and year" );
   } // end method checkDay
   
   public void nextDay() {
	   if ((day+1)>daysPerMonth[month] && !(month==2 && day==29)) { 
		   day = 1;
		   nextMonth();
	   }
	   else ++day;
   }
   
   public void nextMonth() {
	   if ((month+1) == 13) { 
		   month = 1;
		   ++year;
	   }
	   else ++month;
   }
    
   public String toString()
   { 
      return String.format( "%d/%d/%d", year, month, day ); 
   } // end method toString

	

   
}

