package TeamProject;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class practice extends JFrame implements ActionListener{
	public static final JPanel currentPanel = new JPanel();
    public static final Color BGCOLOR = new Color(0xededf9);
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		practice p = new practice();
		p.setVisible(true);
		return;

	}
	
	public practice(){
		super("practice");
		setSize(660, 990);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(600, 10);
        setResizable(false);
        setLayout(new BorderLayout());

        currentPanel.setBackground(BGCOLOR);

        Container test = this.getContentPane();
        test.setBackground(BGCOLOR);
        JPanel viewPanel = new JPanel();
        viewPanel.setBackground(BGCOLOR);
        viewPanel.add(currentPanel);
        test.add(viewPanel, BorderLayout.CENTER);

        //mailSubmitPage(0);
        //showChangePage("");

        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
