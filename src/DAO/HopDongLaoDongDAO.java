package DAO;

import java.util.ArrayList;

import DTO.HOPDONGLAODONG;

public class HopDongLaoDongDAO implements DAOInterface<HOPDONGLAODONG>{

    @Override
    public ArrayList<HOPDONGLAODONG> getList() {
        // TODO Auto-generated method stub
        ArrayList<HOPDONGLAODONG> list = new ArrayList<>();

        return list;
    }

    @Override
    public void insert(HOPDONGLAODONG t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(HOPDONGLAODONG t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void del(String ma) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'del'");
    }

}
