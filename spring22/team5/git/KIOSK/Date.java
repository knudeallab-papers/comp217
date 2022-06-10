package TeamProject;

import java.time.LocalDateTime;

public class Date
{
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private boolean afterNoon;

    LocalDateTime now = LocalDateTime.now();

    public Date()
    {
        setDate(true);
    }

    public Date(boolean NowOrDefault)
    {
        setDate(NowOrDefault);
    }

    public Date(int newYear, int newMonth, int newDay, int newHour, int newMinute, int newSecond)
    {
        if (isValidTime(newYear, newMonth, newDay, newHour, newMinute, newSecond))
        {
            setDate(newYear, newMonth, newDay, newHour, newMinute, newSecond);
        }
        else
            errorhandler("invalid initializing");
    }

    public Date(Date origin)
    {// copy constructor

        if (isValidTime(origin.getYear(), origin.getMonth(), origin.getDay(), origin.getHour(), origin.getMinute(),
                origin.getSecond()))
        {
            setDate(origin.getYear(), origin.getMonth(), origin.getDay(), origin.getHour(), origin.getMinute(),
                    origin.getSecond());
        }
        else
            errorhandler("invalid initializing");
    }

    /////여기 추가//////
    public static Date AddDate(Date origin)
    {
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        Date newDate = new Date(year, month, day, hour, minute, second);
        return newDate;
    }
    ////////////////////////

    public void setDate(boolean NowOrDefault)
    {
        if (NowOrDefault)
        {
            now = LocalDateTime.now();
            year = now.getYear();
            month = now.getMonthValue();
            day = now.getDayOfMonth();
            hour = now.getHour();

            if (hour > 12)
                afterNoon = true;
            else
                afterNoon = false;

            minute = now.getMinute();
            second = now.getSecond();
        }
        else
        {
            setDate(2021, 1, 1, 0, 0, 0);
        }
    }

    public void setDate(int newYear, int newMonth, int newDay, int newHour, int newMinute, int newSecond)
    {
        if (isValidTime(newYear, newMonth, newDay, newHour, newMinute, newSecond))
        {
            year = newYear;
            month = newMonth;
            day = newDay;
            hour = newHour;

            if (hour > 12)
                afterNoon = true;
            else
                afterNoon = false;

            minute = newMinute;
            second = newSecond;
        }
        else
            errorhandler("invalid setting");
    }

    public boolean isValidTime()
    {
        return ((year > 2020) && (year <= 3000) && (month > 0) && (month <= 12) && (day > 0) && (day <= 31)
                && (hour >= 0) && (hour < 24) && (minute >= 0) && (minute < 60) && (second >= 0) && (second < 60));
    }

    public boolean isValidTime(int year, int month, int day, int hour, int minute, int second)
    {
        return ((year > 2020) && (year <= 3000) && (month > 0) && (month <= 12) && (day > 0) && (day <= 31)
                && (hour >= 0) && (hour < 24) && (minute >= 0) && (minute < 60) && (second >= 0) && (second < 60));
    }

    public int compareTime(Date other)
    {
        if (year - other.year != 0)
            return year - other.year;

        if (month - other.month != 0)
            return month - other.month;

        if (day - other.day != 0)
            return day - other.day;

        if (hour - other.hour != 0)
            return hour - other.hour;

        if (minute - other.minute != 0)
            return minute - other.minute;

        if (second - other.second != 0)
            return second - other.second;

        else
            return 0;
    }

    public int getYear()
    {
        return year;
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public int getHour()
    {
        return hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public int getSecond()
    {
        return second;
    }

    public String toString()
    {

        return "" + year + "-" + month + "-" + day + " " + ((hour > 12) ? hour - 12 : hour) + ":" + minute + ":"
                + second + (afterNoon ? " PM" : " AM");
    }
    public String toLogString()
    {

        return year + "/" + month + "/" + day + "/" + hour + ":" + minute;
    }
    
    public String toFileString()
    {
        return "" + year + "\t" + month + "\t" + day + "\t" + hour + "\t" + minute + "\t"
                + second;
    }

    private void errorhandler(String errorType)
    {

        System.err.println("Error occured in Date class: " + errorType);
    }
}