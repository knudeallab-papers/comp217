package teamproject_body;

import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class show extends JFrame implements ActionListener {
      public static final int WIDTH = 800;
      public static final int HEIGHT = 2400;
      private JTextField txt;
      private JTextField ag;
      private JTextField Rmate;
      private JTextArea info;
      student[] std;
      public static void main(String[] args) throws IOException{   
         show iconGui = new show();
         iconGui.setVisible(true);
         
      }
      
      public show() {
         super("경북대학교 기숙사 룸메이트 배정 시스템");
         setSize(WIDTH, HEIGHT);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new GridLayout(22,1));
         
         JPanel Intro = new JPanel();
         Intro.setLayout(new FlowLayout());
         JLabel IntroLabel = new JLabel("이 설문은 기숙사 룸메이트를 정하는 데 반영이 될 것입니다");
         Intro.add(IntroLabel, BorderLayout.NORTH);
         add(Intro);
         
         JPanel Intro2 = new JPanel();
         Intro2.setLayout(new FlowLayout());
         JLabel IntroLabel2 = new JLabel("여러분의 한 학기 기숙사 생활에 영향을 미치는 것이므로 성실히 답변해 주시기 바랍니다.");
         Intro2.add(IntroLabel2, BorderLayout.CENTER);
         add(Intro2);
         
         JPanel name = new JPanel();
         name.setLayout(new FlowLayout());
         JLabel putName = new JLabel("이름을 입력하시오");
         name.add(putName,BorderLayout.NORTH);
         txt = new JTextField(10);
         name.add(txt);
         JButton ok = new JButton("확인");
         ok.addActionListener(this);
         name.add(ok);
         add(name);
         
         JPanel ageP = new JPanel();
         ageP.setLayout(new FlowLayout());
         JLabel putAge = new JLabel("나이를 입력하시오");
         ageP.add(putAge,BorderLayout.NORTH);
         ag = new JTextField(4);
         ageP.add(ag);
         JButton ok2 = new JButton("확인.");
         ok2.addActionListener(this);
         ageP.add(ok2);
         add(ageP);
         
         JPanel gender = new JPanel();
         gender.setLayout(new FlowLayout());
         JLabel genderlabel = new JLabel("본인의 성별은?");
         gender.add(genderlabel, BorderLayout.NORTH);
         JButton female = new JButton("여성");
         female.addActionListener(this);
         gender.add(female);
         JButton male = new JButton("남성");
         male.addActionListener(this);
         gender.add(male);
         add(gender);
         
         JPanel dorm = new JPanel();
         dorm.setLayout(new FlowLayout());
         JLabel dormLab = new JLabel("기숙사를 선택하시오");
         dorm.add(dormLab, BorderLayout.NORTH);
         JButton sung = new JButton("성실관(남)");
         sung.addActionListener(this);
         dorm.add(sung);
         JButton gueng = new JButton("긍지관(남)");
         gueng.addActionListener(this);
         dorm.add(gueng);
         JButton hyang = new JButton("향토관(여)");
         hyang.addActionListener(this);
         dorm.add(hyang);
         JButton hwa = new JButton("화목관(여)");
         hwa.addActionListener(this);
         dorm.add(hwa);
         add(dorm);
         
         JPanel Q1 = new JPanel();
         Q1.setLayout(new FlowLayout());
         setBackground(Color.RED);
         JLabel N1 = new JLabel("1.흡연에 가치관.");
         Q1.add(N1, BorderLayout.NORTH);
         JButton A11 = new JButton("흡연자");//나는 흡연자이다
         A11.addActionListener(this);
         Q1.add(A11);
         JButton A12 = new JButton("담배 용인 가능");//비흡연자이지만 간접흡연과 담배냄새 정도는 용인해 줄 수 있다.
         A12.addActionListener(this);
         Q1.add(A12);
         JButton A13 = new JButton("간접흡연이 싫다");//간접흡연이나 담배냄새가 꺼려진다.
         A13.addActionListener(this);
         Q1.add(A13);
         JButton A14 = new JButton("호흡기가 안좋다");//담배가 정말 싫다. 혹은 천식이 있거나 호흡기가 약해서 절대 안된다.
         A14.addActionListener(this);
         Q1.add(A14);
         add(Q1);
         
         JPanel Q2 = new JPanel();
         JLabel N2 = new JLabel("2.음주 습관.");
         Q2.add(N2, BorderLayout.NORTH);
         JButton A21 = new JButton("자주 마신다");//나는 술을 매우 좋아하고, 자주 마신다.
         A21.addActionListener(this);
         Q2.add(A21);
         JButton A22 = new JButton("마신다");//나는 술을 좋아하는 편이긴 하나 그렇게 자주 마시지는 않는다.
         A12.addActionListener(this);
         Q2.add(A22);
         JButton A23 = new JButton("가끔 마신다");//나는 술을 별로 안좋아해서, 거의 마시지 않는다.
         A23.addActionListener(this);
         Q2.add(A23);
         JButton A24 = new JButton("못 마신다");//나는 술을 마시지 않는다,혹은 못 마신다.
         A24.addActionListener(this);
         Q2.add(A24);
         add(Q2);
         
         JPanel Q3 = new JPanel();
         JLabel N3 = new JLabel("3.룸메이트의 주사");
         Q3.add(N3, BorderLayout.NORTH);
         JButton A31 = new JButton("감당 가능");//심한 술주정을 부려도 감당할 수 있다.
         A31.addActionListener(this);
         Q3.add(A31);
         JButton A32 = new JButton("어느정도 용인");//심하지만 않으면 술주정은 감당할 수 있다.
         A32.addActionListener(this);
         Q3.add(A32);
         JButton A33 = new JButton("꺼린다");//술냄새 정도는 괜찮으나 술주정은 받아줄 수 없다.
         A33.addActionListener(this);
         Q3.add(A33);
         JButton A34 = new JButton("매우 싫다");//와서 술냄새 풍기는 것이랑 술주정 부리는 것은 생각만해도 싫다.
         A34.addActionListener(this);
         Q3.add(A34);
         add(Q3);
         
         JPanel Q4 = new JPanel();
         JLabel N4 = new JLabel("4.보통 몇시쯤에 잠을 자는가?");
         Q4.add(N4, BorderLayout.NORTH);
         JButton A41 = new JButton("10~11시");//10시~11시
         A41.addActionListener(this);
         Q4.add(A41);
         JButton A42 = new JButton("11~12시");//11시~12시
         A42.addActionListener(this);
         Q4.add(A42);
         JButton A43 = new JButton("12~1시");//12시~1시
         A43.addActionListener(this);
         Q4.add(A43);
         JButton A44 = new JButton("1시 이후");//1시 이후
         A44.addActionListener(this);
         Q4.add(A44);
         add(Q4);
         
         JPanel Q5 = new JPanel();
         JLabel N5 = new JLabel("5.잠잘 때 주변 환경");
         Q5.add(N5, BorderLayout.NORTH);
         JButton A51 = new JButton("상관 없다");//전혀 상관없다.
         A51.addActionListener(this);
         Q5.add(A51);
         JButton A52 = new JButton("둔하다");//둔감한 편이다.
         A52.addActionListener(this);
         Q5.add(A52);
         JButton A53 = new JButton("조금 예민하다");//조금 예민한 편이다.
         A53.addActionListener(this);
         Q5.add(A53);
         JButton A54 = new JButton("정말 에민하다");//정말 예민하다.
         A54.addActionListener(this);
         Q5.add(A54);
         add(Q5);
         
         JPanel Q6 = new JPanel();
         JLabel N6 = new JLabel("6.주로 공부는 어디에서 하는가?");
         Q6.add(N6, BorderLayout.NORTH);
         JButton A61 = new JButton("기숙사 방");//기숙사 방
         A61.addActionListener(this);
         Q6.add(A61);
         JButton A62 = new JButton("기숙사 독서실");//기숙사 지하 독서실
         A62.addActionListener(this);
         Q6.add(A62);
         JButton A63 = new JButton("기숙사 휴게실");//기숙사 휴게실
         A63.addActionListener(this);
         Q6.add(A63);
         JButton A64 = new JButton("기숙사 외부");//기숙사 외부
         A64.addActionListener(this);
         Q6.add(A64);
         add(Q6);
         
         JPanel Q7 = new JPanel();
         JLabel N7 = new JLabel("7.공부할 떄 주변 환경에 예민한가?");
         Q7.add(N7, BorderLayout.NORTH);
         JButton A71 = new JButton("상관없다");//전혀 상관없다.
         A71.addActionListener(this);
         Q7.add(A71);
         JButton A72 = new JButton("둔하다");//둔감한 편이다.
         A72.addActionListener(this);
         Q7.add(A72);
         JButton A73 = new JButton("조금 예민하다");//조금 예민한 편이다.
         A73.addActionListener(this);
         Q7.add(A73);
         JButton A74 = new JButton("정말 예민하다");//정말 예민하다.
         A74.addActionListener(this);
         Q7.add(A74);
         add(Q7);
         
         JPanel Q8 = new JPanel();
         JLabel N8 = new JLabel("8.주로 공부하는 시간대는 언제인가?");
         Q8.add(N8, BorderLayout.NORTH);
         JButton A81 = new JButton("아침");//이른 새벽 혹은 아침
         A81.addActionListener(this);
         Q8.add(A81);
         JButton A82 = new JButton("오후");//오후
         A82.addActionListener(this);
         Q8.add(A82);
         JButton A83 = new JButton("저녁");//저녁
         A83.addActionListener(this);
         Q8.add(A83);
         JButton A84 = new JButton("자정 이후");//밤 늦게
         A84.addActionListener(this);
         Q8.add(A84);
         add(Q8);
         
         JPanel Q9 = new JPanel();
         JLabel N9 = new JLabel("9.청소를 얼마나 자주 하는가?");
         Q9.add(N9, BorderLayout.NORTH);
         JButton A91 = new JButton("매일");//청소는 매일 해야 한다.
         A91.addActionListener(this);
         Q9.add(A91);
         JButton A92 = new JButton("2~3일에 한번");//이틀~3일에 1번
         A92.addActionListener(this);
         Q9.add(A92);
         JButton A93 = new JButton("1주일에 한번");//1주일에 한번
         A93.addActionListener(this);
         Q9.add(A93);
         JButton A94 = new JButton("그 이상");//그 이상
         A94.addActionListener(this);
         Q9.add(A94);
         add(Q9);
         
         JPanel Q10 = new JPanel();
         JLabel N10 = new JLabel("10.방향제 사용 여부");
         Q10.add(N10, BorderLayout.NORTH);
         JButton A101 = new JButton("내가 사용한다");//름메이트가 청소를 잘 안해도 상관없다.
         A101.addActionListener(this);
         Q10.add(A101);
         JButton A102 = new JButton("상관없다");//룸메이트가 최소한 자기 자리만이라도 치워줬으면 한다.
         A102.addActionListener(this);
         Q10.add(A102);
         JButton A103 = new JButton("방향제 냄세가 싫다");//룸메이트가 최소한 방 청소까지는 같이 해줬으면 한다
         A103.addActionListener(this);
         Q10.add(A103);
         JButton A104 = new JButton("알레르기, 호흡기 문제가 있다");//룸메이트가 청소를 안하면 정말 못 견딜 것 같다.
         A104.addActionListener(this);
         Q10.add(A104);
         add(Q10);
         
         JPanel confirm = new JPanel();
         confirm.setLayout(new FlowLayout());
         JButton conButton = new JButton("완료");
         conButton.addActionListener(this);
         confirm.add(conButton);
         Rmate = new JTextField(10);
         confirm.add(Rmate);
         JLabel RoomMate = new JLabel("님과 같은 방입니다");
         confirm.add(RoomMate, BorderLayout.NORTH);
         add(confirm);
         
         JPanel end = new JPanel();
         end.setLayout(new FlowLayout());
         JButton who = new JButton("정보확인");
         who.addActionListener(this);
         info = new JTextArea(1,30);
         info.setEditable(false);
         end.add(info);
         end.add(who);
         add(end);
      }
      
      public void actionPerformed (ActionEvent d) {
          String actionCommand  = d.getActionCommand();
          
         String F_name = "dormitory.txt";
          File dormitory = new File(F_name);
          int Line_cnt=0;
          int question_number=10;
          Scanner F_scan = null;
          int mate_name=0;
          try {
         ObjectOutputStream bin = new ObjectOutputStream(new FileOutputStream("dormitory.dat"));
      } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
          try {
             F_scan = new Scanner(new FileInputStream(dormitory));
          }catch(FileNotFoundException e) {
             e.printStackTrace();
             System.exit(0);
          }
          FileReader file_stream = null;
      try {
         file_stream = new FileReader(dormitory);
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }  
          BufferedReader br = new BufferedReader(file_stream);
          String a = null;
          try {
         while((a = br.readLine())!=null)
           {
              Line_cnt++;
           }
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
          HashMap<Integer,String> S = new HashMap();
          Scanner scan = new Scanner(System.in);
          int[][] answers = new int[100][question_number+1];
          String name_tmp = null;
          int sex =0;
          int dorm=0;
          student[] std = new student[Line_cnt+1];
          
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
          System.out.println(curr);
         if(actionCommand.equals("확인")) {
            std[curr].setname(txt.getText());
         }  else if(actionCommand.equals("확인."))
         {
            std[curr].setanswer(0, Integer.parseInt(ag.getText()));
         }
         else if(actionCommand.equals("남"))
         {
            std[curr].setsex(1);
         }
         else if(actionCommand.equals("여")) {
            std[curr].setsex(2);
         }
            
         else if(actionCommand.equals("성실관(남)")) {
            std[curr].setdorm(1);
         }
               
         else if(actionCommand.equals("긍지관(남)")) {
            std[curr].setdorm(2);
         }
         
         else if(actionCommand.equals("향토관(여)")) {
             std[curr].setdorm(3);
          }
         else if(actionCommand.equals("화목관(여)")) {
             std[curr].setdorm(4);
          }
         else if(actionCommand.equals("흡연자")) {
             std[curr].setanswer(1, 1);
          }
         else if(actionCommand.equals("담배 용인 가능")) {
             std[curr].setanswer(1, 2);
          }
         else if(actionCommand.equals("간접흡연이 싫다")) {
             std[curr].setanswer(1, 3);
          }
         else if(actionCommand.equals("호흡기가 안좋다")) {
             std[curr].setanswer(1, 4);
          }
         else if(actionCommand.equals("자주 마신다")) {
             std[curr].setanswer(2, 1);
          }
         else if(actionCommand.equals("마신다")) {
             std[curr].setanswer(2, 2);
          }
         else if(actionCommand.equals("가끔 마신다")) {
             std[curr].setanswer(2, 3);
          }
         else if(actionCommand.equals("못 마신다")) {
             std[curr].setanswer(2, 4);
          }
         else if(actionCommand.equals("감당 가능")) {
             std[curr].setanswer(3, 1);
         }
             else if(actionCommand.equals("어느정도 용인")) {
                 std[curr].setanswer(3, 2);
             }
             else if(actionCommand.equals("꺼린다")) {
                 std[curr].setanswer(3, 3);
             }
             else if(actionCommand.equals("매우 싫다")) {
                 std[curr].setanswer(3, 4);
             }
             else if(actionCommand.equals("10~11시")) {
                 std[curr].setanswer(4, 1);
             }
             else if(actionCommand.equals("11~12시")) {
                 std[curr].setanswer(4, 2);
             }
             else if(actionCommand.equals("12시~1시")) {
                 std[curr].setanswer(4, 3);
             }
             else if(actionCommand.equals("1시 이후")) {
                 std[curr].setanswer(4, 4);
             }
             else if(actionCommand.equals("상관없다")) {
                 std[curr].setanswer(5, 1);
             }
             else if(actionCommand.equals("둔하다")) {
                 std[curr].setanswer(5, 2);
             }
             else if(actionCommand.equals("조금 예민하다")) {
                 std[curr].setanswer(5, 3);
             }
             else if(actionCommand.equals("정말 예민하다")) {
                 std[curr].setanswer(5, 4);
             }
             else if(actionCommand.equals("기숙사 방")) {
                 std[curr].setanswer(6, 1);
             }
             else if(actionCommand.equals("기숙사 독서실")) {
                 std[curr].setanswer(6, 2);
             }
             else if(actionCommand.equals("기숙사 휴게실")) {
                 std[curr].setanswer(6, 3);
             }
             else if(actionCommand.equals("기숙사 외부")) {
                 std[curr].setanswer(6, 4);
             }
             else if(actionCommand.equals("상관없다")) {
                 std[curr].setanswer(7, 1);
             }
             else if(actionCommand.equals("둔하다")) {
                 std[curr].setanswer(7, 2);
             }
             else if(actionCommand.equals("조금 예민하다")) {
                 std[curr].setanswer(7, 3);
             }
             else if(actionCommand.equals("정말 예민하다")) {
                 std[curr].setanswer(7, 4);
             }
             else if(actionCommand.equals("아침")) {
                 std[curr].setanswer(8, 1);
             }
             else if(actionCommand.equals("오후")) {
                 std[curr].setanswer(8, 2);
             }
             else if(actionCommand.equals("저녁")) {
                 std[curr].setanswer(8, 3);
             }
             else if(actionCommand.equals("자정 이후")) {
                 std[curr].setanswer(8, 4);
             }
             else if(actionCommand.equals("매일")) {
                 std[curr].setanswer(9, 1);
             }
             else if(actionCommand.equals("2~3일에 한번")) {
                 std[curr].setanswer(9, 2);
             }
             else if(actionCommand.equals("1주일에 한번")) {
                 std[curr].setanswer(9, 3);
             }
             else if(actionCommand.equals("그 이상")) {
                 std[curr].setanswer(9, 4);
             }
             else if(actionCommand.equals("내가 사용한다")) {
                 std[curr].setanswer(10, 1);
             }
             else if(actionCommand.equals("상관없다")) {
                 std[curr].setanswer(10, 2);
             }
             else if(actionCommand.equals("방향제 냄세가 싫다")) {
                 std[curr].setanswer(10, 3);
             }
             else if(actionCommand.equals("알레르기,호흡기 문제가 있다")) {
                 std[curr].setanswer(10, 4);
             }
         else if(actionCommand.equals("완료")){
            System.out.println(std[curr].toString());
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
             
             for(int i=0;i<Line_cnt;i++)
             {
                if(min>score[i])
                {
                   min = score[i];
                   mate_name=i;
                }
             }
             std[curr].setmate_name(mate_name);
             String mate = std[mate_name].getname();
             Rmate.setText(mate);
         }else if(actionCommand.equals("정보확인")) {
            int mate_name1 = std[curr].getmate_name();
            info.setText(std[mate_name1].toString());
            PrintWriter output = null;
             try {
            output = new PrintWriter(new FileOutputStream(F_name));
         } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
             for(int i=0;i<Line_cnt;i++) 
             {
                output.println(std[i].toString());
             }
             output.println(std[curr].toString());
             output.flush();
             output.close();
         }
         
      }
}
