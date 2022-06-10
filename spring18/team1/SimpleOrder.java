import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SimpleOrder extends JFrame implements ActionListener{
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final int NUMBER_OF_DIGITS = 30;
	
	private static Menu[] menu = new Menu[10];
	static JButton[] menuButton = new JButton[10];
	private static int[] menuItemNum = new int[10];
	private static int menuNum;
	JLabel menuList[] = new JLabel[10];
	JLabel numList[] = new JLabel[10];
	JLabel sumLabel = new JLabel("");
	
	private static JTextArea ioField;
	private String result = "";
	private int whereResult;
	GridBagConstraints c = new GridBagConstraints();
	GridBagLayout gridbag = new GridBagLayout();
	JPanel textPanel;
	
	public Double[] d = new Double[2];
	public String location = null;
	
	
	
	public static void main(String[] args) throws IOException{
		
		menuNum = 4;
		for (int i=0;i<menuNum;i++) {
			menu[i] = new Menu();
		}
        menu[0].name = "까르보나라 스파게티";
        menu[0].setPrice(1000);
        menu[1].name = "이탈리아 피자";
        menu[1].setPrice(2000);
        menu[2].name = "고오급 스테이크";
        menu[2].setPrice(3000);
        menu[3].name = "봉골레 파스타";
        menu[3].setPrice(4000);
//        menu[3].name = "봉골레 파스타2";
//        menu[3].setPrice(4000);
       
      SimpleOrder s = new SimpleOrder();
      s.setVisible(true);
       
        
    }
 
    public void ClientRun(String data){
        
    		Scanner scan = new Scanner(System.in);
        Socket socket = null;
        OutputStream os = null;
        OutputStreamWriter osw =null;
        ObjectOutputStream soos = null;
        BufferedWriter bw = null;
        
        InputStream is =null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        
        for (int i=0;i<menuNum;i++) {
        		menu[i].num = menuItemNum[i];
        }
        
        try{
        	//59.25.242.254
//            socket = new Socket("localhost", 4200);
        		socket = new Socket("127.0.0.1", 4200);
//        	socket = new Socket("59.25.242.254", 4200);
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            soos = new ObjectOutputStream(os);
            bw = new BufferedWriter(osw);            //서버로 전송을 위한 OutputStream
        
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);        // 서버로부터 Data를 받음
            
           
//            System.out.print("데이터를 전송합니다.\n입력 : ");
//            String a = scan.nextLine();
//            bw.write(data);
//            bw.write(a);
           // String tmp = Double.toString(d[0]);
            
            soos.writeObject(menu);
//          soos.writeUTF(tmp);
//          tmp = Double.toString(d[1]);
        //    soos.writeUTF(tmp);
//          soos.writeUTF(location);
            soos.flush();
            
//            bw.newLine();
//            bw.flush();
            
            String receiveData = "";
//            try {
//            		while(true) {
//            			receiveData = receiveData + br.readLine();
//            		}
//            } catch(EOFException e) {
//            		System.out.println("주문완료 : \n" + receiveData);
//            }
//            receiveData = br.readLine();        // 서버로부터 데이터 한줄 읽음
//            System.out.println("주문완료 : \n" + receiveData);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
            		soos.close();
                bw.close();
                osw.close();
                os.close();
                br.close();
                isr.close();
                is.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }    
        
    }
    
    public SimpleOrder()  {
    		
    		int useNum=0;
    		String textMessage = "Your order is hear.";
    		int row, col;
    		row = 1;
    		int textLine=5;
    		if (menuNum >= 3) row = 2;
    		if (menuNum <= 2) col = menuNum;
    		else col = (menuNum+1)/2;
    	
    		
    		setTitle("Order Sheet");
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setSize(WIDTH, HEIGHT);
    		setLayout(new BorderLayout());
    		
    		for (int i=0;i<menuNum;i++) {
    			menuList[i] = new JLabel("");
    			numList[i] = new JLabel("");
    		}
    		
    		
//    		textPanel.setSize(200,400);
//    		textPanel.setLayout(gridbag);
//    		c.fill = GridBagConstraints.BOTH;
//    		c.weightx=1.0;
//    		c.weighty=1.0;
//    		
//    		if (menuNum == 1) {
//    			layout(new JLabel(menu[0].name.toString()), 0,0,1,1);
//    		} else if (menuNum == 2) {
//    			layout(new JLabel(menu[0].name), 0,0,1,1);
//    			layout(new JLabel(menu[1].name), 1,0,1,1);
//    		} else {
////	    		for (int i=0;i<menuNum;i++) {
////	    			layout(new JLabel(menu[i].name), i%row, i/row,1,1);
////	    		}
//    			layout(new JButton(menu[0].name), 0,0,1,1);
//    			layout(new JButton(menu[1].name), 0,1,1,1);
//    			layout(new JButton(menu[2].name), 0,2,1,1);
//    			layout(new JButton(menu[3].name), 0,3,1,1);
//    			
//    		}
//    		
//    		pack();
    		textPanel = new JPanel();
    		textPanel.setLayout(new GridLayout(menuNum+3,2));
    		textPanel.add(new JLabel("menu"));
    		textPanel.add(new JLabel("num"));
    		
    		for (int i=0;i<menuNum;i++) {
    			textPanel.add(menuList[i]);
    			textPanel.add(numList[i]);
    		}
    		textPanel.add(new JLabel(""));
    		textPanel.add(new JLabel(""));
    		textPanel.add(new JLabel("Total : "));
    		textPanel.add(sumLabel);
    		add(textPanel, BorderLayout.NORTH);
    		
    		
//    		if (menuNum >=3)
//    			textLine = menuNum +3;
//    		ioField = new JTextArea(textMessage,textLine, NUMBER_OF_DIGITS);
//    		ioField.setBackground(Color.WHITE);
//    		textPanel.add(ioField);
    		
    		
    		//Make Button Panel 
    		JPanel btnPanel = new JPanel();
    		btnPanel.setBackground(Color.BLUE);
    		btnPanel.setLayout(new FlowLayout());
    		
    		JPanel menuPanel = new JPanel();
    		menuPanel.setLayout(new GridLayout(row,col));
    		for (int i=0;i<menuNum;i++) {
    			menuButton[i] = new JButton(menu[i].name);
    			menuButton[i].addActionListener(this);
    			menuPanel.add(menuButton[i]);
    		}
    		btnPanel.add(menuPanel);
    		
    		JPanel calPanel = new JPanel();
    		calPanel.setLayout(new GridLayout(3,2));
    		
    		JButton addButton = new JButton("+");
    		addButton.addActionListener(this);
    		calPanel.add(addButton);
    		
    		JButton subtractButton = new JButton("-");
    		subtractButton.addActionListener(this);
    		calPanel.add(subtractButton);
    		
    		JButton resetButton = new JButton("Reset");
    		resetButton.addActionListener(this);
    		calPanel.add(resetButton);
    		
    		JButton sendButton = new JButton("Send");
    		sendButton.addActionListener(this);
    		calPanel.add(sendButton);
    		
    		JButton locationButton = new JButton("Loc");
    		locationButton.addActionListener(this);
    		calPanel.add(locationButton);
    		
    		btnPanel.add(calPanel);
    		
    		add(btnPanel, BorderLayout.CENTER);

    }
    
    public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		try {
//			assumingCorrectNumberFormats(e);
//		} catch(NumberFormatException e2) {
//			ioField.setText("Error: Reenter Number.");
//		}
    	
    		String actionCommand = e.getActionCommand();
		int b = 0;
		for (int i=0;i<menuNum;i++) {
			if (actionCommand.equals(menu[i].name)) {
				result = menu[i].name;
				whereResult = i;
				b=1;
				break;
			}
		}
		if (b == 0) {
			if (actionCommand.equals("+")) {
				menuItemNum[whereResult]++;
				setTextLabel();
			} else if (actionCommand.equals("-")) {
				if (menuItemNum[whereResult] != 0) {
					menuItemNum[whereResult]--;
					setTextLabel();
				}
				setTextLabel();
			}  else if (actionCommand.equals("Reset")) {
				for (int i=0;i<menuNum;i++) {
					menuItemNum[i] = 0;
				}
				setTextLabel();
			} else if (actionCommand.equals("Send")) {
				ClientRun("");
				
			} else if (actionCommand.equals("Loc")) {
				GoogleMap map = new GoogleMap();
				d = map.getDouble();
				location = map.getLocation2();
				
			} else {
				ioField.setText("Unexpected error.");
			}
		}
		
	}
    private void assumingCorrectNumberFormats(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		
		for (int i=0;i<menuNum;i++) {
			if (actionCommand.equals(menu[i].name)) {
				result = menu[i].name;
				whereResult = i;
				break;
			}
		}
		if (actionCommand.equals("+")) {
			menuItemNum[whereResult]++;
			setTextLabel();
		} else if (actionCommand.equals("-")) {
			if (menuItemNum[whereResult] != 0) {
				menuItemNum[whereResult]--;
			}
			setTextLabel();
		}  else if (actionCommand.equals("Reset")) {
			for (int i=0;i<menuNum;i++) {
				menuItemNum[i] = 0;
			}
			setTextLabel();
		} else if (actionCommand.equals("Send")) {
			ClientRun("");
		} else {
			ioField.setText("Unexpected error.");
		}
	}
    
    public void layout(Component obj, int x, int y,int width, int height)
    {
    		c.gridx=x; // 시작위치 x
    		c.gridy=y; // 시작위치 y
    		c.gridwidth=width; // 컨테이너 너비
    		c.gridheight=height;  // 컨테이너 높이
    		gridbag.setConstraints(obj, c);
    		add(obj);
    }
    
    public void setTextLabel()
    {
    		
    		int use =0;
    		int sum=0;
    		
    		for (int i=0;i<menuNum;i++) {
    			menuList[i].setText("");
    			numList[i].setText("");
    		}
    		
    		for (int i=0;i<menuNum;i++) {
    			if (menuItemNum[i] != 0 ) {
    				menuList[use].setText(menu[i].name);
    				numList[use].setText(menuItemNum[i]+"");
    				use++;
    				sum += menuItemNum[i]* menu[i].getPrice(); 
    			}
    		}
    		
    		sumLabel.setText(sum+"");
    		
    		
    }

   

    
}
