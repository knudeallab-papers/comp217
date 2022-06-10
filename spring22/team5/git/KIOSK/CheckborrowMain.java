package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CheckborrowMain extends JFrame implements ActionListener
{
    public static final JPanel currentPanel = new JPanel();
    public static final Color BGCOLOR = new Color(0xededf9);

    private Checkborrow_selectPage selectPage;
    private Checkborrow_logPage logPage;
    private Checkborrow_gotomainPage gotomainPage;

    private JButton matBtn;
    private JButton ummBtn;
    private JButton mediBtn;
    private JButton allBtn;
    private JButton okayBtn;
    private JButton gotostartBtn;
    
    public static void main(String[] args)
    {
        CheckborrowMain main = new CheckborrowMain();
        main.setVisible(true);
    }

    public CheckborrowMain()
    {
        super("Checkborrow Main");
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

        selectPage();
        
        setVisible(true);
    }

    private void selectPage()
    {
        selectPage = new Checkborrow_selectPage();

        matBtn = new ButtonForm2("./image/Mat");
        matBtn.addActionListener(this);

        ummBtn = new ButtonForm2("./image/Umm");
        ummBtn.addActionListener(this);

        mediBtn = new ButtonForm2("./image/Med");
        mediBtn.addActionListener(this);

        allBtn = new ButtonForm2("./image/All");
        allBtn.addActionListener(this);

        selectPage.add(matBtn);
        selectPage.add(ummBtn);
        selectPage.add(mediBtn);
        selectPage.add(allBtn);

        currentPanel.add(selectPage, BorderLayout.CENTER);
    }
    
    private void logPage(int obj) {
    	if(obj == 1) {
    		logPage = new Checkborrow_logPage("Mat");    		
    	}
    	else if(obj == 2) {
    		logPage = new Checkborrow_logPage("Umm");
    	}
    	else if(obj == 3) {
    		logPage = new Checkborrow_logPage("Med");
    	}
    	else if(obj == 4) {
    		logPage = new Checkborrow_logPage("All");
    	}
    	else {
    		System.err.println("알수없는오류입니다.");
    	}
        okayBtn = new ButtonForm("./image/codeVerifyButton");
        okayBtn.addActionListener(this);

        logPage.add(okayBtn);
        
        currentPanel.add(logPage, BorderLayout.CENTER);
    }

    private void gotomainPage()
    {
        gotomainPage = new Checkborrow_gotomainPage();

        gotostartBtn = new ButtonForm("./image/gotoMainButton");
        gotostartBtn.addActionListener(this);

        gotomainPage.add(gotostartBtn);

        currentPanel.add(gotomainPage, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        currentPanel.removeAll();

        if (e.getSource() == matBtn)
        {
        	logPage(1);
        }
        if (e.getSource() == ummBtn)
        {
        	logPage(2);
        }
        if (e.getSource() == mediBtn)
        {
        	logPage(3);
        }
        if (e.getSource() == allBtn)
        {
            logPage(4);
        }

        if (e.getSource() == okayBtn)
        {
            gotomainPage();
        }

        if (e.getSource() == gotostartBtn)
        {
        	KioskMain k = new KioskMain();
            k.setVisible(true);
            setVisible(false);
        }

        currentPanel.updateUI();
    }
}
