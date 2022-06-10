import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InputForm2 extends JFrame {
	 JLabel la_number,la_name,la_pay,la_pos,la_day,la_phone;
	 JTextField tf_number,tf_name, tf_pay, tf_pos,tf_day,tf_phone;
	 JButton bt_input, bt_cancel;


	 public InputForm2() {
		setTitle("입력폼");
		  la_number = new JLabel("번호 ");
		  la_name = new JLabel("이름  ");
		  la_pay = new JLabel("급여  ");
		  la_pos= new JLabel("직급 ");
		  la_day = new JLabel("입사일 ");
		  la_phone= new JLabel("연락 ");
		 
		  
		  
		  tf_number = new JTextField();
		  tf_name = new JTextField();
		  tf_pay = new JTextField();
		  tf_pos = new JTextField();
		  tf_day = new JTextField();
		  tf_phone= new JTextField();
		  
		  bt_input = new JButton("입력");
		  bt_cancel = new JButton("취소");
		 
		  
		  setLayout(null);
		  la_number.setBounds(30, 30, 30, 30);//location+size
		  la_name.setBounds(30, 80, 30, 30);//location+size
		  la_pay.setBounds(30, 130, 60, 30);//location+size
		  la_pos.setBounds(30, 180, 30, 30);
		  la_day.setBounds(30, 230, 30, 30);
		  la_phone.setBounds(30, 280, 30, 30);
		 
		  
		  tf_number.setBounds(80, 30, 80, 30);
		  tf_name.setBounds(80, 80, 80, 30);
		  tf_pay.setBounds(80, 130, 80, 30);
		  tf_pos.setBounds(80,  180, 80, 30);
		  tf_day.setBounds(80, 230, 80, 30);
		  tf_phone.setBounds(80, 280, 80, 30);
		  
		  bt_input.setBounds(30, 400, 60, 30);
		  bt_cancel.setBounds(100, 400, 60, 30);
		  
		  add(la_number);
		  add(la_name);
		  add(la_pay);
		  add(la_pos);
		  add(la_day);
		  add(la_phone);
		  
		  
		  add(tf_number);
		  add(tf_name);
		  add(tf_pay);
		  add(tf_pos);
		  add(tf_day);
		  add(tf_phone);
		  
		        add(bt_input);  
		        add(bt_cancel);
		        
		        setBounds(600, 600, 440, 600);
		 //       setVisible(true);
		        setResizable(false);  //프레임 사이즈 변경 불가
		        
		 }//생성자
		 public void initTF(){
		  //텍스트필드 초기화
		  setTitle("입력폼");
		  tf_number.setEditable(true);
		  tf_number.setText("");
		  tf_name.setText("");
		  tf_pay.setText("");
		  tf_pos.setText("");
		  tf_day.setText("");
		  tf_phone.setText("");
		 
		  tf_number.requestFocus();
		   
		  
		 }
		 
		 public void initUp(){//수정폼 초기화
		  setTitle("수정폼");
		  tf_number.setEditable(false);
		//  tf_name.setEnabled(boolean enabled);
		 
		 }
		 
		 
}

