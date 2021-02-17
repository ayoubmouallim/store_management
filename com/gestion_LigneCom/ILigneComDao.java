package com.gestion_LigneCom;

import java.util.List;

import com.DAO.IDao;

public interface ILigneComDao  extends IDao<Ligne> {
	
	public List<Ligne> gettAll(long vente);
	public boolean deleteByVente(long venteId);
	public void addALL(List<Ligne> lignes, long id_vente);

}
