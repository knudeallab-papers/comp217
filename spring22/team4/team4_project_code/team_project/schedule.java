package team_project;

import java.util.Calendar;
import javax.swing.JTextField;
import java.io.Serializable;

public class schedule implements Serializable {

	private String scheduleName;
	private int importance;
	private int cycle;
	private boolean isMission = false;
	private Date scheduleDate;
	private Date nextDate;

	private boolean IsDone = false; // 달성여부

	public schedule() {
		scheduleName = "None";

		cycle = 0;
	}

	public schedule(schedule s) {
		scheduleName = s.getScheduleName();
		importance = s.getImportance();
		cycle = Integer.parseInt(s.getCycle());
		scheduleDate = s.getScheduleDate();
		nextDate = s.getNextDate();
		IsDone = s.getIsDone();
		isMission = s.getIsMission();

	}

	public schedule(String scheduleName, int cycle, int importance) {
		this.scheduleName = scheduleName;
		this.importance = importance;
		this.cycle = cycle;
		scheduleDate = new Date();
		calculateNextDate();

	}

	public void setIsMission(boolean t) {
		isMission = t;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public void setSheduleDate(Date d) {
		scheduleDate = new Date(d);
	}

	public void setGetIsDone(boolean t) {
		IsDone = t;
	}

	public boolean getIsMission() {
		return isMission;
	}

	public int getImportance() {
		return importance;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public String getCycle() {
		return Integer.toString(cycle);
	}

	public Date getScheduleDate() {
		return new Date(scheduleDate);
	}

	public Date getNextDate() {
		return new Date(nextDate);
	}

	public boolean getIsDone() {
		return IsDone;
	}

	public int getDeadLine() {
		return Date.convertDate(nextDate) - Date.getTodayConvertDate();
	}

	public void printSchedule() {
		System.out.println(IsDone + " / " + scheduleName + " / " + cycle + " / " + importance + " / " + getDeadLine());
	}

	public void ChangeIsDone() {
		IsDone = !IsDone;
	}

	public void calculateNextDate() { // 다음 주기 날짜 계산
		int year = this.scheduleDate.getYear();
		int month = this.scheduleDate.getMonth();
		int date = this.scheduleDate.getDay();

		date += cycle;

		while (date > Date.lastDate[month - 1]) {
			date = date - Date.lastDate[month - 1];
			month += 1;
		}

		while (month > 12) {
			month -= 12;
			year++;
		}

		nextDate = new Date(year, month, date);
	}

	public int refresh() {
		if (getDeadLine() <= 0) {

			boolean k = IsDone;
			scheduleDate = Date.getTodayDate();
			calculateNextDate();
			IsDone = false;
			if (k)
				return importance;
			else
				return -importance;
		}
		return 0;

	}

}
