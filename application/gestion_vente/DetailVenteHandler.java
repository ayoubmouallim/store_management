package application.gestion_vente;

import java.util.List;

import com.gestion_LigneCom.ILigneComDao;
import com.gestion_LigneCom.Ligne;
import com.gestion_LigneCom.LigneComDaoImpl;
import com.gestion_Vente.IVenteDao;
import com.gestion_Vente.Vente;
import com.gestion_Vente.VenteDaoImpl;
import com.gestion_client.Client;

import application.gestion_produit.LigneContainer;


public class DetailVenteHandler {
	
	DetailVenteWindow detailWindow;

	public DetailVenteHandler(DetailVenteWindow detailWindow) {
	
		this.detailWindow = detailWindow;
	}
	
	public void updateVenteListWindow()
	{
		
		ILigneComDao ldao = new LigneComDaoImpl();
		
		List<Ligne> list =  ldao.gettAll(detailWindow.id_vente);
		detailWindow.lignesContainer.clear();
		for(Ligne l:list)
		{			
			detailWindow.lignesContainer.add(new LigneContainer(l.getCode(),l.getProduit().getDesignation(),l.getProduit().getPrix(),l.getQte(),l.getStotal()));
		}
		
		detailWindow.lignesObservableList.clear();
		detailWindow.lignesObservableList.addAll(detailWindow.lignesContainer);
		
	}
	
	public void initialiserTextFileds()
	{
		IVenteDao vDao=new VenteDaoImpl();
		Vente v=vDao.getOne(detailWindow.id_vente);
		Client cl = v.getClient();
		System.out.println(v);
		if(cl != null ) {
			
			detailWindow.nomClientLabel.setText(cl.getPrenom() +" "+ cl.getNom());
			detailWindow.dateValueLabel.setText(v.getDate()+"");
			detailWindow.totalLabelValue.setText(v.getTotale()+" MAD");
		}
	}
	
	public void addNodesToVenteGridPane()
	{

		initialiserTextFileds();

		detailWindow.detailVenteGrid.add(detailWindow.clientVenteLabel,0, 1);
		detailWindow.detailVenteGrid.add(detailWindow.nomClientLabel, 1, 1);
		detailWindow.detailVenteGrid.add(detailWindow.dateVenteLabel,0, 2);
		detailWindow.detailVenteGrid.add(detailWindow.dateValueLabel, 1, 2);
		detailWindow.detailVenteGrid.setVgap(10);
	}
	
	public void addEvents()
	{
		detailWindow.suppButton.setOnAction(event->{
			
			  LigneContainer lc = detailWindow.lignesTableView.getSelectionModel().getSelectedItem();
			  
			  if(lc != null)
			  {
				  ILigneComDao ldao = new LigneComDaoImpl();
				  ldao.delete(lc.getCode());
				  
				  updateVenteListWindow();
				  initialiserTextFileds();
			  }
			
		});
		
		detailWindow.cancelButton.setOnAction(event->{
			
			detailWindow.window.close();
		});
			
	}
	
	

}
