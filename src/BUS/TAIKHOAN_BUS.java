package BUS;

import java.util.ArrayList;

import DAO.TaiKhoanDAO;
import DTO.TAIKHOAN;


public class TAIKHOAN_BUS {
    private final TaiKhoanDAO taikhoan_dao = new TaiKhoanDAO();
    private ArrayList<TAIKHOAN> list_taikhoan = new ArrayList<>();

    public TAIKHOAN_BUS() {
        // taikhoan_dao.getInstance();
        list_taikhoan = taikhoan_dao.getList();
    }

    public int getTaiKhoan(String username) {
        int i = 0;
        int vitri = -1;
        while (i < this.list_taikhoan.size() && vitri == -1) {
            if (list_taikhoan.get(i).getUsername().equals(username)) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public ArrayList<TAIKHOAN> getList() {
        return list_taikhoan;
    }

    public int insertTaiKhoan(TAIKHOAN tk) {
        int result = taikhoan_dao.insert(tk);
        if (result > 0) {
            list_taikhoan.add(tk);
        }
        return result;
    }

    public int updateTaiKhoan(TAIKHOAN tk) {
        int result = taikhoan_dao.update(tk);
        if (result > 0) {
            list_taikhoan.set(getTaiKhoan(tk.getUsername()), tk);
        }
        return result;
    }

    public int delTaiKhoan(String username) {
        int result = taikhoan_dao.del(username);
        if (result > 0) {
            list_taikhoan.remove(getTaiKhoan(username));
        }
        return result;
    }

    public Object[][] renderAllTaiKhoanData() {
        Object[][] data = new Object[list_taikhoan.size()][4];
        for (int i = 0; i < list_taikhoan.size(); i++) {
            data[i][0] = i + 1;
            data[i][1] = list_taikhoan.get(i).getUsername();
            data[i][2] = list_taikhoan.get(i).getPass();
            data[i][3] = list_taikhoan.get(i).getMaNhomQuyen();
        }
        return data;
    }


}
