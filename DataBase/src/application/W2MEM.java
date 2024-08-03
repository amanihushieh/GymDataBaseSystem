package application;

public class W2MEM {
	private int mid;
	private int wid;
	public W2MEM(int mid, int wid) {
		super();
		this.mid = mid;
		this.wid = wid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	@Override
	public String toString() {
		return "W2MEM [mid=" + mid + ", wid=" + wid + "]";
	}
	

}
