
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;
import java.awt.*;
public class ex2 extends JFrame {
	public ex2(){
		  	JPanel p = new JPanel();
		  	JLabel l1= new JLabel("이름");	
	        JLabel l2 = new JLabel("아이디");
	        JLabel l3= new JLabel("패스워드");
	        JLabel l4 = new JLabel("주소");
	        JLabel l5 = new JLabel("추가사항");
	        add(l1);
	        add(l2);
	        add(l3);
	        add(l4);
	        add(l5);
	        JTextField t1 = new JTextField();
	        JTextField t2 = new JTextField();
	        JTextField t3 = new JTextField();
	        JTextField t4 = new JTextField();
	        JTextField t5 = new JTextField();
	        add(t1);
	        add(t2);
	        add(t3);
	        add(t4);
	        add(t5);
	       
	        JButton j1 = new JButton("저장");
	        JButton j2 = new JButton("취소");
	        add(j1);
	        add(j2);
	        l1.setBounds(40, 10, 40, 40);
	        l2.setBounds(40, 50, 40, 40);
	        l3.setBounds(40,90,60,40);
	        l4.setBounds(40, 130, 40, 40);
	        l5.setBounds(40, 170, 60, 40);
	        t1.setBounds(120, 10, 200, 30);
	        t2.setBounds(120, 50, 200, 30);
	        t3.setBounds(120, 90, 200, 30);
	        t4.setBounds(120, 130, 280, 30);
	        t5.setBounds(120, 180, 280, 120);
	        j1.setBounds(125, 330, 80, 30);
	        j2.setBounds(240, 330, 80, 30); 
	    add(p);
		setSize(500,500);
		setTitle("회원가입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);
       j1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent T) {
			try{
				BufferedWriter bos = new BufferedWriter(new FileWriter("register.txt",true));
				bos.write(t1.getText()+"/");
				bos.write(t2.getText()+"/");
				bos.write(t3.getText()+"/");
				bos.write(t4.getText()+"/");
				bos.write(t5.getText()+"\r\n");
				bos.close();
				JOptionPane.showMessageDialog(null,"회원가입 완료");
				dispose();
			}catch (Exception ex){
				JOptionPane.showMessageDialog(null, "회원가입 실패");
			}
		}
	});
	}    
	
}

