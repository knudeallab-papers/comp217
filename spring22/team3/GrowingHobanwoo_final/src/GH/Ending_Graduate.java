package GH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Character;

public class Ending_Graduate extends JFrame implements ActionListener{
   

    static String final_grade;

    double[] lecture_grade = new double[6];
    JPanel master;

    ImageIcon pic1;
    ImageIcon pic2;
    
    public Ending_Graduate(){
        super("졸업");
        setBounds(400,100,600,600);
		setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel Back = new JPanel();
        Back.setBackground(Color.WHITE);
        Back.setLayout(new GridLayout(2,1));



        master = new JPanel();
        master.setLayout(null);
        master.setSize(600,600);

        JButton end = new JButton("게임 끝내기");
        end.setBackground(Color.WHITE);
        end.setBounds(480,500,100,30);
        end.setBorderPainted(false);
        end.setFocusPainted(false);
        end.addActionListener(this);

        master.add(end);

        grade_ending();

        JLabel pic1Label = new JLabel();
        pic1Label.setIcon(pic1);
        pic1Label.setHorizontalAlignment(JLabel.CENTER);

        JLabel pic2Label = new JLabel();
        pic2Label.setIcon(pic2);
        pic2Label.setHorizontalAlignment(JLabel.CENTER);

        Back.add(pic1Label);
        Back.add(pic2Label);
        Back.setBounds(0,0,600,600);

        master.add(Back);



        add(master);
        setVisible(true);

    }
    public void grade_ending(){

        pic1 = new ImageIcon("main.jpeg");
        pic2 = new ImageIcon("graduate.png");

        JButton printGrade = new JButton("성적표");
        printGrade.setBounds(100, 100, 80, 30);
        printGrade.setFocusPainted(false);
        printGrade.addActionListener((ActionListener) this); 
        master.add(printGrade);
  }
    
    public void grade_evaluate(double grade){
        if(grade==4.3) 
            final_grade = "A+";
        else if(grade<4.3&&grade>=4.0)
            final_grade = "A0";
        else if(grade<4.0&&grade>=3.5)
            final_grade = "B+";
        else if(grade<3.5&&grade>=3.0)
            final_grade = "B0";
        else{
      
            final_grade = "C+";
        }

    }
    public static void main(String[] args) {
    	
    	Ending_Graduate frame = new Ending_Graduate();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCmd = e.getActionCommand();

        if(actionCmd.equals("게임 끝내기")) {
            System.exit(0);
        }
        else if(actionCmd.equals("성적표")) {
            Ending_Graduate_reportcard final_card = new Ending_Graduate_reportcard();
        }
    }
}
