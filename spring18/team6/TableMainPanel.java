package FoodStore;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableMainPanel extends JPanel {
	public static TLP1 t1;
	public static TLP2 t2;
	public static TLP3 t3;
	public static TLP4 t4;
	public static TLP5 t5;
	public static TLP6 t6;
	public static TLP7 t7;
	public static TLP8 t8;
	public static TLP9 t9;
	
	public static JButton selectedTable, Table1, Table2, Table3, Table4, Table5, Table6, Table7, Table8, Table9;
	public static double result = 0;
	/**
	 * Create the panel.
	 */
	public TableMainPanel() {
		setSize(1176, 750);
		setLayout(null);
		
		t1 = new TLP1();
		t1.setVisible(false);
		t1.setBounds(765, 0, 411, 750);
		add(t1);
		
		t2 = new TLP2();
		t2.setVisible(false);
		t2.setBounds(765, 0, 411, 750);
		add(t2);
		
		t3 = new TLP3();
		t3.setVisible(false);
		t3.setBounds(765, 0, 411, 750);
		add(t3);
		
		t4 = new TLP4();
		t4.setVisible(false);
		t4.setBounds(765, 0, 411, 750);
		add(t4);
		
		t5 = new TLP5();
		t5.setVisible(false);
		t5.setBounds(765, 0, 411, 750);
		add(t5);
		
		t6 = new TLP6();
		t6.setVisible(false);
		t6.setBounds(765, 0, 411, 750);
		add(t6);
		
		t7 = new TLP7();
		t7.setVisible(false);
		t7.setBounds(765, 0, 411, 750);
		add(t7);
		
		t8 = new TLP8();
		t8.setVisible(false);
		t8.setBounds(765, 0, 411, 750);
		add(t8);
		
		t9 = new TLP9();
		t9.setVisible(false);
		t9.setBounds(765, 0, 411, 750);
		add(t9);
		
		Table1 = new JButton("테이블1");
		Table1.setBounds(25, 25, 225, 225);
		Table1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTable = Table1;
				TableMainPanel.t1.setVisible(true);TableMainPanel.t2.setVisible(false);TableMainPanel.t3.setVisible(false);
				TableMainPanel.t4.setVisible(false);TableMainPanel.t5.setVisible(false);TableMainPanel.t6.setVisible(false);
				TableMainPanel.t7.setVisible(false);TableMainPanel.t8.setVisible(false);TableMainPanel.t9.setVisible(false);
			}
		});
		add(Table1);
		
		Table2 = new JButton("테이블2");
		Table2.setBounds(268, 25, 225, 225);
		Table2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTable = Table2;
				TableMainPanel.t1.setVisible(false);TableMainPanel.t2.setVisible(true);TableMainPanel.t3.setVisible(false);
				TableMainPanel.t4.setVisible(false);TableMainPanel.t5.setVisible(false);TableMainPanel.t6.setVisible(false);
				TableMainPanel.t7.setVisible(false);TableMainPanel.t8.setVisible(false);TableMainPanel.t9.setVisible(false);
			}
		});
		add(Table2);
		
		Table3 = new JButton("테이블3");
		Table3.setBounds(515, 25, 225, 225);
		Table3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTable = Table3;
				TableMainPanel.t1.setVisible(false);TableMainPanel.t2.setVisible(false);TableMainPanel.t3.setVisible(true);
				TableMainPanel.t4.setVisible(false);TableMainPanel.t5.setVisible(false);TableMainPanel.t6.setVisible(false);
				TableMainPanel.t7.setVisible(false);TableMainPanel.t8.setVisible(false);TableMainPanel.t9.setVisible(false);
			}
		});
		add(Table3);
		
		Table4 = new JButton("테이블4");
		Table4.setBounds(25, 264, 225, 225);
		Table4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTable = Table4;
				TableMainPanel.t1.setVisible(false);TableMainPanel.t2.setVisible(false);TableMainPanel.t3.setVisible(false);
				TableMainPanel.t4.setVisible(true);TableMainPanel.t5.setVisible(false);TableMainPanel.t6.setVisible(false);
				TableMainPanel.t7.setVisible(false);TableMainPanel.t8.setVisible(false);TableMainPanel.t9.setVisible(false);
			}
		});
		add(Table4);
		
		Table5 = new JButton("테이블5");
		Table5.setBounds(268, 264, 225, 225);
		Table5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTable = Table5;
				TableMainPanel.t1.setVisible(false);TableMainPanel.t2.setVisible(false);TableMainPanel.t3.setVisible(false);
				TableMainPanel.t4.setVisible(false);TableMainPanel.t5.setVisible(true);TableMainPanel.t6.setVisible(false);
				TableMainPanel.t7.setVisible(false);TableMainPanel.t8.setVisible(false);TableMainPanel.t9.setVisible(false);
			}
		});
		add(Table5);
		
		Table6 = new JButton("테이블6");
		Table6.setBounds(515, 264, 225, 225);
		Table6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTable = Table6;
				TableMainPanel.t1.setVisible(false);TableMainPanel.t2.setVisible(false);TableMainPanel.t3.setVisible(false);
				TableMainPanel.t4.setVisible(false);TableMainPanel.t5.setVisible(false);TableMainPanel.t6.setVisible(true);
				TableMainPanel.t7.setVisible(false);TableMainPanel.t8.setVisible(false);TableMainPanel.t9.setVisible(false);
			}
		});
		add(Table6);
		
		Table7 = new JButton("테이블7");
		Table7.setBounds(25, 505, 225, 225);
		Table7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTable = Table7;
				TableMainPanel.t1.setVisible(false);TableMainPanel.t2.setVisible(false);TableMainPanel.t3.setVisible(false);
				TableMainPanel.t4.setVisible(false);TableMainPanel.t5.setVisible(false);TableMainPanel.t6.setVisible(false);
				TableMainPanel.t7.setVisible(true);TableMainPanel.t8.setVisible(false);TableMainPanel.t9.setVisible(false);
			}
		});
		add(Table7);
		
		Table8 = new JButton("테이블8");
		Table8.setBounds(268, 505, 225, 225);
		Table8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTable = Table8;
				TableMainPanel.t1.setVisible(false);TableMainPanel.t2.setVisible(false);TableMainPanel.t3.setVisible(false);
				TableMainPanel.t4.setVisible(false);TableMainPanel.t5.setVisible(false);TableMainPanel.t6.setVisible(false);
				TableMainPanel.t7.setVisible(false);TableMainPanel.t8.setVisible(true);TableMainPanel.t9.setVisible(false);
			}
		});
		add(Table8);
		
		Table9 = new JButton("테이블9");
		Table9.setBounds(515, 505, 225, 225);
		Table9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTable = Table9;
				TableMainPanel.t1.setVisible(false);TableMainPanel.t2.setVisible(false);TableMainPanel.t3.setVisible(false);
				TableMainPanel.t4.setVisible(false);TableMainPanel.t5.setVisible(false);TableMainPanel.t6.setVisible(false);
				TableMainPanel.t7.setVisible(false);TableMainPanel.t8.setVisible(false);TableMainPanel.t9.setVisible(true);
			}
		});
		add(Table9);
	}

}
