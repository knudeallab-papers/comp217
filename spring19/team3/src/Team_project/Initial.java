package Team_project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Initial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Order[] order = new Order[12];
		for(int i =0;i<12;i++)
		{
			order[i] = new Order();
		}
	      Scanner inputStream=null;
	      try {
	          inputStream = new Scanner(new FileInputStream("beverage.txt"));
	       }catch(FileNotFoundException e){
	          e.printStackTrace();
	          System.exit(0);
	       }
	      String na=null;
	      int pri=0;
	      int sto=0;
	      int i=0;
	      while(inputStream.hasNext())
	      {
	         na=inputStream.next();
	         pri=inputStream.nextInt();
	         sto=inputStream.nextInt();
	         order[i].setName(na);
	         order[i].setNum(0);
	         i++;
	      }
	      
	      try {
				ObjectOutputStream outputStream = 
						new ObjectOutputStream(new FileOutputStream("beverage.dat"));
				outputStream.writeObject(order);
				outputStream.close();
			}catch(IOException e) {
				System.err.println("Error writing to file.");
				System.exit(0);
			}
	      
	}

}
