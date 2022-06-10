import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class newuser extends JFrame implements ActionListener {

	person np = new person();
	
	JButton create;
	JButton repetition;
	JPanel nu;
	JTextField id;
	JPasswordField pw;
	JTextField name;
	JTextField snumber;
	JLabel idl;
	JLabel pwl;
	JLabel namel;
	JLabel snumberl;
		
	PrintWriter outputstream = null;
    String filename;
	
	public newuser() {
		super("new user");
		setSize(450,400);
		setLocation(500,280);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		nu = new JPanel();
		nu.setLayout(null);
		
		create = new JButton("Create");
		create.addActionListener(this);
	    
		repetition = new JButton("중복확인");
		repetition.setActionCommand("check");
		repetition.addActionListener(this);
		repetition.setBounds(300,70,90,30);
		
	    id = new JTextField(15);
	    pw = new JPasswordField(15);
	    name = new JTextField(15);
	    snumber = new JTextField(15);
	    idl = new JLabel(" ID :");
	    pwl = new JLabel("PW :");
	    namel = new JLabel("이름");
	    snumberl = new JLabel("학번");
	    
	    
	    create.setBounds(150,200,100,40);
	    
	    idl.setBounds(100,70,30,20);
	    id.setBounds(140,70,150,20);
	    
	    pwl.setBounds(100,100,30,20);
	    pw.setBounds(140,100,150,20);
	    
	    namel.setBounds(100,130,30,20);
	    name.setBounds(140,130,150,20);
	    
	    snumberl.setBounds(100, 160, 30, 20);
	    snumber.setBounds(140, 160, 150, 20);
	    
	    nu.add(idl);
	    nu.add(id);
	    nu.add(repetition);
	    nu.add(pwl);
	    nu.add(pw);
	    nu.add(namel);
	    nu.add(name);
	    nu.add(snumberl);
	    nu.add(snumber);
	    nu.add(create);
	   
	    add(nu);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		String tempid = id.getText();
		String temppw = pw.getText();
		String tempname = name.getText();
		String tempsnumber = snumber.getText();
		
		boolean confirm = false;
		
		if(s.equals("Create"))
		{
			
			np.id = tempid;
			np.name = tempname;
			np.pw = temppw;
			np.snumber = Integer.parseInt(tempsnumber);
			File texting = new File("userdata.txt");
			/*if( texting.exists() )
	        {
	        	System.out.println("hi");
	        }*/
			try{
				String filename = "userdata.txt";
		        outputstream = new PrintWriter(new FileOutputStream(filename,true));
		        outputstream.println(np.toString());
		        outputstream.close();
		      }catch(IOException a){
		        a.printStackTrace();
		      }
			
			dispose();
			
		}
		
		if(s.equals("check"))
		{
			String tr;
			int i = 0;
			
			boolean flag = true;
			
			person temp = new person();
			student stemp= new student();
			
			
			ArrayList<person> plist = new ArrayList<person>();
		  	ArrayList<student> slist = new ArrayList<student>();
			Scanner inputStream = null;
			try {
				inputStream = new Scanner(new FileInputStream("userdata.txt"));
			}catch(FileNotFoundException f) {
				f.printStackTrace();
				System.exit(0);
			}
			
			while(inputStream.hasNextLine() && flag )
			{
				temp.id = inputStream.next();
				temp.pw = inputStream.next();
				temp.name = inputStream.next();
				temp.snumber = inputStream.nextInt();
				tr = inputStream.nextLine();
				
				stemp.id = temp.id;
				stemp.pw = temp.pw;
				
				plist.add(new person(temp));
				slist.add(new student(stemp));
				i++;
			}
			flag = false;
			
			for(int j=0; j<i ; j++)
			{
				if( tempid.equals(slist.get(j).id) )
				{
					confirm = true;
					break;
				}
			}
			
			
			if( confirm ) // 아이디 중복이 있을때
			{
				checkrepeat ch = new checkrepeat();
			}
			
			else//아이디 중복이 없을떄
			{
				notexist n = new notexist();
			}
		}
	}
			
}