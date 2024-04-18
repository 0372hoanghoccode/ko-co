package BUS;

import java.util.ArrayList;
import DAO.LuongDAO;
import DAO.NhanVienDAO;
import DTO.LUONG;
import DTO.CHAMCONG;

public class LUONG_BUS {
    
    private LuongDAO luongDAO;

    public LUONG_BUS() {
        this.luongDAO = new LuongDAO();
    }

    public ArrayList<LUONG> getList() {
        return luongDAO.getList();
    }

    public int insert(LUONG x) {
        // Có thể thực hiện các kiểm tra trước khi thêm dữ liệu vào cơ sở dữ liệu ở đây
        return luongDAO.insert(x);
    }

    public int update(LUONG x) {
        // Có thể thực hiện các kiểm tra trước khi cập nhật dữ liệu vào cơ sở dữ liệu ở đây
        return luongDAO.update(x);
    }

    public int del(String maLuong) {
        // Có thể thực hiện các kiểm tra trước khi xóa dữ liệu khỏi cơ sở dữ liệu ở đây
        return luongDAO.del(maLuong);
    }

    public static LUONG chuyenBangChamCongSangLuong(CHAMCONG bcc,double thuong,double phucapkhac,double khoantrukhac) {
		LUONG x = new LUONG();
		x.setMaLuong("L"+bcc.getMaBangChamCong().substring(3));
		x.setMaBangChamCong(bcc.getMaBangChamCong());
        // Tạo một thể hiện của NhanVienDAO
        NhanVienDAO nhanVienDAO = new NhanVienDAO();
		// Sử dụng thể hiện của NhanVienDAO để gọi phương thức getLuongNhanVien
        double dulieu[] = nhanVienDAO.getLuongNhanVien(bcc.getMaNhanVien());
		double luongThucTe = Math.round(dulieu[0]/26*bcc.getSoNgayLamViec() + dulieu[0]/26/8*bcc.getSoGioLamThem());
		double phuCap = Math.round(dulieu[0]*dulieu[1]/100);
		double truBaoHiem = Math.round(dulieu[0]*0.06);
		double luongTruocThue = luongThucTe+phuCap+phucapkhac-truBaoHiem-khoantrukhac+thuong;
		double thue = 0;
		if(luongTruocThue<=5000000) {
			thue = 0;
		}else if(luongTruocThue>5000000 && luongTruocThue<=10000000) {
			thue = 250000+(luongTruocThue-5000000)*0.1;
		}else if(luongTruocThue>10000000 && luongTruocThue<=18000000) {
			thue = 750000+(luongTruocThue-10000000)*0.15;
		}else if(luongTruocThue>18000000 && luongTruocThue<=32000000) {
			thue = 1850000+(luongTruocThue-18000000)*0.2;
		}else if(luongTruocThue>32000000 && luongTruocThue<=52000000) {
			thue = 4750000+(luongTruocThue-32000000)*0.25;
		}else {
			thue = 9750000+(luongTruocThue-52000000)*0.3;
		}
		thue = Math.round(thue);
		double thucLanh = Math.round(luongTruocThue - thue);
		x.setLuongThucTe(luongThucTe);
		x.setPhuCapChucVu(phuCap);
		x.setPhuCapKhac(phucapkhac);
		x.setKhoanTruBaoHiem(truBaoHiem);
		x.setKhoanTruKhac(khoantrukhac);
		x.setLuongThuong(thuong);
		x.setThue(thue);
		x.setThucLanh(thucLanh);
		return x;
	}

    public static String changeSalaryToFormatString(double value) {
		long temp = (long)value;
        String s = String.valueOf(temp);
        
        int n = s.length(), count = 0;
        for(int i=n-1;i>=0;i--){
            if(count==3){
                s = s.substring(0, i+1) +","+ s.substring(i+1);
                count = 0;
            }
            count++; 
        }
        return s;
	}
}
