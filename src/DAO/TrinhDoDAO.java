package DAO;

import java.util.ArrayList;
import DTO.TRINHDO;
import connectionSQL.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrinhDoDAO implements DAOInterface<TRINHDO> {

    public static TrinhDoDAO getInstance() {
        return new TrinhDoDAO();
    }

    @Override
    public ArrayList<TRINHDO> getList() {
        ArrayList<TRINHDO> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "SELECT * FROM TRINHDO";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TRINHDO trinhdo = new TRINHDO();
                trinhdo.setMaTrinhDo(rs.getString("maTrinhDo"));
                trinhdo.setTrinhDoHocVan(rs.getString("trinhDoHocVan"));
                trinhdo.setTrinhDoChuyenMon(rs.getString("trinhDoChuyenMon"));
                trinhdo.setChuyenNganh(rs.getString("chuyenNganh"));
                list.add(trinhdo);
            }
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insert(TRINHDO t) {
        int result = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "INSERT INTO TRINHDO VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getMaTrinhDo());
            ps.setString(2, t.getTrinhDoHocVan());
            ps.setString(3, t.getTrinhDoChuyenMon());
            ps.setString(4, t.getChuyenNganh());
            result = ps.executeUpdate();
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("lỗi khi thêm trinhdo: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int update(TRINHDO x) {
        int result = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE TRINHDO SET trinhDoHocVan = ?, trinhDoChuyenMon = ?, chuyenNganh = ? WHERE maTrinhDo = ?");
            pst.setString(1, x.getTrinhDoHocVan());
            pst.setString(2, x.getTrinhDoChuyenMon());
            pst.setString(3, x.getChuyenNganh());
            pst.setString(4, x.getMaTrinhDo());
            result = pst.executeUpdate();
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
// có thể chưa cần thiết
	@Override
	public int del(String ma) {
		
		throw new UnsupportedOperationException("Unimplemented method 'del'");
		
	}

}
