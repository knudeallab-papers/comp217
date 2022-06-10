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

public class MenuPanel extends JPanel implements MouseListener, Serializable {
	public static final int SMALL_WIDTH = 700;
	public static final int SMALL_HEIGHT = 400;
	
	Font font = new Font("�������", Font.BOLD, 15);
	
	DefaultTableModel menuModel;
	JTable menuTable;
	private String[] inputStr = new String[1];
	
	static MenuStruct[] struct = new MenuStruct[100];
	
	JTextField nameF;
	JTextField priceF;
	JTextField costF;
	JTextArea ingreA;
	
	private int index;
	
	public MenuPanel() {
		setLayout(new BorderLayout());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(600, 1000));
		leftPanel.setLayout(new BorderLayout());
		String[] header = {"�޴�"};
		String[][] contents = null;
		
		menuModel = new DefaultTableModel(contents, header);
		menuTable = new JTable(menuModel);
		menuTable.setRowHeight(30);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = menuTable.getColumnModel();
		for(int i=0; i<tcm.getColumnCount(); i++)
			tcm.getColumn(i).setCellRenderer(dtcr);
		menuTable.setFont(font);
		JScrollPane menuScr = new JScrollPane(menuTable);
		leftPanel.add(menuScr, BorderLayout.CENTER);
		
		menuTable.addMouseListener(this);
		
		JButton addBtn = new JButton("�߰�");
		addBtn.setFont(font);
		addBtn.setBackground(Color.WHITE);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuWindow menuWindow = new MenuWindow();
				menuWindow.setVisible(true);
			}
		});
		leftPanel.add(addBtn, BorderLayout.SOUTH);
		
		add(leftPanel, BorderLayout.WEST);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		JLabel nameL = new JLabel("�̸�");
		nameL.setFont(font);
		contentPanel.add(nameL);
		nameF = new JTextField(10);
		nameF.setEditable(false);
		nameF.setBackground(Color.WHITE);
		contentPanel.add(nameF);
		JLabel priceL = new JLabel("����");
		priceL.setFont(font);
		contentPanel.add(priceL);
		priceF = new JTextField(10);
		priceF.setEditable(false);
		priceF.setBackground(Color.WHITE);
		contentPanel.add(priceF);
		JLabel costL = new JLabel("����ܰ�");
		costL.setFont(font);
		contentPanel.add(costL);
		costF = new JTextField(10);
		costF.setEditable(false);
		costF.setBackground(Color.WHITE);
		contentPanel.add(costF);
		JLabel ingreL = new JLabel("���� ���");
		ingreL.setFont(font);
		contentPanel.add(ingreL);
		ingreA = new JTextArea(3, 10);
		ingreA.setEditable(false);
		contentPanel.add(ingreA);
		
		nameL.setBounds(230, 170, 100, 20);
		priceL.setBounds(230, 270, 100, 20);
		costL.setBounds(230, 370, 100, 20);
		ingreL.setBounds(230, 470, 100, 20);
		nameF.setBounds(370, 170, 300, 20);
		priceF.setBounds(370, 270, 300, 20);
		costF.setBounds(370, 370, 300, 20);
		ingreA.setBounds(370, 470, 300, 80);
		rightPanel.add(contentPanel, BorderLayout.CENTER);
		
		JPanel editPanel = new JPanel();
		editPanel.setLayout(new FlowLayout());
		JButton editBtn = new JButton("����");
		editBtn.setFont(font);
		editBtn.setBackground(Color.WHITE);
		editBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(menuTable.getSelectedRow() == -1)
		               return;
		            else {
		               MenuEditWindow menuEditWindow = new MenuEditWindow();
		               menuEditWindow.setVisible(true);
		            }
			}
		});
		editPanel.add(editBtn);
		JButton deleteBtn = new JButton("����");
		deleteBtn.setFont(font);
		deleteBtn.setBackground(Color.WHITE);
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(menuTable.getSelectedRow() == -1)
					return;
				else {
					TablePanel.menuBox.removeItemAt(menuTable.getSelectedRow() + 1);
					menuModel.removeRow(menuTable.getSelectedRow());
					struct[index] = null;
				}
			}
		});
		editPanel.add(deleteBtn);
		rightPanel.add(editPanel, BorderLayout.SOUTH);
		
		add(rightPanel, BorderLayout.CENTER);
	}
	
	/////////////// MenuWindow ///////////////
	private class MenuWindow extends JFrame implements ActionListener, Serializable{
		private JTextField nameField;
		private JTextField priceField;
		private JTextField costField;
		private JTextArea ingreArea;
		
		public MenuWindow() {
			super("�޴� ��� â");
			setSize(SMALL_WIDTH, SMALL_HEIGHT);
			setLayout(new BorderLayout());
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(null);
			JLabel nameLabel = new JLabel("�̸�");
			topPanel.add(nameLabel);
			JLabel priceLabel = new JLabel("����");
			topPanel.add(priceLabel);
			JLabel costLabel = new JLabel("���� �ܰ�");
			topPanel.add(costLabel);
			JLabel ingreLabel = new JLabel("���� ���");
			topPanel.add(ingreLabel);
			
			nameField = new JTextField(10);
			topPanel.add(nameField);
			priceField = new JTextField(10);
			topPanel.add(priceField);
			costField = new JTextField(10);
			topPanel.add(costField);
			ingreArea = new JTextArea(3, 30);
			topPanel.add(ingreArea);
			
			nameLabel.setBounds(150, 50, 100, 20);
			priceLabel.setBounds(150, 100, 100, 20);
			costLabel.setBounds(150, 150, 100, 20);
			ingreLabel.setBounds(150, 200, 100, 20);
			
			nameField.setBounds(300, 50, 200, 20);
			priceField.setBounds(300, 100, 200, 20);
			costField.setBounds(300, 150, 200, 20);
			ingreArea.setBounds(300, 200, 200, 50);
			add(topPanel, BorderLayout.CENTER);
			
			JPanel choicePanel = new JPanel();
			choicePanel.setLayout(new FlowLayout());
			JButton confirmBtn = new JButton("Ȯ��");
			confirmBtn.setFont(font);
			confirmBtn.setBackground(Color.WHITE);
			confirmBtn.addActionListener(this);
			choicePanel.add(confirmBtn);
			JButton cancelBtn = new JButton("���");
			cancelBtn.setFont(font);
			cancelBtn.setBackground(Color.WHITE);
			cancelBtn.addActionListener(this);
			choicePanel.add(cancelBtn);
			add(choicePanel, BorderLayout.SOUTH);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String actionCmd = e.getActionCommand();
			
			if(actionCmd.equals("Ȯ��")) {
				for(int i=0; i<100; i++)
					if(struct[i] == null) {
						struct[i] = new MenuStruct();
						struct[i].name = nameField.getText();
						struct[i].price = Integer.parseInt(priceField.getText());
						struct[i].cost = costField.getText();
						struct[i].ingredient = ingreArea.getText();
						break;
					}
				
				inputStr[0] = nameField.getText();
				menuModel.addRow(inputStr);
				
				Object boxObj = nameField.getText();
				TablePanel.menuBox.addItem(boxObj);
				dispose();
			}else if(actionCmd.equals("���"))
				dispose();
			else
				System.err.println("Unexpected error in MenuWindow");
		}
	}
	//////////////////////////////////////////
	
	///////////// MenuEditWindow /////////////
	private class MenuEditWindow extends JFrame implements ActionListener, Serializable{
		private JTextField nameField;
	    private JTextField priceField;
	    private JTextField costField;
	    private JTextArea ingreArea;
	      
	    public MenuEditWindow() {
	    	super("�޴� ���� â");
	        setSize(SMALL_WIDTH, SMALL_HEIGHT);
	        setLayout(new BorderLayout());
	         
	        JPanel topPanel = new JPanel();
	        topPanel.setLayout(null);
	        JLabel nameLabel = new JLabel("�̸�");
	        topPanel.add(nameLabel);
	        JLabel priceLabel = new JLabel("����");
	        topPanel.add(priceLabel);
	        JLabel costLabel = new JLabel("���� �ܰ�");
	        topPanel.add(costLabel);
	        JLabel ingreLabel = new JLabel("���� ���");
	        topPanel.add(ingreLabel);
	          
	        nameField = new JTextField(10);
	        nameField.setEditable(false);
	        nameField.setBackground(Color.WHITE);
	        topPanel.add(nameField);
	        priceField = new JTextField(10);
	        topPanel.add(priceField);
	        costField = new JTextField(10);
	        topPanel.add(costField);
	        ingreArea = new JTextArea(3, 30);
	        topPanel.add(ingreArea);
	          
	        nameLabel.setBounds(150, 50, 100, 20);
	        priceLabel.setBounds(150, 100, 100, 20);
	        costLabel.setBounds(150, 150, 100, 20);
	        ingreLabel.setBounds(150, 200, 100, 20);
	          
	        nameField.setBounds(300, 50, 200, 20);
	        priceField.setBounds(300, 100, 200, 20);
	        costField.setBounds(300, 150, 200, 20);
	        ingreArea.setBounds(300, 200, 200, 50);
	        add(topPanel, BorderLayout.CENTER);
	        
	        nameField.setText(struct[index].name);
	        priceField.setText(String.valueOf(struct[index].price));
	        costField.setText(struct[index].cost);
	        ingreArea.setText(struct[index].ingredient);
	        
	        JPanel choicePanel = new JPanel();
	        choicePanel.setLayout(new FlowLayout());
	        JButton confirmBtn = new JButton("Ȯ��");
	        confirmBtn.setFont(font);
	        confirmBtn.setBackground(Color.WHITE);
	        confirmBtn.addActionListener(this);
	        choicePanel.add(confirmBtn);
	        JButton cancelBtn = new JButton("���");
	        cancelBtn.setFont(font);
	        cancelBtn.setBackground(Color.WHITE);
	        cancelBtn.addActionListener(this);
	        choicePanel.add(cancelBtn);
	        add(choicePanel, BorderLayout.SOUTH);
	    }
	      
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	// TODO Auto-generated method stub
	        String actionCmd = e.getActionCommand();
	         
	        if(actionCmd.equals("Ȯ��")) {
                struct[index].price = Integer.parseInt(priceField.getText());
                struct[index].cost = costField.getText();
                struct[index].ingredient = ingreArea.getText();
	            
	            priceF.setText(String.valueOf(struct[index].price));
	            costF.setText(struct[index].cost);
	            ingreA.setText(struct[index].ingredient);
	            
	            dispose();
	         }else if(actionCmd.equals("���"))
	            dispose();
	         else
	            System.err.println("Unexpected error in MenuWindow");
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JTable table = (JTable)e.getSource();
		int row = table.getSelectedRow();
		String key = (String)table.getValueAt(row, 0);
		
		index = 0;
		for(int i=0; i<100; i++)
			if(struct[i] != null && key.equals(struct[i].name)) {
				index = i;
				break;
			}

		nameF.setText(struct[index].name);
		priceF.setText(String.valueOf(struct[index].price));
		costF.setText(struct[index].cost);
		ingreA.setText(struct[index].ingredient);
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

class MenuStruct implements Serializable{
	public String name; //�̸�
	public int price; //����
	public String cost; //����ܰ�
	public String ingredient; //���� ���
}
