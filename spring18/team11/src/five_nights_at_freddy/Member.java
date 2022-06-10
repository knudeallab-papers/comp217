package five_nights_at_freddy;

import java.io.Serializable;

public class Member implements Serializable{
	public String name;
	public int id_number;
	public String phoneNumber;
	public String grade;
	public double mileage;
	
	public Member() {
		name = "";
		id_number = 0;
		phoneNumber = "";
		grade = "Normal";
		mileage = 0;
	}
	
	public Member(String newName, int newId_number, String newPhoneNumber) {
		this();
		name = newName;
		id_number = newId_number;
		phoneNumber = newPhoneNumber;
	}
	
	public void newPayment(double newPay) {
		mileage += newPay * 0.02;
		setGrade();
	}
	
	public void setGrade() {
		if(mileage > 500) grade = "gold";
		if(mileage > 1000) grade = "platinum";
		
	}
	
	public void setMember(String newName, int newId_number, String newPhoneNumber) {
		name = newName;
		id_number = newId_number;
		phoneNumber = newPhoneNumber;
	}
}
