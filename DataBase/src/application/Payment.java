package application;

import java.sql.Date;

public class Payment {
	private int pid;
	private int mid;
	private double amount;
	private String method;
	public Payment(int pid, int mid, double amount, String method) {
		super();
		this.pid = pid;
		this.mid = mid;
		this.amount = amount;
		this.method = method;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Override
	public String toString() {
		return "Payment [pid=" + pid + ", mid=" + mid + ", amount=" + amount + ", method=" + method + "]";
	}
	
}