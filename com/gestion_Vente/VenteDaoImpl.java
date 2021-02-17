package com.gestion_Vente;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DAO.AbstarctDao;
import com.gestion_LigneCom.Ligne;
import com.gestion_LigneCom.LigneComDaoImpl;
import com.gestion_client.Client;
import com.gestion_client.ClientDaoImpl;

public class VenteDaoImpl extends AbstarctDao implements IVenteDao{

	@Override
	public void add(Vente v) {

       String sql="insert into vente (date,id_client,total) values(?,?,?)";
       PreparedStatement pst;
       
       try {
		pst = connection.prepareStatement(sql,1);
		Date date= Date.valueOf(v.getDate());
		
		pst.setDate(1, date);
		pst.setLong(2,v.getClient().getCode());
		pst.setDouble(3,v.getTotale());
		
		pst.execute();
		long code = 0;
		ResultSet rs = pst.getGeneratedKeys();
		
		while(rs.next())
		{
			 code = rs.getLong(1);
		}
		
		(new LigneComDaoImpl()).addALL(v.getLignes(), code);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	@Override
	// methode supprime la vente et les ses lignes de commande
	public void delete(long code) {
		
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement("delete from vente where code = ?");
			pst.setLong(1, code);
			(new LigneComDaoImpl()).deleteByVente(code);
			pst.executeUpdate();
		
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public Vente getOne(long code) {
		PreparedStatement pst ;
		try {
			pst = connection.prepareStatement("select * from vente where code = ?");
			pst.setLong(1, code);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Client cl = (new ClientDaoImpl()).getOne(rs.getLong("id_client")) ;
				List<Ligne> liste = (new LigneComDaoImpl()).gettAll(rs.getLong("code"));
				
				Vente v= new Vente(rs.getLong("code"), rs.getDate("date").toLocalDate(),liste ,cl);
				return v;
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	@Override
	public List<Vente> getAll() {
		PreparedStatement pst;
		List<Vente> liste = new ArrayList<Vente>();
		try {
			pst = connection.prepareStatement("select * from vente");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Client cl = (new ClientDaoImpl()).getOne(rs.getLong("id_client"));
				if(cl == null )
					System.out.println("cl is null");
				List<Ligne> lignes = (new LigneComDaoImpl()).gettAll(rs.getLong("code"));
				
				liste.add(new Vente(rs.getLong("code"),rs.getDate("date").toLocalDate(),lignes,cl));
			}
			return liste;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

}
