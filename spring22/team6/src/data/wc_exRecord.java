package data;

import java.util.ArrayList;
// 무게 * 횟수로 측정하는 운동에 대한 exRecord
public class wc_exRecord extends exRecord{
	private ArrayList<wc_set> wc_set_ary;
	
	public wc_exRecord() {
		super();
		wc_set_ary = new ArrayList<>();
	}
	
	public wc_exRecord(exRecord exr) {
		super(exr);
		wc_set_ary = new ArrayList<>();
	}
	
	public wc_exRecord (wc_exRecord other_wce) {
		super(other_wce);
		wc_set_ary = other_wce.wc_set_ary;
	}
	
	public void first_add_wcset(wc_set wcs) {
		wc_set_ary.add(wcs);
	}
	
	public void add_wcset(wc_set wcs) {
		wc_set_ary.add(wcs);
		super.setSet_goal(super.getSet_goal()+1);
	}

	@Override
	public int cal_count_set() {
		int count =0;
		for(wc_set wcs : wc_set_ary) {
			if(wcs.getPerform_check() == true)
				count++;
		}
		return count;
	}
	
	public void del_wcset(wc_set tmp_wcs) {
		int count =0;
		for (wc_set wcs : wc_set_ary) {
			if(wcs.equals(tmp_wcs)) {
				wc_set_ary.remove(count);
				break;
			}
			count++;
		}
		super.setSet_goal(super.getSet_goal()-1);
	}
	
	public ArrayList<wc_set> getWc_set_ary() {
		return new ArrayList<wc_set>(wc_set_ary);
	}

	public void setWc_set_ary(ArrayList<wc_set> wc_set_ary) {
		this.wc_set_ary = new ArrayList<wc_set>(wc_set_ary);
	}

}
