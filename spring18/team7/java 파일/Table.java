/**
 * JAVA Term Project : Pos Program
 * Student : Yeji Ahn, Seunghye Jung
 * Prof. : Young-gyoon, Suh
 * Table Class for table button
 */

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table {
	private JTable table;
	private int cost;
	
	public Table() {
		table = new JTable();
		table.setModel(new DefaultTableModel(
				null,
				new String[] {
					"메뉴", "수량", "가격"
				}
			));
		cost = 0;
	}
	
	
	public JTable getTable(JTable oritable) {
		
		if ( oritable != null) {
			oritable.setModel(new DefaultTableModel(
					null,
					new String[] {
						"메뉴", "수량", "가격"
					}
				));
		}
		
		if ( this.table.getRowCount() != 0 ) {
			for ( int i = 0 ; i < this.table.getRowCount() ; i++) {
				String inputStr[] = new String[3];
				inputStr[0] = (String) this.table.getValueAt(i, 0);
				inputStr[1] = (String) this.table.getValueAt(i, 1);
				inputStr[2] = (String) this.table.getValueAt(i, 2);
				((DefaultTableModel) oritable.getModel()).addRow(inputStr);
			}
		}
		return oritable;
	}
	
	public boolean setList(String newMenu) {
		int i = 0;
		int check = 0;
		int cal;
		String nowNum;
		String plusNum;
		String inputStr[] = new String[3];
		
		try {
			for ( i = 0 ; i < table.getRowCount() ; i++) {
				cal = 0;
				if ( ((String)table.getValueAt(i, 0)).equals(newMenu.split("\t")[0])) {
					inputStr[0] = newMenu.split("\t")[0];
					nowNum = (String) table.getValueAt(i, 1);
					plusNum = newMenu.split("\t")[1];
					cal = Integer.parseInt(nowNum) + Integer.parseInt(plusNum);
					inputStr[1] = Integer.toString(cal);
					inputStr[2] = newMenu.split("\t")[2];
					((DefaultTableModel) table.getModel()).removeRow(i);
					((DefaultTableModel) table.getModel()).insertRow(i, inputStr);
					check = 1;
					return true;
				}
			}
			if ( check == 0) {
				inputStr[0] = newMenu.split("\t")[0];
				inputStr[1] = newMenu.split("\t")[1];
				inputStr[2] = newMenu.split("\t")[2];
				((DefaultTableModel) table.getModel()).addRow(inputStr);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int returnCost() {
		return cost;
	}
	
	public void setCost(int Cost) {
		cost = Cost;
	}
	
	public Color updateColor() {
		Color th = new Color(240,248,255);
		if ( table.getRowCount() == 0)
			return Color.WHITE;
		return th;
	}
	
	public Color TableReset() {
		cost = 0;
		table = new JTable();
		table.setModel(new DefaultTableModel(
				null,
				new String[] {
					"메뉴", "수량", "가격"
				}
			));
		return Color.WHITE;
	}
}