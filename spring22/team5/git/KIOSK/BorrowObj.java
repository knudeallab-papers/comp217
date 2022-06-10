package TeamProject;

public class BorrowObj
{

    protected int ID = 0;
    protected Date dateStart;
    protected Date dateHaveto;
    protected Date dateEnd;
    protected String studentID = "";
    protected boolean isBorrowing = false;

    BorrowObj()
    {
        ID = 0;
        dateStart = new Date(2022, 6, 3, 12, 30, 10);
        dateHaveto = new Date(2022, 6, 4, 12, 30, 10);
        dateEnd = new Date(2022, 6, 4, 10, 30, 10);
        studentID = "1234567890";
        isBorrowing = false;
    }

    BorrowObj(int newID, String newStudID)
    {

        ID = newID;
        dateStart = new Date(false);
        dateHaveto = new Date(false);
        dateEnd = new Date(false);
        studentID = newStudID;
        isBorrowing = false;
    }

    BorrowObj(int newID, Date newStart, Date newEnd, String newStudID)
    {

        if (isValid(newID, newStart, newEnd, newStudID))
        {

            ID = newID;
            dateStart = new Date(newStart);
            dateHaveto = new Date(setHaveto(dateStart));//setReturn
            dateEnd = new Date(newEnd);
            studentID = newStudID;
            setObjStatus();

        }
        else
            errorhandler("invalid initialization");
    }

    BorrowObj(int newID, Date newStart, Date newHaveto, Date newEnd, String newStudID)
    {

        if (isValid(newID, newStart, newHaveto, newEnd, newStudID))
        {

            ID = newID;
            dateStart = new Date(newStart);
            dateHaveto = new Date(newHaveto);
            dateEnd = new Date(newEnd);
            studentID = newStudID;
            setObjStatus();

        }
        else
            errorhandler("invalid initialization");
    }

    private boolean isValid(int newID, Date newStart, Date newEnd, String newStudID)
    {

        return ((newID > 0) && (newStart.isValidTime()) && (newEnd.isValidTime()) && (newStudID.length() == 10));
    }

    private boolean isValid(int newID, Date newStart, Date newHaveto, Date newEnd, String newStudID)
    {

        return ((newID > 0) && (newStart.isValidTime()) && (newHaveto.isValidTime()) && (newEnd.isValidTime()) && (newStudID.length() == 10));
    }

    public void updateStart()
    {

        dateStart.setDate(true);
        dateHaveto = new Date(setHaveto(dateStart));//setdatereturn
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

            isBorrowing = true;

        else
            isBorrowing = false;
    }

    public Date setHaveto(Date dateStart)
    {
        Date Haveto = null;
        Haveto.AddDate(dateStart);
        return Haveto;

    }

    public int getID()
    {

        return ID;
    }

    public Date getDateStart()
    {

        return new Date(dateStart);
    }

    public Date getDateHaveto()
    {

        return new Date(dateHaveto);
    }

    public Date getDateEnd()
    {

        return new Date(dateEnd);
    }

    public String getStudentID()
    {

        return studentID;
    }

    public boolean getIsBorrowing()
    {

        return isBorrowing;
    }

    public String toString()
    {

        return "ID: " + ID + " Start: " + dateStart.toString() + " Have to Return: " + dateHaveto.toString() + " End: " + dateEnd.toString() + " StudID: " + studentID
                + " " + isBorrowing;
    }

    private void errorhandler(String errorType)
    {

        System.err.println("Error occured in BorrowObj class: " + errorType);
    }

}
