package DAO;

import java.lang.classfile.instruction.NewMultiArrayInstruction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Flow.Publisher;

import org.jdesktop.animation.timing.triggers.FocusTrigger;

import DTO.DANHGIA;
import DTO.NHANVIEN;
import connectionSQL.ConnectionManager;

public class DanhGiaDAO implements DAOInterface<DANHGIA>{

	@Override
	public ArrayList<DANHGIA> getList() {
		ArrayList<DANHGIA> list = new ArrayList<>();
		Connection con= ConnectionManager.getConnection();
		try {
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery("SELECT * FROM BANGDANHGIA");
			
			while(rs.next()) {
				DANHGIA temp= new DANHGIA();
				temp.setMaDanhGia(rs.getString("maDanhGia"));
				temp.setMaNhanVien(rs.getString("maNhanVien"));
				temp.setNguoiDanhGia(rs.getString("nguoiDanhGia"));
				java.sql.Date sqlDate = rs.getDate("ngayDanhGia");
				LocalDate ngayDanhGia = sqlDate.toLocalDate();
				temp.setNgayDanhGia(ngayDanhGia);

				temp.setDiemDanhGia(rs.getFloat("diemDanhGia"));
				temp.setXepLoaiDanhGia(rs.getString("xepLoaiDanhGia"));
				temp.setChiTietDanhGia(rs.getString("chiTietDanhGia"));
				temp.setLoaiDanhGia(rs.getString("loaiDanhGia"));
				temp.setGhiChu(rs.getString("ghiChu"));
				
				list.add(temp);
			}
			
			ConnectionManager.closeConnection(con);
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(DANHGIA bangDanhGia) {
		int rowsAffected =0;
		Connection con= ConnectionManager.getConnection();
		try {
			PreparedStatement pst= con.prepareStatement("INSERT INTO BANGDANHGIA VALUES (? ,? ,? ,? ,? ,? ,? ,? ,?)");
			pst.setString(1, bangDanhGia.getMaDanhGia());
			pst.setString(2, bangDanhGia.getMaNhanVien());
			pst.setString(3, bangDanhGia.getNguoiDanhGia());
			LocalDate ngayDanhGia = bangDanhGia.getNgayDanhGia();
			java.sql.Date sqlDate = java.sql.Date.valueOf(ngayDanhGia);
			pst.setDate(4, sqlDate);
			pst.setFloat(5, bangDanhGia.getDiemDanhGia());
			pst.setString(6, bangDanhGia.getXepLoaiDanhGia());
			pst.setString(7, bangDanhGia.getChiTietDanhGia());
			pst.setString(8, bangDanhGia.getLoaiDanhGia());
			pst.setString(9, bangDanhGia.getGhiChu());
			rowsAffected= pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}
		return rowsAffected;
	}

	@Override
	public int update(DANHGIA bangDanhGia) {
		int rowsAffected=0;
		Connection con= ConnectionManager.getConnection();
		try {
			PreparedStatement pst=con.prepareStatement("UPDATE BANGDANHGIA SET maDanhGia = ?, maNhanVien= ?, nguoiDanhGia= ?, ngayDanhGia= ?, diemDanhGia= ?, xepLoaiDanhGia= ?, chiTietDanhGia= ?, loaiDanhGia= ?, ghiChu= ?");
			pst.setString(1, bangDanhGia.getMaDanhGia());
			pst.setString(2, bangDanhGia.getMaNhanVien());
			pst.setString(3, bangDanhGia.getNguoiDanhGia());
			LocalDate ngayDanhGia = bangDanhGia.getNgayDanhGia();
			java.sql.Date sqlDate = java.sql.Date.valueOf(ngayDanhGia);
			pst.setDate(4, sqlDate);
			pst.setFloat(5, bangDanhGia.getDiemDanhGia());
			pst.setString(6, bangDanhGia.getXepLoaiDanhGia());
			pst.setString(7, bangDanhGia.getChiTietDanhGia());
			pst.setString(8, bangDanhGia.getLoaiDanhGia());
			pst.setString(9, bangDanhGia.getGhiChu());
			rowsAffected=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}
		return rowsAffected;
	}

	@Override
	public int del(String maDanhGia) {
		int rowsAffected =0;
		Connection con=ConnectionManager.getConnection();
		try {
			PreparedStatement pst=con.prepareStatement("DELETE FROM BANGDANHGIA WHERE maDanhGia= ?");
			pst.setString(1, maDanhGia);
			rowsAffected=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			ConnectionManager.closeConnection(con);
		}
		return rowsAffected;
	}
	
	//Lấy báng đánh giá từ mã
	public DANHGIA getBangDanhGiaByMa(String maBDG) {
		Connection con=ConnectionManager.getConnection();
		try {
			Statement ps= con.createStatement();
			ResultSet rs=ps.executeQuery("SELECT * FROM BANGDANHGIA WHERE maDanhGia= N' " + maBDG +"'");
			while (rs.next()) {
				DANHGIA x =new DANHGIA();
				
				x.setMaDanhGia(rs.getString("maDanhGia"));
				x.setMaNhanVien(rs.getString("maNhanVien"));
				java.sql.Date sqlDate = rs.getDate("ngayDanhGia");
				LocalDate ngayDanhGia = sqlDate.toLocalDate();
				x.setNgayDanhGia(ngayDanhGia);
				x.setNguoiDanhGia(rs.getString("nguoiDanhGia"));
				x.setDiemDanhGia(rs.getFloat("diemDanhGia"));
				x.setXepLoaiDanhGia(rs.getString("xepLoaiDanhGia"));
				x.setChiTietDanhGia(rs.getString("chiTietDanhGia"));
				x.setLoaiDanhGia(rs.getString("loaiDanhGia"));
				x.setGhiChu(rs.getString("ghiChu"));
				ConnectionManager.closeConnection(con);
				return x;
			}
		} catch (SQLException e) {
			
		}
		return null;
	} 
	
	//Lấy tên từ mã bảng đánh giá
	public String getTenNhanVien(String maDanhGia) {
		Connection con=ConnectionManager.getConnection();
		try {
			String ten="";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from BANGDANHGIA BDG "
					+ "join NHANVIEN NV on NV.maNhanVien=BDG.maNhanVien "
					+ "join CONNGUOI CN on CN.CMND=NV.CMND "
					+ "where BDG.maDanhGia='"+maDanhGia+"'");
			while (rs.next()) {
				ten=rs.getString("maNhanVien") + " - " +rs.getString("hoTen");
			}
			ConnectionManager.closeConnection(con);
			return ten;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Lấy tên người đánh giá từ mã bảng đánh giá
	public String getNguoiDanhGia(String maDanhGia) {
		Connection con=ConnectionManager.getConnection();
		try {
			String ten="";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from BANGDANHGIA BDG "
					+ "join NHANVIEN NV on NV.maNhanVien=BDG.nguoiDanhGia "
					+ "join CONNGUOI CN on CN.CMND=NV.CMND "
					+ "where BDG.maDanhGia='"+maDanhGia+"'");
			while (rs.next()) {
				ten=rs.getString("nguoiDanhGia") + " - " +rs.getString("hoTen");
			}
			ConnectionManager.closeConnection(con);
			return ten;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Lấy ds nhân viên ch được đánh giá
	public ArrayList<DANHGIA> getListNVChuaDG(){
		ArrayList<DANHGIA> list=new ArrayList<>();
		Connection con=ConnectionManager.getConnection();
		try {
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * "
				    + "FROM NHANVIEN NV "
				    + "LEFT JOIN BANGDANHGIA BDG ON NV.maNhanVien = BDG.maNhanVien "
				    + "LEFT JOIN CONNGUOI CN ON NV.CMND = CN.CMND "
				    + "WHERE BDG.maNhanVien IS NULL;");

					
			while(rs.next()) {
				DANHGIA temp= new DANHGIA();				
				temp.setMaNhanVien(rs.getString("maNhanVien"));							
				list.add(temp);
			}
			
			ConnectionManager.closeConnection(con);
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	//lấy tên nhân viên từ mã nhân viên
	public String getTenNVChuaDG(String maNhanVien) {
		Connection con=ConnectionManager.getConnection();
		try {
			String ten="";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from NHANVIEN NV "					
					+ "join CONNGUOI CN on CN.CMND=NV.CMND "
					+ "where NV.maNhanVien='"+maNhanVien+"'");
			while (rs.next()) {
				ten=rs.getString("maNhanVien") + " - " +rs.getString("hoTen");
			}
			ConnectionManager.closeConnection(con);
			return ten;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<DANHGIA> renderChangeValue(String[] default_val, String[] event_name) {
	    Connection con = ConnectionManager.getConnection();
	    ArrayList<DANHGIA> list = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM BANGDANHGIA BDG where 1=1 ";
	        if (!default_val[0].equals(event_name[0])) {
	            sql+="and BDG.xepLoaiDanhGia= N'"+event_name[0]+"'";
	            
	        }
	        if (!default_val[1].equals(event_name[1])) {           
	                sql += " AND BDG.loaiDanhGia = N'" + event_name[1] + "'";            
	        }
	        if (!default_val[2].equals(event_name[2])) {
	        	if(default_val[2].equals("Điểm đánh giá")) {
	        		sql += " ORDER BY BDG.diemDanhGia ";
		            if (default_val[3].equals("Tăng dần"))
		            	sql+=" ASC ";
		            else
		            	sql+=" DESC";
	        	}
	        	else {
	        		sql += " ORDER BY BDG.ngayDanhGia ";
		            if (default_val[3].equals("Tăng dần"))
		            	sql+=" ASC ";
		            else 
		            	sql+=" DESC";
				}	                	          
	        }
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            DANHGIA dg = new DANHGIA(); // Khởi tạo một đối tượng DANHGIA mới
	            dg.setMaDanhGia(rs.getString("maDanhGia"));
	            dg.setMaNhanVien(rs.getString("maNhanVien"));
	            java.sql.Date sqlDate = rs.getDate("ngayDanhGia");
	            LocalDate ngayDanhGia = sqlDate.toLocalDate();
	            dg.setNgayDanhGia(ngayDanhGia);
	            dg.setNguoiDanhGia(rs.getString("nguoiDanhGia"));
	            dg.setDiemDanhGia(rs.getFloat("diemDanhGia"));
	            dg.setXepLoaiDanhGia(rs.getString("xepLoaiDanhGia"));
	            dg.setChiTietDanhGia(rs.getString("chiTietDanhGia"));
	            dg.setLoaiDanhGia(rs.getString("loaiDanhGia"));
	            list.add(dg);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionManager.closeConnection(con);
	    }
	    return list;
	}


	
}
