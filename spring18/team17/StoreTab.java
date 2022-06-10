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
import javax.swing.table.DefaultTableModel;

// TO-Do ������ ���̺� ���� ���, �ֹ�, �ֹ����
public class StoreTab extends JPanel implements ActionListener, MouseListener{
   static String[][] resourceList = new String[6][100]; // 0 name 1 cost 2 ���� 3 ����ó 4 ��� 5 �ֹ�����

   JPanel resourcePanel = new JPanel();
   JPanel resourceButtonPanel = new JPanel();
   JPanel infoPanel = new JPanel();
   JPanel infoButtonPanel = new JPanel();
   
   JButton resourceAdd = new JButton("�߰�");
   JButton resourceEdit = new JButton("����");
   JButton resourceDel = new JButton("����");
   JButton orderResource = new JButton("�ֹ�");
   JButton cancleOrder = new JButton("�ֹ����");
   
   static JTextArea resourceInfoArea = new JTextArea(35,80);
   
   JScrollPane infoScroll;
   JScrollPane resourceScroll;
   
   static DefaultTableModel resourceTableModel;
   
   static JTable resourceTable;
   
   static String header[] = {"�̸�", "���", "�ֹ�", "����"};
   String contents[][] = {{"",""}};
   
   static Integer resourceCount = 0;
   static Integer selectedTable = -1;
   
   
   public StoreTab() {
      setLayout(new GridLayout(1,2));
      resourcePanel.setLayout(new BorderLayout());
      infoPanel.setLayout(new BorderLayout());
      resourceButtonPanel.setLayout(new FlowLayout());
      infoButtonPanel.setLayout(new FlowLayout());
      
      
      
		if (MainFrame.isLoaded == false) {
			resourceTableModel = new DefaultTableModel(contents, header) {
				public boolean isCellEditable(int i, int c) {
					return false;
				}
			};
			resourceTableModel.setRowCount(0);
		} else {
			String filename = "store.dat";
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename));
				resourceCount = inputStream.readInt();
				resourceTableModel = (DefaultTableModel) inputStream.readObject();
				resourceList = (String[][]) inputStream.readObject();
				
				inputStream.close();
				if (resourceTableModel == null) {
					resourceCount = 0;
					resourceTableModel = new DefaultTableModel(contents, header) {
						public boolean isCellEditable(int i, int c) {
							return false;
						}
					};
					resourceTableModel.setRowCount(0);
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
		
      resourceTable = new JTable(resourceTableModel);
      resourceTable.addMouseListener(this);
      resourceScroll = new JScrollPane(resourceTable);
      
      resourceAdd.setActionCommand("resourceAdd");
      resourceAdd.addActionListener(this);
      resourceButtonPanel.add(resourceAdd);
      
      resourceEdit.setActionCommand("resourceEdit");
      resourceEdit.addActionListener(this);
      resourceButtonPanel.add(resourceEdit);
      
      resourceDel.setActionCommand("resourceDel");
      resourceDel.addActionListener(this);
      resourceButtonPanel.add(resourceDel);
      
      orderResource.setActionCommand("orderResource");
      orderResource.addActionListener(this);
      infoButtonPanel.add(orderResource);
      
      cancleOrder.setActionCommand("cancelOrder");
      cancleOrder.addActionListener(this);
      infoButtonPanel.add(cancleOrder);
      
      resourcePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      resourcePanel.add(resourceScroll,BorderLayout.CENTER);
      resourcePanel.add(resourceButtonPanel, BorderLayout.SOUTH);
      infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      infoPanel.add(resourceInfoArea, BorderLayout.CENTER);
      infoPanel.add(infoButtonPanel, BorderLayout.SOUTH);
      add(resourcePanel);
      add(infoPanel);
   }
   
   public static void saveStore()
	{
		ObjectOutputStream outputStream = null;
		String filename = "store.dat";
		
		try{
			outputStream = new ObjectOutputStream(new FileOutputStream(filename));
			outputStream.writeInt(resourceCount);
			outputStream.writeObject(resourceTableModel);
			outputStream.writeObject(resourceList);
			outputStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
   public static void dayEndStore() //â������ 
   {
      Integer dayEndStoreTemp;
      
      for(int i = 0; i < resourceCount; i++) {
         dayEndStoreTemp = (Integer.parseInt(StoreTab.resourceList[4][i]) 
                     + Integer.parseInt(StoreTab.resourceList[5][i]));
         MainFrame.totalMoney -= (Integer.parseInt(StoreTab.resourceList[5][i]) * Integer.parseInt(StoreTab.resourceList[1][i]));
         StoreTab.resourceList[4][i] = dayEndStoreTemp.toString();
         StoreTab.resourceList[5][i] = "0";
         
         String input[] = {StoreTab.resourceList[0][i], StoreTab.resourceList[4][i], StoreTab.resourceList[5][i], StoreTab.resourceList[1][i]};
         
         StoreTab.resourceTableModel.removeRow(i);
         StoreTab.resourceTableModel.insertRow(i, input);
      }
      resourceInfoArea.setText("");
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getActionCommand() == "resourceAdd")
      {
         new ResourceAdd(resourceCount);
         resourceCount++;
      }
      else if (e.getActionCommand() == "resourceEdit")
      {
         selectedTable = resourceTable.getSelectedRow();
         if(selectedTable == -1) {
            JOptionPane.showMessageDialog(this, "�޴��� ���õ��� �ʾҽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
         } else {
            new ResourceEdit(selectedTable);
         }
      }
      else if (e.getActionCommand() == "resourceDel")
      {
         selectedTable = resourceTable.getSelectedRow();
         if(selectedTable == -1) {
            JOptionPane.showMessageDialog(this, "�޴��� ���õ��� �ʾҽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
         }else {
            for(int i = selectedTable; i < resourceCount; i++) {
               for(int j = 0; j < 6; j++) {
                  resourceList[j][i] = resourceList[j][i+1];
               }
            }
            resourceCount--;
            DefaultTableModel resourceTableModel = (DefaultTableModel)resourceTable.getModel();
            resourceTableModel.removeRow(selectedTable);
            resourceInfoArea.setText("");
         }
      }
      else if (e.getActionCommand() == "orderResource") {
         selectedTable = resourceTable.getSelectedRow();
         if(selectedTable == -1) {
            JOptionPane.showMessageDialog(this, "�޴��� ���õ��� �ʾҽ��ϴ�.", "�ֹ�", JOptionPane.ERROR_MESSAGE);
         }else {
            new ResourceOrder(selectedTable);
         }
      }
      else if (e.getActionCommand() == "cancelOrder") {
         selectedTable = resourceTable.getSelectedRow();
         if(selectedTable == -1) {
            JOptionPane.showMessageDialog(this, "�޴��� ���õ��� �ʾҽ��ϴ�.", "�ֹ����", JOptionPane.ERROR_MESSAGE);
         }else {
            if(Integer.parseInt(StoreTab.resourceList[5][this.selectedTable]) <= 0)
            {
               JOptionPane.showMessageDialog(this, "���� �ֹ����� 0�Դϴ�.", "�ֹ����", JOptionPane.ERROR_MESSAGE);
            }
            else {
               new ResourceOrderCancel(selectedTable);
            }
         }
      }
   }
   
   @Override
   public void mouseClicked(MouseEvent e) {
      selectedTable = resourceTable.getSelectedRow();
      if (selectedTable == -1) {
      } else {
         resourceInfoArea.setText("�̸� : " + resourceList[0][selectedTable] 
                     + "\n����: " + resourceList[1][selectedTable] + "��"
                     + "\n�Ǹ�ó: " + resourceList[2][selectedTable]
                     + "\n����ó: " + resourceList[3][selectedTable]
                     + "\n�������: " + resourceList[4][selectedTable]
                     + "\n�ֹ���: " + resourceList[5][selectedTable]);
      }

   }

   @Override
   public void mouseEntered(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mousePressed(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseReleased(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }
}

class ResourceAdd extends JFrame implements ActionListener{
   
   JButton resourceAddButton = new JButton("�߰��ϱ�");
   JButton cancelB = new JButton("���");
   JPanel infoPanel = new JPanel();
   JPanel buttonPanel = new JPanel();
   
   JLabel rName = new JLabel("�̸�:");
   JLabel rCost = new JLabel("����:");
   JLabel rShop = new JLabel("�Ǹ�ó:");
   JLabel rTel = new JLabel("����ó:");
   JLabel rStock = new JLabel("����:");
   
   JTextField rNameArea = new JTextField(20);
   JTextField rCostArea = new JTextField(20);
   JTextField rShopArea = new JTextField(20);
   JTextField rTelArea = new JTextField(20);
   JTextField rStockArea = new JTextField(20);
   
   String rNameTemp;
   String rCostTemp;
   String rShopTemp;
   String rTelTemp;
   String rStockTemp;
   
   JScrollPane scroll;
   int resourceCount = -1;
   
   public ResourceAdd(int resourceCount) {
      super("����߰�");
      this.resourceCount = resourceCount;
      setLayout(new BorderLayout());
      setBounds(1000, 200, 400, 600);
      
      infoPanel.setLayout(new GridLayout(7,2));
      
      rNameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rCostArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rShopArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rTelArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rStockArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

      infoPanel.add(rName);
      infoPanel.add(rNameArea);
      
      infoPanel.add(rCost);
      infoPanel.add(rCostArea);

      infoPanel.add(rShop);
      infoPanel.add(rShopArea);

      infoPanel.add(rTel);
      infoPanel.add(rTelArea);

      infoPanel.add(rStock);
      infoPanel.add(rStockArea);

      resourceAddButton.addActionListener(this);
      resourceAddButton.setActionCommand("resourceAddButton");
      cancelB.addActionListener(this);
      cancelB.setActionCommand("cancelResourceAdd");
      buttonPanel.add(resourceAddButton);
      buttonPanel.add(cancelB);
      
      add(infoPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getActionCommand() == "resourceAddButton") {
         rNameTemp = rNameArea.getText();
         rCostTemp = rCostArea.getText();
         rShopTemp = rShopArea.getText();
         rTelTemp = rTelArea.getText();
         rStockTemp = rStockArea.getText();
         
         String input[] = {rNameTemp, rStockTemp, "0", rCostTemp};
         
         StoreTab.resourceList[0][this.resourceCount] = rNameTemp;
         StoreTab.resourceList[1][this.resourceCount] = rCostTemp;
         StoreTab.resourceList[2][this.resourceCount] = rShopTemp;
         StoreTab.resourceList[3][this.resourceCount] = rTelTemp;
         StoreTab.resourceList[4][this.resourceCount] = rStockTemp;
         StoreTab.resourceList[5][this.resourceCount] = "0";
                  
         StoreTab.resourceTableModel.addRow(input);
         dispose();
      }
      else if(e.getActionCommand() == "cancelResourceAdd")
      {
         dispose();
      }   
   }
};

class ResourceEdit extends JFrame implements ActionListener {
   JButton resourceAddButton = new JButton("�����ϱ�");
   JButton cancelB = new JButton("���");
   JPanel infoPanel = new JPanel();
   JPanel buttonPanel = new JPanel();
   
   JLabel rName = new JLabel("�̸�:");
   JLabel rCost = new JLabel("����:");
   JLabel rShop = new JLabel("�Ǹ�ó:");
   JLabel rTel = new JLabel("����ó:");
   JLabel rStock = new JLabel("����:");
   
   JTextField rNameArea = new JTextField(20);
   JTextField rCostArea = new JTextField(20);
   JTextField rShopArea = new JTextField(20);
   JTextField rTelArea = new JTextField(20);
   JTextField rStockArea = new JTextField(20);
   
   String rNameTemp;
   String rCostTemp;
   String rShopTemp;
   String rTelTemp;
   String rStockTemp;
   
   JScrollPane scroll;
   int selectedTable = -1;
   
   public ResourceEdit(int selectedTable) {
      super("������");
      this.selectedTable = selectedTable;
      setLayout(new BorderLayout());
      setBounds(1000, 200, 400, 600);
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            ResourceEdit.this.dispose();
         }
      });
      
      infoPanel.setLayout(new GridLayout(7,2));
      
      rNameArea.setText(StoreTab.resourceList[0][this.selectedTable]);
      rCostArea.setText(StoreTab.resourceList[1][this.selectedTable]);
      rShopArea.setText(StoreTab.resourceList[2][this.selectedTable]);
      rTelArea.setText(StoreTab.resourceList[3][this.selectedTable]);
      rStockArea.setText(StoreTab.resourceList[4][this.selectedTable]);
      
      rNameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rCostArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rShopArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rTelArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rStockArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

      infoPanel.add(rName);
      infoPanel.add(rNameArea);
      
      infoPanel.add(rCost);
      infoPanel.add(rCostArea);

      infoPanel.add(rShop);
      infoPanel.add(rShopArea);

      infoPanel.add(rTel);
      infoPanel.add(rTelArea);

      infoPanel.add(rStock);
      infoPanel.add(rStockArea);

      resourceAddButton.addActionListener(this);
      resourceAddButton.setActionCommand("resourceAddButton");
      cancelB.addActionListener(this);
      cancelB.setActionCommand("cancelResourceAdd");
      buttonPanel.add(resourceAddButton);
      buttonPanel.add(cancelB);
      
      add(infoPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getActionCommand() == "resourceAddButton") {
         rNameTemp = rNameArea.getText();
         rCostTemp = rCostArea.getText();
         rShopTemp = rShopArea.getText();
         rTelTemp = rTelArea.getText();
         rStockTemp = rStockArea.getText();
         
         String input[] = {rNameTemp, rStockTemp, "0", rCostTemp};
         
         StoreTab.resourceList[0][this.selectedTable] = rNameTemp;
         StoreTab.resourceList[1][this.selectedTable] = rCostTemp;
         StoreTab.resourceList[2][this.selectedTable] = rShopTemp;
         StoreTab.resourceList[3][this.selectedTable] = rTelTemp;
         StoreTab.resourceList[4][this.selectedTable] = rStockTemp;
         StoreTab.resourceList[5][this.selectedTable] = "0";
                  
         StoreTab.resourceTableModel.removeRow(selectedTable);
         StoreTab.resourceTableModel.insertRow(selectedTable, input);
         
         StoreTab.resourceInfoArea.setText("�̸� : " + StoreTab.resourceList[0][selectedTable] 
               + "\n����: " + StoreTab.resourceList[1][selectedTable] + "��"
               + "\n�Ǹ�ó: " + StoreTab.resourceList[2][selectedTable]
               + "\n����ó: " + StoreTab.resourceList[3][selectedTable]
               + "\n�������: " + StoreTab.resourceList[4][selectedTable]
               + "\n�ֹ���: " + StoreTab.resourceList[5][selectedTable]);
//         StoreTab.resourceTableModel.addRow(input);
         dispose();
      }
      else if(e.getActionCommand() == "cancelResourceAdd")
      {
         dispose();
      }   
   }
}

class ResourceOrder extends JFrame implements ActionListener{

   JPanel buttonP = new JPanel();
   JPanel infoP = new JPanel();
   
   JButton rOrderPB = new JButton("�ֹ�");
   JButton rOrderCancel = new JButton("���");
   
   JLabel rName = new JLabel("�̸�: ");
   JLabel rCost = new JLabel("����: ");
   JLabel rShop = new JLabel("�Ǹ�ó: ");
   JLabel rTel = new JLabel("����ó: ");
   JLabel rStock = new JLabel("�������: ");
   JLabel rOrder = new JLabel("�����ֹ���: ");
   JLabel rOrder2 = new JLabel("�߰��ֹ���: ");
   
   JLabel rNameArea = new JLabel();
   JLabel rCostArea = new JLabel();
   JLabel rShopArea = new JLabel();
   JLabel rTelArea = new JLabel();
   JLabel rStockArea = new JLabel();
   JLabel rOrderArea = new JLabel();
   JTextField rOrderField = new JTextField(20);
   
   String rOrderTemp;
   
   int selectedTable = -1;
   int orderTempInt = -1;
   
   public ResourceOrder(int selectedTable) {
      super("����ֹ�");
      this.selectedTable = selectedTable;
      setLayout(new BorderLayout());
      setBounds(1000, 200, 400, 600);
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            ResourceOrder.this.dispose();
         }
      });
      
      infoP.setLayout(new GridLayout(8,2));
      
      rNameArea.setText(StoreTab.resourceList[0][this.selectedTable]);
      rCostArea.setText(StoreTab.resourceList[1][this.selectedTable]);
      rShopArea.setText(StoreTab.resourceList[2][this.selectedTable]);
      rTelArea.setText(StoreTab.resourceList[3][this.selectedTable]);
      rStockArea.setText(StoreTab.resourceList[4][this.selectedTable]);
      rOrderArea.setText(StoreTab.resourceList[5][this.selectedTable]);
      
      rNameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rCostArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rShopArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rTelArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rStockArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rOrderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

      infoP.add(rName);
      infoP.add(rNameArea);
      
      infoP.add(rCost);
      infoP.add(rCostArea);

      infoP.add(rShop);
      infoP.add(rShopArea);

      infoP.add(rTel);
      infoP.add(rTelArea);

      infoP.add(rStock);
      infoP.add(rStockArea);
      
      infoP.add(rOrder);
      infoP.add(rOrderArea);
      
      infoP.add(rOrder2);
      infoP.add(rOrderField);
      

      rOrderPB.addActionListener(this);
      rOrderPB.setActionCommand("rOrderPB");
      rOrderCancel.addActionListener(this);
      rOrderCancel.setActionCommand("rOrderCancel");
      buttonP.add(rOrderPB);
      buttonP.add(rOrderCancel);
      
      add(infoP, BorderLayout.CENTER);
      add(buttonP, BorderLayout.SOUTH);
      setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getActionCommand() == "rOrderPB") {

         rOrderTemp = rOrderField.getText();
         
         orderTempInt = Integer.parseInt(rOrderTemp);
         
         orderTempInt += Integer.parseInt(StoreTab.resourceList[5][this.selectedTable]);
         
         rOrderTemp = Integer.toString(orderTempInt);
                  
         StoreTab.resourceList[5][this.selectedTable] = rOrderTemp;

         String input[] = {StoreTab.resourceList[0][selectedTable], StoreTab.resourceList[4][selectedTable], StoreTab.resourceList[5][this.selectedTable], StoreTab.resourceList[1][selectedTable]};
         
         StoreTab.resourceTableModel.removeRow(selectedTable);
         StoreTab.resourceTableModel.insertRow(selectedTable, input);
         
         StoreTab.resourceInfoArea.setText("�̸� : " + StoreTab.resourceList[0][selectedTable] 
               + "\n����: " + StoreTab.resourceList[1][selectedTable] + "��"
               + "\n�Ǹ�ó: " + StoreTab.resourceList[2][selectedTable]
               + "\n����ó: " + StoreTab.resourceList[3][selectedTable]
               + "\n�������: " + StoreTab.resourceList[4][selectedTable]
               + "\n�ֹ���: " + StoreTab.resourceList[5][selectedTable]);
         dispose();
      }
      else if(e.getActionCommand() == "rOrderCancel")
      {
         dispose();
      }   
   }
   
}

class ResourceOrderCancel extends JFrame implements ActionListener{

   JPanel buttonP = new JPanel();
   JPanel infoP = new JPanel();
   
   JButton rOrderPB = new JButton("�ֹ�");
   JButton rOrderCancel = new JButton("���");
   
   JLabel rName = new JLabel("�̸�: ");
   JLabel rCost = new JLabel("����: ");
   JLabel rShop = new JLabel("�Ǹ�ó: ");
   JLabel rTel = new JLabel("����ó: ");
   JLabel rStock = new JLabel("�������: ");
   JLabel rOrder = new JLabel("�����ֹ���: ");
   JLabel rOrder2 = new JLabel("�ֹ���ҷ�: ");
   
   JLabel rNameArea = new JLabel();
   JLabel rCostArea = new JLabel();
   JLabel rShopArea = new JLabel();
   JLabel rTelArea = new JLabel();
   JLabel rStockArea = new JLabel();
   JLabel rOrderArea = new JLabel();
   JTextField rOrderField = new JTextField(20);
   
   String rOrderTemp;
   
   int selectedTable = -1;
   int orderTempInt = -1;
   
   public ResourceOrderCancel(int selectedTable) {
      super("����ֹ����");
      this.selectedTable = selectedTable;
      setLayout(new BorderLayout());
      setBounds(1000, 200, 400, 600);
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            ResourceOrderCancel.this.dispose();
         }
      });
      
      infoP.setLayout(new GridLayout(8,2));
      
      rNameArea.setText(StoreTab.resourceList[0][this.selectedTable]);
      rCostArea.setText(StoreTab.resourceList[1][this.selectedTable]);
      rShopArea.setText(StoreTab.resourceList[2][this.selectedTable]);
      rTelArea.setText(StoreTab.resourceList[3][this.selectedTable]);
      rStockArea.setText(StoreTab.resourceList[4][this.selectedTable]);
      rOrderArea.setText(StoreTab.resourceList[5][this.selectedTable]);
      
      rNameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rCostArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rShopArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rTelArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rStockArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      rOrderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

      infoP.add(rName);
      infoP.add(rNameArea);
      
      infoP.add(rCost);
      infoP.add(rCostArea);

      infoP.add(rShop);
      infoP.add(rShopArea);

      infoP.add(rTel);
      infoP.add(rTelArea);

      infoP.add(rStock);
      infoP.add(rStockArea);
      
      infoP.add(rOrder);
      infoP.add(rOrderArea);
      
      infoP.add(rOrder2);
      infoP.add(rOrderField);
      

      rOrderPB.addActionListener(this);
      rOrderPB.setActionCommand("rOrderPB");
      rOrderCancel.addActionListener(this);
      rOrderCancel.setActionCommand("rOrderCancel");
      buttonP.add(rOrderPB);
      buttonP.add(rOrderCancel);
      
      add(infoP, BorderLayout.CENTER);
      add(buttonP, BorderLayout.SOUTH);
      setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getActionCommand() == "rOrderPB") {

         rOrderTemp = rOrderField.getText();
         
         orderTempInt = Integer.parseInt(rOrderTemp);
         
         if(orderTempInt > Integer.parseInt(StoreTab.resourceList[5][this.selectedTable]))
         {
            JOptionPane.showMessageDialog(this, " ���� �ֹ������� ���� ����� �� �����ϴ�.", "�ֹ����", JOptionPane.ERROR_MESSAGE);
         }
         else {
         orderTempInt = Integer.parseInt(StoreTab.resourceList[5][this.selectedTable]) - orderTempInt;
         
         rOrderTemp = Integer.toString(orderTempInt);
                  
         StoreTab.resourceList[5][this.selectedTable] = rOrderTemp;

         String input[] = {StoreTab.resourceList[0][selectedTable], StoreTab.resourceList[4][selectedTable], StoreTab.resourceList[5][this.selectedTable], StoreTab.resourceList[1][selectedTable]};
         
         StoreTab.resourceTableModel.removeRow(selectedTable);
         StoreTab.resourceTableModel.insertRow(selectedTable, input);
         
         StoreTab.resourceInfoArea.setText("�̸� : " + StoreTab.resourceList[0][selectedTable] 
               + "\n����: " + StoreTab.resourceList[1][selectedTable] + "��"
               + "\n�Ǹ�ó: " + StoreTab.resourceList[2][selectedTable]
               + "\n����ó: " + StoreTab.resourceList[3][selectedTable]
               + "\n�������: " + StoreTab.resourceList[4][selectedTable]
               + "\n�ֹ���: " + StoreTab.resourceList[5][selectedTable]);
         }
         dispose();
      }
      else if(e.getActionCommand() == "rOrderCancel")
      {
         dispose();
      }   
   }
   
}