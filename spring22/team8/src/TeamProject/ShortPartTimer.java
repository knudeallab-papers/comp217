package TeamProject;

public class ShortPartTimer extends Employee{
	//id-name-hourlywage-dayhour-nighthour
	//constructor
	public ShortPartTimer() {
		super();
	}
	public ShortPartTimer(String theId, String theName) {
		super(theId, theName);
	}
	public ShortPartTimer(String theId, String theName, double theHourlyWage) {
		super(theId, theName, theHourlyWage);
	}
	public ShortPartTimer(String theId, String theName, double theHourlyWage, int theDayHours, int theNightHours) {
		super(theId, theName, theHourlyWage, theDayHours, theNightHours);
	}
	public ShortPartTimer(ShortPartTimer x) {
		super(x);
	}
	@Override
	public double totalFare() {
		return getTotalHours() * getWage();
	}
	@Override
	public String toString() {
		return "";
	}
}
