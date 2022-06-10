import java.io.Serializable;

public class Lecture implements Serializable{
   // time has 7 value startHour,Min endHour,Min duringHour,Min ...
   protected String lectureName;
   protected String professor;
   protected int day; // 1 = Mon, 2 = Tues...
   
   private Time start; //input Hour, Min
   private Time end; //input Hour, Min
   private Time during; //Only input time
   
   
   public Lecture() {
      lectureName = null;
      professor = null;
      day = 0;
      start = new Time(0,0);
      end = new Time(0,0);
      during = new Time(0);
   }
   
   public Lecture(String ln, String prof, int d, Time st, Time end) {
      lectureName = ln;
      professor = prof;
      
      if(d >= 1 && d <= 5) { // day check
         day = d;
      }
      else
         System.out.println("Error input day");
      
      start = st;
      this.end = end;
      
      if(start.getTotalMin() < end.getTotalMin()) { //Time check
         this.during = new Time( end.getTotalMin() - start.getTotalMin());
      }
      else
         System.out.println("Error input Time");
   }
   
   public String getLecName() { return lectureName; }
   public String getprofessor() { return professor; }
   public int getDay() { return day; }
   public String getDayString() { 
      if(day == 1)
         return "Monday";
      else if (day == 2)
         return "Tuesday";
      else if (day == 3)
         return "Wednesday";
      else if (day == 4)
         return "Thursday";
      else if (day == 5)
         return "Friday";
      else
         return "";
   }
   public Time getStart() { return start; }
   public Time getEnd() { return end; }
   public Time getDuring() { return during; }
   
}