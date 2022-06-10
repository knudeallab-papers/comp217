package Model;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeCalculator {

	LocalTime nowTime; // 현재 시간
	LocalTime startTime; // 시작 시간
	LocalTime endTime; // 종료 시간
	boolean isEmpty = true;
	long LeftTime = 99999;

	private String result="";

	public TimeCalculator() {}

	public TimeCalculator(String input, String start, String end){
		nowTime = StringToLocal(input);
		startTime = StringToLocal(start);
		endTime = StringToLocal(end);
	}

	public TimeCalculator(String input){
		nowTime = StringToLocal(input);
	}

	public void setTimes(String start, String end){
		startTime = StringToLocal(start);
		endTime = StringToLocal(end);
	}

	public LocalTime StringToLocal(String time){
		String[] arr = time.split(":");
		int[] arrInt = new int[arr.length];
		for(int i = 0; i< arr.length; i++) arrInt[i] = Integer.parseInt(arr[i]);

		return LocalTime.of(arrInt[0],arrInt[1]);
	}
	
	public void compareTime() {

		//사용중일 때 -> (시작시간하고 끝시간 사이인경우 || 현재시각이랑 시작시간이 같은 경우)
		if (nowTime.isAfter(startTime) && nowTime.isBefore(endTime) || nowTime.equals(startTime)) {

			if(nowTime.until(endTime, ChronoUnit.MINUTES) >= 60){
				long hour = 0;
				long min = 0;
				hour = (nowTime.until(endTime, ChronoUnit.MINUTES))/60;
				min = (nowTime.until(endTime, ChronoUnit.MINUTES))%60;
				result =  "<html>현재 강의실이 사용중입니다.<br>" + hour + "시간 " + min + "분 후에 강의가 마칩니다.</html>";
			}else {
				result = "<html>현재 강의실이 사용중입니다.<br>" + nowTime.until(endTime, ChronoUnit.MINUTES) + "분 후에 강의가 마칩니다.</html>";
			}
			LeftTime = nowTime.until(endTime, ChronoUnit.MINUTES);
			isEmpty = false;
		}

		//사용중이지 않을때 && 사용시작 시간이 나보다 뒤일때
		else if (nowTime.isBefore(startTime)) {
			if(LeftTime > nowTime.until(startTime, ChronoUnit.MINUTES)) {
				LeftTime = nowTime.until(startTime, ChronoUnit.MINUTES);

				if (LeftTime >= 60) {
					long hour = 0;
					long min = 0;
					hour = LeftTime/60;
					min = LeftTime%60;
					result = "<html>현재 강의실이 비어있습니다.<br> 다음 수업까지는 " + hour + "시간 " + min + "분 남았습니다.</html>";
				} else {
					result = "<html>현재 강의실이 비어있습니다.<br> 다음 수업까지는 " + LeftTime + "분 남았습니다.</html>";
				}
			}
		}
		
	}

	public LocalTime getNowTime() {
		return nowTime;
	}

	public void setNowTime(LocalTime nowTime) {
		this.nowTime = nowTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public boolean isEmpty() {return isEmpty; }

	public void setEmpty(boolean empty) {
		isEmpty = empty;
	}

	public long getLeftTime() {
		return LeftTime;
	}

	public void setLeftTime(long leftTime) {
		LeftTime = leftTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
