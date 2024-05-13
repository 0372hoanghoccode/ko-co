package DAO;

import DTO.UNGVIEN;
import connectionSQL.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.BAOCAOTUYENDUNG;
import DTO.CONNGUOI;
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
                // chuc vu, tai khoan
                
                pst = con.prepareStatement("select * from UNGVIEN join CONNGUOI on UNGVIEN.CMND=CONNGUOI.CMND "
                		+ "join CMND on UNGVIEN.CMND= CMND.soCMND join TRINHDO on TRINHDO.maTrinhDo=UNGVIEN.maTrinhDo");
                rs = pst.executeQuery();
                while(rs.next()) {
                    UNGVIEN x = new UNGVIEN();
                    x.setMaTuyenDung(rs.getString("maTuyenDung"));
                    x.setMaUngVien(rs.getString("maUngVien"));
                    x.setHoTen(rs.getString("hoTen"));
                    x.setNgaySinh(rs.getDate("ngaySinh").toLocalDate().plusDays(2));
                    x.setGioiTinh(rs.getString("gioiTinh"));
                    x.setSdt(rs.getString("SDT"));
                    
                    String str = rs.getString("diaChi");
                    String arr[] = str.split(",");
                    String arr2[] = arr[0].split(" ");
                    String temp ="";
                    for(int i=1;i<arr2.length;i++) {
                        temp+=arr2[i]+" ";
                    }
                    temp = temp.trim();
                    x.setDanToc(rs.getString("danToc"));
                    x.getCmnd().setSoCmnd(rs.getString("CMND"));
                    x.getCmnd().setNoiCap(rs.getString("noiCap"));
                    x.getCmnd().setNgayCap(rs.getDate("ngayCap").toLocalDate().plusDays(2));
                    x.setDiaChi(new DIACHI(arr2[0],temp, arr[1], arr[2], arr[3]));
                    x.setEmail(rs.getString("email"));
                    x.setTrangThai(rs.getString("trangThai"));
                    x.setTonGiao(rs.getString("tonGiao"));
                    x.getTrinhDo().setMaTrinhDo(rs.getString("maTrinhDo"));
                    x.getTrinhDo().setTrinhDoHocVan(rs.getString("trinhDoHocVan"));
                    x.getTrinhDo().setTrinhDoChuyenMon(rs.getString("trinhdoChuyenMon"));
                    x.getTrinhDo().setChuyenNganh(rs.getString("chuyenNganh"));
                    x.setChucVu(rs.getString("tenChucVu"));
                    x.setMucLuongDeal(Double.parseDouble(rs.getString("mucLuongDeal")));
                    list.add(x);
                }
                ConnectionManager.closeConnection(con);
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    public int insert(UNGVIEN uv) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {

            TrinhDoDAO.getInstance().insert(uv.getTrinhDo());
            CMND_DAO.getInstance().insert(uv.getCmnd());
            ConNguoiDAO.getInstance().insert((CONNGUOI)uv);

            pst = con.prepareStatement("insert UNGVIEN values(?,?,?,?,?,?,?)");
			pst.setString(1, uv.getMaTuyenDung());
			pst.setString(2, uv.getMaUngVien());
			pst.setString(3, uv.getCmnd().getSoCmnd());
			pst.setDouble(4, uv.getMucLuongDeal());
			pst.setString(5, uv.getTrinhDo().getMaTrinhDo());
			pst.setString(6, uv.getChucVu());
			pst.setString(7, uv.getTrangThai());
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
    
    
    public void updateTrangThai(String maUngVien,String trangThai) {
		Connection con = ConnectionManager.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("update UNGVIEN set trangThai=?  where maUngVien = ?");
			pst.setString(1, trangThai);
			pst.setString(2, maUngVien);
			pst.executeUpdate();
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


    public ArrayList<String> getDanhSachMaSo() {
		Connection con = ConnectionManager.getConnection();
		try {
			ArrayList<String> list =new ArrayList<>();
			PreparedStatement pst = con.prepareStatement("select * from UNGVIEN");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				  list.add(rs.getString("maUngVien")); 
			}
			ConnectionManager.closeConnection(con);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


    public void deleteUngVien(String maTrinhDo, String soCMND, String maUngVien) {
		Connection con = ConnectionManager.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("delete UNGVIEN where maUngVien = ?");
			pst.setString(1, maUngVien);
			pst.executeUpdate();
			
			pst = con.prepareStatement("delete CONNGUOI where CMND = ?");
			pst.setString(1, soCMND);
			pst.executeUpdate();
			
			pst = con.prepareStatement("delete CMND where soCMND = ?");
			pst.setString(1, soCMND);
			pst.executeUpdate();
			
			pst = con.prepareStatement("delete TRINHDO where maTrinhDo = ?");
			pst.setString(1, maTrinhDo);
			pst.executeUpdate();
			
			ConnectionManager.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    
    
    
}
