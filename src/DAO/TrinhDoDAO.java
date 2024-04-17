package DAO;


import java.util.ArrayList;

// --- IMPORT DTO ----
import DTO.TRINHDO;

public class TrinhDoDAO implements DAOInterface<TRINHDO>{

	public static TrinhDoDAO getInstance() {
		return new TrinhDoDAO();
	}



	@Override
	public ArrayList<TRINHDO> getList() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getList'");
	}

	@Override
	public int insert(TRINHDO t) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'insert'");
		
	}

	@Override
	public int update(TRINHDO t) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
		
	}

	@Override
	public int del(String ma) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'del'");
		
	}

}
