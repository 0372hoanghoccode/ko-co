package DAO;


import java.util.ArrayList;
public interface DAOInterface<T> {
	
	public ArrayList<T> getList(); 
	
	public int insert(T t);
	
	public int update(T t);
	
	public int del(String ma);
	
	
}
