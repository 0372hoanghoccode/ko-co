package DAO;


import java.util.ArrayList;
public interface DAOInterface<T> {
	
	public ArrayList<T> getList(); // phải có 
	
	public void insert(T t);
	
	public void update(T t);
	
	public void del(String ma);
	
	
	// public int getSelectedIndex(); 
	
	// public ArrayList<String> getDanhSachMaSo();
	
    //	public T getDoiTuongByMaSo(String ma);
}
