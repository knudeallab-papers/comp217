import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Worker 
{
	public static int workerNum = 0;
   private String name;
   private String position;
   
private String phoneNumber;
   private final CDate startday;
   private int salary;

   // constructor
   public Worker(String name, String position, String phoneNumber, int salary, CDate today){
	      this.name = name;                                   
	      this.position = position;                                    
	      this.phoneNumber = phoneNumber;
	      setSalary(salary);
	      startday = new CDate(today);
	      workerNum++;
	   }
   
   public void setName(String name) {
		this.name = name;
	}
   public String getName()
   {
      return name;
   } 

   public String getPhoneNumber()
   {
      return phoneNumber;
   } 
   public void setPhoneNumber(String pn)
   {
      this.phoneNumber = pn;
   }

   public String getPosition()
   {
      return position;
   }
   public void setPosition(String pos)
   {
	   position = pos;
   } 

   public CDate getStartday()
   {
      return startday;
   }
   public double getSalary()
   {
      return salary;
   } 
   public void setSalary(int salary)
   {
      if (salary < 0)
         throw new IllegalArgumentException(
            "Weekly salary must be >= 0.0");

      this.salary = salary;
   }
   
   public static void savefile(){
		String path = Worker.class.getResource(".").getPath().toString()+"Worker"+".txt";

		try(FileWriter fout= new FileWriter(path)) {
			PrintWriter out = new PrintWriter(fout);
			out.println(workerNum + "");
			for( Worker elem : TableDemo.WorkerAry ) 
				out.println(elem.toString());
		}catch(Exception e) {
			System.out.println("saveFile error: "+e.getMessage());
			//System.exit(-1);
		}
	}
   
   
   
   public String toString() {
	   return this.name + " " 
	   		+this.position + " " 
	   		+this.phoneNumber+ " " 
	   		+this.startday.toString()+ " " 
	   		+this.salary;
   }

   public static void readfile() {
		int n = 0;

		try {
			String path = Worker.class.getResource(".").getPath().toString()+"Worker"+".txt";
			File worker = new File(path);
			if(worker != null)
			{
				System.out.println("Worker file is found");
			}
			Scanner keyboard = new Scanner(worker);
			try {
				n = keyboard.nextInt();
			}catch(Exception e) {
				System.out.println("file is empty!!");
			}
			
			String name;
			String position;
			String phoneNumber;
			CDate thatday;
			int salary;
			
			for(int i = 0; i < n; i++) {
				name = keyboard.next();
				position = keyboard.next();
				phoneNumber = keyboard.next();
				String cdate = keyboard.next();
				String[] cdateInst = cdate.split("/");
				System.out.println("year: "+cdateInst[0]);
				System.out.println("month: "+cdateInst[1]);
				System.out.println("day: "+cdateInst[2]);
				thatday = new CDate(Integer.parseInt(cdateInst[0]), Integer.parseInt(cdateInst[1]), Integer.parseInt(cdateInst[2]));
				salary = keyboard.nextInt();
		       TableDemo.WorkerAry[i] = new Worker(name, position, phoneNumber, salary, thatday);
		       System.out.println(TableDemo.WorkerAry[workerNum-1]);
		    }
		}catch(Exception e) {
			System.out.println("Worker file not found");
		}
			
	}}