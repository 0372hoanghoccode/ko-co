package DTO;

import java.time.LocalDate;

public class PHONGBAN {
    
    private String maPhong;
    private String tenPhong;
    private LocalDate ngayThanhLap;
    private String maTruongPhong;
    
    public PHONGBAN(){
        maPhong = "";
        tenPhong = "";
        ngayThanhLap = null;
        maTruongPhong = "";
    }
    
    public PHONGBAN(String maPhong, String tenPhong, LocalDate ngayThanhLap, String maTruongPhong){
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.ngayThanhLap = ngayThanhLap;
        this.maTruongPhong = maTruongPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public LocalDate getNgayThanhLap() {
        return ngayThanhLap;
    }

    public void setNgayThanhLap(LocalDate ngayThanhLap) {
        this.ngayThanhLap = ngayThanhLap;
    }

    public String getMaTruongPhong() {
        return maTruongPhong;
    }

    public void setMaTruongPhong(String maTruongPhong) {
        this.maTruongPhong = maTruongPhong;
    }
}