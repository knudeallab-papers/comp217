import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class TableTap extends JFrame{
	
	Table TableAry[] = new Table[8];
	
	JTabbedPane t = new JTabbedPane();
	
	JPanel p1 = new JPanel();
	
	JLabel[] tableSection = new JLabel[8];
	public static final String tapBtn1 = "테이블";
	public static final String tapBtn2 = "회원";
	public static final String tapBtn3 = "메뉴";
	
	public TableTap(){
		super("TableTap");
		
		JButton btn1 = new JButton("탭 연습 1");
		
		p1.add(btn1);
		
		t.add("table", p1);
		t.add("members", new JTextArea());
		t.add("menu", new JTextArea());
		
		add(t);
		
		setSize(450, 350);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void drawTable(Table item, int i)
	{
		
		tableSection[i] = new JLabel();
		tableSection[i].setText("테이블"+(i+1));
		tableSection[i].setText("총액: " + item.getPrice());
		
		p1.add(tableSection[i]);
	}
	public void drawTable(int i)
	{
		tableSection[i] = new JLabel();
		System.out.println("Draw Table section"+ i);
		tableSection[i].setText("테이블"+(i+1));
		
		p1.add(tableSection[i]);
	}
	
}
