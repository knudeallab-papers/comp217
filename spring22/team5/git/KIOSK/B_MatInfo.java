package TeamProject;

import javax.swing.*;

import java.awt.*;

public class B_MatInfo extends JPanel
{
    public B_MatInfo(int num, Date datestart, Date dateHaveto, boolean isborrowing)
    {
        super();
        setLayout(new GridLayout(3, 1));
        setBackground(BorrowMain.BGCOLOR);

        if (!isborrowing)
        {
        	//print matname
            centerBoldLabel title = new centerBoldLabel(num + "번 돗자리 : 대여가능     ", 30);
            add(title);
        }
        else
        {
        	//print matname
            centerBoldLabel title = new centerBoldLabel(num + "번 돗자리 : 대여불가     ", 30);
            //print date
            String startDate = datestart.toString();
            String havetoDate = dateHaveto.toString();

            JLabel dates = new JLabel(startDate+" ~");
            //JLabel datee = new JLabel("~");

            dates.setFont(new Font("IM혜민 regular", Font.PLAIN, 24));
            //datee.setFont(new Font("IM혜민 regular", Font.PLAIN, 24));

            add(title);
            add(dates);
            //add(datee);

        }
    }
}