
public class student {
	
	protected String id;
	protected String pw;
	
	public student()
	{
		id = null;
		pw = null;
	}
	public student(String i, String p)
	{
		this.id  = i;
		this.pw = p;
	}
	
	public String toString()
	{
		return id+" "+pw;
	}
	
	public student(student s)
	{
		this.id = s.id;
		this.pw = s.pw;
	}
}