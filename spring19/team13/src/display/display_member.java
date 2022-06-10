package display;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import dataClass.DateCheck;
import dataClass.member;

public class display_member extends JFrame implements ActionListener, ListSelectionListener {
	private JTable memberTable;
	private ArrayList<member> memberList;
	private JButton BackBtn, AppendBtn, DeleteBtn, AdjustBtn;
	private DefaultTableModel model;
	private mExplain_Panel explainPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					display_member frame = new display_member();
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
	public display_member() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		memberList = new ArrayList<member>();
		
		
		JPanel TablePanel = new JPanel(new BorderLayout());
		TablePanel.setBounds(0, 103, 650, 650);
		getContentPane().add(TablePanel);
		

		String header[] = {"이름","휴대폰번호","연체여부"};
		model = new DefaultTableModel(header, 0) {
	         public boolean isCellEditable(int row, int column) {
	             if(column >= 0) {
	                return false;
	             }else {
	                return true;
	             }
	          }
	       };;
		memberTable = new JTable(model);
		memberTable.setRowHeight(25);
		memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(memberTable);
		memberTable.getSelectionModel().addListSelectionListener(this);
		TablePanel.add(scrollPane);
		
		
		explainPanel = new mExplain_Panel();
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
			inputStream = new ObjectInputStream(new FileInputStream("./data/member.dat"));
			try {
				while(true)
				{
					try {
						member tmpMember = (member) inputStream.readObject();
						memberList.add(tmpMember);
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}
			} catch(EOFException e) {
				
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i =0; i<memberList.size(); i++)
	      {
	    	 for(int j=0;j<memberList.get(i).getLentList().size(); j++)
	    	 {
	    		String begin =  memberList.get(i).getLentList().get(j).getBorrowDate();
	    		try {
					if(DateCheck.diffOfDate(begin, DateCheck.getCurDate()) > 7)
					{
						 memberList.get(i).setOverdue(true);
						 save();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	 }
	      }
	   	for(int i =0; i<memberList.size(); i++)
	   	{
	   		model.addRow(memberList.get(i).getall());
	   	}
	}
    public void save() {
    	try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./data/member.dat"));
			for(int i=0; i<memberList.size(); i++)
				outputStream.writeObject(memberList.get(i));
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public display_member(ArrayList<member> memberList)
	{
		this();
		this.memberList = memberList;
		refresh();
	}
	public member searchMember(String key)
	{
		for(int i=0; i<memberList.size(); i++)
		{
			if(memberList.get(i).getName().equals(key))
			{
				return memberList.get(i);
			}
		}
		return null;
	}
	//이거부터 완성 부탁
	public void refresh()
	{
		model.addRow(memberList.get(memberList.size()-1).getall());
	}
	
	public void setModel(DefaultTableModel model)
	{
		this.model = model;	
	}
	public void setMemberList(ArrayList<member> memberList)
	{
		this.memberList = memberList;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == AppendBtn)
		{
			display_mAppend hi = null;
			hi = new display_mAppend(memberList, memberTable, model);
			hi.setVisible(true);
		}	
		if(e.getSource() == DeleteBtn)
		{
			int deleteIndex = memberTable.getSelectedRow();
			if(memberList.get(deleteIndex).getLentList() != null && memberList.get(deleteIndex).getLentList().size()>4)
			{
				display_error bookError = new display_error("책을 먼저 반납하십시오");
			}
			else {
				model.removeRow(deleteIndex);
				memberList.remove(deleteIndex);
				save();
			}
		}
		if(e.getSource() == AdjustBtn)
		{
			int adjustIndex = memberTable.getSelectedRow();
			display_mAdjust hehe = new display_mAdjust(memberList.get(adjustIndex), memberList, adjustIndex, model);
			
		}
	    else if(e.getSource() == BackBtn)
	    {
	    	display_main main = new display_main();
	    	main.setVisible(true);
	    	dispose();
	    }
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (!e.getValueIsAdjusting() && memberTable.getSelectedRow() != -1)
		{
			try {
				explainPanel.ExplainChange(memberList.get(memberTable.getSelectedRow()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public static ImageIcon imageChanger(ImageIcon originIcon, int h, int w)
	{
		Image originImg = originIcon.getImage();
		Image chagedImg = originImg.getScaledInstance(h, w, Image.SCALE_SMOOTH);
		ImageIcon Icon = new ImageIcon(chagedImg);	
		return Icon;
	}
}
