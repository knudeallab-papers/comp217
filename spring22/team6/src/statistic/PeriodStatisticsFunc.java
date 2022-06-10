package statistic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import data.c_set;
import data.dayRecord;
import data.exRecord;
import data.exercise;
import data.t_exRecord;
import data.t_set;
import data.wc_exRecord;
import data.wc_set;

public class PeriodStatisticsFunc {
	private String startStr;
	private String endStr;
	
	private LocalDate startDay;
	private LocalDate endDay;
	
	private String totalTimeStr;
	private String totalSetCntStr;
	private String totalWeightsStr;
	private String sen1;
	private ArrayList<String> cateStr;
	
	private String statistics;
	
	private int setCnt;
	private double totalWeight;
	
	private ArrayList<dayRecord> curr_ary;

	private ArrayList<c_set> cset;
	private ArrayList<t_set> tset;
	private ArrayList<wc_set> wcset;
	
	private int[] cnts;
	private double[] percent;
	private HashMap<String, int[]> catemap; // { ī�װ��� : {� ���� , � ��Ʈ��, � ��ǥ ��Ʈ ��} }
	private HashMap<String, double[]> catemapPercent; // { ī�װ���, {��Ʈ ����, ��Ʈ ������}
	
	public PeriodStatisticsFunc(ArrayList<dayRecord> dR_ary, LocalDate sDay, LocalDate eDay) {
		totalSetCntStr = "�� ��Ʈ �� : ";
		totalWeightsStr = "�� ���� : ";
		sen1 = "ī�װ����� ���\t\t��Ʈ����\t��Ʈ������";
		cateStr = new ArrayList<String>();
		statistics="";
		setCnt=0;
		totalWeight=0;
		catemap = new HashMap<String, int[]>();
		catemapPercent = new HashMap<String, double[]>();
		curr_ary = dR_ary;
		startStr = sDay.toString();
		endStr = eDay.toString();
		startDay = sDay;
		endDay = eDay;
		
		calSetCnt(curr_ary);
		calWeights(curr_ary);
		cateStatistic(curr_ary);
	}
	public LocalDate makeLocalDate(String dayStr) {
		StringTokenizer dateTokens = new StringTokenizer(dayStr, "-" , false);
		
		int y = Integer.parseInt(dateTokens.nextToken());
		int m = Integer.parseInt(dateTokens.nextToken());
		int d = Integer.parseInt(dateTokens.nextToken());
		
		LocalDate ld = LocalDate.of(y, m, d);
		return ld;
	}
	
	public void calSetCnt(ArrayList<dayRecord> curr_ary) { // �� ��Ʈ�� ���
		for(dayRecord dr : curr_ary) {
			if((dr.getToday_date().isAfter(startDay) || dr.getToday_date().isEqual(startDay))
					&& (dr.getToday_date().isBefore(endDay) || dr.getToday_date().isEqual(endDay))) {
				for(exRecord er : dr.getExr_ary()){
					setCnt+=er.getCount_set();
				}
			}
		}
		
		totalSetCntStr += String.valueOf(setCnt);
	}
	
	public void calWeights(ArrayList<dayRecord> curr_ary) { // �� ���� ����
		for(dayRecord dr : curr_ary) {
			if((dr.getToday_date().isAfter(startDay) || dr.getToday_date().isEqual(startDay))
					&& (dr.getToday_date().isBefore(endDay) || dr.getToday_date().isEqual(endDay))) {
				for(exRecord er : dr.getExr_ary()){
					if(er instanceof wc_exRecord ) {
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
	
	public void cateStatistic(ArrayList<dayRecord> curr_ary) { 
		for(dayRecord dr: curr_ary) {
			if((dr.getToday_date().isAfter(startDay) || dr.getToday_date().isEqual(startDay))
					&& (dr.getToday_date().isBefore(endDay) || dr.getToday_date().isEqual(endDay))) {
				for(exRecord er : dr.getExr_ary()) {
					exercise e = er.getEx();
					if(!catemap.containsKey(e.getcategory())) { // ī�װ��� �ʿ� �ش� ��� ī�װ����� ������ ī�װ��� �߰�
						cnts = new int[3];
						percent = new double[2];
						catemap.put(e.getcategory(), cnts);// { ī�װ��� : {� ���� , � ��Ʈ��, � ��ǥ ��Ʈ ��} }
						catemapPercent.put(e.getcategory(), percent); // { ī�װ���, {��Ʈ ����, ��Ʈ ������}
					}
					catemap.get(e.getcategory())[0]+=1;
					catemap.get(e.getcategory())[1]+=er.getCount_set();
					catemap.get(e.getcategory())[2]+=er.getSet_goal(); // ���Ŀ� catemapPercent���� ����Ϸ��� ���, ��µǴºκ� �ƴ�
				}
			}
		}
		
		Iterator<String> iter = catemapPercent.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			catemapPercent.get(key)[0]=((double)catemap.get(key)[1]/setCnt)*100;
			catemapPercent.get(key)[1]=((double)catemap.get(key)[1]/catemap.get(key)[2])*100;
		}
		
		iter = catemapPercent.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			cateStr.add(key + " " + "� �� : " + catemap.get(key)[0] + 
					" ��Ʈ �� : " + catemap.get(key)[1] + "\t"+ Math.round(catemapPercent.get(key)[0])
					+"% \t" + Math.round(catemapPercent.get(key)[1])+"%");
		}
		
	}

	public String makeTextArea() {
		statistics = "";
		statistics += totalSetCntStr + '\n';
		statistics += totalWeightsStr + '\n';
		statistics += sen1 + '\n';
		
		for(String s : cateStr)
			statistics += s + '\n'; 

		return statistics;
	}
	
	public static int chkDateSeq(String startDate, String endDate) {
		StringTokenizer dateTokens = new StringTokenizer(startDate, "-" , false);
		
		int y = Integer.parseInt(dateTokens.nextToken());
		int m = Integer.parseInt(dateTokens.nextToken());
		int d = Integer.parseInt(dateTokens.nextToken());
				
		LocalDate sld = LocalDate.of(y, m, d);
		
		dateTokens = new StringTokenizer(endDate, "-", false);	 
		y = Integer.parseInt(dateTokens.nextToken());
		m = Integer.parseInt(dateTokens.nextToken());
		d = Integer.parseInt(dateTokens.nextToken());
		
		LocalDate eld = LocalDate.of(y, m, d);
				
		if(eld.isEqual(sld)) // ������ ������ ������ 
			return 0; 
		else if(eld.isAfter(sld)) // �������� �����Ϻ��� �ڿ��ö�
			return 1;
		else
			return -1;
		
	}
	public HashMap<String, int[]> getCatemap() {
		return catemap;
	}
	public void setCatemap(HashMap<String, int[]> catemap) {
		this.catemap = catemap;
	}
	public HashMap<String, double[]> getCatemapPercent() {
		return catemapPercent;
	}
	public void setCatemapPercent(HashMap<String, double[]> catemapPercent) {
		this.catemapPercent = catemapPercent;
	}
	public String getStartStr() {
		return startStr;
	}
	public String getEndStr() {
		return endStr;
	}
	public LocalDate getStartDay() {
		return startDay;
	}
	public LocalDate getEndDay() {
		return endDay;
	}
	public String getTotalTimeStr() {
		return totalTimeStr;
	}
	public String getTotalSetCntStr() {
		return totalSetCntStr;
	}
	public String getTotalWeightsStr() {
		return totalWeightsStr;
	}
	public String getSen1() {
		return sen1;
	}
	public ArrayList<String> getCateStr() {
		return cateStr;
	}
	public String getStatistics() {
		return statistics;
	}
	public int getSetCnt() {
		return setCnt;
	}
	public double getTotalWeight() {
		return totalWeight;
	}
	public ArrayList<dayRecord> getCurr_ary() {
		return curr_ary;
	}
	public ArrayList<c_set> getCset() {
		return cset;
	}
	public ArrayList<t_set> getTset() {
		return tset;
	}
	public ArrayList<wc_set> getWcset() {
		return wcset;
	}
	public int[] getCnts() {
		return cnts;
	}
	public double[] getPercent() {
		return percent;
	}
}