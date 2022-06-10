package TeamProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class test2 {
    private static String log_obj;
    private static int log_obj_id;
    private static Date log_b_time; //대여시간
    private static String log_time;
    private static Date log_r_time; //반납시간
    private static String log_std_id;
    private static String log_return;
    private static String log_line;
    private static String log;
    
    private void makelog() {
    	
    }
    	
    
    public static void main(String[] args) {
    	try{
			BufferedReader br = new BufferedReader(new FileReader("./data/log.txt"));
			
			PrintWriter logWriter = new PrintWriter(new FileOutputStream("./data/checkAll.txt"));
			PrintWriter matWriter = new PrintWriter(new FileOutputStream("./data/checkMat.txt"));
			PrintWriter ummWriter = new PrintWriter(new FileOutputStream("./data/checkUmm.txt"));
			PrintWriter medWriter = new PrintWriter(new FileOutputStream("./data/checkMed.txt"));
			
			String line;
			while((line = br.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line);
				log_obj = token.nextToken();
				log_obj_id = Integer.parseInt(token.nextToken());
				
				int Year = Integer.parseInt(token.nextToken());
				int Month = Integer.parseInt(token.nextToken());
				int Day = Integer.parseInt(token.nextToken());
				int Hour = Integer.parseInt(token.nextToken());
				int Min = Integer.parseInt(token.nextToken());
				int Sec = Integer.parseInt(token.nextToken());
				log_b_time = new Date(Year, Month, Day, Hour, Min, Sec);
				
		
				if(log_obj.equals("Med")) {
					log_return = " - "; //med는 반납이 없음
				}
				else if(log_obj.equals("Mat") || log_obj.equals("Umm")) {
					//반납 예정일 - 얘는 필요 없음
					Year = Integer.parseInt(token.nextToken());
					Month = Integer.parseInt(token.nextToken());
					Day = Integer.parseInt(token.nextToken());
					Hour = Integer.parseInt(token.nextToken());
					Min = Integer.parseInt(token.nextToken());
					Sec = Integer.parseInt(token.nextToken());
					
					//반납 날짜
					Year = Integer.parseInt(token.nextToken());
					Month = Integer.parseInt(token.nextToken());
					Day = Integer.parseInt(token.nextToken());
					Hour = Integer.parseInt(token.nextToken());
					Min = Integer.parseInt(token.nextToken());
					Sec = Integer.parseInt(token.nextToken());
					log_r_time = new Date(Year, Month, Day, Hour, Min, Sec);
					
					//교수님이 이렇게 하지 말라고 했던 거 같은데,,,,,,
					BorrowObject checkMat = new BorrowObject(log_b_time, log_r_time, true);
					log_return = checkMat.getObjStatus();
				}
				
				log_time = log_b_time.toLogString();
				log_std_id = token.nextToken();
				
				log = "    " + log_obj_id + "     " + log_std_id + "   " + log_time + "    " + log_return;

				if(log_obj.equals("Mat")) {
					matWriter.println(log);
					log_line = log_obj + log_obj_id + "    " + log_std_id + "   " + log_time + "   " + log_return;
				}
				else if(log_obj.equals("Umm")) {
					ummWriter.println(log);
					log_line = log_obj + log_obj_id + "  " + log_std_id + "   " + log_time + "   " + log_return;
				}
				else if(log_obj.equals("Med")) {
					medWriter.println(log);
					log_line = log_obj + log_obj_id + "   " + log_std_id + "   " + log_time + "   " + log_return;
				}
				logWriter.println(log_line);
			}
			
			logWriter.close();
			matWriter.close();
			ummWriter.close();
			medWriter.close();
			
			br.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        }
    }
}
