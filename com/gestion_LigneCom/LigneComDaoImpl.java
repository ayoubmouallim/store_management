package com.gestion_LigneCom;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.AbstarctDao;
import com.gestion_produit.Produit;
import com.gestion_produit.ProduitDaoImpl;

public class LigneComDaoImpl extends AbstarctDao implements ILigneComDao {

	@Override
	public void add(Ligne l) {
		
		String sql="insert into ligne (id_vente,id_produit,qte,stotal) values(?,?,?,?)";
		PreparedStatement pst;
		
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1,l.getVente());
			pst.setLong(2,l.getProduit().getCode());
			pst.setInt(3,l.getQte());
			pst.setDouble(4,l.getStotal());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(long id) {
		
		String sql="delete from ligne where id=?";
		PreparedStatement pst;
		try {
		pst =connection.prepareStatement(sql);
		pst.setLong(1,id);
		pst.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Ligne getOne(long id) {
		
       String sql="SELECT * from ligne where id=?";
       PreparedStatement pst;
       
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1,id);
			ResultSet rs= pst.executeQuery();
			
			if(rs.next()) {
				long codeProduit = rs.getLong("id_produit"); 
				ProduitDaoImpl pdao = new ProduitDaoImpl();
				Ligne ln=new Ligne(rs.getLong("id_vente"),rs.getInt("qte"),pdao.getOne(codeProduit));
				ln.setCode(rs.getLong("id"));
				return ln;
			}
			
		}catch(Exception exp) {
			System.out.println(exp);
		}
		
		
		return null;
	}

	@Override
	public List<Ligne> getAll() {
		List<Ligne> liste=new ArrayList<Ligne>();
		
		 String sql="SELECT * FROM ligne";
		 PreparedStatement pst;
		 
		 try {
				pst=connection.prepareStatement(sql);
				
				ResultSet rs= pst.executeQuery();

				ProduitDaoImpl pdao = new ProduitDaoImpl();
				
				while(rs.next())
				{	
					long codeProduit = rs.getLong("id_produit"); 
					liste.add(new Ligne(rs.getLong("id"),rs.getLong("id"),rs.getInt("qte"),pdao.getOne(codeProduit)));
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		 
		 return liste;
	}

	@Override
	public List<Ligne> gettAll(long vente) {
		
		List<Ligne> liste=new ArrayList<Ligne>();
		
		 String sql="SELECT * FROM ligne where id_vente=?";
		 PreparedStatement pst;
		 
		 try {
				pst=connection.prepareStatement(sql);
				pst.setLong(1,vente);
				
				ResultSet rs= pst.executeQuery();

				ProduitDaoImpl pdao = new ProduitDaoImpl();
				
				while(rs.next())
				{	
					long codeProduit = rs.getLong("id_produit"); 
					liste.add(new Ligne(rs.getLong("id"),rs.getLong("id"),rs.getInt("qte"),pdao.getOne(codeProduit)));
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		 
		 return liste;
	}

	
	public boolean deleteByVente(long VenteId) {
		PreparedStatement pst;
		String sql="delete from Ligne where id_vente = ?";
		try {
			
			pst = connection.prepareStatement(sql);
			pst.setLong(1, VenteId);
			pst.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public void addALL(List<Ligne> lignes, long id_vente) {
		if(lignes != null)
		for (Ligne l : lignes) {
			l.setVente(id_vente);
			this.add(l);
		}
	}

}
