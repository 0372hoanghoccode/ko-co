package DAO;

import DTO.TAIKHOAN;
import connectionSQL.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaiKhoanDAO {

    public static void insertTAIKHOAN(TAIKHOAN taikhoan) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement("INSERT INTO TAIKHOAN (username, pass, maNhomQuyen, avatar) VALUES (?, ?, ?, ?)")) {
            
            pst.setString(1, taikhoan.getUsername());
            pst.setString(2, taikhoan.getPass());
            pst.setString(3, taikhoan.getMaNhomQuyen());
            pst.setString(4, taikhoan.getAvatarImg());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTAIKHOAN(TAIKHOAN taikhoan) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement("UPDATE TAIKHOAN SET  pass = ?, maNhomQuyen = ?, avatar = ? WHERE username = ?")) {
            
            pst.setString(1, taikhoan.getPass());
            pst.setString(2, taikhoan.getMaNhomQuyen());
            pst.setString(3, taikhoan.getAvatarImg());
            pst.setString(4, taikhoan.getUsername());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TAIKHOAN getTAIKHOAN(String username) {
        TAIKHOAN taikhoan = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM TAIKHOAN WHERE username = ?")) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
//                    taikhoan = new TAIKHOAN(rs.getString("maNhanVien"), rs.getString("username"), rs.getString("pass"), rs.getString("maNhomQuyen"), rs.getString("avatar"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taikhoan;
    }

    public static String getAvatar(String username) {
        String avatar = "";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT avatar FROM TAIKHOAN WHERE username = ?")) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    avatar = rs.getString("avatar");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avatar;
    }

    public static ArrayList<TAIKHOAN> getList() {
        ArrayList<TAIKHOAN> list = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM TAIKHOAN JOIN NHANVIEN ON NHANVIEN.maNhanVien = TAIKHOAN.username")) {
            while (rs.next()) {
//                TAIKHOAN taikhoan = new TAIKHOAN(rs.getString("maNhanVien"), rs.getString("username"), rs.getString("pass"), rs.getString("maNhomQuyen"), rs.getString("avatar"));
//                list.add(taikhoan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Object[][] getObjectToRender() {
        ArrayList<Object[]> list = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT nv.maNhanVien, cn.hoTen, tk.username, tk.pass, tk.maNhomQuyen FROM CONNGUOI cn JOIN NHANVIEN nv ON cn.CMND = nv.CMND JOIN CHUCVU cv ON nv.maChucVu = cv.maChucVu JOIN TAIKHOAN tk ON nv.maNhanVien = tk.username WHERE nv.trangThai = 1")) {
            int count = 0;
            while (rs.next()) {
                Object[] ob = new Object[]{count + 1 + "", rs.getString("username") + " - " + rs.getString("hoTen"), rs.getString("maNhomQuyen")};
                list.add(ob);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Object[0][]);
    }

    public static Object[][] getObjectToRender(String tenPhong) {
        ArrayList<Object[]> list = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection();
             Statement st = con.createStatement()) {
            String sql = "SELECT nv.maNhanVien, cn.hoTen, cv.tenChucVu, tk.username, tk.pass, tk.maNhomQuyen FROM CONNGUOI cn JOIN NHANVIEN nv ON cn.CMND = nv.CMND JOIN CHUCVU cv ON nv.maChucVu = cv.maChucVu JOIN TAIKHOAN tk ON nv.maNhanVien = tk.username JOIN PHONGBAN pb ON nv.maPhong = pb.maPhong WHERE nv.trangThai = 1 ";
            if (!tenPhong.equalsIgnoreCase("Phòng ban")) {
                sql += "AND pb.tenPhong = N'" + tenPhong + "'";
            }
            try (ResultSet rs = st.executeQuery(sql)) {
                int count = 0;
                while (rs.next()) {
                    Object[] ob = new Object[]{count + 1 + "", rs.getString("maNhanVien") + " - " + rs.getString("hoTen"), rs.getString("maNhomQuyen")};
                    list.add(ob);
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Object[0][]);
    }

    public static String[] getDanhSachEmailVaUsername() {
        String[] data = null;
        try (Connection con = ConnectionManager.getConnection();
             Statement st = con.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT COUNT(maNhanVien) FROM CONNGUOI cn JOIN NHANVIEN nv ON cn.CMND = nv.CMND WHERE nv.trangThai = 1")) {
                if (rs.next()) {
                    data = new String[rs.getInt(1) * 2];
                }
            }
            try (ResultSet rs = st.executeQuery("SELECT * FROM CONNGUOI cn JOIN NHANVIEN nv ON cn.CMND = nv.CMND WHERE nv.trangThai = 1")) {
                int count = 0;
                while (rs.next()) {
                    data[count] = rs.getString("email");
                    data[count + 1] = rs.getString("maNhanVien");
                    count += 2;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getEmail(String username) {
        String email = "";
        try (Connection con = ConnectionManager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT cn.email FROM CONNGUOI cn JOIN NHANVIEN nv ON cn.CMND = nv.CMND WHERE nv.maNhanVien = '" + username + "'")) {
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    public static String getUsername(String email) {
        String user = "";
        try (Connection con = ConnectionManager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT tk.username FROM CONNGUOI cn JOIN NHANVIEN nv ON cn.CMND = nv.CMND JOIN TAIKHOAN tk ON tk.username=nv.maNhanVien WHERE cn.email = N'" + email + "'")) {
            if (rs.next()) {
                user = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void updatePassword(String username, String pass) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement("UPDATE TAIKHOAN SET pass=? WHERE username=?")) {
            pst.setString(1, pass);
            pst.setString(2, username);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateQuyenTaiKhoan(String username, String maNhomQuyen) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement("UPDATE TAIKHOAN SET maNhomQuyen=? WHERE username=?")) {
            pst.setString(1, maNhomQuyen);
            pst.setString(2, username);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
// viết bậy bạ 
