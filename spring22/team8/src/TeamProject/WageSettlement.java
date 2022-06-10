package TeamProject;

import java.util.*;

import java.io.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class WageSettlement extends JFrame implements ActionListener{
	Manager manager;
	TableModel model;
	JTable table;
	JScrollPane scrollPane;
	String[] header = {"id", "이름", "임금", "퇴직금"};
	String[][] data;
	public WageSettlement() {
		super("임금 정산");
		setSize(16*40, 9*40);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		manager = new Manager();
		//label
		JLabel wageLabel = new JLabel("직원 목록",JLabel.CENTER);
		wageLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(wageLabel, BorderLayout.NORTH);
		//table
		data = convertToList(manager.getEmployeeList());
		model = new DefaultTableModel(data, header);
		table = new JTable();
		table.setModel(model);
		scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		//employee
		JPanel managePanel = new JPanel();
		managePanel.setLayout(new GridLayout(2, 1));
		JPanel hirePanel = new JPanel();
		JButton hireBtn = new JButton("고용");
		hireBtn.addActionListener(this);
		hireBtn.setSize(80, 80);
		hirePanel.add(hireBtn);
		JPanel firePanel = new JPanel();
		JButton fireBtn = new JButton("해고");
		fireBtn.addActionListener(this);
		fireBtn.setSize(80, 80);
		firePanel.add(fireBtn);
		managePanel.add(hirePanel);
		managePanel.add(firePanel);
		add(managePanel, BorderLayout.EAST);
		
		//bottom
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		JButton update = new JButton("갱신");
		update.addActionListener(this);
		bottomPanel.add(update);
		JButton reset  = new JButton("지급 완료");
		reset.addActionListener(this);
		bottomPanel.add(reset);
		JButton check = new JButton("확인");
		check.addActionListener(this);
		bottomPanel.add(check);
		add(bottomPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if(actionCmd.equals("갱신")) {
			manager.updateHistory();
			updateTable();
			manager.resetHistory();
		}
		else if(actionCmd.equals("지급 완료")) {
			int theNum = JOptionPane.showConfirmDialog(null,"임금을 지급하시겠습니까?","확인",JOptionPane.OK_CANCEL_OPTION);
			if(theNum == 0) {
			manager.resetEmployeeList();
			updateTable();
			JOptionPane.showMessageDialog(null, "지급이 완료되었습니다.","완료",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(actionCmd.equals("확인")) {
			dispose();
		}
		else if(actionCmd.equals("고용")) {
			HireWindow hireWindow = new HireWindow();
			hireWindow.setVisible(true);
		}
		else if(actionCmd.equals("해고")) {
			FireWindow fireWindow = new FireWindow();
			fireWindow.setVisible(true);
		}
	}
	private String[][] convertToList(ArrayList<Employee> employeeList){
		String[][] temp = new String[employeeList.size()][4];
		int i = 0;
		for(Employee x:employeeList) {
			temp[i][0] = x.getId();
			temp[i][1] = x.getName();
			temp[i][2] = Double.toString(x.totalFare());
			if(x instanceof Salary)
				temp[i][3] = Integer.toString((int)((Salary) x).getSeverencePay());
			else
				temp[i][3] = "none";
			i++;
		}
		return temp;
	}
	private void updateTable() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setNumRows(0);
		data = convertToList(manager.getEmployeeList());
		model = new DefaultTableModel(data, header);
		table.setModel(model);
	}
	private class HireWindow extends JFrame implements ActionListener{
		JTextField idPanel;
		JTextField namePanel;
		JTextField wagePanel;
		public HireWindow() {
			super("고용");
			setSize(16*20, 9*10);
			setLayout(new BorderLayout());
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JPanel insertPanel = new JPanel();
			insertPanel.setLayout(new GridLayout(2, 4));
			idPanel = new JTextField();
			namePanel = new JTextField();
			wagePanel = new JTextField();
			JLabel idLabel = new JLabel("ID", JLabel.CENTER);
			JLabel nameLabel = new JLabel("이름", JLabel.CENTER);
			JLabel wageLable = new JLabel("시급", JLabel.CENTER);
			JLabel sendLabel = new JLabel("", JLabel.CENTER);
			JButton sendBtn = new JButton("확인");
			sendBtn.addActionListener(this);
			insertPanel.add(idLabel);
			insertPanel.add(nameLabel);
			insertPanel.add(wageLable);
			insertPanel.add(sendLabel);
			insertPanel.add(idPanel);
			insertPanel.add(namePanel);
			insertPanel.add(wagePanel);
			insertPanel.add(sendBtn);
			add(insertPanel, BorderLayout.CENTER);
			setVisible(true);
		}
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			if(actionCmd.equals("확인")) {
				BufferedReader br;
				String s;
				ArrayList<String> employees = new ArrayList<String>();
				boolean isOverlap = false;
				try {
					String path = GoToWork.class.getResource("").getPath();
					path = java.net.URLDecoder.decode(path,"UTF-8");
					File file = new File(path + "../../src/docs/employees.txt");
					br = new BufferedReader(new FileReader(file));
					while((s = br.readLine()) != null) {
						StringTokenizer st = new StringTokenizer(s);
						String id = st.nextToken();
						employees.add(id);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String id = idPanel.getText();
				if(id.length() == 7) {
					for(String someoneId: employees) {
						if(someoneId.substring(0, 6).equals(id.substring(0, 6)))
							isOverlap = true;
					}
				}
				if(isOverlap == false && id.length() == 7 && 1 <= id.charAt(6) - '0' && id.charAt(6) - '0' <= 3) {
					manager.hireWorker(idPanel.getText(), namePanel.getText(), Double.parseDouble(wagePanel.getText()));
					updateTable();
					JOptionPane.showMessageDialog(null, "고용이 완료되었습니다.","완료",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else {
					if(id.length() != 7)
						JOptionPane.showMessageDialog(null, "id는 7자리로 정해주세요.", "Error", JOptionPane.INFORMATION_MESSAGE);
					else if(isOverlap == true)
						JOptionPane.showMessageDialog(null, "중복된 id입니다.", "Error", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "id 7번째 숫자는 1~3사이 입니다.", "Error", JOptionPane.INFORMATION_MESSAGE);
					idPanel.setText("");
				}
				
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	private class FireWindow extends JFrame implements ActionListener{
		JScrollPane sp;
		DefaultListModel model;
		JList nameList;
		String s;
		public FireWindow() {
			super("해고");
			setSize(300, 500);
			setLayout(new BorderLayout());
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			model = new DefaultListModel();
			BufferedReader br;
			nameList = new JList(new DefaultListModel());
			model = (DefaultListModel) nameList.getModel();
			try {
				String path = GoToWork.class.getResource("").getPath();
				path = java.net.URLDecoder.decode(path,"UTF-8");
				File file = new File(path + "../../src/docs/employees.txt");
				br = new BufferedReader(new FileReader(file));
				while((s = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(s);
					String id = st.nextToken();
					String name = st.nextToken();
					model.addElement(id + " " + name);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			sp = new JScrollPane(nameList);
			add(sp, BorderLayout.CENTER);
			
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new FlowLayout());
			JButton fire = new JButton("해고");
			fire.addActionListener(this);
			btnPanel.add(fire);
			JButton check = new JButton("확인");
			check.addActionListener(this);
			btnPanel.add(check);
			add(btnPanel, BorderLayout.SOUTH);
			setVisible(true);
		}
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			if(actionCmd.equals("해고")) {
				String s = (String)nameList.getSelectedValue();
				if(s == null) {
					JOptionPane.showMessageDialog(null, "직원이 선택되지 않았습니다.","오류",JOptionPane.ERROR_MESSAGE);
				}else {
					int theNum = JOptionPane.showConfirmDialog(null,"정말 해고하시겠습니까?","확인",JOptionPane.OK_CANCEL_OPTION);
					if(theNum == 0) {
						StringTokenizer st = new StringTokenizer(s);
						if(st.hasMoreTokens()) {
							String id = st.nextToken();
							manager.fireEmployee(id);
						}
						model.remove(nameList.getSelectedIndex());
						updateTable();
						JOptionPane.showMessageDialog(null, "해고가 완료되었습니다.","완료",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			else if(actionCmd.equals("확인")) {
				dispose();
			}
		}
	}
	
}
