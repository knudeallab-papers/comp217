package GH;

import static GH.Character_status.happy;
import static GH.Character_status.hp;
import static GH.Character_status.stress;

import static GH.Main_HOBAN.happy_;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import java.util.Random;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;

public class Club_codinggame extends JFrame implements ActionListener{
    Club_codinggame_hashmapproblem pro = new Club_codinggame_hashmapproblem();
    JPanel master;
    JPanel codes;
    JButton Back, Assign, Put;
    private JList codeList;
    JTextArea codeArea;

    Stack<Integer>index_code = new Stack<Integer>();
    Stack<String>selected_code = new Stack<String>();
    int[] ran_num = new int[7];
    int cnt=0;
    TimerTask t_task = new TimerTask() {
        @Override
        public void run() {
            setVisible(false);
        }
    };
    Timer t_timer=new Timer();
    public Club_codinggame() {
        super("study_club : CODING_GAME");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
    	setResizable(false);
    	
    	hp -= 5;
		stress += 5;
		happy -= 5;
		
		happy_ -= 5;

        design();
        
        if(hp <= 0) {
			Ending_Guaro gre = new Ending_Guaro();
			gre.setVisible(true);
			setVisible(false);
		}else if(stress >= 100) {
			Ending_Gapyear gye = new Ending_Gapyear();
			gye.setVisible(true);
			setVisible(false);
		}else
			setVisible(true);
    }
    public void design() {

        master = new JPanel(null); 
        master.setBounds(15, 7, 450, 250); 
        master.setBackground(Color.LIGHT_GRAY); 

        codeArea = new JTextArea(1, 1); 
        codeArea.setBounds(12, 101, 430, 150); 
        codeArea.setBackground(Color.LIGHT_GRAY); 
        codeArea.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 3), "Selected Codes"));
        master.add(pro.master_panel); 
       

        JPanel selectInfo = new JPanel(new FlowLayout());
        JLabel selectStr = new JLabel("Select 7 codes in the correct order"); 
        selectInfo.add(selectStr);
        selectInfo.setBounds(15, 265, 450, 30);
        selectInfo.setBackground(Color.YELLOW);
        add(selectInfo);

        codes= new JPanel(); 
        codes.setLayout(null);
        codes.setBounds(15, 300, 450, 100);
        codes.setBackground(Color.BLUE); 

        random_array();
        codeList.setVisibleRowCount(5);
        codeList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        codeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        codeList.addListSelectionListener(this::valueChanged);


        JScrollPane scrolledJob = new JScrollPane(codeList);
        scrolledJob.setBounds(0, 0, 450, 100);
        scrolledJob.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        codes.add(scrolledJob);


        Back = new JButton("BACKSPACE");
        Back.setBounds(15, 415, 150, 40);
        Back.addActionListener(this);

        Assign = new JButton("ASSIGN");
        Assign.setBounds(310, 415, 150, 40);
        Assign.addActionListener(this);

        add(master);
        add(codes);
        add(Back);
        add(Assign);
    }
    public void random_array(){
        
        Random r1 = new Random();
        String[] ran_arr = new String[7];

        int i = 0, rnum;
        rnum=r1.nextInt(7);
        ran_num[i]=rnum;
        ran_arr[i]=pro.current_codes[rnum];
        cnt++;

        while(i<6){
            rnum=r1.nextInt(7);
            if(!hasContain(ran_num, rnum))
            {
                i++; cnt++;
                ran_num[i]=rnum;
                ran_arr[i]=pro.current_codes[rnum];
            }
        }
        codeList = new JList<>(ran_arr);
       


    }
    public boolean hasContain(int[] indexs, int one){
        for(int i =0; i<cnt; i++){
            if(indexs[i]==one)
                return true;
        }
        return false;
    }
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
            String ls = (String)codeList.getSelectedValue()+"\n";
            int a = ran_num[(int)codeList.getSelectedIndex()];
            index_code.push(Integer.valueOf(a));
            selected_code.push(ls);
            valuePrint();
        }
    }
    public void valuePrint(){
        codeArea.setText("");
        master.add(codeArea);
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : selected_code) {
            stringBuilder.append(item);
        }
        String strAppend = stringBuilder.toString();
        codeArea.append(strAppend);
        master.add(codeArea);
    }
    private void assumingAction(ActionEvent e) {
        // TODO Auto-generated method stub
        String actionCommand = e.getActionCommand();
        int a;

        if(actionCommand.equals("BACKSPACE")) {
           
            selected_code.pop();
            index_code.pop();
            valuePrint();
        }
        else if(actionCommand.equals("ASSIGN")) {
            a = checking_index();
            if(a==0)
            {
                codeArea.setText("Wrong Answer !");
                master.add(codeArea);
                t_timer.schedule(t_task, 1000);
            }
            else if(a==1)
            {
                codeArea.setText("Please use all code !\n");
                master.add(codeArea);
            }
            else if(a==2){
                codeArea.setText("Correct Answer !");
                master.add(codeArea);
                t_timer.schedule(t_task, 1000);

            }
            else{
                //error
            }
        }
        else {
            //오류
        }

    }
    public int checking_index(){
        int cnt = index_code.size();
        int i = 7;
        if(cnt<7)
        {
            return 1;
        }
        else{
            while(i-->0){
                if(i!=index_code.pop())
                {
                    return 0;
                }

            }

        }
        return 2;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        try{
            assumingAction(e);

        }catch(NumberFormatException e2) {
            
        }
    }

}

