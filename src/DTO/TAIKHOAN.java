package DTO;

import java.util.Objects;

public class TAIKHOAN {
	private String maNhanVien;
    private String username;
    private String pass;
    private String maNhomQuyen;
    private String avatarImg;
    private int trangThai;
    
    public TAIKHOAN(){
      
    }
    
	public TAIKHOAN(String maNhanVien , String username, String pass,String maNhomQuyen, String avatarImg, int trangThai){
        this.username = username;
        this.pass = pass;
        this.avatarImg = avatarImg;
        this.maNhomQuyen = maNhomQuyen;
        this.trangThai = trangThai;
        this.maNhanVien = maNhanVien;
    }
    
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }
    public String getMaNhomQuyen() {
    	return this.maNhomQuyen;
    }
    public void setMaNhomQuyen(String maNhomQuyen) {
    	this.maNhomQuyen = maNhomQuyen;
    }
   
    public int getTrangThai() {
    	return this.trangThai;
    }
    public void setTrangThai(int trangThai) {
    	this.trangThai = trangThai;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.maNhanVien);
        hash = 19 * hash + Objects.hashCode(this.username);
        hash = 19 * hash + Objects.hashCode(this.pass);
        hash = 19 * hash + Objects.hashCode(this.maNhomQuyen);
        hash = 19 * hash + this.trangThai;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TAIKHOAN other = (TAIKHOAN) obj;
        if (this.maNhanVien != other.maNhanVien) {
            return false;
        }
        if (this.maNhomQuyen != other.maNhomQuyen) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.pass, other.pass);
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "manv=" + maNhanVien + ", username=" + username + ", pass=" + pass + ", maNhomQuyen=" + maNhomQuyen + ", trangThai=" + trangThai + '}';
    }
}
