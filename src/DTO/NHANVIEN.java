package DTO;

import java.time.LocalDate;


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
    public NHANVIEN(String maNhanVien,String hoTen,  String gioiTinh,  LocalDate ngaySinh,DIACHI diaChi,String sdt, CMND cmnd, String danToc,String tinhTrangHonNhan,String tonGiao, String email,String maPhong, TRINHDO trinhDo, CHUCVU chucVu, TAIKHOAN taiKhoan   ) {
        super(cmnd,hoTen, gioiTinh, ngaySinh, diaChi, sdt, danToc,tinhTrangHonNhan, tonGiao, email);
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
 
}
