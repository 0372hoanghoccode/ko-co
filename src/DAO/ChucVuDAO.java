package DAO;

import java.util.ArrayList;

// --- IMPORT DTO ----
import DTO.CHUCVU;

public class ChucVuDAO implements DAOInterface<CHUCVU>{

    public static ChucVuDAO getInstance() {
        return new ChucVuDAO();
    }

    @Override
    public ArrayList<CHUCVU> getList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getList'");
    }

    @Override
    public void insert(CHUCVU t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(CHUCVU t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void del(String ma) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'del'");
    }

}