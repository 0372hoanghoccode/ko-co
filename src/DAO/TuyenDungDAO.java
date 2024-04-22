package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.BAOCAOTUYENDUNG;
import connectionSQL.ConnectionManager;

public class TuyenDungDAO implements DAOInterface<BAOCAOTUYENDUNG> {

    public static TuyenDungDAO getInstance() {
        return new TuyenDungDAO();
    }

    @Override
    public ArrayList<BAOCAOTUYENDUNG> getList() {
        ArrayList<BAOCAOTUYENDUNG> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("SELECT * FROM BAOCAOTUYENDUNG ORDER BY maTuyenDung");
            rs = pst.executeQuery();
            while (rs.next()) {
                BAOCAOTUYENDUNG x = new BAOCAOTUYENDUNG();
                x.setMaTuyenDung(rs.getString("maTuyenDung"));
                x.setChucVu(rs.getString("chucVu"));
                x.setHocVan(rs.getString("hocVan"));
                x.setGioiTinh(rs.getString("yeuCauGioiTinh"));
                x.setDoTuoi(rs.getString("yeuCauDoTuoi"));
                x.setSoLuongCanTuyen(rs.getInt("soLuongCanTuyen"));
                x.setHanNopHoSo(rs.getDate("hanNopHoSo").toLocalDate());
                x.setMucLuongToiThieu(rs.getDouble("mucLuongToiThieu"));
                x.setMucLuongToiDa(rs.getDouble("mucLuongToiDa"));
                // Query for additional fields like soLuongNopHoSo and soLuongDaTuyen
                x.setSoLuongNopHoSo(getSoLuongNopHoSo(con, rs.getString("maTuyenDung")));
                x.setSoLuongDaTuyen(getSoLuongDaTuyen(con, rs.getString("maTuyenDung")));
                list.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pst, con);
        }
        return list;
    }

    public int update(BAOCAOTUYENDUNG x) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(
                "UPDATE BAOCAOTUYENDUNG SET " +
                "chucVu = ?, hocVan = ?, yeuCauGioiTinh = ?, yeuCauDoTuoi = ?, " +
                "soLuongCanTuyen = ?, hanNopHoSo = ?, mucLuongToiThieu = ?, mucLuongToiDa = ? " +
                "WHERE maTuyenDung = ?");
            pst.setString(1, x.getChucVu());
            pst.setString(2, x.getHocVan());
            pst.setString(3, x.getGioiTinh());
            pst.setString(4, x.getDoTuoi());
            pst.setInt(5, x.getSoLuongCanTuyen());
            pst.setDate(6, Date.valueOf(x.getHanNopHoSo()));
            pst.setDouble(7, x.getMucLuongToiThieu());
            pst.setDouble(8, x.getMucLuongToiDa());
            pst.setString(9, x.getMaTuyenDung());
    
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pst, con);
        }
    }
    private  int getSoLuongNopHoSo(Connection con, String maTuyenDung) throws SQLException {
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(CMND) AS Total FROM UNGVIEN WHERE maTuyenDung = ?");
        pst.setString(1, maTuyenDung);
        ResultSet rs = pst.executeQuery();
        int total = 0;
        if (rs.next()) {
            total = rs.getInt("Total");
        }
        rs.close();
        pst.close();
        return total;
    }

    private int getSoLuongDaTuyen(Connection con, String maTuyenDung) throws SQLException {
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(maUngVien) AS Total FROM UNGVIEN UV JOIN NHANVIEN NV ON NV.CMND = UV.CMND WHERE UV.maTuyenDung = ?");
        pst.setString(1, maTuyenDung);
        ResultSet rs = pst.executeQuery();
        int total = 0;
        if (rs.next()) {
            total = rs.getInt("Total");
        }
        rs.close();
        pst.close();
        return total;
    }

    public String getChucVuTuyenDung(String maTuyenDung) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("SELECT chucVu FROM BAOCAOTUYENDUNG WHERE maTuyenDung = ?");
            pst.setString(1, maTuyenDung);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("chucVu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pst, con);
        }
        return null;
    }

    public int insert(BAOCAOTUYENDUNG x) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("INSERT INTO BAOCAOTUYENDUNG (maTuyenDung, chucVu, hocVan, yeuCauGioiTinh, yeuCauDoTuoi, soLuongCanTuyen, hanNopHoSo, mucLuongToiThieu, mucLuongToiDa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, x.getMaTuyenDung());
            pst.setString(2, x.getChucVu());
            pst.setString(3, x.getHocVan());
            pst.setString(4, x.getGioiTinh());
            pst.setString(5, x.getDoTuoi());
            pst.setInt(6, x.getSoLuongCanTuyen());
            pst.setDate(7, Date.valueOf(x.getHanNopHoSo()));
            pst.setDouble(8, x.getMucLuongToiThieu());
            pst.setDouble(9, x.getMucLuongToiDa());
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pst, con);
        }
    }

    public  int del(String maTuyenDung) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("DELETE FROM BAOCAOTUYENDUNG WHERE maTuyenDung = ?");
            pst.setString(1, maTuyenDung);
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
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
