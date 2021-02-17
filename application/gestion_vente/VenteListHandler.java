package application.gestion_vente;

import java.util.ArrayList;
import java.util.List;

import com.gestion_Vente.IVenteDao;
import com.gestion_Vente.Vente;
import com.gestion_Vente.VenteDaoImpl;


import application.gestion_reglement.RegelementWindow;


public class VenteListHandler {
	
    private VenteListeWindow venteWindow ;

	public VenteListHandler(VenteListeWindow venteWindow) {
		
		this.venteWindow = venteWindow;
	}
    
	public void updateVenteListWindow()
	{
		
		IVenteDao vdao = new VenteDaoImpl();
		
		List<Vente> list =  vdao.getAll();
		venteWindow.ventesContainer.clear();
		for(Vente v:list)
		{			
			venteWindow.ventesContainer.add(new VenteContainer(v.getCode(),v.getClient().getPrenom() +" "+ v.getClient().getNom(),v.getTotale(),v.getDate()));
		}
		
		venteWindow.ventesObservableList.clear();
		venteWindow.ventesObservableList.addAll(venteWindow.ventesContainer);
		
	}
	
	public void addEvent()
	{
		
		venteWindow.suppButton.setOnAction(event->{
			
			VenteContainer vc = venteWindow.ventesTableView.getSelectionModel().getSelectedItem();
			if(vc != null)
			{
				System.out.println(vc);
				IVenteDao vdao = new VenteDaoImpl();
				vdao.delete(vc.getCode());
				updateVenteListWindow();
				
			}
		});
		
		venteWindow.actualiserButton.setOnAction(event->{
			
			venteWindow.ventesObservableList.clear();
			venteWindow.ventesObservableList.addAll(venteWindow.ventesContainer);
			});
		
       venteWindow.detailButton.setOnAction(event->{
			
    	   VenteContainer vc = venteWindow.ventesTableView.getSelectionModel().getSelectedItem();
    		if(vc != null)
    		{
    			System.out.println(vc);
    			new DetailVenteWindow(vc.getCode());
    		}
		});
       
       venteWindow.reglementButton.setOnAction(event->{
			
    	   VenteContainer vc = venteWindow.ventesTableView.getSelectionModel().getSelectedItem();
    		if(vc != null)
    		{
    			System.out.println(vc);
    			new RegelementWindow(vc.getCode());
    		}
		});
	}
    

}
