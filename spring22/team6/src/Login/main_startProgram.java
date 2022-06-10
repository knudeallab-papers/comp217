package Login;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class main_startProgram {
	public static void main(String[] args){
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { //JDK에 내장된 lookandfeel 사용
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
				else { //Nimbus lookandfeel이 없을 경우
					UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
				}
			}
			login window = new login();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
