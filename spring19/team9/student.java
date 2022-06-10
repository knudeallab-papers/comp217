package teamproject_body;
import java.io.Serializable;
import java.util.Arrays;

public class student implements Serializable{
      private String name=null;
      private int[] answers = new int[11];
      private int sex;
      private int dorm;
      private int mate_name;
      public void setname(String name)
      {
         this.name = name;
      }
      public void setmate_name(int mate_name) {
         this.mate_name = mate_name;
      }
      public int getmate_name() {
         return mate_name;
      }
      public void setanswer(int num,int answer) {
         this.answers[num] = answer;
      }
      public void setanswers(int[] answers)
      {
         for(int i=0;i<answers.length;i++)
         {
            this.answers[i] = answers[i];
         }
      }
      public void setsex(int sex) {
         this.sex = sex;
      }
      public void setdorm(int dorm) {
         this.dorm = dorm;
      }
      public int[] getanswers() {
         return this.answers;
      }
      public String getname() {
         return this.name;
      }
      public int getanswer(int num) {
         return this.answers[num];
      }
      public int getsex() {
         return this.sex;
      }
      public int getdorm() {
         return this.dorm;
      }
      public String toString()
      {
         String returner = name;
         for(int i=0;i<11;i++) {
            returner = returner+" "+answers[i];
         }
         returner = returner + " " +sex+" "+dorm;
         return returner;
      }
}
