import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class libseat extends JFrame implements ActionListener{
	
	JButton s1;
	JButton s2;
	JButton s3;
	JButton s4;
	JButton s5;
	JButton s6;
	JButton s7;
	JButton s8;
	JButton s9;
	
	seat[] sarr = new seat[9];
	
	public libseat() {
		
		
		super("library seat");
		setSize(400,500);
		setLocation(500,280);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		
		JButton returnbutton = new JButton("�ݳ�");
		returnbutton.setBounds(150,350,100,50);
		returnbutton.addActionListener(this);
		
		s1 = new JButton("1");
		s1.setBounds(75,50,50,50);
		//s1.setBackground(Color.GREEN);
		s1.addActionListener(this);
		
		s2 = new JButton("2");
		s2.setBounds(175,50,50,50);
		//s2.setBackground(Color.GREEN);
		s2.addActionListener(this);
	
		s3 = new JButton("3");
		s3.setBounds(275,50,50,50);
		//s3.setBackground(Color.GREEN);
		s3.addActionListener(this);
		
		s4 = new JButton("4");
		s4.setBounds(75,150,50,50);
		//s4.setBackground(Color.GREEN);
		s4.addActionListener(this);
		
		s5 = new JButton("5");
		s5.setBounds(175,150,50,50);
		//s5.setBackground(Color.GREEN);
		s5.addActionListener(this);
		
		s6 = new JButton("6");
		s6.setBounds(275,150,50,50);
		//s6.setBackground(Color.GREEN);
		s6.addActionListener(this);
		
		s7 = new JButton("7");
		s7.setBounds(75,250,50,50);
		//s7.setBackground(Color.GREEN);
		s7.addActionListener(this);
		
		s8 = new JButton("8");
		s8.setBounds(175,250,50,50);
		//s8.setBackground(Color.GREEN);
		s8.addActionListener(this);
		
		s9 = new JButton("9");
		s9.setBounds(275,250,50,50);
		//s9.setBackground(Color.GREEN);
		s9.addActionListener(this);
		
		
		add(returnbutton);
		
		
		File seatdat = new File("seat.dat");
		if( seatdat.exists() )
		{
			try {
				ObjectInputStream inputstream = new ObjectInputStream(new FileInputStream("seat.dat"));
				sarr = (seat[])inputstream.readObject();
				inputstream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else
		{
			for(int i=0;i<9;i++)
			{
				sarr[i] = new seat();
			}
			try {
				ObjectOutputStream outputstream = new ObjectOutputStream(new FileOutputStream("seat.dat"));
				outputstream.writeObject(sarr);
				outputstream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		if(sarr[0].checkgetter())
		{
			s1.setBackground(Color.RED);
		}
		else
		{
			s1.setBackground(Color.GREEN);
		}
		
		if(sarr[1].checkgetter())
		{
			s2.setBackground(Color.RED);
		}
		else
		{
			s2.setBackground(Color.GREEN);
		}
		
		if(sarr[2].checkgetter())
		{
			s3.setBackground(Color.RED);
		}
		else
		{
			s3.setBackground(Color.GREEN);
		}
		
		if(sarr[3].checkgetter())
		{
			s4.setBackground(Color.RED);
		}
		else
		{
			s4.setBackground(Color.GREEN);
		}
		
		if(sarr[4].checkgetter())
		{
			s5.setBackground(Color.RED);
		}
		else
		{
			s5.setBackground(Color.GREEN);
		}
		
		if(sarr[5].checkgetter())
		{
			s6.setBackground(Color.RED);
		}
		else
		{
			s6.setBackground(Color.GREEN);
		}
		
		if(sarr[6].checkgetter())
		{
			s7.setBackground(Color.RED);
		}
		else
		{
			s7.setBackground(Color.GREEN);
		}
		
		if(sarr[7].checkgetter())
		{
			s8.setBackground(Color.RED);
		}
		else
		{
			s8.setBackground(Color.GREEN);
		}
		
		if(sarr[8].checkgetter())
		{
			s9.setBackground(Color.RED);
		}
		else
		{
			s9.setBackground(Color.GREEN);
		}
		
		add(s1);
		add(s2);
		add(s3);
		add(s4);
		add(s5);
		add(s6);
		add(s7);
		add(s8);
		add(s9);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		if( s.equals("1") )
		{
			boolean test = true;
			if( !sarr[0].check )// �ڸ��� �������  boolean�� false
			{
				for(int i=0;i<9;i++)
				{
					if ( login.user.snumber == sarr[i].sidgetter() )//������ �̹� �ٸ��ڸ��� ������� ���
					{
						JFrame warning = new JFrame("warning");
						warning.setSize(300,300);
						warning.setLocation(500,280);
						warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						String labelstring = "you already have seat"+sarr[i].nogetter();
						JLabel ss = new JLabel(labelstring);
						warning.add(ss);
						warning.setVisible(true);
						test = false;
						break;
					}					
				}
				if( test )//���������� �뿩
				{
					JFrame warning = new JFrame("complete");
					warning.setSize(300,300);
					warning.setLocation(500,280);
					warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					String labelstring = login.user.name+"���� �ڸ��� ���������� �뿩 �Ǿ����ϴ�.";
					JLabel ss = new JLabel(labelstring);
					warning.add(ss);
					warning.setVisible(true);
					sarr[0].namesetter(login.user.name);
					sarr[0].sidsetter(login.user.snumber);
					sarr[0].nosetter(1);
					sarr[0].checksetter(true);
					s1.setBackground(Color.RED);

				}
			}
			else// �ڸ��� �����
			{
				JFrame warning = new JFrame("warning");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = "���ڸ��� "+sarr[0].namegetter()+sarr[0].sidgetter()+" ���� ������Դϴ�.";
				JLabel ss = new JLabel(labelstring);
				warning.add(ss);
				warning.setVisible(true);
				//s1.setBackground(Color.green);
			}
		}
		else if(s.equals("2"))
		{
			
			boolean test = true;
			if( !sarr[1].check )// �ڸ��� �������  boolean�� false
			{
				for(int i=0;i<9;i++)
				{
					if ( login.user.snumber == sarr[i].sidgetter() )//������ �̹� �ٸ��ڸ��� ������� ���
					{
						JFrame warning = new JFrame("warning");
						warning.setSize(300,300);
						warning.setLocation(500,280);
						warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						String labelstring = "you already have seat"+sarr[i].nogetter();
						JLabel ss = new JLabel(labelstring);
						warning.add(ss);
						warning.setVisible(true);
						test = false;
						break;
					}					
				}
				if( test )//���������� �뿩
				{
					JFrame warning = new JFrame("complete");
					warning.setSize(300,300);
					warning.setLocation(500,280);
					warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					String labelstring = login.user.name+"���� �ڸ��� ���������� �뿩 �Ǿ����ϴ�.";
					JLabel ss = new JLabel(labelstring);
					warning.add(ss);
					warning.setVisible(true);
					
					sarr[1].namesetter(login.user.name);
					sarr[1].sidsetter(login.user.snumber);
					sarr[1].nosetter(2);
					sarr[1].checksetter(true);
					s2.setBackground(Color.RED);

				}
			}
			else// �ڸ��� �����
			{
				JFrame warning = new JFrame("warning");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = "���ڸ��� "+sarr[1].namegetter()+sarr[1].sidgetter()+" ���� ������Դϴ�.";
				JLabel ss = new JLabel(labelstring);
				warning.add(ss);
				warning.setVisible(true);
				//s1.setBackground(Color.green);
			}
			
			
		}
		else if(s.equals("3"))
		{
			boolean test = true;
			if( !sarr[2].check )// �ڸ��� �������  boolean�� false
			{
				for(int i=0;i<9;i++)
				{
					if ( login.user.snumber == sarr[i].sidgetter() )//������ �̹� �ٸ��ڸ��� ������� ���
					{
						JFrame warning = new JFrame("warning");
						warning.setSize(300,300);
						warning.setLocation(500,280);
						warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						String labelstring = "you already have seat"+sarr[i].nogetter();
						JLabel ss = new JLabel(labelstring);
						warning.add(ss);
						warning.setVisible(true);
						test = false;
						break;
					}					
				}
				if( test )//���������� �뿩
				{
					JFrame warning = new JFrame("complete");
					warning.setSize(300,300);
					warning.setLocation(500,280);
					warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					String labelstring = login.user.name+"���� �ڸ��� ���������� �뿩 �Ǿ����ϴ�.";
					JLabel ss = new JLabel(labelstring);
					warning.add(ss);
					warning.setVisible(true);
					
					sarr[2].namesetter(login.user.name);
					sarr[2].sidsetter(login.user.snumber);
					sarr[2].nosetter(3);
					sarr[2].checksetter(true);
					s3.setBackground(Color.RED);

				}
			}
			else// �ڸ��� �����
			{
				JFrame warning = new JFrame("warning");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = "���ڸ��� "+sarr[2].namegetter()+sarr[2].sidgetter()+" ���� ������Դϴ�.";
				JLabel ss = new JLabel(labelstring);
				warning.add(ss);
				warning.setVisible(true);
				//s1.setBackground(Color.green);
			}
		}
		else if(s.equals("4"))
		{
			boolean test = true;
			if( !sarr[3].check )// �ڸ��� �������  boolean�� false
			{
				for(int i=0;i<9;i++)
				{
					if ( login.user.snumber == sarr[i].sidgetter() )//������ �̹� �ٸ��ڸ��� ������� ���
					{
						JFrame warning = new JFrame("warning");
						warning.setSize(300,300);
						warning.setLocation(500,280);
						warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						String labelstring = "you already have seat"+sarr[i].nogetter();
						JLabel ss = new JLabel(labelstring);
						warning.add(ss);
						warning.setVisible(true);
						test = false;
						break;
					}					
				}
				if( test )//���������� �뿩
				{
					JFrame warning = new JFrame("complete");
					warning.setSize(300,300);
					warning.setLocation(500,280);
					warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					String labelstring = login.user.name+"���� �ڸ��� ���������� �뿩 �Ǿ����ϴ�.";
					JLabel ss = new JLabel(labelstring);
					warning.add(ss);
					warning.setVisible(true);
					
					sarr[3].namesetter(login.user.name);
					sarr[3].sidsetter(login.user.snumber);
					sarr[3].nosetter(4);
					sarr[3].checksetter(true);
					s4.setBackground(Color.RED);

				}
			}
			else// �ڸ��� �����
			{
				JFrame warning = new JFrame("warning");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = "���ڸ��� "+sarr[3].namegetter()+sarr[3].sidgetter()+" ���� ������Դϴ�.";
				JLabel ss = new JLabel(labelstring);
				warning.add(ss);
				warning.setVisible(true);
				//s1.setBackground(Color.green);
			}
				
		}
		else if(s.equals("5"))
		{
			boolean test = true;
			if( !sarr[4].check )// �ڸ��� �������  boolean�� false
			{
				for(int i=0;i<9;i++)
				{
					if ( login.user.snumber == sarr[i].sidgetter() )//������ �̹� �ٸ��ڸ��� ������� ���
					{
						JFrame warning = new JFrame("warning");
						warning.setSize(300,300);
						warning.setLocation(500,280);
						warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						String labelstring = "you already have seat"+sarr[i].nogetter();
						JLabel ss = new JLabel(labelstring);
						warning.add(ss);
						warning.setVisible(true);
						test = false;
						break;
					}					
				}
				if( test )//���������� �뿩
				{
					JFrame warning = new JFrame("complete");
					warning.setSize(300,300);
					warning.setLocation(500,280);
					warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					String labelstring = login.user.name+"���� �ڸ��� ���������� �뿩 �Ǿ����ϴ�.";
					JLabel ss = new JLabel(labelstring);
					warning.add(ss);
					warning.setVisible(true);
					
					sarr[4].namesetter(login.user.name);
					sarr[4].sidsetter(login.user.snumber);
					sarr[4].nosetter(5);
					sarr[4].checksetter(true);
					s5.setBackground(Color.RED);

				}
			}
			else// �ڸ��� �����
			{
				JFrame warning = new JFrame("warning");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = "���ڸ��� "+sarr[4].namegetter()+sarr[4].sidgetter()+" ���� ������Դϴ�.";
				JLabel ss = new JLabel(labelstring);
				warning.add(ss);
				warning.setVisible(true);
				//s1.setBackground(Color.green);
			}
			
		}
		else if(s.equals("6"))
		{
			boolean test = true;
			if( !sarr[5].check )// �ڸ��� �������  boolean�� false
			{
				for(int i=0;i<9;i++)
				{
					if ( login.user.snumber == sarr[i].sidgetter() )//������ �̹� �ٸ��ڸ��� ������� ���
					{
						JFrame warning = new JFrame("warning");
						warning.setSize(300,300);
						warning.setLocation(500,280);
						warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						String labelstring = "you already have seat"+sarr[i].nogetter();
						JLabel ss = new JLabel(labelstring);
						warning.add(ss);
						warning.setVisible(true);
						test = false;
						break;
					}					
				}
				if( test )//���������� �뿩
				{
					JFrame warning = new JFrame("complete");
					warning.setSize(300,300);
					warning.setLocation(500,280);
					warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					String labelstring = login.user.name+"���� �ڸ��� ���������� �뿩 �Ǿ����ϴ�.";
					JLabel ss = new JLabel(labelstring);
					warning.add(ss);
					warning.setVisible(true);
					
					sarr[5].namesetter(login.user.name);
					sarr[5].sidsetter(login.user.snumber);
					sarr[5].nosetter(6);
					sarr[5].checksetter(true);
					s6.setBackground(Color.RED);

				}
			}
			else// �ڸ��� �����
			{
				JFrame warning = new JFrame("warning");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = "���ڸ��� "+sarr[5].namegetter()+sarr[5].sidgetter()+" ���� ������Դϴ�.";
				JLabel ss = new JLabel(labelstring);
				warning.add(ss);
				warning.setVisible(true);
				//s1.setBackground(Color.green);
			}
			
		}
		else if(s.equals("7"))
		{
			
			boolean test = true;
			if( !sarr[6].check )// �ڸ��� �������  boolean�� false
			{
				for(int i=0;i<9;i++)
				{
					if ( login.user.snumber == sarr[i].sidgetter() )//������ �̹� �ٸ��ڸ��� ������� ���
					{
						JFrame warning = new JFrame("warning");
						warning.setSize(300,300);
						warning.setLocation(500,280);
						warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						String labelstring = "you already have seat"+sarr[i].nogetter();
						JLabel ss = new JLabel(labelstring);
						warning.add(ss);
						warning.setVisible(true);
						test = false;
						break;
					}					
				}
				if( test )//���������� �뿩
				{
					JFrame warning = new JFrame("complete");
					warning.setSize(300,300);
					warning.setLocation(500,280);
					warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					String labelstring = login.user.name+"���� �ڸ��� ���������� �뿩 �Ǿ����ϴ�.";
					JLabel ss = new JLabel(labelstring);
					warning.add(ss);
					warning.setVisible(true);
					
					sarr[6].namesetter(login.user.name);
					sarr[6].sidsetter(login.user.snumber);
					sarr[6].nosetter(7);
					sarr[6].checksetter(true);
					s7.setBackground(Color.RED);

				}
			}
			else// �ڸ��� �����
			{
				JFrame warning = new JFrame("warning");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = "���ڸ��� "+sarr[6].namegetter()+sarr[6].sidgetter()+" ���� ������Դϴ�.";
				JLabel ss = new JLabel(labelstring);
				warning.add(ss);
				warning.setVisible(true);
				//s1.setBackground(Color.green);
			}
			
		}
		else if(s.equals("8"))
		{
			boolean test = true;
			if( !sarr[7].check )// �ڸ��� �������  boolean�� false
			{
				for(int i=0;i<9;i++)
				{
					if ( login.user.snumber == sarr[i].sidgetter() )//������ �̹� �ٸ��ڸ��� ������� ���
					{
						JFrame warning = new JFrame("warning");
						warning.setSize(300,300);
						warning.setLocation(500,280);
						warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						String labelstring = "you already have seat"+sarr[i].nogetter();
						JLabel ss = new JLabel(labelstring);
						warning.add(ss);
						warning.setVisible(true);
						test = false;
						break;
					}					
				}
				if( test )//���������� �뿩
				{
					JFrame warning = new JFrame("complete");
					warning.setSize(300,300);
					warning.setLocation(500,280);
					warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					String labelstring = login.user.name+"���� �ڸ��� ���������� �뿩 �Ǿ����ϴ�.";
					JLabel ss = new JLabel(labelstring);
					warning.add(ss);
					warning.setVisible(true);
					
					sarr[7].namesetter(login.user.name);
					sarr[7].sidsetter(login.user.snumber);
					sarr[7].nosetter(8);
					sarr[7].checksetter(true);
					s8.setBackground(Color.RED);

				}
			}
			else// �ڸ��� �����
			{
				JFrame warning = new JFrame("warning");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = "���ڸ��� "+sarr[7].namegetter()+sarr[7].sidgetter()+" ���� ������Դϴ�.";
				JLabel ss = new JLabel(labelstring);
				warning.add(ss);
				warning.setVisible(true);
				//s1.setBackground(Color.green);
			}
			
			
		}
		else if(s.equals("9"))
		{
			boolean test = true;
			if( !sarr[8].check )// �ڸ��� �������  boolean�� false
			{
				for(int i=0;i<9;i++)
				{
					if ( login.user.snumber == sarr[i].sidgetter() )//������ �̹� �ٸ��ڸ��� ������� ���
					{
						JFrame warning = new JFrame("warning");
						warning.setSize(300,300);
						warning.setLocation(500,280);
						warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						String labelstring = "you already have seat"+sarr[i].nogetter();
						JLabel ss = new JLabel(labelstring);
						warning.add(ss);
						warning.setVisible(true);
						test = false;
						break;
					}					
				}
				if( test )//���������� �뿩
				{
					JFrame warning = new JFrame("complete");
					warning.setSize(300,300);
					warning.setLocation(500,280);
					warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					String labelstring = login.user.name+"���� �ڸ��� ���������� �뿩 �Ǿ����ϴ�.";
					JLabel ss = new JLabel(labelstring);
					warning.add(ss);
					warning.setVisible(true);
					
					sarr[8].namesetter(login.user.name);
					sarr[8].sidsetter(login.user.snumber);
					sarr[8].nosetter(9);
					sarr[8].checksetter(true);
					s9.setBackground(Color.RED);

				}
			}
			else// �ڸ��� �����
			{
				JFrame warning = new JFrame("warning");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = "���ڸ��� "+sarr[8].namegetter()+sarr[8].sidgetter()+" ���� ������Դϴ�.";
				JLabel ss = new JLabel(labelstring);
				warning.add(ss);
				warning.setVisible(true);
				//s1.setBackground(Color.green);
			}
			
			
		}
		
		else if(s.equals("�ݳ�"))
		{
			int count=0;
			for(int i=0;i<9; i++)
			{
				if( sarr[i].sidgetter() == login.user.snumber )
				{
					count = sarr[i].nogetter();
					System.out.println(count);
				}
				
			}
				
			if( count > 0 )//�ݳ��� �ڸ��� �ִ� ���
			{
				JFrame warning = new JFrame("complete");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = count+ "�� �ڸ��� �ݳ� �Ǿ����ϴ�."+"("+login.user.name+")";
				JLabel ss = new JLabel(labelstring);
				warning.add(ss);
				warning.setVisible(true);
				if( count == 1)
				{
					s1.setBackground(Color.GREEN);
					sarr[0].checksetter(false);
					sarr[0].namesetter(null);
					sarr[0].sidsetter(0);
					sarr[0].nosetter(0);
				}
				else if( count == 2)
				{
					s2.setBackground(Color.GREEN);
					sarr[1].checksetter(false);
					sarr[1].namesetter(null);
					sarr[1].sidsetter(0);
					sarr[1].nosetter(0);
				}
				else if( count == 3)
				{
					s3.setBackground(Color.GREEN);
					sarr[2].checksetter(false);
					sarr[2].namesetter(null);
					sarr[2].sidsetter(0);
					sarr[2].nosetter(0);
		
				}
				else if( count == 4)
				{
					s4.setBackground(Color.GREEN);
					sarr[3].checksetter(false);
					sarr[3].namesetter(null);
					sarr[3].sidsetter(0);
					sarr[3].nosetter(0);
		
				}
				else if( count == 5)
				{
					s5.setBackground(Color.GREEN);
					sarr[4].checksetter(false);
					sarr[4].namesetter(null);
					sarr[4].sidsetter(0);
					sarr[4].nosetter(0);
				}
				else if( count == 6)
				{
					s6.setBackground(Color.GREEN);
					sarr[5].checksetter(false);
					sarr[5].namesetter(null);
					sarr[5].sidsetter(0);
					sarr[5].nosetter(0);
				}
				else if( count == 7)
				{
					s7.setBackground(Color.GREEN);
					sarr[6].checksetter(false);
					sarr[6].namesetter(null);
					sarr[6].sidsetter(0);
					sarr[6].nosetter(0);
				}
				else if( count == 8)
				{
					s8.setBackground(Color.GREEN);
					sarr[7].checksetter(false);
					sarr[7].namesetter(null);
					sarr[7].sidsetter(0);
					sarr[7].nosetter(0);
				}
				else if( count == 9)
				{
					s9.setBackground(Color.GREEN);
					sarr[8].checksetter(false);
					sarr[8].namesetter(null);
					sarr[8].sidsetter(0);
					sarr[8].nosetter(0);
				}
							
			}
			else//�ݳ��� �ڸ��� ���� ���
			{
				JFrame warning = new JFrame("complete");
				warning.setSize(300,300);
				warning.setLocation(500,280);
				warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String labelstring = login.user.name+" ���� �ݳ��� �ڸ��� �����ϴ�.";
				JLabel jstring = new JLabel(labelstring);
				warning.add(jstring);
				warning.setVisible(true);
			}
		}
		try {
			ObjectOutputStream outputstream = new ObjectOutputStream(new FileOutputStream("seat.dat"));
			outputstream.writeObject(sarr);
			outputstream.close();
		} catch (FileNotFoundException t) {
			// TODO Auto-generated catch block
			t.printStackTrace();
		} catch (IOException t) {
			// TODO Auto-generated catch block
			t.printStackTrace();
		}
	}
}