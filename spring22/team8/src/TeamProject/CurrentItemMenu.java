package TeamProject;

import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class CurrentItemMenu extends JFrame implements ActionListener{

	
	protected static ArrayList<Item> ItemList;
	protected JTextField BarcodeField;
	protected JTable SearchTable;
	protected DefaultTableModel model1;
	protected JTable SellTable;
	protected JTextField EAField;
	protected JPanel AlertPanel;
	protected JTextField ResultPrice;
	
	Management theM = new Management();
	
	public CurrentItemMenu(Management M) {
		super("��� ��Ȳ");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setLocationRelativeTo(null);
		setLayout(null);	
		
		JLabel TitleLabel = new JLabel("��� ��Ȳ");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("���� ����", Font.BOLD, 35));
		add(TitleLabel);
		TitleLabel.setBounds(140, 10, 500, 70);
		
		theM = M;
		
		JButton AllButton = new JButton("��ü");
		add(AllButton);
		AllButton.setBounds(20,100,95,40);
		AllButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		AllButton.addActionListener(this);
		
		JButton FreshButton = new JButton("�ż� ��ǰ");
		add(FreshButton);
		FreshButton.setBounds(125,100,95,40);
		FreshButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		FreshButton.addActionListener(this);
		
		JButton FreezedButton = new JButton("�õ� ��ǰ");
		add(FreezedButton);
		FreezedButton.setBounds(230,100,95,40);
		FreezedButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		FreezedButton.addActionListener(this);
		
		JButton NormalButton = new JButton("�Ϲ� ��ǰ");
		add(NormalButton);
		NormalButton.setBounds(335,100,95,40);
		NormalButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		NormalButton.addActionListener(this);
		
		JButton DrinksButton = new JButton("�ַ�");
		add(DrinksButton);
		DrinksButton.setBounds(440,100,95,40);
		DrinksButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		DrinksButton.addActionListener(this);
		
		JButton GroceryButton = new JButton("����ǰ");
		add(GroceryButton);
		GroceryButton.setBounds(545,100,95,40);
		GroceryButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		GroceryButton.addActionListener(this);
		
		JButton OtherButton = new JButton("��Ÿ");
		add(OtherButton);
		OtherButton.setBounds(650,100,95,40);
		OtherButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		OtherButton.addActionListener(this);
		
		String header[] = {"���ڵ�", "��ǰ��", "����","���� ����"};
		String contents[][] = {{"","","",""}};
		
		model1 = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		SellTable = new JTable(model1);
		
		JScrollPane SellScroll = new JScrollPane(SellTable);
		add(SellScroll);
		SellScroll.setBounds(20, 150, 730, 200);
		
		JButton ConfirmButton = new JButton("Ȯ��");
		add(ConfirmButton);
		ConfirmButton.setBounds(585, 354, 150, 30);
		ConfirmButton.addActionListener(this);
	}
	
	public void restore() {
		int Row = model1.getRowCount();
		for(int i = Row - 1; i >= 0; i--) {	
		model1.removeRow(i);
		}
	}
	
	private String[] getCurrentData() {
		int theRow = SellTable.getSelectedRow();
		String Data[] = new String[5]; 
		if(theRow != -1) {
		Data[0] = (String)SellTable.getValueAt(theRow, 0);
		Data[1] = (String)SellTable.getValueAt(theRow, 1);
		Data[2] = (String)SellTable.getValueAt(theRow, 2);
		Data[3] = (String)SellTable.getValueAt(theRow, 3);
		}else {
			Data[0] = "Not Selected";
		}
		return Data;
	}
	
	private static String intToString(int num) {
		String res = "";
		res = Integer.toString(num);
		return res;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		ArrayList<Food> FList = new ArrayList<Food>();
		ArrayList<NonFood> NFList = new ArrayList<NonFood>();
		if(ActionCmd.equals("�ż� ��ǰ")) {
			FList = theM.FoodKinds("Fresh");
			restore();
			for(Food Data : FList) {
				String[] K = new String[4];
				K[0] = Data.getID();
				K[1] = Data.getName();
				K[2] = intToString(Data.getNum());
				K[3] = Data.getExpiration().toString();
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("�õ� ��ǰ")) {
			FList = theM.FoodKinds("Freezed");
			restore();
			for(Food Data : FList) {
				String[] K = new String[4];
				K[0] = Data.getID();
				K[1] = Data.getName();
				K[2] = intToString(Data.getNum());
				K[3] = Data.getExpiration().toString();
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("�Ϲ� ��ǰ")) {
			FList = theM.FoodKinds("Normal");
			restore();
			for(Food Data : FList) {
				String[] K = new String[4];
				K[0] = Data.getID();
				K[1] = Data.getName();
				K[2] = intToString(Data.getNum());
				K[3] = Data.getExpiration().toString();
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("�ַ�")) {
			FList = theM.FoodKinds("Drinks");
			restore();
			for(Food Data : FList) {
				String[] K = new String[4];
				K[0] = Data.getID();
				K[1] = Data.getName();
				K[2] = intToString(Data.getNum());
				K[3] = Data.getExpiration().toString();
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("����ǰ")) {
			NFList = theM.NonFoodKinds("Grocery");
			restore();
			for(NonFood Data : NFList) {
				String[] K = new String[4];
				K[0] = Data.getID();
				K[1] = Data.getName();
				K[2] = intToString(Data.getNum());
				K[3] = "9999-01-01";
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("��Ÿ")) {
			NFList = theM.NonFoodKinds("Others");
			restore();
			for(NonFood Data : NFList) {
				String[] K = new String[4];
				K[0] = Data.getID();
				K[1] = Data.getName();
				K[2] = intToString(Data.getNum());
				K[3] = "9999-01-01";
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("��ü")) {
			FList = theM.FoodKinds("All");
			NFList = theM.NonFoodKinds("All");
			restore();
			for(Food Data : FList) {
				String[] K = new String[4];
				K[0] = Data.getID();
				K[1] = Data.getName();
				K[2] = intToString(Data.getNum());
				K[3] = Data.getExpiration().toString();
				model1.addRow(K);
			}
			for(NonFood Data : NFList) {
				String[] K = new String[4];
				K[0] = Data.getID();
				K[1] = Data.getName();
				K[2] = intToString(Data.getNum());
				K[3] = "9999-01-01";
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("����")) {
			String[] Data = new String[4];
			Data = getCurrentData();
			
			if(Data[0].equals("Not Selected")) {
				JOptionPane.showMessageDialog(null,"������ ���õ��� �ʾҽ��ϴ�.");
			}else {
				int theNum = JOptionPane.showConfirmDialog(null,"���� �����Ͻðڽ��ϱ�?","Ȯ��",JOptionPane.OK_CANCEL_OPTION);
				if(theNum == 0) {
					theM.DelTempOrderItem(Data);
					String Tp = theM.getTempPrice();
					ResultPrice.setText(Tp);	
					JOptionPane.showMessageDialog(null,"������ �Ϸ�Ǿ����ϴ�.","�Ϸ�",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		else if(ActionCmd.equals("Ȯ��")) {
			theM.save();
			dispose();
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
		}
}