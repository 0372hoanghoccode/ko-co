package DAO;

import DTO.NHOMQUYEN;

import connectionSQL.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class NhomQuyenDAO implements DAOInterface<NHOMQUYEN>{

    public static NhomQuyenDAO getInstance() {
        return new NhomQuyenDAO();
	
}

    // @Override
    // public ArrayList<NHOMQUYEN> getList() {
    //     ArrayList<NHOMQUYEN> list = new ArrayList<>();
    //     try (Connection con = ConnectionManager.getConnection();
    //         Statement st = con.createStatement();
    //         ResultSet rs = st.executeQuery("SELECT * FROM NHOMQUYEN")) {
    //         while (rs.next()) {
    //             NHOMQUYEN nhomquyen = new NHOMQUYEN(rs.getString("maNhomQuyen"), rs.getString("tenNhomQuyen"));
    //             list.add(nhomquyen);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return list;
    // }

    @Override
    public ArrayList<NHOMQUYEN> getList() {
        ArrayList<NHOMQUYEN> list = new ArrayList<>();
		Connection con = ConnectionManager.getConnection();
		try {
			
			PreparedStatement pst = con.prepareStatement("select * from NHOMQUYEN");
			PreparedStatement pst2;
			ResultSet rs2;
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				NHOMQUYEN x = new NHOMQUYEN(rs.getString("maNhomQuyen"),rs.getString("tenNhomQuyen"),null);
				boolean mangChucNang[] = new boolean[38];
				for(int i=0;i<38;i++) {
					mangChucNang[i] = false;
				}
				pst2 = con.prepareStatement("select * from NHOMQUYEN nq join CHITIETNHOMQUYEN ct on nq.maNhomQuyen = ct.maNhomQuyen where nq.maNhomQuyen = ?");
				pst2.setString(1, rs.getString("maNhomQuyen"));
				rs2 = pst2.executeQuery();
				while(rs2.next()) {
					int index = Integer.valueOf(rs2.getString("maChucNang"));
					mangChucNang[index-1] = true;
				}
				x.setMangChucNang(mangChucNang);
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
    public int insert(NHOMQUYEN t) {
        int result = 0;
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement pst = con.prepareStatement("INSERT INTO NHOMQUYEN (maNhomQuyen, tenNhomQuyen) VALUES (?, ?)")) {
            
            pst.setString(1, t.getMaNhomQuyen());
            pst.setString(2, t.getTenNhomQuyen());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(NHOMQUYEN t) {
        int result = 0;
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement pst = con.prepareStatement("UPDATE NHOMQUYEN SET tenNhomQuyen = ? WHERE maNhomQuyen = ?")) {
            
            pst.setString(1, t.getTenNhomQuyen());
            pst.setString(2, t.getMaNhomQuyen());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int del(String ma) {
        int result = 0;
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM NHOMQUYEN WHERE maNhomQuyen = ?")) {
            
            pst.setString(1, ma);

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    // NON IMPLEMENT METHOD 
    public boolean[] getChucNangTaiKhoan(String username) {
		boolean[] mangChucNang = new boolean[38];
		for(int i=0;i<38;i++) {
			mangChucNang[i] = false;
		}
		Connection con = ConnectionManager.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("select * from TAIKHOAN tk \r\n"
					+ "join CHITIETNHOMQUYEN ct on tk.maNhomQuyen = ct.maNhomQuyen where tk.username = ?");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int index = Integer.valueOf(rs.getString("maChucNang"));
				mangChucNang[index-1] = true;
			}
			ConnectionManager.closeConnection(con);
			return mangChucNang;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

    public boolean insertNHOMQUYEN(String maNhomQuyen, String tenNhomQuyen) {
		Connection con = ConnectionManager.getConnection();
		try {
			
			PreparedStatement pst = con.prepareStatement("insert NHOMQUYEN values(?,?)");
			pst.setString(1, maNhomQuyen);
			pst.setString(2, tenNhomQuyen);
			int flag = pst.executeUpdate();
			ConnectionManager.closeConnection(con);
			if(flag==0) {
				return false;
			}else {
				return true;
			}
			
		} catch (SQLException e) {
			return false;
		}
	}
}
