package TeamProject;

import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class CVSmanager extends JFrame implements ActionListener {
	
	protected static ArrayList<Item> ItemList;
	protected static ArrayList<Item> OrderItemList;
	protected static double TotalCash = 0.0;
	protected JTextField BarcodeField;
	protected JTable SearchTable;
	protected DefaultTableModel model1;
	protected JTable SellTable;
	protected DefaultTableModel model2;
	protected JLabel AlertLabel1;
	protected JLabel AlertLabel2;
	protected JTextField EAField;
	protected JPanel AlertPanel;
	protected JTextField ResultPrice;
	protected JComboBox<String> Combo;
	
	protected Management M;
	protected PromotionManager PM;
	protected SellManager SM;
	
	public CVSmanager() {
		super("������ ���� �ý��� ����ȭ��");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLocationRelativeTo(null);
		setLayout(null);
		
		JLabel BarcodeLabel = new JLabel("���ڵ带 �Է��ϼ���");
		BarcodeLabel.setHorizontalAlignment(JLabel.CENTER);
		BarcodeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(BarcodeLabel);
		BarcodeLabel.setBounds(15, 0, 400, 20);
		
		
		BarcodeField = new JTextField();
		add(BarcodeField);
		BarcodeField.setBounds(20, 20, 400, 30);
		
		JButton SearchButton = new JButton("�˻�");
		SearchButton.addActionListener(this);
		add(SearchButton);
		SearchButton.setBounds(430, 20, 70, 30);
		
		String header1[] = {"���ڵ�", "��ǰ��", "����"};
		String contents1[][] = {{"","",""}};
		model1 = new DefaultTableModel(contents1, header1);
		model1 = new DefaultTableModel(contents1, header1) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		model1.setRowCount(0);
		SearchTable = new JTable(model1);
		JScrollPane SearchScroll = new JScrollPane(SearchTable);
		add(SearchScroll);
		SearchScroll.setBounds(20, 50, 400, 50);
		
		
		JLabel EALabel = new JLabel("�ǸŰ���");
		EALabel.setHorizontalAlignment(JLabel.CENTER);
		EALabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		add(EALabel);
		EALabel.setBounds(435, 50, 60, 50);
		
		EAField = new JTextField("");
		add(EAField);
		EAField.setBounds(500, 60, 50, 30);
		
		JLabel ArrowLabel = new JLabel("��");
		ArrowLabel.setHorizontalAlignment(JLabel.CENTER);
		ArrowLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		add(ArrowLabel);
		ArrowLabel.setBounds(540, 50, 50, 50);
		
		
		JButton AddButton = new JButton("�߰�");
		AddButton.addActionListener(this);
		add(AddButton);
		AddButton.setBounds(580, 60, 70, 30);
		
		
		String header2[] = {"���ڵ�", "��ǰ��", "�ǸŰ���", "�� ����"};
		String contents2[][] = {{"","","",""}};
		model2 = new DefaultTableModel(contents2, header2);
		model2 = new DefaultTableModel(contents2, header2) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		model2.setRowCount(0);
		SellTable = new JTable(model2);
		JScrollPane SellScroll = new JScrollPane(SellTable);
		add(SellScroll);
		SellScroll.setBounds(20, 150, 530, 200);
		
		
		AlertLabel1 = new JLabel("");
		AlertLabel1.setHorizontalAlignment(JLabel.CENTER);
		AlertLabel1.setOpaque(true);
		AlertLabel1.setBackground(Color.WHITE);
		AlertLabel1.setFont(new Font("SansSerif", Font.BOLD, 32));
		add(AlertLabel1);
		AlertLabel1.setBounds(565, 150, 200, 65);
		
		
		AlertLabel2 = new JLabel("");
		AlertLabel2.setHorizontalAlignment(JLabel.CENTER);
		AlertLabel2.setOpaque(true);
		AlertLabel2.setBackground(Color.WHITE);
		AlertLabel2.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(AlertLabel2);
		AlertLabel2.setBounds(565, 205, 200, 65);
		
		
		JButton RemoveButton = new JButton("����");
		RemoveButton.addActionListener(this);
		add(RemoveButton);
		RemoveButton.setBounds(20, 355, 70, 30);
		
		JButton RemoveAllButton = new JButton("��� ����");
		RemoveAllButton.addActionListener(this);
		add(RemoveAllButton);
		RemoveAllButton.setBounds(100, 355, 90, 30);
		
		
		JLabel TotalLabel = new JLabel("��");
		TotalLabel.setHorizontalAlignment(JLabel.CENTER);
		TotalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(TotalLabel);
		TotalLabel.setBounds(385, 355, 30, 30);
		
		
		ResultPrice = new JTextField();
		ResultPrice.setEditable(false);
		add(ResultPrice);
		ResultPrice.setBounds(420, 355, 130, 30);
		
		JLabel WonLabel = new JLabel("��");
		WonLabel.setHorizontalAlignment(JLabel.CENTER);
		WonLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(WonLabel);
		WonLabel.setBounds(550, 355, 30, 30);
		
		
		JLabel PaymentLabel = new JLabel("�������� ����");
		PaymentLabel.setHorizontalAlignment(JLabel.CENTER);
		PaymentLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		add(PaymentLabel);
		PaymentLabel.setBounds(580, 295, 150, 30);
		
	
		String PaymentListString[] = {"ī��", "����"};
		Combo = new JComboBox<String>(PaymentListString);
		add(Combo);
		Combo.setBounds(585, 328, 150, 20);
		
		JButton SellButton = new JButton("�Ǹ�");
		SellButton.addActionListener(this);
		add(SellButton);
		SellButton.setBounds(585, 354, 150, 30);
		
		
		JButton StockButton = new JButton("������");
		StockButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		StockButton.addActionListener(this);
		add(StockButton);
		StockButton.setBounds(20, 450, 120, 70);
		
		JButton WorkButton = new JButton("����ٵ��");
		WorkButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		WorkButton.addActionListener(this);
		add(WorkButton);
		WorkButton.setBounds(155, 450, 120, 70);
		
		JButton CashCheckButton = new JButton("��������");
		CashCheckButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		CashCheckButton.addActionListener(this);
		add(CashCheckButton);
		CashCheckButton.setBounds(290, 450, 120, 70);
		
		JButton WageButton = new JButton("�ӱ�����");
		WageButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		WageButton.addActionListener(this);
		add(WageButton);
		WageButton.setBounds(425, 450, 120, 70);
		
		M = new Management();
		M.Order();
		M.load();
		M.ExpLoad();
		OrderItemList = new ArrayList<Item>();
		ItemList = new ArrayList<Item>();
		OrderItemList.addAll(M.OrderFoodList);
		OrderItemList.addAll(M.OrderNonFoodList);
		ItemList.addAll(M.FoodList);
		ItemList.addAll(M.NonFoodList);
		
		PM = new PromotionManager();
		PM.load();
		
		
	}
	
	
	
	

	public static void main(String[] args) {	
		
		CVSmanager CVSgui = new CVSmanager();
		CVSgui.setVisible(true);
		
	}
	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("�˻�")) {
			boolean isExist = false;
			String InputBarcode = BarcodeField.getText();
			for (Item A : OrderItemList) 
				if (InputBarcode.equals(A.id)){
					String[] temp = {A.id, A.name, Double.toString(A.price)};
					model1.setRowCount(0);
					model1.addRow(temp);
					isExist = true;
					int PromotionCode = PM.searchPromotion(A.id);
					switch (PromotionCode) {
					case 0 : AlertLabel1.setText("");
					         AlertLabel1.setBackground(Color.WHITE);
					         AlertLabel2.setText("");
					         AlertLabel2.setBackground(Color.WHITE);
					         break;
					case 1 : AlertLabel1.setText("1+1");
							 AlertLabel1.setBackground(Color.ORANGE);
							 AlertLabel2.setText("����ǰ �Դϴ�");
							 AlertLabel2.setBackground(Color.ORANGE);
							 break;
					case 2 : AlertLabel1.setText("2+1");
							 AlertLabel1.setBackground(Color.CYAN);
							 AlertLabel2.setText("����ǰ �Դϴ�");
							 AlertLabel2.setBackground(Color.CYAN); 
							 break;
					default : break;		 
					}
					break;
				}
					
			if (isExist == false)
				JOptionPane.showMessageDialog(null, "��ϵ��� ���� ���ڵ��Դϴ�", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		else if(ActionCmd.equals("�߰�")) {
			String SellEA = EAField.getText();
			String Barcode = (String)SearchTable.getValueAt(0, 0);
			int limitEA = 0;
			for (Item A : ItemList)
				if (A.id.equals(Barcode)) limitEA += A.num;  // �ִ� �ǸŰ��� ���
			
			if (IntCheck(SellEA) == false) {
				JOptionPane.showMessageDialog(null, "�߸��� �ǸŰ��� �Դϴ�. ���ڸ� �Է��ϼ���", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (Integer.parseInt(SellEA) == 0){
				JOptionPane.showMessageDialog(null, "0���� �Ǹ��� �� �����ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (Integer.parseInt(SellEA) > limitEA){
				JOptionPane.showMessageDialog(null, "��� �����մϴ�.  ������ : " + limitEA + "��", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				int PromotionCode = PM.searchPromotion(Barcode);
				double TotalPrice = 0.0;
				int Quotient, Reminder;
				
				if(PromotionCode == 1) { 
					Quotient = Integer.parseInt(SellEA) / 2;
					Reminder = Integer.parseInt(SellEA) % 2;
					TotalPrice = Double.parseDouble((String)SearchTable.getValueAt(0, 2)) * (Quotient)
							      +  Double.parseDouble((String)SearchTable.getValueAt(0, 2)) * (Reminder);
				}
				else if (PromotionCode == 2) {
					Quotient = Integer.parseInt(SellEA) / 3;
					Reminder = Integer.parseInt(SellEA) % 3;
					TotalPrice = Double.parseDouble((String)SearchTable.getValueAt(0, 2)) * (Quotient*2)
							      +  Double.parseDouble((String)SearchTable.getValueAt(0, 2)) * (Reminder);
				}
				else TotalPrice = Double.parseDouble((String)SearchTable.getValueAt(0, 2)) * Integer.parseInt(SellEA);
					
				String[] temp = {Barcode, (String)SearchTable.getValueAt(0, 1), Integer.toString(Integer.parseInt(SellEA)), Double.toString(TotalPrice)};
				
				// �̹� JTable�� �����ϴ� ��ǰ�̸� ������ ���ϰ� ��翩�ο� ���� ������ �ٽ� ����ؼ� ǥ��
				boolean alreadyExist = false;
				for (int i = 0; i < SellTable.getRowCount(); i++)
					if (Barcode.equals((String)SellTable.getValueAt(i, 0))) {
						alreadyExist = true;
						int totalSellEA = Integer.parseInt(temp[2]) + Integer.parseInt((String)SellTable.getValueAt(i, 2));
						
						PromotionCode = PM.searchPromotion(Barcode);
						TotalPrice = 0.0;
						if(PromotionCode == 1) { 
							Quotient = (totalSellEA) / 2;
							Reminder = (totalSellEA) % 2;
							TotalPrice = Double.parseDouble((String)SearchTable.getValueAt(0, 2)) * (Quotient)
									      +  Double.parseDouble((String)SearchTable.getValueAt(0, 2)) * (Reminder);
						}
						else if (PromotionCode == 2) {
							Quotient = (totalSellEA) / 3;
							Reminder = (totalSellEA) % 3;
							TotalPrice = Double.parseDouble((String)SearchTable.getValueAt(0, 2)) * (Quotient*2)
									      +  Double.parseDouble((String)SearchTable.getValueAt(0, 2)) * (Reminder);
						}
						else TotalPrice = Double.parseDouble((String)SearchTable.getValueAt(0, 2)) * (totalSellEA);
								
						SellTable.setValueAt(Integer.toString(totalSellEA), i, 2);
						SellTable.setValueAt(Double.toString(TotalPrice), i, 3);
					}
				
				// ���� JTable�� �������� �ʴٸ� ���ο� ���� �߰�
				if(alreadyExist == false)
					model2.addRow(temp);
				
				// �߰��� �� ���� �� ������ ǥ��
				double Result = 0.0;
				for (int i = 0; i < SellTable.getRowCount(); i++)
					Result+= Double.parseDouble((String)SellTable.getValueAt(i, 3));
				ResultPrice.setText(Double.toString(Result));	
			}
			
				
		}
		else if(ActionCmd.equals("����")) {
			int row = SellTable.getSelectedRow();
			if (row != -1) {
				int select = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "�Ǹ� ���� ����", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (select == 0) {
					model2.removeRow(row);
					double Result = 0.0;
					for (int i = 0; i < SellTable.getRowCount(); i++)
						Result+= Double.parseDouble((String)SellTable.getValueAt(i, 3));
					ResultPrice.setText(Double.toString(Result));
				}
			}
		}
		
		
		else if(ActionCmd.equals("��� ����")) {
			int select = JOptionPane.showConfirmDialog(null, "���� ��� �����Ͻðڽ��ϱ�?", "��� �Ǹ� ���� ����", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (select == 0) {
				model2.setRowCount(0);
				ResultPrice.setText("0.0");
			}
		}
		
		
		else if(ActionCmd.equals("�Ǹ�")) {
			 // �Ǹ�â�� ���� ��ǰ�� �ߺ����� ��� �߰��ϸ� ��� ���� �� �����Ƿ� �ǸŽÿ� �ٽ� �ѹ� ��� üũ
			boolean sellable = true;
			for (int i = 0; i < SellTable.getRowCount(); i++) {
				String Barcode = (String)SellTable.getValueAt(i, 0);
				String SellEA = (String)SellTable.getValueAt(i, 2);
				int limitEA = 0;
				for (Item A : ItemList) {
				if (A.id.equals(Barcode)) limitEA += A.num; 
				}
				if (Integer.parseInt(SellEA) > limitEA){
				sellable = false;
				JOptionPane.showMessageDialog(null, (String)SellTable.getValueAt(i, 1) + "�� ��� �����մϴ�.  ������ : " + limitEA + "��", "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
			
			// ��� ��ǰ�� ��� �����ִٸ� SellManager Ŭ������ �̿��Ͽ� ItemList���� �����ϰ� ItemList.txt�� �����
			if (sellable == true) {
				SM = new SellManager(ItemList, SellTable);
				SM.SellAndSave();
				JOptionPane.showMessageDialog(null, "���������� �ǸŵǾ����ϴ�.", "�Ǹ� �Ϸ�", JOptionPane.PLAIN_MESSAGE);
				if (Combo.getSelectedItem().toString().equals("����"))        // ���� ������ static ���� �� ������Ű��
					TotalCash += Double.parseDouble(ResultPrice.getText());
				model2.setRowCount(0);
				ResultPrice.setText("0.0");
			}
			
			
			M.load();
		}
		
		
		else if(ActionCmd.equals("������")) {
			ItemSelectMenu a = new ItemSelectMenu(M);
			a.setVisible(true);
		}
		
		
		else if(ActionCmd.equals("����ٵ��")) {
			GoToWork go = new GoToWork();
			go.setVisible(true);
		}
		
		
		else if(ActionCmd.equals("��������")) {
			CashCheck CashCheckgui = new CashCheck();
			CashCheckgui.setVisible(true);
		}
		
		
		else if(ActionCmd.equals("�ӱ�����")) {
			WageSettlement wageSettle = new WageSettlement();
			wageSettle.setVisible(true);
		}
		
		
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
		}
		

	
	
	public boolean IntCheck(String inputString) {
		for (int i = 0; i < inputString.length(); i++)
			if (!( 48 <= inputString.charAt(i) 
			      && inputString.charAt(i) <= 57) ) return false;
					
		return true;
	}
	
	

	
		
}


