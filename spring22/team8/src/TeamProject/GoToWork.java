package TeamProject;

import java.io.*;

import java.util.ArrayList;
import java.util.StringTokenizer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

@SuppressWarnings("serial")
public class GoToWork extends JFrame implements ActionListener {
	public static final int NUMBER_OF_CHAR = 10;
	private JTextField idField;
	private JComboBox<String> goToWorkCombo;
	private JComboBox<String> endWorkCombo;
	private JComboBox<String> AmPmCombo1;
	private JComboBox<String> AmPmCombo2;
	
	public GoToWork() {
		super("출퇴근 시간 등록");
		setSize(16*40, 9*40);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
		
		JLabel title = new JLabel("출퇴근 시간을 입력하세요", JLabel.CENTER);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		title.setBounds(10*15, 9*3, 16*20, 9*3);
		add(title);
		//label
		JLabel idLabel = new JLabel("id", JLabel.CENTER);
		JLabel startLabel = new JLabel("출근시간", JLabel.CENTER);
		JLabel endLabel = new JLabel("퇴근시간", JLabel.CENTER);
		idLabel.setBounds(50,70,100,40);
		startLabel.setBounds(50,135,100,40);
		endLabel.setBounds(50,200,100,40);
		add(idLabel);
		add(startLabel);
		add(endLabel);
		
		//TextField
		idField = new JTextField(NUMBER_OF_CHAR);
		idField.setBounds(170,75,300,30);
		add(idField);
		
		//ComboBox
		String AmPmString[] = {"오전","오후"};
		String HourString[] = {"01시","02시","03시","04시","05시","06시","07시","08시","09시","10시","11시","12시"};
		AmPmCombo1 = new JComboBox<String>(AmPmString);
		goToWorkCombo = new JComboBox<String>(HourString);
		AmPmCombo1.setBounds(170,140,100,30);
		goToWorkCombo.setBounds(320,140,150,30);
		
		AmPmCombo2 = new JComboBox<String>(AmPmString);
		endWorkCombo = new JComboBox<String>(HourString);
		AmPmCombo2.setBounds(170,205,100,30);
		endWorkCombo.setBounds(320,205,150,30);
		
		add(AmPmCombo1);
		add(goToWorkCombo);
		add(AmPmCombo2);
		add(endWorkCombo);
		
		//Button
		JButton checker = new JButton("입력");
		checker.addActionListener(this);
		checker.setBounds(13*20,9*28,100,40);
		add(checker);
	}
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("입력")) {
		String weekOfYear = getWeekOfYear(); 
		String dayOfWeekNum = Integer.toString(LocalDate.now().getDayOfWeek().getValue());
		//String id = idField.getText();
		ArrayList<String> employees = new ArrayList<String>();
		boolean isOverlap = false;
		try {
			BufferedReader br;
			String s;
			String path = GoToWork.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path,"UTF-8");
			File file = new File(path + "../../src/docs/employees.txt");
			br = new BufferedReader(new FileReader(file));
			while((s = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s);
				String id = st.nextToken();
				employees.add(id);
			}
			br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String id = idField.getText();
		if(id.length() == 7) {
			for(String someoneId: employees) {
				if(someoneId.equals(id))
					isOverlap = true;
			}
		}
		if(isOverlap == true && id.length() == 7 && 1 <= id.charAt(6) - '0' && id.charAt(6) - '0' <= 3) {
			String goToWorkHour = HourCheck((String)AmPmCombo1.getSelectedItem(),(String)goToWorkCombo.getSelectedItem());
			String endWorkHour = HourCheck((String)AmPmCombo2.getSelectedItem(),(String)endWorkCombo.getSelectedItem());
			String workingHour = workHour(goToWorkHour, endWorkHour);
			if(id.equals("")) {
				JOptionPane.showMessageDialog(null,"ID를 입력해주세요.","오류",JOptionPane.ERROR_MESSAGE);
			}else {
				
				//read File
				try {
					String path = GoToWork.class.getResource("").getPath();
					path = java.net.URLDecoder.decode(path,"UTF-8");
					File file = new File(path + "../../src/docs/history.txt");
					BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
					bw.write(weekOfYear + " " + dayOfWeekNum + " "
							+ id + " " + goToWorkHour + " " + endWorkHour + " " + workingHour);
					bw.newLine();
					bw.flush();
					bw.close();
					idField.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("cannot read file");
					System.exit(0);
				}
				JOptionPane.showMessageDialog(null,"입력이 완료되었습니다.","완료",JOptionPane.INFORMATION_MESSAGE);
				//resultPanel.setText(id + " " + goToWorkHour + " " + endWorkHour);
			}
			
			dispose();
		}
		else {
			if(id.length() != 7)
				JOptionPane.showMessageDialog(null, "id는 7자리로 정해주세요.", "Error", JOptionPane.INFORMATION_MESSAGE);
			else if(isOverlap == false)
				JOptionPane.showMessageDialog(null, "존재하지않는 id입니다.", "Error", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "id 7번째 숫자는 1~3사이 입니다.", "Error", JOptionPane.INFORMATION_MESSAGE);
			idField.setText("");
		}
		}
	}

	private String workHour(String startHour, String endHour) {
		int start = Integer.parseInt(startHour);
		int end = Integer.parseInt(endHour);
		if(end > start) {
			return Integer.toString(end - start);
		}
		else {
			return Integer.toString(24-start+end);
		}
	}
	public static String getWeekOfYear() {
	    LocalDate currentDate = LocalDate.now();
	    int weekOfYear = currentDate.get(WeekFields.ISO.weekOfYear());
	
	    return Integer.toString(weekOfYear);
	}
	
	public String HourCheck(String Ampm, String theHours) {
		switch(theHours) {
		case "01시": if(Ampm.equals("오전")) {
			return "01";
		}else {
			return "13";
		}
		case "02시": if(Ampm.equals("오전")) {
			return "02";
		}else {
			return "14";
		}
		case "03시": if(Ampm.equals("오전")) {
			return "03";
		}else {
			return "15";
		}
		case "04시": if(Ampm.equals("오전")) {
			return "04";
		}else {
			return "16";
		}
		case "05시": if(Ampm.equals("오전")) {
			return "05";
		}else {
			return "17";
		}
		case "06시": if(Ampm.equals("오전")) {
			return "06";
		}else {
			return "18";
		}
		case "07시": if(Ampm.equals("오전")) {
			return "07";
		}else {
			return "19";
		}
		case "08시": if(Ampm.equals("오전")) {
			return "08";
		}else {
			return "20";
		}
		case "09시": if(Ampm.equals("오전")) {
			return "09";
		}else {
			return "21";
		}
		case "10시": if(Ampm.equals("오전")) {
			return "10";
		}else {
			return "22";
		}
		case "11시": if(Ampm.equals("오전")) {
			return "11";
		}else {
			return "23";
		}
		case "12시": if(Ampm.equals("오전")) {
			return "0";
		}else {
			return "12";
		}
		default :
			return "";
		}
	}
}
