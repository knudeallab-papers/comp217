package Restraunt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 추가 눌렀을 때 작동하는 윈도우.	
public class ContainerWindow extends JFrame implements ActionListener, Serializable{

	public static final int SMALL_WIDTH = 300;
	public static final int SMALL_HEIGHT = 250;
	
	JTextField inputName = new JTextField(10);
	JTextField inputPrice = new JTextField(10);
	JTextField inputStore = new JTextField(10);
	JTextField inputCall = new JTextField(10);
	
	JPanel BtnPanel = new JPanel();
	JButton okBtn = new JButton("OK");
	JButton cancelBtn = new JButton("Cancel");
	
	private String tmpName;
	private String tmpPrice;
	private String tmpStore;
	private String tmpVolume;
	private String tmpCall;
	
	private boolean END_input = false;
	
	Scanner keyboard = new Scanner(System.in);
	
	public ContainerWindow() {
		super("재료 추가");
		setBackground(Color.WHITE);
		
		setLayout(new GridLayout(5, 1));
		
		JPanel nameField = new JPanel();
		JPanel priceField = new JPanel();
		JPanel storeField = new JPanel();
		JPanel callField = new JPanel();
		
		JLabel name  = new JLabel(" 이 름  ");
		JLabel price = new JLabel(" 가 격  ");
		JLabel store = new JLabel(" 판매처  ");
		JLabel call  = new JLabel(" 연락처  ");
		
		nameField.add(name); nameField.add(inputName);
		priceField.add(price); priceField.add(inputPrice);
		storeField.add(store); storeField.add(inputStore);
		callField.add(call); callField.add(inputCall);
		
		add(nameField);
		add(priceField);
		add(storeField);
		add(callField);
		
		okBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		END_input = false;
		
		BtnPanel.add(okBtn);
		BtnPanel.add(cancelBtn);
		add(BtnPanel, BorderLayout.SOUTH);
		
		pack();
	}
	

	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		
		END_input = false;
		
		if(actionCmd.equals("OK")) {
			tmpName = inputName.getText();
			tmpPrice = inputPrice.getText();
			tmpStore = inputStore.getText();
			tmpCall = inputCall.getText();
			
			Container.ItemName.add(tmpName);
			try {
				Integer.parseInt(tmpPrice);
				Container.ItemPrice.add(tmpPrice);
			}
			catch(Exception a) {
				errorHandler("가격에는 숫자만 입력해주세요 ^^;;");
			}
			Container.ItemFrom.add(tmpStore);
			Container.ItemCall.add(tmpCall);
			Container.ItemNowVol.add("0");
			Container.ItemBuyVol.add("0");
			Container.setData();
			
			END_input = true;
			
			dispose();
		}
		
		else if(actionCmd.equals("Cancel"))
			dispose();
		else
			System.out.println("Unexpected Error");
		
		inputName.setText("");
		inputPrice.setText("");
		inputStore.setText("");
		inputCall.setText("");
	}
	
	public boolean getStatus()  {
		boolean tmp = END_input;
		END_input = false;
		
		return tmp;
	}
	
	public String getName() { return tmpName; }
	public String getPrice() { return tmpPrice; }
	public String getStore() { return tmpStore; }
	public String getVolume() { return tmpVolume; }
	public String getCall() { return tmpCall; }
	
    
    JFrame errFrame;
	int errCount = 0;
	JLabel errLabel;
	JButton errButton;
	public void errorHandler(String str) {
		
		if( errCount == 0 ) {
			System.out.println("Error OK");
			errFrame = new JFrame("Error!!");
			errFrame.setSize(400, 200);
			errFrame.setLayout(null);
			errFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			errLabel = new JLabel();
			errLabel.setText("Err: " + str );
			errButton = new JButton("확인");
			errButton.setActionCommand("OK");
			errButton.addActionListener(new errListener());
			
			errFrame.add(errLabel);
			errFrame.add(errButton);
			
			errLabel.setBounds(100, 30, 250, 50);
			errButton.setBounds(100, 100, 200, 50);
			errCount = 1;
			errFrame.setVisible(true);
		}
		else {
			errLabel.setText("Err: " + str );
			errFrame.setVisible(true);
		}
	}
	
	class errListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			
			System.out.println(actionCmd);
			
			if( actionCmd.equals("OK")) {
				errFrame.setVisible(false);
			}
		}
	}
}