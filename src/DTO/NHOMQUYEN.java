package DTO;

import java.util.Objects;

public class NHOMQUYEN {
    private String manhomquyen;
    private String tennhomquyen;

    public NHOMQUYEN() {
    }

    public NHOMQUYEN(String manhomquyen, String tennhomquyen) {
        this.manhomquyen = manhomquyen;
        this.tennhomquyen = tennhomquyen;
    }

    public String getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(String manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public String getTennhomquyen() {
        return tennhomquyen;
    }

    public void setTennhomquyen(String tennhomquyen) {
        this.tennhomquyen = tennhomquyen;
    }

    // @Override
    // public int hashCode() {
    //     int hash = 3;
    //     hash = 37 * hash + this.manhomquyen;
    //     hash = 37 * hash + Objects.hashCode(this.tennhomquyen);
    //     return hash;
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj) {
    //         return true;
    //     }
    //     if (obj == null) {
    //         return false;
    //     }
    //     if (getClass() != obj.getClass()) {
    //         return false;
    //     }
    //     final NHOMQUYEN other = (NHOMQUYEN) obj;
    //     if (this.manhomquyen != other.manhomquyen) {
    //         return false;
    //     }
    //     return Objects.equals(this.tennhomquyen, other.tennhomquyen);
    // }

    // @Override
    // public String toString() {
    //     return "NhomQuyenDTO{" + "manhomquyen=" + manhomquyen + ", tennhomquyen=" + tennhomquyen + '}';
    // }
    
}
