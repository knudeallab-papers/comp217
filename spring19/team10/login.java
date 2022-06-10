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

public class login extends JFrame implements ActionListener{
  JButton loginbutton;
  JPanel loginpanel;
  JTextField userid;
  JPasswordField pass;
  JButton newuserbutton;
  JLabel username;
  JLabel password;
  static int roomnum;
  static int supersid;
  static person user = new person();

  public static void main(String[] args) {
	  login l = new login();
	  l.setVisible(true);  
  }

  public login(){
    super("Login Autentification");
    setSize(550, 350);
    setLocation(500,280);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    loginpanel = new JPanel();
    userid = new JTextField(15);
    userid.setText("id");
    pass = new JPasswordField(15);
    pass.setText("password");
    newuserbutton = new JButton("New User");
    loginbutton = new JButton("Login");
    username = new JLabel(" ID  :");
    password = new JLabel("PW  :");
    loginbutton.addActionListener(this);
    newuserbutton.addActionListener(this);
    
    loginpanel.setLayout(null);

    userid.setBounds(150,60,250,30);
    pass.setBounds(150,100,250,30);
    username.setBounds(120,60, 80,30);
    password.setBounds(120,100,80,30);
    loginbutton.setBounds(150,140,120,40);
    newuserbutton.setBounds(280,140,120,40);

    loginpanel.add(userid);
    loginpanel.add(pass);
    loginpanel.add(username);
    loginpanel.add(password);
    loginpanel.add(loginbutton);
    loginpanel.add(newuserbutton);
    

    add(loginpanel);

    PrintWriter outputstream = null;
    String filename = "userdata.txt";
    File check = new File("userdata.txt");
    if(check.exists()){

    }
    else{
      try{
        File texting = new File("userdata.txt");
        outputstream = new PrintWriter(new FileOutputStream(filename));
        outputstream.println("");
        outputstream.close();
        
      }catch(IOException e){
        e.printStackTrace();
      }
    }

  	}
  
  	boolean flag = true;
  	int i=0;
  	ArrayList<person> plist = new ArrayList<person>();
  	ArrayList<student> slist = new ArrayList<student>();
@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		String tempid;
		String temppw;
		
		if( s.equals("New User"))
		{
			newuser user = new newuser();
			
			//System.out.println("new");
		}
		else if(s.equals("Login"))
		{
			tempid = userid.getText();
			temppw = pass.getText();
			person temp= new person();
			student stemp = new student();
			student login = new student(tempid,temppw);
			String tr;
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
			int j;
			for( j=0;j<slist.size(); j++)
			{
				if( login.toString().equals( slist.get(j).toString()   ) )
				{
					//System.out.println("환영합니다.");;
					user.name = plist.get(j).name;
					user.id=plist.get(j).id;
					user.pw=plist.get(j).pw;
					user.snumber=plist.get(j).snumber;
					break;
				}
			}
			if( j == slist.size() )
			{
				denied d = new denied();
			}
			else if( j< slist.size())
			{
				mymenu m = new mymenu();
				//System.out.println(user);		
				dispose();
			}
		}
	}
 }