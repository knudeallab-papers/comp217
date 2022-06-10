package GameProject;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GPassport extends GameObject implements Renderable{
	private final int OFFX = 450;
	private final int OFFY = 100;
	
	private int stamp_x = OFFX+200;
	private int stamp_y = OFFY+100;
	private int stamp_width = 250;
	private int stamp_height = 125;
	
	private EntrantData data;
	Image passport_frame;
	Image portrait;
	Image stamp = null;

	
	public GPassport(EntrantData data) {
		super(Type.ETC);
		this.data = data;
		passport_frame = new ImageIcon(getClass().getClassLoader().getResource("passport.jpg")).getImage();
		
		portrait = new ImageIcon(getClass().getClassLoader().getResource(data.getName() +"_pass.jpg")).getImage();//mod 
	}
	
	@Override
	public void Render(Graphics2D g) {
		g.drawImage(passport_frame, OFFX, OFFY, 500,300, null);
		g.drawImage(portrait, OFFX+10, OFFY+70, 150,200, null);
		g.setFont(new Font("SanSerif", Font.PLAIN, 20));

		g.drawString("NAME:"+data.getName(), OFFX+180, OFFY+100);
		g.drawString("COUNTRY:"+data.getCountry(), OFFX+180, OFFY+130);
		g.drawString("CITY:"+data.getCity(), OFFX+180, OFFY+160);
		g.drawString("SEX:"+data.getSex(), OFFX+180, OFFY+190);
		
		if(stamp != null) {
			g.drawImage(stamp, stamp_x, stamp_y, stamp_width,stamp_height, null);
		}
	}
	
	public void SetStamp(Image stamp) {
		this.stamp = stamp;
	}
	
	public void SetStamp(Image stamp, boolean arrested) {
		this.stamp = stamp;
		if(arrested) {
			 stamp_x = OFFX+100;
			stamp_y = OFFY+70;
			stamp_width = 400;
			stamp_height = 300;
		}
	}
	
	public EntrantData getData() {
		return data;
	}
	
	@Override
	public void Destroy() {
	}
}
