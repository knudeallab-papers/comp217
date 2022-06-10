package TeamProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BorrowObject
{
    protected int objID;
    protected Date dateStart;
    protected Date dateHaveto;
    protected Date dateEnd;
    protected String studentID;
    protected boolean isBorrowing;

    protected String ERR_MSG = "Fatal Error.";

    public BorrowObject()
    {
        objID = 0;
        dateStart = new Date(2022, 6, 1, 12, 30, 10);
        dateHaveto = new Date(2022, 6, 2, 12, 30, 10);
        dateEnd = new Date(2022, 6, 1, 16, 30, 10);
        studentID = "1234567890";
        isBorrowing = false;
    }

    public BorrowObject(int newobjID, Date newStart, Date newEnd, String newstudentID)
    {
        if (isValid(newobjID, newStart, newEnd, newstudentID))
        {
            objID = newobjID;
            dateStart = newStart;
            dateHaveto = Date.AddDate(dateStart);
            dateEnd = newEnd;
            studentID = newstudentID;
            setObjStatus();
        }
        else
            errorhandler("invalid initialization");
    }

    public BorrowObject(int newobjID, Date newStart, Date newHaveto, Date newEnd, String newStudID)
    {
        if (isValid(newobjID, newStart, newHaveto, newEnd, newStudID))
        {
            objID = newobjID;
            dateStart = new Date(newStart);
            dateHaveto = new Date(newHaveto);
            //dateHaveto.AddDate(dateStart);
            dateEnd = new Date(newEnd);
            studentID = newStudID;
            setObjStatus();
        }
        else
            errorhandler("invalid initialization");
    }
    
    public BorrowObject(int newobjID, Date newStart, String newStudID)
    {
        if (isValid(newobjID, newStart, newStudID))
        {
            objID = newobjID;
            dateStart = new Date(newStart);
            studentID = newStudID;
            setObjStatus();
        }
        else
            errorhandler("invalid initialization");
    }
    
    //대여기록 확인용 -아진
    public BorrowObject(Date newStart, Date newEnd, boolean newisB)
    {
            dateStart = new Date(newStart);
            dateEnd = new Date(newEnd);
            isBorrowing = newisB;
            setObjStatus();
    }

    public BorrowObject(BorrowObject BObj)
    {
        objID = BObj.objID;
        dateStart = BObj.dateStart;
        dateHaveto = BObj.dateHaveto;
        dateEnd = BObj.dateEnd;
        studentID = BObj.studentID;
        isBorrowing = BObj.isBorrowing;
    }

    public int getObjID()
    {
        return this.objID;
    }

    public Date getDateStart()
    {
        return this.dateStart;
    }

    public Date getDateHaveto()
    {
        return this.dateHaveto;
    }

    public Date getDateEnd()
    {
        return this.dateEnd;
    }

    public String getStudentID()
    {
        return this.studentID;
    }

    public boolean getIsBorrowing()
    {
        return this.isBorrowing;
    }

    public void updateStart()
    {
        dateStart.setDate(true);
        dateHaveto.AddDate(dateStart);
        setObjStatus();
    }

    public void updateEnd()
    {
        dateEnd.setDate(true);
        setObjStatus();
    }

    public void setObjStatus()
    {
        if (dateStart.compareTime(dateEnd) < 0)
            isBorrowing = false;
        else
            isBorrowing = true;
    }
    
    //대여 기록 확인 용 - 아진
    public String getObjStatus()
    {
        if(isBorrowing == false) {
        	return "O";
        }
        else {
        	return "X";
        }
        
    }

    protected boolean isValid(int newID, Date newStart, Date newEnd, String newStudID)
    {
        return ((newID > 0) && (newStart.isValidTime()) && (newEnd.isValidTime()) && (newStudID.length() == 10));
    }

    protected boolean isValid(int newID, Date newStart, Date newHaveto, Date newEnd, String newStudID)
    {
        return ((newID > 0) && (newStart.isValidTime()) && (newHaveto.isValidTime()) && (newEnd.isValidTime()) && (newStudID.length() == 10));
    }
    
    protected boolean isValid(int newID, Date newStart, String newStudID)
    {
        return ((newID > 0) && (newStart.isValidTime()) && (newStudID.length() == 10));
    }
    public static ArrayList<String> Getter()
    {
        Scanner FileReader_OBJ = null;
        ArrayList<String> STR = new ArrayList<>();
        try
        {
            FileReader_OBJ = new Scanner(new FileInputStream("./data/log.txt"));
            while (FileReader_OBJ.hasNext())
            {
                STR.add(FileReader_OBJ.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        FileReader_OBJ.close();

        return STR;
    }
    public void errorhandler(String errorType)
    {
        System.err.println("Error occured in BorrowObj class: " + errorType);
    }
}
