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

public class GuestPanel extends JPanel  implements MouseListener, Serializable{
	public static final int SMALL_WIDTH = 700;
	public static final int SMALL_HEIGHT = 400;
	
	private int guestIndex;
	private int guestRow;
	
	Font font = new Font("맑은고딕", Font.BOLD, 15);
	
	static GuestStruct[] struct = new GuestStruct[100];
	
	DefaultTableModel guestModel;
	static JTable guestTable;
	
	public GuestPanel() {
		setLayout(new BorderLayout());
		
		String[] header = {"번호", "등급", "이름", "마일리지", "연락처"};
		String[][] contents = null;
		
		guestModel = new DefaultTableModel(contents, header){ public boolean isCellEditable(int i, int c){ return false; } };
		guestTable = new JTable(guestModel);
		guestTable.setRowHeight(30);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = guestTable.getColumnModel();
		for(int i=0; i<tcm.getColumnCount(); i++)
			tcm.getColumn(i).setCellRenderer(dtcr);
		guestTable.setFont(font);
		guestTable.addMouseListener(this);
		JScrollPane guestScr = new JScrollPane(guestTable);
		add(guestScr, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		JButton editBtn = new JButton("편집");
		editBtn.setFont(font);
		editBtn.setBackground(Color.WHITE);
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(guestTable.getSelectedRow() == -1)
					return;
		        else {
		        	GuestEditWindow guestEditWindow = new GuestEditWindow();
		            guestEditWindow.setVisible(true);
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
				GuestWindow guestWindow = new GuestWindow();
				guestWindow.setVisible(true);
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
				if(guestTable.getSelectedRow() == -1)
					return;
				else {
					TablePanel.guestBox.removeItemAt(guestTable.getSelectedRow() + 1);
					guestModel.removeRow(guestTable.getSelectedRow());
					struct[guestIndex] = null;
					
				}
			}
		});
		bottomPanel.add(deleteBtn);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private class GuestWindow extends JFrame implements ActionListener, Serializable{
		JTextField numberField;
		JTextField nameField;
		JTextField phoneField;
		
		public GuestWindow() {
			super("회원 등록 창");
			setSize(SMALL_WIDTH, SMALL_HEIGHT);
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(null);
			JLabel numberLabel = new JLabel("번호");
			topPanel.add(numberLabel);
			JLabel nameLabel = new JLabel("이름");
			topPanel.add(nameLabel);
			JLabel phoneLabel = new JLabel("연락처");
			topPanel.add(phoneLabel);
			
			numberField = new JTextField(10);
			topPanel.add(numberField);
			nameField = new JTextField(10);
			topPanel.add(nameField);
			phoneField = new JTextField(10);
			topPanel.add(phoneField);
			
			numberLabel.setBounds(220, 70, 100, 20);
			nameLabel.setBounds(220, 120, 100, 20);
			phoneLabel.setBounds(220, 170, 100, 20);
			
			numberField.setBounds(320, 70, 150, 20);
			nameField.setBounds(320, 120, 150, 20);
			phoneField.setBounds(320, 170, 150, 20);
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
				for(int i=0; i<100; i++)
					if(struct[i] == null) {
						struct[i] = new GuestStruct();
						struct[i].number = Integer.parseInt(numberField.getText());
						struct[i].grade = "일반";
						struct[i].name = nameField.getText();
						struct[i].mileage = 0;
						struct[i].phone = phoneField.getText();
						break;
					}
				
				String[] stateStr = new String[5];
				stateStr[0] = numberField.getText();
				stateStr[1] = "일반";
				stateStr[2] = nameField.getText();
				stateStr[3] = "0"; //0으로 수정되어야 함
				stateStr[4] = phoneField.getText();
				
				guestModel.addRow(stateStr);
				
				Object boxObj = nameField.getText();
				TablePanel.guestBox.addItem(boxObj);
				dispose();
			}else if(actionCmd.equals("취소"))
				dispose();
			else
				System.out.println("Unexpected error in MemberWindow");
		}
	}
	
	private class GuestEditWindow extends JFrame implements ActionListener, Serializable{
		JTextField numberField;
	    JTextField nameField;
	    JTextField phoneField;
	      
	    public GuestEditWindow() {
	    	super("회원 편집 창");
	        setSize(SMALL_WIDTH, SMALL_HEIGHT);
	        setLayout(new BorderLayout());
	         
	        JPanel topPanel = new JPanel();
	        topPanel.setLayout(null);
	        JLabel numberLabel = new JLabel("번호");
	        topPanel.add(numberLabel);
	        JLabel nameLabel = new JLabel("이름");
	        topPanel.add(nameLabel);
	        JLabel phoneLabel = new JLabel("연락처");
	        topPanel.add(phoneLabel);
	        
	        numberField = new JTextField(10);
	        topPanel.add(numberField);
	        nameField = new JTextField(10);
	        nameField.setEditable(false);
	        nameField.setBackground(Color.WHITE);
	        topPanel.add(nameField);
	        phoneField = new JTextField(10);
	        topPanel.add(phoneField);
	         
	        numberLabel.setBounds(220, 70, 100, 20);
	        nameLabel.setBounds(220, 120, 100, 20);
	        phoneLabel.setBounds(220, 170, 100, 20);
	        
	        numberField.setBounds(320, 70, 150, 20);
	        nameField.setBounds(320, 120, 150, 20);
	        phoneField.setBounds(320, 170, 150, 20);
	        add(topPanel, BorderLayout.CENTER);
	        
	        numberField.setText(String.valueOf(struct[guestIndex].number));
	        nameField.setText(struct[guestIndex].name);
	        phoneField.setText(struct[guestIndex].phone);
	         
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
	        	 struct[guestIndex].number = Integer.parseInt(numberField.getText());
	             struct[guestIndex].phone = phoneField.getText();
	            
	             guestTable.setValueAt(struct[guestIndex].number, guestRow, 0);
	             guestTable.setValueAt(struct[guestIndex].phone, guestRow, 4);
	            
	             dispose();
	         }else if(actionCmd.equals("취소"))
	             dispose();
	         else
	             System.out.println("Unexpected error in MemberWindow");
	      }
	}
	
	public static void initializeMileage() {
		for(int i=0; i<100; i++)
			if(struct[i] != null) {
				struct[i].grade = "일반";
				struct[i].mileage = 0;
			}
		
		for(int i=0; i<guestTable.getRowCount(); i++) {
			guestTable.setValueAt("일반", i, 1);
			guestTable.setValueAt(0, i, 3);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JTable table = (JTable)e.getSource();
		guestRow = table.getSelectedRow();
		String key = (String)table.getValueAt(guestRow, 2);
		
		guestIndex = 0;
		for(int i=0; i<100; i++)
			if(struct[i] != null && key.equals(struct[i].name)) {
				guestIndex = i;
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

class GuestStruct implements Serializable{
	public int number;
	public String grade;
	public String name;
	public int mileage;
	public String phone;
}

