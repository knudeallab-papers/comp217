package GameProject;

public class Scene_Ending extends Scene {
	private static final int PromotionScore = 20;
	public Scene_Ending() {
		super("End");
	}

	public void GameObjInit() {
		//background
		GImage background = new GImage("passport.jpg",0,0);
		//Addobj(background);

		//Ending text
		String ResultText = "<html><font color='white'>Result</font></html>";
		GLabel resultTag = new GLabel(ResultText, Main.WCENTER - 102, 20, 150, 50, 48, frame);
		Addobj(resultTag);
		
		int totalScore = (int)Scene.common_info.get("score");
		
		if(totalScore < 0)
			ResultText = "<html><br><font color='white'>SUSPENDED TO AOJI!!!</font></html>";
		else if(totalScore > PromotionScore) {
			ResultText = "<html><font color='white'>You have got promoted<br>Congratulations!</font></html>";
		}
		else {
			ResultText = "<html><font color='white'>You're ganna Fired</font></html>";
		}
		GLabel result = new GLabel(ResultText, 20, 20, Main.WIDTH - 300, 400, 40, frame);
		Addobj(result);
		
		//Main menu button
		GButton MainmenuBtn = new GButton("mainmenu_normal.png",
				"mainmenu_pressed.png", frame, Main.WCENTER - 100, Main.HEIGHT - 200, 160, 140,
				(event) -> {
					main_Routine.GetInstance().NextScene(SCENE_TYPE.INTRO);
				});
		Addobj(MainmenuBtn);

	}
}

