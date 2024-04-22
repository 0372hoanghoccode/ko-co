package BUS;

import java.util.ArrayList;
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
}
