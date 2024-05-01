package BUS;

import java.text.NumberFormat;
import java.util.Locale;

import java.util.ArrayList;
import DAO.LuongDAO;
import DAO.NhanVienDAO;
import DTO.LUONG;
import DTO.NHANVIEN;
import DTO.CHAMCONG;
import DTO.HOPDONGLAODONG;

public class LUONG_BUS {
	private final LuongDAO luong_dao = new LuongDAO();
    private ArrayList<LUONG> list_luong = new ArrayList<>();

    public LUONG_BUS() {
	
		list_luong = luong_dao.getList();
    }

    public ArrayList<LUONG> getList() {
        
		return list_luong;
    }
    
	public int insert(LUONG luong) {
		int result = luong_dao.insert(luong);
		if (result > 0) {
			list_luong.add(luong);
		}
		return result;
	}
    
	public int update(LUONG luong) {
		int result = luong_dao.update(luong);
		if (result > 0) {
			int index = getIndexByMaLuong(luong.getMaLuong());
			if (index != -1) {
				list_luong.set(index, luong);
			}
		}
		return result;
	}
    
	public int delete(String maLuong) {
		int result = luong_dao.del(maLuong);
		if (result > 0) {
			int index = getIndexByMaLuong(maLuong);
			if (index != -1) {
				list_luong.remove(index);
			}
		}
		return result;
	}
    
	private int getIndexByMaLuong(String maLuong) {
		for (int i = 0; i < list_luong.size(); i++) {
			if (list_luong.get(i).getMaLuong().equals(maLuong)) {
				return i;
			}
		}
		return -1;  // Nếu không tìm thấy đối tượng
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

	public Object[][] getDataObjectToRender() {
		int n = list_luong.size();  
		Object[][] data = new Object[n][];  
	
		for (int i = 0; i < n; i++) {
			LUONG luong = list_luong.get(i);  
	
			data[i] = new Object[] {
				i + 1 + "",  // STT
				luong.getMaLuong(),
				luong.getMaBangChamCong(),
				formatCurrency(luong.getLuongThucTe()),  
				formatCurrency(luong.getLuongThuong()),
				formatCurrency(luong.getPhuCapChucVu()),
				formatCurrency(luong.getPhuCapKhac()),
				formatCurrency(luong.getKhoanTruBaoHiem()),
				formatCurrency(luong.getKhoanTruKhac()),
				formatCurrency(luong.getThue()),
				formatCurrency(luong.getThucLanh())
			};
		}
		return data;
	}
	
	public static String formatCurrency(double value) {
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		return format.format(value);
	}
	

}
