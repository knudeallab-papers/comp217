package GameProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Scene_Intro extends Scene {
	public Scene_Intro() {
		super("Intro");
	}
	Scene_Help help_frame;
	@Override
	public void GameObjInit() {
		//background
		GImage background = new GImage("intro_Background.png",0,0);
		Addobj(background);
		
		//start button
		GButton startBtn = new GButton("intro_startBtn_normal.png",
				"intro_startBtn_pressed.png", frame, 50, 50, 160, 80,
				(event) -> {
					Scene.common_info.put("score", 0);
					Scene.common_info.put("day", 1);
					Scene.common_info.put("start", "normal");
					
					main_Routine.GetInstance().NextScene(SCENE_TYPE.DAY);
				});
		Addobj(startBtn);
		
		//end button
		GButton endBtn = new GButton("intro_endBtn_normal.png",
				"intro_endBtn_pressed.png", frame, 50, 210, 110, 80,
				(event) -> {
					System.exit(1);
				});
		Addobj(endBtn);
		
		//load button
		GButton loadBtn = new GButton("intro_loadBtn_normal.png",
				"intro_loadBtn_pressed.png", frame, 50, 130, 140, 80,
				(event) -> {
					Scene.common_info.put("start", "load");
					boolean isExist = LoadInfo();
					if(isExist)
						main_Routine.GetInstance().NextScene(SCENE_TYPE.DAY);
				});
		Addobj(loadBtn);
		
		//help button
		GButton helpBtn = new GButton("intro_helpBtn_normal.png",
				"intro_helpBtn_pressed.png", frame, 930, 50, 40, 80,
				(event) -> {
					help_frame = new Scene_Help();
				});
		Addobj(helpBtn);
		
		//intro music
		GMusic introMusic = new GMusic("intro.mp3", true);
		Addobj(introMusic);
		introMusic.Play();
	}
	
	private boolean LoadInfo()  {
		Scanner fin = null;
		try {
			fin = new Scanner(new FileInputStream("saveinfo.txt"));
		} catch (FileNotFoundException e) {
			return false;
		}
		
		int score = fin.nextInt();
		int day = fin.nextInt();
		
		Scene.common_info.put("score", score);
		Scene.common_info.put("day", day);
		
		fin.close();
		
		try {
			ObjectInputStream fobjout = new ObjectInputStream(new FileInputStream("criminalinfo"));
			EntrantData criminalData = (EntrantData)fobjout.readObject();
			common_info.put("criminal", criminalData);
			fobjout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}