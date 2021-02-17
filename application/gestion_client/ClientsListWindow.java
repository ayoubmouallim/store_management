package application.gestion_client;


import com.gestion_client.Client;

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

public class ClientsListWindow {
	
	private ClientsListHandler handler=new ClientsListHandler(this);
	long id_vente = 0;
	
	private Stage window= new Stage();
	private VBox root = new VBox();
	private Scene scene = new Scene(root);
	private Label titleLabel = new Label("Liste des clients");
	TableView<Client> clientsTableView=new TableView<Client>();
	private TableColumn<Client,Long> idColumn = new TableColumn<>("Id");
	private TableColumn<Client,String> nomColumn = new TableColumn<>("Nome");
	private TableColumn<Client,String> prenomColumn = new TableColumn<>("Prenom");
	private TableColumn<Client,String> emailColumn = new TableColumn<>("Email");
	private TableColumn<Client,String> telColumn = new TableColumn<>("Telephone");
	private TableColumn<Client,String> adresseColumn = new TableColumn<>("Adresse");
	private HBox buttonsBox= new HBox();
	Button  suppButton = new Button("Supprimer");
    Button  modifierButton = new Button("Modifier");
    Button addVenteButton = new Button("Ajouter Vente");
    Button actualiserButton = new Button("actualiser");
	 ObservableList<Client> clientsObservableList = FXCollections.observableArrayList();
	
	
	
	
	private void addNodesToPane() {
		
		buttonsBox.getChildren().addAll(modifierButton,suppButton,addVenteButton,actualiserButton);
		root.getChildren().addAll(titleLabel,clientsTableView,buttonsBox);
	}
	
	private void addColumnToTableView()
	{
		clientsTableView.getColumns().addAll(idColumn,nomColumn,prenomColumn,emailColumn,telColumn,adresseColumn);
		clientsTableView.setItems(clientsObservableList);
	}
	
	private void addStylesToNodes() {
		scene.getStylesheets().add(getClass().getResource("../application.css").toString());
		titleLabel.getStyleClass().add("labelTitle");
		clientsTableView.getStyleClass().add("table-row-cell");
		clientsTableView.setMinWidth(500);
		titleLabel.setMinWidth(window.getWidth());
		buttonsBox.setSpacing(25);
		root.setSpacing(15);
			
		
	}
	
	private void updateColumns()
	{
		idColumn.setCellValueFactory(new PropertyValueFactory("code"));
        idColumn.setPrefWidth(100);
        nomColumn.setCellValueFactory(new PropertyValueFactory("nom"));
        nomColumn.setPrefWidth(150);
        prenomColumn.setCellValueFactory(new PropertyValueFactory("prenom"));
        prenomColumn.setPrefWidth(150);
        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
        emailColumn.setPrefWidth(200);
        telColumn.setCellValueFactory(new PropertyValueFactory("tel"));
        telColumn.setPrefWidth(150);
        adresseColumn.setCellValueFactory(new PropertyValueFactory("adresse"));
        adresseColumn.setPrefWidth(250);
	}
	
	public void  initWindow()
	{
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(550);
		window.setTitle("Liste des clients");
		window.getIcons().add(new Image("file:icon.PNG"));
	}
	
	public ClientsListWindow()
	{
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnToTableView();
		handler.updateClientsListWindow();
		handler.addEvent();
		addNodesToPane();
		window.getIcons().add(new Image(getClass().getResourceAsStream("../icon.png")));
		window.show();
	}
	

}
