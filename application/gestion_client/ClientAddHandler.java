package application.gestion_client;

import com.gestion_client.Client;
import com.gestion_client.ClientDaoImpl;
import com.gestion_client.IClientDao;

public class ClientAddHandler {
	
	FormClientWindow formclientwindow;

	
	public ClientAddHandler(FormClientWindow formclientwindow) {

		this.formclientwindow = formclientwindow;
	}
	
	

	public void addClick() {
		
		String nom = formclientwindow.clientNomTextField.getText();
		String prenom = formclientwindow.clientPrenomTextField.getText();
		String tel = formclientwindow.clientTelTextField.getText();
		String email = formclientwindow.clientEmailTextField.getText();
		String adresse = formclientwindow.clientAdresseTextField.getText();
		
		Client cl=new Client(1,nom,prenom,tel,email,adresse);

		IClientDao clao = new ClientDaoImpl();
		
		clao.add(cl);

	}
	
	

}
