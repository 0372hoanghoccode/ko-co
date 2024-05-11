package DAO;

import connectionSQL.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.sql.Statement;
import java.util.ArrayList;

// --- IMPORT DTO ----
import DTO.CHUCVU;


public class ChucVuDAO implements DAOInterface<CHUCVU>{

    public static ChucVuDAO getInstance() {
        return new ChucVuDAO();
    }

    @Override
    public ArrayList<CHUCVU> getList() {
        ArrayList<CHUCVU> list = new ArrayList<>();
		Connection con = ConnectionManager.getConnection();
        try {
            String sql = "select * from CHUCVU";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                CHUCVU chucvu = new CHUCVU();
                chucvu.setMaChucVu(rs.getString(1));
                chucvu.setTenChucVu(rs.getString(2));
             
                chucvu.setPhuCapChucVu(rs.getDouble(3));
                chucvu.setNgayNhanChuc(rs.getDate(4).toLocalDate());
                list.add(chucvu);
            }
            ConnectionManager.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insert(CHUCVU t) {
        int result = 0;
        Connection con = ConnectionManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("insert CHUCVU values(?,?,?,?)");
			st.setString(1, t.getMaChucVu());
			st.setString(2, t.getTenChucVu());
			st.setDouble(3, t.getPhuCapChucVu());
			st.setDate(4, Date.valueOf(t.getNgayNhanChuc()));
			result = st.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    @Override
    public int update(CHUCVU t) {
        int result = 0;
        Connection con = ConnectionManager.getConnection();
        try {
			PreparedStatement st = con.prepareStatement("update CHUCVU set tenChucVu = ?, phuCapChucVu = ?,ngayNhanChuc = ? where maChucVu = ?");
			st.setString(1, t.getTenChucVu());
			st.setDouble(2, t.getPhuCapChucVu());
			st.setDate(3, Date.valueOf(t.getNgayNhanChuc()));
			st.setString(4, t.getMaChucVu());
			result = st.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    @Override
    public int del(String maChucvu) {
        int result = 0;
        Connection con = ConnectionManager.getConnection();
        try {
			PreparedStatement st = con.prepareStatement("delete CHUCVU where maChucVu = ?");
			st.setString(1, maChucvu);
			st.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }

    public Object[] getChucVuCongTyTuTen(String tenChucVu) {
		Object data[] = new Object[3];
		Connection con = ConnectionManager.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs  = st.executeQuery("select * from CHUCVUCONGTY where tenChucVu = N'"+tenChucVu+"'");
			while(rs.next()) {
				data[0] = rs.getString("maChucVu");
				data[1] = rs.getString("tenChucVu");
				data[2] = rs.getDouble("phuCapChucVu");
			}
			ConnectionManager.closeConnection(con);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return data;
	}

	public String[] getDanhSachTenChucVuCongTy() {
		String data[] = null;
		Connection con = ConnectionManager.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs  = st.executeQuery("select COUNT(tenChucVu) from CHUCVUCONGTY");
			while(rs.next()) {
				data = new String[rs.getInt(1)];
			}
			rs = st.executeQuery("select * from CHUCVUCONGTY order by phuCapChucVu desc");
			int count =0;
			while(rs.next()) {
				data[count] = rs.getString("tenChucVu");
				count++;
			}
			ConnectionManager.closeConnection(con);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return data;
	}

}
