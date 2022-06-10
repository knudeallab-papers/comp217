package Team_project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

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

public class Caution extends JFrame {

	JPanel pan = new JPanel();
	public Caution()
	{
		setTitle("Caution!");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		pan.setBackground(Color.LIGHT_GRAY);
		pan.setLayout(new BorderLayout());
	}
	
	public void nostock()
	{
		JLabel lab = new JLabel("Stock is empty");
		pan.add(lab, BorderLayout.CENTER);
		add(pan, BorderLayout.CENTER);
	}
	
	public void nomoney() {
		JLabel lab = new JLabel("Money is scarce. Can't order more");
		pan.add(lab, BorderLayout.CENTER);
		add(pan, BorderLayout.CENTER);
	}
	

}
