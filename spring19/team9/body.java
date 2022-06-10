package teamproject_body;

import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;

public class body {

   public static void main (String[] args) throws IOException {
      String F_name = "dormitory.txt";
      File dormitory = new File(F_name);
      int Line_cnt=0;
      int question_number=10;
      Scanner F_scan = null;
      try {
         F_scan = new Scanner(new FileInputStream(dormitory));
      }catch(FileNotFoundException e) {
         e.printStackTrace();
         System.exit(0);
      }
      FileReader file_stream = new FileReader(dormitory);  
      BufferedReader br = new BufferedReader(file_stream);
      String a = null;
      while((a = br.readLine())!=null)
      {
         Line_cnt++;
      }
      HashMap<Integer,String> student = new HashMap();
      Scanner scan = new Scanner(System.in);
      int[][] answers = new int[100][question_number+1];
      String name = null;
      String name_tmp = null;   
      int answer=0;
      student[] std = new student[Line_cnt+1];
      int sex =0;
      int dorm=0;
      for(int i=0;i<std.length;i++)
      {
         std[i] = new student();
      }
      for(int i=0;i<Line_cnt;i++)
      {
         name_tmp = F_scan.next();
         for(int j=0;j<question_number+1;j++)
            {
            answers[i][j] = F_scan.nextInt();   
            }
         sex = F_scan.nextInt();
         dorm = F_scan.nextInt();
         std[i].setsex(sex);
         std[i].setdorm(dorm);
         std[i].setname(name_tmp);
         std[i].setanswers(answers[i]);
      }
      int curr = Line_cnt;
      int age = 0;
      System.out.println("이름을 입력하시오");
      name = scan.next();
      std[curr].setname(name);
      System.out.println("성별을 입력하시오(남/여)");
      String s = scan.next();
      if(s.equals("남")) {
         std[curr].setsex(1);
      }else if(s.equals("여")){
         std[curr].setsex(2);
      }
     
      while(true)
      {
       System.out.println("기숙사를 선택하세요(성실관,긍지관,협동관,화목관)");
        String dormi = scan.next();
        if(dormi.equals("성실관")) {
           std[curr].setdorm(1);
           if(std[curr].getsex()!=1) {
              System.out.println("성실관은 남성기숙사입니다.");
           }else {
              break;
           }
        }else if(dormi.equals("긍지관")) {
           std[curr].setdorm(2);
           if(std[curr].getsex()!=1) {
              System.out.println("긍지관은 남성기숙사입니다.");
           }else {
              break;
           }
        }else if(dormi.equals("협동관")) {
           std[curr].setdorm(3);
           if(std[curr].getsex()!=2) {
              System.out.println("협동관은 여성기숙사입니다.");
           }else {
              break;
           }
        }else if(dormi.equals("화목관")) {
           std[curr].setdorm(4);
           if(std[curr].getsex()!=2) {
              System.out.println("화목관은 여성기숙사입니다.");
           }else {
              break;
           }
        }
      }

      while(true)
      {
         System.out.println("학번을 입력하시오(2자리)");
         age = scan.nextInt();
         if(age<99)
            break;
         else
            System.out.println("잘못된 값이 입력되었습니다.");
      }
      std[curr].setanswer(0, age);
      System.out.printf("문제1(1,2,3,4중 택 일)");   //흡연에 대한 본인의 습관 혹은 가치관에 대해서 제일 자신과 맞다고 생각하는 것을 골라라.
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(1,1);//나는 흡연자이다.
            break;
      case 2: std[curr].setanswer(1,2);//비흡연자이지만 간접흡연과 담배냄새 정도는 용인해 줄 수 있다.
            break;
      case 3: std[curr].setanswer(1,3);//간접흡연이나 담배냄새가 꺼려진다.
            break;
      case 4: std[curr].setanswer(1,4);//담배가 정말 싫다.혹은 천식이 있거나 호흡기가 약해서 절대 안된다.
            break;
      }
      System.out.printf("문제2(1,2,3,4중 택 일)");   //본인의 음주 빈도 및 주량
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(2,1);//나는 술을 좋아하고, 자주 마신다.
            break;
      case 2: std[curr].setanswer(2,2);//나는 룸메이트의 술주정을 어느 정도 용인해 줄 수 있다.
            break;
      case 3: std[curr].setanswer(2,3);//룸메이트의 술주정을 감당하기 꺼려진다.
            break;
      case 4: std[curr].setanswer(2,4);//나는 술을 정말 싫어한다,혹은 알코올 알러지가 있다.
            break;
      }
      
      System.out.printf("문제3(1,2,3,4중 택 일)");
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(3,1);
            break;
      case 2: std[curr].setanswer(3,2);
            break;
      case 3: std[curr].setanswer(3,3);
            break;
      case 4: std[curr].setanswer(3,4);
            break;
      }
      
      System.out.printf("문제4(1,2,3,4중 택 일)");
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(4,1);
            break;
      case 2: std[curr].setanswer(4,2);
            break;
      case 3: std[curr].setanswer(4,3);
            break;
      case 4: std[curr].setanswer(4,4);
            break;
      }
      System.out.printf("문제5(1,2,3,4중 택 일)");//보통 잠 잘 때 주변 환경에 예민한가?
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(5,1);
            break;
      case 2: std[curr].setanswer(5,2);
            break;
      case 3: std[curr].setanswer(5,3);
            break;
      case 4: std[curr].setanswer(5,4);
            break;
      }
      System.out.printf("문제6(1,2,3,4중 택 일)");//주로 공부는 어디서 하는가?
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(6,1);
            break;
      case 2: std[curr].setanswer(6,2);
            break;
      case 3: std[curr].setanswer(6,3);
            break;
      case 4: std[curr].setanswer(6,4);
            break;
      }
      System.out.printf("문제7(1,2,3,4중 택 일)");//청소에 대해서 어떻게 생각하는가?
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(7,1);
            break;
      case 2: std[curr].setanswer(7,2);
            break;
      case 3: std[curr].setanswer(7,3);
            break;
      case 4: std[curr].setanswer(7,4);
            break;
      }
      System.out.printf("문제8(1,2,3,4중 택 일)");//방향제를 사용하는가?
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(8,1);
            break;
      case 2: std[curr].setanswer(8,2);
            break;
      case 3: std[curr].setanswer(8,3);
            break;
      case 4: std[curr].setanswer(8,4);
            break;
      }
      System.out.printf("문제9(1,2,3,4중 택 일)");//전화나 음성 채팅을 자주 하는가?
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(9,1);
            break;
      case 2:std[curr].setanswer(9,2);
            break;
      case 3: std[curr].setanswer(9,3);
            break;
      case 4: std[curr].setanswer(9,4);
            break;
      }
      System.out.printf("문제10(1,2,3,4중 택 일)");//배달 음식을 자주 시켜먹는가?
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(10,1);
            break;
      case 2: std[curr].setanswer(10,2);
            break;
      case 3: std[curr].setanswer(10,3);
            break;
      case 4: std[curr].setanswer(10,4);
            break;
      }

      String tmp =null;
      int[] score = new int[100];
      for(int i=0;i<Line_cnt;i++)
      {
         for(int j=0;j<question_number;j++)
         {
           if(std[i].getsex()!=std[curr].getsex()) {
               score[i] = 999;
               break;
            }
           if(std[i].getdorm()!=std[curr].getdorm()) {
              score[i] = 999;
              break;
           }
            if(std[i].getanswer(j)>std[curr].getanswer(j))
            {
               score[i]+=std[i].getanswer(j)-std[curr].getanswer(j);
            }else
            {
               score[i]+=std[curr].getanswer(j)-std[i].getanswer(j);
            }
         }
      }
      
      int min = score[0];
      int mate_name=0;
      for(int i=0;i<Line_cnt;i++)
      {
         if(min>score[i])
         {
            min = score[i];
            mate_name=i;
         }
      }
      for(int i=0;i<Line_cnt;i++) {
         System.out.println(std[i].toString());
      }
      String mate = std[mate_name].getname();
      System.out.println(mate+" 님과 같은 방입니다");
      PrintWriter output = null;
      output = new PrintWriter(new FileOutputStream(F_name));
      for(int i=0;i<Line_cnt+1;i++) 
      {
         output.println(std[i].toString());
      }
      output.flush();
      output.close();
   }
}
