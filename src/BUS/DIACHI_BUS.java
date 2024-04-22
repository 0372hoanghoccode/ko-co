package BUS;

import java.util.ArrayList;
import DAO.DiaChiDAO;
import DTO.DIACHI;

public class DIACHI_BUS {
    private final DiaChiDAO diachi_dao = new DiaChiDAO();
    private ArrayList<DIACHI> list_diachi = new ArrayList<>();

    public DIACHI_BUS() {
        list_diachi = diachi_dao.getList();
    }

    public ArrayList<DIACHI> getList() {
        return list_diachi;
    }

    public int insertDIACHI(DIACHI dc) {
        int result = diachi_dao.insert(dc);
        if (result > 0) {
            list_diachi.add(dc);
        }
        return result;
    }

    public int updateDIACHI(DIACHI dc) {
        int result = diachi_dao.update(dc);
        if (result > 0) {
            list_diachi.set(getBySoNha(dc.getSoNha()), dc);
        }
        return result;
    }

    public int delDIACHI(String soNha) {
        int result = diachi_dao.del(soNha);
        if (result > 0) {
            list_diachi.remove(getBySoNha(soNha));
        }
        return result;
    }

    private int getBySoNha(String soNha) {
        int i = 0;
        int vitri = -1;
        while (i < this.list_diachi.size() && vitri == -1) {
            if (list_diachi.get(i).getSoNha().equals(soNha)) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
}
