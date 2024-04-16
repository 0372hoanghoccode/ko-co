package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import DTO.CONNGUOI;
import connectionSQL.ConnectionManager;

public class ConNguoiDAO {
    public static void insertCONNGUOI(CONNGUOI x) {
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO CONNGUOI VALUES(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, x.getCmnd().getSoCmnd());
            pst.setString(2, x.getHoTen());
            pst.setString(3, x.getGioiTinh());
            pst.setDate(4, Date.valueOf(x.getNgaySinh()));
            pst.setString(5, x.getDiaChi().toString());
            pst.setString(6, x.getSdt());
//            pst.setString(7, x.getTinhTrangHonNhan());
            pst.setString(7, x.getDanToc());
            pst.setString(8, x.getTonGiao());
            pst.setString(9, x.getEmail());
            pst.executeUpdate();
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCONNGUOI(CONNGUOI x) {
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE CONNGUOI SET hoTen = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, SDT = ?, danToc = ?, tonGiao = ?, email = ? WHERE CMND = ?");
            pst.setString(1, x.getHoTen());
            pst.setString(2, x.getGioiTinh());
            pst.setDate(3, Date.valueOf(x.getNgaySinh()));
            pst.setString(4, x.getDiaChi().toString());
            pst.setString(5, x.getSdt());
//            pst.setString(6, x.getTinhTrangHonNhan());
            pst.setString(6, x.getDanToc());
            pst.setString(7, x.getTonGiao());
            pst.setString(8, x.getEmail());
            pst.setString(9, x.getCmnd().getSoCmnd());
            pst.executeUpdate();
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCONNGUOI(String cmnd) {
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM CONNGUOI WHERE CMND = ?");
            pst.setString(1, cmnd);
            pst.executeUpdate();
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CONNGUOI selectCONNGUOIById(String cmnd) {
        Connection con = ConnectionManager.getConnection();
        CONNGUOI connguoi = null;
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM CONNGUOI WHERE CMND = ?");
            pst.setString(1, cmnd);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                connguoi = new CONNGUOI();
//                connguoi.setCmnd(rs.getString("CMND"));
                connguoi.setHoTen(rs.getString("hoTen"));
                connguoi.setGioiTinh(rs.getString("gioiTinh"));
                connguoi.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
//                connguoi.setDiaChi(rs.getString("diaChi"));
                connguoi.setSdt(rs.getString("SDT"));
//                connguoi.setTinhTrangHonNhan(rs.getString("tinhTrangHonNhan"));
                connguoi.setDanToc(rs.getString("danToc"));
                connguoi.setTonGiao(rs.getString("tonGiao"));
                connguoi.setEmail(rs.getString("email"));
            }
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connguoi;
    }

    public static ArrayList<CONNGUOI> selectAllCONNGUOI() {
        ArrayList<CONNGUOI> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM CONNGUOI");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CONNGUOI connguoi = new CONNGUOI();
//                connguoi.setCmnd(rs.getString("CMND"));
                connguoi.setHoTen(rs.getString("hoTen"));
                connguoi.setGioiTinh(rs.getString("gioiTinh"));
                connguoi.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
//                connguoi.setDiaChi(rs.getString("diaChi"));
                connguoi.setSdt(rs.getString("SDT"));
//                connguoi.setTinhTrangHonNhan(rs.getString("tinhTrangHonNhan"));
                connguoi.setDanToc(rs.getString("danToc"));
                connguoi.setTonGiao(rs.getString("tonGiao"));
                connguoi.setEmail(rs.getString("email"));
                list.add(connguoi);
            }
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<CONNGUOI> searchCONNGUOI(String keyword) {
        ArrayList<CONNGUOI> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM CONNGUOI WHERE hoTen LIKE ?");
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CONNGUOI connguoi = new CONNGUOI();
//                connguoi.setCmnd(rs.getString("CMND"));
                connguoi.setHoTen(rs.getString("hoTen"));
                connguoi.setGioiTinh(rs.getString("gioiTinh"));
                connguoi.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
//                connguoi.setDiaChi(rs.getString("diaChi"));
                connguoi.setSdt(rs.getString("SDT"));
//                connguoi.setTinhTrangHonNhan(rs.getString("tinhTrangHonNhan"));
                connguoi.setDanToc(rs.getString("danToc"));
                connguoi.setTonGiao(rs.getString("tonGiao"));
                connguoi.setEmail(rs.getString("email"));
                list.add(connguoi);
            }
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int getNumberOfCONNGUOI() {
        int count = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) AS total FROM CONNGUOI");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
