package BUS;

import java.util.ArrayList;

import DAO.HopDongLaoDongDAO;
import DAO.UngVienDAO;
import DTO.HOPDONGLAODONG;
import DTO.UNGVIEN;

public class UngVienBUS {
    private final UngVienDAO ungVienDAO = new UngVienDAO(); 
    private ArrayList<UNGVIEN> listUngVien = new ArrayList<>();

    
    public UngVienBUS() {
       
        listUngVien = new ArrayList<>();
    }

    public ArrayList<UNGVIEN> getList() {
        return listUngVien;
    }

    

    public Object[][] getObject() {
        int n = listUngVien.size();
        Object[][] data = new Object[n][];
        
        for (int i = 0; i < listUngVien.size(); i++) {
            UNGVIEN uv = listUngVien.get(i);
    
           
            data[i] = new Object[]{
                i + 1,  
                uv.getMaTuyenDung(),                                
                uv.getMaUngVien() + " - " + uv.getHoTen(), 
                uv.getSdt(),                             
                uv.getEmail(),                           
                uv.getChucVu(),                          
                uv.getTrinhDo().getTrinhDoHocVan(),      
                uv.getMucLuongDeal(),         
                uv.getTrangThai()            
            };
        }
    
        return data;
    }
    
    public int addUngVien(UNGVIEN uv) {
        int result = ungVienDAO.insert(uv);
        if (result > 0) {
            listUngVien.add(uv); 
        }
        return result;
    }

   
    public int updateUngVien(UNGVIEN uv) {
        int result = ungVienDAO.update(uv);
        if (result > 0) {
            
            for (UNGVIEN u : listUngVien) {
                if (u.getMaUngVien().equals(uv.getMaUngVien())) {
                    u.setHoTen(uv.getHoTen());
                    u.setGioiTinh(uv.getGioiTinh());
                    u.setNgaySinh(uv.getNgaySinh());
                    u.setSdt(uv.getSdt());
                    u.setEmail(uv.getEmail());
                    u.setDiaChi(uv.getDiaChi());
                    u.setTrinhDo(uv.getTrinhDo());
                    break;
                }
            }
        }
        return result;
    }

    
    public int deleteUngVien(String maUngVien) {
        int result = ungVienDAO.del(maUngVien);
        if (result > 0) {
        
            listUngVien.removeIf(uv -> uv.getMaUngVien().equals(maUngVien));
        }
        return result;
    }
}
