package application.gestion_produit;


import com.gestion_produit.Produit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProduitsListWindow {
	
	private ProduitListHandler handler=new ProduitListHandler(this);
	private Stage window= new Stage();
	private VBox root = new VBox();
	private Scene scene = new Scene(root);
	private Label titleLabel = new Label("Liste des produits");
	TableView<Produit> produitsTableView=new TableView<Produit>();
	private TableColumn<Produit,Long> idColumn = new TableColumn<>("Id");
	private TableColumn<Produit,String> designationColumn = new TableColumn<>("Designation");
	private TableColumn<Produit,Double> prixColumn = new TableColumn<>("Prix");
	private TableColumn<Produit,Integer> qteColumn = new TableColumn<>("Quantity");
	private TableColumn<Produit,Double> totalColumn = new TableColumn<>("Total");
	private TableColumn<Produit,Double> dateColumn = new TableColumn<>("Date");
    Button  suppButton = new Button("Supprimer");
    Button  modifierButton = new Button("Modifier");
	private HBox totalHbox = new HBox();
	private HBox buttonHbox = new HBox();
	private Label totalLabel = new Label("Total : ");
	Label totalLabelValue = new Label("0.0"); 
	Button actualiserButton = new Button("actualiser");
	
	 ObservableList<Produit> produitsObservableList = FXCollections.observableArrayList();
	
	
	private void addNodesToPane() {
		
		buttonHbox.getChildren().addAll(suppButton,modifierButton,actualiserButton);
		totalHbox.getChildren().addAll(totalLabel,totalLabelValue);
		root.getChildren().addAll(titleLabel,produitsTableView,totalHbox,buttonHbox);
	}
	
	private void addColumnToTableView()
	{
		produitsTableView.getColumns().addAll(idColumn,designationColumn,prixColumn,qteColumn,totalColumn,dateColumn);
		produitsTableView.setItems(produitsObservableList);
	}
	
	private void addStylesToNodes() {
		scene.getStylesheets().add(getClass().getResource("../application.css").toString());
		titleLabel.getStyleClass().add("labelTitle");
		totalLabel.getStyleClass().add("labelTotal");
		totalLabelValue.getStyleClass().add("labelTotal");
		totalHbox.getStyleClass().add("boxTotal");
		produitsTableView.getStyleClass().add("table-row-cell");
		produitsTableView.setMinWidth(500);
		titleLabel.setMinWidth(window.getWidth());
		totalHbox.setSpacing(15);
		buttonHbox.setSpacing(25);
		root.setSpacing(10);
		
		
	}
	
	private void updateColumns()
	{
		idColumn.setCellValueFactory(new PropertyValueFactory("code"));
        idColumn.setPrefWidth(100);
        designationColumn.setCellValueFactory(new PropertyValueFactory("designation"));
        designationColumn.setPrefWidth(250);
        prixColumn.setCellValueFactory(new PropertyValueFactory("prix"));
        prixColumn.setPrefWidth(150);
        qteColumn.setCellValueFactory(new PropertyValueFactory("qte"));
        qteColumn.setPrefWidth(100);
        totalColumn.setCellValueFactory(new PropertyValueFactory("total"));
        totalColumn.setPrefWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        dateColumn.setPrefWidth(200);
	}
	
	public void  initWindow()
	{
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Liste des produits");
		window.getIcons().add(new Image("file:icon.PNG"));
	}
	
	public ProduitsListWindow()
	{
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnToTableView();
		handler.updateProduitsListWindow();
		handler.addEvent();
		addNodesToPane();
		window.getIcons().add(new Image(getClass().getResourceAsStream("../icon.png")));
		window.show();
	}
	

}
