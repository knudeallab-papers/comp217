package team_project;

import java.util.Random;

public class makeMission {
	private static Random ran = new Random();
	private static int num = ran.nextInt(10);

	public static String make_Mission() {
		if (num == 0) {
			return "꽃과 함께 사진 찍기 !";
		} else if (num == 1) {
			return "간식 들고 산책 가기 !";
		} else if (num == 2) {
			return "다른 강아지와 인사하기 ! ";
		} else if (num == 3) {
			return "예쁜 옷 입고 산책 가기 ! ";
		} else if (num == 4) {
			return "공원으로 산책 가기 ! ";
		} else if (num == 5) {
			return "강아지 동반 카페 들르기 ! ";
		} else if (num == 6) {
			return "좋아하는 장난감가지고 나가기 ! ";
		} else if (num == 7) {
			return "산책 후 털 빗어주기 ! ";
		} else if (num == 8) {
			return "벤치에 잠시 앉아 계절 느끼기 ! ";
		} else if (num == 9) {
			return "푸른 식물과 함께 사진찍기 ! ";
		} else if (num == 10) {
			return "오늘 하루 산책 하는 모습을 SNS에 자랑하기 ! ";
		}

		return "Error!";
	}

	public static String make_distance(String ageInfo) {
		// 추천 산책 거리 계산 - 입력된 동물 나이 0살 : 10 - 15분 / 1살 - 2살: 40분 / 2살 - 7살 : 30분 / 7살 :
		if (Integer.parseInt(ageInfo) >= 0 && Integer.parseInt(ageInfo) < 1) {
			return "산책 추천 시간은 10 - 15 분,  달성했나요 ?";
		} else if (Integer.parseInt(ageInfo) >= 1 && Integer.parseInt(ageInfo) < 2) {
			return "산책 추천 시간은 20 - 40 분,  달성했나요 ?";
		} else if (Integer.parseInt(ageInfo) >= 2 && Integer.parseInt(ageInfo) < 7) {
			return "산책 추천 시간은 10 - 30 분,  달성했나요 ?";
		} else if (Integer.parseInt(ageInfo) >= 7) {
			return "산책 추천 시간은 10 - 20 분,  달성했나요 ?";
		}
		return "Error!";
	}

}
