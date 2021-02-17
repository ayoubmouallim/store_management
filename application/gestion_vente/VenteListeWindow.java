package application.gestion_vente;

import java.util.ArrayList;
import java.util.List;

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

public class VenteListeWindow {


	private VenteListHandler handler=new VenteListHandler(this);
	private Stage window= new Stage();
	private VBox root = new VBox();
	private Scene scene = new Scene(root);
	private Label titleLabel = new Label("Liste des ventes");
	TableView<VenteContainer> ventesTableView=new TableView<VenteContainer>();
	private TableColumn<VenteContainer,Long> idColumn = new TableColumn<>("Reference");
	private TableColumn<VenteContainer,String> clientColumn = new TableColumn<>("Client");
	private TableColumn<VenteContainer,Double> dateColumn = new TableColumn<>("Date");
	private TableColumn<VenteContainer,Double> totalColumn = new TableColumn<>("Total");
    Button  suppButton = new Button("Supprimer");
    Button  detailButton = new Button("Afficher detail");
	private HBox buttonHbox = new HBox();
	Button actualiserButton = new Button("actualiser");
	Button reglementButton = new Button("Réglement");
	
	 List<VenteContainer> ventesContainer = new ArrayList<>();
	 ObservableList<VenteContainer> ventesObservableList = FXCollections.observableArrayList();
	
	
	private void addNodesToPane() {
		
		buttonHbox.getChildren().addAll(suppButton,detailButton,actualiserButton,reglementButton);
		root.getChildren().addAll(titleLabel,ventesTableView,buttonHbox);
	}
	
	private void addColumnToTableView()
	{
		ventesTableView.getColumns().addAll(idColumn,clientColumn,totalColumn,dateColumn);
		ventesTableView.setItems(ventesObservableList);
	}
	
	private void addStylesToNodes() {
		scene.getStylesheets().add(getClass().getResource("../application.css").toString());
		titleLabel.getStyleClass().add("labelTitle");
		ventesTableView.getStyleClass().add("table-row-cell");
		ventesTableView.setMinWidth(500);
		titleLabel.setMinWidth(window.getWidth());
		buttonHbox.setSpacing(25);
		root.setSpacing(10);
		
		
	}
	
	private void updateColumns()
	{
		idColumn.setCellValueFactory(new PropertyValueFactory("code"));
        idColumn.setPrefWidth(200);
        clientColumn.setCellValueFactory(new PropertyValueFactory("nom"));
        clientColumn.setPrefWidth(200);
        totalColumn.setCellValueFactory(new PropertyValueFactory("total"));
        totalColumn.setPrefWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        dateColumn.setPrefWidth(200);
	}
	
	public void  initWindow()
	{
		window.setScene(scene);
		window.setWidth(900);
		window.setHeight(600);
		window.setTitle("Liste des produits");
		window.getIcons().add(new Image("file:icon.PNG"));
	}
	
	public VenteListeWindow()
	{
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnToTableView();
		handler.updateVenteListWindow();
		handler.addEvent();
		addNodesToPane();
		window.getIcons().add(new Image(getClass().getResourceAsStream("../icon.png")));
		window.show();
	}
	

	
}
