package com.gestion_reglement;

import java.util.List;

import com.DAO.IDao;


public interface IPaiementDao  extends IDao<Paiement>{
	
	 public List<Paiement> getAll(long id_vente);

	  public boolean update(Paiement p);
	  public long create(Paiement p);

}
