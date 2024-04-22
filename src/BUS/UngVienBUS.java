package BUS;

import java.util.ArrayList;
import DAO.UngVienDAO;
import DTO.UNGVIEN;

public class UngVienBUS {
    private final UngVienDAO ungVienDAO = UngVienDAO.getInstance(); // Sử dụng Singleton pattern cho DAO
    private ArrayList<UNGVIEN> listUngVien = new ArrayList<>();

    public UngVienBUS() {
        refreshListUngVien(); // Tải danh sách ứng viên từ cơ sở dữ liệu
    }

    // Làm mới danh sách từ cơ sở dữ liệu
    public void refreshListUngVien() {
        listUngVien = ungVienDAO.getList();
    }

    public ArrayList<UNGVIEN> getListUngVien() {
        return listUngVien;
    }

    public int addUngVien(UNGVIEN uv) {
        int result = ungVienDAO.insert(uv);
        if (result > 0) {
            listUngVien.add(uv); // Thêm vào danh sách nếu thêm vào cơ sở dữ liệu thành công
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
            listUngVien.removeIf(uv -> uv.getMaUngVien().equals(maUngVien)); // Xóa khỏi danh sách nếu xóa khỏi cơ sở dữ liệu thành công
        }
        return result;
    }
}
