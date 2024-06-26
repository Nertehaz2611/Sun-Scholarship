package Model;

public class SpOut {
	private int ID;
	private String Masp;
	private String Name;
	private int Gia;
	private int Sl;
	
	public SpOut(int iD, String masp, String name, int gia, int sl) {
		ID = iD;
		Masp = masp;
		Name = name;
		Gia = gia;
		Sl = sl;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMasp() {
		return Masp;
	}

	public void setMasp(String masp) {
		Masp = masp;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getGia() {
		return Gia;
	}

	public void setGia(int gia) {
		Gia = gia;
	}

	public int getSl() {
		return Sl;
	}

	public void setSl(int sl) {
		Sl = sl;
	}
	
	public int getSum() {
		return Sl * Gia;
	}
	
	
	
}
