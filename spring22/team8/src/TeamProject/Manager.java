package TeamProject;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Manager {
	private ArrayList<String> history;
	private ArrayList<ShortPartTimer> shortList;
	private ArrayList<LongPartTimer> longList;
	private ArrayList<Salary> salaryList;
	public Manager() {
		history = new ArrayList<String>();
		shortList = new ArrayList<ShortPartTimer>();
		longList = new ArrayList<LongPartTimer>();
		salaryList = new ArrayList<Salary>();
		setEmployeeList();
		setHistory();
		recordEmployeeList();
	}
	//setter
	private void setHistory() {
		try {
			String s;
			String path = GoToWork.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path,"UTF-8");
			File file = new File(path + "../../src/docs/history.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((s = br.readLine()) != null) {
				history.add(s);
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void updateHistory() {
		if(history.isEmpty() != true) {
			history.sort(Comparator.naturalOrder());
			for(String s: history) {
				StringTokenizer st = new StringTokenizer(s);
				if(st.hasMoreTokens() == true) {
					int weekNum = Integer.parseInt(st.nextToken());
					st.nextToken();
					String id = st.nextToken();//id
					int startHour = Integer.parseInt(st.nextToken());//start
					int endHour = Integer.parseInt(st.nextToken());
					Integer.parseInt(st.nextToken());
					int dayHour, nightHour;
					//calculate dayHour, nightHour
					dayHour = nightHour = 0;
					if(endHour > startHour) {//in oneDay
						for(int i=startHour+1; i<=endHour; i++) {
							if(6 < i && i <= 22)
								dayHour++;
							else
								nightHour++;
						}
					}else if(endHour < startHour) {//until nextDay
						for(int i=startHour+1; i<=24; i++) {
							if(6 < i && i<= 22)
								dayHour++;
							else
								nightHour++;
						}
						if(endHour != 24 || endHour != 00) {
							for(int i=1; i<=endHour; i++) {
								if(6 < i && i<= 22)
									dayHour++;
								else
									nightHour++;
							}
						}
					}else {
						dayHour = nightHour = 0;
					}
					
					switch(id.charAt(6)) {
						case '1':
							if(shortList.isEmpty() == true)
								break;
							for (ShortPartTimer someone: shortList) {
								if(someone.getId().equals(id)) {
									someone.setDayHours(someone.getDayHours() + dayHour);
									someone.setNightHours(someone.getNightHours() + nightHour);
									break;
								}
							}
							break;
						case '2':
							if(longList.isEmpty() == true)
								break;
							boolean isFind = false;//upgrade
							for(LongPartTimer someone: longList) {
								if(someone.getId().equals(id)) {
									isFind = true;
									if(someone.getLastWeekNum() != weekNum) {
										someone.calHolidayPay();
										someone.readyToSeverencePayList();
										someone.setLastWeekNum(weekNum);
										someone.setWeeklyDayHour(0);
										someone.setWeeklyNightHour(0);
										someone.setIsYear(someone.getIsYear() + 1);
										someone.setDayHours(someone.getDayHours() + dayHour);
										someone.setNightHours(someone.getNightHours() + nightHour);
										someone.addWeeklyHour(dayHour, nightHour);
										if(someone.isOverYear()) //over one year
											upgradeToSalary(someone);
									}
									else {
										someone.setDayHours(someone.getDayHours() + dayHour);
										someone.setNightHours(someone.getNightHours() + nightHour);
										someone.addWeeklyHour(dayHour, nightHour);
									}
									break;
								}
							}
							if(isFind == false) {
								for(Salary someone: salaryList) {
									if(someone.getId().equals(id.substring(0, 6) + "3")) {
										if(someone.getLastWeekNum() != weekNum) {
											someone.calHolidayPay();
											someone.setLastWeekNum(weekNum);
											someone.setIsYear(someone.getIsYear() + 1);
										}
										someone.setDayHours(someone.getDayHours() + dayHour);
										someone.setNightHours(someone.getNightHours() + nightHour);
										someone.addWeeklyHour(dayHour, nightHour);
										break;
									}
								}
							}
							break;
						case '3':
							if(salaryList.isEmpty() == true)
								break;
							for(Salary someone: salaryList) {
								if(someone.getId().equals(id)) {
									isFind = true;
									if(someone.getLastWeekNum() != weekNum) {
										someone.calHolidayPay();
										someone.readyToSeverencePayList();
										someone.setLastWeekNum(weekNum);
										someone.setWeeklyDayHour(0);
										someone.setWeeklyNightHour(0);
										someone.setIsYear(someone.getIsYear() + 1);
									}
									someone.setDayHours(someone.getDayHours() + dayHour);
									someone.setNightHours(someone.getNightHours() + nightHour);
									someone.addWeeklyHour(dayHour, nightHour);
									break;
								}
							}
							break;
						default:
							System.err.println("caanot find worker code");
							System.exit(0);
					}
				}
			}
			recordEmployeeList();
		}
	}
	private void setEmployeeList() {
		try {
			String s;
			String path = GoToWork.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path,"UTF-8");
			File file = new File(path + "../../src/docs/employees.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((s = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s);
				if(st.hasMoreTokens() == true) {
					String id = st.nextToken();
					String name = st.nextToken();
					double hourlyWage = Double.parseDouble(st.nextToken());
					int dayHour = Integer.parseInt(st.nextToken());
					int nightHour = Integer.parseInt(st.nextToken());
					switch(id.charAt(6)) {
						case '1': //ShortPartTimer
							ShortPartTimer someone1 = new ShortPartTimer(id, name, hourlyWage, dayHour, nightHour);
							shortList.add(someone1);
							break;
						case '2': //LongPartTimer
							double holidayPay = Double.parseDouble(st.nextToken());
							int lastWeeklyNum = Integer.parseInt(st.nextToken());
							int isYear = Integer.parseInt(st.nextToken());
							int weeklyDay = Integer.parseInt(st.nextToken());
							int weeklyNight = Integer.parseInt(st.nextToken());
							double[] wageList = new double[13];
							for(int i=0; i<13; i++) {
								wageList[i] = Double.parseDouble(st.nextToken());
							}
							LongPartTimer someone2 = new LongPartTimer(id, name, hourlyWage, dayHour, nightHour,
																holidayPay, lastWeeklyNum, isYear, weeklyDay,
																weeklyNight, wageList);
							longList.add(someone2);
							break;
						case '3':
							holidayPay = Double.parseDouble(st.nextToken());
							lastWeeklyNum = Integer.parseInt(st.nextToken());
							isYear = Integer.parseInt(st.nextToken());
							weeklyDay = Integer.parseInt(st.nextToken());
							weeklyNight = Integer.parseInt(st.nextToken());
							wageList = new double[13];
							for(int i=0; i<13; i++) {
								wageList[i] = Double.parseDouble(st.nextToken());
							}
							double severencePay = Double.parseDouble(st.nextToken());
							Salary someone3 = new Salary(id, name, hourlyWage, dayHour, nightHour,
																holidayPay, lastWeeklyNum, isYear, weeklyDay,
																weeklyNight, wageList, severencePay);
							salaryList.add(someone3);
							break;
						default:
							System.err.println("caanot find worker code");
							System.exit(0);
					}
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//getter
	public ArrayList<Employee> getEmployeeList() {
		ArrayList<Employee> temp = new ArrayList<Employee>();
		for(ShortPartTimer x: shortList)
			temp.add(new ShortPartTimer(x));
		for(LongPartTimer x: longList)
			temp.add(new LongPartTimer(x));
		for(Salary x: salaryList)
			temp.add(new Salary(x));
		return temp;
	}
	public void recordEmployeeList() {
		try {
			String path = GoToWork.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path,"UTF-8");
			File file = new File(path + "../../src/docs/employees.txt");
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			for(ShortPartTimer someone: shortList) {
				br.write(someone.id + " " + someone.name + " " + Double.toString(someone.hourlyWage) + " "
						+ someone.dayHours + " " + someone.nightHours);
				br.newLine();
			}for(LongPartTimer someone: longList) {
				br.write(someone.id + " " + someone.name + " " + Double.toString(someone.hourlyWage) + " "
						+ someone.dayHours + " " + someone.nightHours + " " + someone.getHolidayPay() + " "
						+ someone.getLastWeekNum() + " " + someone.getIsYear() + " " + someone.getWeeklyDayHour() + " "
						+ someone.getWeeklyNightHour() + " ");
				double[] wageList = new double[13];
				wageList = someone.getWageList();
				for(int i=0; i<13; i++) {
					br.write(wageList[i] + " ");
				}
				br.newLine();
			}for(Salary someone: salaryList) {
				br.write(someone.id + " " + someone.name + " " + Double.toString(someone.hourlyWage) + " "
						+ someone.dayHours + " " + someone.nightHours + " " + someone.getHolidayPay() + " "
						+ someone.getLastWeekNum() + " " + someone.getIsYear() + " " + someone.getWeeklyDayHour() + " "
						+ someone.getWeeklyNightHour() + " ");
				double[] wageList = new double[13];
				wageList = someone.getWageList();
				for(int i=0; i<13; i++) {
					br.write(wageList[i] + " ");
				}
				br.write(Double.toString(someone.getSeverencePay()));
				br.newLine();
			}
			br.flush();
			br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("cannot read file");
			System.exit(0);
		}
	}
	//reset
	public void resetEmployeeList() {
		for(ShortPartTimer x: shortList) {
			x.resetHours();
		}
		for(LongPartTimer x: longList) {
			x.resetHours();
			x.setHolidayPay(0);
			x.setLastWeekNum(0);
			x.setWeeklyDayHour(0);
			x.setWeeklyNightHour(0);
		}
		
		for(Salary x: salaryList) {
			x.resetHours();
			x.setHolidayPay(0);
			x.setLastWeekNum(0);
			x.setWeeklyDayHour(0);
			x.setWeeklyNightHour(0);
		}
		recordEmployeeList();
	}
	public void resetHistory() {
		BufferedWriter bw;
		try {
			String path = GoToWork.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path,"UTF-8");
			File file = new File(path + "../../src/docs/history.txt");
			bw = new BufferedWriter(new FileWriter(file));
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		history.clear();
	}
	public void hireWorker(String id, String name, double hourlyWage) {
		// TODO Auto-generated method stub
		switch(id.charAt(6)) {
		case '1': //ShortPartTimer
			ShortPartTimer someone1 = new ShortPartTimer(id, name, hourlyWage);
			shortList.add(someone1);
			break;
		case '2': //LongPartTimer
			LongPartTimer someone2 = new LongPartTimer(id, name, hourlyWage);
			longList.add(someone2);
			break;
		case '3':
			Salary someone3;
			someone3 = new Salary(id, name, hourlyWage);
			salaryList.add(someone3);
			break;
		default:
			System.err.println("caanot find worker code");
			System.exit(0);
		}
		recordEmployeeList();
	}
	public void fireEmployee(String id) {
		// TODO Auto-generated method stub
		switch(id.charAt(6)) {
		case '1': //ShortPartTimer
			for(ShortPartTimer someone1: shortList) {
				if(someone1.getId().equals(id)) {
					shortList.remove(someone1);
					break;
				}
			}
			break;
		case '2': //LongPartTimer
			for(LongPartTimer someone2: longList) {
				if(someone2.getId().equals(id)) {
					longList.remove(someone2);
					break;
				}
			}
			break;
		case '3':
			for(Salary someone3: salaryList) {
				if(someone3.getId().equals(id)) {
					salaryList.remove(someone3);
					break;
				}
			}
			break;
		default:
			System.err.println("caanot find worker code");
			System.exit(0);
		}
		recordEmployeeList();
	}
	public void upgradeToSalary(LongPartTimer someone) {
		longList.remove(someone);
		someone.id = someone.id.substring(0, 6) + "3";
		Salary someone1 = new Salary(someone);
		salaryList.add(someone1);
		recordEmployeeList();
	}
}
