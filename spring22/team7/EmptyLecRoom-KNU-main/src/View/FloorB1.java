package View;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static View.FirstPage.*;

public class FloorB1 extends JFrame implements ActionListener{

    private JLabel b1StateText = new JLabel("");
    private JPanel b1State;

    public static Color emptyBlue = new Color(91,173,255);
    public static Color fullRed = new Color(255,74,74);

    public static void main(String[] args) {
        new Floor3();
    }

    public FloorB1(){

        super("B1 Floor");
        setSize(FirstPage.WIDTH,FirstPage.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); //화면 중앙
        setIconImage(knuLogo.getImage());

        b1State = new JPanel();
        b1State.setBackground(Color.WHITE);
        b1State.setLayout(null);

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


        b1StateText.setBounds(175,305,400,30);
        b1State.add(b1StateText);

        RoundedButton btn101 = new RoundedButton("B101");
        btn101.setBounds(100, 50, 150, 200);
        btn101.addMouseListener(new mouseOnTheRoom());
        btn101.addActionListener(new makingTimeTableOnClick());
        if(isEmptyFloorRoom("B101")) btn101.setBackground(emptyBlue);
        else btn101.setBackground(fullRed);
        b1State.add(btn101);

        RoundedButton btn102 = new RoundedButton("B102");
        btn102.setBounds(330, 50, 150, 200);
        btn102.addMouseListener(new mouseOnTheRoom());
        btn102.addActionListener(new makingTimeTableOnClick());
        if(isEmptyFloorRoom("B102")) btn102.setBackground(emptyBlue);
        else btn102.setBackground(fullRed);
        b1State.add(btn102);

        add(b1State);
        b1State.setVisible(true);

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
                    break;
                case "2F":
                    new Floor2();
                    setVisible(false);
                    break;
                case "3F":
                    new Floor3();
                    setVisible(false);
                    break;
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
            b1StateText.setText(parsingClass.IsEmptyRoom(btnText, nowDay, nowTime));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            b1StateText.setText("");
        }
    }
}

