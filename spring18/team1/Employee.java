import java.io.Serializable;

public class Employee  implements Serializable{
	private int id;
	private String name;
	private int salary;
	private String grade;
	private String day;
	private String call;
	
	public Employee() {
		id = 0;
		name = null;
		salary = 0;
		grade = null;
		day = null;
		call = null;
	}
	
	public Employee(Employee other) {
		setId(other.id);
		setName(other.name);
		setSalary(other.salary);
		setGrade(other.grade);
		setDay(other.day);
		setCall(other.call);
	}
	
	
	public void setId(int i) {
		id = i;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String n) {
		name = new String(n);
	}
	
	public String getName() {
		return new String(name);
	}
	
	public void setSalary(int s) {
		salary = s;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setGrade(String g) {
		grade = new String(g);
	}
	
	public String getGrade() {
		return new String(grade);
	}
	
	public void setDay(int y, int m, int d) {
		day = new String(y + "년 " + m + "월 " + d + "일");
	}
	
	public void setDay(String d) {
		day = new String(d);
	}
	
	public String getDay() {
		return new String(day);
	}
	
	public void setCall(String c) {
		call = new String(c);
	}
	
	public String getCall() {
		return new String(call);
	}
}
