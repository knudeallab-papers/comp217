import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class readLecture {

	public static void read() {
		// TODO Auto-generated method stub
		String FileName = login.roomnum+".dat";
		try { 
	         ObjectInputStream inStream = 
	               new ObjectInputStream(new FileInputStream(FileName));
	         try {
	            while(true) {
	               Lecture lec = (Lecture)inStream.readObject();
	               System.out.println(lec.getLecName());
	            }
	         }catch(EOFException | ClassNotFoundException e) {} 
	         inStream.close();
	      } catch (IOException e) { e.printStackTrace(); }
	}

}
