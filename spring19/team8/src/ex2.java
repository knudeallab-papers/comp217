
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
public class ex2 extends JFrame {//ȸ������ȭ��
	public ex2(){
		  	JPanel p = new JPanel();
		  	Label l1= new Label("�̸�");
	        Label l2 = new Label("���̵�");
	        Label l3= new Label("�н�����");
	        Label l4 = new Label("�ּ�");
	        Label l6 = new Label("�й�");
	        Label l7 = new Label("����");
	        add(l1);
	        add(l2);
	        add(l3);
	        add(l4);
	        add(l6);
	        add(l7);
	        TextField t1 = new TextField();
	        TextField t2 = new TextField();
	        TextField t3 = new TextField();
	        TextField t4 = new TextField();
	        TextField t6 = new TextField();
	        TextField t7 = new TextField();
	        //���(ģ���߰� X)
	        
	        add(t1);
	        add(t2);
	        add(t3);
	        add(t4);
	        add(t6);
	        add(t7);
	        
	        t3.setEchoChar('*');
	        JButton j1 = new JButton("����");
	        JButton j2 = new JButton("���");
	        add(j1);
	        add(j2);
	        l1.setBounds(40, 10, 40, 40);
	        l2.setBounds(40, 50, 40, 40);
	        l3.setBounds(40,90,60,40);
	        l4.setBounds(40, 130, 40, 40);
	        l6.setBounds(40, 170, 60, 40);
	        l7.setBounds(40, 210, 60, 40);
	        t1.setBounds(120, 10, 200, 30);
	        t2.setBounds(120, 50, 200, 30);
	        t3.setBounds(120, 90, 200, 30);
	        t4.setBounds(120, 130, 200, 30);
	        t6.setBounds(120, 170, 200, 30);
	        t7.setBounds(120, 210, 200, 30);
	        j1.setBounds(125, 260, 80, 30);
	        j2.setBounds(240, 260, 80, 30); 
	    add(p);
		setSize(500,400);
		setTitle("ȸ������");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);
       j1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent T) {//ȸ������ ������ ����
			
			member m = new member();
	        int index=0;
	        m.setName(t1.getText());
	        m.setId(t2.getText());
	        m.setPassword(t3.getText());
	        m.setAddress(t4.getText());
	        m.setSid(t6.getText());
	        m.setMajor(t7.getText());
	        
	        ArrayList<member> test = new ArrayList<member>();
	        try {
	        	ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("memberFile"));
	        	test = (ArrayList<member>)inputStream.readObject();
	        	inputStream.close();
	        }catch(FileNotFoundException e) {
	        	System.err.println("cannot find file");
	        	//System.exit(0);
	        }catch(ClassNotFoundException e) {
	        	System.err.println("cannot find class");
	        	System.exit(0);
	        }catch(IOException e) {
	        	System.err.println("cannot find IOE");
	        	System.exit(0);
	        }
	        test.add(m);
	        System.out.println(test.size());
	        try {
	        	ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("memberFile"));
	        	outputStream.writeObject(test);
	        	outputStream.close();
	        }catch(IOException e){
		        System.err.println("Error writing to file");
		        System.exit(0);
		    }
			try{
				PrintWriter bos = new PrintWriter(new FileOutputStream("ȸ�����.txt",true));
				bos.print(t1.getText()+"/");
				bos.print(t2.getText()+"/");
				bos.print(t3.getText()+"/");
				bos.print(t4.getText()+"/");
				bos.print(t6.getText()+"/");
				bos.println(t7.getText()+"/");
				bos.close();
				JOptionPane.showMessageDialog(null, "ȸ�������� �����մϴ�!!");
				dispose();
			}catch (Exception ex){
				JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����Ͽ����ϴ�.");
			}
		}
	});
	
	j2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent T) {
			dispose();
		}
	});
	}
}

