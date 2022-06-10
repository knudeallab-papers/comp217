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
	
	
	
	// Promotion.txt 에 있는 정보를 읽어와서 ArrayList에 저장
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
	
	
	
	// 입력받은 바코드에 해당하는 행사코드를 찾아서 반환
	public int searchPromotion(String code) {
		boolean isExist = false;
		for (Promotion A : PromotionList)
			if (A.Barcode.equals(code)) {
				isExist = true;
				return A.PromotionCode;
			}
			
		if (isExist == false) {
			System.out.println("행사여부 등록이 필요합니다.");
			System.exit(0);
		}
			
		
		return -1;
	}
	
	
	
	
	
}
