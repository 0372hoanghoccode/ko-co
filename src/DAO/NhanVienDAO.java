package DAO;

import java.util.ArrayList;


// --- IMPORT DTO ----
import DTO.HOPDONGLAODONG;
import DTO.NHANVIEN;
import DTO.NHANVIENCHINHTHUC;
import DTO.NHANVIENTHUVIEC;
import DTO.TAIKHOAN;
import DTO.TRINHDO;
import DTO.CHUCVU;
import DTO.CMND;
import DTO.CONNGUOI;
import DTO.DIACHI;

// --- IMPORT SQL -----
import connectionSQL.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class NhanVienDAO implements DAOInterface<NHANVIEN>{
	
	// cmnd, con nguoi, trinh do, chuc vu
	
	
	public static NhanVienDAO getInstance() {
		return new NhanVienDAO();
	}



	// ----------------- IMPLEMENT INTERFACE -----------------
	@Override
	public ArrayList<NHANVIEN> getList()  {
		Connection con = ConnectionManager.getConnection();
		ArrayList<NHANVIEN> list = new ArrayList<>();
		try {
			String sql = "select * from CONNGUOI "
					+ "join NHANVIEN nv on CONNGUOI.CMND = nv.CMND "
					+ "join CMND on CMND.soCMND = CONNGUOI.CMND "
					+ "join TRINHDO on nv.maTrinhDo = TRINHDO.maTrinhDo "
					+ "join CHUCVU on nv.maChucVu = CHUCVU.maChucVu "
					+ "join TAIKHOAN on TAIKHOAN.username = nv.maNhanVien "
					+ "left join HOPDONGLAODONG on HOPDONGLAODONG.maHopDong = nv.maHopDong "
					+ "where nv.trangThai = 1";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next() ) {
				NHANVIEN nv = null;
				String str = rs.getString(5);
				String arr[] = str.split(",");
				String arr2[] = arr[0].split(" ");
				String temp ="";
				for(int i=1;i<arr2.length;i++) {
					temp+=arr2[i]+" ";
				}
				temp = temp.trim();
				
				if (rs.getString(15)==null) { // Dòng 15 truy vấn là ngày bắt đầu thử việc
					nv = new NHANVIENTHUVIEC();
					((NHANVIENTHUVIEC)nv).setNgayBatDauThuViec(rs.getDate("ngayBatDauThuViec").toLocalDate().plusDays(2));
					((NHANVIENTHUVIEC)nv).setNgayKetThucThuViec(rs.getDate("ngayKetThucThuViec").toLocalDate().plusDays(2));
					((NHANVIENTHUVIEC)nv).setLuongThuViec(rs.getDouble("luongThuViec"));
				}
				else {
					nv = new NHANVIENCHINHTHUC();
					((NHANVIENCHINHTHUC)nv).setHOPDONG(new HOPDONGLAODONG(rs.getString("maHopDong"), rs.getDate("tuNgay").toLocalDate().plusDays(2), rs.getDate("denNgay").toLocalDate().plusDays(2), rs.getString("loaiHopDong"), rs.getDouble("luongCoBan")));
				}
				
				nv.setMaNhanVien(rs.getString("maNhanVien"));
				nv.setHoTen(rs.getString("hoTen"));
				nv.setGioiTinh(rs.getString("gioiTinh"));
				nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate().plusDays(2));
				nv.setDiaChi(new DIACHI(arr2[0],temp, arr[1], arr[2], arr[3]));
				nv.setDanToc(rs.getString("danToc"));
				nv.setTonGiao(rs.getString("tonGiao"));
				nv.setEmail(rs.getString("email"));
				nv.setSdt(rs.getString("SDT"));
				nv.setCmnd(new CMND(rs.getString("soCMND"),rs.getString("noiCap"),rs.getDate("ngayCap").toLocalDate().plusDays(2)));
				
				nv.setTaiKhoan(new TAIKHOAN(rs.getString("username"), rs.getString("pass"), rs.getString("maNhomQuyen"), rs.getString("avatar")));
				nv.setChucVu(new CHUCVU(rs.getString("maChucVu"), rs.getString("tenChucVu"), rs.getDouble("phuCapChucVu"), rs.getDate("ngayNhanChuc").toLocalDate().plusDays(2)));
				nv.setMaPhong(rs.getString("maPhong"));
				nv.setTrinhDo(new TRINHDO(rs.getString("maTrinhDo"), rs.getString("trinhDoHocVan"), rs.getString("trinhDoChuyenMon"), rs.getString("chuyenNganh")));
				list.add(nv);
				
				
				
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insert(NHANVIEN x) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection con = ConnectionManager.getConnection();
		try {
			// tạo cmnd -> đang soi
			CMND_DAO.getInstance().insert(x.getCmnd());
			// con người -> đang soi
			ConNguoiDAO.getInstance().insert((CONNGUOI)x);
			// trinh do -> đang soi
			TrinhDoDAO.getInstance().insert(x.getTrinhDo());
			// chuc vu -> đang soi
			ChucVuDAO.getInstance().insert(x.getChucVu());
			// nhanvien
			PreparedStatement pst;
			if(x instanceof NHANVIENCHINHTHUC) {

				// hop dong lao dong -> đang soi
				HopDongLaoDongDAO.getInstance().insert(((NHANVIENCHINHTHUC)x).getHopDong());
				// nhan vien
				pst = con.prepareStatement("insert NHANVIEN values(?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, x.getMaNhanVien());
				pst.setString(2, x.getCmnd().getSoCmnd());
				pst.setString(3, x.getMaPhong());
				pst.setString(4, x.getTrinhDo().getMaTrinhDo());
				pst.setString(5, x.getChucVu().getMaChucVu());
				pst.setString(6, ((NHANVIENCHINHTHUC)x).getHopDong().getMaHopDong());
				pst.setDate(7,null);
				pst.setDate(8,null);
				pst.setDouble(9,0);
				pst.setInt(10, 1);
				pst.executeUpdate();
			}else {
				// nhan vien
				pst = con.prepareStatement("insert NHANVIEN values(?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, x.getMaNhanVien());
				pst.setString(2, x.getCmnd().getSoCmnd());
				pst.setString(3, x.getMaPhong());
				pst.setString(4, x.getTrinhDo().getMaTrinhDo());
				pst.setString(5, x.getChucVu().getMaChucVu());
				pst.setString(6, null);
				pst.setDate(7,Date.valueOf(((NHANVIENTHUVIEC)x).getNgayBatDauThuViec()));
				pst.setDate(8,Date.valueOf(((NHANVIENTHUVIEC)x).getNgayKetThucThuViec()));
				pst.setDouble(9,((NHANVIENTHUVIEC)x).getLuongThuViec());
				pst.setInt(10, 1);
				result = pst.executeUpdate();
			}


			// tài khoản -> đang soi 
			TaiKhoanDAO.getInstance().insert(x.getTaiKhoan());
			
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(NHANVIEN t) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection con = ConnectionManager.getConnection();
		try {

			// SOI đoạn này lại
			CMND_DAO.getInstance().update(t.getCmnd());
			ConNguoiDAO.getInstance().update((CONNGUOI)t);
			TrinhDoDAO.getInstance().update(t.getTrinhDo());
			ChucVuDAO.getInstance().update(t.getChucVu());

			PreparedStatement pst;
			if(t instanceof NHANVIENCHINHTHUC) {
				HopDongLaoDongDAO.getInstance().update(((NHANVIENCHINHTHUC)t).getHopDong());
				pst = con.prepareStatement("update NHANVIEN set maPhong = ?, maTrinhDo = ?, maChucVu = ?, maHopDong = ?, ngayBatDauThuViec = null, ngayKetThucThuViec = null, luongThuViec = 0 where maNhanVien = ?");
				pst.setString(1, t.getMaPhong());
				pst.setString(2, t.getTrinhDo().getMaTrinhDo());
				pst.setString(3, t.getChucVu().getMaChucVu());
				pst.setString(4, ((NHANVIENCHINHTHUC)t).getHopDong().getMaHopDong());
				pst.setString(5, t.getMaNhanVien());
				pst.executeUpdate();
			}else {
				pst = con.prepareStatement("update NHANVIEN set maPhong = ?, maTrinhDo = ?, maChucVu = ?, maHopDong = null, ngayBatDauThuViec = ?, ngayKetThucThuViec = ?, luongThuViec = ? where maNhanVien = ?");
				pst.setString(1, t.getMaPhong());
				pst.setString(2, t.getTrinhDo().getMaTrinhDo());
				pst.setString(3, t.getChucVu().getMaChucVu());
				pst.setDate(4,Date.valueOf(((NHANVIENTHUVIEC)t).getNgayBatDauThuViec()));
				pst.setDate(5,Date.valueOf(((NHANVIENTHUVIEC)t).getNgayKetThucThuViec()));
				pst.setDouble(6,((NHANVIENTHUVIEC)t).getLuongThuViec());
				pst.setString(7, t.getMaNhanVien());
				result = pst.executeUpdate();
			
			}
			TaiKhoanDAO.getInstance().update(t.getTaiKhoan());
			ConnectionManager.closeConnection(con);
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int del(String ma) { // vẫn chưa hiểu được yêu cầu nha (MẤY NÍ NHỚ COI DÙM) --> kiểu hàm này là dc rồi hay là cho nó bay màu khỏi csdl luôn
		// TODO Auto-generated method stub
		int result = 0;
		Connection con = ConnectionManager.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("update NHANVIEN set trangThai = 0 where maNhanVien = ?");
			pst.setString(1, ma);
			result = pst.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}


	// ----------------- OTHER FUNCTION ----------------- chưa test nha

	public NHANVIEN getNhanVien(String maNhanVien) {
		Connection con = ConnectionManager.getConnection();
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery("select * from CONNGUOI join NHANVIEN on CONNGUOI.CMND = NHANVIEN.CMND join CMND on CMND.soCMND = CONNGUOI.CMND join TRINHDO on NHANVIEN.maTrinhDo = TRINHDO.maTrinhDo join CHUCVU on NHANVIEN.maChucVu = CHUCVU.maChucVu join TAIKHOAN on TAIKHOAN.username = NHANVIEN.maNhanVien left join HOPDONGLAODONG on HOPDONGLAODONG.maHopDong = NHANVIEN.maHopDong where NHANVIEN.maNhanVien = N'"+maNhanVien+"'");
			while(rs.next()) {
				NHANVIEN x = null;
				String str = rs.getString(5);
				String arr[] = str.split(",");
				String arr2[] = arr[0].split(" ");
				String temp ="";
				for(int i=1;i<arr2.length;i++) {
					temp+=arr2[i]+" ";
				}
				temp = temp.trim();
				
				
				if(rs.getString("maHopDong")==null) {
					x = new NHANVIENTHUVIEC();
					((NHANVIENTHUVIEC)x).setNgayBatDauThuViec(rs.getDate("ngayBatDauThuViec").toLocalDate().plusDays(2));
					((NHANVIENTHUVIEC)x).setNgayKetThucThuViec(rs.getDate("ngayKetThucThuViec").toLocalDate().plusDays(2));
					((NHANVIENTHUVIEC)x).setLuongThuViec(rs.getDouble("luongThuViec"));
				}else {
					x = new NHANVIENCHINHTHUC();
					((NHANVIENCHINHTHUC)x).setHOPDONG(new HOPDONGLAODONG(rs.getString("maHopDong"), rs.getDate("tuNgay").toLocalDate().plusDays(2), rs.getDate("denNgay").toLocalDate().plusDays(2), rs.getString("loaiHopDong"), rs.getDouble("luongCoBan")));
				}
				x.setMaNhanVien(rs.getString("maNhanVien"));
				x.setHoTen(rs.getString("hoTen"));
				x.setGioiTinh(rs.getString("gioiTinh"));
				x.setNgaySinh(rs.getDate("ngaySinh").toLocalDate().plusDays(2));
				x.setDiaChi(new DIACHI(arr2[0],temp, arr[1], arr[2], arr[3]));
				x.setDanToc(rs.getString("danToc"));
				x.setTonGiao(rs.getString("tonGiao"));
				x.setEmail(rs.getString("email"));
				x.setSdt(rs.getString("SDT"));
				x.setCmnd(new CMND(rs.getString("soCMND"),rs.getString("noiCap"),rs.getDate("ngayCap").toLocalDate().plusDays(2)));
				
				x.setTaiKhoan(new TAIKHOAN(rs.getString("username"), rs.getString("pass"), rs.getString("maNhomQuyen"), rs.getString("avatar")));
				x.setChucVu(new CHUCVU(rs.getString("maChucVu"), rs.getString("tenChucVu"), rs.getDouble("phuCapChucVu"), rs.getDate("ngayNhanChuc").toLocalDate().plusDays(2)));
				x.setMaPhong(rs.getString("maPhong"));
				x.setTrinhDo(new TRINHDO(rs.getString("maTrinhDo"), rs.getString("trinhDoHocVan"), rs.getString("trinhDoChuyenMon"), rs.getString("chuyenNganh")));
				ConnectionManager.closeConnection(con);
				return x;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<String> getDanhSachMaSo() {
		Connection con = ConnectionManager.getConnection();
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery("select maNhanVien from NHANVIEN");
			ArrayList<String> list = new ArrayList<>();
			while(rs.next()) {
				list.add(rs.getString("maNhanVien"));
			}
			ConnectionManager.closeConnection(con);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getTenNhanVien(String maSo) {
		Connection con = ConnectionManager.getConnection();
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery("select hoTen from CONNGUOI join NHANVIEN on NHANVIEN.CMND = CONNGUOI.CMND where maNhanVien = N'"+maSo+"'");
			while(rs.next()) {
				return rs.getString("hoTen");
			}
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void thayDoiMaPhongBanAll(String oldId,String newId) {
		Connection con = ConnectionManager.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("update NHANVIEN set maPhong = ? where maPhong = ?");
			pst.setString(1, newId);
			pst.setString(2, oldId);
			pst.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void thayDoiMaPhongBan(String emplopyeeId, String departmentId) {
		Connection con = ConnectionManager.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("update NHANVIEN set maPhong = ? where maNhanVien = ?");
			pst.setString(1, departmentId);
			pst.setString(2, emplopyeeId);
			pst.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




	// public void insertNHANVIENfromUNGVIEN(NHANVIEN x) {
	// 	Connection con = ConnectionManager.getConnection();
	// 	try {
	// 		// trinh do
	// 		access_TRINHDO.insertTRINHDO(x.getTrinhDo());
	// 		// chuc vu
	// 		access_CHUCVU.insertChucVu(x.getChucVu());
	// 		// nhanvien
	// 		PreparedStatement pst;
	// 		if(x instanceof NHANVIENCHINHTHUC) {
	// 			access_HOPDONGLAODONG.insertHOPDONGLAODONG(((NHANVIENCHINHTHUC)x).getHopDong());
	// 			// nhan vien
				
	// 			pst = con.prepareStatement("insert NHANVIEN values(?,?,?,?,?,?,?,?,?,?)");
	// 			pst.setString(1, x.getMaNhanVien());
	// 			pst.setString(2, x.getCmnd().getSoCmnd());
	// 			pst.setString(3, x.getMaPhong());
	// 			pst.setString(4, x.getTrinhDo().getMaTrinhDo());
	// 			pst.setString(5, x.getChucVu().getMaChucVu());
	// 			pst.setString(6, ((NHANVIENCHINHTHUC)x).getHopDong().getMaHopDong());
	// 			pst.setDate(7,null);
	// 			pst.setDate(8,null);
	// 			pst.setDouble(9,0);
	// 			pst.setInt(10, 1);
	// 			pst.executeUpdate();
	// 		}else {
	// 			// nhan vien
	// 			pst = con.prepareStatement("insert NHANVIEN values(?,?,?,?,?,?,?,?,?,?)");
	// 			pst.setString(1, x.getMaNhanVien());
	// 			pst.setString(2, x.getCmnd().getSoCmnd());
	// 			pst.setString(3, x.getMaPhong());
	// 			pst.setString(4, x.getTrinhDo().getMaTrinhDo());
	// 			pst.setString(5, x.getChucVu().getMaChucVu());
	// 			pst.setString(6, null);
	// 			pst.setDate(7,Date.valueOf(((NHANVIENTHUVIEC)x).getNgayBatDauThuViec()));
	// 			pst.setDate(8,Date.valueOf(((NHANVIENTHUVIEC)x).getNgayKetThucThuViec()));
	// 			pst.setDouble(9,((NHANVIENTHUVIEC)x).getLuongThuViec());
	// 			pst.setInt(10, 1);
	// 			pst.executeUpdate();
	// 		}
	// 		// tài khoản
	// 		access_TAIKHOAN.insertTAIKHOAN(x.getTaiKhoan());
			
	// 		ConnectionManager.closeConnection(con);
	// 	} catch (SQLException e) {
			
	// 		e.printStackTrace();
	// 	}
	// }

	public ArrayList<NHANVIEN>  getListFilterAndSort(String tenPhong,String gioitinh,String doTuoi,String loaiHinh,String mucLuong) {
		//		"2","001 - Le Quan Phat","09/2022","26","0","0","12","25,000,000","0","2,500,000","1,200,000","30,000,000"
		Connection con = ConnectionManager.getConnection();
		ArrayList<NHANVIEN> list = new ArrayList<>();
		try {
			String sql = "select * from CONNGUOI join NHANVIEN on CONNGUOI.CMND = NHANVIEN.CMND join CMND on CMND.soCMND = CONNGUOI.CMND join TRINHDO on NHANVIEN.maTrinhDo = TRINHDO.maTrinhDo join CHUCVU on NHANVIEN.maChucVu = CHUCVU.maChucVu join TAIKHOAN on TAIKHOAN.username = NHANVIEN.maNhanVien left join HOPDONGLAODONG on HOPDONGLAODONG.maHopDong = NHANVIEN.maHopDong join PHONGBAN on NHANVIEN.maPhong = PHONGBAN.maPhong where NHANVIEN.trangThai=1";
			// filter
			if(!tenPhong.equalsIgnoreCase("Phòng ban")) {
				sql+="and tenPhong = N'"+tenPhong+"' ";
			}
			if(!gioitinh.equalsIgnoreCase("Giới tính")) {
				sql+="and gioiTinh = N'"+gioitinh+"' ";
			}if(!doTuoi.equalsIgnoreCase("Độ tuổi")) {
				String arrdoTuoi[] = doTuoi.split("-");
				sql+="and year(getdate()) - year(ngaySinh) >= "+arrdoTuoi[0] +" and year(getdate()) - year(ngaySinh) <= "+arrdoTuoi[1]+" ";
			}
			if(!loaiHinh.equalsIgnoreCase("Loại hình")) {
				if(loaiHinh.equalsIgnoreCase("Chính thức") || loaiHinh.equalsIgnoreCase("Nhân viên chính thức")) {
					sql+="and NHANVIEN.maHopDong is not null ";
				}else {
					sql+="and NHANVIEN.maHopDong is null ";
				}
			}
			if(!mucLuong.equalsIgnoreCase("Mức lương")) {
				String arr[] = mucLuong.split(" - ");
				String min = arr[0].trim().substring(0,2);
				String max = arr[1].trim().substring(0,2);
						
				int i_min = Integer.valueOf(min)*1000000;
				int i_max = Integer.valueOf(max)*1000000;
				sql+=" and (( NHANVIEN.luongThuViec >= "+i_min+" and NHANVIEN.luongThuViec <= "+i_max+" ) or ( HOPDONGLAODONG.luongCoBan >= "+i_min+" and HOPDONGLAODONG.luongCoBan <= "+i_max+" )) ";
				
			}
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				NHANVIEN x = null;
				String str = rs.getString("diaChi");
				String arr[] = str.split(",");
				String arr2[] = arr[0].split(" ");
				String temp ="";
				for(int i=1;i<arr2.length;i++) {
					temp+=arr2[i]+" ";
				}
				temp = temp.trim();
				if(rs.getString("maHopDong")==null) {
					x = new NHANVIENTHUVIEC();
					((NHANVIENTHUVIEC)x).setNgayBatDauThuViec(rs.getDate("ngayBatDauThuViec").toLocalDate());
					((NHANVIENTHUVIEC)x).setNgayKetThucThuViec(rs.getDate("ngayKetThucThuViec").toLocalDate());
					((NHANVIENTHUVIEC)x).setLuongThuViec(rs.getDouble("luongThuViec"));
				}else {
					x = new NHANVIENCHINHTHUC();
					((NHANVIENCHINHTHUC)x).setHOPDONG(new HOPDONGLAODONG(rs.getString("maHopDong"), rs.getDate("tuNgay").toLocalDate(), rs.getDate("denNgay").toLocalDate(), rs.getString("loaiHopDong"), rs.getDouble("luongCoBan")));
				}
				x.setMaNhanVien(rs.getString("maNhanVien"));
				x.setHoTen(rs.getString("hoTen"));
				x.setGioiTinh(rs.getString("gioiTinh"));
				x.setNgaySinh(rs.getDate("ngaySinh").toLocalDate().plusDays(2));
				x.setDiaChi(new DIACHI(arr2[0],temp, arr[1], arr[2], arr[3]));
				x.setDanToc(rs.getString("danToc"));
				x.setTonGiao(rs.getString("tonGiao"));
				x.setEmail(rs.getString("email"));
				x.setSdt(rs.getString("SDT"));
				x.setCmnd(new CMND(rs.getString("soCMND"),rs.getString("noiCap"),rs.getDate("ngayCap").toLocalDate().plusDays(2)));
				
				
				x.setTaiKhoan(new TAIKHOAN(rs.getString("username"), rs.getString("pass"), rs.getString("maNhomQuyen"), rs.getString("avatar")));
				x.setChucVu(new CHUCVU(rs.getString("maChucVu"), rs.getString("tenChucVu"), rs.getDouble("phuCapChucVu"), rs.getDate("ngayNhanChuc").toLocalDate().plusDays(2)));
				x.setMaPhong(rs.getString("maPhong"));
				x.setTrinhDo(new TRINHDO(rs.getString("maTrinhDo"), rs.getString("trinhDoHocVan"), rs.getString("trinhDoChuyenMon"), rs.getString("chuyenNganh")));
				list.add(x);
			}
			con.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
			public void dieuChinhLuong(String maNhanVien,double mucLuong) {
				String sql = " select * from NHANVIEN where maNhanVien = N'"+maNhanVien+"'";
				Connection con = ConnectionManager.getConnection();
				 try {
					 Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while(rs.next()) {
						Statement st1 =con.createStatement();
						if(rs.getString("maHopDong")==null) {
							st1.executeUpdate("update NHANVIEN set luongThuViec = "+mucLuong+" where maNhanVien = N'"+maNhanVien+"'");
						}else {
							st1.executeUpdate("update HOPDONGLAODONG set luongCoBan = "+mucLuong+" where maHopDong = N'"+rs.getString("maHopDong")+"'");
						}
					}
					ConnectionManager.closeConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			// public Object[][] getDanhSachNhanVienThuViecToRender() {
			// 	Connection con = ConnectionManager.getConnection();
			// 	 Object[][] obj =null;
			// 	 try {
			// 		 Statement st = con.createStatement();
			// 		ResultSet rs = st.executeQuery("select count(maNhanVien) from CONNGUOI cn join NHANVIEN nv on cn.CMND = nv.CMND\r\n"
			// 				+ "join PHONGBAN pb on nv.maPhong = pb.maPhong where nv.maHopDong is null and nv.trangThai=1");
			// 		while(rs.next()) {
			// 			obj = new Object[rs.getInt(1)][];
			// 		}
			// 		rs = st.executeQuery("select * from CONNGUOI cn join NHANVIEN nv on cn.CMND = nv.CMND\r\n"
			// 				+ "join PHONGBAN pb on nv.maPhong = pb.maPhong where nv.maHopDong is null and nv.trangThai=1");
			// 		int count = 0;
			// 		while(rs.next()) {
			// 			obj[count] = new Object[] {
			// 					count+1+"", rs.getString("maNhanVien")+" - "+rs.getString("hoTen"), rs.getString("tenPhong"),SUPPORT.LocalDateToString(rs.getDate("ngayBatDauThuViec").toLocalDate())
			// 			};
			// 			count++;
			// 		}
			// 		ConnectionManager.closeConnection(con);
			// 	} catch (SQLException e) {
			// 		e.printStackTrace();
			// 	}
			// 	 return obj;
			// }
			// public Object[][] getDanhSachNhanVienThuViecToRender(String tenPhong, int sort_by, int sort_mode) {
			// 	Connection con = ConnectionManager.getConnection();
			// 	 Object[][] obj =null;
			// 	 try {
			// 		 String sql = "from CONNGUOI cn join NHANVIEN nv on cn.CMND = nv.CMND"
			// 							 + " join PHONGBAN pb on nv.maPhong = pb.maPhong where nv.maHopDong is null and nv.trangThai=1";
			// 		 if(!tenPhong.equals("Phòng ban")) {
			// 			 sql += " and pb.tenPhong = N'"+tenPhong+"' ";
			// 		 }
					 
			// 		 Statement st = con.createStatement();
			// 		ResultSet rs = st.executeQuery("select count(maNhanVien) "+sql);
			// 		while(rs.next()) {
			// 			obj = new Object[rs.getInt(1)][];
			// 		}
			// 		if(sort_by==0) {
			// 			 sql+= " order by nv.maNhanVien ";
			// 			 if(sort_mode==0) {
			// 				 sql+= "asc";
			// 			 }else {
			// 				 sql+= "desc";
			// 			 }
			// 		 }else {
			// 			 sql+= " order by nv.ngayBatDauThuViec ";
			// 			 if(sort_mode==0) {
			// 				 sql+= " asc";
			// 			 }else {
			// 				 sql+= " desc";
			// 			 }
			// 		 }
			// 		rs = st.executeQuery("select * "+sql);
			// 		int count = 0;
			// 		while(rs.next()) {
			// 			obj[count] = new Object[] {
			// 					count+1+"", rs.getString("maNhanVien")+" - "+rs.getString("hoTen"), rs.getString("tenPhong"),SUPPORT.LocalDateToString(rs.getDate("ngayBatDauThuViec").toLocalDate())
			// 			};
			// 			count++;
			// 		}
			// 		ConnectionManager.closeConnection(con);
			// 	} catch (SQLException e) {
			// 		e.printStackTrace();
			// 	}
			// 	 return obj;
			// }
			// public String[] getThongTinNhanVienKiHopDong(String maNhanVien) {
			// 	Connection con = ConnectionManager.getConnection();
			// 	String []data = null;
			// 	try {
			// 		String sql = "select * from CONNGUOI cn join NHANVIEN nv on cn.CMND = nv.CMND\r\n"
			// 				+ "join CMND cmnd on cmnd.soCMND = cn.CMND\r\n"
			// 				+ "join PHONGBAN pb on nv.maPhong = pb.maPhong\r\n"
			// 				+ "join CHUCVU cv on cv.maChucVu = nv.maChucVu\r\n"
			// 				+ "join TRINHDO td on td.maTrinhDo = nv.maTrinhDo where maNhanVien = N'"+maNhanVien+"'";
			// 		Statement st = con.createStatement();
			// 		ResultSet rs = st.executeQuery(sql);
			// 		while(rs.next()) {
			// 			data = new String[] {
			// 				rs.getString("maNhanVien")+" - "+rs.getString("hoTen"),SUPPORT.LocalDateToString(rs.getDate("ngaySinh").toLocalDate()),rs.getString("gioiTinh"),
			// 				rs.getString("diaChi"),rs.getString("SDT"),rs.getString("email"),rs.getString("soCMND")+" - "+SUPPORT.LocalDateToString(rs.getDate("ngayCap").toLocalDate())+" - "+rs.getString("noiCap"),
			// 				rs.getString("trinhDoHocVan"),rs.getString("trinhDoChuyenMon"),rs.getString("chuyenNganh"),
			// 				rs.getString("tenPhong"),rs.getString("tenChucVu"),SUPPORT.changeSalaryToFormatString(rs.getDouble("luongThuViec")*1.25)
			// 			};
			// 		}
			// 		con.close();
			// 	} catch (SQLException e) {
			// 		e.printStackTrace();
			// 	}
			// 	return data;
			// }
			public void updateMaHopDong(String maNhanVien, String maHopDong) {
				Connection con = ConnectionManager.getConnection();
				String []data = null;
				try {
					String sql = "update NHANVIEN set maHopDong = N'"+maHopDong+"' where maNhanVien = N'"+maNhanVien+"'";
					Statement st = con.createStatement();
					st.executeUpdate(sql);
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			public void hiddenNhanVien(String maNhanVien) {
				Connection con = ConnectionManager.getConnection();
				try {
					String sql = "update NHANVIEN set trangThai=0 where maNhanVien = ?";
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, maNhanVien);
					st.executeUpdate();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			public String getTen(String maNhanVien) {
				Connection con = ConnectionManager.getConnection();
		
				try {
					String ten = "";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from CONNGUOI join NHANVIEN on CONNGUOI.CMND = NHANVIEN.CMND where NHANVIEN.maNhanVien = '" + maNhanVien + "' ");
		
					while (rs.next()) {
						ten = rs.getString("maNhanVien") + " - " + rs.getString("hoTen");
						
					}
		
					ConnectionManager.closeConnection(con);
					return ten;
				} catch (SQLException e) {
				}
				return null;
			}
			public double[] getLuongNhanVien(String maNhanVien) {
				Connection con = ConnectionManager.getConnection();
				String sql = "select * from NHANVIEN nv left join HOPDONGLAODONG hd on nv.maHopDong = hd.maHopDong join CHUCVU cv on nv.maChucVu = cv.maChucVu where nv.maNhanVien=?";
				
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, maNhanVien);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						if(rs.getString("maHopDong")==null) {
							return new double[] {rs.getDouble("luongThuViec"), rs.getDouble("phuCapChucVu")};
						}
						return new double[] {rs.getDouble("luongCoBan"), rs.getDouble("phuCapChucVu")};
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				return null;
			}


			public ArrayList<NHANVIEN> renderChangeValue(String[] default_val,String[] event_name) {
				Connection con = ConnectionManager.getConnection();
				ArrayList<NHANVIEN> list = new ArrayList<>();
				try {
					String sql = "select * from CONNGUOI join NHANVIEN on CONNGUOI.CMND = NHANVIEN.CMND join CMND on CMND.soCMND = CONNGUOI.CMND join TRINHDO on NHANVIEN.maTrinhDo = TRINHDO.maTrinhDo join CHUCVU on NHANVIEN.maChucVu = CHUCVU.maChucVu join TAIKHOAN on TAIKHOAN.username = NHANVIEN.maNhanVien left join HOPDONGLAODONG on HOPDONGLAODONG.maHopDong = NHANVIEN.maHopDong join PHONGBAN on NHANVIEN.maPhong = PHONGBAN.maPhong where NHANVIEN.trangThai=1";
					if (!default_val[0].equals(event_name[0])) {
						sql += "and tenPhong = N'"+event_name[0]+"' ";
					}
					if (!default_val[1].equals(event_name[1])) {
						sql += "and gioiTinh = N'"+event_name[1]+"' ";
					}
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next() ) {
						NHANVIEN nv = null;
						String str = rs.getString(5);
						String arr[] = str.split(",");
						String arr2[] = arr[0].split(" ");
						String temp ="";
						for(int i=1;i<arr2.length;i++) {
							temp+=arr2[i]+" ";
						}
						temp = temp.trim();
						
						if (rs.getString(15)==null) { // Dòng 15 truy vấn là ngày bắt đầu thử việc
							nv = new NHANVIENTHUVIEC();
							((NHANVIENTHUVIEC)nv).setNgayBatDauThuViec(rs.getDate("ngayBatDauThuViec").toLocalDate().plusDays(2));
							((NHANVIENTHUVIEC)nv).setNgayKetThucThuViec(rs.getDate("ngayKetThucThuViec").toLocalDate().plusDays(2));
							((NHANVIENTHUVIEC)nv).setLuongThuViec(rs.getDouble("luongThuViec"));
						}
						else {
							nv = new NHANVIENCHINHTHUC();
							((NHANVIENCHINHTHUC)nv).setHOPDONG(new HOPDONGLAODONG(rs.getString("maHopDong"), rs.getDate("tuNgay").toLocalDate().plusDays(2), rs.getDate("denNgay").toLocalDate().plusDays(2), rs.getString("loaiHopDong"), rs.getDouble("luongCoBan")));
						}
						
						nv.setMaNhanVien(rs.getString("maNhanVien"));
						nv.setHoTen(rs.getString("hoTen"));
						nv.setGioiTinh(rs.getString("gioiTinh"));
						nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate().plusDays(2));
						nv.setDiaChi(new DIACHI(arr2[0],temp, arr[1], arr[2], arr[3]));
						nv.setDanToc(rs.getString("danToc"));
						nv.setTonGiao(rs.getString("tonGiao"));
						nv.setEmail(rs.getString("email"));
						nv.setSdt(rs.getString("SDT"));
						nv.setCmnd(new CMND(rs.getString("soCMND"),rs.getString("noiCap"),rs.getDate("ngayCap").toLocalDate().plusDays(2)));
						
						nv.setTaiKhoan(new TAIKHOAN(rs.getString("username"), rs.getString("pass"), rs.getString("maNhomQuyen"), rs.getString("avatar")));
						nv.setChucVu(new CHUCVU(rs.getString("maChucVu"), rs.getString("tenChucVu"), rs.getDouble("phuCapChucVu"), rs.getDate("ngayNhanChuc").toLocalDate().plusDays(2)));
						nv.setMaPhong(rs.getString("maPhong"));
						nv.setTrinhDo(new TRINHDO(rs.getString("maTrinhDo"), rs.getString("trinhDoHocVan"), rs.getString("trinhDoChuyenMon"), rs.getString("chuyenNganh")));
						list.add(nv);
						
						
						
					}
				} catch(SQLException e) {
					e.printStackTrace();
				} finally {
					ConnectionManager.closeConnection(con);
				}

				return list;
			}

}
