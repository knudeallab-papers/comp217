package GH;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Library_rewrite_page extends JFrame implements ActionListener {
	
	ImageIcon icon;
	Lecture temp;
	
	JPanel viewPanel;
	JTextArea memopad;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Library_rewrite_page gui = new Library_rewrite_page(Regis_page.JAVA); //test
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
	
	public Library_rewrite_page(Lecture a) throws IOException {
		super("호반우를 키워라_재정리");
		setSize(740, 430);
		setLocationRelativeTo(null);
    	setResizable(false);
		temp = a;
      
		icon = new ImageIcon("동아리방.jfif");
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, 720, 404, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        panel.setLayout(null);
        
        
        viewPanel = new JPanel();
    	viewPanel.setBounds(100, 50, 520, 250);
    	viewPanel.setBackground(Color.RED);
    	viewPanel.setLayout(new GridLayout(1, 2));
    	panel.add(viewPanel);
        
        JPanel memoPanel = new JPanel();
    	memoPanel.setLayout(new BorderLayout());
    	viewPanel.add(memoPanel);
    	
    	
    	JLabel note = new JLabel("필기노트를 다시 정리해 봅시다.");
    	memoPanel.add(note, BorderLayout.NORTH);
    	
    	memopad = new JTextArea();
    	memopad.setEditable(true);
    	
    	byte[] bytes = Files.readAllBytes(Paths.get(a.getName()+".txt"));
    	memopad.setText(new String(bytes));
        
    	JScrollPane scrollpad = new JScrollPane(memopad);
    	
    	memoPanel.add(scrollpad, BorderLayout.CENTER);
    	
    	JButton amend_btn = new JButton("수정");
    	amend_btn.addActionListener(this);
    	memoPanel.add(amend_btn, BorderLayout.SOUTH);
    	
        add(panel);
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("수정")) {
		
			PrintWriter outputStream = null;
			File obj = null;
			
			try {
				obj = new File(temp.getName()+".txt");
				if (!obj.exists()) {
					JOptionPane.showMessageDialog(null, "수업을 듣고 필기하세요!");
		        	
		        	setVisible(false);
				}
				outputStream = new PrintWriter(new FileOutputStream(obj));
			} catch (FileNotFoundException e1) {
			
			}
			
			outputStream.println(memopad.getText());
			System.out.println(memopad.getText());
			outputStream.close();
			
			JOptionPane.showMessageDialog(null, "수정사항이 반영되었습니다.");
		
			setVisible(false);
		}
	}
}
