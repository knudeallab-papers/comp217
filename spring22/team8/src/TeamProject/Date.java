package TeamProject;

import java.util.StringTokenizer;
import java.time.LocalDate;

public class Date {
	private int month;
	private int day;
	private int year;
	
	public Date() {
		this.month = 1;
		this.day = 1;
		this.year = 1900;
	}
	
	public Date(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	public Date(Date orgDate) {
		if(orgDate == null) {
			System.exit(0);
		}
		this.month = orgDate.month;
		this.day = orgDate.day;
		this.year = orgDate.year;
	}
	
	public String toString(){
		return (this.year + "-" + month + "-" + day);
	}
	
	public boolean equals(Date otherDate){
		return (
			(month == otherDate.month)
				&& (day == otherDate.day) 
				&& (year == otherDate.year) 
		);
	}
	
	public int DateCompare(Date D) {  // 자신과 D를 비교한다. 자신이 최근이면 1, 같은 날짜면 0, 더 늦으면 -1
	if(this.getYear() > D.getYear()) {
		return -1;
	}else if(this.getYear() == D.getYear()){
		if(this.getMonth() > D.getMonth()) {
			return -1;
			}else if(this.getMonth() == D.getMonth()) {
				if(this.getDay() > D.getDay()) {
				return -1;
				}else if(this.getDay() == D.getDay()){
					return 0;
				}else {
					return 1;
				}
			}else {
				return 1;
			}
	}else {
		return 1;
	}
	}
	
	public boolean isEqual(Date D) {
		if(DateCompare(D) == 0) return true;
		else return false;
	}
	
	public void setDate(String theDate) {
		StringTokenizer stk = new StringTokenizer(theDate,"-");
		this.year = stringToInt(stk.nextToken());
		this.month = stringToInt(stk.nextToken());
		this.day = stringToInt(stk.nextToken());
		
	}
	
	private static int stringToInt(String str) {
		int res = 0;
		res = Integer.parseInt(str.trim());
		return res;
	}
	
	public int getMonth(){
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setMonth(int theMonth) {
		month = theMonth;
	}
	
	public void setYear(int theYear) {
		year = theYear;
	}
	
	public void setDay(int theDay) {
		day = theDay;
	}
	
	public void setToday() {
		LocalDate now = LocalDate.now();
		StringTokenizer stk = new StringTokenizer(now.toString(),"-");
		this.year = stringToInt(stk.nextToken());
		this.month = stringToInt(stk.nextToken());
		this.day = stringToInt(stk.nextToken());
	}
	
	public void addDate(int theDay) {
		this.day += theDay;
		int Md = Monthdays(this.year,this.month);
		while(this.day > Md || this.month > 12) {
		if(this.day > Md) {
			this.month++;
			this.day -= Md;
		}
		if(this.month > 12) {
			this.year++;
			this.month -= 12;
		}
		}
	}
	
	private int Monthdays(int theYear, int theMonth) {
		if(theMonth == 1 || theMonth == 3 || theMonth == 5 || theMonth == 7
				|| theMonth == 8 || theMonth == 10 || theMonth == 12) {
			return 31;
		}else if(theMonth == 4 || theMonth == 6 || theMonth == 9 || theMonth == 11) {
			return 30;
		}else {
			if(theYear % 4 == 0) {
				if(theYear % 100 == 0) {
					if(theYear % 400 == 0) {
						return 29;
					}else {
						return 28;
					}
				}else {
					return 28;
				}
			}else {
				return 29;
			}
		}
	}
	
	
}
