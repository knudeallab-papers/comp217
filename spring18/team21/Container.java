package Restraunt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class Container extends JPanel implements ActionListener , Serializable{

	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	
	public static final int LINES = 15;
	public static final int CHAR_PER_LINE = 30;
	
	public static int count = 0;
	
	public static ArrayList<String> ItemName = new ArrayList<String>(); //상품이름
	public static ArrayList<String> ItemFrom = new ArrayList<String>(); //상품판매처
	public static ArrayList<String> ItemCall = new ArrayList<String>(); //상품연락처
	public static ArrayList<String> ItemPrice = new ArrayList<String>(); //상품가	
	public static ArrayList<String> ItemNowVol = new ArrayList<String>(); //상품현재수량
	public static ArrayList<String> ItemBuyVol = new ArrayList<String>(); //상품주문량
	static String[][] Saving = new String[9][3];   // 4 -> 3
	//[x][0]은 이름. 	
	//[x][1]는 재고.
	//[x][2]은 주문.	
	//[x][3]는 가격.	

	public static JTextField NameInfo = new JTextField(10);
	public static JTextField PriceInfo = new JTextField(10);
	public static JTextField MarketInfo = new JTextField(10);
	public static JTextField ContactInfo = new JTextField(10);
	public static JTextField StockInfo = new JTextField(10);
	public static JTextField OrderInfo = new JTextField(10);
	
	static String Index[] = {"이름", "재고","가격"};
	static DefaultTableModel model = new DefaultTableModel(null, Index);
	public static JTable leftTable = new JTable(model);

	ContainerWindow GUI = new ContainerWindow();
	
	public class Error extends JFrame implements ActionListener	{
		public Error() {
			super("ERROR");
			setSize(300, 100);
			setLayout(new BorderLayout());
			
			JLabel errorInfo = new JLabel("\"Error : incorrect.\"");
			errorInfo.setHorizontalAlignment(JLabel.CENTER);
			add(errorInfo, BorderLayout.CENTER);
			
			JButton retry = new JButton("OK");
			retry.addActionListener(this);
			add(retry, BorderLayout.SOUTH);
		}
		
		public void actionPerformed(ActionEvent e) {
			String btnSig = e.getActionCommand();
			
			if(btnSig.equals("OK"))
				dispose();
		}
	}
	 
	public Container() {
		setLayout(new BorderLayout());

		JPanel data = new JPanel();
		data.setLayout(new GridLayout(1,2));
		
		JPanel buttonPanel = new JPanel(); // 버튼 
		JPanel RightButtonPanel = new JPanel(); // 오른쪽 버튼 
		JPanel LeftButtonPanel = new JPanel(); // 왼쪽 버튼 

		//왼쪽
		for(int i=0;i<9;i++)
			for(int j=0;j<3;j++) // 4 -> 3
				Saving[i][j] = new String();
		
		//테이블 구현
		leftTable.setGridColor(Color.WHITE);
		leftTable.setBorder(new LineBorder(Color.BLACK));
		JScrollPane scroll = new JScrollPane(leftTable);
		data.add(scroll);
		
		JButton AddBtn = new JButton("추가");
		AddBtn.addActionListener(this);
		LeftButtonPanel.add(AddBtn);
		
		JButton DelBtn = new JButton("삭제");
		DelBtn.addActionListener(this);
		LeftButtonPanel.add(DelBtn);
		
		
		//오른쪽
		JPanel info = new JPanel(new GridLayout(6,1));

		info.setBorder(new TitledBorder (new LineBorder(Color.LIGHT_GRAY), " << 정보 >> "));
		
		JPanel NamePanel = new JPanel();
		JPanel PricePanel = new JPanel();
		JPanel MarketPanel = new JPanel();
		JPanel ContactPanel = new JPanel();
		JPanel StockPanel = new JPanel();
		JPanel OrderPanel = new JPanel();
		
		JLabel NameLabel = new JLabel("  이 름  :  "); //NameLabel.setBorder(new LineBorder(Color.BLACK));
		JLabel PriceLabel = new JLabel("  가 격  :  "); //PriceLabel.setBorder(new LineBorder(Color.BLACK));
		JLabel MarketLabel = new JLabel("  판매처  :  "); //MarketLabel.setBorder(new LineBorder(Color.BLACK));
		JLabel ContactLabel = new JLabel("  연락처  :  "); //ContactLabel.setBorder(new LineBorder(Color.BLACK));
		JLabel StockLabel = new JLabel("  재 고  :  "); //StockLabel.setBorder(new LineBorder(Color.BLACK));
		JLabel OrderLabel = new JLabel(" 주문수량 :  "); //OrderLabel.setBorder(new LineBorder(Color.BLACK));
		
		NamePanel.add(NameLabel); NamePanel.setBackground(Color.WHITE);
		NamePanel.add(NameInfo); NameInfo.setBackground(new Color(0,0,0,0)); NameInfo.setEditable(false);
		
		PricePanel.add(PriceLabel); PricePanel.setBackground(Color.WHITE);
		PricePanel.add(PriceInfo); PriceInfo.setBackground(new Color(0,0,0,0)); PriceInfo.setEditable(false);
		
		MarketPanel.add(MarketLabel); MarketPanel.setBackground(Color.WHITE);
		MarketPanel.add(MarketInfo); MarketInfo.setBackground(new Color(0,0,0,0)); MarketInfo.setEditable(false);
		
		ContactPanel.add(ContactLabel); ContactPanel.setBackground(Color.WHITE);
		ContactPanel.add(ContactInfo); ContactInfo.setBackground(new Color(0,0,0,0)); ContactInfo.setEditable(false);
		
		StockPanel.add(StockLabel); StockPanel.setBackground(Color.WHITE);
		StockPanel.add(StockInfo); StockInfo.setBackground(new Color(0,0,0,0)); StockInfo.setEditable(false);
		
		OrderPanel.add(OrderLabel); OrderPanel.setBackground(Color.WHITE);
		OrderPanel.add(OrderInfo);
		
		info.add(NamePanel);
		info.add(PricePanel);
		info.add(MarketPanel);
		info.add(ContactPanel);
		info.add(StockPanel);
		info.add(OrderPanel);
		info.setBackground(Color.WHITE);	
		
		JButton OrderBtn = new JButton("주문");
		OrderBtn.addActionListener(this);
		RightButtonPanel.add(OrderBtn);
		
		JButton CancleBtn = new JButton("주문 취소");
		CancleBtn.addActionListener(this);
		RightButtonPanel.add(CancleBtn);
		
		data.add(info);
		add(data, BorderLayout.CENTER);
		
		JLabel empty = new JLabel("                         ");
		LeftButtonPanel.setBackground(new Color(170,200,180,255));
		buttonPanel.add(LeftButtonPanel);
		buttonPanel.add(empty);
		RightButtonPanel.setBackground(new Color(170,200,180,255));
		buttonPanel.add(RightButtonPanel);
		buttonPanel.setBackground(new Color(170,200,180,255)); buttonPanel.setBorder(new LineBorder(Color.BLACK));
		add(buttonPanel, BorderLayout.SOUTH);
		
		MyMouseListener m1 = new MyMouseListener();
		leftTable.addMouseListener(m1);
	}
	
	public void setName(String tmp) { ItemName.add(tmp); }
	public void setPrice(String tmp) { ItemPrice.add(tmp); }
	public void setStore(String tmp) { ItemFrom.add(tmp); }
	public void setVolume(String tmp) { ItemBuyVol.add(tmp); }
	public void setCall(String tmp) { ItemCall.add(tmp); }
	
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		

		
		if(actionCmd.equals("주문")) {	
			String JumunTmp = OrderInfo.getText();
			String JaegoTmp = StockInfo.getText();
			
			int JaegoSum = 0;
			
			try {
				JaegoSum += Integer.parseInt(JumunTmp.trim());
				if(JaegoSum < 0)
				{
					throw new Exception();
				}
			}
			catch(Exception ee)
			{
				Error OnlyInt = new Error();
				OnlyInt.setVisible(true);
			}

			if(JaegoSum >= 0) {
				JaegoSum = JaegoSum + Integer.parseInt(JaegoTmp.trim());
				String SumTmp = Integer.toString(JaegoSum);
			
				int tmpRow = leftTable.getSelectedRow();
			
				ItemNowVol.remove(tmpRow);
				ItemNowVol.add(tmpRow, SumTmp);
			
				StockInfo.setText(ItemNowVol.get(tmpRow));
				Saving[count][2]=ItemNowVol.get(tmpRow);
				leftTable.setValueAt(Saving[count][2], tmpRow, 1);
				
				/* 이재준 수정할 부분 주문한 재료의 총 가격을 리턴 시켜줘야함*/
				int price = Integer.parseInt(JumunTmp.trim()) * Integer.parseInt(PriceInfo.getText().trim());
				Restraunt.ContainerAddMaterial( price );
				ExportAccount.writeAccount("지출 <재료 주문>  " + NameInfo.getText() + OrderInfo.getText() + " =" + price, price );
			
				OrderInfo.setText("0");
			}
			else
				OrderInfo.setText("0");
		}
		else if(actionCmd.equals("주문 취소")) {
		// 몇개만 주문 취소
			String JumunTmp = OrderInfo.getText();
			String JaegoTmp = StockInfo.getText();
			
			int JaegoSum = Integer.parseInt(JumunTmp) - Integer.parseInt(JaegoTmp);
			String SumTmp = Integer.toString(JaegoSum);
			JaegoSum = JaegoSum - Integer.parseInt(JaegoTmp.trim());

			int tmpRow = leftTable.getSelectedRow();
			
			ItemNowVol.remove(tmpRow);
			ItemNowVol.add(tmpRow, Integer.toString(Math.abs(Integer.parseInt(SumTmp))));
			
			StockInfo.setText(ItemNowVol.get(Math.abs(tmpRow)));
			Saving[count][2]=ItemNowVol.get(tmpRow);
			leftTable.setValueAt(Saving[count][2], tmpRow, 1);
			
			int price = Integer.parseInt(JumunTmp.trim()) * Integer.parseInt(PriceInfo.getText().trim());
			Restraunt.ContainerMinusMaterial( Integer.parseInt(JumunTmp.trim()) * Integer.parseInt(PriceInfo.getText().trim()) );
			ImportAccount.writeAccount("수익 <주문취소>  " + NameInfo.getText() + OrderInfo.getText() + " =" + price, price );
			
			
			StockInfo.setText(Integer.toString(Math.abs(Integer.parseInt(SumTmp)))); // 부호 오류 

			OrderInfo.setText("0");
		}
		
		else if(actionCmd.equals("추가")) {
			GUI.setVisible(true);
		}
		
		else if(actionCmd.equals("삭제")) {
			if(leftTable.getSelectedRow() == -1) {
				NameInfo.setText(null);
				StockInfo.setText(null);
				OrderInfo.setText(null);
				PriceInfo.setText(null);
				MarketInfo.setText(null);
				ContactInfo.setText(null);
			
				return;
			}
			
			else {
				int tmpSelectedRow = leftTable.getSelectedRow();
				model.removeRow(tmpSelectedRow);
				
				ItemName.remove(tmpSelectedRow); //상품이름
				ItemFrom.remove(tmpSelectedRow); //상품판매처
				ItemCall.remove(tmpSelectedRow); //상품연락처
				ItemPrice.remove(tmpSelectedRow); //상품가	
				ItemNowVol.remove(tmpSelectedRow); //상품현재수량
				ItemBuyVol.remove(tmpSelectedRow);
				
				count --;
			}
		}
		else {
			Error showError = new Error();
			showError.setVisible(true);
		}
		
		if(leftTable.getSelectedRow() == -1)
		{
			NameInfo.setText(null);
			StockInfo.setText(null);
			OrderInfo.setText(null);
			PriceInfo.setText(null);
			MarketInfo.setText(null);
			ContactInfo.setText(null);
		}
	}
	
	public static void setData() {
		if(Integer.parseInt(ItemBuyVol.get(count)) >= 0) {
			Saving[count][0] = ItemName.get(count);
			Saving[count][1] = ItemNowVol.get(count);
			//Saving[count][2] = ItemBuyVol.get(count);
			Saving[count][2] = ItemPrice.get(count);
		
			model.addRow(Saving[count]);

			count++;
		}
	}
	
	public static boolean findMaterial(String name) {
		if( name.equals("") )
			return true;
		
		for(int i=0; i< Container.count ; i++) {
			if( name.equals(ItemName.get(i).trim() ) ) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkMaterial(String[] name) {
		for( int i=0; i<Container.count ; i++) {
			for( int j=0; j<name.length; j++) {
				if( ItemName.get(i).equals(name[j]) ) {
					if( Integer.parseInt(ItemNowVol.get(i)) <= 0 )
						return false;
				}
			}
		}
		return true;
	}
	
	public static void Table_minusMaterial(String[] name) {
		for( int i=0; i<Container.count ; i++) {
			for( int j=0; j<name.length; j++) {
				if( ItemName.get(i).equals(name[j]) ) {
					ItemNowVol.add(i, Integer.parseInt(ItemNowVol.get(i)) -1 +"");
					
					StockInfo.setText(ItemNowVol.get(i));
					Saving[count][2]=ItemNowVol.get(i);
					leftTable.setValueAt(Saving[count][2], i, 1);
				}
			}
		}
	}
	
	public static int calculateMaterial(String[] name) {
		int price = 0;
		for( int i=0; i<Container.count ; i++) {
			for( int j=0; j<name.length; j++) {
				if( ItemName.get(i).equals(name[j].trim()) ) {
					price += Integer.parseInt(ItemPrice.get(i));
				}
			}
		}
		return price;
	}
}

	class MyMouseListener implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {
    		int row = Container.leftTable.getSelectedRow();
    		
    		if(Container.leftTable.isRowSelected(row)) {	
    			Container.NameInfo.setText(Container.ItemName.get(row));
    			Container.StockInfo.setText(Container.ItemNowVol.get(row));
    			Container.OrderInfo.setText(Container.ItemBuyVol.get(row));
    			Container.PriceInfo.setText(Container.ItemPrice.get(row));
    			Container.MarketInfo.setText(Container.ItemFrom.get(row));
    			Container.ContactInfo.setText(Container.ItemCall.get(row));
    		}
    }
	
    // 클릭빼고 다 필요없다~~~	
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}