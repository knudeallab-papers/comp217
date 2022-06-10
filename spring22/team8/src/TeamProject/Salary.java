package TeamProject;
public class Salary extends LongPartTimer{
	//id-name-hourlyWage-dayHour-nightHour-holidayPay-lastWeekNum-weeklyHour-severence
	private double severencePay;
	//constructor
	public Salary() {
		super();
		severencePay = 0;
	}
	public Salary(String theId, String theName) {
		super(theId, theName);
		severencePay = 0;
	}
	public Salary(String theId, String theName, double theHourlyWage) {
		super(theId, theName, theHourlyWage);
		severencePay = 0;
	}
	public Salary(String theId, String theName, double theHourlyWage, int theDayHours, int theNightHours,
			double theHolidayPay, int theLastWeekNum, int isYear, int theWeeklyDay, int theWeeklyNight, double[] theWageList) {
		super(theId, theName, theHourlyWage, theDayHours, theNightHours, theHolidayPay, theLastWeekNum,
				isYear, theWeeklyDay, theWeeklyNight, theWageList);
		severencePay = 0;
	}
	public Salary(String theId, String theName, double theHourlyWage, int theDayHours, int theNightHours,
			double theHolidayPay, int theLastWeekNum, int isYear, int theWeeklyDay, int theWeeklyNight,
			double[] theWageList, double theSeverencePay) {
		super(theId, theName, theHourlyWage, theDayHours, theNightHours, theHolidayPay, theLastWeekNum,
				isYear, theWeeklyDay, theWeeklyNight, theWageList);
		this.severencePay = theSeverencePay;
	}
	public Salary(LongPartTimer x) {
		super(x);
		severencePay = calSeverencePay();
	}
	//setter
	public void setSeverencePay(double theSeverencePay) {
		severencePay = theSeverencePay;
	}
	//getter
	public double getSeverencePay() {
		return severencePay;
	}
	
	@Override
	public double totalFare() {
		return getTotalHours() * getWage();
	}
	public double calSeverencePay() {
		return calAverageWagePerDay() * 30 * (getIsYear() * 7 / 365);
	}
	private double calAverageWagePerDay() {
		double[] wageList = getWageList();
		double sumWage = 0;
		for(int i=0; i<LIST_NUM; i++)
			sumWage += wageList[i];
		return sumWage/91;
	}
}
