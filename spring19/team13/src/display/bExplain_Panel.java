package display;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
import dataClass.member;
 
public class bExplain_Panel extends JPanel{
   
	private JPanel p;
	private JTextField tfTitle, tfAuthor, tfIsbn, tfLink, tfimg;// 제목 저자 링크 isbn 이미지
	private JTextArea taExp; // 설명
	private JButton btnInsert, btnCancel; //등록, 취소 버튼
	private GridBagLayout gb;
	private GridBagConstraints gbc;
	private display_book back;
	
	private ArrayList<book> bookList;    
	private book NEWbook;
	private JTable BackTable;
	private DefaultTableModel BackModel;
	private ImageIcon bookImg;
	private JLabel bookImgLabel;
	
	
    public static void main(String[] args) {
        JFrame hm = new JFrame();
        bExplain_Panel hi = new bExplain_Panel();
        hm.add(hi);
        hm.setSize(500, 650);
        hm.setVisible(true);
    }
	public bExplain_Panel()
	{	
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
	
		bookImg = display_member.imageChanger(new ImageIcon("./images/Book.png"), 120, 130);
        bookImgLabel = new JLabel(bookImg);
    	gbAdd(bookImgLabel,2,0,2,1);
		
		//제목
		JLabel bTitle = new JLabel("*제목 :");
		tfTitle = new JTextField(20); tfTitle.setEditable(false);
		gbAdd(bTitle,0,1,1,1);
		gbAdd(tfTitle,1,1,3,1);
		
		//저자
		JLabel bAuthor = new JLabel("*저자: ");
		tfAuthor = new JTextField(20); tfAuthor.setEditable(false);
		gbAdd(bAuthor,0,2,1,1);
		gbAdd(tfAuthor,1,2,3,1);
		
		//isbn
		JLabel bIsbn = new JLabel("*ISBN : ");
		tfIsbn = new JTextField(20); tfIsbn.setEditable(false);
		gbAdd(bIsbn, 0,3,1,1);
		gbAdd(tfIsbn,1,3,3,1);
		
        //링크
		JLabel bLink= new JLabel("링크: ");
		tfLink = new JTextField(20); tfLink.setEditable(false);
		gbAdd(bLink,0,4,1,1);
		gbAdd(tfLink,1,4,3,1);
		
		
		//이미지
		JLabel bimg = new JLabel("이미지: ");
		tfimg = new JTextField(20); tfimg.setEditable(false);
		gbAdd(bimg,0,5,1,1);
		gbAdd(tfimg,1,5,3,1);
		
		JLabel bExp = new JLabel("설명: ");
		taExp = new JTextArea(20,5); taExp.setEditable(false);
		JScrollPane spExp = new JScrollPane(taExp);
		gbAdd(bExp,0,6,1,2);
		gbAdd(spExp,1,6,3,2);

	}
    public void ExplainChange(book newBook) throws Exception
    {	
    	tfTitle.setText(newBook.getTitle());
    	tfAuthor.setText(newBook.getAuthor());
    	tfLink.setText(newBook.getLink());
    	tfIsbn.setText(newBook.getIsbn());
    	tfimg.setText(newBook.getImgAddres());
    	taExp.setText(newBook.getExplain());
    	if (newBook.getImgAddres() == null)
        {
        }
        else
        {
        	bookImg = display_member.imageChanger(new ImageIcon(newBook.getImgAddres()), 120, 130);
        	bookImgLabel.setIcon(bookImg);
        }
    }

	//그리드백레이아웃에 붙이는 메소드
    private void gbAdd(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        //gb.setConstraints(c, gbc);
        gbc.insets = new Insets(5,5,5,5);
        add(c, gbc);
    }//gbAdd
    
}//end