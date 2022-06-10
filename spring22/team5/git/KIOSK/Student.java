package TeamProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Student
{
    private static final String mailForm = "@knu.ac.kr";
    private String ID = "";
    private String name = "";
    private String phoneNumber = "";
    private String webMailAddress = "";
    private Cabinet cabinet = null;
    private Boolean isCouncil = false;

    public Student()
    {
        ID = "";
        name = "";
        phoneNumber = "";
        webMailAddress = "";
        cabinet = new Cabinet(0, "0000");
        isCouncil = false;
    }

    public Student(String newID, String newname, String newphone, String newmail, int newcabID, String newcabPW, boolean newCouncil)
    {
        if (isValidData(newID, newname, newphone))
        {
            ID = newID;
            name = newname;
            phoneNumber = newphone;
            webMailAddress = newmail;
            cabinet = new Cabinet(newcabID, newcabPW);
            isCouncil = newCouncil;
        }
        else
            errorhandler("invalid initialization");
    }

    public static Student dataGetter(String UserID, String UserName, String UserPhone)
    {
        Scanner FileReader_Student = null;
        ArrayList<Student> StudentList = new ArrayList<>();

        try
        {
            FileReader_Student = new Scanner(new FileInputStream("./data/Student.txt"));
            while (FileReader_Student.hasNext())
            {
                String f_ID = FileReader_Student.next();
                String f_name = FileReader_Student.next();
                String f_phone = FileReader_Student.next();
                String f_mail = FileReader_Student.next();
                int f_cabID = FileReader_Student.nextInt();
                String f_cabPW = FileReader_Student.next();
                String f_council = FileReader_Student.next();

                if (f_council.equals("false"))
                {
                    StudentList.add(new Student(f_ID, f_name, f_phone, f_mail, f_cabID, f_cabPW, false));
                }
                else
                {
                    StudentList.add(new Student(f_ID, f_name, f_phone, f_mail, f_cabID, f_cabPW, true));
                }
            }
            FileReader_Student.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Student theStudent = null;
        for (Student s : StudentList)
        {
            if (UserID.equals(s.getID()) && UserName.equals(s.getName())&&UserPhone.equals(s.getPhoneNumber()))
            {
                theStudent = s;
            }
        }
        return theStudent;
    }

    private boolean isValidData(String newID, String newname, String newphone)
    {
        return ((newID.length() == 10) && (newname.length() >= 2) && (newname.length() < 20)
                && (newphone.length() == 11));
    }

    public String getID()
    {
        return ID;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getWebMailAddress()
    {
        return webMailAddress;
    }

    public int getCabID()
    {
        return cabinet.getID();
    }

    public String getCabPW()
    {
        return cabinet.getPW();
    }

    public void setCabPW(String newPW)
    {
        cabinet.setPW(newPW);
    }

    public boolean getCouncil()
    {
        return isCouncil;
    }

    public String toString()
    {
        String phoneNumberLong = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 7) + "-" + phoneNumber.substring(7);
        return "" + ID + "\t" + name + "\t" + phoneNumberLong + "\t" + webMailAddress + mailForm + "\t" + "\tcouncil: "
                + isCouncil;
    }

    public String toFileString()
    {
        return "" + ID + "\t" + name + "\t" + phoneNumber + "\t" + webMailAddress + "\t" + cabinet.getID() + "\t" + cabinet.getPW() + "\t"
                + isCouncil;
    }

    private void errorhandler(String errorType)
    {
        System.err.println("Error occured in Student class: " + errorType);
    }
}