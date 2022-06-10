package TeamProject;

import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class OrderedList extends JFrame implements ActionListener{
	
	JTextField NumTextField;
	String numText;
	String[] theData = new String[4];
	Management theM = new Management();
	
	protected static ArrayList<Item> ItemList;
	protected JTextField BarcodeField;
	protected JTable SearchTable;
	protected DefaultTableModel model1;
	protected JTable SellTable;
	protected JTextField EAField;
	protected JPanel AlertPanel;
	protected JTextField ResultPrice;
	
	public OrderedList(Management M) {
		super("발주 목록");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setLocationRelativeTo(null);
		setLayout(null);	
		
		JLabel TitleLabel = new JLabel("발주 목록");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		add(TitleLabel);
		TitleLabel.setBounds(140, 10, 500, 70);
		
		String header[] = {"바코드", "상품명", "가격","개수"};
		String contents[][] = {{"","","",""}};
		model1 = new DefaultTableModel(contents, header){
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		SellTable = new JTable(model1);
		model1.removeRow(0);
		
		for(ArrayList<String> Data : M.TempOrderList) {
			String[] K = new String[4];
			K[0] = Data.get(0);
			K[1] = Data.get(1);
			K[2] = Data.get(2);
			K[3] = Data.get(3);
			model1.addRow(K);
		}
		
		JScrollPane SellScroll = new JScrollPane(SellTable);
		add(SellScroll);
		SellScroll.setBounds(20, 80, 730, 270);
	
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
		
		String Tp = M.getTempPrice();
		ResultPrice.setText(Tp);
		
		
		JButton ConfirmButton = new JButton("확인");
		add(ConfirmButton);
		ConfirmButton.setBounds(585, 354, 150, 30);
		ConfirmButton.addActionListener(this);
		
		JButton BackButton = new JButton("취소");
		add(BackButton);
		BackButton.setBounds(585, 400, 150, 30);
		BackButton.addActionListener(this);
		
		theM = M;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("확인")) {
			theM.TempOrderToReal();
			theM.TempOrderList = new ArrayList<>();
			JOptionPane.showMessageDialog(null,"발주가 완료되었습니다.","완료",JOptionPane.INFORMATION_MESSAGE);
			theM.save();
			dispose();
		}
		else if(ActionCmd.equals("취소")) {
			dispose();
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
		}
}
