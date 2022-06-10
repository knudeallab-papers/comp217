package five_nights_at_freddy;

import java.io.Serializable;

public class Employee implements Serializable {
	public int id;
	public String name;
	public int salary;
	public String position;
	public Date hired;
	public String phoneNumber;
	
	public Employee() {
		id = 0;
		name = "";
		salary = 0;
		position = "";
		hired = new Date();
		phoneNumber = "";
	}
	
	public Employee(int newId, String newName, int newSalary, String newPosition, 
			Date newDate, String newPhoneNumber) {
		super();
		id = newId;
		name = newName;
		salary = newSalary;
		position = newPosition;
		hired = new Date(newDate);
		phoneNumber = newPhoneNumber;
	}
	
	public void setEmployee(String newName, int newSalary, String newPosition, String newPhoneNumber) {
		name = newName;
		salary = newSalary;
		position = newPosition;
		phoneNumber = newPhoneNumber;
	}
}
