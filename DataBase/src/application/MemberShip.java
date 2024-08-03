package application;

import java.sql.Date;

public class MemberShip {
	private int mid;
	private String type;
	private Date date;
	private String duration;
	public MemberShip(int mid, String type, Date date, String duration) {
		super();
		this.mid = mid;
		this.type = type;
		this.date = date;
		this.duration = duration;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "MemberShip [mid=" + mid + ", type=" + type + ", date=" + date + ", duration=" + duration + "]";
	}
	
}