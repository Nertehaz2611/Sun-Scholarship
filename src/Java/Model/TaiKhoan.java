package Model;

public class TaiKhoan {
	private String User;
	private String Pass;
	private String Email;
	
	@Override
	public String toString() {
		return User;
	}

	public TaiKhoan() {
	}
	
	public TaiKhoan(String user, String pass, String email) {
		User = user;
		Pass = pass;
		Email = email;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}	
	
}
