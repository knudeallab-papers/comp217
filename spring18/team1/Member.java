import java.io.Serializable;

public class Member  implements Serializable{

	private int id;
	public String name;
	private double mile;
	private String call;
	public enum Grade {COMMON, GOLD, PLATINUM}
	private Grade grade;
	
	public Member() {
		id = 0;
		grade = Grade.COMMON;
		name = null;
		mile = 0;
		call = null;
	}
	
	public Member(int i, Grade g, String n, int m, String c) {
		setId(i);
		setGrade(g);
		setName(n);
		setMile(m);
		setCall(c);
	}
	
	public Member(Member other) {
		id = other.id;
		grade = other.grade;
		name = new String(other.name);
		mile = other.mile;
		call = new String(other.call);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setGrade(Grade g) {
		grade = g;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setName(String name) {
		this.name = new String(name);
	}
	public String getName() {
		return new String(name);
	}
	public void setMile(double mile) {
		this.mile = mile;
	}
	public double getMile() {
		return mile;
	}
	public void setCall(String c) {
		call = c;
	}
	public String getCall() {
		return new String(call);
	}
}
