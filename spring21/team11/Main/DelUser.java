package Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.io.File;
public class DelUser extends JFrame implements ActionListener{

	public static final int WIDTH = 250;
	public static final int HEIGHT = 85;
	
	private JTextField Text;
	public DelUser()
	{
		setTitle("Delete User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		Text = new JTextField("", 10);
		mainPanel.add(Text);
		JButton okBtn = new JButton("OK");
		okBtn.addActionListener(this);
		mainPanel.add(okBtn);
		add(mainPanel);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("OK"))
		{
			File f = new File(Text.getText() + ".txt");
			if (!f.exists())
				JOptionPane.showMessageDialog(this, "User Not Registered", "Message", JOptionPane.INFORMATION_MESSAGE);
			else
			{
				if (f.delete())
					JOptionPane.showMessageDialog(this, "Delete Success", "Message", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(this, "Delete Fail", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}