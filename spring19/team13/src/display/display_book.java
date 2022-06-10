package display;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dataClass.book;

public class display_book extends JFrame implements ActionListener, ListSelectionListener {
	/**
	 * 
	 */
	private JTable bookTable;
	private ArrayList<book> bookList;
	private JButton BackBtn, AppendBtn, DeleteBtn, AdjustBtn;
	private DefaultTableModel model;
	private bExplain_Panel explainPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					display_book frame = new display_book();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public display_book() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		bookList = new ArrayList<book>();
		
		
		JPanel TablePanel = new JPanel(new BorderLayout());
		TablePanel.setBounds(0, 103, 650, 650);
		getContentPane().add(TablePanel);
		

		String header[] = {"책 이름","저자","대출가능여부"};
		model = new DefaultTableModel(header, 0) {
	         public boolean isCellEditable(int row, int column) {
	             if(column >= 0) {
	                return false;
	             }else {
	                return true;
	             }
	          }
	       };;
		bookTable = new JTable(model);
		bookTable.setRowHeight(25);
		bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(bookTable);
		TablePanel.add(scrollPane);
		bookTable.getSelectionModel().addListSelectionListener(this);
		
		explainPanel = new bExplain_Panel();
		explainPanel.setBounds(682, 103, 500, 650);
		getContentPane().add(explainPanel);
		
		//버튼들 생성
		BackBtn = new JButton();
		BackBtn.setBounds(1032, 0, 150, 100);
		getContentPane().add(BackBtn);
		ImageIcon BackIcon = new ImageIcon("images/back.png");
		BackIcon = imageChanger(BackIcon, 150, 100);
		BackBtn.setIcon(BackIcon);
		BackBtn.addActionListener(this);
		
		JPanel BtnPanel = new JPanel();
		BtnPanel.setBounds(0, 0, 400, 91);
		getContentPane().add(BtnPanel);
		BtnPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		AppendBtn = new JButton();
		BtnPanel.add(AppendBtn);
		ImageIcon AppendIcon = new ImageIcon("images/append.png");
		AppendIcon = imageChanger(AppendIcon, BtnPanel.getWidth()/3, BtnPanel.getHeight());
		AppendBtn.setIcon(AppendIcon);	
		AppendBtn.addActionListener(this);
		
		DeleteBtn = new JButton();
		BtnPanel.add(DeleteBtn);
		ImageIcon DeleteIcon = new ImageIcon("images/delete.png");
		DeleteIcon = imageChanger(DeleteIcon, BtnPanel.getWidth()/3, BtnPanel.getHeight());
		DeleteBtn.setIcon(DeleteIcon);
		DeleteBtn.addActionListener(this);
		
		AdjustBtn = new JButton();
		BtnPanel.add(AdjustBtn);
		ImageIcon AdjustIcon = new ImageIcon("images/adjust.png");
		AdjustIcon = imageChanger(AdjustIcon, BtnPanel.getWidth()/3, BtnPanel.getHeight());
		AdjustBtn.setIcon(AdjustIcon);
		AdjustBtn.addActionListener(this);
		
		load();
		
		
	}
	
	public void load()
	{
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream("./data/book.dat"));
			try {
				while(true)
				{
					try {
						book tmpBook = (book) inputStream.readObject();
						bookList.add(tmpBook);
						model.addRow(tmpBook.getall());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			} catch(EOFException e) {
				
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void save() {
    	try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./data/member.dat"));
			for(int i=0; i<bookList.size(); i++)
				outputStream.writeObject(bookList.get(i));
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public display_book(ArrayList<book> bookList)
	{
		this();
		this.bookList = bookList;
		refresh();
	}
	
	public static ImageIcon imageChanger(ImageIcon originIcon, int h, int w)
	{
		Image originImg = originIcon.getImage();
		Image chagedImg = originImg.getScaledInstance(h, w, Image.SCALE_SMOOTH);
		ImageIcon Icon = new ImageIcon(chagedImg);	
		return Icon;
	}


	//이거부터 완성 부탁
	public void refresh()
	{
		model.addRow(bookList.get(bookList.size()-1).getall());
	}
	
	public void setModel(DefaultTableModel model)
	{
		this.model = model;	
	}
	public void setBookList(ArrayList<book> bookList)
	{
		this.bookList = bookList;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == AppendBtn)
		{
			display_bAppend hi = null;
			hi = new display_bAppend(bookList, model);
			hi.setVisible(true);
		}
		if(e.getSource() == DeleteBtn)
		{
			int deleteIndex = bookTable.getSelectedRow();
			if(!bookList.get(deleteIndex).getLentChk())
			{
				display_error bookError = new display_error("책이 반납되지 않았습니다");
				bookError.setVisible(true);
			}
			else {
				model.removeRow(deleteIndex);
				bookList.remove(deleteIndex);
				save();
			}
		}
		if(e.getSource() == AdjustBtn)
		{
			int adjustIndex = bookTable.getSelectedRow();
			display_bAdjust adj = new display_bAdjust(bookList.get(adjustIndex), bookList, adjustIndex, model);
		}
	    if(e.getSource() == BackBtn)
	    {
	    	display_main main = new display_main();
	    	main.setVisible(true);
	    	dispose();
	    }
	}
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (!e.getValueIsAdjusting() && bookTable.getSelectedRow() != -1)
		{
			try {
				explainPanel.ExplainChange(bookList.get(bookTable.getSelectedRow()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}

