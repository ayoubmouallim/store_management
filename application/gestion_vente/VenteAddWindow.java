package application.gestion_vente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gestion_LigneCom.Ligne;
import com.gestion_client.Client;
import com.gestion_produit.Produit;

import application.gestion_produit.LigneContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class VenteAddWindow {

	private VenteAddHandler handler = new VenteAddHandler(this);
	
	 Client client;
	private HBox root = new HBox();
	private VBox ventBox=new VBox();
	private Scene scene= new Scene(root);
	 Stage window=new Stage();
	private Label titleVenteLabel = new Label("Detail Vente");
	private Label clientVenteLabel = new Label("Client : ");
	private Label nomClientLabel = new Label("");
	private Label dateVenteLabel = new Label("Date : ");
	 DatePicker dateVenteField = new DatePicker(LocalDate.now());
	private GridPane detailVenteGrid = new GridPane();
	 Label codeProduitLabel = new Label("Code : ");
	 Label codeValueLabel = new Label("");
	 Label designationProduitLabel = new Label("Designation : ");
	 Label designationValueLabel = new Label("");
	 Label prixProduitLabel = new Label("Prix : ");
	 Label prixValueLabel = new Label("");
	 Label qteVenteLabel = new Label("Quantité : ");
	 TextField qteTextField = new TextField();
	 Button addToLigneButton = new Button("+");
	 private GridPane detailProduitGrid = new GridPane();
	 // Total
	 Label totalTitleLabel = new Label("Total de la vente");
	 Label totalLigneLabel = new Label("Total : ");
	 Label totalValueLabel = new Label("0 MAD");
	 private VBox LigneBox=new VBox();
	 // buttons
	 private HBox buttonBox = new HBox();
	  Button saveBox = new Button("enregistrer");
	  Button deleteBox = new Button("supprimer");
	  Button cancelBox = new Button("annuler");
	 
	 private GridPane totalVenteGrid = new GridPane();
	 // tableau liste produit
	 TableView<Produit> produitsTableView=new TableView<Produit>();
	 private TableColumn<Produit,Long> idColumn = new TableColumn<>("Id");
	 private TableColumn<Produit,String> designationColumn = new TableColumn<>("Designation");
	 private TableColumn<Produit,Double> prixColumn = new TableColumn<>("Prix");
	 ObservableList<Produit> produitsObservableList = FXCollections.observableArrayList();
	// tableau liste Ligne
	 TableView<LigneContainer> lignesTableView=new TableView<LigneContainer>();
	 private TableColumn<LigneContainer,Long> codeLigneColumn = new TableColumn<>("code");
	 private TableColumn<LigneContainer,String> designationLigneColumn = new TableColumn<>("Designation");
	 private TableColumn<LigneContainer,Double> prixLigneColumn = new TableColumn<>("Prix");
	 private TableColumn<LigneContainer,Integer> qteLigneColumn = new TableColumn<>("Quantité");
	 private TableColumn<LigneContainer,Double> totalLigneColumn = new TableColumn<>("Total");
	 ObservableList<LigneContainer> lignesObservableList = FXCollections.observableArrayList();
	 List<Ligne> listLigne = new ArrayList<>();
	 List<LigneContainer> listContainer = new ArrayList<>();
	 
    
	 
	 private void addColumnToTableView()
		{
			produitsTableView.getColumns().addAll(idColumn,designationColumn,prixColumn);
			produitsTableView.setItems(produitsObservableList);
           //Ligne			
			lignesTableView.getColumns().addAll(codeLigneColumn,designationLigneColumn,prixLigneColumn,qteLigneColumn,totalLigneColumn);
			lignesTableView.setItems(lignesObservableList);
		} 
	 private void updateColumns()
		{
			idColumn.setCellValueFactory(new PropertyValueFactory("code"));
	        idColumn.setPrefWidth(100);
	        designationColumn.setCellValueFactory(new PropertyValueFactory("designation"));
	        designationColumn.setPrefWidth(150);
	        prixColumn.setCellValueFactory(new PropertyValueFactory("prix"));
	        prixColumn.setPrefWidth(150);
	        // Ligne c
	        codeLigneColumn.setCellValueFactory(new PropertyValueFactory("code"));
	        codeLigneColumn.setPrefWidth(100);
	        designationLigneColumn.setCellValueFactory(new PropertyValueFactory("designation"));
	        designationLigneColumn.setPrefWidth(150);
	        prixLigneColumn.setCellValueFactory(new PropertyValueFactory("prix"));
	        prixLigneColumn.setPrefWidth(100);
	        qteLigneColumn.setCellValueFactory(new PropertyValueFactory("qte"));
	        qteLigneColumn.setPrefWidth(100);
	        totalLigneColumn.setCellValueFactory(new PropertyValueFactory("total"));
	        totalLigneColumn.setPrefWidth(100);
	     
	        
		}

	
	public void initWindow()
	{
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(700);
		window.setTitle("Gestion des Ventes");
		window.getIcons().add(new Image("file:icon.PNG"));
	}
	
	public void addStylesToNodes()
	{
		scene.getStylesheets().add(getClass().getResource("../application.css").toString());
		
		titleVenteLabel.getStyleClass().add("labelTitle");
		titleVenteLabel.setMinWidth(detailVenteGrid.getWidth());
		totalTitleLabel.getStyleClass().add("labelTitle");
		totalTitleLabel.setMinWidth(totalVenteGrid.getWidth());
		
		detailVenteGrid.getStyleClass().add("ventBox");
		detailProduitGrid.getStyleClass().add("ventBox");
		totalVenteGrid.getStyleClass().add("ventBox");
		
		root.setSpacing(15);
				
	}
	
	public void addNodesToVenteGridPane()
	{
		nomClientLabel.setText(client.getPrenom() +" "+ client.getNom());
		detailVenteGrid.add(titleVenteLabel,0, 0);
		detailVenteGrid.add(clientVenteLabel,0, 1);
		detailVenteGrid.add(nomClientLabel, 1, 1);
		detailVenteGrid.add(dateVenteLabel,0, 2);
		detailVenteGrid.add(dateVenteField, 1, 2);
		detailVenteGrid.setVgap(10);
	}
	
	public void addNodesToProduitGridPane()
	{
		detailProduitGrid.add(codeProduitLabel,0, 0);
		detailProduitGrid.add(codeValueLabel, 1, 0);
		detailProduitGrid.add(designationProduitLabel, 0, 1);
		detailProduitGrid.add(designationValueLabel,1, 1);
		detailProduitGrid.add(prixProduitLabel, 0, 2);
		detailProduitGrid.add(prixValueLabel,1, 2);
		detailProduitGrid.add(qteVenteLabel, 0, 3);
		detailProduitGrid.add(qteTextField,1, 3);
		detailProduitGrid.add(addToLigneButton, 1, 4);
		detailProduitGrid.setVgap(10);
	}
	public void addNodesToTotalGridPane()
	{
		totalVenteGrid.add(totalTitleLabel,0, 0);
		totalVenteGrid.add(totalLigneLabel, 0, 1);
		totalVenteGrid.add(totalValueLabel, 1, 1);
		
		totalVenteGrid.setVgap(10);
	}
	
	public void addNodesToWindow()
	{
		buttonBox.getChildren().addAll(saveBox,deleteBox,cancelBox);
		addNodesToVenteGridPane();
		addNodesToProduitGridPane();
		addNodesToTotalGridPane();
		ventBox.getChildren().addAll(detailVenteGrid,detailProduitGrid,produitsTableView);
		LigneBox.getChildren().addAll(totalVenteGrid,lignesTableView,buttonBox);
		root.getChildren().addAll(ventBox,LigneBox);
	
	}
	
	public VenteAddWindow(Client client)
	{
		this.client = client;
		initWindow();
		updateColumns();
		handler.updateProduitsListWindow();
		addColumnToTableView();
		addStylesToNodes();
		addNodesToWindow();
		handler.addEvents();
		window.getIcons().add(new Image(getClass().getResourceAsStream("../icon.png")));
		window.show();
	}
	
}
