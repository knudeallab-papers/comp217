import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
public class ex1 extends JFrame{
	Image img = null;
	public ex1()
	{
		JPanel p = new JPanel();
        p.setLayout(null);
      
		JLabel b2= new JLabel("아이디 : ");
		JLabel b3= new JLabel("비밀번호 :");
		add(b2);
		add(b3);
		JTextField b4 = new JTextField();
		add(b4);
		JTextField b5 = new JTextField();
		add(b5);
		
		JButton b6 = new JButton("로그인");
		add(b6);
		JButton b7 = new JButton("회원가입");
		add(b7);
		
		
		b2.setBounds(40, 85, 60, 60);
		b3.setBounds(40, 135, 60, 60);
		b4.setBounds(150, 100, 200, 30);
		b5.setBounds(150, 150, 200, 30);
		b6.setBounds(380, 100, 80, 30);
		b7.setBounds(380, 150, 90, 30);
		add(p);
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("로그인");
		setVisible(true);
		b7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ex2 f2= new ex2();
			}
		});;
		b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				// TODO Auto-generated method stub
				try{
					String s;
					String[] array;
					BufferedReader bos = new BufferedReader(new FileReader("register.txt"));
					while((s=bos.readLine())!=null){
						array=s.split("/");
					if(b4.getText().equals(array[1])&&b5.getText().equals(array[2]))
					{
						JOptionPane.showMessageDialog(null,"로그인완료 ");
						MainGUI ss = new MainGUI();
						ss.setVisible(true);
						dispose();
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "로그인실패 (성공시 까지 눌러주세요. 만약 계속실패시 그아이디는 존재하지않음).");
					}
					}
					bos.close();
					dispose();
				}catch (IOException E10){
					E10.printStackTrace();
				}
			}
		});
	}
	public static void main(String[] args) {
		ex1 sss = new ex1();
		sss.setVisible(true);
	}
}

