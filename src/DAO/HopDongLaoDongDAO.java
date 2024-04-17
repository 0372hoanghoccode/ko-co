package DAO;

import java.util.ArrayList;

import DTO.HOPDONGLAODONG;

public class HopDongLaoDongDAO implements DAOInterface<HOPDONGLAODONG>{

    public static HopDongLaoDongDAO getInstance() {
        return new HopDongLaoDongDAO();
    }

    @Override
    public ArrayList<HOPDONGLAODONG> getList() {
        // TODO Auto-generated method stub
        ArrayList<HOPDONGLAODONG> list = new ArrayList<>();

        return list;
    }

    @Override
    public int insert(HOPDONGLAODONG t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(HOPDONGLAODONG t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int del(String ma) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'del'");
    }

}
