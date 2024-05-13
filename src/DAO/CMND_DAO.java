package DAO;

import java.util.ArrayList;


// --- IMPORT DTO ----
import DTO.CMND;


// --- IMPORT SQL -----
import connectionSQL.ConnectionManager;
import java.sql.Connection;
// import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 import java.sql.Statement;




public class CMND_DAO implements DAOInterface<CMND>{

	public static CMND_DAO getInstance() {
		return new CMND_DAO();
	}

	@Override
	public ArrayList<CMND> getList() {
		ArrayList<CMND> list = new ArrayList<>();
		Connection con = ConnectionManager.getConnection();
		try {
			String sql = "select * from CMND";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				CMND cmnd = new CMND();
				cmnd.setSoCmnd(rs.getString(1));
				cmnd.setNoiCap(rs.getString(2));
				cmnd.setNgayCap(rs.getDate("ngayCap").toLocalDate());
				list.add(cmnd);
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public int insert(CMND t) {
		int result = 0;
		Connection con = ConnectionManager.getConnection();
		try {
			String sql = "insert into CMND values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, t.getSoCmnd());
			ps.setString(2, t.getNoiCap());
			ps.setDate(3, java.sql.Date.valueOf(t.getNgayCap()));
			result = ps.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			System.out.println("Loi khi them cmnd" + e.getMessage());
		} 
		return result;
	}

	@Override
	public int update(CMND x) {
		int result = 0;
		Connection con = ConnectionManager.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("update CMND set noiCap = ? , ngayCap = ? where soCMND = ?");
			pst.setString(1, x.getNoiCap());
			pst.setDate(2,  java.sql.Date.valueOf(x.getNgayCap()));
			pst.setString(3, x.getSoCmnd());
			result = pst.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public ArrayList<String> getDanhSachMaSo(){
		ArrayList<String> dsMaso = new ArrayList<>();
		Connection con = ConnectionManager.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from CMND");
			while(rs.next()) {
				dsMaso.add(rs.getString("soCMND"));
			}
			ConnectionManager.closeConnection(con);
			return dsMaso;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}




	// tạm thời không cần
	@Override
	public int del(String ma) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'del'");
	}

}
