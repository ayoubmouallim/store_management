package application.gestion_client;

import java.time.LocalDate;

import com.gestion_client.Client;
import com.gestion_client.ClientDaoImpl;
import com.gestion_client.IClientDao;
import com.gestion_produit.IProduitDao;
import com.gestion_produit.Produit;
import com.gestion_produit.ProduitDaoImpl;

public class ClientModifierHandler {
	
	private  ClientModifierWindow clientmodifierWindow;

	public ClientModifierHandler(ClientModifierWindow clientmodifierWindow) {
		
		this.clientmodifierWindow=clientmodifierWindow;
	}

	public void modifierClick() {
		
		
		    String  nom = clientmodifierWindow.clientNomTextField.getText();
		    String prenom = clientmodifierWindow.clientPrenomTextField.getText();
		    String email = clientmodifierWindow.clientEmailTextField.getText();
		    String tel = clientmodifierWindow.clientTelTextField.getText();
		    String adresse = clientmodifierWindow.clientAdresseTextField.getText();
    	    long code = clientmodifierWindow.getCode();
    	
            Client cl = new Client(code,nom,prenom,tel,email,adresse);
    		
    		IClientDao clao = new ClientDaoImpl();
    		
    		clao.update(cl);
	}

}
