package application.gestion_produit;

import java.time.LocalDate;


import com.gestion_produit.IProduitDao;
import com.gestion_produit.Produit;
import com.gestion_produit.ProduitDaoImpl;

public class ProduitAddHandler {
	
	FormProductWindow formproductwindow;

	
	public ProduitAddHandler(FormProductWindow formproductwindow) {

		this.formproductwindow = formproductwindow;
	}
	
	

	public void addClick() {
		
		double prix = Double.valueOf(formproductwindow.produitPrixTextField.getText());
    	int qte =Integer.valueOf(formproductwindow.produitQteTextField.getText());
    	String designation = formproductwindow.produitDesignationTextField.getText();
    	LocalDate date = formproductwindow.produitDateSaisirDatePicker.getValue();
    	
    	
    		Produit p=new Produit(1,designation,prix,qte,date);
    		
    		IProduitDao pdao = new ProduitDaoImpl();
    		
    		pdao.add(p);
    	
		
	}
	

	

}
