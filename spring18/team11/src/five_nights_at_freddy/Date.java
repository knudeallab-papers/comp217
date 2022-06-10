/***
 * Lab #4-2
 * Date: 04/04/2018
 * @author knuprime008
 */
package five_nights_at_freddy;
import java.io.Serializable;
import java.util.Scanner;

public class Date implements Serializable{
	private String month;
	private int day;
	private int year;
	
	private final String ERR_MSG = "Fatal Error.";
	
	public Date() {
		month = "January";
		day = 1;
		year = 1000;
	}
	
	public Date(int monthInt, int day, int year) {
		setDate(monthInt,day,year);
	}
	
	public Date(String monthString, int day, int year) {
		setDate(monthString, day, year);
	}
	
	public Date(int year) {
		setDate(1,1,year);
	}
	
	public Date(Date aDate) {
		if(aDate == null) {
			errorHandler(ERR_MSG);
		}
		month = aDate.month;
		day = aDate.day;
		year = aDate.year;
	}
	
	public void setDate(int monthInt, int day, int year) {
		if (dateOK(monthInt,day,year)) {
			this.month = monthString(monthInt);
			this.day = day;
			this.year = year;
		}else
			errorHandler(ERR_MSG);
	}
	
	public void setDate(String monthString,int day, int year) {
		if(dateOK(monthString,day,year)) {
			this.month = monthString;
			this.day = day;
			this.year = year;
		}else
			errorHandler(ERR_MSG);
	}
	
	public void setDate(int year) {
		setDate(1,1,year);
	}
	
	public void nextDay() {
		if(month.equals("Feburary") && day == 28) {
			month = "March";
			day = 1;
		}
		else if((month.equals("January") || month.equals("March")  || month.equals("May") 
				|| month.equals("July") || month.equals("August") || month.equals("October") ) && day == 31) {
			this.setDate(getMonth() + 1, 1, getYear());
		}
		else if(month.equals("December") && day == 31) {
			this.setDate(1, 1, getYear() + 1);
		}
		else if((month.equals("April") || month.equals("June")  || month.equals("September") 
				|| month.equals("November")) && day == 30){
			this.setDate(getMonth() + 1, 1, getYear());
		}
		else {
			day += 1;
		}
	}
	
	public void setYear(int year) {
		if ((year < 1000) || (year > 9999)) {
			errorHandler(ERR_MSG);
		}
		else 
			this.year = year;
	}
	
	public void setMonth(int monthInt) {
		if ((monthInt <= 0) || (monthInt > 12)) {
			errorHandler(ERR_MSG);
		}
		else 
			this.month = monthString(monthInt);
	}
	
	public void setDay(int day) {
		if ((day <= 0) || (day > 31)) {
			errorHandler(ERR_MSG);
		}
		else 
			this.day = day;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		int res = 0;
		switch(this.month) {
			case "Januray":
				res = 1; break;
			case "February":
				res = 2; break;
			case "March":
				res = 3; break;
			case "April":
				res = 4; break;
			case "May":
				res = 5; break;
			case "June":
				res = 6; break;
			case "July":
				res = 7; break;
			case "August":
				res = 8; break;
			case "September":
				res = 9; break;
			case "October":
				res = 10; break;
			case "November":
				res = 11; break;
			case "December":
				res = 12; break;
			default:
				errorHandler(ERR_MSG);
		}
		
		return res;
	}
	
	public String monthString(int monthNumber) {
		String res = "";
		switch(monthNumber) {
			case 1:
				res = "Januray"; break;
			case 2:
				res = "February"; break;
			case 3:
				res = "March"; break;
			case 4:
				res = "April"; break;
			case 5:
				res = "May"; break;
			case 6:
				res = "June"; break;
			case 7:
				res = "July"; break;
			case 8:
				res = "August"; break;
			case 9:
				res = "September"; break;
			case 10:
				res = "October"; break;
			case 11:
				res = "November"; break;
			case 12:
				res = "December"; break;
			default:
				errorHandler(ERR_MSG);
		}
		return res;
	}
	public void readInput() {
		boolean tryAgain = true;
		Scanner keyboard = new Scanner(System.in);
		
		while(tryAgain) {
			System.out.println("Enter month, day, and year.");
			System.out.println("Do not use a comma.");
			String monthInput = keyboard.next();
			int dayInput = keyboard.nextInt();
			int yearInput = keyboard.nextInt();
			
			if(dateOK(monthInput, dayInput,yearInput)) {
				setDate(monthInput, dayInput, yearInput);
				tryAgain = false;
			}
			else
				System.err.println("Illegal data. Reenter input.");
		}
		keyboard.close();
	}
	private boolean monthOK(String strMonth) {
		return (strMonth.equals("January") || strMonth.equals("February") || 
				strMonth.equals("March") || strMonth.equals("April") || strMonth.equals("May") ||
				strMonth.equals("June") || strMonth.equals("July") || strMonth.equals("August") ||
				strMonth.equals("September") || strMonth.equals("October") || 
				strMonth.equals("November") || strMonth.equals("December"));
	}
	
	private boolean dateOK(int monthInt, int day, int year) {
		if (((year < 1000) || (year > 9999)) && ((day <= 0) || 
				(day > 31)) && ((monthInt < 1) || (monthInt > 12))) {
			errorHandler(ERR_MSG);
		}
		else 
			return true;	
		return false;
	}
	
	private boolean dateOK(String monthString, int day, int year) {
		if ((year < 1000 || year > 9999) && (day <= 0 || day > 31) && monthOK(monthString)) {
			errorHandler(ERR_MSG);
		}
		else 
			return true;
		return false;	
	}
	
	public String toString() {
		return (year + " 년 " + getMonth() + " 월 " + day + " 일 ");
	}
	
	public boolean equals(Date otherDate) {
		return (month.equals(otherDate.month) && day == otherDate.day && year == otherDate.year);
	}
	
	public boolean precedes(Date otherDate) {
		return (year < otherDate.year || 
				(year == otherDate.year && (getMonth() < otherDate.getMonth())) ||
				(year == otherDate.year && (getMonth() == otherDate.getMonth()) && day < otherDate.day));
	}
	
	public void errorHandler(String msg) {
		if(msg != null && !msg.equalsIgnoreCase("")) {
			System.err.println(msg);
			System.exit(-1);
		}
	}
	
}
