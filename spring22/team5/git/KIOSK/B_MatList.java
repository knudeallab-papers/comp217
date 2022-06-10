package TeamProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class B_MatList {
	ArrayList<MatObj> matList = new ArrayList<MatObj>();
    
	MatObj mat1 = new MatObj(1, new Date(2022,6,3,12,30,10), new Date(2022,6,4,11,15,5), "2021111111");
	MatObj mat2 = new MatObj(2, new Date(2022,6,4,12,30,10), new Date(2022,6,4,11,15,5), "2021111111");
	MatObj mat3 = new MatObj(3, new Date(2022,6,2,12,30,10), new Date(2022,6,3,11,15,5), "2021111111");
	MatObj mat4 = new MatObj(4, new Date(2022,5,31,12,30,10), new Date(2022,6,1,11,15,5), "2021111111");
	
	/*
	matList.add(mat1); 
	matList.add(mat2);
	matList.add(mat3);
	matList.add(mat4);
	*/
	
	/*
	public void dataSetter()
    {
        Scanner FileReader_Mat = null;
        ArrayList<B_MatObj> MatList = new ArrayList<B_MatObj>();

        try
        {
            FileReader_Mat = new Scanner(new FileInputStream("./data/mat.txt"));
            while (FileReader_Mat.hasNext())
            {
            	int f_ID = FileReader_Mat.nextInt();
            	Date f_dateStart = null ;
            	int f_year_Start = FileReader_Mat.nextInt();
            	int f_month_Start = FileReader_Mat.nextInt();
            	int f_day_Start = FileReader_Mat.nextInt();
            	int f_hour_Start = FileReader_Mat.nextInt();
            	int f_minute_Start = FileReader_Mat.nextInt();
            	int f_second_Start = FileReader_Mat.nextInt();
            	f_dateStart.setDate(f_year_Start, f_month_Start, f_day_Start, f_hour_Start, f_minute_Start, f_second_Start);
            	Date f_dateHaveto = null ;
            	int f_year_Haveto = FileReader_Mat.nextInt();
            	int f_month_Haveto = FileReader_Mat.nextInt();
            	int f_day_Haveto = FileReader_Mat.nextInt();
            	int f_hour_Haveto = FileReader_Mat.nextInt();
            	int f_minute_Haveto = FileReader_Mat.nextInt();
            	int f_second_Haveto = FileReader_Mat.nextInt();
            	f_dateHaveto.setDate(f_year_Haveto, f_month_Haveto, f_day_Haveto, f_hour_Haveto, f_minute_Haveto, f_second_Haveto);
            	Date f_dateEnd = null ;
            	int f_year_End = FileReader_Mat.nextInt();
            	int f_month_End = FileReader_Mat.nextInt();
            	int f_day_End = FileReader_Mat.nextInt();
            	int f_hour_End = FileReader_Mat.nextInt();
            	int f_minute_End = FileReader_Mat.nextInt();
            	int f_second_End = FileReader_Mat.nextInt();
            	f_dateEnd.setDate(f_year_End, f_month_End, f_day_End, f_hour_End, f_minute_End, f_second_End);
            	String f_studentID = FileReader_Mat.next();
            	String f_isBorrowing = FileReader_Mat.next();
            	
            	MatList.add(new B_MatObj(f_ID, f_dateStart, f_dateHaveto, f_dateEnd, f_studentID));
                
            }
            FileReader_Mat.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }*/


}
