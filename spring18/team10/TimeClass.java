
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TimeClass
{
	SimpleDateFormat dateFormat;
	Calendar cal;
	MyCalendar m;
	
	public TimeClass() {
		//file open
		//read last object --> set cal
		
		MyCalendar temp = new MyCalendar(0,0,0);
		MyCalendar last = new MyCalendar(0,0,0);
		
		try
		{
			 ObjectInputStream input =
				new ObjectInputStream(new FileInputStream("datafile.ser"));
			 
			 //last object of datafile
			 while(temp != null)
			 {
    			 	temp = (MyCalendar)input.readObject();
    			 	
    			 // temp -> last deep copy
    			 	last.setDay(temp.getDay());
    			 	last.setMonth(temp.getMonth());
    			 	last.setYear(temp.getYear());
    			 
//    			 	System.out.println(temp.toInt());
			 }
			 
			 input.close();

		}
		catch(FileNotFoundException e)
		{
		     System.out.println("Cannot find datafile.");
		     dateStart();
		}
		
		catch(ClassNotFoundException e)
		{
		    System.out.println("Problems with file input 1.");
		}

		catch(IOException e)
		{
		    System.out.println("Not use null data");
//		    System.out.println(last.toInt());
		}
//		System.out.println(last.toInt());
		
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, last.getYear());
		cal.set(Calendar.MONTH, last.getMonth());
		cal.set(Calendar.DAY_OF_MONTH, last.getDay());
		
		m = new MyCalendar(last.getDay(), last.getMonth(), last.getDay());
		
	}
	
    
    	public void nextDay()
    	{
    		cal.add(Calendar.DATE,1);
    		
    		m.setYear(cal.get(Calendar.YEAR)); 
    		m.setMonth(cal.get(Calendar.MONTH)); 
    		m.setDay(cal.get(Calendar.DATE));
    		
    		
    		try
    		{
    			ObjectOutputStream outputStream =
    	    		new ObjectOutputStream(new FileOutputStream("datafile.ser"));

    			outputStream.writeObject(m);
    			System.out.println(m.toInt());
    			outputStream.close();
    		}
    		
    		catch(FileNotFoundException e)
    		{
    			
    		}
    		
    		catch(IOException e2)
    		{
    			
    		}
    		
    	}
    	
    	public String toString()
    	{
    		return(cal.get(Calendar.YEAR) + "년 " + cal.get(Calendar.MONTH) + "월 " + cal.get(Calendar.DATE) + "일");
    	}
//    	
    	public void dateStart()
    	{
    		try
    		{
    			ObjectOutputStream output;
    			
    	    		output = new ObjectOutputStream(Files.newOutputStream(Paths.get("datafile.ser")));

    	    		output.writeObject(m);
    	     
    	    		output.close();
         	
    	    		System.out.println("Start market!");
    		}
    		
    		catch(IOException e)
    		{
    			System.out.println("Problem with writing date");
    		}
    	}
    	
    	public MyCalendar setDate()
    	{    		
    		return m;
    	}
    	
}





