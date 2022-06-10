package GameProject;


public class Timer implements Runnable{
	private int remain_sec;
	private Thread thread;
	
	public Timer() {
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		remain_sec -= 1;
		if(remain_sec > 0) run();
	}
	
	public void Start(int sec) {
		remain_sec = sec;
		thread = new Thread(this);
		thread.start();
	}
	
	public int GetRemain() {
		return remain_sec;
	}
	
	public static Thread setTimeout(Runnable runnable, int delay) {
		Thread thread = new Thread(()->{
			try {
				Thread.sleep(delay);
				runnable.run();
			}catch(InterruptedException e) {
				System.err.println(e);
			}
		});
		thread.start();
		return thread;
	}
}
