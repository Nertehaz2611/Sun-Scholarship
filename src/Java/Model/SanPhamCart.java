package Model;

public class SanPhamCart {
	private String MaSp;
	private String TenSp;
	private int Gia;
	private String Anh;
	private int sl;
	private int Maxsl;
	
	public SanPhamCart() {
	}
	
	

	@Override
	public String toString() {
		return "SanPhamCart [MaSp=" + MaSp + ", TenSp=" + TenSp + ", Gia=" + Gia + ", Anh=" + Anh + ", sl=" + sl + "]";
	}



	public SanPhamCart(String maSp, String tenSp, int gia, String anh, int sl, int Maxsl) {
		MaSp = maSp;
		TenSp = tenSp;
		Gia = gia;
		Anh = anh;
		this.sl = sl;
		this.Maxsl = Maxsl;
	}
	

	public int getMaxsl() {
		return Maxsl;
	}



	public void setMaxsl(int maxsl) {
		Maxsl = maxsl;
	}



	public String getMaSp() {
		return MaSp;
	}

	public void setMaSp(String maSp) {
		MaSp = maSp;
	}

	public String getTenSp() {
		return TenSp;
	}

	public void setTenSp(String tenSp) {
		TenSp = tenSp;
	}

	public int getGia() {
		return Gia;
	}

	public void setGia(int gia) {
		Gia = gia;
	}

	public String getAnh() {
		return Anh;
	}

	public void setAnh(String anh) {
		Anh = anh;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}
	
	
}
