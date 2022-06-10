package termProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MemberPanel extends Panel implements MouseListener, Serializable {
	public static final int SMALL_WIDTH = 700;
	public static final int SMALL_HEIGHT = 400;
	
	private int memberIndex;
	private int memberRow;
	
	Font font = new Font("맑은고딕", Font.BOLD, 15);
	
	static MemberStruct[] struct = new MemberStruct[100];
	
	DefaultTableModel memberModel;
	static JTable memberTable;
	
	public MemberPanel() {
		setLayout(new BorderLayout());
		
		String[] header = {"번호", "이름", "급여", "직급", "입사일", "급여 지불 날짜", "연락처"};
		String[][] contents = null;
		
		memberModel = new DefaultTableModel(contents, header){ public boolean isCellEditable(int i, int c){ return false; } };
		memberTable = new JTable(memberModel);
		memberTable.setRowHeight(30);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = memberTable.getColumnModel();
		for(int i=0; i<tcm.getColumnCount(); i++)
			tcm.getColumn(i).setCellRenderer(dtcr);
		memberTable.setFont(font);
		memberTable.addMouseListener(this);
		JScrollPane memberScr = new JScrollPane(memberTable);
		add(memberScr, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		JButton editBtn = new JButton("편집");
		editBtn.setFont(font);
		editBtn.setBackground(Color.WHITE);
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(memberTable.getSelectedRow() == -1)
		               return;
		            else {
		               MemberEditWindow memberEditWindow = new MemberEditWindow();
		               memberEditWindow.setVisible(true);
		            }            
			}
		});
		bottomPanel.add(editBtn);
		JButton addBtn = new JButton("추가");
		addBtn.setFont(font);
		addBtn.setBackground(Color.WHITE);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MemberWindow memberWindow = new MemberWindow();
				memberWindow.setVisible(true);
			}
		});
		bottomPanel.add(addBtn);
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setFont(font);
		deleteBtn.setBackground(Color.WHITE);
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(memberTable.getSelectedRow() == -1)
					return;
				else {
					memberModel.removeRow(memberTable.getSelectedRow());
					struct[memberIndex] = null;
				}
			}
		});
		bottomPanel.add(deleteBtn);
		add(bottomPanel, BorderLayout.SOUTH);
		
	}
	
	private class MemberWindow extends JFrame implements ActionListener, Serializable{
		JComboBox numberBox;
		JTextField nameField;
		JComboBox positionBox;
		JTextField phoneField;
		
		public MemberWindow() {
			super("직원 등록 창");
			setSize(SMALL_WIDTH, SMALL_HEIGHT);
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(null);
			JLabel numberLabel = new JLabel("번호");
			topPanel.add(numberLabel);
			JLabel nameLabel = new JLabel("이름");
			topPanel.add(nameLabel);
			JLabel positionLabel = new JLabel("직급");
			topPanel.add(positionLabel);
			JLabel phoneLabel = new JLabel("연락처");
			topPanel.add(phoneLabel);
			
			String[] numberStr = {"선택안함", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			numberBox = new JComboBox(numberStr);
			topPanel.add(numberBox);
			nameField = new JTextField(10);
			topPanel.add(nameField);
			String[] positionStr = {"선택안함", "매니저", "요리사", "아르바이트"};
			positionBox = new JComboBox(positionStr);
			topPanel.add(positionBox);
			phoneField = new JTextField(10);
			topPanel.add(phoneField);
			
			numberLabel.setBounds(200, 60, 100, 20);
			nameLabel.setBounds(200, 100, 100, 20);
			positionLabel.setBounds(200, 140, 100, 20);
			phoneLabel.setBounds(200, 180, 100, 20);
			
			numberBox.setBounds(300, 60, 150, 20);
			nameField.setBounds(300, 100, 150, 20);
			positionBox.setBounds(300, 140, 150, 20);
			phoneField.setBounds(300, 180, 150, 20);
			add(topPanel, BorderLayout.CENTER);
			
			JPanel choicePanel = new JPanel();
			choicePanel.setLayout(new FlowLayout());
			JButton confirmBtn = new JButton("확인");
			confirmBtn.setFont(font);
			confirmBtn.setBackground(Color.WHITE);
			confirmBtn.addActionListener(this);
			choicePanel.add(confirmBtn);
			JButton deleteBtn = new JButton("취소");
			deleteBtn.setFont(font);
			deleteBtn.setBackground(Color.WHITE);
			deleteBtn.addActionListener(this);
			choicePanel.add(deleteBtn);
			add(choicePanel, BorderLayout.SOUTH);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String actionCmd = e.getActionCommand();
			
			if(actionCmd.equals("확인")) {
				int i;
				for(i=0; i<100; i++)
					if(struct[i] == null) {
						struct[i] = new MemberStruct();
						struct[i].name = nameField.getText();
						String position = (String)positionBox.getSelectedItem();
						if(position.equals("매니저"))
							struct[i].pay = 1800000;
						else if(position.equals("요리사"))
							struct[i].pay = 1500000;
						else
							struct[i].pay = 600000;
						struct[i].phone = phoneField.getText();
						if(Sushi.day == 29 || Sushi.day == 30 || Sushi.day == 31)
							struct[i].payDay = 28;
						else
							struct[i].payDay= Sushi.day;
						
						struct[i].payMonth = Sushi.month;
						struct[i].payYear = Sushi.year;
						break;
					}
				
				String[] stateStr = new String[7];
				stateStr[0] = (String)numberBox.getSelectedItem();
				stateStr[1] = nameField.getText();
				stateStr[2] = String.valueOf(struct[i].pay);
				stateStr[3] = (String)positionBox.getSelectedItem();
				stateStr[4] = Sushi.date;
				stateStr[5] = String.valueOf(struct[i].payDay);
				stateStr[6] = phoneField.getText();
				
				memberModel.addRow(stateStr);
				dispose();
			}else if(actionCmd.equals("취소"))
				dispose();
			else
				System.out.println("Unexpected error in MemberWindow");
		}
	}
	
	private class MemberEditWindow extends JFrame implements ActionListener, Serializable{
		JComboBox numberBox;
	    JTextField nameField;
	    JComboBox positionBox;
	    JTextField phoneField;
	      
	    private int index;
	    private int row;
	      
	    public MemberEditWindow() {
	    	
	        super("직원 편집 창");
	        setSize(SMALL_WIDTH, SMALL_HEIGHT);
	        setLayout(new BorderLayout());
	         
	        JPanel topPanel = new JPanel();
	        topPanel.setLayout(null);
	        JLabel numberLabel = new JLabel("번호");
	        topPanel.add(numberLabel);
	        JLabel nameLabel = new JLabel("이름");
	        topPanel.add(nameLabel);
	        JLabel positionLabel = new JLabel("직급");
	        topPanel.add(positionLabel);
	        JLabel phoneLabel = new JLabel("연락처");
	        topPanel.add(phoneLabel);
	         
	        String[] numberStr = {"선택안함", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	        numberBox = new JComboBox(numberStr);
	        topPanel.add(numberBox);
	        nameField = new JTextField(10);
	        topPanel.add(nameField);
	        String[] positionStr = {"선택안함", "매니저", "요리사", "아르바이트"};
	        positionBox = new JComboBox(positionStr);
	        topPanel.add(positionBox);
	        phoneField = new JTextField(10);
	        topPanel.add(phoneField);
	         
	        numberLabel.setBounds(200, 60, 100, 20);
	        nameLabel.setBounds(200, 100, 100, 20);
	        positionLabel.setBounds(200, 140, 100, 20);
	        phoneLabel.setBounds(200, 180, 100, 20);
	        
	        numberBox.setBounds(300, 60, 150, 20);
	        nameField.setBounds(300, 100, 150, 20);
	        positionBox.setBounds(300, 140, 150, 20);
	        phoneField.setBounds(300, 180, 150, 20);
	        add(topPanel, BorderLayout.CENTER);
	        
	        nameField.setText(struct[memberIndex].name);
	        phoneField.setText(struct[memberIndex].phone);
	         
	        JPanel choicePanel = new JPanel();
	        choicePanel.setLayout(new FlowLayout());
	        JButton confirmBtn = new JButton("확인");
	        confirmBtn.setFont(font);
	        confirmBtn.setBackground(Color.WHITE);
	        confirmBtn.addActionListener(this);
	        choicePanel.add(confirmBtn);
	        JButton deleteBtn = new JButton("취소");
	        deleteBtn.setFont(font);
	        deleteBtn.setBackground(Color.WHITE);
	        deleteBtn.addActionListener(this);
	        choicePanel.add(deleteBtn);
	        add(choicePanel, BorderLayout.SOUTH);
	    }
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // TODO Auto-generated method stub
	        String actionCmd = e.getActionCommand();
	         
	        if(actionCmd.equals("확인")) {
	        	struct[memberIndex].name = nameField.getText();
                String position = (String)positionBox.getSelectedItem();
                if(position.equals("매니저"))
                	struct[memberIndex].pay = 1800000;
                else if(position.equals("요리사"))
                    struct[memberIndex].pay = 1500000;
                else
                    struct[memberIndex].pay = 600000;
                
                struct[memberIndex].phone = phoneField.getText();
              
	            String[] stateStr = new String[4];
	            stateStr[0] = (String)numberBox.getSelectedItem();
	            stateStr[1] = nameField.getText();
	            stateStr[2] = String.valueOf(struct[memberIndex].pay);
	            stateStr[3] = (String)positionBox.getSelectedItem();
	            
	            for(int i=0; i<4; i++)
	            	memberModel.setValueAt(stateStr[i], memberRow, i);
	            memberTable.setValueAt(struct[memberIndex].phone, memberRow, 6);
	            
	            dispose();
	        }else if(actionCmd.equals("취소"))
	        	dispose();
	        else
	        	System.out.println("Unexpected error in MemberWindow");
	    }

	}
	
	public static void paySalary(int today) {
		
		for(int i=0; i<memberTable.getRowCount(); i++) {
			if(today == Integer.parseInt((String)memberTable.getValueAt(i, 5)))
				Sushi.balance -= Integer.parseInt((String)memberTable.getValueAt(i, 2));
		}
		
		for(int i=0; i<100; i++)
			if(struct[i] != null)
				if(struct[i].payDay == Sushi.day && struct[i].payMonth == Sushi.month && struct[i].payYear == Sushi.year)
					Sushi.balance += struct[i].pay;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JTable table = (JTable)e.getSource();
		memberRow = table.getSelectedRow();
		String key = (String)table.getValueAt(memberRow, 1);
		
		memberIndex = 0;
		for(int i=0; i<100; i++)
			if(struct[i] != null && key.equals(struct[i].name)) {
				memberIndex = i;
				break;
			}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

}

class MemberStruct implements Serializable{
	public String name;
	public int pay;
	public String phone;
	public int payDay;
	public int payMonth;
	public int payYear;
	
}
