package com.gestion_reglement;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.DAO.AbstarctDao;
import com.gestion_Vente.Vente;
import com.gestion_Vente.VenteDaoImpl;
import com.gestion_client.Client;
import com.gestion_produit.Produit;
import com.gestion_produit.ProduitDaoImpl;

public class PaiementDaoImpl  extends AbstarctDao implements IPaiementDao{
	
	
	public long create(Paiement p)
	{
		String sql = "insert into paiement (total, type,id_vente,date) values(?,?,?,?)";
		PreparedStatement pst;
		if(p.getTotal() == 0.0) return -1;
		try {
			pst = connection.prepareStatement(sql,1);
			pst.setDouble(1, p.getTotal());
			pst.setString(2, p.getType());
			pst.setLong(3, p.getVente().getCode());
			pst.setDate(4,Date.valueOf(p.getDate()));
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			while(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return -1;
	}

	@Override
	public void add(Paiement p) {
		String sql = "insert into paiement (total, type,id_vente,date) values(?,?,?,?)";
		PreparedStatement pst;
		
		try {
			pst = connection.prepareStatement(sql,1);
			pst.setDouble(1, p.getTotal());
			pst.setString(2, p.getType());
			pst.setLong(3, p.getVente().getCode());
			pst.setDate(4,Date.valueOf(p.getDate()));
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
	}

	@Override
	public void delete(long id) {
		String sql="delete from paiement where id=?";
		try {
		PreparedStatement pst =connection.prepareStatement(sql);
		pst.setLong(1,id);
		pst.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Paiement getOne(long id) {
		  String sql="SELECT * from paiement where id=?";
			
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setLong(1,id);
				ResultSet rs= pst.executeQuery();
				
				
				if(rs.next()) {
					Date date=rs.getDate("date");
					Vente v=(new VenteDaoImpl()).getOne(rs.getLong("id_vente"));
					Paiement p=new Paiement(rs.getLong("id"),rs.getDouble("total"),rs.getString("type"),v,date.toLocalDate() );
					return p;
				}
				
			}catch(Exception exp) {
				System.out.println(exp);
			}
			
			
			return null;
	}

	@Override
	public List<Paiement> getAll() {
		List<Paiement> liste=new ArrayList<Paiement>();
		
		 PreparedStatement pst;
		 String sql="SELECT * FROM paiement";
		 
		 try {
				pst=connection.prepareStatement(sql);
				
				ResultSet rs= pst.executeQuery();

				while(rs.next())
				{
					Date date=rs.getDate("date");
					Vente v=(new VenteDaoImpl()).getOne(rs.getLong("id_vente"));
					liste.add(new Paiement(rs.getLong("id"),rs.getDouble("total"),rs.getString("type"),v,date.toLocalDate() ));
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		 
		 return liste;
	}

	@Override
	public List<Paiement> getAll(long id_vente) {
		List<Paiement> liste=new ArrayList<Paiement>();
		
		 PreparedStatement pst;
		 String sql="SELECT * FROM paiement where id_vente=?";
		 
		 try {
				pst=connection.prepareStatement(sql);
				pst.setLong(1,id_vente);
				ResultSet rs= pst.executeQuery();

				while(rs.next())
				{
					Date date=rs.getDate("date");
					Vente v=(new VenteDaoImpl()).getOne(rs.getLong("id_vente"));
					liste.add(new Paiement(rs.getLong("id"),rs.getDouble("total"),rs.getString("type"),v,date.toLocalDate() ));
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		 
		 return liste;
	}

	@Override
	public boolean update(Paiement p) {
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement("update paiement set type=? , total=? , id_vente=? ,date=? where id = ?");
			pst.setString(1,p.getType());
			pst.setDouble(2,p.getTotal());
			pst.setDouble(3,p.getVente().getCode());
			pst.setDate(4, Date.valueOf(p.getDate()));
			pst.setLong(5,p.getId());
			pst.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
