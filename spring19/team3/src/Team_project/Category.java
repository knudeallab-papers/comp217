package Team_project;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Category extends JFrame{
	String file;
	
	public Category()
	{
		this.file=null;
	}
	
	public Category(String file)
	{
		this.file=file;
	}
	
	public String get_file()
	{
		return file;
	}
	
	public void set_file(String file)
	{
		this.file=file;
	}
}
