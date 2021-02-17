package application;
	
import application.gestion_client.ClientsListWindow;
import application.gestion_client.FormClientWindow;
import application.gestion_produit.FormProductWindow;
import application.gestion_produit.ProduitsListWindow;
import application.gestion_vente.VenteListeWindow;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;


public class Main extends Application {
	
	private  BorderPane root = new BorderPane();
	private  Scene scene = new Scene(root,1100,650);
	private MenuItem nouveauProductsMenuItem = new MenuItem("Nouveau"); 
	private MenuItem listeProductsMenuItem = new MenuItem("Liste");
	
	private MenuItem nouveauClientsMenuItem = new MenuItem("Nouveau"); 
	private MenuItem listeClientsMenuItem = new MenuItem("Liste");
	private Label copyrithLabel = new Label("Copyright ©2021 AYOUB MOUALLIM. All rights reserved.");
	private MenuItem listeVentesMenuItem = new MenuItem("Liste");
	
	private MenuItem helpMenuItem = new MenuItem("?");

	
	private void createMenu()
	{
		MenuBar menuBar = new MenuBar();
		
		Menu productsMenu = new Menu("Produits");
		Menu clientsMenu = new Menu("Clients");
		Menu ventesMenu = new Menu("Ventes");
		
		
		productsMenu.getItems().addAll(nouveauProductsMenuItem,listeProductsMenuItem);
		clientsMenu.getItems().addAll(nouveauClientsMenuItem,listeClientsMenuItem);
		ventesMenu.getItems().add(listeVentesMenuItem);
		
		
		menuBar.getMenus().addAll(productsMenu,clientsMenu,ventesMenu);
		
		root.setBottom(copyrithLabel);
		root.setTop(menuBar);
	}
	
	
	
	private void addStylesToNodes() {
		scene.getStylesheets().add("application/application.css");
		root.getStyleClass().add("mainWindow");
		copyrithLabel.getStyleClass().add("footer");
		copyrithLabel.setMinWidth(scene.getWidth());
	}
	
	private void addEvents() {
		//lambda en java 8 (sans passer par interfaces--imple-redifinie)
		nouveauProductsMenuItem.setOnAction(event ->{
			new FormProductWindow();
		});
		nouveauClientsMenuItem.setOnAction(event ->{
			new FormClientWindow();
		});
		listeProductsMenuItem.setOnAction(event->{
			new ProduitsListWindow();
		});
		listeClientsMenuItem.setOnAction(event->{
			new ClientsListWindow();
		});
		listeVentesMenuItem.setOnAction(event->{
			new VenteListeWindow();
		});
	}
	
	@Override
	public void start(Stage window) throws Exception {
	  
		createMenu();
		addEvents();
		
	  addStylesToNodes();
	  window.setScene(scene);
	  window.setTitle("Gestion du magasin");
	  window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
	  window.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
}
