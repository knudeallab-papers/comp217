package TeamProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ItemSelectMenu extends JFrame implements ActionListener{

	Management theM = new Management();
	
	public ItemSelectMenu(Management M) {
		super("Àç°í °ü¸®");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setLocationRelativeTo(null);
		setLayout(null);
		
		theM = M;
		
		JLabel TitleLabel = new JLabel("Àç°í °ü¸®");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(140, 50, 500, 70);
		
		JButton OrderButton = new JButton("¹°Ç° ¹ßÁÖ");
		add(OrderButton);
		OrderButton.setBounds(20, 200, 200, 170);
		OrderButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		OrderButton.addActionListener(this);
		
		JButton ItemButton = new JButton("Àç°í ÇöÈ²");
		add(ItemButton);
		ItemButton.setBounds(290, 200, 200, 170);
		ItemButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		ItemButton.addActionListener(this);
		
		JButton ExpButton = new JButton("Æó±â ¸ñ·Ï");
		add(ExpButton);
		ExpButton.setBounds(560, 200, 200, 170);
		ExpButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		ExpButton.addActionListener(this);
	
		JButton BackButton = new JButton("µÚ·Î");
		add(BackButton);
		BackButton.setBounds(290, 450, 200, 50);
		BackButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		BackButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("¹°Ç° ¹ßÁÖ")) {
			OrderMenu a = new OrderMenu(theM);
			a.setVisible(true);
		}
		else if(ActionCmd.equals("Àç°í ÇöÈ²")) {
			CurrentItemMenu a = new CurrentItemMenu(theM);
			a.setVisible(true);
		}
		else if(ActionCmd.equals("Æó±â ¸ñ·Ï")) {
			ExpMenu a = new ExpMenu(theM);
			a.setVisible(true);
		}
		else if(ActionCmd.equals("µÚ·Î")) {
			theM = new Management();
			dispose();
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
		}
}
