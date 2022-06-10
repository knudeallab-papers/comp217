package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Checkborrow_logPage extends JPanel{
	private JTextArea log;
    private String logtxt;
    private String logline;
    
    private centerBoldLabel title;
    
	public Checkborrow_logPage(String obj) {
		super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(4,1));
        
        if(obj.equals("All")) {
        	title = new centerBoldLabel("전체 대여 기록", 72);        	
        }
        else if(obj.equals("Mat")) {
        	title = new centerBoldLabel("돗자리 대여 기록", 72);
        }
        else if(obj.equals("Umm")) {
        	title = new centerBoldLabel("우산 대여 기록", 72);
        }
        else if(obj.equals("Med")) {
        	title = new centerBoldLabel("상비약 대여 기록", 72);
        }
        
        JLabel index = new JLabel(" 물품ID      학생ID       빌린시간     반납여부");
        index.setFont(new Font("IM혜민 bold", Font.BOLD, 20));
        
    	Scanner filereader = null;
        try {
        	if(obj.equals("All")) {
        		filereader = new Scanner(new FileInputStream("./data/checkAll.txt"));        		
        	}
        	else if(obj.equals("Mat")) {
        		filereader = new Scanner(new FileInputStream("./data/checkMat.txt"));
            }
            else if(obj.equals("Umm")) {
            	filereader = new Scanner(new FileInputStream("./data/checkUmm.txt"));
            }
            else if(obj.equals("Med")) {
            	filereader = new Scanner(new FileInputStream("./data/checkMed.txt"));
            }
        	
        	logtxt = filereader.nextLine() + "\n";
        	while(filereader.hasNext()){
				logline = filereader.nextLine()+"\n";
				logtxt += logline;
			}
			filereader.close();
        }catch(FileNotFoundException e) {
        	e.printStackTrace();
        }
        
        log = new JTextArea(20, 10);
        log.setText(logtxt);
        log.setFont(new Font("IM혜민 regular", Font.PLAIN, 18));
		log.setBackground(Color.WHITE);
		log.setLineWrap(true);

        JScrollPane scrolledText = new JScrollPane(log);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel WrapperPanel = new JPanel(new GridLayout(1, 1));
		WrapperPanel.setPreferredSize(new Dimension(100,200));
		WrapperPanel.setBackground(PasswordMain.BACKGROUND_COLOR);
       
		WrapperPanel.add(scrolledText);
		
		add(title);
		add(index);
		add(WrapperPanel);
	}
}
