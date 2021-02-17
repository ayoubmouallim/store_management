package application.gestion_produit;


import com.gestion_produit.Produit;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifierProductWindow {
	
	private ModifierProduitHandler handler=new ModifierProduitHandler(this);
	
	long code=1;
	private VBox root = new VBox();
	private HBox buttonsBox= new HBox();
	private Scene scene=new Scene(root);
	private Stage window= new Stage();
	private Label titleLabel= new Label(" Modifier un produit");
	private Label produitDesignationLabel= new Label(" Designation:");
	TextField produitDesignationTextField = new TextField();
	private Label produitPrixLabel= new Label(" Prix:");
	TextField produitPrixTextField = new TextField();
	private Label produitQteLabel= new Label(" Quantité:");
	TextField produitQteTextField = new TextField();
	private Label produitDateLabel= new Label(" Date:");
	DatePicker produitDateSaisirDatePicker =new DatePicker();
	Button produitModifierButton=new Button("modifier");
	Button produitCancelButton=new Button("annuler");
	
	public ModifierProductWindow(Produit p)
	{
		code = p.getCode();
		produitDesignationTextField.setText(p.getDesignation());
		produitPrixTextField.setText(p.getPrix()+"");
		produitQteTextField.setText(p.getQte()+"");
		produitDateSaisirDatePicker.setValue(p.getDate());
	    
		initWindow();
		addStylesToNodes();
		addNodesToWindow();
		addEvents();
		
		window.show();
	}
	
	
	private void initWindow()
	{
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(550);
		window.setTitle(" Modifier un produit");
		window.getIcons().add(new Image("file:icon.PNG"));
		window.initModality(Modality.APPLICATION_MODAL);
		
	}
	
	private void addNodesToWindow()
	{
		
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(produitDesignationLabel,produitDesignationTextField);
		root.getChildren().addAll(produitPrixLabel,produitPrixTextField);
		root.getChildren().addAll(produitQteLabel,produitQteTextField);
		root.getChildren().addAll(produitDateLabel,produitDateSaisirDatePicker);
		
		buttonsBox.getChildren().addAll(produitModifierButton,produitCancelButton);
		root.getChildren().add(buttonsBox);
	}
	
	private void addStylesToNodes() {
		scene.getStylesheets().add(getClass().getResource("../application.css").toString());
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		produitDesignationLabel.getStylesheets().add("labelForm");
		produitPrixLabel.getStylesheets().add("labelForm");
		produitQteLabel.getStylesheets().add("labelForm");
		produitDateLabel.getStylesheets().add("labelForm");
		root.setSpacing(15);
		buttonsBox.setSpacing(50);
	}
		
public void addEvents() {
		
    	produitCancelButton.setOnAction(event->{
			
    	window.close();
		});

    	window.setOnCloseRequest(event ->{
			
			event.consume();
		});
		
		// ajouter un nouveau produit
		
    	produitModifierButton.setOnAction(event ->{
        	
        	handler.modifierClick();
        	window.close();
			
		});
	}
	
	private ModifierProductWindow()
	{
		initWindow();
		addStylesToNodes();
		addNodesToWindow();
		addEvents();
		window.getIcons().add(new Image(getClass().getResourceAsStream("../icon.png")));
		window.show();
	}
}
