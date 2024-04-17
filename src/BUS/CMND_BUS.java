package BUS;

import java.util.ArrayList;

import DAO.CMND_DAO;

import DTO.CMND;


public class CMND_BUS {
    private final CMND_DAO cmnd_dao = new CMND_DAO();
    private ArrayList<CMND> list_cmnd = new ArrayList<>();


    public CMND_BUS() {
        list_cmnd = cmnd_dao.getList();
    }
    
    public ArrayList<CMND> getList() {
        return list_cmnd;
    }


    public int insertCMND(CMND cmnd) {
        int result = cmnd_dao.insert(cmnd);
        if (result > 0) {
            list_cmnd.add(cmnd);
        }
        return result;
    }

    public int updateCMND(CMND cmnd) {
        int result = cmnd_dao.update(cmnd);
        if (result > 0) {
            for (CMND c : list_cmnd) {
                if (c.getSoCmnd().equals(cmnd.getSoCmnd())) {
                    c.setNoiCap(cmnd.getNoiCap());
                    c.setNgayCap(cmnd.getNgayCap());
                    break;
                }
            }
        }
        return result;
    }

    public int delCMND(String soCmnd) { // del by soCmnd
        int result = cmnd_dao.del(soCmnd);
        if (result > 0) {
            for (CMND c : list_cmnd) {
                if (c.getSoCmnd().equals(soCmnd)) {
                    list_cmnd.remove(c);
                    break;
                }
            }
        }
        return result;
    }

}
