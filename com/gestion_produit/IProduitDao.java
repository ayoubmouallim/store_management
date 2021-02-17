package com.gestion_produit;

import java.util.List;

import com.DAO.IDao;

public interface IProduitDao extends IDao<Produit>  {
	
  public List<Produit> getAll(String des);

  public boolean update(Produit p);
	
}
