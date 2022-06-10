package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameManual extends JFrame implements ActionListener {
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	
	public GameManual() {
		setTitle("Game Manual");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(new Color(240, 217, 89));
		
		JButton Monorail = new JButton(new ImageIcon(GameSelect.class.getResource("/images/monorail.png")));
		JButton Checkers = new JButton(new ImageIcon(GameSelect.class.getResource("/images/checkers.png")));
		JButton Omok = new JButton(new ImageIcon(GameSelect.class.getResource("/images/omok.png")));
		
		Monorail.setBounds(20, 20, 245, 60);
		Checkers.setBounds(20, 100, 245, 60);
		Omok.setBounds(20, 180, 245, 60);
		
		Monorail.setActionCommand("Monorail");
		Checkers.setActionCommand("Checkers");
		Omok.setActionCommand("Omok");
		
		Monorail.addActionListener(this);
		Checkers.addActionListener(this);
		Omok.addActionListener(this);
		
		add(Monorail);
		add(Checkers);
		add(Omok);
		
		setVisible(true);
	}
	
	public class Manual extends JFrame {
		String game;
		
		private ImageIcon screen = new ImageIcon(GameSelect.class.getResource("/images/manualScreen.png"));
		
		private JTextArea manualDisplay;
		
		public Manual(String game) {
			this.game = game;
			setTitle( game + " Manual");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(600, 700);
			setLocationRelativeTo(null);
			setResizable(false);
			setLayout(null);
			
			/* Text Area */
			manualDisplay = new JTextArea("");
			manualDisplay.setBackground(new Color(233, 230, 213));
			manualDisplay.setFont(new Font("SanSerif", Font.BOLD, 15));
			manualDisplay.setEditable(false);
			
			JScrollPane scrolledText = new JScrollPane(manualDisplay);
			scrolledText.setBounds(178, 148, 380, 500);
			add(scrolledText);
			
			/* Game name Label */
			JLabel gameName = new JLabel();
			gameName.setFont(new Font("SanSerif", Font.BOLD, 36));
			gameName.setForeground(Color.WHITE);
			gameName.setBounds(280, 70, 250, 65);
			
			if (game.equals("Monorail")) {
				gameName.setText("'Monorail'");
				
				manualDisplay.setText("\r\n"
						+ "6개의 서로 다른 타일 모양 버튼을 눌러 놓을 타일의 모양을 \r\n"
						+ "바꿀 수 있다.\r\n"
						+ "그 아래의 방향키 버튼으로 놓을 타일의 위치를 바꿀 수 있으\r\n"
						+ "며 O 키를 눌러 놓고, X키를 눌러 놓은 타일을 삭제할 수 있다.\r\n"
						+ "Turn End 버튼을 눌러 놓은 타일들을 확정하면, 상대 턴으로 \r\n"
						+ "넘어가게 된다.\r\n"
						+ "Impossible 버튼을 눌러 불가능 선언을 할 수 있다.\r\n"
						+ "\r\n"
						+ "-모노레일 규칙-\r\n"
						+ "\r\n"
						+ "1. 두 플레이어는 양쪽 끝으로 철로가 열려있는 2개의\r\n"
						+ "  기차역 타일을 두고 게임을 시작한다.\r\n"
						+ "\r\n"
						+ "2. 플레이어들은 16개의 철로 타일을 번갈아가며 놓아\r\n"
						+ "  기차역에서 출발해 다시 기차역으로 돌아오는 하나의\r\n"
						+ "  순환선 철로를 완성시켜야 한다.\r\n"
						+ "\r\n"
						+ "3. 선 플레이어부터 번갈아가며 타일을 놓게 되며 자신의\r\n"
						+ "  차례에 1개에서 3개까지 놓을 수 있으며 타일을 놓는 \r\n"
						+ "  시간에 제한은 없다.\r\n"
						+ "\r\n"
						+ "4. 타일은 반드시 기존에 놓여있는 타일의 상, 하, 좌, 우 중\r\n"
						+ "  한 면에 맞닿아 놓아야 하며 반드시 철로가 연결되게 놓을\r\n"
						+ "  필요는 없다.\r\n"
						+ "\r\n"
						+ "5. 또한, 타일을 2개 이상 놓을 경우 타일을 나란히 일렬로\r\n"
						+ "  놓아야 한다.\r\n"
						+ "\r\n"
						+ "6. 철로를 완성하는 데 16개의 타일을 모두 사용할 필요는 \r\n"
						+ " 없으나 이미 놓인 철로는 모두 연결된 상태여야 한다.\r\n"
						+ "\r\n"
						+ "7. 같은 방식으로 번갈아가며 타일을 놓아 기차역에서\r\n"
						+ "  기차역으로 이어지는 철로를 완성하는 마지막 타일을 놓는\r\n"
						+ "  플레이어가 승리한다.\r\n"
						+ "\r\n"
						+ "8. 모노레일에는 승리 방법이 한 가지 더 존재한다. \r\n"
						+ "  게임 도중 자신의 턴 시작에 남아 있는 타일을 가지고\r\n"
						+ "  하나로 연결된 철로를 완성시킬 수 없다고\r\n"
						+ "  판단되면 '불가능'을 선언할 수 있다.\r\n"
						+ "\r\n"
						+ "9. 만약 한 플레이어가 '불가능'을 선언했을 경우\r\n"
						+ "  상대방은 남은 타일을 이용해 철로를 완성시켜야 한다.\r\n"
						+ "\r\n"
						+ "10. 남은 타일을 가지고 하나로 연결된 철로를 완성시켰을\r\n"
						+ "  경우 완성시킨 플레이어가 승리하게 되며 실패할 경우\r\n"
						+ "  '불가능'을 선언한 플레이어가 승리하게 된다.");
			}
			else if (game.equals("Checkers")) {
				gameName.setText("'Checkers'");
				
				manualDisplay.setText("\r\n"
						+ "어두운 칸과 밝은 칸이 가로 세로 여덟 칸씩 번갈아\r\n"
						+ "놓인 판에 양 편 각각 12개의 말을 써서 모로\r\n"
						+ "상대방의 말을 넘어서 다 잡거나 움직일 수 없도록\r\n"
						+ "만들어 승부를 겨루는 게임\r\n"
						+ "\r\n"
						+ "=플레이 방식=\r\n"
						+ "\r\n"
						+ "- 백색, 흑색 각각 12개의 말로 시작한다\r\n"
						+ "- 모든 말은 대각선으로밖에 이동할 수 없다.\r\n"
						+ "- 능력치 동일한 모든 말들을 Men이라 부르는데\r\n"
						+ "  대각선으로 '전진'만 할 수 있으며 \r\n"
						+ "  마지막 행에 도달하면 King으로 승급하고 \r\n"
						+ "  King은 대각선 네 방향으로 모두 이동 가능하다.\r\n"
						+ "- 만일 자신의 말 대각선 앞 칸에 상대의 말이 있고\r\n"
						+ "  그 다음 칸이 비었을 경우는 \r\n"
						+ "  그 말을 뛰어넘어서 반드시 잡아먹는다.\r\n"
						+ "  그것이 연속된 경우에는 연속해서 먹어야만 한다.\r\n"
						+ "- 먹을 수 있는 상대의 말이 없는 경우에는 자신의\r\n"
						+ "  이동가능한 모든 말을 움직일 수 있다.\r\n"
						+ "- 그렇게 해서 상대의 말을 모두 먹거나 움직이지\r\n"
						+ "  못 하게 만든 쪽이 승리한다.\r\n"
						+ "\r\n"
						+ "백색부터 게임을 시작하며,\r\n"
						+ "0. 각 턴마다 움직일 수 있는 말이 표시된다.\r\n"
						+ " (한 번 말을 클릭하면 다른 말로 선택을 바꿀 수\r\n"
						+ "  없으니 신중하게 클릭해야 한다.)\r\n"
						+ "1. 말을 클릭하면 그 말이 이동할 수 있는 칸이\r\n"
						+ "  표시된다.\r\n"
						+ "2. 한 편의 말이 모두 제거되거나 움직일 수 없게\r\n"
						+ "  되어 승자가 정해지면 게임을 다시 시작한다.");
			}
			else if (game.equals("Omok")) {
				gameName.setText("'Omok'");
				
				manualDisplay.setText("\n흑색(1P)돌 부터 시작해 19 x 19 크기의 바둑판에\r\n"
						+ "서로 번갈아가며 돌을 놓아\r\n"
						+ "먼저 5개의 돌을 이어지게 만드는 플레이어가\r\n"
						+ "승리하는 게임이다.");
			}
			add(gameName);
			
			JLabel screenLabel = new JLabel(screen);
			screenLabel.setBounds(0, 0, 600, 700);
			add(screenLabel);
			
			setVisible(true);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("Monorail")) {
			new Manual("Monorail");
		}
		else if (actionCmd.equals("Checkers")) {
			new Manual("Checkers");
		}
		else if (actionCmd.equals("Omok")) {
			new Manual("Omok");
		}
		
	}
	
	public static void main(String[] args) {
		new GameManual();
	}
}
