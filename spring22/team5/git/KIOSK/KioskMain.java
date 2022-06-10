package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KioskMain extends JFrame implements ActionListener
{
    public static final JPanel currentPanel = new JPanel();
    public static final Color BGCOLOR = new Color(0xededf9);
    private JPanel Wrapper;

    private JButton OKBtn;
    private JButton submitBtn;
    private JButton startBtn;
    private JButton borrowBtn;
    private JButton PWBtn;
    private JButton SettingBtn;
    private JButton mainBtn;
    private Setting_villainPage villainPage;
    private Main_startPage startPage;
    private Main_infoPage infoPage;
    private Student User;


    public static void main(String[] args)
    {
        KioskMain main = new KioskMain();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image logo = toolkit.getImage("./image/logo.png");
        main.setIconImage(logo);
        main.setVisible(true);
        return;
    }

    public KioskMain()
    {
        super("Kiosk Main");
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

        startPage();
        //selectPage();

        setVisible(true);
    }
    public KioskMain(Student user)
    {
        super("Kiosk Main");
        setSize(660, 990);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(600, 10);
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

        selectPage(User.getCouncil());
        //selectPage();

        setVisible(true);
    }

    private void startPage()
    {
        startPage = new Main_startPage();

        startBtn = new ButtonForm("./image/start");
        startBtn.addActionListener(this);
        startBtn.setBackground(new Color(255, 255, 255, 0));
        Wrapper = new JPanel(new GridLayout(2, 1));
        Wrapper.setBackground(new Color(255, 255, 255, 0));
        Wrapper.add(startBtn);
        Wrapper.add(new EmptyPanel());
        add(Wrapper, BorderLayout.SOUTH);

        currentPanel.add(startPage, BorderLayout.CENTER);
    }

    private void infoPage(String errorStr, String name, String ID, String phone)
    {
        infoPage = new Main_infoPage(errorStr, name, ID, phone);

        submitBtn = new ButtonForm("./image/mailSubmitButton");
        submitBtn.addActionListener(this);

        infoPage.add(submitBtn);

        currentPanel.add(infoPage, BorderLayout.CENTER);
    }

    private void selectPage(boolean council)
    {
        Main_selectPage selectpage = new Main_selectPage();

        borrowBtn = new ButtonForm5("./image/selectRent");
        borrowBtn.addActionListener(this);
        PWBtn = new ButtonForm5("./image/selectPW");
        PWBtn.addActionListener(this);
        SettingBtn = new ButtonFormSQ("./image/setting");
        SettingBtn.addActionListener(this);

        selectpage.add(borrowBtn);
        selectpage.add(PWBtn);
        selectpage.add(new EmptyPanel());
        selectpage.add(SettingBtn);
        if(!council)
        {
            SettingBtn.setEnabled(false);
        }

        currentPanel.add(selectpage);
    }

    private void villain()
    {
        villainPage = new Setting_villainPage();
        mainBtn = new ButtonForm("./image/gotoMainButton");
        mainBtn.addActionListener(this);

        villainPage.add(mainBtn);

        currentPanel.add(villainPage, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        currentPanel.removeAll();

        if (e.getSource() == startBtn)
        {
            remove(Wrapper);
            infoPage("", "", "", "");
        }

        if (e.getSource() == submitBtn)
        {
            String name = infoPage.getName();
            String id = infoPage.getID();
            String phone = infoPage.getPhone();

            User = Student.dataGetter(id, name, phone);

            if (id.length() == 10 && !name.isEmpty() && phone.length() == 11)
            {
                if (User != null)
                {
                    selectPage(User.getCouncil());
                }
                else
                {
                    infoPage("등록되지 않은 이용자입니다", name, id, phone);
                }
            }
            else if (name.length() == 0 || id.length() == 0 || phone.length() == 0)
            {
                infoPage("정보를 모두 입력해주세요", name, id, phone);
            }
            else if (id.length() != 10)
            {
                infoPage("학번을 다시 입력해주세요", name, id, phone);
            }
            else if (phone.length() != 11)
            {
                infoPage("전화번호를 다시 입력해주세요", name, id, phone);
            }
            else
            {
                infoPage("알 수 없는 오류입니다", name, id, phone);
            }
        }

        if (e.getSource() == borrowBtn)
        {
            BorrowMain b = new BorrowMain(User);
            b.setVisible(true);
            setVisible(false);
        }
        if (e.getSource() == PWBtn)
        {
            PasswordMain p = new PasswordMain(User);
            p.setVisible(true);
            setVisible(false);
        }
        if (e.getSource() == SettingBtn)
        {
            if (User.getCouncil())
            {
                SettingMain s = new SettingMain(User);
                s.setVisible(true);
                setVisible(false);
            }
            else
            {
                villain();
            }
        }
        if (e.getSource() == mainBtn)
        {
            KioskMain k = new KioskMain();
            k.setVisible(true);
            setVisible(false);
        }

        currentPanel.updateUI();
    }
}




