
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.*;
public class ex1 extends JFrame{//로그인화면
	Image img = null;
	
	public ex1()
	{
		try {
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.jpg")))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JPanel p = new JPanel();
        p.setLayout(null);
        JLabel label = new JLabel(new ImageIcon("main_logo.png"));
		add(label);
		Font f=new Font("돋움", Font.ITALIC, 15);
		JLabel t1= new JLabel("시간표를 어떻게 짜야할지 모르겠다구요?");
		t1.setFont(f);
		t1.setOpaque(false);
		add(t1);
		JLabel t2= new JLabel("어떤 수업이 나에게 맞는지 잘 모르겠다구요?");
		t2.setFont(f);
		t2.setOpaque(false);
		add(t2);
		JLabel t3= new JLabel("그럴 땐 knu 시간표 마법사를 사용해보세요!");
		t3.setFont(f);
		t3.setOpaque(false);
		add(t3);
		JLabel t4= new JLabel("나만의 시간표를 만들 수 있습니다 :)");
		t4.setFont(f);
		t4.setOpaque(false);
		add(t4);
		JLabel b2= new JLabel("아이디:");
		b2.setFont(f);
		b2.setOpaque(false);
		add(b2);
		JLabel b3= new JLabel("비밀번호:");
		b3.setFont(f);
		b3.setOpaque(false);
		add(b3);
		TextField b4 = new TextField();
		add(b4);
		TextField b5 = new TextField();
		add(b5);
		b5.setEchoChar('*');//암호화
		JButton b6 = new JButton("로그인");
		add(b6);
		JButton b7 = new JButton("회원가입");
		add(b7);
		
		label.setBounds(10, 25, 350, 255);
		t1.setBounds(360, 75, 300, 40);
		t2.setBounds(360,110, 300, 40);
		t3.setBounds(360, 145,300, 40);
		t4.setBounds(360, 180, 300, 40);
		b2.setBounds(90, 315, 60, 30);
		b3.setBounds(90, 355, 70, 30);
		b4.setBounds(200, 315, 200, 30);
		b5.setBounds(200, 355, 200, 30);
		b6.setBounds(430, 315, 80, 30);
		b7.setBounds(430, 355, 90, 30);
		add(p);
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("knu 시간표 마법사");
		setVisible(true);
		b7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				// TODO Auto-generated method stub
				ex2 f2= new ex2();
			}
		});;
		b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {//로그인 할때 
				// TODO Auto-generated method stub
				try{
					String s;
					String[] array;
					int count=0;
					BufferedReader bos = new BufferedReader(new FileReader("회원명단.txt"));
					while((s=bos.readLine())!=null){
						array=s.split("/");
						if(b4.getText().equals(array[1])&&b5.getText().equals(array[2]))
						{
							count=0;
							JLabel message=new JLabel("로그인에 성공하였습니다!");
							message.setHorizontalAlignment(JLabel.CENTER);
							JOptionPane.showMessageDialog(null, message);
							dispose();
							
							mainpage main=new mainpage(array[1], array[2]);
							main.setVisible(true);
							break;
						}
						else 
						{
							count++;
						}
					}
					if(count!=0) {
						JLabel message=new JLabel("로그인에 실패하였습니다!");
						message.setHorizontalAlignment(JLabel.CENTER);
						JOptionPane.showMessageDialog(null, message);						
						//bos.close();
						//dispose();
					}
					
				}catch (IOException E10){
					E10.printStackTrace();
				}
			}
		});
	}
}

