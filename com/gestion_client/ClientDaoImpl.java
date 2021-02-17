package com.gestion_client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.DAO.AbstarctDao;



public class ClientDaoImpl extends AbstarctDao implements IClientDao {

	@Override
	public void add(Client c) {
		String sql="insert into client (nom,prenom,tel,email,adresse) values (?,?,?,?,?)";
		
		PreparedStatement pst;
		
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1,c.getNom());
			pst.setString(2,c.getPrenom());
			pst.setString(3,c.getTel());
			pst.setString(4,c.getEmail());
			pst.setString(5,c.getAdresse());
			
			pst.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(long id) {
		String sql="delete from client where code=?";
		try {
		PreparedStatement pst =connection.prepareStatement(sql);
		pst.setLong(1,id);
		pst.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Client getOne(long id) {
		
         String sql="SELECT * from client where code=?";
		
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setLong(1,id);
			ResultSet rs= pst.executeQuery();
			
			
			if(rs.next()) {
				Client c=new Client(rs.getLong("code"),rs.getString("nom"),rs.getString("prenom"),rs.getString("tel"),rs.getString("email"),rs.getString("adresse"));
				return c;
			}
			
		}catch(Exception exp) {
			System.out.println(exp);
		}
		
		return null;
	}

	@Override
	public List<Client> getAll() {
		
		 List<Client> liste=new ArrayList<Client>();
			
		 String sql="SELECT * FROM client";
		 
		 try {
				PreparedStatement pst=connection.prepareStatement(sql);
				
				ResultSet rs= pst.executeQuery();

				while(rs.next())
				{
					liste.add(new Client(rs.getLong("code"),rs.getString("nom"),rs.getString("prenom"),rs.getString("tel"),rs.getString("email"),rs.getString("adresse")));
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		 
		 return liste;
	}

	@Override
	public List<Client> getAll(String nom) {
		
		 List<Client> liste=new ArrayList<Client>();
			
		 String sql="SELECT * FROM client where nom like ?";
		 
		 try {
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setString(1,nom+"%");
				ResultSet rs= pst.executeQuery();

				while(rs.next())
				{
					liste.add(new Client(rs.getLong("code"),rs.getString("nom"),rs.getString("prenom"),rs.getString("tel"),rs.getString("email"),rs.getString("adresse")));
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		 
		 return liste;
	}

	@Override
	public boolean update(Client cl) {
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement("update client set nom=? , prenom=? , email=? ,tel=? ,adresse=?  where code = ?");
			pst.setString(1,cl.getNom());
			pst.setString(2,cl.getPrenom() );
			pst.setString(3,cl.getEmail());
			pst.setString(4,cl.getTel());
			pst.setString(5,cl.getAdresse());
			pst.setLong(6,cl.getCode());
			
			pst.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	
}
