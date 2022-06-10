package TeamProject;

import java.util.ArrayList;

import javax.swing.JTable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SellManager {
	protected ArrayList<Item> SellableList;
	protected JTable SellableTable;
	
	
	
	public SellManager(ArrayList<Item> List, JTable Table) {
		SellableList = List;
		SellableTable = Table;
	}
	
	
	
	public void SellAndSave() {
		for (int i = 0; i < SellableTable.getRowCount(); i++) {     // SellableTable �� �࿡ ���Ͽ� ����
			String Barcode = (String)SellableTable.getValueAt(i, 0);
			int SellEA = Integer.parseInt((String)SellableTable.getValueAt(i, 2));
			while (SellEA != 0) {                                 // �ǸŰ����� 0���� �Ǹ� ����
			for (int j = 0; j < SellableList.size(); j++) {
				if (Barcode.equals(SellableList.get(j).id)) {     // SellableList���� Barcode�� �˻��� ��
					SellableList.get(j).num -= SellEA;            // �ش� Item�� num�� ���ش�.
					if (SellableList.get(j).num <= 0) {           // �ǸŰ����� �� Ŀ�� ����� ������ ������ �ǰų� 0�� �Ǿ��ٸ�
						SellEA = Math.abs(SellableList.get(j).num); // ���밪�� ������ ���� �ǸŰ��� ��ŭ �� �ȱ� ���� Ž��
						SellableList.remove(j);                     // ��� 0�̳� ������ �� �׸��� ����
						j--;                                       // �����Ͽ����Ƿ� j�� �ϳ� �ٿ��� ��� Ž��
					} else {                           
						SellEA = 0;                                // �ǸŸ� �ص� ��� 0�� �ƴ϶�� ���� �ǸŰ����� 0���� ���� ����
					}
				}
			}
		}
	}
		
	
		try {
			String path = SellManager.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path,"UTF-8");
			File file = new File(path + "../../src/docs/ItemList.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for(Item A : SellableList) {
				writer.write(A.toString());
			}
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	
	
}
