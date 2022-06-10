package Model;

public class Time {
    private String day;
    private String start;
    private String end;

    public Time(String day, String start, String end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public Time() {}

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {return (day + ", " + start + ", " + end);}
}
