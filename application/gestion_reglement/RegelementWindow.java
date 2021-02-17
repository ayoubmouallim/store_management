package application.gestion_reglement;

import java.util.ArrayList;
import java.util.List;

import com.gestion_reglement.Paiement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegelementWindow {
	
	ReglementHandler handler=new ReglementHandler(this);
	long id_vente ;
	Stage window= new Stage();
	private HBox root = new HBox();
	private Scene scene = new Scene(root);
	//detail vente et paiement BOX
	VBox venteBox = new VBox();
	Label venteTitleLabel = new Label("Detail du vente");
	// grid
	GridPane detailVenteGrid = new GridPane();
	Label clientLabel = new Label("Client : ");
	Label nomClientLabel = new Label("");
	Label dateVenteLabel = new Label("Date : ");
	Label dateValueLabel = new Label("");
	Label totalLabel = new Label("Total : ");
	Label totalLabelValue = new Label("0.0"); 
	Label totalPayerLabel = new Label("Total payé : ");
	Label totalPayerLabelValue = new Label("0.0");
	Label resteLabel = new Label("Reste : ");
	Label resteLabelValue = new Label("0.0");

	
	Label paiementTitleLabel = new Label("Paiements");
    //table of paiements
	TableView<Paiement> paiementsTableView=new TableView<Paiement>();
	 private TableColumn<Paiement,Long> codeColumn = new TableColumn<>("code");
	 private TableColumn<Paiement,Double> montantColumn = new TableColumn<>("Montant");
	 private TableColumn<Paiement,String> dateColumn = new TableColumn<>("Date");
	 private TableColumn<Paiement,String> typeColumn = new TableColumn<>("Type");
	 ObservableList<Paiement> paiementsObservableList = FXCollections.observableArrayList();
	 List<Paiement> paiementsListe = new ArrayList<>();
	
	//detail Paiment
	VBox paimentBox = new VBox();
	Label detailPaiementTitleLabel = new Label("Detail de paiement");
	Label codePaiementLabel= new Label(" Code:");
	TextField codePaiementTextField = new TextField();
	Label paiementDateLabel= new Label(" Date:");
	DatePicker paiementDatePicker =new DatePicker();
	Label montantPaiementLabel= new Label(" Montant:");
	TextField montantPaiementTextField = new TextField();
	Label typePaiementLabel= new Label(" Type:");
	ChoiceBox typeChoiseBox = new ChoiceBox(FXCollections.observableArrayList("ESPECE", "CHEQUE"));
	Button paiementAddButton=new Button("enregister");
	Button paiementmodifierButton=new Button("modifier");
	Button paiementsupprimerButton=new Button("supprimer");
	
	private void addNodesToPane() {
		paimentBox.getChildren().addAll(detailPaiementTitleLabel,codePaiementLabel,codePaiementTextField,paiementDateLabel,paiementDatePicker);
		paimentBox.getChildren().addAll(montantPaiementLabel,montantPaiementTextField,typePaiementLabel,typeChoiseBox,paiementAddButton,paiementmodifierButton,paiementsupprimerButton);
		
		detailVenteGrid.add(clientLabel, 0, 0);
		detailVenteGrid.add(nomClientLabel, 1, 0);
		detailVenteGrid.add(dateVenteLabel, 0, 1);
		detailVenteGrid.add(dateValueLabel, 1, 1);
		detailVenteGrid.add(totalLabel, 0, 2);
		detailVenteGrid.add(totalLabelValue, 1, 2);
		detailVenteGrid.add(totalPayerLabel, 0, 3);
		detailVenteGrid.add(totalPayerLabelValue, 1, 3);
		detailVenteGrid.add(resteLabel, 0, 4);
		detailVenteGrid.add(resteLabelValue, 1, 4);
		
		venteBox.getChildren().addAll(venteTitleLabel,detailVenteGrid,paiementTitleLabel,paiementsTableView);
		root.getChildren().addAll(venteBox,paimentBox);
		
	}
	
	
	private void updateColumns()
	{
		codeColumn.setCellValueFactory(new PropertyValueFactory("id"));
		codeColumn.setPrefWidth(100);
		montantColumn.setCellValueFactory(new PropertyValueFactory("total"));
		montantColumn.setPrefWidth(150);
		dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
		dateColumn.setPrefWidth(150);
		typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
		typeColumn.setPrefWidth(150);
	        
	}
	
	private void addColumnToTableView()
	{
		paiementsTableView.getColumns().addAll(codeColumn,montantColumn,dateColumn,typeColumn);
		paiementsTableView.setItems(paiementsObservableList);
	}
	
	private void addStylesToNodes() {
		scene.getStylesheets().add(getClass().getResource("../application.css").toString());
		paiementsTableView.getStyleClass().add("table-row-cell");
		venteBox.getStyleClass().add("ventBox");
		paimentBox.getStyleClass().add("ventBox");
		venteTitleLabel.getStyleClass().add("labelTitle");
		venteTitleLabel.setMinWidth(venteBox.getWidth());
		detailPaiementTitleLabel.getStyleClass().add("labelTitle");
		paiementTitleLabel.getStyleClass().add("labelTitle");
		root.setSpacing(15);	
		venteBox.setSpacing(15);
		paimentBox.setSpacing(15);
		detailVenteGrid.setVgap(10);
	}
	
	public void  initWindow()
	{
		window.setScene(scene);
		window.setWidth(850);
		window.setHeight(700);
		window.setTitle("Reglement");
		window.getIcons().add(new Image("file:icon.PNG"));
	}
	
	public RegelementWindow(long id_vente) {
	
		this.id_vente = id_vente;
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnToTableView();
	 	handler.updateVenteListWindow();
		handler.addEvents();
		addNodesToPane();
		window.getIcons().add(new Image(getClass().getResourceAsStream("../icon.png")));
		
		window.show();
		
	}
	
	

}
