package BUS;
import java.util.ArrayList;
import java.text.NumberFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import DAO.HopDongLaoDongDAO;

import DTO.HOPDONGLAODONG;

public class HOPDONGLAODONG_BUS {
    private final HopDongLaoDongDAO hopdonglaodong_dao = new HopDongLaoDongDAO();
    private ArrayList<HOPDONGLAODONG> list_hopdonglaodong = new ArrayList<>();

    public HOPDONGLAODONG_BUS() {
        // hopdonglaodong_dao.getInstance();
        list_hopdonglaodong = hopdonglaodong_dao.getList();
    }

    public ArrayList<HOPDONGLAODONG> getList() {
        return list_hopdonglaodong;
    }

    public int insertHopDongLaoDong(HOPDONGLAODONG hdld) {
        int result = hopdonglaodong_dao.insert(hdld);
        if (result > 0) {
            list_hopdonglaodong.add(hdld);
        }
        return result;
    }

    public int updateHopDongLaoDong(HOPDONGLAODONG hdld) {
        int result = hopdonglaodong_dao.update(hdld);
        if (result > 0) {
            list_hopdonglaodong.set(getMaHopDongLaoDong(hdld.getMaHopDong()), hdld);
        }
        return result;
    }  

    public int delHopDongLaoDong(String maHopDongLaoDong) {
        int result = hopdonglaodong_dao.del(maHopDongLaoDong);
        if (result > 0) {
            list_hopdonglaodong.remove(getMaHopDongLaoDong(maHopDongLaoDong));
        }
        return result;
    }


    private int getMaHopDongLaoDong(String maHopDongLaoDong) {
        int i = 0;
        int vitri = -1;
        while (i < this.list_hopdonglaodong.size() && vitri == -1) {
            if (list_hopdonglaodong.get(i).getMaHopDong().equals(maHopDongLaoDong)) {
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
        int n = list_hopdonglaodong.size();
        Object[][] data = new Object[n][];

        for (int i = 0; i < n; i++) {
            HOPDONGLAODONG temp_hdld = list_hopdonglaodong.get(i);

            data[i] = new Object[] {
                i + 1 + "",
                temp_hdld.getMaHopDong(),
                temp_hdld.getMaNhanVien(),
                temp_hdld.getTenNhanVien(),
                temp_hdld.getPhongBan(),
                formatDate(temp_hdld.getTuNgay()),
                formatDate(temp_hdld.getDenNgay()),
                temp_hdld.getLoaiHopDong(),
                formatSalary(temp_hdld.getLuongCoBan()),
            };
        }


        return data;
    }

}   
