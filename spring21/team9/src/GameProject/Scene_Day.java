package GameProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import GameProject.Scene.SCENE_TYPE;

public class Scene_Day extends Scene{

	public Scene_Day() {
		super("Day");
	}

	@Override
	public void GameObjInit() {
		//day text
		String dayText = "Day " + Scene.common_info.get("day");
		dayText = "<html><font color='white'>" + dayText + "</font></html>";
		GLabel daylabel = new GLabel(dayText, Main.WCENTER - 85, Main.HCENTER - 302, 250, 80, 60, frame);
		Addobj(daylabel);
		int old_day = (int)Scene.common_info.get("day");
		
		//save&exit button
		GButton save_exitBtn = new GButton("day_saveBtn_normal.png",
				"day_saveBtn_pressed.png", frame, Main.WCENTER - 450, Main.HEIGHT - 200, 160, 140,
				(event) -> {
					//score, day 정보 저장하기
					SaveInfo();
					System.exit(1);
				});
		Addobj(save_exitBtn);
		
		//next button
		GButton nextBtn = new GButton("day_nextBtn_normal.png",
				"day_nextBtn_pressed.png", frame, Main.WCENTER + 250, Main.HEIGHT - 200, 160, 140,
				(event) -> {
					main_Routine.GetInstance().NextScene(SCENE_TYPE.GAME);
				});
		Addobj(nextBtn);
		
		//ciriminal data generate
		EntrantData ciriminalData = null;
		if(!Scene.common_info.get("start").equals("load")) {
			EntrantsDataMgr datamgr =  (EntrantsDataMgr) Scene.common_info.get("datamgr");
			ciriminalData = datamgr.getRandomEntrant();
			Scene.common_info.put("criminal", ciriminalData);
		}
		else {
			Scene.common_info.put("start", "normal");
			ciriminalData = (EntrantData) Scene.common_info.get("criminal");
			Scene.common_info.put("criminal", ciriminalData);
		}
		
		//wanted image
		GImage wantedImage = new GImage("wanted.jpg", 
				Main.WCENTER - 150, Main.HEIGHT - 500, 300, 400);
		Addobj(wantedImage);
		
		//ciriminal portrait image
		GImage criminal_portrait = new GImage(ciriminalData.getName()+"_pass.jpg", 
				Main.WCENTER - 100, Main.HEIGHT - 385, 200, 267);
		Addobj(criminal_portrait);
	}
	
	private void SaveInfo() {
		File file = new File("saveInfo.txt");
		PrintWriter fout = null;
		try {
			fout = new PrintWriter(new FileOutputStream(file), true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String score = Scene.common_info.get("score").toString();
		String day = Scene.common_info.get("day").toString();
		fout.println(score + " " + day);
		fout.close();
		
		file = new File("criminalinfo");
		try {
			ObjectOutputStream fobjout = new ObjectOutputStream(new FileOutputStream(file));
			fobjout.writeObject(Scene.common_info.get("criminal"));
			fobjout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
