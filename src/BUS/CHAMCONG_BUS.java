package BUS;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import DAO.ChamCongDAO;
import DTO.CHAMCONG;
import DTO.NHANVIEN;

public class CHAMCONG_BUS {
    private final ChamCongDAO chamCong_dao= new ChamCongDAO();
    private ArrayList<CHAMCONG> list_chamCong= new ArrayList<>();
    private ArrayList<NHANVIEN> list_nhanVien= new ArrayList<>();
    
    public CHAMCONG_BUS() {
		list_chamCong=chamCong_dao.getList();
	}
    
    public ArrayList<CHAMCONG> getList(){
    	return list_chamCong;
    }
    
    public int insertChamCong(CHAMCONG cc) {
    	int result = chamCong_dao.insert(cc);
    	if (result > 0)
    		list_chamCong.add(cc);
    	return result;
    }
    
    public int updateChamCong( CHAMCONG cc) {
    	int result = chamCong_dao.update(cc);
    	if (result > 0) 
    		list_chamCong.set(getMaBCC(cc.getMaBangChamCong()),cc);
    	return result;
    }
    
    private int getMaBCC(String maBCC) {
    	int i=0;
    	int vitri=-1;
    	while ( i< this.list_chamCong.size() && vitri == -1) {
    		if (list_chamCong.get(i).getMaBangChamCong().equals(maBCC))
    			vitri=i;
    		else
    			i++;   			
    	}
    	return vitri;
    }
    
    
    
    public Object[][] renderChamCongData() {
    	int n=list_chamCong.size();
    	Object[][] data = new Object[n][];
    	for (int i=0; i< n; i++) {
    		CHAMCONG temp_cc=list_chamCong.get(i);
    		String tenNhanVien=chamCong_dao.getTenNhanVien(temp_cc.getMaBangChamCong());
    		String chamCongTime = temp_cc.getThangChamCong() + "/" + temp_cc.getNamChamCong();
    		data[i] =new Object[] {
    			i+1+"",
    			temp_cc.getMaBangChamCong(),
    			tenNhanVien,
    			chamCongTime,
    			temp_cc.getSoNgayLamViec(),
    			temp_cc.getSoNgayNghi(),
    			temp_cc.getSoNgayTre(),
    			temp_cc.getSoGioLamThem(),
    			temp_cc.getChiTiet(),
    		
    		};
    	}
		return data;
		  	
    }
    
//    public Object[][] renderDSNhanVienData(){
//    	int n=list_nhanVien.size();
//    	Object[][] data= new Object[n][];
//    	for (int i=0; i<n; i++) {
//    		NHANVIEN temp_cc=list_nhanVien.get(i); 
//    		data[i]= new Object[] {
//    				i+1+"",
//    				temp_cc.getHoTen(),
//    				
//    				
//    		};
//    	}
//    	return data;
//    }
    
    public Object[][] changeDataValue(String[] event_name) {
    	String[] default_val = new String[]{
    	    	"Phòng Ban", "Tháng", "Năm"
        };
        ArrayList<CHAMCONG> list_render = chamCong_dao.renderChangevalue(default_val, event_name);
        int n = list_render.size();
        
        Object[][] data = new Object[n][];
        
        for (int i = 0; i < n ;i++) {
            CHAMCONG temp_cc = list_render.get(i);
            String tenNhanVien=chamCong_dao.getTenNhanVien(temp_cc.getMaBangChamCong());
            String chamCongTime=temp_cc.getThangChamCong()+"/"+temp_cc.getNamChamCong();
            data[i] = new Object[] {
                i+1 + "", 
                temp_cc.getMaBangChamCong(),
                tenNhanVien,
                chamCongTime,
                temp_cc.getSoNgayLamViec(),
                temp_cc.getSoNgayNghi(),
                temp_cc.getSoNgayTre(),
                temp_cc.getSoGioLamThem(),
            };

        }
    	return data;
    	
    	
    }
//    public void sortMaBCC(int type) {
//        list_chamCong.sort((o1, o2) -> o1.getMaBangChamCong().compareTo(o2.getMaBangChamCong()));
//        if (type != 0) {
//            Collections.reverse(list_chamCong);
//        }
    //} 
    
   
}
