package DTO;

public class CHAMCONG {

    private String maBangChamCong;
    private String maNhanVien;
    private int thangChamCong;
    private int namChamCong;
    private int soNgayLamViec;
    private int soNgayNghi;
    private int soNgayTre;
    private int soGioLamThem;
    private String chiTiet;
    
    public CHAMCONG() {
        this.maBangChamCong = null;
        this.maNhanVien = "";
        this.thangChamCong = 1;
        this.namChamCong = 2020;
        this.soNgayLamViec = 0;
        this.soNgayNghi = 0;
        this.soNgayTre = 0;
        this.soGioLamThem = 0;
        this.chiTiet=null;
    
    }

    public CHAMCONG(String maBangChamCong, String maNhanVien, int thangChamCong, int namChamCong, int soNgayLamViec, int soNgayNghi, int soNgayTre, int soGioLamThem, String chiTiet,String trangThai) {
        this.maBangChamCong = maBangChamCong;
        this.maNhanVien = maNhanVien;
        this.thangChamCong = thangChamCong;
        this.namChamCong = namChamCong;
        this.soNgayLamViec = soNgayLamViec;
        this.soNgayNghi = soNgayNghi;
        this.soNgayTre = soNgayTre;
        this.soGioLamThem = soGioLamThem;
        this.chiTiet=chiTiet;

    }
    
    public String getChiTiet() {
		return chiTiet;
	}

	public void setChiTiet(String chiTiet) {
		this.chiTiet = chiTiet;
	}

	public void setMaBangChamCong(String maBangChamCong) {
        this.maBangChamCong = maBangChamCong;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setThangChamCong(int thangChamCong) {
        this.thangChamCong = thangChamCong;
    }

    public void setNamChamCong(int namChamCong) {
        this.namChamCong = namChamCong;
    }

    public void setSoNgayLamViec(int soNgayLamViec) {
        this.soNgayLamViec = soNgayLamViec;
    }

    public void setSoNgayNghi(int soNgayNghi) {
        this.soNgayNghi = soNgayNghi;
    }

    public void setSoNgayTre(int soNgayTre) {
        this.soNgayTre = soNgayTre;
    }

    public void setSoGioLamThem(int soGioLamThem) {
        this.soGioLamThem = soGioLamThem;
    }

    public String getMaBangChamCong() {
        return maBangChamCong;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public int getThangChamCong() {
        return thangChamCong;
    }

    public int getNamChamCong() {
        return namChamCong;
    }

    public int getSoNgayLamViec() {
        return soNgayLamViec;
    }

    public int getSoNgayNghi() {
        return soNgayNghi;
    }

    public int getSoNgayTre() {
        return soNgayTre;
    }

    public int getSoGioLamThem() {
        return soGioLamThem;
    }

    public Object[][] getObjectToRender() {
        Object[][] data = new Object[][]{{
            this.getMaBangChamCong(),
            this.getMaNhanVien(),
            this.getThangChamCong() + "/" + this.getNamChamCong(),
            this.getSoNgayLamViec(),
            this.getSoNgayNghi(),
            this.getSoNgayTre(),
            this.getSoGioLamThem(),}
        };
        return data;
    }

    public String[] getDataToRenderEdit() {
        return new String[]{
            this.getMaBangChamCong(),
            this.getMaNhanVien(),
            this.getThangChamCong() + "",
            this.getNamChamCong() + "",
            this.getSoNgayLamViec() + "",
            this.getSoNgayNghi() + "",
            this.getSoNgayTre() + "",
            this.getSoGioLamThem() + ""
        };
    }
}
