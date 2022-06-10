import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class testmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<member> test=new ArrayList<member>();
		member input = new member();
		input.setId("a");
		input.setName("a");
		input.setAddress("a");
		input.setMajor("a");
		input.setPassword("a");
		input.setSid("a");
		test.add(input);
//		input = new member();
//		input.setId("ba");
//		input.setName("ba");
//		input.setAddress("ba");
//		input.setMajor("ab");
//		input.setPassword("ba");
//		input.setSid("ab");
		test.add(input);
		try {
        	ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("memberFile"));
        	outputStream.writeObject(test);
        	outputStream.close();
        }catch(IOException e){
	        System.err.println("Error writing to file");
	        System.exit(0);
	    }
		test = null;
		try {
	         ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("memberFile"));
	         try {
	        		test= (ArrayList<member>)inputStream.readObject();
	         }catch(EOFException e) {
	        	 System.out.println("No more numbers in the file.");
	         }
	      }catch(FileNotFoundException e){
	         System.out.println("Cannot find file arrayfile.");
	         System.exit(0);
	      }catch(ClassNotFoundException e) {
	         System.err.println("Problems with the file input");
	         System.exit(0);
	      }catch(IOException e) {
	         System.out.println("Problems with the file input1");
	         System.exit(0);
	      }
		System.out.println(test.get(0).getId());
		System.out.println(test.get(1).getId());
	}

}
