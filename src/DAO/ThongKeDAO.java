package DAO;

import java.util.ArrayList;

import connectionSQL.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import DTO.THONGKENAMNUTUNGNAM;

public class ThongKeDAO {
    public static ThongKeDAO getInstance() {
        return new ThongKeDAO();
    }

    public THONGKENAMNUTUNGNAM getNamNuTheoNam(int year) {
       THONGKENAMNUTUNGNAM tk = new THONGKENAMNUTUNGNAM();

        try{
            Connection con = ConnectionManager.getConnection();
            Statement statement = con.createStatement();
            
            
                
            String sql = " select (select count(gioiTinh) \r\n" + //
                                    "from CONNGUOI \r\n" + //
                                    "join NHANVIEN nv on CONNGUOI.CMND = nv.CMND \r\n" + //
                                    "join CMND on CMND.soCMND = CONNGUOI.CMND \r\n" + //
                                    "join TRINHDO on nv.maTrinhDo = TRINHDO.maTrinhDo \r\n" + //
                                    "join CHUCVU on nv.maChucVu = CHUCVU.maChucVu \r\n" + //
                                    "join TAIKHOAN on TAIKHOAN.username = nv.maNhanVien \r\n" + //
                                    "left join HOPDONGLAODONG on HOPDONGLAODONG.maHopDong = nv.maHopDong \r\n" + //
                                    "where nv.maHopDong is null and year(nv.ngayBatDauThuViec) <= " + year + "and year(nv.ngayKetThucThuViec) >= " + year + "and CONNGUOI.gioiTinh = N'Nữ')\r\n" + //
                                    "+\r\n" + //
                                    "(select count(gioiTinh) \r\n" + //
                                    "from CONNGUOI \r\n" + //
                                    "join NHANVIEN nv on CONNGUOI.CMND = nv.CMND \r\n" + //
                                    "join CMND on CMND.soCMND = CONNGUOI.CMND \r\n" + //
                                    "join TRINHDO on nv.maTrinhDo = TRINHDO.maTrinhDo \r\n" + //
                                    "join CHUCVU on nv.maChucVu = CHUCVU.maChucVu \r\n" + //
                                    "join TAIKHOAN on TAIKHOAN.username = nv.maNhanVien \r\n" + //
                                    "left join HOPDONGLAODONG on HOPDONGLAODONG.maHopDong = nv.maHopDong \r\n" + //
                                    "where nv.maHopDong is not null and year(HOPDONGLAODONG.tuNgay) <= " + year + "and year(HOPDONGLAODONG.denNgay) >= " + year + "and CONNGUOI.gioiTinh = N'Nữ'\r\n" + //
                                    ") as SoNhanVienNu,\r\n" + //
                                    "\r\n" + //
                                    "(select count(gioiTinh) \r\n" + //
                                    "from CONNGUOI \r\n" + //
                                    "join NHANVIEN nv on CONNGUOI.CMND = nv.CMND \r\n" + //
                                    "join CMND on CMND.soCMND = CONNGUOI.CMND \r\n" + //
                                    "join TRINHDO on nv.maTrinhDo = TRINHDO.maTrinhDo \r\n" + //
                                    "join CHUCVU on nv.maChucVu = CHUCVU.maChucVu \r\n" + //
                                    "join TAIKHOAN on TAIKHOAN.username = nv.maNhanVien \r\n" + //
                                    "left join HOPDONGLAODONG on HOPDONGLAODONG.maHopDong = nv.maHopDong \r\n" + //
                                    "where nv.maHopDong is null and year(nv.ngayBatDauThuViec) <= " + year + "and year(nv.ngayKetThucThuViec) >= " + year + "and CONNGUOI.gioiTinh = N'Nam')\r\n" + //
                                    "+\r\n" + //
                                    "(select count(gioiTinh) \r\n" + //
                                    "from CONNGUOI \r\n" + //
                                    "join NHANVIEN nv on CONNGUOI.CMND = nv.CMND \r\n" + //
                                    "join CMND on CMND.soCMND = CONNGUOI.CMND \r\n" + //
                                    "join TRINHDO on nv.maTrinhDo = TRINHDO.maTrinhDo \r\n" + //
                                    "join CHUCVU on nv.maChucVu = CHUCVU.maChucVu \r\n" + //
                                    "join TAIKHOAN on TAIKHOAN.username = nv.maNhanVien \r\n" + //
                                    "left join HOPDONGLAODONG on HOPDONGLAODONG.maHopDong = nv.maHopDong \r\n" + //
                                    "where nv.maHopDong is not null and year(HOPDONGLAODONG.tuNgay) <= " + year + "and year(HOPDONGLAODONG.denNgay) >= " + year + "and CONNGUOI.gioiTinh = N'Nam'\r\n" + //
                                    ") as SoNhanVienNam ";
        
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                
                tk.setThoigian(year);
                tk.setNu((double) rs.getInt("SoNhanVienNu"));
                tk.setNam((double) rs.getInt("SoNhanVienNam"));
                
            
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return tk;
    }


    public ArrayList<THONGKENAMNUTUNGNAM> thongKeSoNamGanDay() { // 5
        ArrayList<THONGKENAMNUTUNGNAM> list = new ArrayList<THONGKENAMNUTUNGNAM>();
        int year = LocalDate.now().getYear();

        for (int i = year - 4; i <= year; i++) {
            list.add(getNamNuTheoNam(i));
        }

        return list;

    }
}
