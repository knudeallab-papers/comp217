package GameProject;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GEntrant extends GameObject implements Renderable, Movable{
	public static int XPOS = 20;
	public static int YPOS = 10;
	public static int WIDTH = 100;
	public static int HEIGTH = 200;
	
	private EntrantData data;
	private Image image;
	private double scale = 1.0;
	private double vert_coeff = 0.0;
	private boolean arrive = false;
	private GPassport passport;
	
	private Scene scene;	//ref
	private GgameFlowMgr game_mgr;
	private EntrantsDataMgr entrantsdatamgr;
	
	public GEntrant(EntrantData data, Scene game_scene, GgameFlowMgr game_mgr) {
		super(Type.ETC);
		this.data = new EntrantData(data);
		this.game_mgr = game_mgr;
		image = new ImageIcon(getClass().getClassLoader().getResource(data.getName() + "_real.jpg")).getImage();
		scene = game_scene;
		entrantsdatamgr = (EntrantsDataMgr) Scene.common_info.get("datamgr");
	}
	
	public boolean IsMathWithPassPort() {
		EntrantData passport_data = passport.getData();
		if(!data.getName().equals(passport_data.getName())){
			return false;
		}
		
		return true;
	}
	
	public void Progress() {
		if(arrive) return;
		if(scale >= 3) {
			arrive = true;
			
			GPassport pass = new GPassport(entrantsdatamgr.getRandomPass(data));//mod
			GJudge judge = new GJudge(scene, this, game_mgr);
			
			passport = pass;
			scene.Addobj(pass);
			scene.Addobj(judge);
			
			return;
		}
		scale += 0.01;
		vert_coeff += 0.1;
	}
	
	public void Render(Graphics2D g) {
		double ymove = Math.cos(vert_coeff);
		g.drawImage(image, XPOS, (int)(YPOS + ymove * 10),
				(int)(WIDTH * scale), (int)(HEIGTH * scale), null);
	}
	
	public EntrantData getData() {
		return new EntrantData(data);
	}
	
	public GPassport getPassport() {
		return passport;
	}
	
	public void Destroy() {}
}
