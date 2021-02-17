package com.DAO;

import java.util.List;

public interface IDao <T> {
	
	public void add(T obj);
	public void delete(long id);
	public T getOne(long id);
	public List<T> getAll();

}
