package DAO;

import java.util.ArrayList;

import DTO.HOPDONGLAODONG;


import connectionSQL.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class HopDongLaoDongDAO implements DAOInterface<HOPDONGLAODONG>{

    public static HopDongLaoDongDAO getInstance() {
        return new HopDongLaoDongDAO();
    }

    @Override
    public ArrayList<HOPDONGLAODONG> getList() {
        ArrayList<HOPDONGLAODONG> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "select * from HOPDONGLAODONG hd \r\n"
            + "join NHANVIEN nv on hd.maHopDong = nv.maHopDong \r\n"
            + "join CONNGUOI cn on nv.CMND = cn.CMND\r\n"
            + "join PHONGBAN pb on nv.maPhong = pb.maPhong where nv.trangThai=1";

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HOPDONGLAODONG x = new HOPDONGLAODONG();
				x.setMaHopDong(rs.getString(1));
				x.setMaNhanVien(rs.getString("maNhanVien")); 
				x.setTenNhanVien(rs.getString("hoTen"));
				x.setPhongBan(rs.getString("tenPhong"));
				x.setTuNgay(rs.getDate(2).toLocalDate().plusDays(2));
				x.setDenNgay(rs.getDate(3).toLocalDate().plusDays(2));
				x.setLoaiHopDong(rs.getString(4));
				x.setLuongCoBan(rs.getDouble(5));
				list.add(x);
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return list;
    }

    @Override
    public int insert(HOPDONGLAODONG t) {
        int result = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "insert into HOPDONGLAODONG values(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaHopDong());
			pst.setDate(2, Date.valueOf(t.getTuNgay()));
			pst.setDate(3, Date.valueOf(t.getDenNgay()));
			pst.setString(4, t.getLoaiHopDong());
			pst.setDouble(5,t.getLuongCoBan());
			result = pst.executeUpdate();
            ConnectionManager.closeConnection(con);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(HOPDONGLAODONG t) {
        int result = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "update HOPDONGLAODONG set tuNgay = ?, denNgay = ?, loaiHopDong = ?, luongCoBan = ? where maHopDong = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, Date.valueOf(t.getTuNgay()));
            pst.setDate(2, Date.valueOf(t.getDenNgay()));
            pst.setString(3, t.getLoaiHopDong());
            pst.setDouble(4, t.getLuongCoBan());
            pst.setString(5, t.getMaHopDong());
            result = pst.executeUpdate();
            ConnectionManager.closeConnection(con);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int del(String ma) {
        int result = 0;
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "delete HOPDONGLAODONG where maHopDong = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ma);
            result = pst.executeUpdate();
            ConnectionManager.closeConnection(con);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }





    // PHUƠNG THỨC KHÔNG CÓ TRONG INTERFACE --> Chưa được kiểm chứng --> Cần kiểm chứng
    public ArrayList<HOPDONGLAODONG> getHopDongSapHetHan() { // đã check sql
		Connection con = ConnectionManager.getConnection();
        ArrayList<HOPDONGLAODONG> list = new ArrayList<>();
		try {
			
            String sql = "select * from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong join CONNGUOI cn on cn.CMND=nv.CMND join PHONGBAN pb on pb.maPhong=nv.maPhong where nv.trangThai=1 and DATEDIFF(DAY, GETDATE(), denNgay) < 180 and DATEDIFF(DAY, GETDATE(), denNgay) > 0";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				HOPDONGLAODONG x = new HOPDONGLAODONG();
				x.setMaHopDong(rs.getString("maHopDong"));
				x.setTenNhanVien(rs.getString("hoTen"));
				x.setMaNhanVien(rs.getString("maNhanVien"));
				x.setPhongBan(rs.getString("tenPhong"));
				x.setTuNgay(rs.getDate("tuNgay").toLocalDate().plusDays(2));
				x.setDenNgay(rs.getDate("denNgay").toLocalDate().plusDays(2));
				x.setLoaiHopDong(rs.getString("loaiHopDong"));
				x.setLuongCoBan(rs.getDouble("luongCoBan"));
				list.add(x);
			}
			ConnectionManager.closeConnection(con);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<HOPDONGLAODONG> getHopDongDaHetHan() { // đã check sql
		Connection con = ConnectionManager.getConnection();
        ArrayList<HOPDONGLAODONG> list = new ArrayList<>();
		try {
			String sql = "select * from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong join CONNGUOI cn on cn.CMND=nv.CMND join PHONGBAN pb on pb.maPhong=nv.maPhong where nv.trangThai=1 and DATEDIFF(DAY, GETDATE(), denNgay) <= 0";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HOPDONGLAODONG x = new HOPDONGLAODONG();
				x.setMaHopDong(rs.getString("maHopDong"));
				x.setTenNhanVien(rs.getString("hoTen"));
				x.setMaNhanVien(rs.getString("maNhanVien"));
				x.setPhongBan(rs.getString("tenPhong"));
				x.setTuNgay(rs.getDate("tuNgay").toLocalDate().plusDays(2));
				x.setDenNgay(rs.getDate("denNgay").toLocalDate().plusDays(2));
				x.setLoaiHopDong(rs.getString("loaiHopDong"));
				x.setLuongCoBan(rs.getDouble("luongCoBan"));
				list.add(x);
			}
			ConnectionManager.closeConnection(con);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

    public ArrayList<HOPDONGLAODONG> getHopDongTheoTenPhong(String tenPhong) {
		Connection con = ConnectionManager.getConnection();
        ArrayList<HOPDONGLAODONG> list = new ArrayList<>();
		try {
			String sql = "select * from HOPDONGLAODONG HD join NHANVIEN NV on HD.maHopDong = NV.maHopDong join PHONGBAN PB on NV.maPhong = PB.maPhong where NV.trangThai=1 and PB.tenPhong = N'" + tenPhong + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HOPDONGLAODONG x = new HOPDONGLAODONG();
				x.setMaHopDong(rs.getString(1));
				x.setTuNgay(rs.getDate(2).toLocalDate().plusDays(2));
				x.setDenNgay(rs.getDate(3).toLocalDate().plusDays(2));
				x.setLoaiHopDong(rs.getString(4));
				x.setLuongCoBan(rs.getDouble(5));
				list.add(x);
			}
			ConnectionManager.closeConnection(con);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<HOPDONGLAODONG> getHopDongMoiKy() {
		Connection con = ConnectionManager.getConnection();
        ArrayList<HOPDONGLAODONG> list = new ArrayList<>();
		try {
			String sql = "select * from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong join CONNGUOI cn on cn.CMND= nv.CMND join PHONGBAN pb on pb.maPhong=nv.maPhong where nv.trangThai=1 and DATEDIFF(DAY, tuNgay, GETDATE()) < 186";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HOPDONGLAODONG x = new HOPDONGLAODONG();
				x.setMaHopDong(rs.getString("maHopDong"));
				x.setTenNhanVien(rs.getString("hoTen"));
				x.setMaNhanVien(rs.getString("maNhanVien"));
				x.setPhongBan(rs.getString("tenPhong"));
				x.setTuNgay(rs.getDate("tuNgay").toLocalDate().plusDays(2));
				x.setDenNgay(rs.getDate("denNgay").toLocalDate().plusDays(2));
				x.setLoaiHopDong(rs.getString("loaiHopDong"));
				x.setLuongCoBan(rs.getDouble("luongCoBan"));
				list.add(x);
			}
			ConnectionManager.closeConnection(con);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getSoLuongHopDong(){ // đã check sql
		Connection con = ConnectionManager.getConnection();
		try {
            String sql = "select COUNT(*) from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong where nv.trangThai=1";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int sum = 0;
			while (rs.next()) {
				sum = rs.getInt(1);
			}
			ConnectionManager.closeConnection(con);
			return sum;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getSoLuongHopDongMoiKy(){
		Connection con = ConnectionManager.getConnection();
		try {
            String sql = "select COUNT(*) from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong where nv.trangThai=1 and DATEDIFF(DAY, tuNgay, GETDATE()) < 180";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int sum = 0;
			while (rs.next()) {
				sum = rs.getInt(1);
			}
			ConnectionManager.closeConnection(con);
			return sum;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void updateHopDong(LocalDate denNgay ,String loaiHopDong, String maHopDong) {
		Connection con = ConnectionManager.getConnection();
		try {
            String sql = "update HOPDONGLAODONG set denNgay = ?, loaiHopDong = ? where maHopDong = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1, Date.valueOf(denNgay));
			pst.setString(2, loaiHopDong);
			pst.setString(3, maHopDong);
			pst.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getLoaiHopDong(String maHopDong) {
		Connection con = ConnectionManager.getConnection();
		try {
            String sql = "select * from HOPDONGLAODONG where maHopDong ='" + maHopDong + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String loaiHopDong = "";
			while (rs.next()) {
				loaiHopDong = (rs.getString(4));
			}
			ConnectionManager.closeConnection(con);
			return loaiHopDong;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getSoLuongHopDongSapHetHan(){
		Connection con = ConnectionManager.getConnection();
		try {
            String sql = "select COUNT(*) from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong where nv.trangThai=1 and DATEDIFF(DAY, GETDATE(), denNgay) < 180 and DATEDIFF(DAY, GETDATE(), denNgay) > 0";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int sum = 0;
			while (rs.next()) {
				sum = rs.getInt(1);
			}
			ConnectionManager.closeConnection(con);
			return sum;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getSoLuongHopDongDaHetHan(){
		Connection con = ConnectionManager.getConnection();
		try {
			String sql = "select COUNT(*) from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong where nv.trangThai=1 and DATEDIFF(DAY, GETDATE(), denNgay) <= 0";
            PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int sum = 0;
			while (rs.next()) {
				sum = rs.getInt(1);
			}
			ConnectionManager.closeConnection(con);
			return sum;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int[] getThongKeTiLeLoaiHopDong() {
		Connection con = ConnectionManager.getConnection();
		int []data = new int[5];
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery("select COUNT(*) from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong where nv.trangThai=1 and DATEDIFF(YEAR, tuNgay, denNgay) <=2");
			while (rs.next()) {
				data[0] = rs.getInt(1);
			}
			rs = ps.executeQuery("select COUNT(*) from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong where nv.trangThai=1 and DATEDIFF(YEAR, tuNgay, denNgay) >2 and DATEDIFF(YEAR, tuNgay, denNgay) <=5");
			while (rs.next()) {
				data[1] = rs.getInt(1);
			}
			rs = ps.executeQuery("select COUNT(*) from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong where nv.trangThai=1 and DATEDIFF(YEAR, tuNgay, denNgay) >5 and DATEDIFF(YEAR, tuNgay, denNgay) <=8");
			while (rs.next()) {
				data[2] = rs.getInt(1);
			}
			rs = ps.executeQuery("select COUNT(*) from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong where nv.trangThai=1 and DATEDIFF(YEAR, tuNgay, denNgay) >8 and DATEDIFF(YEAR, tuNgay, denNgay) <=10");
			while (rs.next()) {
				data[3] = rs.getInt(1);
			}
			rs = ps.executeQuery("select COUNT(*) from HOPDONGLAODONG hd join NHANVIEN nv on hd.maHopDong=nv.maHopDong where nv.trangThai=1 and DATEDIFF(YEAR, tuNgay, denNgay) >10");
			while (rs.next()) {
				data[4] = rs.getInt(1);
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public int[] getSoLuongHopDongHetHanVaKiTrongNam(int nam) {
		Connection con = ConnectionManager.getConnection();
		int []data = new int[2];
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery("select COUNT(*) from HOPDONGLAODONG where tuNgay like '%"+nam+"%'");
			while (rs.next()) {
				data[0] = rs.getInt(1);
			}
			rs = ps.executeQuery("select COUNT(*) from HOPDONGLAODONG where denNgay like '%"+nam+"%'");
			while (rs.next()) {
				data[1] = rs.getInt(1);
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
}
