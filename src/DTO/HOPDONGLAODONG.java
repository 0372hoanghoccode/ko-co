package DTO;

import java.time.LocalDate;

public class HOPDONGLAODONG {
	private String maHopDong;
	private String tenNhanVien;
	private String maNhanVien;
	private LocalDate tuNgay;
	private LocalDate denNgay;
	private String loaiHopDong;
	private String phongBan;
	private double luongCoBan;

	public HOPDONGLAODONG() {
		this.maHopDong = "";
		this.tuNgay = null;
		this.denNgay = null;
		this.loaiHopDong = "";
		this.luongCoBan = 0;
		this.maNhanVien = "";
		this.tenNhanVien = "";
		this.phongBan = "";
	}
	public HOPDONGLAODONG(String maHopDong, LocalDate tuNgay, LocalDate denNgay, String loaiHopDong,
			double luongCoBan) {
		this.maHopDong = maHopDong;
		this.maNhanVien = "null";
		this.tenNhanVien = "null";
		this.phongBan = "null";
		this.tuNgay = tuNgay;
		this.denNgay = denNgay;
		this.loaiHopDong = loaiHopDong;
		this.luongCoBan = luongCoBan;
	}
	public HOPDONGLAODONG(String maHopDong,String maNhanVien,String tenNhanVien,String phongBan, LocalDate tuNgay, LocalDate denNgay, String loaiHopDong,
			double luongCoBan) {
		this.maHopDong = maHopDong;
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.phongBan = phongBan;
		this.tuNgay = tuNgay;
		this.denNgay = denNgay;
		this.loaiHopDong = loaiHopDong;
		this.luongCoBan = luongCoBan;
	}

	public String getMaHopDong() {
		return maHopDong;
	}
	public void setPhongBan(String phongBan) {
		this.phongBan = phongBan;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getPhongBan() {
		return this.phongBan;
	}
	public void setMaHopDong(String maHopDong) {
		this.maHopDong = maHopDong;
	}

	public LocalDate getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(LocalDate tuNgay) {
		this.tuNgay = tuNgay;
	}
	public String getMaNhanVien() {
		return this.maNhanVien;
	}
	public String getTenNhanVien() {
		return this.tenNhanVien;
	}
	public LocalDate getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(LocalDate denNgay) {
		this.denNgay = denNgay;
	}

	public String getLoaiHopDong() {
		return loaiHopDong;
	}

	public void setLoaiHopDong(String loaiHopDong) {
		this.loaiHopDong = loaiHopDong;
	}

	public double getLuongCoBan() {
		return luongCoBan;
	}

	public void setLuongCoBan(double luongCoBan) {
		this.luongCoBan = luongCoBan;
	}

}
