package DAO;

import DTO.NHOMQUYEN;

import connectionSQL.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class NhomQuyenDAO implements DAOInterface<NHOMQUYEN>{

    public static NhomQuyenDAO getInstance() {
        return new NhomQuyenDAO();
	
}

    @Override
    public ArrayList<NHOMQUYEN> getList() {
        ArrayList<NHOMQUYEN> list = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM NHOMQUYEN")) {
            while (rs.next()) {
                NHOMQUYEN nhomquyen = new NHOMQUYEN(rs.getString("maNhomQuyen"), rs.getString("tenNhomQuyen"));
                list.add(nhomquyen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insert(NHOMQUYEN t) {
        int result = 0;
        try (Connection con = ConnectionManager.getConnection();
            PreparedStatement pst = con.prepareStatement("INSERT INTO NHOMQUYEN (maNhomQuyen, tenNhomQuyen) VALUES (?, ?)")) {
            
            pst.setString(1, t.getManhomquyen());
            pst.setString(2, t.getTennhomquyen());

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
            
            pst.setString(1, t.getTennhomquyen());
            pst.setString(2, t.getManhomquyen());

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
}
