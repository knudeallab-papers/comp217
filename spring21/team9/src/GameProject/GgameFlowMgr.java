package GameProject;

import java.awt.Container;

import GameProject.Scene.SCENE_TYPE;

public class GgameFlowMgr extends GameObject 
implements Movable{
	public static final int SECINDAY = 30;
	public static final int MAXDAY = 3;

	private int curday;
	private Timer timer;
	private GLabel timer_text;	//ref
	private Scene game_scene; 	//ref
	private EntrantsDataMgr entrantsDataMgr; //ref
	
	public GgameFlowMgr(Scene scene) {
		super(Type.ETC);
	//	curday = 1;
		timer = new Timer();
		timer.Start(SECINDAY);
		game_scene = scene;
		entrantsDataMgr = (EntrantsDataMgr)Scene.common_info.get("datamgr");
		
		GEntrant entrant = new GEntrant(entrantsDataMgr.getRandomEntrant(), scene, this);
		game_scene.Addobj(entrant);
	}
	
	public void SetTimerText(GLabel label) {
		timer_text = label;
	}
	
	@Override
	public void Progress() {
		try {
			TimeCheck();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void TimeCheck() {
		if((int)Scene.common_info.get("day") >= MAXDAY) {
			main_Routine.GetInstance().NextScene(SCENE_TYPE.ENDING);
		}
		else {
			int remain = timer.GetRemain();
			if(remain <= 0) {
				Scene.common_info.put("day", (int)(Scene.common_info.get("day"))+1);
				main_Routine.GetInstance().NextScene(SCENE_TYPE.DAY);	
			}
			String str = Scene.common_info.get("day") + " Day, " + Integer.toString(remain);
			str = "<html><font color='white'>"+str+"</font></html>";
			timer_text.resetText(str);
		}
	}
	
	public void GenerateNewEntrant() {
		GEntrant entrant = new GEntrant(entrantsDataMgr.getRandomEntrant(), game_scene, this);
		game_scene.Addobj(entrant);
	}

	@Override
	public void Destroy() {
	}
}
