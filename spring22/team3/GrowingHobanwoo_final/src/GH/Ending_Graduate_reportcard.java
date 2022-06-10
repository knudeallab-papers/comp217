package GH;
/*
* 성적표 출력 클래스
* */

import javax.swing.*;
import java.awt.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static GH.Main_HOBAN.user_name;
import static GH.Regis_page.selectedList;

public class Ending_Graduate_reportcard extends JFrame implements Serializable {
    ImageIcon final_pic;

    static String name_report = new String();
    int grade;
    JPanel gradePanel;
    public Ending_Graduate_reportcard(){
        super("성적표");
        setSize(500 ,700);
        setLocationRelativeTo(null);
    	setResizable(false);
        JPanel master = new JPanel(new BorderLayout());

        JPanel header = new JPanel();
        header.setBounds(0,0,200,50);
        JLabel title = new JLabel("REPORT CARD");
        Font f1 = new Font("serif", Font.ITALIC,50);
        title.setFont(f1);
        header.add(title);
        master.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(null);
        JPanel mid = new JPanel(new GridLayout(3,1));
        mid.setBounds(70, 200, 370, 100);
        mid.setBackground(Color.GRAY);

        Font f2 = new Font("serif", Font.ITALIC,20);

        JLabel school = new JLabel("School : KNU");
        school.setFont(f2);
        mid.add(school);

        JLabel major = new JLabel("Major : CSE");
        major.setFont(f2);
        mid.add(major);
        try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("username.dat"));
			name_report = (String)inputStream.readUTF();
			 
			inputStream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
        JLabel name = new JLabel("Name : "+name_report);
        name.setFont(f2);
        mid.add(name);

        center.add(mid);

        final_pic = new ImageIcon("graduate.png");
        JPanel pic = new JPanel(){

            public void paintComponent(Graphics g) {

                g.drawImage(final_pic.getImage(), 0, 0,180,180, null);

                setOpaque(false);
                super.paintComponent(g);
            }

        };
        pic.setBounds(150, 20, 300, 300);
        center.add(pic);

        gradePanel = new JPanel(new GridLayout(6,1));
        gradePanel.setBounds(70, 320, 370, 250);
        grading_program();
      
        gradePanel.setBackground(Color.CYAN);
        center.add(gradePanel);


        master.add(center, BorderLayout.CENTER);
        add(master);
        setVisible(true);
    }
    public void grading_program(){
       
        int i =0;
        
        while(i<6){
            String temp=selectedList[i].name + " : "+grade_evaluate(selectedList[i].GPA);

            JLabel selected_temp = new JLabel(temp);
            gradePanel.add(selected_temp);

            i++;
        }
    }
    public String grade_evaluate(double GPA){ //최종 학점(성적) 결정


        String final_grade;

        if(GPA==4.3)
            final_grade = "A+";
        else if(GPA<4.3&&GPA>=4.0)
            final_grade = "A0";
        else if(GPA<4.0&&GPA>=3.5)
            final_grade = "B+";
        else if(GPA<3.5&&GPA>=3.0)
            final_grade = "B0";
        else{
         
            final_grade = "C+";
        }

        return final_grade;
    }

    public static void main(String[] args) {
    	
    	
        Ending_Graduate_reportcard frame = new Ending_Graduate_reportcard();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
}
