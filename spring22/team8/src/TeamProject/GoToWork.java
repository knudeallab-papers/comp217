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
		super("����� �ð� ���");
		setSize(16*40, 9*40);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
		
		JLabel title = new JLabel("����� �ð��� �Է��ϼ���", JLabel.CENTER);
		title.setFont(new Font("���� ���", Font.BOLD, 25));
		title.setBounds(10*15, 9*3, 16*20, 9*3);
		add(title);
		//label
		JLabel idLabel = new JLabel("id", JLabel.CENTER);
		JLabel startLabel = new JLabel("��ٽð�", JLabel.CENTER);
		JLabel endLabel = new JLabel("��ٽð�", JLabel.CENTER);
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
		String AmPmString[] = {"����","����"};
		String HourString[] = {"01��","02��","03��","04��","05��","06��","07��","08��","09��","10��","11��","12��"};
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
		JButton checker = new JButton("�Է�");
		checker.addActionListener(this);
		checker.setBounds(13*20,9*28,100,40);
		add(checker);
	}
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("�Է�")) {
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
				JOptionPane.showMessageDialog(null,"ID�� �Է����ּ���.","����",JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null,"�Է��� �Ϸ�Ǿ����ϴ�.","�Ϸ�",JOptionPane.INFORMATION_MESSAGE);
				//resultPanel.setText(id + " " + goToWorkHour + " " + endWorkHour);
			}
			
			dispose();
		}
		else {
			if(id.length() != 7)
				JOptionPane.showMessageDialog(null, "id�� 7�ڸ��� �����ּ���.", "Error", JOptionPane.INFORMATION_MESSAGE);
			else if(isOverlap == false)
				JOptionPane.showMessageDialog(null, "���������ʴ� id�Դϴ�.", "Error", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "id 7��° ���ڴ� 1~3���� �Դϴ�.", "Error", JOptionPane.INFORMATION_MESSAGE);
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
		case "01��": if(Ampm.equals("����")) {
			return "01";
		}else {
			return "13";
		}
		case "02��": if(Ampm.equals("����")) {
			return "02";
		}else {
			return "14";
		}
		case "03��": if(Ampm.equals("����")) {
			return "03";
		}else {
			return "15";
		}
		case "04��": if(Ampm.equals("����")) {
			return "04";
		}else {
			return "16";
		}
		case "05��": if(Ampm.equals("����")) {
			return "05";
		}else {
			return "17";
		}
		case "06��": if(Ampm.equals("����")) {
			return "06";
		}else {
			return "18";
		}
		case "07��": if(Ampm.equals("����")) {
			return "07";
		}else {
			return "19";
		}
		case "08��": if(Ampm.equals("����")) {
			return "08";
		}else {
			return "20";
		}
		case "09��": if(Ampm.equals("����")) {
			return "09";
		}else {
			return "21";
		}
		case "10��": if(Ampm.equals("����")) {
			return "10";
		}else {
			return "22";
		}
		case "11��": if(Ampm.equals("����")) {
			return "11";
		}else {
			return "23";
		}
		case "12��": if(Ampm.equals("����")) {
			return "0";
		}else {
			return "12";
		}
		default :
			return "";
		}
	}
}
