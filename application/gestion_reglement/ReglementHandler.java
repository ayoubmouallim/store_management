package application.gestion_reglement;

import java.time.LocalDate;
import java.util.List;

import com.gestion_Vente.IVenteDao;
import com.gestion_Vente.Vente;
import com.gestion_Vente.VenteDaoImpl;
import com.gestion_reglement.IPaiementDao;
import com.gestion_reglement.Paiement;
import com.gestion_reglement.PaiementDaoImpl;


public class ReglementHandler {

	RegelementWindow reglementWindow;

	
	
	public void updateVenteListWindow()
	{

		IPaiementDao padao = new PaiementDaoImpl();
		
		List<Paiement> list =  padao.getAll(reglementWindow.id_vente);
		
		reglementWindow.paiementsObservableList.clear();
		reglementWindow.paiementsObservableList.addAll(list);
		setDetailVente();
	}
	
	public void addEvents()
	{
		reglementWindow.paiementAddButton.setOnAction(event->{
			
			LocalDate date=reglementWindow.paiementDatePicker.getValue();
			String type=reglementWindow.typeChoiseBox.getValue().toString();
			Double total=verifierMontant();
            Double reste = Double.valueOf(reglementWindow.resteLabelValue.getText());
            if(reste != 0)
            {
            	
			Paiement p=new Paiement(1,total,type,(new VenteDaoImpl()).getOne(reglementWindow.id_vente),date);
			IPaiementDao padao = new PaiementDaoImpl();
			
			padao.create(p);
			
            }
			updateVenteListWindow();
			viderTextField();
		});
		
		reglementWindow.paiementsTableView.getSelectionModel().selectedItemProperty().addListener(event->{
			
           Paiement p = reglementWindow.paiementsTableView.getSelectionModel().getSelectedItem();
           
           if(p != null)
           {
        	reglementWindow.codePaiementTextField.setText(p.getId()+"");
        	reglementWindow.paiementDatePicker.setValue(p.getDate());
       		reglementWindow.typeChoiseBox.setValue(p.getType());
       		reglementWindow.montantPaiementTextField.setText(p.getTotal()+"");
       		
           }
            		
		});
		
		reglementWindow.paiementmodifierButton.setOnAction(event->{
			
			LocalDate date=reglementWindow.paiementDatePicker.getValue();
			String type=reglementWindow.typeChoiseBox.getValue().toString();
			Double total=Double.valueOf((reglementWindow.montantPaiementTextField.getText()));
            Double reste = Double.valueOf(reglementWindow.resteLabelValue.getText());
            Long id = Long.valueOf(reglementWindow.codePaiementTextField.getText());
            
            IPaiementDao padao = new PaiementDaoImpl();
            Paiement oldPaiement = padao.getOne(id);
            
            if(total <= reste + oldPaiement.getTotal() )
            {	
			Paiement newPaiment=new Paiement(id,total,type,(new VenteDaoImpl()).getOne(reglementWindow.id_vente),date);
			
			padao.update(newPaiment);
			
            }
			updateVenteListWindow();
			viderTextField();
			
		});
		
		reglementWindow.paiementsupprimerButton.setOnAction(event->{
			
			  Paiement p = reglementWindow.paiementsTableView.getSelectionModel().getSelectedItem();
	           
	           if(p != null)
	           {
	        	   IPaiementDao padao = new PaiementDaoImpl();
	        	   padao.delete(p.getId());
	        	   updateVenteListWindow();
	           }
		});
		
	}
	
	public void viderTextField()
	{
		reglementWindow.paiementDatePicker.setValue(null);
		reglementWindow.typeChoiseBox.setValue(null);
		reglementWindow.montantPaiementTextField.setText("");
		reglementWindow.codePaiementTextField.setText("");
		
	}
	
	public double verifierMontant()
	{
		Double montant=Double.valueOf(reglementWindow.montantPaiementTextField.getText());
		Double reste = Double.valueOf(reglementWindow.resteLabelValue.getText());
		
		if(montant > reste)
			montant = reste;
			
		return montant;	
	}
	
	public void  setDetailVente()
	{
		IVenteDao vdao = new VenteDaoImpl();
		Vente v = vdao.getOne(reglementWindow.id_vente);
		reglementWindow.nomClientLabel.setText(v.getClient().getNom());
		reglementWindow.dateValueLabel.setText(v.getDate().toString());
		reglementWindow.totalLabelValue.setText(v.getTotale()+"");
		double totalPayer = culculerTotalPayer();
		reglementWindow.totalPayerLabelValue.setText(totalPayer+"");
		reglementWindow.resteLabelValue.setText(v.getTotale()-totalPayer+"");
	}
	
	public double culculerTotalPayer()
	{
		double t=0;
        IPaiementDao padao = new PaiementDaoImpl();
		List<Paiement> list =  padao.getAll(reglementWindow.id_vente);
		for(Paiement p:list)
		{
			t+=p.getTotal();
		}
		return t;
	}
	
	public ReglementHandler(RegelementWindow reglementWindow) {
		this.reglementWindow = reglementWindow;
	}
	
}
