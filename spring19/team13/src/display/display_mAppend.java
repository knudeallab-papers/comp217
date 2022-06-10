package display;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dataClass.member;
 
public class display_mAppend extends JFrame implements ActionListener {
   
	private JPanel p;
	private JTextField tfName, tfAddr, tfEmail, tfimg;// 이름 주소 이메일 이미지
	private JTextField tfTel1, tfTel2, tfTel3; //전화
	private JTextField tfYear, tfMonth, tfDay; //생년월일
	private JRadioButton rbMan, rbWoman; //남, 여
	private JTextArea taIntro;
	private JButton btnInsert, btnCancel; //가입, 취소 버튼
	private GridBagLayout gb;
	private GridBagConstraints gbc;
	private display_member back;
    
    ArrayList<member> memberList;    
    member NEWmember;
    JTable BackTable;
    DefaultTableModel BackModel;
    
   
    public display_mAppend(){
        super("등록");
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(350,400);
        
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
       

        //이름
        JLabel bName = new JLabel("이름 :");
        tfName = new JTextField(20);
        gbAdd(bName,0,0,1,1);
        gbAdd(tfName,1,0,3,1);
       
        //전화
        JLabel bTel = new JLabel("전화 :");
        JPanel pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfTel1 = new JTextField(6);    
        tfTel2 = new JTextField(6);    
        tfTel3 = new JTextField(6);
        pTel.add(tfTel1);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel2);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel3);
        gbAdd(bTel, 0, 1, 1,1);
        gbAdd(pTel, 1, 1, 3,1);
       
        //주소
        JLabel bAddr = new JLabel("주소: ");
        tfAddr = new JTextField(20);
        gbAdd(bAddr, 0,4,1,1);
        gbAdd(tfAddr, 1, 4, 3,1);
       
        //생일
        JLabel bBirth= new JLabel("생일: ");
        tfYear = new JTextField(6);
        tfMonth = new JTextField(6);
        tfDay = new JTextField(6);
        JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pBirth.add(tfYear);
        pBirth.add(new JLabel("/"));
        pBirth.add(tfMonth);
        pBirth.add(new JLabel("/"));
        pBirth.add(tfDay);
        gbAdd(bBirth, 0,5,1,1);
        gbAdd(pBirth, 1, 5, 3,1);
       
        
        //이메일
        JLabel bEmail = new JLabel("이메일 : ");
        tfEmail = new JTextField(20);
        gbAdd(bEmail, 0,6,1,1);//0611
        gbAdd(tfEmail,1,6,3,1);//1631
        

        //성별
        JLabel bGender = new JLabel("성별 : ");
        JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMan = new JRadioButton("남",true);
        rbWoman = new JRadioButton("여",true);
        ButtonGroup group = new ButtonGroup();
        group.add(rbMan);
        group.add(rbWoman);
        pGender.add(rbMan);
        pGender.add(rbWoman);      
        gbAdd(bGender, 0,7,1,1);
        gbAdd(pGender,1,7,3,1);
       
        //직업       
        JLabel bimg = new JLabel("*이미지: ");
        tfimg = new JTextField(20);
        gbAdd(bimg, 0,8,1,1);
        gbAdd(tfimg, 1, 8, 3,1);
       
        //버튼
        JPanel pButton = new JPanel();
        btnInsert = new JButton("가입");
        btnCancel = new JButton("취소");     
        pButton.add(btnInsert);
        pButton.add(btnCancel);    
        gbAdd(pButton, 0, 10, 4, 1);
        btnInsert.addActionListener(this);
        btnCancel.addActionListener(this);
       
        setVisible(true);

       
       
    }//생성자
    public display_mAppend(ArrayList<member> memberList, JTable BT, DefaultTableModel BM)
    {
    	this();
    	this.BackTable = BT;
    	this.BackModel = BM;
    	this.memberList = memberList;
    }

    public static void main(String[] args) {
        
        display_mAppend hi = new display_mAppend();
        hi.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == btnInsert)
       {
    	   if(memberCheck())
    	   {
    		   setMember();
    		   save();
    		   dispose();
    	   }
    	   
       }
       if(e.getSource() == btnCancel)
       {
    	   dispose();
       }   
    }//actionPerformed 
    public void save() {
    	try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./data/member.dat"));
			for(int i=0; i<memberList.size(); i++)
				outputStream.writeObject(memberList.get(i));
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
	//그리드백레이아웃에 붙이는 메소드
    private void gbAdd(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        //gb.setConstraints(c, gbc);
        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }//gbAdd
  
    public boolean memberCheck()
    {
    	String error_message = "";
    	if (tfName.getText().equals(""))
    	{
    		error_message = "이름을 입력하세요";
    	}
    	else if (tfTel1.getText().equals("")
    			|| tfTel2.getText().equals("")
    			|| tfTel3.getText().equals("")) {
    		error_message = "전화번호를 입력하세요";
    	}
    	if(error_message.equals(""))
    		return true;
    	else
    	{
    		display_error err = new display_error(error_message);
    		err.setVisible(true);
        	return false;
    	}
    }
    

    public boolean hasmember()
    {
    	if (NEWmember != null)
    		return true;
    	return false;
    }
    
    public void setMember()
    {
    	NEWmember = new member();
    	NEWmember.setName(tfName.getText());
    	NEWmember.setTel1(tfTel1.getText());
    	NEWmember.setTel2(tfTel2.getText());
    	NEWmember.setTel3(tfTel3.getText());
    	NEWmember.setAderess(tfAddr.getText());
    	NEWmember.setMail(tfEmail.getText());
    	NEWmember.setYear(tfYear.getText());
    	NEWmember.setMonth(tfMonth.getText());
    	NEWmember.setDay(tfDay.getText());
    	if(!tfimg.getText().equals(""))
    	{
    			NEWmember.setImgAddress(tfimg.getText());
    	}
    	
    	memberList.add(NEWmember);
    	refresh();
    }
	public void refresh()
	{
		BackModel.addRow(memberList.get(memberList.size()-1).getall());
	}
    public member getMember()
    {
    	return NEWmember;
    }
    
    
}//end