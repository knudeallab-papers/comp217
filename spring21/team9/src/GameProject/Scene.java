package GameProject;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;


public abstract class Scene {
	public enum SCENE_TYPE{
		INTRO,
		GAME,
		ENDING,
		DAY
	}
	
	public static HashMap<String, Object> common_info = new HashMap<String, Object>();
	public static JPanel frame;
	
	protected String SceneName;
	protected ArrayList<GameObject> gObjects = new ArrayList<GameObject>();
	
	//Renderable interface를 상속한 object에 대한 reference를 따로 가지고 있음
	protected ArrayList<Renderable> gRenderable = new ArrayList<Renderable>();
	protected ArrayList<Movable> gMovable = new ArrayList<Movable>();
	protected ArrayList<Thread> gThread = new ArrayList<Thread>();
	
	//movable object의 progress를 주기적으로 실행시켜주기 위한 thread
	private ScheduledExecutorService scheduleService = 
			Executors.newScheduledThreadPool(1);
	
	private Runnable frameAction = new Runnable() {
		@Override
		public void run() {
			FrameAction();
		}
	};
	
	public Scene(String name) {
		SceneName = name;
	}
	public static void SetFrame(JPanel fra) {
		frame = fra;
	}

	public void Addobj(GameObject obj) {
		gObjects.add(obj);
		
		if(obj instanceof Renderable) {
			gRenderable.add((Renderable)obj);
		}
		if(obj instanceof Movable) {
			gMovable.add((Movable)obj);
		}
	}
	
	public void AddThread(Thread thread) {
		gThread.add(thread);
	}
	
	public synchronized void RemoveObj(GameObject obj) {
		try {
			gObjects.remove(obj);
			
			if(obj instanceof Renderable) {
				gRenderable.remove(obj);
			}
			if(obj instanceof Movable) {
				gMovable.remove(obj);
			}
			
			obj.Destroy();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Render(Graphics2D g) {
		for(int i = 0; i < gRenderable.size(); i++) {
			gRenderable.get(i).Render(g);
		}
	}
	
	static public Scene MakeScene(SCENE_TYPE type) {
		Scene newScene;
		
		switch(type) {
		case INTRO:
			newScene = new Scene_Intro();
			break;
		case GAME:
			newScene = new Scene_Game();
			break;
		case ENDING:
			newScene = new Scene_Ending();
			break;
		case DAY:
			newScene = new Scene_Day();
			break;
		default:
			newScene = new Scene_Intro();
			break;
		}
		
		newScene.GameObjInit();
		newScene.scheduleService.scheduleAtFixedRate(newScene.frameAction, 100, 10, TimeUnit.MILLISECONDS);
		return newScene;
	}
	
	public void FrameAction() {
		for(int i = 0; i < gMovable.size(); i++) {
			gMovable.get(i).Progress();
		}
	}
	
	public void DestroyScene() {
		 for(int i = 0; i < gThread.size(); i++) {
			 gThread.get(i).interrupt();
		 }
		
		 Iterator<GameObject> iter = gObjects.iterator();
		 while(iter.hasNext()) {
			 GameObject obj = iter.next();
			 if(obj != null)	obj.Destroy();
		 }
		 
		 gObjects.clear();
		 gRenderable.clear();
		 gMovable.clear();
		 gThread.clear();
	}
	
	
	public abstract void GameObjInit();
}
