package DTO;

import java.time.LocalDate;

public class UNGVIEN extends CONNGUOI {

    public String maTuyenDung;
    public String maUngVien;
    public double mucLuongDeal;
    public TRINHDO trinhDo;
    public String chucVu;
    private String trangThai;

    public UNGVIEN(CMND cmnd, String hoTen, String gioiTinh, LocalDate ngaySinh, DIACHI diaChi, String sdt,
            String danToc, String tonGiao, String email, String maTuyenDung, String maUngVien, double mucLuongDeal,
            TRINHDO trinhDo, String chucVu,
            String trangThai) {
        super(cmnd, hoTen, gioiTinh, ngaySinh, diaChi, sdt, danToc, tonGiao, email);
        this.maTuyenDung = maTuyenDung;
        this.maUngVien = maUngVien;
        this.mucLuongDeal = mucLuongDeal;
        this.trinhDo = trinhDo;
        this.chucVu = chucVu;
        this.trangThai = trangThai;
    }

    public UNGVIEN() {
        super();
        this.maTuyenDung = null;
        this.maUngVien = null;
        this.mucLuongDeal = 0;
        this.trinhDo = new TRINHDO();
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMaTuyenDung() {
        return maTuyenDung;
    }

    public void setMaTuyenDung(String maTuyenDung) {
        this.maTuyenDung = maTuyenDung;
    }

    public String getMaUngVien() {
        return maUngVien;
    }

    public void setMaUngVien(String maUngVien) {
        this.maUngVien = maUngVien;
    }

    public double getMucLuongDeal() {
        return mucLuongDeal;
    }

    public void setMucLuongDeal(double mucLuongDeal) {
        this.mucLuongDeal = mucLuongDeal;
    }

    public TRINHDO getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(TRINHDO trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String LocalDateToString(LocalDate date) {
        String arr[] = date.toString().split("-");
        return arr[2] + "-" + arr[1] + "-" + arr[0];

    }

    public String changeSalaryToFormatString(double value) {
        long temp = (long) value;
        String s = String.valueOf(temp);

        int n = s.length(), count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (count == 3) {
                s = s.substring(0, i + 1) + "," + s.substring(i + 1);
                count = 0;
            }
            count++;
        }
        return s;
    }

    public String[] getDataToTuyen() {
        String data[] = { 
            this.getHoTen(), //0
            this.getGioiTinh(), //1
            LocalDateToString(this.getNgaySinh()), //2
            this.getSdt(), //3
            this.getTrinhDo().getTrinhDoHocVan(), //4
            this.getTrinhDo().getTrinhDoChuyenMon(), //5
            this.getTrinhDo().getChuyenNganh(), //6
            this.getDiaChi().getSoNha(),//7
            this.getDiaChi().getDuong(), //8
            this.getDiaChi().getPhuongXa(),//9
            this.getDiaChi().getQuanHuyen(),//10
            this.getDiaChi().getTinhThanhPho(),//11
            this.getDanToc(), //12
            this.getTonGiao(), //13
            this.getCmnd().getSoCmnd(), //14
            LocalDateToString(this.getCmnd().getNgayCap()), //15
            // this.getCmnd().getNoiCap(), this.getTinhTrangHonNhan(),
            this.getChucVu(),//16
            changeSalaryToFormatString(this.getMucLuongDeal()),//17
            this.getEmail(),//18
            this.getMaUngVien()//19
        };
        return data;
    }

}
