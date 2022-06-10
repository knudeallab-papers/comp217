package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AddMediMain extends JFrame implements ActionListener
{
    public static final JPanel currentPanel = new JPanel();
    public static final Color BGCOLOR = new Color(0xededf9);

    private JButton med1Btn;
    private JButton med2Btn;
    private JButton med3Btn;
    private JButton yes1Btn;
    private JButton yes2Btn;
    private JButton yes3Btn;
    private JButton noBtn;
    private JButton gotostartBtn;

    private AddMedi_statusPage statusPage;
    private AddMedi_editstatusPage editPage;
    private AddMedi_completePage completePage;

    //
    private MedObj[] MedList = new MedObj[3];
    private MedObj thismed;
    private MedObj med2;
    private MedObj med3;

    //DB에서 받아오는 아이로 바꾸기
    private String medi_name;
    private int medi_num;

    public static void main(String[] args)
    {
        //AddMediMain main = new AddMediMain();
        //main.setVisible(true);
        //return;
    }

    public AddMediMain()
    {
        super("AddMedi Main");
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

        statusPage();

        setVisible(true);
    }

    private void getMedList()
    {
        MedList = MedObj.dataGetter();
    }

    private void statusPage()
    {
        statusPage = new AddMedi_statusPage();
        MedList = MedObj.dataGetter();

        //임시로 데이터 입력했습니당
        //AddMedi_medInfo에 MedObj list로 id랑 num 넘기기 
        //getMedList();
        AddMedi_medInfo med1info = new AddMedi_medInfo("반창고", MedList[0].getNum());
        AddMedi_medInfo med2info = new AddMedi_medInfo("파 스", MedList[1].getNum());
        AddMedi_medInfo med3info = new AddMedi_medInfo("감기약", MedList[2].getNum());


        med1Btn = new ButtonForm("./image/codeVerifyButton");
        med1Btn.addActionListener(this);

        med2Btn = new ButtonForm("./image/codeVerifyButton");
        med2Btn.addActionListener(this);

        med3Btn = new ButtonForm("./image/codeVerifyButton");
        med3Btn.addActionListener(this);

        JPanel itemPanel1 = new JPanel(new GridLayout(1, 2));
        itemPanel1.setBackground(new Color(0xededf9));
        itemPanel1.add(med1info);
        itemPanel1.add(med1Btn);


        JPanel itemPanel2 = new JPanel(new GridLayout(1, 2));
        itemPanel2.setBackground(new Color(0xededf9));
        itemPanel2.add(med2info);
        itemPanel2.add(med2Btn);

        JPanel itemPanel3 = new JPanel(new GridLayout(1, 2));
        itemPanel3.setBackground(new Color(0xededf9));
        itemPanel3.add(med3info);
        itemPanel3.add(med3Btn);

        statusPage.add(itemPanel1);
        statusPage.add(itemPanel2);
        statusPage.add(itemPanel3);

        currentPanel.add(statusPage, BorderLayout.CENTER);
    }

    private void editPage(int medi_type)
    {
        if (medi_type == 1)
        {
            editPage = new AddMedi_editstatusPage("반창고");

            JPanel checkPanel = new JPanel(new GridLayout(1, 2));
            checkPanel.setBackground(BGCOLOR);//

            yes1Btn = new ButtonForm("./image/yes");
            yes1Btn.addActionListener(this);

            noBtn = new ButtonForm("./image/no");
            noBtn.addActionListener(this);

            checkPanel.add(yes1Btn);
            checkPanel.add(noBtn);

            editPage.add(checkPanel);

            currentPanel.add(editPage, BorderLayout.CENTER);
        }
        else if (medi_type == 2)
        {
            editPage = new AddMedi_editstatusPage("파 스");

            JPanel checkPanel = new JPanel(new GridLayout(1, 2));
            checkPanel.setBackground(BGCOLOR);

            yes2Btn = new ButtonForm("./image/yes");
            yes2Btn.addActionListener(this);

            noBtn = new ButtonForm("./image/no");
            noBtn.addActionListener(this);

            checkPanel.add(yes2Btn);
            checkPanel.add(noBtn);

            editPage.add(checkPanel);

            currentPanel.add(editPage, BorderLayout.CENTER);
        }
        else if (medi_type == 3)
        {
            editPage = new AddMedi_editstatusPage("감기약");

            JPanel checkPanel = new JPanel(new GridLayout(1, 2));
            checkPanel.setBackground(BGCOLOR);

            yes3Btn = new ButtonForm("./image/yes");
            yes3Btn.addActionListener(this);

            noBtn = new ButtonForm("./image/no");
            noBtn.addActionListener(this);

            checkPanel.add(yes3Btn);
            checkPanel.add(noBtn);

            editPage.add(checkPanel);

            currentPanel.add(editPage, BorderLayout.CENTER);
        }
    }

    private void completePage(int med_type)
    {
        if (med_type == 1)
        {
            //테스트용
            //medi_num = 추가한 거 불러오기로 수정
            completePage = new AddMedi_completePage("반창고", MedList[0].getNum());

            gotostartBtn = new ButtonForm("./image/gotoMainButton");
            gotostartBtn.addActionListener(this);

            completePage.add(gotostartBtn);

            currentPanel.add(completePage, BorderLayout.CENTER);
        }
        else if (med_type == 2)
        {
            //테스트용
            medi_num = 2;
            //medi_num = 추가한 거 불러오기로 수정
            completePage = new AddMedi_completePage("파 스", MedList[1].getNum());

            gotostartBtn = new ButtonForm("./image/gotoMainButton");
            gotostartBtn.addActionListener(this);

            completePage.add(gotostartBtn);

            currentPanel.add(completePage, BorderLayout.CENTER);
        }
        else if (med_type == 3)
        {
            //테스트용
            medi_num = 3;
            //medi_num = 추가한 거 불러오기로 수정
            completePage = new AddMedi_completePage("감기약", MedList[2].getNum());

            gotostartBtn = new ButtonForm("./image/gotoMainButton");
            gotostartBtn.addActionListener(this);

            completePage.add(gotostartBtn);

            currentPanel.add(completePage, BorderLayout.CENTER);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        currentPanel.removeAll();

        if (e.getSource() == med1Btn)
        {
            editPage(1);
        }
        else if (e.getSource() == med2Btn)
        {
            editPage(2);
        }
        else if (e.getSource() == med3Btn)
        {
            editPage(3);
        }

        if (e.getSource() == yes1Btn)
        {
            MedList[0].setMed_num(editPage.getNumber());
            completePage(1);
        }
        else if (e.getSource() == yes2Btn)
        {
            MedList[1].setMed_num(editPage.getNumber());
            completePage(2);
        }
        else if (e.getSource() == yes3Btn)
        {
            MedList[2].setMed_num(editPage.getNumber());
            completePage(3);
        }
        if (e.getSource() == noBtn)
        {
            statusPage();
        }

        if (e.getSource() == gotostartBtn)
        {
            MedDataSetter();
            KioskMain k = new KioskMain();
            k.setVisible(true);
            setVisible(false);
        }

        currentPanel.updateUI();
    }

    public void MedDataSetter()
    {
        try
        {
            PrintWriter FileWriter_Umm = new PrintWriter(new FileOutputStream("./data/med.txt"));
            for (MedObj m : MedList)
            {
                FileWriter_Umm.println(m.toFileString());
            }
            FileWriter_Umm.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
