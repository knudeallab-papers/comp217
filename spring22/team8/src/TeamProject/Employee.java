package TeamProject;

public abstract class Employee {
	protected String id;
	protected String name;
	protected double hourlyWage;
	protected int dayHours;
	protected int nightHours;
	//constructor
	public Employee() {
		id = null;
		name = null;
		//default value
		hourlyWage = 9160;
		dayHours = 0;
		nightHours = 0;
	}
	public Employee(String theId, String theName) {//id and name
		id = theId;
		name = theName;
		//default value
		hourlyWage = 9160;
		dayHours = 0;
		nightHours = 0;
	}
	public Employee(String theId, String theName, double theHourlyWage) {//id and name
		id = theId;
		name = theName;
		hourlyWage = theHourlyWage;
		//default value
		dayHours = 0;
		nightHours = 0;
	}
	public Employee(String theId, String theName, double theHourlyWage, int theDayHours, int theNightHours) {//id and name
		id = theId;
		name = theName;
		hourlyWage = theHourlyWage;
		dayHours = theDayHours;
		nightHours = theNightHours;
	}
	public Employee(Employee someone) {
		this.id = someone.id;
		this.name = someone.name;
		this.hourlyWage = someone.hourlyWage;
		this.dayHours = someone.dayHours;
		this.nightHours = someone.nightHours;
	}
	//setter
	public void setName(String theName) {
		name = theName;
	}
	public void setId(String theId) {
		id = theId;
	}
	public void setWage(double theHourlyWage) {
		hourlyWage = theHourlyWage;
	}
	public void setDayHours(int theDayHours) {
		dayHours = theDayHours;
	}
	public void setNightHours(int theNightHours) {
		nightHours = theNightHours;
	}
	public void resetHours() {
		setDayHours(0);
		setNightHours(0);
	}
	//getter
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public double getWage() {
		return hourlyWage;
	}
	public int getDayHours() {
		return dayHours;
	}
	public int getNightHours() {
		return nightHours;
	}
	public int getTotalHours() {
		return (dayHours + nightHours);
	}
	public abstract double totalFare();//return totalWage
}
