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
		super("물품 발주");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setLocationRelativeTo(null);
		setLayout(null);	
		
		theM = M;
		
		JLabel TitleLabel = new JLabel("물품 발주");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		add(TitleLabel);
		TitleLabel.setBounds(140, 10, 500, 70);
		
		JButton AllButton = new JButton("전체");
		add(AllButton);
		AllButton.setBounds(20,100,95,40);
		AllButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		AllButton.addActionListener(this);
		
		JButton FreshButton = new JButton("신선 식품");
		add(FreshButton);
		FreshButton.setBounds(125,100,95,40);
		FreshButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		FreshButton.addActionListener(this);
		
		JButton FreezedButton = new JButton("냉동 식품");
		add(FreezedButton);
		FreezedButton.setBounds(230,100,95,40);
		FreezedButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		FreezedButton.addActionListener(this);
		
		JButton NormalButton = new JButton("일반 식품");
		add(NormalButton);
		NormalButton.setBounds(335,100,95,40);
		NormalButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		NormalButton.addActionListener(this);
		
		JButton DrinksButton = new JButton("주류");
		add(DrinksButton);
		DrinksButton.setBounds(440,100,95,40);
		DrinksButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		DrinksButton.addActionListener(this);
		
		JButton GroceryButton = new JButton("생필품");
		add(GroceryButton);
		GroceryButton.setBounds(545,100,95,40);
		GroceryButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		GroceryButton.addActionListener(this);
		
		JButton OtherButton = new JButton("기타");
		add(OtherButton);
		OtherButton.setBounds(650,100,95,40);
		OtherButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		OtherButton.addActionListener(this);
		
		String header[] = {"바코드", "상품명", "가격"};
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
		
		JButton AddButton = new JButton("추가");
		add(AddButton);
		AddButton.setBounds(20, 355, 70, 30);
		AddButton.addActionListener(this);
		
		JButton RemoveButton = new JButton("삭제");
		add(RemoveButton);
		RemoveButton.setBounds(100, 355, 70, 30);
		RemoveButton.addActionListener(this);
		
		JLabel ResultLabelTotal = new JLabel("총");
		add(ResultLabelTotal);
		ResultLabelTotal.setBounds(375,360,25,25);
		ResultLabelTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		JLabel ResultLabelWon = new JLabel("원");
		add(ResultLabelWon);
		ResultLabelWon.setBounds(525,360,25,25);
		ResultLabelWon.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		ResultPrice = new JTextField();
		add(ResultPrice);
		ResultPrice.setBounds(400, 355, 120, 30);
		ResultPrice.setEditable(false);
		
		JButton OrderButton = new JButton("발주");
		add(OrderButton);
		OrderButton.setBounds(585, 354, 150, 30);
		OrderButton.addActionListener(this);
		
		JButton BackButton = new JButton("뒤로");
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
		if(ActionCmd.equals("신선 식품")) {
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
		else if(ActionCmd.equals("냉동 식품")) {
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
		else if(ActionCmd.equals("일반 식품")) {
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
		else if(ActionCmd.equals("주류")) {
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
		else if(ActionCmd.equals("생필품")) {
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
		else if(ActionCmd.equals("기타")) {
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
		else if(ActionCmd.equals("전체")) {
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
		else if(ActionCmd.equals("삭제")) {
			String[] Data = new String[4];
			Data = getCurrentData();
			
			if(Data[0].equals("Not Selected")) {
				JOptionPane.showMessageDialog(null,"물류가 선택되지 않았습니다.","오류",JOptionPane.ERROR_MESSAGE);
			}else if(theM.TempOrderFindIndex(Data) == -1) {
				JOptionPane.showMessageDialog(null,"발주목록에 이 물류가 없습니다.","오류",JOptionPane.ERROR_MESSAGE);
			}else {
				int theNum = JOptionPane.showConfirmDialog(null,"정말 삭제하시겠습니까?","확인",JOptionPane.OK_CANCEL_OPTION);
				if(theNum == 0) {
					theM.DelTempOrderItem(Data);
					String Tp = theM.getTempPrice();
					ResultPrice.setText(Tp);	
					JOptionPane.showMessageDialog(null,"삭제가 완료되었습니다.","완료",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		else if(ActionCmd.equals("발주")) {
			OrderedList O = new OrderedList(theM);
			O.setVisible(true);
		}
		else if(ActionCmd.equals("뒤로")) {
			theM.TempOrderList = new ArrayList<>();
			dispose();
		}
		else if(ActionCmd.equals("추가")) {
			String[] Data = new String[4];
			Data = getCurrentData();
			if(Data[0].equals("Not Selected")) {
				JOptionPane.showMessageDialog(null,"물류가 선택되지 않았습니다.","오류",JOptionPane.ERROR_MESSAGE);
			}else {
				String theNum = JOptionPane.showInputDialog(null,"개수를 입력하세요","입력",JOptionPane.QUESTION_MESSAGE);
				if(theNum == null) {}
				else if(theNum.equals("")) {
					JOptionPane.showMessageDialog(null,"개수가 입력되지 않았습니다.","오류",JOptionPane.ERROR_MESSAGE);
				}else if(!isInt(theNum) ){
					JOptionPane.showMessageDialog(null, "올바른 개수를 입력해주세요","오류",JOptionPane.ERROR_MESSAGE);
				}else if(stringToInt(theNum) <= 0) {
					JOptionPane.showMessageDialog(null, "올바른 개수를 입력해주세요","오류",JOptionPane.ERROR_MESSAGE);
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
