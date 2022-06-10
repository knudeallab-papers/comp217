package javaPr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		MainFrame mainFrame = new MainFrame();

	}

	@SuppressWarnings("deprecation")
	static public void saveDat() {
		ObjectOutputStream outputStream = null;
		String filename = "restaurant.dat";

		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(filename));
			outputStream.writeInt(MainFrame.year);
			outputStream.writeInt(MainFrame.month);
			outputStream.writeInt(MainFrame.day);

			outputStream.writeInt(MainFrame.totalMoney);

			outputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
