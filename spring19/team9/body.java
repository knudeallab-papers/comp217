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
      System.out.println("�̸��� �Է��Ͻÿ�");
      name = scan.next();
      std[curr].setname(name);
      System.out.println("������ �Է��Ͻÿ�(��/��)");
      String s = scan.next();
      if(s.equals("��")) {
         std[curr].setsex(1);
      }else if(s.equals("��")){
         std[curr].setsex(2);
      }
     
      while(true)
      {
       System.out.println("����縦 �����ϼ���(���ǰ�,������,������,ȭ���)");
        String dormi = scan.next();
        if(dormi.equals("���ǰ�")) {
           std[curr].setdorm(1);
           if(std[curr].getsex()!=1) {
              System.out.println("���ǰ��� ����������Դϴ�.");
           }else {
              break;
           }
        }else if(dormi.equals("������")) {
           std[curr].setdorm(2);
           if(std[curr].getsex()!=1) {
              System.out.println("�������� ����������Դϴ�.");
           }else {
              break;
           }
        }else if(dormi.equals("������")) {
           std[curr].setdorm(3);
           if(std[curr].getsex()!=2) {
              System.out.println("�������� ����������Դϴ�.");
           }else {
              break;
           }
        }else if(dormi.equals("ȭ���")) {
           std[curr].setdorm(4);
           if(std[curr].getsex()!=2) {
              System.out.println("ȭ����� ����������Դϴ�.");
           }else {
              break;
           }
        }
      }

      while(true)
      {
         System.out.println("�й��� �Է��Ͻÿ�(2�ڸ�)");
         age = scan.nextInt();
         if(age<99)
            break;
         else
            System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
      }
      std[curr].setanswer(0, age);
      System.out.printf("����1(1,2,3,4�� �� ��)");   //���� ���� ������ ���� Ȥ�� ��ġ���� ���ؼ� ���� �ڽŰ� �´ٰ� �����ϴ� ���� ����.
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(1,1);//���� �����̴�.
            break;
      case 2: std[curr].setanswer(1,2);//������������ �������� ��賿�� ������ ������ �� �� �ִ�.
            break;
      case 3: std[curr].setanswer(1,3);//�������̳� ��賿���� ��������.
            break;
      case 4: std[curr].setanswer(1,4);//��谡 ���� �ȴ�.Ȥ�� õ���� �ְų� ȣ��Ⱑ ���ؼ� ���� �ȵȴ�.
            break;
      }
      System.out.printf("����2(1,2,3,4�� �� ��)");   //������ ���� �� �� �ַ�
      answer = scan.nextInt();
      switch(answer) {
      case 1: std[curr].setanswer(2,1);//���� ���� �����ϰ�, ���� ���Ŵ�.
            break;
      case 2: std[curr].setanswer(2,2);//���� �����Ʈ�� �������� ��� ���� ������ �� �� �ִ�.
            break;
      case 3: std[curr].setanswer(2,3);//�����Ʈ�� �������� �����ϱ� ��������.
            break;
      case 4: std[curr].setanswer(2,4);//���� ���� ���� �Ⱦ��Ѵ�,Ȥ�� ���ڿ� �˷����� �ִ�.
            break;
      }
      
      System.out.printf("����3(1,2,3,4�� �� ��)");
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
      
      System.out.printf("����4(1,2,3,4�� �� ��)");
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
      System.out.printf("����5(1,2,3,4�� �� ��)");//���� �� �� �� �ֺ� ȯ�濡 �����Ѱ�?
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
      System.out.printf("����6(1,2,3,4�� �� ��)");//�ַ� ���δ� ��� �ϴ°�?
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
      System.out.printf("����7(1,2,3,4�� �� ��)");//û�ҿ� ���ؼ� ��� �����ϴ°�?
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
      System.out.printf("����8(1,2,3,4�� �� ��)");//�������� ����ϴ°�?
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
      System.out.printf("����9(1,2,3,4�� �� ��)");//��ȭ�� ���� ä���� ���� �ϴ°�?
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
      System.out.printf("����10(1,2,3,4�� �� ��)");//��� ������ ���� ���ѸԴ°�?
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
      System.out.println(mate+" �԰� ���� ���Դϴ�");
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
