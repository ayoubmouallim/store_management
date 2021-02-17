package application.gestion_client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormClientWindow {
	
	private ClientAddHandler handler=new ClientAddHandler(this);
	
	private VBox root = new VBox();
	private HBox buttons=new HBox();
	private Scene scene= new Scene(root);
	private Stage window=new Stage();
	
	private Label titleLabel=new Label("Ajouter un client :");
	private Label clientNomLabel= new Label(" Nom:");
	 TextField clientNomTextField=new TextField();
	private Label clientPrenomLabel= new Label(" Prenom: ");
	 TextField clientPrenomTextField=new TextField();
	private Label cleintEmailLabel = new Label(" Email :");
	private Label clientTelLabel = new Label(" Telephone :");
	private Label clientAdresseLabel = new Label(" Adresse :");
	 TextField clientEmailTextField=new TextField();
	 TextField clientTelTextField=new TextField();
	 TextField clientAdresseTextField=new TextField();
	
	private Button addButton=new Button("ajouter");
	private Button closeButton=new Button("annuler");
	
	public void initWindow()
	{
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(550);
		window.setTitle("Gestion des clients");
	}
	
	public void addStylesToNodes()
	{
		scene.getStylesheets().add(getClass().getResource("../application.css").toString());
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		clientNomLabel.getStylesheets().add("labelForm");
		clientPrenomLabel.getStylesheets().add("labelForm");
		cleintEmailLabel.getStylesheets().add("labelForm");
		clientTelLabel.getStylesheets().add("labelForm");
		clientAdresseLabel.getStylesheets().add("labelForm");
		root.setSpacing(15);
				
	}
	
   private void addEvents() {
		
	   closeButton.setOnAction(event->{
			
			window.close();
		});
				
		window.setOnCloseRequest(event ->{
			
			event.consume();
		});
		
		// ajouter un nouveau client
		
		addButton.setOnAction(event ->{
        	
			handler.addClick();
		});
	}
	
	public void addNodesToWindow()
	{
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(clientNomLabel,clientPrenomTextField);
		root.getChildren().addAll(clientPrenomLabel,clientNomTextField);
		root.getChildren().addAll(cleintEmailLabel,clientEmailTextField);
		root.getChildren().addAll(clientTelLabel,clientTelTextField);
		root.getChildren().addAll(clientAdresseLabel,clientAdresseTextField);
		
		buttons.getChildren().addAll(addButton,closeButton);
		root.getChildren().add(buttons);
		buttons.setSpacing(15);
	}
	
	public FormClientWindow() {
		
		initWindow();
		addNodesToWindow();
		addStylesToNodes();
		addEvents();
		window.getIcons().add(new Image(getClass().getResourceAsStream("../icon.png")));
		window.show();
		
	}
}
