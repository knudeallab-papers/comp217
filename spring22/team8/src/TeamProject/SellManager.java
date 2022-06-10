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
		for (int i = 0; i < SellableTable.getRowCount(); i++) {     // SellableTable 각 행에 대하여 진행
			String Barcode = (String)SellableTable.getValueAt(i, 0);
			int SellEA = Integer.parseInt((String)SellableTable.getValueAt(i, 2));
			while (SellEA != 0) {                                 // 판매개수가 0개가 되면 종료
			for (int j = 0; j < SellableList.size(); j++) {
				if (Barcode.equals(SellableList.get(j).id)) {     // SellableList에서 Barcode를 검색한 후
					SellableList.get(j).num -= SellEA;            // 해당 Item의 num을 빼준다.
					if (SellableList.get(j).num <= 0) {           // 판매개수가 더 커서 재고의 개수가 음수가 되거나 0이 되었다면
						SellEA = Math.abs(SellableList.get(j).num); // 절대값을 씌워서 남은 판매개수 만큼 더 팔기 위해 탐색
						SellableList.remove(j);                     // 재고가 0이나 음수가 된 항목은 삭제
						j--;                                       // 삭제하였으므로 j를 하나 줄여서 계속 탐색
					} else {                           
						SellEA = 0;                                // 판매를 해도 재고가 0이 아니라면 남은 판매개수를 0으로 만들어서 종료
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
