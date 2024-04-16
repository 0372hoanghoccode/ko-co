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
//import java.sql.Statement;



public class NhanVienDAO implements DAOInterface<NHANVIEN>{
	
	 
	
	
	



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
				
				if (rs.getString(16)==null) { // Dòng 16 truy vấn là ngày bắt đầu thử việc
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
//				nv.setTinhTrangHonNhan(rs.getString("tinhTrangHonNhan"));
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
	public void insert(NHANVIEN x) {
		// TODO Auto-generated method stub
		Connection con = ConnectionManager.getConnection();
		try {
			// tạo cmnd -> đang soi
			CMND_DAO.getInstance().insert(x.getCmnd());
			// con người -> đang soi
			ConNguoiDAO.insertCONNGUOI((CONNGUOI)x);
			// trinh do -> đang soi
			TrinhDoDAO.getInstance().insert(x.getTrinhDo());
			// chuc vu -> đang soi
			ChucVuCongTyDAO.getInstance().insert(x.getChucVu());
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
				pst.executeUpdate();
			}


			// tài khoản -> đang soi 
			TaiKhoanDAO.getInstance().insert(x.getTaiKhoan());
			
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(NHANVIEN t) {
		// TODO Auto-generated method stub
		Connection con = ConnectionManager.getConnection();
		try {

			// SOI đoạn này lại
			CMND_DAO.getInstance().update(t.getCmnd());
			ConNguoiDAO.updateCONNGUOI((CONNGUOI)t);
			TrinhDoDAO.getInstance().update(t.getTrinhDo());
			ChucVuCongTyDAO.getInstance().update(t.getChucVu());

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
				pst.executeUpdate();
			
			}
			TaiKhoanDAO.getInstance().update(t.getTaiKhoan());
			ConnectionManager.closeConnection(con);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void del(String ma) { // --- vẫn chưa hiểu được yêu cầu nha (MẤY NÍ NHỚ COI DÙM) --> kiểu hàm này là dc rồi hay là cho nó bay màu khỏi csdl luôn
		// TODO Auto-generated method stub
		Connection con = ConnectionManager.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("update NHANVIEN set trangThai = 0 where maNhanVien = ?");
			pst.setString(1, ma);
			pst.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// ----------------- OTHER FUNCTION -----------------





}
