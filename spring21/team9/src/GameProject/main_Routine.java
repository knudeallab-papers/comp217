package GameProject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import GameProject.Scene.SCENE_TYPE;

public class main_Routine extends JPanel{
	private Scene scene;
	private Image screenImage;
	private Graphics screenGraphic;
	
	private static class InnerClass{
		private static final main_Routine instance = new main_Routine();
	}
	
	public static main_Routine GetInstance() {
		return InnerClass.instance;
	}
	
	private main_Routine() {
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		setLayout(null);
		Scene.SetFrame(this);
		scene = Scene.MakeScene(SCENE_TYPE.INTRO);
		Scene.common_info.put("datamgr", new EntrantsDataMgr());
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.WIDTH, Main.HEIGHT);
		screenGraphic = screenImage.getGraphics();
		draw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void draw(Graphics2D g) {
		scene.Render(g);
		paintComponents(g);
	}
	
	public void NextScene(SCENE_TYPE scene_type) {
		scene.DestroyScene();
		scene = Scene.MakeScene(scene_type);
	}
}
