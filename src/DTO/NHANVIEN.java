package DTO;

import java.time.LocalDate;

import DAO.PhongBanDAO;
import DAO.NhanVienDAO;

public class NHANVIEN extends CONNGUOI {

    private String maNhanVien;
    private String maPhong;
    private TRINHDO trinhDo;
    private CHUCVU chucVu;
    private TAIKHOAN taiKhoan;

    public NHANVIEN() {
        super();
        this.maNhanVien = null;
        this.maPhong = null;
        this.trinhDo = new TRINHDO();
        this.chucVu = null;
        this.taiKhoan = new TAIKHOAN();
    }
    public NHANVIEN(String maNhanVien,String hoTen,  String gioiTinh,  LocalDate ngaySinh,DIACHI diaChi,String sdt, CMND cmnd, String danToc,String tonGiao, String email,String maPhong, TRINHDO trinhDo, CHUCVU chucVu, TAIKHOAN taiKhoan   ) {
        super(cmnd,hoTen, gioiTinh, ngaySinh, diaChi, sdt, danToc, tonGiao, email);
        this.maNhanVien = maNhanVien;
        this.maPhong = maPhong;
        this.trinhDo = trinhDo;
        this.chucVu = chucVu;
        this.taiKhoan = taiKhoan;
    }
    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public void setTrinhDo(TRINHDO trinhDo) {
        this.trinhDo = trinhDo;
    }

    public void setChucVu(CHUCVU chucVu) {
        this.chucVu = chucVu;
    }

    public void setTaiKhoan(TAIKHOAN taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMaNhanVien() {
        return this.maNhanVien;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public TRINHDO getTrinhDo() {
        return this.trinhDo;
    }

    public CHUCVU getChucVu() {
        return this.chucVu;
    }

    public TAIKHOAN getTaiKhoan() {
        return this.taiKhoan;
    }
    public double getMucLuongChung() {
    	if(this instanceof NHANVIENCHINHTHUC) {
    		return ((NHANVIENCHINHTHUC)this).getHopDong().getLuongCoBan();
    	}else {
    		return ((NHANVIENTHUVIEC)this).getLuongThuViec();
    	}
    }
    public String[] getObjectToRender() {
    	
    	String loaiHinh = "Chính thức";
    	String ngayBatDau = "";
    	String ngayKetThuc = "";
    	String hopDong = "Chưa có";
    	String loaiHopDong = "Chưa xác định";
    	if(this instanceof NHANVIENTHUVIEC) {
    		loaiHinh = "Thử việc";
    		ngayBatDau = LocalDateToString(((NHANVIENTHUVIEC)this).getNgayBatDauThuViec());
    		ngayKetThuc =LocalDateToString(((NHANVIENTHUVIEC)this).getNgayKetThucThuViec());
    	}else {
    		hopDong = ((NHANVIENCHINHTHUC)this).getHopDong().getMaHopDong();
    		loaiHopDong = ((NHANVIENCHINHTHUC)this).getHopDong().getLoaiHopDong();
    		ngayBatDau = LocalDateToString(((NHANVIENCHINHTHUC)this).getHopDong().getTuNgay());
    		ngayKetThuc =  LocalDateToString(((NHANVIENCHINHTHUC)this).getHopDong().getDenNgay());
    	}
    	String[] data = new String[] {
			this.getMaNhanVien(),this.getHoTen(),PhongBanDAO.getTenTuMaSo(this.maPhong),this.getChucVu().getTenChucVu(),LocalDateToString(this.getChucVu().getNgayNhanChuc()),loaiHinh,
			hopDong,ngayBatDau,ngayKetThuc,loaiHopDong,changeSalaryToFormatString(this.getMucLuongChung()),
			this.getGioiTinh(),LocalDateToString(this.getNgaySinh()),this.getDiaChi().toString(),this.getSdt(),this.getCmnd().getSoCmnd()+" - "+this.getCmnd().getNoiCap()+" - "+LocalDateToString(this.getCmnd().getNgayCap()),this.getTrinhDo().getTrinhDoChuyenMon()+" - "+this.getTrinhDo().getChuyenNganh(),this.getDanToc(),this.getTonGiao(),
			this.getHoTen(),this.getDiaChi().getTinhThanhPho(),this.getSdt(),this.getEmail(),
			
    	};
		
		return data;
    }
    public String[] getDataToRenderDepartmentDetailInfoEmployee() {
    	String data[] = {this.getMaNhanVien(),this.getHoTen(),this.getGioiTinh(),LocalDateToString(this.getNgaySinh()),this.getSdt(),this.getDiaChi().getTinhThanhPho().trim(),PhongBanDAO.getTenTuMaSo(this.getMaPhong()),this.getChucVu().getTenChucVu(),"         "+this.getChucVu().getNgayNhanChuc().toString()};
    	return data;
    }
    public String[] getDataToFix() {
    	String loaiHinh = "Nhân viên chính thức";
    	String ngayBatDau = "";
    	String ngayKetThuc = "";
    	String mucLuong = "";
    	if(this instanceof NHANVIENCHINHTHUC) {
    		ngayBatDau = LocalDateToString(((NHANVIENCHINHTHUC)this).getHopDong().getTuNgay());
    		ngayKetThuc = ((NHANVIENCHINHTHUC)this).getHopDong().getLoaiHopDong();
    		mucLuong = changeSalaryToFormatStringToFix(((NHANVIENCHINHTHUC)this).getHopDong().getLuongCoBan());
    		
    	}else {
    		loaiHinh = "Nhân viên thử việc";
    		ngayBatDau = LocalDateToString(((NHANVIENTHUVIEC)this).getNgayBatDauThuViec());
    		ngayKetThuc = LocalDateToString(((NHANVIENTHUVIEC)this).getNgayKetThucThuViec());
    		mucLuong = changeSalaryToFormatStringToFix(((NHANVIENTHUVIEC)this).getLuongThuViec());
    	}
    	String data[] = { this.getMaNhanVien(),this.getHoTen(),this.getGioiTinh(),LocalDateToString(this.getNgaySinh()),this.getSdt(),this.getEmail(),
    			this.getDiaChi().getSoNha(),this.getDiaChi().getDuong(),this.getDiaChi().getPhuongXa(),this.getDiaChi().getQuanHuyen(),this.getDiaChi().getTinhThanhPho(),
    			this.getTrinhDo().getTrinhDoHocVan(),this.getTrinhDo().getTrinhDoChuyenMon(),this.getTrinhDo().getChuyenNganh(),
    			this.getCmnd().getSoCmnd(),LocalDateToString(this.getCmnd().getNgayCap()),this.getCmnd().getNoiCap(),
    			this.getDanToc(),this.getTonGiao(),
    			PhongBanDAO.getTenTuMaSo(this.maPhong),
    			this.getChucVu().getTenChucVu(),LocalDateToString(this.getChucVu().getNgayNhanChuc()),
    			loaiHinh,
    			ngayBatDau,ngayKetThuc,mucLuong,
    			this.getTaiKhoan().getAvatarImg(),
    	};
    	return data;
    }

    public static String LocalDateToString(LocalDate date) {
		String arr[] = date.toString().split("-");
		return arr[2]+"-"+arr[1]+"-"+arr[0];
		
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
    
    public static String changeSalaryToFormatStringToFix(double value) {
		long temp = (long)value;
        String s = String.valueOf(temp);
        
        int n = s.length(), count = 0;
        String kq = "";
        for(int i=0;i<n;i++){
            kq+= s.charAt(i)+"";
        }
        return kq;
	}
}
