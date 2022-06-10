package TeamProject;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class PromotionManager {
	protected ArrayList<Promotion> PromotionList;
	
	
	public PromotionManager() {
		PromotionList = new ArrayList<Promotion>();
	}
	
	
	
	// Promotion.txt �� �ִ� ������ �о�ͼ� ArrayList�� ����
	public void load() {
		try {
			String path = PromotionManager.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path,"UTF-8");
			File file = new File(path + "../../src/docs/Promotion.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "/");
				while(st.hasMoreTokens()) {
					String BarCode = st.nextToken();
					String ItemName = st.nextToken();
					int ItemEA = Integer.parseInt(st.nextToken());
					Promotion temp = new Promotion(BarCode, ItemName, ItemEA);
					PromotionList.add(temp);
				}
			}
			br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
	
	
	
	// �Է¹��� ���ڵ忡 �ش��ϴ� ����ڵ带 ã�Ƽ� ��ȯ
	public int searchPromotion(String code) {
		boolean isExist = false;
		for (Promotion A : PromotionList)
			if (A.Barcode.equals(code)) {
				isExist = true;
				return A.PromotionCode;
			}
			
		if (isExist == false) {
			System.out.println("��翩�� ����� �ʿ��մϴ�.");
			System.exit(0);
		}
			
		
		return -1;
	}
	
	
	
	
	
}
