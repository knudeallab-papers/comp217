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
         super("��ϴ��б� ����� �����Ʈ ���� �ý���");
         setSize(WIDTH, HEIGHT);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new GridLayout(22,1));
         
         JPanel Intro = new JPanel();
         Intro.setLayout(new FlowLayout());
         JLabel IntroLabel = new JLabel("�� ������ ����� �����Ʈ�� ���ϴ� �� �ݿ��� �� ���Դϴ�");
         Intro.add(IntroLabel, BorderLayout.NORTH);
         add(Intro);
         
         JPanel Intro2 = new JPanel();
         Intro2.setLayout(new FlowLayout());
         JLabel IntroLabel2 = new JLabel("�������� �� �б� ����� ��Ȱ�� ������ ��ġ�� ���̹Ƿ� ������ �亯�� �ֽñ� �ٶ��ϴ�.");
         Intro2.add(IntroLabel2, BorderLayout.CENTER);
         add(Intro2);
         
         JPanel name = new JPanel();
         name.setLayout(new FlowLayout());
         JLabel putName = new JLabel("�̸��� �Է��Ͻÿ�");
         name.add(putName,BorderLayout.NORTH);
         txt = new JTextField(10);
         name.add(txt);
         JButton ok = new JButton("Ȯ��");
         ok.addActionListener(this);
         name.add(ok);
         add(name);
         
         JPanel ageP = new JPanel();
         ageP.setLayout(new FlowLayout());
         JLabel putAge = new JLabel("���̸� �Է��Ͻÿ�");
         ageP.add(putAge,BorderLayout.NORTH);
         ag = new JTextField(4);
         ageP.add(ag);
         JButton ok2 = new JButton("Ȯ��.");
         ok2.addActionListener(this);
         ageP.add(ok2);
         add(ageP);
         
         JPanel gender = new JPanel();
         gender.setLayout(new FlowLayout());
         JLabel genderlabel = new JLabel("������ ������?");
         gender.add(genderlabel, BorderLayout.NORTH);
         JButton female = new JButton("����");
         female.addActionListener(this);
         gender.add(female);
         JButton male = new JButton("����");
         male.addActionListener(this);
         gender.add(male);
         add(gender);
         
         JPanel dorm = new JPanel();
         dorm.setLayout(new FlowLayout());
         JLabel dormLab = new JLabel("����縦 �����Ͻÿ�");
         dorm.add(dormLab, BorderLayout.NORTH);
         JButton sung = new JButton("���ǰ�(��)");
         sung.addActionListener(this);
         dorm.add(sung);
         JButton gueng = new JButton("������(��)");
         gueng.addActionListener(this);
         dorm.add(gueng);
         JButton hyang = new JButton("�����(��)");
         hyang.addActionListener(this);
         dorm.add(hyang);
         JButton hwa = new JButton("ȭ���(��)");
         hwa.addActionListener(this);
         dorm.add(hwa);
         add(dorm);
         
         JPanel Q1 = new JPanel();
         Q1.setLayout(new FlowLayout());
         setBackground(Color.RED);
         JLabel N1 = new JLabel("1.���� ��ġ��.");
         Q1.add(N1, BorderLayout.NORTH);
         JButton A11 = new JButton("����");//���� �����̴�
         A11.addActionListener(this);
         Q1.add(A11);
         JButton A12 = new JButton("��� ���� ����");//������������ �������� ��賿�� ������ ������ �� �� �ִ�.
         A12.addActionListener(this);
         Q1.add(A12);
         JButton A13 = new JButton("�������� �ȴ�");//�������̳� ��賿���� ��������.
         A13.addActionListener(this);
         Q1.add(A13);
         JButton A14 = new JButton("ȣ��Ⱑ ������");//��谡 ���� �ȴ�. Ȥ�� õ���� �ְų� ȣ��Ⱑ ���ؼ� ���� �ȵȴ�.
         A14.addActionListener(this);
         Q1.add(A14);
         add(Q1);
         
         JPanel Q2 = new JPanel();
         JLabel N2 = new JLabel("2.���� ����.");
         Q2.add(N2, BorderLayout.NORTH);
         JButton A21 = new JButton("���� ���Ŵ�");//���� ���� �ſ� �����ϰ�, ���� ���Ŵ�.
         A21.addActionListener(this);
         Q2.add(A21);
         JButton A22 = new JButton("���Ŵ�");//���� ���� �����ϴ� ���̱� �ϳ� �׷��� ���� �������� �ʴ´�.
         A12.addActionListener(this);
         Q2.add(A22);
         JButton A23 = new JButton("���� ���Ŵ�");//���� ���� ���� �������ؼ�, ���� ������ �ʴ´�.
         A23.addActionListener(this);
         Q2.add(A23);
         JButton A24 = new JButton("�� ���Ŵ�");//���� ���� ������ �ʴ´�,Ȥ�� �� ���Ŵ�.
         A24.addActionListener(this);
         Q2.add(A24);
         add(Q2);
         
         JPanel Q3 = new JPanel();
         JLabel N3 = new JLabel("3.�����Ʈ�� �ֻ�");
         Q3.add(N3, BorderLayout.NORTH);
         JButton A31 = new JButton("���� ����");//���� �������� �η��� ������ �� �ִ�.
         A31.addActionListener(this);
         Q3.add(A31);
         JButton A32 = new JButton("������� ����");//�������� ������ �������� ������ �� �ִ�.
         A32.addActionListener(this);
         Q3.add(A32);
         JButton A33 = new JButton("������");//������ ������ �������� �������� �޾��� �� ����.
         A33.addActionListener(this);
         Q3.add(A33);
         JButton A34 = new JButton("�ſ� �ȴ�");//�ͼ� ������ ǳ��� ���̶� ������ �θ��� ���� �������ص� �ȴ�.
         A34.addActionListener(this);
         Q3.add(A34);
         add(Q3);
         
         JPanel Q4 = new JPanel();
         JLabel N4 = new JLabel("4.���� ����뿡 ���� �ڴ°�?");
         Q4.add(N4, BorderLayout.NORTH);
         JButton A41 = new JButton("10~11��");//10��~11��
         A41.addActionListener(this);
         Q4.add(A41);
         JButton A42 = new JButton("11~12��");//11��~12��
         A42.addActionListener(this);
         Q4.add(A42);
         JButton A43 = new JButton("12~1��");//12��~1��
         A43.addActionListener(this);
         Q4.add(A43);
         JButton A44 = new JButton("1�� ����");//1�� ����
         A44.addActionListener(this);
         Q4.add(A44);
         add(Q4);
         
         JPanel Q5 = new JPanel();
         JLabel N5 = new JLabel("5.���� �� �ֺ� ȯ��");
         Q5.add(N5, BorderLayout.NORTH);
         JButton A51 = new JButton("��� ����");//���� �������.
         A51.addActionListener(this);
         Q5.add(A51);
         JButton A52 = new JButton("���ϴ�");//�а��� ���̴�.
         A52.addActionListener(this);
         Q5.add(A52);
         JButton A53 = new JButton("���� �����ϴ�");//���� ������ ���̴�.
         A53.addActionListener(this);
         Q5.add(A53);
         JButton A54 = new JButton("���� �����ϴ�");//���� �����ϴ�.
         A54.addActionListener(this);
         Q5.add(A54);
         add(Q5);
         
         JPanel Q6 = new JPanel();
         JLabel N6 = new JLabel("6.�ַ� ���δ� ��𿡼� �ϴ°�?");
         Q6.add(N6, BorderLayout.NORTH);
         JButton A61 = new JButton("����� ��");//����� ��
         A61.addActionListener(this);
         Q6.add(A61);
         JButton A62 = new JButton("����� ������");//����� ���� ������
         A62.addActionListener(this);
         Q6.add(A62);
         JButton A63 = new JButton("����� �ްԽ�");//����� �ްԽ�
         A63.addActionListener(this);
         Q6.add(A63);
         JButton A64 = new JButton("����� �ܺ�");//����� �ܺ�
         A64.addActionListener(this);
         Q6.add(A64);
         add(Q6);
         
         JPanel Q7 = new JPanel();
         JLabel N7 = new JLabel("7.������ �� �ֺ� ȯ�濡 �����Ѱ�?");
         Q7.add(N7, BorderLayout.NORTH);
         JButton A71 = new JButton("�������");//���� �������.
         A71.addActionListener(this);
         Q7.add(A71);
         JButton A72 = new JButton("���ϴ�");//�а��� ���̴�.
         A72.addActionListener(this);
         Q7.add(A72);
         JButton A73 = new JButton("���� �����ϴ�");//���� ������ ���̴�.
         A73.addActionListener(this);
         Q7.add(A73);
         JButton A74 = new JButton("���� �����ϴ�");//���� �����ϴ�.
         A74.addActionListener(this);
         Q7.add(A74);
         add(Q7);
         
         JPanel Q8 = new JPanel();
         JLabel N8 = new JLabel("8.�ַ� �����ϴ� �ð���� �����ΰ�?");
         Q8.add(N8, BorderLayout.NORTH);
         JButton A81 = new JButton("��ħ");//�̸� ���� Ȥ�� ��ħ
         A81.addActionListener(this);
         Q8.add(A81);
         JButton A82 = new JButton("����");//����
         A82.addActionListener(this);
         Q8.add(A82);
         JButton A83 = new JButton("����");//����
         A83.addActionListener(this);
         Q8.add(A83);
         JButton A84 = new JButton("���� ����");//�� �ʰ�
         A84.addActionListener(this);
         Q8.add(A84);
         add(Q8);
         
         JPanel Q9 = new JPanel();
         JLabel N9 = new JLabel("9.û�Ҹ� �󸶳� ���� �ϴ°�?");
         Q9.add(N9, BorderLayout.NORTH);
         JButton A91 = new JButton("����");//û�Ҵ� ���� �ؾ� �Ѵ�.
         A91.addActionListener(this);
         Q9.add(A91);
         JButton A92 = new JButton("2~3�Ͽ� �ѹ�");//��Ʋ~3�Ͽ� 1��
         A92.addActionListener(this);
         Q9.add(A92);
         JButton A93 = new JButton("1���Ͽ� �ѹ�");//1���Ͽ� �ѹ�
         A93.addActionListener(this);
         Q9.add(A93);
         JButton A94 = new JButton("�� �̻�");//�� �̻�
         A94.addActionListener(this);
         Q9.add(A94);
         add(Q9);
         
         JPanel Q10 = new JPanel();
         JLabel N10 = new JLabel("10.������ ��� ����");
         Q10.add(N10, BorderLayout.NORTH);
         JButton A101 = new JButton("���� ����Ѵ�");//������Ʈ�� û�Ҹ� �� ���ص� �������.
         A101.addActionListener(this);
         Q10.add(A101);
         JButton A102 = new JButton("�������");//�����Ʈ�� �ּ��� �ڱ� �ڸ����̶� ġ�������� �Ѵ�.
         A102.addActionListener(this);
         Q10.add(A102);
         JButton A103 = new JButton("������ ������ �ȴ�");//�����Ʈ�� �ּ��� �� û�ұ����� ���� �������� �Ѵ�
         A103.addActionListener(this);
         Q10.add(A103);
         JButton A104 = new JButton("�˷�����, ȣ��� ������ �ִ�");//�����Ʈ�� û�Ҹ� ���ϸ� ���� �� �ߵ� �� ����.
         A104.addActionListener(this);
         Q10.add(A104);
         add(Q10);
         
         JPanel confirm = new JPanel();
         confirm.setLayout(new FlowLayout());
         JButton conButton = new JButton("�Ϸ�");
         conButton.addActionListener(this);
         confirm.add(conButton);
         Rmate = new JTextField(10);
         confirm.add(Rmate);
         JLabel RoomMate = new JLabel("�԰� ���� ���Դϴ�");
         confirm.add(RoomMate, BorderLayout.NORTH);
         add(confirm);
         
         JPanel end = new JPanel();
         end.setLayout(new FlowLayout());
         JButton who = new JButton("����Ȯ��");
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
         if(actionCommand.equals("Ȯ��")) {
            std[curr].setname(txt.getText());
         }  else if(actionCommand.equals("Ȯ��."))
         {
            std[curr].setanswer(0, Integer.parseInt(ag.getText()));
         }
         else if(actionCommand.equals("��"))
         {
            std[curr].setsex(1);
         }
         else if(actionCommand.equals("��")) {
            std[curr].setsex(2);
         }
            
         else if(actionCommand.equals("���ǰ�(��)")) {
            std[curr].setdorm(1);
         }
               
         else if(actionCommand.equals("������(��)")) {
            std[curr].setdorm(2);
         }
         
         else if(actionCommand.equals("�����(��)")) {
             std[curr].setdorm(3);
          }
         else if(actionCommand.equals("ȭ���(��)")) {
             std[curr].setdorm(4);
          }
         else if(actionCommand.equals("����")) {
             std[curr].setanswer(1, 1);
          }
         else if(actionCommand.equals("��� ���� ����")) {
             std[curr].setanswer(1, 2);
          }
         else if(actionCommand.equals("�������� �ȴ�")) {
             std[curr].setanswer(1, 3);
          }
         else if(actionCommand.equals("ȣ��Ⱑ ������")) {
             std[curr].setanswer(1, 4);
          }
         else if(actionCommand.equals("���� ���Ŵ�")) {
             std[curr].setanswer(2, 1);
          }
         else if(actionCommand.equals("���Ŵ�")) {
             std[curr].setanswer(2, 2);
          }
         else if(actionCommand.equals("���� ���Ŵ�")) {
             std[curr].setanswer(2, 3);
          }
         else if(actionCommand.equals("�� ���Ŵ�")) {
             std[curr].setanswer(2, 4);
          }
         else if(actionCommand.equals("���� ����")) {
             std[curr].setanswer(3, 1);
         }
             else if(actionCommand.equals("������� ����")) {
                 std[curr].setanswer(3, 2);
             }
             else if(actionCommand.equals("������")) {
                 std[curr].setanswer(3, 3);
             }
             else if(actionCommand.equals("�ſ� �ȴ�")) {
                 std[curr].setanswer(3, 4);
             }
             else if(actionCommand.equals("10~11��")) {
                 std[curr].setanswer(4, 1);
             }
             else if(actionCommand.equals("11~12��")) {
                 std[curr].setanswer(4, 2);
             }
             else if(actionCommand.equals("12��~1��")) {
                 std[curr].setanswer(4, 3);
             }
             else if(actionCommand.equals("1�� ����")) {
                 std[curr].setanswer(4, 4);
             }
             else if(actionCommand.equals("�������")) {
                 std[curr].setanswer(5, 1);
             }
             else if(actionCommand.equals("���ϴ�")) {
                 std[curr].setanswer(5, 2);
             }
             else if(actionCommand.equals("���� �����ϴ�")) {
                 std[curr].setanswer(5, 3);
             }
             else if(actionCommand.equals("���� �����ϴ�")) {
                 std[curr].setanswer(5, 4);
             }
             else if(actionCommand.equals("����� ��")) {
                 std[curr].setanswer(6, 1);
             }
             else if(actionCommand.equals("����� ������")) {
                 std[curr].setanswer(6, 2);
             }
             else if(actionCommand.equals("����� �ްԽ�")) {
                 std[curr].setanswer(6, 3);
             }
             else if(actionCommand.equals("����� �ܺ�")) {
                 std[curr].setanswer(6, 4);
             }
             else if(actionCommand.equals("�������")) {
                 std[curr].setanswer(7, 1);
             }
             else if(actionCommand.equals("���ϴ�")) {
                 std[curr].setanswer(7, 2);
             }
             else if(actionCommand.equals("���� �����ϴ�")) {
                 std[curr].setanswer(7, 3);
             }
             else if(actionCommand.equals("���� �����ϴ�")) {
                 std[curr].setanswer(7, 4);
             }
             else if(actionCommand.equals("��ħ")) {
                 std[curr].setanswer(8, 1);
             }
             else if(actionCommand.equals("����")) {
                 std[curr].setanswer(8, 2);
             }
             else if(actionCommand.equals("����")) {
                 std[curr].setanswer(8, 3);
             }
             else if(actionCommand.equals("���� ����")) {
                 std[curr].setanswer(8, 4);
             }
             else if(actionCommand.equals("����")) {
                 std[curr].setanswer(9, 1);
             }
             else if(actionCommand.equals("2~3�Ͽ� �ѹ�")) {
                 std[curr].setanswer(9, 2);
             }
             else if(actionCommand.equals("1���Ͽ� �ѹ�")) {
                 std[curr].setanswer(9, 3);
             }
             else if(actionCommand.equals("�� �̻�")) {
                 std[curr].setanswer(9, 4);
             }
             else if(actionCommand.equals("���� ����Ѵ�")) {
                 std[curr].setanswer(10, 1);
             }
             else if(actionCommand.equals("�������")) {
                 std[curr].setanswer(10, 2);
             }
             else if(actionCommand.equals("������ ������ �ȴ�")) {
                 std[curr].setanswer(10, 3);
             }
             else if(actionCommand.equals("�˷�����,ȣ��� ������ �ִ�")) {
                 std[curr].setanswer(10, 4);
             }
         else if(actionCommand.equals("�Ϸ�")){
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
         }else if(actionCommand.equals("����Ȯ��")) {
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
