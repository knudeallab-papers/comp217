package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KioskMain2 extends JFrame implements ActionListener
{
    public static final JPanel currentPanel = new JPanel();
    public static final Color BGCOLOR = new Color(0xededf9);

    private JButton OKBtn;
    private JButton submitBtn;
    private JButton startBtn;

    private Main_startPage startPage;
    private Main_infoPage infoPage;

    public static void main(String[] args)
    {
        KioskMain2 main = new KioskMain2();
        main.setVisible(true);
        return;
    }

    public KioskMain2()
    {
        super("Kiosk Main");
        setSize(660, 990);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(600, 10);
        setResizable(false);
        setLayout(new BorderLayout());

        currentPanel.setBackground(BGCOLOR);

        /*Container test = this.getContentPane();
        test.setBackground(BGCOLOR);
        JPanel viewPanel = new JPanel();
        viewPanel.setBackground(BGCOLOR);
        viewPanel.add(currentPanel);*/
        add(currentPanel);

        startPage();

        setVisible(true);
    }

    private void startPage()
    {
        ImageIcon img = new ImageIcon("./image/background.png");

        startPage = new Main_startPage()
        {
            public void paintComponent(Graphics g)
            {
                g.drawImage(img.getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };

        startBtn = new ButtonForm("./image/start");
        startBtn.addActionListener(this);

        startPage.add(startBtn);

        add(startPage, BorderLayout.CENTER);
    }

    private void infoPage(int errorCode, String name, String ID, String phone)
    {
        // TODO Auto-generated method stub
        String errorStr = "";

        if (errorCode == 1)
        {
            errorStr += "등록되지 않은 이용자입니다";
        }
        else if (errorCode == 0)
        {
            errorStr += "";
        }
        else if (errorCode == -1)
        {
            errorStr += "정보를 모두 입력해주세요";
        }
        else if (errorCode == -2)
        {
            errorStr += "학번을 다시 입력해주세요";
        }
        else if (errorCode == -3)
        {
            errorStr += "전화번호를 다시 입력해주세요";
        }
        else
        {
            errorStr += "알 수 없는 오류입니다";
        }

        infoPage = new Main_infoPage(errorStr, name, ID, phone);

        submitBtn = new ButtonForm("./image/mailSubmitButton");
        submitBtn.addActionListener(this);

        infoPage.add(submitBtn);

        add(infoPage, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        removeAll();

        if (e.getSource() == startBtn)
        {
            infoPage(0, "", "", "");
        }

        if (e.getSource() == submitBtn)
        {
            String name = infoPage.getName();
            String id = infoPage.getID();
            String phone = infoPage.getPhone();

            if (id.equals("1234567890") && !name.isEmpty() && !phone.isEmpty()
                    && id.length() == 10 && phone.length() == 11)
            {
                //다음 페이지로
            }
            else if (name.length() == 0 || id.length() == 0
                    || phone.length() == 0)
            {
                infoPage(-1, name, id, phone);
            }
            else if (id.length() != 10)
            {
                infoPage(-2, name, id, phone);
            }
            else if (phone.length() != 11)
            {
                infoPage(-3, name, id, phone);
            }
            else
            {
                infoPage(1, name, id, phone);
            }
        }

        currentPanel.updateUI();
    }
}











