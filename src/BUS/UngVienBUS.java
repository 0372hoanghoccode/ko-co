package BUS;

import java.util.ArrayList;
import DAO.UngVienDAO;
import DTO.UNGVIEN;

public class UngVienBUS {
    private final UngVienDAO ungVienDAO = UngVienDAO.getInstance(); 
    private ArrayList<UNGVIEN> listUngVien = new ArrayList<>();

    public UngVienBUS() {
        refreshListUngVien();
    }

    // Tải lại danh sách ứng viên từ cơ sở dữ liệu
    public void refreshListUngVien() {
        listUngVien = ungVienDAO.getList();
    }


    // Lấy danh sách ứng viên
    public ArrayList<UNGVIEN> getListUngVien() {
        return listUngVien;
    }

    public Object[][] getObject(){
		Object[][] data = new Object[listUngVien.size()][];
        for (int i = 0; i < listUngVien.size(); i++) {
        	UNGVIEN obj = listUngVien.get(i);
            data[i] = new Object[]{obj.getMaTuyenDung(), obj.getMaUngVien()+" - "+obj.getHoTen(),obj.getSdt(),obj.getEmail(),obj.getChucVu(),obj.getTrinhDo().getTrinhDoHocVan(),obj.getMucLuongDeal(),obj.getTrangThai()};
        }
        return data;
	}

    // Thêm một ứng viên mới vào cơ sở dữ liệu và danh sách
    public int addUngVien(UNGVIEN uv) {
        int result = ungVienDAO.insert(uv);
        if (result > 0) {
            listUngVien.add(uv); // Thêm vào danh sách nếu thêm vào cơ sở dữ liệu thành công
        }
        return result;
    }

    // Cập nhật thông tin của một ứng viên
    public int updateUngVien(UNGVIEN uv) {
        int result = ungVienDAO.update(uv);
        if (result > 0) {
            // Tìm và cập nhật ứng viên trong danh sách
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

    // Xoá một ứng viên khỏi cơ sở dữ liệu và danh sách
    public int deleteUngVien(String maUngVien) {
        int result = ungVienDAO.del(maUngVien);
        if (result > 0) {
            // Xoá ứng viên khỏi danh sách nếu xoá khỏi cơ sở dữ liệu thành công
            listUngVien.removeIf(uv -> uv.getMaUngVien().equals(maUngVien));
        }
        return result;
    }
}
