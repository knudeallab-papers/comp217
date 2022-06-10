package TeamProject;

import java.awt.*;

import javax.swing.*;

public class CompletePage extends JPanel
{
    public CompletePage(String complete, BorrowObject b)
    {
        super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        if (complete.equals("borrowComplete"))
        {
            centerBoldLabel title = new centerBoldLabel("대여 완료", 42);

            Date dateHaveto = b.getDateHaveto();
            JLabel HavetoGuide = new JLabel(dateHaveto.toString() + "까지 반납입니다.");
            HavetoGuide.setFont(new Font("IM혜민 regular", Font.PLAIN, 24));
            HavetoGuide.setForeground(Color.RED);

            //카운터

            add(new EmptyPanel());
            add(title);
            add(new EmptyPanel());
            add(HavetoGuide);
            add(new EmptyPanel());
            add(new EmptyPanel());
        }
        else if (complete.equals("returnComplete"))
        {
            centerBoldLabel title = new centerBoldLabel("반납 완료", 42);

            //카운터

            add(new EmptyPanel());
            add(title);
            add(new EmptyPanel());
            add(new EmptyPanel());
            add(new EmptyPanel());
            add(new EmptyPanel());
        }
        else if (complete.equals("UseMedComplete"))
        {
            centerBoldLabel title = new centerBoldLabel("사용 완료", 42);

            JLabel HavetoGuide = new JLabel("상비약 물품은 컴퓨터학부 사물함 안에 있습니다.");
            HavetoGuide.setFont(new Font("IM혜민 regular", Font.PLAIN, 24));

            //카운터

            add(new EmptyPanel());
            add(title);
            add(new EmptyPanel());
            add(HavetoGuide);
            add(new EmptyPanel());
            add(new EmptyPanel());
        }


    }

}
