package TeamProject;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class Terms extends JPanel {
	private JTextArea terms;
	private String termsline;
	private String termstxt;

	public Terms(String file) {
		super();
		setBackground(BorrowMain.BGCOLOR);
		setLayout(new GridLayout(2, 1));

		centerBoldLabel title = new centerBoldLabel("약관동의", 42);

		JLabel termsLB = new JLabel("개인정보 수집 동의 및 이용 안내");
		termsLB.setFont(new Font("IM혜민 regular", Font.PLAIN, 30));
		//termsLB.setPreferredSize(new Dimension(500,50));
		
		Scanner filereader = null;
		try{
			filereader = new Scanner(new FileInputStream(file));
			termstxt = filereader.nextLine()+"\n";
			while(filereader.hasNext()){
				termsline = filereader.nextLine()+"\n";//
				termstxt += termsline;
			}
			filereader.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}

		terms = new JTextArea(10, 35);
		terms.setText(termstxt);
		terms.setFont(new Font("IM혜민 regular", Font.PLAIN, 20));
		terms.setBackground(Color.WHITE);
		terms.setLineWrap(true);
		//terms.setPreferredSize(new Dimension(500,600));

		JScrollPane scrolledText = new JScrollPane(terms);
		scrolledText
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel WrapperPanel = new JPanel(new GridLayout(3, 1));
		WrapperPanel.setPreferredSize(new Dimension(500,550));
		WrapperPanel.setBackground(PasswordMain.BACKGROUND_COLOR);

		WrapperPanel.add(title);
		WrapperPanel.add(termsLB);
		WrapperPanel.add(scrolledText);

		add(WrapperPanel);

	}

}
