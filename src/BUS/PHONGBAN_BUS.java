package BUS;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import DAO.PhongBanDAO;
import DTO.PHONGBAN;


public class PHONGBAN_BUS {
    private final PhongBanDAO phongban_dao = new PhongBanDAO();
    private ArrayList<PHONGBAN> list_phongban = new ArrayList<>();

    public PHONGBAN_BUS() {
        // phongban_dao.getInstance();
        list_phongban = phongban_dao.getList();
    }

    public ArrayList<PHONGBAN> getList() {
        return list_phongban;
    }

    public int insertPhongBan(PHONGBAN pb) {
        int result = phongban_dao.insert(pb);
        if (result > 0) {
            list_phongban.add(pb);
        }
        return result;
    }

    public int updatePhongBan(PHONGBAN pb) {
        int result = phongban_dao.update(pb);
        if (result > 0) {
            list_phongban.set(getMaPhongBan(pb.getMaPhong()), pb);
        }
        return result;
    }

    public int delPhongBan(String maPhongBan) {
        int result = phongban_dao.del(maPhongBan);
        if (result > 0) {
            list_phongban.remove(getMaPhongBan(maPhongBan));
        }
        return result;
    }

    private int getMaPhongBan(String maPhongBan) {
        int i = 0;
        int vitri = -1;
        while (i < this.list_phongban.size() && vitri == -1) {
            if (list_phongban.get(i).getMaPhong().equals(maPhongBan)) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public String formatSalary(Double salary) {
        long roundedSalary = Math.round(salary);
        return NumberFormat.getInstance(Locale.US).format(roundedSalary);
    }

    public Object[][] renderAllPhongBanData() {
        Object[][] data = new Object[list_phongban.size()][];
        for (int i = 0; i < list_phongban.size(); i++) {
            PHONGBAN temp_pb = list_phongban.get(i);
            
            double[] luong = phongban_dao.getAverageSalaryData(temp_pb.getMaPhong());
            double avg_luong = (luong[1] + luong[2]) / luong[0];

            data[i] = new Object[] {
                i + 1 + "",
                temp_pb.getTenPhong(),
                temp_pb.getMaTruongPhong(),
                phongban_dao.getSoLuongNhanVien(temp_pb.getMaPhong()),
                formatSalary(avg_luong),
            };
        }   
        return data;   
    }


    public Object[][] renderChiTietPhongBan(int index) {
        PHONGBAN temp_pb = list_phongban.get(index);
        Object[][] data = phongban_dao.getDataNhanVienCuaPhong(temp_pb.getMaPhong());
        return data;
    }

    public ArrayList<String> renderChiTietNhanVien(int curr_tablerow, String maNV) {
        String maPhong = list_phongban.get(curr_tablerow).getMaPhong();
        
        ArrayList<String> temp = phongban_dao.getDataNhanVienTablePhongBan(maPhong, maNV);
        return temp;    
       
    }

}