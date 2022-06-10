package TeamProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class CashCheck extends JFrame implements ActionListener {
	protected JTextField Field50000;
	protected JTextField Field10000;
	protected JTextField Field5000;
	protected JTextField Field1000;
	protected JTextField Field500;
	protected JTextField Field100;
	protected JTextField Field50;
	protected JTextField Field10;
	protected JTextField TotalSellCash;
	protected JTextField TotalCash;
	protected JTextField CashGap;
	

	public CashCheck() {
		super("시재점검");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setLocationRelativeTo(null);
		setLayout(null);
		
		JLabel MoneyLabel = new JLabel("권종");
		MoneyLabel.setHorizontalAlignment(JLabel.CENTER);
		MoneyLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		MoneyLabel.setOpaque(true);
		MoneyLabel.setBackground(Color.ORANGE);
		add(MoneyLabel);
		MoneyLabel.setBounds(40, 10, 50, 30);
		
		JLabel EALabel = new JLabel("장수");
		EALabel.setHorizontalAlignment(JLabel.CENTER);
		EALabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		EALabel.setOpaque(true);
		EALabel.setBackground(Color.ORANGE);
		add(EALabel);
		EALabel.setBounds(100, 10, 50, 30);
		
		JLabel Label50000 = new JLabel("50000원");
		Label50000.setHorizontalAlignment(JLabel.CENTER);
		Label50000.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(Label50000);
		Label50000.setBounds(30, 60, 70, 30);
		
		JLabel Label10000 = new JLabel("10000원");
		Label10000.setHorizontalAlignment(JLabel.CENTER);
		Label10000.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(Label10000);
		Label10000.setBounds(30, 100, 70, 30);
		
		JLabel Label5000 = new JLabel("5000원");
		Label5000.setHorizontalAlignment(JLabel.CENTER);
		Label5000.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(Label5000);
		Label5000.setBounds(30, 140, 70, 30);
		
		JLabel Label1000 = new JLabel("1000원");
		Label1000.setHorizontalAlignment(JLabel.CENTER);
		Label1000.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(Label1000);
		Label1000.setBounds(30, 180, 70, 30);
		
		JLabel Label500 = new JLabel("500원");
		Label500.setHorizontalAlignment(JLabel.CENTER);
		Label500.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(Label500);
		Label500.setBounds(30, 220, 70, 30);
		
		JLabel Label100 = new JLabel("100원");
		Label100.setHorizontalAlignment(JLabel.CENTER);
		Label100.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(Label100);
		Label100.setBounds(30, 260, 70, 30);
		
		JLabel Label50 = new JLabel("50원");
		Label50.setHorizontalAlignment(JLabel.CENTER);
		Label50.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(Label50);
		Label50.setBounds(30, 300, 70, 30);
		
		JLabel Label10 = new JLabel("10원");
		Label10.setHorizontalAlignment(JLabel.CENTER);
		Label10.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(Label10);
		Label10.setBounds(30, 340, 70, 30);
		
		
		Field50000 = new JTextField();
		add(Field50000);
		Field50000.setBounds(110, 60, 30, 30);
		
		Field10000 = new JTextField();
		add(Field10000);
		Field10000.setBounds(110, 100, 30, 30);
		
		Field5000 = new JTextField();
		add(Field5000);
		Field5000.setBounds(110, 140, 30, 30);
		
		Field1000 = new JTextField();
		add(Field1000);
		Field1000.setBounds(110, 180, 30, 30);
		
		Field500 = new JTextField();
		add(Field500);
		Field500.setBounds(110, 220, 30, 30);
		
		Field100 = new JTextField();
		add(Field100);
		Field100.setBounds(110, 260, 30, 30);
		
		Field50 = new JTextField();
		add(Field50);
		Field50.setBounds(110, 300, 30, 30);
		
		Field10 = new JTextField();
		add(Field10);
		Field10.setBounds(110, 340, 30, 30);
		
		JButton AddButton = new JButton("계산");
		AddButton.addActionListener(this);
		add(AddButton);
		AddButton.setBounds(40, 380, 100, 50);
		
		JButton ClearButton = new JButton("초기화");
		ClearButton.addActionListener(this);
		add(ClearButton);
		ClearButton.setBounds(340, 380, 100, 50);
		
		
		JLabel SellCashLabel = new JLabel("현금판매금액");
		SellCashLabel.setHorizontalAlignment(JLabel.CENTER);
		SellCashLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(SellCashLabel);
		SellCashLabel.setBounds(190, 100, 100, 30);
		
		TotalSellCash = new JTextField();
		TotalSellCash.setEditable(false);
		add(TotalSellCash);
		TotalSellCash.setBounds(300, 100, 120, 30);
		
		JLabel WonLabel1 = new JLabel("원");
		WonLabel1.setHorizontalAlignment(JLabel.CENTER);
		WonLabel1.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(WonLabel1);
		WonLabel1.setBounds(415, 100, 50, 30);
		
		JLabel CashLabel = new JLabel("보유현금");
		CashLabel.setHorizontalAlignment(JLabel.CENTER);
		CashLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(CashLabel);
		CashLabel.setBounds(190, 150, 100, 30);
		
		TotalCash = new JTextField();
		TotalCash.setEditable(false);
		add(TotalCash);
		TotalCash.setBounds(300, 150, 120, 30);
		
		JLabel WonLabel2 = new JLabel("원");
		WonLabel2.setHorizontalAlignment(JLabel.CENTER);
		WonLabel2.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(WonLabel2);
		WonLabel2.setBounds(415, 150, 50, 30);
		
		JLabel GapLabel = new JLabel("차이");
		GapLabel.setHorizontalAlignment(JLabel.CENTER);
		GapLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(GapLabel);
		GapLabel.setBounds(190, 200, 100, 30);
		
		CashGap = new JTextField();
		CashGap.setEditable(false);
		add(CashGap);
		CashGap.setBounds(300, 200, 120, 30);
		
		JLabel WonLabel3 = new JLabel("원");
		WonLabel3.setHorizontalAlignment(JLabel.CENTER);
		WonLabel3.setFont(new Font("SansSerif", Font.BOLD, 14));
		add(WonLabel3);
		WonLabel3.setBounds(415, 200, 50, 30);
		
		
		TotalSellCash.setText(Double.toString(CVSmanager.TotalCash));
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("계산")) {
			double total = 0.0;
			total += Double.parseDouble(Field50000.getText()) * 50000;
			total += Double.parseDouble(Field10000.getText()) * 10000;
			total += Double.parseDouble(Field5000.getText()) * 5000;
			total += Double.parseDouble(Field1000.getText()) * 1000;
			total += Double.parseDouble(Field500.getText()) * 500;
			total += Double.parseDouble(Field100.getText()) * 100;
			total += Double.parseDouble(Field50.getText()) * 50;
			total += Double.parseDouble(Field10.getText()) * 10;
			TotalCash.setText(Double.toString(total));
			double gap;
			gap = Double.parseDouble(TotalSellCash.getText()) - Double.parseDouble(TotalCash.getText());
			CashGap.setText(Double.toString(gap));
		}
		else if (ActionCmd.equals("초기화")) {
			Field50000.setText("");
			Field10000.setText("");
			Field5000.setText("");
			Field1000.setText("");
			Field500.setText("");
			Field100.setText("");
			Field50.setText("");
			Field10.setText("");
			TotalCash.setText("");
			CashGap.setText("");
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
	}

	
	
}