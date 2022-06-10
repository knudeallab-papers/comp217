package TeamProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UmmObj extends BorrowObject
{

    public UmmObj()
    {
        super();
    }

    public UmmObj(int objID, Date dateStart, Date dateEnd, String studentID)
    {
        super(objID, dateStart, dateEnd, studentID);
        dateHaveto = new Date();
        dateHaveto.AddDate(dateStart);
        setObjStatus();
    }

    public UmmObj(int objID, Date dateStart, Date dateHaveto, Date dateEnd, String studentID)
    {
        super(objID, dateStart, dateHaveto, dateEnd, studentID);
        setObjStatus();
    }

    public UmmObj(MatObj BObj)
    {
        objID = BObj.objID;
        dateStart = BObj.dateStart;
        dateHaveto = BObj.dateHaveto;
        dateEnd = BObj.dateEnd;
        studentID = BObj.studentID;
        isBorrowing = BObj.isBorrowing;
    }

    public boolean equals(Object otherObj)
    {
        if (otherObj == null) return false;
        else if (!(otherObj instanceof UmmObj))
            return false;
        else
        {
            UmmObj otherUmm = (UmmObj) otherObj;
            return (objID == otherUmm.objID &&
                    dateStart.equals(otherUmm.dateStart) &&
                    dateHaveto.equals(otherUmm.dateHaveto) &&
                    dateEnd.equals(otherUmm.dateEnd) &&
                    studentID.equals(otherUmm.studentID) &&
                    isBorrowing == otherUmm.isBorrowing);
        }
    }

    public static UmmObj[] dataGetter()
    {
        Scanner FileReader_OBJ = null;
        UmmObj[] ObjList = new UmmObj[4];

        try
        {
            FileReader_OBJ = new Scanner(new FileInputStream("./data/umm.txt"));
            int count = 0;
            while (FileReader_OBJ.hasNext())
            {
                int ID = FileReader_OBJ.nextInt();
                int Year1 = FileReader_OBJ.nextInt();
                int Month1 = FileReader_OBJ.nextInt();
                int Day1 = FileReader_OBJ.nextInt();
                int Hour1 = FileReader_OBJ.nextInt();
                int Min1 = FileReader_OBJ.nextInt();
                int Sec1 = FileReader_OBJ.nextInt();
                int Year2 = FileReader_OBJ.nextInt();
                int Month2 = FileReader_OBJ.nextInt();
                int Day2 = FileReader_OBJ.nextInt();
                int Hour2 = FileReader_OBJ.nextInt();
                int Min2 = FileReader_OBJ.nextInt();
                int Sec2 = FileReader_OBJ.nextInt();
                int Year3 = FileReader_OBJ.nextInt();
                int Month3 = FileReader_OBJ.nextInt();
                int Day3 = FileReader_OBJ.nextInt();
                int Hour3 = FileReader_OBJ.nextInt();
                int Min3 = FileReader_OBJ.nextInt();
                int Sec3 = FileReader_OBJ.nextInt();
                String StudendID = FileReader_OBJ.next();

                ObjList[count++] = new UmmObj(ID, new Date(Year1, Month1, Day1, Hour1, Min1, Sec1),
                        new Date(Year2, Month2, Day2, Hour2, Min2, Sec2), new Date(Year3, Month3, Day3, Hour3, Min3, Sec3), StudendID);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        FileReader_OBJ.close();

        return ObjList;
    }

    public String toFileString()
    {
        return "" + objID + "\t" + dateStart.toFileString() + "\t" + dateHaveto.toFileString() + "\t" + dateEnd.toFileString() + "\t" + studentID;
    }

}
