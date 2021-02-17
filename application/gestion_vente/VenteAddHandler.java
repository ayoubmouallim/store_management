package application.gestion_vente;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.gestion_LigneCom.ILigneComDao;
import com.gestion_LigneCom.Ligne;
import com.gestion_LigneCom.LigneComDaoImpl;
import com.gestion_Vente.IVenteDao;
import com.gestion_Vente.Vente;
import com.gestion_Vente.VenteDaoImpl;
import com.gestion_client.Client;
import com.gestion_produit.IProduitDao;
import com.gestion_produit.Produit;
import com.gestion_produit.ProduitDaoImpl;

import application.gestion_produit.LigneContainer;
import application.gestion_produit.ModifierProductWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;



public class VenteAddHandler {
	
	VenteAddWindow addWindow;
	
	public VenteAddHandler(VenteAddWindow addWindow)
	{
		this.addWindow = addWindow;
	}
	
	
	
	public void updateProduitsListWindow()
	{
		
		IProduitDao pdao = new ProduitDaoImpl();
		
		List<Produit> list =  pdao.getAll();
		addWindow.produitsObservableList.clear();
		addWindow.produitsObservableList.addAll(list);
		
	}
	
	public void updateLigneListWindow(Ligne l)
	{
		int indice = productExist(addWindow.listContainer,l.getProduit().getCode());
		if(indice != -1)
		{
			addWindow.listContainer.get(indice).setQte(addWindow.listContainer.get(indice).getQte() + l.getQte());
			addWindow.listContainer.get(indice).setTotal(addWindow.listContainer.get(indice).getTotal() + l.getStotal());
		}
		else {
			
			addWindow.listContainer.add(new LigneContainer(l.getProduit().getCode(),l.getProduit().getDesignation(),l.getProduit().getPrix(),l.getQte(),l.getStotal()));
		}
		addWindow.lignesObservableList.clear();
		addWindow.lignesObservableList.addAll(addWindow.listContainer);
		calculerTotal();
	
	}
	
	//  check if the product is already exist on LigneContainer liste

	public int productExist(List<LigneContainer> liste ,long code)
	{
		for(LigneContainer lc:liste)
		{
			if(lc.getCode() == code)
				return  liste.indexOf(lc);
		}
		return -1;
	}
	
	//  check if the product is already exist on Ligne liste

	public int productExistInListLign(List<Ligne> liste ,long code)
	{
		for(Ligne ln:liste)
		{
			if(ln.getProduit().getCode() == code)
				return  liste.indexOf(ln);
		}
		return -1;
	}
	
	public void addEvents()
	{
		addWindow.produitsTableView.getSelectionModel().selectedItemProperty().addListener(event ->{
			
			Produit p = addWindow.produitsTableView.getSelectionModel().getSelectedItem();
			
			if(p != null)
			{
				addWindow.codeValueLabel.setText(p.getCode()+"");
				addWindow.designationValueLabel.setText(p.getDesignation());
				addWindow.prixValueLabel.setText(p.getPrix()+"");
				addWindow.qteTextField.setText("0");
			}
			
		});
		
		addWindow.addToLigneButton.setOnAction(event->{
			
			Produit p = addWindow.produitsTableView.getSelectionModel().getSelectedItem();
			if(p != null)
			{
				int qte = Integer.valueOf(addWindow.qteTextField.getText());
				// check if the product is already exist 
				
				long code = (long)( Math.random()*100 +  ( Math.random()*10 +1));
				Ligne l=new Ligne(code,qte,p); 	
				
				int indice =productExistInListLign(addWindow.listLigne,l.getProduit().getCode()) ;
				
				if(indice != -1)
				{
					addWindow.listLigne.get(indice).setQte(addWindow.listLigne.get(indice).getQte() + l.getQte());
				}
				else {
									
					addWindow.listLigne.add(l);
				}
				updateLigneListWindow(l);
				
			}
		});
		
	   addWindow.cancelBox.setOnAction(event->{
		   addWindow.window.close();
	   });
	   
	   addWindow.saveBox.setOnAction(event->{
		  
		   IVenteDao venteDao = new VenteDaoImpl();
		   
		   LocalDate date = addWindow.dateVenteField.getValue();
		   Vente v = new Vente(1,date,addWindow.client);
		   v.setLignes(addWindow.listLigne);
		   
		   venteDao.add(v);
		   addWindow.window.close();
		   
		   
	   });
	   
	   addWindow.deleteBox.setOnAction(event->{
		  
		   LigneContainer lc = addWindow.lignesTableView.getSelectionModel().getSelectedItem();
			if(lc != null)
			{
				int indice1 = productExist(addWindow.listContainer,lc.getCode());
				int indice2 = productExistInListLign(addWindow.listLigne,lc.getCode());
				if(indice1 != -1 && indice2 != -1)
				{					
				addWindow.listContainer.remove(indice1);
				addWindow.listLigne.remove(indice2);
				
				addWindow.lignesObservableList.clear();
				addWindow.lignesObservableList.addAll(addWindow.listContainer);
				calculerTotal();
				}
				
			}
		   
	   });
	   
		
	}
	
	public void  calculerTotal()
	{
		double total = 0;
		for(LigneContainer lc:addWindow.lignesObservableList)
		{
			total+=lc.getTotal();
		}
		addWindow.totalValueLabel.setText(total + " MAD");
			
	}
	
	

}
