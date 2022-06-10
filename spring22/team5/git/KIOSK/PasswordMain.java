package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordMain extends JFrame implements ActionListener
{
    public static final JPanel currentPanel = new JPanel();
    public static final Color BACKGROUND_COLOR = new Color(0xededf9);
    //public ImageIcon IMG = new ImageIcon("./image/background.png");
    private JButton mailSubmitBtn;
    private JButton codeVerifyBtn;
    private JButton gotoMainBtn;
    private JButton changePWBtn;
    private JButton changePWSubmitBtn;
    private MailPage mailPage;
    private CodeVerifyPage codePage;
    private JPanel passwordPage;
    private ChangePage changePage;
    private String verifiedCode = "1234";
    private Student User = null;

    public static void main(String[] args)
    {
        PasswordMain t = new PasswordMain(new Student("2021111873", "김진현", "01023824633", "kimjhyun0627", 82, "0082", false));
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image logo = toolkit.getImage("./image/logo.png");
        t.setIconImage(logo);
        t.setVisible(true);
    }

    public void dataSetter()
    {
        Scanner FileReader_Student = null;
        ArrayList<Student> StudentList = new ArrayList<>();

        try
        {
            FileReader_Student = new Scanner(new FileInputStream("./data/Student.txt"));
            while (FileReader_Student.hasNext())
            {
                String f_ID = FileReader_Student.next();
                String f_name = FileReader_Student.next();
                String f_phone = FileReader_Student.next();
                String f_mail = FileReader_Student.next();
                int f_cabID = FileReader_Student.nextInt();
                String f_cabPW = FileReader_Student.next();
                String f_council = FileReader_Student.next();

                if (f_council.equals("false"))
                {
                    StudentList.add(new Student(f_ID, f_name, f_phone, f_mail, f_cabID, f_cabPW, false));
                }
                else
                {
                    StudentList.add(new Student(f_ID, f_name, f_phone, f_mail, f_cabID, f_cabPW, true));
                }
            }
            FileReader_Student.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try
        {
            PrintWriter FileWriter_Student = new PrintWriter(new FileOutputStream("./data/Student.txt"));
            for (Student s : StudentList)
            {
                if (s.getID().equals(User.getID()))
                {
                    FileWriter_Student.println(User.toFileString());
                    continue;
                }
                FileWriter_Student.println(s.toFileString());
            }
            FileWriter_Student.close();
            System.out.println("success");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public PasswordMain(Student usingStudent)
    {
        super("test");
        setSize(660, 990);
        setLocation(600, 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        this.User = usingStudent;

        currentPanel.setBackground(BACKGROUND_COLOR);

        Container test = this.getContentPane();
        test.setBackground(BACKGROUND_COLOR);
        JPanel viewPanel = new JPanel();
        viewPanel.setBackground(BACKGROUND_COLOR);
        viewPanel.add(currentPanel);
        test.add(viewPanel, BorderLayout.CENTER);

        mailSubmitPage("", "");

        setVisible(true);
    }

    private void mailSubmitPage(String errorMSG, String mailStr)
    {
        mailPage = new MailPage(errorMSG, mailStr);

        mailSubmitBtn = new ButtonForm("./image/mailSubmitButton");
        mailSubmitBtn.addActionListener(this);

        mailPage.add(mailSubmitBtn);

        currentPanel.add(mailPage, BorderLayout.CENTER);
    }

    private void codeVerifyPage(String errorMSG)
    {
        codePage = new CodeVerifyPage(errorMSG);

        codeVerifyBtn = new ButtonForm("./image/codeVerifyButton");
        codeVerifyBtn.addActionListener(this);

        codePage.add(codeVerifyBtn);

        currentPanel.add(codePage, BorderLayout.CENTER);
    }

    private void showPasswordPage(boolean b)
    {
        gotoMainBtn = new ButtonForm("./image/gotoMainButton");
        gotoMainBtn.addActionListener(this);

        if (b)
        {
            passwordPage = new FindPasswordPage(User, true, User.getCabID(), User.getCabPW());

            changePWBtn = new ButtonForm("./image/changePWButton");
            changePWBtn.addActionListener(this);
            JPanel wrapperPanel = new JPanel(new FlowLayout());
            wrapperPanel.add(changePWBtn);
            wrapperPanel.add(gotoMainBtn);
            wrapperPanel.setBackground(BACKGROUND_COLOR);

            passwordPage.add(wrapperPanel);
        }
        else
        {
            passwordPage = new FindPasswordErrorPage(User);
            passwordPage.add(gotoMainBtn);
        }
        currentPanel.add(passwordPage, BorderLayout.CENTER);
    }

    private void showChangePage(String errorMSG, String PWWritten)
    {
        changePage = new ChangePage(errorMSG, PWWritten);

        changePWSubmitBtn = new ButtonForm("./image/changePWButton");
        changePWSubmitBtn.addActionListener(this);

        changePage.add(changePWSubmitBtn);

        currentPanel.add(changePage, BorderLayout.CENTER);
    }

    private void showChangedPasswordPage()
    {
        passwordPage = new FindPasswordPage(User, false, User.getCabID(), User.getCabPW());
        gotoMainBtn = new ButtonForm("./image/gotoMainButton");
        gotoMainBtn.addActionListener(this);
        passwordPage.add(gotoMainBtn);

        currentPanel.add(passwordPage, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        currentPanel.removeAll();

        if (e.getSource() == mailSubmitBtn)
        {
            String str = mailPage.getMailAddress();

            if (str.equals(User.getWebMailAddress()))
            {
                mailSender mail = new mailSender(str);
                verifiedCode = mail.getCode();
                codeVerifyPage("");
            }
            else if (str.length() == 0)
            {
                mailSubmitPage("웹메일을 입력해주세요", "");
            }
            else
            {
                mailSubmitPage("등록되지 않은 웹메일입니다", str);
            }
        }

        if (e.getSource() == codeVerifyBtn)
        {
            String str = codePage.getCodeInput();

            if (str.equals(verifiedCode))
            {
                showPasswordPage(true);
            }
            else if (str.length() == 0)
            {
                codeVerifyPage("인증코드를 입력해주세요");
            }
            else if (str.equals("4321"))
            {
                showPasswordPage(false);
            }
            else
            {
                codeVerifyPage("잘못된 인증코드입니다");
            }
        }

        if (e.getSource() == changePWBtn)
        {
            showChangePage("", "");
        }

        if (e.getSource() == changePWSubmitBtn)
        {
            String str = changePage.getChangedPassword();

            if (str.length() == 0)
            {
                showChangePage("비밀번호를 입력해주세요", str);
            }
            else if (Cabinet.isValidPW(str))
            {
                String popupBtns[] = {"네", "아니오", "초기화"};
                int response = PopUpForm.showOptionDialog(popupBtns, "./image/ChangePWSubmitAlert.png", 45, 50, "비밀번호 변경 확인", "비밀번호를 " + str + "로 변경하시겠습니까?", "네");

                if (response == 0)
                {
                    User.setCabPW(str);
                    showChangedPasswordPage();//다음장으로
                }
                else if (response == 1)
                {
                    showChangePage("", str);
                }
                else
                {
                    showChangePage("", "");
                }
            }
            else
            {
                showChangePage("잘못된 비밀번호입니다", str);
            }
        }

        if (e.getSource() == gotoMainBtn)
        {
            dataSetter();
            KioskMain k = new KioskMain();
            k.setVisible(true);
            setVisible(false);
        }

        currentPanel.updateUI();
    }
}