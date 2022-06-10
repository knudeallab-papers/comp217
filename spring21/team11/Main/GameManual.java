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
						+ "6���� ���� �ٸ� Ÿ�� ��� ��ư�� ���� ���� Ÿ���� ����� \r\n"
						+ "�ٲ� �� �ִ�.\r\n"
						+ "�� �Ʒ��� ����Ű ��ư���� ���� Ÿ���� ��ġ�� �ٲ� �� ����\r\n"
						+ "�� O Ű�� ���� ����, XŰ�� ���� ���� Ÿ���� ������ �� �ִ�.\r\n"
						+ "Turn End ��ư�� ���� ���� Ÿ�ϵ��� Ȯ���ϸ�, ��� ������ \r\n"
						+ "�Ѿ�� �ȴ�.\r\n"
						+ "Impossible ��ư�� ���� �Ұ��� ������ �� �� �ִ�.\r\n"
						+ "\r\n"
						+ "-��뷹�� ��Ģ-\r\n"
						+ "\r\n"
						+ "1. �� �÷��̾�� ���� ������ ö�ΰ� �����ִ� 2����\r\n"
						+ "  ������ Ÿ���� �ΰ� ������ �����Ѵ�.\r\n"
						+ "\r\n"
						+ "2. �÷��̾���� 16���� ö�� Ÿ���� �����ư��� ����\r\n"
						+ "  ���������� ����� �ٽ� ���������� ���ƿ��� �ϳ���\r\n"
						+ "  ��ȯ�� ö�θ� �ϼ����Ѿ� �Ѵ�.\r\n"
						+ "\r\n"
						+ "3. �� �÷��̾���� �����ư��� Ÿ���� ���� �Ǹ� �ڽ���\r\n"
						+ "  ���ʿ� 1������ 3������ ���� �� ������ Ÿ���� ���� \r\n"
						+ "  �ð��� ������ ����.\r\n"
						+ "\r\n"
						+ "4. Ÿ���� �ݵ�� ������ �����ִ� Ÿ���� ��, ��, ��, �� ��\r\n"
						+ "  �� �鿡 �´�� ���ƾ� �ϸ� �ݵ�� ö�ΰ� ����ǰ� ����\r\n"
						+ "  �ʿ�� ����.\r\n"
						+ "\r\n"
						+ "5. ����, Ÿ���� 2�� �̻� ���� ��� Ÿ���� ������ �Ϸķ�\r\n"
						+ "  ���ƾ� �Ѵ�.\r\n"
						+ "\r\n"
						+ "6. ö�θ� �ϼ��ϴ� �� 16���� Ÿ���� ��� ����� �ʿ�� \r\n"
						+ " ������ �̹� ���� ö�δ� ��� ����� ���¿��� �Ѵ�.\r\n"
						+ "\r\n"
						+ "7. ���� ������� �����ư��� Ÿ���� ���� ����������\r\n"
						+ "  ���������� �̾����� ö�θ� �ϼ��ϴ� ������ Ÿ���� ����\r\n"
						+ "  �÷��̾ �¸��Ѵ�.\r\n"
						+ "\r\n"
						+ "8. ��뷹�Ͽ��� �¸� ����� �� ���� �� �����Ѵ�. \r\n"
						+ "  ���� ���� �ڽ��� �� ���ۿ� ���� �ִ� Ÿ���� ������\r\n"
						+ "  �ϳ��� ����� ö�θ� �ϼ���ų �� ���ٰ�\r\n"
						+ "  �ǴܵǸ� '�Ұ���'�� ������ �� �ִ�.\r\n"
						+ "\r\n"
						+ "9. ���� �� �÷��̾ '�Ұ���'�� �������� ���\r\n"
						+ "  ������ ���� Ÿ���� �̿��� ö�θ� �ϼ����Ѿ� �Ѵ�.\r\n"
						+ "\r\n"
						+ "10. ���� Ÿ���� ������ �ϳ��� ����� ö�θ� �ϼ�������\r\n"
						+ "  ��� �ϼ���Ų �÷��̾ �¸��ϰ� �Ǹ� ������ ���\r\n"
						+ "  '�Ұ���'�� ������ �÷��̾ �¸��ϰ� �ȴ�.");
			}
			else if (game.equals("Checkers")) {
				gameName.setText("'Checkers'");
				
				manualDisplay.setText("\r\n"
						+ "��ο� ĭ�� ���� ĭ�� ���� ���� ���� ĭ�� ������\r\n"
						+ "���� �ǿ� �� �� ���� 12���� ���� �Ἥ ���\r\n"
						+ "������ ���� �Ѿ �� ��ų� ������ �� ������\r\n"
						+ "����� �ºθ� �ܷ�� ����\r\n"
						+ "\r\n"
						+ "=�÷��� ���=\r\n"
						+ "\r\n"
						+ "- ���, ��� ���� 12���� ���� �����Ѵ�\r\n"
						+ "- ��� ���� �밢�����ιۿ� �̵��� �� ����.\r\n"
						+ "- �ɷ�ġ ������ ��� ������ Men�̶� �θ��µ�\r\n"
						+ "  �밢������ '����'�� �� �� ������ \r\n"
						+ "  ������ �࿡ �����ϸ� King���� �±��ϰ� \r\n"
						+ "  King�� �밢�� �� �������� ��� �̵� �����ϴ�.\r\n"
						+ "- ���� �ڽ��� �� �밢�� �� ĭ�� ����� ���� �ְ�\r\n"
						+ "  �� ���� ĭ�� ����� ���� \r\n"
						+ "  �� ���� �پ�Ѿ �ݵ�� ��ƸԴ´�.\r\n"
						+ "  �װ��� ���ӵ� ��쿡�� �����ؼ� �Ծ�߸� �Ѵ�.\r\n"
						+ "- ���� �� �ִ� ����� ���� ���� ��쿡�� �ڽ���\r\n"
						+ "  �̵������� ��� ���� ������ �� �ִ�.\r\n"
						+ "- �׷��� �ؼ� ����� ���� ��� �԰ų� ��������\r\n"
						+ "  �� �ϰ� ���� ���� �¸��Ѵ�.\r\n"
						+ "\r\n"
						+ "������� ������ �����ϸ�,\r\n"
						+ "0. �� �ϸ��� ������ �� �ִ� ���� ǥ�õȴ�.\r\n"
						+ " (�� �� ���� Ŭ���ϸ� �ٸ� ���� ������ �ٲ� ��\r\n"
						+ "  ������ �����ϰ� Ŭ���ؾ� �Ѵ�.)\r\n"
						+ "1. ���� Ŭ���ϸ� �� ���� �̵��� �� �ִ� ĭ��\r\n"
						+ "  ǥ�õȴ�.\r\n"
						+ "2. �� ���� ���� ��� ���ŵǰų� ������ �� ����\r\n"
						+ "  �Ǿ� ���ڰ� �������� ������ �ٽ� �����Ѵ�.");
			}
			else if (game.equals("Omok")) {
				gameName.setText("'Omok'");
				
				manualDisplay.setText("\n���(1P)�� ���� ������ 19 x 19 ũ���� �ٵ��ǿ�\r\n"
						+ "���� �����ư��� ���� ����\r\n"
						+ "���� 5���� ���� �̾����� ����� �÷��̾\r\n"
						+ "�¸��ϴ� �����̴�.");
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
