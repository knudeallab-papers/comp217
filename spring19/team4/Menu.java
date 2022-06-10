import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Menu extends JFrame implements ActionListener{
	
	public static final int WIDTH=800;
	public static final int HEIGHT=500;
	
	public Menu() {
		super("�μ��� ���尣");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JLabel title=new JLabel("�μ��� ���尣",JLabel.CENTER);
		Font font=new Font("Sherif",Font.BOLD,40);
		title.setFont(font);
		add(title,BorderLayout.NORTH);
		ImageIcon startmenu=new ImageIcon("newmain.jpg");
		JLabel icon=new JLabel("",JLabel.CENTER);
		icon.setIcon(startmenu);
		add(icon,BorderLayout.CENTER);
		JPanel btn=new JPanel();
		btn.setLayout(new FlowLayout());
		
		JButton shield=new JButton("���� ��ȭ");
		shield.addActionListener(this);
		btn.add(shield);
		
		JButton gambling=new JButton("���ڼ�");
		gambling.addActionListener(this);
		btn.add(gambling);
		
		JButton sword=new JButton("�� ��ȭ");
		sword.addActionListener(this);
		btn.add(sword);
		
		JButton edit = new JButton("Editor");
		edit.addActionListener(this);
		btn.add(edit);
		
		
		
		add(btn,BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu=new Menu();
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actioncommand=e.getActionCommand();
		if(actioncommand.equals("���� ��ȭ")) {
			Reinforce reinforce=new Reinforce();
			reinforce.setLocationRelativeTo(null);
			reinforce.setVisible(true);
			dispose();
		}
		else if(actioncommand.equals("���ڼ�")) {
			LasVegas lasvegas = new LasVegas();
			lasvegas.setLocationRelativeTo(null);
			lasvegas.setVisible(true);
			dispose();
		}
		else if(actioncommand.equals("�� ��ȭ")) {
			Reinforce2 reinforce2 = new Reinforce2();
			reinforce2.setLocationRelativeTo(null);
			reinforce2.setVisible(true);
			dispose();
		}
		else if(actioncommand.equals("Editor")) {
			editor ed = new editor();
			ed.setLocationRelativeTo(null);
			ed.setVisible(true);
			dispose();
		}
	}
}
