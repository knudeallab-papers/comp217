package TeamProject;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class BorrowMain extends JFrame implements ActionListener
{
    public static final JPanel currentPanel = new JPanel();
    public static final Color BGCOLOR = new Color(0xededf9);
    private JButton matBtn;
    private JButton mat1Btn;
    private JButton mat2Btn;
    private JButton mat3Btn;
    private JButton mat4Btn;
    private JButton ummBtn;
    private JButton umm1Btn;
    private JButton umm2Btn;
    private JButton umm3Btn;
    private JButton umm4Btn;
    private JButton medBtn;
    private JButton med1Btn;
    private JButton med2Btn;
    private JButton med3Btn;
    private JButton OKComfirmBtn;
    private JButton MedOKComfirmBtn;
    private JButton OKReturnBtn;
    private JButton AgreeBtn;
    private JButton AgreeRBtn;
    private JButton MedAgreeBtn;
    private JButton OKTermsBtn;
    private JButton MedOKTermsBtn;
    private JButton OKReturnGuideBtn;
    private JButton gotoMainBtn;

    private MatObj[] MatList = new MatObj[4];
    private UmmObj[] UmmList = new UmmObj[4];
    private MedObj[] MedList = new MedObj[3];

    private B_ObjSelect objSelectPage;
    private B_MatSelect matSelectPage;
    private B_UmmSelect ummSelectPage;
    private B_MedSelect medSelectPage;
    // private JPanel matSelectPage;
    private B_BorrowComfirm borrowComfirmPage;
    private B_UseComfirm useComfirmPage;
    private B_ReturnPage returnPage;
    private Terms termsPage;
    private B_ReturnGuidePage returnguidePage;
    private CompletePage completePage;
    private boolean Agree = false;
    private boolean AgreeR = false;
    private boolean MedAgree = false;
    private final Student User;
    private int OBJNUMER = 0;
    private int OBJTYPE = 0;
    private String ChangedData;

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        //BorrowMain b = new BorrowMain();
        //b.setVisible(true);
        return;
    }

    public BorrowMain(Student user)
    {
        super("test");
        setSize(660, 990);
        setLocation(600, 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        User = user;

        currentPanel.setBackground(BGCOLOR);

        Container test = this.getContentPane();
        test.setBackground(BGCOLOR);
        JPanel viewPanel = new JPanel();
        viewPanel.setBackground(BGCOLOR);
        viewPanel.add(currentPanel);
        test.add(viewPanel, BorderLayout.CENTER);

        //getMatList();// getter로 수정해야
        ObjSelectPage();

        setVisible(true);
    }

    private void ObjSelectPage()
    {
        objSelectPage = new B_ObjSelect();
        matBtn = new ButtonForm2("./image/Mat");
        ummBtn = new ButtonForm2("./image/Umm");
        medBtn = new ButtonForm2("./image/Med");

        matBtn.addActionListener(this);
        ummBtn.addActionListener(this);
        medBtn.addActionListener(this);

        objSelectPage.add(matBtn);
        objSelectPage.add(ummBtn);
        objSelectPage.add(medBtn);

        currentPanel.add(objSelectPage, BorderLayout.CENTER);
    }

    private void MatSelectPage()
    {
        matSelectPage = new B_MatSelect();

        MatList = MatObj.dataGetter();

        B_MatInfo mat1info = new B_MatInfo(MatList[0].getObjID(), MatList[0].getDateStart(),
                MatList[0].getDateHaveto(), MatList[0].getIsBorrowing());
        B_MatInfo mat2info = new B_MatInfo(MatList[1].getObjID(), MatList[1].getDateStart(),
                MatList[1].getDateHaveto(), MatList[1].getIsBorrowing());
        B_MatInfo mat3info = new B_MatInfo(MatList[2].getObjID(), MatList[2].getDateStart(),
                MatList[2].getDateHaveto(), MatList[2].getIsBorrowing());
        B_MatInfo mat4info = new B_MatInfo(MatList[3].getObjID(), MatList[3].getDateStart(),
                MatList[3].getDateHaveto(), MatList[3].getIsBorrowing());

        mat1Btn = new ButtonForm3(BorrowAble(MatList[0]));
        if (MatList[0].getIsBorrowing() && !MatList[0].getStudentID().equals(User.getID()))
        {
            mat1Btn.setEnabled(false);
        }
        mat2Btn = new ButtonForm3(BorrowAble(MatList[1]));
        if (MatList[1].getIsBorrowing() && !MatList[1].getStudentID().equals(User.getID()))
        {
            mat2Btn.setEnabled(false);
        }
        mat3Btn = new ButtonForm3(BorrowAble(MatList[2]));
        if (MatList[2].getIsBorrowing() && !MatList[2].getStudentID().equals(User.getID()))
        {
            mat3Btn.setEnabled(false);
        }
        mat4Btn = new ButtonForm3(BorrowAble(MatList[3]));
        if (MatList[3].getIsBorrowing() && !MatList[3].getStudentID().equals(User.getID()))
        {
            mat4Btn.setEnabled(false);
        }

        mat1Btn.addActionListener(this);
        mat2Btn.addActionListener(this);
        mat3Btn.addActionListener(this);
        mat4Btn.addActionListener(this);

        JPanel mat1Pane = new JPanel();
        mat1Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        mat1Pane.add(mat1info);
        mat1Pane.add(mat1Btn);

        JPanel mat2Pane = new JPanel();
        mat2Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        mat2Pane.add(mat2info);
        mat2Pane.add(mat2Btn);

        JPanel mat3Pane = new JPanel();
        mat3Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        mat3Pane.add(mat3info);
        mat3Pane.add(mat3Btn);

        JPanel mat4Pane = new JPanel();
        mat4Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        mat4Pane.add(mat4info);
        mat4Pane.add(mat4Btn);

        matSelectPage.add(mat1Pane);
        matSelectPage.add(mat2Pane);
        matSelectPage.add(mat3Pane);
        matSelectPage.add(mat4Pane);

        currentPanel.add(matSelectPage, BorderLayout.CENTER);
    }

    private void UmmSelectPage()
    {
        ummSelectPage = new B_UmmSelect();
        UmmList = UmmObj.dataGetter();

        B_UmmInfo umm1info = new B_UmmInfo(UmmList[0].getObjID(), UmmList[0].getDateStart(),
                UmmList[0].getDateHaveto(), UmmList[0].getIsBorrowing());
        B_UmmInfo umm2info = new B_UmmInfo(UmmList[1].getObjID(), UmmList[1].getDateStart(),
                UmmList[1].getDateHaveto(), UmmList[1].getIsBorrowing());
        B_UmmInfo umm3info = new B_UmmInfo(UmmList[2].getObjID(), UmmList[2].getDateStart(),
                UmmList[2].getDateHaveto(), UmmList[2].getIsBorrowing());
        B_UmmInfo umm4info = new B_UmmInfo(UmmList[3].getObjID(), UmmList[3].getDateStart(),
                UmmList[3].getDateHaveto(), UmmList[3].getIsBorrowing());

        umm1Btn = new ButtonForm3(BorrowAble(UmmList[0]));
        if (UmmList[0].getIsBorrowing() && !UmmList[0].getStudentID().equals(User.getID()))
        {
            umm1Btn.setEnabled(false);
        }
        umm2Btn = new ButtonForm3(BorrowAble(UmmList[1]));
        if (UmmList[1].getIsBorrowing() && !UmmList[1].getStudentID().equals(User.getID()))
        {
            umm2Btn.setEnabled(false);
        }
        umm3Btn = new ButtonForm3(BorrowAble(UmmList[2]));
        if (UmmList[2].getIsBorrowing() && !UmmList[2].getStudentID().equals(User.getID()))
        {
            umm3Btn.setEnabled(false);
        }
        umm4Btn = new ButtonForm3(BorrowAble(UmmList[3]));
        if (UmmList[3].getIsBorrowing() && !UmmList[3].getStudentID().equals(User.getID()))
        {
            umm4Btn.setEnabled(false);
        }

        umm1Btn.addActionListener(this);
        umm2Btn.addActionListener(this);
        umm3Btn.addActionListener(this);
        umm4Btn.addActionListener(this);

        JPanel umm1Pane = new JPanel();
        umm1Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        umm1Pane.add(umm1info);
        umm1Pane.add(umm1Btn);

        JPanel umm2Pane = new JPanel();
        umm2Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        umm2Pane.add(umm2info);
        umm2Pane.add(umm2Btn);

        JPanel umm3Pane = new JPanel();
        umm3Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        umm3Pane.add(umm3info);
        umm3Pane.add(umm3Btn);

        JPanel umm4Pane = new JPanel();
        umm4Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        umm4Pane.add(umm4info);
        umm4Pane.add(umm4Btn);

        ummSelectPage.add(umm1Pane);
        ummSelectPage.add(umm2Pane);
        ummSelectPage.add(umm3Pane);
        ummSelectPage.add(umm4Pane);

        currentPanel.add(ummSelectPage, BorderLayout.CENTER);
    }

    private void MedSelectPage()
    {
        medSelectPage = new B_MedSelect();
        MedList = MedObj.dataGetter();

        B_MedInfo med1info = new B_MedInfo("반창고", MedList[0].getNum());
        B_MedInfo med2info = new B_MedInfo("파 스", MedList[1].getNum());
        B_MedInfo med3info = new B_MedInfo("감기약", MedList[2].getNum());

        med1Btn = new ButtonForm3("./image/BorrowAble");
        med2Btn = new ButtonForm3((MedList[1].getIsAvailble() ? "./image/BorrowAble" : "./image/BorrowUnable"));
        med3Btn = new ButtonForm3((MedList[2].getIsAvailble() ? "./image/BorrowAble" : "./image/BorrowUnable"));
        if (!MedList[0].getIsAvailble())
        {
            med1Btn = new ButtonForm3("./image/BorrowUnable");
            med1Btn.setEnabled(false);
        }
        if (!MedList[1].getIsAvailble())
        {
            med2Btn = new ButtonForm3("./image/BorrowUnable");
            med2Btn.setEnabled(false);
        }
        if (!MedList[2].getIsAvailble())
        {
            med3Btn = new ButtonForm3("./image/BorrowUnable");
            med3Btn.setEnabled(false);
        }


        med1Btn.addActionListener(this);
        med2Btn.addActionListener(this);
        med3Btn.addActionListener(this);

        JPanel med1Pane = new JPanel();
        med1Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        med1Pane.add(med1info);
        med1Pane.add(med1Btn);

        JPanel med2Pane = new JPanel();
        med2Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        med2Pane.add(med2info);
        med2Pane.add(med2Btn);

        JPanel med3Pane = new JPanel();
        med3Pane.setBackground(BGCOLOR);
        setLayout(new FlowLayout());
        med3Pane.add(med3info);
        med3Pane.add(med3Btn);

        medSelectPage.add(med1Pane);
        medSelectPage.add(med2Pane);
        medSelectPage.add(med3Pane);

        currentPanel.add(medSelectPage, BorderLayout.CENTER);
    }

    // Mat isBorrowing boolean function
    private String BorrowAble(BorrowObject obj)
    {
        String img;
        if (!obj.getIsBorrowing())
        {
            img = "./image/BorrowAble";
            return img;
        }
        else
        {
            img = "./image/BorrowUnable";
            return img;
        }
    }

    private void BorrowPage(BorrowObject BObj)
    {
        borrowComfirmPage = new B_BorrowComfirm(BObj, User);
        OKComfirmBtn = new ButtonForm("./image/codeVerifyButton");
        OKComfirmBtn.addActionListener(this);

        borrowComfirmPage.add(OKComfirmBtn);

        currentPanel.add(borrowComfirmPage, BorderLayout.CENTER);
    }

    private void UsePage(String medname, int count)
    {// matobj로 바꿀것

        useComfirmPage = new B_UseComfirm(medname, count);
        MedOKComfirmBtn = new ButtonForm("./image/codeVerifyButton");
        MedOKComfirmBtn.addActionListener(this);

        useComfirmPage.add(MedOKComfirmBtn);

        currentPanel.add(useComfirmPage, BorderLayout.CENTER);
    }

    private void ReturnPage(BorrowObject BObj)
    {// matobj로 바꿀것
        returnPage = new B_ReturnPage(BObj, User);
        OKReturnBtn = new ButtonForm("./image/codeVerifyButton");
        OKReturnBtn.addActionListener(this);

        returnPage.add(OKReturnBtn);

        currentPanel.add(returnPage, BorderLayout.CENTER);
    }

    private void TermsPage(String filedirectory)
    {// 에베베
        termsPage = new Terms(filedirectory);

        JPanel BtnWrapper = new JPanel(new FlowLayout());
        BtnWrapper.setBackground(BGCOLOR);
        JPanel CheckWrapper = new JPanel(new FlowLayout());
        CheckWrapper.setBackground(BGCOLOR);

        if (filedirectory.equals("./data/MatTerms.txt"))
        {
            if (Agree)
            {
                AgreeBtn = new ButtonForm4("./image/Agreeclick");
            }
            else
            {
                AgreeBtn = new ButtonForm4("./image/Agree");
            }
            AgreeBtn.addActionListener(this);

            OKTermsBtn = new ButtonForm("./image/codeVerifyButton");
            OKTermsBtn.addActionListener(this);
            if (!Agree)
            {
                OKTermsBtn.setEnabled(false);
            }
            CheckWrapper.add(AgreeBtn);
            BtnWrapper.add(OKTermsBtn);
        }
        else if (filedirectory.equals("./data/MedTerms.txt"))
        {
            if (MedAgree)
            {
                MedAgreeBtn = new ButtonForm4("./image/Agreeclick");
            }
            else
            {
                MedAgreeBtn = new ButtonForm4("./image/Agree");
            }
            MedAgreeBtn.addActionListener(this);

            MedOKTermsBtn = new ButtonForm("./image/codeVerifyButton");
            MedOKTermsBtn.addActionListener(this);
            if (!MedAgree)
            {
                MedOKTermsBtn.setEnabled(false);
            }
            CheckWrapper.add(MedAgreeBtn);
            BtnWrapper.add(MedOKTermsBtn);
        }

        JPanel Wrapper = new JPanel(new GridLayout(2, 1));
        Wrapper.setBackground(BGCOLOR);

        Wrapper.add(CheckWrapper);
        Wrapper.add(BtnWrapper);
        termsPage.add(Wrapper);
        termsPage.setPreferredSize(new Dimension(500, 990));

        currentPanel.add(termsPage);
    }

    private void ReturnGuidePage()
    {
        returnguidePage = new B_ReturnGuidePage();

        if (AgreeR)
        {
            AgreeRBtn = new ButtonForm4("./image/Agreeclick");
        }
        else
        {
            AgreeRBtn = new ButtonForm4("./image/Agree");
        }
        AgreeRBtn.addActionListener(this);

        OKReturnGuideBtn = new ButtonForm("./image/codeVerifyButton");
        OKReturnGuideBtn.addActionListener(this);
        if (!AgreeR)
        {
            OKReturnGuideBtn.setEnabled(false);
        }
        JPanel Wrapper = new JPanel(new GridLayout(2, 1));
        Wrapper.setBackground(BGCOLOR);
        JPanel W2 = new JPanel(new FlowLayout());
        W2.setBackground(BGCOLOR);
        W2.add(AgreeRBtn);
        Wrapper.add(W2);
        JPanel W1 = new JPanel(new FlowLayout());
        W1.setBackground(BGCOLOR);

        W1.add(OKReturnGuideBtn);
        Wrapper.add(W1);
        returnguidePage.add(Wrapper);
        returnguidePage.setPreferredSize(new Dimension(500, 990));

        currentPanel.add(returnguidePage);
    }

    private void CompletePage(String completej, BorrowObject b)//, BorrowObject borrowOb
    {// obj 받기->Haveto date 받기
        completePage = new CompletePage(completej, b);//, borrowObj
        gotoMainBtn = new ButtonForm("./image/gotoMainButton");
        gotoMainBtn.addActionListener(this);

        completePage.add(gotoMainBtn);

        currentPanel.add(completePage, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        currentPanel.removeAll();

        if (e.getSource() == matBtn)
        {
            OBJTYPE = 1;
            MatSelectPage();
        }
        if (e.getSource() == ummBtn)
        {
            OBJTYPE = 2;
            UmmSelectPage();
        }
        if (e.getSource() == medBtn)
        {
            OBJTYPE = 3;
            MedSelectPage();
        }
        if (e.getSource() == mat1Btn)
        {
            boolean borrowing = MatList[0].getIsBorrowing();//objisborrowing
            if (borrowing)
            {
                ReturnPage(MatList[0]);//objID
            }
            else
            {
                BorrowPage(MatList[0]);
            }
            OBJNUMER = 1;
        }
        if (e.getSource() == mat2Btn)
        {
            boolean borrowing = MatList[1].getIsBorrowing();//objisborrowing
            if (borrowing)
            {
                ReturnPage(MatList[1]);//objID
            }
            else
            {
                BorrowPage(MatList[1]);
            }
            OBJNUMER = 2;
        }
        if (e.getSource() == mat3Btn)
        {
            boolean borrowing = MatList[2].getIsBorrowing();//objisborrowing
            if (borrowing)
            {
                ReturnPage(MatList[2]);//objID
            }
            else
            {
                BorrowPage(MatList[2]);
            }
            OBJNUMER = 3;
        }
        if (e.getSource() == mat4Btn)
        {
            boolean borrowing = MatList[3].getIsBorrowing();//objisborrowing
            if (borrowing)
            {
                ReturnPage(MatList[3]);//objID
            }
            else
            {
                BorrowPage(MatList[3]);
            }
            OBJNUMER = 4;
        }
        if (e.getSource() == umm1Btn)
        {
            boolean borrowing = UmmList[0].getIsBorrowing();//objisborrowing
            if (borrowing)
            {
                ReturnPage(UmmList[0]);//objID
            }
            else
            {
                BorrowPage(UmmList[0]);
            }
            OBJNUMER = 1;
        }
        if (e.getSource() == umm2Btn)
        {
            boolean borrowing = UmmList[1].getIsBorrowing();//objisborrowing
            if (borrowing)
            {
                ReturnPage(UmmList[1]);//objID
            }
            else
            {
                BorrowPage(UmmList[1]);
            }
            OBJNUMER = 2;
        }
        if (e.getSource() == umm3Btn)
        {
            boolean borrowing = UmmList[2].getIsBorrowing();//objisborrowing
            if (borrowing)
            {
                ReturnPage(UmmList[2]);//objID
            }
            else
            {
                BorrowPage(UmmList[2]);
            }
            OBJNUMER = 3;
        }
        if (e.getSource() == umm4Btn)
        {
            boolean borrowing = UmmList[4].getIsBorrowing();//objisborrowing
            if (borrowing)
            {
                ReturnPage(UmmList[3]);//objID
            }
            else
            {
                BorrowPage(UmmList[3]);
            }
            OBJNUMER = 4;
        }
        if (e.getSource() == OKComfirmBtn)
        {
            TermsPage("./data/MatTerms.txt");
        }
        if (e.getSource() == OKReturnBtn)
        {
            ReturnGuidePage();
        }
        if (e.getSource() == AgreeBtn)
        {
            Agree = !Agree;
            TermsPage("./data/MatTerms.txt");
        }
        if (e.getSource() == AgreeRBtn)
        {
            AgreeR = !AgreeR;
            ReturnGuidePage();
        }
        if (e.getSource() == OKTermsBtn)
        {
            Date tmp;
            if (OBJTYPE == 1)
            {
                tmp = new Date(MatList[OBJNUMER - 1].getDateEnd());
                MatList[OBJNUMER - 1] = new MatObj(OBJNUMER, new Date(), tmp, User.getID());
                ChangedData = "Mat  " + MatList[OBJNUMER - 1].toFileString();
                CompletePage("borrowComplete", MatList[OBJNUMER - 1]);
            }
            else if (OBJTYPE == 2)
            {
                tmp = new Date(UmmList[OBJNUMER - 1].getDateEnd());
                UmmList[OBJNUMER - 1] = new UmmObj(OBJNUMER, new Date(), tmp, User.getID());
                ChangedData = "Umm  " + UmmList[OBJNUMER - 1].toFileString();
                CompletePage("borrowComplete", UmmList[OBJNUMER - 1]);
            }
            else
            {
                System.exit(1212);
            }
        }
        if (e.getSource() == OKReturnGuideBtn)
        {
            Date tmpStart;
            if (OBJTYPE == 1)
            {
                tmpStart = new Date(MatList[OBJNUMER - 1].getDateStart());
                MatList[OBJNUMER - 1] = new MatObj(OBJNUMER, tmpStart, new Date(), User.getID());
                ChangedData = "Mat  " + MatList[OBJNUMER - 1].toFileString();
                CompletePage("returnComplete", MatList[OBJNUMER - 1]);
            }
            else if (OBJTYPE == 2)
            {
                tmpStart = new Date(UmmList[OBJNUMER - 1].getDateStart());
                UmmList[OBJNUMER - 1] = new UmmObj(OBJNUMER, tmpStart, new Date(), User.getID());
                ChangedData = "Umm  " + UmmList[OBJNUMER - 1].toFileString();
                CompletePage("returnComplete", UmmList[OBJNUMER - 1]);
            }
            else
            {
                System.exit(1);
            }

        }

        if (e.getSource() == med1Btn)
        {
            OBJNUMER = 1;
            UsePage("반창고", MedList[0].getNum());
        }
        if (e.getSource() == med2Btn)
        {
            OBJNUMER = 2;
            UsePage("파 스", MedList[1].getNum());
        }
        if (e.getSource() == med3Btn)
        {
            OBJNUMER = 3;
            UsePage("감기약", MedList[2].getNum());
        }
        if (e.getSource() == MedOKComfirmBtn)
        {
            TermsPage("./data/MedTerms.txt");
        }
        if (e.getSource() == MedOKTermsBtn)
        {
            CompletePage("UseMedComplete", new BorrowObject());
            MedList[OBJNUMER - 1].setMed_num(-1);
            ChangedData = "Med  " + MedList[OBJNUMER-1].toFileString();
        }
        if (e.getSource() == MedAgreeBtn)
        {
            MedAgree = !MedAgree;
            TermsPage("./data/MedTerms.txt");
        }
        if (e.getSource() == gotoMainBtn)
        {
            if (OBJTYPE == 1)
            {
                MatDataSetter();
                LogDataSetter(ChangedData);
            }
            else if (OBJTYPE == 2)
            {
                UmmDataSetter();
                LogDataSetter(ChangedData);
            }
            else if (OBJTYPE == 3)
            {
                MedDataSetter();
                LogDataSetter(ChangedData);
            }
            else
            {
                System.exit(1);
            }
            KioskMain k = new KioskMain();
            k.setVisible(true);
            setVisible(false);
        }

        currentPanel.updateUI();
    }

    public void MatDataSetter()
    {
        try
        {
            PrintWriter FileWriter_Mat = new PrintWriter(new FileOutputStream("./data/mat.txt"));
            for (MatObj m : MatList)
            {
                FileWriter_Mat.println(m.toFileString());
            }
            FileWriter_Mat.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void UmmDataSetter()
    {
        try
        {
            PrintWriter FileWriter_Umm = new PrintWriter(new FileOutputStream("./data/umm.txt"));
            for (UmmObj u : UmmList)
            {
                FileWriter_Umm.println(u.toFileString());
            }
            FileWriter_Umm.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
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

    public void LogDataSetter(String newLog)
    {
        ArrayList<String> log = BorrowObject.Getter();
        try
        {
            PrintWriter FileWriter = new PrintWriter(new FileOutputStream("./data/log.txt"));

            for (String s : log)
            {
                FileWriter.println(s);
            }
            FileWriter.println(newLog);

            FileWriter.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
