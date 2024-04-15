package DTO;

import java.util.Objects;

public class TAIKHOAN {
	
    private String username;
    private String pass;
    private String maNhomQuyen;
    private String avatarImg;
    
    
    public TAIKHOAN(){
      
    }
    
	public TAIKHOAN(String username, String pass,String maNhomQuyen, String avatarImg){
        this.username = username;
        this.pass = pass;
        this.avatarImg = avatarImg;
        this.maNhomQuyen = maNhomQuyen;
     
       
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
   
    
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 19 * hash + Objects.hashCode(this.username);
        hash = 19 * hash + Objects.hashCode(this.pass);
        hash = 19 * hash + Objects.hashCode(this.maNhomQuyen);
        
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
        
        if (this.maNhomQuyen != other.maNhomQuyen) {
            return false;
        }
        
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.pass, other.pass);
    }

    @Override
    public String toString() {
        return "AccountDTO{" +  ", username=" + username + ", pass=" + pass + ", maNhomQuyen=" + maNhomQuyen +  '}';
    }
}
