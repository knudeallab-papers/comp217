package View;

import Model.ClassInfo;
import Model.Time;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

import static View.FirstPage.knuLogo;

public class TimeTable extends JFrame{

    private static final int WIDTH = 500;
    private static final int HEIGHT = 800;

    private ArrayList<Color> colors = new ArrayList<Color>(); // Color
    private static final Color newRed = new Color(255, 153 , 153);
    private static final Color newOrange = new Color(255, 204 , 153);
    private static final Color newYellow = new Color(255, 255 , 153);
    private static final Color newGreen = new Color(204, 255 , 153);
    private static final Color newMint = new Color(204, 255 , 229);
    private static final Color newBlue = new Color(204, 255 , 255);
    private static final Color newPurple = new Color(204, 204 , 255);
    private static final Color newPink = new Color(255, 204 , 229);


    private String[] dayAry = {"      월      ", "      화      ", "      수      ", "      목      ", "      금      "}; // 날짜 문자열 배열
    private JPanel[] dayPanelAry = new JPanel[5]; // 날짜 패널

    private String[] timeAry = {"9", "10", "11", "12", "13", "14", "15", "16", "17"}; // 시간 문자열 배열
    private JPanel[] timePanelAry = new JPanel[10]; // 시간 패널
    private JPanel[] monPanelAry = new JPanel[18]; // 시간표 패널
    private JPanel[] tuePanelAry = new JPanel[18]; // 시간표 패널
    private JPanel[] wedPanelAry = new JPanel[18]; // 시간표 패널
    private JPanel[] thuPanelAry = new JPanel[18]; // 시간표 패널
    private JPanel[] friPanelAry = new JPanel[18]; // 시간표 패널

    private GridBagLayout gbLayout;
    private GridBagConstraints gbc = new GridBagConstraints();

    private HashMap<String, Integer> timeHash = new HashMap<String, Integer>(){{ // 시간 -> index변환 해쉬 (시간과 위 시간표 배열을 연결하기 위한 것)
        put("09:00", 0);
        put("09:30", 1);
        put("10:00", 2);
        put("10:30", 3);
        put("11:00", 4);
        put("11:30", 5);
        put("12:00", 6);
        put("12:30", 7);
        put("13:00", 8);
        put("13:30", 9);
        put("14:00", 10);
        put("14:30", 11);
        put("15:00", 12);
        put("15:30", 13);
        put("16:00", 14);
        put("16:30", 15);
        put("17:00", 16);
        put("17:30", 17);
        put("18:00", 18);
    }};


    public TimeTable(String classNum, ArrayList<ClassInfo> classInfos) {
        super(classNum);
        setIconImage(knuLogo.getImage());

        colors.add(newRed);
        colors.add(newOrange);
        colors.add(newYellow);
        colors.add(newGreen);
        colors.add(newMint);
        colors.add(newBlue);
        colors.add(newPurple);
        colors.add(newPink);

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbc.fill= GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        for (int i = 0; i < dayAry.length; i++) { // 날짜 패널 add
            dayPanelAry[i] = new JPanel();
            dayPanelAry[i].setLayout(new BorderLayout());
            JLabel label = new JLabel(dayAry[i]);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            dayPanelAry[i].add(label, BorderLayout.CENTER);
            dayPanelAry[i].setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
            gbInsert(dayPanelAry[i], i + 1, 0, 1, 1 );
        }

        for (int i = 0; i < timeAry.length; i++) { // 시간 패널 add
            timePanelAry[i] = new JPanel();
            timePanelAry[i].setLayout(new BorderLayout());
            JLabel label = new JLabel(timeAry[i]);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            timePanelAry[i].add(label, BorderLayout.CENTER);
            timePanelAry[i].setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
            gbInsert(timePanelAry[i], 0, i + i + (2 * i) + 1, 1, 4);
        }

        for (int i = 0; i < monPanelAry.length; i++) { // 월요일 패널 add
            monPanelAry[i] = new JPanel();
            gbInsert(monPanelAry[i], 1, 1 + (i * 2), 1, 1 );
        }

        for (int i = 0; i < tuePanelAry.length; i++) { // 화요일 패널 add
            tuePanelAry[i] = new JPanel();
            gbInsert(tuePanelAry[i], 2, 1 + (i * 2), 1, 1 );
        }

        for (int i = 0; i < wedPanelAry.length; i++) { // 수요일 패널 add
            wedPanelAry[i] = new JPanel();
            gbInsert(wedPanelAry[i], 3, 1 + (i * 2), 1, 1 );
        }
        for (int i = 0; i < thuPanelAry.length; i++) { // 목요일 패널 add
            thuPanelAry[i] = new JPanel();
            gbInsert(thuPanelAry[i], 4, 1 + (i * 2), 1, 1 );
        }

        for (int i = 0; i < friPanelAry.length; i++) { // 금요일 패널 add
            friPanelAry[i] = new JPanel();
            gbInsert(friPanelAry[i], 5, 1 + (i * 2), 1, 1 );
        }

        String className = "";

        for (ClassInfo classInfo: classInfos) {
            if (classInfo.getClassroom().equals(classNum)) {
                if (classInfo.getClassname().length() > 6) {
                    String beforeClassName = classInfo.getClassname();
                    String add1 = "<html><pre>";
                    String breakLine = "<br>";
                    String add2 = "</pre></html>";
                    className = add1 + beforeClassName.substring(0, 6) + breakLine + beforeClassName.substring(6) + add2;
                } else {
                    className = classInfo.getClassname();
                }
                for (Time time : classInfo.getTimetable()) {
                    coloringTable(className, time.getDay(), time.getStart(), time.getEnd());
                }
                intForColor++;
            } else {
            }
        }

    }

    public void gbInsert(Component c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight= h;
        add(c, gbc);
    }

    private void coloringTable (String className, String day, String start, String end) {
        for (int i = timeHash.get(start); i <= timeHash.get(end); i++) {
            if (i == timeHash.get(start)){
                if (day.equals("월")) {
                    monPanelAry[i].setBackground(setColor());
                    JLabel label = new JLabel(className);
                    monPanelAry[i].add(label);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                } else if (day.equals("화")) {
                    tuePanelAry[i].setBackground(setColor());
                    tuePanelAry[i].setBackground(setColor());
                    JLabel label = new JLabel(className);
                    tuePanelAry[i].add(label);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                } else if (day.equals("수")) {
                    wedPanelAry[i].setBackground(setColor());
                    wedPanelAry[i].setBackground(setColor());
                    JLabel label = new JLabel(className);
                    wedPanelAry[i].add(label);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                } else if (day.equals("목")) {
                    thuPanelAry[i].setBackground(setColor());
                    thuPanelAry[i].setBackground(setColor());
                    JLabel label = new JLabel(className);
                    thuPanelAry[i].add(label);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                } else if (day.equals("금")) {
                    friPanelAry[i].setBackground(setColor());
                    friPanelAry[i].setBackground(setColor());
                    JLabel label = new JLabel(className);
                    friPanelAry[i].add(label);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                } else {
                    System.out.println("Wrong parameter");
                    System.exit(0);
                }
            } else if (i == timeHash.get(end)) {
                break;
            } else {
                if (day.equals("월")) {
                    monPanelAry[i].setBackground(setColor());
                } else if (day.equals("화")) {
                    tuePanelAry[i].setBackground(setColor());
                } else if (day.equals("수")) {
                    wedPanelAry[i].setBackground(setColor());
                } else if (day.equals("목")) {
                    thuPanelAry[i].setBackground(setColor());
                } else if (day.equals("금")) {
                    friPanelAry[i].setBackground(setColor());
                } else {
                    System.out.println("Wrong parameter");
                    System.exit(0);
                }
            }
        }
    }

    private int intForColor = 0;
    public Color setColor() {
        if (intForColor == 7)
            intForColor = 0;
        Color returnColor = colors.get(intForColor);
        return returnColor;
    }

}
