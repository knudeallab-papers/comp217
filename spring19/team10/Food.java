import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Food extends JFrame implements ActionListener{
	
	ArrayList<String> a= new ArrayList<String>();
	public Food() {	
		super("food menu");
		setSize(400,400);
		setLocation(500,280);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		JButton m = new JButton("문화관");
		m.addActionListener(this);
		m.setActionCommand("m");
		JButton c = new JButton("첨성관");
		c.addActionListener(this);
		c.setActionCommand("c");
		
		JPanel pan = new JPanel();
		pan.setLayout(null);
		
		m.setBounds(100,100,150,50);
		c.setBounds(100,200,150,50);
		
		pan.add(m);
		pan.add(c);
		
		add(pan);
	}
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		String url = "http://dorm.knu.ac.kr/_new_ver/";
		if( s.equals("m"))
		{	
			try {
				Document doc = Jsoup.connect(url).get();
			
				//System.out.println(doc.toString());
				//Elements links = doc.select("tr");
				Elements links = doc.getElementsByClass("txt_right");
				for( Element link : links)
				{
					a.add(link.text());
				}
			}catch(IOException g){
				g.printStackTrace();
			}
			JFrame n = new JFrame("문화관");
			n.setSize(800, 400);
			n.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			n.setVisible(true);
			n.setLocation(500,280);
			n.setLayout(null);
				
			JLabel f1 = new JLabel("아침 : "+a.get(0));
			f1.setBounds(50,50,1500,50);
			n.add(f1);
			JLabel f2 = new JLabel("점심 : "+a.get(1));
			f2.setBounds(50,150,1500,50);
			n.add(f2);
			JLabel f3 = new JLabel("저녁 : "+a.get(2));
			f3.setBounds(50,250,1500,50);
			n.add(f3);
			
		}
		else if( s.equals("c"))
		{
			try {
				Document doc = Jsoup.connect(url).get();
			
				//System.out.println(doc.toString());
				//Elements links = doc.select("tr");
				Elements links = doc.getElementsByClass("txt_right");
				for( Element link : links)
				{
					a.add(link.text());
				}
			}catch(IOException g){
				g.printStackTrace();
			}
			JFrame n = new JFrame("첨성관");
			n.setSize(800, 400);
			n.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			n.setVisible(true);
			n.setLocation(500,280);
			n.setLayout(null);
			
			JLabel f1 = new JLabel("아침 : "+a.get(3));
			f1.setBounds(50,50,1500,50);
			n.add(f1);
			JLabel f2 = new JLabel("점심 : "+a.get(4));
			f2.setBounds(50,150,1500,50);
			n.add(f2);
			JLabel f3 = new JLabel("저녁 : "+a.get(5));
			f3.setBounds(50,250,1500,50);
			n.add(f3);
		}
	}
}
