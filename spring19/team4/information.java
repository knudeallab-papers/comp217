import java.io.Serializable;
import java.io.FileOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.io.IOException;
public class information implements Serializable{
	
	int money;
	class mywindow extends JFrame implements WindowListener{
		
		public mywindow() {
			super("저장");
			JLabel a=new JLabel("저장되었습니다.");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(this);
			setSize(200,100);
			add(a);
		}

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			dispose();
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void save(int money) {
		try {
			ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream("information.dat"));
			this.money=money;
			outputStream.writeObject(this);
			outputStream.close();
		}catch(IOException e) {
			System.out.println("Error Writing to file");
			System.exit(0);
		}
		
		mywindow a=new mywindow();
		a.setLocationRelativeTo(null);
		
		a.setVisible(true);
		
	}
	public void save2(int money) {
		try {
			ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream("information.dat"));
			this.money=money;
			outputStream.writeObject(this);
			outputStream.close();
		}catch(IOException e) {
			System.out.println("Error Writing to file");
			System.exit(0);
		}
		
		
		
	}
}
