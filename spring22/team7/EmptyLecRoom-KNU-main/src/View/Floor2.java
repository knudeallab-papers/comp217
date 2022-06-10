package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static View.FirstPage.*;

public class Floor2 extends JFrame implements ActionListener{

    private JLabel secondStateText = new JLabel("");
    private JPanel secondState;
    ImageIcon t1 = new ImageIcon("./img/t1.png"); //
    ImageIcon t2 = new ImageIcon("./img/t2.png"); //
    ImageIcon underHall = new ImageIcon("./img/underHall.png");
    ImageIcon toi = new ImageIcon("./img/toi.png");

    public static Color emptyBlue = new Color(91,173,255);
    public static Color fullRed = new Color(255,74,74);

    public static void main(String[] args) {
        new Floor3();
    }

    public Floor2(){

        super("2nd Floor");
        setSize(FirstPage.WIDTH,FirstPage.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); //화면 중앙
        setIconImage(knuLogo.getImage());

        secondState = new JPanel();
        secondState.setBackground(Color.WHITE);
        secondState.setLayout(null);

        JMenu menu = new JMenu("Select");

        JMenuItem B1 = new JMenuItem("B1");
        B1.addActionListener(new MoveMenu());
        menu.add(B1);

        JMenuItem second = new JMenuItem("2F");
        second.addActionListener(new MoveMenu());
        menu.add(second);

        JMenuItem third = new JMenuItem("3F");
        third.addActionListener(new MoveMenu());
        menu.add(third);

        JMenuBar bar = new JMenuBar();
        bar.add(menu);

        setJMenuBar(bar);


        secondStateText.setBounds(175,305,400,30);
        secondState.add(secondStateText);

        RoundedButton btn201 = new RoundedButton("201");
        btn201.setBounds(20, 20, 100, 130);
        btn201.addMouseListener(new mouseOnTheRoom());
        btn201.addActionListener(new makingTimeTableOnClick());
        if(isEmptyFloorRoom("201")) btn201.setBackground(emptyBlue);
        else btn201.setBackground(fullRed);

        secondState.add(btn201);

        RoundedButton btn209 = new RoundedButton("209");
        btn209.setBounds(20, 160, 100, 130);
        secondState.add(btn209);

        RoundedButton btn217 = new RoundedButton("217");
        btn217.setBounds(20, 300, 100, 100);
        secondState.add(btn217);

        RoundedButton btn255 = new RoundedButton("255");
        btn255.setBounds(130, 20, 230, 100);
        btn255.addMouseListener(new mouseOnTheRoom());
        btn255.addActionListener(new makingTimeTableOnClick());
        if(isEmptyFloorRoom("255")) btn255.setBackground(emptyBlue);
        else btn255.setBackground(fullRed);
        secondState.add(btn255);

        RoundedButton btn254 = new RoundedButton("254");
        btn254.setBounds(370, 20, 70, 100);
        btn254.addMouseListener(new mouseOnTheRoom());
        btn254.addActionListener(new makingTimeTableOnClick());
        if(isEmptyFloorRoom("254")) btn254.setBackground(emptyBlue);
        else btn254.setBackground(fullRed);
        secondState.add(btn254);

        RoundedButton btn251 = new RoundedButton("251");
        btn251.setBounds(450, 20, 100, 90);
        btn251.addMouseListener(new mouseOnTheRoom());
        btn251.addActionListener(new makingTimeTableOnClick());
        if(isEmptyFloorRoom("251")) btn251.setBackground(emptyBlue);
        else btn251.setBackground(fullRed);
        secondState.add(btn251);

        RoundedButton btn248 = new RoundedButton("248");
        btn248.setBounds(450, 120, 100, 90);
        btn248.addMouseListener(new mouseOnTheRoom());
        btn248.addActionListener(new makingTimeTableOnClick());
        if(isEmptyFloorRoom("248")) btn248.setBackground(emptyBlue);
        else btn248.setBackground(fullRed);
        secondState.add(btn248);

        RoundedButton btn245 = new RoundedButton("245");
        btn245.setBounds(450, 220, 100, 90);
        btn245.addMouseListener(new mouseOnTheRoom());
        btn245.addActionListener(new makingTimeTableOnClick());
        if(isEmptyFloorRoom("245")) btn245.setBackground(emptyBlue);
        else btn245.setBackground(fullRed);
        secondState.add(btn245);

        RoundedButton btn242 = new RoundedButton("242");
        btn242.setBounds(450, 320, 100, 90);
        secondState.add(btn242);

        JLabel btntoi = new JLabel(toi);
        btntoi.setBounds(140, 140, 290, 150);
        secondState.add(btntoi);

        JLabel hallLabel = new JLabel(underHall);
        hallLabel.setBounds(140, 350, 290, 150);
        secondState.add(hallLabel);


        add(secondState);
        secondState.setVisible(true);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class MoveMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "B1":
                    new FloorB1();
                    setVisible(false);
                    break;
                case "2F":
                    break;
                case "3F":
                    new Floor3();
                    setVisible(false);
            }
        }
    }

    public boolean isEmptyFloorRoom(String roomNum){
        return parsingClass.IsEmptyRoomBool(roomNum, nowDay, nowTime);
    }

    private class makingTimeTableOnClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TimeTable tt = new TimeTable(e.getActionCommand(),parsingClass.getClassInfos());

            tt.setVisible(true);
        }
    }
    private class mouseOnTheRoom implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            String btnText = ((RoundedButton) e.getSource()).getText();
            secondStateText.setText(parsingClass.IsEmptyRoom(btnText, nowDay, nowTime));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            secondStateText.setText("");
        }
    }
}
