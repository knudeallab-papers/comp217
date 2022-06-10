package team_project;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.time.LocalDate;
import java.util.ArrayList;

public class ThirdPage extends JFrame implements ActionListener {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int NUMBER_OF_INSERT = 30;

	public static final String srcPath = "src";
	public static final String packageName = "team_project";

	private String nameInfo = "";
	private String speciesInfo = "";
	private String ageInfo = "";
	private int happinessInfo = 30;

	private petData item;
	ObjectInputStream BfileIStream;
	ArrayList<petData> Array = new ArrayList<petData>(); // ArrayList 생성
	ArrayList<schedule> Petschedule = new ArrayList<schedule>();
	JList PetList = new JList(new DefaultListModel());
	DefaultListModel Model;

	public ThirdPage(ArrayList<petData> list, String petName) {
		super("Information");

		this.item = null;
		for (petData a : list) {
			if (a.getName().equals(petName)) {
				this.item = a;
			}
		}

		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.GRAY);
		setLayout(new BorderLayout());

		String currentProjPath = "";
		try {
			currentProjPath = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JPanel TopPanel = new JPanel();
		TopPanel.setLayout(new GridLayout(1, 3));
		JPanel insertPanel = new JPanel();
		insertPanel.setLayout(new GridLayout(4, 1));
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(4, 1));

		JLabel animalLabel = new JLabel("");
		ImageIcon animalIcon = new ImageIcon(item.getFilePath()); // Array에서 가져온 path를 통해 사진 불러오기

		Image OriginalImg = animalIcon.getImage(); // 원본 이미지
		Image ReducedImg = OriginalImg.getScaledInstance(120, 160, Image.SCALE_SMOOTH); // 이미지 축소하기
		ImageIcon ReducedIcon = new ImageIcon(ReducedImg);
		animalLabel.setIcon(ReducedIcon); // 축소한 이미지 출력

		animalLabel.setBounds(0, 0, 30, 30);
		TopPanel.add(animalLabel);

		nameInfo = item.getName();
		JLabel name = new JLabel("이름 ");
		insertPanel.add(name);
		JLabel nameField = new JLabel(nameInfo);
		textPanel.add(nameField);

		speciesInfo = item.getSpecies();
		JLabel species = new JLabel("종(강아지/고양이) ");
		insertPanel.add(species);
		JLabel speciesField = new JLabel(speciesInfo);
		textPanel.add(speciesField);

		ageInfo = item.getAge();
		JLabel age = new JLabel("나이  ");
		insertPanel.add(age);
		JLabel ageField = new JLabel(ageInfo);
		textPanel.add(ageField);

		JPanel MidPanel = new JPanel();
		MidPanel.setLayout(null);

		JPanel missionPanel = new JPanel();
		missionPanel.setLayout(null);
		LocalDate now = LocalDate.now();
		JButton dateBtn = new JButton("오늘의 날짜는 " + now);
		dateBtn.setBounds(80, 50, 450, 65);
		MidPanel.add(dateBtn);

		int changedY = 0;
		Petschedule = item.getSchedule();
		int updateHappiness = 0;
		for (int i = 0; i < Petschedule.size(); i++) {
			updateHappiness += Petschedule.get(i).refresh();

			String str = "";
			if (Petschedule.get(i).getDeadLine() == 1) {
				str = nameInfo + "의 " + Petschedule.get(i).getScheduleName() + "은 "
						+ Petschedule.get(i).getNextDate().toString() + "전까지, " + "오늘 해야 해요! 달성했나요 ?";
			}

			else {
				int deadLine = Petschedule.get(i).getDeadLine() - 1;
				str = nameInfo + "의 " + Petschedule.get(i).getScheduleName() + "은 "
						+ Petschedule.get(i).getNextDate().toString() + "전까지, " + deadLine + "일 남았어요 ! 달성했나요 ?";
			}

			JButton scheduleBtn = new JButton(str);
			scheduleBtn.addActionListener(this);
			scheduleBtn.setActionCommand(Integer.toString(i));
			changedY = 120 + i * 70;
			scheduleBtn.setBounds(80, changedY, 450, 65);
			MidPanel.add(scheduleBtn);
		}

		if (speciesInfo.equals("강아지")) {
			for (int i = 0; i < list.size(); i++) {
				if (petName.equals(list.get(i).getName())) {

					int a = list.get(i).getMission().get(0).refresh();
					int b = list.get(i).getMission().get(1).refresh();


					if (a != 0)
						list.get(i).updateMission();
					this.item = list.get(i);
					updateHappiness += a + b;

					break;
				}
			}
			JPanel walkPanel = new JPanel();
			walkPanel.setLayout(null);

			JButton walkMission = new JButton("오늘의 미션은 " + item.getMission().get(0).getScheduleName() + " 달성했나요 ?");
			walkMission.setBounds(80, changedY + 70, 450, 65);
			MidPanel.add(walkMission);

			walkMission.setActionCommand("mission1");
			walkMission.addActionListener(this);

			JButton walkDistance = new JButton(item.getMission().get(1).getScheduleName());
			walkDistance.setActionCommand("mission2");
			walkDistance.addActionListener(this);
			walkDistance.setBounds(80, changedY + 140, 450, 65);
			MidPanel.add(walkDistance);
		}

		try {
			ObjectOutputStream BfileOStream = new ObjectOutputStream(new FileOutputStream("arrayFile.dat"));
			BfileOStream.writeObject(list);
		} catch (IOException e) {
			System.out.println("File does not exist.");
		}

		for (int i = 0; i < list.size(); i++) {
			if (petName.equals(list.get(i).getName())) {
				list.get(i).setHappiness(list.get(i).getHappiness() + updateHappiness);
			}
		}
		happinessInfo = item.getHappiness();

		String emotion = "";
		if (happinessInfo >= 0 && happinessInfo < 30) {
			emotion = happinessInfo + " 😭️";
		} else if (happinessInfo >= 30 && happinessInfo < 60) {
			emotion = happinessInfo + " 🙂";
		} else if (happinessInfo >= 60 && happinessInfo < 90) {
			emotion = happinessInfo + " 😃";
		} else if (happinessInfo >= 90) {
			emotion = happinessInfo + " 😍";
		}
		JLabel happiness = new JLabel("행복도 ");
		insertPanel.add(happiness);
		JLabel happinessField = new JLabel(emotion);
		textPanel.add(happinessField);

		TopPanel.add(insertPanel);
		TopPanel.add(textPanel);
		add(TopPanel, BorderLayout.NORTH);

		add(MidPanel, BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("mission1"))
			petKNUDemo.openForthPage(item, item.getMission().get(0));
		else if (actionCmd.equals("mission2"))
			petKNUDemo.openForthPage(item, item.getMission().get(1));
		else
			petKNUDemo.openForthPage(item, item.getSchedule().get(Integer.parseInt(actionCmd)));

	}

}
