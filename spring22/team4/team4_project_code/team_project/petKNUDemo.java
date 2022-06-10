package team_project;

import java.util.ArrayList;
import java.util.Calendar;

public class petKNUDemo {

	public static FirstPage mainPage;
	public static SecondPage registerPage;
	public static ThirdPage infoPage;
	public static ErrorMsg error;
	public static FourthPage forthPage;

	public static void main(String[] args) {

		openFirstPage();
	}

	public static void openFirstPage() {
		mainPage = new FirstPage();
		mainPage.setVisible(true);
	}

	public static void closeFirstPage() {
		mainPage.dispose();
	}

	public static void openRegisterPage() {
		registerPage = new SecondPage();
		registerPage.setVisible(true);
	}

	public static void closeRegisterPage() {
		registerPage.dispose();
	}

	public static void closeInfoPage() {
		infoPage.dispose();
	}

	public static void openInfoPage(ArrayList<petData> list, String name) {
		infoPage = new ThirdPage(list, name);
		infoPage.setVisible(true);
	}

	public static void errorMsg(String err) {
		error = new ErrorMsg(err);
		error.setVisible(true);
	}

	public static void openForthPage(petData p, schedule s) {
		forthPage = new FourthPage(p, s);
		forthPage.setVisible(true);
	}

	public static void reopenThirdPage(ArrayList<petData> list, String name) {
		closeFirstPage();
		closeInfoPage();
		openFirstPage();
		openInfoPage(list, name);

	}

}
