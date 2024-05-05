package DTO;

import java.util.Objects;

public class NHOMQUYEN {
    private String maNhomQuyen;
	private String tenNhomQuyen;
	private boolean[] mangChucNang;
	public NHOMQUYEN() {
		
		this.maNhomQuyen = "";
		this.tenNhomQuyen = "";
		this.mangChucNang = null;
	}
	public NHOMQUYEN(String maNhomQuyen,String tenNhomQuyen,boolean[] mangChucNang) {
		
		this.maNhomQuyen = maNhomQuyen;
		this.tenNhomQuyen = tenNhomQuyen;
		this.mangChucNang = mangChucNang;
	}
	public String getMaNhomQuyen() {
		return maNhomQuyen;
	}
	public void setMaNhomQuyen(String maNhomQuyen) {
		this.maNhomQuyen = maNhomQuyen;
	}
	public String getTenNhomQuyen() {
		return tenNhomQuyen;
	}
	public void setTenNhomQuyen(String tenNhomQuyen) {
		this.tenNhomQuyen = tenNhomQuyen;
	}
	public boolean[] getMangChucNang() {
		return mangChucNang;
	}
	public void setMangChucNang(boolean[] mangChucNang) {
		this.mangChucNang = mangChucNang;
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
