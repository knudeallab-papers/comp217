public class person extends student {
	
	protected String name;
	protected int snumber;
	
	public person() {
		super();
		name = null;
		snumber = 0;
	}
	
	public person(String i, String p, String n, int s)
	{
		super(i,p);
		this.name = n;
		this.snumber= s;
	}
	
	public person(person p)
	{
		super(p.id, p.pw);
		this.name = p.name;
		this.snumber = p.snumber;
	}
	
	public String toString()
	{
		return id+" "+pw+" "+name+" "+ Integer.toString(snumber);
	}
}
