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
	private JTextField tfTitle, tfAuthor, tfIsbn, tfLink, tfimg;// 제목 저자 링크 isbn 이미지
	private JTextArea taExp; // 설명
	private JButton btnInsert, btnCancel; //등록, 취소 버튼
	private GridBagLayout gb;
	private GridBagConstraints gbc;
	private display_book back;
	
	ArrayList<book> bookList;    
	book NEWbook;
	JTable BackTable;
	DefaultTableModel BackModel;
	
	
	public display_bAppend()
	{
		super("등록");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(350,400);
		
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
	
		
		//제목
		JLabel bTitle = new JLabel("*제목 :");
		tfTitle = new JTextField(20);
		gbAdd(bTitle,0,0,1,1);
		gbAdd(tfTitle,1,0,3,1);
		
		//저자
		JLabel bAuthor = new JLabel("*저자: ");
		tfAuthor = new JTextField(20);
		gbAdd(bAuthor,0,1,1,1);
		gbAdd(tfAuthor,1,1,3,1);
		
		//isbn
		JLabel bIsbn = new JLabel("*ISBN : ");
		tfIsbn = new JTextField(20);
		gbAdd(bIsbn, 0,2,1,1);
		gbAdd(tfIsbn,1,2,3,1);
		
        //링크
		JLabel bLink= new JLabel("링크: ");
		tfLink = new JTextField(20);
		gbAdd(bLink,0,3,1,1);
		gbAdd(tfLink,1,3,3,1);
		
		
		//이미지
		JLabel bimg = new JLabel("이미지: ");
		tfimg = new JTextField(20);
		gbAdd(bimg,0,4,1,1);
		gbAdd(tfimg,1,4,3,1);
		
		JLabel bExp = new JLabel("설명: ");
		taExp = new JTextArea(20,5);
		JScrollPane spExp = new JScrollPane(taExp);
		gbAdd(bExp,0,5,1,2);
		gbAdd(spExp,1,5,3,2);
		
		//버튼
		JPanel pButton = new JPanel();
		btnInsert = new JButton("등록");
		btnCancel = new JButton("취소");     
		pButton.add(btnInsert);
		pButton.add(btnCancel);    
		gbAdd(pButton, 0, 10, 4, 1);
		btnInsert.addActionListener(this);
		btnCancel.addActionListener(this);
		
        setVisible(true);
	}//생성자
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
    
    
	//그리드백레이아웃에 붙이는 메소드
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
			error_message = "제목을 입력하세요";
    	}
    	else if (tfAuthor.getText().equals("")) 
    	{
    		error_message = "저자를 입력하세요";
    	}
   		else if (tfIsbn.getText().equals("")) 
   		{
   			error_message = "ISBN을 입력하세요";
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