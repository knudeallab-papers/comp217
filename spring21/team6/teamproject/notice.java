package teamproject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class notice extends JFrame implements ActionListener{
	public static final int WIDTH = 300;
	public static final int HEIGHT = 150;
	private int page;
	JFrame comp = new JFrame();
	JPanel notice = new JPanel();
	private boolean check = false;
	private int cnt;
	
	public notice() {
		comp.setSize(WIDTH,HEIGHT);
		comp.setBackground(Color.WHITE);
		comp.setLayout(new FlowLayout());
		comp.setTitle("Notice");
		
		notice.setLayout(new GridLayout(2,1));
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("파일이 존재합니다. 덮어쓰겠습니까?");
		
		JButton submit = new JButton("확인");
		submit.addActionListener(this);
		submit.setBackground(Color.white);
		btnPanel.add(submit);
		
		JButton no = new JButton("취소");
		no.addActionListener(this);
		no.setBackground(Color.white);
		btnPanel.add(no);
		
		notice.add(label);
		notice.add(btnPanel);
		
		comp.add(notice);

	}

	public static void main(String[] args) {
		notice panGui = new notice();
		panGui.comp.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("확인")) {
			cnt = 1;
		}
		
		if(actionCommand.equals("취소")) {
			cnt = 2;
			JOptionPane.showMessageDialog(null, "다른 이름을 입력해 주세요.", "Notice", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public int getCnt() {
		return cnt;
	}

}
