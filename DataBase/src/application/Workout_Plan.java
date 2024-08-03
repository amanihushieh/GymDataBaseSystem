package application;

import java.sql.Date;

public class Workout_Plan {
	private int wid;
	private int tid;
	private String name;
	private String type;
	private Date date;
	private String time;
	private String capacity;
	public Workout_Plan(int wid, int tid, String name, String type, Date date, String time, String capacity) {
		super();
		this.wid = wid;
		this.tid = tid;
		this.name = name;
		this.type = type;
		this.date = date;
		this.time = time;
		this.capacity = capacity;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "Workout_Plan [wid=" + wid + ", tid=" + tid + ", name=" + name + ", type=" + type + ", date=" + date
				+ ", time=" + time + ", capacity=" + capacity + "]";
	}
	
	
	

}
