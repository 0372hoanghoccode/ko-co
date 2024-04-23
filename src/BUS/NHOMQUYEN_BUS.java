package BUS;

import java.util.ArrayList;

import DAO.NhomQuyenDAO;
import DTO.NHOMQUYEN;

public class NHOMQUYEN_BUS {
    private final NhomQuyenDAO nhomquyen_dao = new NhomQuyenDAO();
    private ArrayList<NHOMQUYEN> list_nhomquyen = new ArrayList<>();

    public NHOMQUYEN_BUS() {
        // nhomquyen_dao.getInstance();
        list_nhomquyen = nhomquyen_dao.getList();
    }

    public ArrayList<NHOMQUYEN> getList() {
        return list_nhomquyen;
    }

    public int getNhomQuyen(String maNhomQuyen) {
        int i = 0;
        int vitri = -1;

        while(i < this.list_nhomquyen.size() && vitri == -1) {
            if (list_nhomquyen.get(i).getManhomquyen() == maNhomQuyen) {
                vitri = i;
            }
            else {
                i++;
            }
        }
        return vitri;
    }
    public int insertNhomQuyen(NHOMQUYEN nq) {
        int result = nhomquyen_dao.insert(nq);
        if (result > 0) {
            list_nhomquyen.add(nq);
        }
        return result;
    }

    public int updateNhomQuyen(NHOMQUYEN nq) {
        int result = nhomquyen_dao.update(nq);
        if (result > 0) {
            list_nhomquyen.set(getNhomQuyen(nq.getManhomquyen()), nq);
        }
        return result;
    }

    public int delNhomQuyen(String maNhomQuyen) {
        int result = nhomquyen_dao.del(String.valueOf(maNhomQuyen));
        if (result > 0) {
            list_nhomquyen.remove(getNhomQuyen(maNhomQuyen));
        }
        return result;
    }

    public Object[][] renderAllNhomQuyenData() {
        Object[][] data = new Object[list_nhomquyen.size()][3];
        for (int i = 0; i < list_nhomquyen.size(); i++) {
            data[i][0] = i + 1;
            data[i][1] = list_nhomquyen.get(i).getManhomquyen();
            data[i][2] = list_nhomquyen.get(i).getTennhomquyen();
        }
        return data;
    }

}
 