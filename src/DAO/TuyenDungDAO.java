package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.BAOCAOTUYENDUNG;
import connectionSQL.ConnectionManager;

public class TuyenDungDAO implements DAOInterface<BAOCAOTUYENDUNG> {

    public static TuyenDungDAO getInstance() {
        return new TuyenDungDAO();
    }

    @Override
    public ArrayList<BAOCAOTUYENDUNG> getList(){
		ArrayList<BAOCAOTUYENDUNG> list= new ArrayList<>();
		// Tạo kết nối
 		Connection con = ConnectionManager.getConnection();
 		Statement st;
 		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from BAOCAOTUYENDUNG order by maTuyenDung");
			while(rs.next()) {
				BAOCAOTUYENDUNG x = new BAOCAOTUYENDUNG();
				x.setMaTuyenDung(rs.getString("maTuyenDung"));
				x.setChucVu(rs.getString("chucVu"));
				x.setHocVan(rs.getString("hocVan"));
				x.setGioiTinh(rs.getString("yeuCauGioiTinh"));
				x.setDoTuoi(rs.getString("yeuCauDoTuoi"));;
				x.setSoLuongCanTuyen(Integer.parseInt(rs.getString(6)));
				x.setHanNopHoSo(rs.getDate("hanNopHoSo").toLocalDate().plusDays(2));
				x.setMucLuongToiThieu(rs.getDouble("mucLuongToiThieu"));
				x.setMucLuongToiDa(rs.getDouble("mucLuongToiDa"));
				Statement st1 = con.createStatement();
				ResultSet rs1 = st1.executeQuery("select COUNT(CMND) from UNGVIEN where maTuyenDung = '"+x.getMaTuyenDung()+"'");
				
				while(rs1.next()) {
					x.setSoLuongNopHoSo(Integer.parseInt(rs1.getString(1)));
				}
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery("select count(maUngVien) from UNGVIEN uv join NHANVIEN nv on nv.CMND = uv.CMND where uv.maTuyenDung = '"+x.getMaTuyenDung()+"'");
				
				while(rs2.next()) {
					x.setSoLuongDaTuyen(Integer.parseInt(rs2.getString(1)));
				}
				list.add(x);
			}
			ConnectionManager.closeConnection(con);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    public int update(BAOCAOTUYENDUNG x) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(
                "UPDATE BAOCAOTUYENDUNG SET " +
                "chucVu = ?, hocVan = ?, yeuCauGioiTinh = ?, yeuCauDoTuoi = ?, " +
                "soLuongCanTuyen = ?, hanNopHoSo = ?, mucLuongToiThieu = ?, mucLuongToiDa = ? " +
                "WHERE maTuyenDung = ?");
            pst.setString(1, x.getChucVu());
            pst.setString(2, x.getHocVan());
            pst.setString(3, x.getGioiTinh());
            pst.setString(4, x.getDoTuoi());
            pst.setInt(5, x.getSoLuongCanTuyen());
            pst.setDate(6, Date.valueOf(x.getHanNopHoSo()));
            pst.setDouble(7, x.getMucLuongToiThieu());
            pst.setDouble(8, x.getMucLuongToiDa());
            pst.setString(9, x.getMaTuyenDung());
    
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pst, con);
        }
    }
    @SuppressWarnings("unused")
	private  int getSoLuongNopHoSo(Connection con, String maTuyenDung) throws SQLException {
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(CMND) AS Total FROM UNGVIEN WHERE maTuyenDung = ?");
        pst.setString(1, maTuyenDung);
        ResultSet rs = pst.executeQuery();
        int total = 0;
        if (rs.next()) {
            total = rs.getInt("Total");
        }
        rs.close();
        pst.close();
        return total;
    }

    @SuppressWarnings("unused")
	private int getSoLuongDaTuyen(Connection con, String maTuyenDung) throws SQLException {
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(maUngVien) AS Total FROM UNGVIEN UV JOIN NHANVIEN NV ON NV.CMND = UV.CMND WHERE UV.maTuyenDung = ?");
        pst.setString(1, maTuyenDung);
        ResultSet rs = pst.executeQuery();
        int total = 0;
        if (rs.next()) {
            total = rs.getInt("Total");
        }
        rs.close();
        pst.close();
        return total;
    }

    public String getChucVuTuyenDung(String maTuyenDung) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("SELECT chucVu FROM BAOCAOTUYENDUNG WHERE maTuyenDung = ?");
            pst.setString(1, maTuyenDung);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("chucVu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pst, con);
        }
        return null;
    }

    public int insert(BAOCAOTUYENDUNG x) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("INSERT INTO BAOCAOTUYENDUNG (maTuyenDung, chucVu, hocVan, yeuCauGioiTinh, yeuCauDoTuoi, soLuongCanTuyen, hanNopHoSo, mucLuongToiThieu, mucLuongToiDa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, x.getMaTuyenDung());
            pst.setString(2, x.getChucVu());
            pst.setString(3, x.getHocVan());
            pst.setString(4, x.getGioiTinh());
            pst.setString(5, x.getDoTuoi());
            pst.setInt(6, x.getSoLuongCanTuyen());
            pst.setDate(7, Date.valueOf(x.getHanNopHoSo()));
            pst.setDouble(8, x.getMucLuongToiThieu());
            pst.setDouble(9, x.getMucLuongToiDa());
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pst, con);
        }
    }

    public  int del(String maTuyenDung) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("DELETE FROM BAOCAOTUYENDUNG WHERE maTuyenDung = ?");
            pst.setString(1, maTuyenDung);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pst, con);
        }
    }

    private void closeResources(ResultSet rs, PreparedStatement pst, Connection con) {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            ConnectionManager.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String[] getMaTuyenDung() {
		String data[] = null;
		Connection con = ConnectionManager.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs  = st.executeQuery("select COUNT(DISTINCT maTuyenDung) from BAOCAOTUYENDUNG");
			while(rs.next()) {
				data = new String[rs.getInt(1)];
			}
			rs = st.executeQuery("select * from BAOCAOTUYENDUNG order by maTuyenDung");
			int count =0;
			while(rs.next()) {
				data[count] = rs.getString(1);
				count++;
			}
			ConnectionManager.closeConnection(con);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

    public BAOCAOTUYENDUNG getBaoCaoTuyenDung(String maBaoCao) { 
        ArrayList<BAOCAOTUYENDUNG> list = this.getList();
        for(BAOCAOTUYENDUNG i : list) {
			if(i.getMaTuyenDung().equals(maBaoCao)) {
				return i;
			}
		}
		return null;

    }
}
