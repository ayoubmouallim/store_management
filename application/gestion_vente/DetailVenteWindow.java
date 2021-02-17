package application.gestion_vente;

import java.util.ArrayList;
import java.util.List;


import application.gestion_produit.LigneContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailVenteWindow {
	
	long id_vente;
    private DetailVenteHandler handler =new  DetailVenteHandler(this); 

	 Stage window= new Stage();
	private VBox root = new VBox();
	private Scene scene = new Scene(root);
	private Label titleLabel = new Label("Detail du client");
	 TableView<LigneContainer> lignesTableView=new TableView<LigneContainer>();
	 private TableColumn<LigneContainer,Long> codeLigneColumn = new TableColumn<>("code");
	 private TableColumn<LigneContainer,String> designationLigneColumn = new TableColumn<>("Designation");
	 private TableColumn<LigneContainer,Double> prixLigneColumn = new TableColumn<>("Prix");
	 private TableColumn<LigneContainer,Integer> qteLigneColumn = new TableColumn<>("Quantité");
	 private TableColumn<LigneContainer,Double> totalLigneColumn = new TableColumn<>("Total");
	 ObservableList<LigneContainer> lignesObservableList = FXCollections.observableArrayList();
	 List<LigneContainer> lignesContainer = new ArrayList<>();
	
	  GridPane detailVenteGrid = new GridPane();
	  Label clientVenteLabel = new Label("Client : ");
	  Label nomClientLabel = new Label("");
	  Label dateVenteLabel = new Label("Date : ");
	  Label dateValueLabel = new Label("");
		
	private HBox buttonsBox= new HBox();
	Button  suppButton = new Button("Supprimer");
    Button cancelButton = new Button("annuler");
    //total vente
    private HBox totalHbox = new HBox();
    private Label totalLabel = new Label("Total : ");
	Label totalLabelValue = new Label("0.0"); 
	
	
	
	
	private void addNodesToPane() {
		totalHbox.getChildren().addAll(totalLabel,totalLabelValue);
		buttonsBox.getChildren().addAll(suppButton,cancelButton);
		root.getChildren().addAll(titleLabel,detailVenteGrid,lignesTableView,totalHbox,buttonsBox);
	}
	
	private void addColumnToTableView()
	{
		lignesTableView.getColumns().addAll(codeLigneColumn,designationLigneColumn,prixLigneColumn,qteLigneColumn,totalLigneColumn);
		lignesTableView.setItems(lignesObservableList);
	}
	
	private void addStylesToNodes() {
		scene.getStylesheets().add(getClass().getResource("../application.css").toString());
		titleLabel.getStyleClass().add("labelTitle");
		lignesTableView.getStyleClass().add("table-row-cell");
		lignesTableView.setMinWidth(500);
		totalHbox.getStyleClass().add("boxTotal");
		totalLabel.getStyleClass().add("labelTotal");
		totalLabelValue.getStyleClass().add("labelTotal");
		titleLabel.setMinWidth(window.getWidth());
		totalHbox.setSpacing(15);
		buttonsBox.setSpacing(25);
		root.setSpacing(15);
			
		
	}
	

	
	private void updateColumns()
	{
		    codeLigneColumn.setCellValueFactory(new PropertyValueFactory("code"));
	        codeLigneColumn.setPrefWidth(150);
	        designationLigneColumn.setCellValueFactory(new PropertyValueFactory("designation"));
	        designationLigneColumn.setPrefWidth(250);
	        prixLigneColumn.setCellValueFactory(new PropertyValueFactory("prix"));
	        prixLigneColumn.setPrefWidth(150);
	        qteLigneColumn.setCellValueFactory(new PropertyValueFactory("qte"));
	        qteLigneColumn.setPrefWidth(150);
	        totalLigneColumn.setCellValueFactory(new PropertyValueFactory("total"));
	        totalLigneColumn.setPrefWidth(200);
	}
	
	public void  initWindow()
	{
		window.setScene(scene);
		window.setWidth(900);
		window.setHeight(700);
		window.setTitle("Liste des lignes");
		window.getIcons().add(new Image("file:icon.PNG"));
	}
	
	public DetailVenteWindow(long id_vente) {
		
		this.id_vente = id_vente;
		
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnToTableView();
        handler.addNodesToVenteGridPane();
	 	handler.updateVenteListWindow();
		handler.addEvents();
		addNodesToPane();
		window.getIcons().add(new Image(getClass().getResourceAsStream("../icon.png")));
		window.show();
	}
	

}
