import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class addPlan extends JFrame implements ActionListener {
   private boolean flag = true;
   
   JButton ok;
   JTextField planNameText, dayText;
   JTextField startHour,startMin, endHour,endMin;
   JLabel explan, planName, day, start, end;
   
   public static int WIDTH = 100, HEIGHT = 50;
   private Planner plan;
   private String planString;
   private int dayInt, startHourInt, startMinInt;
   private int endHourInt, endMinInt;
   
   public addPlan()
   {
      super("add Plan");
      setSize(330,500);
      setLocation(500,280);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      setLayout(null);
      
      explan = new JLabel("Input Plan Data");
      planNameText = new JTextField(30);
      dayText = new JTextField(1);
      startHour = new JTextField(2);
      startHour.setText("09");
      startMin = new JTextField(2);
      startMin.setText("00");
      endHour = new JTextField(2);
      endHour.setText("22");
      endMin = new JTextField(2);
      endMin.setText("59");
      planName = new JLabel("Plan");
      day = new JLabel("Day");
      start = new JLabel("Start Time");
      end = new JLabel("End Time");
      ok = new JButton("ok");
      ok.addActionListener(this);
      
      explan.setBounds(100, 30, WIDTH * 2, HEIGHT);
      planName.setBounds(30, 100, WIDTH, HEIGHT);
      planNameText.setBounds(170, 100, WIDTH, HEIGHT);
      day.setBounds(30, 150, WIDTH, HEIGHT);
      dayText.setBounds(170, 150, WIDTH, HEIGHT);
      start.setBounds(30, 200, WIDTH, HEIGHT);
      startHour.setBounds(170, 200, WIDTH / 2 - 5, HEIGHT);
      startMin.setBounds(225, 200, WIDTH / 2 - 5, HEIGHT);
      end.setBounds(30, 250, WIDTH, HEIGHT);
      endHour.setBounds(170, 250, WIDTH / 2 - 5, HEIGHT);
      endMin.setBounds(225, 250, WIDTH / 2 - 5, HEIGHT);
      ok.setBounds(100, 320, WIDTH, HEIGHT);
      
      add(explan);
      add(planName);
      add(planNameText);
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
         planString = planNameText.getText();
         dayInt = Integer.valueOf(dayText.getText());
         startHourInt = Integer.valueOf(startHour.getText());
         startMinInt = Integer.valueOf(startMin.getText());
         endHourInt = Integer.valueOf(endHour.getText());
         endMinInt = Integer.valueOf(endMin.getText());
         
         //System.out.println(lecture.getDay() + lecture.getLecName());
         Planner.planList[Planner.currentNum++] = new PlanItem(planString, dayInt, 
               new Time(startHourInt, startMinInt), 
               new Time(endHourInt, endMinInt));
         flag = false;
      }
      dispose();
   }
   
   public boolean getFlag() { return flag; }
   public void setFlag( boolean f ) { this.flag = f; }
}