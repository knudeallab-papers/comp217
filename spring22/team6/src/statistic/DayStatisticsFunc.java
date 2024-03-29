package statistic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import data.Set;
import data.c_exRecord;
import data.c_set;
import data.dayRecord;
import data.exRecord;
import data.exercise;
import data.t_exRecord;
import data.t_set;
import data.wc_exRecord;
import data.wc_set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class DayStatisticsFunc {
	private LocalDate today;
	private String totalSetCntStr;
	private String totalWeightsStr;
	private String sen1;
	
	private ArrayList<String> cateStr;
	
	private String statistics="";
	
	private int setCnt;
	private double totalWeight;
	
	private ArrayList<dayRecord> curr_ary;
	private ArrayList<wc_set> wcset;
	
	int[] cnts;
	
	HashMap<String, int[]> catemap; // { 카테고리 : {운동 갯수 , 운동 세트수 } }
	public DayStatisticsFunc(ArrayList<dayRecord> dR_ary, LocalDate currDate) {
		totalSetCntStr = "총 세트 수 : ";
		totalWeightsStr = "총 무게 : ";
		sen1 = "카테고리별 통계";
		cateStr = new ArrayList<String>();
		catemap = new HashMap<String, int[]>();
		statistics="";

		setCnt=0;
		totalWeight=0;
		
		curr_ary = dR_ary;
		today = currDate;
				
		calSetCnt(today);
		calWeights(today);
		cateStatistic(today);
	}

	
	public void calSetCnt(LocalDate today) { // 모든 운동 세트수 합
		for(dayRecord dr : curr_ary) {
			if(dr.getToday_date().equals(today)) {
				for(exRecord er : dr.getExr_ary()){
					setCnt+=er.getCount_set();
				}
			}
		}
		totalSetCntStr += String.valueOf(setCnt);
	}
	
	public void calWeights(LocalDate today) { // wc_exRecord 무게*횟수로 총무게 구하기
		for(dayRecord dr : curr_ary) {
			if(dr.getToday_date().equals(today)) {
				for(exRecord er : dr.getExr_ary()){
					if(er instanceof wc_exRecord) {
						wcset = ((wc_exRecord) er).getWc_set_ary();
						for(int i=0;i<wcset.size();i++) {
							totalWeight+=wcset.get(i).getP_weight() * wcset.get(i).getP_count();
						}
					}
				}
			}
		}
		totalWeightsStr+=String.valueOf(totalWeight);
	}
	public void cateStatistic(LocalDate today) {	//카테고리 별 운동 갯수 구하는 메소드
		for(dayRecord dr: curr_ary) {
			if(dr.getToday_date().equals(today)) {
				for(exRecord er : dr.getExr_ary()) {
					exercise e = er.getEx();
					if(!catemap.containsKey(e.getcategory())) { // 카테고리 맵에 해당 운동의 카테고리가 없으면 카테고리 추가
						cnts = new int[2];
						catemap.put(e.getcategory(), cnts);
					}
					catemap.get(e.getcategory())[0]+=1;
					catemap.get(e.getcategory())[1]+=er.getCount_set();
				}
			}
		}
		Iterator<String> iter = catemap.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			cateStr.add(key + " " + "운동 수 : " + catemap.get(key)[0] + 
					" 세트 수 : " + catemap.get(key)[1]);
		}
	}
	public String makeTextArea() { // DayStatisticsDemo의 statistics String변수에 할당
		statistics = "";
		statistics += totalSetCntStr + '\n';
		statistics += totalWeightsStr + '\n';
		statistics += sen1 + '\n';
		
		for(String s : cateStr)
			statistics += s + '\n'; 

		return statistics;
	}

}