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

// TO-Do 수정시 테이블 갱신 기능, 주문, 주문취소
public class StoreTab extends JPanel implements ActionListener, MouseListener{
   static String[][] resourceList = new String[6][100]; // 0 name 1 cost 2 가게 3 연락처 4 재고 5 주문수량

   JPanel resourcePanel = new JPanel();
   JPanel resourceButtonPanel = new JPanel();
   JPanel infoPanel = new JPanel();
   JPanel infoButtonPanel = new JPanel();
   
   JButton resourceAdd = new JButton("추가");
   JButton resourceEdit = new JButton("수정");
   JButton resourceDel = new JButton("삭제");
   JButton orderResource = new JButton("주문");
   JButton cancleOrder = new JButton("주문취소");
   
   static JTextArea resourceInfoArea = new JTextArea(35,80);
   
   JScrollPane infoScroll;
   JScrollPane resourceScroll;
   
   static DefaultTableModel resourceTableModel;
   
   static JTable resourceTable;
   
   static String header[] = {"이름", "재고", "주문", "가격"};
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
   public static void dayEndStore() //창고마감시 
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
            JOptionPane.showMessageDialog(this, "메뉴가 선택되지 않았습니다.", "수정", JOptionPane.ERROR_MESSAGE);
         } else {
            new ResourceEdit(selectedTable);
         }
      }
      else if (e.getActionCommand() == "resourceDel")
      {
         selectedTable = resourceTable.getSelectedRow();
         if(selectedTable == -1) {
            JOptionPane.showMessageDialog(this, "메뉴가 선택되지 않았습니다.", "삭제", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "메뉴가 선택되지 않았습니다.", "주문", JOptionPane.ERROR_MESSAGE);
         }else {
            new ResourceOrder(selectedTable);
         }
      }
      else if (e.getActionCommand() == "cancelOrder") {
         selectedTable = resourceTable.getSelectedRow();
         if(selectedTable == -1) {
            JOptionPane.showMessageDialog(this, "메뉴가 선택되지 않았습니다.", "주문취소", JOptionPane.ERROR_MESSAGE);
         }else {
            if(Integer.parseInt(StoreTab.resourceList[5][this.selectedTable]) <= 0)
            {
               JOptionPane.showMessageDialog(this, "현재 주문량이 0입니다.", "주문취소", JOptionPane.ERROR_MESSAGE);
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
         resourceInfoArea.setText("이름 : " + resourceList[0][selectedTable] 
                     + "\n가격: " + resourceList[1][selectedTable] + "원"
                     + "\n판매처: " + resourceList[2][selectedTable]
                     + "\n연락처: " + resourceList[3][selectedTable]
                     + "\n현재수량: " + resourceList[4][selectedTable]
                     + "\n주문량: " + resourceList[5][selectedTable]);
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
   
   JButton resourceAddButton = new JButton("추가하기");
   JButton cancelB = new JButton("취소");
   JPanel infoPanel = new JPanel();
   JPanel buttonPanel = new JPanel();
   
   JLabel rName = new JLabel("이름:");
   JLabel rCost = new JLabel("가격:");
   JLabel rShop = new JLabel("판매처:");
   JLabel rTel = new JLabel("연락처:");
   JLabel rStock = new JLabel("수량:");
   
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
      super("재료추가");
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
   JButton resourceAddButton = new JButton("수정하기");
   JButton cancelB = new JButton("취소");
   JPanel infoPanel = new JPanel();
   JPanel buttonPanel = new JPanel();
   
   JLabel rName = new JLabel("이름:");
   JLabel rCost = new JLabel("가격:");
   JLabel rShop = new JLabel("판매처:");
   JLabel rTel = new JLabel("연락처:");
   JLabel rStock = new JLabel("수량:");
   
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
      super("재료수정");
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
         
         StoreTab.resourceInfoArea.setText("이름 : " + StoreTab.resourceList[0][selectedTable] 
               + "\n가격: " + StoreTab.resourceList[1][selectedTable] + "원"
               + "\n판매처: " + StoreTab.resourceList[2][selectedTable]
               + "\n연락처: " + StoreTab.resourceList[3][selectedTable]
               + "\n현재수량: " + StoreTab.resourceList[4][selectedTable]
               + "\n주문량: " + StoreTab.resourceList[5][selectedTable]);
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
   
   JButton rOrderPB = new JButton("주문");
   JButton rOrderCancel = new JButton("취소");
   
   JLabel rName = new JLabel("이름: ");
   JLabel rCost = new JLabel("가격: ");
   JLabel rShop = new JLabel("판매처: ");
   JLabel rTel = new JLabel("연락처: ");
   JLabel rStock = new JLabel("현재수량: ");
   JLabel rOrder = new JLabel("현재주문량: ");
   JLabel rOrder2 = new JLabel("추가주문량: ");
   
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
      super("재료주문");
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
         
         StoreTab.resourceInfoArea.setText("이름 : " + StoreTab.resourceList[0][selectedTable] 
               + "\n가격: " + StoreTab.resourceList[1][selectedTable] + "원"
               + "\n판매처: " + StoreTab.resourceList[2][selectedTable]
               + "\n연락처: " + StoreTab.resourceList[3][selectedTable]
               + "\n현재수량: " + StoreTab.resourceList[4][selectedTable]
               + "\n주문량: " + StoreTab.resourceList[5][selectedTable]);
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
   
   JButton rOrderPB = new JButton("주문");
   JButton rOrderCancel = new JButton("취소");
   
   JLabel rName = new JLabel("이름: ");
   JLabel rCost = new JLabel("가격: ");
   JLabel rShop = new JLabel("판매처: ");
   JLabel rTel = new JLabel("연락처: ");
   JLabel rStock = new JLabel("현재수량: ");
   JLabel rOrder = new JLabel("현재주문량: ");
   JLabel rOrder2 = new JLabel("주문취소량: ");
   
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
      super("재료주문취소");
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
            JOptionPane.showMessageDialog(this, " 현재 주문량보다 많이 취소할 수 없습니다.", "주문취소", JOptionPane.ERROR_MESSAGE);
         }
         else {
         orderTempInt = Integer.parseInt(StoreTab.resourceList[5][this.selectedTable]) - orderTempInt;
         
         rOrderTemp = Integer.toString(orderTempInt);
                  
         StoreTab.resourceList[5][this.selectedTable] = rOrderTemp;

         String input[] = {StoreTab.resourceList[0][selectedTable], StoreTab.resourceList[4][selectedTable], StoreTab.resourceList[5][this.selectedTable], StoreTab.resourceList[1][selectedTable]};
         
         StoreTab.resourceTableModel.removeRow(selectedTable);
         StoreTab.resourceTableModel.insertRow(selectedTable, input);
         
         StoreTab.resourceInfoArea.setText("이름 : " + StoreTab.resourceList[0][selectedTable] 
               + "\n가격: " + StoreTab.resourceList[1][selectedTable] + "원"
               + "\n판매처: " + StoreTab.resourceList[2][selectedTable]
               + "\n연락처: " + StoreTab.resourceList[3][selectedTable]
               + "\n현재수량: " + StoreTab.resourceList[4][selectedTable]
               + "\n주문량: " + StoreTab.resourceList[5][selectedTable]);
         }
         dispose();
      }
      else if(e.getActionCommand() == "rOrderCancel")
      {
         dispose();
      }   
   }
   
}