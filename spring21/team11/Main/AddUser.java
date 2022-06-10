package Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class AddUser extends JFrame implements ActionListener{

	public static final int WIDTH = 250;
	public static final int HEIGHT = 85;
	
	private JTextField Text;
	public AddUser()
	{
		setTitle("Add User");
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
			PrintWriter outputStream = null;
			if (f.exists())
				JOptionPane.showMessageDialog(this, "User Already Registered", "Message", JOptionPane.INFORMATION_MESSAGE);
			else
			{
				try {
					outputStream = new PrintWriter(f);
				}
				catch (FileNotFoundException g)
				{
					g.printStackTrace();
					System.exit(0);
				}
				JOptionPane.showMessageDialog(this, "Register Success", "Message", JOptionPane.INFORMATION_MESSAGE);
				outputStream.println(Text.getText() + ",0,0");
				outputStream.close();
			}
		}
	}

}