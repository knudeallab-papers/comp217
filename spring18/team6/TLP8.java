package FoodStore;

import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TLP8 extends JPanel {
	public static JTable addTable8;
	public static Object[] name = new Object[2];
	public static int result = 0;
	public static JScrollPane tableScroll8;
	public static int counter = 0;
	
	public static boolean Tok = false;
	/**
	 * Create the panel.
	 */
	public TLP8() {
		setSize(411, 750);
		setLayout(null);
		
		String[] colName = {"메뉴", "가격"};
		DefaultTableModel defaultTableMod = new DefaultTableModel(colName, 0);
		
		addTable8 = new JTable(defaultTableMod);
		tableScroll8 = new JScrollPane(addTable8);
		tableScroll8.setBounds(6, 6, 399, 600);
		add(tableScroll8);
		
		JButton addBtn = new JButton("추가");
		addBtn.setBounds(16, 618, 175, 126);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTableFrame fr = new AddTableFrame();
				fr.setVisible(true);
			}
		});
		add(addBtn);
		
		JButton payBtn = new JButton("결제");
		payBtn.setBounds(218, 618, 175, 126);
		payBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PayTableFrame pfr = new PayTableFrame();
				pfr.setVisible(true);
			}
		});
		add(payBtn);
	}
}