import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

//게임이 진행되는 화면
public class game_Frame extends JFrame implements KeyListener, Runnable
{
	int f_width;
	int f_height;

	int x, y;	//플레이어의 좌표값

	int by = 0;	//연속적으로 내려가는 배경이미지를 위한 배경의 y좌표값

	//키입력 상태를 받는 부분
	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;	
	
	//게임의 클리어 판정을 하는 부분
	boolean isDead = false;
	boolean isClear = false;

	int cnt;		//시나리오를 위한 타이머
	
	int firecnt;		// 미사일 연사 타이머 (유동값)
	int fire_Speed;		// 미사일 연사 속도 조절 변수 (고정값)
	int player_Speed;	// 유저의 캐릭터가 움직이는 속도를 조절할 변수
	int player_Damage;	// 유저의 공격력을 위한 변수
	int player_Hp;		// 플레이어 캐릭터의 체력
	
	int missile_Speed;	// 미사일이 날라가는 속도 조절할 변수
	int enemy_speed; 	// 적 이동 속도 설정
	
	int player_Status = 0;
	// 유저 캐릭터 상태 체크 변수
	//0 : 평상시
	//1: 미사일발사
	//2: 충돌
	
	int game_Score;		// 게임 점수 계산

	Thread th;
	Toolkit tk = Toolkit.getDefaultToolkit();
	//화면에 대한 정보를 받기 위해 사용한 변수

	Image Player_img;		// 플레이어 이미지
	Image Missile_img; 		// 미사일 이미지 변수
	Image BackGround_img;	// 배경 이미지
	Image[] Enemy_img; 		// 적이미지
	Image[] Explo_img;		// 폭발 이펙트를 위한 이미지

	ArrayList Missile_List = new ArrayList();	//미사일들을 저장하는 리스트
	ArrayList Enemy_List = new ArrayList();		//적들을 저장하는 리스트
	ArrayList Explosion_List = new ArrayList();	//폭발 이펙트를 저장하는 리스트

	Image buffImage;	//부하를 줄이기 위해 버퍼 이미지 사용
	Graphics buffg;	

	Missile ms;			// 미사일 클래스 접근 키
	Enemy en;			// 적 클래스 접근 키
	Explosion ex;		// 폭발 이펙트용 클래스 접근 키

	game_Frame()
	{
		init();		//기본 설정
		start();	//쓰레드 시작

		//화면 크기 및 위치 조정
		setTitle("슈팅 게임 만들기");
		setSize(f_width, f_height);
		
		Dimension screen = tk.getScreenSize();
		int f_xpos = (int) (screen.getWidth() / 2 - f_width / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - f_height / 2);

		setLocation(f_xpos, f_ypos);
		setResizable(false);
		setVisible(true);
	}

	//===============================================================================================================================
	//게임 시작 세팅 부분
	
	public void init()
	{
		f_width = 800;
		f_height = 800;
		
		x = f_width / 2;
		y = f_height * 3 / 4;

		Player_img = new ImageIcon(".\\img\\airplane_01_64x64.png").getImage();

		Missile_img = new ImageIcon(".\\img\\airplane_05_48x48_002.png").getImage();

		BackGround_img = new ImageIcon(".\\img\\back.png").getImage();

		Enemy_img = new Image[5];
		Enemy_img[0] = new ImageIcon(".\\img\\spaceship_003_64x64.png").getImage();
		Enemy_img[1] = new ImageIcon(".\\img\\spaceship_004_64x64.png").getImage();
		Enemy_img[2] = new ImageIcon(".\\img\\spaceship_005_64x64.png").getImage();
		Enemy_img[3] = new ImageIcon(".\\img\\spaceship_006_64x64.png").getImage();
		Enemy_img[4] = new ImageIcon((new ImageIcon(".\\img\\shmup_obj_boss\\enemy_boss.png")).getImage().getScaledInstance(240, 120, Image.SCALE_SMOOTH)).getImage(); 

		Explo_img = new Image[3];
		for (int i = 0; i < Explo_img.length; ++i) {
			Image img = new ImageIcon(".\\img\\explo.png").getImage(); // ImageIcon을 Image로 변환.
			Explo_img[i] = img.getScaledInstance(100 - (100 * i / Explo_img.length), 100 - (100 * i / Explo_img.length),
					java.awt.Image.SCALE_SMOOTH);
		}

		cnt = 1671;			// 게임 진행 시간 초기화
		firecnt = 0;		// 미사일 타이머 초기화
		game_Score = 0;		// 게임 스코어 초기화
		player_Hp = 3;		// 최초 플레이어 체력

		player_Speed = 5; 	// 유저 캐릭터 움직이는 속도 설정
		missile_Speed = 11; // 미사일 움직임 속도 설정
		fire_Speed = 15; 	// 미사일 연사 속도 설정
		enemy_speed = 7;	// 적이 날라오는 속도 설정
		
		player_Damage = 1;	// 유저 데미지 설정

	}

	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addKeyListener(this);
		th = new Thread(this);
		th.start();

	}

	public void run() {
		try {
			while (isDead == false && isClear == false)	//사망 혹은 클리어시 쓰레드 종료
			{
				KeyProcess();		// 키 입력 처리
				EnemyProcess();		// 적 관련 처리
				MissileProcess(); 	// 미사일 관련 처리 메소드 실행
				ExplosionProcess();	// 폭발 관련 메서드

				repaint();
				Thread.sleep(20);
				cnt++;
			}
		} catch (Exception e) {
		}
	}
	//===============================================================================================================================
	//처리 메서드 부분
	
	//키를 눌렸을 경우 상태 변경
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KeyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = true;
			break;

		case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가
			KeySpace = true;
			break;
		}
	}

	//키를 떼었을 경우 상태 변경
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KeyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = false;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = false;
			break;

		case KeyEvent.VK_SPACE: // 스페이스키 입력 처리 추가
			KeySpace = false;
			break;

		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void KeyProcess() {
		if (KeyUp == true && y - player_Speed > 0)
			y -= player_Speed;
		if (KeyDown == true && y + player_Speed + this.Player_img.getHeight(null) < this.getHeight())
			y += player_Speed;
		if (KeyLeft == true && x - player_Speed > 0)
			x -= player_Speed;
		if (KeyRight == true && x + player_Speed + this.Player_img.getWidth(null) < this.getWidth())
			x += player_Speed;

	}
	
	
	//미사일 처리 메서드
	public void MissileProcess()
	{

		if (KeySpace)							// 스페이스바 키 상태가 true 면
		{ 			
			player_Status = 1;					// 미사일을 발사하면 플레이어 캐릭터 상태를 1로 변경.
			if ((firecnt % fire_Speed) == 0)	// 플레이어의 미사일 연사속도를 조절한다.
			{			
				// 미사일 위치(x,y), 미사일 속도, 미사일 종류 (플레이어 마사일 : 0, 적 미사일 : 1)
				ms = new Missile(x + 8, y - 30, missile_Speed, 0);
				Missile_List.add(ms);
			}
			firecnt++;
		}
		else									// 스페이스 바 키 상태가 false면 0으로 초기화
			firecnt = 0;

		
		for (int i = 0; i < Missile_List.size(); ++i)
		{
			ms = (Missile) Missile_List.get(i);				// 각 미사일 개체에 접근
			ms.move();										// move() 메서드 호출
			
			if (ms.y > this.getHeight() + 10 || ms.y < 0) {	// 프레임 밖으로 나갈 경우 미사일 제거
				Missile_List.remove(i);
			}

			//플레이어가 적 미사일(who == 1)에 맞았을 경우
			if (Crash(x, y, ms.x, ms.y, Player_img, Missile_img) && ms.who == 1) {
				player_Hp--;
				if (player_Hp <= 0)
					Death();

				ex = new Explosion(x, y, 1);
				Explosion_List.add(ex);
				Missile_List.remove(i);
			}
			
			
			for (int j = 0; j < Enemy_List.size(); ++j)
			{
				int enemyIdx=0;		// 보스와 일반 적들의 크기가 달라 다른 이미지 인덱스를 사용하기 위한 변수
								// 일반 몬스터 : 0 ~ 3 ( 값이 같아서 0으로 사용)
								// 보스 몬스터 : 4
				en = (Enemy) Enemy_List.get(j);
				if(en.getClass() == Boss.class)
					enemyIdx =4;
				
				// 플레이어의 미사일이 적과 충돌했는지 판정
				if (Crash(ms.x, ms.y, en.x, en.y, Missile_img, Enemy_img[enemyIdx]) && ms.who == 0)
				{

					Missile_List.remove(i);
					
					en.hp -= player_Damage;
					if (en.hp <= 0)
					{
						Enemy_List.remove(j);
						
						if(en.getClass() == Boss.class)
						{
							game_Score += 200;
							Clear();
						}
						else
							game_Score += 10;
					}

					ex = new Explosion(en.x + Enemy_img[enemyIdx].getWidth(null) / 2, en.y + Enemy_img[enemyIdx].getHeight(null) / 2,0);

					Explosion_List.add(ex);
					break;		//적이 겹칠 경우, 제거한 객체의 접근을 막기 위한 구문
				}
			}
		}
	}

	
	private int crash_time=0;		//무적 시간 타이머
	private int loopcount = 1900;	//반복 시나리오 시 사용
	public void EnemyProcess()		/// 적 행동 처리 메소드
	{

		for (int i = 0; i < Enemy_List.size(); ++i)
		{
			en = (Enemy) (Enemy_List.get(i));
			en.move();
			en.cnt++;
			
			if (en.y > this.getHeight())	//화면 밖으로 나갈 경우 적을 제거
			{
				Enemy_List.remove(i);
			}

			if (en.cnt % 50 == 0 && en.getClass() == Enemy2.class)	// Enemy2클래스 일 경우 일정 시간마다 미사일 발사
			{
				ms = new Missile(en.x, en.y + 25, missile_Speed, 1);
				Missile_List.add(ms);
			}
			if(en.getClass() == Boss.class)							// 보스는 좌우 번갈아 미사일 발사
			{
				if (en.cnt % 80 == 0) {
					ms = new Missile(en.x, en.y + 25, missile_Speed, 1);
					Missile_List.add(ms);
				}
				if ((en.cnt) % 80 == 40) {
					ms = new Missile(en.x+180, en.y + 25, missile_Speed, 1);
					Missile_List.add(ms);
				}
			}
			
			//충돌 처리 부분
			int enemyIdx=0;
			if(en.getClass() == Boss.class) {
				enemyIdx=4;
			}
			if (Crash(x, y, en.x, en.y, Player_img, Enemy_img[enemyIdx]) && crash_time<=0)
			{
				crash_time =20;		// 20번 호출 될 때 까지 무적
				if(enemyIdx != 4) {
					Enemy_List.remove(i);
				}
				player_Hp--;
				
				if (player_Hp <= 0)
					Death();

				//적 폭발 이펙트
				ex = new Explosion(en.x + Enemy_img[enemyIdx].getWidth(null) / 2, en.y + Enemy_img[enemyIdx].getHeight(null) / 2, 0);
				Explosion_List.add(ex);

				//플레이어 폭발 이펙트
				ex = new Explosion(x, y, 1);
				Explosion_List.add(ex);
			}
			crash_time--;
		}

		
		// 시나리오 파트 (적 생성 부분)
		if (cnt % loopcount == 50) {
			int num = 8;
			for (int i = 0; i < num; ++i)
			{
				en = new Enemy1(this.getWidth() / num * i, -50);
				Enemy_List.add(en);
			}
		}
		if (cnt % loopcount == 300) {
			int num = 4;
			for (int i = 0; i < num; ++i)
			{
				en = new Enemy2(this.getWidth() / num * i, -50);
				Enemy_List.add(en);
			}
		}
		if (cnt % loopcount == 600) {
			int num = 2;
			for (int i = 0; i < num; ++i)
			{
				en = new Enemy3(-50, 80 * i, true);
				Enemy_List.add(en);
			}
			for (int i = 0; i < num; ++i)
			{
				en = new Enemy3(800, 80 * i, false);
				Enemy_List.add(en);
			}
		}
		if (cnt % loopcount == 800) {
			en = new Enemy1(350, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 820) {
			en = new Enemy1(250, -50);
			Enemy_List.add(en);
			en = new Enemy1(450, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 840) {
			en = new Enemy1(150, -50);
			Enemy_List.add(en);
			en = new Enemy1(550, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 860) {
			en = new Enemy1(50, -50);
			Enemy_List.add(en);
			en = new Enemy1(650, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1000) {
			int num = 2;
			for (int i = 0; i < num; ++i)
			{
				en = new Enemy3(80 * i, -50, true);
				Enemy_List.add(en);
			}
		}
		if (cnt % loopcount == 1015) {
			int num = 2;
			for (int i = 0; i < num; ++i)
			{
				en = new Enemy3(80 * i, -50, true);
				Enemy_List.add(en);
			}
		}
		if (cnt % loopcount == 1030) {
			int num = 2;
			for (int i = 0; i < num; ++i)
			{
				en = new Enemy3(80 * i, -50, true);
				Enemy_List.add(en);
			}
		}
		if (cnt % loopcount == 1180) {
			en = new Enemy2(50, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1200) {
			en = new Enemy2(150, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1220) {
			en = new Enemy2(250, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1240) {
			en = new Enemy2(350, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1260) {
			en = new Enemy2(450, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1280) {
			en = new Enemy2(550, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1300) {
			en = new Enemy2(650, -50);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1400) {
			int num = 2;
			for (int i = 0; i < num; ++i)
			{
				en = new Enemy3(350, 80 * i, true);
				Enemy_List.add(en);
			}
			for (int i = 0; i < num; ++i)
			{
				en = new Enemy3(350, 80 * i, false);
				Enemy_List.add(en);
			}
		}
		if (cnt % loopcount == 1550) {
			en = new Enemy3( 50,-50,true);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1570) {
			en = new Enemy3(150, -50,true);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1590) {
			en = new Enemy3(250, -50,true);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1610) {
			en = new Enemy3(350, -50,true);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1630) {
			en = new Enemy3(450, -50,true);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1650) {
			en = new Enemy3(550, -50,true);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == 1670) {
			en = new Enemy3(650, -50,true);
			Enemy_List.add(en);
		}
		if (cnt % loopcount == loopcount - 2) {
			en = new Boss(0, 100);
			Enemy_List.add(en);
			loopcount = 1;
		}

	}

	public void ExplosionProcess() {
		for (int i = 0; i < Explosion_List.size(); ++i) {
			ex = (Explosion) Explosion_List.get(i);
			ex.effect();
		}
	}

	//이미지 크기를 이용한 충돌함수
	public boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2) {

		boolean check = false;

		if (Math.abs((x1 + img1.getWidth(null) / 2) - (x2 + img2.getWidth(null) / 2)) < (img2.getWidth(null) / 2
				+ img1.getWidth(null) / 2)
				&& Math.abs((y1 + img1.getHeight(null) / 2)
						- (y2 + img2.getHeight(null) / 2)) < (img2.getHeight(null) / 2 + img1.getHeight(null) / 2)) {
			check = true;
		}
		else
		{
			check = false;
		}

		return check;
	}
	
	//===============================================================================================================================
	// 페인팅 부분
	
	// 각각의 이미지를 직접 그릴 시 코스트가 많이 들어 버퍼 이미지, 버퍼 그래픽을 사용
	// 사망, 클리어 판정 시 화면에 문구를 그려준다.
	public void paint(Graphics g) {

		if (isDead == false && isClear == false) {
			buffImage = createImage(f_width, f_height);
			buffg = buffImage.getGraphics();
			update(g);
		}
		else if (isClear == true) {

			buffg.setFont(new Font("Serif", Font.PLAIN, 50));
			buffg.setColor(Color.WHITE);

			buffg.drawString("Game Clear!!!", this.getWidth() / 2 - 160, this.getHeight() / 2 - 50);
			buffg.drawString("Score : " + (this.game_Score), this.getWidth() / 2 - 160,
					this.getHeight() / 2 + 50);
			g.drawImage(buffImage, 0, 0, this);

		}
		else if (isDead == true) {

			buffg.setFont(new Font("Serif", Font.PLAIN, 50));
			buffg.setColor(Color.WHITE);

			buffg.drawString("Game Over", this.getWidth() / 2 - 160, this.getHeight() / 2 - 50);
			buffg.drawString("Score : " + (this.game_Score), this.getWidth() / 2 - 160,
					this.getHeight() / 2 + 50);
			g.drawImage(buffImage, 0, 0, this);

		}
	}

	public void update(Graphics g) {

		Draw_Background();

		Draw_Char();

		Draw_Enemy();

		Draw_Missile();

		Draw_Explosion();
		
		Draw_StatusText();

		g.drawImage(buffImage, 0, 0, this);
	}

	// 배경을 그리는 부분
	// 화면 초기화 후 배경을 그려준다
	public void Draw_Background()
	{
		buffg.clearRect(0, 0, f_width, f_height);

		if (by < BackGround_img.getHeight(null)) {
			buffg.drawImage(BackGround_img, -1000, by - BackGround_img.getHeight(null), this);
			buffg.drawImage(BackGround_img, -1000, by, this);
			by += 1;
		}
		else
		{
			by = 0;
		}
	}

	// 플레이어를 그리는 부분
	public void Draw_Char() {
		buffg.drawImage(Player_img, x, y, this);
	}

	// 미사일을 그리는 부분
	public void Draw_Missile()
	{
		for (int i = 0; i < Missile_List.size(); ++i)
		{
			// 미사일 존재 유무를 확인한다.
			ms = (Missile) (Missile_List.get(i));
			// 미사일 위치값을 확인
			buffg.drawImage(Missile_img, ms.x, ms.y, this);
			// 현재 좌표에 미사일 그리기.
			// 이미지 크기를 감안한 미사일 발사 좌표는 수정됨.

		}
	}
	
	// 적을 그리는 부분
	public void Draw_Enemy()
	{
		for (int i = 0; i < Enemy_List.size(); ++i)
		{
			en = (Enemy) (Enemy_List.get(i));
			if (en.getClass() == Enemy1.class)
				buffg.drawImage(Enemy_img[0], en.x, en.y, this);
			if (en.getClass() == Enemy2.class)
				buffg.drawImage(Enemy_img[1], en.x, en.y, this);
			if (en.getClass() == Enemy3.class)
				buffg.drawImage(Enemy_img[2], en.x, en.y, this);
			if (en.getClass() == Boss.class)
				buffg.drawImage(Enemy_img[4], en.x, en.y, this);
			// 배열에 생성된 각 적을 판별하여 이미지 그리기
		}
	}
	
	//폭발 이펙트를 그리는 부분
	public void Draw_Explosion()
	{

		for (int i = 0; i < Explosion_List.size(); ++i)
		{
			ex = (Explosion) Explosion_List.get(i);
			// 폭발 이펙트의 존재 유무를 체크하여 리스트를 받음.

			if (ex.damage == 0)	// 설정값이 0 이면 맞췄을때 이미지 그리기
			{
				// 이미지를 순차적으로 그려준다.
				if (ex.ex_cnt < 7)
				{
					buffg.drawImage(Explo_img[0], ex.x - Explo_img[0].getWidth(null) / 2,
							ex.y - Explo_img[0].getHeight(null) / 2, this);
				}
				else if (ex.ex_cnt < 14)
				{
					buffg.drawImage(Explo_img[1], ex.x - Explo_img[1].getWidth(null) / 2,
							ex.y - Explo_img[1].getHeight(null) / 2, this);
				}
				else if (ex.ex_cnt < 21)
				{
					buffg.drawImage(Explo_img[2], ex.x - Explo_img[2].getWidth(null) / 2,
							ex.y - Explo_img[2].getHeight(null) / 2, this);
				}
				else if (ex.ex_cnt > 21)
				{
					Explosion_List.remove(i);
					ex.ex_cnt = 0;
				}
			}
			else	// 설정값이 1이면 단순 피격용 이미지 그리기
			{
				if (ex.ex_cnt < 7)
				{
					buffg.drawImage(Explo_img[0], ex.x + 8, ex.y, this);
				}
				else if (ex.ex_cnt < 14)
				{
					buffg.drawImage(Explo_img[1], ex.x + 8, ex.y + 5, this);
				}
				else if (ex.ex_cnt < 21)
				{
					buffg.drawImage(Explo_img[0], ex.x + 8, ex.y + 10, this);
				}
				else if (ex.ex_cnt > 21)
				{
					Explosion_List.remove(i);
					ex.ex_cnt = 0;
				}
			}
		}
	}

	// 각 상태 체크용 텍스트를 그려주는 부분
	public void Draw_StatusText()
	{
		buffg.setColor(Color.white);
		buffg.setFont(new Font("Defualt", Font.BOLD, 20));

		buffg.drawString("SCORE : " + game_Score, this.getWidth() - 200, 70);
		buffg.drawString("Time : " + cnt, this.getWidth() - 200, 90);
		buffg.drawString("HP : " + player_Hp, this.getWidth() - 200, 110);
		buffg.drawString("Missile Count : " + Missile_List.size(), this.getWidth() - 200, 130);
		buffg.drawString("Enemy Count : " + Enemy_List.size(), this.getWidth() - 200, 150);

	}

	//===============================================================================================================================
	// 클리어 판정 부분
	
	public void Death() {
		isDead = true;
		
		score_Frame scoreFrame = new score_Frame(this, game_Score, cnt);
		//dispose();

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		ImageIcon MenuIcon = new ImageIcon(".\\img\\restart.png");
		Image menuImage = MenuIcon.getImage(); // ImageIcon을 Image로 변환.
		Image changereplayImage = menuImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		MenuIcon = new ImageIcon(changereplayImage);
		JButton menuButton = new JButton("Menu");
		menuButton.setIcon(MenuIcon);
		menuButton.addActionListener(new endAction());
		buttonPanel.add(menuButton);

		ImageIcon exitIcon = new ImageIcon(".\\img\\exit.png");
		Image exitImage = exitIcon.getImage(); // ImageIcon을 Image로 변환.
		Image changeExitImage = exitImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		exitIcon = new ImageIcon(changeExitImage);
		JButton exitButton = new JButton("Exit");
		exitButton.setIcon(exitIcon);
		exitButton.addActionListener(new endAction());
		buttonPanel.add(exitButton);

		add(buttonPanel, BorderLayout.SOUTH);
		validate();
	}
	
	
	public void Clear() {
		isClear = true;
		
		score_Frame scoreFrame = new score_Frame(this, game_Score, cnt);
		//dispose();


		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		ImageIcon MenuIcon = new ImageIcon(".\\img\\restart.png");
		Image menuImage = MenuIcon.getImage(); // ImageIcon을 Image로 변환.
		Image changereplayImage = menuImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		MenuIcon = new ImageIcon(changereplayImage);
		JButton menuButton = new JButton("Menu");
		menuButton.setIcon(MenuIcon);
		menuButton.addActionListener(new endAction());
		buttonPanel.add(menuButton);

		ImageIcon exitIcon = new ImageIcon(".\\img\\exit.png");
		Image exitImage = exitIcon.getImage(); // ImageIcon을 Image로 변환.
		Image changeExitImage = exitImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		exitIcon = new ImageIcon(changeExitImage);
		JButton exitButton = new JButton("Exit");
		exitButton.setIcon(exitIcon);
		exitButton.addActionListener(new endAction());
		buttonPanel.add(exitButton);

		add(buttonPanel, BorderLayout.SOUTH);
		validate();
	}

	//===============================================================================================================================
	// 리스너 부분
	
	private class endAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCmd = e.getActionCommand();
			if (actionCmd.equals("Menu")) {
				// Start start = new Start();
				game_Frame game = new game_Frame();

				dispose();
			} else if (actionCmd.equals("Exit")) {
				dispose();
			}
		}

	}
}
