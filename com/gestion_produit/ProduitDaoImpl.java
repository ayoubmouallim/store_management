package com.gestion_produit;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.AbstarctDao;


public class ProduitDaoImpl extends AbstarctDao implements IProduitDao {

	@Override
	public void add(Produit p) {
		
        String sql="insert into produit (designation,prix,qte,date,total) values (?,?,?,?,?)";
		
			PreparedStatement pst;
			
			try {
				pst = connection.prepareStatement(sql);
				pst.setString(1,p.getDesignation());
				pst.setDouble(2,p.getPrix());
				pst.setInt(3,p.getQte());
				Date date=Date.valueOf(p.getDate());
				pst.setDate(4,date);
				pst.setDouble(5,p.getTotal());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	@Override
	public void delete(long code) {

		String sql="delete from produit where code=?";
		try {
		PreparedStatement pst =connection.prepareStatement(sql);
		pst.setLong(1,code);
		pst.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Produit getOne(long code) {
		
        String sql="SELECT * from produit where code=?";
		
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setLong(1,code);
			ResultSet rs= pst.executeQuery();
			
			
			if(rs.next()) {
				Date date=rs.getDate("date");
				Produit p=new Produit(rs.getLong("code"),rs.getString("designation"),rs.getDouble("prix"),rs.getInt("qte"),date.toLocalDate());
				return p;
			}
			
		}catch(Exception exp) {
			System.out.println(exp);
		}
		
		
		return null;
	}

	@Override
	public List<Produit> getAll() {
		
     List<Produit> liste=new ArrayList<Produit>();
		
	 String sql="SELECT * FROM produit";
	 
	 try {
			PreparedStatement pst=connection.prepareStatement(sql);
			
			ResultSet rs= pst.executeQuery();

			while(rs.next())
			{
				Date date=rs.getDate("date");
				liste.add(new Produit(rs.getLong("code"),rs.getString("designation"),rs.getDouble("prix"),rs.getInt("qte"),date.toLocalDate() ));
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	 
	 return liste;
	}

	@Override
	public List<Produit> getAll(String des) {
		
		 List<Produit> liste=new ArrayList<Produit>();
			
		 String sql="SELECT * FROM produit where designation like ?";
		 
		 try {
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1,des+"%");
				ResultSet rs= pst.executeQuery();

				while(rs.next())
				{
					Date date=rs.getDate("date");
					liste.add(new Produit(rs.getLong("code"),rs.getString("designation"),rs.getDouble("prix"),rs.getInt("qte"),date.toLocalDate() ));
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		 
		 return liste;
	}
	
	public boolean update(Produit p)
	{
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement("update produit set designation=? , prix=? , qte=? ,date=? where code = ?");
			pst.setString(1,p.getDesignation());
			pst.setDouble(2,p.getPrix());
			pst.setDouble(3,p.getQte());
			pst.setDate(4, Date.valueOf(p.getDate()));
			pst.setLong(5,p.getCode());
			pst.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
