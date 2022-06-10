package team_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class FirstPage extends JFrame implements ActionListener, ListSelectionListener {

	private String title = "myPet KNU";
	private Font titlefont = new Font("SanSerif", Font.BOLD, 64);
	private int BtnWidth = 140;
	private int BtnHeight = 45;
	ArrayList<String> PetName = new ArrayList<String>();
	DefaultListModel Model;
	CardLayout card = new CardLayout();
	JButton PetRegist = new JButton("New Pet");
	JButton SelectBtn = new JButton("Select Pet");
	JButton DeleteBtn = new JButton("Delete Pet");
	JPanel UpperPanel = new JPanel();
	JLabel UpperText = new JLabel("Pet List");
	JPanel ListPanel = new JPanel(card);
	JList PetList = new JList(new DefaultListModel());
	JPanel PetInfo = new JPanel();
	JLabel PetInfoText = new JLabel();
	ArrayList<petData> Array = new ArrayList<petData>(); // ArrayList 생성
	JPanel MidPanel = new JPanel(new BorderLayout());
	JLabel PetImage = new JLabel();
	JScrollPane ListScroll;
	int count = 0;
	ObjectInputStream BfileIStream;
	JLabel TimeShow = new JLabel();

	public FirstPage() {
		// TODO Auto-generated constructor stub
		super("");
		setSize(600, 600);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		addBtn();
		PetLsitSet();
		readFrombfile();
	}

	public void addBtn() {
		PetRegist.setSize(BtnWidth, BtnHeight);
		SelectBtn.setSize(BtnWidth, BtnHeight);
		DeleteBtn.setSize(BtnWidth, BtnHeight);
		PetRegist.setLocation(50, 150);
		SelectBtn.setLocation(205, 150);
		DeleteBtn.setLocation(360, 150);

		add(PetRegist);
		PetRegist.addActionListener(this);
		add(SelectBtn);
		SelectBtn.addActionListener(this);
		add(DeleteBtn);
		DeleteBtn.addActionListener(this);
	}

	public void PetLsitSet() {
		UpperPanel.setBackground(Color.WHITE);
		UpperPanel.setBounds(40, 210, 470, 20);
		UpperPanel.add(UpperText);

		ListPanel.setBounds(40, 230, 470, 270);
		ListPanel.setLayout(new GridLayout(0, 3));
		ListPanel.add(PetList);
		ListScroll = new JScrollPane(PetList);
		ListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		;
		ListPanel.add(ListScroll);
		MidPanel.setBackground(Color.GRAY);
		MidPanel.add(PetImage, BorderLayout.CENTER);
		ListPanel.add(MidPanel);
		PetInfo.setBackground(Color.WHITE);
		PetInfo.setLayout(new BorderLayout());
		PetInfo.add(PetInfoText, BorderLayout.WEST);
		ListPanel.add(PetInfo);

		TimeShow.setBounds(40, 500, 470, 30);
		TimeShow.setText(LocalDate.now().toString());

		add(UpperPanel);
		add(ListPanel);
		add(TimeShow);

		PetList.addListSelectionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (actionCommand.equals("New Pet")) {
			petKNUDemo.closeFirstPage();
			petKNUDemo.openRegisterPage();

		} else if (actionCommand.equals("Select Pet")) {

			String SelectedName = PetList.getSelectedValue().toString();

			if (SelectedName.isEmpty()) {
				System.out.println("No selected option.");
			}

			petKNUDemo.openInfoPage(Array, SelectedName);

		} else if (actionCommand.equals("Delete Pet")) {
			DeletePet();
		} else {
		}
		
	}

	public void paint(Graphics g) {
		super.paint(g);

		g.setFont(titlefont);
		g.setColor(Color.BLACK);
		g.drawString(title, 115, 150);
		;
	}

	// 바이너리 파일 읽는 메소드
	public void readFrombfile() {
		try {
			BfileIStream = new ObjectInputStream(new FileInputStream("arrayFile.dat"));

			Array = (ArrayList<petData>) BfileIStream.readObject(); // 바이너리 파일로부터 ArrayList 읽어오기
			PetName.clear();
			// ArrayList로부터 petData 객체 하나씩 읽어들이기
			for (int i = 0; i < Array.size(); i++) {
				PetName.add(Array.get(i).getName());
			}
			Model = (DefaultListModel) PetList.getModel(); // 기존 JList을 읽어들임
			Model.removeAllElements(); // JList 초기화
			// JList 최신화
			for (int i = 0; i < Array.size(); i++) {
				Model.addElement(PetName.get(i));
			}

		} catch (IOException e) {
			System.out.println("File does not exist.");
		} catch (ClassNotFoundException e) {
			System.out.println("No data exist.");
		}
	}

	public void DeletePet() {
		try {
			BfileIStream = new ObjectInputStream(new FileInputStream("arrayFile.dat"));

			Array = (ArrayList<petData>) BfileIStream.readObject(); // 바이너리 파일로부터 ArrayList 읽어오기
			int index = PetList.getSelectedIndex();

			Model = (DefaultListModel) PetList.getModel(); // 기존 JList을 읽어들임
			Model.remove(index);
			Array.remove(index);

		} catch (IOException e) {
			System.out.println("File does not exist.");
		} catch (ClassNotFoundException e) {
			System.out.println("No data exist.");
		}

		try {
			ObjectOutputStream BfileOStream = new ObjectOutputStream(new FileOutputStream("arrayFile.dat"));
			BfileOStream.writeObject(Array);
		} catch (IOException e) {
			System.out.println("File does not exist.");
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int index = PetList.getSelectedIndex();

			try {
				BfileIStream = new ObjectInputStream(new FileInputStream("arrayFile.dat"));

				Array = (ArrayList<petData>) BfileIStream.readObject();
				if (index < 0) {
					PetInfoText.setText("");
				} else {
					petData p = Array.get(index);
					String text = "<html><body>Name : " + p.getName() + "<br>Species : " + p.getSpecies() + "<br>Age : "
							+ p.getAge() + "<body><html>";

					ArrayList<schedule> slist = p.getSchedule();
					if (CheckSchedule(slist)) {
						text += "<html><body><br>오늘 해야할 일정:<br><body><html>";
					}
					for (int i = 0; i < p.getSchedule().size(); i++) {
						if (slist.get(i).getDeadLine() <= 1) {
							text += slist.get(i).getScheduleName() + " ";
						}
					}
					PetInfoText.setText(text);
					ImageIcon animalIcon = new ImageIcon(p.getFilePath()); // Array에서 가져온 path를 통해 사진 불러오기

					Image OriginalImg = animalIcon.getImage(); // 원본 이미지
					Image ReducedImg = OriginalImg.getScaledInstance(120, 160, Image.SCALE_SMOOTH); // 이미지 축소하기
					ImageIcon ReducedIcon = new ImageIcon(ReducedImg);
					PetImage.setIcon(ReducedIcon); // 축소한 이미지 출력
				}
			} catch (IOException e2) {
				System.out.println("File does not exist.");
			} catch (ClassNotFoundException e2) {
				System.out.println("No data exist.");
			}
		}
	}

	public boolean CheckSchedule(ArrayList<schedule> list) {
		int i = 0;
		boolean check = false;
		while (i < list.size()) {
			if (list.get(i).getDeadLine() <= 1)
				check = true;

			i++;
		}

		return check;
	}
}
