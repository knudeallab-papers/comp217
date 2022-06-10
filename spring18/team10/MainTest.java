
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class MainTest
{
	public static MainPage main;
	public static void main(String[] args)
	{
		main = new MainPage();
		main.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent event) {
				main.fileSave();
				System.exit(0);
			}
		});
		main.setVisible(true);
	}
}
