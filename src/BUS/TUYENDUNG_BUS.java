package BUS;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import DAO.TuyenDungDAO;
import DTO.BAOCAOTUYENDUNG;

public class TUYENDUNG_BUS {
    private TuyenDungDAO tuyenDungDAO; // Loại bỏ việc sử dụng singleton ở đây để có thể mock/test dễ dàng hơn
    private ArrayList<BAOCAOTUYENDUNG> listBaoCaoTuyenDung;

    public TUYENDUNG_BUS() {
        tuyenDungDAO = new TuyenDungDAO(); 
        refreshListBaoCaoTuyenDung();
    }

    public void refreshListBaoCaoTuyenDung() {
        listBaoCaoTuyenDung = tuyenDungDAO.getList();
    }

    public ArrayList<BAOCAOTUYENDUNG> getListBaoCaoTuyenDung() {
        return new ArrayList<>(listBaoCaoTuyenDung); 
    }

    public int addBaoCaoTuyenDung(BAOCAOTUYENDUNG baoCao) {
        int result = tuyenDungDAO.insert(baoCao);
        if (result > 0) {
            listBaoCaoTuyenDung.add(baoCao);
        }
        return result;
    }

    public int updateBaoCaoTuyenDung(BAOCAOTUYENDUNG baoCao) {
        int result = tuyenDungDAO.update(baoCao);
        if (result > 0) {
            // Tìm và cập nhật thông tin báo cáo tuyển dụng trong danh sách
            listBaoCaoTuyenDung.stream()
                .filter(bc -> bc.getMaTuyenDung().equals(baoCao.getMaTuyenDung()))
                .findFirst()
                .ifPresent(bc -> {
                    bc.setChucVu(baoCao.getChucVu());
                    bc.setHocVan(baoCao.getHocVan());
                    bc.setGioiTinh(baoCao.getGioiTinh());
                    bc.setDoTuoi(baoCao.getDoTuoi());
                    bc.setSoLuongCanTuyen(baoCao.getSoLuongCanTuyen());
                    bc.setHanNopHoSo(baoCao.getHanNopHoSo());
                    bc.setMucLuongToiThieu(baoCao.getMucLuongToiThieu());
                    bc.setMucLuongToiDa(baoCao.getMucLuongToiDa());
                });
        }
        return result;
    }

    public int deleteBaoCaoTuyenDung(String maTuyenDung) {
        int result = tuyenDungDAO.del(maTuyenDung);
        if (result > 0) {
            listBaoCaoTuyenDung.removeIf(baoCao -> baoCao.getMaTuyenDung().equals(maTuyenDung));
        }
        return result;
    }
    
    public String vnd(Double number) {
		Locale locale = new Locale("vi", "VN"); // Thiết lập locale cho tiếng Việt
		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		String formattedNumber = numberFormat.format(number);
		return formattedNumber;
	}

    public Object[][] getDataObjectToRender(){
		Object[][] data = new Object[listBaoCaoTuyenDung.size()][];
        for (int i = 0; i < listBaoCaoTuyenDung.size(); i++) {
            BAOCAOTUYENDUNG obj = listBaoCaoTuyenDung.get(i);
            data[i] = new Object[]{i+1,obj.getMaTuyenDung(), obj.getChucVu(),obj.getHocVan(),obj.getGioiTinh(),obj.getDoTuoi(),obj.getSoLuongCanTuyen(),obj.getSoLuongNopHoSo(),obj.getSoLuongDaTuyen(),obj.getHanNopHoSo(),vnd(obj.getMucLuongToiThieu()),vnd(obj.getMucLuongToiDa())};
        }
        return data;
	}

    public BAOCAOTUYENDUNG getBaoCaoTuyenDung(String maBaoCao) {
		for(BAOCAOTUYENDUNG i : listBaoCaoTuyenDung) {
			if(i.getMaTuyenDung().equals(maBaoCao)) {
				return i;
			}
		}
		return null;
	}
    
}
