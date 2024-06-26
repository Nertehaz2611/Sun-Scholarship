package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    
	public Connection connect() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dbUrl1 = "jdbc:sqlserver://CHUNGGNI\\CHUNGGNI:1433;" + "databaseName=WebBanHang;"
					+ "integratedSecurity=true;" + "user=sa;" + "password=28102003;" + "encrypt = false";
			return DriverManager.getConnection(dbUrl1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public DAO() {
	}
	
	public ResultSet getResultSet(String SQL) {
        try {
        	DAO sl = new DAO();
			Connection cnn = sl.connect();
			Statement stm = cnn.createStatement();
            ResultSet rs;
            rs = stm.executeQuery(SQL);
            return rs;
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return null;
    }

    public void ExcuteSQL(String sql, Object[] ob){
        try {
        	DAO sl = new DAO();
			Connection cnn = sl.connect();
			PreparedStatement ps = cnn.prepareStatement(sql);
            int i = 1;
            for (Object obj : ob){
                ps.setObject(i, obj);
                i++;
            }
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    
    public int ExecuteSQL(String SQL) {
        try {
            int k = 0;
        	DAO sl = new DAO();
			Connection cnn = sl.connect();
			Statement stm = cnn.createStatement();
            k = stm.executeUpdate(SQL);
            return k;
        } catch (SQLException e) {
        }
        return 0;
    }

    public int ExecuteSQL(String SQL, Object[] param) {
		try {
			int k = 0;
			DAO sl = new DAO();
			Connection cnn = sl.connect();
			PreparedStatement ps = cnn.prepareStatement(SQL);
			int i = 1;
			for (Object value : param) {
				ps.setObject(i, value);
				i++;
			}
			k = ps.executeUpdate();
			return k;
		} catch (SQLException e) {
		}
		return 0;
	}
    
    
    public ResultSet ExcuteSQL_Re(String sql, Object[] ob){
        try {
			DAO sl = new DAO();
			Connection cnn = sl.connect();
			PreparedStatement ps = cnn.prepareStatement(sql);
            int i = 1;
            for (Object obj : ob){
                ps.setObject(i, obj);
                i++;
            }
            return ps.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
	
	public static void main(String[] args) {
		DAO dao = new DAO();
		if (dao.getResultSet("select * from SanPham") != null) {
			System.out.println("Success!");
		}
		else {
			System.out.println("Fail!");
		}
	}
}
