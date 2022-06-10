package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static View.FirstPage.*;

public class Floor3 extends JFrame implements ActionListener{

	private JLabel thirdStateText = new JLabel("");
	private JPanel thirdState;
	ImageIcon t1 = new ImageIcon("./img/t1.png"); //
	ImageIcon t2 = new ImageIcon("./img/t2.png"); //
	ImageIcon ele = new ImageIcon("./img/elevator.png");
	ImageIcon toi = new ImageIcon("./img/toi.png");
	ImageIcon underHall = new ImageIcon("./img/underHall.png");
	private JLabel stateLabel;
	public static Color emptyBlue = new Color(91,173,255);
	public static Color fullRed = new Color(255,74,74);

	public static void main(String[] args) {
		 new Floor3();
	}

	public Floor3(){

		super("3rd Floor");
		setSize(FirstPage.WIDTH,FirstPage.HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null); //화면 중앙
		setIconImage(knuLogo.getImage());


		thirdState = new JPanel();
		thirdState.setBackground(Color.WHITE);
		thirdState.setLayout(null);

		JMenu menu = new JMenu("Select");

		JMenuItem B1 = new JMenuItem("B1");
		B1.addActionListener(new MoveMenu());
		menu.add(B1);

		JMenuItem second = new JMenuItem("2F");
		second.addActionListener(new MoveMenu());
		menu.add(second);

		JMenuItem third = new JMenuItem("3F");
		third.addActionListener(new MoveMenu());
		menu.add(third);

		JMenuBar bar = new JMenuBar();
		bar.add(menu);

		setJMenuBar(bar);


		thirdStateText.setBounds(175,305,400,30);
		thirdState.add(thirdStateText);
//		thirdStateText.setFont(font.deriveFont(Font.BOLD, 12f));

		RoundedButton btn301 = new RoundedButton("301");
		btn301.setBounds(20, 20, 100, 130);
		btn301.addMouseListener(new mouseOnTheRoom());
		btn301.addActionListener(new makingTimeTableOnClick());
		if(isEmptyFloorRoom("301")) btn301.setBackground(emptyBlue);
		else btn301.setBackground(fullRed);
		thirdState.add(btn301);

		RoundedButton btn309 = new RoundedButton("309");
		btn309.addMouseListener(new mouseOnTheRoom());
		btn309.addActionListener(new makingTimeTableOnClick());
		btn309.setBounds(20, 160, 100, 130);
		if(isEmptyFloorRoom("309")) btn309.setBackground(emptyBlue);
		else btn309.setBackground(fullRed);

		thirdState.add(btn309);

		RoundedButton btn317 = new RoundedButton("Dept Office");
		btn317.setBounds(20, 300, 100, 100);
		thirdState.add(btn317);

		RoundedButton btn355 = new RoundedButton("355");
		btn355.setBounds(130, 20, 230, 100);
		btn355.addMouseListener(new mouseOnTheRoom());
		btn355.addActionListener(new makingTimeTableOnClick());
		thirdState.add(btn355);
		if(isEmptyFloorRoom("355")) btn355.setBackground(emptyBlue);
		else btn355.setBackground(fullRed);

		RoundedButton btn354 = new RoundedButton("354");
		btn354.setBounds(370, 20, 70, 100);
		thirdState.add(btn354);

		RoundedButton btn351 = new RoundedButton("351");
		btn351.setBounds(450, 20, 100, 90);
		btn351.addMouseListener(new mouseOnTheRoom());
		btn351.addActionListener(new makingTimeTableOnClick());
		if(isEmptyFloorRoom("351")) btn351.setBackground(emptyBlue);
		else btn351.setBackground(fullRed);
		thirdState.add(btn351);

		RoundedButton btn348 = new RoundedButton("348");
		btn348.setBounds(450, 120, 100, 90);
		btn348.addMouseListener(new mouseOnTheRoom());
		btn348.addActionListener(new makingTimeTableOnClick());
		if(isEmptyFloorRoom("348")) btn348.setBackground(emptyBlue);
		else btn348.setBackground(fullRed);
		thirdState.add(btn348);

		RoundedButton btn345 = new RoundedButton("345");
		btn345.setBounds(450, 220, 100, 90);
		btn345.addMouseListener(new mouseOnTheRoom());
		btn345.addActionListener(new makingTimeTableOnClick());
		if(isEmptyFloorRoom("345")) btn345.setBackground(emptyBlue);
		else btn345.setBackground(fullRed);
		thirdState.add(btn345);

		RoundedButton btn342 = new RoundedButton("342");
		btn342.setBounds(450, 320, 100, 90);
		btn342.addMouseListener(new mouseOnTheRoom());
		btn342.addActionListener(new makingTimeTableOnClick());
		if(isEmptyFloorRoom("342")) btn342.setBackground(emptyBlue);
		else btn342.setBackground(fullRed);
		thirdState.add(btn342);

		JLabel btntoi = new JLabel(toi);
		btntoi.setBounds(140, 140, 290, 150);
		thirdState.add(btntoi);

		JLabel hallLabel = new JLabel(underHall);
		hallLabel.setBounds(140, 340, 290, 150);
		thirdState.add(hallLabel);

//		JLabel btnele2 = new JLabel(ele);
//		btnele2.setBounds(290, 350, 60, 60);
//		thirdState.add(btnele2);

		add(thirdState);
		thirdState.setVisible(true);

		setVisible(true);



	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	private class MoveMenu implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()){
				case "B1":
					new FloorB1();
					setVisible(false);
					break;
				case "2F":
					new Floor2();
					setVisible(false);
					break;
			}
		}
	}

	public boolean isEmptyFloorRoom(String roomNum){
		return parsingClass.IsEmptyRoomBool(roomNum, nowDay, nowTime);
	}

	private class makingTimeTableOnClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			TimeTable tt = new TimeTable(e.getActionCommand(),parsingClass.getClassInfos());
			tt.setVisible(true);
		}
	}
	private class mouseOnTheRoom implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			String btnText = ((RoundedButton) e.getSource()).getText();
			stateLabel = new JLabel(parsingClass.IsEmptyRoom(btnText, nowDay, nowTime));
			thirdState.add(stateLabel);
			stateLabel.setVisible(true);
			thirdStateText.setText(parsingClass.IsEmptyRoom(btnText, nowDay, nowTime));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			thirdStateText.setText("");
			stateLabel.setText("");
		}
	}
}
