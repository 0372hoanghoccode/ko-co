package DAO;

import DTO.CHAMCONG;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import connectionSQL.ConnectionManager;
import java.sql.Connection;

public class ChamCongDAO implements DAOInterface<CHAMCONG> {

    public static ChamCongDAO getInstance() {
        return new ChamCongDAO();
    }

    @Override
    public ArrayList<CHAMCONG> getList() {
        ArrayList<CHAMCONG> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM BANGCHAMCONG");

            while (rs.next()) {
                CHAMCONG temp = new CHAMCONG();

                temp.setMaBangChamCong(rs.getString("maBangChamCong"));
                temp.setMaNhanVien(rs.getString("maNhanVien"));
                temp.setThangChamCong(rs.getInt("thangChamCong"));
                temp.setNamChamCong(rs.getInt("namChamCong"));
                temp.setSoNgayLamViec(rs.getInt("soNgayLamViec"));
                temp.setSoNgayNghi(rs.getInt("soNgayNghi"));
                temp.setSoNgayTre(rs.getInt("soNgayTre"));
                temp.setSoGioLamThem(rs.getInt("soGioLamThem"));
                temp.setChiTiet(rs.getString("chiTiet"));

                list.add(temp);
            }

            ConnectionManager.closeConnection(con);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(CHAMCONG bangChamCong) {
        int rowsAffected = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO BANGCHAMCONG VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, bangChamCong.getMaBangChamCong());
            pst.setString(2, bangChamCong.getMaNhanVien());
            pst.setInt(3, bangChamCong.getThangChamCong());
            pst.setInt(4, bangChamCong.getNamChamCong());
            pst.setInt(5, bangChamCong.getSoNgayLamViec());
            pst.setInt(6, bangChamCong.getSoNgayNghi());
            pst.setInt(7, bangChamCong.getSoNgayTre());
            pst.setInt(8, bangChamCong.getSoGioLamThem());
            pst.setString(9, bangChamCong.getChiTiet());
            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return rowsAffected;
    }

    @Override
    public int update(CHAMCONG bangChamCong) {
        int rowsAffected = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE BANGCHAMCONG SET thangChamCong = ?, namChamCong = ?, soNgayLamViec = ?, soNgayNghi = ?, soNgayTre = ?, soGioLamThem = ?, chiTiet = ? WHERE maBangChamCong = ?");
            pst.setInt(1, bangChamCong.getThangChamCong());
            pst.setInt(2, bangChamCong.getNamChamCong());
            pst.setInt(3, bangChamCong.getSoNgayLamViec());
            pst.setInt(4, bangChamCong.getSoNgayNghi());
            pst.setInt(5, bangChamCong.getSoNgayTre());
            pst.setInt(6, bangChamCong.getSoGioLamThem());
            pst.setString(7, bangChamCong.getChiTiet());
            pst.setString(8, bangChamCong.getMaBangChamCong());
            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return rowsAffected;
    }

    @Override
    public int del(String maBangChamCong) {
        int rowsAffected = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM CHAMCONG WHERE maBangChamCong = ?");
            pst.setString(1, maBangChamCong);
            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return rowsAffected;
    }

    // Lấy Bảng Chấm Công từ mã
    public static CHAMCONG getBangChamCongByMa(String maBCC) {
        Connection con = ConnectionManager.getConnection();

        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM CHAMCONG WHERE maBangChamCong = N'" + maBCC + "'");
            while (rs.next()) {
                CHAMCONG x = new CHAMCONG();

                x.setMaBangChamCong(rs.getString("maBangChamCong"));
                x.setMaNhanVien(rs.getString("maNhanVien"));
                x.setThangChamCong(rs.getInt("thangChamCong"));
                x.setNamChamCong(rs.getInt("namChamCong"));
                x.setSoNgayLamViec(rs.getInt("soNgayLamViec"));
                x.setSoNgayNghi(rs.getInt("soNgayNghi"));
                x.setSoNgayTre(rs.getInt("soNgayTre"));
                x.setSoGioLamThem(rs.getInt("soGioLamThem"));
                x.setChiTiet(rs.getString("chiTiet"));
                ConnectionManager.closeConnection(con);
                return x;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // Lấy tên nhân viên từ mã Bảng Chấm Công
    public static String getTenNhanVien(String maBangChamCong) {
        Connection con = ConnectionManager.getConnection();

        try {
            String ten = "";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM BANGCHAMCONG JOIN NHANVIEN ON NHANVIEN.maNhanVien = BANGCHAMCONG.maNhanVien JOIN CONNGUOI ON CONNGUOI.CMND = NHANVIEN.CMND WHERE BANGCHAMCONG.maBangChamCong = '" + maBangChamCong + "' ");

            while (rs.next()) {
                ten = rs.getString("maNhanVien") + " - " + rs.getString("hoTen");
            }

            ConnectionManager.closeConnection(con);
            return ten;
        } catch (SQLException e) {
        }
        return null;
    }

    // Lấy mã phòng ban từ tên phòng ban
    public static String getMaPhongBanFromTen(String ten) {
        Connection con = ConnectionManager.getConnection();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT maPhong FROM PHONGBAN WHERE tenPhong = N'" + ten + "'");

            while (rs.next()) {
                return rs.getString("maPhong");
            }

            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
        }

        ConnectionManager.closeConnection(con);
        return null;
    }

    // Lấy danh sách Bảng Chấm Công theo mã phòng ban
    public static ArrayList<CHAMCONG> getBangChamCongByMaPhongBan(String maPhong) {
        Connection con = ConnectionManager.getConnection();

        try {
            ArrayList<CHAMCONG> list = new ArrayList<>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM BANGCHAMCONG BCC JOIN NHANVIEN NV ON BCC.maNhanVien = NV.maNhanVien JOIN PHONGBAN PB ON NV.maPhong = PB.maPhong WHERE PB.maPhong = '" + maPhong + "'");

            while (rs.next()) {
                CHAMCONG x = new CHAMCONG();
                x.setMaBangChamCong(rs.getString("maBangChamCong"));
                x.setMaNhanVien(rs.getString("maNhanVien"));
                x.setThangChamCong(rs.getInt("thangChamCong"));
                x.setNamChamCong(rs.getInt("namChamCong"));
                x.setSoNgayLamViec(rs.getInt("soNgayLamViec"));
                x.setSoNgayNghi(rs.getInt("soNgayNghi"));
                x.setSoNgayTre(rs.getInt("soNgayTre"));
                x.setSoGioLamThem(rs.getInt("soGioLamThem"));
                x.setChiTiet(rs.getString("chiTiet"));
                list.add(x);
            }

            ConnectionManager.closeConnection(con);
            return list;

        } catch (SQLException e) {
        }
        return null;
    }

    // Lấy danh sách Bảng Chấm Công theo mã nhân viên
    public static ArrayList<CHAMCONG> getBangChamCongByMaNhanVien(String maNhanVien) {
        Connection con = ConnectionManager.getConnection();

        try {
            ArrayList<CHAMCONG> list = new ArrayList<>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM BANGCHAMCONG BCC JOIN NHANVIEN NV ON BCC.maNhanVien = NV.maNhanVien JOIN PHONGBAN PB ON NV.maPhong = PB.maPhong WHERE NV.maNhanVien = '" + maNhanVien + "'");

            while (rs.next()) {
                CHAMCONG x = new CHAMCONG();
                x.setMaBangChamCong(rs.getString("maBangChamCong"));
                x.setMaNhanVien(rs.getString("maNhanVien"));
                x.setThangChamCong(rs.getInt("thangChamCong"));
                x.setNamChamCong(rs.getInt("namChamCong"));
                x.setSoNgayLamViec(rs.getInt("soNgayLamViec"));
                x.setSoNgayNghi(rs.getInt("soNgayNghi"));
                x.setSoNgayTre(rs.getInt("soNgayTre"));
                x.setSoGioLamThem(rs.getInt("soGioLamThem"));
                x.setChiTiet(rs.getString("chiTiet"));
                list.add(x);
            }

            ConnectionManager.closeConnection(con);
            return list;

        } catch (SQLException e) {
        }
        return null;
    }
}
