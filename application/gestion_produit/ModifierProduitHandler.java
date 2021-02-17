package application.gestion_produit;

import java.time.LocalDate;

import com.gestion_produit.IProduitDao;
import com.gestion_produit.Produit;
import com.gestion_produit.ProduitDaoImpl;

public class ModifierProduitHandler {
	
	private  ModifierProductWindow modifierProductWindow;

	public ModifierProduitHandler(ModifierProductWindow modifierProductWindow) {
		
		this.modifierProductWindow=modifierProductWindow;
	}

	public void modifierClick() {
		
		double prix = Double.valueOf(modifierProductWindow.produitPrixTextField.getText());
    	int qte =Integer.valueOf(modifierProductWindow.produitQteTextField.getText());
    	String designation = modifierProductWindow.produitDesignationTextField.getText();
    	LocalDate date = modifierProductWindow.produitDateSaisirDatePicker.getValue();
    	
    	
    		Produit p=new Produit(modifierProductWindow.code,designation,prix,qte,date);
    		
    		IProduitDao pdao = new ProduitDaoImpl();
    		
    		pdao.update(p);
    	
		
	}

}
