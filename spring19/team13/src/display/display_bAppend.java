package display;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dataClass.book;
 
public class display_bAppend extends JFrame implements ActionListener
{
   
	private JPanel p;
	private JTextField tfTitle, tfAuthor, tfIsbn, tfLink, tfimg;// ���� ���� ��ũ isbn �̹���
	private JTextArea taExp; // ����
	private JButton btnInsert, btnCancel; //���, ��� ��ư
	private GridBagLayout gb;
	private GridBagConstraints gbc;
	private display_book back;
	
	ArrayList<book> bookList;    
	book NEWbook;
	JTable BackTable;
	DefaultTableModel BackModel;
	
	
	public display_bAppend()
	{
		super("���");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(350,400);
		
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
	
		
		//����
		JLabel bTitle = new JLabel("*���� :");
		tfTitle = new JTextField(20);
		gbAdd(bTitle,0,0,1,1);
		gbAdd(tfTitle,1,0,3,1);
		
		//����
		JLabel bAuthor = new JLabel("*����: ");
		tfAuthor = new JTextField(20);
		gbAdd(bAuthor,0,1,1,1);
		gbAdd(tfAuthor,1,1,3,1);
		
		//isbn
		JLabel bIsbn = new JLabel("*ISBN : ");
		tfIsbn = new JTextField(20);
		gbAdd(bIsbn, 0,2,1,1);
		gbAdd(tfIsbn,1,2,3,1);
		
        //��ũ
		JLabel bLink= new JLabel("��ũ: ");
		tfLink = new JTextField(20);
		gbAdd(bLink,0,3,1,1);
		gbAdd(tfLink,1,3,3,1);
		
		
		//�̹���
		JLabel bimg = new JLabel("�̹���: ");
		tfimg = new JTextField(20);
		gbAdd(bimg,0,4,1,1);
		gbAdd(tfimg,1,4,3,1);
		
		JLabel bExp = new JLabel("����: ");
		taExp = new JTextArea(20,5);
		JScrollPane spExp = new JScrollPane(taExp);
		gbAdd(bExp,0,5,1,2);
		gbAdd(spExp,1,5,3,2);
		
		//��ư
		JPanel pButton = new JPanel();
		btnInsert = new JButton("���");
		btnCancel = new JButton("���");     
		pButton.add(btnInsert);
		pButton.add(btnCancel);    
		gbAdd(pButton, 0, 10, 4, 1);
		btnInsert.addActionListener(this);
		btnCancel.addActionListener(this);
		
        setVisible(true);
	}//������
	public display_bAppend(ArrayList<book> bookList, DefaultTableModel BM)
	{
		this();
		this.BackModel = BM;
		this.bookList = bookList;
	}
	
    	
	public static void main(String[] args)
	{
		
		display_bAppend hi = new display_bAppend();
		hi.setVisible(true);
    }
    
	@Override
    public void actionPerformed(ActionEvent e) 
	{
    	
		if(e.getSource() == btnInsert)
		{
			if(bookCheck())
			{
				setBook();
				save();
				dispose();
			}
		}
		if(e.getSource() == btnCancel)
		{
			dispose();
		}   
	}//actionPerformed 
    public void save() {
    	try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./data/book.dat"));
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
    
    
	//�׸���鷹�̾ƿ��� ���̴� �޼ҵ�
	private void gbAdd(JComponent c, int x, int y, int w, int h){
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		//gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		add(c, gbc);
    }//gbAdd
	
	public boolean bookCheck()
	{
		String error_message = "";
		if (tfTitle.getText().equals("")) 
		{
			error_message = "������ �Է��ϼ���";
    	}
    	else if (tfAuthor.getText().equals("")) 
    	{
    		error_message = "���ڸ� �Է��ϼ���";
    	}
   		else if (tfIsbn.getText().equals("")) 
   		{
   			error_message = "ISBN�� �Է��ϼ���";
   		}
    	
		if(error_message.equals("")) return true;
		else
		{
			display_error err = new display_error(error_message);
			err.setVisible(true);
			return false;
		}
	}
    
	public boolean hasmember() 
	{
		if (NEWbook != null) return true;
		return false;
	}
    
	public void setBook() 
	{
		NEWbook = new book();
		NEWbook.setTitle(tfTitle.getText());
		NEWbook.setAuthor(tfAuthor.getText());
		NEWbook.setLink(tfLink.getText());
		NEWbook.setIsbn(tfIsbn.getText());
		NEWbook.setExpalin(taExp.getText());
		if(!tfimg.getText().equals(""))
		{
			NEWbook.setImgAddress(tfimg.getText());
		}
		bookList.add(NEWbook);
		refresh();
	}
	public void refresh() 
	{
		BackModel.addRow(bookList.get(bookList.size()-1).getall());
	}
	public book getBook() 
	{
		return NEWbook;
	}
    
    
}//end