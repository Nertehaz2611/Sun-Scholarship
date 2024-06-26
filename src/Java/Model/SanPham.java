package Model;

public class SanPham {
	private String MaSp;
	private String TenSp;
	private String MoTa;
	private int Gia;
	private String Anh;
	private int MaDanhMuc;
	
	public SanPham() {
	}
	
	public SanPham(String maSp, String tenSp, String moTa, int gia, String anh, int maDanhMuc) {
		MaSp = maSp;
		TenSp = tenSp;
		MoTa = moTa;
		Gia = gia;
		Anh = anh;
		MaDanhMuc = maDanhMuc;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
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
	public String getMoTa() {
		return MoTa;
	}
	public void setMoTa(String moTa) {
		MoTa = moTa;
	}
	public int getGia() {
		return Gia;
	}
	

	public int getGia2() {
		return Gia * 125 / 100;
	}
	public void setGia(int gia) {
		Gia = gia;
	}
	public String getAnh() {
		return Anh.trim();
	}
	public void setAnh(String anh) {
		Anh = anh;
	}
	public int getMaDanhMuc() {
		return MaDanhMuc;
	}
	public void setMaDanhMuc(int maDanhMuc) {
		MaDanhMuc = maDanhMuc;
	}
	
}
