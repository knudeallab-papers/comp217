package bk;
import bk.MainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BurgerKing extends JFrame implements ActionListener, WindowListener, Serializable {

	public static final int WIDTH = 900;
	public static final int HEIGHT = 560;
	protected static String str;
	public static int todaysales = 0;
	public static int totalsales = 0;
	public static final String srcPath = "src";
	public static final String packageName = "bk";
	
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
	JLabel label;
	public static JLabel label2;
	public static BurgerKing burger;// = new BurgerKing();
	public static MainWindow w ;//= new MainWindow();
	static ObjectOutputStream outputstream ;
	static ObjectInputStream inputStream ;
	static File fileObj = null;// = new File("datafile.dat");
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub


			try {
				//BurgerKing burgerKing = new BurgerKing();
				fileObj = new File("datafile.dat");
				inputStream = new ObjectInputStream(new FileInputStream("datafile"));
				burger = (BurgerKing)inputStream.readObject();

			} catch(IOException excep) {
				System.err.println("Problem with file input.");
				burger = new BurgerKing();
				burger.setVisible(true);
			} catch(ClassNotFoundException excep) {
				excep.printStackTrace();
			}

		burger.setVisible(true);
		
		//MainWindow w = new MainWindow();
		//w.setVisible(true);
	}
	
	public BurgerKing() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Welcome To BurgerKing!");
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.RED);
		str = date.format(cal.getTime());
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.ORANGE);
		
		String currentProjPath = "";
		try {
			currentProjPath = new File(".").getCanonicalPath();
		} catch( IOException e) {
			e.printStackTrace();
		}
		String IconName = "Burger_King-logo-880x660.png";
		String bkImgIconFilePath = currentProjPath +"/" + srcPath +"/" + packageName+"/" + IconName;
		ImageIcon bkIcon = new ImageIcon(bkImgIconFilePath);
		label = new JLabel(str,Label.LEFT);
		label.setIcon(bkIcon);
		JButton button = new JButton("마감");
		label2 = new JLabel("오늘 매출 : "+todaysales+"원 전체 잔고 : "+totalsales+"원");
		JButton button2 = new JButton("종료");
		button2.addActionListener(this);
		button.addActionListener(this);
		addWindowListener(this);
		
		//JLabel welcome = new JLabel("welcome to BurgerKing!!");
	
			try {
				
				inputStream = new ObjectInputStream(new FileInputStream("datafile"));
				w = (MainWindow)inputStream.readObject();

				inputStream.close();
			} catch(IOException excep) {
				System.err.println("Problem with file input.");
				w = new MainWindow();
				
			} catch(ClassNotFoundException excep) {
				excep.printStackTrace();
			}
		
			
		
		label.setIcon(bkIcon);
		panel1.add(label);
		panel1.add(button);
		panel1.add(label2);
		panel1.add(button2);
		add(panel1,BorderLayout.NORTH);
		add(w,BorderLayout.CENTER);
	
		/*addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					
					outputstream = new ObjectOutputStream(new FileOutputStream("datafile.ser"));
					outputstream.writeObject(burger.getWindows());
					outputstream.writeObject(w);
					
					outputstream.close();
					System.exit(0);
				} catch(FileNotFoundException exc) {
					System.err.println("Cannot Find File!");
					exc.printStackTrace();
				}catch(IOException exc) {
					System.err.println("Problem with file output.");
				}
			}
		});*/
		w.setVisible(true);
	}
	
	/*public JLabel getLabel2() {
		return label2;
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String btnStr = e.getActionCommand();
		
		if(btnStr.equals("마감"))
		{
			cal.add(Calendar.DATE,1);
			str = date.format(cal.getTime());
			label.setText(str);
			for(int i =0; i < MainWindow.modelTbl.getRowCount(); i++) {//주문 배송완료 처리 
				if(Integer.parseInt(MainWindow.modelTbl.getValueAt(i, 2).toString()) != 0) {//주문 한 것이 있으면  
					totalsales = totalsales - ((Integer.parseInt(MainWindow.modelTbl.getValueAt(i, 2).toString()))*
							(Integer.parseInt(MainWindow.modelTbl.getValueAt(i, 3).toString())));
					MainWindow.modelTbl.setValueAt((Integer.parseInt(MainWindow.modelTbl.getValueAt(i,1).toString()) + 
					Integer.parseInt(MainWindow.modelTbl.getValueAt(i, 2).toString())), i, 1);//stock update
					MainWindow.ingQuantityVal.setText("  수량 : "+ MainWindow.modelTbl.getValueAt(i, 1));
					//주문량 0 처리 
					MainWindow.modelTbl.setValueAt(0, i, 2);
					MainWindow.ingOrderAmountVal.setText("  주문량 : " + MainWindow.modelTbl.getValueAt(i, 2));
				}	
			}
			totalsales = totalsales + todaysales;
			todaysales = 0;
			label2.setText("오늘 매출 : "+todaysales+"원 전체 잔고 : "+totalsales+"원");
		}
		else if(btnStr.equals("종료"))
			System.exit(0);
					
					
				
		
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		try {
			
			outputstream = new ObjectOutputStream(new FileOutputStream("datafile"));
			outputstream.writeObject(getWindows());
			outputstream.writeObject(w);
			
			outputstream.close();
			System.exit(0);
		} catch(FileNotFoundException exc) {
			System.err.println("Cannot Find File!");
			exc.printStackTrace();
		}catch(IOException exc) {
			System.err.println("Problem with file output.");
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub//현재까지의 data 즉시 저장 
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
