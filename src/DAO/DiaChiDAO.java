package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import DTO.DIACHI;
import connectionSQL.ConnectionManager;

public class DiaChiDAO implements DAOInterface<DIACHI> {

	public static DiaChiDAO getInstance() {
        return new DiaChiDAO();
    }
	@Override
    public ArrayList<DIACHI> getList() {
        ArrayList<DIACHI> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("SELECT soNha, duong, phuongXa, quanHuyen, tinhThanhPho FROM DIACHI");
            rs = pst.executeQuery();
            while (rs.next()) {
                DIACHI diachi = new DIACHI(
                    rs.getString("soNha"),
                    rs.getString("duong"),
                    rs.getString("phuongXa"),
                    rs.getString("quanHuyen"),
                    rs.getString("tinhThanhPho")
                );
                list.add(diachi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pst, con);
        }
        return list;
    }

    public int insert(DIACHI diachi) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("INSERT INTO DIACHI (soNha, duong, phuongXa, quanHuyen, tinhThanhPho) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, diachi.getSoNha());
            pst.setString(2, diachi.getDuong());
            pst.setString(3, diachi.getPhuongXa());
            pst.setString(4, diachi.getQuanHuyen());
            pst.setString(5, diachi.getTinhThanhPho());
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pst, con);
        }
    }

    public int update(DIACHI diachi) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("UPDATE DIACHI SET soNha = ?, duong = ?, phuongXa = ?, quanHuyen = ?, tinhThanhPho = ? WHERE id = ?");
            pst.setString(1, diachi.getSoNha());
            pst.setString(2, diachi.getDuong());
            pst.setString(3, diachi.getPhuongXa());
            pst.setString(4, diachi.getQuanHuyen());
            pst.setString(5, diachi.getTinhThanhPho());
            
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pst, con);
        }
    }

    public int del(String id) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("DELETE FROM DIACHI WHERE id = ?");
            pst.setString(1, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pst, con);
        }
    }

    private void closeResources(ResultSet rs, PreparedStatement pst, Connection con) {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
