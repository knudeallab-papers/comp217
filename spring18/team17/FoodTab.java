package javaPr;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.swing.event.*;

public class FoodTab extends JPanel implements ActionListener, MouseListener {
	static String[][] foodList = new String[4][300];
	static Integer foodCount = 0;
	JPanel foodPanel = new JPanel();
	JPanel ButtonP = new JPanel();
	static JPanel foodInfoPanel = new JPanel();
	JPanel infoBP = new JPanel();

	JButton foodAdd = new JButton("�߰�");
	JButton infoEdit = new JButton("����");
	JButton infoDel = new JButton("����");
	static DefaultListModel listModel;
	static JList fList;
	static JScrollPane scrollFoodList;
	JTextArea foodInfoArea;
	int selectedFood = 0;

	public FoodTab() {
		setLayout(new BorderLayout());
		this.foodPanel.setLayout(new BorderLayout());
		this.foodPanel.setPreferredSize(new Dimension(300, HEIGHT));

		foodInfoPanel.setLayout(new BorderLayout());
		this.ButtonP.setLayout(new FlowLayout());
		this.foodAdd.setActionCommand("foodAdd");
		this.foodAdd.addActionListener(this);
		this.ButtonP.add(this.foodAdd);
		this.foodPanel.add(this.ButtonP, "South");

		this.foodPanel.setBackground(Color.WHITE);

		listModel = new DefaultListModel();

		if (MainFrame.isLoaded == false) {

		} else {
			String filename = "food.dat";
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename));
				foodCount = inputStream.readInt();
				foodList = (String[][]) inputStream.readObject();
				inputStream.close();
				if (foodList == null) {
					foodCount = 0;
					foodList = new String[4][300];
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fList = new JList(listModel);
		fList.addMouseListener(this);
		scrollFoodList = new JScrollPane(fList);

		if (foodCount > 0) {
			for (int i = 0; i < foodCount; i++) {
				String input = foodList[0][i];
				listModel.addElement(input);
			}
		}

		foodPanel.add(scrollFoodList, BorderLayout.CENTER);
		infoEdit.setActionCommand("foodEdit");
		infoDel.setActionCommand("foodDel");
		infoEdit.addActionListener(this);
		infoDel.addActionListener(this);
		infoBP.add(infoEdit);
		infoBP.add(infoDel);

		foodInfoArea = new JTextArea(35, 110);
		foodInfoArea.setBackground(Color.WHITE);
		foodInfoArea.setForeground(Color.BLACK);
		// foodInfoArea.addComponentListener(new ComponentListener());

		JScrollPane scrolledText = new JScrollPane(foodInfoArea);
		scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		foodPanel.add(scrollFoodList);
		foodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		foodInfoPanel.add(scrolledText, "Center");
		foodInfoPanel.add(infoBP, "South");
		foodInfoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		add(foodInfoPanel, "Center");
		add(this.foodPanel, "West");
	}

	public static void saveFood() {
		ObjectOutputStream outputStream = null;
		String filename = "food.dat";

		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(filename));
			outputStream.writeInt(foodCount);
			outputStream.writeObject(foodList);

			outputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "foodAdd") {
			new FoodAdd(foodCount.intValue());
			foodCount++;
		} else if (e.getActionCommand() == "foodEdit") {
			selectedFood = fList.getSelectedIndex();
			if (selectedFood == -1) {
				JOptionPane.showMessageDialog(this, "�޴��� ���õ��� �ʾҽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			} else {
				new FoodEdit(selectedFood);
			}
		} else if (e.getActionCommand() == "foodDel") {
			selectedFood = fList.getSelectedIndex();
			if (selectedFood == -1) {
				JOptionPane.showMessageDialog(this, "�޴��� ���õ��� �ʾҽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			} else {
				for (int i = selectedFood; i < foodCount; i++) {
					for (int j = 0; j < 4; j++) {
						foodList[j][i] = foodList[j][i + 1];
					}
				}
				foodCount--;
				FoodTab.listModel.removeElementAt(selectedFood);
				foodInfoArea.setText("");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		selectedFood = fList.getSelectedIndex();
		System.out.println(selectedFood);
		if (selectedFood == -1) {
		} else {
			foodInfoArea.setText("�̸� : " + foodList[0][selectedFood] + "\n����: " + foodList[1][selectedFood] + "��"
					+ "\n����ܰ�: " + foodList[2][selectedFood] + "��" + "\n���� ���:\n" + foodList[3][selectedFood]);
		}

	}

	// foodInfoArea.addComponentListener(new ComponentListener());
	//
	// {
	//
	// @Override
	//
	// public void componentResized(ComponentEvent e)
	//
	// {
	//
	// Dimension di = e.getComponent().getSize();
	//
	//
	//
	// jp.setPreferredSize( di );
	//
	// }
	//
	// }

	class FoodAdd extends JFrame implements ActionListener {
		int foodCount = 0;
		JButton add = new JButton("�߰�");
		JButton cancel = new JButton("���");
		JPanel buttonP = new JPanel();
		JPanel infoP = new JPanel();

		JLabel nFName = new JLabel("�̸�:");
		JLabel nFCost = new JLabel("����:");
		JLabel nFUnitCost = new JLabel("����ܰ�:");
		JLabel nFResorce = new JLabel("���� ���(�������� ����):");

		JTextField nFNameArea = new JTextField(20);
		JTextField nFCostArea = new JTextField(20);
		JTextField nFUnitCostArea = new JTextField(20);
		JTextArea nFResorceArea = new JTextArea(20, 80);

		String nFNameTemp;
		String nFCostTemp;
		String nFUnitCostTemp;
		String nFResorceTemp;

		public FoodAdd(int foodCount) {
			super();
			this.foodCount = foodCount;
			setLayout(new BorderLayout());
			setBounds(1000, 200, 400, 600);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					FoodTab.FoodAdd.this.dispose();
				}
			});

			nFNameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			nFCostArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			nFUnitCostArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			nFResorceArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			infoP.setLayout(new GridLayout(4, 2));
			infoP.add(nFName);
			infoP.add(nFNameArea);
			infoP.add(nFCost);
			infoP.add(nFCostArea);
			infoP.add(nFUnitCost);
			infoP.add(nFUnitCostArea);
			infoP.add(nFResorce);
			add(nFResorceArea, "Center");

			add.setActionCommand("addNewFood");
			add.addActionListener(this);
			buttonP.add(add);
			cancel.setActionCommand("cancelNewFood");
			cancel.addActionListener(this);
			buttonP.add(cancel);

			add(infoP, "North");
			add(buttonP, "South");
			setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "addNewFood") {
				nFNameTemp = nFNameArea.getText();
				nFCostTemp = nFCostArea.getText();
				nFUnitCostTemp = nFUnitCostArea.getText();
				nFResorceTemp = nFResorceArea.getText();

				FoodTab.foodList[0][foodCount] = nFNameTemp;
				FoodTab.foodList[1][foodCount] = nFCostTemp;
				FoodTab.foodList[2][foodCount] = nFUnitCostTemp;
				FoodTab.foodList[3][foodCount] = nFResorceTemp;

				FoodTab.listModel.addElement(nFNameTemp);
				foodInfoArea.setText("�̸� : " + foodList[0][foodCount] + "\n����: " + foodList[1][foodCount] + "��"
						+ "\n����ܰ�: " + foodList[2][foodCount] + "��" + "\n���� ���:\n" + foodList[3][foodCount]);
				dispose();

			} else if (e.getActionCommand() == "cancelNewFood") {
				dispose();
			}
		}
	}

	class FoodEdit extends JFrame implements ActionListener {
		int selectedNum = 0;
		JButton add = new JButton("����");
		JButton cancel = new JButton("���");
		JPanel buttonP = new JPanel();
		JPanel infoP = new JPanel();

		JLabel nFName = new JLabel("�̸�: ");
		JLabel nFCost = new JLabel("����: ");
		JLabel nFUnitCost = new JLabel("����ܰ�: ");
		JLabel nFResorce = new JLabel("���� ���: ");

		JTextField nFNameArea = new JTextField(20);
		JTextField nFCostArea = new JTextField(20);
		JTextField nFUnitCostArea = new JTextField(20);
		JTextArea nFResorceArea = new JTextArea(20, 80);

		String nFNameTemp;
		String nFCostTemp;
		String nFUnitCostTemp;
		String nFResorceTemp;

		public FoodEdit(int selectedNum) {
			super("�޴�����");
			this.selectedNum = selectedNum;
			setLayout(new BorderLayout());
			setBounds(1000, 200, 400, 600);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					FoodTab.FoodEdit.this.dispose();
				}
			});
			nFNameArea.setText(FoodTab.foodList[0][this.selectedNum]);
			nFCostArea.setText(FoodTab.foodList[1][this.selectedNum]);
			nFUnitCostArea.setText(FoodTab.foodList[2][this.selectedNum]);
			nFResorceArea.setText(FoodTab.foodList[3][this.selectedNum]);

			nFNameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			nFCostArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			nFUnitCostArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			nFResorceArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			infoP.setLayout(new GridLayout(4, 2));
			infoP.add(nFName);
			infoP.add(nFNameArea);
			infoP.add(nFCost);
			infoP.add(nFCostArea);
			infoP.add(nFUnitCost);
			infoP.add(nFUnitCostArea);
			infoP.add(nFResorce);
			add(nFResorceArea, "Center");

			add.setActionCommand("editOldFood");
			add.addActionListener(this);
			buttonP.add(add);
			cancel.setActionCommand("cancelOldFood");
			cancel.addActionListener(this);
			buttonP.add(cancel);

			add(infoP, "North");
			add(buttonP, "South");
			setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "editOldFood") {
				nFNameTemp = nFNameArea.getText();
				nFCostTemp = nFCostArea.getText();
				nFUnitCostTemp = nFUnitCostArea.getText();
				nFResorceTemp = nFResorceArea.getText();

				FoodTab.foodList[0][this.selectedNum] = nFNameTemp;
				FoodTab.foodList[1][this.selectedNum] = nFCostTemp;
				FoodTab.foodList[2][this.selectedNum] = nFUnitCostTemp;
				FoodTab.foodList[3][this.selectedNum] = nFResorceTemp;

				FoodTab.listModel.removeElementAt(this.selectedNum);
				FoodTab.listModel.insertElementAt(foodList[0][this.selectedNum], this.selectedNum);

				foodInfoArea.setText("�̸� : " + foodList[0][this.selectedNum] + "\n����: " + foodList[1][this.selectedNum]
						+ "��" + "\n����ܰ�: " + foodList[2][this.selectedNum] + "��" + "\n���� ���:\n"
						+ foodList[3][this.selectedNum]);
				dispose();

			} else if (e.getActionCommand() == "cancelOldFood") {
				dispose();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}