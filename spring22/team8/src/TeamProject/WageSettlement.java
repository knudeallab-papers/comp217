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
	String[] header = {"id", "�̸�", "�ӱ�", "������"};
	String[][] data;
	public WageSettlement() {
		super("�ӱ� ����");
		setSize(16*40, 9*40);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		manager = new Manager();
		//label
		JLabel wageLabel = new JLabel("���� ���",JLabel.CENTER);
		wageLabel.setFont(new Font("���� ���", Font.BOLD, 20));
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
		JButton hireBtn = new JButton("���");
		hireBtn.addActionListener(this);
		hireBtn.setSize(80, 80);
		hirePanel.add(hireBtn);
		JPanel firePanel = new JPanel();
		JButton fireBtn = new JButton("�ذ�");
		fireBtn.addActionListener(this);
		fireBtn.setSize(80, 80);
		firePanel.add(fireBtn);
		managePanel.add(hirePanel);
		managePanel.add(firePanel);
		add(managePanel, BorderLayout.EAST);
		
		//bottom
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		JButton update = new JButton("����");
		update.addActionListener(this);
		bottomPanel.add(update);
		JButton reset  = new JButton("���� �Ϸ�");
		reset.addActionListener(this);
		bottomPanel.add(reset);
		JButton check = new JButton("Ȯ��");
		check.addActionListener(this);
		bottomPanel.add(check);
		add(bottomPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if(actionCmd.equals("����")) {
			manager.updateHistory();
			updateTable();
			manager.resetHistory();
		}
		else if(actionCmd.equals("���� �Ϸ�")) {
			int theNum = JOptionPane.showConfirmDialog(null,"�ӱ��� �����Ͻðڽ��ϱ�?","Ȯ��",JOptionPane.OK_CANCEL_OPTION);
			if(theNum == 0) {
			manager.resetEmployeeList();
			updateTable();
			JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.","�Ϸ�",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(actionCmd.equals("Ȯ��")) {
			dispose();
		}
		else if(actionCmd.equals("���")) {
			HireWindow hireWindow = new HireWindow();
			hireWindow.setVisible(true);
		}
		else if(actionCmd.equals("�ذ�")) {
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
			super("���");
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
			JLabel nameLabel = new JLabel("�̸�", JLabel.CENTER);
			JLabel wageLable = new JLabel("�ñ�", JLabel.CENTER);
			JLabel sendLabel = new JLabel("", JLabel.CENTER);
			JButton sendBtn = new JButton("Ȯ��");
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
			if(actionCmd.equals("Ȯ��")) {
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
					JOptionPane.showMessageDialog(null, "����� �Ϸ�Ǿ����ϴ�.","�Ϸ�",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else {
					if(id.length() != 7)
						JOptionPane.showMessageDialog(null, "id�� 7�ڸ��� �����ּ���.", "Error", JOptionPane.INFORMATION_MESSAGE);
					else if(isOverlap == true)
						JOptionPane.showMessageDialog(null, "�ߺ��� id�Դϴ�.", "Error", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "id 7��° ���ڴ� 1~3���� �Դϴ�.", "Error", JOptionPane.INFORMATION_MESSAGE);
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
			super("�ذ�");
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
			JButton fire = new JButton("�ذ�");
			fire.addActionListener(this);
			btnPanel.add(fire);
			JButton check = new JButton("Ȯ��");
			check.addActionListener(this);
			btnPanel.add(check);
			add(btnPanel, BorderLayout.SOUTH);
			setVisible(true);
		}
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			if(actionCmd.equals("�ذ�")) {
				String s = (String)nameList.getSelectedValue();
				if(s == null) {
					JOptionPane.showMessageDialog(null, "������ ���õ��� �ʾҽ��ϴ�.","����",JOptionPane.ERROR_MESSAGE);
				}else {
					int theNum = JOptionPane.showConfirmDialog(null,"���� �ذ��Ͻðڽ��ϱ�?","Ȯ��",JOptionPane.OK_CANCEL_OPTION);
					if(theNum == 0) {
						StringTokenizer st = new StringTokenizer(s);
						if(st.hasMoreTokens()) {
							String id = st.nextToken();
							manager.fireEmployee(id);
						}
						model.remove(nameList.getSelectedIndex());
						updateTable();
						JOptionPane.showMessageDialog(null, "�ذ� �Ϸ�Ǿ����ϴ�.","�Ϸ�",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			else if(actionCmd.equals("Ȯ��")) {
				dispose();
			}
		}
	}
	
}
