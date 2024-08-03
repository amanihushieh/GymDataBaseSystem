package application;

public class Member {
	private int mid;
	private String mname;
	private String gender;
	private String contact;
	private int age;
	private String email;
	private String password;
	
	public Member(int mid, String mname, String gender, String contact, int age, String email, String password) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.gender = gender;
		this.contact = contact;
		this.age = age;
		this.email = email;
		this.password = password;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Member [mid=" + mid + ", mname=" + mname + ", gender=" + gender + ", contact=" + contact + ", age="
				+ age + ", email=" + email + ", password=" + password + "]";
	}
	

}
