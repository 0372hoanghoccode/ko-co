
package BUS;
import java.util.ArrayList;
import DAO.TrinhDoDAO;
import DTO.TRINHDO;


public class TRINHDO_BUS {
    
    private TrinhDoDAO trinhDoDAO;
    private ArrayList<TRINHDO> listTrinhDo;

    public TRINHDO_BUS() {
        this.trinhDoDAO = TrinhDoDAO.getInstance();
        this.listTrinhDo = trinhDoDAO.getList();
    }

    public ArrayList<TRINHDO> getListTrinhDo() {
        return listTrinhDo;
    }

    public int insertTrinhDo(TRINHDO trinhDo) {
        int result = trinhDoDAO.insert(trinhDo);
        if (result > 0) {
            listTrinhDo.add(trinhDo);
        }
        return result;
    }

    public int updateTrinhDo(TRINHDO trinhDo) {
        int result = trinhDoDAO.update(trinhDo);
        if (result > 0) {
            for (TRINHDO td : listTrinhDo) {
                if (td.getMaTrinhDo().equals(trinhDo.getMaTrinhDo())) {
                    td.setTrinhDoHocVan(trinhDo.getTrinhDoHocVan());
                    td.setTrinhDoChuyenMon(trinhDo.getTrinhDoChuyenMon());
                    td.setChuyenNganh(trinhDo.getChuyenNganh());
                    break;
                }
            }
        }
        return result;
    }

    // public int deleteTrinhDo(String maTrinhDo) {
    //     int result = trinhDoDAO.del(maTrinhDo);
    //     if (result > 0) {
    //         listTrinhDo.removeIf(trinhDo -> trinhDo.getMaTrinhDo().equals(maTrinhDo));
    //     }
    //     return result;
    // }
}

