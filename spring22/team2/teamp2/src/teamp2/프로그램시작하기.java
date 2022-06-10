package teamp2;

public class 프로그램시작하기 {
	
	public static void main(String[] args) {
		
		
		로그인화면 start = new 로그인화면();
		//어플홈화면 start = new 어플홈화면("dd");
		
		while(true) 
		{
		bgmplay bgm_player = new bgmplay();
		
		try {
			bgm_player.play_bgm();
			Thread.sleep(30700000);
		} catch(Exception e) {
			
		}
	}
	}
}
