import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class member extends JFrame implements MouseListener,ActionListener{
	JTable table;
	JScrollPane scroll;
	JPanel northp,southp;
	JLabel label;
	JButton bt_add, bt_del, bt_up, bt_search;

	InputForm form;
	DefaultTableModel dtm;
	int srow;
	public member() {
		setTitle("JTable테스트");
		form = new InputForm();
   
    
    //JTable(Object[][] rowData, Object[] columnNames)
    Object[][] rowData={
                               {1,"a", "sam", 15000,"010-2354-3567"}, //0행
                               {2,"b", "tom", 5000,"010-2124-2938"},//1행
                               {3,"a", "yam", 10000,"010-5938-2085"},  //2행
                               
              
                                };
    
    Object[] columnNames={"번호","등급 ","이름 ","마일리지","연락처 "};
    
    //JTable(TableModel dm)
    dtm = new DefaultTableModel(rowData, columnNames);
    
    table = new JTable(dtm);//(rowData,columnNames);
    scroll = new JScrollPane(table);
    label = new JLabel("테이블");
    northp = new JPanel();
    northp.add(label);
      
    bt_add = new JButton("추가");  
    bt_del = new JButton("삭제");  
    bt_up = new JButton("편집 ");  
    //bt_search = new JButton("검색");  
    
    southp = new JPanel();
    southp.add(bt_add);
    southp.add(bt_del);
    southp.add(bt_up);
    //southp.add(bt_search);
    
    
    //add("Center",table);
    add("Center",scroll);
    add("North",northp);
    add("South",southp);
    
    setBounds(400,300,300,300);
    this.setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    eventUp();
	}//생성자
 
	 private void eventUp(){
	    table.addMouseListener(this);
	    
	    bt_add.addActionListener(this);
	    bt_del.addActionListener(this);
	    bt_up.addActionListener(this);
	    
	    form.bt_input.addActionListener(this);
	    form.bt_cancel.addActionListener(this);
	    
	 }
 
 @Override
	 public void actionPerformed(ActionEvent e) {
	 
	 
	  //이벤트 소스에 대한 구분 : 
	  //object e.getSource(), String e.getActionCommand()
	  
	//  System.out.println(e.getActionCommand());
	  Object ob=e.getSource();
	  if(ob==bt_add){//추가버튼 클릭시
	   form.initTF();
	   setVisible(false);
	   form.setVisible(true);
	  }else if(ob==form.bt_input){//입력버튼 클릭시(입력폼, 수정폼)
		  String number = form.tf_number.getText();
	      String name= form.tf_name.getText();
	      String pay= form.tf_pay.getText();//급여 
	      String pos= form.tf_pos.getText();//직급 
	      String phone= form.tf_phone.getText();
	     
//	 	 man newMan = new man(number, name, pay, pos, phone);
	      
	      if(number == null  || number.length() == 0){
	      //name변수에 null값이 들어올 가능성이 단 1%라도 있다면
	      //null에 대한 체크를 하는 데 반드시 앞에서 체크해야 함.
	      JOptionPane.showMessageDialog(form, "번호 입력!!"); 
	         form.tf_number.requestFocus();
	       return;
	      }
	      
	      if(name.length()==0){
	       //TextField에는 null값이 들어오지 않으므로 null체크 뺌.
	      JOptionPane.showMessageDialog(form, "이름 입력!!"); 
	          form.tf_name.requestFocus();
	       return;
	      }
	      
	      if(pos.length()==0){//입력된 문자가 없다면
	      JOptionPane.showMessageDialog(form, "직급 입력!!");
	          form.tf_pos.requestFocus();
	       return;
	      }
	      if(phone.length()==0){
		       //TextField에는 null값이 들어오지 않으므로 null체크 뺌.
		      JOptionPane.showMessageDialog(form, "입사일 입력!!"); 
		          form.tf_phone.requestFocus();
		       return;
		      }
	     
	      
	      if(form.getTitle().equals("입력폼")){//입력폼이었을때
	      Object rowData[] = {number,name, pay,pos, phone};
	      dtm.addRow(rowData);
	      }else{//수정폼이였을때
	//       table.setValueAt(Object aValue, int row, int column)
	       table.setValueAt(name, srow, 1);
	       //나이수정
	       table.setValueAt(pay, srow, 2);
	       //직업수정
	       table.setValueAt(pos, srow, 3);
	       table.setValueAt(phone, srow, 4);
	       
	      }
	      form.setVisible(false);
	      setVisible(true);
	      
	  }else if(ob==form.bt_cancel){
	   form.setVisible(false);
	   
	   setVisible(true);
	  }else if(ob==bt_del){
	    //삭제할 이름  입력
	//   JOptionPane.showInputDialog(parentComponent, object message)
	   String delName = JOptionPane.showInputDialog(this, "삭제할 번호 :");
	   //System.out.println("delName="+delName);
	   //확인, 취소, X : 입력된 이름 또는 빈문자열, null,null
	   
	//   delName.equals(table.getValueAt(0,0));
	//   delName.equals(table.getValueAt(1,0));
	//   delName.equals(table.getValueAt(2,0));
	   //table.getRowCount();//전체 행갯수
	   if(delName == null) return ; //취소, X
	    
	   if(delName.length()==0){
	    JOptionPane.showMessageDialog(this, "삭제할이름입력!!");
	    
	    return; //빈문자열 체크
	  }
	    
	//    table.getRowCount();
	   for(int i=0; i<table.getRowCount(); i++){
	    if(delName.equals(table.getValueAt(i,0))){
	//     dtm.removeRow(int 행인덱스)
	     dtm.removeRow(i);
	     
	    }
	    
	    
	   
	  }//for
	  
	 
	     }else if(ob==bt_up){//수정버튼을 클릭했다면
	//     table.getValueAt(row index, column index);
	     srow = table.getSelectedRow(); 
	     //JTable에서 선택된 행이 없다면 -1값을 리턴.
	     
	     if(srow==-1){
	      JOptionPane.showMessageDialog(this, "수정할 행선택!!");
	      return;
	     }
	     
	  String number = table.getValueAt(srow, 0).toString();
	  String name = (String) table.getValueAt(srow,1);
	  String pay  = (String)table.getValueAt(srow,2);
	                    //nw Integer(13);
	  String pos  = table.getValueAt(srow,3).toString();
	  String phone  = (String) table.getValueAt(srow,4);
	 
	      
	  form.tf_number.setText(number);
	  form.tf_name.setText(name);
	  form.tf_pay.setText(pay);
	  form.tf_pos.setText(pos);
	  form.tf_phone.setText(phone);
	 
	       
	  form.initUp();
	     
	  setVisible(false);
	  form.setVisible(true);
	  }
	  }
 @Override
 public void mouseClicked(MouseEvent e) {//이벤트핸들러
     //System.out.println("mouseClicked");
     //JTable을 클릭했을때 선택되어진 행의 데이터정보를
     //JLabel에 출력.
     //예)   이름:홍길동, 나이:13, 직업:학생
     
     int srow= table.getSelectedRow();//선택된 행 인덱스
     //System.out.println("srow="+srow);
  //table.getValueAt(int row, int column)
     //column 0:이름, 1:나이, 2:직업
//     System.out.println(table.getValueAt(srow, 0));
//     System.out.println(table.getValueAt(srow, 1));
//     System.out.println(table.getValueAt(srow, 2));
     Object name=table.getValueAt(srow, 0);
     Object age=table.getValueAt(srow, 1);
     Object job=table.getValueAt(srow, 2);
     
     label.setText("이름:"+name+", 나이:"+age+", 직업:"+job);
     
 }
 

 @Override
 public void mousePressed(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }
 @Override
 public void mouseReleased(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }
 public void mouseEntered(MouseEvent e) {
	  // TODO Auto-generated method stub
	  
	 }
	 @Override
	 public void mouseExited(MouseEvent e) {
	  // TODO Auto-generated method stub
	  
	 }
}