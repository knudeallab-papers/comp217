package Login;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class main_startProgram {
	public static void main(String[] args){
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { //JDK�� ����� lookandfeel ���
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
				else { //Nimbus lookandfeel�� ���� ���
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
