package BUS;

import java.util.ArrayList;
import java.text.NumberFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import DAO.NhanVienDAO;

import DTO.NHANVIEN;

public class NhanVienBUS {
    private final NhanVienDAO nhanvien_dao = new NhanVienDAO();
    private ArrayList<NHANVIEN> list_nhanvien = new ArrayList<>();

    public NhanVienBUS() {
        nhanvien_dao.getInstance();
        list_nhanvien = nhanvien_dao.getList();

    }

    public ArrayList<NHANVIEN> getList() {
        return list_nhanvien;
    }

    public int insertNhanVien(NHANVIEN nv) {
        int result = nhanvien_dao.insert(nv);
        if (result > 0) {
            list_nhanvien.add(nv);
        }
        return result;
    }

    public int updateNhanVien(NHANVIEN nv) {
        int result = nhanvien_dao.update(nv);
        if (result > 0) {
            list_nhanvien.set(getByMaNhanVien(nv.getMaNhanVien()), nv);
        }
        return result;
    }

    public int delNhanVien(String maNhanVien) {
        int result = nhanvien_dao.del(maNhanVien);
        if (result > 0) {
            list_nhanvien.remove(getByMaNhanVien(maNhanVien));
        }
        return result;
    }

    private int getByMaNhanVien(String maNhanVien) {
        int i = 0;
        int vitri = -1;
        while (i < this.list_nhanvien.size() && vitri == -1) {
            if (list_nhanvien.get(i).getMaNhanVien().equals(maNhanVien)) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }


    
    public String formatSalary(Double salary) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        return formatter.format(salary);
    }

    public String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public Object[][] getDataObjectToRender() {
        int n = list_nhanvien.size();
        Object[][] data = new Object[n][];

        for (int i = 0; i < n; i++) {
            NHANVIEN temp_nv = list_nhanvien.get(i);

            data[i] = new Object[] {
                i+1 + "",
                temp_nv.getHoTen(),
                temp_nv.getGioiTinh(),
                formatDate(temp_nv.getNgaySinh()),
                temp_nv.getSdt(),
                temp_nv.getMaPhong(),
                temp_nv.getChucVu().getTenChucVu(),
                formatSalary(temp_nv.getMucLuongChung()),
            };

        }

        return data;
    }


}