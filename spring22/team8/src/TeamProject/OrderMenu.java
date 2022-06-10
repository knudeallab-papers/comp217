package TeamProject;

import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class OrderMenu extends JFrame implements ActionListener{
	
	protected static ArrayList<Item> ItemList;
	protected JTextField BarcodeField;
	protected JTable SearchTable;
	protected DefaultTableModel model1;
	protected JTable SellTable;
	protected JTextField EAField;
	protected JPanel AlertPanel;
	protected JTextField ResultPrice;
	
	Management theM = new Management();
	
	public OrderMenu(Management M) {
		super("��ǰ ����");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setLocationRelativeTo(null);
		setLayout(null);	
		
		theM = M;
		
		JLabel TitleLabel = new JLabel("��ǰ ����");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("���� ���", Font.BOLD, 35));
		add(TitleLabel);
		TitleLabel.setBounds(140, 10, 500, 70);
		
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
		
		String header[] = {"���ڵ�", "��ǰ��", "����"};
		String contents[][] = {{"","",""}};
		
		model1 = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		SellTable = new JTable(model1);
		
		JScrollPane SellScroll = new JScrollPane(SellTable);
		add(SellScroll);
		SellScroll.setBounds(20, 150, 730, 200);
		
		JButton AddButton = new JButton("�߰�");
		add(AddButton);
		AddButton.setBounds(20, 355, 70, 30);
		AddButton.addActionListener(this);
		
		JButton RemoveButton = new JButton("����");
		add(RemoveButton);
		RemoveButton.setBounds(100, 355, 70, 30);
		RemoveButton.addActionListener(this);
		
		JLabel ResultLabelTotal = new JLabel("��");
		add(ResultLabelTotal);
		ResultLabelTotal.setBounds(375,360,25,25);
		ResultLabelTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		JLabel ResultLabelWon = new JLabel("��");
		add(ResultLabelWon);
		ResultLabelWon.setBounds(525,360,25,25);
		ResultLabelWon.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		ResultPrice = new JTextField();
		add(ResultPrice);
		ResultPrice.setBounds(400, 355, 120, 30);
		ResultPrice.setEditable(false);
		
		JButton OrderButton = new JButton("����");
		add(OrderButton);
		OrderButton.setBounds(585, 354, 150, 30);
		OrderButton.addActionListener(this);
		
		JButton BackButton = new JButton("�ڷ�");
		add(BackButton);
		BackButton.setBounds(585, 400, 150, 30);
		BackButton.addActionListener(this);
		
		
	}
		
		
	public void restore() {
		int Row = model1.getRowCount();
		for(int i = Row - 1; i >= 0; i--) {	
		model1.removeRow(i);
		}
	}
	
	private String[] getCurrentData() {
		int theRow = SellTable.getSelectedRow();
		String Data[] = new String[4]; 
		if(theRow != -1) {
		Data[0] = (String)SellTable.getValueAt(theRow, 0);
		Data[1] = (String)SellTable.getValueAt(theRow, 1);
		Data[2] = (String)SellTable.getValueAt(theRow, 2);
		}else {
			Data[0] = "Not Selected";
		}
		return Data;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		ArrayList<ArrayList<String>> List = new ArrayList<ArrayList<String>>();
		if(ActionCmd.equals("�ż� ��ǰ")) {
			List = theM.OrderList("Fresh");
			restore();
			for(ArrayList<String> Data : List) {
				String[] K = new String[3];
				K[0] = Data.get(0);
				K[1] = Data.get(1);
				K[2] = Data.get(2);
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("�õ� ��ǰ")) {
			List = theM.OrderList("Freezed");
			restore();
			for(ArrayList<String> Data : List) {
				String[] K = new String[3];
				K[0] = Data.get(0);
				K[1] = Data.get(1);
				K[2] = Data.get(2);
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("�Ϲ� ��ǰ")) {
			List = theM.OrderList("Normal");
			restore();
			for(ArrayList<String> Data : List) {
				String[] K = new String[3];
				K[0] = Data.get(0);
				K[1] = Data.get(1);
				K[2] = Data.get(2);
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("�ַ�")) {
			List = theM.OrderList("Drinks");
			restore();
			for(ArrayList<String> Data : List) {
				String[] K = new String[3];
				K[0] = Data.get(0);
				K[1] = Data.get(1);
				K[2] = Data.get(2);
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("����ǰ")) {
			List = theM.OrderList("Grocery");
			restore();
			for(ArrayList<String> Data : List) {
				String[] K = new String[3];
				K[0] = Data.get(0);
				K[1] = Data.get(1);
				K[2] = Data.get(2);
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("��Ÿ")) {
			List = theM.OrderList("Others");
			restore();
			for(ArrayList<String> Data : List) {
				String[] K = new String[3];
				K[0] = Data.get(0);
				K[1] = Data.get(1);
				K[2] = Data.get(2);
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("��ü")) {
			List = theM.OrderList("All");
			restore();
			for(ArrayList<String> Data : List) {
				String[] K = new String[3];
				K[0] = Data.get(0);
				K[1] = Data.get(1);
				K[2] = Data.get(2);
				model1.addRow(K);
			}
		}
		else if(ActionCmd.equals("����")) {
			String[] Data = new String[4];
			Data = getCurrentData();
			
			if(Data[0].equals("Not Selected")) {
				JOptionPane.showMessageDialog(null,"������ ���õ��� �ʾҽ��ϴ�.","����",JOptionPane.ERROR_MESSAGE);
			}else if(theM.TempOrderFindIndex(Data) == -1) {
				JOptionPane.showMessageDialog(null,"���ָ�Ͽ� �� ������ �����ϴ�.","����",JOptionPane.ERROR_MESSAGE);
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
		else if(ActionCmd.equals("����")) {
			OrderedList O = new OrderedList(theM);
			O.setVisible(true);
		}
		else if(ActionCmd.equals("�ڷ�")) {
			theM.TempOrderList = new ArrayList<>();
			dispose();
		}
		else if(ActionCmd.equals("�߰�")) {
			String[] Data = new String[4];
			Data = getCurrentData();
			if(Data[0].equals("Not Selected")) {
				JOptionPane.showMessageDialog(null,"������ ���õ��� �ʾҽ��ϴ�.","����",JOptionPane.ERROR_MESSAGE);
			}else {
				String theNum = JOptionPane.showInputDialog(null,"������ �Է��ϼ���","�Է�",JOptionPane.QUESTION_MESSAGE);
				if(theNum == null) {}
				else if(theNum.equals("")) {
					JOptionPane.showMessageDialog(null,"������ �Էµ��� �ʾҽ��ϴ�.","����",JOptionPane.ERROR_MESSAGE);
				}else if(!isInt(theNum) ){
					JOptionPane.showMessageDialog(null, "�ùٸ� ������ �Է����ּ���","����",JOptionPane.ERROR_MESSAGE);
				}else if(stringToInt(theNum) <= 0) {
					JOptionPane.showMessageDialog(null, "�ùٸ� ������ �Է����ּ���","����",JOptionPane.ERROR_MESSAGE);
				}else {
				Data[3] = theNum;
				theM.AddTempOrderItem(Data);
				String Tp = theM.getTempPrice();
				ResultPrice.setText(Tp);
				}
			}
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
		}
		
	  public static boolean isInt(String s) {
		    try {
		        Integer.parseInt(s);
		        return true;
		    } catch (NumberFormatException e) {
		        return false;
		    }
		  }
	  
	  private static int stringToInt(String str) {
			int res = 0;
			res = Integer.parseInt(str.trim());
			return res;
		}
}
