package five_nights_at_freddy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FNAF_PizzaStore {
	public static void main(String orgs[]) {
		FNAF_Screen gui = null;
		try {
			ObjectInputStream saveFile = new ObjectInputStream(new FileInputStream("save"));
			gui = (FNAF_Screen) saveFile.readObject();
		}catch(FileNotFoundException e) {
			gui = new FNAF_Screen();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(gui == null) gui = new FNAF_Screen();
		gui.setVisible(true);
	}
}
