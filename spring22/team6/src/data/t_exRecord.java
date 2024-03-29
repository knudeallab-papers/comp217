package data;

import java.util.ArrayList;

// 시간으로 측정하는 운동에 대한 exRecord
public class t_exRecord extends exRecord{
	private ArrayList<t_set> t_set_ary;
	
	public t_exRecord() {
		super();
		t_set_ary = new ArrayList<>();
	}
	
	public t_exRecord(exRecord exr) {
		super(exr);
		t_set_ary = new ArrayList<>();
	}
	
	public t_exRecord (t_exRecord other_te) {
		super(other_te);
		t_set_ary = other_te.t_set_ary;
	}
	
	public void first_add_tset(t_set ts) {
		t_set_ary.add(ts);
	}
	
	public void add_tset(t_set ts) {
		t_set_ary.add(ts);
		super.setSet_goal(getSet_goal()+1);
	}

	public void del_tset(t_set tmp_ts) {
		int count =0;
		for (t_set ts : t_set_ary) {
			if(ts.equals(tmp_ts)) {
				t_set_ary.remove(count);
				break;
			}
			count++;
		}
		super.setSet_goal(getSet_goal()-1);
	}
	
	@Override
	public int cal_count_set() {
		int count =0;
		for(t_set ts : t_set_ary) {
			if(ts.getPerform_check() == true)
				count++;
		}
		return count;
	}
	
	public ArrayList<t_set> gett_set_ary() {
		return new ArrayList<t_set>(t_set_ary);
	}

	public void sett_set_ary(ArrayList<t_set> wc_set_ary) {
		this.t_set_ary = new ArrayList<t_set>(wc_set_ary);
	}

}
