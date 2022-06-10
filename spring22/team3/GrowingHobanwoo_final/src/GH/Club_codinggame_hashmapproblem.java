package GH;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Random;

import javax.swing.*;

public class Club_codinggame_hashmapproblem extends JFrame{
    static JPanel problem_panel, master_panel;
    String problem="다음 문제에 맞는 코드를 순서대로 선택하시오.";
    String current_problem="";
    String[] current_codes = new String[7];
    int current_index;     
    final String[] problems = {
            "변수 A, B를 선언 후 값을 할당 한 후 출력을 하라." +"\n"+
                    "그 후 두 변수를 더한 sum을 출력하고 sum에 10을 더해 최종 sum을 출력하는 프로그램을 작성하라",
            "키보드로 변수 num을 입력 받고 출력하라." +"\n"+
                    "그 후 10을 더해 최종 num을 출력하는 프로그램을 작성하시오.",
            "fileIn을 여는 프로그램을 작성하시오.",
            "변수 num에 10을 할당하라." +"\n"+
                    "그 후 1씩 빼며 num이 5보다 크고 3으로 나누었을때 나머지가 1이면 while을 종료하는 프로그램을 작성하시오.",
            "문자열을 선언 후 문장을 저장한 후 StringTokenizer을 이용해 white space 마다 끊어 출력하라."
    };
    final String codes1[]= {
            "int A, B;", "A = value1, B = value2;", "System.out.println(A + ” “ + B);",
            "int sum;", "sum = A + B;", "System.out.println(sum);", "System.out.println(“계산을 마칩니다.“);"
    };
    final String codes2[]= {
            "import java.util.Scanner;", "Scanner keyboard = new Scanner(System.in);",
            "int num;", "num = keyboard.nextInt();", "System.out.println(num);", "num + = 10;", "System.out.println(“최종 num : “ + num);"
    };
    final String codes3[]= {
            "Scanner fileIn = null;", "Try{", "fileIn = new Scanner(new FileInputStream(inputFile));",
            "}catch(FileNotFoundException e){", "System.out.println(“File not Found”);", "System.exit(0); }", "fileIn.close();"
    };
    final String codes4[]= {
            "int num = 10;", "while(num— > 5){", "if(number % 3 == 1)", "break;",
            "else if(num % 3 == 0)", "continue;", "}"
    };
    final String codes5[]= {
            "import java.util.StringTokenizer;", "String str;", "str = “A single word can change the world.”;",
            "StringTokenizer wordFactory = new StringTokenizer(str, “ “);", "while(wordFactory.hasMoreToken()){", "System.out.println(wordFactory.nextToken());", "}"
    };
    HashMap<Integer, Club_codinggame_problem> problems_hash = new HashMap<>(5);
    String[][] arr_codes= {codes1, codes2, codes3, codes4, codes5};

    public Club_codinggame_hashmapproblem() {

        super("Coding_problems");
        setSize(700, 700);
        setLayout(new GridLayout(1, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        problems_hash.put(0, new Club_codinggame_problem(problems[0], codes1));
        problems_hash.put(1, new Club_codinggame_problem(problems[1], codes2));
        problems_hash.put(2, new Club_codinggame_problem(problems[2], codes3));
        problems_hash.put(3, new Club_codinggame_problem(problems[3], codes4));
        problems_hash.put(4, new Club_codinggame_problem(problems[4], codes5));

        master_panel = new JPanel();
        master_panel.setBounds(15, 7, 650, 300);
        master_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        master_panel.setBackground(Color.LIGHT_GRAY);
        current_index = ranIndex(); 
        current_problem = problems_hash.get(current_index).getProblem_q();

        System.out.println(current_problem); 
        current_codes=problems_hash.get(current_index).getProblem_codes();

        System.out.println();
        for(int i =0; i<7; i++){
            System.out.println(current_codes[i]);
        }
        printRan_problems();
        master_panel.add(problem_panel);
        add(master_panel);
        setVisible(false);
    }
        public int ranIndex() {
        int index;
        Random ran = new Random();
        index = ran.nextInt(5);
        return index;
    }
    public void printRan_problems() {
        problem_panel = new JPanel();
        problem_panel.setBounds(15, 7, 650, 100);

        JTextArea problem_area = new JTextArea(problem+"\n\n"+current_problem);
        problem_area.setBackground(Color.LIGHT_GRAY);
        problem_area.setBounds(15, 7, 430, 100);
        problem_area.setLineWrap(true);
        
        problem_area.setEditable(false);
        problem_panel.add(problem_area);
        
        problem_panel.setBackground(Color.LIGHT_GRAY);
        master_panel.add(problem_panel);
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Club_codinggame_hashmapproblem ran1= new Club_codinggame_hashmapproblem();

    }
}

