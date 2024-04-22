package DAO;

import DTO.UNGVIEN;
import connectionSQL.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.DIACHI;
import DTO.TRINHDO;

public class UngVienDAO implements DAOInterface<UNGVIEN> {

    public static UngVienDAO getInstance() {
        return new UngVienDAO();
    }

    @Override
    public ArrayList<UNGVIEN> getList() {
        ArrayList<UNGVIEN> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("SELECT * from UNGVIEN join CONNGUOI on UNGVIEN.CMND=CONNGUOI.CMND join CMND on UNGVIEN.CMND= CMND.soCMND join TRINHDO on TRINHDO.maTrinhDo=UNGVIEN.maTrinhDo");
            rs = pst.executeQuery();
            while (rs.next()) {
                UNGVIEN uv = new UNGVIEN();
                uv.setMaUngVien(rs.getString("maUngVien"));
                uv.setHoTen(rs.getString("hoTen"));
                uv.setGioiTinh(rs.getString("gioiTinh"));
                uv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
                uv.setSdt(rs.getString("soDienThoai"));
                uv.setEmail(rs.getString("email"));
                uv.setDiaChi(new DIACHI( rs.getString("tinhThanhPho"), rs.getString("quanHuyen"), rs.getString("phuongXa"), rs.getString("duong"), rs.getString("soNha")));
                uv.setTrinhDo(new TRINHDO(rs.getString("maTrinhDo"), rs.getString("trinhDoHocVan"), rs.getString("trinhDoChuyenMon"), rs.getString("chuyenNganh")));
                list.add(uv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                ConnectionManager.closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int insert(UNGVIEN uv) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("INSERT INTO UNGVIEN (maUngVien, hoTen, gioiTinh, ngaySinh, soDienThoai, email, diaChi, trinhDoHocVan) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, uv.getMaUngVien());
            pst.setString(2, uv.getHoTen());
            pst.setString(3, uv.getGioiTinh());
            pst.setDate(4, java.sql.Date.valueOf(uv.getNgaySinh()));
            pst.setString(5, uv.getSdt());
            pst.setString(6, uv.getEmail());
            pst.setString(7, uv.getDiaChi().toString());
            pst.setString(8, uv.getTrinhDo().getTrinhDoHocVan()); 
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pst != null) pst.close();
                ConnectionManager.closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int update(UNGVIEN uv) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("UPDATE UNGVIEN SET hoTen=?, gioiTinh=?, ngaySinh=?, soDienThoai=?, email=?, diaChi=?, trinhDoHocVan=? WHERE maUngVien=?");
            pst.setString(1, uv.getHoTen());
            pst.setString(2, uv.getGioiTinh());
            pst.setDate(3, java.sql.Date.valueOf(uv.getNgaySinh()));
            pst.setString(4, uv.getSdt());
            pst.setString(5, uv.getEmail());
            pst.setString(6, uv.getDiaChi().toString());
            pst.setString(7, uv.getTrinhDo().getTrinhDoHocVan()); 
            pst.setString(8, uv.getMaUngVien());
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pst != null) pst.close();
                ConnectionManager.closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int del(String maUngVien) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("DELETE FROM UNGVIEN WHERE maUngVien=?");
            pst.setString(1, maUngVien);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pst != null) pst.close();
                ConnectionManager.closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
