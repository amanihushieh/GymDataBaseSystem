package application;

public class Trainor {
	private int tid;
	private String tname;
	private String gender;
	private String contact;
	private int age;
	private String email;
    private String password;
	public Trainor(int tid, String tname, String gender, String contact, int age, String email, String password) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.gender = gender;
		this.contact = contact;
		this.age = age;
		this.email = email;
		this.password = password;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
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
		return "Trainor [tid=" + tid + ", tname=" + tname + ", gender=" + gender + ", contact=" + contact + ", age="
				+ age + ", email=" + email + ", password=" + password + "]";
	}
    

}
	