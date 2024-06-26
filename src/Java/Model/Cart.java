package Model;

public class Cart {
	private String User;
	private String Masp;
	private int sl;
	
	public Cart(String user, String masp, int sl) {
		User = user;
		Masp = masp;
		this.sl = sl;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getMasp() {
		return Masp;
	}

	public void setMasp(String masp) {
		Masp = masp;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}
	
	
}
