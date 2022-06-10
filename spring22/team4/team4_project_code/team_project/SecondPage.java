package team_project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SecondPage extends JFrame implements ActionListener {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final int NUMBER_OF_INSERT = 30;

	private JTextField nameField;
	private JTextField speciesField;
	private JTextField ageField;
	private JTextField itemField;
	private JTextField periodField;
	private JTextField importanceField;

	private JList toDoList;
	private ArrayList<String> addToList = new ArrayList<String>();
	private ArrayList<petData> petDataList = new ArrayList<petData>();
	public static final String srcPath = "src";
	public static final String packageName = "team_project";
	private String animalFilePath = "";
	private JLabel animalLabel;

	public static final int happiness = 30;

	private ArrayList<schedule> scheduleList = new ArrayList<schedule>();

	public SecondPage() {
		super("For register");

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

		readPetFileData();
		printPetList();

		JPanel TopPanel = new JPanel();
		TopPanel.setLayout(new GridLayout(1, 3));
		JPanel insertPanel = new JPanel();
		insertPanel.setLayout(new GridLayout(3, 1));
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(3, 1));

		JButton animalBtn = new JButton("image");
		animalBtn.addActionListener(this);
		animalLabel = new JLabel("");
		animalLabel.setLayout(null);

		animalBtn.setBounds(80, 20, 70, 30);

		animalLabel.add(animalBtn);

		TopPanel.add(animalLabel);

		JLabel name = new JLabel("이름 ");
		insertPanel.add(name);
		nameField = new JTextField("Insert name here.", NUMBER_OF_INSERT);
		nameField.setBackground(Color.WHITE);
		textPanel.add(nameField);

		JLabel species = new JLabel("종(강아지/고양이) ");
		insertPanel.add(species);
		speciesField = new JTextField("Insert species here.", NUMBER_OF_INSERT);
		speciesField.setBackground(Color.WHITE);
		textPanel.add(speciesField);

		JLabel age = new JLabel("나이  ");
		ageField = new JTextField("Insert age here.", NUMBER_OF_INSERT);
		ageField.setBackground(Color.WHITE);
		textPanel.add(ageField);
		insertPanel.add(age);

		TopPanel.add(insertPanel);
		TopPanel.add(textPanel);
		add(TopPanel, BorderLayout.NORTH);

		JPanel MidPanel = new JPanel();
		MidPanel.setLayout(new GridLayout(3, 1));
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(2, 3));

		JLabel items = new JLabel("관리 사항");
		labelPanel.add(items);
		JLabel period = new JLabel("주기");
		labelPanel.add(period);
		JLabel importance = new JLabel("중요도 1~30");

		labelPanel.add(importance);

		itemField = new JTextField("Insert item here.", NUMBER_OF_INSERT);
		itemField.setBackground(Color.WHITE);
		labelPanel.add(itemField);
		periodField = new JTextField("Insert period here.", NUMBER_OF_INSERT);
		periodField.setBackground(Color.WHITE);
		labelPanel.add(periodField);
		importanceField = new JTextField("Insert importance here.", NUMBER_OF_INSERT);
		importanceField.setBackground(Color.WHITE);
		labelPanel.add(importanceField);

		MidPanel.add(labelPanel);

		toDoList = new JList();

		JScrollPane toDoScroll = new JScrollPane(toDoList);

		MidPanel.add(toDoScroll);
		add(MidPanel, BorderLayout.CENTER);

		JPanel BottomPanel = new JPanel();
		BottomPanel.setLayout(new GridLayout(2, 1));
		JPanel btnPanel = new JPanel();
		JPanel btnPanel2 = new JPanel();
		btnPanel.setLayout(new FlowLayout());

		JButton plus = new JButton("+");
		plus.addActionListener(this);
		btnPanel.add(plus);

		JButton minus = new JButton("-");
		minus.addActionListener(this);
		btnPanel.add(minus);

		JButton register = new JButton("등록 ");
		register.addActionListener(this);
		btnPanel2.add(register);
		JButton cancel = new JButton("취소 ");
		cancel.addActionListener(this);
		btnPanel2.add(cancel);

		BottomPanel.add(btnPanel);
		BottomPanel.add(btnPanel2);
		add(BottomPanel, BorderLayout.SOUTH);

	}

	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();

		if (actionCmd.equals("+")) {

			if (periodField.getText().matches("-?\\d+") && importanceField.getText().matches("-?\\d+")) {
				schedule newSchedule = new schedule(itemField.getText(), Integer.parseInt(periodField.getText()),
						Integer.parseInt(importanceField.getText()));
				String newTodo = "\"" + itemField.getText() + "\"을(를) ";
				newTodo = newTodo + " \"" + periodField.getText() + "\"일 주기로 할거에요.  중요도는 ";

				newTodo = newTodo + " \"" + importanceField.getText() + "\"이에요.";

				itemField.setText("");
				periodField.setText("");
				importanceField.setText("");
				addToList.add(newTodo);

				Vector<String> v = new Vector<String>();

				for (int i = 0; i < addToList.size(); i++)
					v.addElement(addToList.get(i));

				scheduleList.add(newSchedule);
				toDoList.setListData(v);
			} else
				petKNUDemo.errorMsg("Input Error");

		}

		else if (actionCmd.equals("-")) {

			if (addToList.size() > 0 && toDoList.getSelectedIndex() >= 0) {
				addToList.remove(toDoList.getSelectedIndex());
				scheduleList.remove(toDoList.getSelectedIndex());

				Vector<String> v = new Vector<String>();

				for (int i = 0; i < addToList.size(); i++)
					v.addElement(addToList.get(i));

				toDoList.setListData(v);

			}

		}

		else if (actionCmd.equals("등록 ")) {

			if (ageField.getText().matches("-?\\d+")) {

				petData tempData = new petData(nameField.getText(), speciesField.getText(), ageField.getText(),
						scheduleList, animalFilePath, happiness);

				tempData.updateMission();

				petDataList.add(tempData); // Field입력 정보 데이터에 저장

				try {
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("arrayFile.dat"));

					outputStream.writeObject(petDataList);
					outputStream.close();
				}

				catch (IOException e1) {
					System.err.println("Error writing to file.");
					System.exit(0);
				}

				printPetList();

				petKNUDemo.closeRegisterPage();
				petKNUDemo.openFirstPage();
			}

			else
				petKNUDemo.errorMsg("Input Error");

		}

		else if (actionCmd.equals("image")) {
			animalFilePath = FileChoose.jFileChooserUtil();
			ImageIcon animalIcon = new ImageIcon(animalFilePath);

			Image OriginalImg = animalIcon.getImage();
			Image ReducedImg = OriginalImg.getScaledInstance(50, 68, Image.SCALE_SMOOTH);
			ImageIcon ReducedIcon = new ImageIcon(ReducedImg);
			animalLabel.setIcon(ReducedIcon);

		}

		else if (actionCmd.equals("취소 ")) {
			this.dispose();
			petKNUDemo.openFirstPage();
		}
	}

	public void printPetList() // print pet ArrayList
	{
		for (petData a : petDataList) {
			a.printPetData();
		}
	}

	public void readPetFileData() // load from saved .dat file
	{
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("arrayFile.dat"));
			petDataList = (ArrayList<petData>) inputStream.readObject();

			inputStream.close();
		} catch (FileNotFoundException e1) {
			System.err.println("Cannot find file arrayfile.(there's no saved data)");
		} catch (ClassNotFoundException e1) {
			System.err.println("Problems with file input.`1");
		} catch (IOException e1) {
			System.err.println("Problems with file input.2");
		}
	}

}