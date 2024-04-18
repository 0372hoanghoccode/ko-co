package DAO;

import DTO.PHONGBAN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionSQL.ConnectionManager;
import java.sql.Statement;

public class PhongBanDAO implements DAOInterface<PHONGBAN> {
    
    public static PhongBanDAO getInstance() {
        return new PhongBanDAO();
    }

    @Override
    public ArrayList<PHONGBAN> getList() {
        ArrayList<PHONGBAN> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM PHONGBAN ORDER BY tenPhong");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PHONGBAN phongBan = new PHONGBAN();
                phongBan.setMaPhong(rs.getString("maPhong"));
                phongBan.setTenPhong(rs.getString("tenPhong"));
                phongBan.setNgayThanhLap(rs.getDate("ngayThanhLap").toLocalDate().plusDays(2));
                if (rs.getString("maTruongPhong") == null) {
                    phongBan.setMaTruongPhong("Chưa có");
                } else {
                    phongBan.setMaTruongPhong(rs.getString("maTruongPhong"));
                }
                list.add(phongBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return list;
    }

    @Override
    public int insert(PHONGBAN phongBan) {
        int rowsAffected = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO PHONGBAN VALUES (?, ?, ?, ?)");
            pst.setString(1, phongBan.getMaPhong());
            pst.setString(2, phongBan.getTenPhong());
            pst.setDate(3, java.sql.Date.valueOf(phongBan.getNgayThanhLap()));
            if (phongBan.getMaTruongPhong().equals("Chưa có") || phongBan.getMaTruongPhong().equals("")) {
                pst.setNull(4, java.sql.Types.VARCHAR);
            } else {
                pst.setString(4, phongBan.getMaTruongPhong());
            }
            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return rowsAffected;
    }

    @Override
    public int update(PHONGBAN phongBan) {
        int rowsAffected = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE PHONGBAN SET tenPhong = ?, ngayThanhLap = ?, maTruongPhong = ? WHERE maPhong = ?");
            pst.setString(1, phongBan.getTenPhong());
            pst.setDate(2, java.sql.Date.valueOf(phongBan.getNgayThanhLap()));
            if (phongBan.getMaTruongPhong().equals("Chưa có") || phongBan.getMaTruongPhong().equals("")) {
                pst.setNull(3, java.sql.Types.VARCHAR);
            } else {
                pst.setString(3, phongBan.getMaTruongPhong());
            }
            pst.setString(4, phongBan.getMaPhong());
            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return rowsAffected;
    }

    @Override
    public int del(String maPhong) {
        int rowsAffected = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM PHONGBAN WHERE maPhong = ?");
            pst.setString(1, maPhong);
            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return rowsAffected;
    }

    public static String getMaSoTuTen(String tenPhong) {
        String maPhong = null;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT maPhong FROM PHONGBAN WHERE tenPhong = ?");
            pst.setString(1, tenPhong);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                maPhong = rs.getString("maPhong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return maPhong;
    }

    public static String getTenTuMaSo(String maPhong) {
        String tenPhong = null;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT tenPhong FROM PHONGBAN WHERE maPhong = ?");
            pst.setString(1, maPhong);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tenPhong = rs.getString("tenPhong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return tenPhong;
    }

    public static int getSoLuongNhanVien(String maPhongBan) {
        int soLuong = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(maNhanVien) AS count FROM NHANVIEN WHERE NHANVIEN.trangThai=1 AND maPhong = ?");
            pst.setString(1, maPhongBan);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return soLuong;
    }
    public static double[] getAverageSalaryData(String maPhongBan) {
        double[] data = new double[3];
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(maNhanVien), SUM(luongThuViec), SUM(luongCoBan) FROM NHANVIEN LEFT JOIN HOPDONGLAODONG ON NHANVIEN.maHopDong = HOPDONGLAODONG.maHopDong WHERE maPhong = ?");
            pst.setString(1, maPhongBan);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                data[0] = rs.getInt(1);
                data[1] = rs.getDouble(2);
                data[2] = rs.getDouble(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return data;
    }

    public static Object[][] getNhanVienCuaPhongBanData(String maPhong) {
        Object[][] data = null;
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst1 = con.prepareStatement("SELECT COUNT(maNhanVien) AS count FROM NHANVIEN WHERE trangThai=1 AND maPhong = ?");
            pst1.setString(1, maPhong);
            ResultSet rs1 = pst1.executeQuery();
            if (rs1.next()) {
                data = new Object[rs1.getInt("count")][];
            }
            PreparedStatement pst2 = con.prepareStatement("SELECT * FROM CONNGUOI JOIN NHANVIEN ON CONNGUOI.CMND = NHANVIEN.CMND JOIN CHUCVU ON CHUCVU.maChucVu = NHANVIEN.maChucVu WHERE NHANVIEN.trangThai=1 AND maPhong = ?");
            pst2.setString(1, maPhong);
            ResultSet rs2 = pst2.executeQuery();
            int count = 0;
            while (rs2.next()) {
                String loaiHinh = "Chính thức";
                if (rs2.getString("maHopDong") == null) {
                    loaiHinh = "Thử việc";
                }
                data[count] = new String[] {String.valueOf(count + 1), rs2.getString("maNhanVien") + " - " + rs2.getString("hoTen"), loaiHinh, rs2.getString("tenChucVu")};
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return data;
    }

    public static ArrayList<String> getDanhSachMaSo() {
        ArrayList<String> data = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM PHONGBAN");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return data;
    }

    public static ArrayList<Object[]> getDuLieuChucVuThongKe(String maPhong) {
        ArrayList<Object[]> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT TOP 5 cv.tenChucVu, COUNT(nv.maNhanVien) AS sl FROM CHUCVU cv JOIN NHANVIEN nv ON nv.maChucVu = cv.maChucVu JOIN PHONGBAN pb ON nv.maPhong = pb.maPhong WHERE nv.trangThai=1 AND pb.maPhong = ? GROUP BY cv.tenChucVu ORDER BY sl DESC");
            pst.setString(1, maPhong);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Object[] {rs.getString(1), rs.getInt(2)});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return list;
    }

    public static int[] getDuLieuGioiTinhThongKe(String maPhong) {
        int[] data = new int[2];
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT cn.gioiTinh, COUNT(nv.maNhanVien) FROM NHANVIEN nv JOIN CONNGUOI cn ON nv.CMND = cn.CMND JOIN PHONGBAN pb ON nv.maPhong = pb.maPhong WHERE nv.trangThai=1 AND pb.maPhong = ? GROUP BY cn.gioiTinh");
            pst.setString(1, maPhong);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String gioiTinh = rs.getString(1);
                int soLuong = rs.getInt(2);
                if (gioiTinh.equals("Nam")) {
                    data[0] = soLuong;
                } else {
                    data[1] = soLuong;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return data;
    }

    public static int[] getDuLieuDoTuoiThongKe(String maPhong) {
        int[] data = new int[4];
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(maNhanVien) FROM NHANVIEN JOIN CONNGUOI ON CONNGUOI.CMND = NHANVIEN.CMND WHERE DATEDIFF(YEAR,CONNGUOI.ngaySinh,GETDATE()) >= 16 AND DATEDIFF(YEAR,CONNGUOI.ngaySinh,GETDATE()) <= 25 AND NHANVIEN.trangThai=1 AND maPhong=?");
            pst.setString(1, maPhong);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                data[0] = rs.getInt(1);
            }
            pst = con.prepareStatement("SELECT COUNT(maNhanVien) FROM NHANVIEN JOIN CONNGUOI ON CONNGUOI.CMND = NHANVIEN.CMND WHERE DATEDIFF(YEAR,CONNGUOI.ngaySinh,GETDATE()) > 25 AND DATEDIFF(YEAR,CONNGUOI.ngaySinh,GETDATE()) <= 40 AND NHANVIEN.trangThai=1 AND maPhong=?");
            pst.setString(1, maPhong);
            rs = pst.executeQuery();
            if (rs.next()) {
                data[1] = rs.getInt(1);
            }
            pst = con.prepareStatement("SELECT COUNT(maNhanVien) FROM NHANVIEN JOIN CONNGUOI ON CONNGUOI.CMND = NHANVIEN.CMND WHERE DATEDIFF(YEAR,CONNGUOI.ngaySinh,GETDATE()) > 40 AND DATEDIFF(YEAR,CONNGUOI.ngaySinh,GETDATE()) <= 55 AND NHANVIEN.trangThai=1 AND maPhong=?");
            pst.setString(1, maPhong);
            rs = pst.executeQuery();
            if (rs.next()) {
                data[2] = rs.getInt(1);
            }
            pst = con.prepareStatement("SELECT COUNT(maNhanVien) FROM NHANVIEN JOIN CONNGUOI ON CONNGUOI.CMND = NHANVIEN.CMND WHERE DATEDIFF(YEAR,CONNGUOI.ngaySinh,GETDATE()) > 55 AND DATEDIFF(YEAR,CONNGUOI.ngaySinh,GETDATE()) <= 65 AND NHANVIEN.trangThai=1 AND maPhong=?");
            pst.setString(1, maPhong);
            rs = pst.executeQuery();
            if (rs.next()) {
                data[3] = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return data;
    }

    public static ArrayList<Object[]> getDanhSachTenVaSoLuongNhanVienPhongBan() {
        ArrayList<Object[]> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT TOP 5 pb.tenPhong, COUNT(nv.maNhanVien) AS sl FROM PHONGBAN pb JOIN NHANVIEN nv ON pb.maPhong = nv.maPhong WHERE nv.trangThai=1 GROUP BY pb.maPhong, pb.tenPhong ORDER BY sl DESC");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Object[] {rs.getString(1), rs.getInt(2)});
            }
            if (list.size() == 5) {
                list.add(new Object[] {"Các phòng khác", 2}); // Giả sử có 2 phòng khác
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return list;
    }

    public static String[] getDanhSachPhongBan() {
        String[] data = null;
        Connection con = ConnectionManager.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(maPhong) AS count FROM PHONGBAN");
            if (rs.next()) {
                data = new String[rs.getInt("count")];
            }
            rs = st.executeQuery("SELECT * FROM PHONGBAN ORDER BY maPhong");
            int count = 0;
            while (rs.next()) {
                data[count] = rs.getString(1);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return data;
    }
    
}