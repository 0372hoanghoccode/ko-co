package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectionSQL.ConnectionManager;

import DTO.CHAMCONG;
import DTO.LUONG;
import BUS.LUONG_BUS;

public class LuongDAO implements DAOInterface<LUONG>{
	
	public static LuongDAO getInstance() {
		return new LuongDAO();
	}
    
    @Override
    public ArrayList<LUONG> getList() {
        ArrayList<LUONG> list = new ArrayList<>();
        Connection con = ConnectionManager.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM LUONG");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LUONG luong = new LUONG();
                luong.setMaLuong(rs.getString("maLuong"));
                luong.setMaBangChamCong(rs.getString("maBangChamCong"));
                luong.setLuongThucTe(rs.getDouble("luongThucTe"));
                luong.setLuongThuong(rs.getDouble("luongThuong"));
                luong.setPhuCapChucVu(rs.getDouble("phuCapChucVu"));
                luong.setPhuCapKhac(rs.getDouble("phuCapKhac"));
                luong.setKhoanTruBaoHiem(rs.getDouble("khoanTruBaoHiem"));
                luong.setKhoanTruKhac(rs.getDouble("khoanTruKhac"));
                luong.setThue(rs.getDouble("thue"));
                luong.setThucLanh(rs.getDouble("thucLanh"));
                list.add(luong);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return list;
    }

    @Override
    public int insert(LUONG x) {
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "INSERT INTO LUONG VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, x.getMaLuong());
            ps.setString(2, x.getMaBangChamCong());
            ps.setDouble(3, x.getLuongThucTe());
            ps.setDouble(4, x.getLuongThuong());
            ps.setDouble(5, x.getPhuCapChucVu());
            ps.setDouble(6, x.getPhuCapKhac());
            ps.setDouble(7, x.getKhoanTruBaoHiem());
            ps.setDouble(8, x.getKhoanTruKhac());
            ps.setDouble(9, x.getThue());
            ps.setDouble(10, x.getThucLanh());
            int rowsAffected = ps.executeUpdate();
            
            ps.close();
            con.close();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace(); 
            return 0;
        }
    }

    @Override
    public int del(String maLuong) {
        Connection con = ConnectionManager.getConnection();
        try {
            String sql = "DELETE FROM LUONG WHERE maLuong=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maLuong);
            int rowsAffected = ps.executeUpdate();
            
            ps.close();
            con.close();

            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace(); 
            return 0;
        }
    }

    public Object[][]  getObjectToRender() {
		Connection con = ConnectionManager.getConnection();
		ArrayList<Object[]> list = new ArrayList<>();
		try {
			
			Statement st = con.createStatement();
			String sql = "select * from LUONG l \r\n"
					+ "join BANGCHAMCONG bcc on bcc.maBangChamCong = l.maBangChamCong\r\n"
					+ "join NHANVIEN nv on nv.maNhanVien = bcc.maNhanVien\r\n"
					+ "join CONNGUOI cn on cn.CMND = nv.CMND left join HOPDONGLAODONG hd on hd.maHopDong = nv.maHopDong";
					
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			while(rs.next()) {
				String luongCoBan = LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongCoBan"));
						
				if(rs.getString("maHopDong")==null) {
					luongCoBan = LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongThuViec"));
				}
				Object[] obj = new Object[] {
						count+1+"",rs.getString("maNhanVien")+" - "+rs.getString("hoTen"),rs.getInt("thangChamCong")+"/"+rs.getInt("namChamCong"),
						luongCoBan,
						
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongThucTe"))+"",
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("phuCapChucVu")+rs.getDouble("phuCapKhac")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongThuong"))+"",
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("khoanTruBaoHiem")+rs.getDouble("khoanTruKhac")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("thue"))+"",
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("thucLanh"))
				};
				list.add(obj);
				count++;
			}
			Object[][] data = new Object[count][];
			for(int i=0;i<count;i++) {
				data[i] = list.get(i);
			}
			con.close();
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Object[][]  getObjectToRender(String tenPhong,String thang,String nam,int sortby,int type_sort, String maNhanVien) {
		Connection con = ConnectionManager.getConnection();
		ArrayList<Object[]> list = new ArrayList<>();
		try {
			String sql = "select * from  LUONG l \r\n"
					+ "join BANGCHAMCONG bcc on bcc.maBangChamCong = l.maBangChamCong\r\n"
					+ "join NHANVIEN nv on nv.maNhanVien = bcc.maNhanVien\r\n"
					+ "join CONNGUOI cn on cn.CMND = nv.CMND left join HOPDONGLAODONG hd on hd.maHopDong = nv.maHopDong\r\n"
					+ "join PHONGBAN p on p.maPhong = nv.maPhong where 1=1 ";
			// filter
			if(!tenPhong.equalsIgnoreCase("Phòng ban")) {
				sql+="and p.tenPhong = N'"+tenPhong+"' ";
			}
			if(!thang.equalsIgnoreCase("Tháng")) {
				sql+="and bcc.thangChamCong = "+thang.split(" ")[1]+" ";
			}
			if(!nam.equalsIgnoreCase("Năm")) {
				sql+="and bcc.namChamCong =  "+nam.split(" ")[1]+" ";
			}
			if(maNhanVien!=null && !maNhanVien.equals("")) {
				sql+="and nv.maNhanVien =  N'"+maNhanVien+"' ";
			}
			// sort
			if(type_sort==0) {
				if(sortby==0) {
					sql+=" order by bcc.namChamCong asc , bcc.thangChamCong asc";
				}else {
					sql+=" order by l.thucLanh asc";
				}
			}else {
				if(sortby==0) {
					sql+=" order by bcc.namChamCong desc , bcc.thangChamCong desc";
				}else {
					sql+=" order by l.thucLanh desc";
				}
			}
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			int count2 = 0;
			while(rs.next()) {
				String luongCoBan = LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongCoBan"));
						
				if(rs.getString("maHopDong")==null) {
					luongCoBan = LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongThuViec"));
				}

				Object[] obj = new Object[] {
						count2+1+"",rs.getString("maNhanVien")+" - "+rs.getString("hoTen"),rs.getInt("thangChamCong")+"/"+rs.getInt("namChamCong"),
						luongCoBan,
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("phuCapChucVu")+rs.getDouble("phuCapKhac")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongThucTe"))+"",
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongThuong"))+"",
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("khoanTruBaoHiem")+rs.getDouble("khoanTruKhac")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("thue"))+"",
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("thucLanh"))
				};
				list.add(obj);
				count2++;
			}
			Object[][] data = new Object[count2][];
			for(int i=0;i<count2;i++) {
				data[i] = list.get(i);
			}
			con.close();
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Object[][]  getObjectToRender(String maNhanVien) {
		Connection con = ConnectionManager.getConnection();
		ArrayList<Object[]> list = new ArrayList<>();
		try {
			String sql = "select * from  LUONG l \r\n"
					+ "join BANGCHAMCONG bcc on bcc.maBangChamCong = l.maBangChamCong\r\n"
					+ "join NHANVIEN nv on nv.maNhanVien = bcc.maNhanVien\r\n"
					+ "join CONNGUOI cn on cn.CMND = nv.CMND left join HOPDONGLAODONG hd on hd.maHopDong = nv.maHopDong\r\n"
					+ "join PHONGBAN p on p.maPhong = nv.maPhong where nv.maNhanVien=N'"+maNhanVien+"'";
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			int count2 = 0;
			while(rs.next()) {
				String luongCoBan = LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongCoBan"));
						
				if(rs.getString("maHopDong")==null) {
					luongCoBan = LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongThuViec"));
				}

				Object[] obj = new Object[] {
						count2+1+"",rs.getString("maNhanVien")+" - "+rs.getString("hoTen"),rs.getInt("thangChamCong")+"/"+rs.getInt("namChamCong"),
						luongCoBan,
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("phuCapChucVu")+rs.getDouble("phuCapKhac")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongThucTe"))+"",
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("luongThuong"))+"",
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("khoanTruBaoHiem")+rs.getDouble("khoanTruKhac")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("thue"))+"",
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("thucLanh"))
				};
				list.add(obj);
				count2++;
			}
			Object[][] data = new Object[count2][];
			for(int i=0;i<count2;i++) {
				data[i] = list.get(i);
			}
			con.close();
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Object[][]  getDanhSachLuongThuongToRender() {
		Connection con = ConnectionManager.getConnection();
		ArrayList<Object[]> list = new ArrayList<>();
		try {
			
			Statement st = con.createStatement();
			String sql = "select * from  LUONG l \r\n"
					+ "join BANGCHAMCONG bcc on bcc.maBangChamCong = l.maBangChamCong\r\n"
					+ "join NHANVIEN nv on nv.maNhanVien = bcc.maNhanVien\r\n"
					+ "join CONNGUOI cn on cn.CMND = nv.CMND left join HOPDONGLAODONG hd on hd.maHopDong = nv.maHopDong";
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			while(rs.next()) {
				double luongCoBan = rs.getDouble("luongCoBan");
				double luongThuong = rs.getDouble("luongThuong");
				if(rs.getString("maHopDong")==null) {
					luongCoBan = rs.getDouble("luongThuViec");
				}
				double tiLePhanTram = Math.round(luongThuong/luongCoBan*100);
				
				Object[] obj = new Object[] {
						count+1+"",rs.getString("maNhanVien")+" - "+rs.getString("hoTen"),rs.getInt("thangChamCong")+"/"+rs.getInt("namChamCong"),
						LUONG_BUS.changeSalaryToFormatString(luongCoBan),
						tiLePhanTram+"%",
						LUONG_BUS.changeSalaryToFormatString(luongThuong)
				};
				
				list.add(obj);
				count++;
			}
			Object[][] data = new Object[count][];
			for(int i=0;i<count;i++) {
				data[i] = list.get(i);
			}
			con.close();
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Object[][]  getDanhSachLuongThuongToRender(String tenPhong,String thang,String nam,int sortby,int type_sort) {
		Connection con = ConnectionManager.getConnection();
		ArrayList<Object[]> list = new ArrayList<>();
		try {
			String sql = "select * from  LUONG l \r\n"
					+ "join BANGCHAMCONG bcc on bcc.maBangChamCong = l.maBangChamCong\r\n"
					+ "join NHANVIEN nv on nv.maNhanVien = bcc.maNhanVien\r\n"
					+ "join CONNGUOI cn on cn.CMND = nv.CMND left join HOPDONGLAODONG hd on hd.maHopDong = nv.maHopDong\r\n"
					+ "join PHONGBAN p on p.maPhong = nv.maPhong where 1=1 ";
			// filter
			if(!tenPhong.equalsIgnoreCase("Phòng ban")) {
				sql+="and p.tenPhong = N'"+tenPhong+"' ";
			}
			if(!thang.equalsIgnoreCase("Tháng")) {
				
				sql+="and bcc.thangChamCong = "+thang.split(" ")[1]+" ";
			}
			if(!nam.equalsIgnoreCase("Năm")) {
				sql+="and bcc.namChamCong =  "+nam.split(" ")[1]+" ";
			}
			// sort
			if(type_sort==0) {
				if(sortby==0) {
					sql+=" order by bcc.namChamCong asc , bcc.thangChamCong asc";
				}else if(sortby==1) {
					sql+=" order by l.luongThuong asc";
				}else if(sortby==2){
					sql+=" order by l.phuCapChucVu + l.phuCapKhac asc";
				}else if(sortby==3){
					sql+=" order by l.thue asc";
				}else {
					sql+=" order by l.thucLanh asc";
				}
			}else {
				if(sortby==0) {
					sql+=" order by bcc.namChamCong desc , bcc.thangChamCong desc";
				}else if(sortby==1) {
					sql+=" order by l.luongThuong desc";
				}else if(sortby==2){
					sql+=" order by l.phuCapChucVu + l.phuCapKhac desc";
				}else if(sortby==3){
					sql+=" order by l.thue desc";
				}else {
					sql+=" order by l.thucLanh desc";
				}
			}
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			int count2 = 0;
			while(rs.next()) {
				double luongCoBan = rs.getDouble("luongCoBan");
				double luongThuong = rs.getDouble("luongThuong");
				if(rs.getString("maHopDong")==null) {
					luongCoBan = rs.getDouble("luongThuViec");
				}
				double tiLePhanTram = Math.round(luongThuong/luongCoBan*100);
				
				Object[] obj = new Object[] {
						count2+1+"",rs.getString("maNhanVien")+" - "+rs.getString("hoTen"),rs.getInt("thangChamCong")+"/"+rs.getInt("namChamCong"),
						LUONG_BUS.changeSalaryToFormatString(luongCoBan),
						tiLePhanTram+"%",
						LUONG_BUS.changeSalaryToFormatString(luongThuong)
				};
				
				list.add(obj);
				count2++;
			}
			Object[][] data = new Object[count2][];
			for(int i=0;i<count2;i++) {
				data[i] = list.get(i);
			}
			
			con.close();
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void thuongTatCaNhanVien(int nam,int thang, double phanTramThuong) {
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from NHANVIEN nv\r\n"
				+ "join BANGCHAMCONG bcc on bcc.maNhanVien = nv.maNhanVien \r\n"
				+ "join LUONG l on bcc.maBangChamCong = l.maBangChamCong\r\n"
				+ "left join HOPDONGLAODONG hd on hd.maHopDong = nv.maHopDong \r\n"
				+ "where bcc.namChamCong = ? and bcc.thangChamCong = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, nam);
			pst.setInt(2, thang);
			ResultSet rs = pst.executeQuery();
            LUONG_BUS luongBus = new LUONG_BUS(); 
			while(rs.next()) {
				double luongCoBan = rs.getDouble("luongCoBan");
				if(rs.getString("maHopDong")==null) {
					luongCoBan = rs.getDouble("luongThuViec");
				}
				double luongThuong = Math.round(phanTramThuong*luongCoBan/100);
				CHAMCONG bcc = new CHAMCONG(rs.getString("maBangChamCong"), rs.getString("maNhanVien"), rs.getInt("thangChamCong"), rs.getInt("thangChamCong"), rs.getInt("soNgayLamViec"), rs.getInt("soNgayNghi"), rs.getInt("soNgayTre"), rs.getInt("soGioLamThem"), rs.getString("chiTiet"), "");
				LUONG l = LUONG_BUS.chuyenBangChamCongSangLuong(bcc, luongThuong, rs.getDouble("phuCapKhac"), rs.getDouble("khoanTruKhac"));
				luongBus.update(l);
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void thuongNhanVienTheoPhongBan(int nam,int thang, double phanTramThuong,String maPhong) {
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from NHANVIEN nv\r\n"
				+ "join BANGCHAMCONG bcc on bcc.maNhanVien = nv.maNhanVien \r\n"
				+ "join LUONG l on bcc.maBangChamCong = l.maBangChamCong\r\n"
				+ "left join HOPDONGLAODONG hd on hd.maHopDong = nv.maHopDong \r\n"
				+ "where bcc.namChamCong = ? and bcc.thangChamCong = ? and nv.maPhong = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, nam);
			pst.setInt(2, thang);
			pst.setString(3, maPhong);
			ResultSet rs = pst.executeQuery();
            LUONG_BUS luongBus = new LUONG_BUS(); 
			while(rs.next()) {
				double luongCoBan = rs.getDouble("luongCoBan");
				if(rs.getString("maHopDong")==null) {
					luongCoBan = rs.getDouble("luongThuViec");
				}
				double luongThuong = Math.round(phanTramThuong*luongCoBan/100);
				CHAMCONG bcc = new CHAMCONG(rs.getString("maBangChamCong"), rs.getString("maNhanVien"), rs.getInt("thangChamCong"), rs.getInt("thangChamCong"), rs.getInt("soNgayLamViec"), rs.getInt("soNgayNghi"), rs.getInt("soNgayTre"), rs.getInt("soGioLamThem"), rs.getString("chiTiet"), "");
				LUONG l = LUONG_BUS.chuyenBangChamCongSangLuong(bcc, luongThuong, rs.getDouble("phuCapKhac"), rs.getDouble("khoanTruKhac"));
				luongBus.update(l);
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void thuongNhanVien(int nam,int thang, double phanTramThuong,String maNhanVien) {
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from NHANVIEN nv\r\n"
				+ "join BANGCHAMCONG bcc on bcc.maNhanVien = nv.maNhanVien \r\n"
				+ "join LUONG l on bcc.maBangChamCong = l.maBangChamCong\r\n"
				+ "left join HOPDONGLAODONG hd on hd.maHopDong = nv.maHopDong \r\n"
				+ "where bcc.namChamCong = ? and bcc.thangChamCong = ? and nv.maNhanVien = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, nam);
			pst.setInt(2, thang);
			pst.setString(3, maNhanVien);
			ResultSet rs = pst.executeQuery();
            LUONG_BUS luongBus = new LUONG_BUS(); 
			while(rs.next()) {
				double luongCoBan = rs.getDouble("luongCoBan");
				if(rs.getString("maHopDong")==null) {
					luongCoBan = rs.getDouble("luongThuViec");
				}
				double luongThuong = Math.round(phanTramThuong*luongCoBan/100);
				CHAMCONG bcc = new CHAMCONG(rs.getString("maBangChamCong"), rs.getString("maNhanVien"), rs.getInt("thangChamCong"), rs.getInt("thangChamCong"), rs.getInt("soNgayLamViec"), rs.getInt("soNgayNghi"), rs.getInt("soNgayTre"), rs.getInt("soGioLamThem"), rs.getString("chiTiet"), "");
				LUONG l = LUONG_BUS.chuyenBangChamCongSangLuong(bcc, luongThuong, rs.getDouble("phuCapKhac"), rs.getDouble("khoanTruKhac"));
				luongBus.update(l);
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void phuCapNhanVien(int nam,int thang,String maNhanVien,double phuCapKhac) {
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from NHANVIEN nv\r\n"
				+ "join BANGCHAMCONG bcc on bcc.maNhanVien = nv.maNhanVien \r\n"
				+ "join LUONG l on bcc.maBangChamCong = l.maBangChamCong\r\n"
				+ "where bcc.namChamCong = ? and bcc.thangChamCong = ? and nv.maNhanVien = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, nam);
			pst.setInt(2, thang);
			pst.setString(3, maNhanVien);
			ResultSet rs = pst.executeQuery();
            LUONG_BUS luongBus = new LUONG_BUS(); 
			while(rs.next()) {
				CHAMCONG bcc = new CHAMCONG(rs.getString("maBangChamCong"), rs.getString("maNhanVien"), rs.getInt("thangChamCong"), rs.getInt("thangChamCong"), rs.getInt("soNgayLamViec"), rs.getInt("soNgayNghi"), rs.getInt("soNgayTre"), rs.getInt("soGioLamThem"), rs.getString("chiTiet"), "");
				LUONG l = LUONG_BUS.chuyenBangChamCongSangLuong(bcc, rs.getDouble("luongThuong"), phuCapKhac, rs.getDouble("khoanTruKhac"));
				luongBus.update(l);
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void khoanTruNhanVien(int nam,int thang,String maNhanVien,double khoanTru) {
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from NHANVIEN nv\r\n"
				+ "join BANGCHAMCONG bcc on bcc.maNhanVien = nv.maNhanVien \r\n"
				+ "join LUONG l on bcc.maBangChamCong = l.maBangChamCong\r\n"
				+ "where bcc.namChamCong = ? and bcc.thangChamCong = ? and nv.maNhanVien = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, nam);
			pst.setInt(2, thang);
			pst.setString(3, maNhanVien);
			ResultSet rs = pst.executeQuery();
            LUONG_BUS luongBus = new LUONG_BUS(); // Tạo đối tượng của lớp triển khai
			while(rs.next()) {
				CHAMCONG bcc = new CHAMCONG(rs.getString("maBangChamCong"), rs.getString("maNhanVien"), rs.getInt("thangChamCong"), rs.getInt("thangChamCong"), rs.getInt("soNgayLamViec"), rs.getInt("soNgayNghi"), rs.getInt("soNgayTre"), rs.getInt("soGioLamThem"), rs.getString("chiTiet"), "");
				LUONG l = LUONG_BUS.chuyenBangChamCongSangLuong(bcc, rs.getDouble("luongThuong"), rs.getDouble("phuCapKhac"), khoanTru);
                luongBus.update(l); // Gọi phương thức trên đối tượng vừa tạo
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    @Override
    public int update(LUONG x) {
        Connection con = ConnectionManager.getConnection();
        
        try {
            String sql = "update LUONG set maBangChamCong = ? , luongThuong = ?, phuCapChucVu = ?, phuCapKhac = ?, khoanTruBaoHiem = ?, khoanTruKhac = ?, thue = ?, thucLanh = ? where maLuong = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, x.getMaBangChamCong());
            pst.setDouble(2,x.getLuongThuong());
            pst.setDouble(3,x.getPhuCapChucVu());
            pst.setDouble(4,x.getPhuCapKhac());
            pst.setDouble(5,x.getKhoanTruBaoHiem());
            pst.setDouble(6,x.getKhoanTruKhac());
            pst.setDouble(7,x.getThue());
            pst.setDouble(8,x.getThucLanh());
            pst.setString(9, x.getMaLuong());
            int rowsAffected = pst.executeUpdate();
            ConnectionManager.closeConnection(con);
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public Object[][] getDanhSachPhuCapKhoanTruToRender() {
		Connection con = ConnectionManager.getConnection();
		try {
			
			Statement st = con.createStatement();
			String sql = "select count(maLuong) from LUONG l \r\n"
					+ "join BANGCHAMCONG bcc on bcc.maBangChamCong = l.maBangChamCong\r\n"
					+ "join NHANVIEN nv on nv.maNhanVien = bcc.maNhanVien\r\n"
					+ "join CONNGUOI cn on cn.CMND = nv.CMND ";
			ResultSet rs = st.executeQuery(sql);
			Object[][] data = null;
			while(rs.next()) {
				 data = new Object[rs.getInt(1)][];
			}
			
			sql = "select * from LUONG l \r\n"
					+ "join BANGCHAMCONG bcc on bcc.maBangChamCong = l.maBangChamCong\r\n"
					+ "join NHANVIEN nv on nv.maNhanVien = bcc.maNhanVien\r\n"
					+ "join CONNGUOI cn on cn.CMND = nv.CMND order by bcc.namChamCong desc , bcc.thangChamCong desc";
			rs = st.executeQuery(sql);
			int count = 0;
			while(rs.next()) {
				Object[] obj = new Object[] {
						count+1+"",rs.getString("maNhanVien")+" - "+rs.getString("hoTen"),rs.getInt("thangChamCong")+"/"+rs.getInt("namChamCong"),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("phuCapChucVu")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("phuCapKhac")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("khoanTruBaoHiem")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("khoanTruKhac"))
				};
				data[count] = obj;
				count++;
			}
			
			
			
			con.close();
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Object[][]  getDanhSachPhuCapKhoanTruToRender(String tenPhong,String thang,String nam,int sortby,int type_sort) {
		Connection con = ConnectionManager.getConnection();
		ArrayList<Object[]> list = new ArrayList<>();
		try {
			String sql = "select * from  LUONG l \r\n"
					+ "join BANGCHAMCONG bcc on bcc.maBangChamCong = l.maBangChamCong\r\n"
					+ "join NHANVIEN nv on nv.maNhanVien = bcc.maNhanVien\r\n"
					+ "join CONNGUOI cn on cn.CMND = nv.CMND left join HOPDONGLAODONG hd on hd.maHopDong = nv.maHopDong\r\n"
					+ "join PHONGBAN p on p.maPhong = nv.maPhong where 1=1 ";
			// filter
			if(!tenPhong.equalsIgnoreCase("Phòng ban")) {
				sql+="and p.tenPhong = N'"+tenPhong+"' ";
			}
			if(!thang.equalsIgnoreCase("Tháng")) {
				
				sql+="and bcc.thangChamCong = "+thang.split(" ")[1]+" ";
			}
			if(!nam.equalsIgnoreCase("Năm")) {
				sql+="and bcc.namChamCong =  "+nam.split(" ")[1]+" ";
			}
			// sort
			if(type_sort==0) {
				if(sortby==0) {
					sql+=" order by bcc.namChamCong asc , bcc.thangChamCong asc";
				}else if(sortby==1) {
					sql+=" order by l.phuCapChucVu + l.phuCapKhac asc";
				}else {
					sql+=" order by l.khoanTruBaoHiem + l.khoanTruKhac asc";
				}
			}else {
				if(sortby==0) {
					sql+=" order by bcc.namChamCong desc , bcc.thangChamCong desc";
				}else if(sortby==1) {
					sql+=" order by l.phuCapChucVu + l.phuCapKhac desc";
				}else {
					sql+=" order by l.khoanTruBaoHiem + l.khoanTruKhac desc";
				}
			}
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			int count2 = 0;
			while(rs.next()) {
				double luongCoBan = rs.getDouble("luongCoBan");
				double luongThuong = rs.getDouble("luongThuong");
				if(rs.getString("maHopDong")==null) {
					luongCoBan = rs.getDouble("luongThuViec");
				}
				double tiLePhanTram = Math.round(luongThuong/luongCoBan*100);
				
				Object[] obj = new Object[] {
						count2+1+"",rs.getString("maNhanVien")+" - "+rs.getString("hoTen"),rs.getInt("thangChamCong")+"/"+rs.getInt("namChamCong"),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("phuCapChucVu")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("phuCapKhac")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("khoanTruBaoHiem")),
						LUONG_BUS.changeSalaryToFormatString(rs.getDouble("khoanTruKhac"))
				};
				
				list.add(obj);
				count2++;
			}
			Object[][] data = new Object[count2][];
			for(int i=0;i<count2;i++) {
				data[i] = list.get(i);
			}
			
			con.close();
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void tangLuongHangNam(String maHopDong, double mucTang) {
		Connection con = ConnectionManager.getConnection();
		String sql = "update HOPDONGLAODONG set luongCoBan = luongCoBan*? where maHopDong=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDouble(1, mucTang);
			pst.setString(2, maHopDong);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
