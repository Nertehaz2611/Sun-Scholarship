package Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DAO.DAO;
import Model.DanhMuc;
import Model.SanPham;
import Model.TaiKhoan;
import Model.Cart;

public class DBconnet {
	
	public DBconnet() {

	}

	public void insert(SanPham a) {
		String sql = "Insert into SanPham Values(?, ?, ?, ?, ?, ?)";
		DAO dao = new DAO();
		try {
			dao.ExcuteSQL(sql,
					new Object[] { a.getMaSp(), a.getTenSp(), a.getMoTa(), a.getGia(), a.getAnh(), a.getMaDanhMuc() });
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi");
		}
	}

	public List<SanPham> selectTow(String nameTable, String DieuKien) {
		ResultSet rs = null;
		List<SanPham> listOut = new ArrayList<>();
		DAO dao = new DAO();
		String sql = "Select * from " + nameTable + " where MASP like '" + DieuKien + "%'";
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				String MaSp = rs.getString("MASP");
				String TenSp = rs.getString("TENSP");
				String MoTa = rs.getString("MOTA");
				int Gia = rs.getInt("GIA");
				String HinhAnh = rs.getString("HINHANH");
				int MaDanhMuc = rs.getInt("MADANHMUC");
				SanPham SP = new SanPham(MaSp, TenSp, MoTa, Gia, HinhAnh, MaDanhMuc);
				listOut.add(SP);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listOut;
	}

	public SanPham selectSp(String nameTable, String DieuKien) {
		ResultSet rs = null;
		DAO dao = new DAO();
		String sql = "Select * from " + nameTable + " where MASP = '" + DieuKien + "'";
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				String MaSp = rs.getString("MASP");
				String TenSp = rs.getString("TENSP");
				String MoTa = rs.getString("MOTA");
				int Gia = rs.getInt("GIA");
				String HinhAnh = rs.getString("HINHANH");
				int MaDanhMuc = rs.getInt("MADANHMUC");
				return new SanPham(MaSp, TenSp, MoTa, Gia, HinhAnh, MaDanhMuc);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
	
	public List<String> selectMasp() {
		ResultSet rs = null;
		List<String> listOut = new ArrayList<>();
		DAO dao = new DAO();
		String sql = "select MASP from SanPham where MASP like '%sp%'";
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				String MaSp = rs.getString("MASP");
				listOut.add(MaSp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listOut;
	}

	public List<String> selectAllMasp() {
		ResultSet rs = null;
		List<String> listOut = new ArrayList<>();
		DAO dao = new DAO();
		String sql = "select MASP from SanPham";
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				String MaSp = rs.getString("MASP");
				listOut.add(MaSp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listOut;
	}

	public List<TaiKhoan> select(String nameTable) {
		ResultSet rs = null;
		List<TaiKhoan> listOut = new ArrayList<>();
		DAO dao = new DAO();
		String sql = "Select * from " + nameTable;
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				String user = rs.getString("user");
				String pass = rs.getString("password");
				String email = rs.getString("email");
				TaiKhoan Tk = new TaiKhoan(user, pass, email);
				listOut.add(Tk);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listOut;
	}

	public List<DanhMuc> selectDm(String nameTable) {
		ResultSet rs = null;
		List<DanhMuc> listOut = new ArrayList<>();
		DAO dao = new DAO();
		String sql = "Select * from " + nameTable;
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				DanhMuc Dm = new DanhMuc(id, name);
				listOut.add(Dm);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return listOut;
	}

	public void insertAcc(Object[] a) {
		DAO dao = new DAO();
		String sql = "insert into account values(?,?,?)";
		try {
			dao.ExcuteSQL(sql, a);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void insertSp(Object[] a) {
		DAO dao = new DAO();
		String sql = "insert into SanPham values(?,?,?,?,?,?)";
		try {
			dao.ExcuteSQL(sql, a);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void insertKho(Object[] a) {
		DAO dao = new DAO();
		String sql = "insert into kho values(?, ?)";
		try {
			dao.ExcuteSQL(sql, a);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void insertCart(String user, String Masp, String sl) {
		DAO dao = new DAO();
		String sql = "insert into cart values('" + user + "' , '" + Masp + "', " + sl + ")";
		try {
			System.out.println(sql);
			dao.ExecuteSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void insertCart(Object[] a) {
		DAO dao = new DAO();
		String sql = "insert into cart values(?, ?, ?)";
		try {
			dao.ExcuteSQL(sql, a);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void updateCart(String user, String Masp, String sl) {
		DAO dao = new DAO();
		String sql = "update cart\r\n" + "set SOLUONG = " + sl + "\r\n" + "where [USER] = '" + user + "' and MASP = '" + Masp + "'";
		try {
			System.out.println(sql);
			dao.ExecuteSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void updateCart(Object[] a) {
		DAO dao = new DAO();
		String sql = "update cart\r\n"
				+ "set SOLUONG = ?\r\n"
				+ "where [USER] = ? and MASP = ?";
		try {
			dao.ExcuteSQL(sql, a);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public TaiKhoan selectAcc(String Nameuser) {
		ResultSet rs = null;
		DAO dao = new DAO();
		String sql = "select * from account\r\n"
				+ "where [user] = '" + Nameuser + "'";
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				String user = rs.getString("user");
				String pass = rs.getString("password");
				String email = rs.getString("email");
				return new TaiKhoan(user, pass, email);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
	
	public int selectCart(String user, String masp) {
		ResultSet rs = null;
		DAO dao = new DAO();
		String sql = "select * from cart\r\n"
				+ "where [user] = '" + user + "' and MASP = '" + masp + "'";
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				return rs.getInt("SOLUONG");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return -1;
	}

	public int selectKho(String nameTable, String Dk) {
		ResultSet rs = null;
		DAO dao = new DAO();
		String sql = "Select * from " + nameTable + " Where MASP = '" + Dk + "'";
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				int sl = rs.getInt("SOLUONG");
				return sl;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	public void delete(String Masp, String NameTable) {
		DAO dao = new DAO();
		String sql = "delete from " + NameTable + "\r\n" + "where MASP = '" + Masp + "'";
		try {
			dao.ExecuteSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void delete(Object[] a) {
		DAO dao = new DAO();
		String sql = "delete from cart\r\n"
				+ "where MASP = ? and [USER] = ?";
		try {
			dao.ExcuteSQL(sql, a);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public SanPham select(String nameTable, String Id) {
		ResultSet rs = null;
		DAO dao = new DAO();
		String sql = "Select * from " + nameTable + " Where MASP = '" + Id + "'";
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				String MaSp = rs.getString("MASP");
				String TenSp = rs.getString("TENSP");
				String MoTa = rs.getString("MOTA");
				int Gia = rs.getInt("GIA");
				String HinhAnh = rs.getString("HINHANH");
				int MaDanhMuc = rs.getInt("MADANHMUC");
				return new SanPham(MaSp, TenSp, MoTa, Gia, HinhAnh, MaDanhMuc);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public List<Cart> selectCart(String nameTable) {
		ResultSet rs = null;
		DAO dao = new DAO();
		String sql = "Select * from " + nameTable;
		List<Cart> listOut = new ArrayList<>();
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				String UESR = rs.getString("USER");
				String MASP = rs.getString("MASP");
				int sl = rs.getInt("SOLUONG");
				Cart Cr = new Cart(UESR, MASP, sl);
				listOut.add(Cr);
			}
			return listOut;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
	
	public int selectSlCart(String nameTable, String User) {
		ResultSet rs = null;
		DAO dao = new DAO();
		int sl = 0;
		String sql = "Select * from " + nameTable + " Where [USER] = '" + User + "'";
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				sl = sl + 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return sl;
	}

	public int findCart(String nameTable, String user, String Dk) {
		ResultSet rs = null;
		DAO dao = new DAO();
		String sql = "Select * from " + nameTable + " Where [USER] = '" + user + "'" + " and MASP = '" + Dk + "'";
		try {
			rs = dao.getResultSet(sql);
			while (rs.next()) {
				return rs.getInt("SOLUONG");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	public static void main(String[] args) {
		DBconnet cnn = new DBconnet();
		String nameTable = "account";
		List<TaiKhoan> listTK = new ArrayList<>();
		listTK = cnn.select(nameTable);
		for (TaiKhoan a : listTK) {
			System.out.println(a.getUser());
		}
		System.out.println("Finish");
	}
}
