package GameProject;

import java.awt.Image;

import javax.swing.ImageIcon;

public class GJudge extends GameObject{
	private static final int CORRECT_SCORE = 20;
	
	private Scene scene;
	
	private GButton accept_btn;
	private GButton deny_btn;
	private GEntrant judge_objective;
	private Boolean clicked = false;
	
	private GgameFlowMgr game_mgr; //ref

	public GJudge(Scene scene, GEntrant judge_objective, GgameFlowMgr game_mgr) {
		super(Type.ETC);
		this.scene = scene;
		this.judge_objective = judge_objective;
		this.game_mgr = game_mgr;
		
		//accept button
		accept_btn = new GButton("accept_normal.jpg",
				"accept_pressed.jpg", scene.frame, 450, 450, 200, 75,
				(event) -> {
					if(clicked) return;
					JudgeEntrant("accept");
					clicked = true;
				});
		
		//denied button
		deny_btn = new GButton("denied_normal.jpg",
				"denied_pressed.jpg", scene.frame, 700, 450, 200, 75,
				(event) -> {
					if(clicked) return;
					JudgeEntrant("deny");
					clicked = true;
				});
		
		scene.Addobj(accept_btn);
		scene.Addobj(deny_btn);
	}
	
	public void JudgeEntrant(String judge) {
		String fileName = "accept_pressed.jpg";
		boolean match = judge_objective.IsMathWithPassPort();
		
		EntrantData criminalData = (EntrantData) Scene.common_info.get("criminal");
		boolean isCriminal = judge_objective.getData().getName().equals(criminalData.getName());
		boolean arrest = false;
		
		int old_score = (int)Scene.common_info.get("score");
		
		if(judge.equals("accept")) {
			fileName = "accept_pressed.jpg";
			
			if(isCriminal)	Scene.common_info.put("score", old_score - CORRECT_SCORE * 3);
			else if(match) Scene.common_info.put("score", old_score + CORRECT_SCORE);
			else Scene.common_info.put("score", old_score - CORRECT_SCORE);
		}
		else if(judge.equals("deny")) {
			fileName = "denied_pressed.jpg";
			
			if(isCriminal)	{
				arrest = true;
				Scene.common_info.put("score", old_score + CORRECT_SCORE * 3);
				fileName = "arrested.jpg";
			}
			else if(!match) Scene.common_info.put("score", old_score + CORRECT_SCORE);
			else Scene.common_info.put("score", old_score - CORRECT_SCORE);
		}
		
		GPassport pass = judge_objective.getPassport();
		Image stampImage = new ImageIcon(getClass().getClassLoader().getResource(fileName)).getImage();
		if(arrest) pass.SetStamp(stampImage, true);
		else pass.SetStamp(stampImage);
		
		scene.AddThread(Timer.setTimeout(() -> scene.RemoveObj(judge_objective), 950));
		scene.AddThread(Timer.setTimeout(() -> scene.RemoveObj(judge_objective.getPassport()), 950));
		scene.AddThread(Timer.setTimeout(() -> game_mgr.GenerateNewEntrant(), 1000));
		
		System.out.println("score : " + (int)Scene.common_info.get("score"));
		
		scene.RemoveObj(this);
	}

	@Override
	public void Destroy() {
		accept_btn.Destroy();
		deny_btn.Destroy();
	}
	
}
