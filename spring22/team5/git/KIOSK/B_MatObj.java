package TeamProject;

public class B_MatObj extends BorrowObj
{

    B_MatObj()
    {
        super();
    }

    B_MatObj(int newID, String newStudID)
    {
        super();
        // TODO Auto-generated constructor stub
        ID = newID;
        dateStart = new Date(false);
        dateHaveto = new Date(false);
        dateEnd = new Date(false);
        studentID = newStudID;
        isBorrowing = false;

    }

    B_MatObj(int newID, Date newStart, Date newEnd, String newStudID)
    {
        super(newID, newStart, newEnd, newStudID);
        // TODO Auto-generated constructor stub
        if (isValid(newID, newStart, newEnd, newStudID))
        {

            ID = newID;
            dateStart = new Date(newStart);
            dateHaveto = new Date(setHaveto(dateStart));
            dateEnd = new Date(newEnd);
            studentID = newStudID;
            setObjStatus();

        }
        else
            errorhandler("invalid initialization");
    }

    B_MatObj(int newID, Date newStart, Date newHaveto, Date newEnd, String newStudID)
    {
        super(newID, newStart, newHaveto, newEnd, newStudID);
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

        System.err.println("Error occured in Abstract BorrowObj class: " + errorType);
    }

}
