
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
public class ex1 extends JFrame{//�α���ȭ��
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
		Font f=new Font("����", Font.ITALIC, 15);
		JLabel t1= new JLabel("�ð�ǥ�� ��� ¥������ �𸣰ڴٱ���?");
		t1.setFont(f);
		t1.setOpaque(false);
		add(t1);
		JLabel t2= new JLabel("� ������ ������ �´��� �� �𸣰ڴٱ���?");
		t2.setFont(f);
		t2.setOpaque(false);
		add(t2);
		JLabel t3= new JLabel("�׷� �� knu �ð�ǥ �����縦 ����غ�����!");
		t3.setFont(f);
		t3.setOpaque(false);
		add(t3);
		JLabel t4= new JLabel("������ �ð�ǥ�� ���� �� �ֽ��ϴ� :)");
		t4.setFont(f);
		t4.setOpaque(false);
		add(t4);
		JLabel b2= new JLabel("���̵�:");
		b2.setFont(f);
		b2.setOpaque(false);
		add(b2);
		JLabel b3= new JLabel("��й�ȣ:");
		b3.setFont(f);
		b3.setOpaque(false);
		add(b3);
		TextField b4 = new TextField();
		add(b4);
		TextField b5 = new TextField();
		add(b5);
		b5.setEchoChar('*');//��ȣȭ
		JButton b6 = new JButton("�α���");
		add(b6);
		JButton b7 = new JButton("ȸ������");
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
		setTitle("knu �ð�ǥ ������");
		setVisible(true);
		b7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//ȸ������â���� �̵�
				// TODO Auto-generated method stub
				ex2 f2= new ex2();
			}
		});;
		b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {//�α��� �Ҷ� 
				// TODO Auto-generated method stub
				try{
					String s;
					String[] array;
					int count=0;
					BufferedReader bos = new BufferedReader(new FileReader("ȸ�����.txt"));
					while((s=bos.readLine())!=null){
						array=s.split("/");
						if(b4.getText().equals(array[1])&&b5.getText().equals(array[2]))
						{
							count=0;
							JLabel message=new JLabel("�α��ο� �����Ͽ����ϴ�!");
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
						JLabel message=new JLabel("�α��ο� �����Ͽ����ϴ�!");
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

