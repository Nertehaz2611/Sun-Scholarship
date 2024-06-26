package Model;

public class kho {
	private String maSp;
	private int soluong;
	
	public kho(String maSp, int soluong) {
		this.maSp = maSp;
		this.soluong = soluong;
	}

	public String getMaSp() {
		return maSp;
	}

	public void setMaSp(String maSp) {
		this.maSp = maSp;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
}
