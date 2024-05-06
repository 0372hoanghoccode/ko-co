package DTO;

public class THONGKENAMNUTUNGNAM {
    int thoigian;
    double nam;
    double nu;

    public THONGKENAMNUTUNGNAM() {

    }

    public THONGKENAMNUTUNGNAM(int thoigian, double nam, double nu) {
        this.thoigian = thoigian;
        this.nam = nam;
        this.nu = nu;
    }

    public int getThoigian() {
        return thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }

    public double getNam() {
        return nam;
    }

    public void setNam(double nam) {
        this.nam = nam;
    }

    public double getNu() {
        return nu;
    }

    public void setNu(double nu) {
        this.nu = nu;
    }

    @Override
    public String toString() {
        return "Năm " + thoigian + ", Nam: " + nam + ", Nữ: " + nu;
    }
}
