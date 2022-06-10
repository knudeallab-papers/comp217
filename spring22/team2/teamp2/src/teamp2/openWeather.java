package teamp2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class openWeather {
	
	double ftemp;
    
	double temp;//최고기온
    double min_temp;//최저기온
    double max_temp;//최고기온
    double avg_temp;
    String mainWeather;
    
    
    public static void main(String[] args) {
        
        openWeather 시작= new openWeather();
    }

    
	public openWeather() {
        try{
            //대구의 위도와 경도
            String lat = "35.798838";  // 위도
            String lon = "128.583052";   // 경도
            String cnt = "5";
            
            //날짜 정보 
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		String currentDate1 = dateFormat.format(new Date());
    		System.out.println(currentDate1);

            //OpenAPI call하는 URL
            String urlstr = "https://api.openweathermap.org/data/2.5/forecast?"
                    + "lat="+lat+"&lon="+lon+"&cnt="+cnt+"&appid=d0aafc304a6f19804b2c16674c3580ab";
            URL url = new URL(urlstr);
            BufferedReader bf;
            String line;
            String result="";
            
            
            //날씨 정보를 받아오기
            bf = new BufferedReader(new InputStreamReader(url.openStream()));

            //버퍼에 있는 정보를 문자열로 변환
            while((line=bf.readLine())!=null){
                result=result.concat(line);
                //System.out.println(line);
            }

            //문자열을 JSON으로 파싱
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
            
            
            //날씨 출력      
            JSONArray weatherlist = (JSONArray) jsonObj.get("list");
            JSONObject obj = (JSONObject) weatherlist.get(0);
            JSONObject tempArray = (JSONObject) obj.get("main");
            
            JSONArray weatherArray = (JSONArray) obj.get("weather");
            JSONObject weatherObj = (JSONObject) weatherArray.get(0);
            
            mainWeather = (String) weatherObj.get("main");
            System.out.println("날씨 = "+mainWeather);
            
            //기온 출력
            ArrayList<Double> arrList = new ArrayList<Double>();
            double sum = 0;
            
            for(int i = 0 ; i < 5 ; i++) {
            	JSONObject obji = (JSONObject) weatherlist.get(i);
            	JSONObject tempAry = (JSONObject) obji.get("main");
            	double itemp = Double.parseDouble(tempAry.get("temp").toString());
            	double tmp = itemp - 273.15;
            	arrList.add(tmp);
            	sum += tmp;
            }
            max_temp = Collections.max(arrList);
            min_temp = Collections.min(arrList);
            avg_temp = sum / arrList.size();		
            
            //온도 변환(절대온도라서 변환 필요)
            ftemp = Double.parseDouble(tempArray.get("temp").toString());
            temp = ftemp - 273.15;
                       
            if(obj.get("dt_txt").equals(currentDate1+" 12:00:00")) {
            	max_temp=temp;
            	System.out.printf("최고 기온 : %.2f\n", temp);
            	System.out.printf("최저 기온 : %.2f\n", min_temp);
            }
            else {
            	System.out.printf("최고 기온 : %.2f\n", max_temp);
            	System.out.printf("최저 기온 : %.2f\n", min_temp);
            }
            
            System.out.printf("평균 기온 : %.2f\n", avg_temp);
            System.out.println(showWeather(avg_temp));

            bf.close();
            
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
	
	


   
   
	   public String showMain_weather() {
	      
	      return mainWeather;
	}   
	   public double showMax_temp() {
	      
	         return max_temp;
	   }
	   public double showMin_temp() {
	      
	      return min_temp;
	}
	   public double showAvg_temp() {
	      
	      return avg_temp;
	}
	   public String showWeather(double temp) {
			if(temp > 24) 
				return "여름";
			else if(temp > 6) 
				return "봄가을";
			else 
				return "겨울";
		}

   
}