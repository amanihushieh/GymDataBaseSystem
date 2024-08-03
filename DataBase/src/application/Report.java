package application;

public class Report {
	private int mid;
	private double total_amount;
	public Report(int mid, double total_amount) {
		super();
		this.mid = mid;
		this.total_amount = total_amount;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	@Override
	public String toString() {
		return "Report [mid=" + mid + ", total_amount=" + total_amount + "]";
	}
	
}