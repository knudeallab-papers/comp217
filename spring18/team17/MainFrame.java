package javaPr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame implements ActionListener {

	public static boolean isLoaded = false;
	public static boolean isGraphOpened = false;
	static Calendar cal = Calendar.getInstance();

	public static int year = cal.get(Calendar.YEAR);
	public static int month = cal.get(Calendar.MONTH) + 1;
	public static int day = cal.get(Calendar.DAY_OF_MONTH);

	JPanel upperMenu = new JPanel();
	JPanel upperLeft = new JPanel();
	JPanel upperRight = new JPanel();
	JTabbedPane tab = new JTabbedPane();
	TableTab tab1;
	StoreTab tab2;
	MemberTab tab3;
	FoodTab tab4;
	EmployeeTab tab5;
	Graph tab6;

	public static int totalMoney = 1000000;
	public static int todaysMoney = 0;

	JButton dayEnd = new JButton("마감");
	JButton exit = new JButton("종료");
	JLabel date = new JLabel("");
	static JLabel money = new JLabel();

	public MainFrame() {
		super("가게관리시스템");

		ObjectOutputStream outputStream = null;
		String filename = "restaurant.dat";
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename));
			year = inputStream.readInt();
			month = inputStream.readInt();
			day = inputStream.readInt();

			totalMoney = inputStream.readInt();

			inputStream.close();
			isLoaded = true;
			System.out.println(isLoaded);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		tab1 = new TableTab();
		tab2 = new StoreTab();
		tab3 = new MemberTab();
		tab4 = new FoodTab();
		tab5 = new EmployeeTab();
		tab6 = new Graph();
		cal.set(year, month - 1, day);

		setLayout(new BorderLayout());
		setBounds(200, 100, 1600, 800);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		tab.addTab("테이블", tab1);
		tab.addTab("창고", tab2);
		tab.addTab("회원", tab3);
		tab.addTab("메뉴", tab4);
		tab.addTab("직원", tab5);
		tab.addTab("통계", tab6);

		money.setText("오늘 매출 : " + todaysMoney + "원 전체 잔고 : " + totalMoney + "원");
		dayEnd.setActionCommand("DayEnd");
		dayEnd.addActionListener(this);
		exit.setActionCommand("exit");
		exit.addActionListener(this);
		exit.setBackground(Color.CYAN);

		upperMenu.setLayout(new BorderLayout());
		upperLeft.setLayout(new FlowLayout());
		upperRight.setLayout(new BorderLayout());

		date.setText(year + "년 " + month + "월 " + day + "일");

		upperLeft.add(date);
		upperLeft.add(dayEnd);

		upperRight.add(money, BorderLayout.CENTER);
		upperRight.add(exit, BorderLayout.SOUTH);

		upperMenu.add(upperLeft, BorderLayout.WEST);
		upperMenu.add(upperRight, BorderLayout.EAST);

		add(tab, BorderLayout.CENTER);
		add(upperMenu, BorderLayout.NORTH);
		setResizable(false);
		setVisible(true);
	}

	public static String getDay() {
		return year + "." + month + "." + day;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "DayEnd") {
			boolean cannot = false;
			for (int i = 0; i < 10; i++) {
				if (TableTab.isTableOn[i]) {
					cannot = true;
					break;
				}
			}

			if (cannot == false) {
				cal.add(Calendar.DATE, 1);

				year = cal.get(Calendar.YEAR);
				month = cal.get(Calendar.MONTH) + 1;
				day = cal.get(Calendar.DAY_OF_MONTH);
				StoreTab.dayEndStore();
				Graph.updateData();
				tab6 = new Graph();
				totalMoney += todaysMoney;
				todaysMoney = 0;

				if (cal.get(cal.DATE) == 1) {
					EmployeeTab.payWage();
					int count = MemberTab.memberTable.getRowCount();

					for (int i = 0; i < count; i++) {
						MemberTab.initMileage(i);
					}
				}
				money.setText("오늘 매출 : " + todaysMoney + "원 전체 잔고 : " + totalMoney + "원");
				date.setText(year + "년 " + month + "월 " + day + "일");
			} else {
				JOptionPane.showMessageDialog(this, "결제되지 않은 테이블이 존재합니다", "마감", JOptionPane.ERROR_MESSAGE);
			}
		} else if (arg0.getActionCommand() == "exit") {
			Main.saveDat();
			MemberTab.saveMember();
			FoodTab.saveFood();
			EmployeeTab.saveEmployee();
			StoreTab.saveStore();
			Graph.saveProfit();
			System.exit(0);
		}
	}

	static public void setMoney() {
		money.setText("오늘 매출 : " + todaysMoney + "원 전체 잔고 : " + totalMoney + "원");
	}

}
