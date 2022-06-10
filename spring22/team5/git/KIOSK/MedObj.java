package TeamProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MedObj
{
    protected int med_ID;
    protected Date dateStart;
    protected String studentID;
    protected int med_num;
    protected boolean isAvailable;

    protected String ERR_MSG = "Fatal Error.";

    public MedObj()
    {
        med_ID = 0;
        dateStart = new Date(2022, 6, 1, 12, 30, 10);
        studentID = "1234567890";
        med_num = 0;
    }

    //AddMedi에서 medInfo 확인용
    //Borrow에서 med 확인용
    public MedObj(int newID, String newStudentID, int newNum)
    {
        if (isValid(newID, newStudentID, newNum))
        {
            med_ID = newID;
            studentID = newStudentID;
            setMedStatus();
        }
        else
            errorhandler("invalid initialization");
    }

    //CheckBorrow에서 medInfo 확인용
    public MedObj(int newID, Date newStart, String newStudentID, int newNum)
    {
        med_ID = newID;
        dateStart = newStart;
        studentID = newStudentID;
        med_num = newNum;
        setMedStatus();
    }

    public MedObj(MedObj BObj)
    {

        med_ID = BObj.med_ID;
        dateStart = BObj.dateStart;
        studentID = BObj.studentID;
        med_num = BObj.med_num;
    }

    public boolean equals(Object otherObj)
    {

        if (otherObj == null) return false;
        else if (!(otherObj instanceof MedObj))
            return false;
        else
        {
            MedObj otherMed = (MedObj) otherObj;
            return (med_ID == otherMed.med_ID &&
                    dateStart.equals(otherMed.dateStart) &&
                    studentID.equals(otherMed.studentID) &&
                    med_num == otherMed.med_num &&
                    isAvailable == otherMed.isAvailable);
        }
    }

    public static MedObj[] dataGetter()
    {
        Scanner FileReader_OBJ = null;
        MedObj[] ObjList = new MedObj[3];

        try
        {
            FileReader_OBJ = new Scanner(new FileInputStream("./data/med.txt"));
            int count = 0;
            while (FileReader_OBJ.hasNext())
            {
                int ID = FileReader_OBJ.nextInt();
                int Year = FileReader_OBJ.nextInt();
                int Month = FileReader_OBJ.nextInt();
                int Day = FileReader_OBJ.nextInt();
                int Hour = FileReader_OBJ.nextInt();
                int Min = FileReader_OBJ.nextInt();
                int Sec = FileReader_OBJ.nextInt();
                String StudID = FileReader_OBJ.next();
                int Amount = FileReader_OBJ.nextInt();

                System.out.println(ID + " " + Sec + " " + StudID + " " + Amount);

                ObjList[count++] = new MedObj(ID, new Date(Year, Month, Day, Hour, Min, Sec), StudID, Amount);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        FileReader_OBJ.close();

        for (MedObj m : ObjList)
        {
            System.out.println(m.toFileString());
        }
        return ObjList;
    }

    public void setMed_num(int i)
    {
        med_num = med_num + i;
    }

    public int getID()
    {
        return med_ID;
    }

    public Date getDateStart()
    {
        return new Date(dateStart);
    }

    public String getStudentID()
    {
        return studentID;
    }

    public int getNum()
    {
        return med_num;
    }

    public boolean getIsAvailble()
    {
        return this.isAvailable;
    }

    public void setMedStatus()
    {
        if (med_num <= 0)
            isAvailable = false;
        else
            isAvailable = true;

    }

    private boolean isValid(int newID, String newStudID, int newNum)
    {
        return ((newID > 0) && (newStudID.length() == 10) && (newNum > 0));
    }

    private boolean isValid(int newID, Date newStart, String newStudID, int newNum)
    {
        return ((newID > 0) && (newStart.isValidTime()) && (newStudID.length() == 10) && (newNum > 0));
    }

    public String toFileString()
    {
        return "" + med_ID + "\t" + dateStart.toFileString() + "\t" + studentID + "\t" + med_num;
    }

    private void errorhandler(String errorType)
    {
        System.err.println("Error occured in Abstract BorrowObj class: " + errorType);
    }

}
