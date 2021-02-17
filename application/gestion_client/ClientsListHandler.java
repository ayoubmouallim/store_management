package application.gestion_client;


import java.util.List;

import com.gestion_Vente.IVenteDao;
import com.gestion_Vente.VenteDaoImpl;
import com.gestion_client.Client;
import com.gestion_client.ClientDaoImpl;
import com.gestion_client.IClientDao;

import application.gestion_vente.VenteAddWindow;




public class ClientsListHandler {
	
	ClientsListWindow listWindow;
	
	public ClientsListHandler(ClientsListWindow listWindow)
	{
		this.listWindow=listWindow;
	}
	

	public void updateClientsListWindow()
	{
		
		IClientDao clao = new ClientDaoImpl();
		
		List<Client> list=  clao.getAll();
		listWindow.clientsObservableList.clear();
		listWindow.clientsObservableList.addAll(list);
	}


	public void addEvent() {
		
		listWindow.suppButton.setOnAction(event->{
			
			Client cl = listWindow.clientsTableView.getSelectionModel().getSelectedItem();
			if(cl != null)
			{
				System.out.println(cl);
				IClientDao clao = new ClientDaoImpl();
				clao.delete(cl.getCode());
				updateClientsListWindow();
				
			}
		});
		
		
		listWindow.modifierButton.setOnAction(event->{
			
			Client cl = listWindow.clientsTableView.getSelectionModel().getSelectedItem();
			
			
			if(cl != null)
			{
				new ClientModifierWindow(cl);
			}
		});
		
        listWindow.actualiserButton.setOnAction(event->{
			
        	updateClientsListWindow();
		});
		
        listWindow.addVenteButton.setOnAction(event->{
			
			Client cl = listWindow.clientsTableView.getSelectionModel().getSelectedItem();
			
			if(cl != null)
			{
				
				new VenteAddWindow(cl);
				
			}
		});
		
	}
	
	
	

}
