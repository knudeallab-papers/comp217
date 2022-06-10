import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Members {

	public static enum MemberHier {Nomal, Gold, Platinum};
	public static int membernum =0;
	private String name;
	private String phone_number;
	private MemberHier level;
	public int mileage;
	
	public Members(String input_name, String input_phone)
	{
		setName(input_name);
		setPhone(input_phone);
		/*
		for(MemberHier a: MemberHier.values())
		{
			if(a.toString() == input_level)
			{
				level = a;
				break;
			}
		}
		*/
		level = MemberHier.Nomal;
		mileage = 0;
		membernum++;
	}
	

	public String getName()
	{
		return name;
	}
	public String getPhone()
	{
		return phone_number;
	}
	public MemberHier getLevel()
	{
		return level;
	}
	public int getMile()
	{
		return (int)mileage;
	}
	public double getSale()
	{
		if(level.equals(MemberHier.Nomal))
		{
			return 0.98;
		}
		else if(level.equals(MemberHier.Gold))
		{
			return 0.95;
		}
		else if(level.equals(MemberHier.Platinum))
		{
			return 0.9;
		}
		else
		{
			System.err.println("fatal error occured in member.");
			return -1;
		}
	}
	
	public void addMile(double add)
	{
		mileage+= add;
		checkLevel();
	}
	
	private void checkLevel()
	{
		if(mileage<=500)
		{
			for(MemberHier a: MemberHier.values())
			{
				if(a.toString() == "Nomal")
				{
					level = a;
					break;
				}
			}
		}
		else if(mileage<=1000 && mileage >500)
		{
			for(MemberHier a: MemberHier.values())
			{
				if(a.toString().equals("Gold") )
				{
					level = a;
					break;
				}
			}
		}
		else
		{
			for(MemberHier a: MemberHier.values())
			{
				if(a.toString().equals( "Platinum"))
				{
					level = a;
					break;
				}
			}
		}
		
		
	}
	
	public void setName(String newname)
	{
		name = newname;
	}
	
	public void setPhone(String newnumber)
	{
		phone_number = newnumber;
	}
	
	public void EndMember()
	{
		mileage = 0;
		level = null;
		name = null;
		phone_number = null;
		membernum--;
	}
	
	public static void savefile(){
		String path = Members.class.getResource(".").getPath().toString()+"Member"+".txt";
		try(FileWriter fout = new FileWriter(path)) {
			PrintWriter out = new PrintWriter(fout);
			out.println(membernum+"");
			for( Members elem : TableDemo.MemberAry ) 
				out.println(elem.toString());
		}catch(Exception e) {
			System.out.println("saveFile error: "+e.getMessage());
			//System.exit(-1);
		}
	}
	
	public String toString() {
		return 	this.name+ " " 
				+ phone_number+ " " 
				+ level + " " 
				+ mileage;
	}
	
	public static MemberHier getLevel(String str)
	{
		if(str.equals(MemberHier.Nomal.toString()))
		{
			return MemberHier.Nomal;
		}else if(str.equals(MemberHier.Gold.toString()))
		{
			return MemberHier.Gold;
		}else if(str.equals(MemberHier.Platinum.toString()))
		{
			return MemberHier.Platinum;
		}
		return null;
	}
	public void setLevel(MemberHier lev)
	{
		level = lev;
	}
	
	public static void readfile() {
		int n = 0;

		try {
			String path = Members.class.getResource(".").getPath().toString()+"Member"+".txt";
			File mem = new File(path);
			if(mem != null)
			{
				System.out.println("Member file is found");
			}
			Scanner keyboard = new Scanner(mem);
			try {
				n = keyboard.nextInt();
			}catch(Exception e) {
				System.out.println("file is empty!!");
			}
			
			String name;
			String phone_number;
			MemberHier level;
			int mileage;
			
			for(int i = 0; i < n; i++) {
				name = keyboard.next();
				phone_number = keyboard.next();
				level = getLevel(keyboard.next());
				mileage = Integer.parseInt(keyboard.next());

		       TableDemo.MemberAry[i] = new Members(name, phone_number);
		       TableDemo.MemberAry[i].setLevel(level);
		       TableDemo.MemberAry[i].mileage = mileage;
		    }
			return;
		}catch(Exception e) {
			System.out.println("Member file not found");
		}
			
	}
}