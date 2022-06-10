package TP;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Review extends JFrame {
	JTable table;
	JScrollPane jsp;
	Vector<String> columnrestaurants;
	Vector<Vector<String>> rowData, rowDatal;
 
	JTextField textfield_restaurant;
	JTextField textfield_menu;
	JTextField textfield_score;
	JTextField textfield_review;
 
	JButton button_add;
	JButton button_delete;
	JButton button_find;
	JButton button_erase;
 
	public Review(){
		setLayout(new BorderLayout());
		columnrestaurants=new Vector<String>();
		rowData=new Vector<Vector<String>>();
		rowDatal=new Vector<Vector<String>>();
		columnrestaurants.add("식당");
		columnrestaurants.add("메뉴");
		columnrestaurants.add("평점(1~5)");
		columnrestaurants.add("내용");
		table=new JTable(rowData, columnrestaurants);
		try{
	    	  Scanner keyboard = new Scanner(new FileInputStream("review.txt"));
	    	  while(keyboard.hasNext()) {
	    		  Vector<String> v =new Vector<String>();
	    		  v.add(keyboard.next());
	    		  v.add(keyboard.next());
	    		  v.add(keyboard.next());
	    		  v.add(keyboard.nextLine());
	    		  rowData.add(v);
	    		  rowDatal.add(v);
	    	  }
	    	  table.updateUI();
	    }catch(FileNotFoundException e) {
	    	  e.printStackTrace();
	    	  System.exit(0);
	    }
		jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(jsp, BorderLayout.NORTH);
  
		JPanel panel=new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JLabel label_restaurant=new JLabel("식당");
		JLabel label_menu=new JLabel("메뉴");
		JLabel label_score=new JLabel("평점(1~5)");
		JLabel label_review=new JLabel("내용");
		
		textfield_restaurant=new JTextField(10);
		textfield_menu=new JTextField(20);
		textfield_score=new JTextField(9);
		textfield_review=new JTextField(50);
  
		panel.add(label_restaurant);
		panel.add(textfield_restaurant);
		panel.add(label_menu);
		panel.add(textfield_menu);
		panel.add(label_score);
		panel.add(textfield_score);
		panel.add(label_review);
		panel.add(textfield_review);
  
 
		JPanel panel2=new JPanel();
		add(panel2, BorderLayout.SOUTH);
  
		button_add=new JButton("추가");
		button_delete=new JButton("삭제");
		button_find=new JButton("검색");
		button_erase=new JButton("지우기");
		panel2.add(button_add);
		panel2.add(button_delete);
		panel2.add(button_find);
		panel2.add(button_erase);
		setVisible(true);
		setSize(800,1000);
		
		button_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String restaurant=textfield_restaurant.getText();
				String menu=textfield_menu.getText();
				String score=textfield_score.getText();
				String review=textfield_review.getText();
    
				Vector<String> v =new Vector<String>();
				v.add(restaurant);
				v.add(menu);
				v.add(score);
				v.add(review);
    
				rowData.add(v);
				rowDatal.add(v);
				table.updateUI();
    
				textfield_restaurant.setText("");
				textfield_menu.setText("");
				textfield_score.setText("");
				textfield_review.setText("");
			}
		});
		button_delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int selection=table.getSelectedRow();
				rowData.remove(selection);
				rowDatal.remove(selection);
				table.updateUI();
			}
		});
		button_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String restaurant=textfield_restaurant.getText();
				String menu=textfield_menu.getText();
				if(restaurant.equals("") && menu.equals("")) {
					rowData.clear();
					for(int i=0; i<rowDatal.size(); i++) {
						rowData.add(rowDatal.get(i));
					}
					table.updateUI();
				}
				else if(restaurant.equals("")) {
					rowData.clear();
					for(int i=0; i<rowDatal.size(); i++) {
						if(rowDatal.get(i).get(1).equals(menu)) {
							Vector<String> v =new Vector<String>();
							v.add(rowDatal.get(i).get(0));
							v.add(menu);
							v.add(rowDatal.get(i).get(2));
							v.add(rowDatal.get(i).get(3));
							rowData.add(v);
						}
					}
					table.updateUI();
				}
				else if(menu.equals("")) {
					rowData.clear();
					for(int i=0; i<rowDatal.size(); i++) {
						if(rowDatal.get(i).get(0).equals(restaurant)) {
							Vector<String> v =new Vector<String>();
							v.add(restaurant);
							v.add(rowDatal.get(i).get(1));
							v.add(rowDatal.get(i).get(2));
							v.add(rowDatal.get(i).get(3));
							rowData.add(v);
						}
					}
					table.updateUI();
				}
				else {
					rowData.clear();
					for(int i=0; i<rowDatal.size(); i++) {
						if(rowDatal.get(i).get(1).equals(menu) && rowDatal.get(i).get(0).equals(restaurant)) {
							Vector<String> v =new Vector<String>();
							v.add(restaurant);
							v.add(menu);
							v.add(rowDatal.get(i).get(2));
							v.add(rowDatal.get(i).get(3));
							rowData.add(v);
						}
					}
					table.updateUI();
				}
				textfield_restaurant.setText("");
				textfield_menu.setText("");
				textfield_score.setText("");
				textfield_review.setText("");
			}
		});
		button_erase.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				textfield_restaurant.setText("");
				textfield_menu.setText("");
				textfield_score.setText("");
				textfield_review.setText("");
			}
		});
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	try {
            	      FileWriter fw = new FileWriter("review.txt");
            	      for(int i=0;i<rowDatal.size();i++) {
            	    	  fw.write(rowDatal.get(i).get(0)+" "+rowDatal.get(i).get(1)+" "+rowDatal.get(i).get(2)+" "+rowDatal.get(i).get(3)+"\n");
            	      }
            	      fw.close();
            	}catch(IOException e2) {
            		e2.printStackTrace();
            	}
            }
        });
		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				int selection=table.getSelectedRow();
				Vector<String> vc=rowData.get(selection);
    
				textfield_restaurant.setText(vc.get(0));
				textfield_menu.setText(vc.get(1));
				textfield_score.setText(vc.get(2));
				textfield_review.setText(vc.get(3));
			}
		});
	}
} 