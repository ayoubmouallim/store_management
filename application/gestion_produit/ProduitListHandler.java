package application.gestion_produit;


import java.util.List;

import com.gestion_produit.IProduitDao;
import com.gestion_produit.Produit;
import com.gestion_produit.ProduitDaoImpl;

public class ProduitListHandler {
	
	ProduitsListWindow listWindow;
	
	public ProduitListHandler(ProduitsListWindow listWindow)
	{
		this.listWindow=listWindow;
	}
	public ProduitListHandler()
	{
		
	}

	public void updateProduitsListWindow()
	{
		
		IProduitDao pdao = new ProduitDaoImpl();
		
		List<Produit> list =  pdao.getAll();
		listWindow.produitsObservableList.clear();
		listWindow.produitsObservableList.addAll(list);
		updateTotal();
	}
	
	public void updateTotal()
	{
		double total=0;
		for(Produit p:listWindow.produitsObservableList)
		{
			total+=p.getTotal();
		}
		listWindow.totalLabelValue.setText(total+" MAD ");
	}
	
	public void addEvent()
	{
		listWindow.suppButton.setOnAction(event->{
			Produit p = listWindow.produitsTableView.getSelectionModel().getSelectedItem();
			if(p != null)
			{
				System.out.println(p);
				IProduitDao pdao = new ProduitDaoImpl();
				pdao.delete(p.getCode());
				updateProduitsListWindow();
				
			}
		});
		
		listWindow.modifierButton.setOnAction(event->{
			Produit p = listWindow.produitsTableView.getSelectionModel().getSelectedItem();
			if(p != null)
			{
				
				new ModifierProductWindow(p);
				
			}
		});
		
		listWindow.actualiserButton.setOnAction(event->{
			
			updateProduitsListWindow();
		});
		
		
	}
	
	
	

}
