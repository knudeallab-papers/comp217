import java.io.Serializable;

public class seat implements Serializable{
	
	private int no;
	private String name;
	private int sid;
	boolean check;
	
	public seat()
	{
		no=0;
		name = null;
		sid = 0;
		check = false;
	}
	public seat(int n, String na, String si, boolean c)
	{
		this.no= n;
		this.name = na;
		this.sid = 0;
		this.check = c;
	}
	
	public void nosetter(int n)
	{
		this.no = n;
	}
	public int nogetter()
	{
		return this.no;
	}
	
	
	public void namesetter(String n)
	{
		this.name = n;
	}
	public String namegetter()
	{
		return this.name;
	}
	
	public void sidsetter(int s)
	{
		this.sid = s;
	}
	
	public int sidgetter()
	{
		return this.sid;
	}
	
	public void checksetter(boolean b)
	{
		this.check = b;
	}
	
	public boolean checkgetter()
	{
		return this.check;
	}
	
	public String toString()
	{
		return no+name+sid+check;
	}
}