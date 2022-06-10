package TeamProject;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JTextArea;

public class testtesttest {
    private static String log_obj;
    private static int log_obj_id;
    private static Date log_b_time; //대여시간
    private static String log_time;
    private static Date log_r_time; //반납시간
    private static String log_std_id;
    private static String log_return;

	public static void main(String[] args) {
		Scanner filereader = null;
		try {
			filereader = new Scanner(new FileInputStream("./data/log.txt"));
			log_obj = filereader.next();
			log_obj_id = filereader.nextInt();
			
			int Year = filereader.nextInt();
			int Month = filereader.nextInt();
			int Day = filereader.nextInt();
			int Hour = filereader.nextInt();
			int Min = filereader.nextInt();
			int Sec = filereader.nextInt();
			log_b_time = new Date(Year, Month, Day, Hour, Min, Sec);
			
			if(log_obj.equals("Med")) {
				log_return = "-"; //med는 반납이 없음
			}
			else if(log_obj.equals("Mat") || log_obj.equals("Umm")) {
				//반납 예정일 - 얘는 필요 없음
				Year = filereader.nextInt();
				Month = filereader.nextInt();
				Day = filereader.nextInt();
				Hour = filereader.nextInt();
				Min = filereader.nextInt();
				Sec = filereader.nextInt();
				
				if(filereader.hasNextInt()) {
					//반납 날짜
					Year = filereader.nextInt();
					Month = filereader.nextInt();
					Day = filereader.nextInt();
					Hour = filereader.nextInt();
					Min = filereader.nextInt();
					Sec = filereader.nextInt();
					log_r_time = new Date(Year, Month, Day, Hour, Min, Sec);
					
					//교수님이 이렇게 하지 말라고 했던 거 같은데,,,,,,
					BorrowObject checkMat = new BorrowObject(log_b_time, log_r_time, true);
					log_return = checkMat.getObjStatus();
				}
				else {
					
				}
			}
			
			log_time = log_b_time.toLogString();
			log_std_id = filereader.next();
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		filereader.close();
		
		System.out.println(log_obj + log_obj_id + "  " + log_std_id + "  " + log_time + "  " + log_return);
	}
	
}
