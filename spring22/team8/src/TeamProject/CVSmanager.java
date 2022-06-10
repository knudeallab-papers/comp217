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
		super("편의점 관리 시스템 메인화면");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLocationRelativeTo(null);
		setLayout(null);
		
		JLabel BarcodeLabel = new JLabel("바코드를 입력하세요");
		BarcodeLabel.setHorizontalAlignment(JLabel.CENTER);
		BarcodeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(BarcodeLabel);
		BarcodeLabel.setBounds(15, 0, 400, 20);
		
		
		BarcodeField = new JTextField();
		add(BarcodeField);
		BarcodeField.setBounds(20, 20, 400, 30);
		
		JButton SearchButton = new JButton("검색");
		SearchButton.addActionListener(this);
		add(SearchButton);
		SearchButton.setBounds(430, 20, 70, 30);
		
		String header1[] = {"바코드", "상품명", "가격"};
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
		
		
		JLabel EALabel = new JLabel("판매개수");
		EALabel.setHorizontalAlignment(JLabel.CENTER);
		EALabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		add(EALabel);
		EALabel.setBounds(435, 50, 60, 50);
		
		EAField = new JTextField("");
		add(EAField);
		EAField.setBounds(500, 60, 50, 30);
		
		JLabel ArrowLabel = new JLabel("→");
		ArrowLabel.setHorizontalAlignment(JLabel.CENTER);
		ArrowLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		add(ArrowLabel);
		ArrowLabel.setBounds(540, 50, 50, 50);
		
		
		JButton AddButton = new JButton("추가");
		AddButton.addActionListener(this);
		add(AddButton);
		AddButton.setBounds(580, 60, 70, 30);
		
		
		String header2[] = {"바코드", "상품명", "판매개수", "총 가격"};
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
		
		
		JButton RemoveButton = new JButton("삭제");
		RemoveButton.addActionListener(this);
		add(RemoveButton);
		RemoveButton.setBounds(20, 355, 70, 30);
		
		JButton RemoveAllButton = new JButton("모두 삭제");
		RemoveAllButton.addActionListener(this);
		add(RemoveAllButton);
		RemoveAllButton.setBounds(100, 355, 90, 30);
		
		
		JLabel TotalLabel = new JLabel("총");
		TotalLabel.setHorizontalAlignment(JLabel.CENTER);
		TotalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(TotalLabel);
		TotalLabel.setBounds(385, 355, 30, 30);
		
		
		ResultPrice = new JTextField();
		ResultPrice.setEditable(false);
		add(ResultPrice);
		ResultPrice.setBounds(420, 355, 130, 30);
		
		JLabel WonLabel = new JLabel("원");
		WonLabel.setHorizontalAlignment(JLabel.CENTER);
		WonLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(WonLabel);
		WonLabel.setBounds(550, 355, 30, 30);
		
		
		JLabel PaymentLabel = new JLabel("결제수단 선택");
		PaymentLabel.setHorizontalAlignment(JLabel.CENTER);
		PaymentLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		add(PaymentLabel);
		PaymentLabel.setBounds(580, 295, 150, 30);
		
	
		String PaymentListString[] = {"카드", "현금"};
		Combo = new JComboBox<String>(PaymentListString);
		add(Combo);
		Combo.setBounds(585, 328, 150, 20);
		
		JButton SellButton = new JButton("판매");
		SellButton.addActionListener(this);
		add(SellButton);
		SellButton.setBounds(585, 354, 150, 30);
		
		
		JButton StockButton = new JButton("재고관리");
		StockButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		StockButton.addActionListener(this);
		add(StockButton);
		StockButton.setBounds(20, 450, 120, 70);
		
		JButton WorkButton = new JButton("출퇴근등록");
		WorkButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		WorkButton.addActionListener(this);
		add(WorkButton);
		WorkButton.setBounds(155, 450, 120, 70);
		
		JButton CashCheckButton = new JButton("시재점검");
		CashCheckButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		CashCheckButton.addActionListener(this);
		add(CashCheckButton);
		CashCheckButton.setBounds(290, 450, 120, 70);
		
		JButton WageButton = new JButton("임금정산");
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
		if(ActionCmd.equals("검색")) {
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
							 AlertLabel2.setText("행사상품 입니다");
							 AlertLabel2.setBackground(Color.ORANGE);
							 break;
					case 2 : AlertLabel1.setText("2+1");
							 AlertLabel1.setBackground(Color.CYAN);
							 AlertLabel2.setText("행사상품 입니다");
							 AlertLabel2.setBackground(Color.CYAN); 
							 break;
					default : break;		 
					}
					break;
				}
					
			if (isExist == false)
				JOptionPane.showMessageDialog(null, "등록되지 않은 바코드입니다", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		else if(ActionCmd.equals("추가")) {
			String SellEA = EAField.getText();
			String Barcode = (String)SearchTable.getValueAt(0, 0);
			int limitEA = 0;
			for (Item A : ItemList)
				if (A.id.equals(Barcode)) limitEA += A.num;  // 최대 판매개수 계산
			
			if (IntCheck(SellEA) == false) {
				JOptionPane.showMessageDialog(null, "잘못된 판매개수 입니다. 숫자를 입력하세요", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (Integer.parseInt(SellEA) == 0){
				JOptionPane.showMessageDialog(null, "0개는 판매할 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (Integer.parseInt(SellEA) > limitEA){
				JOptionPane.showMessageDialog(null, "재고가 부족합니다.  재고수량 : " + limitEA + "개", "Error", JOptionPane.ERROR_MESSAGE);
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
				
				// 이미 JTable에 존재하는 물품이면 개수를 더하고 행사여부에 따라 가격을 다시 계산해서 표시
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
				
				// 아직 JTable에 존재하지 않다면 새로운 행을 추가
				if(alreadyExist == false)
					model2.addRow(temp);
				
				// 추가할 때 마다 총 가격을 표시
				double Result = 0.0;
				for (int i = 0; i < SellTable.getRowCount(); i++)
					Result+= Double.parseDouble((String)SellTable.getValueAt(i, 3));
				ResultPrice.setText(Double.toString(Result));	
			}
			
				
		}
		else if(ActionCmd.equals("삭제")) {
			int row = SellTable.getSelectedRow();
			if (row != -1) {
				int select = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "판매 내역 삭제", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (select == 0) {
					model2.removeRow(row);
					double Result = 0.0;
					for (int i = 0; i < SellTable.getRowCount(); i++)
						Result+= Double.parseDouble((String)SellTable.getValueAt(i, 3));
					ResultPrice.setText(Double.toString(Result));
				}
			}
		}
		
		
		else if(ActionCmd.equals("모두 삭제")) {
			int select = JOptionPane.showConfirmDialog(null, "정말 모두 삭제하시겠습니까?", "모든 판매 내역 삭제", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (select == 0) {
				model2.setRowCount(0);
				ResultPrice.setText("0.0");
			}
		}
		
		
		else if(ActionCmd.equals("판매")) {
			 // 판매창에 같은 물품을 중복으로 계속 추가하면 재고를 넘을 수 있으므로 판매시에 다시 한번 재고 체크
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
				JOptionPane.showMessageDialog(null, (String)SellTable.getValueAt(i, 1) + "의 재고가 부족합니다.  재고수량 : " + limitEA + "개", "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
			
			// 모든 물품의 재고가 남아있다면 SellManager 클래스를 이용하여 ItemList에서 제거하고 ItemList.txt에 덮어쓰기
			if (sellable == true) {
				SM = new SellManager(ItemList, SellTable);
				SM.SellAndSave();
				JOptionPane.showMessageDialog(null, "정상적으로 판매되었습니다.", "판매 완료", JOptionPane.PLAIN_MESSAGE);
				if (Combo.getSelectedItem().toString().equals("현금"))        // 현금 결제시 static 변수 값 증가시키기
					TotalCash += Double.parseDouble(ResultPrice.getText());
				model2.setRowCount(0);
				ResultPrice.setText("0.0");
			}
			
			
			M.load();
		}
		
		
		else if(ActionCmd.equals("재고관리")) {
			ItemSelectMenu a = new ItemSelectMenu(M);
			a.setVisible(true);
		}
		
		
		else if(ActionCmd.equals("출퇴근등록")) {
			GoToWork go = new GoToWork();
			go.setVisible(true);
		}
		
		
		else if(ActionCmd.equals("시재점검")) {
			CashCheck CashCheckgui = new CashCheck();
			CashCheckgui.setVisible(true);
		}
		
		
		else if(ActionCmd.equals("임금정산")) {
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


