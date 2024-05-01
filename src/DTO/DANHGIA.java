package DTO;

import java.sql.Date;

import javax.management.loading.PrivateClassLoader;

public class DANHGIA {
	private String maDanhGia;
	private String maNhanVien;
	private String nguoiDanhGia;
	private Date ngayDanhGia;
	private float diemDanhGia;
	private String xepLoaiDanhGia;
	private String chiTietDanhGia;
	private String loaiDanhGia;
	private String ghiChu;
	
	public DANHGIA() {
		
	}

	public DANHGIA(String maDanhGia, String maNhanVien, String nguoiDanhGia, Date ngayDanhGia, float diemDanhGia,
			String xepLoaiDanhGia, String chiTietDanhGia, String loaiDanhGia, String ghiChu) {
		super();
		this.maDanhGia = maDanhGia;
		this.maNhanVien = maNhanVien;
		this.nguoiDanhGia = nguoiDanhGia;
		this.ngayDanhGia = ngayDanhGia;
		this.diemDanhGia = diemDanhGia;
		this.xepLoaiDanhGia = xepLoaiDanhGia;
		this.chiTietDanhGia = chiTietDanhGia;
		this.loaiDanhGia = loaiDanhGia;
		this.ghiChu = ghiChu;
	}

	public String getMaDanhGia() {
		return maDanhGia;
	}

	public void setMaDanhGia(String maDanhGia) {
		this.maDanhGia = maDanhGia;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getNguoiDanhGia() {
		return nguoiDanhGia;
	}

	public void setNguoiDanhGia(String nguoiDanhGia) {
		this.nguoiDanhGia = nguoiDanhGia;
	}

	public Date getNgayDanhGia() {
		return ngayDanhGia;
	}

	public void setNgayDanhGia(Date ngayDanhGia) {
		this.ngayDanhGia = ngayDanhGia;
	}

	public float getDiemDanhGia() {
		return diemDanhGia;
	}

	public void setDiemDanhGia(float diemDanhGia) {
		this.diemDanhGia = diemDanhGia;
	}

	public String getXepLoaiDanhGia() {
		return xepLoaiDanhGia;
	}

	public void setXepLoaiDanhGia(String xepLoaiDanhGia) {
		this.xepLoaiDanhGia = xepLoaiDanhGia;
	}

	public String getChiTietDanhGia() {
		return chiTietDanhGia;
	}

	public void setChiTietDanhGia(String chiTietDanhGia) {
		this.chiTietDanhGia = chiTietDanhGia;
	}

	public String getLoaiDanhGia() {
		return loaiDanhGia;
	}

	public void setLoaiDanhGia(String loaiDanhGia) {
		this.loaiDanhGia = loaiDanhGia;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	
	
}


