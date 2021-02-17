package com.gestion_client;

import java.util.List;

import com.DAO.IDao;


public interface IClientDao extends IDao <Client> {
	public List<Client> getAll(String nom);
	
	 public boolean update(Client cl);
}
