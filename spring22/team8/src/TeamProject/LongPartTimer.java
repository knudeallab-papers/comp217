package TeamProject;


public class LongPartTimer extends Employee{
	//id-name-hourlyWage-dayHour-nightHour-holidayPay-lastWeekNum-isYear-weeklyDay-weeklyNight-1~13
	private double holidayPay;
	private int lastWeekNum;
	private int isYear;
	private int weeklyDay;
	private int weeklyNight;
	private double[] wageList;
	public static int LIST_NUM = 13;
	//constructor
	public LongPartTimer() {
		super();
		holidayPay = 0;
		lastWeekNum = 0;
		isYear = 0;
		weeklyDay = 0;
		weeklyNight = 0;
		wageList = new double[LIST_NUM];
		setList();
	}
	public LongPartTimer(String theId, String theName) {
		super(theId, theName);
		holidayPay = 0;
		lastWeekNum = 0;
		isYear = 0;
		weeklyDay = 0;
		weeklyNight = 0;
		wageList = new double[LIST_NUM];
		setList();
	}
	public LongPartTimer(String theId, String theName, double theHourlyWage) {
		super(theId, theName, theHourlyWage);
		holidayPay = 0;
		lastWeekNum = 0;
		isYear = 0;
		weeklyDay = 0;
		weeklyNight = 0;
		wageList = new double[LIST_NUM];
		setList();
	}
	public LongPartTimer(String theId, String theName, double theHourlyWage, int theDayHours, int theNightHours,
			double theHolidayPay, int theLastWeekNum, int isYear, int theWeeklyDay, int theWeeklyNight, double[] theWageList) {
		super(theId, theName, theHourlyWage, theDayHours, theNightHours);
		this.holidayPay = theHolidayPay;
		this.lastWeekNum = theLastWeekNum;
		this.isYear = isYear;
		weeklyDay = theWeeklyDay;
		weeklyNight = theWeeklyNight;
		wageList = new double[LIST_NUM];
		setList(theWageList);
	}
	public LongPartTimer(LongPartTimer x) {
		super(x);
		this.holidayPay = x.holidayPay;
		this.lastWeekNum = x.lastWeekNum;
		this.isYear = x.isYear;
		this.weeklyDay = x.weeklyDay;
		this.weeklyNight = x.weeklyNight;
		this.wageList = x.wageList;
		wageList = new double[LIST_NUM];
		setList(x.wageList);
	}
	//getter
	public double getHolidayPay() {
		return holidayPay;
	}
	public int getLastWeekNum() {
		return lastWeekNum;
	}
	public int getIsYear() {
		return isYear;
	}
	public int getWeeklyDayHour() {
		return weeklyDay;
	}
	public int getWeeklyNightHour() {
		return weeklyNight;
	}
	public double[] getWageList() {
		double[] temp = new double[LIST_NUM];
		for(int i=0; i<LIST_NUM; i++)
			temp[i] = wageList[i];
		return temp;
	}
	//setter
	public void setHolidayPay(double theHolidayPay) {
		holidayPay = theHolidayPay;
	}
	public void setLastWeekNum(int weekNum) {
		lastWeekNum = weekNum;
	}
	public void setIsYear(int isYear) {
		this.isYear = isYear;
	}
	public void setWeeklyDayHour(int weeklyDayHour) {
		weeklyDay = weeklyDayHour;
	}
	public void setWeeklyNightHour(int weeklyNightHour) {
		weeklyNight = weeklyNightHour;
	}
	private void setList() {
		for(int i=0; i<LIST_NUM; i++)
			wageList[i] = 0;
	}
	private void setList(double[] list) {
		for(int i=0; i<LIST_NUM; i++)
			wageList[i] = list[i];
	}
	//adder
	public void addWeeklyHour(int dayHour, int nightHour) {
		weeklyDay += dayHour;
		weeklyNight += nightHour;
	}
	public boolean isOverYear() {
		return isYear >= 52;
	}
	public void readyToSeverencePayList() {
		wageList[lastWeekNum % LIST_NUM] = calWeeklyPay();
	}
	@Override
	public double totalFare() {
		return getHolidayPay() + getWage() * getDayHours() + getWage() * getNightHours() * 1.5;
	}
	public void calHolidayPay() {
		holidayPay += getWeeklyHolidayPay();
	}
	public double getWeeklyHolidayPay() {
		int weeklyHour = weeklyDay + weeklyNight;
		if(weeklyHour >= 15)
			return getWage() * 8 * weeklyHour /40;
		return 0;
	}
	private double calWeeklyPay() {
		return  getWeeklyHolidayPay() + getWage() * weeklyDay + getWage() * weeklyNight * 1.5;
	}
}

