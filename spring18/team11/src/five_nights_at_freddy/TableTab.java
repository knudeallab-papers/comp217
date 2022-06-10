package five_nights_at_freddy;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTab extends JPanel implements ActionListener{
	public FoodMenu[] foodmenus = new FoodMenu[8];
	public TableTab() {
		setLayout(new GridLayout(2,4));
		JButton table1 = new JButton("테이블 1");
		JButton table2 = new JButton("테이블 2");
		JButton table3 = new JButton("테이블 3");
		JButton table4 = new JButton("테이블 4");
		JButton table5 = new JButton("테이블 5");
		JButton table6 = new JButton("테이블 6");
		JButton table7 = new JButton("테이블 7");
		JButton table8 = new JButton("테이블 8");
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("테이블 1")) {
			
		}
	}
}
