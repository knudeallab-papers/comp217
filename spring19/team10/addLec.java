import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class addLec extends JFrame implements ActionListener {
   private boolean flag = true;
   
   JButton ok;
   JTextField lecNameText, professorText, dayText;
   JTextField startHour,startMin, endHour,endMin;
   JLabel explan, lecName, professor, day, start, end;
   
   public static int WIDTH = 100, HEIGHT = 50;
   private Lecture lecture;
   private String lecString, profString;
   private int dayInt, startHourInt, startMinInt;
   private int endHourInt, endMinInt;
   
   public addLec()
   {
      super("add Lecture");
      setSize(330,500);
      setLocation(500,280);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      setLayout(null);
      
      explan = new JLabel("Input Lecture Data");
      lecNameText = new JTextField(30);
      professorText = new JTextField(20);
      dayText = new JTextField(1);
      startHour = new JTextField(2);
      startHour.setText("09");
      startMin = new JTextField(2);
      startMin.setText("00");
      endHour = new JTextField(2);
      endHour.setText("22");
      endMin = new JTextField(2);
      endMin.setText("59");
      lecName = new JLabel("Lecture");
      professor = new JLabel("Professor");
      day = new JLabel("Day");
      start = new JLabel("Start Time");
      end = new JLabel("End Time");
      ok = new JButton("ok");
      ok.addActionListener(this);
      
      explan.setBounds(100, 30, WIDTH * 2, HEIGHT);
      lecName.setBounds(30, 100, WIDTH, HEIGHT);
      lecNameText.setBounds(170, 100, WIDTH, HEIGHT);
      professor.setBounds(30, 150, WIDTH, HEIGHT);
      professorText.setBounds(170, 150, WIDTH, HEIGHT);
      day.setBounds(30, 200, WIDTH, HEIGHT);
      dayText.setBounds(170, 200, WIDTH, HEIGHT);
      start.setBounds(30, 250, WIDTH, HEIGHT);
      startHour.setBounds(170, 250, WIDTH / 2 - 5, HEIGHT);
      startMin.setBounds(225, 250, WIDTH / 2 - 5, HEIGHT);
      end.setBounds(30, 300, WIDTH, HEIGHT);
      endHour.setBounds(170, 300, WIDTH / 2 - 5, HEIGHT);
      endMin.setBounds(225, 300, WIDTH / 2 - 5, HEIGHT);
      ok.setBounds(100, 370, WIDTH, HEIGHT);
      
      add(explan);
      add(lecName);
      add(lecNameText);
      add(professor);
      add(professorText);
      add(day);
      add(dayText);
      add(start);
      add(startHour);
      add(startMin);
      add(end);
      add(endHour);
      add(endMin);
      add(ok);
      
      setVisible(true);
      
   }
   
   public void actionPerformed(ActionEvent e) {
      String s = e.getActionCommand();
      if( s.equals("ok")) {
         lecString = lecNameText.getText();
         profString = professorText.getText();
         dayInt = Integer.valueOf(dayText.getText());
         startHourInt = Integer.valueOf(startHour.getText());
         startMinInt = Integer.valueOf(startMin.getText());
         endHourInt = Integer.valueOf(endHour.getText());
         endMinInt = Integer.valueOf(endMin.getText());
         
         //System.out.println(lecture.getDay() + lecture.getLecName());
         lectureTimetable.lecList[lectureTimetable.currentNum++] = new Lecture(lecString, profString, dayInt, 
               new Time(startHourInt, startMinInt), 
               new Time(endHourInt, endMinInt));
         flag = false;
      }
      dispose();
   }
   
   public boolean getFlag() { return flag; }
   public void setFlag( boolean f ) { this.flag = f; }
}