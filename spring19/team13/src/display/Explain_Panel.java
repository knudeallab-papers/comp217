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
 
public class Explain_Panel extends JPanel{
   
    JPanel p;
    JTextField tfName, tfAddr, tfEmail, tfimg;// �̸� �ּ� �̸��� �̹���
    JTextField tfTel1, tfTel2, tfTel3; //��ȭ
    JComboBox cbJob; //����
    JPasswordField pfPwd; //��й�ȣ   
    JTextField tfYear, tfMonth, tfDay; //�������
    JRadioButton rbMan, rbWoman; //��, ��
    JTextArea taIntro;
    JButton btnInsert, btnCancel; //����, ��� ��ư
    GridBagLayout gb;
    GridBagConstraints gbc;
    display_member back;
    JLabel memLabel;
    JLabel nameLabel;
    ImageIcon memberImg;
    
    ArrayList<book> borrowList;
    member NEWmember;
    JTable lentTable;
    DefaultTableModel lentModel;
    
    public static void main(String[] args) {
        JFrame hm = new JFrame();
        member he = new member();
        Explain_Panel hi = new Explain_Panel();
        hm.add(hi);
        hm.setSize(500, 650);
        hm.setVisible(true);
    }
   
    public Explain_Panel(){
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
       
        //�̸�
        JLabel bName = new JLabel("�̸� :");
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("����", Font.ITALIC, 30));
        gbAdd(bName,0,0,1,1);
        gbAdd(nameLabel,1,0,1,1);
    
       	memberImg = display_member.imageChanger(new ImageIcon("./images/anyone.jpg"), 120, 130);
        memLabel = new JLabel(memberImg);
    	gbAdd(memLabel,2,0,2,1);
       
        //��ȭ
        JLabel bTel = new JLabel("��ȭ :");
        JPanel pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfTel1 = new JTextField(6);
        tfTel2 = new JTextField(6);    
        tfTel3 = new JTextField(6);
        
        pTel.add(tfTel1);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel2);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel3);
        gbAdd(bTel, 0, 2, 1,1);
        gbAdd(pTel, 1, 2, 3,1);
       
        //�ּ�
        JLabel bAddr = new JLabel("�ּ�: ");
        tfAddr = new JTextField(20);
        gbAdd(bAddr, 0,3,1,1);
        gbAdd(tfAddr, 1, 3, 3,1);
       
        //����
        JLabel bBirth= new JLabel("����: ");
        tfYear = new JTextField(6);
        tfMonth = new JTextField(6);
        tfDay = new JTextField(6);
        JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pBirth.add(tfYear);
        pBirth.add(new JLabel("/"));
        pBirth.add(tfMonth);
        pBirth.add(new JLabel("/"));
        pBirth.add(tfDay);
        gbAdd(bBirth, 0,4,1,1);
        gbAdd(pBirth, 1, 4, 3,1);
       
        //�̸���
        JLabel bEmail = new JLabel("�̸��� : ");
        tfEmail = new JTextField(20);
        gbAdd(bEmail, 0,5,1,1);//0611
        gbAdd(tfEmail,1,5,3,1);//1631
        
        //����
        JLabel bGender = new JLabel("���� : ");
        JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMan = new JRadioButton("��",true);
        rbWoman = new JRadioButton("��",true);
        ButtonGroup group = new ButtonGroup();
        group.add(rbMan);
        group.add(rbWoman);
        pGender.add(rbMan);
        pGender.add(rbWoman);      
        gbAdd(bGender, 0,6,1,1);
        gbAdd(pGender,1,6,3,1);
       

        
        String header[] = {"å �̸�", "���� ��¥", "�ݳ� ��¥", "�̳���Ȳ"};
        lentModel = new DefaultTableModel(header, 0) {
            public boolean isCellEditable(int row, int column) {
                if(column >= 0) {
                   return false;
                }else {
                   return true;
                }
             }
          };; 
        lentTable = new JTable(lentModel);
        JScrollPane lentPane = new JScrollPane(lentTable);
        gbAdd(lentPane, 0, 8, 6, 6);
        
     
    }//������
    public void ExplainChange(member newMember) throws Exception
    {
    	nameLabel.setText(newMember.getName());
    	
        tfTel1.setText(newMember.getTel1());
        tfTel2.setText(newMember.getTel2());
        tfTel3.setText(newMember.getTel3());
        
        tfAddr.setText(newMember.getAddress());
        
        tfYear.setText(newMember.getYear());
        tfMonth.setText(newMember.getMonth());
        tfDay.setText(newMember.getDay());
        tfEmail.setText(newMember.getMail());
    	if (newMember.getAddress()== null)
        {
        	memberImg = display_member.imageChanger(new ImageIcon("./images/anyone.jpg"), 120, 130);	
        }
        else
        {
        	memberImg = display_member.imageChanger(new ImageIcon(newMember.getImgAddress()), 120, 130);
        }
    	memLabel.setIcon(memberImg);
    	
    	if (newMember.getLentList() != null)
    	{
    		for(int i=0; i<newMember.getLentList().size(); i++)
    		{
    			book tmpBook = null;
    			tmpBook = newMember.getLentList().get(i);
    			lentModel.addRow(tmpBook.getLentVector());
    		}
    		
    	}
    	
    }

	//�׸���鷹�̾ƿ��� ���̴� �޼ҵ�
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