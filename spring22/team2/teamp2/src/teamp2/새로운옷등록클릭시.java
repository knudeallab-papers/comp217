package teamp2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



public class 새로운옷등록클릭시 extends JFrame implements ActionListener {

 private JPanel contentPane;
 private static String season;
 private static String cloth_var;
 JPanel outputimg;
 JButton 파일저장버튼;
 BufferedImage im;
 int cnt = 0;
 int cnt2 = 0;
 private JButton 파일선택버튼;
 JFileChooser jfile;
 File selectedFile =null;
 String ID_value;
 JLabel 저장완료 = new JLabel("저장 완료!");
 String address;
 int cloth_int;
 
 int check1=0;
 int check2=0;
 int check3=0;
 int check4=0;
 
 int check;
 String[] seasonlist= {"봄/가을", "여름", "겨울"};
 String[] clothlist= {"모자", "상의","하의","신발"};
 String[] stylelist= {"캐주얼", "로맨틱","하의","신발"};
 String 선택된파일이름;
 int 선택된옷종류;
 
 @SuppressWarnings("unchecked")
JComboBox 계절선택콤보박스 = new JComboBox(seasonlist);
 @SuppressWarnings("unchecked")
JComboBox 옷종류선택콤보박스 = new JComboBox(clothlist);
 
 
 JRadioButton 캐주얼라디오버튼 = new JRadioButton("캐주얼");
 JRadioButton 로맨틱라디오버튼 = new JRadioButton("로맨틱");
 JRadioButton 출근라디오버튼 = new JRadioButton("출근");
 JRadioButton 하객라디오버튼= new JRadioButton("하객");

String[] 스타일선택된값=new String[10];
JLabel 프로필갱신알람 = new JLabel("새로운 프로필은 재구동시 적용됩니다");
 
 public 새로운옷등록클릭시(String ID_value,String address,int check) 
 {
	 
	 
	 this.address= address;
	 this.ID_value=ID_value;
	 this.check=check;
	 
  setTitle("새로운 옷 등록하기");
  if(check==0)
  {
	  setTitle("새로운 프로필 등록하기");
  }
  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  setBounds(100, 100, 446, 294);
  contentPane = new JPanel();
  contentPane.setBackground(Color.WHITE);
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
  contentPane.setLayout(null);

  
  outputimg = new ImageP();
  outputimg.setBounds(10, 10, 400, 400);
  contentPane.add(outputimg);

  파일저장버튼 = new 둥근버튼("파일저장");
  파일저장버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
  파일저장버튼.setBounds(325, 42, 97, 23);
  파일저장버튼.addActionListener(this);
  contentPane.add(파일저장버튼);
  contentPane.add(get파일선택버튼());
  

  계절선택콤보박스.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
  계절선택콤보박스.setBounds(199, 10, 114, 23);
  contentPane.add(계절선택콤보박스);
  
  
  
  
  옷종류선택콤보박스.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
  옷종류선택콤보박스.setBounds(199, 42, 114, 23);
  contentPane.add(옷종류선택콤보박스);
  
  if(check==0)
  {
	  계절선택콤보박스.setVisible(false);
	  옷종류선택콤보박스.setVisible(false);
  }
  
  
  저장완료.setHorizontalAlignment(SwingConstants.CENTER);
  저장완료.setFont(new Font("휴먼편지체", Font.PLAIN, 30));
  저장완료.setBounds(225, 183, 171, 57);
  contentPane.add(저장완료);
  저장완료.setVisible(false);
  
  
  프로필갱신알람.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
  프로필갱신알람.setBounds(199, 188, 211, 57);
  contentPane.add(프로필갱신알람);
  
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  캐주얼라디오버튼.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		if(check1==0)
  		{
  			스타일선택된값[++cnt]="캐주얼";
  			check1=cnt;
  		}
  		else if(check1!=0)
  		{
  			스타일선택된값[check1]="";
  			check1=0;
  		}
  		
  	}
  });
  캐주얼라디오버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
  캐주얼라디오버튼.setBackground(Color.WHITE);
  캐주얼라디오버튼.setBounds(199, 71, 61, 23);
  contentPane.add(캐주얼라디오버튼);
  
  
  로맨틱라디오버튼.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		if(check2==0)
  		{
  			스타일선택된값[++cnt]="로맨틱";
  			check2=cnt;
  		}
  		else if(check2!=0)
  		{
  			스타일선택된값[check2]="";
  			check2=0;
  		}
  	}
  });
  로맨틱라디오버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
  로맨틱라디오버튼.setBackground(Color.WHITE);
  로맨틱라디오버튼.setBounds(199, 96, 61, 23);
  contentPane.add(로맨틱라디오버튼);
  
  
  
  출근라디오버튼.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		if(check3==0)
  		{
  			스타일선택된값[++cnt]="출근";
  			check3=cnt;
  		}
  		else if(check3!=0)
  		{
  			스타일선택된값[check3]="";
  			check3=0;
  		}
  		
  	}
  });
  출근라디오버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
  출근라디오버튼.setBackground(Color.WHITE);
  출근라디오버튼.setBounds(199, 121, 61, 23);
  contentPane.add(출근라디오버튼);
  
  하객라디오버튼 = new JRadioButton("하객");
  하객라디오버튼.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		if(check4==0)
  		{
  			스타일선택된값[++cnt]="출근";
  			check4=cnt;
  		}
  		else if(check4!=0)
  		{
  			스타일선택된값[check4]="";
  			check4=0;
  		}
  	}
  });
  하객라디오버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
  하객라디오버튼.setBackground(Color.WHITE);
  하객라디오버튼.setBounds(199, 146, 61, 23);
  contentPane.add(하객라디오버튼);
  

  
  if(check==0)
  {
	  캐주얼라디오버튼.setVisible(false);
	  로맨틱라디오버튼.setVisible(false);
	  출근라디오버튼.setVisible(false);
	  하객라디오버튼.setVisible(false);
  }
  
  
  JPanel panel = new JPanel();
  panel.setBackground(Color.LIGHT_GRAY);
  panel.setBounds(186, 10, 1, 235);
  contentPane.add(panel);
  
  프로필갱신알람.setVisible(false);
  저장완료.setVisible(false);
  저장완료.setVisible(false);
  if(check==0)
  {
	  프로필갱신알람.setVisible(true);
  }
 }

 public JButton get파일선택버튼() {
	 
	 
	 if (파일선택버튼 == null) {	  
   파일선택버튼 = new 둥근버튼("\uD30C\uC77C\uC120\uD0DD");
   파일선택버튼.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
   파일선택버튼.setBounds(325, 10, 97, 23);
   파일선택버튼.addActionListener(this);
   파일선택버튼.addMouseListener(new MouseAdapter() {

	   
	   
    @Override
    public void mouseClicked(MouseEvent arg0) {
     // TODO Auto-generated method stub
    	
    	
     			
     super.mouseClicked(arg0);
     jfile=new JFileChooser(".../Desktop");
     jfile.setDialogTitle("새로운 옷 등록하기");
     FileFilter filter =new FileNameExtensionFilter(".png","png");
     jfile.addChoosableFileFilter(filter);
    
     
     
     int status =jfile.showOpenDialog(contentPane);

     if (status == JFileChooser.APPROVE_OPTION)
     {
      selectedFile =jfile.getSelectedFile(); 
      선택된파일이름=selectedFile.getName();
     }
         
     
     try {
      
      im = ImageIO.read(new File(selectedFile.getParent()+"\\"+selectedFile.getName()));
      ImageP imp=new ImageP();
      imp.repaint();
     } catch (IOException e) 
     {
       
     }
   }
 

  
  });
}
  return 파일선택버튼;
 }

 public void actionPerformed(ActionEvent e) {

	 cnt2=1;
	 
	 if (e.getSource() == 파일저장버튼) {
	  
		 프로필갱신알람.setVisible(false);
		 season=계절선택콤보박스.getSelectedItem().toString();
	 	
	 	if(season.equals("봄/가을"))
	 	{
	 		season="봄가을";
	 	}
	 	cloth_var=옷종류선택콤보박스.getSelectedItem().toString();
	     
	     if(cloth_var.equals("모자"))
	     {
	    	 cloth_int=1;
	     }
	     else if(cloth_var.equals("상의"))
	     {
	    	 cloth_int=2;
	     }
	     else if(cloth_var.equals("하의"))
	     {
	    	 cloth_int=3;
	     }
	     else
	     {
	    	 cloth_int=4;
	     }
	  	 String 최종스타일 = "";
	     for(int j=1;j<=cnt;j++)
	   	 {
	   		 최종스타일+=스타일선택된값[j];
	   		 System.out.println(스타일선택된값[j]);
	   	 }	
	     
	     String 중간파일이름= 선택된파일이름.replace(".PNG","");
	     String 최종파일이름= 중간파일이름.replace(".png","");
	     String input= 최종파일이름+" "+season+" "+최종스타일;
	     
	     
	//옷종류나타내는값(cloth_int), 파일이름값(선택된파일이름) 계절값(season), 캐주얼로맨틱포멀하객값(스타일선택된값)
	if(check==1)
	{
		//새로운 옷을 추가할때
		address="./src/teamp2/program/"+ID_value+"/cloth/"+cloth_int+"/"+선택된파일이름;
		
		//여기다가 이제 새로 txt 추가하는거 넣어야함
		
		try(FileWriter fw = new FileWriter("./src/teamp2/program/"+ID_value+"/cloth/"+cloth_int+"/"+cloth_int+".txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{				
				out.println(input);
			    
			} catch (IOException e1) {
			    //exception handling left as an exercise for the reader
			}
	}
	else if(check==0)
	{
		//새로운 프로필을 추가할때
		address="./src/teamp2/program/"+ID_value+"/profile.png";
		File file3 = new File(address);
    	file3.delete();
	}
	 

   try {
	   
    im = ImageIO.read(selectedFile);
    if (new File(address).exists() == false)// 바탕화면에 이 파일이 존재한다면
    {
     File file = new File(address); //새로운 파일만들고
     file.createNewFile();
    }
    OutputStream out = new FileOutputStream(new File(address)); // 파일출력스트림 생성
    ImageIO.write(im, "png", out); // 이미지 출력
    out.close(); // 출력스트림 닫기

   } catch (IOException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
   }

   repaint();
   저장완료.setVisible(true);

  }

 }

 public class ImageP extends JPanel {

  private static final long serialVersionUID = 1232451532361342351L;

  public ImageP() {

  }

  public void paintComponent(Graphics g) {
   if (cnt2 == 1 && im != null) {

    BufferedImage newImage = new BufferedImage(240, 240, BufferedImage.TYPE_INT_RGB);
    Graphics gg = newImage.getGraphics();
    gg.setColor(Color.white); 
    gg.fillRect(0, 0, 240, 240); 
    Graphics2D g2d = (Graphics2D) gg;
    g2d.scale(0.2, 0.2);

    g2d.drawImage(im, 0, 0, this);

    Graphics2D g2dd = (Graphics2D) g;
    g2dd.scale(0.3, 0.3);
    g2dd.drawImage(im, 0, 0, this);

   
   }
  
  }

  
 }
 
} 