import java.io.Serializable;

public class Time implements Serializable{
	private int Hour;
	private int Min;
	private int totalMin;
	
	//private final String ERR_MSG = "Input Time Error.";
	
	public Time() {
		Hour = 0;
		Min = 0;
		totalMin = 0;
	}
	
	public Time(int h, int m) { //if input Hour and Min
		if(checkTime(h,m)) {
			Hour = h;
			Min = m;		
			totalMin = h * 60 + m;
		}
		//else
			//System.out.println(ERR_MSG);
	}
	
	public Time(int t) { //if input Only Time
		if(checkTime(t)) {
			totalMin = t;
			Hour = t / 60;
			Min = t & 60;
		}
		//else
			//System.out.println(ERR_MSG);
	}
	
	private boolean checkTime(int h, int m) {
		if((h>=9)&&(h<=22)
				&& (m>=0)&&(m<=59))
			return true;
		else
			return false;
	}
	
	private boolean checkTime(int t) {
		if(t>=0)
			return true;
		else
			return false;
	}
	
	public int getHour() { return Hour; }
	public int getMin()  { return Min;  } 
	public int getTotalMin() { return totalMin; }
}