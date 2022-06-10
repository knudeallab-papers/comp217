package TeamProject;

import javax.swing.*;
import java.awt.*;

public class B_UseComfirm extends JPanel
{

    public B_UseComfirm(String medName, int count)
    {////obj로 바꿀것
        super();
        setBackground(BorrowMain.BGCOLOR);
        setLayout(new GridLayout(8, 1));

        centerBoldLabel title = new centerBoldLabel("대여 정보 확인", 42);

        JPanel cp = new JPanel(new GridLayout(2,1));
        cp.setBackground(BorrowMain.BGCOLOR);

        JLabel nObjLB = new centerBoldLabel("상품명 : "+medName, 30);//자녀class판단필요
        //nObjLB.setFont(new Font("IM혜민 regular", Font.PLAIN, 30));
        JLabel nObjnum = new centerBoldLabel("남은 수량 : " + count,30);//자녀class판단필요
        //nObjnum.setFont(new Font("IM혜민 regular", Font.PLAIN, 30));

        JLabel correct = new JLabel("물품을 대여하시겠습니까?");
        correct.setFont(new Font("IM혜민 regular", Font.PLAIN, 30));
        correct.setBackground(BorrowMain.BGCOLOR);

        //JLabel dates = new JLabel(Bobj.getDateStart().toString());
        //JLabel dateh = new JLabel("~ "+Bobj.getDateHaveto().toString());

        //임시
        cp.add(nObjLB);
        cp.add(nObjnum);

        //add(new EmptyPanel());
        add(new EmptyPanel());
        add(title);
        add(new EmptyPanel());
        add(cp);
        add(new EmptyPanel());
        add(new EmptyPanel());
        add(correct);
    }

}
