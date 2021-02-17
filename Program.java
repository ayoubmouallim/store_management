

import java.util.List;

import com.gestion_produit.IProduitDao;
import com.gestion_produit.Produit;
import com.gestion_produit.ProduitDaoImpl;

import com.gestion_client.IClientDao;
import com.gestion_client.Client;
import com.gestion_client.ClientDaoImpl;

public class Program {

	public static void main(String[] args) {
	
		IProduitDao pdao = new ProduitDaoImpl();
		
		IClientDao clao = new ClientDaoImpl();
		//pdao.delete(21);
		
		List<Produit> liste=pdao.getAll();
		
		
		
		/*for(Produit p:liste)
		{
			System.out.println(p);
		}
		
		Produit p=pdao.getOne(19);
		
		System.out.println(p);
		*/
		
		System.out.println("------------- ajouter client 1    ------------");
		Client cl=new Client(3,"maslah","amine","+212691754831","maslah@gmail.com","ainchok");
		clao.add(cl);
		
		System.out.println("-------------supprimer un client -----------");
		clao.delete(2);
		System.out.println("-------------la liste des clients -----------");
		
		List<Client> liste2=clao.getAll();
		
		for(Client c:liste2)
		{
			System.out.println(c);
		}
		
	}

}
