package Model;
import java.util.ArrayList;

public class ClassInfo {
    private String classname;
    private FloorEnum floor;
    private String classroom;
    private ArrayList<Time> timetable;

    public ClassInfo(String classname, FloorEnum floor, String classroom, ArrayList<Time> arrayList) {
        this.classname = classname; //클래스명
        this.floor = floor; //층수
        this.classroom = classroom; //호수
        this.timetable = arrayList; // 요일, 시작시간, 종료시간
    }

    public ClassInfo() {
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public FloorEnum getFloor() {
        return floor;
    }

    public void setFloor(FloorEnum floor) {
        this.floor = floor;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public ArrayList<Time> getTimetable() {
        return timetable;
    }

    public void printTimetable() {
        for (Time time : timetable) {
            System.out.println(time);
        }
    }

    public void setTimetable(ArrayList<Time> timetable) {
        this.timetable = timetable;
    }
}
