/**
 * JAVA Term Project : Pos Program
 * Student : Yeji Ahn, Seunghye Jung
 * Prof. : Young-gyoon, Suh
 * Pos GUI Class
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Choice;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLayeredPane;

public class Pos extends JFrame {

	// variables
	DBmanager dm;
	private int OrderCost = 0;
	private int DayProfit = 0;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextArea textArea;
	//테이블
	private Table table1;
	private Table table2;
	private Table table3;
	private Table table4;
	private Table table5;
	private Table table6;
	private Table table7;
	private Table table8;
	//테이블버튼
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JButton btnNewButton_8;
	private JButton btnNewButton_9;
	private JButton btnNewButton_10;
	//날짜
	private static String today = null;
	private String day;
	//메뉴 리스트
	private java.awt.List list_3;
	//테이블 초이스
	private Choice choice;
	//테이블 스피너
	private JSpinner spinner;
	//누른 테이블 name
	private String tempTableName = "";
	// 당일매출 숫자
	private JLabel lblNewLabel_26;
	private JLabel lblNewLabel_27;
	// 주문 누적액
	private JLabel lblNewLabel_36;
	//당일 날짜
	private JLabel lblNewLabel_23;
	// 메소드 날짜
    private String date;
    //직원테이블
    private JTable table_1;
    //회원테이블
    private JTable table;
    //창고테이블
    private JTable table_2;
    //테이블메뉴 테이블
    private JTable table_3;
    //프레임 배경
    ImageIcon icon;
	/**
	 * Create the frame.
	 */
	public Pos() {
		super("YS POS");
		//DB 생성
		dm = new DBmanager();
		dm.modifyEmployeePay();
		//table 생성
		table1 = new Table();
		table2 = new Table();
		table3 = new Table();
		table4 = new Table();
		table5 = new Table();
		table6 = new Table();
		table7 = new Table();
		table8 = new Table();
		
		// 날짜 설정 -> 정보가 없다면
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat d_format = new SimpleDateFormat("YYYY MM dd");
        today = d_format.format(cal.getTime());
       
        date = dm.returnToday();
        
		// 날짜 설정
		lblNewLabel_23 = new JLabel("");
		lblNewLabel_23.setForeground(Color.WHITE);
		lblNewLabel_23.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		lblNewLabel_23.setBounds(10, 79, 113, 29);
        
        if ( date == null ) {
        	lblNewLabel_23.setText(today);
        	dm.saveDay(today);
        	date = dm.returnToday();
        }
        else {
        	lblNewLabel_23.setText(date);
        }
       
         // 당일 매출
         DayProfit = 0;
         // JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 543);
		// tab 메뉴
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(10, 117, 624, 380);
		
		//-----------------------------------------------------------------
		// 테이블 탭
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("테이블", null, panel, null);
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		tabbedPane.setForegroundAt(0, new Color(0, 0, 0));
		panel.setLayout(null);
		
		// 테이블 메뉴 왼쪽
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 10, 427, 331);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("B A R");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Impact", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(158, 273, 111, 33);
		panel_5.add(lblNewLabel_1);
		
		JLabel lblNewLabel_28 = new JLabel("");
		lblNewLabel_28.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_28.setBounds(29, 113, 66, 15);
		panel_5.add(lblNewLabel_28);
		
		JLabel lblNewLabel_29 = new JLabel("");
		lblNewLabel_29.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_29.setBounds(80, 185, 95, 15);
		panel_5.add(lblNewLabel_29);
		
		JLabel lblNewLabel_30 = new JLabel("");
		lblNewLabel_30.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_30.setBounds(168, 113, 87, 15);
		panel_5.add(lblNewLabel_30);
		
		JLabel lblNewLabel_31 = new JLabel("");
		lblNewLabel_31.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_31.setBounds(170, 185, 87, 15);
		panel_5.add(lblNewLabel_31);
		
		JLabel lblNewLabel_32 = new JLabel("");
		lblNewLabel_32.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_32.setBounds(346, 185, 87, 15);
		panel_5.add(lblNewLabel_32);
		
		JLabel lblNewLabel_33 = new JLabel("");
		lblNewLabel_33.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_33.setBounds(312, 113, 103, 15);
		panel_5.add(lblNewLabel_33);
		
		JLabel lblNewLabel_34 = new JLabel("");
		lblNewLabel_34.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_34.setBounds(0, 185, 87, 15);
		panel_5.add(lblNewLabel_34);
		
		JLabel lblNewLabel_35 = new JLabel("");
		lblNewLabel_35.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_35.setBounds(255, 185, 87, 15);
		panel_5.add(lblNewLabel_35);
		
		btnNewButton_4 = new JButton("Bar-2");
		btnNewButton_4.setFont(new Font("굴림", Font.BOLD, 11));
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton_4.setBackground(new Color(176,196,221));
				table_3 = table2.getTable(table_3);
				tempTableName = arg0.getActionCommand();
				btnNewButton_3.setBackground(table1.updateColor());
				btnNewButton_5.setBackground(table3.updateColor());
				btnNewButton_6.setBackground(table4.updateColor());
				btnNewButton_7.setBackground(table5.updateColor());
				btnNewButton_8.setBackground(table6.updateColor());
				btnNewButton_9.setBackground(table7.updateColor());
				btnNewButton_10.setBackground(table8.updateColor());
			}
		});
		btnNewButton_4.setBounds(94, 203, 66, 60);
		panel_5.add(btnNewButton_4);
		
		btnNewButton_3 = new JButton("W-1");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton_3.setBackground(new Color(176,196,221));
				table_3 = table1.getTable(table_3);
				tempTableName = arg0.getActionCommand();
				btnNewButton_4.setBackground(table2.updateColor());
				btnNewButton_5.setBackground(table3.updateColor());
				btnNewButton_6.setBackground(table4.updateColor());
				btnNewButton_7.setBackground(table5.updateColor());
				btnNewButton_8.setBackground(table6.updateColor());
				btnNewButton_9.setBackground(table7.updateColor());
				btnNewButton_10.setBackground(table8.updateColor());
			}
		});
		
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_3.setBounds(10, 10, 103, 101);
		panel_5.add(btnNewButton_3);
		
		btnNewButton_5 = new JButton("W-2");
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_5.setBounds(158, 10, 103, 101);
		panel_5.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_5.setBackground(new Color(176,196,221));
				table_3 = table3.getTable(table_3);
				tempTableName = e.getActionCommand();
				btnNewButton_3.setBackground(table1.updateColor());
				btnNewButton_4.setBackground(table2.updateColor());
				btnNewButton_6.setBackground(table4.updateColor());
				btnNewButton_7.setBackground(table5.updateColor());
				btnNewButton_8.setBackground(table6.updateColor());
				btnNewButton_9.setBackground(table7.updateColor());
				btnNewButton_10.setBackground(table8.updateColor());
			}
		});
		
		btnNewButton_6 = new JButton("Bar-3");
		btnNewButton_6.setFont(new Font("굴림", Font.BOLD, 11));
		btnNewButton_6.setBackground(Color.WHITE);
		btnNewButton_6.setBounds(180, 203, 66, 60);
		panel_5.add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_6.setBackground(new Color(176,196,221));
				table_3 = table4.getTable(table_3);
				tempTableName = e.getActionCommand();				
				btnNewButton_3.setBackground(table1.updateColor());
				btnNewButton_4.setBackground(table2.updateColor());
				btnNewButton_5.setBackground(table3.updateColor());
				btnNewButton_7.setBackground(table5.updateColor());
				btnNewButton_8.setBackground(table6.updateColor());
				btnNewButton_9.setBackground(table7.updateColor());
				btnNewButton_10.setBackground(table8.updateColor());
			}
		});
		
		btnNewButton_7 = new JButton("Bar-5");
		btnNewButton_7.setFont(new Font("굴림", Font.BOLD, 11));
		btnNewButton_7.setBackground(Color.WHITE);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton_7.setBackground(new Color(176,196,221));
				table_3 = table5.getTable(table_3);
				tempTableName = arg0.getActionCommand();
				btnNewButton_3.setBackground(table1.updateColor());
				btnNewButton_4.setBackground(table2.updateColor());
				btnNewButton_5.setBackground(table3.updateColor());
				btnNewButton_6.setBackground(table4.updateColor());
				btnNewButton_8.setBackground(table6.updateColor());
				btnNewButton_9.setBackground(table7.updateColor());
				btnNewButton_10.setBackground(table8.updateColor());
			}
		});
		btnNewButton_7.setBounds(348, 203, 67, 60);
		panel_5.add(btnNewButton_7);
		
		btnNewButton_8 = new JButton("W-3");
		btnNewButton_8.setBackground(Color.WHITE);
		btnNewButton_8.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton_8.setBackground(new Color(176,196,221));
				table_3 = table6.getTable(table_3);
				tempTableName = arg0.getActionCommand();				
				btnNewButton_3.setBackground(table1.updateColor());
				btnNewButton_4.setBackground(table2.updateColor());
				btnNewButton_5.setBackground(table3.updateColor());
				btnNewButton_6.setBackground(table4.updateColor());
				btnNewButton_7.setBackground(table5.updateColor());
				btnNewButton_9.setBackground(table7.updateColor());
				btnNewButton_10.setBackground(table8.updateColor());
			}
		});
		btnNewButton_8.setBounds(312, 10, 103, 101);
		panel_5.add(btnNewButton_8);
		
		btnNewButton_9 = new JButton("Bar-1");
		btnNewButton_9.setFont(new Font("굴림", Font.BOLD, 11));
		btnNewButton_9.setBackground(Color.WHITE);
		btnNewButton_9.setBounds(10, 203, 66, 60);
		panel_5.add(btnNewButton_9);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton_9.setBackground(new Color(176,196,221));
				table_3 = table7.getTable(table_3);
				tempTableName = arg0.getActionCommand();
				btnNewButton_3.setBackground(table1.updateColor());
				btnNewButton_4.setBackground(table2.updateColor());
				btnNewButton_5.setBackground(table3.updateColor());
				btnNewButton_6.setBackground(table4.updateColor());
				btnNewButton_7.setBackground(table5.updateColor());
				btnNewButton_8.setBackground(table6.updateColor());
				btnNewButton_10.setBackground(table8.updateColor());
			}
		});
		
		btnNewButton_10 = new JButton("Bar-4");
		btnNewButton_10.setFont(new Font("굴림", Font.BOLD, 11));
		btnNewButton_10.setBackground(Color.WHITE);
		btnNewButton_10.setBounds(265, 203, 66, 60);
		panel_5.add(btnNewButton_10);
		
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton_10.setBackground(new Color(176,196,221));
				table_3 = table8.getTable(table_3);
				tempTableName = arg0.getActionCommand();				
				btnNewButton_3.setBackground(table1.updateColor());
				btnNewButton_4.setBackground(table2.updateColor());
				btnNewButton_5.setBackground(table3.updateColor());
				btnNewButton_6.setBackground(table4.updateColor());
				btnNewButton_7.setBackground(table5.updateColor());
				btnNewButton_8.setBackground(table6.updateColor());
				btnNewButton_9.setBackground(table7.updateColor());
			}
		});
		
		JButton btnNewButton_25 = new JButton("");
		btnNewButton_25.setEnabled(false);
		btnNewButton_25.setBackground(new Color(204, 204, 255));
		btnNewButton_25.setBounds(10, 273, 405, 33);
		panel_5.add(btnNewButton_25);
		
		JButton btnNewButton_26 = new JButton("");
		btnNewButton_26.setEnabled(false);
		btnNewButton_26.setBackground(new Color(204, 204, 255));
		btnNewButton_26.setBounds(10, 273, 33, 58);
		panel_5.add(btnNewButton_26);
		
		JButton button_2 = new JButton("");
		button_2.setEnabled(false);
		button_2.setBackground(new Color(204, 204, 255));
		button_2.setBounds(382, 273, 33, 58);
		panel_5.add(button_2);
		
		// 테이블 메뉴 오른쪽
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(230, 230, 250));
		panel_6.setBounds(441, 0, 178, 351);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("추가");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = "";
				String name = choice.getSelectedItem();
				Object count =  spinner.getValue();
				String price = dm.getCostOfMenu(choice.getSelectedItem());
				str = name + "\t" + count + "\t"+ price;

				int i = JOptionPane.showConfirmDialog(null,tempTableName+"에  "+ name+" "+ count +"개를 추가하시겠습니까? 개당 " + price+"원","추가",JOptionPane.OK_CANCEL_OPTION);
				if ( i == 0 )
				{
					String exist = dm.ExistInventory(name, count);
					if ( !exist.equals("")) {
						dm.modifyIngWhenOrder(exist);
						table_2 = dm.UpdateIngList(table_2);
						
					if ( tempTableName.equals("W-1")) {
						table1.setList(str);
						table_3 = table1.getTable(table_3);
						table1.setCost(dm.returnTableCost(table1));
						lblNewLabel_28.setText(Integer.toString(table1.returnCost()));
					}
					else if ( tempTableName.equals("Bar-2")) {
						table2.setList(str);
						table_3 = table2.getTable(table_3);
						table2.setCost(dm.returnTableCost(table2));
						lblNewLabel_29.setText(Integer.toString(table2.returnCost()));
					}
					else if ( tempTableName.equals("W-2")) {
						table3.setList(str);
						table_3 = table3.getTable(table_3);
						table3.setCost(dm.returnTableCost(table3));
						lblNewLabel_30.setText(Integer.toString(table3.returnCost()));
					}
					else if ( tempTableName.equals("Bar-3")) {
						table4.setList(str);
						table_3 = table4.getTable(table_3);
						table4.setCost(dm.returnTableCost(table4));
						lblNewLabel_31.setText(Integer.toString(table4.returnCost()));
					}
					else if ( tempTableName.equals("Bar-5")) {
						table5.setList(str);
						table_3 = table5.getTable(table_3);
						table5.setCost(dm.returnTableCost(table5));
						lblNewLabel_32.setText(Integer.toString(table5.returnCost()));
					}					
					else if ( tempTableName.equals("W-3")) {
						table6.setList(str);
						table_3 = table6.getTable(table_3);
						table6.setCost(dm.returnTableCost(table6));
						lblNewLabel_33.setText(Integer.toString(table6.returnCost()));
					}					
					else if ( tempTableName.equals("Bar-1")) {
						table7.setList(str);
						table_3 = table7.getTable(table_3);
						table7.setCost(dm.returnTableCost(table7));
						lblNewLabel_34.setText(Integer.toString(table7.returnCost()));
					}					
					else if ( tempTableName.equals("Bar-4")) {
						table8.setList(str);
						table_3 = table8.getTable(table_3);
						table8.setCost(dm.returnTableCost(table8));
						lblNewLabel_35.setText(Integer.toString(table8.returnCost()));
					}
					}
					else
						JOptionPane.showMessageDialog(null, "재고문제로 추가할 수 없습니다.");
				}
			}
		});
		btnNewButton_1.setBounds(20, 289, 67, 30);
		panel_6.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("결제");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i = JOptionPane.showConfirmDialog(null,tempTableName+"의 결제를 진행하시겠습니까?","결제",JOptionPane.OK_CANCEL_OPTION);
				if ( i == 0 ) {
				String str = JOptionPane.showInputDialog(null, "회원이시면 등록번호를 입력해주세요.\n없다면 X를 입력하세요.","",JOptionPane.OK_CANCEL_OPTION);
				if (str != null) {
				int tableCost = 0;
				int SaleCost = 0;
				int mileage = 0;
				
				if ( tempTableName.equals("W-1")) {
					tableCost = dm.returnTableCost(table1);
					SaleCost = dm.returnSaleTableCost(tableCost, str);
					btnNewButton_3.setBackground(table1.TableReset());
					lblNewLabel_28.setText("");
				}
				else if ( tempTableName.equals("Bar-2")) {
					tableCost = dm.returnTableCost(table2);
					SaleCost = dm.returnSaleTableCost(tableCost, str);
					btnNewButton_4.setBackground(table2.TableReset());
					lblNewLabel_29.setText("");
				}
				else if ( tempTableName.equals("W-2")) {
					tableCost = dm.returnTableCost(table3);
					SaleCost = dm.returnSaleTableCost(tableCost, str);
					btnNewButton_5.setBackground(table3.TableReset());
					lblNewLabel_30.setText("");
				}
				else if ( tempTableName.equals("Bar-3")) {
					tableCost = dm.returnTableCost(table4);
					SaleCost = dm.returnSaleTableCost(tableCost, str);
					btnNewButton_6.setBackground(table4.TableReset());
					lblNewLabel_31.setText("");
				}
				else if ( tempTableName.equals("Bar-5")) {
					tableCost = dm.returnTableCost(table5);
					SaleCost = dm.returnSaleTableCost(tableCost, str);
					btnNewButton_7.setBackground(table5.TableReset());
					lblNewLabel_32.setText("");
				}					
				else if ( tempTableName.equals("W-3")) {
					tableCost = dm.returnTableCost(table6);
					SaleCost = dm.returnSaleTableCost(tableCost, str);
					btnNewButton_8.setBackground(table6.TableReset());
					lblNewLabel_33.setText("");
				}					
				else if ( tempTableName.equals("Bar-1")) {
					tableCost = dm.returnTableCost(table7);
					SaleCost = dm.returnSaleTableCost(tableCost, str);
					btnNewButton_9.setBackground(table7.TableReset());
					lblNewLabel_34.setText("");
				}					
				else if ( tempTableName.equals("Bar-4")) {
					tableCost = dm.returnTableCost(table8);
					SaleCost = dm.returnSaleTableCost(tableCost, str);
					btnNewButton_10.setBackground(table8.TableReset());
					lblNewLabel_35.setText("");
				}
				
				if ( tableCost == SaleCost)
					JOptionPane.showMessageDialog(null,"등록된 회원이 아닙니다.\n결제를 진행합니다.");
				else {
					JOptionPane.showMessageDialog(null, "결제 가격 : " + SaleCost + " 입니다.");
					mileage = dm.setMileageNChangeLevel(tableCost, str);
					JOptionPane.showMessageDialog(null, mileage + "마일리지를 쌓았습니다.");
				}
				DayProfit += SaleCost;
				lblNewLabel_26.setText(Integer.toString(DayProfit)+"원");
				table = dm.UpdateCustomerList(table);
				JOptionPane.showMessageDialog(null, "결제완료");
				}
				}
			}
		});
		btnNewButton_2.setBounds(100, 289, 68, 30);
		panel_6.add(btnNewButton_2);
		
		JLabel label = new JLabel("메뉴");
		label.setFont(new Font("굴림", Font.PLAIN, 13));
		label.setBounds(40, 221, 37, 22);
		panel_6.add(label);
		
		JLabel lblNewLabel = new JLabel("수량");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 13));
		lblNewLabel.setBounds(128, 225, 29, 15);
		panel_6.add(lblNewLabel);
		
		
		// 테이블 초이스 메뉴
		choice = new Choice();
		dm.ListToChoice(choice);
		choice.setBounds(10, 246, 89, 30);
		panel_6.add(choice);
		
		//테이블 주문 스피너 (수량)
		spinner = new JSpinner();
		spinner.setBounds(126, 246, 31, 22);
		panel_6.add(spinner);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 10, 158, 206);
		panel_6.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uBA54\uB274", "\uC218\uB7C9", "\uAC00\uACA9"
			}
		));
		scrollPane_3.setViewportView(table_3);
		
		//--------------------------------------------------------------
		// 창고
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("창고", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setBounds(0, 0, 397, 351);
		panel_1.add(panel_7);
		panel_7.setLayout(null);
		
		JButton button_1 = new JButton("추가");
		button_1.setBackground(new Color(255, 255, 255));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dm.addIng(textField.getText(), textField_1.getText(), textField_4.getText(), textField_5.getText(), textField_2.getText(), textField_3.getText());
				table_2 = dm.UpdateIngList(table_2);
			}
		});
		button_1.setBounds(198, 307, 82, 34);
		panel_7.add(button_1);
		
		JButton btnNewButton_11 = new JButton("삭제");
		btnNewButton_11.setBackground(new Color(255, 255, 255));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dm.delIng((table_2.getValueAt(table_2.getSelectedRow(),0)));
				table_2 = dm.UpdateIngList(table_2);
			}
		});
		btnNewButton_11.setBounds(290, 307, 82, 34);
		panel_7.add(btnNewButton_11);
		
		//초기화
		JButton btnNewButton_23 = new JButton("초기화");
		btnNewButton_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("0");
				textField_5.setText("0");
			}
		});
		btnNewButton_23.setBounds(106, 307, 82, 34);
		panel_7.add(btnNewButton_23);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(230, 230, 250));
		panel_8.setBounds(399, 0, 220, 351);
		panel_1.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("이름");
		lblNewLabel_5.setBounds(46, 34, 50, 15);
		panel_8.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("가격");
		lblNewLabel_6.setBounds(46, 71, 50, 15);
		panel_8.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("판매처");
		lblNewLabel_7.setBounds(34, 110, 50, 15);
		panel_8.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("연락처");
		lblNewLabel_8.setBounds(34, 147, 50, 15);
		panel_8.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("현 수량");
		lblNewLabel_9.setBounds(46, 187, 50, 15);
		panel_8.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("주문량");
		lblNewLabel_10.setBounds(134, 187, 50, 15);
		panel_8.add(lblNewLabel_10);
		
		textField = new JTextField();
		textField.setBounds(88, 31, 96, 21);
		panel_8.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(88, 68, 96, 21);
		panel_8.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(76, 107, 108, 21);
		panel_8.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(76, 144, 108, 21);
		panel_8.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(36, 210, 60, 35);
		panel_8.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(124, 210, 60, 35);
		panel_8.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton_12 = new JButton("주문");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dm.modifyIng(textField.getText(), textField_1.getText(), textField_4.getText(), textField_5.getText(), textField_2.getText(), textField_3.getText());
				table_2 = dm.UpdateIngList(table_2);
				OrderCost += Integer.parseInt(textField_1.getText()) * Integer.parseInt(textField_5.getText());
				lblNewLabel_36.setText(Integer.toString(OrderCost));
			}
		});
		btnNewButton_12.setBackground(new Color(255, 255, 255));
		btnNewButton_12.setBounds(30, 306, 78, 35);
		panel_8.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("주문 취소");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cancel = "-"+textField_5.getText();
				dm.modifyIng(textField.getText(), textField_1.getText(), textField_4.getText(), cancel, textField_2.getText(), textField_3.getText());
				table_2 = dm.UpdateIngList(table_2);
				OrderCost -= Integer.parseInt(textField_1.getText()) * Integer.parseInt(textField_5.getText());
				lblNewLabel_36.setText(Integer.toString(OrderCost));
			}
		});
		btnNewButton_13.setBackground(new Color(255, 255, 255));
		btnNewButton_13.setBounds(118, 306, 92, 35);
		panel_8.add(btnNewButton_13);
		
		JLabel label_5 = new JLabel("\uB2F9\uC77C \uC8FC\uBB38 \uB204\uC801\uC561");
		label_5.setBounds(10, 269, 137, 15);
		panel_8.add(label_5);
		
		lblNewLabel_36 = new JLabel("");
		lblNewLabel_36.setBounds(115, 269, 84, 15);
		panel_8.add(lblNewLabel_36);
		tabbedPane.setBackgroundAt(1, Color.WHITE);
		
		// JTable
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 10, 377, 289);
		panel_7.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"이름", "가격", "재고", "주문"
			}
		));
		scrollPane_2.setViewportView(table_2);
		
		table_2.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				JTable str1 = (JTable) e.getComponent();
				
				String str = (String)str1.getValueAt(str1.getSelectedRow(), 0);
				if ( str != null) {
				StringTokenizer st = new StringTokenizer(str,"\t");
				str = st.nextToken();
				String newstr = dm.returnIngLine(str);
				
				st = new StringTokenizer(newstr,"\t");
				textField.setText(st.nextToken());
				textField_1.setText(st.nextToken());
				textField_4.setText(st.nextToken());
				textField_5.setText(st.nextToken());
				textField_2.setText(st.nextToken());
				textField_3.setText(st.nextToken());
				}
			}
			 public void mousePressed(MouseEvent arg0) {};
			 public void mouseReleased(MouseEvent arg0) {};
			 public void mouseEntered(MouseEvent arg0) {};
			 public void mouseExited(MouseEvent arg0) {};
		});
		table_2 = dm.UpdateIngList(table_2);
		
		// 회원---------------------------------------------------------------
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("회원", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		panel_9.setBounds(0, 0, 619, 351);
		panel_2.add(panel_9);
		panel_9.setLayout(null);
		
		// 회원 편집 기능
		JButton btnNewButton_14 = new JButton("편집");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dm.modifyCustomer(table.getValueAt(table.getSelectedRow(), 0),
						table.getValueAt(table.getSelectedRow(), 1),table.getValueAt(table.getSelectedRow(), 2),
						table.getValueAt(table.getSelectedRow(), 3),table.getValueAt(table.getSelectedRow(), 4));
				table = dm.UpdateCustomerList(table);
			}
		});
		btnNewButton_14.setBackground(new Color(255, 255, 255));
		btnNewButton_14.setBounds(329, 303, 81, 38);
		panel_9.add(btnNewButton_14);
		
		// 회원 추가 기능
		JButton btnNewButton_15 = new JButton("추가");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dm.addCustomer(table.getValueAt(table.getSelectedRow(), 0),
						table.getValueAt(table.getSelectedRow(), 1),table.getValueAt(table.getSelectedRow(), 2),
						table.getValueAt(table.getSelectedRow(), 3),table.getValueAt(table.getSelectedRow(), 4));
				table = dm.UpdateCustomerList(table);
			}
		});
		btnNewButton_15.setBackground(new Color(255, 255, 255));
		btnNewButton_15.setBounds(421, 303, 81, 38);
		panel_9.add(btnNewButton_15);
		
		// 회원 삭제
		JButton btnNewButton_16 = new JButton("삭제");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.delCustomer(table.getValueAt(table.getSelectedRow(),0));
				table = dm.UpdateCustomerList(table);
			}
		});
		btnNewButton_16.setBackground(new Color(255, 255, 255));
		btnNewButton_16.setBounds(512, 303, 81, 38);
		panel_9.add(btnNewButton_16);
		
		//JTable
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 599, 283);
		panel_9.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"번호", "등급", "이름", "마일리지", "연락처"
			}
		));
		table = dm.UpdateCustomerList(table);
		scrollPane_1.setViewportView(table);
		tabbedPane.setBackgroundAt(2, Color.WHITE);
		//-----------------------------------------------------------------
		
		// 메뉴
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("메뉴", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(230, 230, 250));
		panel_10.setBounds(0, 0, 187, 351);
		panel_3.add(panel_10);
		panel_10.setLayout(null);
		
		list_3 = new java.awt.List();
		list_3 = dm.UpdateMenuList(list_3);
		list_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = e.getActionCommand();
				String info = dm.getInfoOfMenu(str);
				if ( info != null) {
					textField_6.setText(info.split("\t")[0]);
					textField_7.setText(info.split("\t")[1]);
					textField_8.setText(info.split("\t")[2]);
					textArea.setText(info.split("\t")[3]);
				}
			}
		});
		list_3.setBounds(10, 10, 167, 275);
		panel_10.add(list_3);
		
		JLabel label_4 = new JLabel("\uD3B8\uC9D1\uC740 \uB354\uBE14\uD074\uB9AD");
		label_4.setBounds(10, 306, 98, 35);
		panel_10.add(label_4);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(255, 255, 255));
		panel_11.setBounds(187, 0, 432, 351);
		panel_3.add(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblNewLabel_16 = new JLabel("이름");
		lblNewLabel_16.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel_16.setBounds(28, 20, 95, 31);
		panel_11.add(lblNewLabel_16);
		
		JLabel label_1 = new JLabel("가격");
		label_1.setFont(new Font("굴림", Font.PLAIN, 16));
		label_1.setBounds(28, 61, 95, 31);
		panel_11.add(label_1);
		
		JLabel label_2 = new JLabel("생산단가");
		label_2.setFont(new Font("굴림", Font.PLAIN, 16));
		label_2.setBounds(28, 102, 95, 31);
		panel_11.add(label_2);
		
		JLabel label_3 = new JLabel("재료");
		label_3.setFont(new Font("굴림", Font.PLAIN, 16));
		label_3.setBounds(28, 143, 95, 31);
		panel_11.add(label_3);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_6.setBounds(79, 21, 222, 31);
		panel_11.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_7.setColumns(10);
		textField_7.setBounds(79, 62, 222, 31);
		panel_11.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_8.setColumns(10);
		textField_8.setBounds(115, 102, 186, 31);
		panel_11.add(textField_8);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setLineWrap(true);
		textArea.setRows(10);
		textArea.setBounds(37, 174, 354, 115);
		panel_11.add(textArea);
		
		JButton btnNewButton_18 = new JButton("편집");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = JOptionPane.showConfirmDialog(null,textField_6.getText() + " 메뉴를 편집하시겠습니까?","편집",JOptionPane.OK_CANCEL_OPTION);
				if ( i == 0 )
				{
					dm.modifyMenu(textField_6.getText(), textField_7.getText(), textField_8.getText(), textArea.getText());
					list_3 = dm.UpdateMenuList(list_3);
					dm.ListToChoice(choice);
				}
			}
		});
		btnNewButton_18.setBackground(Color.WHITE);
		btnNewButton_18.setBounds(239, 299, 76, 42);
		panel_11.add(btnNewButton_18);
		
		JButton btnNewButton_19 = new JButton("삭제");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null,textField_6.getText() +" 메뉴를 삭제하시겠습니까","삭제",JOptionPane.OK_CANCEL_OPTION);
				if ( i == 0 )
				{
					dm.delMenu(textField_6.getText());
					list_3 = dm.UpdateMenuList(list_3);
					dm.ListToChoice(choice);
					textField_6.setText(""); 
					textField_7.setText("");
					textField_8.setText(""); 
					textArea.setText("");
				}
			}
		});
		btnNewButton_19.setBackground(Color.WHITE);
		btnNewButton_19.setBounds(325, 299, 76, 42);
		panel_11.add(btnNewButton_19);
		
		JButton btnNewButton_24 = new JButton("추가");
		btnNewButton_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = JOptionPane.showConfirmDialog(null,"메뉴를 추가하시겠습니까?","추가",JOptionPane.OK_CANCEL_OPTION);
				if ( i == 0 )
				{
					dm.addMenu(textField_6.getText(), textField_7.getText(), textField_8.getText(), textArea.getText());
					list_3 = dm.UpdateMenuList(list_3);
					dm.ListToChoice(choice);
				}
			}
		});
		btnNewButton_24.setBackground(Color.WHITE);
		btnNewButton_24.setBounds(153, 299, 76, 42);
		panel_11.add(btnNewButton_24);
		
		JButton btnNewButton_17 = new JButton("초기화");
		btnNewButton_17.setBounds(67, 299, 76, 42);
		panel_11.add(btnNewButton_17);
		btnNewButton_17.setBackground(new Color(255, 255, 255));
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textArea.setText("");
			}
		});
		tabbedPane.setBackgroundAt(3, Color.WHITE);
		//---------------------------------------------------------------
		
		// 직원
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("직원", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		panel_12.setBounds(0, 0, 619, 351);
		panel_4.add(panel_12);
		panel_12.setLayout(null);
		
		JButton btnNewButton_20 = new JButton("편집");
		btnNewButton_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.modifyEmployee(table_1.getValueAt(table_1.getSelectedRow(), 0),
						table_1.getValueAt(table_1.getSelectedRow(), 1),table_1.getValueAt(table_1.getSelectedRow(), 2),
						table_1.getValueAt(table_1.getSelectedRow(), 3),table_1.getValueAt(table_1.getSelectedRow(), 5));
				table_1 = dm.UpdateEmployeeList(table_1);
			}
		});
		
		btnNewButton_20.setBounds(383, 297, 65, 44);
		panel_12.add(btnNewButton_20);
		
		JButton btnNewButton_21 = new JButton("추가");
		btnNewButton_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dm.addEmployee(table_1.getValueAt(table_1.getSelectedRow(), 0),
						table_1.getValueAt(table_1.getSelectedRow(), 1), table_1.getValueAt(table_1.getSelectedRow(), 2),
						table_1.getValueAt(table_1.getSelectedRow(), 3), date,
						table_1.getValueAt(table_1.getSelectedRow(), 5));
				table_1 = dm.UpdateEmployeeList(table_1);
			}
		});
		btnNewButton_21.setBounds(458, 297, 65, 44);
		panel_12.add(btnNewButton_21);
		
		JButton btnNewButton_22 = new JButton("삭제");
		btnNewButton_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.delEmployee(table_1.getValueAt(table_1.getSelectedRow(),0));
				table_1 = dm.UpdateEmployeeList(table_1);
			}
		});
		
		btnNewButton_22.setBounds(533, 297, 65, 44);
		panel_12.add(btnNewButton_22);
		
		//JTable
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 599, 277);
		panel_12.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"\uBC88\uD638", "\uC774\uB984", "\uAE09\uC5EC", "\uC9C1\uAE09", "\uC785\uC0AC\uC77C", "\uC5F0\uB77D\uCC98"
			}
		));
		table_1.setBounds(10, 10, 599, 277);
		scrollPane.setViewportView(table_1);
		table_1 = dm.UpdateEmployeeList(table_1);
		//------------------------------------------------------------------
		
		// 전체 판넬 component
		
		// 종료버튼
		JButton btnNewButton = new JButton("종료");
		btnNewButton.setForeground(new Color(75, 0, 130));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				int i = JOptionPane.showConfirmDialog(null,"프로그램을 종료합니다.\n마감을 하셨는지 확인해주십시오.","종료",JOptionPane.OK_CANCEL_OPTION);
				if ( i == 0 ) {
					// 창고, 회원, 메뉴, 직원 데이터는 상시 저장됨
					// 날짜데이터 저장
					dm.saveDay(date);
					//총자금 업뎃 - AllProfit
					lblNewLabel_27.setText(dm.updateAllProfit());
					//주문누적액 업뎃
					lblNewLabel_36.setText("");
					//당일매출액 업뎃
					lblNewLabel_26.setText("0원");
					System.exit(0);
					}
				}
			});
		btnNewButton.setBounds(557, 78, 77, 29);
		
		// 마감버튼
		JButton button = new JButton("마감");
		button.setForeground(new Color(75, 0, 130));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//테이블이 모두 비었다면
				if ( lblNewLabel_28.getText().equals("") && lblNewLabel_29.getText().equals("") &&
						lblNewLabel_30.getText().equals("") && lblNewLabel_31.getText().equals("") &&
						lblNewLabel_32.getText().equals("") && lblNewLabel_33.getText().equals("") &&
						lblNewLabel_34.getText().equals("") && lblNewLabel_35.getText().equals("") ) {
					//당일매출액 더하기
				dm.modifyAllProfit(DayProfit);
				// 주문누적액 빼기
				OrderCost -= 2*OrderCost;
				dm.modifyAllProfit(OrderCost);
				// 재고데이터 저장
				dm.modifyIngWhenExit();
				table_2 = dm.UpdateIngList(table_2);
				// 고객데이터 업뎃
				table = dm.UpdateCustomerList(table);
				// 직원데이터 업뎃
				table_1 = dm.UpdateEmployeeList(table_1);
				//총자금 업뎃
				lblNewLabel_27.setText(dm.updateAllProfit() + "원");
				//주문누적액 업뎃
				lblNewLabel_36.setText("");
				//당일매출액 업뎃
				lblNewLabel_26.setText("0원");
				//다음날로
				date = dm.returnNextDay();
				// 날짜 텍스트
				lblNewLabel_23.setText(date);
				// 날짜데이터 저장
				dm.saveDay(date);
				OrderCost = 0;
				DayProfit = 0;
				// 급여날짜 : 매월 1일 급여 지불
				// day 설정 ( 직원월급, 회원 등급, 마일리지 초기화)
		        if ( date != null) {
		        StringTokenizer st = new StringTokenizer(date," ");
		        day = st.nextToken();
		        day = st.nextToken();
		        day = st.nextToken();
		        }
				if ( day.equals("01")) {
					int emPay = dm.returnAllEmployeePay();
					emPay -= 2*emPay;
					dm.modifyAllProfit(emPay);
					dm.InitialMileage();
					lblNewLabel_27.setText(dm.updateAllProfit() + "원");
					table = dm.UpdateCustomerList(table);
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "결제되지 않은 테이블이 있습니다.");
				}
			}
		});
		button.setBackground(Color.WHITE);
		button.setBounds(124, 78, 63, 29);
		
		// 타이틀
		JLabel lblWelcomTo = new JLabel("YOLO wa BAR");
		lblWelcomTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomTo.setBackground(Color.WHITE);
		lblWelcomTo.setForeground(Color.WHITE);
		lblWelcomTo.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 29));
		lblWelcomTo.setBounds(221, 35, 227, 50);
		
		// 당일 매출
		JLabel lblNewLabel_24 = new JLabel("당일 매출 : ");
		lblNewLabel_24.setForeground(Color.WHITE);
		lblNewLabel_24.setBounds(10, 30, 71, 15);
		
		// 총자금
		JLabel lblNewLabel_25 = new JLabel("총 자금 : ");
		lblNewLabel_25.setForeground(Color.WHITE);
		lblNewLabel_25.setBounds(10, 54, 71, 15);
		
		// 당일매출 숫자
		lblNewLabel_26 = new JLabel("");
		lblNewLabel_26.setForeground(Color.WHITE);
		lblNewLabel_26.setBounds(73, 30, 114, 15);
		lblNewLabel_26.setText(Integer.toString(DayProfit)+"원");
		
		// 총자금 숫자
		lblNewLabel_27 = new JLabel("");
		lblNewLabel_27.setForeground(Color.WHITE);
		lblNewLabel_27.setBounds(73, 54, 114, 15);
		lblNewLabel_27.setText(dm.updateAllProfit()+"원");
		
		// 배경설정
		icon = new ImageIcon("galaxy.jpg");
		JPanel panel_draw = new JPanel() {
			public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
			}
		};
		panel_draw.setBounds(0, 0, 647, 506);
		
		//layeredpane 배경, tab메뉴, 나머지 라벨, 메뉴
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,647,506);
		layeredPane.add(panel_draw, new Integer(0));
		layeredPane.add(tabbedPane,new Integer(1));
		layeredPane.add(lblNewLabel_23, new Integer(2));
		layeredPane.add(lblNewLabel_24, new Integer(2));
		layeredPane.add(lblNewLabel_25, new Integer(2));
		layeredPane.add(lblNewLabel_26, new Integer(2));
		layeredPane.add(lblNewLabel_27, new Integer(2));
		layeredPane.add(lblWelcomTo, new Integer(2));
		layeredPane.add(button, new Integer(2));
		layeredPane.add(btnNewButton, new Integer(2));
		getContentPane().add(layeredPane);
	}
}
