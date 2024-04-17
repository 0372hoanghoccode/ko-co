package DAO;

import connectionSQL.ConnectionManager;
import java.sql.Connection;
// import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement;
import java.util.ArrayList;

// --- IMPORT DTO ----
import DTO.CHUCVU;


public class ChucVuDAO implements DAOInterface<CHUCVU>{

    public static ChucVuDAO getInstance() {
        return new ChucVuDAO();
    }

    @Override
    public ArrayList<CHUCVU> getList() {
        ArrayList<CHUCVU> list = new ArrayList<>();
		Connection con = ConnectionManager.getConnection();
        try {
            String sql = "select * from CHUCVU";
            PreparedStatement st = con.prepareStatement(null);
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                CHUCVU chucvu = new CHUCVU();
                chucvu.setMaChucVu(rs.getString(1));
                chucvu.setTenChucVu(rs.getString(2));
                chucvu.setPhuCapChucVu(rs.getDouble(3));
                chucvu.setNgayNhanChuc(rs.getDate(4).toLocalDate());
                list.add(chucvu);
            }
            ConnectionManager.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insert(CHUCVU t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(CHUCVU t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int del(String ma) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'del'");
    }

}
