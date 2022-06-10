
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class course implements Serializable{
	ArrayList<String> theme = new ArrayList<String>();
	HashMap<Character, String[]> time=new HashMap<Character, String[]>(); // key: 요일 정보 , value: 시작 시간과 종료 시간
	private ArrayList<String> temp = new ArrayList<String>(10);
	ArrayList<String> timeList =new ArrayList<String>(10);
	private String department; // 개설 대학 개념으로 생각하면 될 것 같음
	private String major; //개설 학과 + 개설 전공 및 분반
	private String code; //교과목 명
	private String type; // 저 위에 있는 분류기준 (구분)
	private String classRoom; //강의실
	private String maxPeople; //수강신청 정원 
	private String people; //신청인원
	private String gradePoint; //학점
	private String online; //인강
	private String culture; //인문교양
	private String timeString; //시간표
	private String name; //교과목 명 만들기
	private String prof; //교수님 이름 
   //테마 돌려주기
	public void save() {
		setCulture();
		setDepartment();
		setMajor();
		setType();
		setClassRoom();
		setPeople();
		setMaxPeople();
		setCulture();
		setProf();
		settimeString();
		setGradePoint();
		setType();
		setCode();
		setName();
		setOnline();
		setTimeTable();
		//setTimeSet();
	}
	public ArrayList<String> getTheme() {
		Collections.sort(theme);
		return theme;
	}
   public void addString(String theme) {
      this.theme.add(theme);
   }
//   public void setTimeSet() {
//	   timeSet[0] = timeString.charAt(1);
//	   timeSet[1] = timeString.charAt(timeString.length()-2);
//   }
   public void setTemp(ArrayList<String> a) {
      temp.addAll(a);
   }
   public ArrayList<String> getTemp() {
      return temp;
   }
   public HashMap<Character, String[]> getTime() {
      return time;
   }
   public void setCulture() {
	    this.culture = temp.get(17);
   }
   public String getCulture() {
	    return this.culture;
   }
   public void settimeString() {
	    this.timeString = temp.get(9);
   }
   public String gettimeString() {
	    return this.timeString;
   }
   //시간표의 시작과 끝 설정 해주기
   public void setTimeTable() {
	   String[] words = timeString.split("\n");
//	   for(int i=0; i<words.length; i++) {
//		   System.out.println(words[i].charAt(0));
//	   }
	   String[] startEnd = new String[3];
	   
	   for(int i=0; i<words.length; i++) {
		   startEnd[0] = words[i].substring(1, 2);
		   startEnd[1] = words[i].substring(3, 4);
		  /* if(words.length>=5) {
			   startEnd[2] = words[i].substring(5);
		   }*/
		   time.put(words[i].charAt(0), startEnd);
	   }
   }
   public void setType() {
	    this.type = temp.get(6);
   }
   public String getType() {
	    return this.type;
   }
   public void setName() {
	    this.name = temp.get(5);
   }
   public String getName() {
	    return this.name;
   }
   public void setProf() {
	    this.prof = temp.get(14);
   }
   public String getProf() {
	    return this.prof;
   }
   public void setDepartment() {
	    this.department = temp.get(11);
   }
   public String getDepartment() {
	    return this.department;
   }
   public void setMajor() {
	    this.major = temp.get(12);
   }
   public String getMajor() {
	    return this.major;
   }
   public void setCode() {
	    this.code = temp.get(1);	
   }
   public String getCode() {
	    return this.code;
   }
   public void setClassRoom() {
	    this.classRoom = temp.get(10);
   }
   public String getClassRoom() {
	    return this.classRoom;
   }
   public void setMaxPeople() {
	    this.maxPeople = temp.get(7);
   }
   public String getMaxPeople() {
	    return this.people;
   }
   public void setPeople() {
	    this.people = temp.get(8);
   }
   public String getPeople() {
	    return this.people;
   }
   public void setOnline() {
	    this.online = temp.get(16); 
   }
   public String getOnline() {
	    return this.online;
   }
   public void setGradePoint() {
	    this.gradePoint = temp.get(2);
   }
   public String getGradePoint() {
	    return this.gradePoint;
   }
   
}
