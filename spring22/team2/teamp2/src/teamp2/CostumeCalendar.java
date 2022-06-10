package teamp2;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Integer;
import java.lang.String;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.text.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.PrintWriter;

public class CostumeCalendar extends JFrame implements ActionListener{
	
	static LocalDate now = LocalDate.now();
	static int similarity = 3;
	JLabel monthLabel;
	String ID_value;
	
	private ImageIcon bg1 = null;
	private ImageIcon bg2 = null;
	private ImageIcon bg3 = null;
	private ImageIcon bg4 = null;
	
	ArrayList<costume> clotharray = new ArrayList<>();
	
	class costume {
		int date;
		String head;
		String top;
		String bottoms;
		String shoes;
		
		public costume() {
			date = 0;
			head = "a";
			top = "a";
			bottoms = "a";
			shoes = "a";
		}
		
		public costume(int a, String b, String c, String d, String e) {
			date = a;
			head = b;
			top = c;
			bottoms = d;
			shoes = e;
		}
	}
	
	
	
	public void initcloth(LocalDate date,String ID_value) {
		this.ID_value=ID_value;
		clotharray.clear();
		costume ncostume = new costume();
		for (int i = 0; i < date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth(); i++) {
			ncostume.date = i + 1;
			ncostume.head = "a";
			ncostume.top = "a";
			ncostume.bottoms = "a";
			ncostume.shoes = "a";
			clotharray.add(ncostume);
			ncostume = new costume();
		}
	}
	
	public void makefile(LocalDate date, String ID_value) {
		initcloth(date,ID_value);
		File fileObj = null;
		String filename = "./src/teamp2/program/"+ID_value+"/date/"+date.getYear() + "_" + date.getMonthValue() + ".txt";
		if(new File(filename).exists() == false) {
			fileObj = new File(filename);
			try {
				PrintWriter outputStream = 
						new PrintWriter(new FileOutputStream(filename));
				for(int i = 0; i < clotharray.size(); i++) {
					outputStream.println(clotharray.get(i).date + " " + clotharray.get(i).head + " " + clotharray.get(i).top + " " + clotharray.get(i).bottoms + " " + clotharray.get(i).shoes);
				}
				outputStream.close();
			}catch(IOException e){
				System.out.println("Unexpected error");
			}
		}
	}
	
	public void readfile(LocalDate date, String ID_value) {
		String filename = "./src/teamp2/program/"+ID_value+"/date/"+date.getYear() + "_" + date.getMonthValue() + ".txt";
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream(filename));
		}catch(FileNotFoundException e) {
			System.err.println("Cannot find file.");
			System.exit(0);
		}
		for(int i = 0; i < clotharray.size(); i++)
				clotharray.set(i, new costume(inputStream.nextInt(), inputStream.next(), inputStream.next(), inputStream.next(), inputStream.next())); 
		inputStream.close();
	}
	
	public void savecostume(String head, String top, String bottom, String shoes, LocalDate date, String ID_value) {
		readfile(date,ID_value);
		costume ncostume = new costume();
		ncostume.date = date.getDayOfMonth();
		ncostume.head = head;
		ncostume.top = top;
		ncostume.bottoms = bottom;
		ncostume.shoes = shoes;
		clotharray.set(date.getDayOfMonth()-1, ncostume);
		File fileObj = null;
		String filename = "./src/teamp2/program/"+ID_value+"/date/"+date.getYear() + "_" + date.getMonthValue() + ".txt";
		
		if(new File(filename).exists() == false) {
			fileObj = new File(filename);
		}
		try {
				PrintWriter outputStream = new PrintWriter(new FileOutputStream(filename));
				for(int i = 0; i < clotharray.size(); i++)
					outputStream.println(clotharray.get(i).date + " " + clotharray.get(i).head + " " + clotharray.get(i).top + " " + clotharray.get(i).bottoms + " " + clotharray.get(i).shoes);
				outputStream.close();
			}catch(IOException e){
				System.out.println("Unexpected error");
			}
	}
	
	public String getcostume(LocalDate date,String ID_value) {
		readfile(date,ID_value);
		costume cos = clotharray.get(date.getDayOfMonth() - 1);
		if(cos.head.equals("a"))
			return ("no data.");
		else
			return (cos.date + " " + cos.head + " " + cos.top + " " + cos.bottoms + " " + cos.shoes);
	}
	
	public CostumeCalendar(LocalDate now, String ID_value) {
		super("Calendar");
		getContentPane().setBackground(Color.PINK);
		setSize(794, 539);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel monthPanel = new JPanel();
		monthPanel.setBackground(Color.WHITE);
		monthPanel.setBounds(12, 10, 754, 34);
		
		monthLabel = new JLabel(now.getYear() + "." + now.getMonthValue());
		둥근버튼 prevButton = new 둥근버튼("<");
		prevButton.addActionListener(this);
		둥근버튼 nextButton = new 둥근버튼(">");
		nextButton.addActionListener(this);
		getContentPane().setLayout(null);
		
		monthPanel.add(prevButton, BorderLayout.WEST);
		monthPanel.add(monthLabel, BorderLayout.CENTER);
		monthPanel.add(nextButton, BorderLayout.EAST);
		
		getContentPane().add(monthPanel);
		
		getContentPane().add(makeCalendar(now,ID_value));
		makefile(now,ID_value);
		readfile(now,ID_value);
	}
	
	public JPanel makeCalendar(LocalDate date, String iD_value) {
		LocalDate prev;
		if(now.getMonthValue() == 1)
			prev = LocalDate.of(now.getYear() - 1, 12, 1);
		else
			prev = LocalDate.of(now.getYear(), now.getMonthValue() - 1, 1);
		int prevLast = prev.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
		int currLast = now.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
		int weekFirst = now.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();
		
		if(weekFirst == 7)
			weekFirst = 0;
		JPanel Calendar = new JPanel();
		Calendar.setBackground(Color.WHITE);
		Calendar.setBounds(12, 54, 754, 436);
		if((currLast == 30 && weekFirst == 6 ) || (currLast == 31 && weekFirst >= 5))
			Calendar.setLayout(new GridLayout(7,7));
		else 
			Calendar.setLayout(new GridLayout(6,7));
		
		JLabel sunLabel = new JLabel("Sun");
		sunLabel.setHorizontalAlignment(JLabel.CENTER);
		sunLabel.setForeground(Color.RED);
		JLabel monLabel = new JLabel("Mon");
		monLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel tueLabel = new JLabel("Tue");
		tueLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel wedLabel = new JLabel("Wed");
		wedLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel thuLabel = new JLabel("Thu");
		thuLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel friLabel = new JLabel("Fri");
		friLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel satLabel = new JLabel("Sat");
		satLabel.setHorizontalAlignment(JLabel.CENTER);
		satLabel.setForeground(Color.BLUE);
		
		Calendar.add(sunLabel);
		Calendar.add(monLabel);
		Calendar.add(tueLabel);
		Calendar.add(wedLabel);
		Calendar.add(thuLabel);
		Calendar.add(friLabel);
		Calendar.add(satLabel);
		
		int CalendarFirst = prevLast - weekFirst + 1;
		
		JLabel[] prevLabel = new JLabel[weekFirst];
		for(int i = 0; i < weekFirst; i++ ) {
			prevLabel[i] = new JLabel("" + (CalendarFirst + i));
			prevLabel[i].setHorizontalAlignment(JLabel.CENTER);
			if(i == 0)
				prevLabel[i].setForeground(Color.RED);
			if(i == 6)
				prevLabel[i].setForeground(Color.BLUE);
			Calendar.add(prevLabel[i]);
		}
		
		둥근버튼[] currButton = new 둥근버튼[currLast];
		for(int i = 0; i < currLast; i++ ) {
			currButton[i] = new 둥근버튼("" +(1 + i));
			currButton[i].addActionListener(this);
			if((i + weekFirst) % 7 == 0)
				currButton[i].setForeground(Color.RED);
			if((i + weekFirst) % 7 == 6)
				currButton[i].setForeground(Color.BLUE);
			Calendar.add(currButton[i]);
		}
		
		int nextday;
		
		if((currLast == 30 && weekFirst == 6 ) || (currLast == 31 && weekFirst >= 5))
			nextday = 42 - weekFirst - currLast;
		else
			nextday = 35 - weekFirst - currLast;
		JLabel[] nextLabel = new JLabel[nextday];
		for(int i = 0; i < nextday; i++ ) {
			nextLabel[i] = new JLabel("" + (1 + i));
			nextLabel[i].setHorizontalAlignment(JLabel.CENTER);
			if((i + currLast + weekFirst) % 7 == 0)
				nextLabel[i].setForeground(Color.RED);
			if((i + currLast + weekFirst) % 7 == 6)
				nextLabel[i].setForeground(Color.BLUE);
			Calendar.add(nextLabel[i]);
		}
		return Calendar;
	}
	
	private class ConfirmWindow extends JFrame {
		public ConfirmWindow(LocalDate date, String ID_value) {
			
			String text = "";
			readfile(date,ID_value);
			costume cos = clotharray.get(date.getDayOfMonth() - 1);
			getContentPane().setName("" + cos.date);
			if(cos.head.equals("a"))
				text = ("no data.");
			else
				text = (cos.head + " " + cos.top + " " + cos.bottoms + " " + cos.shoes);
			
			String 모자="./src/teamp2/program/"+ID_value+"/cloth/1/"+cos.head+".png";
			bg1 = new ImageIcon(모자);
			
			String 상의="./src/teamp2/program/"+ID_value+"/cloth/2/"+cos.top+".png";
			bg2 = new ImageIcon(상의);
			
			String 하의="./src/teamp2/program/"+ID_value+"/cloth/3/"+cos.bottoms+".png";
			bg3 = new ImageIcon(하의);
			
			String 신발="./src/teamp2/program/"+ID_value+"/cloth/4/"+cos.shoes+".png";
			bg4 = new ImageIcon(신발);
			
			setTitle(date.getYear() + "." + date.getMonthValue() + "." + date.getDayOfMonth());
			setSize(503, 671);
			getContentPane().setBackground(Color.white);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(null);
			
			JPanel 모자표시패널 = new JPanel() //모자 이미지 띄울 패널
			{
			    public void paintComponent(Graphics g) 
			    {
			     g.drawImage(bg1.getImage(), 30, 0, 150, 150, null);
			    }
			   };
			모자표시패널.setBounds(12, -20, 424, 382);
			add(모자표시패널);
			모자표시패널.setLayout(null);
			모자표시패널.setVisible(true);
			
			JPanel 상의표시패널 = new JPanel() //상의 이미지 띄울 패널
			   {
			    public void paintComponent(Graphics g) 
			    {
			     g.drawImage(bg2.getImage(), 80, 0, 260, 250, null);
			    }
			   };
			상의표시패널.setBounds(12, 50, 424, 382);
			add(상의표시패널);
			상의표시패널.setLayout(null);
			상의표시패널.setVisible(true);
			
			JPanel 신발표시패널 = new JPanel() //신발 이미지 띄울 패널
					{
					    public void paintComponent(Graphics g) 
					    {
					     g.drawImage(bg4.getImage(), 30, 0, 150, 150, null);
					    }
					   };   
					신발표시패널.setBounds(12, 430, 463, 200);
					add(신발표시패널);
					신발표시패널.setLayout(null);
					신발표시패널.setVisible(true);
			
			JPanel 하의표시패널 = new JPanel() //하의 이미지 띄울 패널
				{
			    public void paintComponent(Graphics g) 
			    {
			     g.drawImage(bg3.getImage(), 115, 0, 200, 300, null);
			    }
			   };
			하의표시패널.setBounds(12, 230, 424, 382);
			add(하의표시패널);
			하의표시패널.setLayout(null);
			하의표시패널.setVisible(true);
			
			
			
			
			
			
			JLabel confirmLabel2 = new JLabel(date.getYear()+ "." +date.getMonthValue() + "." + date.getDayOfMonth()+"날 입었던 옷 조합은\n");
			confirmLabel2.setBounds(24, 530, 366, 76);
			confirmLabel2.setFont(new Font("휴먼편지체", Font.PLAIN, 20));
			confirmLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			add(confirmLabel2);
			
			JLabel confirmLabel = new JLabel(text+"!");
			confirmLabel.setBounds(24, 570, 366, 76);
			confirmLabel.setFont(new Font("휴먼편지체", Font.PLAIN, 20));
			confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
			add(confirmLabel);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String btnStr = e.getActionCommand();
			
		if(btnStr.equals("<")) {
			
			setVisible(false);
			if(now.getMonthValue() == 1)
				now = LocalDate.of(now.getYear() - 1, 12, 1);
			else
				now = LocalDate.of(now.getYear(), now.getMonthValue() - 1, 1);
			monthLabel.setText(now.getYear() + "." + now.getMonthValue());
			CostumeCalendar gui = new CostumeCalendar(now,ID_value);
			gui.setVisible(true);
			makefile(now,ID_value);
			readfile(now,ID_value);
		}
		else if(btnStr.equals(">")) {
			
			setVisible(false);
			if(now.getMonthValue() == 12)
				now = LocalDate.of(now.getYear() + 1, 1, 1);
			else
				now = LocalDate.of(now.getYear(), now.getMonthValue() + 1, 1);
			monthLabel.setText(now.getYear() + "." + now.getMonthValue());
			CostumeCalendar gui = new CostumeCalendar(now,ID_value);
			gui.setVisible(true);
			makefile(now,ID_value);
			readfile(now,ID_value);
		}
		else if(Integer.parseInt(btnStr) > 0) {
			LocalDate getdate = LocalDate.of(now.getYear(), now.getMonth(), Integer.parseInt(btnStr));
			ConfirmWindow checkers = new ConfirmWindow(getdate,ID_value);
			checkers.setVisible(true);
		}
		else {
			System.err.println("Unexpected error.");
		}
	}
		
	
	
	public int date() {
		LocalDate curr = LocalDate.now();
		return(curr.getYear() * 10000 + curr.getMonthValue() * 100 + curr.getDayOfMonth());				
	}
	
	public ArrayList<costume> similar(String ID_value) {
		LocalDate curr = LocalDate.now();
		ArrayList<costume> similararray = new ArrayList<>();
		for(int i = 1; i <= similarity; i++) {
			LocalDate date = curr.minusDays(i);
			readfile(date,ID_value);
			costume cos = clotharray.get(date.getDayOfMonth() - 1);
			similararray.add(cos);
		}
		return similararray;
	}

	
}
