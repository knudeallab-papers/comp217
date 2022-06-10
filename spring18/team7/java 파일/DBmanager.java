/**
 * JAVA Term Project : Pos Program
 * Student : Yeji Ahn, Seunghye Jung
 * Prof. : Young-gyoon, Suh
 * Class to connect with Text File
 */

import java.awt.Choice;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DBmanager {
	String url;
	BufferedWriter bw;//����
	BufferedReader br;//�б�
	String urlInventory, urlCustomer, urlGoods, urlEmployee, urlCost,urlEmployeeCost,urlToday; // �������, ȸ������, �޴�����, ��������, �ǸŽ���
	
	public DBmanager() {
		urlInventory = "Inventory.txt";
		urlCustomer = "Customer.txt";
		urlGoods = "Goods.txt";
		urlEmployee = "Employee.txt";
		urlCost = "AllProfit.txt";
		urlEmployeeCost = "EmployeePay.txt";
		urlToday = "Today.txt";
	}
	
	//_____________________________________________________________________________________________________
	// �ſ� 1�� �� ���ϸ��� �ʱ�ȭ
	public boolean InitialMileage() {
		ArrayList<String> line = new ArrayList<String>();//string ���  arraylist ����
		try {
			br = new BufferedReader(new FileReader(urlCustomer));//��ǰ ���� �б�
			while (true) {
				String lines = br.readLine();
				if (lines == null) {
					break;
				}
				else { 
					String newStr = lines.split("\t")[0] + "\t�Ϲ�\t" + lines.split("\t")[2] +  "\t0\t" + lines.split("\t")[4]; 
					line.add(newStr);
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
			return false;
		}

		try {
			bw = new BufferedWriter(new FileWriter(urlCustomer));//��ǰ ���Ͽ� ����. �ڿ� true�� �����Ƿ� ����� �ٽ� ����.
			for (int i = 0; i < line.size(); i++) {
				bw.write(line.get(i));//���Ͽ� ����
				bw.newLine();
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// �� ���
	public boolean addCustomer(Object object, Object object2,
			Object object3, Object object4, Object object5) {//����ȣ, �̸�, ����, ��ȭ��ȣ
		try {
			bw = new BufferedWriter(new FileWriter(urlCustomer, true));//������ ���Ͽ� ����. true�̸� �ִ� ���� �ڿ� ÷���ϴ� ��.
			String localtype = object + "\t" + object2 + "\t" //����� �� ��� ���ڿ� ����(tab���� ����)
					+ object3 + "\t" + object4 + "\t" + object5;
			bw.write(localtype.toString());//�� ��� ��
			bw.newLine();//�� �� ����
			bw.flush(); //��� ������ ��¼ҽ��� ����� ��, ���۸� ����.
			bw.close();//���۸� ����.
		} catch (IOException e) {//����ó��
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// �� ���� ����
	public boolean delCustomer(Object object) {
		ArrayList<String> line = new ArrayList<String>();//string ���  arraylist ����
		int check = 1;
		try {
			br = new BufferedReader(new FileReader(urlCustomer));//��ǰ ���� �б�
			while (true) {
				String lines = br.readLine();
				if (lines == null) {
					break;
				} else if (!lines.split("\t")[0].equals(object))//��ǰ����  arraylist�� ������
					line.add(lines);//list�� ����
				else {
					check = 0; // ��ġ�ϴ°� �ִ�
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}

		try {
			bw = new BufferedWriter(new FileWriter(urlCustomer));//��ǰ ���Ͽ� ����. �ڿ� true�� �����Ƿ� ����� �ٽ� ����.
			for (int i = 0; i < line.size(); i++) {
				bw.write(line.get(i));//���Ͽ� ����
				bw.newLine();
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if ( check == 1 ) {
				JOptionPane.showMessageDialog(null,"������ ������ �����ϴ�");
			}
		}
		if ( check == 1 ) return false;
		return true;
	}
	
	// �� ���� ȭ�鿡 ������Ʈ(��ü)
	public JTable UpdateCustomerList(JTable table) {
		
		if ( table != null)
		{
			table.setModel(new DefaultTableModel(
					null,
					new String[] {
							"��ȣ", "���", "�̸�", "���ϸ���", "����ó"}
				));
		}
		
		try {
			br = new BufferedReader(new FileReader(urlCustomer));
			String extra[] = new String[5];
			while (true) {
				String lines = br.readLine();
				String inputStr[] = new String[5];
				if ( lines == null ) break;
				else {
					inputStr[0] = lines.split("\t")[0];
					inputStr[1] = lines.split("\t")[1];
					inputStr[2] = lines.split("\t")[2];
					inputStr[3] = lines.split("\t")[3];
					inputStr[4] = lines.split("\t")[4];
					((DefaultTableModel) table.getModel()).addRow(inputStr);
				}
			}
				for ( int i = 0 ; i < 20 ; i++)
					((DefaultTableModel) table.getModel()).addRow(extra);
			br.close();
			return table;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	// �� ���� ����
	public boolean modifyCustomer(Object object, Object object2,
			Object object3, Object object4, Object object5) {
		boolean a = false;
		a = this.delCustomer(object);
		if ( a == false ) {
				JOptionPane.showMessageDialog(null,"������ ������ �����ϴ�");
				return false;
		}
		this.addCustomer(object, object2, object3, object4, object5);
		return true;
	}
	
	public String returnCustomerLine(String Customer_num) {
		String lines = null;
		if ( Customer_num.equals("")) return lines;
		try {
			br = new BufferedReader(new FileReader(urlCustomer));
			while (true) {
				lines = br.readLine();
				if (lines == null) {
					break;
				} else if (lines.split("\t")[0].equals(Customer_num))
					return lines;
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		return lines;
	}
	
	
	// �� ���� �ִ��� Ȯ��  -> ��� Ȯ�� -> ���ε� ���� ��ȯ
	public int returnSaleTableCost(int cost, String Customer_num) {
		if ( Customer_num.equals("")) return cost;
		String str = this.returnCustomerLine(Customer_num);
		if ( str == null ) {
			return cost;
		}
		else {
		String level = str.split("\t")[1];
		if ( level.equals("�Ϲ�"))
			return (int) (cost*0.98);
		else if ( level.equals("���"))
			return (int) (cost*0.95);
		else if ( level.equals("�÷�Ƽ��"))
			return (int)(cost*0.9);
		}
			return cost;
		
	}
	
	// ���ϸ��� ���� �� ��� ����
	public int setMileageNChangeLevel(int cost, String Customer_num) {
		String str = this.returnCustomerLine(Customer_num);
		int mileage = (int) (cost*0.02);
		int plusMile = Integer.parseInt(str.split("\t")[3]) + mileage;
		if ( plusMile <= 500)
			this.modifyCustomer(str.split("\t")[0], "�Ϲ�",str.split("\t")[2] ,Integer.toString(plusMile), str.split("\t")[4]);
		else if ( plusMile > 500 && plusMile <= 1000)
			this.modifyCustomer(str.split("\t")[0], "���",str.split("\t")[2] ,Integer.toString(plusMile), str.split("\t")[4]);
		else if ( plusMile > 1000)
			this.modifyCustomer(str.split("\t")[0], "�÷�Ƽ��",str.split("\t")[2] ,Integer.toString(plusMile), str.split("\t")[4]);
		return mileage;
	}
	//______________________________________________________________________________________________
	// ���� ���
		public boolean addEmployee(Object object1, Object object2,
				Object object3, Object object4, Object object5, Object object6) {
			try {
				bw = new BufferedWriter(new FileWriter(urlEmployee, true));
				String localtype = object1 + "\t" + object2 + "\t" 
						+ object3 + "\t" + object4 + "\t" + object5 + "\t" + object6;
				bw.write(localtype.toString());
				bw.newLine();//�� �� ����
				bw.flush(); //��� ������ ��¼ҽ��� ����� ��, ���۸� ����.
				bw.close();//���۸� ����.
			} catch (IOException e) {//����ó��
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			this.modifyEmployeePay();
			return true;
		}
		
		// ���� ���� ����
		public boolean delEmployee(Object object) {
			ArrayList<String> line = new ArrayList<String>();//string ���  arraylist ����
			int check = 1;
			
			try {
				br = new BufferedReader(new FileReader(urlEmployee));//��ǰ ���� �б�
				while (true) {
					String lines = br.readLine();
					if (lines == null) {
						break;
					} else if (!lines.split("\t")[0].equals(object))//��ǰ����  arraylist�� ������
						line.add(lines);//list�� ����
					else {
						check = 0; // ��ġ�ϴ°� �ִ�
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}

			try {
				bw = new BufferedWriter(new FileWriter(urlEmployee));//��ǰ ���Ͽ� ����. �ڿ� true�� �����Ƿ� ����� �ٽ� ����.
				for (int i = 0; i < line.size(); i++) {
					bw.write(line.get(i));//���Ͽ� ����
					bw.newLine();
					bw.flush();
				}
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if ( check == 1 ) {
					JOptionPane.showMessageDialog(null,"������ ������ �����ϴ�");
				}
			}
			if ( check == 1 ) return false;
			this.modifyEmployeePay();
			return true;
		}
		
		// ���� ���� ������Ʈ(��ü)
		public JTable UpdateEmployeeList(JTable table) {
			
			if ( table != null)
			{
				table.setModel(new DefaultTableModel(
						null,
						new String[] {
							"\uBC88\uD638", "\uC774\uB984", "\uAE09\uC5EC", "\uC9C1\uAE09", "\uC785\uC0AC\uC77C", "\uC5F0\uB77D\uCC98"
						}
					));
			}
			
			try {
				br = new BufferedReader(new FileReader(urlEmployee));
				String extra[] = new String[6];
				while (true) {
					String lines = br.readLine();
					String inputStr[] = new String[6];
					if ( lines == null ) break;
					else {
						inputStr[0] = lines.split("\t")[0];
						inputStr[1] = lines.split("\t")[1];
						inputStr[2] = lines.split("\t")[2];
						inputStr[3] = lines.split("\t")[3];
						inputStr[4] = lines.split("\t")[4];
						inputStr[5] = lines.split("\t")[5];
						((DefaultTableModel) table.getModel()).addRow(inputStr);
					}
				}
				for ( int i = 0 ; i < 20 ; i++)
					((DefaultTableModel) table.getModel()).addRow(extra);
				br.close();
				return table;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return table;
		}
		
		// ���� ���� ����
		public boolean modifyEmployee(Object object, Object object2,
				Object object3, Object object4, Object object5)
		{
			boolean a = false;
			String day = this.returnDay(object);
			a = this.delEmployee(object);
			if ( a == false ) {
					JOptionPane.showMessageDialog(null,"������ ������ �����ϴ�");
					return false;
			}
			this.addEmployee(object,object2,
					object3, object4, day, object5);
			this.modifyEmployeePay();
			return true;
		}
		
		// �Ի� ��¥ ��ȯ
		public String returnDay(Object object) {
			String lines = null;
			try {
				br = new BufferedReader(new FileReader(urlEmployee));//��ǰ ���� �б�
				while (true) {
					lines = br.readLine();
					if (lines == null) {
						break;
					} else if (lines.split("\t")[0].equals(object)) {
						return lines.split("\t")[4];
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}
			return lines;
		}
		
		//�����ѱ޿� ���� �޼ҵ� - ���� �߰�������, ���� ���� ����������, ����������
		//�ؽ�Ʈ������ ���� �о �ѱ޿� ����
		public boolean modifyEmployeePay() {
			int Cost = 0;
			try {
				br = new BufferedReader(new FileReader(urlEmployee));//��ǰ ���� �б�
				while (true) {
					String lines = br.readLine();
					if (lines == null) {
						break;
					} else {
						Cost += Integer.parseInt((lines.split("\t")[2]));
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
				return false;
			}

			try {
				bw = new BufferedWriter(new FileWriter(urlEmployeeCost));
					bw.write(Integer.toString(Cost));//���Ͽ� ����
					bw.newLine();
					bw.flush();
					bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//���� �޿� ��ȯ
		public int returnAllEmployeePay() {
			int all = 0;
			try {
				br = new BufferedReader(new FileReader(urlEmployeeCost));//��ǰ ���� �б�
					String lines = br.readLine();
					if (lines == null) {
						all = 0;
					} else {
						all = Integer.parseInt(lines);
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}
			return all;
		}
		
		//_________________________________________________________________________________________
		// �޴� �߰��ϱ�
		public boolean addMenu(String Menu_name, String Menu_cost,
				String Menu_produce, String Menu_ingredient) {
			try {
				bw = new BufferedWriter(new FileWriter(urlGoods, true));
				String localtype = Menu_name + "\t" + Menu_cost + "\t" 
						+ Menu_produce + "\t" + Menu_ingredient;
				bw.write(localtype.toString());
				bw.newLine();//�� �� ����
				bw.flush(); //��� ������ ��¼ҽ��� ����� ��, ���۸� ����.
				bw.close();//���۸� ����.
			} catch (IOException e) {//����ó��
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		// �޴� �����ϱ�
		public boolean delMenu(String Menu_name) {
			ArrayList<String> line = new ArrayList<String>();//string ���  arraylist ����
			int check = 1;
			try {
				br = new BufferedReader(new FileReader(urlGoods));//��ǰ ���� �б�
				while (true) {
					String lines = br.readLine();
					if (lines == null) {
						break;
					} else if (!lines.split("\t")[0].equals(Menu_name))//��ǰ����  arraylist�� ������
						line.add(lines);//list�� ����
					else {
						check = 0; // ��ġ�ϴ°� �ִ�
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}

			try {
				bw = new BufferedWriter(new FileWriter(urlGoods));//��ǰ ���Ͽ� ����. �ڿ� true�� �����Ƿ� ����� �ٽ� ����.
				for (int i = 0; i < line.size(); i++) {
					bw.write(line.get(i));//���Ͽ� ����
					bw.newLine();
					bw.flush();
				}
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if ( check == 1 ) {
					JOptionPane.showMessageDialog(null,"������ ������ �����ϴ�");
				}
			}
			if ( check == 1 ) return false;
			return true;
		}
		
		// �޴� ���� ȭ�鿡 ������Ʈ(��ü)
		public List UpdateMenuList(List list) {
			
			String paste = "";
			if ( list != null )
				list.removeAll();
			
			try {
				br = new BufferedReader(new FileReader(urlGoods));
				while (true) {
					String lines = br.readLine();
					if (lines == null) {
						break;
					} else {
						paste = lines.split("\t")[0];
						list.add(paste);
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		
		// �޴� ���� ����
		public boolean modifyMenu(String Menu_name, String Menu_cost,
				String Menu_produce, String Menu_ingredient) {
			boolean a = false;
			a = this.delMenu(Menu_name);
			if ( a == false ) {
					JOptionPane.showMessageDialog(null,"������ ������ �����ϴ�");
					return false;
			}
			this.addMenu(Menu_name, Menu_cost, Menu_produce, Menu_ingredient);
			return true;
		}
		
		//����Ʈ�� ���̽��� ����
		public Choice ListToChoice(Choice choice) {
			String paste = "";
			
			if ( choice != null ) choice.removeAll();
			try {
				br = new BufferedReader(new FileReader(urlGoods));
				while (true) {
					String lines = br.readLine();
					if (lines == null) {
						break;
					} else {
						paste = lines.split("\t")[0];
						choice.add(paste);
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return choice;
		}
		
		// �޴��� ���� ���� ��ȯ
		public String getInfoOfMenu(String menu_name) {
			String none = null;
			try {
				br = new BufferedReader(new FileReader(urlGoods));
				while (true) {
					String lines = br.readLine();
					if (lines == null) {
						break;
					} else if ( lines.split("\t")[0].equals(menu_name)){
						return lines;
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return none;
		}
		// �޴��� ���� ���� ��ȯ
		public String getCostOfMenu(String menu_name) {
			String none = null;
			try {
				br = new BufferedReader(new FileReader(urlGoods));
				while (true) {
					String lines = br.readLine();
					if (lines == null) {
						break;
					} else if ( lines.split("\t")[0].equals(menu_name)){
						return lines.split("\t")[1];
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return none;
		}
		
		// �޴��� ��� ���� ��ȯ
		public String getIngOfMenu(String menu_name) {
			String none = null;
			try {
				br = new BufferedReader(new FileReader(urlGoods));
				while (true) {
					String lines = br.readLine();
					if (lines == null) {
						break;
					} else if ( lines.split("\t")[0].equals(menu_name)){
						return lines.split("\t")[3];
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return none;
		}
		//______________________________________________________________________________________________
		// â�� ��� �ֹ�
		public boolean addIng(String Ing_name, String Ing_cost,
				String Ing_nowMany, String Ing_buyMany, String Ing_whereSell, String Ing_phone) {
			try {
				bw = new BufferedWriter(new FileWriter(urlInventory, true));
				String localtype = Ing_name + "\t" + Ing_cost + "\t" 
						+ Ing_nowMany + "\t" + Ing_buyMany + "\t" + Ing_whereSell + "\t"+Ing_phone;
				bw.write(localtype.toString());
				bw.newLine();//�� �� ����
				bw.flush(); //��� ������ ��¼ҽ��� ����� ��, ���۸� ����.
				bw.close();//���۸� ����.
			} catch (IOException e) {//����ó��
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		// â�� �����ϱ�
		public boolean delIng(Object object) {
			ArrayList<String> line = new ArrayList<String>();//string ���  arraylist ����
			int check = 1;
			try {
				br = new BufferedReader(new FileReader(urlInventory));//��ǰ ���� �б�
				while (true) {
					String lines = br.readLine();
					if (lines == null) {
						break;
					} else if (!lines.split("\t")[0].equals(object))
						line.add(lines);//list�� ����
					else {
						check = 0; // ��ġ�ϴ°� �ִ�
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}

			try {
				bw = new BufferedWriter(new FileWriter(urlInventory));
				for (int i = 0; i < line.size(); i++) {
					bw.write(line.get(i));//���Ͽ� ����
					bw.newLine();
					bw.flush();
				}
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if ( check == 1 ) {
					JOptionPane.showMessageDialog(null,"������ ������ �����ϴ�");
				}
			}
			if ( check == 1 ) return false;
			return true;
		}
		
		// â�� ���� ȭ�鿡 ������Ʈ(��ü)
		public JTable UpdateIngList(JTable table) {
			
			if ( table != null)
			{
				table.setModel(new DefaultTableModel(
						null,
						new String[] {
							"�̸�", "����", "���", "�ֹ�"
						}
					));
			}
			
			try {
				br = new BufferedReader(new FileReader(urlInventory));
				String extra[] = new String[4];
				while (true) {
					String lines = br.readLine();
					String inputStr[] = new String[4];
					if ( lines == null ) break;
					else {
						inputStr[0] = lines.split("\t")[0];
						inputStr[1] = lines.split("\t")[1];
						inputStr[2] = lines.split("\t")[2];
						inputStr[3] = lines.split("\t")[3];
						((DefaultTableModel) table.getModel()).addRow(inputStr);
					}
				}
				for ( int i = 0 ; i < 20 ; i++)
					((DefaultTableModel) table.getModel()).addRow(extra);
				br.close();
				return table;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return table;
		}
		
		// �ֹ� ������ ��ȯ
		public String returnIngLine(String string) {
			String lines = null;
			try {
				br = new BufferedReader(new FileReader(urlInventory));
				while (true) {
					lines = br.readLine();
					if (lines == null) {
						break;
					} else if (lines.split("\t")[0].equals(string))
						return lines;
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}
			return lines;
		}
		
		// ��� �ֹ��� �ֳ�
		public String returnBuyMany(String Ing_name) {
			String lines = null;
			try {
				br = new BufferedReader(new FileReader(urlInventory));
				while (true) {
					lines = br.readLine();
					if (lines == null) {
						break;
					} else if (lines.split("\t")[0].equals(Ing_name))
						return lines.split("\t")[3];
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}
			return lines;
		}
		
		// â�� ���� ����
		public boolean modifyIng(String Ing_name, String Ing_cost,
				String Ing_nowMany, String Ing_buyMany, String Ing_whereSell, String Ing_phone) {
			boolean a = false;
			String postbuyMany = this.returnBuyMany(Ing_name);
			a = this.delIng(Ing_name);
			if ( a == false ) {
				this.addIng(Ing_name, Ing_cost, Ing_nowMany, Ing_buyMany, Ing_whereSell, Ing_phone);
			}
			else {
				int NewBuyMany = Integer.parseInt(postbuyMany)+ Integer.parseInt(Ing_buyMany);
				this.addIng(Ing_name, Ing_cost, Ing_nowMany, Integer.toString(NewBuyMany), Ing_whereSell, Ing_phone);
			}
			return true;
		}
		// ������ ��� �ױ�
		public boolean modifyIngWhenExit() {
			ArrayList<String> line = new ArrayList<String>();//string ���  arraylist ����
			try {
				br = new BufferedReader(new FileReader(urlInventory));
				while (true) {
					String lines = br.readLine();
					if (lines == null) {
						break;
					} else {
						StringTokenizer st = new StringTokenizer(lines,"\t");
						String paste = st.nextToken() +"\t";
						paste += st.nextToken() + "\t";
						String nowMany = st.nextToken();
						String buyMany = st.nextToken();
						int All = Integer.parseInt(nowMany) + Integer.parseInt(buyMany);
						paste += Integer.toString(All) + "\t";
						paste += "0\t";
						paste += st.nextToken() + "\t";
						paste += st.nextToken();
						line.add(paste);
					}
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}

			try {
				bw = new BufferedWriter(new FileWriter(urlInventory));
				for (int i = 0; i < line.size(); i++) {
					bw.write(line.get(i));//���Ͽ� ����
					bw.newLine();
					bw.flush();
				}
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
					JOptionPane.showMessageDialog(null,"������ ������ �����ϴ�");
					return false;
				}
			return true;
			}
			
		public String ExistInventory(String Ing_name, Object count2) {
			String temp = this.getIngOfMenu(Ing_name);
			String str = null;
			if ( temp != null )
				str = this.returnIngLine(temp);
			if ( str != null ) {
				String now = str.split("\t")[2];
				int buyCount = (int) count2;
				if (Integer.parseInt(now) >= buyCount) {
					int count = Integer.parseInt(now) - buyCount;
					String newStr = str.split("\t")[0] +"\t"+ str.split("\t")[1] +"\t" + Integer.toString(count) + "\t"+ str.split("\t")[3]+"\t"+ str.split("\t")[4]+"\t"+ str.split("\t")[5];
					return newStr;
				}
				else {
					JOptionPane.showMessageDialog(null, "��� ���ڶ��ϴ�");
					return "";
				}
			}
			else {
			JOptionPane.showMessageDialog(null, "��� �����ϴ�.");
			return "";
			}
		}
		
		public void modifyIngWhenOrder(String str) {
			if ( str != null) {
				this.modifyIng(str.split("\t")[0], str.split("\t")[1], str.split("\t")[2], str.split("\t")[3], str.split("\t")[4], str.split("\t")[5]);
			}
		}
		
		//______________________________________________________________________________________________
		// �Ϸ������ �� �ڱݿ� �ֱ�
		public boolean modifyAllProfit(int DayProfit) {
			int All = 0;
			try {
				br = new BufferedReader(new FileReader(urlCost));
				String lines = br.readLine();
				if (lines == null) {
					lines = "0";
				}
				else {
					All += Integer.parseInt(lines);
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
				return false;
			}
			
			All += DayProfit;
					
			try {
				bw = new BufferedWriter(new FileWriter(urlCost));
					bw.write(Integer.toString(All));
					bw.newLine();
					bw.flush();
					bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		// ���ڱ� ������Ʈ
		public String updateAllProfit() {
			try {
				br = new BufferedReader(new FileReader(urlCost));
				String lines = br.readLine();
				if (lines == null) {
					lines = "0";
				}
				br.close();
				return lines;
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
				return "0";
			}
		}
		//________________________________________________________________________________________________________
		
		// ��¥ ������ ���� - ������ "", ������ �������� ��¥�� �����Ѵ�
		public String returnNextDay() {
			// 2017 06 08
			String lines = null;
			try {
				br = new BufferedReader(new FileReader(urlToday));
				lines = br.readLine();
				
				if (lines == null) {
					br.close();
					return lines;
				}
				else {
					// date
					SimpleDateFormat dateDisplay = new SimpleDateFormat("yyyy MM dd");
					Calendar c = Calendar.getInstance();
					c.setTime(dateDisplay.parse(lines));
					c.add(Calendar.DATE, 1);
					lines = dateDisplay.format(c.getTime());
					br.close();
					return lines;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}
			return lines;
		}
		
		// ���α׷� ������ ���� ��¥ ��ȯ
		public String returnToday() {
			// 2017 06 08
			String lines = null;
			try {
				br = new BufferedReader(new FileReader(urlToday));
				lines = br.readLine();
				if ( lines == null) return lines;
				else return lines;
			} catch (Exception e) {
				// TODO Auto-generated catch bloc
				e.printStackTrace();
			}
			return lines;
		}
		
		//���Ŀ� ��¥ ����ϱ�
		public boolean saveDay(String date) {
			try {
				bw = new BufferedWriter(new FileWriter(urlToday));
					bw.write(date);
					bw.newLine();
					bw.flush();
					bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		// ���̺� ���� ���
		public int returnTableCost(Table table) {
			JTable n = new JTable();
			n.setModel(new DefaultTableModel(
					null,
					new String[] {
						"�޴�", "����", "����"
					}
				));
			n = table.getTable(n);
			if ( n.getRowCount() == 0 )
				return 0;
			
			int TotalPrice = 0;
			try {
			for ( int i = 0 ; i < n.getRowCount() ; i++) {
				String price;
				String count;
					count =  (String) n.getValueAt(i, 1);
					price =  (String) n.getValueAt(i,2);
					TotalPrice += Integer.parseInt(price)*Integer.parseInt(count);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return TotalPrice;
		}
}
