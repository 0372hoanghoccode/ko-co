package BUS;

import java.util.ArrayList;
import java.text.NumberFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import java.util.Collections;
import java.util.Comparator;

import DAO.ChucVuDAO;
import DAO.NhanVienDAO;
import DTO.CHUCVU;
import DTO.CMND;
import DTO.DIACHI;
import DTO.HOPDONGLAODONG;
import DTO.NHANVIEN;
import DTO.NHANVIENCHINHTHUC;
import DTO.NHANVIENTHUVIEC;
import DTO.TAIKHOAN;
import DTO.TRINHDO;



public class NhanVienBUS {
    private final NhanVienDAO nhanvien_dao = new NhanVienDAO();
    private ArrayList<NHANVIEN> list_nhanvien = new ArrayList<>();

    public NhanVienBUS() {
        
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
                temp_nv.getMaNhanVien() + " - " + temp_nv.getHoTen() ,
                temp_nv.getGioiTinh(),
                formatDate(temp_nv.getNgaySinh()),
                temp_nv.getDiaChi().toString(),
                temp_nv.getSdt(),
                DAO.PhongBanDAO.getInstance().getTenTuMaSo(temp_nv.getMaPhong()),
                temp_nv.getChucVu().getTenChucVu(),
                formatSalary(temp_nv.getMucLuongChung()),
            };

        }

        return data;
    }
    public Object[][] changeDataValue(String[] event_name) {
    	String[] default_val = new String[]{
    	    	"Phòng ban", "Giới tính", "Độ tuổi", "Thuộc tính sắp xếp", "Thứ tự sắp"
        };
        list_nhanvien = nhanvien_dao.renderChangeValue(default_val, event_name);
        
    	return getDataObjectToRender();
    	
    }
    
    public Object[][] filterData(String searchText) {
        ArrayList<NHANVIEN> allEmployees = nhanvien_dao.getList(); 
        ArrayList<NHANVIEN> filteredEmployees = new ArrayList<>();

        // chuyển về chuổi thường
        String lowerCaseSearchText = searchText.toLowerCase();

        for (NHANVIEN nv : allEmployees) {
            String maVaTen = nv.getMaNhanVien() + " - " + nv.getHoTen();

            // chuyển về chuổi thường trc khi so 
            if (maVaTen.toLowerCase().contains(lowerCaseSearchText)) {
                filteredEmployees.add(nv);
            }
        }

        Object[][] data = new Object[filteredEmployees.size()][];
        for (int i = 0; i < filteredEmployees.size(); i++) {
            NHANVIEN temp_nv = filteredEmployees.get(i);
            data[i] = new Object[] {
                i+1 + "", 
                temp_nv.getMaNhanVien() + " - " + temp_nv.getHoTen(),
                temp_nv.getGioiTinh(),
                formatDate(temp_nv.getNgaySinh()),
                temp_nv.getDiaChi().toString(),
                temp_nv.getSdt(),
                DAO.PhongBanDAO.getInstance().getTenTuMaSo(temp_nv.getMaPhong()),
                temp_nv.getChucVu().getTenChucVu(),
                formatSalary(temp_nv.getMucLuongChung()),
            };
        }

        return data;
    }


  
    public Object[] renderSelectedNhanVien(String maNhanVien) {
        NHANVIEN nv = nhanvien_dao.getNhanVien(maNhanVien);
        Object[] data = new Object[15];
        data[0] = nv.getMaNhanVien();
        data[1] = nv.getHoTen();
        data[2] = nv.getGioiTinh();
        data[3] = formatDate(nv.getNgaySinh());
        data[4] = nv.getDiaChi().toString();
        data[5] = nv.getSdt();
        data[6] = nv.getDanToc();
        data[7] = nv.getTonGiao();
        data[8] = nv.getEmail();
        data[9] = DAO.PhongBanDAO.getInstance().getTenTuMaSo(nv.getMaPhong());
        data[10] = nv.getTrinhDo().getTrinhDoHocVan();
        data[11] = nv.getTrinhDo().getTrinhDoChuyenMon();
        data[12] = nv.getTrinhDo().getChuyenNganh();
        data[13] = nv.getChucVu().getTenChucVu();
        data[14] = formatSalary(nv.getMucLuongChung());
        return data;
    }
    
    



    // Tên
    public Object[][] sortNhanVienByNameAscending() {
        Collections.sort(list_nhanvien, new Comparator<NHANVIEN>() {
            @Override
            public int compare(NHANVIEN nv1, NHANVIEN nv2) {
                return nv1.getHoTen().compareTo(nv2.getHoTen());
            }
        });
        return getDataObjectToRender();

    }
    public Object[][] sortNhanVienByNameDescending() {
        Collections.sort(list_nhanvien, new Comparator<NHANVIEN>() {
            @Override
            public int compare(NHANVIEN nv1, NHANVIEN nv2) {
                return nv1.getHoTen().compareTo(nv2.getHoTen());
            }
        });
        return getDataObjectToRender();
    }

    // Ngày sinh
    public Object[][] sortNhanVienByAgeAscending() {
        Collections.sort(list_nhanvien, new Comparator<NHANVIEN>() {
            @Override
            public int compare(NHANVIEN nv1, NHANVIEN nv2) {
                return nv1.getNgaySinh().compareTo(nv2.getNgaySinh());
            }
        });
        return getDataObjectToRender();
    }
    
    public Object[][] sortNhanVienByAgeDescending() {
        Collections.sort(list_nhanvien, new Comparator<NHANVIEN>() {
            @Override
            public int compare(NHANVIEN nv1, NHANVIEN nv2) {
                return nv2.getNgaySinh().compareTo(nv1.getNgaySinh());
            }
        });
        return getDataObjectToRender();
    }


    // Lương
    public Object[][] sortNhanVienBySalaryAscending() {
        Collections.sort(list_nhanvien, new Comparator<NHANVIEN>() {
            @Override
            public int compare(NHANVIEN nv1, NHANVIEN nv2) {
                return Double.compare(nv1.getMucLuongChung(), nv2.getMucLuongChung());
            }
        });
        return getDataObjectToRender();
    }
    
    public Object[][] sortNhanVienBySalaryDescending() {
        Collections.sort(list_nhanvien, new Comparator<NHANVIEN>() {
            @Override
            public int compare(NHANVIEN nv1, NHANVIEN nv2) {
                return Double.compare(nv2.getMucLuongChung(), nv1.getMucLuongChung());
            }
        });
        return getDataObjectToRender();
    }

    
    
    

}