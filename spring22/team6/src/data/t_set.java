package data;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// 목표 시간 set
public class t_set extends Set implements Cloneable,Serializable{
	private LocalTime g_time;
	private LocalTime p_time;
	
	public t_set() {
		super();
		g_time = LocalTime.of(0, 0);
		p_time = LocalTime.of(0, 0);
	}

	public t_set(LocalTime other_gtime) {
		super();
		g_time = other_gtime;
		p_time = LocalTime.of(0, 0);
	}
	
	public t_set(LocalTime other_gtime, LocalTime resttime) {
		super(resttime);
		g_time = other_gtime;
		p_time = LocalTime.of(0, 0);
	}
	
	public t_set(String other_gtime) {
		super();
		g_time = LocalTime.parse("00:"+other_gtime,DateTimeFormatter.ofPattern("HH:mm:ss"));
		p_time = LocalTime.of(0, 0);
	}
	
	@Override
	public void performed_update() {
		p_time = g_time;
		super.setPerform_check(true);
	}

	public void performed_update(LocalTime tmp_time) {
		if(p_time.isBefore(tmp_time)) {
			p_time = tmp_time;
		}else {
			p_time = g_time;
		}
		super.setPerform_check(true);
	}
	
	public void performed_reset() {
		p_time = LocalTime.of(0, 0);
		super.setPerform_check(false);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if(!(o instanceof t_set))
			return false;
		t_set ts = (t_set) o;
		return super.equals(ts) && g_time.equals(ts.getG_time()) &&  p_time.equals(ts.getP_time());
	}
	
	public void setG_time(String other_gtime) {
		g_time = LocalTime.parse("00:"+other_gtime,DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	public void setP_time(String other_gtime) {
		p_time = LocalTime.parse("00:"+other_gtime,DateTimeFormatter.ofPattern("HH:mm:ss"));
		super.setPerform_check(p_time != LocalTime.of(0, 0,0));															// 00:00이 아니면 Perform_check = true
	}

	public LocalTime getG_time() {
		return g_time;
	}

	public void setG_time(LocalTime g_time) {
		this.g_time = g_time;
	}
	public void setP_time(LocalTime p_time) {
		this.p_time = p_time;
	}

	public LocalTime getP_time() {
		return p_time;
	}

	@Override
	public t_set clone() throws CloneNotSupportedException{
		return (t_set) super.clone();
	}
}
